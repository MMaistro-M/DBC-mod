/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyAbility;

public interface IEnergyBarrier<T extends Entity>
extends IEnergyAbility<T> {
    public float getCurrentHealth();

    public void setCurrentHealth(float var1);

    public float getHealthPercent();

    public float getMaxHealth();

    public void setMaxHealth(float var1);

    public boolean isUseHealth();

    public void setUseHealth(boolean var1);

    public int getDuration();

    public void setDuration(int var1);

    public boolean isUseDuration();

    public void setUseDuration(boolean var1);

    public int getTicksAlive();

    public float getDefaultMultiplier();

    public void setDefaultMultiplier(float var1);

    public boolean isSolid();

    public void setSolid(boolean var1);

    public boolean isKnockbackEnabled();

    public void setKnockbackEnabled(boolean var1);

    public float getKnockbackStrength();

    public void setKnockbackStrength(float var1);

    public boolean isAbsorbing();

    public void setAbsorbing(boolean var1);

    public float getAbsorbRadius();

    public void setAbsorbRadius(float var1);

    public int getBarrierType();

    public void syncClient();
}

