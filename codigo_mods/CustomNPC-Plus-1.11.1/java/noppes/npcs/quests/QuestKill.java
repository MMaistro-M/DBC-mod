/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.quests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.handler.data.IQuestKill;
import noppes.npcs.api.handler.data.IQuestObjective;
import noppes.npcs.constants.EnumPartyObjectives;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.quests.QuestInterface;
import noppes.npcs.scripted.CustomNPCsException;

public class QuestKill
extends QuestInterface
implements IQuestKill {
    public TreeMap<String, Integer> targets = new TreeMap();
    public int targetType = 0;
    public String customTargetType = "noppes.npcs.entity.EntityCustomNpc";

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.func_74782_a("QuestKills", (NBTBase)NBTTags.nbtStringIntegerMap(this.targets));
        compound.func_74768_a("TargetType", this.targetType);
        compound.func_74778_a("CustomTargetType", this.customTargetType);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        if (!compound.func_74764_b("QuestKills")) {
            this.targets.clear();
            TreeMap<String, Integer> oldTargets = new TreeMap<String, Integer>(NBTTags.getStringIntegerMap(compound.func_150295_c("QuestDialogs", 10)));
            this.targets.putAll(oldTargets);
        } else {
            this.targets = new TreeMap<String, Integer>(NBTTags.getStringIntegerMap(compound.func_150295_c("QuestKills", 10)));
        }
        this.targetType = compound.func_74762_e("TargetType");
        this.customTargetType = compound.func_74779_i("CustomTargetType");
    }

    @Override
    public boolean isCompleted(PlayerData playerData) {
        PlayerQuestData playerdata = playerData.questData;
        QuestData data = playerdata.activeQuests.get(this.questId);
        if (data == null) {
            return false;
        }
        HashMap<String, Integer> killed = this.getKilled(data);
        int completed = 0;
        for (String entityName : this.targets.keySet()) {
            int amount = 0;
            if (killed.containsKey(entityName)) {
                amount = killed.get(entityName);
            }
            if (amount < this.targets.get(entityName)) continue;
            ++completed;
        }
        if (completed >= this.targets.keySet().size()) {
            return true;
        }
        if (killed.size() != this.targets.size()) {
            return false;
        }
        for (String entity : killed.keySet()) {
            if (this.targets.containsKey(entity) && this.targets.get(entity) <= killed.get(entity)) continue;
            return false;
        }
        return true;
    }

    @Override
    public void handleComplete(EntityPlayer player) {
        super.handleComplete(player);
    }

    @Override
    public Vector<String> getQuestLogStatus(EntityPlayer player) {
        Vector<String> vec = new Vector<String>();
        PlayerQuestData playerdata = PlayerData.get((EntityPlayer)player).questData;
        QuestData data = playerdata.activeQuests.get(this.questId);
        if (data == null) {
            return vec;
        }
        HashMap<String, Integer> killed = this.getKilled(data);
        for (String entityName : this.targets.keySet()) {
            int amount = 0;
            if (killed.containsKey(entityName)) {
                amount = killed.get(entityName);
            }
            String state = amount + "/" + this.targets.get(entityName);
            vec.add(entityName + ": " + state);
        }
        return vec;
    }

    public HashMap<String, Integer> getKilled(QuestData data) {
        return NBTTags.getStringIntegerMap(data.extraData.func_150295_c("Killed", 10));
    }

    public void setKilled(QuestData data, HashMap<String, Integer> killed) {
        data.extraData.func_74782_a("Killed", (NBTBase)NBTTags.nbtStringIntegerMap(killed));
    }

    @Override
    public IQuestObjective[] getObjectives(EntityPlayer player) {
        ArrayList<QuestKillObjective> list = new ArrayList<QuestKillObjective>();
        for (Map.Entry<String, Integer> entry : this.targets.entrySet()) {
            list.add(new QuestKillObjective(this, player, entry.getKey(), (int)entry.getValue()));
        }
        return list.toArray(new IQuestObjective[list.size()]);
    }

    public HashMap<String, Integer> getPlayerKilled(QuestData data, String playerName) {
        return NBTTags.getStringIntegerMap(data.extraData.func_150295_c(playerName + "Killed", 10));
    }

    public void setPlayerKilled(QuestData data, HashMap<String, Integer> killed, String playerName) {
        data.extraData.func_74782_a(playerName + "Killed", (NBTBase)NBTTags.nbtStringIntegerMap(killed));
    }

    @Override
    public IQuestObjective[] getPartyObjectives(Party party) {
        ArrayList<QuestKillObjective> list = new ArrayList<QuestKillObjective>();
        for (Map.Entry<String, Integer> entry : this.targets.entrySet()) {
            list.add(new QuestKillObjective(this, party, entry.getKey(), (int)entry.getValue()));
        }
        return list.toArray(new IQuestObjective[list.size()]);
    }

    @Override
    public Vector<String> getPartyQuestLogStatus(Party party) {
        Vector<String> vec = new Vector<String>();
        QuestData data = party.getQuestData();
        if (data == null) {
            return vec;
        }
        if (data.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
            for (String entityName : this.targets.keySet()) {
                String firstLine = entityName + ": " + this.targets.get(entityName);
                ArrayList<String> playerResults = new ArrayList<String>();
                for (String player : party.getPlayerNames()) {
                    HashMap<String, Integer> killed = this.getPlayerKilled(data, player);
                    int amount = 0;
                    if (killed.containsKey(entityName)) {
                        amount = killed.get(entityName);
                    }
                    if (amount >= this.targets.get(entityName)) continue;
                    String state = player + ": " + amount;
                    playerResults.add(state);
                }
                if (!playerResults.isEmpty()) {
                    vec.add(firstLine);
                    vec.add("[" + String.join((CharSequence)", ", playerResults) + "]");
                    continue;
                }
                vec.add(firstLine + " (Done)");
            }
        } else {
            HashMap<String, Integer> killed = this.getKilled(data);
            for (String entityName : this.targets.keySet()) {
                int amount = 0;
                if (killed.containsKey(entityName)) {
                    amount = killed.get(entityName);
                }
                String state = amount + "/" + this.targets.get(entityName);
                vec.add(entityName + ": " + state);
            }
        }
        return vec;
    }

    @Override
    public boolean isPartyCompleted(Party party) {
        if (party == null) {
            return false;
        }
        QuestData data = party.getQuestData();
        if (data == null) {
            return false;
        }
        if (data.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
            for (String entityName : this.targets.keySet()) {
                for (String player : party.getPlayerNames()) {
                    HashMap<String, Integer> killed = this.getPlayerKilled(data, player);
                    int amount = killed.getOrDefault(entityName, 0);
                    if (amount >= this.targets.get(entityName)) continue;
                    return false;
                }
            }
        } else {
            int completed = 0;
            HashMap<String, Integer> killed = this.getKilled(data);
            for (String entityName : this.targets.keySet()) {
                int amount = 0;
                if (killed.containsKey(entityName)) {
                    amount = killed.get(entityName);
                }
                if (amount < this.targets.get(entityName)) continue;
                ++completed;
            }
            if (completed >= this.targets.keySet().size()) {
                return true;
            }
            if (killed.size() != this.targets.size()) {
                return false;
            }
            for (String entity : killed.keySet()) {
                if (this.targets.containsKey(entity) && this.targets.get(entity) <= killed.get(entity)) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public void setTargetType(int type) {
        if (type < 0) {
            type = 0;
        }
        if (type > 2) {
            type = 2;
        }
        this.targetType = type;
    }

    @Override
    public int getTargetType() {
        return this.targetType;
    }

    class QuestKillObjective
    implements IQuestObjective {
        private final QuestKill parent;
        private final EntityPlayer player;
        private final Party party;
        private final String entity;
        private final int amount;

        public QuestKillObjective(QuestKill parent, EntityPlayer player, String entity, int amount) {
            this.parent = parent;
            this.player = player;
            this.entity = entity;
            this.amount = amount;
            this.party = null;
        }

        public QuestKillObjective(QuestKill parent, Party party, String entity, int amount) {
            this.parent = parent;
            this.party = party;
            this.entity = entity;
            this.amount = amount;
            this.player = null;
        }

        @Override
        public int getProgress() {
            QuestData questdata;
            if (this.player != null) {
                PlayerData data = PlayerData.get(this.player);
                PlayerQuestData playerdata = data.questData;
                QuestData questdata2 = playerdata.activeQuests.get(this.parent.questId);
                if (questdata2 != null) {
                    HashMap<String, Integer> killed = this.parent.getKilled(questdata2);
                    return !killed.containsKey(this.entity) ? 0 : killed.get(this.entity);
                }
            } else if (this.party != null && (questdata = this.party.getQuestData()) != null) {
                if (questdata.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
                    int howManyDone = 0;
                    for (String player : this.party.getPlayerNames()) {
                        HashMap<String, Integer> killed = this.parent.getPlayerKilled(questdata, player);
                        int currentProgress = !killed.containsKey(this.entity) ? 0 : killed.get(this.entity);
                        if (currentProgress < this.amount) continue;
                        ++howManyDone;
                    }
                    if (howManyDone == this.party.getPlayerNames().size()) {
                        return this.getMaxProgress();
                    }
                    return 0;
                }
                HashMap<String, Integer> killed = this.parent.getKilled(questdata);
                return !killed.containsKey(this.entity) ? 0 : killed.get(this.entity);
            }
            return 0;
        }

        @Override
        public void setProgress(int progress) {
            if (progress >= 0 && progress <= this.amount) {
                QuestData questdata;
                if (this.player != null) {
                    HashMap<String, Integer> killed;
                    PlayerData data = PlayerData.get(this.player);
                    PlayerQuestData playerdata = data.questData;
                    QuestData questdata2 = playerdata.activeQuests.get(this.parent.questId);
                    if (!(questdata2 == null || (killed = this.parent.getKilled(questdata2)).containsKey(this.entity) && killed.get(this.entity) == progress)) {
                        killed.put(this.entity, progress);
                        this.parent.setKilled(questdata2, killed);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[2]);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[4]);
                        data.updateClient = true;
                        data.save();
                    }
                } else if (this.party != null && (questdata = this.party.getQuestData()) != null) {
                    if (questdata.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
                        for (String player : this.party.getPlayerNames()) {
                            HashMap<String, Integer> killed = this.parent.getPlayerKilled(questdata, player);
                            if (killed.containsKey(this.entity) && killed.get(this.entity) == progress) continue;
                            killed.put(this.entity, progress);
                            this.parent.setPlayerKilled(questdata, killed, player);
                        }
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[2]);
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[4]);
                    } else {
                        HashMap<String, Integer> killed = this.parent.getKilled(questdata);
                        if (!killed.containsKey(this.entity) || killed.get(this.entity) != progress) {
                            killed.put(this.entity, progress);
                            this.parent.setKilled(questdata, killed);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[2]);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[4]);
                        }
                    }
                }
            } else {
                throw new CustomNPCsException("Progress has to be between 0 and " + this.amount, new Object[0]);
            }
        }

        @Override
        public void setPlayerProgress(String playerName, int progress) {
            if (progress >= 0 && progress <= this.amount) {
                QuestData questdata;
                EntityPlayer foundplayer = NoppesUtilServer.getPlayerByName(playerName);
                if (foundplayer != null && this.party == null) {
                    HashMap<String, Integer> killed;
                    PlayerData data = PlayerData.get(foundplayer);
                    PlayerQuestData playerdata = data.questData;
                    QuestData questdata2 = playerdata.activeQuests.get(this.parent.questId);
                    if (!(questdata2 == null || (killed = this.parent.getKilled(questdata2)).containsKey(this.entity) && killed.get(this.entity) == progress)) {
                        killed.put(this.entity, progress);
                        this.parent.setKilled(questdata2, killed);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[2]);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[4]);
                        data.updateClient = true;
                        data.save();
                    }
                } else if (foundplayer != null && (questdata = this.party.getQuestData()) != null) {
                    if (questdata.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
                        HashMap<String, Integer> killed = this.parent.getPlayerKilled(questdata, playerName);
                        if (!killed.containsKey(this.entity) || killed.get(this.entity) != progress) {
                            killed.put(this.entity, progress);
                            this.parent.setPlayerKilled(questdata, killed, playerName);
                        }
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[2]);
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[4]);
                    } else {
                        HashMap<String, Integer> killed = this.parent.getKilled(questdata);
                        if (!killed.containsKey(this.entity) || killed.get(this.entity) != progress) {
                            killed.put(this.entity, progress);
                            this.parent.setKilled(questdata, killed);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[2]);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[4]);
                        }
                    }
                }
            } else {
                throw new CustomNPCsException("Progress has to be between 0 and " + this.amount, new Object[0]);
            }
        }

        @Override
        public int getMaxProgress() {
            return this.amount;
        }

        @Override
        public boolean isCompleted() {
            return this.getProgress() >= this.amount;
        }

        @Override
        public String getText() {
            if (this.party != null && this.party.getObjectiveRequirement() == EnumPartyObjectives.All) {
                return this.entity + ": " + this.getMaxProgress() + (this.isCompleted() ? " (Done)" : "");
            }
            return this.entity + ": " + this.getProgress() + "/" + this.getMaxProgress();
        }

        @Override
        public String getAdditionalText() {
            if (this.party != null) {
                ArrayList<String> incompletePlayers = new ArrayList<String>();
                QuestData questdata = this.party.getQuestData();
                if (questdata != null && questdata.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
                    for (String player : this.party.getPlayerNames()) {
                        HashMap<String, Integer> killed = this.parent.getPlayerKilled(questdata, player);
                        int currentProgress = !killed.containsKey(this.entity) ? 0 : killed.get(this.entity);
                        if (currentProgress >= this.amount) continue;
                        String state = player + ": " + currentProgress;
                        incompletePlayers.add(state);
                    }
                    if (!incompletePlayers.isEmpty()) {
                        return "[" + String.join((CharSequence)", ", incompletePlayers) + "]";
                    }
                }
            }
            return null;
        }
    }
}

