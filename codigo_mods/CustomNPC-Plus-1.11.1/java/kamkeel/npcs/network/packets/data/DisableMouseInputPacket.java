/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.network.packets.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.client.ClientEventHandler;

public final class DisableMouseInputPacket
extends AbstractPacket {
    public static final String packetName = "Data|DisableMouseInput";
    private long duration;
    private String keys;

    public DisableMouseInputPacket() {
    }

    public DisableMouseInputPacket(long duration, String keys) {
        this.duration = duration;
        this.keys = keys;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.DISABLE_MOUSE_INPUT;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeLong(this.duration);
        ByteBufUtils.writeString(out, this.keys);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        String[] buttonIds;
        long duration = in.readLong();
        String parsedButtons = ByteBufUtils.readString(in);
        if (parsedButtons == null || parsedButtons.isEmpty()) {
            ClientEventHandler.disabledButtonTimes.put(-1, duration);
            return;
        }
        for (String button : buttonIds = parsedButtons.split(";")) {
            ClientEventHandler.disabledButtonTimes.put(Integer.parseInt(button), duration);
        }
    }
}

