/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.client.gui.GuiBookBase;
import riskyken.armourersWorkshop.client.guidebook.GuideBook;

@SideOnly(value=Side.CLIENT)
public class GuiGuideBook
extends GuiBookBase {
    private ItemStack stack;

    public GuiGuideBook(ItemStack stack) {
        super(new GuideBook(), 256, 180);
        this.stack = stack;
    }
}

