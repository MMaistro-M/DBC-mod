/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data.action;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.controllers.data.action.Action;
import noppes.npcs.controllers.data.action.ActionQueue;

public class ActionThread {
    private Action action;
    private final Object lock = new Object();
    private Thread thread;
    ExecutorService executor;
    private final Queue<Future<?>> runningTasks = new ConcurrentLinkedQueue();
    private final Set<Object> runningKeys = ConcurrentHashMap.newKeySet();
    private volatile boolean threadPaused;
    private volatile boolean threadSleeping;
    private Function<IAction, Boolean> pauseUntil;

    public ActionThread(Action action) {
        this.action = action;
        this.executor = Executors.newSingleThreadExecutor(r -> {
            this.thread = new Thread(r);
            this.thread.setName(String.format("ActionThread (%s)", action.getName()));
            return this.thread;
        });
        if (action.manager.debug) {
            action.manager.LOGGER.log("Created Action thread!", this);
        }
    }

    public void stop() {
        this.runningTasks.forEach(task -> {
            if (!task.isDone()) {
                task.cancel(true);
            }
        });
        this.executor.shutdown();
        if (this.action.manager.debug) {
            this.action.manager.LOGGER.log("Stopping thread", this);
        }
    }

    public Action getAction() {
        return this.action;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void pauseFor(long millis) {
        if (this.isPaused() || !this.inActionThread()) {
            return;
        }
        try {
            this.threadSleeping = true;
            if (this.action.manager.debug) {
                this.action.manager.LOGGER.log(String.format("Sleeping thread for %s ticks", millis / 50L), this);
            }
            Thread.sleep(millis);
            this.threadSleeping = false;
            if (!this.action.manager.debug) return;
        }
        catch (InterruptedException e) {
            try {
                Thread.currentThread().interrupt();
                this.threadSleeping = false;
                if (!this.action.manager.debug) return;
            }
            catch (Throwable throwable) {
                this.threadSleeping = false;
                if (!this.action.manager.debug) throw throwable;
                this.action.manager.LOGGER.log(String.format("Woken up thread after sleeping for %s ticks", millis / 50L), this);
                throw throwable;
            }
            this.action.manager.LOGGER.log(String.format("Woken up thread after sleeping for %s ticks", millis / 50L), this);
            return;
        }
        this.action.manager.LOGGER.log(String.format("Woken up thread after sleeping for %s ticks", millis / 50L), this);
        return;
    }

    public void pauseFor(int ticks) {
        this.pauseFor((long)ticks * 50L);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void pause() {
        if (!this.inActionThread() || this.isPaused()) {
            return;
        }
        this.threadPaused = true;
        Object object = this.lock;
        synchronized (object) {
            while (this.threadPaused) {
                try {
                    if (this.action.manager.debug) {
                        this.action.manager.LOGGER.log(String.format("Pausing thread", new Object[0]), this);
                    }
                    this.lock.wait();
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void pauseUntil(Function<IAction, Boolean> until) {
        this.pauseUntil = until;
        this.pause();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void resume() {
        Object object = this.lock;
        synchronized (object) {
            if (this.threadPaused) {
                this.threadPaused = false;
                this.lock.notify();
                if (this.action.manager.debug) {
                    this.action.manager.LOGGER.log(String.format("Resumed thread", new Object[0]), this);
                }
            }
            if (this.threadSleeping) {
                this.thread.interrupt();
            }
        }
    }

    public boolean isTaskRunning(String taskKey) {
        return this.runningKeys.contains(taskKey);
    }

    public boolean inActionThread() {
        return Thread.currentThread() == this.thread;
    }

    public boolean isPaused() {
        return this.threadPaused || this.threadSleeping;
    }

    public boolean isThreadRunning() {
        return this.runningTasks.stream().anyMatch(task -> !task.isDone());
    }

    public void execute(String taskKey, Runnable task) {
        if (this.threadPaused && this.pauseUntil != null && this.pauseUntil.apply(this.action).booleanValue()) {
            this.resume();
        }
        if (!this.runningKeys.add(taskKey)) {
            return;
        }
        if (this.action.manager.debug) {
            this.action.manager.LOGGER.log(String.format("Delegating task '%s' to Action thread...", taskKey), this.action);
        }
        AtomicReference taskRef = new AtomicReference();
        taskRef.set(this.executor.submit(() -> {
            if (this.action.manager.debug) {
                this.action.manager.LOGGER.log(String.format("Started executing task '%s' on Action thread...", taskKey), this);
            }
            this.runningTasks.add((Future<?>)taskRef.get());
            try {
                task.run();
            }
            catch (Throwable throwable) {
                this.runningTasks.remove(taskRef.get());
                this.runningKeys.remove(taskKey);
                if (this.action.manager.debug) {
                    this.action.manager.LOGGER.log(String.format("Finished executing task '%s' on Action thread", taskKey), this);
                }
                if (((ActionQueue)this.action.queue).finish(this.action)) {
                    this.action.queue.getQueue().remove(this.action);
                }
                throw throwable;
            }
            this.runningTasks.remove(taskRef.get());
            this.runningKeys.remove(taskKey);
            if (this.action.manager.debug) {
                this.action.manager.LOGGER.log(String.format("Finished executing task '%s' on Action thread", taskKey), this);
            }
            if (((ActionQueue)this.action.queue).finish(this.action)) {
                this.action.queue.getQueue().remove(this.action);
            }
        }));
    }
}

