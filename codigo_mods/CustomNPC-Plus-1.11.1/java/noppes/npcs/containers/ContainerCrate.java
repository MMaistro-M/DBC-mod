/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCrate
extends Container {
    public IInventory lowerChestInventory;
    public IInventory upperChestInventory;
    private int numRows;

    public ContainerCrate(IInventory par1IInventory, IInventory par2IInventory) {
        this.lowerChestInventory = par2IInventory;
        this.upperChestInventory = par1IInventory;
        this.numRows = 0;
        if (par2IInventory != null && par1IInventory != null) {
            int k;
            int j;
            this.numRows = par2IInventory.func_70302_i_() / 9;
            par2IInventory.func_70295_k_();
            int i = (this.numRows - 4) * 18;
            for (j = 0; j < this.numRows; ++j) {
                for (k = 0; k < 9; ++k) {
                    this.func_75146_a(new Slot(par2IInventory, k + j * 9, 8 + k * 18, 18 + j * 18));
                }
            }
            for (j = 0; j < 3; ++j) {
                for (k = 0; k < 9; ++k) {
                    this.func_75146_a(new Slot(par1IInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
                }
            }
            for (j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot(par1IInventory, j, 8 + j * 18, 161 + i));
            }
        }
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return this.lowerChestInventory.func_70300_a(par1EntityPlayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 < this.numRows * 9 ? !this.func_75135_a(itemstack1, this.numRows * 9, this.field_75151_b.size(), true) : !this.func_75135_a(itemstack1, 0, this.numRows * 9, false)) {
                return null;
            }
            if (itemstack1.field_77994_a == 0) {
                slot.func_75215_d((ItemStack)null);
            } else {
                slot.func_75218_e();
            }
        }
        return itemstack;
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        this.lowerChestInventory.func_70305_f();
    }

    public IInventory getLowerChestInventory() {
        return this.lowerChestInventory;
    }
}

