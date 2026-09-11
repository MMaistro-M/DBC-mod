/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraftforge.common.MinecraftForge
 */
package hedaox.ninjinentities.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import hedaox.ninjinentities.event.EventAttackManager;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    EventAttackManager EAHandler = new EventAttackManager();
    public static SimpleNetworkWrapper network;

    public void registerRenderers() {
    }

    public void preInit(FMLPreInitializationEvent $e) {
        MinecraftForge.EVENT_BUS.register((Object)this.EAHandler);
        FMLCommonHandler.instance().bus().register((Object)this.EAHandler);
    }

    public void init(FMLInitializationEvent $e) {
    }

    public void postInit(FMLPostInitializationEvent $e) {
    }
}

