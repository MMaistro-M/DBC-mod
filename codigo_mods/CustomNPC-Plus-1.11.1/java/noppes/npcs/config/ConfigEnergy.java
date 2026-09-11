/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraftforge.common.config.Configuration
 *  org.apache.logging.log4j.Level
 */
package noppes.npcs.config;

import cpw.mods.fml.common.FMLLog;
import java.io.File;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class ConfigEnergy {
    public static Configuration config;
    public static final String DOMES = "Domes";
    public static final String EXPLOSIONS = "Explosions";
    public static String[] DomeItemBlacklist;
    public static boolean EnableEnergyExplosionBlockDamage;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        try {
            config.load();
            DomeItemBlacklist = config.getStringList("Item Blacklist", DOMES, new String[]{"minecraft:ender_pearl"}, "Items that cannot be used while inside an Energy Dome.\nFormat: modid:itemname (e.g., minecraft:ender_pearl)");
            EnableEnergyExplosionBlockDamage = config.getBoolean("Enable Energy Explosion Block Damage", EXPLOSIONS, false, "If true, explosive ENERGY projectile abilities can destroy terrain.\nThis is intended for server-side use and is disabled by default.");
        }
        catch (Exception e) {
            FMLLog.log((Level)Level.ERROR, (Throwable)e, (String)"CustomNPC+ has had a problem loading its energy configuration", (Object[])new Object[0]);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}

