/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.Slot
 */
package noppes.npcs.scripted.gui;

import net.minecraft.inventory.Slot;
import noppes.npcs.api.gui.IItemSlot;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.scripted.gui.ScriptGuiComponent;

public class ScriptGuiItemSlot
extends ScriptGuiComponent
implements IItemSlot {
    IItemStack stack;

    public ScriptGuiItemSlot() {
    }

    public ScriptGuiItemSlot(int id, int x, int y) {
        this.setID(id);
        this.setPos(x, y);
    }

    public ScriptGuiItemSlot(int id, int x, int y, IItemStack stack) {
        this(id, x, y);
        this.setStack(stack);
    }

    @Override
    public boolean hasStack() {
        return this.stack != null && this.stack.getStackSize() > 0;
    }

    @Override
    public IItemStack getStack() {
        return this.stack;
    }

    @Override
    public IItemSlot setStack(IItemStack itemStack) {
        this.stack = itemStack;
        return this;
    }

    @Override
    public Slot getMCSlot() {
        return null;
    }

    @Override
    public int getType() {
        return 5;
    }
}

