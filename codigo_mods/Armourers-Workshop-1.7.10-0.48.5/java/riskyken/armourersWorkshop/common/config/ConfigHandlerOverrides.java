/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package riskyken.armourersWorkshop.common.config;

import java.io.File;
import java.util.Arrays;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;

public class ConfigHandlerOverrides {
    public static String CATEGORY_OVERRIDES = "overrides";
    public static Configuration config;

    public static void init(File file) {
        if (config == null) {
            config = new Configuration(file, "1");
            ConfigHandlerOverrides.loadConfigFile();
        }
    }

    public static void loadConfigFile() {
        ConfigHandlerOverrides.loadCategoryCompatibility();
        if (config.hasChanged()) {
            config.save();
        }
    }

    private static void loadCategoryCompatibility() {
        Property prop = config.get(CATEGORY_OVERRIDES, "itemOverrides", ModAddonManager.getDefaultOverrides());
        prop.setLanguageKey("itemOverrides");
        prop.comment = "List of items that can have skins applied.\nFormat [override type:mod id:item name]\nValid override types are:\nsword\nitem\npickaxe\naxe\nshovel\nhoe\nbow";
        ModAddonManager.itemOverrides.clear();
        ModAddonManager.itemOverrides.addAll(Arrays.asList(prop.getStringList()));
    }
}

