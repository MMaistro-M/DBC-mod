/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.network.packets.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.controllers.data.PlayerData;

public class ScreenSizePacket
extends AbstractPacket {
    public static final String packetName = "Player|ScreenSize";
    private int width;
    private int height;

    public ScreenSizePacket() {
    }

    public ScreenSizePacket(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.ScreenSize;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.width);
        out.writeInt(this.height);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        int width = in.readInt();
        int height = in.readInt();
        PlayerData.get((EntityPlayer)player).screenSize.setSize(width, height);
    }
}

