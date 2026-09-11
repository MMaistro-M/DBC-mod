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
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;
import java.util.Vector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.handler.data.IQuestDialog;
import noppes.npcs.api.handler.data.IQuestObjective;
import noppes.npcs.constants.EnumPartyObjectives;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.quests.QuestInterface;
import noppes.npcs.scripted.CustomNPCsException;

public class QuestDialog
extends QuestInterface
implements IQuestDialog {
    public HashMap<Integer, Integer> dialogs = new HashMap();

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.dialogs = NBTTags.getIntegerIntegerMap(compound.func_150295_c("QuestDialogs", 10));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.func_74782_a("QuestDialogs", (NBTBase)NBTTags.nbtIntegerIntegerMap(this.dialogs));
    }

    @Override
    public boolean isCompleted(PlayerData playerData) {
        for (int dialogId : this.dialogs.values()) {
            if (playerData.dialogData.dialogsRead.contains(dialogId)) continue;
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
        for (int dialogId : this.dialogs.values()) {
            Dialog dialog = DialogController.Instance.dialogs.get(dialogId);
            if (dialog == null) continue;
            String title = dialog.title;
            title = PlayerData.get((EntityPlayer)player).dialogData.dialogsRead.contains(dialogId) ? title + " (read)" : title + " (unread)";
            vec.add(title);
        }
        return vec;
    }

    @Override
    public IQuestObjective[] getObjectives(EntityPlayer player) {
        ArrayList<QuestDialogObjective> list = new ArrayList<QuestDialogObjective>();
        for (int i = 0; i < 3; ++i) {
            Dialog dialog;
            if (!this.dialogs.containsKey(i) || (dialog = DialogController.Instance.dialogs.get(this.dialogs.get(i))) == null) continue;
            list.add(new QuestDialogObjective(this, player, dialog));
        }
        return list.toArray(new IQuestObjective[list.size()]);
    }

    @Override
    public IQuestObjective[] getPartyObjectives(Party party) {
        ArrayList<QuestDialogObjective> list = new ArrayList<QuestDialogObjective>();
        for (int i = 0; i < 3; ++i) {
            Dialog dialog;
            if (!this.dialogs.containsKey(i) || (dialog = DialogController.Instance.dialogs.get(this.dialogs.get(i))) == null) continue;
            list.add(new QuestDialogObjective(this, party, dialog));
        }
        return list.toArray(new IQuestObjective[list.size()]);
    }

    @Override
    public Vector<String> getPartyQuestLogStatus(Party party) {
        Dialog dialog;
        Vector<String> vec = new Vector<String>();
        if (party == null) {
            return vec;
        }
        if (party.getQuestData() == null) {
            return vec;
        }
        if (party.getQuestData().quest == null) {
            return vec;
        }
        EnumPartyObjectives objectives = party.getQuestData().quest.partyOptions.objectiveRequirement;
        if (objectives == EnumPartyObjectives.All) {
            for (int dialogId : this.dialogs.values()) {
                Dialog dialog2 = DialogController.Instance.dialogs.get(dialogId);
                if (dialog2 == null) continue;
                ArrayList<String> unread = new ArrayList<String>();
                for (UUID uuid : party.getPlayerUUIDs()) {
                    EntityPlayer player = NoppesUtilServer.getPlayer(uuid);
                    PlayerData playerData = player != null ? PlayerData.get(player) : PlayerDataController.Instance.getPlayerDataCache(uuid.toString());
                    if (playerData == null || playerData.dialogData.dialogsRead.contains(dialogId)) continue;
                    unread.add(playerData.playername);
                }
                String title = dialog2.title;
                title = !unread.isEmpty() ? title + " (unread)" : title + " (read)";
                vec.add(title);
                if (unread.isEmpty()) continue;
                StringBuilder unreaders = new StringBuilder();
                for (String name : unread) {
                    if (unreaders.length() > 0) {
                        unreaders.append(", ");
                    }
                    unreaders.append(name);
                }
                vec.add(unreaders.toString());
            }
            return vec;
        }
        if (objectives == EnumPartyObjectives.Leader) {
            EntityPlayer leader = NoppesUtilServer.getPlayer(party.getLeaderUUID());
            PlayerData playerData = leader != null ? PlayerData.get(leader) : PlayerDataController.Instance.getPlayerDataCache(party.getLeaderUUID().toString());
            if (playerData == null) {
                return vec;
            }
            for (int dialogId : this.dialogs.values()) {
                Dialog dialog3 = DialogController.Instance.dialogs.get(dialogId);
                if (dialog3 == null) continue;
                String title = dialog3.title;
                title = playerData.dialogData.dialogsRead.contains(dialogId) ? title + " (read)" : title + " (unread)";
                vec.add(title);
            }
            return vec;
        }
        HashSet<Integer> readValues = new HashSet<Integer>();
        for (int dialogId : this.dialogs.values()) {
            dialog = DialogController.Instance.dialogs.get(dialogId);
            if (dialog == null) continue;
            for (UUID uuid : party.getPlayerUUIDs()) {
                EntityPlayer player = NoppesUtilServer.getPlayer(uuid);
                PlayerData sharedData = player != null ? PlayerData.get(player) : PlayerDataController.Instance.getPlayerDataCache(uuid.toString());
                if (sharedData == null || !sharedData.dialogData.dialogsRead.contains(dialogId)) continue;
                readValues.add(dialogId);
            }
        }
        for (int dialogId : this.dialogs.values()) {
            dialog = DialogController.Instance.dialogs.get(dialogId);
            if (dialog == null) continue;
            String title = dialog.title;
            title = readValues.contains(dialogId) ? title + " (read)" : title + " (unread)";
            vec.add(title);
        }
        return vec;
    }

    @Override
    public boolean isPartyCompleted(Party party) {
        if (party == null) {
            return false;
        }
        if (party.getQuestData() == null) {
            return false;
        }
        if (party.getQuestData().quest == null) {
            return false;
        }
        EnumPartyObjectives objectives = party.getQuestData().quest.partyOptions.objectiveRequirement;
        if (objectives == EnumPartyObjectives.All) {
            ArrayList<String> incomplete = new ArrayList<String>();
            block0: for (UUID uuid : party.getPlayerUUIDs()) {
                EntityPlayer individual = NoppesUtilServer.getPlayer(uuid);
                PlayerData individualData = individual != null ? PlayerData.get(individual) : PlayerDataController.Instance.getPlayerDataCache(uuid.toString());
                if (individualData == null) continue;
                for (int dialogId : this.dialogs.values()) {
                    if (individualData.dialogData.dialogsRead.contains(dialogId)) continue;
                    incomplete.add(individualData.playername);
                    continue block0;
                }
            }
            return incomplete.isEmpty();
        }
        if (objectives == EnumPartyObjectives.Leader) {
            EntityPlayer leaderPlayer = NoppesUtilServer.getPlayer(party.getLeaderUUID());
            PlayerData leaderData = leaderPlayer != null ? PlayerData.get(leaderPlayer) : PlayerDataController.Instance.getPlayerDataCache(party.getLeaderUUID().toString());
            if (leaderData != null) {
                for (int dialogId : this.dialogs.values()) {
                    if (leaderData.dialogData.dialogsRead.contains(dialogId)) continue;
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }
        HashMap<Integer, Boolean> readValues = new HashMap<Integer, Boolean>();
        for (UUID uuid : party.getPlayerUUIDs()) {
            EntityPlayer player = NoppesUtilServer.getPlayer(uuid);
            PlayerData playerData = player != null ? PlayerData.get(player) : PlayerDataController.Instance.getPlayerDataCache(uuid.toString());
            if (playerData == null) continue;
            for (int dialogId : this.dialogs.values()) {
                if (!playerData.dialogData.dialogsRead.contains(dialogId)) continue;
                readValues.put(dialogId, true);
            }
        }
        Iterator<UUID> iterator = readValues.values().iterator();
        while (iterator.hasNext()) {
            boolean result = (Boolean)((Object)iterator.next());
            if (result) continue;
            return false;
        }
        return true;
    }

    class QuestDialogObjective
    implements IQuestObjective {
        private final QuestDialog parent;
        private final EntityPlayer player;
        private final Party party;
        private final Dialog dialog;

        public QuestDialogObjective(QuestDialog this$02, EntityPlayer player, Dialog dialog) {
            this.parent = this$02;
            this.player = player;
            this.dialog = dialog;
            this.party = null;
        }

        public QuestDialogObjective(QuestDialog this$02, Party party, Dialog dialog) {
            this.parent = this$02;
            this.party = party;
            this.dialog = dialog;
            this.player = null;
        }

        @Override
        public int getProgress() {
            return this.isCompleted() ? 1 : 0;
        }

        @Override
        public void setProgress(int progress) {
            if (progress >= 0 && progress <= 1) {
                if (this.player != null) {
                    PlayerData data = PlayerData.get(this.player);
                    boolean completed = data.dialogData.dialogsRead.contains(this.dialog.id);
                    if (progress == 0 && completed) {
                        data.dialogData.dialogsRead.remove(this.dialog.id);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[1]);
                        data.updateClient = true;
                        data.save();
                    }
                    if (progress == 1 && !completed) {
                        data.dialogData.dialogsRead.add(this.dialog.id);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[1]);
                        data.updateClient = true;
                        data.save();
                    }
                } else if (this.party != null && this.party.getObjectiveRequirement() != null) {
                    EnumPartyObjectives objectives = this.party.getObjectiveRequirement();
                    if (objectives == EnumPartyObjectives.Leader) {
                        EntityPlayer leaderPlayer = NoppesUtilServer.getPlayer(this.party.getLeaderUUID());
                        PlayerData leaderData = leaderPlayer != null ? PlayerData.get(leaderPlayer) : PlayerDataController.Instance.getPlayerDataCache(this.party.getLeaderUUID().toString());
                        if (leaderData == null) {
                            return;
                        }
                        boolean completed = leaderData.dialogData.dialogsRead.contains(this.dialog.id);
                        if (progress == 0 && completed) {
                            leaderData.dialogData.dialogsRead.remove(this.dialog.id);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[1]);
                            leaderData.updateClient = true;
                            leaderData.save();
                        }
                        if (progress == 1 && !completed) {
                            leaderData.dialogData.dialogsRead.add(this.dialog.id);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[1]);
                            leaderData.updateClient = true;
                            leaderData.save();
                        }
                    } else {
                        for (UUID uuid : this.party.getPlayerUUIDs()) {
                            EntityPlayer individual = NoppesUtilServer.getPlayer(uuid);
                            PlayerData individualData = individual != null ? PlayerData.get(individual) : PlayerDataController.Instance.getPlayerDataCache(uuid.toString());
                            if (individualData == null) continue;
                            boolean completed = individualData.dialogData.dialogsRead.contains(this.dialog.id);
                            if (progress == 0 && completed) {
                                individualData.dialogData.dialogsRead.remove(this.dialog.id);
                                individualData.save();
                                individualData.updateClient = true;
                            }
                            if (progress != 1 || completed) continue;
                            individualData.dialogData.dialogsRead.add(this.dialog.id);
                            individualData.save();
                            individualData.updateClient = true;
                        }
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[1]);
                    }
                }
            } else {
                throw new CustomNPCsException("Progress has to be 0 or 1", new Object[0]);
            }
        }

        @Override
        public void setPlayerProgress(String playerName, int progress) {
            if (progress >= 0 && progress <= 1) {
                EntityPlayer foundplayer = NoppesUtilServer.getPlayerByName(playerName);
                if (foundplayer != null && this.party == null) {
                    PlayerData data = PlayerData.get(foundplayer);
                    boolean completed = data.dialogData.dialogsRead.contains(this.dialog.id);
                    if (progress == 0 && completed) {
                        data.dialogData.dialogsRead.remove(this.dialog.id);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[1]);
                        data.updateClient = true;
                        data.save();
                    }
                    if (progress == 1 && !completed) {
                        data.dialogData.dialogsRead.add(this.dialog.id);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[1]);
                        data.updateClient = true;
                        data.save();
                    }
                } else if (foundplayer != null && this.party.getObjectiveRequirement() != null) {
                    EnumPartyObjectives objectives = this.party.getObjectiveRequirement();
                    if (objectives == EnumPartyObjectives.Leader) {
                        EntityPlayer leaderPlayer = NoppesUtilServer.getPlayer(this.party.getLeaderUUID());
                        PlayerData leaderData = leaderPlayer != null ? PlayerData.get(leaderPlayer) : PlayerDataController.Instance.getPlayerDataCache(this.party.getLeaderUUID().toString());
                        if (leaderData == null) {
                            return;
                        }
                        boolean completed = leaderData.dialogData.dialogsRead.contains(this.dialog.id);
                        if (progress == 0 && completed) {
                            leaderData.dialogData.dialogsRead.remove(this.dialog.id);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[1]);
                            leaderData.updateClient = true;
                            leaderData.save();
                        }
                        if (progress == 1 && !completed) {
                            leaderData.dialogData.dialogsRead.add(this.dialog.id);
                            PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[1]);
                            leaderData.updateClient = true;
                            leaderData.save();
                        }
                    } else {
                        PlayerData individualData = PlayerData.get(foundplayer);
                        if (individualData != null) {
                            boolean completed = individualData.dialogData.dialogsRead.contains(this.dialog.id);
                            if (progress == 0 && completed) {
                                individualData.dialogData.dialogsRead.remove(this.dialog.id);
                                individualData.save();
                                individualData.updateClient = true;
                            }
                            if (progress == 1 && !completed) {
                                individualData.dialogData.dialogsRead.add(this.dialog.id);
                                individualData.save();
                                individualData.updateClient = true;
                            }
                        }
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[1]);
                    }
                }
            } else {
                throw new CustomNPCsException("Progress has to be 0 or 1", new Object[0]);
            }
        }

        @Override
        public int getMaxProgress() {
            return 1;
        }

        @Override
        public boolean isCompleted() {
            if (this.player != null) {
                PlayerData data = PlayerData.get(this.player);
                return data.dialogData.dialogsRead.contains(this.dialog.id);
            }
            if (this.party != null && this.party.getObjectiveRequirement() != null) {
                EnumPartyObjectives objectives = this.party.getObjectiveRequirement();
                if (objectives == EnumPartyObjectives.Leader) {
                    EntityPlayer leaderPlayer = NoppesUtilServer.getPlayer(this.party.getLeaderUUID());
                    PlayerData leaderData = leaderPlayer != null ? PlayerData.get(leaderPlayer) : PlayerDataController.Instance.getPlayerDataCache(this.party.getLeaderUUID().toString());
                    if (leaderData == null) {
                        return false;
                    }
                    return leaderData.dialogData.dialogsRead.contains(this.dialog.id);
                }
                boolean requiresOneRead = objectives == EnumPartyObjectives.Shared;
                for (UUID uuid : this.party.getPlayerUUIDs()) {
                    EntityPlayer individual = NoppesUtilServer.getPlayer(uuid);
                    PlayerData individualData = individual != null ? PlayerData.get(individual) : PlayerDataController.Instance.getPlayerDataCache(uuid.toString());
                    if (individualData == null) continue;
                    boolean read = individualData.dialogData.dialogsRead.contains(this.dialog.id);
                    if (requiresOneRead && read) {
                        return true;
                    }
                    if (requiresOneRead || read) continue;
                    return false;
                }
                return !requiresOneRead;
            }
            return true;
        }

        @Override
        public String getText() {
            return this.dialog.title + (this.isCompleted() ? " (read)" : " (unread)");
        }

        @Override
        public String getAdditionalText() {
            if (this.party != null && this.party.getObjectiveRequirement() == EnumPartyObjectives.All) {
                ArrayList<String> incomplete = new ArrayList<String>();
                for (UUID uuid : this.party.getPlayerUUIDs()) {
                    boolean read;
                    EntityPlayer individual = NoppesUtilServer.getPlayer(uuid);
                    PlayerData individualData = individual != null ? PlayerData.get(individual) : PlayerDataController.Instance.getPlayerDataCache(uuid.toString());
                    if (individualData == null || (read = individualData.dialogData.dialogsRead.contains(this.dialog.id))) continue;
                    incomplete.add(individualData.playername);
                }
                if (!incomplete.isEmpty()) {
                    return "[" + String.join((CharSequence)", ", incomplete) + "]";
                }
            }
            return null;
        }
    }
}

