/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package noppes.npcs.controllers.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.IPlayerQuestData;
import noppes.npcs.api.handler.data.IParty;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.constants.EnumPartyObjectives;
import noppes.npcs.constants.EnumPartyRequirements;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestData;

public class Party
implements IParty {
    private final UUID partyUUID;
    private UUID partyLeader;
    private QuestData questData;
    private final HashMap<UUID, String> partyMembers = new HashMap();
    private final ArrayList<UUID> partyOrder = new ArrayList();
    private int currentQuestID = -1;
    private String currentQuestName = "";
    private boolean friendlyFire;
    private boolean partyLocked = false;
    private String partyLeaderName;
    private Quest quest;

    public Party() {
        this.partyUUID = UUID.randomUUID();
    }

    public Party(UUID uuid) {
        this.partyUUID = uuid;
    }

    public UUID getPartyUUID() {
        return this.partyUUID;
    }

    @Override
    public String getPartyUUIDString() {
        return this.partyUUID.toString();
    }

    @Override
    public boolean getIsLocked() {
        return this.partyLocked;
    }

    @Override
    public void setQuest(IQuest quest) {
        if (quest != null) {
            this.currentQuestID = quest.getId();
            this.currentQuestName = quest.getName();
            this.questData = new QuestData((Quest)quest);
        } else {
            this.currentQuestID = -1;
            this.currentQuestName = "";
            this.questData = null;
        }
        this.partyLocked = quest != null;
    }

    @Override
    public IQuest getQuest() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            return QuestController.Instance.get(this.currentQuestID);
        }
        return this.quest;
    }

    public QuestData getQuestData() {
        return this.questData;
    }

    public EnumPartyObjectives getObjectiveRequirement() {
        if (this.getQuestData() != null && this.getQuestData().quest != null) {
            return this.getQuestData().quest.partyOptions.objectiveRequirement;
        }
        return null;
    }

    @Override
    public int getCurrentQuestID() {
        return this.currentQuestID;
    }

    @Override
    public String getCurrentQuestName() {
        return this.currentQuestName;
    }

    public boolean addPlayer(EntityPlayer player) {
        if (player == null) {
            return false;
        }
        if (this.partyMembers.containsKey(player.func_110124_au())) {
            return false;
        }
        PlayerData playerData = PlayerData.get(player);
        playerData.partyUUID = this.partyUUID;
        this.partyMembers.put(player.func_110124_au(), player.func_70005_c_());
        this.partyOrder.add(player.func_110124_au());
        return true;
    }

    public boolean removePlayer(EntityPlayer player) {
        if (player == null) {
            return false;
        }
        UUID uuid = player.func_110124_au();
        if (this.partyMembers.containsKey(uuid)) {
            this.partyMembers.remove(uuid);
            this.partyOrder.remove(uuid);
            if (uuid.equals(this.partyLeader) && this.partyMembers.size() > 0) {
                this.partyLeader = this.partyOrder.get(0);
            }
            PlayerData playerData = PlayerData.get(player);
            playerData.partyUUID = null;
            return true;
        }
        return false;
    }

    public boolean removePlayer(UUID uuid) {
        if (uuid == null) {
            return false;
        }
        if (this.partyMembers.containsKey(uuid)) {
            this.partyMembers.remove(uuid);
            this.partyOrder.remove(uuid);
            if (uuid.equals(this.partyLeader) && !this.partyMembers.isEmpty()) {
                this.partyLeader = this.partyOrder.get(0);
            }
            PlayerData playerData = PlayerDataController.Instance.getPlayerDataCache(uuid.toString());
            playerData.partyUUID = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean addPlayer(String playerName) {
        return playerName != null && this.addPlayer(NoppesUtilServer.getPlayerByName(playerName));
    }

    @Override
    public boolean removePlayer(String playerName) {
        return playerName != null && this.removePlayer(NoppesUtilServer.getPlayerByName(playerName));
    }

    @Override
    public boolean addPlayer(IPlayer player) {
        return player != null && this.addPlayer((EntityPlayer)((EntityPlayerMP)player.getMCEntity()));
    }

    @Override
    public boolean removePlayer(IPlayer player) {
        return player != null && this.removePlayer((EntityPlayer)((EntityPlayerMP)player.getMCEntity()));
    }

    public boolean hasPlayer(EntityPlayer player) {
        return this.partyMembers.containsKey(player.func_110124_au());
    }

    @Override
    public boolean hasPlayer(IPlayer player) {
        if (player == null) {
            return false;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        return this.partyMembers.containsKey(entityPlayer.func_110124_au());
    }

    @Override
    public boolean hasPlayer(String playerName) {
        UUID uuid = Party.getUUID(playerName);
        if (uuid == null) {
            return false;
        }
        return this.partyMembers.containsKey(uuid);
    }

    public EntityPlayer getPartyLeader() {
        return NoppesUtilServer.getPlayer(this.partyLeader);
    }

    @Override
    public String getPartyLeaderName() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            return this.partyLeaderName == null ? "" : this.partyLeaderName;
        }
        EntityPlayer leader = this.getPartyLeader();
        if (leader != null) {
            return leader.func_70005_c_();
        }
        String name = this.partyMembers.get(this.partyLeader);
        return name == null ? "" : name;
    }

    public boolean setLeader(EntityPlayer player) {
        UUID uuid = player.func_110124_au();
        if (this.partyLeader != null && this.partyLeader.equals(uuid)) {
            return false;
        }
        if (!this.partyMembers.containsKey(uuid)) {
            return false;
        }
        this.partyOrder.remove(uuid);
        this.partyOrder.add(0, uuid);
        this.partyLeader = uuid;
        return true;
    }

    public UUID getLeaderUUID() {
        return this.partyLeader;
    }

    public Collection<String> getPlayerNames() {
        return this.getPlayerNames(false);
    }

    @Override
    public List<String> getPlayerNamesList() {
        return new ArrayList<String>(this.getPlayerNames(false));
    }

    public Collection<String> getPlayerNames(boolean lowercase) {
        Collection<String> names = this.partyMembers.values();
        if (!lowercase) {
            return names;
        }
        ArrayList<String> lowerNames = new ArrayList<String>();
        for (String s : names) {
            lowerNames.add(s.toLowerCase());
        }
        return lowerNames;
    }

    public Collection<UUID> getPlayerUUIDs() {
        return this.partyMembers.keySet();
    }

    @Override
    public boolean validateQuest(int questID, boolean sendLeaderMessages) {
        IQuest quest = QuestController.Instance.get(questID);
        if (quest == null) {
            return false;
        }
        EntityPlayer leader = PlayerDataController.getPlayerFromUUID(this.partyLeader);
        if (leader == null) {
            return false;
        }
        int partyReq = quest.getPartyOptions().getPartyRequirements();
        if (partyReq < 0 || partyReq >= EnumPartyRequirements.values().length) {
            this.sendInfoMessage(leader, "Error in quest party requirements", sendLeaderMessages);
            return false;
        }
        if (this.partyMembers.size() < quest.getPartyOptions().getMinPartySize()) {
            this.sendInfoMessage(leader, String.format("Party too small. Min %d members", quest.getPartyOptions().getMinPartySize()), sendLeaderMessages);
            return false;
        }
        if (this.partyMembers.size() > quest.getPartyOptions().getMaxPartySize()) {
            this.sendInfoMessage(leader, String.format("Party too large. Max %d members", quest.getPartyOptions().getMaxPartySize()), sendLeaderMessages);
            return false;
        }
        EnumPartyRequirements partyRequirements = EnumPartyRequirements.values()[partyReq];
        if (partyRequirements == EnumPartyRequirements.Leader) {
            boolean leaderBool = this.isValidLeaderQuest(leader, questID);
            if (!leaderBool) {
                this.sendInfoMessage(leader, "\u00a7cYou are invalid for this quest", sendLeaderMessages);
            }
            return leaderBool;
        }
        return this.arePlayersValid(leader, partyRequirements, quest, sendLeaderMessages);
    }

    private boolean isValidLeaderQuest(EntityPlayer leader, int questID) {
        IPlayerQuestData questData = PlayerData.get(leader).getQuestData();
        return questData != null && questData.hasActiveQuest(questID);
    }

    private boolean arePlayersValid(EntityPlayer leader, EnumPartyRequirements requirements, IQuest quest, boolean sendLeaderMessages) {
        boolean allowQuest = true;
        for (UUID playerUUID : this.partyMembers.keySet()) {
            EntityPlayer player = PlayerDataController.getPlayerFromUUID(playerUUID);
            if (player != null) {
                PlayerData playerData = PlayerData.get(player);
                if (playerData != null) {
                    IPlayerQuestData questData = playerData.getQuestData();
                    if (questData != null) {
                        boolean hasActive = questData.hasActiveQuest(quest.getId());
                        boolean hasFinished = questData.hasFinishedQuest(quest.getId());
                        if (requirements == EnumPartyRequirements.All && !hasActive) {
                            allowQuest = false;
                            this.sendInfoMessage(leader, String.format("%s does not have quest active", player.func_70005_c_()), sendLeaderMessages);
                            continue;
                        }
                        if (requirements != EnumPartyRequirements.Valid || hasActive || hasFinished) continue;
                        allowQuest = false;
                        this.sendInfoMessage(leader, String.format("%s does not have the quest active or finished", player.func_70005_c_()), sendLeaderMessages);
                        continue;
                    }
                    allowQuest = false;
                    this.sendInfoMessage(leader, String.format("%s has no quest data", player.func_70005_c_()), sendLeaderMessages);
                    continue;
                }
                allowQuest = false;
                this.sendInfoMessage(leader, String.format("%s has no player data", player.func_70005_c_()), sendLeaderMessages);
                continue;
            }
            allowQuest = false;
            String playerName = this.partyMembers.get(playerUUID);
            this.sendInfoMessage(leader, String.format("%s was not found", playerName), sendLeaderMessages);
        }
        return allowQuest;
    }

    private void sendInfoMessage(EntityPlayer player, String message, boolean send) {
        if (!send) {
            return;
        }
        player.func_145747_a((IChatComponent)new ChatComponentText(String.format("\u00a7c%s", message)));
    }

    @Override
    public void toggleFriendlyFire() {
        this.friendlyFire = !this.friendlyFire;
    }

    @Override
    public boolean friendlyFire() {
        return this.friendlyFire;
    }

    public static UUID getUUID(String name) {
        UUID uuid = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openStream()));
            String uuidString = ((JsonObject)new JsonParser().parse((Reader)in)).get("id").toString().replaceAll("\"", "");
            uuidString = uuidString.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
            in.close();
            uuid = UUID.fromString(uuidString);
        }
        catch (Exception exception) {
            // empty catch block
        }
        return uuid;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.partyLeaderName = compound.func_74779_i("PartyLeader");
        this.currentQuestName = compound.func_74779_i("PartyQuestName");
        this.friendlyFire = compound.func_74767_n("FriendlyFire");
        this.partyLocked = compound.func_74767_n("PartyLocked");
        this.currentQuestID = compound.func_74762_e("PartyQuestID");
        if (compound.func_74764_b("QuestNBT")) {
            this.quest = new Quest();
            this.quest.readNBT(compound.func_74775_l("QuestNBT"));
        }
        NBTTagList list = compound.func_150295_c("PartyMembers", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound tagCompound = list.func_150305_b(i);
            UUID uuid = UUID.fromString(tagCompound.func_74779_i("UUID"));
            String playerName = tagCompound.func_74779_i("PlayerName");
            this.partyOrder.add(uuid);
            this.partyMembers.put(uuid, playerName);
        }
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("PartyUUID", this.partyUUID.toString());
        compound.func_74778_a("PartyQuestName", this.currentQuestName);
        compound.func_74757_a("FriendlyFire", this.friendlyFire);
        compound.func_74757_a("PartyLocked", this.partyLocked);
        compound.func_74768_a("PartyQuestID", this.currentQuestID);
        if (this.getQuest() != null) {
            Quest quest = (Quest)this.getQuest();
            compound.func_74782_a("QuestNBT", (NBTBase)quest.writeToNBT(new NBTTagCompound()));
        }
        NBTTagList list = new NBTTagList();
        for (UUID uuid : this.partyOrder) {
            NBTTagCompound uuidCompound = new NBTTagCompound();
            uuidCompound.func_74778_a("UUID", uuid.toString());
            String name = this.partyMembers.get(uuid);
            uuidCompound.func_74778_a("PlayerName", name == null ? "" : name);
            list.func_74742_a((NBTBase)uuidCompound);
        }
        compound.func_74782_a("PartyMembers", (NBTBase)list);
        EntityPlayer leader = this.getPartyLeader();
        compound.func_74778_a("PartyLeader", leader != null ? leader.func_70005_c_() : "");
        Quest quest = (Quest)QuestController.Instance.get(this.getCurrentQuestID());
        if (quest != null) {
            compound.func_74778_a("QuestName", quest.getName());
            compound.func_74778_a("QuestCategory", quest.getCategory().getName());
        }
        return compound;
    }

    @Override
    public void updateQuestObjectiveData() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            PartyController.Instance().pingPartyQuestObjectiveUpdate(this);
        }
    }

    @Override
    public void updatePartyData() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            PartyController.Instance().pingPartyUpdate(this);
        }
    }

    public void readClientNBT(NBTTagCompound compound) {
        this.currentQuestID = compound.func_74762_e("PartyQuestID");
        this.currentQuestName = compound.func_74779_i("PartyQuestName");
        this.friendlyFire = compound.func_74767_n("FriendlyFire");
        NBTTagList list = compound.func_150295_c("PartyMembers", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound tagCompound = list.func_150305_b(i);
            UUID uuid = UUID.fromString(tagCompound.func_74779_i("UUID"));
            String playerName = tagCompound.func_74779_i("PlayerName");
            this.partyOrder.add(uuid);
            this.partyMembers.put(uuid, playerName);
        }
    }

    public NBTTagCompound writeClientNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("PartyQuestID", this.currentQuestID);
        compound.func_74778_a("PartyQuestName", this.currentQuestName);
        compound.func_74757_a("FriendlyFire", this.friendlyFire);
        NBTTagList list = new NBTTagList();
        for (UUID uuid : this.partyOrder) {
            NBTTagCompound uuidCompound = new NBTTagCompound();
            uuidCompound.func_74778_a("UUID", uuid.toString());
            String name = this.partyMembers.get(uuid);
            uuidCompound.func_74778_a("PlayerName", name == null ? "" : name);
            list.func_74742_a((NBTBase)uuidCompound);
        }
        compound.func_74782_a("PartyMembers", (NBTBase)list);
        return compound;
    }
}

