/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package noppes.npcs.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import noppes.npcs.roles.RoleCompanion;

class SlotCompanionArmor
extends Slot {
    final int armorType;
    final RoleCompanion role;

    public SlotCompanionArmor(RoleCompanion role, IInventory iinventory, int id, int x, int y, int type) {
        super(iinventory, id, x, y);
        this.armorType = type;
        this.role = role;
    }

    public int func_75219_a() {
        return 1;
    }

    public IIcon func_75212_b() {
        return ItemArmor.func_94602_b((int)this.armorType);
    }

    public boolean func_75214_a(ItemStack itemstack) {
        if (itemstack.func_77973_b() instanceof ItemArmor && this.role.canWearArmor(itemstack)) {
            return ((ItemArmor)itemstack.func_77973_b()).field_77881_a == this.armorType;
        }
        if (itemstack.func_77973_b() instanceof ItemBlock) {
            return this.armorType == 0;
        }
        return false;
    }
}

