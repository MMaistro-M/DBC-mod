package JinRyuu.JRMCore;

import JinRyuu.FamilyC.FamilyCConfig;
import JinRyuu.JBRA.DBC_GiTurtleMdl;
import JinRyuu.JBRA.GiTurtleMdl;
import JinRyuu.JBRA.JRMC_GiTurtleMdl;
import JinRyuu.JBRA.NC_GiTurtleMdl;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import JinRyuu.JRMCore.entity.ModelKB;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.m.mEnergy5;
import JinRyuu.JYearsC.JYearsCConfig;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class JRMCoreHJBRA {
   public static final ModelBiped GiTurtleMdl1 = new GiTurtleMdl(0.201F);
   public static final ModelBiped GiTurtleMdl2 = new GiTurtleMdl(0.11F);
   public static final ModelBiped GiTurtleMdl3 = new GiTurtleMdl(0.056F);
   public static final ModelBiped GiTurtleMdl4 = new ModelBipedBody(0.198F);
   public static final ModelBiped GiTurtleMdl5 = new ModelBipedBody(0.5F);
   public static final ModelBiped GiTurtleMdl3_1 = new GiTurtleMdl(0.056F);
   public static final ModelBiped[] JRMC_GiTurtleMdl2 = new ModelBiped[]{
      new JRMC_GiTurtleMdl(0), new JRMC_GiTurtleMdl(1), new JRMC_GiTurtleMdl(2), new JRMC_GiTurtleMdl(3), new JRMC_GiTurtleMdl(4)
   };
   public static final ModelBiped[] DBC_GiTurtleMdl2 = new ModelBiped[]{
      new DBC_GiTurtleMdl(0), new DBC_GiTurtleMdl(1), new DBC_GiTurtleMdl(2), new DBC_GiTurtleMdl(3), new DBC_GiTurtleMdl(4)
   };
   public static final ModelBiped[] NC_GiTurtleMdl2 = new ModelBiped[]{new NC_GiTurtleMdl(0), new NC_GiTurtleMdl(1)};
   public static final mEnergy5 model1 = new mEnergy5();
   public static final ModelKB model2 = new ModelKB();

   public static final ModelBiped ModelBipedBody(float s) {
      return new ModelBipedBody(s);
   }

   private static int JFCgetConfigpt() {
      return FamilyCConfig.pt;
   }

   private static float JYCgetConfigpgut() {
      return JYearsCConfig.pgut;
   }

   public static ModelBiped showModel(ModelBiped m, EntityLivingBase entityLiving, ItemStack is, int par2) {
      ModelBipedBody mdl = (ModelBipedBody)m;
      modelHelper(entityLiving, mdl);
      if (JRMCoreH.JFC()) {
         JRMCoreHJFC.modelHelper(entityLiving, mdl);
      }

      mdl.field_78116_c.field_78806_j = par2 == 0 || par2 == 5;
      if (par2 == 5) {
         par2 = 1;
      }

      if (ModelBipedBody.g >= 2) {
         mdl.body.field_78806_j = par2 == 1 || par2 == 2 || par2 == 4;
         mdl.hip.field_78806_j = par2 == 1 || par2 == 2 || par2 == 4;
         mdl.hip2.field_78806_j = par2 == 1 || par2 == 2 || par2 == 4;
         mdl.waist.field_78806_j = par2 == 1 || par2 == 2 || par2 == 4;
         mdl.bottom.field_78806_j = par2 == 1 || par2 == 2 || par2 == 4;
         mdl.bottom2.field_78806_j = par2 == 1 || par2 == 2 || par2 == 4;
         mdl.Bbreast.field_78806_j = par2 == 1 || par2 == 2 || par2 == 4;
         mdl.Bbreast2.field_78806_j = par2 == 1 || par2 == 2 || par2 == 4;
         mdl.Brightarm.field_78806_j = par2 == 1 || par2 == 4;
         mdl.Bleftarm.field_78806_j = par2 == 1 || par2 == 4;
         mdl.rightleg.field_78806_j = par2 == 2 || par2 == 3 || par2 == 4;
         mdl.leftleg.field_78806_j = par2 == 2 || par2 == 3 || par2 == 4;
      } else {
         mdl.field_78115_e.field_78806_j = par2 == 1 || par2 == 2 || par2 == 4;
         mdl.field_78112_f.field_78806_j = par2 == 1 || par2 == 4;
         mdl.field_78113_g.field_78806_j = par2 == 1 || par2 == 4;
         mdl.field_78123_h.field_78806_j = par2 == 2 || par2 == 3 || par2 == 4;
         mdl.field_78124_i.field_78806_j = par2 == 2 || par2 == 3 || par2 == 4;
      }

      mdl.field_78117_n = entityLiving.func_70093_af();
      mdl.field_78095_p = 0.0F;
      mdl.field_78093_q = entityLiving.func_70115_ae();
      mdl.field_78091_s = entityLiving.func_70631_g_();
      mdl.field_78117_n = entityLiving.func_70093_af();
      return mdl;
   }

   private static void modelHelper(EntityLivingBase entityLiving, ModelBipedBody mdl) {
      if (entityLiving instanceof EntityPlayer) {
         EntityPlayer ply = (EntityPlayer)entityLiving;
         float childScl = 1.0F;
         float age = 0.0F;
         int gen = 1;
         int preg = 0;
         int breast = 0;
         if (JRMCoreH.JYC()) {
            age = JRMCoreHJYC.JYCAge(ply);
            childScl = JRMCoreHJYC.JYCsizeBasedOnAge(ply);
            childScl = 3.0F - childScl * 2.0F;
         }

         if (JRMCoreH.JFC()) {
            if (JRMCoreH.dnn(1) && JRMCoreH.dnn(2)) {
               String[] s = JRMCoreH.data(ply.func_70005_c_(), 1, "0;0;0;0;0;0").split(";");
               String dns = s[1];
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

               int pwr = Integer.parseInt(s[2]);
               if (pwr == 1 && A > 1) {
                  int race = Integer.parseInt(s[0]);
                  String[] dummy = new String[]{"0", "0", "0"};
                  String[] state = JRMCoreH.data(ply.func_70005_c_(), 2, "0;0;0").split(";");
                  int State = pwr != 2 && race != 0 ? Integer.parseInt(state[0]) : 0;
                  boolean saiOozar = JRMCoreH.rSai(race) ? State == 7 || State == 8 : false;
                  if (saiOozar) {
                     gen = 1;
                  }
               }
            }

            if (JRMCoreH.dnn(30) && !ply.func_82150_aj() && JRMCoreH.dnn(30)) {
               String s = JRMCoreH.data(ply.func_70005_c_(), 30, "0");
               int i = s.matches("[0-9]+") ? Integer.parseInt(s) : 0;
               int def = JFCgetConfigpt() * 120;
               if (i > 1) {
                  preg = (int)((i - def / 2.0F) / (def / 2.0F * 0.01F));
                  preg = 100 - preg;
                  preg = preg > 100 ? 100 : (preg < 0 ? 0 : preg);
               } else {
                  preg = 0;
               }
            }

            if (JRMCoreH.dnn(1) && JRMCoreH.dnn(2)) {
               String[] s = JRMCoreH.data(ply.func_70005_c_(), 1, "0;0;0;0;0;0").split(";");
               String dns = s[1];
               breast = JRMCoreH.dnsBreast(dns);
            }
         }

         ModelBipedBody.g = gen;
         ModelBipedBody.f = childScl;
         ModelBipedBody.p = preg;
         mdl.b = breast;
         ExtendedPlayer props = ExtendedPlayer.get(ply);
         boolean block = props.getBlocking() == 1;
         boolean instantTransmissionOn = props.getBlocking() == 2;
         int kishoot = props.getAnimKiShoot();
         mdl.blk = block;
         mdl.instantTransmission = instantTransmissionOn;
         mdl.KiAttack = kishoot;
         ItemStack var11 = ply.field_71071_by.func_70448_g();
         mdl.field_78120_m = var11 != null ? JRMCoreHSAC.ah(var11.func_77973_b(), 1) : 0;
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
