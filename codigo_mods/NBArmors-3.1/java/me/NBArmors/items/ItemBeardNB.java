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
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package me.NBArmors.items;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJFC;
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
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBeardNB
extends ItemVanity {
    public int defcol = vanitycolorex.cols[15];
    public String Display = "Color1";
    public final int armorType;
    public final int VANITY_BARRIER_OF_TIME = 0;
    public final int VANITY_SCARF = 1;
    public final int VANITY_EARS1 = 2;
    public final int VANITY_MULEHORNS = 3;
    public final int VANITY_MASKG = 4;
    public final int VANITY_POTARA = 5;
    public final int VANITY_PUAR = 7;
    public final int VANITY_GGMASK = 8;
    public final int VANITY_SHUKATANA = 9;
    public final int VANITY_BRAVE = 10;
    public final int VANITY_COAT = 11;
    public final int VANITY_AEOS = 12;
    public final int VANITY_BEARD = 13;
    public int type = -1;

    public ItemBeardNB(int defcol, ItemArmor.ArmorMaterial par2ArmorMaterial, int armorType, String armornamePrefix, int type) {
        super(defcol, armorType, armornamePrefix, type);
        this.defcol = defcol;
        this.armorType = armorType;
        this.rl = par2ArmorMaterial;
        this.type = type;
    }

    @Override
    public ModelBiped giMdl(int slt, EntityLivingBase e) {
        if (this.wear(e)) {
            boolean has = false;
            for (int i = 0; i < NBmain.ItemsVanityNum.length; ++i) {
                if (NBmain.ItemVanity3[i] <= -1 || this.type != NBmain.ItemVanity3[i]) continue;
                has = true;
                return NBHJBRA.vanityextra2[this.type];
            }
            if (!has) {
                if (slt != 5 && this.func_77658_a().contains("Head")) {
                    return NBHJBRA.VanityModelNNB2;
                }
                return NBHJBRA.VanityModelNNB3;
            }
            return NBHJBRA.VanityModelNNB2;
        }
        return null;
    }

    @Override
    public boolean wear(EntityLivingBase e) {
        return JRMCoreH.JBRA() && (e instanceof EntityPlayer || JRMCoreH.JFC() && JRMCoreHJFC.isChildNPC((Entity)e));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int par3) {
        ModelBiped modelbiped = this.giMdl(par3, entityLiving);
        if (this.wear(entityLiving)) {
            modelbiped = NBHJBRA.showModel(modelbiped, entityLiving, itemStack, par3);
        } else {
            ItemStack var11;
            modelbiped.field_78116_c.field_78806_j = par3 == 0 || par3 == 1;
            modelbiped.field_78115_e.field_78806_j = par3 == 0;
            modelbiped.field_78114_d.field_78806_j = false;
            modelbiped.field_78115_e.field_78806_j = par3 == 0 || par3 == 1 || par3 == 2;
            modelbiped.field_78112_f.field_78806_j = par3 == 0 || par3 == 1;
            modelbiped.field_78113_g.field_78806_j = par3 == 0 || par3 == 1;
            modelbiped.field_78123_h.field_78806_j = par3 == 2 || par3 == 3;
            boolean bl = modelbiped.field_78124_i.field_78806_j = par3 == 2 || par3 == 3;
            if (entityLiving instanceof EntityMob) {
                modelbiped.field_78112_f.field_78806_j = false;
                modelbiped.field_78113_g.field_78806_j = false;
            }
            int n = modelbiped.field_78120_m = (var11 = entityLiving.func_70694_bm()) != null ? 1 : 0;
            if (var11 != null && entityLiving instanceof EntityPlayer && ((EntityPlayer)entityLiving).func_71052_bv() > 0) {
                EnumAction var12 = var11.func_77975_n();
                if (var12 == EnumAction.block) {
                    modelbiped.field_78120_m = 3;
                } else if (var12 == EnumAction.bow) {
                    modelbiped.field_78118_o = true;
                }
            }
            modelbiped.field_78117_n = entityLiving.func_70093_af();
            modelbiped.field_78093_q = entityLiving.func_70115_ae();
            modelbiped.field_78091_s = entityLiving.func_70631_g_();
        }
        return modelbiped;
    }

    @Override
    public String getColorReadable(ItemStack par1) {
        int i = this.getColor(par1);
        for (int j = 0; j < vanitycolorex.cols.length; ++j) {
            if (vanitycolorex.cols[j] != i) continue;
            return vanitycolorex.colNams[j];
        }
        return vanitycolorex.colNams[15];
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
        if (type.equals("overlay")) {
            return null;
        }
        String r = "";
        String j = "";
        j = "jbra";
        if (stack.toString().contains("leg") || stack.toString().contains("Leg")) {
            this.id = 2;
            r = "NBArmors:textures/armor/" + this.na + "_" + 2 + j + ".png";
        } else {
            r = stack.toString().contains("boot") || stack.toString().contains("Boot") ? "NBArmors:textures/armor/" + this.na + "_3" + j + ".png" : (stack.toString().contains("head") || stack.toString().contains("Head") ? "NBArmors:textures/armor/" + this.na + "_0" + j + ".png" : "NBArmors:textures/armor/" + this.na + "_1" + j + ".png");
        }
        return r;
    }
}

