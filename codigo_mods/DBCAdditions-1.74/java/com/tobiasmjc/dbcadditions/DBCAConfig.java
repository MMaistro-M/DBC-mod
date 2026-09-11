/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package com.tobiasmjc.dbcadditions;

import com.tobiasmjc.dbcadditions.data.FormMasteryData;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.FormItem;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.data.forms.display.CustomAura;
import com.tobiasmjc.dbcadditions.data.forms.display.HairType;
import com.tobiasmjc.dbcadditions.data.forms.display.Tattoo;
import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.commons.lang3.ArrayUtils;

public class DBCAConfig {
    private static Configuration config;
    public static boolean BeerusGriefing;
    public static boolean SpawnCheck;
    public static boolean FusionModifyAppearance;
    public static boolean WhisTeleport;
    public static boolean CustomRaces;
    public static boolean CustomForms;
    public static double PotaraMultiplier;
    public static double HPotaraMultiplier;
    public static int PotaraAmount;
    public static int PotaraCooldown;
    public static int PotaraTime;
    public static int PotaraMinLevel;
    public static int HPotaraMinLevel;
    public static double AbsorbTPMultiplier;
    public static double AbsorbMaxDamage;
    public static double AbsorbTimeLimit;
    public static byte HSaiyanMaxRacial;
    public static byte HArcosianMaxRacial;
    public static byte HHumanMaxRacial;
    public static byte HNamekianMaxRacial;
    public static byte HMajinMaxRacial;
    public static byte SaiyanMaxRacial;
    public static byte ArcosianMaxRacial;
    public static byte HumanMaxRacial;
    public static byte NamekianMaxRacial;
    public static byte MajinMaxRacial;
    public static int[] SaiyanTPCosts;
    public static int[] NamekianTPCosts;
    public static int[] ArcosianTPCosts;
    public static int[] MajinTPCosts;
    public static int[] HumanTPCosts;
    public static int[] SaiyanMindCosts;
    public static int[] NamekianMindCosts;
    public static int[] ArcosianMindCosts;
    public static int[] MajinMindCosts;
    public static int[] HumanMindCosts;
    public static int[] HSaiyanTPCosts;
    public static int[] HNamekianTPCosts;
    public static int[] HArcosianTPCosts;
    public static int[] HMajinTPCosts;
    public static int[] HHumanTPCosts;
    public static int[] HBioAndroidTPCosts;
    public static int[] HSaiyanMindCosts;
    public static int[] HNamekianMindCosts;
    public static int[] HArcosianMindCosts;
    public static int[] HMajinMindCosts;
    public static int[] HHumanMindCosts;
    public static int[] HBioAndroidMindCosts;

