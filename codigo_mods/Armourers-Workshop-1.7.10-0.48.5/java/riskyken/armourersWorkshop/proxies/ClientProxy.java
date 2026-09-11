/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.ICrashCallable
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.Item
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.proxies;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ICrashCallable;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.client.handler.BlockHighlightRenderHandler;
import riskyken.armourersWorkshop.client.handler.DebugTextHandler;
import riskyken.armourersWorkshop.client.handler.EquipmentWardrobeHandler;
import riskyken.armourersWorkshop.client.handler.ItemTooltipHandler;
import riskyken.armourersWorkshop.client.handler.ModClientFMLEventHandler;
import riskyken.armourersWorkshop.client.handler.PlayerTextureHandler;
import riskyken.armourersWorkshop.client.handler.RehostedJarHandler;
import riskyken.armourersWorkshop.client.handler.SkinPreviewHandler;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.client.library.ClientLibraryManager;
import riskyken.armourersWorkshop.client.model.ModelMannequin;
import riskyken.armourersWorkshop.client.model.bake.ModelBakery;
import riskyken.armourersWorkshop.client.render.SkinModelRenderer;
import riskyken.armourersWorkshop.client.render.block.RenderBlockColourMixer;
import riskyken.armourersWorkshop.client.render.block.RenderBlockGlowing;
import riskyken.armourersWorkshop.client.render.entity.EntitySkinRenderHandler;
import riskyken.armourersWorkshop.client.render.entity.RenderSkinnedArrow;
import riskyken.armourersWorkshop.client.render.item.RenderItemEquipmentSkin;
import riskyken.armourersWorkshop.client.render.item.RenderItemMannequin;
import riskyken.armourersWorkshop.client.render.tileEntity.RenderBlockArmourer;
import riskyken.armourersWorkshop.client.render.tileEntity.RenderBlockColourable;
import riskyken.armourersWorkshop.client.render.tileEntity.RenderBlockGlobalSkinLibrary;
import riskyken.armourersWorkshop.client.render.tileEntity.RenderBlockHologramProjector;
import riskyken.armourersWorkshop.client.render.tileEntity.RenderBlockMannequin;
import riskyken.armourersWorkshop.client.render.tileEntity.RenderBlockMiniArmourer;
import riskyken.armourersWorkshop.client.render.tileEntity.RenderBlockSkinnable;
import riskyken.armourersWorkshop.client.settings.Keybindings;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.client.texture.PlayerTextureDownloader;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;
import riskyken.armourersWorkshop.common.blocks.BlockColourMixer;
import riskyken.armourersWorkshop.common.blocks.BlockColourable;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileType;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerClientCommand;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerLibrarySendSkin;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityBoundingBox;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourable;
import riskyken.armourersWorkshop.common.tileentities.TileEntityGlobalSkinLibrary;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentData;
import riskyken.armourersWorkshop.common.wardrobe.entity.EntitySkinHandler;
import riskyken.armourersWorkshop.proxies.CommonProxy;
import riskyken.armourersWorkshop.utils.HolidayHelper;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

