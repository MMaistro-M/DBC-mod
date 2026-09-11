/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.network.packets.request.playerdata;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.constants.EnumPlayerData;

public final class PlayerDataDeleteInfoPacket
extends AbstractPacket {
    public static final String packetName = "Request|PlayerDataDeleteInfo";
    private String playerName;
    private EnumPlayerData tabType;
    private int value;

    public PlayerDataDeleteInfoPacket() {
    }

    public PlayerDataDeleteInfoPacket(String playerName, EnumPlayerData tabType, int selectedKey) {
        this.playerName = playerName;
        this.tabType = tabType;
        this.value = selectedKey;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.PlayerDataDeleteInfo;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeString(out, this.playerName);
        out.writeInt(this.tabType.ordinal());
        out.writeInt(this.value);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        String playerName = ByteBufUtils.readString(in);
        int tabType = in.readInt();
        int value = in.readInt();
        NoppesUtilServer.removePlayerDataInfo(playerName, tabType, value, (EntityPlayerMP)player);
    }
}

