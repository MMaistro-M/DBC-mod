package JinRyuu.JRMCore;

import JinRyuu.JRMCore.blocks.BlocksJRMC;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigsClient;
import JinRyuu.JRMCore.entity.EntitiesJRMC;
import JinRyuu.JRMCore.items.ItemsJRMC;
import JinRyuu.JRMCore.items.RecipesJRMC;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.server.commands.CommandNotification;
import JinRyuu.JRMCore.server.config.JGConfigsServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = "jinryuujrmcore", name = "JinRyuu's JRMCore", version = "1.3.51")
public class mod_JRMCore {
   @SidedProxy(clientSide = "JinRyuu.JRMCore.JRMCoreClient", serverSide = "JinRyuu.JRMCore.JRMCore")
   public static JRMCore proxy;
   @Instance("mod_JRMCore")
   public static mod_JRMCore instance = new mod_JRMCore();
   private JRMCoreGuiHandler guiHandler = new JRMCoreGuiHandler();
   private static int modGuiIndex = -10;
   public static CreativeTabs JRMCore = new JRMCTab("JRMCore");
   public static final int GUI_CUSTOM_INV = modGuiIndex--;
   public static final int GUI_ITEM_INV = modGuiIndex--;
   public static Logger logger;

   private String getVersion() {
      return "1.3.51";
   }

   @EventHandler
   public void ServerStarting(FMLServerStartingEvent event) {
      ServerCommandManager manager = (ServerCommandManager)event.getServer().func_71187_D();
      manager.func_71560_a(new ComJrmctp());
      manager.func_71560_a(new ComJrmca());
      manager.func_71560_a(new ComJrmc());
      manager.func_71560_a(new ComJrmcpvp());
      manager.func_71560_a(new ComJrmcpvpcheck());
      manager.func_71560_a(new ComJrmcKills());
      manager.func_71560_a(new ComJrmcCheck());
      manager.func_71560_a(new ComJrmcHeal());
      manager.func_71560_a(new ComJrmcStatusEffect());
      manager.func_71560_a(new ComJrmcTech());
      manager.func_71560_a(new ComJrmcm());
      manager.func_71560_a(new ComJrmctexp());
      manager.func_71560_a(new ComJrmcRep());
      manager.func_71560_a(new ComJrmcRei());
      manager.func_71560_a(new ComJrmcDebug());
      manager.func_71560_a(new ComJrmcRacialSkill());
      manager.func_71560_a(new ComJrmcaBonus());
      manager.func_71560_a(new ComJrmcaBonusCheck());
      manager.func_71560_a(new CommandNotification());
      manager.func_71560_a(new ComJrmcSafeZone());
      manager.func_71560_a(new ComJrmcFormMastery());
      manager.func_71560_a(new ComJrmcFormMasteryCheck());
   }

   @EventHandler
   public void PreLoad(FMLPreInitializationEvent event) {
      if (System.getProperty("posX") != null && System.getProperty("posY") != null) {
         Display.setLocation(Integer.parseInt(System.getProperty("posX")), Integer.parseInt(System.getProperty("posY")));
      }

      if (System.getProperty("title") != null) {
         Display.setTitle(System.getProperty("title"));
      }

      if (event.getSide() == Side.CLIENT) {
         JGConfigsClient.preInitClient(event);
      }

      logger = event.getModLog();
      logger.info("Current Version " + this.getVersion());
      String thisYear = new SimpleDateFormat("yyyy").format(new Date());
      logger.info("Copyright (c) Tamas 'JinRyuu' Nagy,  2012-2018");
      logger.info("Copyright (c) Benjamin 'JinGames_Ben' Nagy,  2018-" + new SimpleDateFormat("yyyy").format(new Date()) + "");
      logger.info("https://jingames.net");
      proxy.registerKeys();
      proxy.registerTicks();
      proxy.initialize();
      JRMCoreH.init();
      PD.registerPackets();
      JGConfigsServer.preInitServer(event);
      logger.info("Pre Initialization Complated");
      instance = this;
   }

   @EventHandler
   public void load(FMLInitializationEvent event) {
      BlocksJRMC.init();
      BlocksJRMC.register();
      ItemsJRMC.init();
      RecipesJRMC.init();
      proxy.registerRenderThings();
      if (event.getSide() == Side.CLIENT) {
         JRMCoreGuiScreenE eventgs = new JRMCoreGuiScreenE();
         MinecraftForge.EVENT_BUS.register(eventgs);
         FMLCommonHandler.instance().bus().register(eventgs);
      }

      JRMCoreEH events = new JRMCoreEH();
      MinecraftForge.EVENT_BUS.register(events);
      FMLCommonHandler.instance().bus().register(events);
      NetworkRegistry.INSTANCE.registerGuiHandler(this, this.guiHandler);
      EntitiesJRMC.common();
   }

   @EventHandler
   public void PostLoad(FMLPostInitializationEvent event) {
      proxy.postInit();
      long m = Runtime.getRuntime().maxMemory() / 1000000L;
      JRMCoreH.mem = m;
      logger.info("Max Memory: " + m + "M");
      logger.info("Initialization Completed");
      logger.info("Fully Loaded!");
      logger.info("Enjoy ^^");
   }
}
