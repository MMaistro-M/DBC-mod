package JinRyuu.JRMCore.server.config.dbc;

import JinRyuu.JRMCore.server.config.JGConfigBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JGConfigDBCAAiDifficulty extends JGConfigBase {
   public static final String[] DIFFICULTIES = new String[]{"Easy", "Medium", "Hard", "Insane"};
   public static final String[] AAIs = new String[]{"Ground Dash", "Jump", "Flying Dash", "Ki Attack Charge", "Teleport"};
   public static double[] SpeedMulti = new double[DIFFICULTIES.length];
   public static double[] GroundDashSpeedMulti = new double[DIFFICULTIES.length];
   public static double[] GroundDashSpeedMulti2 = new double[DIFFICULTIES.length];
   public static double[] GroundDashLimit = new double[DIFFICULTIES.length];
   public static double[] JumpMulti = new double[DIFFICULTIES.length];
   public static double[] JumpMulti2 = new double[DIFFICULTIES.length];
   public static double[] JumpLimit = new double[DIFFICULTIES.length];
   public static double[] JumpLimit2 = new double[DIFFICULTIES.length];
   public static double[] JumpRate = new double[DIFFICULTIES.length];
   public static double[] FlyingDashMulti = new double[DIFFICULTIES.length];
   public static double[] FlyingDashLimit = new double[DIFFICULTIES.length];
   public static double[] KiAttackChargeMulti = new double[DIFFICULTIES.length];
   public static double[] KiAttackChargeLimit = new double[DIFFICULTIES.length];
   public static int[] TeleportRateMin = new int[DIFFICULTIES.length];
   public static int[] TeleportRateMax = new int[DIFFICULTIES.length];
   public static double[] cSpeedMulti = new double[DIFFICULTIES.length];
   public static double[] cGroundDashSpeedMulti = new double[DIFFICULTIES.length];
   public static double[] cGroundDashSpeedMulti2 = new double[DIFFICULTIES.length];
   public static double[] cGroundDashLimit = new double[DIFFICULTIES.length];
   public static double[] cJumpMulti = new double[DIFFICULTIES.length];
   public static double[] cJumpMulti2 = new double[DIFFICULTIES.length];
   public static double[] cJumpLimit = new double[DIFFICULTIES.length];
   public static double[] cJumpLimit2 = new double[DIFFICULTIES.length];
   public static double[] cJumpRate = new double[DIFFICULTIES.length];
   public static double[] cFlyingDashMulti = new double[DIFFICULTIES.length];
   public static double[] cFlyingDashLimit = new double[DIFFICULTIES.length];
   public static double[] cKiAttackChargeMulti = new double[DIFFICULTIES.length];
   public static double[] cKiAttackChargeLimit = new double[DIFFICULTIES.length];
   public static int[] cTeleportRateMin = new int[DIFFICULTIES.length];
   public static int[] cTeleportRateMax = new int[DIFFICULTIES.length];

   public static void init(Configuration config, byte difficultyID) {
      config.load();
      init_difficulty(config, difficultyID);
      config.save();
   }

   private static void init_difficulty(Configuration config, byte difficultyID) {
      String name = "DBC " + DIFFICULTIES[difficultyID];
      String percentage = " (Percentage)";
      String server = "Server Sided! ";
      String CATEGORY = "Main Settings";
      int min = 0;
      int max = 10000;
      String title = CATEGORY + " Speed Multiplier";
      Property property = config.get(CATEGORY, title, new double[]{-1.0, 1.3, 1.8, 2.3}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + min, "" + max);
      cSpeedMulti[difficultyID] = property.getDouble();
      if (cSpeedMulti[difficultyID] < min) {
         cSpeedMulti[difficultyID] = min;
      } else if (cSpeedMulti[difficultyID] > max) {
         cSpeedMulti[difficultyID] = max;
      }

      SpeedMulti[difficultyID] = cSpeedMulti[difficultyID];
      CATEGORY = AAIs[0];
      int var38 = 0;
      int var52 = 10000;
      title = CATEGORY + " Speed Multiplier";
      property = config.get(CATEGORY, title, new double[]{1.2, 1.3, 1.4, 1.5}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cGroundDashSpeedMulti[difficultyID] = property.getDouble();
      if (cGroundDashSpeedMulti[difficultyID] < var38) {
         cGroundDashSpeedMulti[difficultyID] = var38;
      } else if (cGroundDashSpeedMulti[difficultyID] > var52) {
         cGroundDashSpeedMulti[difficultyID] = var52;
      }

      GroundDashSpeedMulti[difficultyID] = cGroundDashSpeedMulti[difficultyID];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Position Difference Speed Multiplier";
      property = config.get(CATEGORY, title, new double[]{0.3, 0.4, 0.5, 0.8}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cGroundDashSpeedMulti2[difficultyID] = property.getDouble();
      if (cGroundDashSpeedMulti2[difficultyID] < var38) {
         cGroundDashSpeedMulti2[difficultyID] = var38;
      } else if (cGroundDashSpeedMulti2[difficultyID] > var52) {
         cGroundDashSpeedMulti2[difficultyID] = var52;
      }

      GroundDashSpeedMulti2[difficultyID] = cGroundDashSpeedMulti2[difficultyID];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Speed Limit";
      property = config.get(CATEGORY, title, new double[]{0.2, 0.3, 0.4, 0.5}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cGroundDashLimit[difficultyID] = property.getDouble();
      if (cGroundDashLimit[difficultyID] < var38) {
         cGroundDashLimit[difficultyID] = var38;
      } else if (cGroundDashLimit[difficultyID] > var52) {
         cGroundDashLimit[difficultyID] = var52;
      }

      GroundDashLimit[difficultyID] = cGroundDashLimit[difficultyID];
      CATEGORY = AAIs[1];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Upwards Multiplier";
      property = config.get(CATEGORY, title, new double[]{0.9, 0.9, 0.9, 0.9}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cJumpMulti[difficultyID] = property.getDouble();
      if (cJumpMulti[difficultyID] < var38) {
         cJumpMulti[difficultyID] = var38;
      } else if (cJumpMulti[difficultyID] > var52) {
         cJumpMulti[difficultyID] = var52;
      }

      JumpMulti[difficultyID] = cJumpMulti[difficultyID];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Sideways Multiplier";
      property = config.get(CATEGORY, title, new double[]{0.5, 0.8, 1.0, 1.2}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cJumpMulti2[difficultyID] = property.getDouble();
      if (cJumpMulti2[difficultyID] < var38) {
         cJumpMulti2[difficultyID] = var38;
      } else if (cJumpMulti2[difficultyID] > var52) {
         cJumpMulti2[difficultyID] = var52;
      }

      JumpMulti2[difficultyID] = cJumpMulti2[difficultyID];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Sideways Limit";
      property = config.get(CATEGORY, title, new double[]{2.5, 4.0, -1.0, -1.0}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cJumpLimit[difficultyID] = property.getDouble();
      if (cJumpLimit[difficultyID] < var38) {
         cJumpLimit[difficultyID] = var38;
      } else if (cJumpLimit[difficultyID] > var52) {
         cJumpLimit[difficultyID] = var52;
      }

      JumpLimit[difficultyID] = cJumpLimit[difficultyID];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Upwards Limit";
      property = config.get(CATEGORY, title, new double[]{2.0, 3.0, 7.0, 7.0}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cJumpLimit2[difficultyID] = property.getDouble();
      if (cJumpLimit2[difficultyID] < var38) {
         cJumpLimit2[difficultyID] = var38;
      } else if (cJumpLimit2[difficultyID] > var52) {
         cJumpLimit2[difficultyID] = var52;
      }

      JumpLimit2[difficultyID] = cJumpLimit2[difficultyID];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Rate";
      property = config.get(CATEGORY, title, new double[]{0.6, 0.8, 0.9, 0.9}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cJumpRate[difficultyID] = property.getDouble();
      if (cJumpRate[difficultyID] < var38) {
         cJumpRate[difficultyID] = var38;
      } else if (cJumpRate[difficultyID] > var52) {
         cJumpRate[difficultyID] = var52;
      }

      JumpRate[difficultyID] = cJumpRate[difficultyID];
      CATEGORY = AAIs[2];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Speed Multiplier";
      property = config.get(CATEGORY, title, new double[]{1.0, 2.0, 4.0, 6.0}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cFlyingDashMulti[difficultyID] = property.getDouble();
      if (cFlyingDashMulti[difficultyID] < var38) {
         cFlyingDashMulti[difficultyID] = var38;
      } else if (cFlyingDashMulti[difficultyID] > var52) {
         cFlyingDashMulti[difficultyID] = var52;
      }

      FlyingDashMulti[difficultyID] = cFlyingDashMulti[difficultyID];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Speed Limit";
      property = config.get(CATEGORY, title, new double[]{0.15, 0.2, 0.8, 1.0}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cFlyingDashLimit[difficultyID] = property.getDouble();
      if (cFlyingDashLimit[difficultyID] < var38) {
         cFlyingDashLimit[difficultyID] = var38;
      } else if (cFlyingDashLimit[difficultyID] > var52) {
         cFlyingDashLimit[difficultyID] = var52;
      }

      FlyingDashLimit[difficultyID] = cFlyingDashLimit[difficultyID];
      CATEGORY = AAIs[3];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Runaway Speed Multiplier";
      property = config.get(CATEGORY, title, new double[]{0.6, 0.7, 0.9, 1.1}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cKiAttackChargeMulti[difficultyID] = property.getDouble();
      if (cKiAttackChargeMulti[difficultyID] < var38) {
         cKiAttackChargeMulti[difficultyID] = var38;
      } else if (cKiAttackChargeMulti[difficultyID] > var52) {
         cKiAttackChargeMulti[difficultyID] = var52;
      }

      KiAttackChargeMulti[difficultyID] = cKiAttackChargeMulti[difficultyID];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Runaway Speed Limit";
      property = config.get(CATEGORY, title, new double[]{0.2, 0.4, 0.7, 1.0}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cKiAttackChargeLimit[difficultyID] = property.getDouble();
      if (cKiAttackChargeLimit[difficultyID] < var38) {
         cKiAttackChargeLimit[difficultyID] = var38;
      } else if (cKiAttackChargeLimit[difficultyID] > var52) {
         cKiAttackChargeLimit[difficultyID] = var52;
      }

      KiAttackChargeLimit[difficultyID] = cKiAttackChargeLimit[difficultyID];
      CATEGORY = AAIs[4];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Minimum Rate";
      property = config.get(CATEGORY, title, new int[]{120, 100, 90, 75}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cTeleportRateMin[difficultyID] = property.getInt();
      if (cTeleportRateMin[difficultyID] < var38) {
         cTeleportRateMin[difficultyID] = var38;
      } else if (cTeleportRateMin[difficultyID] > var52) {
         cTeleportRateMin[difficultyID] = var52;
      }

      TeleportRateMin[difficultyID] = cTeleportRateMin[difficultyID];
      var38 = 0;
      var52 = 10000;
      title = CATEGORY + " Maximum Rate";
      property = config.get(CATEGORY, title, new int[]{50, 50, 45, 45}[difficultyID]);
      property.comment = "Server Sided! " + title + getDefault("" + var38, "" + var52);
      cTeleportRateMax[difficultyID] = property.getInt();
      if (cTeleportRateMax[difficultyID] < var38) {
         cTeleportRateMax[difficultyID] = var38;
      } else if (cTeleportRateMax[difficultyID] > var52) {
         cTeleportRateMax[difficultyID] = var52;
      }

      TeleportRateMax[difficultyID] = cTeleportRateMax[difficultyID];
   }
}
