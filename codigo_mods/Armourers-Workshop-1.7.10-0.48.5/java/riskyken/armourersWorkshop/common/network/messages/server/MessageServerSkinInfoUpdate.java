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
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentData;

public class MessageServerSkinInfoUpdate
implements IMessage,
IMessageHandler<MessageServerSkinInfoUpdate, IMessage> {
    PlayerPointer playerPointer;
    EntityEquipmentData equipmentData;

    public MessageServerSkinInfoUpdate(PlayerPointer playerPointer, EntityEquipmentData equipmentData) {
        this.playerPointer = playerPointer;
        this.equipmentData = equipmentData;
    }

    public MessageServerSkinInfoUpdate() {
    }

    public void fromBytes(ByteBuf buf) {
        this.playerPointer = new PlayerPointer(buf);
        this.equipmentData = EntityEquipmentData.readFromByteBuf(buf);
    }

    public void toBytes(ByteBuf buf) {
        this.playerPointer.writeToByteBuffer(buf);
        EntityEquipmentData.writeToByteBuf(this.equipmentData, buf);
    }

    public IMessage onMessage(MessageServerSkinInfoUpdate message, MessageContext ctx) {
        ArmourersWorkshop.proxy.addEquipmentData(message.playerPointer, message.equipmentData);
        return null;
    }
}

