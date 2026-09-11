/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.IFuelHandler
 *  cpw.mods.fml.common.IWorldGenerator
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.ModMetadata
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ServerCommandManager
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidContainerRegistry
 *  org.apache.logging.log4j.Logger
 */
package JinRyuu.DragonBC.common;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCEH;
import JinRyuu.DragonBC.common.DBCHandlerBucket;
import JinRyuu.DragonBC.common.DBCHandlerFuel;
import JinRyuu.DragonBC.common.DBCTab;
import JinRyuu.DragonBC.common.Gui.ComDbc;
import JinRyuu.DragonBC.common.Gui.ComDbcSpawn;
import JinRyuu.DragonBC.common.Gui.ComDbcSpawnKi;
import JinRyuu.DragonBC.common.Gui.ComReincarnate;
import JinRyuu.DragonBC.common.Gui.ComRespCon;
import JinRyuu.DragonBC.common.Gui.ComRevive;
import JinRyuu.DragonBC.common.Gui.ComSkill;
import JinRyuu.DragonBC.common.Gui.ComStrain;
import JinRyuu.DragonBC.common.Gui.DBCGuiHandler;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Lang;
import JinRyuu.DragonBC.common.Npcs.EntitiesDBC;
import JinRyuu.DragonBC.common.RecipesDBC;
import JinRyuu.DragonBC.common.Worlds.BiomeGenBaseDBC;
import JinRyuu.DragonBC.common.Worlds.Dimension.NullRealm.x2WorldProviderNullRealm;
import JinRyuu.DragonBC.common.Worlds.WorldGeneratorDB;
import JinRyuu.DragonBC.common.Worlds.x0WorldProviderNamek;
import JinRyuu.DragonBC.common.Worlds.x1WorldProviderOW;
import JinRyuu.DragonBC.common.Worlds.x2WorldProviderTC;
import JinRyuu.DragonBC.common.Worlds.x3WorldProviderVegeta;
import JinRyuu.JRMCore.entity.EntityEnergyAtt;
import JinRyuu.JRMCore.entity.EntityPrjtls_1;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid="jinryuudragonblockc", name="Dragon Block C", version="1.4.85", dependencies="required-after:jinryuujrmcore")
public class mod_DragonBC {
    public static final String MOD = "Dragon Block C";
    public static CreativeTabs DragonBlockC = new DBCTab("DragonBlockC");
    @SidedProxy(clientSide="JinRyuu.DragonBC.common.DBCClient", serverSide="JinRyuu.DragonBC.common.DBC")
    public static DBC proxy;
    @Mod.Instance(value="mod_DragonBC")
    public static mod_DragonBC instance;
    private DBCGuiHandler guiHandler = new DBCGuiHandler();
    public static boolean DeathSystemOff;
    public static int DBSpawnChance;
    public static boolean DBSpawnEnabled;
    public static String DBSpawnTime;
    public static boolean SagaSystemOn;
    public static boolean SagaSysSpawnPods;
    public static boolean cDeathSystemOff;
    public static int cDBSpawnChance;
    public static boolean cDBSpawnEnabled;
    public static String cDBSpawnTime;
    public static boolean cSagaSystemOn;
    public static boolean cSagaSysSpawnPods;
    public static int cwfb;
    public static int chfb;
    public static int cwfn;
    public static int chfn;
    public static boolean ConsSizeChangeOn;
    public static boolean TransSizeChangeOn;
    public static Properties runtimeIdProperties;
    public static Logger logger;

    private String getVersion() {
        return "1.4.85";
    }

    public static DamageSource causePrjctlsDamage(EntityPrjtls_1 entityEnergyAtt, Entity par1Entity) {
        return new EntityDamageSourceIndirect("dbcprojectile_1", (Entity)entityEnergyAtt, par1Entity).func_76349_b();
    }

    public static DamageSource causeEntityEnergyAttDamage(EntityEnergyAtt entityEnergyAtt, Entity par1Entity) {
        return new EntityDamageSourceIndirect("Energy Attack", (Entity)entityEnergyAtt, par1Entity).func_76349_b();
    }

    @Mod.EventHandler
    public void ServerStarting(FMLServerStartingEvent event) {
        ServerCommandManager manager = (ServerCommandManager)event.getServer().func_71187_D();
        manager.func_71560_a((ICommand)new ComRespCon());
        manager.func_71560_a((ICommand)new ComDbc());
        manager.func_71560_a((ICommand)new ComDbcSpawn());
        manager.func_71560_a((ICommand)new ComRevive());
        manager.func_71560_a((ICommand)new ComSkill());
        manager.func_71560_a((ICommand)new ComStrain());
        manager.func_71560_a((ICommand)new ComReincarnate());
        manager.func_71560_a((ICommand)new ComDbcSpawnKi());
    }

