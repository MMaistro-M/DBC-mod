/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package riskyken.armourersWorkshop.common.network.messages.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentData;

public class MessageServerEntitySkinData
implements IMessage,
IMessageHandler<MessageServerEntitySkinData, IMessage> {
    private EntityEquipmentData equipmentData;
    private int entityId;

    public MessageServerEntitySkinData() {
    }

    public MessageServerEntitySkinData(EntityEquipmentData equipmentData, int entityId) {
        this.equipmentData = equipmentData;
        this.entityId = entityId;
    }

    public void fromBytes(ByteBuf buf) {
        this.entityId = buf.readInt();
        this.equipmentData = EntityEquipmentData.readFromByteBuf(buf);
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entityId);
        EntityEquipmentData.writeToByteBuf(this.equipmentData, buf);
    }

    public IMessage onMessage(MessageServerEntitySkinData message, MessageContext ctx) {
        ArmourersWorkshop.proxy.receivedEquipmentData(message.equipmentData, message.entityId);
        return null;
    }
}

