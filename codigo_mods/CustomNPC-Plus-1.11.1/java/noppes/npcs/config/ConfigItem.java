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
import org.apache.logging.log4j.Level;

public class ConfigItem {
    public static Configuration config;
    public static final String ALL = "All";
    public static final String GUN = "Gun";
    public static Property DisableExtraBlockProperty;
    public static boolean DisableExtraBlock;
    public static Property DisableExtraItemsProperty;
    public static boolean DisableExtraItems;
    public static Property GunsEnabledProperty;
    public static boolean GunsEnabled;
    public static int MachineGunAmmo;
    public static int MachineGunTickSpeed;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        try {
            config.load();
            DisableExtraBlockProperty = config.get(ALL, "Disable Extra Blocks", false);
            DisableExtraBlock = DisableExtraBlockProperty.getBoolean(false);
            DisableExtraItemsProperty = config.get(ALL, "Disable Extra Items", false);
            DisableExtraItems = DisableExtraItemsProperty.getBoolean(false);
            GunsEnabledProperty = config.get(GUN, "Guns Enabled", true, "Set to false if you want to disable guns");
            GunsEnabled = GunsEnabledProperty.getBoolean(true);
            MachineGunAmmo = config.get(GUN, "Machine Gun Ammo", 8, "Max Clip of a Machine Gun").getInt(8);
            MachineGunTickSpeed = config.get(GUN, "Machine Gun Fire Rate", 6, "How many ticks to shoot bullets at").getInt(6);
            if (CustomNpcs.legacyExist) {
                DisableExtraBlock = LegacyConfig.DisableExtraBlock;
                DisableExtraBlockProperty.set(DisableExtraBlock);
                DisableExtraItems = LegacyConfig.DisableExtraItems;
                DisableExtraItemsProperty.set(DisableExtraItems);
                GunsEnabled = LegacyConfig.GunsEnabled;
                GunsEnabledProperty.set(GunsEnabled);
            }
        }
        catch (Exception e) {
            FMLLog.log((Level)Level.ERROR, (Throwable)e, (String)"CNPC+ has had a problem loading its item configuration", (Object[])new Object[0]);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    static {
        DisableExtraBlock = false;
        DisableExtraItems = false;
        GunsEnabled = true;
        MachineGunAmmo = 8;
        MachineGunTickSpeed = 6;
    }
}

