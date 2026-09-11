/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package hedaox.ninjinentities.config;

import java.io.File;
import java.util.regex.Pattern;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ModConfig {
    public static int kiDrainInterval;
    public static double kiDrainRange;
    public static String kiDrainAmount;
    public static int kiDrainIntervalYoung;
    public static double kiDrainRangeYoung;
    public static String kiDrainAmountYoung;
    public static int kiDrainIntervalFusion;
    public static double kiDrainRangeFusion;
    public static String kiDrainAmountFusion;
    public static double kiToHealthRatio;
    public static String kiToMaxHealthRatio;
    public static String kiToAttackRatio;
    public static double kiToHealthRatioYoung;
    public static String kiToMaxHealthRatioYoung;
    public static String kiToAttackRatioYoung;
    public static double kiToHealthRatioFusion;
    public static String kiToMaxHealthRatioFusion;
    public static String kiToAttackRatioFusion;
    public static double maxHealthEnhancementLimit;
    public static double attackEnhancementLimit;
    public static double gokuUIDodgeRate;
    public static double gokuUICounterChance;
    public static double gokuUIMDodgeRate;
    public static double gokuUIMCounterChance;
    public static double GogetaUIDodgeRate;
    public static double GogetaUICounterChance;
    public static double VegetoUIDodgeRate;
    public static double VegetoUICounterChance;
    public static double WhisDodgeRate;
    public static double WhisCounterChance;
    public static double attackBoostEnergyFactor;
    public static double attackBoostPercentPerHit;
    public static double attackMaxPercent;
    public static boolean useNewFlightAI;

    public static void loadConfig(File configFile) {
        Configuration config = new Configuration(configFile);
        try {
            config.load();
            ModConfig.initConfig(config);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    private static double parsePercentage(String value, double defaultValue) {
        if (value.endsWith("%")) {
            try {
                double percentage = Double.parseDouble(value.substring(0, value.length() - 1));
                return Math.min(percentage, 100.0);
            }
            catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    private static void initConfig(Configuration cfg) {
        cfg.addCustomCategoryComment("ninjinentities-unofficial", "# Ninjin Entities configuration\n");
        Property kiDrainProp = cfg.get("ninjinentities-unofficial", "Ki Drain settings (Interval, Range, Amount) value from 0 to 1000 (defaults: 20, 10, 1%).", new String[]{"20", "10", "1%"}, "Eldmoro-kidrain.\nkiDrainAmount can be a fixed value (e.g., 1) or a percentage (e.g., 1%).");
        kiDrainProp.setValidationPattern(Pattern.compile(".+%?"));
        String[] kiDrainValues = kiDrainProp.getStringList();
        kiDrainInterval = Integer.parseInt(kiDrainValues[0]);
        kiDrainRange = Double.parseDouble(kiDrainValues[1]);
        kiDrainAmount = kiDrainValues[2];
        Property kiDrainPropYoung = cfg.get("ninjinentities-unofficial", "Ki Drain settings (Interval, Range, Amount) value from 0 to 1000 (defaults: 18, 12, 1.5%).", new String[]{"18", "12", "1.5%"}, "moro-kidrain.\nkiDrainAmount can be a fixed value (e.g., 1) or a percentage (e.g., 1%).");
        kiDrainPropYoung.setValidationPattern(Pattern.compile(".+%?"));
        String[] kiDrainValuesYoung = kiDrainPropYoung.getStringList();
        kiDrainIntervalYoung = Integer.parseInt(kiDrainValuesYoung[0]);
        kiDrainRangeYoung = Double.parseDouble(kiDrainValuesYoung[1]);
        kiDrainAmountYoung = kiDrainValuesYoung[2];
        Property kiDrainPropFusion = cfg.get("ninjinentities-unofficial", "Ki Drain settings (Interval, Range, Amount) value from 0 to 1000 (defaults: 15, 16, 2.0%).", new String[]{"15", "16", "2.0%"}, "moroFusion-kidrain.\nkiDrainAmount can be a fixed value (e.g., 1) or a percentage (e.g., 1%).");
        kiDrainPropFusion.setValidationPattern(Pattern.compile(".+%?"));
        String[] kiDrainValuesFusion = kiDrainPropFusion.getStringList();
        kiDrainIntervalFusion = Integer.parseInt(kiDrainValuesFusion[0]);
        kiDrainRangeFusion = Double.parseDouble(kiDrainValuesFusion[1]);
        kiDrainAmountFusion = kiDrainValuesFusion[2];
        Property kiRatioProp = cfg.get("ninjinentities-unofficial", "Ki Conversion settings (Regeneration, MaxHealth, Attack) value from 0 to 1000 (defaults: 1%, 1, 1).", new String[]{"1%", "1", "1"}, "Eldmoro-Ki Conversion settings.\nRegeneration must be a percentage (e.g., 10%). MaxHealth and Attack can be a fixed value (e.g., 0.1) or a percentage (e.g., 10%).");
        String[] kiRatioValues = kiRatioProp.getStringList();
        kiToHealthRatio = ModConfig.parsePercentage(kiRatioValues[0], 0.1);
        kiToMaxHealthRatio = kiRatioValues[1];
        kiToAttackRatio = kiRatioValues[2];
        Property kiRatioPropYoung = cfg.get("ninjinentities-unofficial", "Ki Conversion settings (Regeneration, MaxHealth, Attack) value from 0 to 1000 (defaults: 1%, 1.1, 1.1).", new String[]{"1%", "1.1", "1.1"}, "Youngmoro-Ki Conversion settings.\nRegeneration must be a percentage (e.g., 10%). MaxHealth and Attack can be a fixed value (e.g., 0.1) or a percentage (e.g., 10%).");
        kiRatioPropYoung.setValidationPattern(Pattern.compile(".+%?"));
        String[] kiRatioValuesYoung = kiRatioProp.getStringList();
        kiToHealthRatioYoung = ModConfig.parsePercentage(kiRatioValuesYoung[0], 0.1);
        kiToMaxHealthRatioYoung = kiRatioValuesYoung[1];
        kiToAttackRatioYoung = kiRatioValuesYoung[2];
        Property kiRatioPropFusion = cfg.get("ninjinentities-unofficial", "Ki Conversion settings (Regeneration, MaxHealth, Attack) value from 0 to 1000 (defaults: 1%, 1.15, 1.15).", new String[]{"1%", "1.15", "1.15"}, "moroFusion-Ki Conversion settings.\nRegeneration must be a percentage (e.g., 10%). MaxHealth and Attack can be a fixed value (e.g., 0.1) or a percentage (e.g., 10%).");
        String[] kiRatioValuesFusion = kiRatioProp.getStringList();
        kiToHealthRatioFusion = ModConfig.parsePercentage(kiRatioValuesFusion[0], 0.1);
        kiToMaxHealthRatioFusion = kiRatioValuesFusion[1];
        kiToAttackRatioFusion = kiRatioValuesFusion[2];
        maxHealthEnhancementLimit = cfg.getFloat("maxHealthEnhancementLimit", "ninjinentities-unofficial", 50.0f, 0.0f, 1000.0f, "Maximum percentage by which max health can be enhanced (default: 50%).");
        attackEnhancementLimit = cfg.getFloat("attackEnhancementLimit", "ninjinentities-unofficial", 50.0f, 0.0f, 1000.0f, "Maximum percentage by which attack damage can be enhanced (default: 50%).");
        Property gokuUICombatProp = cfg.get("ninjinentities-unofficial", "gokuUI Combat (DodgeRate CounterChance QuadCounter DecaCounter)", new String[]{"50%", "30%"}, "GokuUI combat settings in percentage (dodge, counter)");
        String[] gokuUIValues = gokuUICombatProp.getStringList();
        gokuUIDodgeRate = ModConfig.parsePercentage(gokuUIValues.length > 0 ? gokuUIValues[0] : "38%", 50.0);
        gokuUICounterChance = ModConfig.parsePercentage(gokuUIValues.length > 1 ? gokuUIValues[1] : "10%", 30.0);
        Property gokuUIMCombatProp = cfg.get("ninjinentities-unofficial", "gokuUIM Combat (DodgeRate CounterChance QuadCounter DecaCounter)", new String[]{"50%", "30%"}, "GokuUIM combat settings in percentage (dodge, counter)");
        String[] gokuUIMValues = gokuUIMCombatProp.getStringList();
        gokuUIMDodgeRate = ModConfig.parsePercentage(gokuUIMValues.length > 0 ? gokuUIMValues[0] : "50%", 50.0);
        gokuUIMCounterChance = ModConfig.parsePercentage(gokuUIMValues.length > 1 ? gokuUIMValues[1] : "30%", 30.0);
        Property GogetaUICombatProp = cfg.get("ninjinentities-unofficial", "GogetaUI Combat (DodgeRate CounterChance QuadCounter DecaCounter)", new String[]{"50%", "30%"}, "GogetaUI combat settings in percentage (dodge, counter)");
        String[] GogetaUIValues = GogetaUICombatProp.getStringList();
        GogetaUIDodgeRate = ModConfig.parsePercentage(GogetaUIValues.length > 0 ? GogetaUIValues[0] : "50%", 50.0);
        GogetaUICounterChance = ModConfig.parsePercentage(GogetaUIValues.length > 1 ? GogetaUIValues[1] : "30%", 30.0);
        Property VegetoUICombatProp = cfg.get("ninjinentities-unofficial", "VegetoUI Combat (DodgeRate CounterChance QuadCounter DecaCounter)", new String[]{"50%", "30%"}, "VegetoUI combat settings in percentage (dodge, counter)");
        String[] VegetoUIValues = VegetoUICombatProp.getStringList();
        VegetoUIDodgeRate = ModConfig.parsePercentage(VegetoUIValues.length > 0 ? VegetoUIValues[0] : "50%", 50.0);
        VegetoUICounterChance = ModConfig.parsePercentage(VegetoUIValues.length > 1 ? VegetoUIValues[1] : "30%", 30.0);
        Property WhisCombatProp = cfg.get("ninjinentities-unofficial", "Whis Combat (DodgeRate CounterChance QuadCounter DecaCounter)", new String[]{"70%", "50%"}, "Whis combat settings in percentage (dodge, counter)");
        String[] WhisValues = WhisCombatProp.getStringList();
        WhisDodgeRate = ModConfig.parsePercentage(WhisValues.length > 0 ? WhisValues[0] : "70%", 70.0);
        WhisCounterChance = ModConfig.parsePercentage(WhisValues.length > 1 ? WhisValues[1] : "50%", 50.0);
        Property attackEnhanceProp = cfg.get("ninjinentities-unofficial", "Super Android 17 ki attack absorption settings (EnergyFactor, BoostPercentPerHit, MaxPercent)", new String[]{"0.1", "5%", "300%"}, "Attack enhancement settings.\nEnergyFactor = how much attack increases per energy damage (e.g., 0.1).\nBoostPercentPerHit = max boost per hit, relative to initial attack (e.g., 5%).\nMaxPercent = maximum attack relative to initial (e.g., 300%).");
        String[] attackEnhanceValues = attackEnhanceProp.getStringList();
        try {
            attackBoostEnergyFactor = Double.parseDouble(attackEnhanceValues[0]);
        }
        catch (Exception e) {
            attackBoostEnergyFactor = 0.1;
        }
        attackBoostPercentPerHit = ModConfig.parsePercentage(attackEnhanceValues.length > 1 ? attackEnhanceValues[1] : "5%", 5.0) / 100.0;
        attackMaxPercent = ModConfig.parsePercentage(attackEnhanceValues.length > 2 ? attackEnhanceValues[2] : "300%", 300.0) / 100.0;
        Property flightAIProp = cfg.get("ninjinentities-unofficial", "Flight AI settings (use New Flight AI)", true, "Switch between new flight AI and old flight AI.\ntrue = use new smooth 3D flight AI\nfalse = use old height-based flight AI");
        useNewFlightAI = flightAIProp.getBoolean(true);
    }
}

