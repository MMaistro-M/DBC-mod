/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.item.ItemStack;

public interface ITileIcon {
    public boolean canEdit();

    public void setTime(long var1);

    public void setIcon(ItemStack var1);
}

