/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.ScreenShotHelper
 *  net.minecraft.util.Util
 *  net.minecraft.util.Util$EnumOS
 *  org.apache.commons.io.FileUtils
 *  org.apache.logging.log4j.Level
 *  org.lwjgl.Sys
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.skinlibrary;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.Util;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialog;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialogContainer;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.client.gui.controls.GuiFileListItem;
import riskyken.armourersWorkshop.client.gui.controls.GuiIconButton;
import riskyken.armourersWorkshop.client.gui.controls.GuiLabeledTextField;
import riskyken.armourersWorkshop.client.gui.controls.GuiList;
import riskyken.armourersWorkshop.client.gui.controls.GuiScrollbar;
import riskyken.armourersWorkshop.client.gui.controls.IGuiListItem;
import riskyken.armourersWorkshop.client.gui.skinlibrary.GuiDialogDelete;
import riskyken.armourersWorkshop.client.gui.skinlibrary.GuiDialogNewFolder;
import riskyken.armourersWorkshop.client.gui.skinlibrary.GuiDialogOverwrite;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinItemRenderHelper;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.inventory.ContainerArmourLibrary;
import riskyken.armourersWorkshop.common.items.ItemSkinTemplate;
import riskyken.armourersWorkshop.common.library.ILibraryManager;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileList;
import riskyken.armourersWorkshop.common.library.LibraryFileType;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.SkinUploadHelper;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiLoadSaveArmour;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiSkinLibraryCommand;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinLibrary;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

