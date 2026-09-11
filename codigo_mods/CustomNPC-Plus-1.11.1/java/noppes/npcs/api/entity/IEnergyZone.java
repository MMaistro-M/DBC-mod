/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.INbt;
import noppes.npcs.api.entity.IEntity;

public interface IEnergyZone<T extends Entity>
extends IEntity<T> {
    public int getZoneType();

    public int getZoneShape();

    public void setZoneShape(int var1);

    public float getRadius();

    public void setRadius(float var1);

    public float getZoneHeight();

    public void setZoneHeight(float var1);

    public int getDuration();

    public void setDuration(int var1);

    public int getInnerColor();

    public void setInnerColor(int var1);

    public int getOuterColor();

    public void setOuterColor(int var1);

    public boolean isOuterColorEnabled();

    public void setOuterColorEnabled(boolean var1);

    public float getParticleDensity();

    public void setParticleDensity(float var1);

    public float getParticleScale();

    public void setParticleScale(float var1);

    public float getAnimSpeed();

    public void setAnimSpeed(float var1);

    public boolean isIgnoreIFrames();

    public void setIgnoreIFrames(boolean var1);

    public float getDamagePerSecond();

    public void setDamagePerSecond(float var1);

    public int getDamageInterval();

    public void setDamageInterval(int var1);

    public boolean isAffectsCaster();

    public void setAffectsCaster(boolean var1);

    public float getTriggerRadius();

    public void setTriggerRadius(float var1);

    public int getArmTime();

    public void setArmTime(int var1);

    public int getMaxTriggers();

    public void setMaxTriggers(int var1);

    public int getTriggerCooldown();

    public void setTriggerCooldown(int var1);

    public float getDamage();

    public void setDamage(float var1);

    public float getKnockback();

    public void setKnockback(float var1);

    public boolean isVisible();

    public void setVisible(boolean var1);

    public INbt getDamageData();

    public void setDamageData(INbt var1);

    public void spawn();
}

