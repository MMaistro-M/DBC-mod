/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.scoreboard;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.scoreboard.IScoreboardObjective;
import noppes.npcs.api.scoreboard.IScoreboardTeam;

public interface IScoreboard {
    public IScoreboardObjective[] getObjectives();

    public IScoreboardObjective getObjective(String var1);

    public boolean hasObjective(String var1);

    public void removeObjective(String var1);

    public IScoreboardObjective addObjective(String var1, String var2);

    public void setPlayerScore(String var1, String var2, int var3, String var4);

    public void setPlayerScore(IPlayer var1, String var2, int var3, String var4);

    public int getPlayerScore(String var1, String var2, String var3);

    public int getPlayerScore(IPlayer var1, String var2, String var3);

    public boolean hasPlayerObjective(String var1, String var2, String var3);

    public boolean hasPlayerObjective(IPlayer var1, String var2, String var3);

    public void deletePlayerScore(String var1, String var2, String var3);

    public void deletePlayerScore(IPlayer var1, String var2, String var3);

    public IScoreboardTeam[] getTeams();

    public IScoreboardTeam getTeamByName(String var1);

    public boolean hasTeam(String var1);

    public IScoreboardTeam addTeam(String var1);

    public IScoreboardTeam getTeam(String var1);

    public void removeTeam(String var1);
}

