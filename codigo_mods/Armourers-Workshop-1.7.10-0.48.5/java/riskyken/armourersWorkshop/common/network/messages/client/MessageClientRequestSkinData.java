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
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.data.serialize.SkinIdentifierSerializer;

public class MessageClientRequestSkinData
implements IMessage,
IMessageHandler<MessageClientRequestSkinData, IMessage> {
    private ISkinIdentifier skinIdentifier;

    public MessageClientRequestSkinData() {
    }

    public MessageClientRequestSkinData(ISkinIdentifier skinIdentifier) {
        this.skinIdentifier = skinIdentifier;
    }

    public void toBytes(ByteBuf buf) {
        SkinIdentifierSerializer.writeToByteBuf(this.skinIdentifier, buf);
    }

    public void fromBytes(ByteBuf buf) {
        this.skinIdentifier = SkinIdentifierSerializer.readFromByteBuf(buf);
    }

    public IMessage onMessage(MessageClientRequestSkinData message, MessageContext ctx) {
        CommonSkinCache.INSTANCE.clientRequestEquipmentData(message.skinIdentifier, ctx.getServerHandler().field_147369_b);
        return null;
    }
}

