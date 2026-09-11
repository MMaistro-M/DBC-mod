/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

public interface IPartyOptions {
    public boolean isAllowParty();

    public void setAllowParty(boolean var1);

    public boolean isOnlyParty();

    public void setOnlyParty(boolean var1);

    public int getPartyRequirements();

    public void setPartyRequirements(int var1);

    public int getRewardControl();

    public void setRewardControl(int var1);

    public int getCompleteFor();

    public void setCompleteFor(int var1);

    public int getExecuteCommandFor();

    public void setExecuteCommandFor(int var1);

    public int getObjectiveRequirement();

    public void setObjectiveRequirement(int var1);

    public int getMinPartySize();

    public void setMinPartySize(int var1);

    public int getMaxPartySize();

    public void setMaxPartySize(int var1);
}

