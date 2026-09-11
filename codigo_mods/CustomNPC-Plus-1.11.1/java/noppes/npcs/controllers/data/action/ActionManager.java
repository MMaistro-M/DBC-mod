/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import noppes.npcs.api.handler.IActionManager;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.IActionChain;
import noppes.npcs.api.handler.data.IActionListener;
import noppes.npcs.api.handler.data.IActionQueue;
import noppes.npcs.api.handler.data.actions.IConditionalAction;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.data.action.Action;
import noppes.npcs.controllers.data.action.ActionLogger;
import noppes.npcs.controllers.data.action.ActionQueue;
import noppes.npcs.controllers.data.action.action.ConditionalAction;

public class ActionManager
implements IActionManager {
    public static final ActionManager GLOBAL = new ActionManager("GLOBAL");
    private static final Map<Object, IActionListener> listeners = new ConcurrentHashMap<Object, IActionListener>();
    protected boolean isWorking = false;
    public ActionLogger LOGGER = new ActionLogger(this);
    protected String name = "";
    protected final ActionQueue sequentialQueue = new ActionQueue(this, "mainSequential");
    protected final ActionQueue parallelQueue = new ActionQueue(this, "mainParallel", true);
    protected final ActionQueue conditionalQueue = new ActionQueue(this, "mainConditional", true);
    protected final Map<String, IActionQueue> otherQueues = new HashMap<String, IActionQueue>();
    protected boolean debug = false;
    protected ScriptContainer reportTo;

    public ActionManager() {
    }

    public ActionManager(String name) {
        this.name = name;
    }

    @Override
    public IActionManager start() {
        this.isWorking = true;
        return this;
    }

    @Override
    public IActionManager stop() {
        this.isWorking = false;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IActionManager setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean inDebugMode() {
        return this.debug;
    }

    @Override
    public IActionManager setDebugMode(boolean debug) {
        this.debug = debug;
        if (debug) {
            this.LOGGER.log("Enabled debug mode", this);
        }
        return this;
    }

    @Override
    public IAction create(String name) {
        return new Action(this, name);
    }

    @Override
    public IAction create(Consumer<IAction> t) {
        return new Action(this, t);
    }

    @Override
    public IAction create(int maxDuration, int delay, Consumer<IAction> task) {
        return new Action(this, maxDuration, delay, task);
    }

    @Override
    public IAction create(String name, int maxDuration, int delay, Consumer<IAction> task) {
        return new Action(this, name, maxDuration, delay, task);
    }

    @Override
    public IAction create(String name, int delay, Consumer<IAction> t) {
        return new Action(this, name, delay, t);
    }

    @Override
    public IAction create(int delay, Consumer<IAction> t) {
        return new Action(this, delay, t);
    }

    @Override
    public IAction create(String name, Consumer<IAction> t) {
        return new Action(this, name, t);
    }

    @Override
    public IConditionalAction create(Function<IAction, Boolean> condition, Consumer<IAction> task) {
        return new ConditionalAction(this, condition, task);
    }

    @Override
    public IConditionalAction create(String name, Function<IAction, Boolean> condition, Consumer<IAction> task) {
        return new ConditionalAction(this, name, condition, task);
    }

    @Override
    public IConditionalAction create(Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen) {
        return new ConditionalAction(this, condition, task, terminateWhen);
    }

    @Override
    public IConditionalAction create(String name, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen) {
        return new ConditionalAction(this, name, condition, task, terminateWhen);
    }

    @Override
    public IConditionalAction create(Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen, Consumer<IAction> onTermination) {
        return new ConditionalAction(this, condition, task, terminateWhen, onTermination);
    }

    @Override
    public IConditionalAction create(String name, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen, Consumer<IAction> onTermination) {
        return new ConditionalAction(this, name, condition, task, terminateWhen, onTermination);
    }

    @Override
    public IActionQueue createQueue(String name) {
        return this.createQueue(name, false);
    }

    @Override
    public IActionQueue createQueue(String name, boolean isParallel) {
        IActionQueue queue = new ActionQueue(this, name, isParallel).killWhenEmpty(true);
        this.otherQueues.put(name, queue);
        return queue;
    }

    @Override
    public IActionQueue getOrCreateQueue(String name) {
        return this.getOrCreateQueue(name, false);
    }

    @Override
    public IActionQueue getOrCreateQueue(String name, boolean isParallel) {
        IActionQueue queue = this.getQueue(name);
        if (queue == null) {
            queue = this.createQueue(name, isParallel);
        }
        return queue;
    }

    @Override
    public IActionQueue getQueue(String name) {
        return this.otherQueues.get(name);
    }

    @Override
    public boolean hasQueue(String name) {
        return this.otherQueues.containsKey(name);
    }

    @Override
    public boolean removeQueue(String name) {
        IActionQueue queue = this.otherQueues.get(name);
        if (queue == null) {
            return false;
        }
        queue.kill();
        return true;
    }

    @Override
    public IActionQueue getSequentialQueue() {
        return this.sequentialQueue;
    }

    @Override
    public IAction schedule(IAction action) {
        if (action instanceof IConditionalAction) {
            return this.conditionalQueue.schedule(action);
        }
        return this.sequentialQueue.schedule(action);
    }

    @Override
    public void schedule(IAction ... actions) {
        this.sequentialQueue.schedule(actions);
    }

    @Override
    public void schedule(Consumer<IAction> ... tasks) {
        this.sequentialQueue.schedule(tasks);
    }

    @Override
    public IAction schedule(Consumer<IAction> task) {
        return this.sequentialQueue.schedule(task);
    }

    @Override
    public IAction schedule(int delay, Consumer<IAction> task) {
        return this.sequentialQueue.schedule(delay, task);
    }

    @Override
    public IAction schedule(int maxDuration, int delay, Consumer<IAction> task) {
        return this.sequentialQueue.schedule(maxDuration, delay, task);
    }

    @Override
    public IAction schedule(String name, Consumer<IAction> task) {
        return this.sequentialQueue.schedule(name, task);
    }

    @Override
    public IAction schedule(String name, int delay, Consumer<IAction> task) {
        return this.sequentialQueue.schedule(name, delay, task);
    }

    @Override
    public IAction schedule(String name, int maxDuration, int delay, Consumer<IAction> task) {
        return this.sequentialQueue.schedule(name, maxDuration, delay, task);
    }

    @Override
    public IAction scheduleActionAt(int index, IAction action) {
        return this.sequentialQueue.scheduleActionAt(index, action);
    }

    @Override
    public IActionQueue getParallelQueue() {
        return this.parallelQueue;
    }

    @Override
    public IAction scheduleParallel(IAction action) {
        return this.parallelQueue.schedule(action);
    }

    @Override
    public void scheduleParallel(IAction ... actions) {
        this.parallelQueue.schedule(actions);
    }

    @Override
    public void scheduleParallel(Consumer<IAction> ... tasks) {
        this.parallelQueue.schedule(tasks);
    }

    @Override
    public IAction scheduleParallel(Consumer<IAction> task) {
        return this.parallelQueue.schedule(task);
    }

    @Override
    public IAction scheduleParallel(int delay, Consumer<IAction> task) {
        return this.parallelQueue.schedule(delay, task);
    }

    @Override
    public IAction scheduleParallel(int maxDuration, int delay, Consumer<IAction> task) {
        return this.parallelQueue.schedule(maxDuration, delay, task);
    }

    @Override
    public IAction scheduleParallel(String name, Consumer<IAction> task) {
        return this.parallelQueue.schedule(name, task);
    }

    @Override
    public IAction scheduleParallel(String name, int delay, Consumer<IAction> task) {
        return this.parallelQueue.schedule(name, delay, task);
    }

    @Override
    public IAction scheduleParallel(String name, int maxDuration, int delay, Consumer<IAction> task) {
        return this.parallelQueue.schedule(name, maxDuration, delay, task);
    }

    @Override
    public IActionQueue getConditionalQueue() {
        return this.conditionalQueue;
    }

    @Override
    public IConditionalAction schedule(IConditionalAction action) {
        return (IConditionalAction)this.conditionalQueue.schedule((IAction)action);
    }

    @Override
    public void schedule(IConditionalAction ... actions) {
        this.conditionalQueue.schedule(actions);
    }

    @Override
    public IConditionalAction schedule(Function<IAction, Boolean> condition, Consumer<IAction> task) {
        return this.schedule((IConditionalAction)new ConditionalAction(this, condition, task));
    }

    @Override
    public IConditionalAction schedule(Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen) {
        return this.schedule((IConditionalAction)new ConditionalAction(this, condition, task, terminateWhen));
    }

    @Override
    public IConditionalAction schedule(Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen, Consumer<IAction> onTermination) {
        return this.schedule((IConditionalAction)new ConditionalAction(this, condition, task, terminateWhen, onTermination));
    }

    @Override
    public IConditionalAction schedule(String name, Function<IAction, Boolean> condition, Consumer<IAction> task) {
        return this.schedule((IConditionalAction)new ConditionalAction(this, name, condition, task));
    }

    @Override
    public IConditionalAction schedule(String name, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen) {
        return this.schedule((IConditionalAction)new ConditionalAction(this, name, condition, task, terminateWhen));
    }

    @Override
    public IConditionalAction schedule(String name, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen, Consumer<IAction> onTermination) {
        return this.schedule((IConditionalAction)new ConditionalAction(this, name, condition, task, terminateWhen, onTermination));
    }

    @Override
    public IActionQueue[] getAllQueues() {
        ArrayList<IActionQueue> allQueues = new ArrayList<IActionQueue>();
        allQueues.add(this.sequentialQueue);
        allQueues.add(this.parallelQueue);
        allQueues.add(this.conditionalQueue);
        allQueues.addAll(this.otherQueues.values());
        return allQueues.toArray(new IActionQueue[0]);
    }

    public boolean hasActiveTasks() {
        return Arrays.stream(this.getAllQueues()).anyMatch(queue -> queue.hasActiveTasks());
    }

    @Override
    public boolean hasAny(String name) {
        return this.getAny(name) != null;
    }

    @Override
    public IAction getAny(String name) {
        IAction act = this.sequentialQueue.get(name);
        if (act != null) {
            return act;
        }
        act = this.parallelQueue.get(name);
        if (act != null) {
            return act;
        }
        act = this.conditionalQueue.get(name);
        if (act != null) {
            return act;
        }
        for (IActionQueue q : this.otherQueues.values()) {
            act = q.get(name);
            if (act == null) continue;
            return act;
        }
        return null;
    }

    @Override
    public boolean cancelAny(String name) {
        boolean canceled = this.sequentialQueue.cancel(name);
        if (canceled) {
            return true;
        }
        canceled = this.parallelQueue.cancel(name);
        if (canceled) {
            return true;
        }
        canceled = this.conditionalQueue.cancel(name);
        if (canceled) {
            return true;
        }
        for (IActionQueue queue : this.otherQueues.values()) {
            if (!queue.cancel(name)) continue;
            return true;
        }
        return false;
    }

    public void tick() {
        if (!this.isWorking) {
            return;
        }
        boolean active = this.hasActiveTasks();
        if (this.debug && active) {
            this.LOGGER.beginTick(this).log("Started Tick Cycle", this);
        }
        this.sequentialQueue.tick();
        this.parallelQueue.tick();
        this.conditionalQueue.tick();
        Iterator<IActionQueue> it = this.otherQueues.values().iterator();
        while (it.hasNext()) {
            IActionQueue other = it.next();
            ((ActionQueue)other).tick();
            if (!other.isDead()) continue;
            other.clear();
            if (this.debug) {
                this.LOGGER.log(String.format("Removing queue '%s' from '%s'", other.getName(), this.getInternalName()), this);
            }
            it.remove();
        }
        if (this.debug && active) {
            this.LOGGER.finish("Finished Tick Cycle", this);
        }
    }

    @Override
    public void clear() {
        this.sequentialQueue.clear();
        this.parallelQueue.clear();
        this.conditionalQueue.clear();
        this.otherQueues.forEach((name, queue) -> queue.clear());
        if (this.debug) {
            this.LOGGER.log("Cleared all queues!", this);
        }
    }

    public String getInternalName() {
        return this.name.isEmpty() ? "Action Manager" : this.name;
    }

    @Override
    public IActionChain chain() {
        return this.sequentialQueue.chain();
    }

    @Override
    public IActionChain parallelChain() {
        return this.parallelQueue.chain();
    }

    @Override
    public String printQueues() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ActionManager [working=%s]\n", this.isWorking));
        int i = 0;
        for (IActionQueue action : this.getAllQueues()) {
            sb.append(String.format("  [%d] %s\n", i++, action.toString()));
        }
        return sb.toString();
    }
}