@SideOnly(value=Side.CLIENT)
public class ClientProxy
extends CommonProxy {
    public static EquipmentWardrobeHandler equipmentWardrobeHandler;
    public static PlayerTextureHandler playerTextureHandler;
    public static PlayerTextureDownloader playerTextureDownloader;
    public static int renderPass;
    public static IIcon dyeBottleSlotIcon;

    public static boolean isJrbaClientLoaded() {
        return ModAddonManager.addonJBRAClient.isModLoaded();
    }

    public ClientProxy() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        this.enableCrossModSupport();
        this.spamSillyMessages();
        new RehostedJarHandler(event.getSourceFile(), "Armourers-Workshop-1.7.10-0.48.5.jar");
    }

    @Override
    public void initLibraryManager() {
        this.libraryManager = new ClientLibraryManager();
    }

    @Override
    public void initRenderers() {
        SkinModelRenderer.init();
        EntitySkinRenderHandler.init();
        new BlockHighlightRenderHandler();
        new ItemTooltipHandler();
        new SkinPreviewHandler();
        RenderSkinnedArrow arrowRender = new RenderSkinnedArrow();
        arrowRender.func_76976_a(RenderManager.field_78727_a);
        RenderManager.field_78727_a.field_78729_o.put(EntityArrow.class, arrowRender);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArmourer.class, (TileEntitySpecialRenderer)new RenderBlockArmourer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMannequin.class, (TileEntitySpecialRenderer)new RenderBlockMannequin());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMiniArmourer.class, (TileEntitySpecialRenderer)new RenderBlockMiniArmourer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkinnable.class, (TileEntitySpecialRenderer)new RenderBlockSkinnable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityColourable.class, (TileEntitySpecialRenderer)new RenderBlockColourable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoundingBox.class, (TileEntitySpecialRenderer)new RenderBlockColourable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlobalSkinLibrary.class, (TileEntitySpecialRenderer)new RenderBlockGlobalSkinLibrary());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHologramProjector.class, (TileEntitySpecialRenderer)new RenderBlockHologramProjector());
        ModelMannequin modelSteve = new ModelMannequin(false);
        ModelMannequin modelAlex = new ModelMannequin(true);
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.equipmentSkin, (IItemRenderer)new RenderItemEquipmentSkin());
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)ModBlocks.mannequin), (IItemRenderer)new RenderItemMannequin(modelSteve, modelAlex));
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)ModBlocks.doll), (IItemRenderer)new RenderItemMannequin(modelSteve, modelAlex));
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockColourMixer());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockGlowing());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        equipmentWardrobeHandler = new EquipmentWardrobeHandler();
        playerTextureHandler = new PlayerTextureHandler();
        playerTextureDownloader = new PlayerTextureDownloader();
        ClientSkinCache.init();
        FMLCommonHandler.instance().bus().register((Object)new ModClientFMLEventHandler());
        MinecraftForge.EVENT_BUS.register((Object)new DebugTextHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ModAddonManager.initRenderers();
        EntitySkinRenderHandler.INSTANCE.initRenderer();
        if (HolidayHelper.valentins.isHolidayActive()) {
            this.enableValentinsClouds();
        }
        FMLCommonHandler.instance().registerCrashCallable(new ICrashCallable(){

            public String call() throws Exception {
                int bakeQueue = ModelBakery.INSTANCE.getBakingQueueSize();
                return "\n\t\tRender Type: " + ClientProxy.getSkinRenderType().toString() + "\n\t\tTexture Render: " + ClientProxy.useSafeTextureRender() + "\n\t\tBaking Queue: " + bakeQueue + "\n\t\tRequest Queue: " + (ClientSkinCache.INSTANCE.getRequestQueueSize() - bakeQueue) + "\n\t\tTexture Painting: " + ClientProxy.useTexturePainting() + "\n\t\tMultipass Skin Rendering: " + ClientProxy.useMultipassSkinRendering();
            }

            public String getLabel() {
                return "Armourer's Workshop";
            }
        });
    }

    @SubscribeEvent
    public void onTextureStitchEvent(TextureStitchEvent.Pre event) {
        if (event.map.func_130086_a() == 1) {
            dyeBottleSlotIcon = event.map.func_94245_a(LibItemResources.SLOT_DYE_BOTTLE);
        }
    }

    private void enableValentinsClouds() {
        ModLogger.log("Love is in the air!");
        try {
            Object o = ReflectionHelper.getPrivateValue(RenderGlobal.class, null, (String[])new String[]{"locationCloudsPng", "field_110925_j"});
            Field f = ReflectionHelper.findField(ResourceLocation.class, (String[])new String[]{"resourceDomain", "field_110626_a"});
            f.setAccessible(true);
            f.set(o, "armourersWorkshop".toLowerCase());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enableCrossModSupport() {
        if (ModAddonManager.addonMorePlayerModels.isModLoaded() & ModAddonManager.addonSmartMoving.isModLoaded()) {
            ModLogger.log(Level.WARN, "Smart Moving and More Player Models are both installed. Armourer's Workshop can not support this.");
        }
        if (ModAddonManager.addonColoredLights.isModLoaded() & ModAddonManager.addonSmartMoving.isModLoaded()) {
            ModLogger.log(Level.WARN, "Colored Lights and Smart Moving are both installed. Armourer's Workshop can not support this.");
        }
        ModLogger.log("Skin render type set to: " + ClientProxy.getSkinRenderType().toString().toLowerCase());
    }

    public static SkinRenderType getSkinRenderType() {
        switch (ConfigHandlerClient.skinRenderType) {
            case 1: {
                return SkinRenderType.RENDER_EVENT;
            }
            case 2: {
                return SkinRenderType.MODEL_ATTACHMENT;
            }
            case 3: {
                return SkinRenderType.RENDER_LAYER;
            }
        }
        if (ModAddonManager.addonMorePlayerModels.isModLoaded()) {
            return SkinRenderType.RENDER_EVENT;
        }
        if (ModAddonManager.addonShaders.isModLoaded() & !ModAddonManager.addonSmartMoving.isModLoaded()) {
            return SkinRenderType.RENDER_EVENT;
        }
        if (ModAddonManager.addonColoredLights.isModLoaded() & !ModAddonManager.addonSmartMoving.isModLoaded()) {
            return SkinRenderType.RENDER_EVENT;
        }
        if (ModAddonManager.addonJBRAClient.isModLoaded()) {
            return SkinRenderType.RENDER_EVENT;
        }
        return SkinRenderType.MODEL_ATTACHMENT;
    }

    public static boolean useSafeTextureRender() {
        if (ModAddonManager.addonShaders.isModLoaded()) {
            return true;
        }
        if (ConfigHandlerClient.skinTextureRenderOverride) {
            return true;
        }
        return ModAddonManager.addonColoredLights.isModLoaded();
    }

    public static boolean useTexturePainting() {
        if (ConfigHandlerClient.texturePainting == 1) {
            return true;
        }
        if (ConfigHandlerClient.texturePainting == 2) {
            return false;
        }
        return !ModAddonManager.addonJBRAClient.isModLoaded();
    }

    public static boolean useMultipassSkinRendering() {
        return ConfigHandlerClient.multipassSkinRendering;
    }

    public static int getNumberOfRenderLayers() {
        if (ClientProxy.useMultipassSkinRendering()) {
            return 4;
        }
        return 2;
    }

    private void spamSillyMessages() {
        if (Loader.isModLoaded((String)"Tails")) {
            ModLogger.log("Tails detected! - Sand praising module active.");
        }
        if (Loader.isModLoaded((String)"BuildCraft|Core")) {
            ModLogger.log("Buildcraft detected! - Enabling knishes support.");
        }
        if (Loader.isModLoaded((String)"integratedcircuits")) {
            ModLogger.log("Integrated Circuits detected! - Applying cosplay to mannequins.");
        }
    }

    public static void playerLeftTrackingRange(PlayerPointer playerPointer) {
        SkinModelRenderer.INSTANCE.removeEquipmentData(playerPointer);
        equipmentWardrobeHandler.removeEquipmentWardrobeData(playerPointer);
    }

    @Override
    public void registerKeyBindings() {
        ClientRegistry.registerKeyBinding((KeyBinding)Keybindings.openCustomArmourGui);
        ClientRegistry.registerKeyBinding((KeyBinding)Keybindings.undo);
    }

    @Override
    public void addEquipmentData(PlayerPointer playerPointer, EntityEquipmentData equipmentData) {
        SkinModelRenderer.INSTANCE.addEquipmentData(playerPointer, equipmentData);
    }

    @Override
    public int getPlayerModelCacheSize() {
        return ClientSkinCache.INSTANCE.getCacheSize();
    }

    @Override
    public void receivedCommandFromSever(MessageServerClientCommand.CommandType command) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        switch (command) {
            case CLEAR_MODEL_CACHE: {
                ClientSkinCache.INSTANCE.clearCache();
                CommonSkinCache.INSTANCE.clearAll();
                break;
            }
            case OPEN_ADMIN_PANEL: {
                player.openGui((Object)ArmourersWorkshop.instance, 14, player.func_130014_f_(), 0, 0, 0);
            }
        }
    }

    @Override
    public void receivedEquipmentData(EntityEquipmentData equipmentData, int entityId) {
        EntitySkinHandler.INSTANCE.receivedEquipmentData(equipmentData, entityId);
    }

    @Override
    public void receivedSkinFromLibrary(String fileName, String filePath, Skin skin, MessageServerLibrarySendSkin.SendType sendType) {
        switch (sendType) {
            case LIBRARY_SAVE: {
                SkinIOUtils.saveSkinFromFileName(filePath, fileName + ".armour", skin);
                ArmourersWorkshop.proxy.libraryManager.addFileToListType(new LibraryFile(fileName, filePath, skin.getSkinType()), LibraryFileType.LOCAL, null);
                break;
            }
            case GLOBAL_UPLOAD: {
                GuiScreen screen = Minecraft.func_71410_x().field_71462_r;
                if (!(screen instanceof GuiGlobalLibrary)) break;
                ((GuiGlobalLibrary)screen).gotSkinFromServer(skin);
            }
        }
    }

    @Override
    public int getBlockRenderType(Block block) {
        if (block instanceof BlockColourable) {
            return RenderBlockGlowing.renderId;
        }
        if (block instanceof BlockColourMixer) {
            return RenderBlockColourMixer.renderId;
        }
        return super.getBlockRenderType(block);
    }

    @Override
    public MinecraftServer getServer() {
        if (Minecraft.func_71410_x().func_71387_A()) {
            return Minecraft.func_71410_x().func_71401_C();
        }
        return super.getServer();
    }

    @Override
    public boolean isLocalPlayer(String username) {
        GameProfile gameProfile = this.getLocalGameProfile();
        return gameProfile != null && !StringUtils.func_151246_b((String)gameProfile.getName()) && username.equals(gameProfile.getName());
    }

    @Override
    public boolean haveFullLocalProfile() {
        GameProfile gameProfile = this.getLocalGameProfile();
        return gameProfile.isComplete() && gameProfile.getProperties().containsKey((Object)"textures");
    }

    @Override
    public GameProfile getLocalGameProfile() {
        return Minecraft.func_71410_x().field_71439_g.func_146103_bH();
    }

    public static enum SkinRenderType {
        RENDER_EVENT,
        MODEL_ATTACHMENT,
        RENDER_LAYER;

    }
}

