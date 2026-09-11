/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.api.common.painting;

import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.painting.PaintType;

public interface IPaintingTool {
    public boolean getToolHasColour(ItemStack var1);

    public int getToolColour(ItemStack var1);

    public void setToolColour(ItemStack var1, int var2);

    public void setToolPaintType(ItemStack var1, PaintType var2);

    public PaintType getToolPaintType(ItemStack var1);
}

