/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import riskyken.armourersWorkshop.common.data.BipedRotations;
import riskyken.armourersWorkshop.common.inventory.ContainerMannequin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;

public class MessageClientGuiBipedRotations
implements IMessage,
IMessageHandler<MessageClientGuiBipedRotations, IMessage> {
    BipedRotations bipedRotations;

    public MessageClientGuiBipedRotations() {
        this.bipedRotations = new BipedRotations();
    }

    public MessageClientGuiBipedRotations(BipedRotations bipedRotations) {
        this.bipedRotations = bipedRotations;
    }

    public void fromBytes(ByteBuf buf) {
        this.bipedRotations.readFromBuf(buf);
    }

    public void toBytes(ByteBuf buf) {
        this.bipedRotations.writeToBuf(buf);
    }

    public IMessage onMessage(MessageClientGuiBipedRotations message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container != null && container instanceof ContainerMannequin) {
            TileEntityMannequin tileEntity = ((ContainerMannequin)container).getTileEntity();
            tileEntity.setBipedRotations(message.bipedRotations);
        }
        return null;
    }
}

