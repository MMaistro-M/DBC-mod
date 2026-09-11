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
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.common.inventory.ContainerMannequin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;

public class MessageClientGuiMannequinData
implements IMessage,
IMessageHandler<MessageClientGuiMannequinData, IMessage> {
    private float xOffset;
    private float yOffset;
    private float zOffset;
    private int skinColour;
    private int hairColour;
    private String username;
    private boolean renderExtras;
    private boolean flying;
    private boolean visible;
    private TextureType textureType;

    public MessageClientGuiMannequinData() {
    }

    public MessageClientGuiMannequinData(float xOffset, float yOffset, float zOffset, int skinColour, int hairColour, String username, boolean renderExtras, boolean flying, boolean visible, TextureType textureType) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.skinColour = skinColour;
        this.hairColour = hairColour;
        this.username = username;
        this.renderExtras = renderExtras;
        this.flying = flying;
        this.visible = visible;
        this.textureType = textureType;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeFloat(this.xOffset);
        buf.writeFloat(this.yOffset);
        buf.writeFloat(this.zOffset);
        buf.writeInt(this.skinColour);
        buf.writeInt(this.hairColour);
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.username);
        buf.writeBoolean(this.renderExtras);
        buf.writeBoolean(this.flying);
        buf.writeBoolean(this.visible);
        buf.writeByte(this.textureType.ordinal());
    }

    public void fromBytes(ByteBuf buf) {
        this.xOffset = buf.readFloat();
        this.yOffset = buf.readFloat();
        this.zOffset = buf.readFloat();
        this.skinColour = buf.readInt();
        this.hairColour = buf.readInt();
        this.username = ByteBufUtils.readUTF8String((ByteBuf)buf);
        this.renderExtras = buf.readBoolean();
        this.flying = buf.readBoolean();
        this.visible = buf.readBoolean();
        this.textureType = TextureType.values()[buf.readByte()];
    }

    public IMessage onMessage(MessageClientGuiMannequinData message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container != null && container instanceof ContainerMannequin) {
            TileEntityMannequin tileEntity = ((ContainerMannequin)container).getTileEntity();
            tileEntity.gotUpdateFromClient(message.xOffset, message.yOffset, message.zOffset, message.skinColour, message.hairColour, message.username, message.renderExtras, message.flying, message.visible, message.textureType);
        }
        return null;
    }
}

