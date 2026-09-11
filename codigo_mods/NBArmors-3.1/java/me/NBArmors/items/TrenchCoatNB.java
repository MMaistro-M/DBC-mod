/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package me.NBArmors.items;

import JinRyuu.JRMCore.items.ItemVanity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.NBArmors.main.NBHJBRA;
import me.NBArmors.main.NBmain;
import me.NBArmors.main.vanitycolorex;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class TrenchCoatNB
extends ItemVanity {
    private int defcol = vanitycolorex.cols[15];
    private String Display = "Color1";
    public final int armorType;
    private final int VANITY_BARRIER_OF_TIME = 0;
    private final int VANITY_SCARF = 1;
    private final int VANITY_EARS1 = 2;
    private final int VANITY_MULEHORNS = 3;
    private final int VANITY_MASKG = 4;
    private final int VANITY_POTARA = 5;
    private final int VANITY_PUAR = 7;
    private final int VANITY_GGMASK = 8;
    private final int VANITY_SHUKATANA = 9;
    private final int VANITY_BRAVE = 10;
    private final int VANITY_TRENCHCOAT = 11;
    private int type = -1;

    public TrenchCoatNB(int defcol, ItemArmor.ArmorMaterial par2ArmorMaterial, int armorType, String armornamePrefix, int type) {
        super(defcol, armorType, armornamePrefix, type);
        this.defcol = defcol;
        this.armorType = armorType;
        this.rl = par2ArmorMaterial;
        this.type = type;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ModelBiped giMdl(int slt, EntityLivingBase e) {
        if (this.wear(e)) {
            boolean has = false;
            for (int i = 0; i < NBmain.trenchcoatNum.length; ++i) {
                if (NBmain.trenchcoat3[i] <= -1 || this.type != NBmain.trenchcoat3[i]) continue;
                has = true;
                return NBHJBRA.trenchcoat2[this.type];
            }
            if (!has) {
                if (slt != 5 && this.func_77658_a().contains("Head")) {
                    return NBHJBRA.TrenchCoatNB2;
                }
                return NBHJBRA.TrenchCoatNB3;
            }
            return NBHJBRA.TrenchCoatNB2;
        }
        return null;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("NBArmors:" + this.func_77658_a().replaceAll("item.", ""));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack item, int var) {
        return this.getColor(item);
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

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String r = "";
        String j = "";
        j = "jbra";
        r = stack.toString().contains("leg") || stack.toString().contains("Leg") ? "NBArmors:textures/armor/" + this.na + "_2" + j + ".png" : (stack.toString().contains("boot") || stack.toString().contains("Boot") ? "NBArmors:textures/armor/" + this.na + "_3" + j + ".png" : (stack.toString().contains("head") || stack.toString().contains("Head") ? "NBArmors:textures/armor/" + this.na + "_0" + j + ".png" : "NBArmors:textures/armor/" + this.na + "_1" + j + ".png"));
        return r;
    }
}