@SideOnly(value=Side.CLIENT)
public class GuiSkinLibrary
extends AbstractGuiDialogContainer {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/armourLibrary.png");
    private static final int BUTTON_ID_LOAD_SAVE = 0;
    private static final int TITLE_HEIGHT = 15;
    private static final int PADDING = 5;
    private static final int INVENTORY_HEIGHT = 76;
    private static final int INVENTORY_WIDTH = 162;
    private static int scrollAmount = 0;
    private static ISkinType lastSkinType;
    private static String lastSearchText;
    private static String currentFolder;
    private static boolean trackFile;
    private TileEntitySkinLibrary armourLibrary;
    private final EntityPlayer player;
    private GuiIconButton fileSwitchlocal;
    private GuiIconButton fileSwitchRemotePublic;
    private GuiIconButton fileSwitchRemotePrivate;
    public static LibraryFileType fileSwitchType;
    private GuiList fileList;
    private GuiButtonExt loadSaveButton;
    private GuiIconButton openFolderButton;
    private GuiIconButton deleteButton;
    private GuiIconButton reloadButton;
    private GuiIconButton newFolderButton;
    private GuiScrollbar scrollbar;
    private GuiLabeledTextField filenameTextbox;
    private GuiLabeledTextField searchTextbox;
    private GuiDropDownList dropDownList;
    private GuiCheckBox checkBoxTrack;
    private boolean isNEIVisible;
    private int neiBump = 18;

    public GuiSkinLibrary(InventoryPlayer invPlayer, TileEntitySkinLibrary armourLibrary) {
        super(new ContainerArmourLibrary(invPlayer, armourLibrary));
        this.player = invPlayer.field_70458_d;
        this.armourLibrary = armourLibrary;
        this.isNEIVisible = ModAddonManager.addonNEI.isVisible();
    }

    @Override
    public void func_73866_w_() {
        ScaledResolution reso = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        this.field_146999_f = reso.func_78326_a();
        this.field_147000_g = reso.func_78328_b();
        super.func_73866_w_();
        String guiName = this.armourLibrary.func_145825_b();
        int slotSize = 18;
        this.neiBump = ModAddonManager.addonNEI.isVisible() ? 18 : 0;
        for (int x = 0; x < 9; ++x) {
            Slot slot = (Slot)this.field_147002_h.field_75151_b.get(x);
            slot.field_75221_f = this.field_146295_m + 1 - 5 - slotSize - this.neiBump;
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                Slot slot = (Slot)this.field_147002_h.field_75151_b.get(x + y * 9 + 9);
                slot.field_75221_f = this.field_146295_m + 1 - 76 - 5 + y * slotSize - this.neiBump;
            }
        }
        Slot slot = (Slot)this.field_147002_h.field_75151_b.get(36);
        slot.field_75221_f = this.field_146295_m + 2 - 76 - 15 - slotSize - this.neiBump;
        slot.field_75223_e = 6;
        slot = (Slot)this.field_147002_h.field_75151_b.get(37);
        slot.field_75221_f = this.field_146295_m + 2 - 76 - 15 - slotSize - this.neiBump;
        slot.field_75223_e = 167 - slotSize - 3;
        this.field_146292_n.clear();
        this.fileSwitchlocal = new GuiIconButton((GuiScreen)this, -1, 5, 20, 50, 30, GuiHelper.getLocalizedControlName(guiName, "rollover.localFiles"), texture);
        this.fileSwitchRemotePublic = new GuiIconButton((GuiScreen)this, -1, 61, 20, 50, 30, GuiHelper.getLocalizedControlName(guiName, "rollover.remotePublicFiles"), texture);
        this.fileSwitchRemotePrivate = new GuiIconButton((GuiScreen)this, -1, 117, 20, 50, 30, GuiHelper.getLocalizedControlName(guiName, "rollover.remotePrivateFiles"), texture);
        this.fileSwitchlocal.setIconLocation(0, 0, 50, 30);
        this.fileSwitchRemotePublic.setIconLocation(0, 31, 50, 30);
        this.fileSwitchRemotePrivate.setIconLocation(0, 62, 50, 30);
        this.openFolderButton = new GuiIconButton((GuiScreen)this, 4, 5, this.field_147009_r + 80, 24, 24, GuiHelper.getLocalizedControlName(guiName, "rollover.openLibraryFolder"), texture);
        this.openFolderButton.setIconLocation(0, 93, 24, 24);
        this.field_146292_n.add(this.openFolderButton);
        this.reloadButton = new GuiIconButton((GuiScreen)this, -1, 30, this.field_147009_r + 80, 24, 24, GuiHelper.getLocalizedControlName(guiName, "rollover.refresh"), texture);
        this.reloadButton.setIconLocation(75, 93, 24, 24);
        this.field_146292_n.add(this.reloadButton);
        this.deleteButton = new GuiIconButton((GuiScreen)this, -1, 55, this.field_147009_r + 80, 24, 24, GuiHelper.getLocalizedControlName(guiName, "rollover.deleteSkin"), texture);
        this.deleteButton.setIconLocation(0, 118, 24, 24);
        this.field_146292_n.add(this.deleteButton);
        this.newFolderButton = new GuiIconButton((GuiScreen)this, -1, 80, this.field_147009_r + 80, 24, 24, GuiHelper.getLocalizedControlName(guiName, "rollover.newFolder"), texture);
        this.newFolderButton.setIconLocation(75, 118, 24, 24);
        this.field_146292_n.add(this.newFolderButton);
        int listWidth = this.field_146294_l - 162 - 25;
        int listHeight = this.field_146295_m - 15 - 14 - 15;
        int typeSwitchWidth = 80;
        listWidth = MathHelper.func_76125_a((int)listWidth, (int)0, (int)200);
        this.fileList = new GuiList(172, 39, listWidth, listHeight, 14);
        if (this.field_146297_k.func_71356_B()) {
            this.fileSwitchRemotePublic.field_146124_l = false;
            this.fileSwitchRemotePrivate.field_146124_l = false;
            this.fileSwitchRemotePublic.setDisableText(GuiHelper.getLocalizedControlName(guiName, "rollover.notOnServer"));
            this.fileSwitchRemotePrivate.setDisableText(GuiHelper.getLocalizedControlName(guiName, "rollover.notOnServer"));
            this.setFileSwitchType(LibraryFileType.LOCAL);
        } else {
            this.setFileSwitchType(LibraryFileType.SERVER_PUBLIC);
        }
        this.field_146292_n.add(this.fileSwitchlocal);
        this.field_146292_n.add(this.fileSwitchRemotePublic);
        this.field_146292_n.add(this.fileSwitchRemotePrivate);
        this.loadSaveButton = new GuiButtonExt(0, 28, this.field_146295_m - 76 - 10 - 2 - 20 - this.neiBump, 108, 20, "----LS--->");
        this.field_146292_n.add(this.loadSaveButton);
        this.filenameTextbox = new GuiLabeledTextField(this.field_146289_q, 5, 55, 162, 12);
        this.filenameTextbox.func_146203_f(100);
        this.filenameTextbox.setEmptyLabel(GuiHelper.getLocalizedControlName(guiName, "label.enterFileName"));
        this.searchTextbox = new GuiLabeledTextField(this.field_146289_q, 172, 21, listWidth - typeSwitchWidth - 5 + 10, 12);
        this.searchTextbox.func_146203_f(100);
        this.searchTextbox.setEmptyLabel(GuiHelper.getLocalizedControlName(guiName, "label.typeToSearch"));
        this.searchTextbox.func_146180_a(lastSearchText);
        this.scrollbar = new GuiScrollbar(2, 172 + listWidth, 39, 10, listHeight, "", false);
        this.scrollbar.setValue(scrollAmount);
        this.field_146292_n.add(this.scrollbar);
        this.dropDownList = new GuiDropDownList(5, 187 + listWidth - typeSwitchWidth - 5, 20, typeSwitchWidth, "", null);
        ArrayList<ISkinType> skinTypes = SkinTypeRegistry.INSTANCE.getRegisteredSkinTypes();
        this.dropDownList.addListItem("*");
        this.dropDownList.setListSelectedIndex(0);
        int addCount = 0;
        for (int i = 0; i < skinTypes.size(); ++i) {
            ISkinType skinType = skinTypes.get(i);
            if (skinType.isHidden()) continue;
            this.dropDownList.addListItem(SkinTypeRegistry.INSTANCE.getLocalizedSkinTypeName(skinType), skinType.getRegistryName(), true);
            ++addCount;
            if (skinType != lastSkinType) continue;
            this.dropDownList.setListSelectedIndex(addCount);
        }
        this.field_146292_n.add(this.dropDownList);
        this.checkBoxTrack = new GuiCheckBox(-1, 5, this.field_146295_m - 76 - 25 - 2 - 20 - this.neiBump, GuiHelper.getLocalizedControlName(guiName, "trackFile"), trackFile);
        this.checkBoxTrack.setTextColour(0xCCCCCC);
        this.field_146292_n.add(this.checkBoxTrack);
    }

    public TileEntitySkinLibrary getArmourLibrary() {
        return this.armourLibrary;
    }

    private boolean isLoading() {
        Slot slot = (Slot)this.field_147002_h.field_75151_b.get(36);
        ItemStack stack = slot.func_75211_c();
        return stack == null || stack.func_77973_b() instanceof ItemSkinTemplate;
    }

    private boolean isPlayerOp(EntityPlayer player) {
        MinecraftServer minecraftServer = FMLCommonHandler.instance().getMinecraftServerInstance();
        return minecraftServer.func_71203_ab().func_152596_g(player.func_146103_bH());
    }

    private void setFileSwitchType(LibraryFileType type) {
        if (fileSwitchType == type) {
            return;
        }
        this.fileSwitchlocal.setPressed(false);
        this.fileSwitchRemotePublic.setPressed(false);
        this.fileSwitchRemotePrivate.setPressed(false);
        fileSwitchType = type;
        switch (type) {
            case LOCAL: {
                this.fileSwitchlocal.setPressed(true);
                currentFolder = "/";
                this.setupLibraryEditButtons();
                break;
            }
            case SERVER_PUBLIC: {
                this.fileSwitchRemotePublic.setPressed(true);
                currentFolder = "/";
                this.setupLibraryEditButtons();
                break;
            }
            case SERVER_PRIVATE: {
                this.fileSwitchRemotePrivate.setPressed(true);
                currentFolder = this.getPrivateRoot(this.player);
                this.setupLibraryEditButtons();
            }
        }
    }

    private void setupLibraryEditButtons() {
        IGuiListItem listItem = this.fileList.getSelectedListEntry();
        this.deleteButton.field_146124_l = false;
        this.openFolderButton.field_146124_l = false;
        this.newFolderButton.field_146124_l = false;
        this.reloadButton.field_146124_l = false;
        if (fileSwitchType == LibraryFileType.LOCAL) {
            this.newFolderButton.field_146124_l = true;
            this.openFolderButton.field_146124_l = true;
            this.reloadButton.field_146124_l = true;
            this.reloadButton.field_146124_l = true;
            if (listItem != null && !listItem.getDisplayName().equals("../")) {
                this.deleteButton.field_146124_l = true;
            } else {
                this.deleteButton.setDisableText(GuiHelper.getLocalizedControlName(this.armourLibrary.func_145825_b(), "rollover.deleteSkinSelect"));
            }
        }
        if (fileSwitchType == LibraryFileType.SERVER_PUBLIC) {
            this.openFolderButton.setDisableText("");
            this.reloadButton.setDisableText("");
            this.deleteButton.setDisableText("");
            this.newFolderButton.setDisableText("");
        }
        if (fileSwitchType == LibraryFileType.SERVER_PRIVATE) {
            this.openFolderButton.setDisableText("");
            this.reloadButton.setDisableText("");
            this.newFolderButton.field_146124_l = true;
            if (listItem != null && !listItem.getDisplayName().equals("../")) {
                this.deleteButton.field_146124_l = true;
            } else {
                this.deleteButton.setDisableText(GuiHelper.getLocalizedControlName(this.armourLibrary.func_145825_b(), "rollover.deleteSkinSelect"));
            }
        }
    }

    protected void func_146284_a(GuiButton button) {
        String filename = this.filenameTextbox.func_146179_b().trim();
        if (button == this.fileSwitchlocal | button == this.fileSwitchRemotePublic | button == this.fileSwitchRemotePrivate) {
            if (button == this.fileSwitchlocal) {
                this.setFileSwitchType(LibraryFileType.LOCAL);
            }
            if (button == this.fileSwitchRemotePublic) {
                this.setFileSwitchType(LibraryFileType.SERVER_PUBLIC);
            }
            if (button == this.fileSwitchRemotePrivate) {
                this.setFileSwitchType(LibraryFileType.SERVER_PRIVATE);
            }
        }
        if (button == this.reloadButton && fileSwitchType == LibraryFileType.LOCAL) {
            ILibraryManager libraryManager = ArmourersWorkshop.proxy.libraryManager;
            libraryManager.reloadLibrary();
        }
        if (button == this.openFolderButton) {
            this.openEquipmentFolder();
        }
        if (button == this.deleteButton && this.fileList.getSelectedListEntry() != null) {
            GuiFileListItem item = (GuiFileListItem)this.fileList.getSelectedListEntry();
            this.openDialog(new GuiDialogDelete((GuiScreen)this, this.armourLibrary.func_145825_b() + ".dialog.delete", this, 190, 100, item.getFile().isDirectory(), item.getDisplayName()));
        }
        if (button == this.newFolderButton) {
            this.openDialog(new GuiDialogNewFolder((GuiScreen)this, this.armourLibrary.func_145825_b() + ".dialog.newFolder", this, 190, 120));
        }
        if (button == this.checkBoxTrack) {
            trackFile = this.checkBoxTrack.isChecked();
        }
        GuiFileListItem fileItem = (GuiFileListItem)this.fileList.getSelectedListEntry();
        boolean clientLoad = false;
        boolean publicList = true;
        if (fileSwitchType == LibraryFileType.LOCAL && !this.field_146297_k.func_71387_A()) {
            clientLoad = true;
        }
        if (fileSwitchType == LibraryFileType.SERVER_PRIVATE) {
            publicList = false;
        }
        if (button == this.loadSaveButton) {
            MessageClientGuiLoadSaveArmour message;
            if (fileItem != null && !fileItem.getFile().isDirectory()) {
                LibraryFile file = fileItem.getFile();
                if (this.isLoading()) {
                    if (clientLoad) {
                        Skin itemData = SkinIOUtils.loadSkinFromFileName(file.filePath + filename + ".armour");
                        if (itemData != null) {
                            SkinUploadHelper.uploadSkinToServer(itemData);
                        }
                    } else {
                        message = new MessageClientGuiLoadSaveArmour(file.fileName, file.filePath, MessageClientGuiLoadSaveArmour.LibraryPacketType.SERVER_LOAD, publicList, trackFile);
                        PacketHandler.networkWrapper.sendToServer((IMessage)message);
                    }
                    this.filenameTextbox.func_146180_a("");
                }
            }
            if (!filename.isEmpty() && !this.isLoading()) {
                if (this.fileExists(currentFolder, filename)) {
                    this.openDialog(new GuiDialogOverwrite((GuiScreen)this, this.armourLibrary.func_145825_b() + ".dialog.overwrite", this, 190, 100, filename));
                    return;
                }
                if (clientLoad) {
                    message = new MessageClientGuiLoadSaveArmour(filename, currentFolder, MessageClientGuiLoadSaveArmour.LibraryPacketType.CLIENT_SAVE, false, trackFile);
                    PacketHandler.networkWrapper.sendToServer((IMessage)message);
                } else {
                    message = new MessageClientGuiLoadSaveArmour(filename, currentFolder, MessageClientGuiLoadSaveArmour.LibraryPacketType.SERVER_SAVE, publicList, trackFile);
                    PacketHandler.networkWrapper.sendToServer((IMessage)message);
                }
            }
        }
        this.setupLibraryEditButtons();
    }

    private boolean fileExists(String path, String name) {
        LibraryFileList fileList = this.getFileList(fileSwitchType);
        ArrayList<LibraryFile> files = fileList.getFileList();
        for (int i = 0; i < files.size(); ++i) {
            LibraryFile file = files.get(i);
            if (!file.getFullName().equalsIgnoreCase(path + name)) continue;
            return true;
        }
        return false;
    }

    private LibraryFileList getFileList(LibraryFileType libraryFileType) {
        ILibraryManager libraryManager = ArmourersWorkshop.proxy.libraryManager;
        switch (libraryFileType) {
            case LOCAL: {
                return libraryManager.getClientPublicFileList();
            }
            case SERVER_PUBLIC: {
                return libraryManager.getServerPublicFileList();
            }
            case SERVER_PRIVATE: {
                return libraryManager.getServerPrivateFileList((EntityPlayer)this.field_146297_k.field_71439_g);
            }
        }
        return null;
    }

    private void reloadLocalLibrary() {
        ArmourersWorkshop.proxy.libraryManager.reloadLibrary();
    }

    @Override
    public void dialogResult(AbstractGuiDialog dialog, AbstractGuiDialog.DialogResult result) {
        if (result == AbstractGuiDialog.DialogResult.OK) {
            LibraryFile libraryFile;
            if (dialog instanceof GuiDialogNewFolder) {
                GuiDialogNewFolder newFolderDialog = (GuiDialogNewFolder)dialog;
                if (fileSwitchType == LibraryFileType.LOCAL) {
                    File dir = new File(SkinIOUtils.getSkinLibraryDirectory(), currentFolder);
                    if (!(dir = new File(dir, newFolderDialog.getFolderName())).exists()) {
                        dir.mkdir();
                    }
                    this.reloadLocalLibrary();
                    ModLogger.log(String.format("making folder call %s in %s", newFolderDialog.getFolderName(), currentFolder));
                    ModLogger.log("full path: " + dir.getAbsolutePath());
                } else {
                    MessageClientGuiSkinLibraryCommand message = new MessageClientGuiSkinLibraryCommand();
                    message.newFolder(new LibraryFile(newFolderDialog.getFolderName(), currentFolder, null, true), fileSwitchType == LibraryFileType.SERVER_PUBLIC);
                    PacketHandler.networkWrapper.sendToServer((IMessage)message);
                }
            }
            if (dialog instanceof GuiDialogDelete) {
                GuiDialogDelete deleteDialog = (GuiDialogDelete)dialog;
                boolean isFolder = deleteDialog.isFolder();
                String name = deleteDialog.getFileName();
                if (fileSwitchType == LibraryFileType.LOCAL) {
                    File dir = new File(SkinIOUtils.getSkinLibraryDirectory(), currentFolder);
                    dir = deleteDialog.isFolder() ? new File(dir, name + "/") : new File(dir, name + ".armour");
                    if (dir.isDirectory() == isFolder && dir.exists()) {
                        if (isFolder) {
                            try {
                                FileUtils.deleteDirectory((File)dir);
                                this.reloadLocalLibrary();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            libraryFile = new LibraryFile(currentFolder + deleteDialog.getFileName());
                            ClientSkinCache.INSTANCE.markSkinAsDirty(new SkinIdentifier(0, libraryFile, 0, null));
                            dir.delete();
                            this.reloadLocalLibrary();
                        }
                    }
                } else {
                    MessageClientGuiSkinLibraryCommand message = new MessageClientGuiSkinLibraryCommand();
                    message.delete(new LibraryFile(deleteDialog.getFileName(), currentFolder, null, isFolder), fileSwitchType == LibraryFileType.SERVER_PUBLIC);
                    PacketHandler.networkWrapper.sendToServer((IMessage)message);
                }
            }
            if (dialog instanceof GuiDialogOverwrite) {
                GuiDialogOverwrite overwriteDialog = (GuiDialogOverwrite)dialog;
                boolean clientLoad = false;
                boolean publicList = true;
                if (fileSwitchType == LibraryFileType.LOCAL && !this.field_146297_k.func_71387_A()) {
                    clientLoad = true;
                }
                if (fileSwitchType == LibraryFileType.SERVER_PRIVATE) {
                    publicList = false;
                }
                if (clientLoad) {
                    MessageClientGuiLoadSaveArmour message = new MessageClientGuiLoadSaveArmour(overwriteDialog.getFileName(), currentFolder, MessageClientGuiLoadSaveArmour.LibraryPacketType.CLIENT_SAVE, false, trackFile);
                    PacketHandler.networkWrapper.sendToServer((IMessage)message);
                } else {
                    MessageClientGuiLoadSaveArmour message = new MessageClientGuiLoadSaveArmour(overwriteDialog.getFileName(), currentFolder, MessageClientGuiLoadSaveArmour.LibraryPacketType.SERVER_SAVE, publicList, trackFile);
                    PacketHandler.networkWrapper.sendToServer((IMessage)message);
                }
                libraryFile = new LibraryFile(currentFolder + overwriteDialog.getFileName());
                ClientSkinCache.INSTANCE.markSkinAsDirty(new SkinIdentifier(0, libraryFile, 0, null));
            }
        }
        super.dialogResult(dialog, result);
    }

    public void setFileName(String text) {
        this.filenameTextbox.func_146180_a(text);
    }

    private void openEquipmentFolder() {
        File armourDir = new File(System.getProperty("user.dir"));
        File file = new File(armourDir, "armourersWorkshop");
        String filePath = file.getAbsolutePath();
        if (Util.func_110647_a() == Util.EnumOS.OSX) {
            try {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open", filePath});
                return;
            }
            catch (IOException ioexception1) {
                ModLogger.log(Level.ERROR, "Couldn't open file: " + ioexception1);
            }
        } else if (Util.func_110647_a() == Util.EnumOS.WINDOWS) {
            String s1 = String.format("cmd.exe /C start \"Open file\" \"%s\"", filePath);
            try {
                Runtime.getRuntime().exec(s1);
                return;
            }
            catch (IOException ioexception) {
                ModLogger.log(Level.ERROR, "Couldn't open file: " + ioexception);
            }
        }
        boolean openedFailed = false;
        try {
            Class<?> oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            oclass.getMethod("browse", URI.class).invoke(object, file.toURI());
        }
        catch (Throwable throwable) {
            ModLogger.log(Level.ERROR, "Couldn't open link: " + throwable);
            openedFailed = true;
        }
        if (openedFailed) {
            ModLogger.log("Opening via system class!");
            Sys.openURL((String)("file://" + filePath));
        }
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTickTime) {
        SkinIdentifier identifier;
        Skin skin;
        GuiFileListItem item;
        int i;
        ISkinType skinTypeFilter;
        int oldMouseX = mouseX;
        int oldMouseY = mouseY;
        if (this.isDialogOpen()) {
            mouseY = 0;
            mouseX = 0;
        }
        GL11.glPushAttrib((int)1048575);
        super.func_73863_a(mouseX, mouseY, partialTickTime);
        GL11.glPopAttrib();
        ILibraryManager libraryManager = ArmourersWorkshop.proxy.libraryManager;
        ArrayList<LibraryFile> files = libraryManager.getServerPublicFileList().getFileList();
        this.loadSaveButton.field_146124_l = true;
        if (this.isLoading()) {
            this.loadSaveButton.field_146126_j = GuiHelper.getLocalizedControlName(this.armourLibrary.func_145825_b(), "load");
            if (this.fileList.getSelectedListEntry() == null || ((GuiFileListItem)this.fileList.getSelectedListEntry()).getFile().directory) {
                this.loadSaveButton.field_146126_j = "";
                this.loadSaveButton.field_146124_l = false;
            }
        } else {
            this.loadSaveButton.field_146126_j = GuiHelper.getLocalizedControlName(this.armourLibrary.func_145825_b(), "save");
        }
        if (!((Slot)this.field_147002_h.field_75151_b.get(36)).func_75216_d() & !this.armourLibrary.isCreativeLibrary()) {
            this.loadSaveButton.field_146126_j = "";
            this.loadSaveButton.field_146124_l = false;
        }
        if (fileSwitchType == LibraryFileType.LOCAL) {
            files = libraryManager.getClientPublicFileList().getFileList();
            if (!this.field_146297_k.func_71387_A()) {
                this.loadSaveButton.field_146124_l = false;
                this.loadSaveButton.field_146124_l = this.isLoading() ? ConfigHandler.allowClientsToUploadSkins : ConfigHandler.allowClientsToDownloadSkins;
            }
        } else {
            this.loadSaveButton.field_146124_l = true;
        }
        if (fileSwitchType == LibraryFileType.SERVER_PRIVATE) {
            files = libraryManager.getServerPrivateFileList((EntityPlayer)this.field_146297_k.field_71439_g).getFileList();
        }
        String typeFilter = this.dropDownList.getListSelectedItem().tag;
        lastSkinType = skinTypeFilter = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(typeFilter);
        lastSearchText = this.searchTextbox.func_146179_b();
        IGuiListItem selectedItem = this.fileList.getSelectedListEntry();
        if (selectedItem != null) {
            // empty if block
        }
        this.fileList.setSelectedIndex(-1);
        this.fileList.clearList();
        if (!(currentFolder.equals("/") | currentFolder.equals(this.getPrivateRoot(this.player)))) {
            this.fileList.addListItem(new GuiFileListItem(new LibraryFile("../", "", null, true)));
            if (selectedItem != null && ((GuiFileListItem)selectedItem).getFile().fileName.equals("../")) {
                this.fileList.setSelectedIndex(0);
            }
        }
        if (files != null) {
            for (i = 0; i < files.size(); ++i) {
                LibraryFile file = files.get(i);
                if (!(skinTypeFilter == null | skinTypeFilter == file.skinType) || !file.filePath.equals(currentFolder)) continue;
                if (!this.searchTextbox.func_146179_b().equals("")) {
                    if (!file.fileName.toLowerCase().contains(this.searchTextbox.func_146179_b().toLowerCase())) continue;
                    this.fileList.addListItem(new GuiFileListItem(file));
                    if (selectedItem == null || ((GuiFileListItem)selectedItem).getFile() != file) continue;
                    this.fileList.setSelectedIndex(this.fileList.getSize() - 1);
                    continue;
                }
                this.fileList.addListItem(new GuiFileListItem(file));
                if (selectedItem == null || ((GuiFileListItem)selectedItem).getFile() != file) continue;
                this.fileList.setSelectedIndex(this.fileList.getSize() - 1);
            }
        }
        scrollAmount = this.scrollbar.getValue();
        this.fileList.setScrollPercentage(this.scrollbar.getPercentageValue());
        for (i = 0; i < this.field_146292_n.size(); ++i) {
            GuiButton button = (GuiButton)this.field_146292_n.get(i);
            if (!(button instanceof GuiIconButton)) continue;
            ((GuiIconButton)button).drawRollover(this.field_146297_k, mouseX, mouseY);
        }
        if (GuiSkinLibrary.showModelPreviews() && (item = (GuiFileListItem)this.fileList.getSelectedListEntry()) != null && !item.getFile().isDirectory() && (skin = ClientSkinCache.INSTANCE.getSkin(identifier = new SkinIdentifier(0, new LibraryFile(item.getFile().getFullName()), 0, null), true)) != null) {
            SkinPointer skinPointer = new SkinPointer(identifier);
            int listRight = this.field_146294_l - 162 - 25;
            listRight = MathHelper.func_76125_a((int)listRight, (int)0, (int)200);
            int listTop = 39;
            int xSize = (this.field_146294_l - (listRight += 182) - 5) / 2;
            int ySize = (this.field_146295_m - listTop - 5) / 2;
            float x = listRight + xSize;
            float y = listTop + ySize;
            float scale = 1.0f;
            scale = 1 * Math.min(xSize, ySize);
            ScaledResolution scaledResolution = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
            int startX = listRight + 5;
            int startY = listTop + 5;
            int tarW = (int)(x + (float)xSize);
            int tarH = (int)(y + (float)ySize);
            GuiSkinLibrary.func_73734_a((int)startX, (int)startY, (int)tarW, (int)tarH, (int)0x77777777);
            if (scale > 8.0f) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)x, (float)y, (float)500.0f);
                GL11.glScalef((float)10.0f, (float)10.0f, (float)-10.0f);
                GL11.glRotatef((float)30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                float rotation = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
                GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                RenderHelper.func_74519_b();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glPushAttrib((int)1048575);
                GL11.glEnable((int)2977);
                GL11.glEnable((int)2903);
                ModRenderHelper.enableAlphaBlend();
                SkinItemRenderHelper.renderSkinAsItem(skin, skinPointer, true, false, tarW - startX, tarH - startY);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.isDialogOpen()) {
            this.dialog.draw(oldMouseX, oldMouseY, partialTickTime);
        }
    }

    private String getPrivateRoot(EntityPlayer player) {
        String privateRoot = "/private/";
        privateRoot = ConfigHandler.remotePlayerId != null ? privateRoot + ConfigHandler.remotePlayerId.toString() + "/" : privateRoot + player.func_110124_au().toString() + "/";
        return privateRoot;
    }

    public static boolean showModelPreviews() {
        Minecraft mc = Minecraft.func_71410_x();
        if (!ConfigHandler.libraryShowsModelPreviews) {
            return false;
        }
        if (mc.func_71387_A()) {
            return true;
        }
        return fileSwitchType != LibraryFileType.LOCAL;
    }

    @Override
    protected void func_73864_a(int mouseX, int mouseY, int button) {
        if (!this.isDialogOpen()) {
            this.searchTextbox.func_146192_a(mouseX, mouseY, button);
            this.filenameTextbox.func_146192_a(mouseX, mouseY, button);
            if (button == 1) {
                if (this.searchTextbox.func_146206_l()) {
                    this.searchTextbox.func_146180_a("");
                }
                if (this.filenameTextbox.func_146206_l()) {
                    this.filenameTextbox.func_146180_a("");
                }
            }
            if (!this.dropDownList.getIsDroppedDown()) {
                GuiFileListItem oldItem = (GuiFileListItem)this.fileList.getSelectedListEntry();
                if (this.fileList.mouseClicked(mouseX, mouseY, button)) {
                    GuiFileListItem item = (GuiFileListItem)this.fileList.getSelectedListEntry();
                    if (!item.getFile().isDirectory()) {
                        this.filenameTextbox.func_146180_a(item.getDisplayName());
                    } else if (item.getFile().fileName.equals("../") & oldItem == item) {
                        this.goBackFolder();
                    } else if (oldItem == item) {
                        this.setCurrentFolder(item.getFile().getFullName() + "/");
                    }
                }
            }
            this.scrollbar.setValue(scrollAmount);
            this.scrollbar.func_146116_c(this.field_146297_k, mouseX, mouseY);
            this.setupLibraryEditButtons();
        }
        super.func_73864_a(mouseX, mouseY, button);
    }

    private void setCurrentFolder(String currentFolder) {
        GuiSkinLibrary.currentFolder = currentFolder;
        this.fileList.setSelectedIndex(-1);
        scrollAmount = 0;
    }

    private void goBackFolder() {
        String[] folderSplit = currentFolder.split("/");
        String currentFolder = "";
        for (int i = 0; i < folderSplit.length - 1; ++i) {
            currentFolder = currentFolder + folderSplit[i] + "/";
        }
        this.setCurrentFolder(currentFolder);
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int button) {
        super.func_146286_b(mouseX, mouseY, button);
        if (!this.isDialogOpen()) {
            if (!this.dropDownList.getIsDroppedDown()) {
                this.fileList.mouseMovedOrUp(mouseX, mouseY, button);
            }
            this.scrollbar.func_146118_a(mouseX, mouseY);
        }
    }

    @Override
    protected void func_73869_a(char key, int keyCode) {
        if (keyCode == this.field_146297_k.field_71474_y.field_151447_Z.func_151463_i()) {
            this.field_146297_k.field_71456_v.func_146158_b().func_146227_a(ScreenShotHelper.func_148260_a((File)this.field_146297_k.field_71412_D, (int)this.field_146297_k.field_71443_c, (int)this.field_146297_k.field_71440_d, (Framebuffer)this.field_146297_k.func_147110_a()));
        }
        if (!this.isDialogOpen()) {
            if (!(this.searchTextbox.func_146201_a(key, keyCode) | this.filenameTextbox.func_146201_a(key, keyCode))) {
                if (keyCode == 200) {
                    this.fileList.setSelectedIndex(this.fileList.getSelectedIndex() - 1);
                }
                if (keyCode == 208) {
                    this.fileList.setSelectedIndex(this.fileList.getSelectedIndex() + 1);
                }
                super.func_73869_a(key, keyCode);
            }
        } else {
            super.func_73869_a(key, keyCode);
        }
        this.checkNEIVisibility();
    }

    private void checkNEIVisibility() {
        if (this.isNEIVisible != ModAddonManager.addonNEI.isVisible()) {
            this.isNEIVisible = !this.isNEIVisible;
            this.func_73866_w_();
        }
    }

    protected void func_146976_a(float someFloat, int mouseX, int mouseY) {
        if (this.isDialogOpen()) {
            mouseY = 0;
            mouseX = 0;
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(texture);
        ModRenderHelper.enableAlphaBlend();
        this.func_73729_b(5, this.field_146295_m - 76 - 5 - this.neiBump, 0, 180, 162, 76);
        this.func_73729_b(5, this.field_146295_m - 76 - 18 - 10 - 4 - this.neiBump, 0, 162, 18, 18);
        this.func_73729_b(141, this.field_146295_m - 76 - 26 - 10 - this.neiBump, 18, 154, 26, 26);
        ModRenderHelper.disableAlphaBlend();
        this.searchTextbox.func_146194_f();
        this.filenameTextbox.func_146194_f();
        this.fileList.drawList(mouseX, mouseY, 0.0f);
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        if (this.armourLibrary.isCreativeLibrary()) {
            GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, this.armourLibrary.func_145825_b() + "1", 0xCCCCCC);
        } else {
            GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, this.armourLibrary.func_145825_b() + "0", 0xCCCCCC);
        }
        String filesLabel = GuiHelper.getLocalizedControlName(this.armourLibrary.func_145825_b(), "label.files");
        String filenameLabel = GuiHelper.getLocalizedControlName(this.armourLibrary.func_145825_b(), "label.filename");
        String searchLabel = GuiHelper.getLocalizedControlName(this.armourLibrary.func_145825_b(), "label.search");
        this.dropDownList.drawForeground(this.field_146297_k, mouseX, mouseY, 0.0f);
    }

    static {
        lastSearchText = "";
        currentFolder = "/";
        trackFile = false;
    }
}

