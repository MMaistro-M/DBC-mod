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
import noppes.npcs.containers.ContainerNPCBankInterface;

public class SlotNpcBankCurrency
extends Slot {
    public ItemStack item;

    public SlotNpcBankCurrency(ContainerNPCBankInterface containerplayer, IInventory iinventory, int i, int j, int k) {
        super(iinventory, i, j, k);
    }

    public int func_75219_a() {
        return 64;
    }

    public boolean func_75214_a(ItemStack itemstack) {
        if (this.item == null) {
            return false;
        }
        return this.item.func_77973_b() == itemstack.func_77973_b() && (!this.item.func_77981_g() || this.item.func_77960_j() == itemstack.func_77960_j());
    }
}

