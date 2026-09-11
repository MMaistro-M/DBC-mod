package JinRyuu.JRMCore;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;

public class JRMCoreH2 {
   public static ChatStyle styl_ylw = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
   public static ChatStyle styl_wht = new ChatStyle().func_150238_a(EnumChatFormatting.WHITE);
   public static ChatStyle styl_gld = new ChatStyle().func_150238_a(EnumChatFormatting.GOLD);
   public static ChatStyle styl_red = new ChatStyle().func_150238_a(EnumChatFormatting.RED);
   public static final String[] colNams = new String[]{
      "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white"
   };
   public static final int[] cols = new int[]{
      1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320
   };
   public static final int col_black = 0;
   public static final int col_red = 1;
   public static final int col_green = 2;
   public static final int col_brown = 3;
   public static final int col_blue = 4;
   public static final int col_purple = 5;
   public static final int col_cyan = 6;
   public static final int col_silver = 7;
   public static final int col_gray = 8;
   public static final int col_pink = 9;
   public static final int col_lime = 10;
   public static final int col_yellow = 11;
   public static final int col_lightBlue = 12;
   public static final int col_magenta = 13;
   public static final int col_orange = 14;
   public static final int col_white = 15;
   public static String[] jnam0 = new String[]{"a", "i", "u", "e", "o"};
   public static String[] jnam1 = new String[]{
      "ka",
      "ki",
      "ki",
      "ku",
      "ku",
      "ke",
      "ke",
      "ko",
      "ko",
      "sa",
      "sa",
      "sa",
      "shi",
      "shi",
      "shi",
      "su",
      "su",
      "se",
      "so",
      "ta",
      "ta",
      "chi",
      "chi",
      "tsu",
      "te",
      "to",
      "na",
      "ni",
      "ni",
      "nu",
      "nu",
      "ne",
      "no",
      "no",
      "ha",
      "hi",
      "fu",
      "fu",
      "he",
      "ho",
      "ma",
      "ma",
      "ma",
      "mi",
      "mi",
      "mi",
      "mu",
      "mu",
      "mu",
      "mu",
      "me",
      "mo",
      "mo",
      "mo",
      "ya",
      "yu",
      "yu",
      "yu",
      "yo",
      "ra",
      "ra",
      "ra",
      "ri",
      "ru",
      "ru",
      "ru",
      "re",
      "ro",
      "ro",
      "ro",
      "wa",
      "wa",
      "wa",
      "wa",
      "wo",
      "wo"
   };
   public static String[] jnam2 = new String[]{"n"};
   public static String[] nam1 = new String[]{
      "Kami",
      "Kame",
      "Ko",
      "Leto",
      "Le",
      "La",
      "Lao",
      "Mu",
      "Mute",
      "Na",
      "Nap",
      "Ni",
      "Omeni",
      "Ome",
      "Oo",
      "Pic",
      "Pik",
      "Pi",
      "Pui",
      "Poi",
      "Po",
      "Pu",
      "Pa",
      "Pud",
      "Pu",
      "Para",
      "Puru",
      "Pora",
      "Poru",
      "Rai",
      "Rei",
      "Re",
      "Reno",
      "Ra",
      "Roshi",
      "Ro",
      "Sei",
      "Sa",
      "She",
      "Tru",
      "Turu",
      "Tu",
      "Tia",
      "Ti",
      "Tur",
      "Ve",
      "Vel",
      "Vege",
      "Veji",
      "Vi",
      "Vide",
      "Ya",
      "Yam",
      "Yamu",
      "Yako",
      "Zarbo",
      "Za",
      "Ze",
      "Bi",
      "Ba",
      "Be",
      "Buu",
      "Baba",
      "Bibi",
      "Beji",
      "Bu",
      "Bul",
      "Buru",
      "Ba",
      "Bardo",
      "Bro",
      "Broli",
      "Chi",
      "Chao",
      "Choa",
      "Ce",
      "Dodo",
      "Do",
      "Duru",
      "Dabu",
      "Da",
      "Du",
      "Furi",
      "Frei",
      "Free",
      "Frie",
      "Fu",
      "Fuze",
      "Gero",
      "Ge",
      "Giyu",
      "Gin",
      "Gi",
      "Gig",
      "Gaji",
      "Ga",
      "Gul",
      "Gu",
      "Ger",
      "Go",
      "Jei",
      "Jie",
      "Ji",
      "Je",
      "Jin",
      "Kai",
      "Ki",
      "Ka",
      "Kril",
      "Kri",
      "Kuru",
      "Kuri",
      "Ku",
      "Kururi",
      "Ka"
   };
   public static String[] nam2 = new String[]{
      "nku",
      "nkuu",
      "tai",
      "kai",
      "ken",
      "aio",
      "san",
      "drick",
      "nix",
      "golo",
      "gollo",
      "gelo",
      "galo",
      "gello",
      "ucha",
      "'ken",
      "'kan",
      "sen",
      "zar",
      "bon",
      "don",
      "kou",
      "han",
      "sin",
      "-Chi",
      "nate",
      "rne",
      "jitsu",
      "jiit",
      "jit",
      "u",
      "ut",
      "be",
      "long",
      "razu",
      "tle",
      "rin",
      "rrin",
      "tenks",
      "tunks",
      "zedd",
      "edd",
      "er",
      "bidi",
      "bi",
      "di",
      "dd",
      "d",
      "badi",
      "ba",
      "ta",
      "ter",
      "rter",
      "ma",
      "po",
      "bo",
      "ck",
      "tsu",
      "tzu",
      "ll",
      "ria",
      "ra",
      "ri",
      "za",
      "zer",
      "yu",
      "do",
      "o",
      "ku",
      "kuu",
      "ce",
      "ke",
      "lin",
      "rin",
      "me",
      "sennin",
      "shinhan",
      "ten",
      "en",
      "mat",
      "t",
      "tien",
      "pa",
      "colo",
      "kon",
      "kolo",
      "con",
      "-pui",
      "-poi",
      "ditz",
      "dditz",
      "dittz",
      "shi",
      "shu",
      "sho",
      "sha",
      "she",
      "les",
      "nks",
      "geta",
      "jita",
      "gita",
      "jeta",
      "tto",
      "to",
      "cha",
      "chi",
      "n"
   };

