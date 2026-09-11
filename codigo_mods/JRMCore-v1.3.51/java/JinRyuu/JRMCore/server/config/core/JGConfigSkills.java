package JinRyuu.JRMCore.server.config.core;

import JinRyuu.JRMCore.server.config.JGConfigBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JGConfigSkills extends JGConfigBase {
   public static final String CATEGORY_SKILLS_SERVERSIDED = "Skills";
   public static float GlobalSkillTPMultiplier;
   public static float cGlobalSkillTPMultiplier;
   public static float GlobalSkillMindMultiplier;
   public static float cGlobalSkillMindMultiplier;
   public static float GlobalSkillTPMultiplierFirst;
   public static float cGlobalSkillTPMultiplierFirst;
   public static float GlobalSkillMindMultiplierFirst;
   public static float cGlobalSkillMindMultiplierFirst;
   public static boolean GlobalSkillTPMultiplierWithLevel;
   public static boolean cGlobalSkillTPMultiplierWithLevel;
   public static boolean GlobalSkillMindMultiplierWithLevel;
   public static boolean cGlobalSkillMindMultiplierWithLevel;

   public static void init(Configuration config) {
      config.load();
      init_configs(config);
      config.save();
   }

   private static void init_configs(Configuration config) {
      String CATEGORY = "Skills";
      String server = "Server Sided!";
      int min = 0;
      int max = 1000000;
      String title = "Global Skill TP Cost Multiplier";
      String description = "Server Sided! " + title + getDefault("" + min, "" + max);
      Property property = config.get(CATEGORY, title, 1.0);
      property.comment = description;
      cGlobalSkillTPMultiplier = (float)property.getDouble();
      if (cGlobalSkillTPMultiplier < min) {
         cGlobalSkillTPMultiplier = min;
      } else if (cGlobalSkillTPMultiplier > max) {
         cGlobalSkillTPMultiplier = max;
      }

      GlobalSkillTPMultiplier = cGlobalSkillTPMultiplier;
      title = "Global Skill Mind Requirement Multiplier";
      description = "Server Sided! " + title + getDefault("" + min, "" + max);
      property = config.get(CATEGORY, title, 1.0);
      property.comment = description;
      cGlobalSkillMindMultiplier = (float)property.getDouble();
      if (cGlobalSkillMindMultiplier < min) {
         cGlobalSkillMindMultiplier = min;
      } else if (cGlobalSkillMindMultiplier > max) {
         cGlobalSkillMindMultiplier = max;
      }

      GlobalSkillMindMultiplier = cGlobalSkillMindMultiplier;
      title = "Global Skill TP Cost Multiplier for Level 1";
      description = "Server Sided! " + title + getDefault("" + min, "" + max);
      property = config.get(CATEGORY, title, 1.0);
      property.comment = description;
      cGlobalSkillTPMultiplierFirst = (float)property.getDouble();
      if (cGlobalSkillTPMultiplierFirst < min) {
         cGlobalSkillTPMultiplierFirst = min;
      } else if (cGlobalSkillTPMultiplierFirst > max) {
         cGlobalSkillTPMultiplierFirst = max;
      }

      GlobalSkillTPMultiplierFirst = cGlobalSkillTPMultiplierFirst;
      title = "Global Skill Mind Requirement Multiplier for Level 1";
      description = "Server Sided! " + title + getDefault("" + min, "" + max);
      property = config.get(CATEGORY, title, 1.0);
      property.comment = description;
      cGlobalSkillMindMultiplierFirst = (float)property.getDouble();
      if (cGlobalSkillMindMultiplierFirst < min) {
         cGlobalSkillMindMultiplierFirst = min;
      } else if (cGlobalSkillMindMultiplierFirst > max) {
         cGlobalSkillMindMultiplierFirst = max;
      }

      GlobalSkillMindMultiplierFirst = cGlobalSkillMindMultiplierFirst;
      title = "Global Skills TP Cost Multiplied with each Level On";
      description = "Server Sided! " + title + ". (Default: false).";
      property = config.get(CATEGORY, title, false);
      property.comment = description;
      cGlobalSkillTPMultiplierWithLevel = property.getBoolean();
      GlobalSkillTPMultiplierWithLevel = cGlobalSkillTPMultiplierWithLevel;
      title = "Global Skills Mind Requirement Multiplied with each Level On";
      description = "Server Sided! " + title + ". (Default: true).";
      property = config.get(CATEGORY, title, false);
      property.comment = description;
      cGlobalSkillMindMultiplierWithLevel = property.getBoolean();
      GlobalSkillMindMultiplierWithLevel = cGlobalSkillMindMultiplierWithLevel;
   }
}
