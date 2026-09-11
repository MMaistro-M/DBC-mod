/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.roles;

import noppes.npcs.api.jobs.IJobGuard;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobGuard;
import noppes.npcs.scripted.roles.ScriptJobInterface;

public class ScriptJobGuard
extends ScriptJobInterface
implements IJobGuard {
    private JobGuard job;

    public ScriptJobGuard(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobGuard)npc.jobInterface;
    }

    @Override
    public boolean attackCreepers() {
        return this.job.attackCreepers;
    }

    @Override
    public void attackCreepers(boolean value) {
        this.job.attackCreepers = value;
    }

    @Override
    public boolean attacksAnimals() {
        return this.job.attacksAnimals;
    }

    @Override
    public void attacksAnimals(boolean value) {
        this.job.attacksAnimals = value;
    }

    @Override
    public boolean attackHostileMobs() {
        return this.job.attackHostileMobs;
    }

    @Override
    public void attackHostileMobs(boolean value) {
        this.job.attackHostileMobs = value;
    }

    @Override
    public int getType() {
        return 3;
    }
}

