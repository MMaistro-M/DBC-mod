package JinRyuu.JRMCore;

import JinRyuu.JYearsC.JYearsCConfig;
import JinRyuu.JYearsC.JYearsCItems;
import JinRyuu.JYearsC.mod_JYearsC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class JRMCoreHJYC {
   public static void openGui(int id, EntityPlayer pl) {
      pl.openGui(mod_JYearsC.instance, id, pl.field_70170_p, (int)pl.field_70165_t, (int)pl.field_70163_u, (int)pl.field_70161_v);
   }

   public static int JYCgetConfigcpgut() {
      return JYearsCConfig.cpgut;
   }

   public static float JYCAge(EntityPlayer plyr) {
      float yc = 100.0F;
      if (JYearsCH.p != null && JYearsCH.p.length > 0) {
         int id = 0;

         for (String n : JYearsCH.p) {
            String[] m = n.split(";");
            if (plyr.func_70005_c_().equals(m[0])) {
               yc = Float.parseFloat(m[1]);
            }

            id++;
         }
      }

      return yc;
   }

   public static float JYCsizeBasedOnAge(EntityPlayer plyr) {
      float yc = 1.0F;
      if (JYearsCH.p != null && JYearsCH.p.length > 0) {
         for (String n : JYearsCH.p) {
            String[] m = n.split(";");
            if (plyr.func_70005_c_().equals(m[0])) {
               float A = Float.parseFloat(m[1]);
               float gu = JYearsCConfig.pgut;
               if (A <= 5.0F) {
                  yc = 0.5F;
               }

               if (A > 5.0F && A <= gu) {
                  yc = 0.5F + (A - 5.0F) / (gu - 5.0F) * 0.5F;
               }

               if (A > gu) {
                  yc = 1.0F;
               }

               yc = yc < 0.5531915F ? 0.5531915F : yc;
            }
         }
      }

      String[] state = JRMCoreH.data(plyr.func_70005_c_(), 2, "0;0").split(";");
      String[] s = JRMCoreH.data(plyr.func_70005_c_(), 1, "0;0;0;0;0;0").split(";");
      int pwr = Integer.parseInt(s[2]);
      int race = Integer.parseInt(s[0]);
      int State = pwr != 2 && race != 0 ? Integer.parseInt(state[0]) : 0;
      boolean saiOozar = JRMCoreH.rSai(race) ? State == 7 || State == 8 : false;
      boolean ssj4 = JRMCoreH.rSai(race) ? State == 14 : false;
      return ssj4 ? 1.0F : (saiOozar ? 1.0F : yc);
   }

   public static void JYCsetConfigpgut(int pgut) {
      JYearsCConfig.pgut = pgut;
   }

   public static Item JYCgetItemWatch() {
      return JYearsCItems.ItemWatch;
   }

   public static int JYCgetConfigpls() {
      return JYearsCConfig.pls;
   }
}
