/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.jobs;

import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.jobs.IJob;

public interface IJobHealer
extends IJob {
    public void heal(IEntityLivingBase var1, float var2);

    public void setRange(int var1);

    public int getRange();

    public void setSpeed(int var1);

    public int getSpeed();
}

