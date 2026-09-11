/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.HashMap;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.AchievementPacket;
import kamkeel.npcs.network.packets.data.ChatAlertPacket;
import kamkeel.npcs.network.packets.data.OverlayQuestTrackingPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.EventHooks;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.api.handler.IPlayerQuestData;
import noppes.npcs.api.handler.data.IParty;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.constants.EnumQuestCompletion;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.PlayerQuestController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.quests.QuestInterface;
import noppes.npcs.quests.QuestItem;

public class PlayerQuestData
implements IPlayerQuestData {
    private final PlayerData parent;
    private IQuest trackedQuest = null;
    public HashMap<Integer, QuestData> activeQuests = new HashMap();
    public HashMap<Integer, Long> finishedQuests = new HashMap();

    public PlayerQuestData(PlayerData parent) {
        this.parent = parent;
    }

    public void loadNBTData(NBTTagCompound mainCompound) {
        NBTTagList list2;
        if (mainCompound == null) {
            return;
        }
        NBTTagCompound compound = mainCompound.func_74775_l("QuestData");
        NBTTagList list = compound.func_150295_c("CompletedQuests", 10);
        if (list != null) {
            HashMap<Integer, Long> finishedQuests = new HashMap<Integer, Long>();
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound = list.func_150305_b(i);
                finishedQuests.put(nbttagcompound.func_74762_e("Quest"), nbttagcompound.func_74763_f("Date"));
            }
            this.finishedQuests = finishedQuests;
        }
        if ((list2 = compound.func_150295_c("ActiveQuests", 10)) != null) {
            HashMap<Integer, QuestData> activeQuests = new HashMap<Integer, QuestData>();
            for (int i = 0; i < list2.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound = list2.func_150305_b(i);
                int id = nbttagcompound.func_74762_e("Quest");
                Quest quest = QuestController.Instance.quests.get(id);
                if (quest == null) continue;
                QuestData data = new QuestData(quest);
                data.readEntityFromNBT(nbttagcompound);
                activeQuests.put(id, data);
            }
            this.activeQuests = activeQuests;
        }
        this.trackedQuest = QuestController.Instance.get(mainCompound.func_74762_e("TrackedQuestID"));
    }

    public void saveNBTData(NBTTagCompound maincompound) {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        for (int quest : this.finishedQuests.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Quest", quest);
            nbttagcompound.func_74772_a("Date", this.finishedQuests.get(quest).longValue());
            list.func_74742_a((NBTBase)nbttagcompound);
        }
        compound.func_74782_a("CompletedQuests", (NBTBase)list);
        NBTTagList list2 = new NBTTagList();
        for (int quest : this.activeQuests.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Quest", quest);
            this.activeQuests.get(quest).writeEntityToNBT(nbttagcompound);
            list2.func_74742_a((NBTBase)nbttagcompound);
        }
        compound.func_74782_a("ActiveQuests", (NBTBase)list2);
        maincompound.func_74782_a("QuestData", (NBTBase)compound);
        if (this.trackedQuest != null) {
            maincompound.func_74768_a("TrackedQuestID", this.trackedQuest.getId());
        } else {
            maincompound.func_74768_a("TrackedQuestID", -1);
        }
    }

    public QuestData getQuestCompletion(EntityPlayer player, EntityNPCInterface npc) {
        PlayerData playerData = PlayerData.get(player);
        for (QuestData data : this.activeQuests.values()) {
            Quest quest = data.quest;
            if (quest == null || quest.completion != EnumQuestCompletion.Npc || !quest.completerNpc.equals(npc.func_70005_c_()) || !quest.questInterface.isCompleted(playerData)) continue;
            return data;
        }
        return null;
    }

    public Party getPartyQuestCompletion(EntityPlayer player, EntityNPCInterface npc) {
        Quest quest;
        QuestData data;
        Party party;
        PlayerData playerData = PlayerData.get(player);
        if (playerData != null && (party = playerData.getPlayerParty()) != null && (data = party.getQuestData()) != null && (quest = data.quest) != null && quest.completion == EnumQuestCompletion.Npc && quest.completerNpc.equals(npc.func_70005_c_()) && quest.questInterface.isPartyCompleted(party)) {
            return party;
        }
        return null;
    }

    public boolean checkQuestCompletion(PlayerData playerData, EnumQuestType type) {
        IQuest quest;
        Party party;
        boolean bo = false;
        EntityPlayer player = playerData.player;
        int partyQuestID = -1;
        if (playerData.partyUUID != null && (party = PartyController.Instance().getParty(playerData.partyUUID)) != null && (quest = party.getQuest()) != null) {
            partyQuestID = quest.getId();
        }
        ArrayList<QuestData> activeQuestValues = new ArrayList<QuestData>(this.activeQuests.values());
        for (QuestData data : activeQuestValues) {
            if (data.quest.type != type && type != null || partyQuestID != -1 && data.quest.id == partyQuestID && data.quest.partyOptions.isAllowParty() || data.quest.partyOptions.allowParty && data.quest.partyOptions.onlyParty) continue;
            QuestInterface inter = data.quest.questInterface;
            if (inter.isCompleted(playerData)) {
                if (!data.isCompleted && data.quest.completion == EnumQuestCompletion.Npc || data.quest.instantComplete(player, data)) {
                    data.isCompleted = true;
                    if (data.quest.completion == EnumQuestCompletion.Npc) {
                        EventHooks.onQuestFinished(player, data.quest);
                    }
                    bo = true;
                }
            } else {
                data.isCompleted = false;
            }
            if (this.trackedQuest == null || data.quest.getId() != this.trackedQuest.getId()) continue;
            NoppesUtilPlayer.sendTrackedQuestData((EntityPlayerMP)player);
        }
        QuestItem.pickedUp = null;
        return bo;
    }

    public void trackQuest(IQuest quest) {
        if (this.trackedQuest == null || quest.getId() != this.trackedQuest.getId()) {
            this.trackedQuest = quest;
            NoppesUtilPlayer.sendTrackedQuestData((EntityPlayerMP)this.parent.player);
        }
    }

    public void trackParty(IParty party) {
        if (party == null) {
            return;
        }
        IQuest quest = party.getQuest();
        if (this.trackedQuest == null || quest.getId() != this.trackedQuest.getId()) {
            this.trackedQuest = quest;
            NoppesUtilPlayer.sendPartyTrackedQuestData((EntityPlayerMP)this.parent.player, (Party)party);
        }
    }

    public void untrackQuest() {
        if (this.trackedQuest != null) {
            this.trackedQuest = null;
            PacketHandler.Instance.sendToPlayer(new OverlayQuestTrackingPacket(new NBTTagCompound()), (EntityPlayerMP)this.parent.player);
        }
    }

    @Override
    public IQuest getTrackedQuest() {
        return this.trackedQuest;
    }

    @Override
    public void startQuest(int id) {
        Quest quest = QuestController.Instance.quests.get(id);
        if (quest == null) {
            return;
        }
        if (this.activeQuests.containsKey(id)) {
            return;
        }
        QuestData questdata = new QuestData(quest);
        this.activeQuests.put(id, questdata);
        AchievementPacket.sendAchievement((EntityPlayerMP)this.parent.player, false, "quest.newquest", quest.title);
        ChatAlertPacket.sendChatAlert((EntityPlayerMP)this.parent.player, "quest.newquest", ": ", quest.title);
        this.parent.updateClient = true;
    }

    @Override
    public void finishQuest(int id) {
        Quest quest = QuestController.Instance.quests.get(id);
        if (quest == null) {
            return;
        }
        PlayerQuestController.setQuestFinished(quest, this.parent.player);
        this.parent.updateClient = true;
    }

    @Override
    public void stopQuest(int id) {
        Quest quest = QuestController.Instance.quests.get(id);
        if (quest == null) {
            return;
        }
        this.activeQuests.remove(id);
        this.parent.updateClient = true;
    }

    @Override
    public void removeQuest(int id) {
        Quest quest = QuestController.Instance.quests.get(id);
        if (quest == null) {
            return;
        }
        this.activeQuests.remove(id);
        this.finishedQuests.remove(id);
        this.parent.updateClient = true;
    }

    @Override
    public boolean hasFinishedQuest(int id) {
        return this.finishedQuests.containsKey(id);
    }

    @Override
    public boolean hasActiveQuest(int id) {
        return this.activeQuests.containsKey(id);
    }

    @Override
    public IQuest[] getActiveQuests() {
        ArrayList<IQuest> quests = new ArrayList<IQuest>();
        for (int id : this.activeQuests.keySet()) {
            IQuest quest = QuestController.Instance.quests.get(id);
            if (quest == null) continue;
            quests.add(quest);
        }
        return quests.toArray(new IQuest[0]);
    }

    @Override
    public IQuest[] getFinishedQuests() {
        ArrayList<IQuest> quests = new ArrayList<IQuest>();
        for (int id : this.finishedQuests.keySet()) {
            IQuest quest = QuestController.Instance.quests.get(id);
            if (quest == null) continue;
            quests.add(quest);
        }
        return quests.toArray(new IQuest[0]);
    }

    @Override
    public long getLastCompletedTime(int id) {
        if (this.hasFinishedQuest(id)) {
            return this.finishedQuests.get(id);
        }
        return 0L;
    }

    @Override
    public void setLastCompletedTime(int id, long time) {
        Quest quest = QuestController.Instance.quests.get(id);
        if (quest == null) {
            return;
        }
        this.finishedQuests.put(id, time);
    }
}