    public DBCAConfig(Configuration config1, String customFormsPath, String skillsPath) {
        String name;
        config = config1;
        config.load();
        Property customForms = config.get("General", "Custom Forms Enabled", true);
        customForms.comment = "Enable/Disable Custom Forms (set to false in case you are having trouble with other transformation addons)";
        CustomForms = customForms.getBoolean();
        Property customRaces = config.get("General", "Custom Races Enabled", true);
        customRaces.comment = "Enable/Disable Custom Races (set to false in case you are having trouble with other transformation addons) - DO NOT DISABLE IN A WORLD WHERE CUSTOM RACES ARE USED!";
        CustomRaces = customRaces.getBoolean();
        Property whisTeleport = config.get("General", "Whis Dimensional Teleport", true);
        whisTeleport.comment = "If false, teleporting to Universal Arena and Beerus Planet by talking to Whis is going to be disabled.";
        WhisTeleport = whisTeleport.getBoolean();
        Property spawnCheck = config.get("General", "Custom DBC Additions NPC Spawn/Respawn", true);
        spawnCheck.comment = "Should DBC Additions NPC Spawn or Respawn (Spawn Check)?";
        SpawnCheck = spawnCheck.getBoolean();
        Property potaraFusionMultiplier = config.get("General", "Potara Fusion Multiplier", PotaraMultiplier);
        potaraFusionMultiplier.comment = "Additional Potara Fusion Multiplier (Default: 1.0)";
        HPotaraMultiplier = potaraFusionMultiplier.getDouble();
        Property potaraCooldown = config.get("General", "Potara Old Kai Cooldown", PotaraCooldown);
        potaraCooldown.comment = "How much time should the player wait to get Potara again? (Minutes) (Default: 120)";
        PotaraCooldown = potaraCooldown.getInt();
        Property potaraMinLevel = config.get("General", "Potara Minimum Level", HPotaraMinLevel);
        potaraMinLevel.comment = "Minimum Player Level to ask for Potara (Default: 500) (If set to -1, Old Kai wont give Potaras)";
        HPotaraMinLevel = potaraMinLevel.getInt();
        Property potaraAmount = config.get("General", "Potara Old Kai Amount", PotaraAmount);
        potaraAmount.comment = "How many Potaras should be given to the player? (Default: 1)";
        PotaraAmount = potaraAmount.getInt();
        Property potaraFusionTime = config.get("General", "Potara Fusion Time", PotaraTime);
        potaraFusionTime.comment = "Potara Fusion Time in minutes (Default: 20)";
        PotaraTime = potaraFusionTime.getInt();
        Property fusionModify = config.get("Client", "Fusion Combine Player Traits", true);
        fusionModify.comment = "If set to false, Fusion will not combine player traits.";
        FusionModifyAppearance = fusionModify.getBoolean();
        Property beerusGriefing = config.get("Dimensions", "Enable Beerus Griefing", false);
        beerusGriefing.comment = "Enable/Disable griefing in Beerus Planet";
        BeerusGriefing = beerusGriefing.getBoolean();
        Property saiyanMaxRacial = config.get("Races.Saiyan", "Max Racial Skill", (int)HSaiyanMaxRacial).setMaxValue(9).setMinValue(1);
        saiyanMaxRacial.comment = "Saiyan Max Racial Skill (Range: 1-9)";
        HSaiyanMaxRacial = (byte)saiyanMaxRacial.getInt();
        for (int i = 8; i <= 9; ++i) {
            int recommendedTPCost = i == 8 ? 120000 : 240000;
            int recommendedMindCost = i == 8 ? 30 : 45;
            Property saiyanTPCost = config.get("Races.Saiyan", "Racial Skill Level " + i + " TP Cost", recommendedTPCost).setMaxValue(2000000000).setMinValue(1);
            saiyanTPCost.comment = "Saiyan Racial Skill TP Cost (Range: 1-2000000000)";
            DBCAConfig.HSaiyanTPCosts[i - 8] = proportionalCost(saiyanTPCost, recommendedTPCost);
            Property saiyanMindCost = config.get("Races.Saiyan", "Racial Skill Level " + i + " Mind Cost", recommendedMindCost).setMaxValue(100000).setMinValue(1);
            saiyanMindCost.comment = "Saiyan Racial Skill Mind Cost (Range: 1-10000)";
            DBCAConfig.HSaiyanMindCosts[i - 8] = proportionalCost(saiyanMindCost, recommendedMindCost);
        }
        Property humanMaxRacial = config.get("Races.Human", "Max Racial Skill", (int)HHumanMaxRacial).setMaxValue(9).setMinValue(1);
        humanMaxRacial.comment = "Human Max Racial Skill (Range: 1-9)";
        HHumanMaxRacial = (byte)humanMaxRacial.getInt();
        for (int i = 6; i <= 9; ++i) {
            int recommendedTPCost = new int[]{15000, 30000, 40000, 60000}[i - 6];
            int recommendedMindCost = new int[]{20, 25, 30, 40}[i - 6];
            Property humanTPCost = config.get("Races.Human", "Racial Skill Level " + i + " TP Cost", recommendedTPCost).setMaxValue(2000000000).setMinValue(1);
            humanTPCost.comment = "Human Racial Skill TP Cost (Range: 1-2000000000)";
            DBCAConfig.HHumanTPCosts[i - 6] = proportionalCost(humanTPCost, recommendedTPCost);
            Property humanMindCost = config.get("Races.Human", "Racial Skill Level " + i + " Mind Cost", recommendedMindCost).setMaxValue(100000).setMinValue(1);
            humanMindCost.comment = "Human Racial Skill Mind Cost (Range: 1-10000)";
            DBCAConfig.HHumanMindCosts[i - 6] = proportionalCost(humanMindCost, recommendedMindCost);
        }
        Property arcosianMaxRacial = config.get("Races.Arcosian", "Max Racial Skill", (int)HArcosianMaxRacial).setMaxValue(9).setMinValue(1);
        arcosianMaxRacial.comment = "Arcosian Max Racial Skill (Range: 1-9)";
        HArcosianMaxRacial = (byte)arcosianMaxRacial.getInt();
        for (int i = 7; i <= 9; ++i) {
            int recommendedTPCost = new int[]{66000, 99000, 150000}[i - 7];
            int recommendedMindCost = new int[]{25, 35, 50}[i - 7];
            Property arcosianTPCost = config.get("Races.Arcosian", "Racial Skill Level " + i + " TP Cost", recommendedTPCost).setMaxValue(2000000000).setMinValue(1);
            arcosianTPCost.comment = "Arcosian Racial Skill TP Cost (Range: 1-2000000000)";
            DBCAConfig.HArcosianTPCosts[i - 7] = proportionalCost(arcosianTPCost, recommendedTPCost);
            Property arcosianMindCost = config.get("Races.Arcosian", "Racial Skill Level " + i + " Mind Cost", recommendedMindCost).setMaxValue(10000).setMinValue(1);
            arcosianMindCost.comment = "Arcosian Racial Skill Mind Cost (Range: 1-100000)";
            DBCAConfig.HArcosianMindCosts[i - 7] = proportionalCost(arcosianMindCost, recommendedMindCost);
        }
        Property majinMaxRacial = config.get("Races.Majin", "Max Racial Skill", (int)HMajinMaxRacial).setMaxValue(9).setMinValue(1);
        majinMaxRacial.comment = "Majin Max Racial Skill (Range: 1-9)";
        HMajinMaxRacial = (byte)majinMaxRacial.getInt();
        for (int i = 6; i <= 9; ++i) {
            int recommendedTPCost = new int[]{85000, 145000, 250000, 425000}[i - 6];
            int recommendedMindCost = new int[]{25, 35, 50, 70}[i - 6];
            Property majinTPCost = config.get("Races.Majin", "Racial Skill Level " + i + " TP Cost", recommendedTPCost).setMaxValue(2000000000).setMinValue(1);
            majinTPCost.comment = "Majin Racial Skill TP Cost (Range: 1-2000000000)";
            DBCAConfig.HMajinTPCosts[i - 6] = proportionalCost(majinTPCost, recommendedTPCost);
            Property majinMindCost = config.get("Races.Majin", "Racial Skill Level " + i + " Mind Cost", recommendedMindCost).setMaxValue(10000).setMinValue(1);
            majinMindCost.comment = "Majin Racial Skill Mind Cost (Range: 1-100000)";
            DBCAConfig.HMajinMindCosts[i - 6] = proportionalCost(majinMindCost, recommendedMindCost);
        }
        Property namekianMaxRacial = config.get("Races.Namekian", "Max Racial Skill", (int)HNamekianMaxRacial).setMaxValue(9).setMinValue(1);
        namekianMaxRacial.comment = "Namekian Max Racial Skill (Range: 1-9)";
        HNamekianMaxRacial = (byte)namekianMaxRacial.getInt();
        for (int i = 6; i <= 9; ++i) {
            int recommendedTPCost = new int[]{90000, 135000, 205000, 310000}[i - 6];
            int recommendedMindCost = new int[]{25, 35, 50, 70}[i - 6];
            Property namekianTPCost = config.get("Races.Namekian", "Racial Skill Level " + i + " TP Cost", recommendedTPCost).setMaxValue(2000000000).setMinValue(1);
            namekianTPCost.comment = "Namekian Racial Skill TP Cost (Range: 1-2000000000)";
            DBCAConfig.HNamekianTPCosts[i - 6] = proportionalCost(namekianTPCost, recommendedTPCost);
            Property namekianMindCost = config.get("Races.Namekian", "Racial Skill Level " + i + " Mind Cost", recommendedMindCost).setMaxValue(10000).setMinValue(1);
            namekianMindCost.comment = "Namekian Racial Skill Mind Cost (Range: 1-100000)";
            DBCAConfig.HNamekianMindCosts[i - 6] = proportionalCost(namekianMindCost, recommendedMindCost);
        }
        for (DBCARace race : DBCARaces.HOST_RACES) {
            Property baseMultiplier = config.get("Races." + race.getName(), "Base Multiplier", race.getBaseMultiplier()).setMaxValue(1000000).setMinValue(1);
            baseMultiplier.comment = race.getName() + " Base Multiplier (Default: " + race.getBaseMultiplier() + ") (Range: 1 to 1000000)";
            race.setBaseMultiplier(baseMultiplier.getDouble());
            if (race == DBCARaces.BIO_ANDROID) {
                Property absorbTp = config.get("Races." + race.getName(), "Absorb TP Multiplier", AbsorbTPMultiplier).setMaxValue(10000).setMinValue(0.1);
                absorbTp.comment = race.getName() + " Absorb TP Multiplier on Entity killed (Calc: totalAbsorptionDamage / 3 * multiplier) (Default: " + AbsorbTPMultiplier + ") (Range: 0.1 to 10000)";
                AbsorbTPMultiplier = absorbTp.getDouble();
                Property absorbTime = config.get("Races." + race.getName(), "Absorb Time Limit (seconds)", AbsorbTimeLimit).setMaxValue(10000).setMinValue(1);
                absorbTime.comment = race.getName() + " Absorption Time Limit (Default: " + AbsorbTimeLimit + " seconds)";
                AbsorbTimeLimit = absorbTime.getDouble();
                Property absorbMaxDamage = config.get("Races." + race.getName(), "Absorb Max Damage Received (Percentage of Max HP)", AbsorbMaxDamage).setMaxValue(100).setMinValue(1);
                absorbMaxDamage.comment = race.getName() + " Absorption Max Damage that the Bio Android can handle before absorption stops. (Default: " + AbsorbMaxDamage + " %) (Range: 0-100)";
                AbsorbMaxDamage = absorbMaxDamage.getDouble();
            }
            int raceMax = 5;
            Property raceMaxRacial = config.get("Races." + race.getName(), "Max Racial Skill", raceMax).setMaxValue(9).setMinValue(1);
            raceMaxRacial.comment = race.getName() + " Max Racial Skill (Range: 1-9)";
            raceMax = (byte)raceMaxRacial.getInt();
            race.MaxRacial = (byte)raceMax;
            int[] newTPCosts = new int[raceMax];
            int[] tpCosts = race.getTPCosts();
            for (int i = 0; i < 9; ++i) {
                int defTPCost = tpCosts.length > i ? tpCosts[i] : 0;
                Property tpCost = config.get("Races." + race.getName(), "TP Cost Form" + (i + 1), defTPCost).setMaxValue(2000000000).setMinValue(1);
                tpCost.comment = race.getName() + " Form " + (i + 1) + " TP Cost (Default: " + defTPCost + ") (Range: 1 to 2000000000)";
                if (newTPCosts.length <= i) continue;
                newTPCosts[i] = nonFree(tpCost);
            }
            int[] newMindCosts = new int[raceMax];
            int[] mindCosts = race.getMindCosts();
            for (int i = 0; i < 9; ++i) {
                int defMindCost = mindCosts.length > i ? mindCosts[i] : 0;
                Property mindCost = config.get("Races." + race.getName(), "Mind Cost Form" + (i + 1), defMindCost).setMaxValue(10000).setMinValue(1);
                mindCost.comment = race.getName() + " Form " + (i + 1) + " Mind Cost (Default: " + defMindCost + ") (Range: 1 to 10000)";
                if (newMindCosts.length <= i) continue;
                newMindCosts[i] = nonFree(mindCost);
            }
            race.setMindCosts(newMindCosts);
            race.setTPCosts(newTPCosts);
        }
        for (DBCAForm form : DBCAForms.HOST_FORMS) {
            name = form.getName();
            Property formEnabled = config.get("Transformations." + name, "Form Enabled", form.isEnabled());
            formEnabled.comment = "Is " + name + " Form Enabled? (Default: " + form.isEnabled() + ")";
            form.setEnabled(formEnabled.getBoolean());
            Property formMultiplier = config.get("Transformations." + name, "Form Base Multiplier", (double)form.getMultiplier()).setMaxValue(1000000).setMinValue(1);
            formMultiplier.comment = name + " Form Multiplier (Default: " + form.getMultiplier() + ") (Range: 1 to 1000000)";
            form.setMultiplier(formMultiplier.getDouble());
            Property formKiDrain = config.get("Transformations." + name, "Ki Drain", (double)form.getKiDrain()).setMaxValue(0).setMinValue(-10);
            Property formHealthDrain = config.get("Transformations." + name, "Kaioken Health Drain", (double)form.getHealthDrainKaio()).setMaxValue(50).setMinValue(0);
            formKiDrain.comment = name + " Form Ki Drain (expressed in negative values, lower the value, higher the drain). -1 equals to SSJ3 Ki Drain. (Default: " + form.getKiDrain() + ") (Range: 0 to -10)";
            formHealthDrain.comment = name + " Kaioken Health Drain Multiplier. (Base health drain multiplied by this number). (Default: " + form.getHealthDrainKaio() + ") (Range: 0 to 50)";
            form.setKiDrainPercentage(formKiDrain.getDouble());
            form.setHealthDrainKaio(formHealthDrain.getDouble());
            Property formMasteryMaxLevel = config.get("Transformations." + name + ".Mastery", "Max Level", form.MasteryData.maxLevel).setMaxValue(500).setMinValue(0);
            formMasteryMaxLevel.comment = "Max Form Mastery Level (Default: 50) (Range: 0-500)";
            Property formMasteryInstantTransformLevel = config.get("Transformations." + name + ".Mastery", "Instant Transform Level", form.MasteryData.instantTransformLevel).setMaxValue(500).setMinValue(0);
            formMasteryInstantTransformLevel.comment = "Instant Transform Mastery Level. -1 equals to Instant Transform Disabled. (Default: -1) (Range: 0-500)";
            Property formMasteryAttributeMultiplier = config.get("Transformations." + name + ".Mastery", "Attribute Multiplier", form.MasteryData.attributeMultiplier).setMaxValue(100).setMinValue(0);
            formMasteryAttributeMultiplier.comment = "Attribute Multiplier per Form Mastery Level (Default: 0.01) (Range: 0-100)";
            Property formMasteryKiDrain = config.get("Transformations." + name + ".Mastery", "Ki Drain Reduction", form.MasteryData.kiDrainMultiplier);
            formMasteryKiDrain.comment = "Ki Drain Reduction per Form Mastery Level. 0 equals to no reduction. Calc: drain-(drain*fm_level*fm_reduction)(Default: 0.005)";
            Property formMasteryHpDrain = config.get("Transformations." + name + ".Mastery", "Health Drain Reduction", form.MasteryData.healthDrainMultiplier);
            formMasteryHpDrain.comment = "Health Drain Reduction per Form Mastery Level. 0 equals to no reduction. Calc: drain-(drain*fm_level*fm_reduction) (Default: 0.005)";
            Property kaiokenStackable = config.get("Transformations." + name + ".Mastery", "Kaioken Stackable", true);
            kaiokenStackable.comment = "Can Form be used with Kaioken?. This requires Kaioken Sustainable Super enabled in the DBC config. (Default: true)";
            form.MasteryData.maxLevel = formMasteryMaxLevel.getDouble();
            form.MasteryData.instantTransformLevel = formMasteryInstantTransformLevel.getDouble();
            form.MasteryData.attributeMultiplier = formMasteryAttributeMultiplier.getDouble();
            form.MasteryData.kiDrainMultiplier = formMasteryKiDrain.getDouble();
            form.MasteryData.healthDrainMultiplier = formMasteryHpDrain.getDouble();
            form.setKaiokenStackable(kaiokenStackable.getBoolean());
        }
        for (DBCASkill skill : DBCASkills.HOST_SKILLS) {
            name = skill.getName();
            int recommendedTPCost = recommendedSkillTPCost(skill);
            int recommendedMindCost = recommendedSkillMindCost(skill);
            Property tpCost = config.get("Skills." + name, "TP Cost", recommendedTPCost).setMaxValue(2000000000).setMinValue(1);
            Property mindCost = config.get("Skills." + name, "Mind Cost", recommendedMindCost).setMaxValue(100000).setMinValue(1);
            Property maxLevel = config.get("Skills." + name, "Max Level", skill.getMaxLevel()).setMaxValue(10).setMinValue(1);
            tpCost.comment = name + " Skill TP Cost (Range 1-2000000000)";
            mindCost.comment = name + " Skill Mind Cost (Range 1-100000)";
            maxLevel.comment = name + " Max Level (Range 1-10)";
            skill.setMaxLevel(maxLevel.getInt());
            skill.setTPCost(Math.max(recommendedTPCost, nonFree(tpCost)));
            skill.setMindCost(Math.max(recommendedMindCost, nonFree(mindCost)));
        }
        SaiyanMaxRacial = HSaiyanMaxRacial;
        HumanMaxRacial = HHumanMaxRacial;
        MajinMaxRacial = HMajinMaxRacial;
        NamekianMaxRacial = HNamekianMaxRacial;
        ArcosianMaxRacial = HArcosianMaxRacial;
        SaiyanTPCosts = HSaiyanTPCosts;
        HumanTPCosts = HHumanTPCosts;
        ArcosianTPCosts = HArcosianTPCosts;
        NamekianTPCosts = HNamekianTPCosts;
        MajinTPCosts = HMajinTPCosts;
        SaiyanMindCosts = HSaiyanMindCosts;
        HumanMindCosts = HHumanMindCosts;
        ArcosianMindCosts = HArcosianMindCosts;
        NamekianMindCosts = HNamekianMindCosts;
        MajinMindCosts = HMajinMindCosts;
        this.loadSkills(skillsPath);
        this.loadCustomForms(customFormsPath);
        DBCASkills.Beast.setEnabled(DBCAForms.Beast.isEnabled());
        DBCASkills.NamekianPotential.setEnabled(DBCAForms.NamekianPotential.isEnabled() || DBCAForms.Orange.isEnabled());
        DBCASkills.ArcosianPotential.setEnabled(DBCAForms.Black.isEnabled());
        config.save();
    }

