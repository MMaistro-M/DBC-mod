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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.file.AnimationFile;
import software.bernie.geckolib3.resource.GeckoLibCache;

public class PacketSendAnimation
implements IMessage,
IMessageHandler<PacketSendAnimation, IMessage> {
    private AnimationFile animationFile;
    private String resLoc;

    public PacketSendAnimation() {
    }

    public PacketSendAnimation(AnimationFile animationFile, String resLoc) {
        this.animationFile = animationFile;
        this.resLoc = resLoc;
    }

    public void fromBytes(ByteBuf buf) {
        try {
            this.resLoc = ByteBufUtils.readUTF8String((ByteBuf)buf);
            byte[] bytes = new byte[buf.readInt()];
            buf.readBytes(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            AnimationFile file = (AnimationFile)objectInputStream.readObject();
            objectInputStream.close();
            this.animationFile = file;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void toBytes(ByteBuf buf) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this.animationFile);
            objectOutputStream.flush();
            objectOutputStream.close();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.resLoc);
            buf.writeInt(bytes.length);
            buf.writeBytes(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public IMessage onMessage(PacketSendAnimation message, MessageContext ctx) {
        if (message.animationFile != null) {
            ResourceLocation resourceLocation;
            HashMap<ResourceLocation, AnimationFile> animations = GeckoLibCache.getInstance().getAnimations();
            if (animations.containsKey(resourceLocation = new ResourceLocation("custom", message.resLoc))) {
                animations.remove(resourceLocation);
            }
            animations.put(resourceLocation, message.animationFile);
        }
        return null;
    }
}

