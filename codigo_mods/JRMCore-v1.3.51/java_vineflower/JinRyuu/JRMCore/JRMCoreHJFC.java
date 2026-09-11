package JinRyuu.JRMCore;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.FamilyC.FamilyCConfig;
import JinRyuu.FamilyC.mod_FamilyC;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCoreHJFC {
   public static int getConfigcpt() {
      return FamilyCConfig.cpt;
   }

   public static void setConfigpt(int value) {
      FamilyCConfig.pt = value;
   }

   public static void openGui(int id, EntityPlayer pl) {
      pl.openGui(mod_FamilyC.instance, id, pl.field_70170_p, (int)pl.field_70165_t, (int)pl.field_70163_u, (int)pl.field_70161_v);
   }

   public static boolean isChildNPC(Entity entity) {
      return entity instanceof EntityNPC;
   }

   public static String childDNS(Entity entity) {
      if (entity instanceof EntityNPC) {
         EntityNPC e = (EntityNPC)entity;
         return e.getDNS();
      } else {
         return "";
      }
   }

   public static String childDNSH(Entity entity) {
      if (entity instanceof EntityNPC) {
         EntityNPC e = (EntityNPC)entity;
         return e.getDNSH();
      } else {
         return "";
      }
   }

   public static void childDNSset(Entity entity, String w) {
      if (entity instanceof EntityNPC) {
         EntityNPC e = (EntityNPC)entity;
         e.setDNS(w);
         FamilyCH.jfcd(23, entity.func_145782_y() + ":dns:" + w);
      }
   }

   public static void childDNSHset(Entity entity, String w) {
      if (entity instanceof EntityNPC) {
         EntityNPC e = (EntityNPC)entity;
         e.setDNSH(w);
         FamilyCH.jfcd(23, entity.func_145782_y() + ":dnsH:" + w);
      }
   }

   public static void modelHelper(EntityLivingBase entityLiving, ModelBipedBody mdl) {
      if (entityLiving instanceof EntityNPC) {
         EntityNPC e = (EntityNPC)entityLiving;
         String dns = e.getDNS();
         mdl.b = JRMCoreH.dnsBreast(dns);
         if (dns.length() > 5) {
            ModelBipedBody.g = JRMCoreH.dnsGender(dns) + 1;
         }

         ModelBipedBody.f = e.getNPCgrw();
         mdl.b = JRMCoreH.dnsBreast(dns);
      }
   }
}
