/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.items.MoneyBagContents;

public class ItemMoneyBag
extends Item {
    public ItemMoneyBag(int i) {
        this.field_77777_bU = 1;
        this.func_77637_a(CustomItems.tab);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.field_72995_K) {
            return par1ItemStack;
        }
        if (par1ItemStack.field_77990_d == null) {
            par1ItemStack.field_77990_d = new NBTTagCompound();
        }
        MoneyBagContents contents = new MoneyBagContents(par3EntityPlayer);
        NoppesUtil.openGUI(par3EntityPlayer, new GuiScreen());
        return par1ItemStack;
    }
}

