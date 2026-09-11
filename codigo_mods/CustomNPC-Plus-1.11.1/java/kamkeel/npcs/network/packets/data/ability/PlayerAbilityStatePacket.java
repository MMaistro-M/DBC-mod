/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.network.packets.data.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.client.ClientAbilityState;

public final class PlayerAbilityStatePacket
extends AbstractPacket {
    public static final String packetName = "Data|PlayerAbilityState";
    public static final byte FLAG_MOVEMENT_LOCKED = 1;
    public static final byte FLAG_ROTATION_LOCKED = 2;
    public static final byte FLAG_HAS_ABILITY_MOVEMENT = 4;
    public static final byte FLAG_POSITION_LOCKED = 8;
    public static final byte FLAG_WAS_FLYING_AT_LOCK = 16;
    public static final byte FLAG_ACTIVE_PHASE = 32;
    private byte flags;
    private float lockedYaw;
    private float lockedPitch;

    public PlayerAbilityStatePacket() {
    }

    public PlayerAbilityStatePacket(byte flags, float lockedYaw, float lockedPitch) {
        this.flags = flags;
        this.lockedYaw = lockedYaw;
        this.lockedPitch = lockedPitch;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.PLAYER_ABILITY_STATE;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeByte((int)this.flags);
        if ((this.flags & 2) != 0) {
            out.writeFloat(this.lockedYaw);
            out.writeFloat(this.lockedPitch);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        byte flags = in.readByte();
        float yaw = 0.0f;
        float pitch = 0.0f;
        if ((flags & 2) != 0) {
            yaw = in.readFloat();
            pitch = in.readFloat();
        }
        ClientAbilityState.update(flags, yaw, pitch);
    }

    public static void sendToPlayer(EntityPlayerMP player, byte flags, float lockedYaw, float lockedPitch) {
        PacketHandler.Instance.sendToPlayer(new PlayerAbilityStatePacket(flags, lockedYaw, lockedPitch), player);
    }
}

