/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.inventory;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.inventory.IInventorySlotUpdate;
import riskyken.armourersWorkshop.utils.NBTHelper;

public class WardrobeInventory
implements IInventory {
    private ItemStack[] wardrobeItemStacks = new ItemStack[10];
    private boolean inventoryChanged;
    private final IInventorySlotUpdate callback;
    private final ISkinType skinType;

    public WardrobeInventory(IInventorySlotUpdate callback, ISkinType skinType) {
        this.callback = callback;
        this.skinType = skinType;
    }

    public ISkinType getSkinType() {
        return this.skinType;
    }

    public int func_70302_i_() {
        return this.wardrobeItemStacks.length;
    }

    public ItemStack func_70301_a(int slot) {
        return this.wardrobeItemStacks[slot];
    }

    public ItemStack func_70298_a(int slot, int count) {
        ItemStack itemstack = this.func_70301_a(slot);
        if (itemstack != null) {
            if (itemstack.field_77994_a <= count) {
                this.func_70299_a(slot, null);
            } else {
                itemstack = itemstack.func_77979_a(count);
                this.func_70296_d();
            }
        }
        return itemstack;
    }

    public ItemStack func_70304_b(int slot) {
        ItemStack item = this.func_70301_a(slot);
        this.func_70299_a(slot, null);
        return item;
    }

    public void func_70299_a(int slot, ItemStack stack) {
        this.wardrobeItemStacks[slot] = stack;
        if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
            stack.field_77994_a = this.func_70297_j_();
        }
        this.callback.setInventorySlotContents(this, slot, stack);
        this.func_70296_d();
    }

    public String func_145825_b() {
        return "pasta";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {
        this.inventoryChanged = true;
    }

    public boolean func_70300_a(EntityPlayer player) {
        return !player.field_70128_L;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int slot, ItemStack stack) {
        return true;
    }

    public void writeItemsToNBT(NBTTagCompound compound) {
        NBTHelper.writeStackArrayToNBT(compound, this.skinType.getRegistryName(), this.wardrobeItemStacks);
    }

    public void readItemsFromNBT(NBTTagCompound compound) {
        NBTHelper.readStackArrayFromNBT(compound, this.skinType.getRegistryName(), this.wardrobeItemStacks);
    }

    public void dropItems(EntityPlayer player) {
        World world = player.field_70170_p;
        double x = player.field_70165_t;
        double y = player.field_70163_u;
        double z = player.field_70161_v;
        for (int i = 0; i < this.func_70302_i_(); ++i) {
            ItemStack stack = this.func_70301_a(i);
            if (stack == null) continue;
            float f = 0.7f;
            double xV = (double)(world.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5;
            double yV = (double)(world.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5;
            double zV = (double)(world.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5;
            EntityItem entityitem = new EntityItem(world, x + xV, y + yV, z + zV, stack);
            world.func_72838_d((Entity)entityitem);
            this.func_70299_a(i, null);
        }
    }
}

