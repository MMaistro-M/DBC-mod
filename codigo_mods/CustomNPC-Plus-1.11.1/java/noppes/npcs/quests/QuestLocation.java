/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.quests;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.handler.data.IQuestLocation;
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

public class QuestLocation
extends QuestInterface
implements IQuestLocation {
    public String location = "";
    public String location2 = "";
    public String location3 = "";

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        this.location = compound.func_74779_i("QuestLocation");
        this.location2 = compound.func_74779_i("QuestLocation2");
        this.location3 = compound.func_74779_i("QuestLocation3");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.func_74778_a("QuestLocation", this.location);
        compound.func_74778_a("QuestLocation2", this.location2);
        compound.func_74778_a("QuestLocation3", this.location3);
    }

    @Override
    public boolean isCompleted(PlayerData playerData) {
        PlayerQuestData playerdata = playerData.questData;
        QuestData data = playerdata.activeQuests.get(this.questId);
        if (data == null) {
            return false;
        }
        return this.getFound(data, 0);
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
        String found = StatCollector.func_74838_a((String)"quest.found");
        String notfound = StatCollector.func_74838_a((String)"quest.notfound");
        if (!this.location.isEmpty()) {
            vec.add(this.location + ": " + (this.getFound(data, 1) ? found : notfound));
        }
        if (!this.location2.isEmpty()) {
            vec.add(this.location2 + ": " + (this.getFound(data, 2) ? found : notfound));
        }
        if (!this.location3.isEmpty()) {
            vec.add(this.location3 + ": " + (this.getFound(data, 3) ? found : notfound));
        }
        return vec;
    }

    @Override
    public IQuestObjective[] getObjectives(EntityPlayer player) {
        ArrayList<QuestLocationObjective> list = new ArrayList<QuestLocationObjective>();
        if (!this.location.isEmpty()) {
            list.add(new QuestLocationObjective(this, player, this.location, "LocationFound"));
        }
        if (!this.location2.isEmpty()) {
            list.add(new QuestLocationObjective(this, player, this.location2, "Location2Found"));
        }
        if (!this.location3.isEmpty()) {
            list.add(new QuestLocationObjective(this, player, this.location3, "Location3Found"));
        }
        return list.toArray(new IQuestObjective[list.size()]);
    }

    public boolean getFound(QuestData data, int i) {
        if (i == 1) {
            return data.extraData.func_74767_n("LocationFound");
        }
        if (i == 2) {
            return data.extraData.func_74767_n("Location2Found");
        }
        if (i == 3) {
            return data.extraData.func_74767_n("Location3Found");
        }
        if (!this.location.isEmpty() && !data.extraData.func_74767_n("LocationFound")) {
            return false;
        }
        if (!this.location2.isEmpty() && !data.extraData.func_74767_n("Location2Found")) {
            return false;
        }
        return this.location3.isEmpty() || data.extraData.func_74767_n("Location3Found");
    }

    public boolean setFound(QuestData data, String location) {
        if (location.equalsIgnoreCase(this.location) && !data.extraData.func_74767_n("LocationFound")) {
            data.extraData.func_74757_a("LocationFound", true);
            return true;
        }
        if (location.equalsIgnoreCase(this.location2) && !data.extraData.func_74767_n("LocationFound2")) {
            data.extraData.func_74757_a("Location2Found", true);
            return true;
        }
        if (location.equalsIgnoreCase(this.location3) && !data.extraData.func_74767_n("LocationFound3")) {
            data.extraData.func_74757_a("Location3Found", true);
            return true;
        }
        return false;
    }

    public boolean setFoundParty(Party party, EntityPlayer player, String location, boolean isLeader) {
        QuestData data = party.getQuestData();
        if (data == null) {
            return false;
        }
        if (data.quest == null) {
            return false;
        }
        int memberCount = party.getPlayerNames().size();
        EnumPartyObjectives objectives = data.quest.partyOptions.objectiveRequirement;
        if (objectives == EnumPartyObjectives.All) {
            if (location.equalsIgnoreCase(this.location) && !data.extraData.func_74767_n("LocationFound")) {
                return this.setPartyPlayerFound(data, player, "LocationFound", memberCount);
            }
            if (location.equalsIgnoreCase(this.location2) && !data.extraData.func_74767_n("LocationFound2")) {
                return this.setPartyPlayerFound(data, player, "Location2Found", memberCount);
            }
            if (location.equalsIgnoreCase(this.location3) && !data.extraData.func_74767_n("LocationFound3")) {
                return this.setPartyPlayerFound(data, player, "Location3Found", memberCount);
            }
        } else if (objectives == EnumPartyObjectives.Leader && isLeader || objectives == EnumPartyObjectives.Shared) {
            if (location.equalsIgnoreCase(this.location) && !data.extraData.func_74767_n("LocationFound")) {
                data.extraData.func_74757_a("LocationFound", true);
                return true;
            }
            if (location.equalsIgnoreCase(this.location2) && !data.extraData.func_74767_n("LocationFound2")) {
                data.extraData.func_74757_a("Location2Found", true);
                return true;
            }
            if (location.equalsIgnoreCase(this.location3) && !data.extraData.func_74767_n("LocationFound3")) {
                data.extraData.func_74757_a("Location3Found", true);
                return true;
            }
        }
        return false;
    }

    public List<String> getPartyFound(Party party, String locationFoundKey) {
        List<String> foundPlayers = new ArrayList<String>();
        if (party == null) {
            return foundPlayers;
        }
        QuestData data = party.getQuestData();
        if (data == null) {
            return foundPlayers;
        }
        String locationKey = "Players" + locationFoundKey;
        if (data.extraData.func_74764_b(locationKey)) {
            foundPlayers = NBTTags.getStringList(data.extraData.func_150295_c(locationKey, 10));
        }
        return foundPlayers;
    }

    public boolean setPartyPlayerFound(QuestData data, EntityPlayer player, String locationFoundKey, int partySize) {
        boolean newFind = false;
        String locationKey = "Players" + locationFoundKey;
        List<Object> foundPlayers = data.extraData.func_74764_b(locationKey) ? NBTTags.getStringList(data.extraData.func_150295_c(locationKey, 10)) : new ArrayList();
        if (!foundPlayers.contains(player.func_70005_c_())) {
            foundPlayers.add(player.func_70005_c_());
            newFind = true;
        }
        data.extraData.func_74782_a(locationKey, (NBTBase)NBTTags.nbtStringList(foundPlayers));
        if (foundPlayers.size() == partySize) {
            data.extraData.func_74757_a(locationFoundKey, true);
        }
        return newFind;
    }

    @Override
    public IQuestObjective[] getPartyObjectives(Party party) {
        ArrayList<QuestLocationObjective> list = new ArrayList<QuestLocationObjective>();
        if (!this.location.isEmpty()) {
            list.add(new QuestLocationObjective(this, party, this.location, "LocationFound", this.getPartyFound(party, "LocationFound")));
        }
        if (!this.location2.isEmpty()) {
            list.add(new QuestLocationObjective(this, party, this.location2, "Location2Found", this.getPartyFound(party, "Location2Found")));
        }
        if (!this.location3.isEmpty()) {
            list.add(new QuestLocationObjective(this, party, this.location3, "Location3Found", this.getPartyFound(party, "Location3Found")));
        }
        return list.toArray(new IQuestObjective[list.size()]);
    }

    @Override
    public Vector<String> getPartyQuestLogStatus(Party party) {
        String playersFoundString;
        List<String> playersFound;
        boolean requireAll;
        Vector<String> vec = new Vector<String>();
        QuestData data = party.getQuestData();
        if (data == null) {
            return vec;
        }
        String found = StatCollector.func_74838_a((String)"quest.found");
        String notfound = StatCollector.func_74838_a((String)"quest.notfound");
        if (data.quest == null) {
            return vec;
        }
        boolean bl = requireAll = data.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All;
        if (!this.location.isEmpty()) {
            vec.add(this.location + ": " + (this.getFound(data, 1) ? found : notfound));
            if (requireAll) {
                playersFound = this.getPartyFound(party, "LocationFound");
                playersFoundString = "Completed: " + String.join((CharSequence)", ", playersFound);
                vec.add(playersFoundString);
            }
        }
        if (!this.location2.isEmpty()) {
            vec.add(this.location2 + ": " + (this.getFound(data, 2) ? found : notfound));
            if (requireAll) {
                playersFound = this.getPartyFound(party, "Location2Found");
                playersFoundString = "Completed: " + String.join((CharSequence)", ", playersFound);
                vec.add(playersFoundString);
            }
        }
        if (!this.location3.isEmpty()) {
            vec.add(this.location3 + ": " + (this.getFound(data, 3) ? found : notfound));
            if (requireAll) {
                playersFound = this.getPartyFound(party, "Location3Found");
                playersFoundString = "Completed: " + String.join((CharSequence)", ", playersFound);
                vec.add(playersFoundString);
            }
        }
        return vec;
    }

    public boolean isMultiQuest(Party party) {
        QuestData data = party.getQuestData();
        if (data == null) {
            return false;
        }
        if (data.quest == null) {
            return false;
        }
        return data.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All;
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
        return this.getFound(data, 0);
    }

    @Override
    public void setLocation1(String loc1) {
        this.location = loc1;
    }

    @Override
    public String getLocation1() {
        return this.location;
    }

    @Override
    public void setLocation2(String loc2) {
        this.location2 = loc2;
    }

    @Override
    public String getLocation2() {
        return this.location2;
    }

    @Override
    public void setLocation3(String loc3) {
        this.location3 = loc3;
    }

    @Override
    public String getLocation3() {
        return this.location3;
    }

    class QuestLocationObjective
    implements IQuestObjective {
        private final QuestLocation parent;
        private final EntityPlayer player;
        private final Party party;
        private final String location;
        private final String nbtName;
        private final List<String> completedPlayers;

        public QuestLocationObjective(QuestLocation this$02, EntityPlayer player, String location, String nbtName) {
            this.parent = this$02;
            this.player = player;
            this.location = location;
            this.nbtName = nbtName;
            this.party = null;
            this.completedPlayers = new ArrayList<String>();
        }

        public QuestLocationObjective(QuestLocation this$02, Party party, String location, String nbtName, List<String> completedPlayers) {
            this.parent = this$02;
            this.player = null;
            this.location = location;
            this.nbtName = nbtName;
            this.party = party;
            this.completedPlayers = new ArrayList<String>(completedPlayers);
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
                    QuestData questData = data.questData.activeQuests.get(this.parent.questId);
                    boolean completed = questData.extraData.func_74767_n(this.nbtName);
                    if (!(completed && progress == 1 || !completed && progress == 0)) {
                        questData.extraData.func_74757_a(this.nbtName, progress == 1);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[3]);
                        data.save();
                        data.updateClient = true;
                    }
                } else if (this.party != null) {
                    QuestData questData = this.party.getQuestData();
                    boolean completed = questData.extraData.func_74767_n(this.nbtName);
                    if (!(completed && progress == 1 || !completed && progress == 0)) {
                        boolean setTo = progress == 1;
                        questData.extraData.func_74757_a(this.nbtName, setTo);
                        if (questData.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
                            this.completedPlayers.clear();
                            if (setTo) {
                                this.completedPlayers.addAll(this.party.getPlayerNames());
                            }
                            String locationKey = "Players" + this.nbtName;
                            questData.extraData.func_74782_a(locationKey, (NBTBase)NBTTags.nbtStringList(this.completedPlayers));
                        }
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[3]);
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
                    QuestData questData = data.questData.activeQuests.get(this.parent.questId);
                    boolean completed = questData.extraData.func_74767_n(this.nbtName);
                    if (!(completed && progress == 1 || !completed && progress == 0)) {
                        questData.extraData.func_74757_a(this.nbtName, progress == 1);
                        data.questData.checkQuestCompletion(data, EnumQuestType.values()[3]);
                        data.save();
                        data.updateClient = true;
                    }
                } else if (foundplayer != null) {
                    QuestData questData = this.party.getQuestData();
                    boolean completed = questData.extraData.func_74767_n(this.nbtName);
                    if (!(completed && progress == 1 || !completed && progress == 0)) {
                        boolean setTo = progress == 1;
                        questData.extraData.func_74757_a(this.nbtName, setTo);
                        if (questData.quest.partyOptions.objectiveRequirement == EnumPartyObjectives.All) {
                            if (setTo) {
                                if (!this.completedPlayers.contains(foundplayer.func_70005_c_())) {
                                    this.completedPlayers.add(foundplayer.func_70005_c_());
                                }
                            } else {
                                this.completedPlayers.remove(foundplayer.func_70005_c_());
                            }
                            String locationKey = "Players" + this.nbtName;
                            questData.extraData.func_74782_a(locationKey, (NBTBase)NBTTags.nbtStringList(this.completedPlayers));
                        }
                        PartyController.Instance().checkQuestCompletion(this.party, EnumQuestType.values()[3]);
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
            QuestData questData = null;
            if (this.player != null) {
                PlayerData data = PlayerData.get(this.player);
                PlayerQuestData playerQuestData = data.questData;
                if (playerQuestData != null) {
                    questData = playerQuestData.activeQuests.get(this.parent.questId);
                }
            } else if (this.party != null) {
                questData = this.party.getQuestData();
            }
            if (questData != null) {
                return questData.extraData.func_74767_n(this.nbtName);
            }
            return false;
        }

        @Override
        public String getText() {
            return this.location + ": " + (this.isCompleted() ? "Found" : "Not Found");
        }

        @Override
        public String getAdditionalText() {
            if (this.party != null && this.parent.isMultiQuest(this.party) && this.completedPlayers.size() != this.party.getPlayerNames().size()) {
                return "Completed: " + String.join((CharSequence)", ", this.completedPlayers);
            }
            return null;
        }
    }
}

