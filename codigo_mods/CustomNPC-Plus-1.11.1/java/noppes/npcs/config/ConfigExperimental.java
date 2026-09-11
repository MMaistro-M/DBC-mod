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
import org.apache.logging.log4j.Level;

public class ConfigExperimental {
    public static Configuration config;
    public static final String CLIENT = "Client";
    public static final String SERVER = "Server";
    public static Property ModernGuiSystemProperty;
    public static boolean ModernGuiSystem;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        try {
            config.load();
            ModernGuiSystemProperty = config.get(CLIENT, "Experimental Dialog GUI", false, "Enables the new CNPC+ Modern GUI for Dialog and Quest information");
            ModernGuiSystem = ModernGuiSystemProperty.getBoolean(false);
        }
        catch (Exception e) {
            FMLLog.log((Level)Level.ERROR, (Throwable)e, (String)"CNPC+ has had a problem loading its experimental configuration", (Object[])new Object[0]);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    static {
        ModernGuiSystem = false;
    }
}