    private void modMeta(FMLPreInitializationEvent event) {
        ModMetadata meta = event.getModMetadata();
        meta.name = MOD;
        meta.description = "A Dragon Ball mod for Minecraft.";
        meta.authorList.clear();
        meta.authorList.add("JinRyuu Nagy");
        meta.modId = "jinryuudragonblockc";
        meta.version = "1.4.85";
        meta.autogenerated = false;
    }

    @Mod.EventHandler
    public void PreLoad(FMLPreInitializationEvent event) {
        this.modMeta(event);
        logger = event.getModLog();
        logger.info("Current Version " + this.getVersion());
        logger.info("Copyright (c) Tamas 'JinRyuu' Nagy,  2012-2018");
        logger.info("Copyright (c) Benjamin 'JinGames_Ben' Nagy,  2018-" + new SimpleDateFormat("yyyy").format(new Date()) + "");
        logger.info("https://jingames.net");
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        DBCConfig.init(config);
        proxy.registerKeys();
        proxy.registerTicks();
        proxy.registerDBCRender();
        proxy.initialize();
        logger.info("Pre Initialization Complated");
        instance = this;
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        BlocksDBC.init();
        BlocksDBC.register();
        ItemsDBC.init();
        RecipesDBC.init();
        DBCEH events = new DBCEH();
        MinecraftForge.EVENT_BUS.register((Object)events);
        FMLCommonHandler.instance().bus().register((Object)events);
        GameRegistry.registerFuelHandler((IFuelHandler)new DBCHandlerFuel());
        FluidContainerRegistry.registerFluidContainer((Fluid)BlocksDBC.medicalLiquid, (ItemStack)new ItemStack(ItemsDBC.ItemBucketMedLiq), (ItemStack)new ItemStack(Items.field_151133_ar));
        DBCHandlerBucket.INSTANCE.buckets.put(BlocksDBC.BlockHealingPods, ItemsDBC.ItemBucketMedLiq);
        MinecraftForge.EVENT_BUS.register((Object)DBCHandlerBucket.INSTANCE);
        BiomeGenBaseDBC.init();
        GameRegistry.registerWorldGenerator((IWorldGenerator)new WorldGeneratorDB(), (int)0);
        DimensionManager.registerProviderType((int)DBCConfig.planetNamek, x0WorldProviderNamek.class, (boolean)true);
        DimensionManager.registerDimension((int)DBCConfig.planetNamek, (int)DBCConfig.planetNamek);
        Side side = event.getSide();
        if (side == Side.CLIENT || DBCConfig.plntVegeta) {
            DimensionManager.registerProviderType((int)DBCConfig.planetVegeta, x3WorldProviderVegeta.class, (boolean)true);
            DimensionManager.registerDimension((int)DBCConfig.planetVegeta, (int)DBCConfig.planetVegeta);
        }
        int dimID = DBCConfig.otherWorld;
        DimensionManager.registerProviderType((int)dimID, x1WorldProviderOW.class, (boolean)true);
        DimensionManager.registerDimension((int)dimID, (int)dimID);
        dimID = DBCConfig.dimTimeChamber;
        DimensionManager.registerProviderType((int)dimID, x2WorldProviderTC.class, (boolean)true);
        DimensionManager.registerDimension((int)dimID, (int)dimID);
        dimID = DBCConfig.dimNullRealm;
        DimensionManager.registerProviderType((int)dimID, x2WorldProviderNullRealm.class, (boolean)true);
        DimensionManager.registerDimension((int)dimID, (int)dimID);
        Lang.init();
        logger.info("Initialization Completed");
        EntitiesDBC.common();
        logger.info(" Entities Loaded!");
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)this.guiHandler);
        proxy.registerRenderThings();
    }

    @Mod.EventHandler
    public void PostLoad(FMLPostInitializationEvent event) {
        proxy.postInit();
        logger.info("Fully Loaded!");
        logger.info("Have a good game!");
        logger.info("Fight For Your Life ^^");
    }

    static {
        instance = new mod_DragonBC();
        DeathSystemOff = false;
        DBSpawnChance = 4;
        DBSpawnEnabled = true;
        DBSpawnTime = "daytime";
        SagaSystemOn = true;
        SagaSysSpawnPods = true;
        cDeathSystemOff = false;
        cDBSpawnChance = 4;
        cDBSpawnEnabled = true;
        cDBSpawnTime = "daytime";
        cSagaSystemOn = true;
        cSagaSysSpawnPods = true;
        cwfb = 0;
        chfb = 0;
        cwfn = 0;
        chfn = 0;
        ConsSizeChangeOn = true;
        TransSizeChangeOn = true;
        runtimeIdProperties = new Properties();
    }
}

