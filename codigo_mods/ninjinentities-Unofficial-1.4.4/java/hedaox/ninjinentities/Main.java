/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.command.ICommand
 */
package hedaox.ninjinentities;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import hedaox.ninjinentities.command.CommandSummonNinjin;
import hedaox.ninjinentities.config.ModConfig;
import hedaox.ninjinentities.entities.NinjinEntities;
import hedaox.ninjinentities.network.MessageSendEntityToSpark;
import hedaox.ninjinentities.proxy.CommonProxy;
import net.minecraft.command.ICommand;

@Mod(modid="ninjinentities", name="Ninjin Entities Unofficial")
public class Main {
    @Mod.Instance
    public static Main instance = new Main();
    @SidedProxy(clientSide="hedaox.ninjinentities.proxy.ClientProxy", serverSide="hedaox.ninjinentities.proxy.ServerProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent $e) {
        ModConfig.loadConfig($e.getSuggestedConfigurationFile());
        proxy.preInit($e);
        NinjinEntities.init();
        network = NetworkRegistry.INSTANCE.newSimpleChannel("MyChannel");
        network.registerMessage(MessageSendEntityToSpark.Handler.class, MessageSendEntityToSpark.class, 0, Side.SERVER);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent $e) {
        proxy.postInit($e);
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new CommandSummonNinjin());
    }
}

