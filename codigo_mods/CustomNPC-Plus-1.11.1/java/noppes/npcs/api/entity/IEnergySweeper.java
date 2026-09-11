/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyAbility;

public interface IEnergySweeper<T extends Entity>
extends IEnergyAbility<T> {
    public float getBeamLength();

    public void setBeamLength(float var1);

    public float getBeamWidth();

    public void setBeamWidth(float var1);

    public float getBeamHeight();

    public void setBeamHeight(float var1);

    public float getSweepSpeed();

    public void setSweepSpeed(float var1);

    public int getNumberOfRotations();

    public void setNumberOfRotations(int var1);

    public boolean isLockOnTarget();

    public void setLockOnTarget(boolean var1);

    public float getDamage();

    public void setDamage(float var1);

    public int getDamageInterval();

    public void setDamageInterval(int var1);

    public boolean isPiercing();

    public void setPiercing(boolean var1);

    public void spawn();
}

