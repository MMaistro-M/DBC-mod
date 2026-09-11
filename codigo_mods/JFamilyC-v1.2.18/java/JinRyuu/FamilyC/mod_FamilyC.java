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
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ServerCommandManager
 *  net.minecraftforge.common.config.Configuration
 *  org.apache.logging.log4j.Logger
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.FamilyC.FamilyC;
import JinRyuu.FamilyC.FamilyCComJFCGen;
import JinRyuu.FamilyC.FamilyCComJFCsoc;
import JinRyuu.FamilyC.FamilyCConfig;
import JinRyuu.FamilyC.FamilyCGuiHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

@Mod(modid="jinryuufamilyc", name="JinRyuu's Family C", version="1.2.18", dependencies="required-after:jinryuujrmcore")
public class mod_FamilyC {
    public static final String MOD = "JinRyuu's Family C";
    @SidedProxy(clientSide="JinRyuu.FamilyC.FamilyCClient", serverSide="JinRyuu.FamilyC.FamilyC")
    public static FamilyC proxy;
    @Mod.Instance(value="mod_FamilyC")
    public static mod_FamilyC instance;
    private FamilyCGuiHandler guiHandler = new FamilyCGuiHandler();
    public static Properties runtimeIdProperties;
    public static Logger logger;

    private String getVersion() {
        return "1.2.18";
    }

    @Mod.EventHandler
    public void ServerStarting(FMLServerStartingEvent event) {
        ServerCommandManager manager = (ServerCommandManager)event.getServer().func_71187_D();
        manager.func_71560_a((ICommand)new FamilyCComJFCGen());
        manager.func_71560_a((ICommand)new FamilyCComJFCsoc());
    }

    @Mod.EventHandler
    public void PreLoad(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Current Version " + this.getVersion());
        logger.info("Copyright (c) Tamas 'JinRyuu' Nagy,  2012-" + new SimpleDateFormat("yyyy").format(new Date()) + "");
        logger.info("https://jingames.net");
        proxy.registerKeys();
        proxy.registerTicks();
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        FamilyCConfig.init(config);
        instance = this;
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        EntityRegistry.registerModEntity(EntityNPC.class, (String)"Child", (int)1, (Object)instance, (int)80, (int)5, (boolean)true);
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)this.guiHandler);
        proxy.registerRenderThings();
    }

    @Mod.EventHandler
    public void PostLoad(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    static {
        instance = new mod_FamilyC();
        runtimeIdProperties = new Properties();
    }
}

