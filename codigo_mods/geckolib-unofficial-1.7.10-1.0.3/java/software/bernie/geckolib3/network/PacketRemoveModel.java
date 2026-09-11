/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.util.ResourceLocation
 */
package software.bernie.geckolib3.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.resource.GeckoLibCache;

public class PacketRemoveModel
implements IMessage,
IMessageHandler<PacketRemoveModel, IMessage> {
    private String resLoc;

    public PacketRemoveModel() {
    }

    public PacketRemoveModel(String resLoc) {
        this.resLoc = resLoc;
    }

    public void fromBytes(ByteBuf buf) {
        this.resLoc = ByteBufUtils.readUTF8String((ByteBuf)buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.resLoc);
    }

    public IMessage onMessage(PacketRemoveModel message, MessageContext ctx) {
        HashMap<ResourceLocation, GeoModel> models = GeckoLibCache.getInstance().getGeoModels();
        ResourceLocation resourceLocation = new ResourceLocation("custom", message.resLoc);
        models.remove(resourceLocation);
        return null;
    }
}

