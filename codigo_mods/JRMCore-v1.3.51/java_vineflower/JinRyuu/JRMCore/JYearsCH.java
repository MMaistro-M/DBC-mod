package JinRyuu.JRMCore;

import net.minecraft.server.MinecraftServer;

public class JYearsCH {
   public static String[] dayNames = new String[]{"Wolfsday", "Blazeday", "Witherday", "Ghastday", "Enderday"};
   public static String[] monthNames = new String[]{"Redstonary", "Gol'dar", "Diamondar", "Emeraldary"};
   public static String[] monthInDays = new String[]{"12", "13", "9", "12"};
   public static int[] mID = new int[]{12, 13, 9, 12};
   public static int y = 0;
   public static int m = 0;
   public static int d = 0;
   public static double py = 0.0;
   static String cd = "JYearsC_d.jyc";
   public static String[] p = null;

   public static void wcd(MinecraftServer server, String d, String id, boolean b) {
      String fd = "/data";
      JRMCoreH.wd(server, d, id, fd, cd, b);
   }

   public static String rcd(MinecraftServer server, String id) {
      String fd = "/data";
      return JRMCoreH.rd(server, id, fd, cd);
   }
}