    private static int nonFree(Property property) {
        return Math.max(1, property.getInt());
    }

    private static int proportionalCost(Property property, int recommended) {
        int configured = property.getInt();
        return configured <= 1 ? recommended : configured;
    }

    private static int recommendedSkillTPCost(DBCASkill skill) {
        if (skill == DBCASkills.NamekianPotential) {
            return 40000;
        }
        if (skill == DBCASkills.Beast) {
            return 75000;
        }
        if (skill == DBCASkills.ArcosianPotential) {
            return 100000;
        }
        return Math.max(1, skill.getTPCost());
    }

    private static int recommendedSkillMindCost(DBCASkill skill) {
        if (skill == DBCASkills.NamekianPotential) {
            return 25;
        }
        if (skill == DBCASkills.Beast) {
            return 10;
        }
        if (skill == DBCASkills.ArcosianPotential) {
            return 50;
        }
        return Math.max(1, skill.getMindCost());
    }

    private void loadSkills(String path) {
        String[] skillsArr;
        Configuration config = new Configuration(new File(path));
        config.load();
        Property skills = config.get("Custom Skills", "Skill List", new String[]{""});
        skills.comment = "Format: (name,displayName,description,maxLevel,tpCost,mindCost,master,races). \nMasters: NONE, GOKU, GOHAN, PICCOLO, WHIS, OLD_KAI, BEERUS, KAIO, VEGETA, TRUNKS, FRIEZA, ROSHI\nRaces: ALL, SAI, HSAI, FRI, HUM, NAM, MAJ, BIO. (Format: race1_race2_race3) (Example: SAI_HUM_FRI)\nIMPORTANT! The name MUST not have spaces or any special character!";
        for (String skill : skillsArr = skills.getStringList()) {
            String races;
            String[] raceArr;
            String[] fields = skill.split(",");
            if (fields.length < 6) {
                continue;
            }
            String name = fields[0];
            String displayName = fields[1];
            String description = fields[2];
            int maxLevel;
            int tpCost;
            int mindCost;
            try {
                maxLevel = Integer.parseInt(fields[3]);
                tpCost = Integer.parseInt(fields[4]);
                mindCost = Integer.parseInt(fields[5]);
            }
            catch (NumberFormatException e) {
                continue;
            }
            DBCASkill skillObj = new DBCASkill(name, displayName, maxLevel);
            skillObj.setDescription(description);
            skillObj.setTPCost(Math.max(1, tpCost));
            skillObj.setMindCost(Math.max(1, mindCost));
            if (fields.length > 6) {
                String master;
                switch (master = fields[6]) {
                    case "NONE": {
                        skillObj.SkillMaster = DBCASkill.Master.NONE;
                        break;
                    }
                    case "GOKU": {
                        skillObj.SkillMaster = DBCASkill.Master.GOKU;
                        break;
                    }
                    case "GOHAN": {
                        skillObj.SkillMaster = DBCASkill.Master.GOHAN;
                        break;
                    }
                    case "PICCOLO": {
                        skillObj.SkillMaster = DBCASkill.Master.PICCOLO;
                        break;
                    }
                    case "WHIS": {
                        skillObj.SkillMaster = DBCASkill.Master.WHIS;
                        break;
                    }
                    case "OLD_KAI": {
                        skillObj.SkillMaster = DBCASkill.Master.OLD_KAI;
                        break;
                    }
                    case "BEERUS": {
                        skillObj.SkillMaster = DBCASkill.Master.BEERUS;
                        break;
                    }
                    case "KAIO": {
                        skillObj.SkillMaster = DBCASkill.Master.KAIO;
                        break;
                    }
                    case "VEGETA": {
                        skillObj.SkillMaster = DBCASkill.Master.VEGETA;
                        break;
                    }
                    case "TRUNKS": {
                        skillObj.SkillMaster = DBCASkill.Master.TRUNKS;
                        break;
                    }
                    case "FRIEZA": {
                        skillObj.SkillMaster = DBCASkill.Master.FRIEZA;
                        break;
                    }
                    case "ROSHI": {
                        skillObj.SkillMaster = DBCASkill.Master.ROSHI;
                    }
                }
            }
            if (fields.length > 7 && !Arrays.asList(raceArr = (races = fields[7]).split("_")).contains("ALL")) {
                HashSet<Byte> raceBytes = new HashSet<Byte>();
                block47: for (String race : raceArr) {
                    switch (race.toUpperCase()) {
                        case "SAI": {
                            raceBytes.add((byte)1);
                            continue block47;
                        }
                        case "HSAI": {
                            raceBytes.add((byte)2);
                            continue block47;
                        }
                        case "HUM": {
                            raceBytes.add((byte)0);
                            continue block47;
                        }
                        case "NAM": {
                            raceBytes.add((byte)3);
                            continue block47;
                        }
                        case "FRI": {
                            raceBytes.add((byte)4);
                            continue block47;
                        }
                        case "MAJ": {
                            raceBytes.add((byte)5);
                            continue block47;
                        }
                        case "BIO": {
                            raceBytes.add((byte)(DBCARaces.BIO_ANDROID.ID + 10));
                        }
                    }
                }
                skillObj.races = ArrayUtils.toPrimitive(raceBytes.toArray(new Byte[0]));
            }
            DBCASkills.HOST_SKILLS.add(skillObj);
        }
        config.save();
    }

