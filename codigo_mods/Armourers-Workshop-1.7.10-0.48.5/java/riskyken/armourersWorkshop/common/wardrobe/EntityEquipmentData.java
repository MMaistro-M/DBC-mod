/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.wardrobe;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.api.common.skin.IEntityEquipment;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;

public class EntityEquipmentData
implements IEntityEquipment {
    private static final String TAG_SKIN_LIST = "skinList";
    private static final String TAG_SKIN_TYPE = "skinType";
    private static final String TAG_EQUIPMENT_ID = "equipmentId";
    private HashMap<String, ISkinPointer> skinPointerMap = new HashMap();

    public static EntityEquipmentData readFromByteBuf(ByteBuf buf) {
        EntityEquipmentData eed = new EntityEquipmentData();
        eed.fromBytes(buf);
        return eed;
    }

    public static void writeToByteBuf(EntityEquipmentData eed, ByteBuf buf) {
        eed.toBytes(buf);
    }

    @Override
    public void addEquipment(ISkinType skinType, int slotIndex, ISkinPointer skinPointer) {
        String key = skinType.getRegistryName() + ":" + slotIndex;
        this.skinPointerMap.remove(key);
        this.skinPointerMap.put(key, skinPointer);
    }

    @Override
    public void removeEquipment(ISkinType skinType, int slotIndex) {
        String key = skinType.getRegistryName() + ":" + slotIndex;
        this.skinPointerMap.remove(key);
    }

    @Override
    public boolean haveEquipment(ISkinType skinType, int slotIndex) {
        String key = skinType.getRegistryName() + ":" + slotIndex;
        return this.skinPointerMap.containsKey(key);
    }

    @Override
    public ISkinPointer getSkinPointer(ISkinType skinType, int slotIndex) {
        String key = skinType.getRegistryName() + ":" + slotIndex;
        return this.skinPointerMap.get(key);
    }

    private void toBytes(ByteBuf buf) {
        buf.writeByte(this.skinPointerMap.size());
        for (int i = 0; i < this.skinPointerMap.size(); ++i) {
            String skinName = (String)this.skinPointerMap.keySet().toArray()[i];
            NBTTagCompound compound = new NBTTagCompound();
            SkinPointer skinPointer = (SkinPointer)this.skinPointerMap.get(skinName);
            skinPointer.writeToCompound(compound);
            ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)compound);
            ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)skinName);
        }
    }

    public void clear() {
        this.skinPointerMap.clear();
    }

    private void fromBytes(ByteBuf buf) {
        int itemCount = buf.readByte();
        this.skinPointerMap.clear();
        for (int i = 0; i < itemCount; ++i) {
            NBTTagCompound compound = ByteBufUtils.readTag((ByteBuf)buf);
            String skinKey = ByteBufUtils.readUTF8String((ByteBuf)buf);
            SkinPointer skinPointer = new SkinPointer();
            skinPointer.readFromCompound(compound);
            this.skinPointerMap.put(skinKey, skinPointer);
        }
    }

    public boolean hasCustomEquipment() {
        return this.skinPointerMap.size() > 0;
    }

    @Override
    @Deprecated
    public int getNumberOfSlots() {
        return 0;
    }

    @Override
    @Deprecated
    public int getEquipmentId(ISkinType skinType, int slotIndex) {
        return 0;
    }

    @Override
    @Deprecated
    public ISkinDye getSkinDye(ISkinType skinType, int slotIndex) {
        return null;
    }
}

