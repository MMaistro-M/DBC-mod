/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import java.util.function.Consumer;
import java.util.function.Function;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.IActionChain;
import noppes.npcs.api.handler.data.IActionQueue;
import noppes.npcs.api.handler.data.actions.IConditionalAction;

public interface IActionManager {
    public IActionManager start();

    public IActionManager stop();

    public IAction create(String var1, int var2, int var3, Consumer<IAction> var4);

    public IAction create(int var1, int var2, Consumer<IAction> var3);

    public IAction create(String var1, int var2, Consumer<IAction> var3);

    public IAction create(int var1, Consumer<IAction> var2);

    public IAction create(String var1, Consumer<IAction> var2);

    public String getName();

    public IActionManager setName(String var1);

    public boolean inDebugMode();

    public IActionManager setDebugMode(boolean var1);

    public IAction create(String var1);

    public IAction create(Consumer<IAction> var1);

    public IConditionalAction create(Function<IAction, Boolean> var1, Consumer<IAction> var2);

    public IConditionalAction create(String var1, Function<IAction, Boolean> var2, Consumer<IAction> var3);

    public IConditionalAction create(Function<IAction, Boolean> var1, Consumer<IAction> var2, Function<IAction, Boolean> var3);

    public IConditionalAction create(String var1, Function<IAction, Boolean> var2, Consumer<IAction> var3, Function<IAction, Boolean> var4);

    public IConditionalAction create(Function<IAction, Boolean> var1, Consumer<IAction> var2, Function<IAction, Boolean> var3, Consumer<IAction> var4);

    public IConditionalAction create(String var1, Function<IAction, Boolean> var2, Consumer<IAction> var3, Function<IAction, Boolean> var4, Consumer<IAction> var5);

    public IActionQueue createQueue(String var1);

    public IActionQueue createQueue(String var1, boolean var2);

    public IActionQueue getOrCreateQueue(String var1);

    public IActionQueue getOrCreateQueue(String var1, boolean var2);

    public IActionQueue getQueue(String var1);

    public boolean hasQueue(String var1);

    public boolean removeQueue(String var1);

    public IActionQueue getSequentialQueue();

    public IAction schedule(IAction var1);

    public void schedule(IAction ... var1);

    public IAction schedule(Consumer<IAction> var1);

    public void schedule(Consumer<IAction> ... var1);

    public IAction schedule(int var1, Consumer<IAction> var2);

    public IAction schedule(String var1, Consumer<IAction> var2);

    public IAction schedule(String var1, int var2, Consumer<IAction> var3);

    public IAction schedule(String var1, int var2, int var3, Consumer<IAction> var4);

    public IAction schedule(int var1, int var2, Consumer<IAction> var3);

    public IAction scheduleActionAt(int var1, IAction var2);

    public IActionQueue getConditionalQueue();

    public IConditionalAction schedule(IConditionalAction var1);

    public void schedule(IConditionalAction ... var1);

    public IConditionalAction schedule(Function<IAction, Boolean> var1, Consumer<IAction> var2);

    public IConditionalAction schedule(Function<IAction, Boolean> var1, Consumer<IAction> var2, Function<IAction, Boolean> var3);

    public IConditionalAction schedule(Function<IAction, Boolean> var1, Consumer<IAction> var2, Function<IAction, Boolean> var3, Consumer<IAction> var4);

    public IConditionalAction schedule(String var1, Function<IAction, Boolean> var2, Consumer<IAction> var3);

    public IConditionalAction schedule(String var1, Function<IAction, Boolean> var2, Consumer<IAction> var3, Function<IAction, Boolean> var4);

    public IConditionalAction schedule(String var1, Function<IAction, Boolean> var2, Consumer<IAction> var3, Function<IAction, Boolean> var4, Consumer<IAction> var5);

    public IActionQueue getParallelQueue();

    public IAction scheduleParallel(IAction var1);

    public void scheduleParallel(IAction ... var1);

    public IAction scheduleParallel(Consumer<IAction> var1);

    public void scheduleParallel(Consumer<IAction> ... var1);

    public IAction scheduleParallel(int var1, Consumer<IAction> var2);

    public IAction scheduleParallel(int var1, int var2, Consumer<IAction> var3);

    public IAction scheduleParallel(String var1, Consumer<IAction> var2);

    public IAction scheduleParallel(String var1, int var2, Consumer<IAction> var3);

    public IAction scheduleParallel(String var1, int var2, int var3, Consumer<IAction> var4);

    public IActionQueue[] getAllQueues();

    public boolean hasAny(String var1);

    public IAction getAny(String var1);

    public boolean cancelAny(String var1);

    public void clear();

    public IActionChain chain();

    public IActionChain parallelChain();

    public String printQueues();
}

