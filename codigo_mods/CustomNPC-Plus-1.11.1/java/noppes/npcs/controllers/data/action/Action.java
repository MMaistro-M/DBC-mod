/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data.action;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import noppes.npcs.api.handler.IActionManager;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.IActionQueue;
import noppes.npcs.api.handler.data.actions.IConditionalAction;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.data.action.ActionList;
import noppes.npcs.controllers.data.action.ActionManager;
import noppes.npcs.controllers.data.action.ActionThread;
import noppes.npcs.scripted.CustomNPCsException;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class Action
implements IAction {
    protected final ActionManager manager;
    protected IActionQueue queue;
    protected boolean isScheduled;
    protected final String name;
    protected int startAfterTicks;
    protected int count;
    protected int maxCount = -1;
    protected int duration;
    protected int maxDuration = -1;
    protected int updateEveryXTick = 5;
    protected Consumer<IAction> task;
    protected Consumer<IAction> onStart;
    protected Consumer<IAction> onDone;
    protected boolean done;
    protected final Map<String, Object> dataStore = new HashMap<String, Object>();
    protected boolean isThreaded;
    protected ActionThread actionThread;
    protected ScriptContainer reportTo;
    protected ActionList unscheduledList;

    public Action(ActionManager manager, String name) {
        this.manager = manager;
        this.name = name;
        if (ScriptContainer.Current != null) {
            this.reportTo = ScriptContainer.Current;
        }
    }

    public Action(ActionManager manager, Consumer<IAction> task) {
        this(manager, Integer.toHexString(task.hashCode()), task);
    }

    public Action(ActionManager manager, String name, Consumer<IAction> task) {
        this(manager, name);
        this.task = task;
    }

    public Action(ActionManager manager, int startAfterTicks, Consumer<IAction> task) {
        this(manager, task);
        this.startAfterTicks = startAfterTicks;
    }

    public Action(ActionManager manager, String name, int startAfterTicks, Consumer<IAction> task) {
        this(manager, name, task);
        this.startAfterTicks = startAfterTicks;
    }

    public Action(ActionManager manager, int maxDuration, int startAfterTicks, Consumer<IAction> task) {
        this(manager, startAfterTicks, task);
        this.maxDuration = maxDuration;
    }

    public Action(ActionManager manager, String name, int maxDuration, int startAfterTicks, Consumer<IAction> task) {
        this(manager, name, startAfterTicks, task);
        this.maxDuration = maxDuration;
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
    public IActionQueue getQueue() {
        return this.queue;
    }

    public String getQueueName() {
        return this.queue != null ? this.queue.getName() : "null";
    }

    @Override
    public IAction setQueue(IActionQueue queue) {
        if (this.queue == queue) {
            return this;
        }
        if (this.queue != null) {
            this.queue.getQueue().remove(this);
        }
        this.queue = queue;
        queue.schedule((IAction)this);
        return this;
    }

    protected void schedule(IActionQueue queue) {
        queue.getQueue().add(this);
        this.queue = queue;
        this.isScheduled = true;
        if (this.manager.debug) {
            this.manager.LOGGER.log(String.format("Scheduled %s", this.getIdentifier()), queue);
        }
    }

    @Override
    public boolean isScheduled() {
        return this.isScheduled;
    }

    @Override
    public IAction setTask(Consumer<IAction> task) {
        this.task = task;
        return this;
    }

    @Override
    public IAction onStart(Consumer<IAction> task) {
        this.onStart = task;
        return this;
    }

    @Override
    public IAction onDone(Consumer<IAction> task) {
        this.onDone = task;
        return this;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public int getMaxDuration() {
        return this.maxDuration;
    }

    @Override
    public IAction setMaxDuration(int ticks) {
        this.maxDuration = Math.max(-1, ticks);
        return this;
    }

    @Override
    public int getStartAfterTicks() {
        return this.startAfterTicks;
    }

    @Override
    public int getUpdateEvery() {
        return this.updateEveryXTick;
    }

    @Override
    public IAction updateEvery(int x) {
        this.updateEveryXTick = Math.max(1, x);
        return this;
    }

    @Override
    public IAction everyTick() {
        this.updateEveryXTick = 1;
        return this;
    }

    @Override
    public IAction everySecond() {
        this.updateEveryXTick = 20;
        return this;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public int getMaxCount() {
        return this.maxCount;
    }

    @Override
    public IAction times(int n) {
        this.maxCount = Math.max(-1, n);
        return this;
    }

    @Override
    public IAction once() {
        this.maxCount = 1;
        return this;
    }

    @Override
    public Object getData(String key) {
        return this.dataStore.get(key);
    }

    @Override
    public IAction setData(String key, Object v) {
        this.dataStore.put(key, v);
        return this;
    }

    @Override
    public IAction removeData(String key) {
        this.dataStore.remove(key);
        return this;
    }

    @Override
    public IAction copyDataTo(IAction to) {
        ((Action)to).dataStore.putAll(this.dataStore);
        return this;
    }

    @Override
    public String printData() {
        if (this.dataStore.isEmpty()) {
            return String.format("Action[name=%s] dataStore is empty", this.name);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Action[name=%s] dataStore contents:\n", this.name));
        for (Map.Entry<String, Object> entry : this.dataStore.entrySet()) {
            sb.append(String.format("  %s = %s\n", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }

    @Override
    public boolean hasData(String key) {
        return this.dataStore.containsKey(key);
    }

    @Override
    public void markDone() {
        this.done = true;
        if (this.manager.debug) {
            this.manager.LOGGER.log("Marked done!", this);
        }
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public IAction start() {
        this.manager.start();
        return this;
    }

    public void tick() {
        if (this.done) {
            return;
        }
        if (this.startAfterTicks > 0) {
            this.manager.LOGGER.log(String.format("Starts after %s ticks...", this.startAfterTicks), this);
            --this.startAfterTicks;
            return;
        }
        if (this.duration == 0 && this.onStart != null) {
            this.execute("onStart", this::executeOnStart);
        }
        if (this.done) {
            return;
        }
        ++this.duration;
        this.inheritedTick();
        if (this.maxDuration > -1 && this.duration >= this.maxDuration || this.maxCount == 0) {
            if (this.manager.debug) {
                this.manager.LOGGER.log("Reached max duration", this);
            }
            this.markDone();
            return;
        }
        if (this.maxCount > -1 && this.count >= this.maxCount) {
            if (this.manager.debug) {
                this.manager.LOGGER.log("Reached max count", this);
            }
            this.markDone();
        }
    }

    public void inheritedTick() {
        if (this.manager.debug) {
            this.manager.LOGGER.log(String.format("Ticking... (duration = %s/%s, count = %s/%s)", this.duration, this.maxDuration, this.count, this.maxCount), this);
        }
        if (this.duration % this.updateEveryXTick == 0 && this.task != null) {
            this.execute("task", this::executeTask);
        }
    }

    public void execute(String taskName, Runnable task) {
        if (this.isThreaded) {
            this.actionThread.execute(taskName, task);
        } else {
            task.run();
        }
    }

    protected void executeOnStart() {
        if (this.manager.debug) {
            this.manager.LOGGER.log("Executing onStart task...", this);
        }
        try {
            this.onStart.accept(this);
        }
        catch (Throwable t) {
            String err = "IAction" + this.getName() + "  onStart threw an exception:";
            if (this.reportTo != null) {
                this.reportTo.appendConsole(err + "\n" + ExceptionUtils.getStackTrace(t));
            }
            this.manager.LOGGER.error("onStart Task of " + this + " threw an exception:", t);
        }
        if (this.manager.debug) {
            this.manager.LOGGER.log("Finished executing onStart task", this);
        }
    }

    protected void executeTask() {
        if (this.manager.debug) {
            this.manager.LOGGER.log("Executing task...", this);
        }
        try {
            this.task.accept(this);
            ++this.count;
        }
        catch (Throwable t) {
            String err = "IAction '" + this.name + "' threw an exception:";
            if (this.reportTo != null) {
                this.reportTo.appendConsole(err + "\n" + ExceptionUtils.getStackTrace(t));
            }
            this.manager.LOGGER.error("Task of " + this + " threw an exception:", t);
            this.markDone();
        }
        if (this.manager.debug) {
            this.manager.LOGGER.log(String.format("Finished executing task (count = %s/%s)", this.count, this.maxCount), this);
        }
    }

    protected void executeOnDone() {
        if (this.manager.debug) {
            this.manager.LOGGER.log("Executing onDone task...", this);
        }
        try {
            this.onDone.accept(this);
        }
        catch (Throwable t) {
            String err = "IAction" + this.getName() + "  onDone threw an exception:";
            if (this.reportTo != null) {
                this.reportTo.appendConsole(err + "\n" + ExceptionUtils.getStackTrace(t));
            }
            this.manager.LOGGER.error("onDone Task of " + this + " threw an exception:", t);
        }
        if (this.manager.debug) {
            this.manager.LOGGER.log("Finished executing onDone task", this);
        }
    }

    @Override
    public void kill() {
        if (this.manager.debug) {
            this.manager.LOGGER.log(String.format("Killing %s...", this.getIdentifier()), this.queue);
        }
        if (this.actionThread != null) {
            this.actionThread.stop();
        }
        this.dataStore.clear();
        this.isScheduled = false;
        this.done = true;
    }

    @Override
    public String getIdentifier() {
        return String.format("%s '%s'", this.getClass().getSimpleName(), this.name);
    }

    public String toString() {
        return String.format("%s [queue='%s', scheduled=%s, done=%s, paused=%s, updateEvery=%s, duration=%d/%d, count=%d/%d, threaded=%s]", this.getIdentifier(), this.getQueueName(), this.isScheduled, this.done, this.isPaused(), this.updateEveryXTick, this.duration, this.maxDuration, this.count, this.maxCount, this.isThreaded);
    }

    @Override
    public IAction threadify() {
        if (!this.isThreaded) {
            this.isThreaded = true;
            this.actionThread = new ActionThread(this);
        }
        return this;
    }

    @Override
    public void resume() {
        if (this.isThreaded) {
            this.actionThread.resume();
        }
    }

    @Override
    public void pause() {
        if (!this.isThreaded) {
            throw new CustomNPCsException("Must threadify() IAction before pausing!", new Object[0]);
        }
        this.actionThread.pause();
    }

    @Override
    public IAction pauseFor(int ticks) {
        if (this.isThreaded) {
            this.actionThread.pauseFor(ticks);
        } else {
            this.startAfterTicks = ticks;
        }
        return this;
    }

    @Override
    public IAction pauseFor(long millis) {
        if (this.isThreaded) {
            this.actionThread.pauseFor(millis);
            return this;
        }
        return this.pauseFor((int)(millis / 50L));
    }

    @Override
    public void pauseUntil(Function<IAction, Boolean> until) {
        if (!this.isThreaded) {
            throw new CustomNPCsException("Must threadify() IAction before pausing!", new Object[0]);
        }
        this.actionThread.pauseUntil(until);
    }

    @Override
    public boolean isPaused() {
        if (this.isThreaded) {
            return this.actionThread.isPaused();
        }
        return this.startAfterTicks > 0;
    }

    @Override
    public IAction getNext() {
        if (this.queue == null) {
            return null;
        }
        boolean seenMe = false;
        for (IAction a : this.queue.getQueue()) {
            if (seenMe) {
                return a;
            }
            if (a != this) continue;
            seenMe = true;
        }
        return null;
    }

    @Override
    public IAction getPrevious() {
        if (this.queue == null) {
            return null;
        }
        IAction prev = null;
        for (IAction a : this.queue.getQueue()) {
            if (a == this) {
                return prev;
            }
            prev = a;
        }
        return null;
    }

    @Override
    public IAction after(IAction after) {
        int idx;
        if (after instanceof IConditionalAction) {
            return this.conditional((IConditionalAction)after);
        }
        int n = idx = this.queue == null ? -1 : this.queue.getIndex(this);
        if (idx >= 0 && !this.queue.isParallel()) {
            this.queue.scheduleActionAt(idx + 1, after);
        } else {
            if (this.unscheduledList == null) {
                this.unscheduledList = new ActionList(this);
            }
            this.unscheduledList.after(this, (Action)after);
        }
        return after;
    }

    @Override
    public void after(IAction ... actions) {
        for (int i = 0; i < actions.length - 1; ++i) {
            actions[i].after(actions[i + 1]);
        }
    }

    @Override
    public void after(Consumer<IAction> ... tasks) {
        IAction[] actions = new IAction[tasks.length];
        for (int i = 0; i < tasks.length; ++i) {
            actions[i] = this.manager.create(tasks[i]);
        }
        this.after(actions);
    }

    @Override
    public IAction after(String name, int maxDuration, int delay, Consumer<IAction> t) {
        return this.after(this.manager.create(name, maxDuration, delay, t));
    }

    @Override
    public IAction after(String name, int delay, Consumer<IAction> t) {
        return this.after(this.manager.create(name, delay, t));
    }

    @Override
    public IAction after(int delay, Consumer<IAction> t) {
        return this.after(this.manager.create(delay, t));
    }

    @Override
    public IAction after(String name, Consumer<IAction> t) {
        return this.after(this.manager.create(name, t));
    }

    @Override
    public IAction after(Consumer<IAction> t) {
        return this.after(this.manager.create(t));
    }

    @Override
    public IAction before(IAction before) {
        int idx;
        if (before instanceof IConditionalAction) {
            return this.conditional((IConditionalAction)before);
        }
        int n = idx = this.queue == null ? -1 : this.queue.getIndex(this);
        if (idx >= 0 && !this.queue.isParallel()) {
            this.queue.scheduleActionAt(Math.max(0, idx), before);
        } else {
            if (this.unscheduledList == null) {
                this.unscheduledList = new ActionList(this);
            }
            this.unscheduledList.before(this, (Action)before);
        }
        return before;
    }

    @Override
    public IAction before(String name, int maxDuration, int delay, Consumer<IAction> t) {
        return this.before(this.manager.create(name, maxDuration, delay, t));
    }

    @Override
    public IAction before(String name, int delay, Consumer<IAction> t) {
        return this.before(this.manager.create(name, delay, t));
    }

    @Override
    public IAction before(int delay, Consumer<IAction> t) {
        return this.before(this.manager.create(delay, t));
    }

    @Override
    public IAction before(String name, Consumer<IAction> t) {
        return this.before(this.manager.create(name, t));
    }

    @Override
    public IAction before(Consumer<IAction> t) {
        return this.before(this.manager.create(t));
    }

    @Override
    public IAction parallel(IAction act) {
        if (this.unscheduledList != null) {
            ((Action)act).unscheduledList = this.unscheduledList;
        }
        if (this.queue != null && this.queue.isParallel() && this.queue != this.manager.conditionalQueue) {
            return this.queue.schedule(act);
        }
        return this.manager.scheduleParallel(act);
    }

    @Override
    public void parallel(IAction ... actions) {
        for (IAction act : actions) {
            this.parallel(act);
        }
    }

    @Override
    public IAction parallel(Consumer<IAction> task) {
        return this.parallel(this.manager.create(task));
    }

    @Override
    public void parallel(Consumer<IAction> ... tasks) {
        for (Consumer<IAction> task : tasks) {
            this.parallel(task);
        }
    }

    @Override
    public IAction parallel(int delay, Consumer<IAction> task) {
        return this.parallel(this.manager.create(delay, task));
    }

    @Override
    public IAction parallel(String name, Consumer<IAction> task) {
        return this.parallel(this.manager.create(name, task));
    }

    @Override
    public IAction parallel(String name, int startAfterTicks, Consumer<IAction> task) {
        return this.parallel(this.manager.create(name, startAfterTicks, task));
    }

    @Override
    public IAction parallel(String name, int maxDuration, int delay, Consumer<IAction> t) {
        return this.parallel(this.manager.create(name, maxDuration, delay, t));
    }

    @Override
    public IConditionalAction conditional(IConditionalAction after) {
        if (this.unscheduledList != null) {
            ((Action)((Object)after)).unscheduledList = this.unscheduledList;
        }
        return this.manager.schedule(after);
    }

    @Override
    public void conditional(IConditionalAction ... actions) {
        for (IConditionalAction act : actions) {
            this.conditional(act);
        }
    }

    @Override
    public IConditionalAction conditional(Function<IAction, Boolean> condition, Consumer<IAction> task) {
        return this.conditional(this.manager.create(condition, task));
    }

    @Override
    public IConditionalAction conditional(String name, Function<IAction, Boolean> condition, Consumer<IAction> task) {
        return this.conditional(this.manager.create(name, condition, task));
    }

    @Override
    public IConditionalAction conditional(Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen) {
        return this.conditional(this.manager.create(condition, task, terminateWhen));
    }

    @Override
    public IConditionalAction conditional(String name, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen) {
        return this.conditional(this.manager.create(name, condition, task, terminateWhen));
    }

    @Override
    public IConditionalAction conditional(Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen, Consumer<IAction> onTermination) {
        return this.conditional(this.manager.create(condition, task, terminateWhen, onTermination));
    }

    @Override
    public IConditionalAction conditional(String name, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen, Consumer<IAction> onTermination) {
        return this.conditional(this.manager.create(name, condition, task, terminateWhen, onTermination));
    }
}

