/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

class SlotNpcTraderItems
extends Slot {
    public SlotNpcTraderItems(IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
    }

    public void onPickupFromSlot(ItemStack itemstack) {
        if (itemstack == null) {
            return;
        }
        if (this.func_75211_c() == null) {
            return;
        }
        if (itemstack.func_77973_b() != this.func_75211_c().func_77973_b()) {
            return;
        }
        --itemstack.field_77994_a;
    }

    public int func_75219_a() {
        return 64;
    }

    public boolean func_75214_a(ItemStack itemstack) {
        return false;
    }
}

