/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package kamkeel.npcs.network.packets.request.party;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.HashSet;
import java.util.UUID;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.PartyDataPacket;
import kamkeel.npcs.network.packets.request.party.PartyInfoPacket;
import kamkeel.npcs.network.packets.request.party.PartyPacketUtil;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.EventHooks;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.PartyEvent;

public final class PartyInvitePacket
extends AbstractPacket {
    public static final String packetName = "Request|PartyInvite";
    private String name;

    public PartyInvitePacket() {
    }

    public PartyInvitePacket(String playername) {
        this.name = playername;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.PartyInvite;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeString(out, this.name);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        String invitedName = ByteBufUtils.readString(in);
        if (!ConfigMain.PartiesEnabled) {
            return;
        }
        PlayerData senderData = PlayerData.get(player);
        if (senderData.partyUUID == null || invitedName == null || invitedName.isEmpty()) {
            return;
        }
        Party party = PartyController.Instance().getParty(senderData.partyUUID);
        if (!PartyPacketUtil.canManageParty(player, party) || party.getIsLocked()) {
            PartyInfoPacket.sendPartyData((EntityPlayerMP)player);
            return;
        }
        EntityPlayer invitedPlayer = NoppesUtilServer.getPlayerByName(invitedName);
        if (invitedPlayer != null) {
            PlayerData invitedData = PlayerData.get(invitedPlayer);
            if (invitedData.partyUUID == null) {
                PartyEvent.PartyInviteEvent partyEvent = new PartyEvent.PartyInviteEvent(party, party.getQuest(), (IPlayer)NpcAPI.Instance().getIEntity((Entity)invitedPlayer));
                EventHooks.onPartyInvite(party, partyEvent);
                if (!partyEvent.isCancelled()) {
                    invitedData.inviteToParty(party);
                    PartyInvitePacket.sendInviteData((EntityPlayerMP)invitedPlayer);
                }
            }
        }
    }

    public static void sendInviteData(EntityPlayerMP player) {
        PlayerData playerData = PlayerData.get((EntityPlayer)player);
        if (playerData.partyUUID == null) {
            NBTTagCompound compound = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            HashSet<UUID> partyInvites = playerData.getPartyInvites();
            for (UUID uuid : partyInvites) {
                Party party = PartyController.Instance().getParty(uuid);
                NBTTagCompound partyCompound = new NBTTagCompound();
                partyCompound.func_74778_a("PartyLeader", party.getPartyLeaderName());
                partyCompound.func_74778_a("PartyUUID", party.getPartyUUID().toString());
                list.func_74742_a((NBTBase)partyCompound);
            }
            compound.func_74782_a("PartyInvites", (NBTBase)list);
            PartyDataPacket.sendPartyData(player, compound);
        }
    }
}

