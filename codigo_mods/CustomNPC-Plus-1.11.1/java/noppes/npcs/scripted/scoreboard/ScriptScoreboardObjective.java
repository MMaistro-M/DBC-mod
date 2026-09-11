/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.scoreboard.ScoreObjective
 */
package noppes.npcs.scripted.scoreboard;

import net.minecraft.scoreboard.ScoreObjective;
import noppes.npcs.api.scoreboard.IScoreboardObjective;

public class ScriptScoreboardObjective
implements IScoreboardObjective {
    private ScoreObjective objective;

    public ScriptScoreboardObjective(ScoreObjective objective) {
        this.objective = objective;
    }

    @Override
    public String getName() {
        return this.objective.func_96679_b();
    }

    @Override
    public String getDisplayName() {
        return this.objective.func_96678_d();
    }

    @Override
    public void setDisplayName(String name) {
        if (name.length() > 0 && name.length() <= 32) {
            this.objective.func_96681_a(name);
        }
    }

    @Override
    public String getCriteria() {
        return this.objective.func_96680_c().func_96636_a();
    }

    @Override
    public boolean isReadyOnly() {
        return this.objective.func_96680_c().func_96637_b();
    }
}

