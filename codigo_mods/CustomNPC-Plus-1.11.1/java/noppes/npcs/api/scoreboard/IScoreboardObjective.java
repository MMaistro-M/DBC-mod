/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.scoreboard;

public interface IScoreboardObjective {
    public String getName();

    public String getDisplayName();

    public void setDisplayName(String var1);

    public String getCriteria();

    public boolean isReadyOnly();
}

