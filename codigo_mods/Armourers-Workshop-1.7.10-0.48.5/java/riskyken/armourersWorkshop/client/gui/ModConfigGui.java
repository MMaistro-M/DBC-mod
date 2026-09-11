/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiConfig
 *  cpw.mods.fml.client.config.IConfigElement
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.common.config.ConfigElement
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;

@SideOnly(value=Side.CLIENT)
public class ModConfigGui
extends GuiConfig {
    public ModConfigGui(GuiScreen parent) {
        super(parent, ModConfigGui.makeConfigScreens(), "armourersWorkshop", false, false, GuiConfig.getAbridgedConfigPath((String)ConfigHandler.config.toString()));
    }

    public static List<IConfigElement> makeConfigScreens() {
        ArrayList<IConfigElement> configs = new ArrayList<IConfigElement>();
        configs.addAll(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_GENERAL)).getChildElements());
        configs.addAll(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_RECIPE)).getChildElements());
        configs.addAll(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_SERVER)).getChildElements());
        configs.addAll(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_COMPATIBILITY)).getChildElements());
        configs.addAll(new ConfigElement(ConfigHandler.config.getCategory(ConfigHandler.CATEGORY_ENTITY_SKINS)).getChildElements());
        configs.addAll(new ConfigElement(ConfigHandlerClient.config.getCategory(ConfigHandlerClient.CATEGORY_CLIENT)).getChildElements());
        configs.addAll(new ConfigElement(ConfigHandlerClient.config.getCategory(ConfigHandlerClient.CATEGORY_SKIN_PREVIEW)).getChildElements());
        configs.addAll(new ConfigElement(ConfigHandlerClient.config.getCategory(ConfigHandlerClient.CATEGORY_DEBUG)).getChildElements());
        return configs;
    }
}

