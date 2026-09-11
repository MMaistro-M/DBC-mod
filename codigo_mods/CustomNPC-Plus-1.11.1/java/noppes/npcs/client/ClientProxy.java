/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntityFlameFX
 *  net.minecraft.client.particle.EntitySmokeFX
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.client.resources.IReloadableResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.command.ICommand
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.ReportedException
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.ClientCommandHandler
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.FakePlayer
 */
package noppes.npcs.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import kamkeel.npcs.addon.client.DBCClient;
import kamkeel.npcs.client.command.CommandCNPCDebugger;
import kamkeel.npcs.client.renderer.EnergyChargePreviewRenderer;
import kamkeel.npcs.client.renderer.RenderEnergyBeam;
import kamkeel.npcs.client.renderer.RenderEnergyDisc;
import kamkeel.npcs.client.renderer.RenderEnergyDome;
import kamkeel.npcs.client.renderer.RenderEnergyExplosion;
import kamkeel.npcs.client.renderer.RenderEnergyLaser;
import kamkeel.npcs.client.renderer.RenderEnergyOrb;
import kamkeel.npcs.client.renderer.RenderEnergyPanel;
import kamkeel.npcs.client.renderer.RenderEnergySlicer;
import kamkeel.npcs.client.renderer.RenderSweeper;
import kamkeel.npcs.client.renderer.RenderZone;
import kamkeel.npcs.client.renderer.TelegraphRenderer;
import kamkeel.npcs.client.renderer.lightning.LightningHandler;
import kamkeel.npcs.controllers.data.energycharge.EnergyChargePreviewManager;
import kamkeel.npcs.controllers.data.telegraph.TelegraphManager;
import kamkeel.npcs.entity.EntityAbilityBeam;
import kamkeel.npcs.entity.EntityAbilityDisc;
import kamkeel.npcs.entity.EntityAbilityLaser;
import kamkeel.npcs.entity.EntityAbilityOrb;
import kamkeel.npcs.entity.EntityAbilityZone;
import kamkeel.npcs.entity.EntityEnergyDome;
import kamkeel.npcs.entity.EntityEnergyExplosion;
import kamkeel.npcs.entity.EntityEnergyPanel;
import kamkeel.npcs.entity.EntityEnergySlicer;
import kamkeel.npcs.entity.EntityEnergySweeper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import noppes.npcs.CommonProxy;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.api.IWorld;
import noppes.npcs.blocks.tiles.TileBanner;
import noppes.npcs.blocks.tiles.TileBarrel;
import noppes.npcs.blocks.tiles.TileBeam;
import noppes.npcs.blocks.tiles.TileBigSign;
import noppes.npcs.blocks.tiles.TileBlockAnvil;
import noppes.npcs.blocks.tiles.TileBook;
import noppes.npcs.blocks.tiles.TileCampfire;
import noppes.npcs.blocks.tiles.TileCandle;
import noppes.npcs.blocks.tiles.TileChair;
import noppes.npcs.blocks.tiles.TileCouchWood;
import noppes.npcs.blocks.tiles.TileCouchWool;
import noppes.npcs.blocks.tiles.TileCrate;
import noppes.npcs.blocks.tiles.TileLamp;
import noppes.npcs.blocks.tiles.TileMailbox;
import noppes.npcs.blocks.tiles.TilePedestal;
import noppes.npcs.blocks.tiles.TileScripted;
import noppes.npcs.blocks.tiles.TileShelf;
import noppes.npcs.blocks.tiles.TileShortLamp;
import noppes.npcs.blocks.tiles.TileSign;
import noppes.npcs.blocks.tiles.TileStool;
import noppes.npcs.blocks.tiles.TileTable;
import noppes.npcs.blocks.tiles.TileTallLamp;
import noppes.npcs.blocks.tiles.TileTombstone;
import noppes.npcs.blocks.tiles.TileWallBanner;
import noppes.npcs.blocks.tiles.TileWeaponRack;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.client.ClientTickHandler;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.KeyPressHandler;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.ScriptClientConfig;
import noppes.npcs.client.VersionChecker;
import noppes.npcs.client.controllers.ClientCloneController;
import noppes.npcs.client.controllers.ClientTagMapController;
import noppes.npcs.client.controllers.MusicController;
import noppes.npcs.client.controllers.PresetController;
import noppes.npcs.client.controllers.ScriptSoundController;
import noppes.npcs.client.fx.EntityBigSmokeFX;
import noppes.npcs.client.fx.EntityElementalStaffFX;
import noppes.npcs.client.fx.EntityEnderFX;
import noppes.npcs.client.fx.EntityRainbowFX;
import noppes.npcs.client.gui.GuiBorderBlock;
import noppes.npcs.client.gui.GuiMerchantAdd;
import noppes.npcs.client.gui.GuiNpcDimension;
import noppes.npcs.client.gui.GuiNpcMobSpawner;
import noppes.npcs.client.gui.GuiNpcMobSpawnerFullscreen;
import noppes.npcs.client.gui.GuiNpcMobSpawnerMounter;
import noppes.npcs.client.gui.GuiNpcPather;
import noppes.npcs.client.gui.GuiNpcRedstoneBlock;
import noppes.npcs.client.gui.GuiNpcRemoteEditor;
import noppes.npcs.client.gui.GuiNpcWaypoint;
import noppes.npcs.client.gui.GuiScript;
import noppes.npcs.client.gui.custom.GuiCustom;
import noppes.npcs.client.gui.global.GuiNPCManageAnimations;
import noppes.npcs.client.gui.global.GuiNPCManageBanks;
import noppes.npcs.client.gui.global.GuiNPCManageDialogs;
import noppes.npcs.client.gui.global.GuiNPCManageEffects;
import noppes.npcs.client.gui.global.GuiNPCManageFactions;
import noppes.npcs.client.gui.global.GuiNPCManageLinked;
import noppes.npcs.client.gui.global.GuiNPCManageQuest;
import noppes.npcs.client.gui.global.GuiNPCManageTags;
import noppes.npcs.client.gui.global.GuiNPCManageTransporters;
import noppes.npcs.client.gui.global.GuiNpcManageAbilities;
import noppes.npcs.client.gui.global.GuiNpcManageAuction;
import noppes.npcs.client.gui.global.GuiNpcManageMagic;
import noppes.npcs.client.gui.global.GuiNpcManageRecipes;
import noppes.npcs.client.gui.global.GuiNpcQuestReward;
import noppes.npcs.client.gui.item.GuiNpcMagicBook;
import noppes.npcs.client.gui.item.GuiNpcPaintbrush;
import noppes.npcs.client.gui.mainmenu.GuiNPCGlobalMainMenu;
import noppes.npcs.client.gui.mainmenu.GuiNPCInv;
import noppes.npcs.client.gui.mainmenu.GuiNpcAI;
import noppes.npcs.client.gui.mainmenu.GuiNpcAdvanced;
import noppes.npcs.client.gui.mainmenu.GuiNpcDisplay;
import noppes.npcs.client.gui.mainmenu.GuiNpcStats;
import noppes.npcs.client.gui.player.GuiAuctionBidding;
import noppes.npcs.client.gui.player.GuiAuctionListing;
import noppes.npcs.client.gui.player.GuiAuctionSell;
import noppes.npcs.client.gui.player.GuiAuctionTrades;
import noppes.npcs.client.gui.player.GuiBigSign;
import noppes.npcs.client.gui.player.GuiCrate;
import noppes.npcs.client.gui.player.GuiMailbox;
import noppes.npcs.client.gui.player.GuiMailmanWrite;
import noppes.npcs.client.gui.player.GuiNPCBankChest;
import noppes.npcs.client.gui.player.GuiNPCTrader;
import noppes.npcs.client.gui.player.GuiNpcAnvil;
import noppes.npcs.client.gui.player.GuiNpcCarpentryBench;
import noppes.npcs.client.gui.player.GuiNpcFollower;
import noppes.npcs.client.gui.player.GuiNpcFollowerHire;
import noppes.npcs.client.gui.player.GuiTransportSelection;
import noppes.npcs.client.gui.player.companion.GuiNpcCompanionInv;
import noppes.npcs.client.gui.player.companion.GuiNpcCompanionStats;
import noppes.npcs.client.gui.player.companion.GuiNpcCompanionTalents;
import noppes.npcs.client.gui.player.modern.BlurEventHandler;
import noppes.npcs.client.gui.questtypes.GuiNpcQuestTypeItem;
import noppes.npcs.client.gui.roles.GuiNpcBankSetup;
import noppes.npcs.client.gui.roles.GuiNpcFollowerSetup;
import noppes.npcs.client.gui.roles.GuiNpcItemGiver;
import noppes.npcs.client.gui.roles.GuiNpcTraderSetup;
import noppes.npcs.client.gui.roles.GuiNpcTransporter;
import noppes.npcs.client.gui.script.GuiScriptGlobal;
import noppes.npcs.client.gui.script.GuiScriptInterface;
import noppes.npcs.client.gui.util.script.PackageFinder;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.type.ClassIndex;
import noppes.npcs.client.model.ModelNPCGolem;
import noppes.npcs.client.model.ModelNpcCrystal;
import noppes.npcs.client.model.ModelNpcDragon;
import noppes.npcs.client.model.ModelNpcSlime;
import noppes.npcs.client.model.ModelSkirtArmor;
import noppes.npcs.client.renderer.NpcItemRenderer;
import noppes.npcs.client.renderer.RenderCustomNpc;
import noppes.npcs.client.renderer.RenderNPCHumanMale;
import noppes.npcs.client.renderer.RenderNPCPony;
import noppes.npcs.client.renderer.RenderNpcCrystal;
import noppes.npcs.client.renderer.RenderNpcDragon;
import noppes.npcs.client.renderer.RenderNpcSlime;
import noppes.npcs.client.renderer.RenderProjectile;
import noppes.npcs.client.renderer.blocks.BlockBannerRenderer;
import noppes.npcs.client.renderer.blocks.BlockBarrelRenderer;
import noppes.npcs.client.renderer.blocks.BlockBeamRenderer;
import noppes.npcs.client.renderer.blocks.BlockBigSignRenderer;
import noppes.npcs.client.renderer.blocks.BlockBloodRenderer;
import noppes.npcs.client.renderer.blocks.BlockBookRenderer;
import noppes.npcs.client.renderer.blocks.BlockBorderRenderer;
import noppes.npcs.client.renderer.blocks.BlockCampfireRenderer;
import noppes.npcs.client.renderer.blocks.BlockCandleRenderer;
import noppes.npcs.client.renderer.blocks.BlockCarpentryBenchRenderer;
import noppes.npcs.client.renderer.blocks.BlockChairRenderer;
import noppes.npcs.client.renderer.blocks.BlockCouchWoodRenderer;
import noppes.npcs.client.renderer.blocks.BlockCouchWoolRenderer;
import noppes.npcs.client.renderer.blocks.BlockCrateRenderer;
import noppes.npcs.client.renderer.blocks.BlockLanternRenderer;
import noppes.npcs.client.renderer.blocks.BlockMailboxRenderer;
import noppes.npcs.client.renderer.blocks.BlockPedestalRenderer;
import noppes.npcs.client.renderer.blocks.BlockScriptedRenderer;
import noppes.npcs.client.renderer.blocks.BlockShelfRenderer;
import noppes.npcs.client.renderer.blocks.BlockShortLampRenderer;
import noppes.npcs.client.renderer.blocks.BlockSignRenderer;
import noppes.npcs.client.renderer.blocks.BlockStoolRenderer;
import noppes.npcs.client.renderer.blocks.BlockTableRenderer;
import noppes.npcs.client.renderer.blocks.BlockTallLampRenderer;
import noppes.npcs.client.renderer.blocks.BlockTombstoneRenderer;
import noppes.npcs.client.renderer.blocks.BlockWallBannerRenderer;
import noppes.npcs.client.renderer.blocks.BlockWeaponRackRenderer;
import noppes.npcs.client.renderer.items.ItemBannerRenderer;
import noppes.npcs.client.renderer.items.ItemBannerWallRenderer;
import noppes.npcs.client.renderer.items.ItemCouchWoolRenderer;
import noppes.npcs.client.renderer.items.ItemCustomRenderer;
import noppes.npcs.client.renderer.items.ItemShortLampRenderer;
import noppes.npcs.client.renderer.items.ItemTallLampRenderer;
import noppes.npcs.client.renderer.items.ItemToolRenderer;
import noppes.npcs.client.renderer.items.ScriptedBlockItemRenderer;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.config.ConfigItem;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.config.StringCache;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.containers.ContainerAnvilRepair;
import noppes.npcs.containers.ContainerAuctionBidding;
import noppes.npcs.containers.ContainerAuctionListing;
import noppes.npcs.containers.ContainerAuctionSell;
import noppes.npcs.containers.ContainerAuctionTrades;
import noppes.npcs.containers.ContainerCarpentryBench;
import noppes.npcs.containers.ContainerCrate;
import noppes.npcs.containers.ContainerCustomGui;
import noppes.npcs.containers.ContainerMail;
import noppes.npcs.containers.ContainerManageAuction;
import noppes.npcs.containers.ContainerManageBanks;
import noppes.npcs.containers.ContainerManageRecipes;
import noppes.npcs.containers.ContainerNPCBankInterface;
import noppes.npcs.containers.ContainerNPCCompanion;
import noppes.npcs.containers.ContainerNPCFollower;
import noppes.npcs.containers.ContainerNPCFollowerHire;
import noppes.npcs.containers.ContainerNPCFollowerSetup;
import noppes.npcs.containers.ContainerNPCInv;
import noppes.npcs.containers.ContainerNPCTrader;
import noppes.npcs.containers.ContainerNPCTraderSetup;
import noppes.npcs.containers.ContainerNpcItemGiver;
import noppes.npcs.containers.ContainerNpcQuestReward;
import noppes.npcs.containers.ContainerNpcQuestTypeItem;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCGolem;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.EntityNpcCrystal;
import noppes.npcs.entity.EntityNpcDragon;
import noppes.npcs.entity.EntityNpcPony;
import noppes.npcs.entity.EntityNpcSlime;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;
import noppes.npcs.items.ItemLinked;
import noppes.npcs.items.ItemNpcTool;
import noppes.npcs.items.ItemScripted;
import noppes.npcs.scripted.item.ScriptCustomItem;
import tconstruct.client.tabs.InventoryTabCustomNpc;
import tconstruct.client.tabs.InventoryTabVanilla;
import tconstruct.client.tabs.TabRegistry;

