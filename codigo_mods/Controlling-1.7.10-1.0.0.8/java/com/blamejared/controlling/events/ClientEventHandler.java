/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiControls
 *  net.minecraftforge.client.event.GuiOpenEvent
 */
package com.blamejared.controlling.events;

import com.blamejared.controlling.client.gui.GuiNewControls;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiControls;
import net.minecraftforge.client.event.GuiOpenEvent;

public class ClientEventHandler {
    @SubscribeEvent
    public void openGui(GuiOpenEvent event) {
        try {
            if (event.gui instanceof GuiControls && !(event.gui instanceof GuiNewControls)) {
                event.gui = new GuiNewControls(Minecraft.func_71410_x().field_71462_r, Minecraft.func_71410_x().field_71474_y);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

