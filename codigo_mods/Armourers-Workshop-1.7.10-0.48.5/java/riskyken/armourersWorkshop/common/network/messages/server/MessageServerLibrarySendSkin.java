/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package riskyken.armourersWorkshop.common.network.messages.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.network.ByteBufHelper;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;

public class MessageServerLibrarySendSkin
implements IMessage,
IMessageHandler<MessageServerLibrarySendSkin, IMessage> {
    private String fileName;
    private String filePath;
    private Skin skin;
    private SendType sendType;

    public MessageServerLibrarySendSkin() {
    }

    public MessageServerLibrarySendSkin(String fileName, String filePath, Skin skin, SendType sendType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.skin = skin;
        this.sendType = sendType;
    }

    public void toBytes(ByteBuf buf) {
        if (this.fileName != null) {
            buf.writeBoolean(true);
            ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.fileName);
        } else {
            buf.writeBoolean(false);
        }
        if (this.filePath != null) {
            buf.writeBoolean(true);
            ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.filePath);
        } else {
            buf.writeBoolean(false);
        }
        this.skin.requestId = new SkinIdentifier(this.skin);
        ByteBufHelper.writeSkinToByteBuf(buf, this.skin);
        buf.writeByte(this.sendType.ordinal());
    }

    public void fromBytes(ByteBuf buf) {
        if (buf.readBoolean()) {
            this.fileName = ByteBufUtils.readUTF8String((ByteBuf)buf);
        }
        if (buf.readBoolean()) {
            this.filePath = ByteBufUtils.readUTF8String((ByteBuf)buf);
        }
        this.skin = ByteBufHelper.readSkinFromByteBuf(buf);
        this.sendType = SendType.values()[buf.readByte()];
    }

    public IMessage onMessage(MessageServerLibrarySendSkin message, MessageContext ctx) {
        ArmourersWorkshop.proxy.receivedSkinFromLibrary(message.fileName, message.filePath, message.skin, message.sendType);
        return null;
    }

    public static enum SendType {
        LIBRARY_SAVE,
        GLOBAL_UPLOAD;

    }
}

