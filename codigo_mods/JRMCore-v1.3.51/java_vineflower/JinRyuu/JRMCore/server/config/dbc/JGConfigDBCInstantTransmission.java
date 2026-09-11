package JinRyuu.JRMCore.server.config.dbc;

import JinRyuu.JRMCore.server.config.JGConfigBase;
import java.util.HashMap;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JGConfigDBCInstantTransmission extends JGConfigBase {
   public static final String CATEGORY_SERVERSIDED_MAIN = "main";
   public static final String CATEGORY_SERVERSIDED_SHORT = "short distance mode only";
   public static final String CATEGORY_SERVERSIDED_LONG = "long distance mode only";
   public static final String CATEGORY_SERVERSIDED_COMMON = "both distance modes";
   public static final String[] TP_MODES = new String[]{"Short Distance Teleportation", "Long Distance Teleportation"};
   public static final String[] SHORT_MODES = new String[]{"To Target", "In-front Target", "Behind Target"};
   public static final String[] SURROUND_MODES = new String[]{"Alone", "Group Members", "All Players"};
   public static final int MAX_LEVELS = 10;
   public static final int SHORT_TP = 0;
   public static final int LONG_TP = 1;
   public static final int FLAT_COST = 0;
   public static final int PERCENTAGE_COST = 1;
   public static boolean CONFIG_INSTANT_TRANSMISSION_NOTIFY_SERVER_ENABLED;
   public static final int MODES = 2;
   public static boolean[] CCONFIG_INSTANT_TRANSMISSION_ENABLED = new boolean[2];
   public static boolean[] CONFIG_INSTANT_TRANSMISSION_ENABLED = new boolean[2];
   public static byte[] CONFIG_INSTANT_TRANSMISSION_UNLOCK_SKILL_LEVEL = new byte[2];
   public static float[][][] CONFIG_INSTANT_TRANSMISSION_COST = new float[2][2][10];
   public static byte[] CONFIG_INSTANT_TRANSMISSION_KI_SENSE_REQUIREMENT = new byte[2];
   static final int MODES_SHORT = 3;
   public static boolean[] CONFIG_INSTANT_TRANSMISSION_ENABLED_SHORT = new boolean[3];
   public static boolean CONFIG_INSTANT_TRANSMISSION_SHORT_GO_THROUGH_BLOCKS_ENABLED;
   public static double CONFIG_INSTANT_TRANSMISSION_SHORT_TARGET_FINDER_RANGE;
   static final int MODES_SURROUND = 3;
   public static boolean[] CONFIG_INSTANT_TRANSMISSION_ENABLED_SURROUND = new boolean[3];
   public static int[][] CONFIG_INSTANT_TRANSMISSION_SURROUND_PLAYER_LIMIT_SKILL_LEVEL = new int[2][10];
   public static double CONFIG_INSTANT_TRANSMISSION_SURROUND_TARGET_FINDER_RANGE;
   public static float[][] CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER = new float[2][10];
   public static boolean CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_TP_ENABLED;
   public static float[][] CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST = new float[2][10];
   public static HashMap<Integer, Boolean> CONFIG_INSTANT_TRANSMISSION_DIMENSION_BLACKLIST = new HashMap<>();
   public static int[] CONFIG_INSTANT_TRANSMISSION_SHORT_MAX_RANGE = new int[10];
   public static int[][] CONFIG_INSTANT_TRANSMISSION_COOLDOWN = new int[2][10];
   public static boolean[] CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_RELEASE_SENSE_REQUIRED_ENABLED = new boolean[2];

   public static void init(Configuration config) {
      config.load();
      init_instantTransmission(config);
      config.save();
   }

   private static void init_instantTransmission(Configuration config) {
      String name = "";
      String nameShort = "Short Distance";
      String nameLong = "Long Distance";
      String CATEGORY = "main";
      String percentage = " (Percentage)";
      String warning = " Change only to your own responsibility! Having too high multiplier will cause glitches!";
      String server = "Server Sided!";
      String[] titles = TP_MODES;

      for (int i = 0; i < 2; i++) {
         String modeName = i == 0 ? "Short Distance" : "Long Distance";
         boolean defaultB = true;
         String title = "Teleport Mode: '" + modeName + "' Enabled";
         String description = "(true = Enabled, false = Disabled) (Default: " + defaultB + ")";
         Property property = config.get(CATEGORY, "" + title, defaultB);
         property.comment = "Server Sided! " + description;
         CCONFIG_INSTANT_TRANSMISSION_ENABLED[i] = property.getBoolean();
         CONFIG_INSTANT_TRANSMISSION_ENABLED[i] = CCONFIG_INSTANT_TRANSMISSION_ENABLED[i];
         defaultB = true;
         title = "Teleport Mode: '" + modeName + "' Release Level Required Enabled";
         description = "Teleport player targets must have a release level above 0%. (true = Enabled, false = Disabled) (Default: " + defaultB + ")";
         property = config.get(CATEGORY, "" + title, defaultB);
         property.comment = "Server Sided! " + description;
         CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_RELEASE_SENSE_REQUIRED_ENABLED[i] = property.getBoolean();
         int min = 1;
         int max = 10;
         title = "Teleport Mode: '" + modeName + "' Unlock level";
         description = "Set which Instant Transmission Skill Level unlocks this Mode" + getDefault("" + min, "" + max);
         property = config.get(CATEGORY, title, 1);
         property.comment = "Server Sided! " + description;
         CONFIG_INSTANT_TRANSMISSION_UNLOCK_SKILL_LEVEL[i] = (byte)property.getInt();
         if (CONFIG_INSTANT_TRANSMISSION_UNLOCK_SKILL_LEVEL[i] < min) {
            CONFIG_INSTANT_TRANSMISSION_UNLOCK_SKILL_LEVEL[i] = (byte)min;
         } else if (CONFIG_INSTANT_TRANSMISSION_UNLOCK_SKILL_LEVEL[i] > max) {
            CONFIG_INSTANT_TRANSMISSION_UNLOCK_SKILL_LEVEL[i] = (byte)max;
         }

         int var78 = 0;
         max = 1000000000;
         String[] defaultStringArray = i == 0
            ? new String[]{"500 9.5", "500 9", "500 8.5", "500 8", "500 7.5", "500 7", "500 6.5", "500 6", "500 5.5", "500 5"}
            : new String[]{"500 9.5", "500 9", "500 8.5", "500 8", "500 7.5", "500 7", "500 6.5", "500 6", "500 5.5", "500 5"};
         title = "Teleport Mode: '" + modeName + "' Ki Cost";
         description = "Set the Ki Cost of using the Skill per level (FlatCost PercentageCost)" + getDefault("" + var78, "" + max);
         property = config.get(CATEGORY, title, defaultStringArray, "Server Sided! " + description);
         String[] data = property.getStringList();

         for (int j = 0; j < 10; j++) {
            String line = data[j];
            String[] values = line.split(" ");
            CONFIG_INSTANT_TRANSMISSION_COST[i][0][j] = Float.parseFloat(values[0]);
            if (CONFIG_INSTANT_TRANSMISSION_COST[i][0][j] < var78) {
               CONFIG_INSTANT_TRANSMISSION_COST[i][0][j] = var78;
            } else if (CONFIG_INSTANT_TRANSMISSION_COST[i][0][j] > max) {
               CONFIG_INSTANT_TRANSMISSION_COST[i][0][j] = max;
            }

            CONFIG_INSTANT_TRANSMISSION_COST[i][1][j] = Float.parseFloat(values[1]);
            if (CONFIG_INSTANT_TRANSMISSION_COST[i][1][j] < var78) {
               CONFIG_INSTANT_TRANSMISSION_COST[i][1][j] = var78;
            } else if (CONFIG_INSTANT_TRANSMISSION_COST[i][1][j] > max) {
               CONFIG_INSTANT_TRANSMISSION_COST[i][1][j] = max;
            }
         }

         var78 = -1;
         max = 1000000000;
         defaultStringArray = i == 0
            ? new String[]{"0", "1", "2", "4", "6", "9", "12", "16", "20", "25"}
            : new String[]{"0", "1", "2", "4", "6", "9", "12", "16", "20", "25"};
         title = "Teleport Mode: '" + modeName + "' 'Surround' Player Limit";
         description = "Set the MAX number of Nearby Players the 'Surround' Mode can Teleport with the user per level. (-1 = No Limit)"
            + getDefault("" + var78, "" + max);
         property = config.get(CATEGORY, title, defaultStringArray, "Server Sided! " + description);
         data = property.getStringList();

         for (int j = 0; j < 10; j++) {
            String line = data[j];
            CONFIG_INSTANT_TRANSMISSION_SURROUND_PLAYER_LIMIT_SKILL_LEVEL[i][j] = Integer.parseInt(line);
            if (CONFIG_INSTANT_TRANSMISSION_SURROUND_PLAYER_LIMIT_SKILL_LEVEL[i][j] < var78) {
               CONFIG_INSTANT_TRANSMISSION_SURROUND_PLAYER_LIMIT_SKILL_LEVEL[i][j] = var78;
            } else if (CONFIG_INSTANT_TRANSMISSION_SURROUND_PLAYER_LIMIT_SKILL_LEVEL[i][j] > max) {
               CONFIG_INSTANT_TRANSMISSION_SURROUND_PLAYER_LIMIT_SKILL_LEVEL[i][j] = max;
            }
         }

         var78 = 0;
         int var90 = 9;
         title = "Teleport Mode: '" + modeName + "' Ki Sense Skill Level Requirement";
         description = "Required level of Ki Sense in order to this teleportation mode" + getDefault("" + var78, "" + var90);
         property = config.get(CATEGORY, title, i == 0 ? 1 : 3, "Server Sided! " + description);
         CONFIG_INSTANT_TRANSMISSION_KI_SENSE_REQUIREMENT[i] = (byte)property.getInt();
         if (CONFIG_INSTANT_TRANSMISSION_KI_SENSE_REQUIREMENT[i] < var78) {
            CONFIG_INSTANT_TRANSMISSION_KI_SENSE_REQUIREMENT[i] = (byte)var78;
         } else if (CONFIG_INSTANT_TRANSMISSION_KI_SENSE_REQUIREMENT[i] > var90) {
            CONFIG_INSTANT_TRANSMISSION_KI_SENSE_REQUIREMENT[i] = (byte)var90;
         }

         var78 = 0;
         var90 = 1000000000;
         defaultStringArray = i == 0
            ? new String[]{"2", "2", "2", "2", "1", "1", "1", "1", "0", "0"}
            : new String[]{"12", "11", "10", "9", "8", "7", "6", "5", "4", "3"};
         title = "Teleport Mode: '" + modeName + "' Cooldown";
         description = "Cooldown timer. Result is ConfigValue multiplied by 5 Seconds! Timer until the Skill can be used again"
            + getDefault("" + var78, "" + var90);
         property = config.get(CATEGORY, title, defaultStringArray, "Server Sided! " + description);
         data = property.getStringList();

         for (int j = 0; j < 10; j++) {
            String line = data[j];
            CONFIG_INSTANT_TRANSMISSION_COOLDOWN[i][j] = Integer.parseInt(line);
            if (CONFIG_INSTANT_TRANSMISSION_COOLDOWN[i][j] < var78) {
               CONFIG_INSTANT_TRANSMISSION_COOLDOWN[i][j] = var78;
            } else if (CONFIG_INSTANT_TRANSMISSION_COOLDOWN[i][j] > var90) {
               CONFIG_INSTANT_TRANSMISSION_COOLDOWN[i][j] = var90;
            }
         }
      }

      CATEGORY = "long distance mode only";
      boolean defaultB = false;
      String title = "Long Teleport Mode: Notify Server if someone Teleported Enabled";
      String description = "(true = Enabled, false = Disabled) (Default: " + defaultB + ")";
      Property property = config.get(CATEGORY, "" + title, defaultB);
      property.comment = "Server Sided! " + description;
      CONFIG_INSTANT_TRANSMISSION_NOTIFY_SERVER_ENABLED = property.getBoolean();
      defaultB = true;
      title = "Long Teleport Mode: Dimensional Teleport Enabled";
      description = "(true = Enabled, false = Disabled) (Default: " + defaultB + ")";
      property = config.get(CATEGORY, "" + title, defaultB);
      property.comment = "Server Sided! " + description;
      CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_TP_ENABLED = property.getBoolean();
      int min = 0;
      int max = 1000000000;
      String[] defaultStringArray = new String[]{"500 9.5", "500 9", "500 8.5", "500 8", "500 7.5", "500 7", "500 6.5", "500 6", "500 5.5", "500 5"};
      title = "Long Teleport Mode: Dimensional Teleport Ki Cost";
      description = "Set the Ki Cost of using the Skill per level (FlatCost PercentageCost)" + getDefault("" + min, "" + max);
      property = config.get(CATEGORY, title, defaultStringArray, "Server Sided! " + description);
      String[] data = property.getStringList();

      for (int j = 0; j < 10; j++) {
         String line = data[j];
         String[] values = line.split(" ");
         CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[0][j] = Float.parseFloat(values[0]);
         if (CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[0][j] < min) {
            CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[0][j] = min;
         } else if (CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[0][j] > max) {
            CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[0][j] = max;
         }

         CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[1][j] = Float.parseFloat(values[1]);
         if (CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[1][j] < min) {
            CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[1][j] = min;
         } else if (CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[1][j] > max) {
            CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_COST[1][j] = max;
         }
      }

      int var83 = 0;
      max = 1000000000;
      defaultStringArray = new String[]{""};
      title = "Long Teleport Mode: Dimensional Blacklist";
      description = "List of Dimension IDs where you can't use OR go into with Dimensional Teleportation.";
      property = config.get(CATEGORY, title, defaultStringArray, "Server Sided! " + description);
      data = property.getStringList();

      for (int j = 0; j < data.length; j++) {
         String line = data[j];
         if (line != null && line.length() > 0) {
            int value = Integer.parseInt(line);
            if (value < var83) {
               value = var83;
            } else if (value > max) {
               value = max;
            }

            CONFIG_INSTANT_TRANSMISSION_DIMENSION_BLACKLIST.put(value, true);
         }
      }

      CATEGORY = "short distance mode only";
      defaultB = true;
      title = "Short Teleport Mode: Target Finder can go through blocks Enabled";
      description = "(true = Enabled, false = Disabled) (Default: " + defaultB + ")";
      property = config.get(CATEGORY, "" + title, defaultB);
      property.comment = "Server Sided! " + description;
      CONFIG_INSTANT_TRANSMISSION_SHORT_GO_THROUGH_BLOCKS_ENABLED = property.getBoolean();
      var83 = 0;
      int var94 = 1000;
      title = "Short Teleport Mode: Target Finder Range per Update";
      description = "Block Radius around Target Finder. (Higher number = More Lagg)" + getDefault("" + var83, "" + var94);
      property = config.get(CATEGORY, "" + title, 1);
      property.comment = "Server Sided! " + description;
      CONFIG_INSTANT_TRANSMISSION_SHORT_TARGET_FINDER_RANGE = property.getDouble();
      if (CONFIG_INSTANT_TRANSMISSION_SHORT_TARGET_FINDER_RANGE < var83) {
         CONFIG_INSTANT_TRANSMISSION_SHORT_TARGET_FINDER_RANGE = var83;
      } else if (CONFIG_INSTANT_TRANSMISSION_SHORT_TARGET_FINDER_RANGE > var94) {
         CONFIG_INSTANT_TRANSMISSION_SHORT_TARGET_FINDER_RANGE = var94;
      }

      var83 = 0;
      var94 = 1000000;
      defaultStringArray = new String[]{"55", "60", "65", "70", "75", "80", "85", "90", "95", "100"};
      title = "Short Teleport Mode: Target Finder Distance Range";
      description = "(Higher number = More Lagg)" + getDefault("" + var83, "" + var94);
      property = config.get(CATEGORY, title, defaultStringArray, "Server Sided! " + description);
      data = property.getStringList();

      for (int j = 0; j < 10; j++) {
         String line = data[j];
         CONFIG_INSTANT_TRANSMISSION_SHORT_MAX_RANGE[j] = Integer.parseInt(line);
         if (CONFIG_INSTANT_TRANSMISSION_SHORT_MAX_RANGE[j] < var83) {
            CONFIG_INSTANT_TRANSMISSION_SHORT_MAX_RANGE[j] = var83;
         } else if (CONFIG_INSTANT_TRANSMISSION_SHORT_MAX_RANGE[j] > var94) {
            CONFIG_INSTANT_TRANSMISSION_SHORT_MAX_RANGE[j] = var94;
         }
      }

      titles = SHORT_MODES;

      for (int i = 0; i < 3; i++) {
         defaultB = true;
         title = "Short Teleport Mode: '" + titles[i] + "' Enabled";
         description = "(true = Enabled, false = Disabled) (Default: " + defaultB + ")";
         property = config.get(CATEGORY, "" + title, defaultB);
         property.comment = "Server Sided! " + description;
         CONFIG_INSTANT_TRANSMISSION_ENABLED_SHORT[i] = property.getBoolean();
      }

      CATEGORY = "both distance modes";
      titles = SURROUND_MODES;

      for (int i = 0; i < 3; i++) {
         defaultB = true;
         title = "Surround Teleport Mode: '" + titles[i] + "' Enabled";
         description = "(true = Enabled, false = Disabled) (Default: " + defaultB + ")";
         property = config.get(CATEGORY, "" + title, defaultB);
         property.comment = "Server Sided! " + description;
         CONFIG_INSTANT_TRANSMISSION_ENABLED_SURROUND[i] = property.getBoolean();
      }

      var83 = 0;
      var94 = 1000000000;
      defaultStringArray = new String[]{"50 0.275", "50 0.25", "50 0.225", "50 0.2", "50 0.175", "50 0.15", "50 0.125", "50 0.1", "50 0.075", "50 0.05"};
      title = "Surround Teleport Mode: Ki Cost per Player";
      description = "Set the Ki Cost of using the Skill per level (FlatCost PercentageCost)" + getDefault("" + var83, "" + var94);
      property = config.get(CATEGORY, title, defaultStringArray, "Server Sided! " + description);
      data = property.getStringList();

      for (int j = 0; j < 10; j++) {
         String line = data[j];
         String[] values = line.split(" ");
         CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[0][j] = Float.parseFloat(values[0]);
         if (CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[0][j] < var83) {
            CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[0][j] = var83;
         } else if (CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[0][j] > var94) {
            CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[0][j] = var94;
         }

         CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[1][j] = Float.parseFloat(values[1]);
         if (CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[1][j] < var83) {
            CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[1][j] = var83;
         } else if (CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[1][j] > var94) {
            CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[1][j] = var94;
         }
      }

      var83 = 0;
      int var97 = 1000;
      title = "Surround Teleport Mode: Target(s) Finder Range";
      description = "Block Radius around Target(s) Finder. (Higher number = More Lagg)" + getDefault("" + var83, "" + var97);
      property = config.get(CATEGORY, "" + title, 3);
      property.comment = "Server Sided! " + description;
      CONFIG_INSTANT_TRANSMISSION_SURROUND_TARGET_FINDER_RANGE = property.getDouble();
      if (CONFIG_INSTANT_TRANSMISSION_SURROUND_TARGET_FINDER_RANGE < var83) {
         CONFIG_INSTANT_TRANSMISSION_SURROUND_TARGET_FINDER_RANGE = var83;
      } else if (CONFIG_INSTANT_TRANSMISSION_SURROUND_TARGET_FINDER_RANGE > var97) {
         CONFIG_INSTANT_TRANSMISSION_SURROUND_TARGET_FINDER_RANGE = var97;
      }
   }
}
