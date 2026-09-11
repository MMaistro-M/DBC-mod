/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.common.tileentities.ModTileEntity;
import riskyken.armourersWorkshop.utils.NBTHelper;

public abstract class AbstractTileEntityInventory
extends ModTileEntity
implements IInventory {
    private static final String TAG_ITEMS = "items";
    protected final ItemStack[] items;

    public AbstractTileEntityInventory(int inventorySize) {
        this.items = new ItemStack[inventorySize];
    }

    public int func_70302_i_() {
        return this.items.length;
    }

    public ItemStack func_70301_a(int i) {
        return this.items[i];
    }

    public ItemStack func_70298_a(int i, int count) {
        ItemStack itemstack = this.func_70301_a(i);
        if (itemstack != null) {
            if (itemstack.field_77994_a <= count) {
                this.func_70299_a(i, null);
            } else {
                itemstack = itemstack.func_77979_a(count);
                this.func_70296_d();
            }
        }
        return itemstack;
    }

    public ItemStack func_70304_b(int i) {
        ItemStack item = this.func_70301_a(i);
        this.func_70299_a(i, null);
        return item;
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.items[i] = itemstack;
        if (itemstack != null && itemstack.field_77994_a > this.func_70297_j_()) {
            itemstack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return entityplayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        this.writeItemsToNBT(compound);
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.readItemsFromNBT(compound);
    }

    public void writeBaseToNBT(NBTTagCompound compound) {
        super.func_145841_b(compound);
    }

    public void readBaseFromNBT(NBTTagCompound compound) {
        super.func_145839_a(compound);
    }

    public void readCommonFromNBT(NBTTagCompound compound) {
    }

    public void writeCommonToNBT(NBTTagCompound compound) {
    }

    public void writeItemsToNBT(NBTTagCompound compound) {
        NBTHelper.writeStackArrayToNBT(compound, TAG_ITEMS, this.items);
    }

    public void readItemsFromNBT(NBTTagCompound compound) {
        NBTHelper.readStackArrayFromNBT(compound, TAG_ITEMS, this.items);
    }
}

