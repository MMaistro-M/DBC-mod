/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.proxies;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnable;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.config.ConfigHandlerOverrides;
import riskyken.armourersWorkshop.common.config.ConfigSynchronizeHandler;
import riskyken.armourersWorkshop.common.crafting.CraftingManager;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.library.CommonLibraryManager;
import riskyken.armourersWorkshop.common.library.ILibraryCallback;
import riskyken.armourersWorkshop.common.library.ILibraryManager;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileType;
import riskyken.armourersWorkshop.common.library.global.permission.PermissionSystem;
import riskyken.armourersWorkshop.common.network.GuiHandler;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiAdminPanel;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiSkinLibraryCommand;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerClientCommand;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerLibrarySendSkin;
import riskyken.armourersWorkshop.common.skin.SkinExtractor;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.update.UpdateCheck;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentData;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentDataManager;
import riskyken.armourersWorkshop.common.wardrobe.entity.EntitySkinHandler;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

public class CommonProxy
implements ILibraryCallback {
    private static ModItems modItems;
    private static ModBlocks modBlocks;
    public ILibraryManager libraryManager;
    private PermissionSystem permissionSystem;
    private File directoryInstance;
    private File directoryConfig;
    private File directoryConfigMod;
    private File directoryMod;
    private File directorySkinLibrary;
    private ArrayList<LibraryFile> clearFiles = new ArrayList();

    public void preInit(FMLPreInitializationEvent event) {
        this.directoryInstance = event.getSuggestedConfigurationFile().getParentFile().getParentFile();
        this.directoryConfig = event.getSuggestedConfigurationFile().getParentFile();
        this.directoryConfigMod = new File(this.directoryConfig, "armourersWorkshop");
        this.directoryMod = new File(this.directoryInstance, "armourers_workshop_data");
        this.directorySkinLibrary = new File(this.directoryInstance, "armourersWorkshop");
        if (!this.directoryConfigMod.exists()) {
            this.directoryConfigMod.mkdirs();
        }
        if (!this.directoryMod.exists()) {
            this.directoryMod.mkdirs();
        }
        if (!this.directorySkinLibrary.exists()) {
            this.directorySkinLibrary.mkdirs();
        }
        ModAddonManager.preInit();
        ConfigHandler.init(new File(this.directoryConfigMod, "common.cfg"));
        ConfigHandlerClient.init(new File(this.directoryConfigMod, "client.cfg"));
        ConfigHandlerOverrides.init(new File(this.directoryConfigMod, "overrides.cfg"));
        EntityRegistry.registerModEntity(BlockSkinnable.Seat.class, (String)"seat", (int)1, (Object)ArmourersWorkshop.instance, (int)10, (int)20, (boolean)false);
        SkinIOUtils.makeLibraryDirectory();
        UpdateCheck.checkForUpdates();
        SkinExtractor.extractSkins();
        SkinTypeRegistry.init();
        CubeRegistry.init();
        modItems = new ModItems();
        modBlocks = new ModBlocks();
    }

    public void initLibraryManager() {
        this.libraryManager = new CommonLibraryManager();
    }

    public void initRenderers() {
    }

    public void init(FMLInitializationEvent event) {
        modBlocks.registerTileEntities();
        CraftingManager.init();
        new GuiHandler();
        new ConfigSynchronizeHandler();
        PacketHandler.init();
        EntityEquipmentDataManager.init();
        EntitySkinHandler.init();
        this.permissionSystem = new PermissionSystem();
        ModAddonManager.init();
    }

    public void postInit(FMLPostInitializationEvent event) {
        ModAddonManager.postInit();
        this.libraryManager.reloadLibrary();
    }

    public PermissionSystem getPermissionSystem() {
        return this.permissionSystem;
    }

    public void registerKeyBindings() {
    }

    public void addEquipmentData(PlayerPointer playerPointer, EntityEquipmentData equipmentData) {
    }

    public int getPlayerModelCacheSize() {
        return 0;
    }

    public void receivedCommandFromSever(MessageServerClientCommand.CommandType command) {
    }

    public void receivedAdminPanelCommand(EntityPlayer player, MessageClientGuiAdminPanel.AdminPanelCommand command) {
        switch (command) {
            case RECOVER_SKINS: {
                SkinIOUtils.recoverSkins(player);
                break;
            }
            case RELOAD_LIBRARY: {
                ArmourersWorkshop.proxy.libraryManager.reloadLibrary();
                break;
            }
            case UPDATE_SKINS: {
                SkinIOUtils.updateSkins(player);
            }
        }
    }

    public void receivedEquipmentData(EntityEquipmentData equipmentData, int entityId) {
    }

    public void receivedSkinFromLibrary(String fileName, String filePath, Skin skin, MessageServerLibrarySendSkin.SendType sendType) {
    }

    public int getBlockRenderType(Block block) {
        return 0;
    }

    public MinecraftServer getServer() {
        return MinecraftServer.func_71276_C();
    }

    public void skinLibraryCommand(EntityPlayerMP player, MessageClientGuiSkinLibraryCommand.SkinLibraryCommand command, LibraryFile file, boolean publicList) {
        switch (command) {
            case DELETE: {
                if (!publicList) {
                    File dir = new File(SkinIOUtils.getSkinLibraryDirectory(), file.filePath);
                    dir = file.isDirectory() ? new File(dir, file.fileName + "/") : new File(dir, file.fileName + ".armour");
                    if (dir.isDirectory() != file.isDirectory()) break;
                    if (!SkinIOUtils.isInLibraryDir(dir)) {
                        ModLogger.log(Level.WARN, String.format("Player '%s' tried to delete the file/folder '%s' that is outside the library directory.", player.func_146103_bH().toString(), dir.getAbsolutePath()));
                        return;
                    }
                    if (!dir.exists()) break;
                    if (file.isDirectory()) {
                        try {
                            FileUtils.deleteDirectory((File)dir);
                            this.libraryManager.reloadLibrary();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    this.clearFiles.add(file);
                    ModLogger.log("deleting skin " + dir.getAbsolutePath());
                    dir.delete();
                    this.libraryManager.removeFileFromListType(file, LibraryFileType.SERVER_PRIVATE, (EntityPlayer)player);
                    this.libraryManager.reloadLibrary(this);
                    break;
                }
                ModLogger.log("public delete");
                break;
            }
            case NEW_FOLDER: {
                if (!publicList) {
                    File dir = new File(SkinIOUtils.getSkinLibraryDirectory(), file.filePath);
                    if (!SkinIOUtils.isInLibraryDir(dir = new File(dir, file.fileName))) {
                        ModLogger.log(Level.WARN, String.format("Player '%s' tried to make the folder '%s' that is outside the library directory.", player.func_146103_bH().toString(), dir.getAbsolutePath()));
                        return;
                    }
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    this.libraryManager.reloadLibrary();
                    ModLogger.log(String.format("making folder call %s in %s", file.fileName, file.filePath));
                    ModLogger.log("full path: " + dir.getAbsolutePath());
                    break;
                }
                ModLogger.log("public new folder");
            }
        }
    }

    @Override
    public void libraryReloaded(ILibraryManager libraryManager) {
        for (int i = 0; i < this.clearFiles.size(); ++i) {
            CommonSkinCache.INSTANCE.clearFileNameIdLink(this.clearFiles.get(i));
        }
    }

    public boolean isLocalPlayer(String username) {
        return false;
    }

    public boolean haveFullLocalProfile() {
        return false;
    }

    public GameProfile getLocalGameProfile() {
        return null;
    }

    public File getModDirectory() {
        return this.directoryMod;
    }
}

