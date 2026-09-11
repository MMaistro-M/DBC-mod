/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 */
package noppes.npcs.controllers;

import java.util.HashMap;
import java.util.UUID;
import java.util.Vector;
import kamkeel.npcs.network.packets.data.AchievementPacket;
import kamkeel.npcs.network.packets.data.ChatAlertPacket;
import kamkeel.npcs.network.packets.data.large.PartyDataPacket;
import kamkeel.npcs.network.packets.request.party.PartyInvitePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.EventHooks;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.IPartyHandler;
import noppes.npcs.api.handler.data.IParty;
import noppes.npcs.constants.EnumQuestCompletion;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.quests.QuestInterface;
import noppes.npcs.quests.QuestItem;
import noppes.npcs.scripted.event.PartyEvent;

public class PartyController
implements IPartyHandler {
    private static PartyController Instance;
    private HashMap<UUID, Party> parties = new HashMap();

    private PartyController() {
    }

    public static PartyController Instance() {
        if (Instance == null) {
            Instance = new PartyController();
        }
        return Instance;
    }

    public Party createParty() {
        Party party = new Party();
        this.parties.put(party.getPartyUUID(), party);
        return party;
    }

    @Override
    public IParty createParty(IPlayer player) {
        if (player == null) {
            return null;
        }
        if (player.getMCEntity() == null) {
            return null;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        PlayerData playerData = PlayerData.get(entityPlayer);
        if (playerData.partyUUID != null) {
            return this.getParty(playerData.partyUUID);
        }
        Party party = new Party();
        party.addPlayer(entityPlayer);
        party.setLeader(entityPlayer);
        this.parties.put(party.getPartyUUID(), party);
        return party;
    }

    @Override
    public void disbandParty(IPlayer player) {
        if (player == null) {
            return;
        }
        if (player.getMCEntity() == null) {
            return;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        PlayerData playerData = PlayerData.get(entityPlayer);
        if (playerData.partyUUID != null) {
            this.disbandParty(playerData.partyUUID);
        }
    }

    public Party getParty(UUID partyUUID) {
        return this.parties.get(partyUUID);
    }

    public void disbandParty(UUID partyUUID) {
        Party party = this.parties.get(partyUUID);
        if (party != null) {
            PartyEvent.PartyDisbandEvent partyEvent = new PartyEvent.PartyDisbandEvent(party, party.getQuest());
            EventHooks.onPartyDisband(party, partyEvent);
            for (UUID uuid : party.getPlayerUUIDs()) {
                EntityPlayer player = NoppesUtilServer.getPlayer(uuid);
                PlayerData playerData = player != null ? PlayerData.get(player) : PlayerDataController.Instance.getPlayerDataCache(uuid.toString());
                if (playerData == null) continue;
                playerData.partyUUID = null;
                if (player == null) continue;
                PartyInvitePacket.sendInviteData((EntityPlayerMP)player);
                AchievementPacket.sendAchievement((EntityPlayerMP)player, true, "party.disbandAlert", "");
                ChatAlertPacket.sendChatAlert((EntityPlayerMP)player, "\u00a7c", "party.disbandMessage", "!");
            }
            this.parties.remove(partyUUID);
        }
    }

    public void sendKickMessages(Party party, EntityPlayer kickPlayer, String kickPlayerName) {
        if (party == null) {
            return;
        }
        for (String name : party.getPlayerNames()) {
            EntityPlayer playerMP = NoppesUtilServer.getPlayerByName(name);
            if (playerMP == null) continue;
            AchievementPacket.sendAchievement((EntityPlayerMP)playerMP, true, "party.kickOtherAlert", kickPlayerName);
            ChatAlertPacket.sendChatAlert((EntityPlayerMP)playerMP, "\u00a7e", kickPlayerName, " \u00a74", "party.kickOtherChat", "!");
        }
        if (kickPlayer != null) {
            AchievementPacket.sendAchievement((EntityPlayerMP)kickPlayer, true, "party.kickYouAlert", "");
            ChatAlertPacket.sendChatAlert((EntityPlayerMP)kickPlayer, "\u00a74", "party.kickYouChat", "!");
        }
    }

    public void sendLeavingMessages(Party party, EntityPlayer leavingPlayer) {
        if (leavingPlayer == null || party == null) {
            return;
        }
        for (String name : party.getPlayerNames()) {
            EntityPlayer playerMP = NoppesUtilServer.getPlayerByName(name);
            if (playerMP == null) continue;
            AchievementPacket.sendAchievement((EntityPlayerMP)playerMP, true, "party.leaveOtherAlert", leavingPlayer.func_70005_c_());
            ChatAlertPacket.sendChatAlert((EntityPlayerMP)playerMP, "\u00a7e", leavingPlayer.func_70005_c_(), " \u00a7c", "party.leaveOtherChat", "!");
        }
        AchievementPacket.sendAchievement((EntityPlayerMP)leavingPlayer, true, "party.leaveYouAlert", "");
        ChatAlertPacket.sendChatAlert((EntityPlayerMP)leavingPlayer, "\u00a7c", "party.leaveYouChat", "!");
    }

    public void pingPartyUpdate(Party party) {
        if (party == null) {
            return;
        }
        NBTTagCompound compound = party.writeToNBT();
        if (party.getQuest() != null) {
            Quest quest = (Quest)party.getQuest();
            Vector<String> vector = quest.questInterface.getPartyQuestLogStatus(party);
            compound.func_74778_a("QuestName", quest.getCategory().getName() + ":" + quest.getName());
            NBTTagList list = new NBTTagList();
            for (String s : vector) {
                list.func_74742_a((NBTBase)new NBTTagString(s));
            }
            compound.func_74782_a("QuestProgress", (NBTBase)list);
            if (quest.completion == EnumQuestCompletion.Npc && quest.questInterface.isPartyCompleted(party)) {
                compound.func_74778_a("QuestCompleteWith", quest.completerNpc);
            }
        }
        for (String name : party.getPlayerNames()) {
            PlayerData playerData;
            EntityPlayer playerMP = NoppesUtilServer.getPlayerByName(name);
            if (playerMP == null || (playerData = PlayerData.get(playerMP)) == null) continue;
            PartyDataPacket.sendPartyData((EntityPlayerMP)playerMP, compound);
        }
    }

    public void pingPartyQuestObjectiveUpdate(Party party) {
        if (party == null) {
            return;
        }
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("QuestPing", 0);
        if (party.getQuest() != null) {
            Quest quest = (Quest)party.getQuest();
            Vector<String> vector = quest.questInterface.getPartyQuestLogStatus(party);
            NBTTagList list = new NBTTagList();
            compound.func_74778_a("QuestName", quest.getCategory().getName() + ":" + quest.getName());
            for (String s : vector) {
                list.func_74742_a((NBTBase)new NBTTagString(s));
            }
            compound.func_74782_a("QuestProgress", (NBTBase)list);
            if (quest.completion == EnumQuestCompletion.Npc && quest.questInterface.isPartyCompleted(party)) {
                compound.func_74778_a("QuestCompleteWith", quest.completerNpc);
            }
        }
        for (String name : party.getPlayerNames()) {
            PlayerData playerData;
            EntityPlayer playerMP = NoppesUtilServer.getPlayerByName(name);
            if (playerMP == null || (playerData = PlayerData.get(playerMP)) == null) continue;
            PartyDataPacket.sendPartyData((EntityPlayerMP)playerMP, compound);
        }
    }

    public void sendQuestChat(Party party, String ... chatAlerts) {
        if (party == null) {
            return;
        }
        for (String name : party.getPlayerNames()) {
            EntityPlayer playerMP = NoppesUtilServer.getPlayerByName(name);
            if (playerMP == null) continue;
            Object[] args = new Object[2 + chatAlerts.length];
            args[0] = "\u00a7a";
            System.arraycopy(chatAlerts, 0, args, 1, chatAlerts.length);
            args[args.length - 1] = "!";
            ChatAlertPacket.sendChatAlert((EntityPlayerMP)playerMP, args);
        }
    }

    public boolean checkQuestCompletion(Party party, EnumQuestType type) {
        QuestData questData = party.getQuestData();
        if (questData != null && (questData.quest.type == type || type == null)) {
            QuestInterface inter = questData.quest.questInterface;
            if (inter.isPartyCompleted(party)) {
                if (!questData.isCompleted && questData.quest.completion == EnumQuestCompletion.Npc || questData.quest.instantPartyComplete(party)) {
                    questData.isCompleted = true;
                    if (questData.quest.completion == EnumQuestCompletion.Npc) {
                        EventHooks.onPartyFinished(party, questData.quest);
                        PartyController.Instance().sendQuestChat(party, "party.turnedInChat");
                    } else {
                        PartyController.Instance().sendQuestChat(party, "party.completeChat");
                    }
                    PartyController.Instance().pingPartyUpdate(party);
                }
            } else {
                questData.isCompleted = false;
            }
            for (String name : party.getPlayerNames()) {
                PlayerData playerData;
                EntityPlayer playerMP = NoppesUtilServer.getPlayerByName(name);
                if (playerMP == null || (playerData = PlayerData.get(playerMP)) == null || playerData.questData.getTrackedQuest() == null || questData.quest.getId() != playerData.questData.getTrackedQuest().getId()) continue;
                NoppesUtilPlayer.sendPartyTrackedQuestData((EntityPlayerMP)playerMP, party);
            }
        }
        QuestItem.pickedUpParty = null;
        QuestItem.pickedUpPlayer = null;
        return true;
    }
}

