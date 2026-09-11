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
package kamkeel.npcs.network.packets.request.playerdata;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.constants.EnumPlayerData;

public final class PlayerDataGetNamesPacket
extends AbstractPacket {
    public static String packetName = "Request|PlayerDataGet";
    private EnumPlayerData playerData;
    private String name;

    public PlayerDataGetNamesPacket() {
    }

    public PlayerDataGetNamesPacket(EnumPlayerData playerData, String name) {
        this.playerData = playerData;
        this.name = name;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.PlayerDataGetNames;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.playerData.ordinal());
        if (this.playerData != EnumPlayerData.Players) {
            ByteBufUtils.writeString(out, this.name);
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        int id = in.readInt();
        if (EnumPlayerData.values().length <= id) {
            return;
        }
        String name = null;
        EnumPlayerData datatype = EnumPlayerData.values()[id];
        if (datatype != EnumPlayerData.Players) {
            name = ByteBufUtils.readString(in);
        }
        NoppesUtilServer.sendPlayerData(datatype, (EntityPlayerMP)player, name);
    }
}

