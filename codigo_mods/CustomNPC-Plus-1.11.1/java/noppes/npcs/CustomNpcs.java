/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLLoadCompleteEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerAboutToStartEvent
 *  cpw.mods.fml.common.event.FMLServerStartedEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.event.FMLServerStoppedEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockIce
 *  net.minecraft.block.BlockLeavesBase
 *  net.minecraft.block.BlockVine
 *  net.minecraft.client.Minecraft
 *  net.minecraft.command.ICommand
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.common.ForgeChunkManager
 *  net.minecraftforge.common.ForgeChunkManager$LoadingCallback
 *  net.minecraftforge.common.MinecraftForge
 */
package noppes.npcs;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import foxz.command.CommandNoppes;
import io.github.somehussar.janinoloader.api.IDynamicCompiler;
import io.github.somehussar.janinoloader.api.IDynamicCompilerBuilder;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import kamkeel.npcs.addon.AddonManager;
import kamkeel.npcs.command.CommandKamkeel;
import kamkeel.npcs.command.profile.CommandProfile;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.controllers.TelegraphController;
import kamkeel.npcs.controllers.data.energycharge.EnergyChargeTracker;
import kamkeel.npcs.controllers.data.profile.CNPCData;
import kamkeel.npcs.developer.Developer;
import kamkeel.npcs.entity.EntityAbilityBeam;
import kamkeel.npcs.entity.EntityAbilityDisc;
import kamkeel.npcs.entity.EntityAbilityLaser;
import kamkeel.npcs.entity.EntityAbilityOrb;
import kamkeel.npcs.entity.EntityAbilityZone;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyDome;
import kamkeel.npcs.entity.EntityEnergyPanel;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.entity.EntityEnergySlicer;
import kamkeel.npcs.entity.EntityEnergySweeper;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.util.BukkitUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.BlockVine;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import nikedemos.markovnames.generators.MarkovAncientGreek;
import nikedemos.markovnames.generators.MarkovAztec;
import nikedemos.markovnames.generators.MarkovCustomNPCsClassic;
import nikedemos.markovnames.generators.MarkovGenerator;
import nikedemos.markovnames.generators.MarkovJapanese;
import nikedemos.markovnames.generators.MarkovOldNorse;
import nikedemos.markovnames.generators.MarkovRoman;
import nikedemos.markovnames.generators.MarkovSaami;
import nikedemos.markovnames.generators.MarkovSlavic;
import nikedemos.markovnames.generators.MarkovSpanish;
import nikedemos.markovnames.generators.MarkovWelsh;
import noppes.npcs.CommonProxy;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.LogWriter;
import noppes.npcs.ScriptForgeEventHandler;
import noppes.npcs.ScriptItemEventHandler;
import noppes.npcs.ScriptPlayerEventHandler;
import noppes.npcs.ServerEventsHandler;
import noppes.npcs.ServerTickHandler;
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.config.LoadConfiguration;
import noppes.npcs.config.legacy.LegacyConfig;
import noppes.npcs.controllers.APIRegistry;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.AuctionController;
import noppes.npcs.controllers.BankController;
import noppes.npcs.controllers.ChunkController;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.GlobalDataController;
import noppes.npcs.controllers.LinkedItemController;
import noppes.npcs.controllers.LinkedNpcController;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.ScriptHookController;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.controllers.ServerTagMapController;
import noppes.npcs.controllers.SpawnController;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.TransportController;
import noppes.npcs.enchants.EnchantInterface;
import noppes.npcs.entity.EntityChairMount;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityMagicProjectile;
import noppes.npcs.entity.EntityNPCGolem;
import noppes.npcs.entity.EntityNpcCrystal;
import noppes.npcs.entity.EntityNpcDragon;
import noppes.npcs.entity.EntityNpcPony;
import noppes.npcs.entity.EntityNpcSlime;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.entity.old.EntityNPCDwarfFemale;
import noppes.npcs.entity.old.EntityNPCDwarfMale;
import noppes.npcs.entity.old.EntityNPCElfFemale;
import noppes.npcs.entity.old.EntityNPCElfMale;
import noppes.npcs.entity.old.EntityNPCEnderman;
import noppes.npcs.entity.old.EntityNPCFurryFemale;
import noppes.npcs.entity.old.EntityNPCFurryMale;
import noppes.npcs.entity.old.EntityNPCHumanFemale;
import noppes.npcs.entity.old.EntityNPCHumanMale;
import noppes.npcs.entity.old.EntityNPCOrcFemale;
import noppes.npcs.entity.old.EntityNPCOrcMale;
import noppes.npcs.entity.old.EntityNPCVillager;
import noppes.npcs.entity.old.EntityNpcEnderchibi;
import noppes.npcs.entity.old.EntityNpcMonsterFemale;
import noppes.npcs.entity.old.EntityNpcMonsterMale;
import noppes.npcs.entity.old.EntityNpcNagaFemale;
import noppes.npcs.entity.old.EntityNpcNagaMale;
import noppes.npcs.entity.old.EntityNpcSkeleton;
import noppes.npcs.scripted.NpcAPI;
import somehussar.janino.AdvancedClassFilter;

