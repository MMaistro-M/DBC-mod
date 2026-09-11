/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 */
package JinRyuu.DragonBC.common.Render;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SpacePod01TileEntity
extends TileEntity
implements IInventory {
    private ItemStack[] inventory = new ItemStack[27];

    public int func_70302_i_() {
        return this.inventory.length;
    }

    public ItemStack func_70301_a(int slotIndex) {
        return this.inventory[slotIndex];
    }

    public void func_70299_a(int slot, ItemStack stack) {
        this.inventory[slot] = stack;
        if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
            stack.field_77994_a = this.func_70297_j_();
        }
    }

    public ItemStack func_70298_a(int slotIndex, int amount) {
        ItemStack stack = this.func_70301_a(slotIndex);
        if (stack != null) {
            if (stack.field_77994_a <= amount) {
                this.func_70299_a(slotIndex, null);
            } else {
                stack = stack.func_77979_a(amount);
                if (stack.field_77994_a == 0) {
                    this.func_70299_a(slotIndex, null);
                }
            }
        }
        return stack;
    }

    public ItemStack func_70304_b(int slotIndex) {
        ItemStack stack = this.func_70301_a(slotIndex);
        if (stack != null) {
            this.func_70299_a(slotIndex, null);
        }
        return stack;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer player) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && player.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) < 64.0;
    }

    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
        return false;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return false;
    }

    public boolean canUpdate() {
        return false;
    }

    public String func_145825_b() {
        return null;
    }

    public boolean func_145818_k_() {
        return false;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }
}

