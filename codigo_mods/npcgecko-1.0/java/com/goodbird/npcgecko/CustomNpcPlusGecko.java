/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 */
package com.goodbird.npcgecko;

import com.goodbird.npcgecko.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="npcgecko", name="CustomNPC+ Gecko Addon", version="1.0", dependencies="required-after:customnpcs;required-after:geckolib3")
public class CustomNpcPlusGecko {
    @SidedProxy(clientSide="com.goodbird.npcgecko.client.ClientProxy", serverSide="com.goodbird.npcgecko.CommonProxy")
    public static CommonProxy proxy;
    @Mod.Instance
    public static CustomNpcPlusGecko instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        proxy.preInit(ev);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent ev) {
        proxy.init(ev);
    }
}

