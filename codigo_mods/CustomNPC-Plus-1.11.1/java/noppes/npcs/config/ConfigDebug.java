/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 *  org.apache.logging.log4j.Level
 */
package noppes.npcs.config;

import cpw.mods.fml.common.FMLLog;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import noppes.npcs.CustomNpcs;
import noppes.npcs.config.legacy.LegacyConfig;
import noppes.npcs.constants.EnumScriptType;
import org.apache.logging.log4j.Level;

public class ConfigDebug {
    public static Configuration config;
    public static final String LOGGING = "Logging";
    public static Property PlayerLoggingProperty;
    public static boolean PlayerLogging;
    public static Property ScriptLoggingProperty;
    public static boolean ScriptLogging;
    public static Property ScriptFrequencyProperty;
    public static int ScriptFrequency;
    public static Property ScriptIgnoreTimeProperty;
    public static int ScriptIgnoreTime;
    public static Property ScriptLogIgnoreTypeProperty;
    public static String ScriptLogIgnoreType;
    public static boolean LogActionManager;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void init(File configFile) {
        config = new Configuration(configFile);
        try {
            config.load();
            PlayerLoggingProperty = config.get(LOGGING, "Enable Player Logging", false, "Enables if Player Information (WAND-USE) should be printed to CustomNPCs Logs. IF on Server \nLogs will only be present SERVER-SIDE only in CustomNPCs-latest, -1, -2, and -3");
            PlayerLogging = PlayerLoggingProperty.getBoolean(false);
            ScriptLoggingProperty = config.get(LOGGING, "Enable Script Logging", false, "Enables if Scripting Information should be printed to CustomNPCs Logs. IF on Server \nLogs will only be present SERVER-SIDE only in CustomNPCs-latest, -1, -2, and -3");
            ScriptLogging = ScriptLoggingProperty.getBoolean(false);
            ScriptFrequencyProperty = config.get(LOGGING, "Script Log Frequency Limit", 20, "Amount of Messages marked as SPAM [5, 3000]. Lower Number means MORE accurate messages \nThis frequency will determine if the log will print a line with [SPAM] to warn the console.");
            ScriptFrequency = ScriptFrequencyProperty.getInt(20);
            ScriptIgnoreTimeProperty = config.get(LOGGING, "Script Ignore Time Buffer", 2000, "IN Milliseconds 1s = 1000s. If a recent LOG of the same event is SENT within this threshold it will be ignored.");
            ScriptIgnoreTime = ScriptIgnoreTimeProperty.getInt(2000);
            ScriptLogIgnoreTypeProperty = config.get(LOGGING, "Script Type Ignore", "TICK", "Comma separated list of NPC Script Types that will omit these from the logs,\nINIT,TICK,INTERACT,DIALOG,DAMAGED,KILLED,ATTACK,TARGET,COLLIDE,KILLS,DIALOG_CLOSE,TIMER");
            ScriptLogIgnoreType = ScriptLogIgnoreTypeProperty.getString();
            LogActionManager = config.get(LOGGING, "Log Action Manager", LogActionManager, "Log the Performance of the Action Manager").getBoolean(LogActionManager);
            if (CustomNpcs.legacyExist) {
                PlayerLogging = LegacyConfig.PlayerLogging;
                PlayerLoggingProperty.set(PlayerLogging);
                ScriptLogging = LegacyConfig.ScriptLogging;
                ScriptLoggingProperty.set(ScriptLogging);
                ScriptFrequency = LegacyConfig.ScriptFrequency;
                ScriptFrequencyProperty.set(ScriptFrequency);
                ScriptIgnoreTime = LegacyConfig.ScriptIgnoreTime;
                ScriptIgnoreTimeProperty.set(ScriptIgnoreTime);
                ScriptLogIgnoreType = LegacyConfig.ScriptLogIgnoreType;
                ScriptLogIgnoreTypeProperty.set(ScriptLogIgnoreType);
            }
            if (ScriptFrequency < 5) {
                ScriptFrequency = 5;
            }
            if (ScriptFrequency > 3000) {
                ScriptFrequency = 3000;
            }
            if (ScriptIgnoreTime < 0) {
                ScriptIgnoreTime = 0;
            }
            try {
                String[] ignoreTypes;
                block21: for (String s : ignoreTypes = ScriptLogIgnoreType.split(",")) {
                    EnumScriptType type = EnumScriptType.valueOfIgnoreCase(s);
                    if (type == null) continue;
                    switch (type) {
                        case INIT: {
                            CustomNpcs.InitIgnore = true;
                            continue block21;
                        }
                        case TICK: {
                            CustomNpcs.TickIgnore = true;
                            continue block21;
                        }
                        case INTERACT: {
                            CustomNpcs.InteractIgnore = true;
                            continue block21;
                        }
                        case DIALOG: {
                            CustomNpcs.DialogIgnore = true;
                            continue block21;
                        }
                        case DAMAGED: {
                            CustomNpcs.DamagedIgnore = true;
                            continue block21;
                        }
                        case KILLED: {
                            CustomNpcs.KilledIgnore = true;
                            continue block21;
                        }
                        case ATTACK: {
                            CustomNpcs.AttackIgnore = true;
                            continue block21;
                        }
                        case TARGET: {
                            CustomNpcs.TargetIgnore = true;
                            continue block21;
                        }
                        case COLLIDE: {
                            CustomNpcs.CollideIgnore = true;
                            continue block21;
                        }
                        case KILLS: {
                            CustomNpcs.KillsIgnore = true;
                            continue block21;
                        }
                        case DIALOG_CLOSE: {
                            CustomNpcs.DialogCloseIgnore = true;
                            continue block21;
                        }
                        case TIMER: {
                            CustomNpcs.TimerIgnore = true;
                            continue block21;
                        }
                    }
                }
            }
            catch (Exception ignoreTypes) {
                // empty catch block
            }
        }
        catch (Exception e) {
            FMLLog.log((Level)Level.ERROR, (Throwable)e, (String)"CNPC+ has had a problem loading its debug configuration", (Object[])new Object[0]);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    static {
        PlayerLogging = false;
        ScriptLogging = false;
        ScriptFrequency = 20;
        ScriptIgnoreTime = 2000;
        ScriptLogIgnoreType = "TICK";
        LogActionManager = false;
    }
}

