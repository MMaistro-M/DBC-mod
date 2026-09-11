/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.registry.EntityRegistry
 */
package com.goodbird.npcgecko;

import com.goodbird.npcgecko.CustomNpcPlusGecko;
import com.goodbird.npcgecko.api.AbstractGeckoAPI;
import com.goodbird.npcgecko.entity.EntityCustomModel;
import com.goodbird.npcgecko.handler.EventHandler;
import com.goodbird.npcgecko.network.NetworkHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import noppes.npcs.scripted.NpcAPI;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent ev) {
        EntityRegistry.registerModEntity(EntityCustomModel.class, (String)"CustomModelEntity", (int)0, (Object)CustomNpcPlusGecko.instance, (int)64, (int)10, (boolean)false);
        NpcAPI.EVENT_BUS.register((Object)new EventHandler());
    }

    public void init(FMLInitializationEvent ev) {
        NetworkHandler.init();
        NpcAPI.Instance().addGlobalObject("GeckoAPI", AbstractGeckoAPI.Instance());
    }
}

