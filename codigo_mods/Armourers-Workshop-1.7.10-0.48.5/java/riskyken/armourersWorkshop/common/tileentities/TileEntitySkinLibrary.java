/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.tileentities;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.items.ItemSkinTemplate;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileType;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerLibrarySendSkin;
import riskyken.armourersWorkshop.common.skin.ISkinHolder;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.tileentities.AbstractTileEntityInventory;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class TileEntitySkinLibrary
extends AbstractTileEntityInventory
implements ISidedInventory {
    private static final int INVENTORY_SIZE = 2;

    public TileEntitySkinLibrary() {
        super(2);
    }

    public String func_145825_b() {
        return "armourLibrary";
    }

    public boolean canUpdate() {
        return false;
    }

    public boolean isCreativeLibrary() {
        int meta = this.func_145832_p();
        return meta == 1;
    }

    public void sendArmourToClient(String filename, String filePath, EntityPlayerMP player) {
        if (!ConfigHandler.allowClientsToDownloadSkins) {
            return;
        }
        ItemStack stackInput = this.func_70301_a(0);
        ItemStack stackOutput = this.func_70301_a(1);
        if (stackInput == null) {
            return;
        }
        if (stackOutput != null) {
            return;
        }
        if (!(stackInput.func_77973_b() instanceof ItemSkin)) {
            return;
        }
        if (!SkinNBTHelper.stackHasSkinData(stackInput)) {
            return;
        }
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stackInput);
        Skin skin = CommonSkinCache.INSTANCE.getSkin(skinPointer);
        if (skin == null) {
            return;
        }
        LibraryFile file = new LibraryFile(filename, filePath, skin.getSkinType());
        CommonSkinCache.INSTANCE.clearFileNameIdLink(file);
        MessageServerLibrarySendSkin message = new MessageServerLibrarySendSkin(filename, filePath, skin, MessageServerLibrarySendSkin.SendType.LIBRARY_SAVE);
        PacketHandler.networkWrapper.sendTo((IMessage)message, player);
        this.func_70298_a(0, 1);
        this.func_70299_a(1, stackInput);
    }

    public void saveArmour(String fileName, String filePath, EntityPlayerMP player, boolean publicFiles) {
        ItemStack stackInput = this.func_70301_a(0);
        ItemStack stackOutput = this.func_70301_a(1);
        if (stackInput == null) {
            return;
        }
        if (stackOutput != null) {
            return;
        }
        if (!(stackInput.func_77973_b() instanceof ItemSkin)) {
            return;
        }
        if (!SkinNBTHelper.stackHasSkinData(stackInput)) {
            return;
        }
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stackInput);
        if (skinPointer == null) {
            return;
        }
        if (!publicFiles) {
            // empty if block
        }
        ModLogger.log("save");
        Skin skin = CommonSkinCache.INSTANCE.getSkin(skinPointer);
        if (skin == null) {
            ModLogger.log("no input");
            return;
        }
        ModLogger.log("save");
        filePath = SkinIOUtils.makeFilePathValid(filePath);
        fileName = SkinIOUtils.makeFileNameValid(fileName);
        LibraryFile file = new LibraryFile(fileName, filePath, skin.getSkinType());
        CommonSkinCache.INSTANCE.clearFileNameIdLink(file);
        if (!SkinIOUtils.saveSkinFromFileName(filePath, fileName + ".armour", skin)) {
            return;
        }
        if (ArmourersWorkshop.isDedicated()) {
            if (publicFiles) {
                ArmourersWorkshop.proxy.libraryManager.addFileToListType(new LibraryFile(fileName, filePath, skin.getSkinType()), LibraryFileType.SERVER_PUBLIC, (EntityPlayer)player);
            } else {
                ArmourersWorkshop.proxy.libraryManager.addFileToListType(new LibraryFile(fileName, filePath, skin.getSkinType()), LibraryFileType.SERVER_PRIVATE, (EntityPlayer)player);
            }
        } else {
            ArmourersWorkshop.proxy.libraryManager.addFileToListType(new LibraryFile(fileName, filePath, skin.getSkinType()), LibraryFileType.LOCAL, (EntityPlayer)player);
        }
        this.func_70298_a(0, 1);
        this.func_70299_a(1, stackInput);
    }

    public void loadArmour(String fileName, String filePath, EntityPlayerMP player, boolean trackFile) {
        ItemStack stackInput = this.func_70301_a(0);
        ItemStack stackOutput = this.func_70301_a(1);
        if (!this.isCreativeLibrary() && stackInput == null) {
            return;
        }
        if (stackOutput != null) {
            return;
        }
        if (!this.isCreativeLibrary() && !(stackInput.func_77973_b() instanceof ISkinHolder)) {
            return;
        }
        Skin skin = null;
        String fullFileName = fileName;
        skin = SkinIOUtils.loadSkinFromFileName(filePath + fileName + ".armour");
        if (skin == null) {
            return;
        }
        LibraryFile libraryFile = new LibraryFile(fileName, filePath, skin.getSkinType());
        SkinIdentifier identifier = null;
        identifier = trackFile ? new SkinIdentifier(0, libraryFile, 0, skin.getSkinType()) : new SkinIdentifier(skin.lightHash(), null, 0, skin.getSkinType());
        CommonSkinCache.INSTANCE.addEquipmentDataToCache(skin, libraryFile);
        ModLogger.log("Loaded file form lib: " + libraryFile.toString());
        ItemStack stackArmour = SkinNBTHelper.makeEquipmentSkinStack(skin, identifier);
        if (stackArmour == null) {
            return;
        }
        if (!this.isCreativeLibrary()) {
            this.func_70298_a(0, 1);
        }
        this.func_70299_a(1, stackArmour);
    }

    public void loadArmour(Skin skin, EntityPlayerMP player) {
        ItemStack stackInput = this.func_70301_a(0);
        ItemStack stackOutput = this.func_70301_a(1);
        if (!this.isCreativeLibrary() && stackInput == null) {
            return;
        }
        if (stackOutput != null) {
            return;
        }
        if (!this.isCreativeLibrary() && !(stackInput.func_77973_b() instanceof ISkinHolder)) {
            return;
        }
        ItemStack inputItem = SkinNBTHelper.makeEquipmentSkinStack(skin);
        if (inputItem == null) {
            return;
        }
        CommonSkinCache.INSTANCE.addEquipmentDataToCache(skin, null);
        this.func_70298_a(0, 1);
        this.func_70299_a(1, inputItem);
    }

    public int[] func_94128_d(int side) {
        int[] slots = new int[]{0, 1};
        return slots;
    }

    public boolean func_102007_a(int slot, ItemStack stack, int side) {
        if (slot != 0) {
            return false;
        }
        if (stack.func_77973_b() instanceof ItemSkinTemplate && stack.func_77960_j() == 0) {
            return true;
        }
        return stack.func_77973_b() instanceof ItemSkin;
    }

    public boolean func_102008_b(int slot, ItemStack stack, int side) {
        return true;
    }
}

