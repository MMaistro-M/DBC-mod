/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.scoreboard.Scoreboard
 *  net.minecraft.util.EnumChatFormatting
 */
package noppes.npcs.scripted.scoreboard;

import java.util.ArrayList;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.scoreboard.IScoreboardTeam;

public class ScriptScoreboardTeam
implements IScoreboardTeam {
    private ScorePlayerTeam team;
    private Scoreboard board;

    public ScriptScoreboardTeam(ScorePlayerTeam team, Scoreboard board) {
        this.team = team;
        this.board = board;
    }

    @Override
    public String getName() {
        return this.team.func_96661_b();
    }

    @Override
    public String getDisplayName() {
        return this.team.func_96669_c();
    }

    @Override
    public void setDisplayName(String name) {
        if (name.length() > 0 && name.length() <= 32) {
            this.team.func_96664_a(name);
        }
    }

    @Override
    public void addPlayer(String player) {
        this.board.func_151392_a(player, this.getName());
    }

    @Override
    public void addPlayer(IPlayer player) {
        this.addPlayer(player.getName());
    }

    @Override
    public void removePlayer(String player) {
        this.board.func_96512_b(player, this.team);
    }

    @Override
    public void removePlayer(IPlayer player) {
        this.removePlayer(player.getName());
    }

    @Override
    public String[] getPlayers() {
        ArrayList list = new ArrayList(this.team.func_96670_d());
        return list.toArray(new String[list.size()]);
    }

    @Override
    public int getTeamsize() {
        return this.team.func_96670_d().size();
    }

    @Override
    public void clearPlayers() {
        ArrayList list = new ArrayList(this.team.func_96670_d());
        for (String player : list) {
            this.board.func_96512_b(player, this.team);
        }
    }

    @Override
    public boolean getFriendlyFire() {
        return this.team.func_96665_g();
    }

    @Override
    public void setFriendlyFire(boolean bo) {
        this.team.func_96660_a(bo);
    }

    @Override
    public void setColor(String color) {
        EnumChatFormatting enumchatformatting = EnumChatFormatting.func_96300_b((String)color);
        if (enumchatformatting == null || enumchatformatting.func_96301_b()) {
            return;
        }
        this.team.func_96666_b(enumchatformatting.toString());
        this.team.func_96662_c(EnumChatFormatting.RESET.toString());
    }

    @Override
    public String getColor() {
        String prefix = this.team.func_96668_e();
        if (prefix == null || prefix.isEmpty()) {
            return null;
        }
        for (EnumChatFormatting format : EnumChatFormatting.values()) {
            if (!prefix.equals(format.toString()) || format == EnumChatFormatting.RESET) continue;
            return format.func_96297_d();
        }
        return null;
    }

    @Override
    public void setSeeInvisibleTeamPlayers(boolean bo) {
        this.team.func_98300_b(bo);
    }

    @Override
    public boolean getSeeInvisibleTeamPlayers() {
        return this.team.func_98297_h();
    }
}

