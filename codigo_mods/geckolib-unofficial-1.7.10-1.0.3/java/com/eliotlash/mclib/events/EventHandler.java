/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 */
package com.eliotlash.mclib.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;

public class EventHandler {
    @SubscribeEvent
    public void clientLogsOut(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
    }
}

