/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data.actions;

import java.util.function.Consumer;
import java.util.function.Function;
import noppes.npcs.api.handler.data.IAction;

public interface IConditionalAction
extends IAction {
    public IConditionalAction setCondition(Function<IAction, Boolean> var1);

    public IConditionalAction terminateWhen(Function<IAction, Boolean> var1);

    public IConditionalAction onTermination(Consumer<IAction> var1);

    public boolean wasTaskExecuted();

    public int getCheckCount();

    public int getMaxChecks();

    public boolean isTerminated();

    public IConditionalAction setMaxChecks(int var1);
}

