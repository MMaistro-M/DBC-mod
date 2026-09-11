/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package riskyken.armourersWorkshop.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import riskyken.armourersWorkshop.common.inventory.IInventorySlotUpdate;
import riskyken.armourersWorkshop.utils.NBTHelper;

public class ModInventory
implements IInventory {
    private static final String TAG_ITEMS = "items";
    private final String name;
    private final ItemStack[] slots;
    private final IInventorySlotUpdate callback;
    private final TileEntity parent;

    public ModInventory(String name, int slotCount) {
        this(name, slotCount, null, null);
    }

    public ModInventory(String name, int slotCount, TileEntity parent) {
        this(name, slotCount, parent, null);
    }

    public ModInventory(String name, int slotCount, IInventorySlotUpdate callback) {
        this(name, slotCount, null, callback);
    }

    public ModInventory(String name, int slotCount, TileEntity parent, IInventorySlotUpdate callback) {
        this.name = name;
        this.slots = new ItemStack[slotCount];
        this.parent = parent;
        this.callback = callback;
    }

    public int func_70302_i_() {
        return this.slots.length;
    }

    public ItemStack func_70301_a(int slotId) {
        return this.slots[slotId];
    }

    public ItemStack func_70298_a(int slotId, int count) {
        ItemStack itemstack = this.func_70301_a(slotId);
        if (itemstack != null) {
            if (itemstack.field_77994_a <= count) {
                this.func_70299_a(slotId, null);
            } else {
                itemstack = itemstack.func_77979_a(count);
                this.func_70299_a(slotId, this.func_70301_a(slotId));
                this.func_70296_d();
            }
        }
        return itemstack;
    }

    public ItemStack func_70304_b(int slotId) {
        return this.func_70301_a(slotId);
    }

    public void func_70299_a(int slotId, ItemStack stack) {
        this.slots[slotId] = stack;
        if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
            stack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
        if (this.callback != null) {
            this.callback.setInventorySlotContents(this, slotId, stack);
        }
    }

    public String func_145825_b() {
        return this.name;
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {
        if (this.parent != null) {
            this.parent.func_70296_d();
        }
    }

    public boolean func_70300_a(EntityPlayer player) {
        return !player.field_70128_L;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int slotId, ItemStack stack) {
        return true;
    }

    public void saveItemsToNBT(NBTTagCompound compound) {
        NBTHelper.writeStackArrayToNBT(compound, TAG_ITEMS, this.slots);
    }

    public void loadItemsFromNBT(NBTTagCompound compound) {
        NBTHelper.readStackArrayFromNBT(compound, TAG_ITEMS, this.slots);
    }
}

