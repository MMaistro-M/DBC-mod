/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.undo.UndoManager;

public class MessageClientToolPaintBlock
implements IMessage,
IMessageHandler<MessageClientToolPaintBlock, IMessage> {
    private int x;
    private int y;
    private int z;
    private byte side;
    private byte[] rgbt = new byte[4];

    public MessageClientToolPaintBlock() {
    }

    public MessageClientToolPaintBlock(int x, int y, int z, byte side, byte[] rgbt) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.side = side;
        this.rgbt = rgbt;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeByte((int)this.side);
        buf.writeBytes(this.rgbt);
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.side = buf.readByte();
        buf.readBytes(this.rgbt);
    }

    public IMessage onMessage(MessageClientToolPaintBlock message, MessageContext ctx) {
        World world;
        Block block;
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player != null && player.func_130014_f_() != null && (block = (world = player.func_130014_f_()).func_147439_a(message.x, message.y, message.z)) instanceof IPantableBlock) {
            UndoManager.begin((EntityPlayer)player);
            IPantableBlock paintable = (IPantableBlock)block;
            int oldColour = paintable.getColour((IBlockAccess)world, message.x, message.y, message.z, message.side);
            PaintType oldPaintType = paintable.getPaintType((IBlockAccess)world, message.x, message.y, message.z, message.side);
            UndoManager.blockPainted((EntityPlayer)player, world, message.x, message.y, message.z, oldColour, (byte)oldPaintType.getKey(), (int)message.side);
            paintable.setColour((IBlockAccess)world, message.x, message.y, message.z, message.rgbt, (int)message.side);
            paintable.setPaintType((IBlockAccess)world, message.x, message.y, message.z, PaintType.getPaintTypeFormSKey(message.rgbt[3]), message.side);
            UndoManager.end((EntityPlayer)player);
        }
        return null;
    }
}

