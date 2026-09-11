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
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.util.EnumHelper
 */
package me.NBArmors.armors;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJFC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import me.NBArmors.main.NBHJBRA;
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
import net.minecraftforge.common.util.EnumHelper;

public class ArmorNBI
extends ItemArmor {
    private static String armornamePrefix;
    public String modid;
    private int defcol = vanitycolorex.cols[15];
    private String Display = "Color1";
    public String na = ArmorNBI.armornamePrefix;
    public ItemArmor.ArmorMaterial rl;
    public String j = "jbra";
    public static final ItemArmor.ArmorMaterial ColorM;

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List<String> par3List, boolean par4) {
        if (this.func_82816_b_(par1ItemStack)) {
            par3List.add(JRMCoreH.trl("NBArmor", "Vanity") + ": " + JRMCoreH.trl("NBArmor", this.getColorReadable(par1ItemStack)));
        }
    }

    public ArmorNBI(int defcol, ItemArmor.ArmorMaterial par2ArmorMaterial, int slot, int id, String armornamePrefix) {
        super(par2ArmorMaterial, slot, id);
        this.rl = par2ArmorMaterial;
        this.func_77656_e(1000);
        this.field_77777_bU = 1;
        this.na = armornamePrefix;
        this.modid = "NBArmors";
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped nbMdl(int slt, EntityLivingBase e) {
        switch (slt) {
            case 0: {
                return this.wear(e) ? NBHJBRA.ArmorModelNNB1 : NBHJBRA.armorn1;
            }
            case 1: {
                return this.wear(e) ? NBHJBRA.ArmorModelNNB1 : NBHJBRA.armorn1;
            }
            case 3: {
                return this.wear(e) ? NBHJBRA.ArmorModelNNB1 : NBHJBRA.armorn1;
            }
        }
        return this.wear(e) ? NBHJBRA.ArmorModelNNB2 : NBHJBRA.armorn2;
    }

    public boolean wear(EntityLivingBase e) {
        return JRMCoreH.JBRA() && (e instanceof EntityPlayer || JRMCoreH.JFC() && JRMCoreHJFC.isChildNPC((Entity)e));
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, int slot) {
        ModelBiped armorModel = this.nbMdl(slot, entityLiving);
        if (this.wear(entityLiving)) {
            armorModel = NBHJBRA.showModel(armorModel, entityLiving, itemstack, slot);
        } else {
            ItemStack var11;
            armorModel.field_78116_c.field_78806_j = slot == 0 || slot == 1;
            armorModel.field_78115_e.field_78806_j = slot == 0;
            armorModel.field_78114_d.field_78806_j = false;
            armorModel.field_78115_e.field_78806_j = slot == 0 || slot == 1 || slot == 2;
            armorModel.field_78112_f.field_78806_j = slot == 0 || slot == 1;
            armorModel.field_78113_g.field_78806_j = slot == 0 || slot == 1;
            armorModel.field_78123_h.field_78806_j = slot == 2 || slot == 3;
            boolean bl = armorModel.field_78124_i.field_78806_j = slot == 2 || slot == 3;
            if (entityLiving instanceof EntityMob) {
                armorModel.field_78112_f.field_78806_j = false;
                armorModel.field_78113_g.field_78806_j = false;
            }
            int n = armorModel.field_78120_m = (var11 = entityLiving.func_70694_bm()) != null ? 1 : 0;
            if (var11 != null && entityLiving instanceof EntityPlayer && ((EntityPlayer)entityLiving).func_71052_bv() > 0) {
                EnumAction var12 = var11.func_77975_n();
                if (var12 == EnumAction.block) {
                    armorModel.field_78120_m = 3;
                } else if (var12 == EnumAction.bow) {
                    armorModel.field_78118_o = true;
                }
            }
            armorModel.field_78117_n = entityLiving.func_70093_af();
            armorModel.field_78093_q = entityLiving.func_70115_ae();
            armorModel.field_78091_s = entityLiving.func_70631_g_();
        }
        return armorModel;
    }

    public String getColorReadable(ItemStack par1) {
        int i = this.func_82814_b(par1);
        for (int j = 0; j < vanitycolorex.cols.length; ++j) {
            if (vanitycolorex.cols[j] != i) continue;
            return vanitycolorex.colNams[j];
        }
        return vanitycolorex.colNams[15];
    }

    public String getTextureFile() {
        return "NBArmors:";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("NBArmors:" + this.func_77658_a().replaceAll("item.", ""));
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack item, int var) {
        return this.func_82814_b(item);
    }

    public boolean func_82816_b_(ItemStack p_82816_1_) {
        return !p_82816_1_.func_77942_o() ? false : (!p_82816_1_.func_77978_p().func_150297_b(this.Display, 10) ? false : p_82816_1_.func_77978_p().func_74775_l(this.Display).func_150297_b("color", 3));
    }

    public int func_82814_b(ItemStack p_82814_1_) {
        NBTTagCompound nbttagcompound = p_82814_1_.func_77978_p();
        if (nbttagcompound == null) {
            return this.defcol;
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l(this.Display);
        return nbttagcompound1 == null ? 10511680 : (nbttagcompound1.func_150297_b("color", 3) ? nbttagcompound1.func_74762_e("color") : this.defcol);
    }

    public void func_82815_c(ItemStack p_82815_1_) {
        NBTTagCompound nbttagcompound1;
        NBTTagCompound nbttagcompound = p_82815_1_.func_77978_p();
        if (nbttagcompound != null && (nbttagcompound1 = nbttagcompound.func_74775_l(this.Display)).func_74764_b("color")) {
            nbttagcompound1.func_82580_o("color");
        }
    }

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

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String r = "";
        String j = "";
        j = "jbra";
        r = stack.toString().contains("leg") || stack.toString().contains("Leg") ? "NBArmors:textures/armor/" + this.na + "_2" + j + ".png" : (stack.toString().contains("boot") || stack.toString().contains("Boot") ? "NBArmors:textures/armor/" + this.na + "_3" + j + ".png" : (stack.toString().contains("head") || stack.toString().contains("Head") ? "NBArmors:textures/armor/" + this.na + "_0" + j + ".png" : "NBArmors:textures/armor/" + this.na + "_1" + j + ".png"));
        if (type == "overlay") {
            return r;
        }
        return r;
    }

    static {
        ColorM = EnumHelper.addArmorMaterial((String)"ColorM", (int)2000, (int[])new int[]{30, 40, 30, 20}, (int)30);
    }
}