    private void loadCustomForms(String path) {
        File[] files;
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!directory.isDirectory()) {
            return;
        }
        files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (!file.getName().endsWith(".cfg")) continue;
            this.loadForm(new Configuration(file));
        }
    }

    private void loadForm(Configuration config) {
        config.load();
        Property displayName = config.get("Form.Data", "Form Display Name", "Super Example");
        displayName.comment = "Custom Form Display Name";
        Property formMenuName = config.get("Form.Data", "Form Menu Display Name", displayName.getString());
        formMenuName.comment = "Custom Form Menu Display Name (Form Selection Menu Name) (Empty = Not visible in Form Selection Menu)";
        Property colorCode = config.get("Form.Data", "Form Color Code", "8");
        colorCode.comment = "Custom Form Color Code (used for the Display Name in the Stat Sheet) - https://htmlcolorcodes.com/minecraft-color-codes/";
        Property name = config.get("Form.Data", "Form Name", "example");
        name.comment = "Custom Form Name (DO NOT USE ANY SPACE OR SPECIAL CHARACTER, THIS IS THE NAME THAT IS GOING TO BE USED IN COMMANDS)";
        Property nextForm = config.get("Form.Data", "Next Form Name", "");
        nextForm.comment = "Next Custom Form Name (Empty = No Next Form)";
        Property multiplier = config.get("Form.Data", "Form Multiplier", 1.0).setMinValue(1.0).setMaxValue(20000.0);
        multiplier.comment = "Custom Form Multiplier (Range 1-20000)";
        Property kiDrainPercentage = config.get("Form.Data", "Form Ki Drain", -1.0).setMinValue(-10.0).setMaxValue(0.0);
        kiDrainPercentage.comment = "Ki Drain (expressed in negative values, lower the value, higher the drain). -1 equals to SSJ3 Ki Drain. (Range: 0 to -10)";
        Property healthDrainPercentage = config.get("Form.Data", "Form Health Drain", 0.0).setMinValue(0.0).setMaxValue(100);
        healthDrainPercentage.comment = "Health Drain Percentage. Every second (Max HP * 100 / Form Health Drain) health is drained. (Range: 0 to 100)";
        Property kaioHealthDrainPercentage = config.get("Form.Data", "Form Kaioken Health Drain", 1.0).setMinValue(1.0).setMaxValue(100);
        kaioHealthDrainPercentage.comment = "Kaioken Health Drain Multiplier. (Base health drain multiplied by this number). (Range: 0 to 50)";
        Property racialLevel = config.get("Form.Data", "Form Racial Skill", 5).setMinValue(1).setMaxValue(10);
        racialLevel.comment = "Min Racial Level to transform";
        Property godSkillLevel = config.get("Form.Data", "Form God Skill Level", 0).setMinValue(0).setMaxValue(10);
        godSkillLevel.comment = "Min God Skill Level to transform";
        Property kaioSkillLevel = config.get("Form.Data", "Form Kaioken Skill Level", 0).setMinValue(0).setMaxValue(10);
        kaioSkillLevel.comment = "Min Kaioken Skill Level to transform";
        Property mysticSkillLevel = config.get("Form.Data", "Form Mystic Skill Level", 0).setMinValue(0).setMaxValue(10);
        mysticSkillLevel.comment = "Min Mystic Skill Level to transform";
        Property uiSkillLevel = config.get("Form.Data", "Form Ultra Instinct Skill Level", 0).setMinValue(0).setMaxValue(10);
        uiSkillLevel.comment = "Min Ultra Instinct Skill Level to transform";
        Property ueSkillLevel = config.get("Form.Data", "Form God of Destruction Skill Level", 0).setMinValue(0).setMaxValue(10);
        ueSkillLevel.comment = "Min G.O.D Skill Level to transform";
        Property customSkill = config.get("Form.Data", "Required Custom Skill Name", "").setMinValue(1).setMaxValue(10);
        customSkill.comment = "Required Custom Skill to transform (Empty = No Custom Skill)";
        Property customSkillLevel = config.get("Form.Data", "Required Custom Skill Level", -1).setMinValue(1).setMaxValue(10);
        customSkillLevel.comment = "Min Custom Skill Level to transform";
        Property races = config.get("Form.Data", "Form Races", "sai,hsai,fri,hum,nam,maj,bio");
        races.comment = "Custom Form Races Values: (sai, hsai, fri, hum, nam, maj, bio)";
        Property kaioStackable = config.get("Form.Data", "Is Kaioken Stackable?", false);
        kaioStackable.comment = "Can this form be used with Kaioken? (Kaioken Sustainable Super must be enabled in DBC config!)";
        Property masteryMaxLevel = config.get("Form.Data.Mastery", "Max Level", 50.0);
        masteryMaxLevel.comment = "Form Mastery Max Level";
        Property instantTransformLevel = config.get("Form.Data.Mastery", "Instant Transform Level", -1);
        instantTransformLevel.comment = "Form Mastery Instant Transform Level (-1 = Disabled Instant Transform)";
        Property attributeMultiplier = config.get("Form.Data.Mastery", "Attribute Multiplier", 0.01);
        attributeMultiplier.comment = "Form Mastery Attribute Multiplier";
        Property kiDrainMultiplier = config.get("Form.Data.Mastery", "Ki Drain Multiplier", 0.005);
        kiDrainMultiplier.comment = "Form Mastery Ki Drain Multiplier";
        Property healthDrainMultiplier = config.get("Form.Data.Mastery", "Health Drain Multiplier", 0.005);
        healthDrainMultiplier.comment = "Form Mastery Health Drain Multiplier (Kaioken and Normal Drain)";
        FormMasteryData masterydata = new FormMasteryData();
        masterydata.maxLevel = masteryMaxLevel.getDouble();
        masterydata.instantTransformLevel = instantTransformLevel.getDouble();
        masterydata.attributeMultiplier = attributeMultiplier.getDouble();
        masterydata.kiDrainMultiplier = kiDrainMultiplier.getDouble();
        masterydata.healthDrainMultiplier = healthDrainMultiplier.getDouble();
        Property arcosianState = config.get("Form.Display", "Custom Form State Render", 1);
        arcosianState.comment = "Custom Form Render State (Only applies to Arcosians and Bio Android)";
        Property tattoo = config.get("Form.Display", "Tattoos", "0");
        tattoo.comment = "Custom Form Tattoos - Separate by commas every tattoo id (0 = None, 1 = Namekian Orange, 2 = G.O.D, 3 = Omni, 4 = Infinity, 5 = Sukuna, 6 = Dragon Balls, 7 = Daima SSJ4 Fur)";
        Property tattooColor = config.get("Form.Display", "Tattoo Color", -1);
        tattooColor.comment = "Custom Form Tattoo Color (Decimal Color)";
        Property bodyColors = config.get("Form.Display", "Body Colors", "-1,-1,-1,-1");
        bodyColors.comment = "Custom Form Body Colors value: bodycm,bodyc1,bodyc2,bodyc3 (-1 = Default)";
        Property hairColor = config.get("Form.Display", "Hair Color", -1);
        hairColor.comment = "Custom Form Hair Color (Decimal Color) (-1 = Disabled)";
        Property eyeColor = config.get("Form.Display", "Eye Color", -1);
        eyeColor.comment = "Custom Form Eye Color (Decimal Color) (-1 = Disabled)";
        Property barColor = config.get("Form.Display", "Bar Color", -1);
        barColor.comment = "Custom Form Bar Color (Decimal Color) (-1 = Disabled)";
        Property furColor = config.get("Form.Display", "Fur Color", -1);
        furColor.comment = "Custom Form Fur Color (Decimal Color) (-1 = No Fur)";
        Property tailColor = config.get("Form.Display", "Tail Color", -1);
        tailColor.comment = "Custom Form Tail Color (Decimal Color) (-1 = Hair Color)";
        Property hairType = config.get("Form.Display", "Hair Type", "SS1");
        hairType.comment = "Form Hair Type (BASE, SS1, SS2, SS3, SS4, CUSTOM)";
        Property customHair = config.get("Form.Display", "Custom Hair DNS", "");
        customHair.comment = "Custom Hair DNS (change if Hair Type is CUSTOM)";
        Property eyeBrow = config.get("Form.Display", "Eye Brow", true);
        eyeBrow.comment = "Has Eye Brow?";
        Property bodyType = config.get("Form.Display", "Body Type", -1);
        bodyType.comment = "Custom Form Body Type (Range: -1  to 2) - Only works for Namekians and Arcosians (-1 equals to keep bodytype)";
        Property berserk = config.get("Form.Display", "Berserk", false);
        berserk.comment = "Is Form Berserk?";
        Property size = config.get("Form.Display", "Custom Form Size Multiplier", 1.0);
        size.comment = "Custom Form Size Multiplier";
        Property bulk = config.get("Form.Display", "Custom Form Bulk Multiplier", 1.0);
        bulk.comment = "Custom Form Bulk Multiplier";
        config.getCategory("form.display").setComment("https://www.mathsisfun.com/hexadecimal-decimal-colors.html - Copy the Decimal color and use it here!");
        DBCAForm form = new DBCAForm(name.getString(), (float)multiplier.getDouble(), hairColor.getInt(), eyeColor.getInt(), barColor.getInt());
        if (!hairType.getString().isEmpty()) {
            form.setHairType(HairType.valueOf(hairType.getString()));
        }
        String[] clrs = bodyColors.getString().split(",");
        form.getColors().setBodyColors(Integer.parseInt(clrs[0]), Integer.parseInt(clrs[1]), Integer.parseInt(clrs[2]), Integer.parseInt(clrs[3]));
        form.setKiDrainPercentage(kiDrainPercentage.getDouble());
        form.setHealthDrainKaio(kaioHealthDrainPercentage.getDouble());
        form.setHealthDrain(healthDrainPercentage.getDouble());
        form.DisplayName = displayName.getString();
        form.setBodyType(bodyType.getInt());
        form.setStateRender(arcosianState.getInt());
        ArrayList<Tattoo> tattoos = new ArrayList<Tattoo>();
        String t = tattoo.getString();
        if (!t.isEmpty()) {
            String[] t1 = t.split(",");
            if (t1.length > 0) {
                for (String t2 : t1) {
                    tattoos.add(Tattoo.values()[Integer.parseInt(t2)]);
                }
            } else {
                tattoos.add(Tattoo.values()[Integer.parseInt(t)]);
            }
        }
        form.setTattoos(tattoos);
        form.getColors().setTattooColor(tattooColor.getInt());
        form.setColorCode(colorCode.getString().charAt(0));
        form.setKaiokenStackable(kaioStackable.getBoolean());
        form.setMasteryData(masterydata);
        form.setBerserk(berserk.getBoolean());
        form.setEyeBrow(eyeBrow.getBoolean());
        if (!nextForm.getString().isEmpty()) {
            form.setNextForm(nextForm.getString());
        }
        form.getColors().setFurColor(furColor.getInt());
        int tailColorI = tailColor.getInt();
        if (tailColorI != -1) {
            form.getColors().setTailColor(tailColorI);
        }
        form.setCustomHair(customHair.getString());
        form.setSizeMultiplier((float)size.getDouble());
        form.setBulkMultiplier((float)bulk.getDouble());
        ArrayList<Byte> raceList = new ArrayList<Byte>();
        for (String race : races.getString().split(",")) {
            if (race.equalsIgnoreCase("sai")) {
                raceList.add((byte)1);
            }
            if (race.equalsIgnoreCase("hsai")) {
                raceList.add((byte)2);
            }
            if (race.equalsIgnoreCase("hum")) {
                raceList.add((byte)0);
            }
            if (race.equalsIgnoreCase("nam")) {
                raceList.add((byte)3);
            }
            if (race.equalsIgnoreCase("fri")) {
                raceList.add((byte)4);
            }
            if (race.equalsIgnoreCase("maj")) {
                raceList.add((byte)5);
            }
            if (!race.equalsIgnoreCase("bio")) continue;
            raceList.add((byte)(DBCARaces.BIO_ANDROID.ID + 10));
        }
        byte[] raceArr = new byte[raceList.size()];
        for (int i = 0; i < raceList.size(); ++i) {
            raceArr[i] = (Byte)raceList.get(i);
        }
        FormItem formItem = new FormItem(formMenuName.getString(), raceArr, racialLevel.getInt(), godSkillLevel.getInt() > 0 ? godSkillLevel.getInt() - 1 : -1);
        formItem.setCustomForm(form.getID());
        formItem.setSkillLevels(kaioSkillLevel.getInt(), mysticSkillLevel.getInt(), uiSkillLevel.getInt(), ueSkillLevel.getInt());
        DBCASkill skill = DBCASkills.getSkillHost(customSkill.getString());
        int skillLevel = customSkillLevel.getInt();
        if (skill != null && skillLevel > 0) {
            formItem.addRequiredCustomSkill(skill, skillLevel);
        }
        FormItemsDBA.FormItemsHost.add(formItem);
        Property legendaryHairColor = config.get("Form.Status Effects.Legendary", "Hair Color", -1);
        legendaryHairColor.comment = "Legendary Status Effect Form Hair Color (-1 equals to No Changes)";
        Property legendaryEyeColor = config.get("Form.Status Effects.Legendary", "Eye Color", -1);
        legendaryEyeColor.comment = "Legendary Status Effect Form Eye Color (-1 equals to No Changes)";
        Property legendaryKiColor = config.get("Form.Status Effects.Legendary", "Ki Color", -1);
        legendaryKiColor.comment = "Legendary Status Effect Form Ki Color (-1 equals to No Changes)";
        Property legendaryBarColor = config.get("Form.Status Effects.Legendary", "Bar Color", -1);
        legendaryBarColor.comment = "Legendary Status Effect Form Bar Color (-1 equals to No Changes)";
        Property legendaryFurColor = config.get("Form.Status Effects.Legendary", "Fur Color", -1);
        legendaryFurColor.comment = "Legendary Status Effect Form Fur Color (-1 equals to No Changes)";
        Property legendaryTailColor = config.get("Form.Status Effects.Legendary", "Tail Color", -1);
        legendaryTailColor.comment = "Legendary Status Effect Form Tail Color (-1 equals to No Changes)";
        Property legendaryTattooColor = config.get("Form.Status Effects.Legendary", "Tattoo Color", -1);
        legendaryTattooColor.comment = "Legendary Status Effect Form Tattoo Color (-1 equals to No Changes)";
        Property legendaryBodyColors = config.get("Form.Status Effects.Legendary", "Body Colors", "-1,-1,-1,-1");
        legendaryBodyColors.comment = "Legendary Status Effect Form Body Colors (-1 equals to No Changes)";
        clrs = legendaryBodyColors.getString().split(",");
        int[] legendaryBodyCol = new int[]{Integer.parseInt(clrs[0]), Integer.parseInt(clrs[1]), Integer.parseInt(clrs[2]), Integer.parseInt(clrs[3])};
        form.setLegendaryColors(legendaryHairColor.getInt(), legendaryEyeColor.getInt(), legendaryKiColor.getInt(), legendaryBarColor.getInt(), legendaryFurColor.getInt(), legendaryTailColor.getInt(), legendaryTattooColor.getInt(), legendaryBodyCol);
        Property divineHairColor = config.get("Form.Status Effects.Divine", "Hair Color", -1);
        divineHairColor.comment = "Divine Status Effect Form Hair Color (-1 equals to No Changes)";
        Property divineEyeColor = config.get("Form.Status Effects.Divine", "Eye Color", -1);
        divineEyeColor.comment = "Divine Status Effect Form Eye Color (-1 equals to No Changes)";
        Property divineKiColor = config.get("Form.Status Effects.Divine", "Ki Color", -1);
        divineKiColor.comment = "Divine Status Effect Form Ki Color (-1 equals to No Changes)";
        Property divineBarColor = config.get("Form.Status Effects.Divine", "Bar Color", -1);
        divineBarColor.comment = "Divine Status Effect Form Bar Color (-1 equals to No Changes)";
        Property divineFurColor = config.get("Form.Status Effects.Divine", "Fur Color", -1);
        divineFurColor.comment = "Divine Status Effect Form Fur Color (-1 equals to No Changes)";
        Property divineTailColor = config.get("Form.Status Effects.Divine", "Tail Color", -1);
        divineTailColor.comment = "Divine Status Effect Form Tail Color (-1 equals to No Changes)";
        Property divineTattooColor = config.get("Form.Status Effects.Divine", "Tattoo Color", -1);
        divineTattooColor.comment = "Divine Status Effect Form Tattoo Color (-1 equals to No Changes)";
        Property divineBodyColors = config.get("Form.Status Effects.Divine", "Body Colors", "-1,-1,-1,-1");
        divineBodyColors.comment = "Divine Status Effect Form Body Colors (-1 equals to No Changes)";
        clrs = divineBodyColors.getString().split(",");
        int[] divineBodyCol = new int[]{Integer.parseInt(clrs[0]), Integer.parseInt(clrs[1]), Integer.parseInt(clrs[2]), Integer.parseInt(clrs[3])};
        form.setDivineColors(divineHairColor.getInt(), divineEyeColor.getInt(), divineKiColor.getInt(), divineBarColor.getInt(), divineFurColor.getInt(), divineTailColor.getInt(), divineTattooColor.getInt(), divineBodyCol);
        for (int i = 1; i <= 5; ++i) {
            String auraType;
            Property auraEnabled = config.get("Form.Display.Aura" + i, "Aura Enabled", i <= 1);
            auraEnabled.comment = "Is Aura" + i + " Enabled?";
            if (!auraEnabled.getBoolean()) continue;
            CustomAura aura = new CustomAura();
            Property aura2D = config.get("Form.Display.Aura" + i, "2D Aura", -1);
            aura2D.comment = "2D Aura Type (-1 = Disabled, 0 = God Aura, SSB = 1, SSBE = 2, SSR = 3, Golden = 4, SSRE = 5, 6 = G.O.D, 7 = U.I, 8 = M.U.I)";
            int aura2DI = aura2D.getInt();
            if (aura2DI < 7) {
                aura.setBol6(aura2DI);
            } else if (aura2DI == 7) {
                aura.setBol4(true, false);
            } else if (aura2DI == 8) {
                aura.setBol4(true, true);
            }
            Property auraTypeProperty = config.get("Form.Display.Aura" + i, "3D Aura", "base");
            auraTypeProperty.comment = "3D Aura Type (base, ssg, ssb, ui, gd, golden)";
            Property primaryColorProperty = config.get("Form.Display.Aura" + i, "Primary Color", -1);
            primaryColorProperty.comment = "Primary Color (Decimal Color)";
            Property secondaryColorProperty = config.get("Form.Display.Aura" + i, "Secondary Color", -1);
            secondaryColorProperty.comment = "Secondary Color (Decimal Color)";
            Property auraSpeed = config.get("Form.Display.Aura" + i, "Aura Speed", 20);
            auraSpeed.comment = "Aura Speed (lower the value, faster the aura)";
            Property auraSize = config.get("Form.Display.Aura" + i, "Aura Size", -1);
            auraSize.comment = "Aura Size (-1 = Default)";
            Property hasLightning = config.get("Form.Display.Aura" + i, "Has Lightning", false);
            hasLightning.comment = "Aura Lightning";
            Property lightningColor = config.get("Form.Display.Aura" + i, "Lightning Color", "255,255,255,100");
            lightningColor.comment = "Aura Lightning Color (r,g,b,a) (0-255)";
            if (hasLightning.getBoolean()) {
                String[] lightningStr = lightningColor.getString().split(",");
                float r = Float.parseFloat(lightningStr[0]) / 255.0f;
                float g = Float.parseFloat(lightningStr[1]) / 255.0f;
                float b = Float.parseFloat(lightningStr[2]) / 255.0f;
                float a = Float.parseFloat(lightningStr[3]) / 255.0f;
                aura.setLightning(r, g, b, a);
            }
            int primaryColor = primaryColorProperty.getInt();
            int secondaryColor = secondaryColorProperty.getInt();
            aura.setSpd(auraSpeed.getInt());
            if (auraSize.getInt() != -1) {
                aura.setState(auraSize.getInt());
            }
            aura.setLegendaryCol(legendaryKiColor.getInt());
            aura.setDivineCol(divineKiColor.getInt());
            switch (auraType = auraTypeProperty.getString()) {
                case "ssg": {
                    aura.setAlp(0.2f);
                    aura.setTexL1("aurai");
                    aura.setTexL2("aurai2");
                    aura.setColL1(primaryColor);
                    aura.setColL2(secondaryColor);
                    break;
                }
                case "ssb": {
                    aura.setSpd(40);
                    aura.setAlp(0.5f);
                    aura.setColL1(primaryColor);
                    aura.setTexL1("aurag");
                    aura.setColL3(secondaryColor);
                    aura.setTexL3("auragb");
                    break;
                }
                case "ui": {
                    aura.setSpd(100);
                    aura.setAlp(0.15f);
                    aura.setTexL1("auras");
                    aura.setColL1(0xF0F0F0);
                    aura.setColL1(primaryColor);
                    aura.setColL3(4746495);
                    aura.setColL3(secondaryColor);
                    aura.setTexL3("auragb");
                    break;
                }
                case "gd": {
                    aura.setSpd(30);
                    aura.setAlp(0.2f);
                    aura.setColL1(primaryColor);
                    aura.setTexL1("aurag");
                    aura.setTexL3("auragb");
                    aura.setColL2(12464847);
                    aura.setColL2(secondaryColor);
                    break;
                }
                case "golden": {
                    aura.setAlp(0.5f);
                    aura.setColL1(primaryColor);
                    aura.setTexL1("aurau");
                    aura.setTexL2("aurau2");
                    aura.setColL2(16776724);
                    aura.setColL2(secondaryColor);
                    break;
                }
                case "base": {
                    aura.setSpd(20);
                    aura.setColL1(primaryColor);
                    aura.setAlp(0.2f);
                }
            }
            form.addAura(aura);
            DBCAForms.registerForm(form);
        }
        config.save();
    }

    public Configuration getConfig() {
        return config;
    }

    static {
        BeerusGriefing = false;
        SpawnCheck = false;
        WhisTeleport = false;
        CustomRaces = true;
        CustomForms = true;
        PotaraMultiplier = 1.0;
        HPotaraMultiplier = 1.0;
        PotaraAmount = 1;
        PotaraCooldown = 120;
        PotaraTime = 20;
        PotaraMinLevel = 500;
        HPotaraMinLevel = 500;
        AbsorbTPMultiplier = 1.0;
        AbsorbMaxDamage = 25.0;
        AbsorbTimeLimit = 15.0;
        HSaiyanMaxRacial = (byte)7;
        HArcosianMaxRacial = (byte)6;
        HHumanMaxRacial = (byte)5;
        HNamekianMaxRacial = (byte)5;
        HMajinMaxRacial = (byte)5;
        SaiyanMaxRacial = (byte)7;
        ArcosianMaxRacial = (byte)6;
        HumanMaxRacial = (byte)5;
        NamekianMaxRacial = (byte)5;
        MajinMaxRacial = (byte)5;
        SaiyanTPCosts = new int[2];
        NamekianTPCosts = new int[4];
        ArcosianTPCosts = new int[3];
        MajinTPCosts = new int[4];
        HumanTPCosts = new int[4];
        SaiyanMindCosts = new int[2];
        NamekianMindCosts = new int[4];
        ArcosianMindCosts = new int[3];
        MajinMindCosts = new int[4];
        HumanMindCosts = new int[4];
        HSaiyanTPCosts = new int[2];
        HNamekianTPCosts = new int[4];
        HArcosianTPCosts = new int[3];
        HMajinTPCosts = new int[4];
        HHumanTPCosts = new int[4];
        HBioAndroidTPCosts = new int[4];
        HSaiyanMindCosts = new int[2];
        HNamekianMindCosts = new int[4];
        HArcosianMindCosts = new int[3];
        HMajinMindCosts = new int[4];
        HHumanMindCosts = new int[4];
        HBioAndroidMindCosts = new int[4];
    }
}
