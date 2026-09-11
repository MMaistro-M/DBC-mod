/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import java.util.Queue;
import java.util.function.Consumer;
import noppes.npcs.api.handler.IActionManager;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.IActionChain;

public interface IActionQueue {
    public IActionQueue start();

    public IActionQueue stop();

    public IActionManager getManager();

    public String getName();

    public boolean isParallel();

    public Queue<IAction> getQueue();

    public IActionQueue setParallel(boolean var1);

    public boolean isStoppedWhenEmpty();

    public IActionQueue stopWhenEmpty(boolean var1);

    public boolean isKilledWhenEmpty();

    public int getKillWhenEmptyAfter();

    public IActionQueue killWhenEmpty(boolean var1);

    public IActionQueue killWhenEmptyAfter(int var1);

    public boolean isDead();

    public IActionQueue kill();

    public IAction schedule(IAction var1);

    public void schedule(IAction ... var1);

    public void schedule(Consumer<IAction> ... var1);

    public IAction schedule(Consumer<IAction> var1);

    public IAction schedule(int var1, Consumer<IAction> var2);

    public IAction schedule(int var1, int var2, Consumer<IAction> var3);

    public IAction schedule(String var1, Consumer<IAction> var2);

    public IAction schedule(String var1, int var2, Consumer<IAction> var3);

    public IAction schedule(String var1, int var2, int var3, Consumer<IAction> var4);

    public IAction scheduleActionAt(int var1, IAction var2);

    public boolean hasActiveTasks();

    public int getIndex(IAction var1);

    public IAction getCurrentAction();

    public boolean has(IAction var1);

    public boolean has(String var1);

    public IAction get(String var1);

    public boolean cancel(IAction var1);

    public boolean cancel(String var1);

    public void clear();

    public IActionChain chain();

    public String printQueue();
}

