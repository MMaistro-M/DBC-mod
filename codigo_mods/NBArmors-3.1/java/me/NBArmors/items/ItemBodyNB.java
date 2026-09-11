/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package me.NBArmors.items;

import JinRyuu.JRMCore.JRMCoreH2;
import JinRyuu.JRMCore.items.ItemBodysuit;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBodyNB
extends ItemBodysuit {
    private int defcol = JRMCoreH2.cols[15];
    private String Display = "Color1";
    public String armorNamePrefix;

    public ItemBodyNB(int defcol, String armornamePrefix) {
        super(defcol);
        this.armorNamePrefix = armornamePrefix;
    }

    @Override
    public String getTextureFile() {
        return "NBArmors:";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("NBArmors:" + this.func_77658_a().replaceAll("item.", ""));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String armor = "";
        armor = "NBArmors:textures/armor/" + this.armorNamePrefix + ".png";
        return armor;
    }

    @Override
    public String getColorReadable(ItemStack par1) {
        int i = this.getColor(par1);
        for (int j = 0; j < JRMCoreH2.cols.length; ++j) {
            if (JRMCoreH2.cols[j] != i) continue;
            return JRMCoreH2.colNams[j];
        }
        return JRMCoreH2.colNams[15];
    }

    @Override
    public boolean hasColor(ItemStack p_82816_1_) {
        return !p_82816_1_.func_77942_o() ? false : (!p_82816_1_.func_77978_p().func_150297_b(this.Display, 10) ? false : p_82816_1_.func_77978_p().func_74775_l(this.Display).func_150297_b("color", 3));
    }

    @Override
    public int getColor(ItemStack p_82814_1_) {
        NBTTagCompound nbttagcompound = p_82814_1_.func_77978_p();
        if (nbttagcompound == null) {
            return this.defcol;
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l(this.Display);
        return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.func_150297_b("color", 3) ? nbttagcompound1.func_74762_e("color") : this.defcol);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack item, int var) {
        return this.getColor(item);
    }

    @Override
    public void removeColor(ItemStack p_82815_1_) {
        NBTTagCompound nbttagcompound1;
        NBTTagCompound nbttagcompound = p_82815_1_.func_77978_p();
        if (nbttagcompound != null && (nbttagcompound1 = nbttagcompound.func_74775_l(this.Display)).func_74764_b("color")) {
            nbttagcompound1.func_82580_o("color");
        }
    }

    @Override
    public ItemStack setColor(ItemStack p_82813_1_, int p_82813_2_) {
        NBTTagCompound nbttagcompound = p_82813_1_.func_77978_p();
        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            p_82813_1_.func_77982_d(nbttagcompound);
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l(this.Display);
        if (!nbttagcompound.func_150297_b(this.Display, 10)) {
            nbttagcompound.func_74782_a(this.Display, (NBTBase)nbttagcompound1);
        }
        nbttagcompound1.func_74768_a("color", p_82813_2_);
        p_82813_1_.func_77982_d(nbttagcompound);
        return p_82813_1_;
    }
}

