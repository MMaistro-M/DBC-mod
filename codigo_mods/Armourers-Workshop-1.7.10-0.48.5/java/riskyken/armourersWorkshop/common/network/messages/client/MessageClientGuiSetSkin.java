/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.inventory.ContainerArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

public class MessageClientGuiSetSkin
implements IMessage,
IMessageHandler<MessageClientGuiSetSkin, IMessage> {
    PlayerTexture playerTexture;

    public MessageClientGuiSetSkin() {
    }

    public MessageClientGuiSetSkin(PlayerTexture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public void toBytes(ByteBuf buf) {
        NBTTagCompound compound = new NBTTagCompound();
        this.playerTexture.writeToNBT(compound);
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)compound);
    }

    public void fromBytes(ByteBuf buf) {
        NBTTagCompound compound = ByteBufUtils.readTag((ByteBuf)buf);
        this.playerTexture = PlayerTexture.fromNBT(compound);
    }

    public IMessage onMessage(MessageClientGuiSetSkin message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container == null) {
            return null;
        }
        if (container != null && container instanceof ContainerArmourer) {
            TileEntityArmourer armourerBrain = ((ContainerArmourer)container).getTileEntity();
            armourerBrain.setTexture(message.playerTexture);
        }
        return null;
    }
}

