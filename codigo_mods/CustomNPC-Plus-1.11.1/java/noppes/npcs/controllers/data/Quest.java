/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import kamkeel.npcs.network.packets.data.QuestCompletionPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.ICompatibilty;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NpcMiscInventory;
import noppes.npcs.VersionCompatibility;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.IPlayerQuestData;
import noppes.npcs.api.handler.data.IPartyOptions;
import noppes.npcs.api.handler.data.IProfileOptions;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.handler.data.IQuestCategory;
import noppes.npcs.api.handler.data.IQuestInterface;
import noppes.npcs.api.handler.data.IQuestObjective;
import noppes.npcs.client.ProfileClientConfig;
import noppes.npcs.constants.EnumProfileSync;
import noppes.npcs.constants.EnumQuestCompletion;
import noppes.npcs.constants.EnumQuestRepeat;
import noppes.npcs.constants.EnumQuestType;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.FactionOptions;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PartyOptions;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerMail;
import noppes.npcs.controllers.data.ProfileOptions;
import noppes.npcs.controllers.data.QuestCategory;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.quests.QuestDialog;
import noppes.npcs.quests.QuestInterface;
import noppes.npcs.quests.QuestItem;
import noppes.npcs.quests.QuestKill;
import noppes.npcs.quests.QuestLocation;
import noppes.npcs.quests.QuestManual;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;

