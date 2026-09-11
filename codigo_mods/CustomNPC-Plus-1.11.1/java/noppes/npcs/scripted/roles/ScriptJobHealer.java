/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.scripted.roles;

import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.jobs.IJobHealer;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.JobHealer;
import noppes.npcs.scripted.roles.ScriptJobInterface;

public class ScriptJobHealer
extends ScriptJobInterface
implements IJobHealer {
    private JobHealer job;

    public ScriptJobHealer(EntityNPCInterface npc) {
        super(npc);
        this.job = (JobHealer)npc.jobInterface;
    }

    @Override
    public void heal(IEntityLivingBase entity, float amount) {
        this.job.heal((EntityLivingBase)entity.getMCEntity(), amount);
    }

    @Override
    public void setRange(int range) {
        this.job.range = range;
    }

    @Override
    public int getRange() {
        return this.job.range;
    }

    @Override
    public void setSpeed(int speed) {
        this.job.speed = speed;
    }

    @Override
    public int getSpeed() {
        return this.job.speed;
    }

    @Override
    public int getType() {
        return 2;
    }
}

