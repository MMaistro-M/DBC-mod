/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import noppes.npcs.containers.ContainerNPCTrader;

public class InventoryNpcTrader
implements IInventory {
    private final String inventoryTitle;
    private final int slotsCount;
    private final ItemStack[] inventoryContents;
    private final ContainerNPCTrader con;

    public InventoryNpcTrader(String s, int i, ContainerNPCTrader con) {
        this.con = con;
        this.inventoryTitle = s;
        this.slotsCount = i;
        this.inventoryContents = new ItemStack[i];
    }

    public ItemStack func_70301_a(int i) {
        ItemStack toBuy = this.inventoryContents[i];
        if (toBuy == null) {
            return null;
        }
        return ItemStack.func_77944_b((ItemStack)toBuy);
    }

    public ItemStack func_70298_a(int i, int j) {
        if (this.inventoryContents[i] != null) {
            ItemStack itemstack = this.inventoryContents[i];
            return ItemStack.func_77944_b((ItemStack)itemstack);
        }
        return null;
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        if (itemstack != null) {
            this.inventoryContents[i] = itemstack.func_77946_l();
        }
        this.func_70296_d();
    }

    public int func_70302_i_() {
        return this.slotsCount;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return true;
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
        this.con.func_75130_a(this);
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }
}

