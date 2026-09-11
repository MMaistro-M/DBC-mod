package JinRyuu.JRMCore.server.config.nc;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.config.JGConfigBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JGConfigSkillsNC extends JGConfigBase {
   public static final int COST_MIN_TP = -1;
   public static final int COST_MIN_MIND = 0;
   public static final int COST_MAX = 2000000000;
   public static final String CATEGORY_SKILLS_SERVERSIDED = "Skills";
   public static final String CATEGORY_NC_CLAN_SKILLS_SERVERSIDED = "NC Clan Skills";
   public static final String CATEGORY_NC_SKILLS_SERVERSIDED = "NC Skills";

   public static void init(Configuration config) {
      config.load();
      init_configs(config);
      config.save();
   }

   private static void init_configs(Configuration config) {
      String SERVERSIDE = "Server Sided! ";
      String CATEGORY = "NC Clan Skills";
      int[][] defaultValuesTP = new int[][]{{250}, {250}, {250}};
      int clans = JRMCoreH.Clans.length;

      for (int i = 0; i < clans; i++) {
         String clan = JRMCoreH.Clans[i];
         int[] defaultValuesI = defaultValuesTP[i];
         String title = clan + " Clan Skill TP Costs";
         String description = "Server Sided! " + clan + " unique skill leveling TP Cost. (From " + -1 + " to " + 2000000000 + ")";
         Property property = config.get(CATEGORY, title, defaultValuesI, description, -1, 2000000000);
         JRMCoreH.NCRacialSkillTPCost[i] = property.getIntList();
         JRMCoreH.cNCRacialSkillTPCost[i] = property.getIntList();
         defaultValuesI = new int[]{15};
         title = clan + " Clan Skill Mind Requirement";
         description = "Server Sided! " + clan + " unique skill leveling MIND Requirement. (From " + 0 + " to " + 2000000000 + ")";
         property = config.get(CATEGORY, title, defaultValuesI, description, 0, 2000000000);
         JRMCoreH.NCRacialSkillMindCost[i] = property.getIntList();
         JRMCoreH.cNCRacialSkillMindCost[i] = property.getIntList();
      }

      CATEGORY = "NC Skills";
      int[][] skillDefaultTPCost = new int[][]{{70}, {90}, {80}, {100}, {150}, {150}, {150}, {150}, {150}, {200}, {130}, {250}, {1000}};
      int[][] skillDefaultMindCost = new int[][]{{15}, {15}, {15}, {15}, {15}, {15}, {15}, {15}, {15}, {15}, {15}, {15}, {15}};
      int skills = JRMCoreH.NCSkillNames.length;

      for (int i = 0; i < skills; i++) {
         String skill = JRMCoreH.NCSkillNames[i];
         int[] defaultValuesI = skillDefaultTPCost[i];
         String title = skill + " Skill TP Costs";
         String description = "Server Sided! " + skill + " skill leveling TP Cost. (From " + -1 + " to " + 2000000000 + ")";
         Property property = config.get(CATEGORY, title, defaultValuesI, description, -1, 2000000000);
         JRMCoreH.NCSkillTPCost[i] = property.getIntList();
         JRMCoreH.cNCSkillTPCost[i] = property.getIntList();
         defaultValuesI = skillDefaultMindCost[i];
         title = skill + " Skill Mind Requirement";
         description = "Server Sided! " + skill + " skill leveling MIND Requirement. (From " + 0 + " to " + 2000000000 + ")";
         property = config.get(CATEGORY, title, defaultValuesI, description, 0, 2000000000);
         JRMCoreH.NCSkillMindCost[i] = property.getIntList();
         JRMCoreH.cNCSkillMindCost[i] = property.getIntList();
      }
   }
}
