/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data.action.chain;

import java.util.function.Consumer;
import noppes.npcs.api.handler.data.IAction;
import noppes.npcs.api.handler.data.IActionChain;
import noppes.npcs.api.handler.data.IActionQueue;
import noppes.npcs.controllers.data.action.ActionManager;

public class ActionChain
implements IActionChain {
    protected final ActionManager manager;
    protected final IActionQueue queue;
    protected String name;
    protected int index = 0;
    protected int offset;

    public ActionChain(ActionManager manager, IActionQueue queue, String name) {
        this.manager = manager;
        this.queue = queue;
        this.name = name;
    }

    @Override
    public IActionQueue getQueue() {
        return this.queue;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IActionChain setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IActionChain start() {
        this.manager.start();
        return this;
    }

    @Override
    public IActionChain after(int delay, String name, Consumer<IAction> task) {
        this.offset = this.queue.isParallel() ? this.offset + delay : delay;
        this.queue.schedule(name, this.offset, task).everyTick().once();
        ++this.index;
        return this;
    }

    @Override
    public IActionChain after(int delay, Consumer<IAction> task) {
        return this.after(delay, this.name + this.index, task);
    }
}

