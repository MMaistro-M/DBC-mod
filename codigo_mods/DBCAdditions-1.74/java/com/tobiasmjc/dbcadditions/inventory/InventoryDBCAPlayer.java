/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package com.tobiasmjc.dbcadditions.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryDBCAPlayer
implements IInventory {
    private final ItemStack[] inventory;
    private final int size;
    private final String name = "DBCA Inventory";
    public static final int INVENTORY_SIZE = 2;

    public InventoryDBCAPlayer(int size) {
        this.size = size;
        this.inventory = new ItemStack[size];
    }

    public void copy(InventoryDBCAPlayer inv) {
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack stack = inv.func_70301_a(i);
            this.inventory[i] = stack == null ? null : stack.func_77946_l();
        }
        this.func_70296_d();
    }

    public int func_70302_i_() {
        return this.size;
    }

    public ItemStack func_70301_a(int index) {
        return index >= 0 && index < this.size ? this.inventory[index] : null;
    }

    public ItemStack func_70298_a(int index, int count) {
        if (this.inventory[index] != null) {
            if (this.inventory[index].field_77994_a <= count) {
                ItemStack itemstack = this.inventory[index];
                this.inventory[index] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack = this.inventory[index].func_77979_a(count);
            if (this.inventory[index].field_77994_a == 0) {
                this.inventory[index] = null;
            }
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int index) {
        if (this.inventory[index] != null) {
            ItemStack itemstack = this.inventory[index];
            this.inventory[index] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int index, ItemStack stack) {
        this.inventory[index] = stack;
        if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
            stack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
    }

    public String func_145825_b() {
        return "DBCA Inventory";
    }

    public boolean func_145818_k_() {
        return "DBCA Inventory".length() > 0;
    }

    public int func_70297_j_() {
        return 1;
    }

    public void func_70296_d() {
        for (int i = 0; i < this.func_70302_i_(); ++i) {
            if (this.func_70301_a(i) == null || this.func_70301_a((int)i).field_77994_a != 0) continue;
            this.inventory[i] = null;
        }
    }

    public boolean func_70300_a(EntityPlayer player) {
        return true;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int index, ItemStack stack) {
        return true;
    }

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < this.func_70302_i_(); ++i) {
            if (this.func_70301_a(i) == null) continue;
            NBTTagCompound item = new NBTTagCompound();
            item.func_74774_a("Slot", (byte)i);
            this.func_70301_a(i).func_77955_b(item);
            items.func_74742_a((NBTBase)item);
        }
        compound.func_74782_a("DBCAInventory", (NBTBase)items);
    }

    public void readFromNBT(NBTTagCompound compound) {
        NBTTagList items = compound.func_150295_c("DBCAInventory", (int)compound.func_74732_a());
        for (int i = 0; i < items.func_74745_c(); ++i) {
            NBTTagCompound item = items.func_150305_b(i);
            byte slot = item.func_74771_c("Slot");
            if (slot < 0 || slot >= this.func_70302_i_()) continue;
            this.inventory[slot] = ItemStack.func_77949_a((NBTTagCompound)item);
        }
    }
}

