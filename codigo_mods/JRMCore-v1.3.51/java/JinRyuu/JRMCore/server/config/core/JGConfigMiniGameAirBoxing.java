package JinRyuu.JRMCore.server.config.core;

import JinRyuu.JRMCore.server.config.JGConfigBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JGConfigMiniGameAirBoxing extends JGConfigBase {
   public static final String CATEGORY_MINIGAME_SERVERSIDED = "MiniGame";
   public static final String CATEGORY_MINIGAME_AIRBOXING_SERVERSIDED = "MiniGame AirBoxing";
   public static final String CATEGORY_MINIGAME_MODE_SERVERSIDED = "MiniGame Mode";
   public static boolean TPGainOn;
   public static boolean cTPGainOn;
   public static float TPlimitIncreasesWithPlayerLevel;
   public static float cTPlimitIncreasesWithPlayerLevel;
   public static float TPMultiplier;
   public static float cTPMultiplier;
   public static int TPDailyLimit;
   public static int cTPDailyLimit;
   public static final int MODES = 4;
   public static int StartLife;
   public static int cStartLife;
   public static float[] KeySpawnSpeed = new float[4];
   public static float[] cKeySpawnSpeed = new float[4];
   public static float[] KeySpeed = new float[4];
   public static float[] cKeySpeed = new float[4];
   public static int[] KeyLifeTaken = new int[4];
   public static int[] cKeyLifeTaken = new int[4];
   public static int[][] KeyTypeIDs = new int[4][];
   public static int[][] cKeyTypeIDs = new int[4][];

   public static void init(Configuration config) {
      config.load();
      init_minigame(config);
      config.save();
   }

   private static void init_minigame(Configuration config) {
      String name = "";
      String CATEGORY = "MiniGame AirBoxing";
      String percentage = " (Percentage)";
      String SERVERSIDE = "Server Sided! ";
      String defaultValue = ". (true).";
      String title = "TP Gain On";
      String description = "If 'true' then from this minigame the TP gain feature will be enabled, if 'false' then players can't earn TP";
      Property property = config.get(CATEGORY, "" + title, true);
      property.comment = "Server Sided! " + description + defaultValue;
      cTPGainOn = property.getBoolean();
      TPGainOn = cTPGainOn;
      int min = 0;
      int max = 100000;
      defaultValue = "" + getDefault("" + min, "" + max);
      title = "TP Limit Increases With Player Level Multiplier";
      description = "If value is above 0 then the minigame's TP daily limit will increase based on the player's level multiplied with the config. Setting it to 0 disables this";
      property = config.get(CATEGORY, "" + title, 1.0);
      property.comment = "Server Sided! " + description + defaultValue;
      cTPlimitIncreasesWithPlayerLevel = (float)property.getDouble();
      if (cTPlimitIncreasesWithPlayerLevel < min) {
         cTPlimitIncreasesWithPlayerLevel = min;
      } else if (cTPlimitIncreasesWithPlayerLevel > max) {
         cTPlimitIncreasesWithPlayerLevel = max;
      }

      TPlimitIncreasesWithPlayerLevel = cTPlimitIncreasesWithPlayerLevel;
      int var53 = 0;
      max = 100000;
      defaultValue = "" + getDefault("" + var53, "" + max);
      title = "TP Multiplier";
      description = "TP gain reward multiplier";
      property = config.get(CATEGORY, "" + title, 0.035);
      property.comment = "Server Sided! " + description + defaultValue;
      cTPMultiplier = (float)property.getDouble();
      if (cTPMultiplier < var53) {
         cTPMultiplier = var53;
      } else if (cTPMultiplier > max) {
         cTPMultiplier = max;
      }

      TPMultiplier = cTPMultiplier;
      var53 = 0;
      max = 1000000000;
      defaultValue = "" + getDefault("" + var53, "" + max);
      title = "TP Daily Limit";
      description = "TP daily reward limitation";
      property = config.get(CATEGORY, "" + title, 20);
      property.comment = "Server Sided! " + description + defaultValue;
      cTPDailyLimit = property.getInt();
      if (cTPDailyLimit < var53) {
         cTPDailyLimit = var53;
      } else if (cTPDailyLimit > max) {
         cTPDailyLimit = max;
      }

      TPDailyLimit = cTPDailyLimit;
      var53 = 0;
      max = 1000000;
      defaultValue = "" + getDefault("" + var53, "" + max);
      title = "Starting Life";
      description = "Starting Life points";
      property = config.get(CATEGORY, "" + title, 1);
      property.comment = "Server Sided! " + description + defaultValue;
      cStartLife = property.getInt();
      if (cStartLife < var53) {
         cStartLife = var53;
      } else if (cStartLife > max) {
         cStartLife = max;
      }

      StartLife = cStartLife;
      double[] KeySpawnSpeed2 = new double[]{1.0, 0.8, 0.7, 1.0};
      double[] KeySpeed2 = new double[]{0.09, 0.085, 0.08, 0.1};
      int[] KeyLifeTaken2 = new int[]{0, 0, 0, 0};
      String[][] KeyTypeIDs2 = new String[][]{{"W"}, {"W", "A"}, {"W", "A", "S"}, {"W", "A", "S", "D"}};

      for (int i = 0; i < 4; i++) {
         CATEGORY = "MiniGame Mode" + i;
         var53 = 0;
         int var63 = 1000;
         defaultValue = "" + getDefault("" + var53, "" + var63);
         title = "Key Spawn Timer";
         description = "Key Spawn Speed in seconds";
         property = config.get(CATEGORY, "" + title, KeySpawnSpeed2[i]);
         property.comment = "Server Sided! " + description + defaultValue;
         cKeySpawnSpeed[i] = (float)property.getDouble();
         if (cKeySpawnSpeed[i] < var53) {
            cKeySpawnSpeed[i] = var53;
         } else if (cKeySpawnSpeed[i] > var63) {
            cKeySpawnSpeed[i] = var63;
         }

         KeySpawnSpeed[i] = cKeySpawnSpeed[i];
         var53 = 0;
         var63 = 1000;
         defaultValue = "" + getDefault("" + var53, "" + var63);
         title = "Key Movement Speed";
         description = "Key Movement Speed value";
         property = config.get(CATEGORY, "" + title, KeySpeed2[i]);
         property.comment = "Server Sided! " + description + defaultValue;
         cKeySpeed[i] = (float)property.getDouble();
         if (cKeySpeed[i] < var53) {
            cKeySpeed[i] = var53;
         } else if (cKeySpeed[i] > var63) {
            cKeySpeed[i] = var63;
         }

         KeySpeed[i] = cKeySpeed[i];
         var53 = 0;
         var63 = 1000;
         defaultValue = "" + getDefault("" + var53, "" + var63);
         title = "Life Taken on Miss";
         description = "Life lost on Key Miss";
         property = config.get(CATEGORY, "" + title, KeyLifeTaken2[i]);
         property.comment = "Server Sided! " + description + defaultValue;
         cKeyLifeTaken[i] = property.getInt();
         if (cKeyLifeTaken[i] < var53) {
            cKeyLifeTaken[i] = var53;
         } else if (cKeyLifeTaken[i] > var63) {
            cKeyLifeTaken[i] = var63;
         }

         KeyLifeTaken[i] = cKeyLifeTaken[i];
         var53 = 0;
         int var66 = 4;
         defaultValue = ". (W A S D).";
         String[] defaultValueS = KeyTypeIDs2[i];
         title = "Key Types";
         description = "Key Types in the mode";
         property = config.get(CATEGORY, "" + title, defaultValueS, "Server Sided! " + description + defaultValue);
         String text = property.getString();
         String[] keyValues = property.getStringList();
         cKeyTypeIDs[i] = new int[keyValues.length];
         KeyTypeIDs[i] = new int[keyValues.length];

         for (int j = 0; j < keyValues.length; j++) {
            cKeyTypeIDs[i][j] = keyToId(keyValues[j]);
            if (cKeyTypeIDs[i][j] < var53) {
               cKeyTypeIDs[i][j] = var53;
            } else if (cKeyTypeIDs[i][j] > var66) {
               cKeyTypeIDs[i][j] = var66;
            }

            KeyTypeIDs[i][j] = cKeyTypeIDs[i][j];
         }
      }
   }

   public static int keyToId(String s) {
      if (s.equals("W")) {
         return 0;
      } else if (s.equals("A")) {
         return 1;
      } else {
         return s.equals("S") ? 2 : 3;
      }
   }
}
