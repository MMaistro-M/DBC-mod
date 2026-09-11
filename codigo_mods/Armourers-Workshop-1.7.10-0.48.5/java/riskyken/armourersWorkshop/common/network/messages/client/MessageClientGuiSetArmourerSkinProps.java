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
import riskyken.armourersWorkshop.common.inventory.ContainerArmourer;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

public class MessageClientGuiSetArmourerSkinProps
implements IMessage,
IMessageHandler<MessageClientGuiSetArmourerSkinProps, IMessage> {
    SkinProperties skinProps;

    public MessageClientGuiSetArmourerSkinProps() {
    }

    public MessageClientGuiSetArmourerSkinProps(SkinProperties skinProps) {
        this.skinProps = skinProps;
    }

    public void fromBytes(ByteBuf buf) {
        NBTTagCompound compound = ByteBufUtils.readTag((ByteBuf)buf);
        this.skinProps = new SkinProperties();
        this.skinProps.readFromNBT(compound);
    }

    public void toBytes(ByteBuf buf) {
        NBTTagCompound compound = new NBTTagCompound();
        this.skinProps.writeToNBT(compound);
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)compound);
    }

    public IMessage onMessage(MessageClientGuiSetArmourerSkinProps message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container != null && container instanceof ContainerArmourer) {
            TileEntityArmourer te = ((ContainerArmourer)container).getTileEntity();
            te.setSkinProps(message.skinProps);
        }
        return null;
    }
}

