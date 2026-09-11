package JinRyuu.JRMCore.server.config.dbc;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.config.JGConfigBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JGConfigSkillsDBC extends JGConfigBase {
   public static final int COST_MIN_TP = -1;
   public static final int COST_MIN_MIND = 0;
   public static final int COST_MAX = 2000000000;
   public static final int MIND_COST = 15;
   public static final String CATEGORY_SKILLS_SERVERSIDED = "Skills";
   public static final String CATEGORY_DBC_SKILLS_SERVERSIDED = "DBC Skills";
   public static final String CATEGORY_DBC_RACIAL_SKILLS_SERVERSIDED = "DBC Racial Skills";

   public static void init(Configuration config) {
      config.load();
      init_configs(config);
      config.save();
   }

   private static void init_configs(Configuration config) {
      String SERVERSIDE = "Server Sided! ";
      String CATEGORY = "DBC Racial Skills";
      int[][] defaultValuesTP = new int[][]{
         {3500, 7500, 15000, 30000, 60000},
         {4000, 4500, 5000, 5500, 8000, 15000, 30000, 40000},
         {4000, 4500, 5000, 5500, 8000, 15000, 30000, 40000},
         {3500, 7500, 15000, 30000, 60000},
         {3000, 7500, 11000, 22000, 33000, 44000},
         {2000, 7000, 15000, 30000, 50000}
      };
      int races = JRMCoreH.Races.length;

      for (int i = 0; i < races; i++) {
         String race = JRMCoreH.Races[i];
         int[] defaultValuesI = defaultValuesTP[i];
         String title = race + " Racial Skill TP Costs";
         String description = "Server Sided! " + race + " transformation skill leveling TP Cost. (From " + -1 + " to " + 2000000000 + ")";
         Property property = config.get(CATEGORY, title, defaultValuesI, description, -1, 2000000000);
         int[] configuredTPCosts = normalizeRacialCosts(property.getIntList(), defaultValuesI);
         JRMCoreH.DBCRacialSkillTPCost[i] = configuredTPCosts;
         JRMCoreH.cDBCRacialSkillTPCost[i] = configuredTPCosts.clone();
         defaultValuesI = new int[]{15};
         title = race + " Racial Skill Mind Requirement";
         description = "Server Sided! " + race + " transformation skill leveling MIND Requirement. (From " + 0 + " to " + 2000000000 + ")";
         property = config.get(CATEGORY, title, defaultValuesI, description, 0, 2000000000);
         int[] configuredMindCosts = normalizeRacialCosts(property.getIntList(), defaultValuesI);
         JRMCoreH.DBCRacialSkillMindCost[i] = configuredMindCosts;
         JRMCoreH.cDBCRacialSkillMindCost[i] = configuredMindCosts.clone();
      }

      CATEGORY = "DBC Skills";
      int[][] skillDefaultTPCost = new int[][]{
         {300},
         {40},
         {50},
         {60},
         {150},
         {400},
         {300},
         {250},
         {400},
         {20000},
         {1500},
         {700},
         {1000},
         {800},
         {600},
         {200},
         {50000, 25000, 25000},
         {5000},
         {50000}
      };
      int[][] skillDefaultMindCost = new int[][]{
         {10, 5},
         {5, 2},
         {5, 2},
         {10, 5},
         {10, 5},
         {10, 5},
         {10, 5},
         {10, 5},
         {10, 5},
         {10, 5},
         {10, 5},
         {10, 5},
         {10, 5},
         {10, 5},
         {10, 5},
         {5, 5},
         {10, 5, 5},
         {10, 5},
         {10}
      };
      int skills = JRMCoreH.DBCSkillNames.length;

      for (int i = 0; i < skills; i++) {
         String skill = JRMCoreH.DBCSkillNames[i];
         if (JRMCoreH.DBCSkillNames[i].equals(JRMCoreH.DBCSkillNames[16])) {
            if (JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0) {
               JRMCoreH.DBCSkillTPCost[i] = JGConfigUltraInstinct.CONFIG_UI_TP_COST;
               JRMCoreH.cDBCSkillTPCost[i] = JGConfigUltraInstinct.cCONFIG_UI_TP_COST;
               JRMCoreH.DBCSkillMindCost[i] = JGConfigUltraInstinct.CONFIG_UI_MIND_REQUIREMENT;
               JRMCoreH.cDBCSkillMindCost[i] = JGConfigUltraInstinct.cCONFIG_UI_MIND_REQUIREMENT;
            } else {
               int[] d = new int[]{1};
               JRMCoreH.DBCSkillTPCost[i] = d;
               JRMCoreH.cDBCSkillTPCost[i] = d;
               JRMCoreH.DBCSkillMindCost[i] = d;
               JRMCoreH.cDBCSkillMindCost[i] = d;
            }
         } else if (JRMCoreH.DBCSkillNames[i].equals(JRMCoreH.DBCSkillNames[18])) {
            JRMCoreH.DBCSkillTPCost[i] = new int[]{JGConfigDBCGoD.CONFIG_GOD_TP_COST};
            JRMCoreH.cDBCSkillTPCost[i] = new int[]{JGConfigDBCGoD.cCONFIG_GOD_TP_COST};
            JRMCoreH.DBCSkillMindCost[i] = new int[]{JGConfigDBCGoD.CONFIG_GOD_MIND_REQUIREMENT};
            JRMCoreH.cDBCSkillMindCost[i] = new int[]{JGConfigDBCGoD.cCONFIG_GOD_MIND_REQUIREMENT};
         } else {
            int[] defaultValuesI = skillDefaultTPCost[i];
            String title = skill + " Skill TP Costs";
            String description = "Server Sided! " + skill + " skill leveling TP Cost. (From " + -1 + " to " + 2000000000 + ")";
            Property property = config.get(CATEGORY, title, defaultValuesI, description, -1, 2000000000);
            JRMCoreH.DBCSkillTPCost[i] = property.getIntList();
            JRMCoreH.cDBCSkillTPCost[i] = property.getIntList();
            defaultValuesI = skillDefaultMindCost[i];
            title = skill + " Skill Mind Requirement";
            description = "Server Sided! " + skill + " skill leveling MIND Requirement. (From " + 0 + " to " + 2000000000 + ")";
            property = config.get(CATEGORY, title, defaultValuesI, description, 0, 2000000000);
            JRMCoreH.DBCSkillMindCost[i] = property.getIntList();
            JRMCoreH.cDBCSkillMindCost[i] = property.getIntList();
         }
      }
   }

   private static int[] normalizeRacialCosts(int[] configured, int[] defaults) {
      int length = Math.max(configured.length, defaults.length);
      int[] normalized = new int[length];

      for (int i = 0; i < length; ++i) {
         int fallback = defaults[Math.min(i, defaults.length - 1)];
         int value = i < configured.length ? configured[i] : fallback;
         normalized[i] = value <= 0 ? fallback : value;
      }

      return normalized;
   }
}
