/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiConfig
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.common.config.ConfigElement
 */
package me.NBArmors.config;

import cpw.mods.fml.client.config.GuiConfig;
import me.NBArmors.main.NBmain;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class ConfigGuiNB
extends GuiConfig {
    public ConfigGuiNB(GuiScreen parent) {
        super(parent, new ConfigElement(NBmain.config.getCategory("general")).getChildElements(), "nbarmors", false, false, GuiConfig.getAbridgedConfigPath((String)NBmain.config.toString()));
    }

    public void func_73866_w_() {
        super.func_73866_w_();
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }

    protected void func_146284_a(GuiButton button) {
        super.func_146284_a(button);
    }
}