public class ClientProxy
extends CommonProxy {
    public static KeyBinding NPCButton;
    public static KeyBinding SpecialKey;
    public static KeyBinding AbilityHudKey;
    public static KeyBinding AbilityNextKey;
    public static KeyBinding AbilityPrevKey;
    public static final Random RAND;
    public static FontContainer Font;
    private ModelSkirtArmor model = new ModelSkirtArmor();

    @Override
    public void load() {
        Font = new FontContainer(ConfigClient.FontType, ConfigClient.FontSize);
        this.createFolders();
        new MusicController();
        new ScriptSoundController();
        RenderingRegistry.registerEntityRenderingHandler(EntityNpcPony.class, (Render)new RenderNPCPony());
        RenderingRegistry.registerEntityRenderingHandler(EntityNpcCrystal.class, (Render)new RenderNpcCrystal(new ModelNpcCrystal(0.5f)));
        RenderingRegistry.registerEntityRenderingHandler(EntityNpcDragon.class, (Render)new RenderNpcDragon(new ModelNpcDragon(0.0f), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityNpcSlime.class, (Render)new RenderNpcSlime(new ModelNpcSlime(16), new ModelNpcSlime(0), 0.25f));
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectile.class, (Render)new RenderProjectile());
        RenderingRegistry.registerEntityRenderingHandler(EntityAbilityOrb.class, (Render)new RenderEnergyOrb());
        RenderingRegistry.registerEntityRenderingHandler(EntityAbilityDisc.class, (Render)new RenderEnergyDisc());
        RenderingRegistry.registerEntityRenderingHandler(EntityAbilityLaser.class, (Render)new RenderEnergyLaser());
        RenderingRegistry.registerEntityRenderingHandler(EntityAbilityBeam.class, (Render)new RenderEnergyBeam());
        RenderingRegistry.registerEntityRenderingHandler(EntityEnergySweeper.class, (Render)new RenderSweeper());
        RenderingRegistry.registerEntityRenderingHandler(EntityAbilityZone.class, (Render)new RenderZone());
        RenderingRegistry.registerEntityRenderingHandler(EntityEnergyDome.class, (Render)new RenderEnergyDome());
        RenderingRegistry.registerEntityRenderingHandler(EntityEnergyPanel.class, (Render)new RenderEnergyPanel());
        RenderingRegistry.registerEntityRenderingHandler(EntityEnergySlicer.class, (Render)new RenderEnergySlicer());
        RenderingRegistry.registerEntityRenderingHandler(EntityEnergyExplosion.class, (Render)new RenderEnergyExplosion());
        RenderingRegistry.registerEntityRenderingHandler(EntityCustomNpc.class, (Render)new RenderCustomNpc());
        RenderingRegistry.registerEntityRenderingHandler(EntityNPCGolem.class, (Render)new RenderNPCHumanMale(new ModelNPCGolem(0.0f), new ModelNPCGolem(1.0f), new ModelNPCGolem(0.5f)));
        FMLCommonHandler.instance().bus().register((Object)new ClientTickHandler());
        FMLCommonHandler.instance().bus().register((Object)new KeyPressHandler());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockAnvil.class, (TileEntitySpecialRenderer)new BlockCarpentryBenchRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMailbox.class, (TileEntitySpecialRenderer)new BlockMailboxRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileScripted.class, (TileEntitySpecialRenderer)new BlockScriptedRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)CustomItems.scripted), (IItemRenderer)new ScriptedBlockItemRenderer());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockBorderRenderer());
        BlurEventHandler blurEventHandler = new BlurEventHandler();
        MinecraftForge.EVENT_BUS.register((Object)blurEventHandler);
        FMLCommonHandler.instance().bus().register((Object)blurEventHandler);
        if (!ConfigItem.DisableExtraBlock) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileBanner.class, (TileEntitySpecialRenderer)new BlockBannerRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileWallBanner.class, (TileEntitySpecialRenderer)new BlockWallBannerRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileTallLamp.class, (TileEntitySpecialRenderer)new BlockTallLampRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileShortLamp.class, (TileEntitySpecialRenderer)new BlockShortLampRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileChair.class, (TileEntitySpecialRenderer)new BlockChairRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileWeaponRack.class, (TileEntitySpecialRenderer)new BlockWeaponRackRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileCrate.class, (TileEntitySpecialRenderer)new BlockCrateRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileCouchWool.class, (TileEntitySpecialRenderer)new BlockCouchWoolRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileCouchWood.class, (TileEntitySpecialRenderer)new BlockCouchWoodRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileTable.class, (TileEntitySpecialRenderer)new BlockTableRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileCandle.class, (TileEntitySpecialRenderer)new BlockCandleRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileLamp.class, (TileEntitySpecialRenderer)new BlockLanternRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileStool.class, (TileEntitySpecialRenderer)new BlockStoolRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileBigSign.class, (TileEntitySpecialRenderer)new BlockBigSignRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileBarrel.class, (TileEntitySpecialRenderer)new BlockBarrelRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileCampfire.class, (TileEntitySpecialRenderer)new BlockCampfireRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileTombstone.class, (TileEntitySpecialRenderer)new BlockTombstoneRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileShelf.class, (TileEntitySpecialRenderer)new BlockShelfRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileSign.class, (TileEntitySpecialRenderer)new BlockSignRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileBeam.class, (TileEntitySpecialRenderer)new BlockBeamRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileBook.class, (TileEntitySpecialRenderer)new BlockBookRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TilePedestal.class, (TileEntitySpecialRenderer)new BlockPedestalRenderer());
            RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockBloodRenderer());
            MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)CustomItems.couchWool), (IItemRenderer)new ItemCouchWoolRenderer());
            MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)CustomItems.banner), (IItemRenderer)new ItemBannerRenderer());
            MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)CustomItems.wallBanner), (IItemRenderer)new ItemBannerWallRenderer());
            MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)CustomItems.shortLamp), (IItemRenderer)new ItemShortLampRenderer());
            MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)CustomItems.tallLamp), (IItemRenderer)new ItemTallLampRenderer());
        }
        Minecraft mc = Minecraft.func_71410_x();
        NPCButton = new KeyBinding("NPC Inventory", 49, "key.categories.customnpc");
        SpecialKey = new KeyBinding("key.customnpcs.special", 41, "key.categories.customnpc");
        ClientRegistry.registerKeyBinding((KeyBinding)NPCButton);
        ClientRegistry.registerKeyBinding((KeyBinding)SpecialKey);
        AbilityHudKey = new KeyBinding("key.customnpcs.abilityHudKey", 56, "key.categories.customnpc");
        ClientRegistry.registerKeyBinding((KeyBinding)AbilityHudKey);
        AbilityNextKey = new KeyBinding("key.customnpcs.abilityNext", 0, "key.categories.customnpc");
        AbilityPrevKey = new KeyBinding("key.customnpcs.abilityPrev", 0, "key.categories.customnpc");
        ClientRegistry.registerKeyBinding((KeyBinding)AbilityNextKey);
        ClientRegistry.registerKeyBinding((KeyBinding)AbilityPrevKey);
        new PresetController(CustomNpcs.Dir);
        if (ConfigMain.EnableUpdateChecker) {
            VersionChecker checker = new VersionChecker();
            checker.start();
        }
        ClientCloneController.Instance = new ClientCloneController();
        ClientTagMapController.Instance = new ClientTagMapController();
        MinecraftForge.EVENT_BUS.register((Object)new ClientEventHandler());
        ClientCommandHandler.instance.func_71560_a((ICommand)new CommandCNPCDebugger());
        TelegraphManager.initClient();
        MinecraftForge.EVENT_BUS.register((Object)new TelegraphRenderer());
        EnergyChargePreviewManager.initClient();
        MinecraftForge.EVENT_BUS.register((Object)new EnergyChargePreviewRenderer());
        MinecraftForge.EVENT_BUS.register((Object)new LightningHandler());
        if (ConfigClient.InventoryGuiEnabled) {
            MinecraftForge.EVENT_BUS.register((Object)new TabRegistry());
            if (TabRegistry.getTabList().isEmpty()) {
                TabRegistry.registerTab(new InventoryTabVanilla());
            }
            TabRegistry.registerTab(new InventoryTabCustomNpc());
        }
    }

    @Override
    public FakePlayer getCommandPlayer(IWorld world) {
        return (FakePlayer)new EntityCustomNpc((World)world.getMCWorld()).getFakePlayer();
    }

    private void createFolders() {
        File cache;
        File json;
        File check;
        File file = new File(CustomNpcs.Dir, "assets/customnpcs");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!(check = new File(file, "sounds")).exists()) {
            check.mkdir();
        }
        if (!(json = new File(file, "sounds.json")).exists()) {
            try {
                json.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(json));
                writer.write("{\n\n}");
                writer.close();
            }
            catch (IOException writer) {
                // empty catch block
            }
        }
        if (!(check = new File(file, "textures")).exists()) {
            check.mkdir();
        }
        if (!(cache = new File(check, "cache")).exists()) {
            cache.mkdir();
        }
        ((IReloadableResourceManager)Minecraft.func_71410_x().func_110442_L()).func_110542_a((IResourceManagerReloadListener)new CustomNpcResourceListener());
    }

    @Override
    public PlayerData getPlayerData(EntityPlayer player) {
        EntityClientPlayerMP local = Minecraft.func_71410_x().field_71439_g;
        if (local != null && player.func_110124_au().equals(local.func_110124_au()) && ClientCacheHandler.playerData != null) {
            if (ClientCacheHandler.playerData.player != player) {
                ClientCacheHandler.playerData.player = player;
            }
            return ClientCacheHandler.playerData;
        }
        return null;
    }

    @Override
    public AnimationData getClientAnimationData(Entity entity) {
        if (entity instanceof EntityPlayer) {
            return ClientCacheHandler.playerAnimations.get(entity.func_110124_au());
        }
        if (entity instanceof EntityNPCInterface) {
            return ((EntityNPCInterface)entity).display.animationData;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID > EnumGuiType.values().length) {
            return null;
        }
        EnumGuiType gui = EnumGuiType.values()[ID];
        EntityNPCInterface npc = NoppesUtil.getLastNpc();
        Container container = this.getContainer(gui, player, x, y, z, npc);
        return this.getGui(npc, gui, container, x, y, z);
    }

    private GuiScreen getGui(EntityNPCInterface npc, EnumGuiType gui, Container container, int x, int y, int z) {
        if (gui == EnumGuiType.MainMenuDisplay) {
            if (npc != null) {
                return new GuiNpcDisplay(npc);
            }
            Minecraft.func_71410_x().field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("Unable to find npc"));
        } else {
            if (gui == EnumGuiType.MainMenuStats) {
                return new GuiNpcStats(npc);
            }
            if (gui == EnumGuiType.MainMenuInv) {
                return new GuiNPCInv(npc, (ContainerNPCInv)container);
            }
            if (gui == EnumGuiType.MainMenuAdvanced) {
                return new GuiNpcAdvanced(npc);
            }
            if (gui == EnumGuiType.QuestReward) {
                return new GuiNpcQuestReward(npc, (ContainerNpcQuestReward)container);
            }
            if (gui == EnumGuiType.QuestItem) {
                return new GuiNpcQuestTypeItem(npc, (ContainerNpcQuestTypeItem)container);
            }
            if (gui == EnumGuiType.MovingPath) {
                return new GuiNpcPather(npc);
            }
            if (gui == EnumGuiType.ManageFactions) {
                return new GuiNPCManageFactions(npc);
            }
            if (gui == EnumGuiType.ManageCustomForms) {
                return DBCClient.Instance.manageCustomForms(npc);
            }
            if (gui == EnumGuiType.ManageCustomAuras) {
                return DBCClient.Instance.manageCustomAuras(npc);
            }
            if (gui == EnumGuiType.ManageTags) {
                return new GuiNPCManageTags(npc);
            }
            if (gui == EnumGuiType.ManageAnimations) {
                boolean save;
                EntityCustomNpc animNpc;
                if (npc != null) {
                    animNpc = new EntityCustomNpc(npc.field_70170_p);
                    animNpc.func_82141_a((Entity)npc, true);
                    animNpc.display.showName = 1;
                    animNpc.display.showBossBar = 0;
                    save = true;
                } else {
                    animNpc = new EntityCustomNpc((World)Minecraft.func_71410_x().field_71441_e);
                    animNpc.display.texture = "customnpcs:textures/entity/humanmale/AnimationBody.png";
                    save = false;
                }
                return new GuiNPCManageAnimations(animNpc, save, npc != null);
            }
            if (gui == EnumGuiType.ManageLinked) {
                return new GuiNPCManageLinked(npc);
            }
            if (gui == EnumGuiType.ManageMagic) {
                return new GuiNpcManageMagic(npc);
            }
            if (gui == EnumGuiType.ManageTransport) {
                return new GuiNPCManageTransporters(npc);
            }
            if (gui == EnumGuiType.ManageRecipes) {
                return new GuiNpcManageRecipes(npc, (ContainerManageRecipes)container);
            }
            if (gui == EnumGuiType.ManageAuction) {
                return new GuiNpcManageAuction(npc, (ContainerManageAuction)container);
            }
            if (gui == EnumGuiType.ManageDialogs) {
                return new GuiNPCManageDialogs(npc);
            }
            if (gui == EnumGuiType.ManageQuests) {
                return new GuiNPCManageQuest(npc);
            }
            if (gui == EnumGuiType.ManageBanks) {
                return new GuiNPCManageBanks(npc, (ContainerManageBanks)container);
            }
            if (gui == EnumGuiType.ManageEffects) {
                return new GuiNPCManageEffects(npc);
            }
            if (gui == EnumGuiType.ManageAbilities) {
                EntityCustomNpc abilityNpc;
                if (npc != null) {
                    abilityNpc = new EntityCustomNpc(npc.field_70170_p);
                    abilityNpc.func_82141_a((Entity)npc, true);
                    abilityNpc.display.showName = 1;
                    abilityNpc.display.showBossBar = 0;
                } else {
                    abilityNpc = new EntityCustomNpc((World)Minecraft.func_71410_x().field_71441_e);
                    abilityNpc.display.texture = "customnpcs:textures/entity/humanmale/AnimationBody.png";
                    abilityNpc.field_70131_O = 1.8f;
                    abilityNpc.field_70130_N = 0.6f;
                }
                return new GuiNpcManageAbilities((EntityNPCInterface)abilityNpc, npc != null);
            }
            if (gui == EnumGuiType.MainMenuGlobal) {
                return new GuiNPCGlobalMainMenu(npc);
            }
            if (gui == EnumGuiType.MainMenuAI) {
                return new GuiNpcAI(npc);
            }
            if (gui == EnumGuiType.PlayerFollowerHire) {
                return new GuiNpcFollowerHire(npc, (ContainerNPCFollowerHire)container);
            }
            if (gui == EnumGuiType.PlayerFollower) {
                return new GuiNpcFollower(npc, (ContainerNPCFollower)container);
            }
            if (gui == EnumGuiType.PlayerTrader) {
                return new GuiNPCTrader(npc, (ContainerNPCTrader)container);
            }
            if (gui == EnumGuiType.PlayerAuction) {
                return new GuiAuctionListing(npc, (ContainerAuctionListing)container);
            }
            if (gui == EnumGuiType.PlayerAuctionSell) {
                return new GuiAuctionSell(npc, (ContainerAuctionSell)container);
            }
            if (gui == EnumGuiType.PlayerAuctionTrades) {
                return new GuiAuctionTrades(npc, (ContainerAuctionTrades)container);
            }
            if (gui == EnumGuiType.PlayerAuctionBidding) {
                return new GuiAuctionBidding(npc, (ContainerAuctionBidding)container);
            }
            if (gui == EnumGuiType.PlayerBankSmall || gui == EnumGuiType.PlayerBankUnlock || gui == EnumGuiType.PlayerBankUprade || gui == EnumGuiType.PlayerBankLarge) {
                return new GuiNPCBankChest(npc, (ContainerNPCBankInterface)container);
            }
            if (gui == EnumGuiType.PlayerTransporter) {
                return new GuiTransportSelection(npc);
            }
            if (gui == EnumGuiType.Script) {
                return new GuiScript(npc);
            }
            if (gui == EnumGuiType.ScriptItem) {
                return GuiScriptInterface.create(null, new ScriptCustomItem(new ItemStack(CustomItems.scripted_item)));
            }
            if (gui == EnumGuiType.PlayerCarpentryBench) {
                return new GuiNpcCarpentryBench((ContainerCarpentryBench)container);
            }
            if (gui == EnumGuiType.PlayerAnvil) {
                return new GuiNpcAnvil((ContainerAnvilRepair)container);
            }
            if (gui == EnumGuiType.SetupFollower) {
                return new GuiNpcFollowerSetup(npc, (ContainerNPCFollowerSetup)container);
            }
            if (gui == EnumGuiType.SetupItemGiver) {
                return new GuiNpcItemGiver(npc, (ContainerNpcItemGiver)container);
            }
            if (gui == EnumGuiType.SetupTrader) {
                return new GuiNpcTraderSetup(npc, (ContainerNPCTraderSetup)container);
            }
            if (gui == EnumGuiType.SetupTransporter) {
                return new GuiNpcTransporter(npc);
            }
            if (gui == EnumGuiType.SetupBank) {
                return new GuiNpcBankSetup(npc);
            }
            if (gui == EnumGuiType.NpcRemote && Minecraft.func_71410_x().field_71462_r == null) {
                return new GuiNpcRemoteEditor();
            }
            if (gui == EnumGuiType.ScriptEvent && Minecraft.func_71410_x().field_71462_r == null) {
                return new GuiScriptGlobal();
            }
            if (gui == EnumGuiType.PlayerMailman) {
                return new GuiMailmanWrite((ContainerMail)container, x == 1, y == 1);
            }
            if (gui == EnumGuiType.PlayerMailbox) {
                return new GuiMailbox();
            }
            if (gui == EnumGuiType.MerchantAdd) {
                return new GuiMerchantAdd();
            }
            if (gui == EnumGuiType.Crate) {
                return new GuiCrate((ContainerCrate)container);
            }
            if (gui == EnumGuiType.NpcDimensions) {
                return new GuiNpcDimension();
            }
            if (gui == EnumGuiType.Border) {
                return new GuiBorderBlock(x, y, z);
            }
            if (gui == EnumGuiType.BigSign) {
                return new GuiBigSign(x, y, z);
            }
            if (gui == EnumGuiType.RedstoneBlock) {
                return new GuiNpcRedstoneBlock(x, y, z);
            }
            if (gui == EnumGuiType.Cloner) {
                return GuiNpcMobSpawner.isFullscreen ? new GuiNpcMobSpawnerFullscreen(x, y, z) : new GuiNpcMobSpawner(x, y, z);
            }
            if (gui == EnumGuiType.MobSpawnerMounter) {
                return new GuiNpcMobSpawnerMounter(x, y, z);
            }
            if (gui == EnumGuiType.Waypoint) {
                return new GuiNpcWaypoint(x, y, z);
            }
            if (gui == EnumGuiType.Companion) {
                return new GuiNpcCompanionStats(npc);
            }
            if (gui == EnumGuiType.CompanionTalent) {
                return new GuiNpcCompanionTalents(npc);
            }
            if (gui == EnumGuiType.CompanionInv) {
                return new GuiNpcCompanionInv(npc, (ContainerNPCCompanion)container);
            }
            if (gui == EnumGuiType.CustomGui) {
                return new GuiCustom((ContainerCustomGui)container);
            }
            if (gui == EnumGuiType.ScriptBlock) {
                return this.getScriptBlockGui(x, y, z);
            }
            if (gui == EnumGuiType.Paintbrush) {
                return new GuiNpcPaintbrush();
            }
            if (gui == EnumGuiType.MagicBook) {
                return new GuiNpcMagicBook();
            }
            if (gui == EnumGuiType.GlobalRemote) {
                return new GuiNPCGlobalMainMenu(null);
            }
        }
        return null;
    }

    @Override
    public void openGui(int i, int j, int k, EnumGuiType gui, EntityPlayer player) {
        Minecraft minecraft = Minecraft.func_71410_x();
        if (minecraft.field_71439_g != player) {
            return;
        }
        GuiScreen guiscreen = this.getGui(null, gui, null, i, j, k);
        if (guiscreen != null) {
            minecraft.func_147108_a(guiscreen);
        }
    }

    @Override
    public void openGui(EntityNPCInterface npc, EnumGuiType gui) {
        this.openGui(npc, gui, 0, 0, 0);
    }

    @Override
    public void openGui(EntityNPCInterface npc, EnumGuiType gui, int x, int y, int z) {
        Minecraft minecraft = Minecraft.func_71410_x();
        Container container = this.getContainer(gui, (EntityPlayer)minecraft.field_71439_g, x, y, z, npc);
        GuiScreen guiscreen = this.getGui(npc, gui, container, x, y, z);
        if (guiscreen != null) {
            minecraft.func_147108_a(guiscreen);
        }
    }

    @Override
    public void openGui(EntityPlayer player, Object guiscreen) {
        Minecraft minecraft = Minecraft.func_71410_x();
        if (!player.field_70170_p.field_72995_K || !(guiscreen instanceof GuiScreen)) {
            return;
        }
        if (guiscreen != null) {
            minecraft.func_147108_a((GuiScreen)guiscreen);
        }
    }

    private GuiScreen getScriptBlockGui(int x, int y, int z) {
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        if (world == null) {
            return null;
        }
        TileEntity tile = world.func_147438_o(x, y, z);
        if (!(tile instanceof TileScripted)) {
            return null;
        }
        return GuiScriptInterface.create(null, (TileScripted)tile);
    }

    @Override
    public void spawnParticle(EntityLivingBase player, String string, Object ... ob) {
        block4: {
            Random rand;
            double height;
            Minecraft minecraft;
            ModelPartData particles;
            block5: {
                block3: {
                    if (!string.equals("Spell")) break block3;
                    int color = (Integer)ob[0];
                    int number = (Integer)ob[1];
                    for (int i = 0; i < number; ++i) {
                        Random rand2 = player.field_70170_p.field_73012_v;
                        double x = (rand2.nextDouble() - 0.5) * (double)player.field_70130_N;
                        double y = player.func_70047_e();
                        double z = (rand2.nextDouble() - 0.5) * (double)player.field_70130_N;
                        double f = (rand2.nextDouble() - 0.5) * 2.0;
                        double f1 = -rand2.nextDouble();
                        double f2 = (rand2.nextDouble() - 0.5) * 2.0;
                        Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)new EntityElementalStaffFX(player, x, y, z, f, f1, f2, color));
                    }
                    break block4;
                }
                if (!string.equals("ModelData")) break block4;
                ModelData data = (ModelData)ob[0];
                particles = (ModelPartData)ob[1];
                EntityCustomNpc npc = (EntityCustomNpc)player;
                minecraft = Minecraft.func_71410_x();
                height = npc.func_70033_W() + (double)data.getBodyY();
                rand = npc.func_70681_au();
                if (particles.type != 0) break block5;
                for (int i = 0; i < 2; ++i) {
                    EntityEnderFX fx = new EntityEnderFX(npc, (rand.nextDouble() - 0.5) * (double)player.field_70130_N, rand.nextDouble() * (double)player.field_70131_O - height - 0.25, (rand.nextDouble() - 0.5) * (double)player.field_70130_N, (rand.nextDouble() - 0.5) * 2.0, -rand.nextDouble(), (rand.nextDouble() - 0.5) * 2.0, particles);
                    minecraft.field_71452_i.func_78873_a((EntityFX)fx);
                }
                break block4;
            }
            if (particles.type != 1) break block4;
            for (int i = 0; i < 2; ++i) {
                double x = player.field_70165_t + (rand.nextDouble() - 0.5) * 0.9;
                double y = player.field_70163_u + rand.nextDouble() * 1.9 - 0.25 - height;
                double z = player.field_70161_v + (rand.nextDouble() - 0.5) * 0.9;
                double f = (rand.nextDouble() - 0.5) * 2.0;
                double f1 = -rand.nextDouble();
                double f2 = (rand.nextDouble() - 0.5) * 2.0;
                minecraft.field_71452_i.func_78873_a((EntityFX)new EntityRainbowFX(player.field_70170_p, x, y, z, f, f1, f2));
            }
        }
    }

    @Override
    public ModelBiped getSkirtModel() {
        return this.model;
    }

    @Override
    public boolean hasClient() {
        return true;
    }

    @Override
    public EntityPlayer getPlayer() {
        return Minecraft.func_71410_x().field_71439_g;
    }

    @Override
    public void registerItem(Item item) {
        if (item instanceof ItemScripted || item instanceof ItemLinked) {
            MinecraftForgeClient.registerItemRenderer((Item)item, (IItemRenderer)new ItemCustomRenderer());
        } else if (item instanceof ItemNpcTool) {
            MinecraftForgeClient.registerItemRenderer((Item)item, (IItemRenderer)new ItemToolRenderer());
        } else {
            MinecraftForgeClient.registerItemRenderer((Item)item, (IItemRenderer)new NpcItemRenderer());
        }
    }

    public static void bindTexture(ResourceLocation location) {
        try {
            if (location == null) {
                return;
            }
            TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
            if (location != null) {
                texturemanager.func_110577_a(location);
            }
        }
        catch (NullPointerException nullPointerException) {
        }
        catch (ReportedException reportedException) {
            // empty catch block
        }
    }

    @Override
    public void spawnParticle(String particle, double x, double y, double z, double motionX, double motionY, double motionZ, float scale) {
        RenderGlobal render = Minecraft.func_71410_x().field_71438_f;
        EntityFX fx = render.func_72726_b(particle, x, y, z, motionX, motionY, motionZ);
        if (fx == null) {
            return;
        }
        if (particle.equals("flame")) {
            ObfuscationReflectionHelper.setPrivateValue(EntityFlameFX.class, (Object)((EntityFlameFX)fx), (Object)Float.valueOf(scale), (int)0);
        } else if (particle.equals("smoke")) {
            ObfuscationReflectionHelper.setPrivateValue(EntitySmokeFX.class, (Object)((EntitySmokeFX)fx), (Object)Float.valueOf(scale), (int)0);
        }
    }

    @Override
    public void generateBigSmokeParticles(World world, int x, int y, int z, boolean signalFire) {
        if (RAND.nextFloat() < 0.11f) {
            float[] colours = new float[]{};
            for (int i = 0; i < RAND.nextInt(2) + 1; ++i) {
                Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)new EntityBigSmokeFX(world, x, y, z, signalFire, colours));
            }
        }
    }

    @Override
    public String getAchievementDesc(Achievement achievement) {
        return achievement.func_75989_e();
    }

    @Override
    public boolean isGUIOpen() {
        return Minecraft.func_71410_x().field_71462_r != null;
    }

    @Override
    public void buildPackageIndex() {
        try {
            PackageFinder.init(Thread.currentThread().getContextClassLoader());
            ClassIndex.init();
            JSTypeRegistry.getInstance().initializeFromResources();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    @Override
    public boolean isScriptingEnabled() {
        return ConfigClient.AllowClientScripts && ScriptClientConfig.isScriptingEnabled();
    }

    @Override
    public boolean isRunLoadedScriptsFirst() {
        return ScriptClientConfig.isRunLoadedScriptsFirst();
    }

    @Override
    public boolean isGlobalPlayerScripts() {
        return ConfigClient.AllowClientScripts && ScriptClientConfig.isGlobalPlayerScripts();
    }

    @Override
    public boolean isGlobalForgeScripts() {
        return ConfigClient.AllowClientScripts && ScriptClientConfig.isGlobalForgeScripts();
    }

    @Override
    public boolean isGlobalNPCScripts() {
        return ConfigClient.AllowClientScripts && ScriptClientConfig.isGlobalNPCScripts();
    }

    static {
        RAND = new Random();
    }

    public static class FontContainer {
        public StringCache textFont = null;
        public boolean useCustomFont = true;

        private FontContainer() {
        }

        public FontContainer(String fontType, int fontSize) {
            this.textFont = new StringCache();
            this.textFont.setDefaultFont("Arial", fontSize, true);
            this.useCustomFont = !fontType.equalsIgnoreCase("minecraft");
            try {
                if (!this.useCustomFont || fontType.isEmpty() || fontType.equalsIgnoreCase("default")) {
                    this.textFont.setCustomFont(new ResourceLocation("customnpcs", "OpenSans.ttf"), fontSize, true);
                } else {
                    this.textFont.setDefaultFont(fontType, fontSize, true);
                }
            }
            catch (Exception e) {
                LogWriter.info("Failed loading font so using Arial");
            }
        }

        public int height() {
            if (this.useCustomFont) {
                return this.textFont.fontHeight;
            }
            return Minecraft.func_71410_x().field_71466_p.field_78288_b;
        }

        public int width(String text) {
            if (this.useCustomFont) {
                return this.textFont.getStringWidth(text);
            }
            return Minecraft.func_71410_x().field_71466_p.func_78256_a(text);
        }

        public int width(String text, int fontStyle) {
            if (this.useCustomFont) {
                return this.textFont.getStringWidth(text, fontStyle);
            }
            if (fontStyle == 0) {
                return Minecraft.func_71410_x().field_71466_p.func_78256_a(text);
            }
            StringBuilder sb = new StringBuilder();
            if ((fontStyle & 1) != 0) {
                sb.append('\u00a7').append('l');
            }
            if ((fontStyle & 2) != 0) {
                sb.append('\u00a7').append('o');
            }
            sb.append(text);
            return Minecraft.func_71410_x().field_71466_p.func_78256_a(sb.toString());
        }

        public FontContainer copy() {
            FontContainer font = new FontContainer();
            font.textFont = this.textFont;
            font.useCustomFont = this.useCustomFont;
            return font;
        }

        public void drawString(String text, int x, int y, int color) {
            if (this.useCustomFont) {
                this.textFont.renderString(text, x, y, color, false);
            } else {
                Minecraft.func_71410_x().field_71466_p.func_78261_a(text, x, y, color);
            }
        }

        public String getName() {
            if (!this.useCustomFont) {
                return "Minecraft";
            }
            return this.textFont.usedFont().getFontName();
        }
    }
}

