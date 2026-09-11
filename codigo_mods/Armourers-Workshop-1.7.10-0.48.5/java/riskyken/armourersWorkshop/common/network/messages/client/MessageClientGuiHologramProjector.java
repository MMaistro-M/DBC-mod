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
import riskyken.armourersWorkshop.common.inventory.ContainerHologramProjector;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;

public class MessageClientGuiHologramProjector
implements IMessage,
IMessageHandler<MessageClientGuiHologramProjector, IMessage> {
    private boolean hasOffsets = false;
    private int offsetX = 0;
    private int offsetY = 16;
    private int offsetZ = 0;
    private boolean hasAngle = false;
    private int angleX = 0;
    private int angleY = 16;
    private int angleZ = 0;
    private boolean hasRotationOffset = false;
    private int rotationOffsetX = 0;
    private int rotationOffsetY = 0;
    private int rotationOffsetZ = 0;
    private boolean hasRotationSpeed = false;
    private int rotationSpeedX = 0;
    private int rotationSpeedY = 0;
    private int rotationSpeedZ = 0;
    private boolean hasGlowing = false;
    private boolean glowing = false;
    private boolean hasPowerMode = false;
    private TileEntityHologramProjector.PowerMode powerMode = TileEntityHologramProjector.PowerMode.IGNORED;

    public void setOffset(int x, int y, int z) {
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
        this.hasOffsets = true;
    }

    public void setAngle(int x, int y, int z) {
        this.angleX = x;
        this.angleY = y;
        this.angleZ = z;
        this.hasAngle = true;
    }

    public void setRotationOffset(int x, int y, int z) {
        this.rotationOffsetX = x;
        this.rotationOffsetY = y;
        this.rotationOffsetZ = z;
        this.hasRotationOffset = true;
    }

    public void setRotationSpeedX(int x, int y, int z) {
        this.rotationSpeedX = x;
        this.rotationSpeedY = y;
        this.rotationSpeedZ = z;
        this.hasRotationSpeed = true;
    }

    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
        this.hasGlowing = true;
    }

    public void setPowerMode(TileEntityHologramProjector.PowerMode powerMode) {
        this.powerMode = powerMode;
        this.hasPowerMode = true;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.hasOffsets);
        if (this.hasOffsets) {
            buf.writeInt(this.offsetX);
            buf.writeInt(this.offsetY);
            buf.writeInt(this.offsetZ);
        }
        buf.writeBoolean(this.hasAngle);
        if (this.hasAngle) {
            buf.writeInt(this.angleX);
            buf.writeInt(this.angleY);
            buf.writeInt(this.angleZ);
        }
        buf.writeBoolean(this.hasRotationOffset);
        if (this.hasRotationOffset) {
            buf.writeInt(this.rotationOffsetX);
            buf.writeInt(this.rotationOffsetY);
            buf.writeInt(this.rotationOffsetZ);
        }
        buf.writeBoolean(this.hasRotationSpeed);
        if (this.hasRotationSpeed) {
            buf.writeInt(this.rotationSpeedX);
            buf.writeInt(this.rotationSpeedY);
            buf.writeInt(this.rotationSpeedZ);
        }
        buf.writeBoolean(this.hasGlowing);
        if (this.hasGlowing) {
            buf.writeBoolean(this.glowing);
        }
        buf.writeBoolean(this.hasPowerMode);
        if (this.hasPowerMode) {
            buf.writeByte(this.powerMode.ordinal());
        }
    }

    public void fromBytes(ByteBuf buf) {
        if (buf.readBoolean()) {
            this.offsetX = buf.readInt();
            this.offsetY = buf.readInt();
            this.offsetZ = buf.readInt();
            this.hasOffsets = true;
        }
        if (buf.readBoolean()) {
            this.angleX = buf.readInt();
            this.angleY = buf.readInt();
            this.angleZ = buf.readInt();
            this.hasAngle = true;
        }
        if (buf.readBoolean()) {
            this.rotationOffsetX = buf.readInt();
            this.rotationOffsetY = buf.readInt();
            this.rotationOffsetZ = buf.readInt();
            this.hasRotationOffset = true;
        }
        if (buf.readBoolean()) {
            this.rotationSpeedX = buf.readInt();
            this.rotationSpeedY = buf.readInt();
            this.rotationSpeedZ = buf.readInt();
            this.hasRotationSpeed = true;
        }
        if (buf.readBoolean()) {
            this.glowing = buf.readBoolean();
            this.hasGlowing = true;
        }
        if (buf.readBoolean()) {
            this.powerMode = TileEntityHologramProjector.PowerMode.values()[buf.readByte()];
            this.hasPowerMode = true;
        }
    }

    public IMessage onMessage(MessageClientGuiHologramProjector message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container != null && container instanceof ContainerHologramProjector) {
            TileEntityHologramProjector tileEntity = ((ContainerHologramProjector)container).getTileEntity();
            if (message.hasOffsets) {
                tileEntity.setOffset(message.offsetX, message.offsetY, message.offsetZ);
            }
            if (message.hasAngle) {
                tileEntity.setAngle(message.angleX, message.angleY, message.angleZ);
            }
            if (message.hasRotationOffset) {
                tileEntity.setRotationOffset(message.rotationOffsetX, message.rotationOffsetY, message.rotationOffsetZ);
            }
            if (message.hasRotationSpeed) {
                tileEntity.setRotationSpeed(message.rotationSpeedX, message.rotationSpeedY, message.rotationSpeedZ);
            }
            if (message.hasGlowing) {
                tileEntity.setGlowing(message.glowing);
            }
            if (message.hasPowerMode) {
                tileEntity.setPowerMode(message.powerMode);
            }
        }
        return null;
    }
}