@Mod(modid="customnpcs", name="CustomNPC+", version="1.11.1")
public class CustomNpcs {
    @SidedProxy(clientSide="noppes.npcs.client.ClientProxy", serverSide="noppes.npcs.CommonProxy")
    public static CommonProxy proxy;
    public static File Dir;
    private static int NewEntityStartId;
    public static long ticks;
    public static CustomNpcs instance;
    public static boolean FreezeNPCs;
    public static final MarkovGenerator[] MARKOV_GENERATOR;
    public static boolean InitIgnore;
    public static boolean TickIgnore;
    public static boolean InteractIgnore;
    public static boolean DialogIgnore;
    public static boolean DamagedIgnore;
    public static boolean KilledIgnore;
    public static boolean AttackIgnore;
    public static boolean TargetIgnore;
    public static boolean CollideIgnore;
    public static boolean KillsIgnore;
    public static boolean DialogCloseIgnore;
    public static boolean TimerIgnore;
    public static String configPath;
    public static String legacyPath;
    public static boolean legacyExist;
    public static LegacyConfig legacyConfig;
    public static MinecraftServer Server;
    private static IDynamicCompiler globalJaninoCompiler;
    @SideOnly(value=Side.CLIENT)
    private static IDynamicCompiler clientJaninoCompiler;
    @SideOnly(value=Side.CLIENT)
    private static Set<Consumer<AdvancedClassFilter>> clientClassFilterConsumer;

    @SideOnly(value=Side.CLIENT)
    public static void addClassesToClientClassFilter(Consumer<AdvancedClassFilter> consumer) {
        clientClassFilterConsumer.add(consumer);
    }

    public CustomNpcs() {
        instance = this;
    }

    public static IDynamicCompiler getDynamicCompiler() {
        if (globalJaninoCompiler == null) {
            globalJaninoCompiler = IDynamicCompilerBuilder.createBuilder().getCompiler();
        }
        return globalJaninoCompiler;
    }

    @SideOnly(value=Side.CLIENT)
    public static IDynamicCompiler getClientCompiler() {
        if (clientJaninoCompiler == null) {
            AdvancedClassFilter filter = new AdvancedClassFilter().addRegexes("noppes\\.npcs\\.api\\..*").banRegexes(".*ClassLoader.*", ".*File*.", "java\\.lang\\.reflect\\..*");
            for (Consumer<AdvancedClassFilter> consumer : clientClassFilterConsumer) {
                consumer.accept(filter);
            }
            clientJaninoCompiler = IDynamicCompilerBuilder.createBuilder().setClassFilter(filter).getCompiler();
        }
        return clientJaninoCompiler;
    }

