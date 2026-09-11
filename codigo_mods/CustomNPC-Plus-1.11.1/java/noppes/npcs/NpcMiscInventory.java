/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs;

import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesUtilPlayer;

public class NpcMiscInventory
implements IInventory {
    public HashMap<Integer, ItemStack> items = new HashMap();
    public int stackLimit = 64;
    private int size;

    public NpcMiscInventory(int size) {
        this.size = size;
    }

    public NBTTagCompound getToNBT() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74782_a("NpcMiscInv", (NBTBase)NBTTags.nbtItemStackList(this.items));
        return nbttagcompound;
    }

    public void setFromNBT(NBTTagCompound nbttagcompound) {
        this.items = NBTTags.getItemStackList(nbttagcompound.func_150295_c("NpcMiscInv", 10));
    }

    public int func_70302_i_() {
        return this.size;
    }

    public ItemStack func_70301_a(int var1) {
        return this.items.get(var1);
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.items.get(par1) == null) {
            return null;
        }
        ItemStack var4 = null;
        if (this.items.get((Object)Integer.valueOf((int)par1)).field_77994_a <= par2) {
            var4 = this.items.get(par1);
            this.items.put(par1, null);
        } else {
            var4 = this.items.get(par1).func_77979_a(par2);
            if (this.items.get((Object)Integer.valueOf((int)par1)).field_77994_a == 0) {
                this.items.put(par1, null);
            }
        }
        return var4;
    }

    public boolean decrStackSize(ItemStack eating, int decrease) {
        for (int slot : this.items.keySet()) {
            ItemStack item = this.items.get(slot);
            if (this.items == null || eating != item || item.field_77994_a < decrease) continue;
            item.func_77979_a(decrease);
            if (item.field_77994_a <= 0) {
                this.items.put(slot, null);
            }
            return true;
        }
        return false;
    }

    public ItemStack func_70304_b(int var1) {
        if (this.items.get(var1) != null) {
            ItemStack var3 = this.items.get(var1);
            this.items.put(var1, null);
            return var3;
        }
        return null;
    }

    public void func_70299_a(int var1, ItemStack var2) {
        if (var1 >= this.func_70302_i_()) {
            return;
        }
        this.items.put(var1, var2);
    }

    public int func_70297_j_() {
        return this.stackLimit;
    }

    public boolean func_70300_a(EntityPlayer var1) {
        return true;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public String func_145825_b() {
        return "Npc Misc Inventory";
    }

    public boolean func_145818_k_() {
        return true;
    }

    public void func_70296_d() {
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean addItemStack(ItemStack item) {
        ItemStack mergable;
        boolean merged = false;
        while ((mergable = this.getMergableItem(item)) != null && mergable.field_77994_a > 0) {
            int size = mergable.func_77976_d() - mergable.field_77994_a;
            if (size > item.field_77994_a) {
                mergable.field_77994_a = mergable.func_77976_d();
                item.field_77994_a -= size;
                merged = true;
                continue;
            }
            mergable.field_77994_a += item.field_77994_a;
            item.field_77994_a = 0;
        }
        if (item.field_77994_a <= 0) {
            return true;
        }
        int slot = this.firstFreeSlot();
        if (slot >= 0) {
            this.items.put(slot, item.func_77946_l());
            item.field_77994_a = 0;
            return true;
        }
        return merged;
    }

    public ItemStack getMergableItem(ItemStack item) {
        for (ItemStack is : this.items.values()) {
            if (!NoppesUtilPlayer.compareItems(item, is, false, false) || is.field_77994_a >= is.func_77976_d()) continue;
            return is;
        }
        return null;
    }

    public int firstFreeSlot() {
        for (int i = 0; i < this.func_70302_i_(); ++i) {
            if (this.items.get(i) != null) continue;
            return i;
        }
        return -1;
    }

    public void setSize(int i) {
        this.size = i;
    }
}

