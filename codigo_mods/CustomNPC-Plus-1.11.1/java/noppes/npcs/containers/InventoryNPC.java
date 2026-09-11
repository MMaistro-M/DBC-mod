/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryNPC
implements IInventory {
    private final String inventoryTitle;
    private final int slotsCount;
    private final ItemStack[] inventoryContents;
    private final Container con;

    public InventoryNPC(String s, int i, Container con) {
        this.con = con;
        this.inventoryTitle = s;
        this.slotsCount = i;
        this.inventoryContents = new ItemStack[i];
    }

    public ItemStack func_70301_a(int i) {
        return this.inventoryContents[i];
    }

    public ItemStack func_70298_a(int i, int j) {
        if (this.inventoryContents[i] != null) {
            if (this.inventoryContents[i].field_77994_a <= j) {
                ItemStack itemstack = this.inventoryContents[i];
                this.inventoryContents[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = this.inventoryContents[i].func_77979_a(j);
            if (this.inventoryContents[i].field_77994_a == 0) {
                this.inventoryContents[i] = null;
            }
            return itemstack1;
        }
        return null;
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.inventoryContents[i] = itemstack;
        if (itemstack != null && itemstack.field_77994_a > this.func_70297_j_()) {
            itemstack.field_77994_a = this.func_70297_j_();
        }
    }

    public int func_70302_i_() {
        return this.slotsCount;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return false;
    }

    public ItemStack func_70304_b(int i) {
        return null;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public String func_145825_b() {
        return this.inventoryTitle;
    }

    public boolean func_145818_k_() {
        return true;
    }

    public void func_70296_d() {
        this.con.func_75130_a((IInventory)this);
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }
}