    @Mod.EventHandler
    public void load(FMLPreInitializationEvent ev) {
        File legacyFile;
        PacketHandler.Instance = new PacketHandler();
        MinecraftServer server = MinecraftServer.func_71276_C();
        String dir = "";
        dir = server != null ? new File(".").getAbsolutePath() : Minecraft.func_71410_x().field_71412_D.getAbsolutePath();
        Dir = new File(dir, "customnpcs");
        Dir.mkdir();
        configPath = ev.getModConfigurationDirectory() + File.separator + "CustomNpcPlus";
        legacyPath = ev.getModConfigurationDirectory() + "/CustomNpcs.cfg";
        File configDir = new File(configPath);
        if (!configDir.exists() && (legacyFile = new File(legacyPath)).exists()) {
            System.out.println("Loading Legacy Config");
            legacyExist = true;
            legacyConfig = new LegacyConfig();
            legacyConfig.init();
        }
        configPath = configPath + File.separator;
        LoadConfiguration.init(configPath);
        EnchantInterface.load();
        CustomItems.load();
        proxy.load();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)proxy);
        MinecraftForge.EVENT_BUS.register((Object)new ServerEventsHandler());
        MinecraftForge.EVENT_BUS.register((Object)new ScriptController());
        new ScriptHookController();
        ScriptPlayerEventHandler scriptPlayerEventHandler = new ScriptPlayerEventHandler();
        MinecraftForge.EVENT_BUS.register((Object)scriptPlayerEventHandler);
        FMLCommonHandler.instance().bus().register((Object)scriptPlayerEventHandler);
        ScriptForgeEventHandler forgeEventHandler = new ScriptForgeEventHandler().registerForgeEvents();
        MinecraftForge.EVENT_BUS.register((Object)forgeEventHandler);
        FMLCommonHandler.instance().bus().register((Object)forgeEventHandler);
        ScriptItemEventHandler scriptItemEventHandler = new ScriptItemEventHandler();
        MinecraftForge.EVENT_BUS.register((Object)scriptItemEventHandler);
        FMLCommonHandler.instance().bus().register((Object)scriptItemEventHandler);
        FMLCommonHandler.instance().bus().register((Object)new ServerTickHandler());
        this.registerNpc(EntityNPCHumanMale.class, "npchumanmale");
        this.registerNpc(EntityNPCVillager.class, "npcvillager");
        this.registerNpc(EntityNpcPony.class, "npcpony");
        this.registerNpc(EntityNPCHumanFemale.class, "npchumanfemale");
        this.registerNpc(EntityNPCDwarfMale.class, "npcdwarfmale");
        this.registerNpc(EntityNPCFurryMale.class, "npcfurrymale");
        this.registerNpc(EntityNpcMonsterMale.class, "npczombiemale");
        this.registerNpc(EntityNpcMonsterFemale.class, "npczombiefemale");
        this.registerNpc(EntityNpcSkeleton.class, "npcskeleton");
        this.registerNpc(EntityNPCDwarfFemale.class, "npcdwarffemale");
        this.registerNpc(EntityNPCFurryFemale.class, "npcfurryfemale");
        this.registerNpc(EntityNPCOrcMale.class, "npcorcfmale");
        this.registerNpc(EntityNPCOrcFemale.class, "npcorcfemale");
        this.registerNpc(EntityNPCElfMale.class, "npcelfmale");
        this.registerNpc(EntityNPCElfFemale.class, "npcelffemale");
        this.registerNpc(EntityNpcCrystal.class, "npccrystal");
        this.registerNpc(EntityNpcEnderchibi.class, "npcenderchibi");
        this.registerNpc(EntityNpcNagaMale.class, "npcnagamale");
        this.registerNpc(EntityNpcNagaFemale.class, "npcnagafemale");
        this.registerNpc(EntityNpcSlime.class, "NpcSlime");
        this.registerNpc(EntityNpcDragon.class, "NpcDragon");
        this.registerNpc(EntityNPCEnderman.class, "npcEnderman");
        this.registerNpc(EntityNPCGolem.class, "npcGolem");
        this.registerNpc(EntityCustomNpc.class, "CustomNpc");
        this.registerNewEntity(EntityChairMount.class, "CustomNpcChairMount", 64, 10, false);
        this.registerNewEntity(EntityProjectile.class, "throwableitem", 64, 3, true);
        this.registerNewEntity(EntityMagicProjectile.class, "magicprojectile", 64, 3, true);
        this.registerNewEntity(EntityAbilityOrb.class, "abilityorb", 64, 1, true);
        this.registerNewEntity(EntityAbilityDisc.class, "abilitydisc", 64, 1, true);
        this.registerNewEntity(EntityAbilityLaser.class, "abilitylaser", 64, 1, true);
        this.registerNewEntity(EntityAbilityBeam.class, "abilitybeam", 160, 1, true);
        this.registerNewEntity(EntityEnergySweeper.class, "abilitysweeper", 64, 3, true);
        this.registerNewEntity(EntityAbilityZone.class, "abilityzone", 64, 3, true);
        this.registerNewEntity(EntityEnergyDome.class, "energydome", 64, 3, true);
        this.registerNewEntity(EntityEnergyPanel.class, "energypanel", 64, 3, true);
        this.registerNewEntity(EntityEnergySlicer.class, "energyslicer", 64, 1, true);
        new RecipeController();
        ForgeChunkManager.setForcedChunkLoadingCallback((Object)this, (ForgeChunkManager.LoadingCallback)new ChunkController());
        new CustomNpcsPermissions();
        new Developer();
        PixelmonHelper.load();
        new AddonManager();
        new AttributeController();
        new MagicController();
        APIRegistry.Instance.register("CNPC+ API", "https://kamkeel.github.io/CustomNPC-Plus/");
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent ev) {
        CustomNpcs.MARKOV_GENERATOR[0] = new MarkovRoman(3);
        CustomNpcs.MARKOV_GENERATOR[1] = new MarkovJapanese(4);
        CustomNpcs.MARKOV_GENERATOR[2] = new MarkovSlavic(3);
        CustomNpcs.MARKOV_GENERATOR[3] = new MarkovWelsh(3);
        CustomNpcs.MARKOV_GENERATOR[4] = new MarkovSaami(3);
        CustomNpcs.MARKOV_GENERATOR[5] = new MarkovOldNorse(4);
        CustomNpcs.MARKOV_GENERATOR[6] = new MarkovAncientGreek(3);
        CustomNpcs.MARKOV_GENERATOR[7] = new MarkovAztec(3);
        CustomNpcs.MARKOV_GENERATOR[8] = new MarkovCustomNPCsClassic(3);
        CustomNpcs.MARKOV_GENERATOR[9] = new MarkovSpanish(3);
        PacketHandler.Instance.registerChannels();
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent ev) {
        proxy.buildPackageIndex();
        if (FMLCommonHandler.instance().getSide().isClient()) {
            AnimationController.Instance.loadClientBuiltIns();
        }
    }

    @Mod.EventHandler
    public void setAboutToStart(FMLServerAboutToStartEvent event) {
        globalJaninoCompiler = null;
        if (FMLCommonHandler.instance().getSide().isClient()) {
            clientJaninoCompiler = null;
        }
        Server = event.getServer();
        AnimationController.Instance.load();
        ChunkController.Instance.clear();
        FactionController.getInstance().load();
        MagicController.getInstance().load();
        new PlayerDataController();
        new TagController();
        new TransportController();
        new GlobalDataController();
        new SpawnController();
        new LinkedNpcController();
        AbilityController.Instance.load();
        TelegraphController.init();
        LinkedItemController.getInstance().load();
        CustomEffectController.getInstance().load();
        new ProfileController();
        ProfileController.registerProfileType(new CNPCData());
        ScriptController.Instance.loadStoredData();
        ScriptController.Instance.loadForgeScripts();
        ScriptController.Instance.loadGlobalNPCScripts();
        ScriptController.Instance.loadPlayerScripts();
        ScriptController.HasStart = false;
        NpcAPI.clearCache();
        PlayerDataController.Instance.clearCache();
        Set names = Block.field_149771_c.func_148742_b();
        for (String name : names) {
            Block block = (Block)Block.field_149771_c.func_82594_a(name);
            if (block instanceof BlockLeavesBase) {
                block.func_149675_a(ConfigMain.LeavesDecayEnabled);
            }
            if (block instanceof BlockVine) {
                block.func_149675_a(ConfigMain.VineGrowthEnabled);
            }
            if (!(block instanceof BlockIce)) continue;
            block.func_149675_a(ConfigMain.IceMeltsEnabled);
        }
    }

    @Mod.EventHandler
    public void started(FMLServerStartedEvent event) {
        RecipeController.Instance.load();
        new BankController();
        new AuctionController();
        DialogController.Instance.load();
        QuestController.Instance.load();
        ScriptController.HasStart = true;
        ServerCloneController.Instance = new ServerCloneController();
        ServerTagMapController.Instance = new ServerTagMapController();
        SyncController.load();
        BukkitUtil.init();
        CustomNpcsPermissions.Instance.init();
    }

    @Mod.EventHandler
    public void stopped(FMLServerStoppedEvent event) {
        ServerCloneController.Instance = null;
        GlobalDataController.Instance.saveData();
        ScriptController.Instance.saveForgeScripts();
        ScriptController.Instance.savePlayerScripts();
        ScriptController.Instance.saveGlobalNpcScripts();
        if (AuctionController.Instance != null) {
            AuctionController.Instance.save();
        }
        EntityEnergyProjectile.clearAllProjectiles();
        EntityEnergyBarrier.clearAllBarriers();
        EnergyChargeTracker.Instance.clear();
        if (FMLCommonHandler.instance().getSide().isClient()) {
            clientJaninoCompiler = null;
        }
        globalJaninoCompiler = null;
    }

    @Mod.EventHandler
    public void serverstart(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new CommandNoppes());
        event.registerServerCommand((ICommand)new CommandKamkeel());
        if (ConfigMain.ProfilesEnabled) {
            event.registerServerCommand((ICommand)new CommandProfile());
        }
    }

    private void registerNpc(Class<? extends Entity> cl, String name) {
        EntityRegistry.registerModEntity(cl, (String)name, (int)NewEntityStartId++, (Object)this, (int)64, (int)3, (boolean)true);
        EntityList.field_75625_b.put(name, cl);
    }

    private void registerNewEntity(Class<? extends Entity> cl, String name, int range, int update, boolean velocity) {
        EntityRegistry.registerModEntity(cl, (String)name, (int)NewEntityStartId++, (Object)this, (int)range, (int)update, (boolean)velocity);
    }

    public static File getWorldSaveDirectory() {
        MinecraftServer server = MinecraftServer.func_71276_C();
        File saves = new File(".");
        if (server != null && !server.func_71262_S()) {
            saves = new File(Minecraft.func_71410_x().field_71412_D, "saves");
        }
        if (server != null) {
            File savedir = new File(new File(saves, server.func_71270_I()), "customnpcs");
            if (!savedir.exists()) {
                savedir.mkdir();
            }
            return savedir;
        }
        return null;
    }

    public static File getWorldSaveDirectory(String s) {
        try {
            File dir = new File(".");
            if (CustomNpcs.getServer() != null) {
                if (!CustomNpcs.getServer().func_71262_S()) {
                    dir = new File(Minecraft.func_71410_x().field_71412_D, "saves");
                }
                dir = new File(new File(dir, CustomNpcs.getServer().func_71270_I()), "customnpcs");
            }
            if (s != null) {
                dir = new File(dir, s);
            }
            if (!dir.exists()) {
                dir.mkdirs();
            }
            return dir;
        }
        catch (Exception var2) {
            LogWriter.error("Error getting worldsave", var2);
            return null;
        }
    }

    public static MinecraftServer getServer() {
        return MinecraftServer.func_71276_C();
    }

    public static Side side() {
        return FMLCommonHandler.instance().getEffectiveSide();
    }

    static {
        NewEntityStartId = 0;
        FreezeNPCs = false;
        MARKOV_GENERATOR = new MarkovGenerator[10];
        InitIgnore = false;
        TickIgnore = false;
        InteractIgnore = false;
        DialogIgnore = false;
        DamagedIgnore = false;
        KilledIgnore = false;
        AttackIgnore = false;
        TargetIgnore = false;
        CollideIgnore = false;
        KillsIgnore = false;
        DialogCloseIgnore = false;
        TimerIgnore = false;
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            clientClassFilterConsumer = new HashSet<Consumer<AdvancedClassFilter>>();
        }
    }
}

