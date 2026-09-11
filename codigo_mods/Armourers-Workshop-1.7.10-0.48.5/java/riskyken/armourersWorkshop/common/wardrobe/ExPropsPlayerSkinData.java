/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package riskyken.armourersWorkshop.common.wardrobe;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.BitSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.inventory.IInventorySlotUpdate;
import riskyken.armourersWorkshop.common.inventory.WardrobeInventory;
import riskyken.armourersWorkshop.common.inventory.WardrobeInventoryContainer;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerSkinInfoUpdate;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerSkinWardrobeUpdate;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentData;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class ExPropsPlayerSkinData
implements IExtendedEntityProperties,
IInventorySlotUpdate {
    public static final int MAX_SLOTS_PER_SKIN_TYPE = 10;
    public static final String TAG_EXT_PROP_NAME = "playerCustomEquipmentData";
    private static final String TAG_LAST_XMAS_YEAR = "lastXmasYear";
    public static final ISkinType[] validSkins = new ISkinType[]{SkinTypeRegistry.skinHead, SkinTypeRegistry.skinChest, SkinTypeRegistry.skinLegs, SkinTypeRegistry.skinFeet, SkinTypeRegistry.skinSword, SkinTypeRegistry.skinBow, SkinTypeRegistry.oldSkinArrow, SkinTypeRegistry.skinWings, SkinTypeRegistry.skinOutfit, SkinTypeRegistry.skinPickaxe, SkinTypeRegistry.skinAxe, SkinTypeRegistry.skinShovel, SkinTypeRegistry.skinHoe};
    private final WardrobeInventoryContainer wardrobeInventoryContainer;
    private final EntityEquipmentData equipmentData;
    private final EntityPlayer player;
    private EquipmentWardrobeData equipmentWardrobeData = new EquipmentWardrobeData();
    public int lastXmasYear;
    private boolean allowNetworkUpdates = true;

    public ExPropsPlayerSkinData(EntityPlayer player) {
        this.player = player;
        this.wardrobeInventoryContainer = new WardrobeInventoryContainer(this, validSkins);
        this.equipmentData = new EntityEquipmentData();
    }

    public WardrobeInventoryContainer getWardrobeInventoryContainer() {
        return this.wardrobeInventoryContainer;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }

    public static final void register(EntityPlayer player) {
        player.registerExtendedProperties(TAG_EXT_PROP_NAME, (IExtendedEntityProperties)new ExPropsPlayerSkinData(player));
    }

    public static final ExPropsPlayerSkinData get(EntityPlayer player) {
        return (ExPropsPlayerSkinData)player.getExtendedProperties(TAG_EXT_PROP_NAME);
    }

    @Deprecated
    public void setEquipmentStack(ItemStack stack) {
        this.setEquipmentStack(stack, 0);
    }

    public void setEquipmentStack(ItemStack stack, int index) {
        WardrobeInventory wi;
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        if (skinPointer.getIdentifier().getSkinType() != null && (wi = this.wardrobeInventoryContainer.getInventoryForSkinType(skinPointer.getIdentifier().getSkinType())) != null) {
            wi.func_70299_a(index, stack);
        }
    }

    public boolean setStackInNextFreeSlot(ItemStack stack) {
        WardrobeInventory wi;
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        if (skinPointer.getIdentifier().getSkinType() != null && (wi = this.wardrobeInventoryContainer.getInventoryForSkinType(skinPointer.getIdentifier().getSkinType())) != null) {
            for (int i = 0; i < this.equipmentWardrobeData.getUnlockedSlotsForSkinType(skinPointer.getIdentifier().getSkinType()); ++i) {
                if (wi.func_70301_a(i) != null) continue;
                wi.func_70299_a(i, stack);
                return true;
            }
        }
        return false;
    }

    public ItemStack getEquipmentStack(ISkinType skinType, int index) {
        WardrobeInventory wi = this.wardrobeInventoryContainer.getInventoryForSkinType(skinType);
        if (wi != null) {
            return wi.func_70301_a(index);
        }
        return null;
    }

    public void clearAllEquipmentStacks() {
        ArrayList<ISkinType> skinList = SkinTypeRegistry.INSTANCE.getRegisteredSkinTypes();
        for (int i = 0; i < skinList.size(); ++i) {
            ISkinType skinType = skinList.get(i);
            WardrobeInventory wi = this.wardrobeInventoryContainer.getInventoryForSkinType(skinType);
            if (wi == null) continue;
            for (int j = 0; j < wi.func_70302_i_(); ++j) {
                wi.func_70299_a(j, null);
            }
        }
    }

    public void clearEquipmentStack(ISkinType skinType, int index) {
        WardrobeInventory wi = this.wardrobeInventoryContainer.getInventoryForSkinType(skinType);
        if (wi != null) {
            wi.func_70299_a(index, null);
        }
    }

    public void addCustomEquipment(ISkinType skinType, byte slotId, SkinPointer skinPointer) {
        this.equipmentData.addEquipment(skinType, slotId, skinPointer);
        this.updateEquipmentDataToPlayersAround();
    }

    public void removeCustomEquipment(ISkinType skinType, byte slotId) {
        this.equipmentData.removeEquipment(skinType, slotId);
        this.updateEquipmentDataToPlayersAround();
    }

    public void updateEquipmentDataToPlayersAround() {
        if (!this.allowNetworkUpdates) {
            return;
        }
        NetworkRegistry.TargetPoint p = new NetworkRegistry.TargetPoint(this.player.field_71093_bK, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, 512.0);
        PlayerPointer playerPointer = new PlayerPointer(this.player);
        PacketHandler.networkWrapper.sendToAllAround((IMessage)new MessageServerSkinInfoUpdate(playerPointer, this.equipmentData), p);
    }

    public EntityEquipmentData getEquipmentData() {
        return this.equipmentData;
    }

    public void armourSlotUpdate(WardrobeInventory inventory, byte slot) {
        ItemStack stack = inventory.func_70301_a(slot);
        if (stack == null) {
            this.removeArmourFromSlot(inventory, slot);
            return;
        }
        if (!stack.func_77942_o()) {
            return;
        }
        if (!SkinNBTHelper.stackHasSkinData(stack)) {
            return;
        }
        this.loadFromItemStack(stack, slot);
    }

    private void removeArmourFromSlot(WardrobeInventory inventory, byte slotId) {
        this.removeCustomEquipment(inventory.getSkinType(), slotId);
    }

    public void setSkinColumnCount(ISkinType skinType, int value) {
        if (value > 0 & value <= 10) {
            this.equipmentWardrobeData.setUnlockedSlotsForSkinType(skinType, value);
            this.sendNakedData((EntityPlayerMP)this.player);
        }
    }

    public void sendCustomArmourDataToPlayer(EntityPlayerMP targetPlayer) {
        this.checkAndSendCustomArmourDataTo(targetPlayer);
        this.sendNakedData(targetPlayer);
    }

    public void setSkinInfo(EquipmentWardrobeData equipmentWardrobeData, boolean sendUpdate) {
        this.equipmentWardrobeData = equipmentWardrobeData;
        if (sendUpdate) {
            this.sendSkinData();
        }
    }

    private void checkAndSendCustomArmourDataTo(EntityPlayerMP targetPlayer) {
        PlayerPointer playerPointer = new PlayerPointer(this.player);
        PacketHandler.networkWrapper.sendTo((IMessage)new MessageServerSkinInfoUpdate(playerPointer, this.equipmentData), targetPlayer);
    }

    private void sendNakedData(EntityPlayerMP targetPlayer) {
        PlayerPointer playerPointer = new PlayerPointer(this.player);
        PacketHandler.networkWrapper.sendTo((IMessage)new MessageServerSkinWardrobeUpdate(playerPointer, this.equipmentWardrobeData), targetPlayer);
    }

    private void sendSkinData() {
        NetworkRegistry.TargetPoint p = new NetworkRegistry.TargetPoint(this.player.field_71093_bK, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, 512.0);
        PlayerPointer playerPointer = new PlayerPointer(this.player);
        PacketHandler.networkWrapper.sendToAllAround((IMessage)new MessageServerSkinWardrobeUpdate(playerPointer, this.equipmentWardrobeData), p);
    }

    public EquipmentWardrobeData getEquipmentWardrobeData() {
        return this.equipmentWardrobeData;
    }

    public BitSet getArmourOverride() {
        return this.equipmentWardrobeData.armourOverride;
    }

    public void saveNBTData(NBTTagCompound compound) {
        this.wardrobeInventoryContainer.writeToNBT(compound);
        this.equipmentWardrobeData.saveNBTData(compound);
        compound.func_74768_a(TAG_LAST_XMAS_YEAR, this.lastXmasYear);
    }

    public void loadNBTData(NBTTagCompound compound) {
        this.wardrobeInventoryContainer.readFromNBT(compound);
        this.equipmentWardrobeData.loadNBTData(compound);
        this.allowNetworkUpdates = false;
        for (int i = 0; i < validSkins.length; ++i) {
            WardrobeInventory wi = this.wardrobeInventoryContainer.getInventoryForSkinType(validSkins[i]);
            if (wi == null) continue;
            for (int j = 0; j < wi.func_70302_i_(); ++j) {
                this.armourSlotUpdate(wi, (byte)j);
            }
        }
        this.allowNetworkUpdates = true;
        this.lastXmasYear = compound.func_74764_b(TAG_LAST_XMAS_YEAR) ? compound.func_74762_e(TAG_LAST_XMAS_YEAR) : 0;
    }

    private void loadFromItemStack(ItemStack stack, byte slotId) {
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        this.addCustomEquipment(skinPointer.getIdentifier().getSkinType(), slotId, skinPointer);
    }

    public void init(Entity entity, World world) {
    }

    @Override
    public void setInventorySlotContents(IInventory inventory, int slotId, ItemStack stack) {
        if (!this.player.field_70170_p.field_72995_K && inventory instanceof WardrobeInventory) {
            this.armourSlotUpdate((WardrobeInventory)inventory, (byte)slotId);
        }
    }
}

