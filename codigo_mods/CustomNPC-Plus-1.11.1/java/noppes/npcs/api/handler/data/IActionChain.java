/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import java.util.function.Consumer;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.IActionQueue;

public interface IActionChain {
    public String getName();

    public IActionChain setName(String var1);

    public IActionQueue getQueue();

    public IActionChain after(int var1, String var2, Consumer<IAction> var3);

    public IActionChain after(int var1, Consumer<IAction> var2);

    public IActionChain start();
}

