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
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ServerCommandManager
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraftforge.common.config.Configuration
 *  org.apache.logging.log4j.Logger
 */
package JinRyuu.JYearsC;

import JinRyuu.JYearsC.ComJycage;
import JinRyuu.JYearsC.ComJycdate;
import JinRyuu.JYearsC.JYCTab;
import JinRyuu.JYearsC.JYearsC;
import JinRyuu.JYearsC.JYearsCConfig;
import JinRyuu.JYearsC.JYearsCGuiHandler;
import JinRyuu.JYearsC.JYearsCItems;
import JinRyuu.JYearsC.JYearsCRecipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

@Mod(modid="jinryuuyearsc", name="JinRyuu's Years C", version="1.2.5", dependencies="required-after:jinryuujrmcore")
public class mod_JYearsC {
    public static final String MOD = "JinRyuu's Years C";
    public static CreativeTabs JYearsC = new JYCTab("JYearsC");
    @SidedProxy(clientSide="JinRyuu.JYearsC.JYearsCClient", serverSide="JinRyuu.JYearsC.JYearsC")
    public static JYearsC proxy;
    @Mod.Instance(value="mod_JYearsC")
    public static mod_JYearsC instance;
    private JYearsCGuiHandler guiHandler = new JYearsCGuiHandler();
    public static Logger logger;
    public static Properties runtimeIdProperties;

    private String getVersion() {
        return "1.2.5";
    }

    @Mod.EventHandler
    public void ServerStarting(FMLServerStartingEvent event) {
        ServerCommandManager manager = (ServerCommandManager)event.getServer().func_71187_D();
        manager.func_71560_a((ICommand)new ComJycage());
        manager.func_71560_a((ICommand)new ComJycdate());
    }

    @Mod.EventHandler
    public void PreLoad(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Current Version " + this.getVersion());
        logger.info("Copyright (c) Tamas 'JinRyuu' Nagy,  2012-" + new SimpleDateFormat("yyyy").format(new Date()) + "");
        logger.info("http://jingames.net/");
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        JYearsCConfig.init(config);
        proxy.registerKeys();
        proxy.initialize();
        logger.info("Pre Initialization Complated");
        instance = this;
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        JYearsCItems.init();
        JYearsCRecipes.init();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)this.guiHandler);
        proxy.registerRenderThings();
    }

    @Mod.EventHandler
    public void PostLoad(FMLPostInitializationEvent event) {
        proxy.postInit();
        logger.info("Initialization Completed");
        logger.info("Fully Loaded!");
        logger.info("Enjoy ^^");
    }

    static {
        instance = new mod_JYearsC();
        runtimeIdProperties = new Properties();
    }
}

