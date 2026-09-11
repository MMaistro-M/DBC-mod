/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import java.util.Vector;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.handler.data.IPartyOptions;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.constants.EnumPartyExchange;
import noppes.npcs.constants.EnumPartyObjectives;
import noppes.npcs.constants.EnumPartyRequirements;

public class PartyOptions
implements IPartyOptions {
    public boolean allowParty = false;
    public boolean onlyParty = false;
    public EnumPartyRequirements partyRequirements = EnumPartyRequirements.Leader;
    public EnumPartyExchange rewardControl = EnumPartyExchange.Leader;
    public EnumPartyExchange completeFor = EnumPartyExchange.Leader;
    public EnumPartyExchange executeCommand = EnumPartyExchange.Leader;
    public EnumPartyObjectives objectiveRequirement = EnumPartyObjectives.Shared;
    public int minPartySize = ConfigMain.DefaultMinPartySize;
    public int maxPartySize = ConfigMain.DefaultMaxPartySize;

    public void readFromNBT(NBTTagCompound compound) {
        this.allowParty = compound.func_74767_n("AllowParty");
        if (this.allowParty) {
            this.onlyParty = compound.func_74767_n("OnlyParty");
            this.partyRequirements = EnumPartyRequirements.values()[compound.func_74762_e("PartyRequirements")];
            this.rewardControl = EnumPartyExchange.values()[compound.func_74762_e("RewardControl")];
            this.completeFor = EnumPartyExchange.values()[compound.func_74762_e("CompleteFor")];
            this.executeCommand = EnumPartyExchange.values()[compound.func_74762_e("ExecuteCommand")];
            this.minPartySize = compound.func_74762_e("MinPartySize");
            this.maxPartySize = compound.func_74762_e("MaxPartySize");
            this.objectiveRequirement = EnumPartyObjectives.values()[compound.func_74762_e("ObjectiveRequirement")];
        } else {
            if (compound.func_74764_b("OnlyParty")) {
                compound.func_82580_o("OnlyParty");
            }
            if (compound.func_74764_b("PartyRequirements")) {
                compound.func_82580_o("PartyRequirements");
            }
            if (compound.func_74764_b("RewardControl")) {
                compound.func_82580_o("RewardControl");
            }
            if (compound.func_74764_b("CompleteFor")) {
                compound.func_82580_o("CompleteFor");
            }
            if (compound.func_74764_b("ExecuteCommand")) {
                compound.func_82580_o("ExecuteCommand");
            }
            if (compound.func_74764_b("MinPartySize")) {
                compound.func_82580_o("MinPartySize");
            }
            if (compound.func_74764_b("MaxPartySize")) {
                compound.func_82580_o("MaxPartySize");
            }
            if (compound.func_74764_b("ObjectiveRequirement")) {
                compound.func_82580_o("ObjectiveRequirement");
            }
            this.onlyParty = false;
            this.partyRequirements = EnumPartyRequirements.Leader;
            this.rewardControl = EnumPartyExchange.Leader;
            this.completeFor = EnumPartyExchange.Leader;
            this.executeCommand = EnumPartyExchange.Leader;
            this.minPartySize = ConfigMain.DefaultMinPartySize;
            this.maxPartySize = ConfigMain.DefaultMaxPartySize;
            this.objectiveRequirement = EnumPartyObjectives.Shared;
        }
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("AllowParty", this.allowParty);
        if (this.allowParty) {
            compound.func_74757_a("OnlyParty", this.onlyParty);
            compound.func_74768_a("PartyRequirements", this.partyRequirements.ordinal());
            compound.func_74768_a("RewardControl", this.rewardControl.ordinal());
            compound.func_74768_a("CompleteFor", this.completeFor.ordinal());
            compound.func_74768_a("ExecuteCommand", this.executeCommand.ordinal());
            compound.func_74768_a("MinPartySize", this.minPartySize);
            compound.func_74768_a("MaxPartySize", this.maxPartySize);
            compound.func_74768_a("ObjectiveRequirement", this.objectiveRequirement.ordinal());
        } else {
            if (compound.func_74764_b("OnlyParty")) {
                compound.func_82580_o("OnlyParty");
            }
            if (compound.func_74764_b("PartyRequirements")) {
                compound.func_82580_o("PartyRequirements");
            }
            if (compound.func_74764_b("RewardControl")) {
                compound.func_82580_o("RewardControl");
            }
            if (compound.func_74764_b("CompleteFor")) {
                compound.func_82580_o("CompleteFor");
            }
            if (compound.func_74764_b("ExecuteCommand")) {
                compound.func_82580_o("ExecuteCommand");
            }
            if (compound.func_74764_b("MinPartySize")) {
                compound.func_82580_o("MinPartySize");
            }
            if (compound.func_74764_b("MaxPartySize")) {
                compound.func_82580_o("MaxPartySize");
            }
            if (compound.func_74764_b("ObjectiveRequirement")) {
                compound.func_82580_o("ObjectiveRequirement");
            }
        }
        return compound;
    }

    @Override
    public boolean isAllowParty() {
        return this.allowParty;
    }

    @Override
    public void setAllowParty(boolean allowParty) {
        this.allowParty = allowParty;
    }

    @Override
    public boolean isOnlyParty() {
        return this.onlyParty;
    }

    @Override
    public void setOnlyParty(boolean onlyParty) {
        this.onlyParty = onlyParty;
    }

    @Override
    public int getPartyRequirements() {
        return this.partyRequirements.ordinal();
    }

    @Override
    public void setPartyRequirements(int partyReq) {
        if (partyReq < 0 || partyReq >= EnumPartyRequirements.values().length) {
            return;
        }
        this.partyRequirements = EnumPartyRequirements.values()[partyReq];
    }

    @Override
    public int getRewardControl() {
        return this.rewardControl.ordinal();
    }

    @Override
    public void setRewardControl(int rewardCon) {
        if (rewardCon < 0 || rewardCon >= EnumPartyExchange.values().length) {
            return;
        }
        this.rewardControl = EnumPartyExchange.values()[rewardCon];
    }

    @Override
    public int getCompleteFor() {
        return this.completeFor.ordinal();
    }

    @Override
    public void setCompleteFor(int compFor) {
        if (compFor < 0 || compFor >= EnumPartyExchange.values().length) {
            return;
        }
        this.completeFor = EnumPartyExchange.values()[compFor];
    }

    @Override
    public int getExecuteCommandFor() {
        return this.executeCommand.ordinal();
    }

    @Override
    public void setExecuteCommandFor(int commandFor) {
        if (commandFor < 0 || commandFor >= EnumPartyExchange.values().length) {
            return;
        }
        this.executeCommand = EnumPartyExchange.values()[commandFor];
    }

    @Override
    public int getObjectiveRequirement() {
        return this.objectiveRequirement.ordinal();
    }

    @Override
    public void setObjectiveRequirement(int requirement) {
        if (requirement < 0 || requirement >= EnumPartyObjectives.values().length) {
            return;
        }
        this.objectiveRequirement = EnumPartyObjectives.values()[requirement];
    }

    @Override
    public int getMinPartySize() {
        return this.minPartySize;
    }

    @Override
    public void setMinPartySize(int newSize) {
        if (newSize < 1) {
            newSize = 1;
        }
        if (newSize > this.maxPartySize) {
            newSize = this.maxPartySize;
        }
        this.minPartySize = newSize;
    }

    @Override
    public int getMaxPartySize() {
        return this.maxPartySize;
    }

    @Override
    public void setMaxPartySize(int newSize) {
        if (newSize < this.minPartySize) {
            newSize = this.minPartySize;
        }
        this.maxPartySize = newSize;
    }

    public Vector<String> getPartyOptionsList() {
        Vector<String> vec = new Vector<String>();
        if (this.onlyParty) {
            vec.add("party.only:gui.yes");
        }
        vec.add("party.partyRequirements:" + this.partyRequirements.name);
        vec.add("quest.objectives:" + this.objectiveRequirement.name);
        vec.add("party.completeFor:" + this.completeFor.name);
        vec.add("quest.reward:" + this.rewardControl.name);
        vec.add("party.minPartySize:" + this.minPartySize);
        vec.add("party.maxPartySize:" + this.maxPartySize);
        return vec;
    }
}

