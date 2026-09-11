/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import noppes.npcs.blocks.tiles.TileVariant;

public abstract class TileNpcContainer
extends TileVariant
implements IInventory {
    private ItemStack[] chestContents = new ItemStack[this.func_70302_i_()];
    public String customName = "";
    public int playerUsing = 0;

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        NBTTagList nbttaglist = compound.func_150295_c("Items", 10);
        this.chestContents = new ItemStack[this.func_70302_i_()];
        if (compound.func_150297_b("CustomName", 8)) {
            this.customName = compound.func_74779_i("CustomName");
        }
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
            if (j < 0 || j >= this.chestContents.length) continue;
            this.chestContents[j] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.chestContents[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        compound.func_74782_a("Items", (NBTBase)nbttaglist);
        if (this.func_145818_k_()) {
            compound.func_74778_a("CustomName", this.customName);
        }
    }

    public boolean func_145842_c(int p_145842_1_, int p_145842_2_) {
        if (p_145842_1_ == 1) {
            this.playerUsing = p_145842_2_;
            return true;
        }
        return super.func_145842_c(p_145842_1_, p_145842_2_);
    }

    public int func_70302_i_() {
        return 54;
    }

    public ItemStack func_70301_a(int var1) {
        return this.chestContents[var1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.chestContents[par1] != null) {
            if (this.chestContents[par1].field_77994_a <= par2) {
                ItemStack itemstack = this.chestContents[par1];
                this.chestContents[par1] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack = this.chestContents[par1].func_77979_a(par2);
            if (this.chestContents[par1].field_77994_a == 0) {
                this.chestContents[par1] = null;
            }
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.chestContents[par1] != null) {
            ItemStack itemstack = this.chestContents[par1];
            this.chestContents[par1] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.chestContents[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
    }

    public String func_145825_b() {
        return this.func_145818_k_() ? this.customName : this.getName();
    }

    public abstract String getName();

    public boolean func_145818_k_() {
        return !this.customName.isEmpty();
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer player) {
        return player.field_70128_L || this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : player.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
        ++this.playerUsing;
    }

    public void func_70305_f() {
        --this.playerUsing;
    }

    public boolean func_94041_b(int var1, ItemStack var2) {
        return true;
    }

    public void dropItems(World world, int x, int y, int z) {
        for (int i1 = 0; i1 < this.func_70302_i_(); ++i1) {
            ItemStack itemstack = this.func_70301_a(i1);
            if (itemstack == null) continue;
            float f = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
            float f1 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
            float f2 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
            while (itemstack.field_77994_a > 0) {
                int j1 = world.field_73012_v.nextInt(21) + 10;
                if (j1 > itemstack.field_77994_a) {
                    j1 = itemstack.field_77994_a;
                }
                itemstack.field_77994_a -= j1;
                EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));
                float f3 = 0.05f;
                entityitem.field_70159_w = (float)world.field_73012_v.nextGaussian() * f3;
                entityitem.field_70181_x = (float)world.field_73012_v.nextGaussian() * f3 + 0.2f;
                entityitem.field_70179_y = (float)world.field_73012_v.nextGaussian() * f3;
                if (itemstack.func_77942_o()) {
                    entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                }
                world.func_72838_d((Entity)entityitem);
            }
        }
    }
}

