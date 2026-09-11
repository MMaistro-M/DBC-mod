/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 */
package riskyken.armourersWorkshop.common.network.messages.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import riskyken.armourersWorkshop.client.model.bake.ModelBakery;
import riskyken.armourersWorkshop.common.network.ByteBufHelper;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.serialize.SkinIdentifierSerializer;

public class MessageServerSendSkinData
implements IMessage,
IMessageHandler<MessageServerSendSkinData, IMessage> {
    private SkinIdentifier skinIdentifierRequested;
    private SkinIdentifier skinIdentifierUpdated;
    private Skin skin;

    public MessageServerSendSkinData() {
    }

    public MessageServerSendSkinData(SkinIdentifier skinIdentifierRequested, SkinIdentifier skinIdentifierUpdated, Skin skin) {
        this.skinIdentifierRequested = skinIdentifierRequested;
        this.skinIdentifierUpdated = skinIdentifierUpdated;
        this.skin = skin;
    }

    public void toBytes(ByteBuf buf) {
        SkinIdentifierSerializer.writeToByteBuf(this.skinIdentifierRequested, buf);
        SkinIdentifierSerializer.writeToByteBuf(this.skinIdentifierUpdated, buf);
        buf.writeBoolean(this.skin != null);
        ByteBufHelper.writeSkinToByteBuf(buf, this.skin);
    }

    public void fromBytes(ByteBuf buf) {
        Thread t = new Thread((Runnable)new DownloadThread(buf), "Skin download thread.");
        this.handleDownload(t);
    }

    public IMessage onMessage(MessageServerSendSkinData message, MessageContext ctx) {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public void handleDownload(Thread downloadThread) {
        ModelBakery.INSTANCE.handleModelDownload(downloadThread);
    }

    public class DownloadThread
    implements Runnable {
        private ByteBuf buf;

        public DownloadThread(ByteBuf buf) {
            this.buf = buf.retain();
        }

        @Override
        public void run() {
            SkinIdentifier skinIdentifierRequested = SkinIdentifierSerializer.readFromByteBuf(this.buf);
            SkinIdentifier skinIdentifierUpdated = SkinIdentifierSerializer.readFromByteBuf(this.buf);
            Skin skin = null;
            if (this.buf.readBoolean()) {
                skin = ByteBufHelper.readSkinFromByteBuf(this.buf);
            }
            this.sendSkinForBaking(skin, skinIdentifierRequested, skinIdentifierUpdated);
            this.buf.release();
        }

        @SideOnly(value=Side.CLIENT)
        private void sendSkinForBaking(Skin skin, SkinIdentifier skinIdentifierRequested, SkinIdentifier skinIdentifierUpdated) {
            ModelBakery.INSTANCE.receivedUnbakedModel(skin, skinIdentifierRequested, skinIdentifierUpdated);
        }
    }
}

