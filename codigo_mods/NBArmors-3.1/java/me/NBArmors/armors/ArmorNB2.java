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
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package me.NBArmors.armors;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJFC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ArmorNB2
extends ItemArmor {
    private static String armornamePrefix;
    public String modid;
    private int defcol = vanitycolorex.cols[15];
    private String Display = "Color1";
    public String na = ArmorNB2.armornamePrefix;
    public ItemArmor.ArmorMaterial rl;
    private ResourceLocation icon = null;

    public ArmorNB2(ItemArmor.ArmorMaterial par2ArmorMaterial, int slot, int par4, String armornamePrefix) {
        super(par2ArmorMaterial, slot, par4);
        this.rl = par2ArmorMaterial;
        this.func_77656_e(par2ArmorMaterial.func_78046_a(par4));
        this.field_77777_bU = 1;
        this.na = armornamePrefix;
        this.modid = "NBArmors";
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped nbMdl(int slt, EntityLivingBase e) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        switch (slt) {
            case 0: {
                return this.wear(e) ? NBHJBRA.ArmorModelNBB1 : NBHJBRA.armornn1;
            }
            case 1: {
                return this.wear(e) ? NBHJBRA.ArmorModelNBB1 : NBHJBRA.armornn1;
            }
            case 3: {
                return this.wear(e) ? NBHJBRA.ArmorModelNBB1 : NBHJBRA.armornn1;
            }
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        return this.wear(e) ? NBHJBRA.ArmorModelNBB2 : NBHJBRA.armornn2;
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
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            armorModel.field_78117_n = entityLiving.func_70093_af();
            armorModel.field_78093_q = entityLiving.func_70115_ae();
            armorModel.field_78091_s = entityLiving.func_70631_g_();
        }
        return armorModel;
    }

    public String getTextureFile() {
        return "NBArmors:";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("NBArmors:" + this.func_77658_a().replaceAll("item.", ""));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        String r = "";
        String j = "";
        j = "jbra";
        r = stack.toString().contains("leg") || stack.toString().contains("Leg") ? "NBArmors:textures/armor/" + this.na + "_2" + j + ".png" : (stack.toString().contains("boot") || stack.toString().contains("Boot") ? "NBArmors:textures/armor/" + this.na + "_3" + j + ".png" : (stack.toString().contains("head") || stack.toString().contains("Head") ? "NBArmors:textures/armor/" + this.na + "_0" + j + ".png" : "NBArmors:textures/armor/" + this.na + "_1" + j + ".png"));
        return r;
    }
}

