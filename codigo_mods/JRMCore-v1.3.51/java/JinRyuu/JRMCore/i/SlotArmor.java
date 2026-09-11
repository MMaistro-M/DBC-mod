/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package JinRyuu.JRMCore.i;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SlotArmor
extends Slot {
    final int armorType;
    final EntityPlayer player;

    public SlotArmor(EntityPlayer player, IInventory inventory, int slot, int x, int y, int armorType) {
        super(inventory, slot, x, y);
        this.player = player;
        this.armorType = armorType;
    }

    public int func_75219_a() {
        return 1;
    }

    public boolean func_75214_a(ItemStack stack) {
        Item item = stack == null ? null : stack.func_77973_b();
        return item != null && item.isValidArmor(stack, this.armorType, (Entity)this.player);
    }

    @SideOnly(value=Side.CLIENT)
    public void setBackgroundIconTexture(ResourceLocation texture) {
        this.texture = texture;
    }
}

