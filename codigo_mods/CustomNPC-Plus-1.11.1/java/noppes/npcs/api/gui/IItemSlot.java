/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.Slot
 */
package noppes.npcs.api.gui;

import net.minecraft.inventory.Slot;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.api.item.IItemStack;

public interface IItemSlot
extends ICustomGuiComponent {
    public boolean hasStack();

    public IItemStack getStack();

    public IItemSlot setStack(IItemStack var1);

    public Slot getMCSlot();
}

