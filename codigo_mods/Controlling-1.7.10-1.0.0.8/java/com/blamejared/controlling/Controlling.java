/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.common.MinecraftForge
 */
package com.blamejared.controlling;

import com.blamejared.controlling.events.ClientEventHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid="controlling", name="Controlling", version="1.7.10-1.0.0.8", acceptableRemoteVersions="*")
public class Controlling {
    @Mod.EventHandler
    private void init(FMLInitializationEvent event) {
        if (event.getSide().isClient()) {
            MinecraftForge.EVENT_BUS.register((Object)new ClientEventHandler());
        }
    }
}

