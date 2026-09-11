/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 */
package hedaox.ninjinentities.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;

public class MessageSendEntityToSpark
implements IMessage {
    private int toSend;

    public MessageSendEntityToSpark() {
    }

    public MessageSendEntityToSpark(int text) {
        this.toSend = text;
    }

    public void fromBytes(ByteBuf buf) {
        this.toSend = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.toSend);
    }

    public static class Handler
    implements IMessageHandler<MessageSendEntityToSpark, IMessage> {
        public IMessage onMessage(MessageSendEntityToSpark message, MessageContext ctx) {
            for (Entity entityObject : ctx.getServerHandler().field_147369_b.field_70170_p.field_72996_f) {
                if (entityObject.func_145782_y() != message.toSend) continue;
                entityObject.field_70170_p.func_72956_a(entityObject, "jinryuudragonbc:1610.spark", 0.0375f, 0.85f);
            }
            return null;
        }
    }
}

