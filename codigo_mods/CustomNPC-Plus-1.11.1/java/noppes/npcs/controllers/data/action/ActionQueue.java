/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data.action;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;
import noppes.npcs.api.handler.IActionManager;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.IActionChain;
import noppes.npcs.api.handler.data.IActionQueue;
import noppes.npcs.controllers.data.action.Action;
import noppes.npcs.controllers.data.action.ActionManager;
import noppes.npcs.controllers.data.action.chain.ActionChain;

public class ActionQueue
implements IActionQueue {
    protected final Deque<IAction> queue = new ConcurrentLinkedDeque<IAction>();
    protected final ActionManager manager;
    protected final String name;
    protected boolean isWorking = true;
    protected boolean isParallel;
    protected boolean isDead;
    protected boolean stopWhenEmpty;
    protected boolean killWhenEmpty;
    protected int killWhenEmptyAfter = 100;
    protected Action autoKill;

    public ActionQueue(ActionManager manager, String name) {
        this.manager = manager;
        this.name = name;
    }

    public ActionQueue(ActionManager manager, String name, boolean isParallel) {
        this(manager, name);
        this.isParallel = isParallel;
    }

    @Override
    public IActionQueue start() {
        this.isWorking = true;
        return this;
    }

    @Override
    public IActionQueue stop() {
        this.isWorking = false;
        return this;
    }

    @Override
    public IActionManager getManager() {
        return this.manager;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Queue<IAction> getQueue() {
        return this.queue;
    }

    @Override
    public boolean isParallel() {
        return this.isParallel;
    }

    @Override
    public IActionQueue setParallel(boolean parallel) {
        this.isParallel = parallel;
        return this;
    }

    @Override
    public boolean isStoppedWhenEmpty() {
        return this.stopWhenEmpty;
    }

    @Override
    public IActionQueue stopWhenEmpty(boolean stopWhenEmpty) {
        this.stopWhenEmpty = stopWhenEmpty;
        return this;
    }

    @Override
    public boolean isKilledWhenEmpty() {
        return this.killWhenEmpty;
    }

    @Override
    public int getKillWhenEmptyAfter() {
        return this.killWhenEmptyAfter;
    }

    @Override
    public IActionQueue killWhenEmpty(boolean killWhenEmpty) {
        this.killWhenEmpty = killWhenEmpty;
        return this;
    }

    @Override
    public IActionQueue killWhenEmptyAfter(int ticks) {
        this.killWhenEmptyAfter = ticks;
        return this;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public IActionQueue kill() {
        this.isDead = true;
        if (this.manager.debug) {
            this.manager.LOGGER.log(String.format("Killing queue '%s' on '%s'", this.name, this.manager.getInternalName()), this);
        }
        return this;
    }

    @Override
    public IAction schedule(IAction action) {
        Action act = (Action)action;
        if (!this.isParallel && act.unscheduledList != null) {
            act.unscheduledList.scheduleAll(this).forEach(act1 -> {
                act1.unscheduledList = null;
            }).kill();
            return action;
        }
        act.schedule(this);
        return action;
    }

    @Override
    public void schedule(IAction ... actions) {
        for (IAction act : actions) {
            this.schedule(act);
        }
    }

    @Override
    public IAction schedule(Consumer<IAction> task) {
        return this.schedule(this.manager.create(task));
    }

    @Override
    public void schedule(Consumer<IAction> ... tasks) {
        for (Consumer<IAction> task : tasks) {
            this.schedule(task);
        }
    }

    @Override
    public IAction schedule(int delay, Consumer<IAction> task) {
        return this.schedule(this.manager.create(delay, task));
    }

    @Override
    public IAction schedule(int maxDuration, int delay, Consumer<IAction> task) {
        return this.schedule(this.manager.create(maxDuration, delay, task));
    }

    @Override
    public IAction schedule(String name, Consumer<IAction> task) {
        return this.schedule(this.manager.create(name, task));
    }

    @Override
    public IAction schedule(String name, int delay, Consumer<IAction> task) {
        return this.schedule(this.manager.create(name, delay, task));
    }

    @Override
    public IAction schedule(String name, int maxDuration, int delay, Consumer<IAction> task) {
        return this.schedule(this.manager.create(name, maxDuration, delay, task));
    }

    @Override
    public IAction scheduleActionAt(int index, IAction action) {
        int size = this.queue.size();
        if (index <= 0) {
            this.queue.addFirst(action);
        } else if (index >= size) {
            this.queue.addLast(action);
        } else {
            ArrayList<IAction> tmp = new ArrayList<IAction>(this.queue);
            tmp.add(index, action);
            this.queue.clear();
            this.queue.addAll(tmp);
        }
        ((Action)action).queue = this;
        ((Action)action).isScheduled = true;
        return action;
    }

    @Override
    public boolean hasActiveTasks() {
        return !this.queue.isEmpty();
    }

    @Override
    public int getIndex(IAction action) {
        int i = 0;
        for (IAction a : this.queue) {
            if (a.equals(action)) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    @Override
    public IAction getCurrentAction() {
        return this.queue.peekFirst();
    }

    @Override
    public boolean has(IAction action) {
        return this.has(action.getName());
    }

    @Override
    public boolean has(String actionName) {
        for (IAction act : this.queue) {
            if (!act.getName().equals(actionName)) continue;
            return true;
        }
        return false;
    }

    @Override
    public IAction get(String actionName) {
        for (IAction action : this.queue) {
            if (!action.getName().equals(actionName)) continue;
            return action;
        }
        return null;
    }

    @Override
    public boolean cancel(IAction action) {
        if (this.queue.remove(action)) {
            action.kill();
            return true;
        }
        return false;
    }

    @Override
    public boolean cancel(String actionName) {
        Iterator<IAction> acts = this.queue.iterator();
        while (acts.hasNext()) {
            IAction act = acts.next();
            if (!act.getName().equals(actionName)) continue;
            act.kill();
            acts.remove();
            return true;
        }
        return false;
    }

    public boolean finish(Action a) {
        if (a.isDone()) {
            if (a.onDone != null) {
                a.execute("onDone", a::executeOnDone);
            }
            a.kill();
            if (this.manager.debug) {
                this.manager.LOGGER.finish(String.format("Removing %s from queue", a.getIdentifier()), this);
            }
            return true;
        }
        return false;
    }

    protected boolean tick(Action a) {
        if (a == null) {
            return false;
        }
        boolean wasDone = a.isDone();
        if (this.manager.debug && !wasDone) {
            this.manager.LOGGER.push(this).log(String.format("Started ticking %s", a.getIdentifier(), a.name), this).push(a);
        }
        a.tick();
        if (this.manager.debug && !wasDone) {
            this.manager.LOGGER.pop().finish(String.format("Finished ticking %s", a.getIdentifier(), a.name), this).pop();
        }
        return this.finish(a);
    }

    protected void tick() {
        if (!this.isWorking || this.isDead) {
            return;
        }
        boolean active = this.hasActiveTasks();
        if (this.manager.debug && active) {
            this.manager.LOGGER.push(this.manager).log(String.format("Started ticking queue '%s' ", this.name), this.manager).push(this);
        }
        if (!this.isParallel) {
            if (this.tick((Action)this.getCurrentAction())) {
                this.queue.pollFirst();
            }
        } else {
            Iterator<IAction> pit = this.queue.iterator();
            while (pit.hasNext()) {
                if (!this.tick((Action)pit.next())) continue;
                pit.remove();
            }
        }
        if (this.stopWhenEmpty && !this.hasActiveTasks()) {
            this.stop();
        }
        this.killWhenEmpty();
        if (this.manager.debug && active) {
            this.manager.LOGGER.pop().finish(String.format("Finished ticking queue '%s' ", this.name), this.manager).pop();
        }
    }

    protected void killWhenEmpty() {
        if (this.killWhenEmpty && !this.hasActiveTasks() && this.autoKill == null) {
            if (this.manager.debug) {
                this.manager.LOGGER.log(String.format("Queue is empty! Killing in %s ticks", this.killWhenEmptyAfter), this);
            }
            this.autoKill = (Action)this.manager.create(String.format("Kill '%s'", this.name), this.killWhenEmptyAfter, act -> {
                if (this.hasActiveTasks()) {
                    if (this.manager.debug) {
                        this.manager.LOGGER.log("Scheduled Actions found! Aborted kill process", this);
                    }
                    return;
                }
                this.manager.removeQueue(this.name);
            }).everyTick().once();
        }
        if (this.autoKill != null) {
            this.autoKill.tick();
            if (this.autoKill.isDone()) {
                this.autoKill = null;
            }
        }
    }

    @Override
    public void clear() {
        this.queue.forEach(act -> act.kill());
        this.queue.clear();
        if (this.manager.debug) {
            this.manager.LOGGER.log("Cleared queue!", this);
        }
    }

    @Override
    public IActionChain chain() {
        return new ActionChain(this.manager, this, this.isParallel ? "parallel#" : "sequential#");
    }

    @Override
    public String printQueue() {
        if (this.queue.isEmpty()) {
            return String.format("ActionQueue[name=%s] is empty", this.name);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s\n", this));
        int i = 0;
        for (IAction action : this.queue) {
            sb.append(String.format("  [%d] %s\n", i++, action.toString()));
        }
        return sb.toString();
    }

    public String toString() {
        return String.format("ActionQueue '%s' [size=%d, parallel=%s, working=%s, dead=%s, killWhenEmpty=%s, killAfter=%d]", this.name, this.queue.size(), this.isParallel, this.isWorking, this.isDead, this.killWhenEmpty, this.killWhenEmptyAfter);
    }
}

