/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import riskyken.armourersWorkshop.common.network.ByteBufHelper;
import riskyken.armourersWorkshop.common.network.SkinUploadHelper;

public class MessageClientSkinPart
implements IMessage,
IMessageHandler<MessageClientSkinPart, IMessage> {
    private int skinId;
    private byte packetId;
    private byte[] data;

    public MessageClientSkinPart() {
    }

    public MessageClientSkinPart(int skinId, byte packetId, byte[] data) {
        this.skinId = skinId;
        this.packetId = packetId;
        this.data = data;
    }

    public void fromBytes(ByteBuf buf) {
        this.skinId = buf.readInt();
        this.packetId = buf.readByte();
        this.data = ByteBufHelper.readByteArrayFromByteBuf(buf);
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.skinId);
        buf.writeByte((int)this.packetId);
        ByteBufHelper.writeByteArrayToByteBuf(buf, this.data);
    }

    public IMessage onMessage(MessageClientSkinPart message, MessageContext ctx) {
        SkinUploadHelper.gotSkinPartFromClient(message.skinId, message.packetId, message.data, ctx.getServerHandler().field_147369_b);
        return null;
    }
}

