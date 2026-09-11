/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 */
package me.NBArmors.main;

import JinRyuu.JBRA.ModelBipedDBC;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJYC;
import JinRyuu.JRMCore.JRMCoreHSAC;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.NBArmors.models.ArmorModelNB;
import me.NBArmors.models.ArmorModelNB2;
import me.NBArmors.models.trenchNB;
import me.NBArmors.models.trenchcoat;
import me.NBArmors.models.vanityextra;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class NBHJBRA {
    public static Minecraft mc = Minecraft.func_71410_x();
    public ModelBipedDBC modelMain;
    @SideOnly(value=Side.CLIENT)
    public static final ModelBiped armorn1 = new ArmorModelNB2(0.11f);
    public static final ModelBiped armorn2 = new ArmorModelNB2(0.1f);
    public static final ModelBiped armornn1 = new ArmorModelNB2(0.2f);
    public static final ModelBiped armornn2 = new ArmorModelNB2(0.11f);
    public static final ModelBiped ArmorModelNNB1 = new ArmorModelNB(0.11f);
    public static final ModelBiped ArmorModelNNB2 = new ArmorModelNB(0.1f);
    public static final ModelBiped ArmorModelNNB3 = new ArmorModelNB(0.056f);
    public static final ModelBiped VanityModelNNB1 = new ArmorModelNB(0.1f);
    public static final ModelBiped VanityModelNNB2 = new ArmorModelNB(0.056f);
    public static final ModelBiped VanityModelNNB3 = new ArmorModelNB(0.046f);
    public static final ModelBiped ArmorModelNBB1 = new ArmorModelNB(0.13f);
    public static final ModelBiped ArmorModelNBB2 = new ArmorModelNB(0.11f);
    public static final ModelBiped ArmorModelNBB3 = new ArmorModelNB(0.156f);
    public static final ModelBiped MarksModel1 = new ArmorModelNB(0.13f);
    public static final ModelBiped MarksModel2 = new ArmorModelNB(0.1f);
    public static final ModelBiped MarksModel3 = new ArmorModelNB(0.256f);
    public static final ModelBiped TrenchCoatNB1 = new ArmorModelNB(0.11f);
    public static final ModelBiped TrenchCoatNB2 = new ArmorModelNB(0.1f);
    public static final ModelBiped TrenchCoatNB3 = new ArmorModelNB(0.056f);
    public static final ModelBiped[] vanityextra2 = new ModelBiped[]{new vanityextra(0), new vanityextra(1), new vanityextra(2), new vanityextra(3), new vanityextra(4), new vanityextra(5), new vanityextra(6), new vanityextra(7), new vanityextra(8), new vanityextra(9), new vanityextra(10), new vanityextra(11), new vanityextra(12), new vanityextra(13)};
    public static final ModelBiped[] trenchcoat2 = new ModelBiped[]{new trenchcoat(0)};
    public static final ModelBiped TrenchCoatNBB1 = new trenchNB(0.11f);
    public static final ModelBiped TrenchCoatNBB2 = new trenchNB(0.1f);
    public static final ModelBiped TrenchCoatNBB3 = new trenchNB(0.056f);
    public static final ModelBiped TrenchCNBB1 = new trenchNB(0.13f);
    public static final ModelBiped TrenchCNBB2 = new trenchNB(0.11f);
    public static final ModelBiped TrenchCNBB3 = new trenchNB(0.156f);

    public static final ModelBiped ModelBipedNB(float s) {
        return new ModelBipedBody(s);
    }

    @SideOnly(value=Side.CLIENT)
    public static ModelBipedBody showModel(ModelBiped m, EntityLivingBase entityLiving, ItemStack is, int par2) {
        ModelBipedBody mdl = (ModelBipedBody)m;
        NBHJBRA.modelHelper(entityLiving, mdl);
        if (JRMCoreH.JFC()) {
            NBHJBRA.modelHelper(entityLiving, mdl);
        }
        boolean bl = mdl.field_78116_c.field_78806_j = par2 == 0 || par2 == 1 || par2 == 4 || par2 == 5;
        if (par2 == 5) {
            par2 = 1;
        }
        if (ModelBipedBody.g >= 2) {
            mdl.body.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.hip.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.hip2.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.waist.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.bottom.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.bottom2.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.Bbreast.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.Bbreast2.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.Brightarm.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.Bleftarm.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.rightleg.field_78806_j = par2 == 0 || par2 == 2 || par2 == 3 || par2 == 4;
            mdl.leftleg.field_78806_j = par2 == 0 || par2 == 2 || par2 == 3 || par2 == 4;
        } else {
            mdl.field_78115_e.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.field_78112_f.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.field_78113_g.field_78806_j = par2 == 0 || par2 == 1 || par2 == 2 || par2 == 4;
            mdl.field_78123_h.field_78806_j = par2 == 2 || par2 == 3 || par2 == 4;
            mdl.field_78124_i.field_78806_j = par2 == 2 || par2 == 3 || par2 == 4;
        }
        mdl.field_78117_n = entityLiving.func_70093_af();
        mdl.field_78095_p = 0.0f;
        mdl.field_78093_q = entityLiving.func_70115_ae();
        mdl.field_78091_s = entityLiving.func_70631_g_();
        return mdl;
    }

    @SideOnly(value=Side.CLIENT)
    private static void modelHelper(EntityLivingBase entityLiving, ModelBipedBody mdl) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer ply = (EntityPlayer)entityLiving;
            float childScl = 1.0f;
            float age = 0.0f;
            int gen = 1;
            int preg = 0;
            int breast = 0;
            if (JRMCoreH.JYC()) {
                age = JRMCoreHJYC.JYCAge(ply);
                childScl = JRMCoreHJYC.JYCsizeBasedOnAge(ply);
                childScl = 3.0f - childScl * 2.0f;
            }
            if (JRMCoreH.JFC()) {
                String dns;
                String[] s;
                if (JRMCoreH.dnn(1) && JRMCoreH.dnn(2)) {
                    int pwr;
                    s = JRMCoreH.data(ply.func_70005_c_(), 1, "0;0;0;0;0;0").split(";");
                    dns = s[1];
                    int A = JRMCoreH.dnsGender(dns) + 1;
                    if (A >= 1) {
                        gen = 1;
                    }
                    if (A == 2) {
                        gen = 2;
                    }
                    if (A == 3) {
                        gen = 3;
                    }
                    if ((pwr = Integer.parseInt(s[2])) == 1 && A > 1) {
                        boolean saiOozar;
                        int State;
                        int race = Integer.parseInt(s[0]);
                        String[] dummy = new String[]{"0", "0", "0"};
                        String[] state = JRMCoreH.data(ply.func_70005_c_(), 2, "0;0;0").split(";");
                        int n = State = pwr == 2 || race == 0 ? 0 : Integer.parseInt(state[0]);
                        boolean bl = JRMCoreH.rSai(race) ? State == 7 || State == 8 : (saiOozar = false);
                        if (saiOozar) {
                            gen = 1;
                        }
                    }
                }
                if (JRMCoreH.dnn(1) && JRMCoreH.dnn(2)) {
                    s = JRMCoreH.data(ply.func_70005_c_(), 1, "0;0;0;0;0;0").split(";");
                    dns = s[1];
                    breast = JRMCoreH.dnsBreast(dns);
                }
            }
            ModelBipedBody.g = gen;
            ModelBipedBody.f = childScl;
            ModelBipedBody.p = preg;
            mdl.b = breast;
            ExtendedPlayer props = ExtendedPlayer.get(ply);
            boolean block = props.getBlocking() != 0;
            int kishoot = props.getAnimKiShoot();
            mdl.blk = block;
            mdl.KiAttack = kishoot;
            ItemStack var11 = ply.field_71071_by.func_70448_g();
            int n = mdl.field_78120_m = var11 != null ? JRMCoreHSAC.ah(var11.func_77973_b(), 1) : 0;
            if (var11 != null && ply.func_71052_bv() > 0) {
                EnumAction var12 = var11.func_77975_n();
                if (var12 == EnumAction.block) {
                    mdl.field_78120_m = 3;
                } else if (var12 == EnumAction.bow) {
                    mdl.field_78118_o = true;
                }
            }
        }
    }
}

