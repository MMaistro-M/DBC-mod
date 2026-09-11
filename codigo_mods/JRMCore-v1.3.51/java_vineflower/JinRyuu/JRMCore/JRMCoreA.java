package JinRyuu.JRMCore;

import java.util.HashMap;
import java.util.Map;

public class JRMCoreA {
   public static Map<Integer, Object> actions = new HashMap<>();
   public static Map<Integer, Object> actionsDBC = new HashMap<>();
   public static Map<Integer, Object> actionsNC = new HashMap<>();

   public static void actionInit() {
      if (JRMCoreH.DBC()) {
         JRMCoreHDBC.actionInitDBC();
      }

      if (JRMCoreH.NC()) {
         JRMCoreHNC.actionInit();
      }
   }

   public static boolean pwr_usrs(int p) {
      return p == 1 || p == 2;
   }
}
