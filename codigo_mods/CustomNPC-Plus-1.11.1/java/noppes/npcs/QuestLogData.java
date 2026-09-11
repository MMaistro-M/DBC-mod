/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs;

import java.util.HashMap;
import java.util.Vector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NBTTags;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.constants.EnumQuestCompletion;
import noppes.npcs.controllers.PlayerQuestController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.Quest;

public class QuestLogData {
    public String trackedQuestKey = "";
    public HashMap<String, Vector<String>> categories = new HashMap();
    public String selectedQuest = "";
    public String selectedCategory = "";
    public HashMap<String, String> questText = new HashMap();
    public HashMap<String, String> questAlerts = new HashMap();
    public HashMap<String, Vector<String>> questStatus = new HashMap();
    public HashMap<String, String> finish = new HashMap();
    public HashMap<String, Integer> partyQuests = new HashMap();
    public HashMap<String, Vector<String>> partyOptions = new HashMap();

    public NBTTagCompound writeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74782_a("Categories", (NBTBase)NBTTags.nbtVectorMap(this.categories));
        compound.func_74782_a("Logs", (NBTBase)NBTTags.nbtStringStringMap(this.questText));
        compound.func_74782_a("Alerts", (NBTBase)NBTTags.nbtStringStringMap(this.questAlerts));
        compound.func_74782_a("Status", (NBTBase)NBTTags.nbtVectorMap(this.questStatus));
        compound.func_74782_a("QuestFinisher", (NBTBase)NBTTags.nbtStringStringMap(this.finish));
        compound.func_74778_a("TrackedQuestID", this.trackedQuestKey);
        compound.func_74782_a("PartyQuests", (NBTBase)NBTTags.nbtStringIntegerMap(this.partyQuests));
        compound.func_74782_a("PartyOptions", (NBTBase)NBTTags.nbtVectorMap(this.partyOptions));
        return compound;
    }

    public void readNBT(NBTTagCompound compound) {
        this.categories = NBTTags.getVectorMap(compound.func_150295_c("Categories", 10));
        this.questText = NBTTags.getStringStringMap(compound.func_150295_c("Logs", 10));
        this.questAlerts = NBTTags.getStringStringMap(compound.func_150295_c("Alerts", 10));
        this.questStatus = NBTTags.getVectorMap(compound.func_150295_c("Status", 10));
        this.finish = NBTTags.getStringStringMap(compound.func_150295_c("QuestFinisher", 10));
        this.trackedQuestKey = compound.func_74779_i("TrackedQuestID");
        this.partyQuests = NBTTags.getStringIntegerMap(compound.func_150295_c("PartyQuests", 10));
        this.partyOptions = NBTTags.getVectorMap(compound.func_150295_c("PartyOptions", 10));
    }

    public NBTTagCompound writeTrackedQuest() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("TrackedQuestID", this.trackedQuestKey);
        return compound;
    }

    public void readTrackedQuest(NBTTagCompound compound) {
        this.trackedQuestKey = compound.func_74779_i("TrackedQuestID");
    }

    public void setData(EntityPlayer player) {
        PlayerData playerData = PlayerData.get(player);
        for (Quest quest : PlayerQuestController.getActiveQuests(player)) {
            String category = quest.category.title;
            if (!this.categories.containsKey(category)) {
                this.categories.put(category, new Vector());
            }
            Vector<String> list = this.categories.get(category);
            list.add(quest.title);
            String key = category + ":" + quest.title;
            this.questText.put(key, quest.logText);
            this.questAlerts.put(key, String.valueOf(playerData.questData.activeQuests.get((Object)Integer.valueOf((int)quest.id)).sendAlerts));
            this.questStatus.put(key, quest.questInterface.getQuestLogStatus(player));
            if (quest.completion == EnumQuestCompletion.Npc && quest.questInterface.isCompleted(playerData)) {
                this.finish.put(key, quest.completerNpc);
            }
            if (playerData.questData.getTrackedQuest() != null && quest.id == playerData.questData.getTrackedQuest().getId()) {
                this.trackedQuestKey = key;
            }
            if (!quest.partyOptions.allowParty) continue;
            this.partyQuests.put(key, quest.id);
            this.partyOptions.put(key, quest.partyOptions.getPartyOptionsList());
        }
        Party party = playerData.getPlayerParty();
        if (party != null && party.getQuest() != null && playerData.questData.getTrackedQuest() != null && party.getQuest().getId() == playerData.questData.getTrackedQuest().getId()) {
            this.trackedQuestKey = "P:" + party.getQuest().getCategory().getName() + ":" + party.getQuest().getName();
        }
    }

    public void setTrackedQuestKey(EntityPlayer player) {
        PlayerData playerData = PlayerData.get(player);
        Party party = playerData.getPlayerParty();
        if (party != null && party.getQuest() != null) {
            if (playerData.questData.getTrackedQuest() != null && party.getQuest().getId() == playerData.questData.getTrackedQuest().getId()) {
                this.trackedQuestKey = "P:" + party.getQuest().getCategory().getName() + ":" + party.getQuest().getName();
            }
        } else if (playerData.questData != null && playerData.questData.getTrackedQuest() != null) {
            IQuest quest = playerData.questData.getTrackedQuest();
            this.trackedQuestKey = quest.getCategory().getName() + ":" + quest.getName();
        } else {
            this.trackedQuestKey = "";
        }
    }

    public boolean hasSelectedQuest() {
        return !this.selectedQuest.isEmpty();
    }

    public String getQuestText() {
        return this.questText.get(this.selectedCategory + ":" + this.selectedQuest);
    }

    public Boolean getQuestAlerts() {
        return Boolean.valueOf(this.questAlerts.get(this.selectedCategory + ":" + this.selectedQuest));
    }

    public void toggleQuestAlerts() {
        this.questAlerts.put(this.selectedCategory + ":" + this.selectedQuest, String.valueOf(this.getQuestAlerts() == false));
    }

    public Vector<String> getQuestStatus() {
        return this.questStatus.get(this.selectedCategory + ":" + this.selectedQuest);
    }

    public String getComplete() {
        return this.finish.get(this.selectedCategory + ":" + this.selectedQuest);
    }

    public Vector<String> getPartyOptions() {
        return this.partyOptions.get(this.selectedCategory + ":" + this.selectedQuest);
    }
}

