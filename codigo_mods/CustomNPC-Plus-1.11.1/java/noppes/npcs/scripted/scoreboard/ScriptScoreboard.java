/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.scoreboard.IScoreObjectiveCriteria
 *  net.minecraft.scoreboard.Score
 *  net.minecraft.scoreboard.ScoreObjective
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.scoreboard.Scoreboard
 *  net.minecraft.server.MinecraftServer
 */
package noppes.npcs.scripted.scoreboard;

import java.util.ArrayList;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.scoreboard.IScoreboard;
import noppes.npcs.api.scoreboard.IScoreboardObjective;
import noppes.npcs.api.scoreboard.IScoreboardTeam;
import noppes.npcs.scripted.scoreboard.ScriptScoreboardObjective;
import noppes.npcs.scripted.scoreboard.ScriptScoreboardTeam;

public class ScriptScoreboard
implements IScoreboard {
    private Scoreboard board = MinecraftServer.func_71276_C().func_71218_a(0).func_96441_U();

    @Override
    public IScoreboardObjective[] getObjectives() {
        ArrayList collection = new ArrayList(this.board.func_96514_c());
        IScoreboardObjective[] objectives = new IScoreboardObjective[collection.size()];
        for (int i = 0; i < collection.size(); ++i) {
            objectives[i] = new ScriptScoreboardObjective((ScoreObjective)collection.get(i));
        }
        return objectives;
    }

    @Override
    public IScoreboardObjective getObjective(String name) {
        ScoreObjective obj = this.board.func_96518_b(name);
        if (obj == null) {
            return null;
        }
        return new ScriptScoreboardObjective(obj);
    }

    @Override
    public boolean hasObjective(String objective) {
        return this.board.func_96518_b(objective) != null;
    }

    @Override
    public void removeObjective(String objective) {
        ScoreObjective obj = this.board.func_96518_b(objective);
        if (obj != null) {
            this.board.func_96519_k(obj);
        }
    }

    @Override
    public IScoreboardObjective addObjective(String objective, String criteria) {
        if (this.hasObjective(objective)) {
            return null;
        }
        IScoreObjectiveCriteria icriteria = (IScoreObjectiveCriteria)IScoreObjectiveCriteria.field_96643_a.get(criteria);
        if (icriteria == null) {
            return null;
        }
        if (objective.length() == 0 || objective.length() > 16) {
            return null;
        }
        ScoreObjective obj = this.board.func_96535_a(objective, icriteria);
        return new ScriptScoreboardObjective(obj);
    }

    @Override
    public void setPlayerScore(String player, String objective, int score, String datatag) {
        ScoreObjective objec = this.board.func_96518_b(objective);
        if (objec == null || objec.func_96680_c().func_96637_b() || score < Integer.MIN_VALUE || score > Integer.MAX_VALUE) {
            return;
        }
        Score sco = this.board.func_96529_a(player, objec);
        sco.func_96647_c(score);
    }

    @Override
    public void setPlayerScore(IPlayer player, String objective, int score, String datatag) {
        this.setPlayerScore(player.getName(), objective, score, datatag);
    }

    @Override
    public int getPlayerScore(String player, String objective, String datatag) {
        ScoreObjective objec = this.board.func_96518_b(objective);
        if (objec == null || objec.func_96680_c().func_96637_b()) {
            return 0;
        }
        return this.board.func_96529_a(player, objec).func_96652_c();
    }

    @Override
    public int getPlayerScore(IPlayer player, String objective, String datatag) {
        return this.getPlayerScore(player.getName(), objective, datatag);
    }

    @Override
    public boolean hasPlayerObjective(String player, String objective, String datatag) {
        ScoreObjective objec = this.board.func_96518_b(objective);
        if (objec == null) {
            return false;
        }
        return this.board.func_96510_d(player).get(objec) != null;
    }

    @Override
    public boolean hasPlayerObjective(IPlayer player, String objective, String datatag) {
        return this.hasPlayerObjective(player.getName(), objective, datatag);
    }

    @Override
    public void deletePlayerScore(String player, String objective, String datatag) {
        ScoreObjective objec = this.board.func_96518_b(objective);
        if (objec == null) {
            return;
        }
        if (this.board.func_96510_d(player).remove(objec) != null) {
            this.board.func_96516_a(player);
        }
    }

    @Override
    public void deletePlayerScore(IPlayer player, String objective, String datatag) {
        this.deletePlayerScore(player.getName(), objective, datatag);
    }

    @Override
    public IScoreboardTeam[] getTeams() {
        ArrayList list = new ArrayList(this.board.func_96525_g());
        IScoreboardTeam[] teams = new IScoreboardTeam[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            teams[i] = new ScriptScoreboardTeam((ScorePlayerTeam)list.get(i), this.board);
        }
        return teams;
    }

    @Override
    public IScoreboardTeam getTeamByName(String name) {
        IScoreboardTeam[] teams = this.getTeams();
        for (int i = 0; i < teams.length; ++i) {
            if (!teams[i].getName().equals(name)) continue;
            return teams[i];
        }
        return null;
    }

    @Override
    public boolean hasTeam(String name) {
        return this.board.func_96509_i(name) != null;
    }

    @Override
    public IScoreboardTeam addTeam(String name) {
        if (this.hasTeam(name)) {
            return null;
        }
        return new ScriptScoreboardTeam(this.board.func_96527_f(name), this.board);
    }

    @Override
    public IScoreboardTeam getTeam(String name) {
        ScorePlayerTeam team = this.board.func_96509_i(name);
        if (team == null) {
            return null;
        }
        return new ScriptScoreboardTeam(team, this.board);
    }

    @Override
    public void removeTeam(String name) {
        ScorePlayerTeam team = this.board.func_96509_i(name);
        if (team != null) {
            this.board.func_96511_d(team);
        }
    }
}

