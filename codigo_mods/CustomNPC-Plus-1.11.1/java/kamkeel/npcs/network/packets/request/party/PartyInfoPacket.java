/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 */
package kamkeel.npcs.network.packets.request.party;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.Vector;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.PartyDataPacket;
import kamkeel.npcs.network.packets.request.party.PartyInvitePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumQuestCompletion;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.Quest;

public final class PartyInfoPacket
extends AbstractPacket {
    public static final String packetName = "Request|PartyInfo";
    boolean newParty;

    public PartyInfoPacket() {
    }

    public PartyInfoPacket(boolean party) {
        this.newParty = party;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.PartyInfo;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeBoolean(this.newParty);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        boolean isNew = in.readBoolean();
        if (!ConfigMain.PartiesEnabled) {
            return;
        }
        if (isNew) {
            PlayerData playerData = PlayerData.get(player);
            if (playerData.partyUUID != null) {
                PartyInfoPacket.sendPartyData((EntityPlayerMP)player);
                return;
            }
            Party party = PartyController.Instance().createParty();
            party.addPlayer(player);
            party.setLeader(player);
        }
        PartyInfoPacket.sendPartyData((EntityPlayerMP)player);
    }

    public static void sendPartyData(EntityPlayerMP player) {
        PlayerData playerData = PlayerData.get((EntityPlayer)player);
        if (playerData.partyUUID != null) {
            Party party = PartyController.Instance().getParty(playerData.partyUUID);
            NBTTagCompound compound = party.writeToNBT();
            if (party.getQuest() != null) {
                Quest quest = (Quest)party.getQuest();
                compound.func_74778_a("QuestName", quest.getCategory().getName() + ":" + quest.getName());
                Vector<String> vector = quest.questInterface.getPartyQuestLogStatus(party);
                NBTTagList list = new NBTTagList();
                for (String s : vector) {
                    list.func_74742_a((NBTBase)new NBTTagString(s));
                }
                compound.func_74782_a("QuestProgress", (NBTBase)list);
                if (quest.completion == EnumQuestCompletion.Npc && quest.questInterface.isPartyCompleted(party)) {
                    compound.func_74778_a("QuestCompleteWith", quest.completerNpc);
                }
            }
            PartyDataPacket.sendPartyData(player, compound);
        } else {
            PartyInvitePacket.sendInviteData(player);
        }
    }
}

