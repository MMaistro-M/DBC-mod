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
import riskyken.armourersWorkshop.proxies.ClientProxy;

public class MessageServerPlayerLeftTrackingRange
implements IMessage,
IMessageHandler<MessageServerPlayerLeftTrackingRange, IMessage> {
    PlayerPointer playerPointer;

    public MessageServerPlayerLeftTrackingRange() {
    }

    public MessageServerPlayerLeftTrackingRange(PlayerPointer playerPointer) {
        this.playerPointer = playerPointer;
    }

    public void fromBytes(ByteBuf buf) {
        this.playerPointer = new PlayerPointer(buf);
    }

    public void toBytes(ByteBuf buf) {
        this.playerPointer.writeToByteBuffer(buf);
    }

    public IMessage onMessage(MessageServerPlayerLeftTrackingRange message, MessageContext ctx) {
        this.playerLeftTracking(this.playerPointer);
        return null;
    }

    private void playerLeftTracking(PlayerPointer playerPointer) {
        ClientProxy.playerLeftTrackingRange(playerPointer);
    }
}

