/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package riskyken.armourersWorkshop.common.wardrobe.entity;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import riskyken.armourersWorkshop.api.common.skin.entity.ISkinnableEntity;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.inventory.IInventorySlotUpdate;
import riskyken.armourersWorkshop.common.inventory.InventoryEntitySkin;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerEntitySkinData;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentData;
import riskyken.armourersWorkshop.common.wardrobe.entity.EntitySkinHandler;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class ExPropsEntityEquipmentData
implements IExtendedEntityProperties,
IInventorySlotUpdate {
    private static final String TAG_EXT_PROP_NAME = "entityCustomEquipmentData";
    private static final String TAG_ADDED_SPAWN_ITEMS = "addedSpawnItems";
    private final Entity entity;
    private EntityEquipmentData equipmentData;
    private final InventoryEntitySkin skinInventory;
    private boolean allowNetworkUpdates = true;

    public ExPropsEntityEquipmentData(Entity entity, ISkinnableEntity skinnableEntity) {
        this.entity = entity;
        this.equipmentData = new EntityEquipmentData();
        ArrayList<ISkinType> skinTypes = new ArrayList<ISkinType>();
        skinnableEntity.getValidSkinTypes(skinTypes);
        this.skinInventory = new InventoryEntitySkin(this, skinTypes);
    }

    @Override
    public void setInventorySlotContents(IInventory inventory, int slotId, ItemStack stack) {
        if (this.entity.field_70170_p.field_72995_K) {
            return;
        }
        if (stack == null) {
            ISkinType skinType = this.getSkinTypeForSlot(slotId);
            this.equipmentData.removeEquipment(skinType, 0);
        } else {
            SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(stack);
            this.equipmentData.addEquipment(skinData.getIdentifier().getSkinType(), 0, skinData);
        }
        this.sendEquipmentDataToPlayerToAllPlayersAround();
    }

    private ISkinType getSkinTypeForSlot(int slotId) {
        return this.skinInventory.getSkinTypes().get(slotId);
    }

    private void sendEquipmentDataToPlayerToAllPlayersAround() {
        if (!this.allowNetworkUpdates) {
            return;
        }
        NetworkRegistry.TargetPoint p = new NetworkRegistry.TargetPoint(this.entity.field_71093_bK, this.entity.field_70165_t, this.entity.field_70163_u, this.entity.field_70161_v, 512.0);
        PacketHandler.networkWrapper.sendToAllAround((IMessage)new MessageServerEntitySkinData(this.equipmentData, this.entity.func_145782_y()), p);
    }

    public void sendEquipmentDataToPlayer(EntityPlayerMP targetPlayer) {
        PacketHandler.networkWrapper.sendTo((IMessage)new MessageServerEntitySkinData(this.equipmentData, this.entity.func_145782_y()), targetPlayer);
    }

    public InventoryEntitySkin getSkinInventory() {
        return this.skinInventory;
    }

    public EntityEquipmentData getEquipmentData() {
        return this.equipmentData;
    }

    public void setEquipmentData(EntityEquipmentData equipmentData) {
        this.equipmentData = equipmentData;
    }

    public void saveNBTData(NBTTagCompound compound) {
        this.skinInventory.saveItemsToNBT(compound);
    }

    public void loadNBTData(NBTTagCompound compound) {
        this.allowNetworkUpdates = false;
        this.equipmentData.clear();
        this.skinInventory.loadItemsFromNBT(compound);
        this.allowNetworkUpdates = true;
    }

    private void addSpawnItems() {
        if (this.entity.field_70170_p != null && !this.entity.field_70170_p.field_72995_K) {
            EntitySkinHandler.INSTANCE.giveRandomSkin(this);
        }
    }

    public void init(Entity entity, World world) {
        this.addSpawnItems();
    }

    public static final void register(Entity entity, ISkinnableEntity skinnableEntity) {
        entity.registerExtendedProperties(TAG_EXT_PROP_NAME, (IExtendedEntityProperties)new ExPropsEntityEquipmentData(entity, skinnableEntity));
    }

    private static final ExPropsEntityEquipmentData get(Entity entity) {
        return (ExPropsEntityEquipmentData)entity.getExtendedProperties(TAG_EXT_PROP_NAME);
    }

    public static final ExPropsEntityEquipmentData getExtendedPropsForEntity(Entity entity) {
        return ExPropsEntityEquipmentData.get(entity);
    }

    public Entity getEntity() {
        return this.entity;
    }
}

