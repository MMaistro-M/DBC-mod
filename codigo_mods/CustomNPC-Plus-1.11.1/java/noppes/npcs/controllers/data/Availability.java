/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import noppes.npcs.ICompatibilty;
import noppes.npcs.VersionCompatibility;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IAvailability;
import noppes.npcs.constants.EnumAvailabilityDialog;
import noppes.npcs.constants.EnumAvailabilityFaction;
import noppes.npcs.constants.EnumAvailabilityFactionType;
import noppes.npcs.constants.EnumAvailabilityQuest;
import noppes.npcs.constants.EnumDayTime;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.PlayerQuestController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerFactionData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.scripted.CustomNPCsException;

public class Availability
implements ICompatibilty,
IAvailability {
    public int version = VersionCompatibility.ModRev;
    public EnumAvailabilityDialog dialogAvailable = EnumAvailabilityDialog.Always;
    public EnumAvailabilityDialog dialog2Available = EnumAvailabilityDialog.Always;
    public EnumAvailabilityDialog dialog3Available = EnumAvailabilityDialog.Always;
    public EnumAvailabilityDialog dialog4Available = EnumAvailabilityDialog.Always;
    public int dialogId = -1;
    public int dialog2Id = -1;
    public int dialog3Id = -1;
    public int dialog4Id = -1;
    public EnumAvailabilityQuest questAvailable = EnumAvailabilityQuest.Always;
    public EnumAvailabilityQuest quest2Available = EnumAvailabilityQuest.Always;
    public EnumAvailabilityQuest quest3Available = EnumAvailabilityQuest.Always;
    public EnumAvailabilityQuest quest4Available = EnumAvailabilityQuest.Always;
    public int questId = -1;
    public int quest2Id = -1;
    public int quest3Id = -1;
    public int quest4Id = -1;
    public EnumDayTime daytime = EnumDayTime.Always;
    public int factionId = -1;
    public int faction2Id = -1;
    public EnumAvailabilityFactionType factionAvailable = EnumAvailabilityFactionType.Always;
    public EnumAvailabilityFactionType faction2Available = EnumAvailabilityFactionType.Always;
    public EnumAvailabilityFaction factionStance = EnumAvailabilityFaction.Friendly;
    public EnumAvailabilityFaction faction2Stance = EnumAvailabilityFaction.Friendly;
    public int minPlayerLevel = 0;

    public void readFromNBT(NBTTagCompound compound) {
        this.version = compound.func_74762_e("ModRev");
        VersionCompatibility.CheckAvailabilityCompatibility(this, compound);
        this.dialogAvailable = EnumAvailabilityDialog.values()[compound.func_74762_e("AvailabilityDialog")];
        this.dialog2Available = EnumAvailabilityDialog.values()[compound.func_74762_e("AvailabilityDialog2")];
        this.dialog3Available = EnumAvailabilityDialog.values()[compound.func_74762_e("AvailabilityDialog3")];
        this.dialog4Available = EnumAvailabilityDialog.values()[compound.func_74762_e("AvailabilityDialog4")];
        this.dialogId = compound.func_74762_e("AvailabilityDialogId");
        this.dialog2Id = compound.func_74762_e("AvailabilityDialog2Id");
        this.dialog3Id = compound.func_74762_e("AvailabilityDialog3Id");
        this.dialog4Id = compound.func_74762_e("AvailabilityDialog4Id");
        this.questAvailable = EnumAvailabilityQuest.values()[compound.func_74762_e("AvailabilityQuest")];
        this.quest2Available = EnumAvailabilityQuest.values()[compound.func_74762_e("AvailabilityQuest2")];
        this.quest3Available = EnumAvailabilityQuest.values()[compound.func_74762_e("AvailabilityQuest3")];
        this.quest4Available = EnumAvailabilityQuest.values()[compound.func_74762_e("AvailabilityQuest4")];
        this.questId = compound.func_74762_e("AvailabilityQuestId");
        this.quest2Id = compound.func_74762_e("AvailabilityQuest2Id");
        this.quest3Id = compound.func_74762_e("AvailabilityQuest3Id");
        this.quest4Id = compound.func_74762_e("AvailabilityQuest4Id");
        this.setFactionAvailability(compound.func_74762_e("AvailabilityFaction"));
        this.setFactionAvailabilityStance(compound.func_74762_e("AvailabilityFactionStance"));
        this.setFaction2Availability(compound.func_74762_e("AvailabilityFaction2"));
        this.setFaction2AvailabilityStance(compound.func_74762_e("AvailabilityFaction2Stance"));
        this.factionId = compound.func_74762_e("AvailabilityFactionId");
        this.faction2Id = compound.func_74762_e("AvailabilityFaction2Id");
        this.daytime = EnumDayTime.values()[compound.func_74762_e("AvailabilityDayTime")];
        this.minPlayerLevel = compound.func_74762_e("AvailabilityMinPlayerLevel");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74768_a("ModRev", this.version);
        compound.func_74768_a("AvailabilityDialog", this.dialogAvailable.ordinal());
        compound.func_74768_a("AvailabilityDialog2", this.dialog2Available.ordinal());
        compound.func_74768_a("AvailabilityDialog3", this.dialog3Available.ordinal());
        compound.func_74768_a("AvailabilityDialog4", this.dialog4Available.ordinal());
        compound.func_74768_a("AvailabilityDialogId", this.dialogId);
        compound.func_74768_a("AvailabilityDialog2Id", this.dialog2Id);
        compound.func_74768_a("AvailabilityDialog3Id", this.dialog3Id);
        compound.func_74768_a("AvailabilityDialog4Id", this.dialog4Id);
        compound.func_74768_a("AvailabilityQuest", this.questAvailable.ordinal());
        compound.func_74768_a("AvailabilityQuest2", this.quest2Available.ordinal());
        compound.func_74768_a("AvailabilityQuest3", this.quest3Available.ordinal());
        compound.func_74768_a("AvailabilityQuest4", this.quest4Available.ordinal());
        compound.func_74768_a("AvailabilityQuestId", this.questId);
        compound.func_74768_a("AvailabilityQuest2Id", this.quest2Id);
        compound.func_74768_a("AvailabilityQuest3Id", this.quest3Id);
        compound.func_74768_a("AvailabilityQuest4Id", this.quest4Id);
        compound.func_74768_a("AvailabilityFaction", this.factionAvailable.ordinal());
        compound.func_74768_a("AvailabilityFactionStance", this.factionStance.ordinal());
        compound.func_74768_a("AvailabilityFactionId", this.factionId);
        compound.func_74768_a("AvailabilityFaction2", this.faction2Available.ordinal());
        compound.func_74768_a("AvailabilityFaction2Stance", this.faction2Stance.ordinal());
        compound.func_74768_a("AvailabilityFaction2Id", this.faction2Id);
        compound.func_74768_a("AvailabilityDayTime", this.daytime.ordinal());
        compound.func_74768_a("AvailabilityMinPlayerLevel", this.minPlayerLevel);
        return compound;
    }

    public boolean isDefault() {
        return this.dialogAvailable == EnumAvailabilityDialog.Always && this.dialog2Available == EnumAvailabilityDialog.Always && this.dialog3Available == EnumAvailabilityDialog.Always && this.dialog4Available == EnumAvailabilityDialog.Always && this.dialogId == -1 && this.questAvailable == EnumAvailabilityQuest.Always && this.quest2Available == EnumAvailabilityQuest.Always && this.quest3Available == EnumAvailabilityQuest.Always && this.quest4Available == EnumAvailabilityQuest.Always && this.questId == -1 && this.factionAvailable == EnumAvailabilityFactionType.Always && this.faction2Available == EnumAvailabilityFactionType.Always && this.factionId == -1 && this.faction2Id == -1 && this.factionStance == EnumAvailabilityFaction.Friendly && this.faction2Stance == EnumAvailabilityFaction.Friendly && this.daytime == EnumDayTime.Always && this.minPlayerLevel == 0;
    }

    public void setFactionAvailability(int value) {
        this.factionAvailable = EnumAvailabilityFactionType.values()[value];
    }

    public void setFaction2Availability(int value) {
        this.faction2Available = EnumAvailabilityFactionType.values()[value];
    }

    public void setFactionAvailabilityStance(int integer) {
        this.factionStance = EnumAvailabilityFaction.values()[integer];
    }

    public void setFaction2AvailabilityStance(int integer) {
        this.faction2Stance = EnumAvailabilityFaction.values()[integer];
    }

    public boolean isAvailable(EntityPlayer player) {
        long time;
        if (this.daytime == EnumDayTime.Day && (time = player.field_70170_p.func_72820_D() % 24000L) > 12000L) {
            return false;
        }
        if (this.daytime == EnumDayTime.Night && (time = player.field_70170_p.func_72820_D() % 24000L) < 12000L) {
            return false;
        }
        if (!this.dialogAvailable(this.dialogId, this.dialogAvailable, player)) {
            return false;
        }
        if (!this.dialogAvailable(this.dialog2Id, this.dialog2Available, player)) {
            return false;
        }
        if (!this.dialogAvailable(this.dialog3Id, this.dialog3Available, player)) {
            return false;
        }
        if (!this.dialogAvailable(this.dialog4Id, this.dialog4Available, player)) {
            return false;
        }
        if (!this.questAvailable(this.questId, this.questAvailable, player)) {
            return false;
        }
        if (!this.questAvailable(this.quest2Id, this.quest2Available, player)) {
            return false;
        }
        if (!this.questAvailable(this.quest3Id, this.quest3Available, player)) {
            return false;
        }
        if (!this.questAvailable(this.quest4Id, this.quest4Available, player)) {
            return false;
        }
        if (!this.factionAvailable(this.factionId, this.factionStance, this.factionAvailable, player)) {
            return false;
        }
        if (!this.factionAvailable(this.faction2Id, this.faction2Stance, this.faction2Available, player)) {
            return false;
        }
        return player.field_71068_ca >= this.minPlayerLevel;
    }

    @SideOnly(value=Side.CLIENT)
    public List<String> isAvailableText(EntityPlayer player) {
        long time;
        ArrayList<String> errors = new ArrayList<String>();
        if (this.daytime == EnumDayTime.Day) {
            long time2 = player.field_70170_p.func_72820_D() % 24000L;
            if (time2 > 12000L) {
                errors.add(StatCollector.func_74838_a((String)"availability.error.day"));
            }
        } else if (this.daytime == EnumDayTime.Night && (time = player.field_70170_p.func_72820_D() % 24000L) < 12000L) {
            errors.add(StatCollector.func_74838_a((String)"availability.error.night"));
        }
        if (!this.dialogAvailable(this.dialogId, this.dialogAvailable, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.dialog", (Object[])new Object[]{this.dialogAvailable.toString(), this.dialogId}));
        }
        if (!this.dialogAvailable(this.dialog2Id, this.dialog2Available, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.dialog", (Object[])new Object[]{this.dialog2Available.toString(), this.dialog2Id}));
        }
        if (!this.dialogAvailable(this.dialog3Id, this.dialog3Available, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.dialog", (Object[])new Object[]{this.dialog3Available.toString(), this.dialog3Id}));
        }
        if (!this.dialogAvailable(this.dialog4Id, this.dialog4Available, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.dialog", (Object[])new Object[]{this.dialog4Available.toString(), this.dialog4Id}));
        }
        if (!this.questAvailable(this.questId, this.questAvailable, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.quest", (Object[])new Object[]{this.questAvailable.toString(), this.questId}));
        }
        if (!this.questAvailable(this.quest2Id, this.quest2Available, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.quest", (Object[])new Object[]{this.quest2Available.toString(), this.quest2Id}));
        }
        if (!this.questAvailable(this.quest3Id, this.quest3Available, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.quest", (Object[])new Object[]{this.quest3Available.toString(), this.quest3Id}));
        }
        if (!this.questAvailable(this.quest4Id, this.quest4Available, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.quest", (Object[])new Object[]{this.quest4Available.toString(), this.quest4Id}));
        }
        if (!this.factionAvailable(this.factionId, this.factionStance, this.factionAvailable, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.faction", (Object[])new Object[]{this.factionAvailable.toString(), this.factionStance.toString(), this.factionId}));
        }
        if (!this.factionAvailable(this.faction2Id, this.faction2Stance, this.faction2Available, player)) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.faction", (Object[])new Object[]{this.faction2Available.toString(), this.faction2Stance.toString(), this.faction2Id}));
        }
        if (player.field_71068_ca < this.minPlayerLevel) {
            errors.add(StatCollector.func_74837_a((String)"availability.error.xp", (Object[])new Object[]{this.minPlayerLevel}));
        }
        return errors;
    }

    private boolean factionAvailable(int id, EnumAvailabilityFaction stance, EnumAvailabilityFactionType available, EntityPlayer player) {
        if (available == EnumAvailabilityFactionType.Always) {
            return true;
        }
        Faction faction = FactionController.getInstance().get(id);
        if (faction == null) {
            return true;
        }
        PlayerFactionData data = PlayerData.get((EntityPlayer)player).factionData;
        int points = data.getFactionPoints(id);
        EnumAvailabilityFaction current = EnumAvailabilityFaction.Neutral;
        if (faction.neutralPoints >= points) {
            current = EnumAvailabilityFaction.Hostile;
        }
        if (faction.friendlyPoints < points) {
            current = EnumAvailabilityFaction.Friendly;
        }
        if (available == EnumAvailabilityFactionType.Is && stance == current) {
            return true;
        }
        return available == EnumAvailabilityFactionType.IsNot && stance != current;
    }

    public boolean dialogAvailable(int id, EnumAvailabilityDialog en, EntityPlayer player) {
        if (player == null) {
            return false;
        }
        if (en == EnumAvailabilityDialog.Always) {
            return true;
        }
        PlayerData data = PlayerData.get(player);
        if (data == null) {
            return false;
        }
        boolean hasRead = data.dialogData.dialogsRead.contains(id);
        if (hasRead && en == EnumAvailabilityDialog.After) {
            return true;
        }
        return !hasRead && en == EnumAvailabilityDialog.Before;
    }

    public boolean questAvailable(int id, EnumAvailabilityQuest en, EntityPlayer player) {
        if (en == EnumAvailabilityQuest.Always) {
            return true;
        }
        if (en == EnumAvailabilityQuest.After && PlayerQuestController.isQuestFinished(player, id)) {
            return true;
        }
        if (en == EnumAvailabilityQuest.Before && !PlayerQuestController.isQuestFinished(player, id)) {
            return true;
        }
        if (en == EnumAvailabilityQuest.Active && PlayerQuestController.isQuestActive(player, id)) {
            return true;
        }
        if (en == EnumAvailabilityQuest.NotActive && !PlayerQuestController.isQuestActive(player, id)) {
            return true;
        }
        if (en == EnumAvailabilityQuest.Acceptable || en == EnumAvailabilityQuest.NotAcceptable) {
            Quest quest = QuestController.Instance.quests.get(id);
            if (quest == null) {
                return true;
            }
            boolean result = PlayerQuestController.canQuestBeAccepted(quest, player);
            return result && en == EnumAvailabilityQuest.Acceptable || !result && en == EnumAvailabilityQuest.NotAcceptable;
        }
        return false;
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
    public boolean isAvailable(IPlayer player) {
        return this.isAvailable((EntityPlayer)player.getMCEntity());
    }

    @Override
    public int getDaytime() {
        return this.daytime.ordinal();
    }

    @Override
    public void setDaytime(int type) {
        this.daytime = EnumDayTime.values()[MathHelper.func_76125_a((int)type, (int)0, (int)2)];
    }

    @Override
    public int getMinPlayerLevel() {
        return this.minPlayerLevel;
    }

    @Override
    public void setMinPlayerLevel(int level) {
        this.minPlayerLevel = level;
    }

    @Override
    public int getDialog(int i) {
        if (i < 0 || i > 3) {
            throw new CustomNPCsException(i + " isnt between 0 and 3", new Object[0]);
        }
        if (i == 0) {
            return this.dialogId;
        }
        if (i == 1) {
            return this.dialog2Id;
        }
        return i == 2 ? this.dialog3Id : this.dialog4Id;
    }

    @Override
    public void setDialog(int i, int id, int type) {
        if (i < 0 || i > 3) {
            throw new CustomNPCsException(i + " isnt between 0 and 3", new Object[0]);
        }
        EnumAvailabilityDialog e = EnumAvailabilityDialog.values()[MathHelper.func_76125_a((int)type, (int)0, (int)2)];
        if (i == 0) {
            this.dialogId = id;
            this.dialogAvailable = e;
        } else if (i == 1) {
            this.dialog2Id = id;
            this.dialog2Available = e;
        } else if (i == 2) {
            this.dialog3Id = id;
            this.dialog3Available = e;
        } else if (i == 3) {
            this.dialog4Id = id;
            this.dialog4Available = e;
        }
    }

    @Override
    public void removeDialog(int i) {
        if (i < 0 || i > 3) {
            throw new CustomNPCsException(i + " isnt between 0 and 3", new Object[0]);
        }
        if (i == 0) {
            this.dialogId = -1;
            this.dialogAvailable = EnumAvailabilityDialog.Always;
        } else if (i == 1) {
            this.dialog2Id = -1;
            this.dialog2Available = EnumAvailabilityDialog.Always;
        } else if (i == 2) {
            this.dialog3Id = -1;
            this.dialog3Available = EnumAvailabilityDialog.Always;
        } else if (i == 3) {
            this.dialog4Id = -1;
            this.dialog4Available = EnumAvailabilityDialog.Always;
        }
    }

    @Override
    public int getQuest(int i) {
        if (i < 0 || i > 3) {
            throw new CustomNPCsException(i + " isnt between 0 and 3", new Object[0]);
        }
        if (i == 0) {
            return this.questId;
        }
        if (i == 1) {
            return this.quest2Id;
        }
        return i == 2 ? this.quest3Id : this.quest4Id;
    }

    @Override
    public void setQuest(int i, int id, int type) {
        if (i < 0 || i > 3) {
            throw new CustomNPCsException(i + " isnt between 0 and 3", new Object[0]);
        }
        EnumAvailabilityQuest e = EnumAvailabilityQuest.values()[MathHelper.func_76125_a((int)type, (int)0, (int)5)];
        if (i == 0) {
            this.questId = id;
            this.questAvailable = e;
        } else if (i == 1) {
            this.quest2Id = id;
            this.quest2Available = e;
        } else if (i == 2) {
            this.quest3Id = id;
            this.quest3Available = e;
        } else if (i == 3) {
            this.quest4Id = id;
            this.quest4Available = e;
        }
    }

    @Override
    public void removeQuest(int i) {
        if (i < 0 || i > 3) {
            throw new CustomNPCsException(i + " isnt between 0 and 3", new Object[0]);
        }
        if (i == 0) {
            this.questId = -1;
            this.questAvailable = EnumAvailabilityQuest.Always;
        } else if (i == 1) {
            this.quest2Id = -1;
            this.quest2Available = EnumAvailabilityQuest.Always;
        } else if (i == 2) {
            this.quest3Id = -1;
            this.quest3Available = EnumAvailabilityQuest.Always;
        } else if (i == 3) {
            this.quest4Id = -1;
            this.quest4Available = EnumAvailabilityQuest.Always;
        }
    }

    @Override
    public void setFaction(int i, int id, int type, int stance) {
        if (i < 0 || i > 1) {
            throw new CustomNPCsException(i + " isnt between 0 and 1", new Object[0]);
        }
        EnumAvailabilityFactionType e = EnumAvailabilityFactionType.values()[MathHelper.func_76125_a((int)type, (int)0, (int)2)];
        EnumAvailabilityFaction ee = EnumAvailabilityFaction.values()[MathHelper.func_76125_a((int)stance, (int)0, (int)2)];
        if (i == 0) {
            this.factionId = id;
            this.factionAvailable = e;
            this.factionStance = ee;
        } else if (i == 1) {
            this.faction2Id = id;
            this.faction2Available = e;
            this.faction2Stance = ee;
        }
    }

    @Override
    public void removeFaction(int i) {
        if (i < 0 || i > 1) {
            throw new CustomNPCsException(i + " isnt between 0 and 1", new Object[0]);
        }
        if (i == 0) {
            this.factionId = -1;
            this.factionAvailable = EnumAvailabilityFactionType.Always;
            this.factionStance = EnumAvailabilityFaction.Friendly;
        } else if (i == 1) {
            this.faction2Id = -1;
            this.faction2Available = EnumAvailabilityFactionType.Always;
            this.faction2Stance = EnumAvailabilityFaction.Friendly;
        }
    }
}

