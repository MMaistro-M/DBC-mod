/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 */
package software.bernie.example.config;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
    public static Configuration config;
    public static boolean enableExamples;

    public static void init(File file) {
        config = new Configuration(file);
        ConfigHandler.syncConfig();
    }

    public static void load() {
        config.load();
    }

    public static void syncConfig() {
        config.addCustomCategoryComment("general", "General Settings");
        enableExamples = config.getBoolean("enableexamples", "general", false, "Enable Geckolib Examples");
        config.save();
    }
}

