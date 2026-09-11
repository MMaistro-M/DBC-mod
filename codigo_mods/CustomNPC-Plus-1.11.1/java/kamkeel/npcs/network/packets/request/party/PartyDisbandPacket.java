/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.request.party;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.PartyDataPacket;
import kamkeel.npcs.network.packets.request.party.PartyInfoPacket;
import kamkeel.npcs.network.packets.request.party.PartyPacketUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;

public final class PartyDisbandPacket
extends AbstractPacket {
    public static final String packetName = "Request|PartyDisband";

    @Override
    public Enum getType() {
        return EnumRequestPacket.PartyDisband;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        PlayerData playerData = PlayerData.get(player);
        if (!ConfigMain.PartiesEnabled || playerData.partyUUID == null) {
            return;
        }
        Party party = PartyController.Instance().getParty(playerData.partyUUID);
        if (!PartyPacketUtil.canManageParty(player, party)) {
            PartyInfoPacket.sendPartyData((EntityPlayerMP)player);
            return;
        }
        PartyController.Instance().disbandParty(playerData.partyUUID);
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("Disband", true);
        PartyDataPacket.sendPartyData((EntityPlayerMP)player, compound);
    }
}

