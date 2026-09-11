package JinRyuu.JRMCore.server.config;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.config.core.JGConfigMiniGameAirBoxing;
import JinRyuu.JRMCore.server.config.core.JGConfigMiniGameConcentration;
import JinRyuu.JRMCore.server.config.core.JGConfigSkills;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCAAiDifficulty;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCFormMastery;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCGoD;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCInstantTransmission;
import JinRyuu.JRMCore.server.config.dbc.JGConfigRaces;
import JinRyuu.JRMCore.server.config.dbc.JGConfigSkillsDBC;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import JinRyuu.JRMCore.server.config.nc.JGConfigSkillsNC;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class JGConfigsServer {
   public static final String CONFIG_JRMC = "/jingames/jrmc/";
   public static final String CONFIG_MINIGAMES = "/jingames/jrmc/minigames/";
   public static final String CONFIG_DBC = "/jingames/dbc/";
   public static final String CONFIG_NC = "/jingames/nc/";
   public static final String CONFIG_FORM = "/jingames/dbc/forms/";
   public static final String CONFIG_RACE = "/jingames/dbc/races/";
   public static final String CONFIG_AAI = "/jingames/dbc/aai/";
   public static final String CONFIG_DBC_SKILLS = "/jingames/dbc/skills/";
   public static Configuration minigame_concentration;
   public static Configuration minigame_airBoxing;
   public static Configuration ultraInstinct;
   public static Configuration godOfDestruction;
   public static Configuration[] dbcRaceClassAttributeMultiplier;
   public static Configuration configDir_skillsDBC;
   public static Configuration configDir_skillsNC;
   public static Configuration configDir_skills;
   public static Configuration[] configDir_DBCAAiDifficulties;
   public static Configuration configDir_skillInstantTransmission;
   public static Configuration[] configDir_formMastery = new Configuration[JRMCoreH.Races.length];
   public static Configuration configDir_formMasteryMain;

   public static void preInitServer(FMLPreInitializationEvent event) {
      JRMCore.configDir_minigame_concentration = new File(event.getModConfigurationDirectory().getPath() + "/jingames/jrmc/minigames/" + "concentration.cfg");
      minigame_concentration = new Configuration(JRMCore.configDir_minigame_concentration);
      JGConfigMiniGameConcentration.init(minigame_concentration);
      JRMCore.configDir_minigame_airBoxing = new File(event.getModConfigurationDirectory().getPath() + "/jingames/jrmc/minigames/" + "airboxing.cfg");
      minigame_airBoxing = new Configuration(JRMCore.configDir_minigame_airBoxing);
      JGConfigMiniGameAirBoxing.init(minigame_airBoxing);
      JRMCore.configDir_form = new File(event.getModConfigurationDirectory().getPath() + "/jingames/dbc/forms/" + "ultra_instinct.cfg");
      ultraInstinct = new Configuration(JRMCore.configDir_form);
      JGConfigUltraInstinct.init(ultraInstinct);
      JRMCore.configDir_form_god = new File(event.getModConfigurationDirectory().getPath() + "/jingames/dbc/forms/" + "god_of_destruction.cfg");
      godOfDestruction = new Configuration(JRMCore.configDir_form_god);
      JGConfigDBCGoD.init(godOfDestruction);
      dbcRaceClassAttributeMultiplier = new Configuration[JRMCoreH.Races.length];
      JRMCore.configDir_races = new File[JRMCoreH.Races.length];

      for (int i = 0; i < JRMCoreH.Races.length; i++) {
         String race = JRMCoreH.Races[i];
         JRMCore.configDir_races[i] = new File(event.getModConfigurationDirectory().getPath() + "/jingames/dbc/races/" + race.toLowerCase() + "/main.cfg");
         dbcRaceClassAttributeMultiplier[i] = new Configuration(JRMCore.configDir_races[i]);
         JGConfigRaces.init(dbcRaceClassAttributeMultiplier[i], (byte)i);
      }

      JRMCore.configDir_skillsDBC = new File(event.getModConfigurationDirectory().getPath() + "/jingames/dbc/" + "skill_costs.cfg");
      configDir_skillsDBC = new Configuration(JRMCore.configDir_skillsDBC);
      JGConfigSkillsDBC.init(configDir_skillsDBC);
      JRMCore.configDir_skillsNC = new File(event.getModConfigurationDirectory().getPath() + "/jingames/nc/" + "skill_costs.cfg");
      configDir_skillsNC = new Configuration(JRMCore.configDir_skillsNC);
      JGConfigSkillsNC.init(configDir_skillsNC);
      JRMCore.configDir_skills = new File(event.getModConfigurationDirectory().getPath() + "/jingames/jrmc/" + "skill_costs.cfg");
      configDir_skills = new Configuration(JRMCore.configDir_skills);
      JGConfigSkills.init(configDir_skills);
      configDir_DBCAAiDifficulties = new Configuration[JGConfigDBCAAiDifficulty.DIFFICULTIES.length];
      JRMCore.configDir_DBCAAiDifficulties = new File[JGConfigDBCAAiDifficulty.DIFFICULTIES.length];

      for (int i = 0; i < JGConfigDBCAAiDifficulty.DIFFICULTIES.length; i++) {
         String difficulty = JGConfigDBCAAiDifficulty.DIFFICULTIES[i];
         JRMCore.configDir_DBCAAiDifficulties[i] = new File(event.getModConfigurationDirectory().getPath() + "/jingames/dbc/aai/" + difficulty + ".cfg");
         configDir_DBCAAiDifficulties[i] = new Configuration(JRMCore.configDir_DBCAAiDifficulties[i]);
         JGConfigDBCAAiDifficulty.init(configDir_DBCAAiDifficulties[i], (byte)i);
      }

      JRMCore.configDir_skillInstantTransmission = new File(
         event.getModConfigurationDirectory().getPath() + "/jingames/dbc/skills/" + "instant_transmission.cfg"
      );
      configDir_skillInstantTransmission = new Configuration(JRMCore.configDir_skillInstantTransmission);
      JGConfigDBCInstantTransmission.init(configDir_skillInstantTransmission);
      JRMCore.configDir_formMasteryMain = new File(event.getModConfigurationDirectory().getPath() + "/jingames/dbc/forms/" + "form_mastery_main.cfg");
      configDir_formMasteryMain = new Configuration(JRMCore.configDir_formMasteryMain);
      JGConfigDBCFormMastery.initMain(configDir_formMasteryMain);

      for (int i = 0; i < JRMCoreH.Races.length; i++) {
         String race = JRMCoreH.Races[i];
         JRMCore.configDir_formMastery[i] = new File(
            event.getModConfigurationDirectory().getPath() + "/jingames/dbc/races/" + race.toLowerCase() + "/form_mastery.cfg"
         );
         configDir_formMastery[i] = new Configuration(JRMCore.configDir_formMastery[i]);
         JGConfigDBCFormMastery.init(configDir_formMastery[i], i);
      }

      Configuration config = new Configuration(event.getSuggestedConfigurationFile());
      JRMCoreConfig.init(config);
   }
}
