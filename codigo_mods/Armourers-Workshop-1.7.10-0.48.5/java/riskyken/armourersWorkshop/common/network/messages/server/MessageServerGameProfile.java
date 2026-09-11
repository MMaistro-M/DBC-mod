/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 */
package riskyken.armourersWorkshop.common.network.messages.server;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import riskyken.armourersWorkshop.common.GameProfileCache;

public class MessageServerGameProfile
implements IMessage,
IMessageHandler<MessageServerGameProfile, IMessage> {
    private GameProfile gameProfile;

    public MessageServerGameProfile() {
    }

    public MessageServerGameProfile(GameProfile gameProfile) {
        this.gameProfile = gameProfile;
    }

    public void toBytes(ByteBuf buf) {
        NBTTagCompound profileTag = new NBTTagCompound();
        NBTUtil.func_152460_a((NBTTagCompound)profileTag, (GameProfile)this.gameProfile);
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)profileTag);
    }

    public void fromBytes(ByteBuf buf) {
        NBTTagCompound profileTag = ByteBufUtils.readTag((ByteBuf)buf);
        this.gameProfile = NBTUtil.func_152459_a((NBTTagCompound)profileTag);
    }

    public IMessage onMessage(MessageServerGameProfile message, MessageContext ctx) {
        GameProfileCache.onServerSentProfile(message.gameProfile);
        return null;
    }
}

