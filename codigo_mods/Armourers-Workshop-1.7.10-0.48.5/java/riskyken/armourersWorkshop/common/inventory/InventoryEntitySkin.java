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
package riskyken.armourersWorkshop.common.inventory;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.inventory.IInventorySlotUpdate;

public class InventoryEntitySkin
implements IInventory {
    private static final String TAG_ITEMS = "items";
    private static final String TAG_SLOT = "slot";
    private final ItemStack[] skinSlots;
    private final IInventorySlotUpdate callback;
    private final ArrayList<ISkinType> skinTypes;

    public InventoryEntitySkin(IInventorySlotUpdate callback, ArrayList<ISkinType> skinTypes) {
        this.skinSlots = new ItemStack[skinTypes.size()];
        this.callback = callback;
        this.skinTypes = skinTypes;
    }

    public ArrayList<ISkinType> getSkinTypes() {
        return this.skinTypes;
    }

    public int func_70302_i_() {
        return this.skinSlots.length;
    }

    public ItemStack func_70301_a(int slotId) {
        return this.skinSlots[slotId];
    }

    public ItemStack func_70298_a(int slotId, int count) {
        ItemStack itemstack = this.func_70301_a(slotId);
        if (itemstack != null) {
            if (itemstack.field_77994_a <= count) {
                this.func_70299_a(slotId, null);
            } else {
                itemstack = itemstack.func_77979_a(count);
                this.func_70296_d();
            }
        }
        return itemstack;
    }

    public ItemStack func_70304_b(int slotId) {
        return this.func_70301_a(slotId);
    }

    public void func_70299_a(int slotId, ItemStack stack) {
        this.skinSlots[slotId] = stack;
        if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
            stack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
        if (this.callback != null) {
            this.callback.setInventorySlotContents(this, slotId, stack);
        }
    }

    public String func_145825_b() {
        return "skinInventory";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {
    }

    public boolean func_70300_a(EntityPlayer entityPlayer) {
        return true;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    public void saveItemsToNBT(NBTTagCompound compound) {
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < this.func_70302_i_(); ++i) {
            ItemStack stack = this.func_70301_a(i);
            if (stack == null) continue;
            NBTTagCompound item = new NBTTagCompound();
            item.func_74774_a(TAG_SLOT, (byte)i);
            stack.func_77955_b(item);
            items.func_74742_a((NBTBase)item);
        }
        compound.func_74782_a(TAG_ITEMS, (NBTBase)items);
    }

    public void loadItemsFromNBT(NBTTagCompound compound) {
        int i;
        NBTTagList items = compound.func_150295_c(TAG_ITEMS, 10);
        for (i = 0; i < this.skinSlots.length; ++i) {
            this.skinSlots[i] = null;
        }
        for (i = 0; i < items.func_74745_c(); ++i) {
            NBTTagCompound item = items.func_150305_b(i);
            byte slot = item.func_74771_c(TAG_SLOT);
            if (slot < 0 || slot >= this.func_70302_i_()) continue;
            this.func_70299_a(slot, ItemStack.func_77949_a((NBTTagCompound)item));
        }
    }
}