public class Quest
implements ICompatibilty,
IQuest {
    public int version = VersionCompatibility.ModRev;
    public int id = -1;
    public EnumQuestType type = EnumQuestType.Item;
    public EnumQuestRepeat repeat = EnumQuestRepeat.NONE;
    public EnumQuestCompletion completion = EnumQuestCompletion.Npc;
    public String title = "default";
    public QuestCategory category;
    public String logText = "";
    public String completeText = "";
    public String completerNpc = "";
    public int nextQuestid = -1;
    public String nextQuestTitle = "";
    public PlayerMail mail = new PlayerMail();
    public String command = "";
    public QuestInterface questInterface = new QuestItem();
    public long customCooldown = 0L;
    public int rewardExp = 0;
    public NpcMiscInventory rewardItems = new NpcMiscInventory(9);
    public boolean randomReward = false;
    public FactionOptions factionOptions = new FactionOptions();
    public PartyOptions partyOptions = new PartyOptions();
    public ProfileOptions profileOptions = new ProfileOptions();

    public void readNBT(NBTTagCompound compound) {
        this.id = compound.func_74762_e("Id");
        this.readNBTPartial(compound);
    }

    public void readNBTPartial(NBTTagCompound compound) {
        this.version = compound.func_74762_e("ModRev");
        VersionCompatibility.CheckAvailabilityCompatibility(this, compound);
        this.setType(EnumQuestType.values()[compound.func_74762_e("Type")]);
        this.title = compound.func_74779_i("Title");
        this.logText = compound.func_74779_i("Text");
        this.completeText = compound.func_74779_i("CompleteText");
        this.completerNpc = compound.func_74779_i("CompleterNpc");
        this.command = compound.func_74779_i("QuestCommand");
        this.nextQuestid = compound.func_74762_e("NextQuestId");
        this.nextQuestTitle = compound.func_74779_i("NextQuestTitle");
        this.nextQuestTitle = this.hasNewQuest() ? this.getNextQuest().title : "";
        this.randomReward = compound.func_74767_n("RandomReward");
        this.rewardExp = compound.func_74762_e("RewardExp");
        this.rewardItems.setFromNBT(compound.func_74775_l("Rewards"));
        this.completion = EnumQuestCompletion.values()[compound.func_74762_e("QuestCompletion")];
        this.repeat = EnumQuestRepeat.values()[compound.func_74762_e("QuestRepeat")];
        this.customCooldown = compound.func_74763_f("CustomCooldown");
        this.questInterface.readEntityFromNBT(compound);
        this.factionOptions.readFromNBT(compound.func_74775_l("QuestFactionPoints"));
        this.partyOptions.readFromNBT(compound.func_74775_l("PartyOptions"));
        this.profileOptions.readFromNBT(compound.func_74775_l("ProfileOptions"));
        this.mail.readNBT(compound.func_74775_l("QuestMail"));
    }

    public void setType(EnumQuestType questType) {
        this.type = questType;
        if (this.type == EnumQuestType.Item) {
            this.questInterface = new QuestItem();
        } else if (this.type == EnumQuestType.Dialog) {
            this.questInterface = new QuestDialog();
        } else if (this.type == EnumQuestType.Kill || this.type == EnumQuestType.AreaKill) {
            this.questInterface = new QuestKill();
        } else if (this.type == EnumQuestType.Location) {
            this.questInterface = new QuestLocation();
        } else if (this.type == EnumQuestType.Manual) {
            this.questInterface = new QuestManual();
        }
        if (this.questInterface != null) {
            this.questInterface.questId = this.id;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74768_a("Id", this.id);
        return this.writeToNBTPartial(compound);
    }

    public NBTTagCompound writeToNBTPartial(NBTTagCompound compound) {
        compound.func_74768_a("ModRev", this.version);
        compound.func_74768_a("Type", this.type.ordinal());
        compound.func_74778_a("Title", this.title);
        compound.func_74778_a("Text", this.logText);
        compound.func_74778_a("CompleteText", this.completeText);
        compound.func_74778_a("CompleterNpc", this.completerNpc);
        compound.func_74768_a("NextQuestId", this.nextQuestid);
        compound.func_74778_a("NextQuestTitle", this.nextQuestTitle);
        compound.func_74768_a("RewardExp", this.rewardExp);
        compound.func_74782_a("Rewards", (NBTBase)this.rewardItems.getToNBT());
        compound.func_74778_a("QuestCommand", this.command);
        compound.func_74757_a("RandomReward", this.randomReward);
        compound.func_74772_a("CustomCooldown", this.customCooldown);
        compound.func_74768_a("QuestCompletion", this.completion.ordinal());
        compound.func_74768_a("QuestRepeat", this.repeat.ordinal());
        this.questInterface.writeEntityToNBT(compound);
        compound.func_74782_a("QuestFactionPoints", (NBTBase)this.factionOptions.writeToNBT(new NBTTagCompound()));
        compound.func_74782_a("PartyOptions", (NBTBase)this.partyOptions.writeToNBT());
        compound.func_74782_a("ProfileOptions", (NBTBase)this.profileOptions.writeToNBT());
        compound.func_74782_a("QuestMail", (NBTBase)this.mail.writeNBT());
        return compound;
    }

    public boolean hasNewQuest() {
        return this.getNextQuest() != null;
    }

    @Override
    public Quest getNextQuest() {
        return QuestController.Instance == null ? null : QuestController.Instance.quests.get(this.nextQuestid);
    }

    public boolean instantComplete(EntityPlayer player, QuestData data) {
        if (this.completion == EnumQuestCompletion.Instant && NoppesUtilPlayer.questCompletion((EntityPlayerMP)player, data.quest.id)) {
            QuestCompletionPacket.sendQuestComplete((EntityPlayerMP)player, data.quest.writeToNBT(new NBTTagCompound()));
            return true;
        }
        return false;
    }

    public boolean instantPartyComplete(Party party) {
        return this.completion == EnumQuestCompletion.Instant && NoppesUtilPlayer.questPartyCompletion(party);
    }

    public Quest copy() {
        Quest quest = new Quest();
        quest.readNBT(this.writeToNBT(new NBTTagCompound()));
        return quest;
    }

    public long getTimeUntilRepeat(EntityPlayer player) {
        if (ProfileClientConfig.isProfilesEnabled() && this.profileOptions.enableOptions && this.profileOptions.cooldownControl == EnumProfileSync.Shared) {
            Long questTime = ProfileClientConfig.getSharedQuestTimestamp(player, this.id);
            if (questTime != null) {
                return this.calculateRemainingCooldown(player, questTime);
            }
            return 0L;
        }
        IPlayerQuestData questData = PlayerData.get(player).getQuestData();
        return this.getTimeUntilRepeatQuestData(player, questData);
    }

    public long calculateRemainingCooldown(EntityPlayer player, long questTime) {
        switch (this.repeat) {
            case MCDAILY: {
                long now = player.field_70170_p.func_82737_E();
                long remainingTicks = questTime + 24000L - now;
                return Math.max(0L, remainingTicks * 50L);
            }
            case MCWEEKLY: {
                long now = player.field_70170_p.func_82737_E();
                long remainingTicks = questTime + 168000L - now;
                return Math.max(0L, remainingTicks * 50L);
            }
            case MCCUSTOM: {
                long now = player.field_70170_p.func_82737_E();
                long remainingTicks = questTime + this.customCooldown - now;
                return Math.max(0L, remainingTicks * 50L);
            }
            case RLDAILY: {
                long remainingMillis = questTime + 86400000L - System.currentTimeMillis();
                return Math.max(0L, remainingMillis);
            }
            case RLWEEKLY: {
                long remainingMillis = questTime + 604800000L - System.currentTimeMillis();
                return Math.max(0L, remainingMillis);
            }
            case RLCUSTOM: {
                long remainingMillis = questTime + this.customCooldown - System.currentTimeMillis();
                return Math.max(0L, remainingMillis);
            }
        }
        return 0L;
    }

    public long getTimeUntilRepeatQuestData(EntityPlayer player, IPlayerQuestData questData) {
        long questTime = questData.getLastCompletedTime(this.id);
        switch (this.repeat) {
            case MCDAILY: {
                long now = player.field_70170_p.func_82737_E();
                long allowedTicks = 24000L;
                if (questTime > now && questTime - now > allowedTicks) {
                    questTime = now;
                    questData.setLastCompletedTime(this.id, questTime);
                }
                long remainingTicks = questTime + allowedTicks - now;
                return Math.max(0L, remainingTicks * 50L);
            }
            case MCWEEKLY: {
                long now = player.field_70170_p.func_82737_E();
                long allowedTicks = 168000L;
                if (questTime > now && questTime - now > allowedTicks) {
                    questTime = now;
                    questData.setLastCompletedTime(this.id, questTime);
                }
                long remainingTicks = questTime + allowedTicks - now;
                return Math.max(0L, remainingTicks * 50L);
            }
            case RLDAILY: {
                long now = System.currentTimeMillis();
                long allowedMillis = 86400000L;
                if (questTime > now && questTime - now > allowedMillis) {
                    questTime = now;
                    questData.setLastCompletedTime(this.id, questTime);
                }
                long remainingMillis = questTime + allowedMillis - now;
                return Math.max(0L, remainingMillis);
            }
            case RLWEEKLY: {
                long now = System.currentTimeMillis();
                long allowedMillis = 604800000L;
                if (questTime > now && questTime - now > allowedMillis) {
                    questTime = now;
                    questData.setLastCompletedTime(this.id, questTime);
                }
                long remainingMillis = questTime + allowedMillis - now;
                return Math.max(0L, remainingMillis);
            }
            case MCCUSTOM: {
                long now = player.field_70170_p.func_82737_E();
                long allowedTicks = this.customCooldown;
                if (questTime > now && questTime - now > allowedTicks) {
                    questTime = now;
                    questData.setLastCompletedTime(this.id, questTime);
                }
                long remainingTicks = questTime + allowedTicks - now;
                return Math.max(0L, remainingTicks * 50L);
            }
            case RLCUSTOM: {
                long now = System.currentTimeMillis();
                long allowedMillis = this.customCooldown;
                if (questTime > now && questTime - now > allowedMillis) {
                    questTime = now;
                    questData.setLastCompletedTime(this.id, questTime);
                }
                long remainingMillis = questTime + allowedMillis - now;
                return Math.max(0L, remainingMillis);
            }
        }
        return 0L;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.title;
    }

    @Override
    public int getType() {
        return this.type.ordinal();
    }

    @Override
    public void setType(int questType) {
        if (questType < 0 || questType >= EnumQuestType.values().length) {
            return;
        }
        EnumQuestType type = EnumQuestType.values()[questType];
        this.setType(type);
    }

    @Override
    public IQuestCategory getCategory() {
        return this.category;
    }

    @Override
    public void save() {
        QuestController.Instance.saveQuest(this.category.id, this);
    }

    @Override
    public void setName(String name) {
        this.title = name;
    }

    @Override
    public String getLogText() {
        return this.logText;
    }

    @Override
    public void setLogText(String text) {
        this.logText = text;
    }

    @Override
    public String getCompleteText() {
        return this.completeText;
    }

    @Override
    public void setCompleteText(String text) {
        this.completeText = text;
    }

    @Override
    public void setNextQuest(IQuest quest) {
        if (quest == null) {
            this.nextQuestid = -1;
            this.nextQuestTitle = "";
        } else {
            if (quest.getId() < 0) {
                throw new CustomNPCsException("Quest id is lower than 0", new Object[0]);
            }
            this.nextQuestid = quest.getId();
            this.nextQuestTitle = quest.getName();
        }
    }

    @Override
    public String getNpcName() {
        return this.completerNpc;
    }

    @Override
    public void setNpcName(String name) {
        this.completerNpc = name;
    }

    @Override
    public IQuestObjective[] getObjectives(IPlayer player) {
        if (!player.hasActiveQuest(this.id)) {
            throw new CustomNPCsException("Player doesnt have this quest active.", new Object[0]);
        }
        return this.questInterface.getObjectives((EntityPlayer)player.getMCEntity());
    }

    @Override
    public boolean getIsRepeatable() {
        return this.repeat != EnumQuestRepeat.NONE;
    }

    @Override
    public long getTimeUntilRepeat(IPlayer player) {
        return this.getTimeUntilRepeat((EntityPlayer)player.getMCEntity());
    }

    @Override
    public void setRepeatType(int type) {
        if (type < 0 || type >= EnumQuestRepeat.values().length) {
            return;
        }
        this.repeat = EnumQuestRepeat.values()[type];
    }

    @Override
    public int getRepeatType() {
        return this.repeat.ordinal();
    }

    @Override
    public IContainer getRewards() {
        return NpcAPI.Instance().getIContainer(this.rewardItems);
    }

    @Override
    public IQuestInterface getQuestInterface() {
        return this.questInterface;
    }

    @Override
    public IPartyOptions getPartyOptions() {
        return this.partyOptions;
    }

    @Override
    public IProfileOptions getProfileOptions() {
        return this.profileOptions;
    }

    @Override
    public long getCustomCooldown() {
        return this.customCooldown;
    }

    @Override
    public void setCustomCooldown(long newCooldown) {
        this.customCooldown = newCooldown;
    }
}

