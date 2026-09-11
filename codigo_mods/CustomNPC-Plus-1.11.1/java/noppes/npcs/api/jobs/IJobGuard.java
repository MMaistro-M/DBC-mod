/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.jobs;

import noppes.npcs.api.jobs.IJob;

public interface IJobGuard
extends IJob {
    public boolean attackCreepers();

    public void attackCreepers(boolean var1);

    public boolean attacksAnimals();

    public void attacksAnimals(boolean var1);

    public boolean attackHostileMobs();

    public void attackHostileMobs(boolean var1);
}