   public static void chtmsg_ylw(EntityPlayer entityplayermp, String t) {
      entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(styl_ylw));
   }

   public static Entity getTarget(float par1, double distance) {
      double d0 = distance;
      MovingObjectPosition omo = JRMCoreClient.mc.field_71451_h.func_70614_a(d0, par1);
      double d1 = d0;
      Vec3 vec3 = JRMCoreClient.mc.field_71451_h.func_70666_h(par1);
      Vec3 vec31 = JRMCoreClient.mc.field_71451_h.func_70676_i(par1);
      Vec3 vec32 = vec3.func_72441_c(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0);
      Entity pointedEntity = null;
      Vec3 vec33 = null;
      float f1 = 1.0F;
      List list = JRMCoreClient.mc
         .field_71441_e
         .func_72839_b(
            JRMCoreClient.mc.field_71451_h,
            JRMCoreClient.mc
               .field_71451_h
               .field_70121_D
               .func_72321_a(vec31.field_72450_a * d0, vec31.field_72448_b * d0, vec31.field_72449_c * d0)
               .func_72314_b(f1, f1, f1)
         );
      double d2 = d1;

      for (int i = 0; i < list.size(); i++) {
         Entity entity = (Entity)list.get(i);
         if (entity.func_70067_L()) {
            float f2 = entity.func_70111_Y();
            AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(f2, f2, f2);
            MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3, vec32);
            if (axisalignedbb.func_72318_a(vec3)) {
               if (0.0 < d2 || d2 == 0.0) {
                  pointedEntity = entity;
                  vec33 = movingobjectposition == null ? vec3 : movingobjectposition.field_72307_f;
                  d2 = 0.0;
               }
            } else if (movingobjectposition != null) {
               double d3 = vec3.func_72438_d(movingobjectposition.field_72307_f);
               if (d3 < d2 || d2 == 0.0) {
                  if (entity != JRMCoreClient.mc.field_71451_h.field_70154_o || entity.canRiderInteract()) {
                     pointedEntity = entity;
                     vec33 = movingobjectposition.field_72307_f;
                     d2 = d3;
                  } else if (d2 == 0.0) {
                     pointedEntity = entity;
                     vec33 = movingobjectposition.field_72307_f;
                  }
               }
            }
         }
      }

      if (pointedEntity != null && (d2 < d1 || omo == null)) {
         omo = new MovingObjectPosition(pointedEntity, vec33);
         if (pointedEntity instanceof Entity || pointedEntity instanceof EntityItemFrame) {
            JRMCoreClient.mc.field_147125_j = pointedEntity;
         }
      }

      return omo != null && omo.field_72313_a == MovingObjectType.ENTITY && omo.field_72308_g instanceof Entity ? omo.field_72308_g : null;
   }

   public static String kiAttNamGen() {
      Random r1 = new Random();
      int i0 = r1.nextInt(1);
      String s = "";
      if (i0 == 1) {
         r1 = new Random();
         int i1 = r1.nextInt(nam1.length - 1);
         r1 = new Random();
         int i2 = r1.nextInt(nam2.length - 1);
         s = nam1[i1] + nam2[i2];
      } else {
         r1 = new Random();
         int i1 = r1.nextInt(10);
         String s1 = i1 < jnam0.length ? jnam0[i1] : "";
         r1 = new Random();
         i1 = r1.nextInt(3);
         String s2 = "";

         for (int i = 0; i < i1 + 1; i++) {
            r1 = new Random();
            int i2 = r1.nextInt(jnam1.length);
            s2 = s2 + jnam1[i2];
         }

         r1 = new Random();
         i1 = r1.nextInt(4);
         String s3 = i1 < jnam2.length ? jnam0[i1] : "";
         String n = s1 + s2 + s3;
         s = n.substring(0, 1).toUpperCase() + n.substring(1);
      }

      return s != null ? s : "Null";
   }
}
