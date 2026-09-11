/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 */
package me.NBArmors.main;

import JinRyuu.JRMCore.JRMCoreComTickH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class NB {
    public void registerRenderThings() {
    }

    public void registerTicks() {
    }

    public void initialize() {
        FMLCommonHandler.instance().bus().register((Object)new JRMCoreComTickH());
    }

    public void postInit() {
    }

    public void preInit(FMLPreInitializationEvent event) {
    }
}

