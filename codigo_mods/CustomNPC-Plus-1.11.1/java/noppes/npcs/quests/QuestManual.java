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

public class QuestManual
extends QuestInterface {
    public TreeMap<String, Integer> manuals = new TreeMap();

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.manuals = new TreeMap<String, Integer>(NBTTags.getStringIntegerMap(compound.func_150295_c("QuestManual", 10)));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.func_74782_a("QuestManual", (NBTBase)NBTTags.nbtStringIntegerMap(this.manuals));
    }

    @Override
    public boolean isCompleted(PlayerData playerData) {
        if (playerData == null) {
            return false;
        }
        QuestData data = playerData.questData.activeQuests.get(this.questId);
        if (data == null) {
            return false;
        }
        HashMap<String, Integer> manual = this.getManual(data);
        if (this.manuals.size() != manual.size()) {
            return false;
        }
        for (String entity : this.manuals.keySet()) {
            if (manual.containsKey(entity) && manual.get(entity) >= this.manuals.get(entity)) continue;
            return false;
        }
        return true;
    }

    @Override
    public void handleComplete(EntityPlayer player) {
    }

    @Override
    public Vector<String> getQuestLogStatus(EntityPlayer player) {
        Vector<String> vec = new Vector<String>();
        PlayerData playerdata = PlayerData.get(player);
        if (playerdata == null) {
            return vec;
        }
        QuestData data = playerdata.questData.activeQuests.get(this.questId);
        if (data == null) {
            return vec;
        }
        HashMap<String, Integer> manual = this.getManual(data);
        for (String entity : this.manuals.keySet()) {
            vec.add(entity + ": " + manual.getOrDefault(entity, 0) + "/" + this.manuals.get(entity));
        }
        return vec;
    }

    public HashMap<String, Integer> getManual(QuestData data) {
        return NBTTags.getStringIntegerMap(data.extraData.func_150295_c("Manual", 10));
    }

    public void setManual(QuestData data, HashMap<String, Integer> manual) {
        data.extraData.func_74782_a("Manual", (NBTBase)NBTTags.nbtStringIntegerMap(manual));
    }

    public HashMap<String, Integer> getPlayerManual(QuestData data, String playerName) {
        return NBTTags.getStringIntegerMap(data.extraData.func_150295_c(playerName + "Manual", 10));
    }

    public void setPlayerManual(QuestData data, HashMap<String, Integer> playerManual, String playerName) {
        data.extraData.func_74782_a(playerName + "Manual", (NBTBase)NBTTags.nbtStringIntegerMap(playerManual));
    }

    @Override
    public IQuestObjective[] getObjectives(EntityPlayer player) {
        ArrayList<QuestManualObjective> list = new ArrayList<QuestManualObjective>();
        for (Map.Entry<String, Integer> entry : this.manuals.entrySet()) {
            list.add(new QuestManualObjective(player, entry.getKey(), (int)entry.getValue()));
        }
        return list.toArray(new IQuestObjective[list.size()]);
    }

    @Override
    public IQuestObjective[] getPartyObjectives(Party party) {
        ArrayList<QuestManualObjective> list = new ArrayList<QuestManualObjective>();
        for (Map.Entry<String, Integer> entry : this.manuals.entrySet()) {
            list.add(new QuestManualObjective(party, entry.getKey(), (int)entry.getValue()));
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
            for (String entityName : this.manuals.keySet()) {
                String firstLine = entityName + ": " + this.manuals.get(entityName);
                ArrayList<String> playerResults = new ArrayList<String>();
                for (String player : party.getPlayerNames()) {
                    HashMap<String, Integer> playerManual = this.getPlayerManual(data, player);
                    int amount = 0;
                    if (playerManual.containsKey(entityName)) {
                        amount = playerManual.get(entityName);
                    }
                    if (amount >= this.manuals.get(entityName)) continue;
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
            HashMap<String, Integer> manual = this.getManual(data);
            for (String entity : this.manuals.keySet()) {
                int amount = 0;
                if (manual.containsKey(entity)) {
                    amount = manual.get(entity);
                }
                String state = amount + "/" + this.manuals.get(entity);
                vec.add(entity + ": " + state);
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
            for (String entityName : this.manuals.keySet()) {
                for (String player : party.getPlayerNames()) {
                    HashMap<String, Integer> playerManual = this.getPlayerManual(data, player);
                    int amount = playerManual.getOrDefault(entityName, 0);
                    if (amount >= this.manuals.get(entityName)) continue;
                    return false;
                }
            }
        } else {
            int completed = 0;
            HashMap<String, Integer> playerManual = this.getManual(data);
            for (String entityName : this.manuals.keySet()) {
                int amount = 0;
                if (playerManual.containsKey(entityName)) {
                    amount = playerManual.get(entityName);
                }
                if (amount < this.manuals.get(entityName)) continue;
                ++completed;
            }
            if (completed >= this.manuals.keySet().size()) {
                return true;
            }
            if (playerManual.size() != this.manuals.size()) {
                return false;
            }
            for (String entity : playerManual.keySet()) {
                if (this.manuals.containsKey(entity) && this.manuals.get(entity) <= playerManual.get(entity)) continue;
                return false;
            }
        }
        return true;
    }

    class QuestManualObjective
    implements IQuestObjective {
        private final EntityPlayer player;
        private final String entity;
        private final Party party;
        private final int amount;

        public QuestManualObjective(EntityPlayer player, String entity, int amount) {
            this.player = player;
            this.entity = entity;
            this.amount = amount;
            this.party = null;
        }

        public QuestManualObjective(Party party, String entity, int amount) {
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
                QuestData questdata2 = playerdata.activeQuests.get(QuestManual.this.questId);
                if (questdata2 != null) {
                    HashMap<String, Integer> playerManual = QuestManual.this.getManual(questdata2);
                    return !playerManual.containsKey(this.entity) ? 0 : playerManual.get(this.entity);
                }
            } else if (this.party != null && (questdata = this.party.getQuestData()) != null) {
                if (questdata.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
                    int howManyDone = 0;
                    for (String player : this.party.getPlayerNames()) {
                        HashMap<String, Integer> playerManual = QuestManual.this.getPlayerManual(questdata, player);
                        int currentProgress = !playerManual.containsKey(this.entity) ? 0 : playerManual.get(this.entity);
                        if (currentProgress < this.amount) continue;
                        ++howManyDone;
                    }
                    if (howManyDone == this.party.getPlayerNames().size()) {
                        return this.getMaxProgress();
                    }
                    return 0;
                }
                HashMap<String, Integer> playerManual = QuestManual.this.getManual(questdata);
                return !playerManual.containsKey(this.entity) ? 0 : playerManual.get(this.entity);
            }
            return 0;
        }

        @Override
        public void setProgress(int progress) {
            if (progress >= 0 && progress <= this.amount) {
                QuestData questdata;
                if (this.player != null) {
                    HashMap<String, Integer> playerManual;
                    PlayerData data = PlayerData.get(this.player);
                    PlayerQuestData playerdata = data.questData;
                    QuestData questdata2 = playerdata.activeQuests.get(QuestManual.this.questId);
                    if (!(questdata2 == null || (playerManual = QuestManual.this.getManual(questdata2)).containsKey(this.entity) && playerManual.get(this.entity) == progress)) {
                        playerManual.put(this.entity, progress);
                        QuestManual.this.setManual(questdata2, playerManual);
                        data.questData.checkQuestCompletion(data, EnumQuestType.Manual);
                        data.updateClient = true;
                        data.save();
                    }
                } else if (this.party != null && (questdata = this.party.getQuestData()) != null) {
                    if (questdata.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
                        for (String player : this.party.getPlayerNames()) {
                            HashMap<String, Integer> playerManual = QuestManual.this.getPlayerManual(questdata, player);
                            if (playerManual.containsKey(this.entity) && playerManual.get(this.entity) == progress) continue;
                            playerManual.put(this.entity, progress);
                            QuestManual.this.setPlayerManual(questdata, playerManual, player);
                        }
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.Manual);
                    } else {
                        HashMap<String, Integer> playerManual = QuestManual.this.getManual(questdata);
                        if (!playerManual.containsKey(this.entity) || playerManual.get(this.entity) != progress) {
                            playerManual.put(this.entity, progress);
                            QuestManual.this.setManual(questdata, playerManual);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.Manual);
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
                    HashMap<String, Integer> playerManual;
                    PlayerData data = PlayerData.get(foundplayer);
                    PlayerQuestData playerdata = data.questData;
                    QuestData questdata2 = playerdata.activeQuests.get(QuestManual.this.questId);
                    if (!(questdata2 == null || (playerManual = QuestManual.this.getManual(questdata2)).containsKey(this.entity) && playerManual.get(this.entity) == progress)) {
                        playerManual.put(this.entity, progress);
                        QuestManual.this.setManual(questdata2, playerManual);
                        data.questData.checkQuestCompletion(data, EnumQuestType.Manual);
                        data.updateClient = true;
                        data.save();
                    }
                } else if (foundplayer != null && (questdata = this.party.getQuestData()) != null) {
                    if (questdata.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
                        HashMap<String, Integer> playerManual = QuestManual.this.getPlayerManual(questdata, foundplayer.func_70005_c_());
                        if (!playerManual.containsKey(this.entity) || playerManual.get(this.entity) != progress) {
                            playerManual.put(this.entity, progress);
                            QuestManual.this.setPlayerManual(questdata, playerManual, foundplayer.func_70005_c_());
                        }
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.Manual);
                    } else {
                        HashMap<String, Integer> playerManual = QuestManual.this.getManual(questdata);
                        if (!playerManual.containsKey(this.entity) || playerManual.get(this.entity) != progress) {
                            playerManual.put(this.entity, progress);
                            QuestManual.this.setManual(questdata, playerManual);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.Manual);
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
                        HashMap<String, Integer> playerManual = QuestManual.this.getPlayerManual(questdata, player);
                        int currentProgress = !playerManual.containsKey(this.entity) ? 0 : playerManual.get(this.entity);
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

