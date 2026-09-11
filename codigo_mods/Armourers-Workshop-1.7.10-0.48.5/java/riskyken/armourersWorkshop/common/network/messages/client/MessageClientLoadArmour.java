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
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import riskyken.armourersWorkshop.common.inventory.ContainerArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

public class MessageClientLoadArmour
implements IMessage,
IMessageHandler<MessageClientLoadArmour, IMessage> {
    String name;
    String tags;

    public MessageClientLoadArmour() {
    }

    public MessageClientLoadArmour(String name, String tags) {
        this.name = name;
        this.tags = tags;
    }

    public void fromBytes(ByteBuf buf) {
        this.name = ByteBufUtils.readUTF8String((ByteBuf)buf);
        this.tags = ByteBufUtils.readUTF8String((ByteBuf)buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.name);
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.tags);
    }

    public IMessage onMessage(MessageClientLoadArmour message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container != null && container instanceof ContainerArmourer) {
            TileEntityArmourer armourerBrain = ((ContainerArmourer)container).getTileEntity();
            armourerBrain.saveArmourItem(player, message.name, message.tags);
        }
        return null;
    }
}

