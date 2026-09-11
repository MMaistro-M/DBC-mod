/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data.action.action;

import java.util.function.Consumer;
import java.util.function.Function;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.actions.IConditionalAction;
import noppes.npcs.controllers.data.action.Action;
import noppes.npcs.controllers.data.action.ActionManager;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ConditionalAction
extends Action
implements IConditionalAction {
    protected Function<IAction, Boolean> condition;
    protected Function<IAction, Boolean> terminateWhen;
    protected Consumer<IAction> onTermination;
    protected int maxChecks = -1;
    protected int checkCount;
    protected boolean taskExecuted;

    public ConditionalAction(ActionManager manager, Function<IAction, Boolean> condition, Consumer<IAction> task) {
        super(manager, task);
        this.condition = condition;
    }

    public ConditionalAction(ActionManager manager, String name, Function<IAction, Boolean> condition, Consumer<IAction> task) {
        super(manager, name, task);
        this.condition = condition;
    }

    public ConditionalAction(ActionManager manager, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen) {
        this(manager, condition, task);
        this.terminateWhen = terminateWhen;
    }

    public ConditionalAction(ActionManager manager, String name, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen) {
        this(manager, name, condition, task);
        this.terminateWhen = terminateWhen;
    }

    public ConditionalAction(ActionManager manager, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen, Consumer<IAction> onTermination) {
        this(manager, condition, task, terminateWhen);
        this.onTermination = onTermination;
    }

    public ConditionalAction(ActionManager manager, String name, Function<IAction, Boolean> condition, Consumer<IAction> task, Function<IAction, Boolean> terminateWhen, Consumer<IAction> onTermination) {
        this(manager, name, condition, task, terminateWhen);
        this.onTermination = onTermination;
    }

    @Override
    public int getCheckCount() {
        return this.checkCount;
    }

    @Override
    public int getMaxChecks() {
        return this.maxChecks;
    }

    @Override
    public IConditionalAction setMaxChecks(int maxChecks) {
        this.maxChecks = Math.max(-1, maxChecks);
        return this;
    }

    @Override
    public IConditionalAction setCondition(Function<IAction, Boolean> condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public IConditionalAction terminateWhen(Function<IAction, Boolean> terminateWhen) {
        this.terminateWhen = terminateWhen;
        return this;
    }

    @Override
    public IConditionalAction onTermination(Consumer<IAction> onTermination) {
        this.onTermination = onTermination;
        return this;
    }

    @Override
    public boolean wasTaskExecuted() {
        return this.taskExecuted;
    }

    @Override
    public boolean isTerminated() {
        return this.checkTermination();
    }

    @Override
    public void inheritedTick() {
        if (this.manager.inDebugMode()) {
            this.manager.LOGGER.log(String.format("Ticking... (duration = %s/%s, count = %s/%s, checkCount = %s/%s)", this.duration, this.maxDuration, this.count, this.maxCount, this.checkCount, this.maxChecks), this);
        }
        if (this.maxChecks > -1 && this.checkCount >= this.maxChecks) {
            if (this.manager.inDebugMode()) {
                this.manager.LOGGER.log("Reached max check count", this);
            }
            this.markDone();
            return;
        }
        if (this.duration % this.updateEveryXTick == 0) {
            ++this.checkCount;
            Runnable execute = () -> {
                if (this.evaluatePredicate(this.condition, "Condition")) {
                    this.executeTask();
                }
                if (this.checkTermination()) {
                    if (this.onTermination != null) {
                        this.executeOnTermination();
                    }
                    this.markDone();
                }
            };
            this.execute("task", execute);
        }
    }

    @Override
    protected void executeTask() {
        if (this.manager.inDebugMode()) {
            this.manager.LOGGER.log("Executing task...", this);
        }
        try {
            this.task.accept(this);
            ++this.count;
            this.taskExecuted = true;
        }
        catch (Throwable t) {
            String err = "IConditionalAction '" + this.getName() + "' threw an exception:";
            if (this.reportTo != null) {
                this.reportTo.appendConsole(err + "\n" + ExceptionUtils.getStackTrace(t));
            }
            this.manager.LOGGER.error("Task of " + this + " threw an exception:", t);
            this.markDone();
        }
        if (this.manager.inDebugMode()) {
            this.manager.LOGGER.log(String.format("Finished executing task (count = %s/%s)", this.count, this.maxCount), this);
        }
    }

    protected void executeOnTermination() {
        if (this.manager.inDebugMode()) {
            this.manager.LOGGER.log("Executing onTermination task...", this);
        }
        try {
            this.onTermination.accept(this);
        }
        catch (Throwable t) {
            String err = "IConditionalAction" + this.getName() + "  onTermination threw an exception:";
            if (this.reportTo != null) {
                this.reportTo.appendConsole(err + "\n" + ExceptionUtils.getStackTrace(t));
            }
            this.manager.LOGGER.error("Termination Task of " + this + " threw an exception:", t);
        }
        if (this.manager.inDebugMode()) {
            this.manager.LOGGER.log("Finished executing onTermination task", this);
        }
    }

    private boolean checkTermination() {
        return this.evaluatePredicate(this.terminateWhen, "Termination condition");
    }

    private boolean evaluatePredicate(Function<IAction, Boolean> predicate, String name) {
        Boolean result;
        if (predicate == null) {
            return false;
        }
        try {
            result = predicate.apply(this);
        }
        catch (Throwable t) {
            this.manager.LOGGER.error(name + " of " + this + " threw an exception:", t);
            this.markDone();
            return false;
        }
        if (result == null) {
            if (this.manager.inDebugMode()) {
                this.manager.LOGGER.log(name + " returned null; treating as false", this);
            }
            return false;
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s [queue='%s', scheduled=%s, done=%s, paused=%s, updateEvery=%s, duration=%d/%d, count=%d/%d, checks=%d/%d, taskExecuted=%s, threaded=%s]", this.getIdentifier(), this.getQueueName(), this.isScheduled, this.done, this.isPaused(), this.updateEveryXTick, this.duration, this.maxDuration, this.count, this.maxCount, this.checkCount, this.maxChecks, this.taskExecuted, this.isThreaded);
    }
}

