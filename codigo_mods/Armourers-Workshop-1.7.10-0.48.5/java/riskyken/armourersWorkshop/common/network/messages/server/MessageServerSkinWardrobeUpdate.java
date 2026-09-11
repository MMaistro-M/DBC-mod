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
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.proxies.ClientProxy;

public class MessageServerSkinWardrobeUpdate
implements IMessage,
IMessageHandler<MessageServerSkinWardrobeUpdate, IMessage> {
    PlayerPointer playerPointer;
    EquipmentWardrobeData equipmentWardrobeData;

    public MessageServerSkinWardrobeUpdate() {
        this.equipmentWardrobeData = new EquipmentWardrobeData();
    }

    public MessageServerSkinWardrobeUpdate(PlayerPointer playerPointer, EquipmentWardrobeData equipmentWardrobeData) {
        this.playerPointer = playerPointer;
        this.equipmentWardrobeData = equipmentWardrobeData;
    }

    public void fromBytes(ByteBuf buf) {
        this.playerPointer = new PlayerPointer(buf);
        this.equipmentWardrobeData.fromBytes(buf);
    }

    public void toBytes(ByteBuf buf) {
        this.playerPointer.writeToByteBuffer(buf);
        this.equipmentWardrobeData.toBytes(buf);
    }

    public IMessage onMessage(MessageServerSkinWardrobeUpdate message, MessageContext ctx) {
        this.setEquipmentWardrobeData(message.playerPointer, message.equipmentWardrobeData);
        return null;
    }

    private void setEquipmentWardrobeData(PlayerPointer playerPointer, EquipmentWardrobeData ewd) {
        ClientProxy.equipmentWardrobeHandler.setEquipmentWardrobeData(playerPointer, ewd);
    }
}

