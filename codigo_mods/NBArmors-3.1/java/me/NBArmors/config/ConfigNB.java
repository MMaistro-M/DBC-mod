/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Mod$EventHandler
 */
package me.NBArmors.config;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import me.NBArmors.main.NBmain;

public class ConfigNB {
    public static boolean Recipe;
    public static final boolean RECIPE_DEFAULT = false;

    @Mod.EventHandler
    public static void syncConfig() {
        FMLCommonHandler.instance().bus().register((Object)NBmain.instance);
        String RECIPES = "general" + "." + "Items";
        NBmain.config.addCustomCategoryComment(RECIPES, "Enable or disable the FabriCore Items");
        Recipe = NBmain.config.get(RECIPES, "Disable the FabricCore Items <DEFAULT = false>", false).getBoolean(false);
        if (NBmain.config.hasChanged()) {
            NBmain.config.save();
        }
    }
}

