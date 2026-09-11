/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  net.minecraft.client.Minecraft
 */
package me.NBArmors.main;

import JinRyuu.JRMCore.JRMCoreCliTicH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import me.NBArmors.main.NB;
import net.minecraft.client.Minecraft;

public class NBClient
extends NB {
    public static Minecraft mc = Minecraft.func_71410_x();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void registerRenderThings() {
    }

    @Override
    public void initialize() {
        super.initialize();
        FMLCommonHandler.instance().bus().register((Object)new JRMCoreCliTicH(mc));
    }

    @Override
    public void registerTicks() {
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}

