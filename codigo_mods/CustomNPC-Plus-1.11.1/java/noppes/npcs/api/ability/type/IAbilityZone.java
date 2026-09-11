/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityZone
extends IAbility {
    public int getDurationTicks();

    public void setDurationTicks(int var1);

    public int getZoneShapeOrdinal();

    public void setZoneShapeOrdinal(int var1);

    public float getSpawnRadius();

    public void setSpawnRadius(float var1);

    public int getZoneCount();

    public void setZoneCount(int var1);

    public float getZoneHeight();

    public void setZoneHeight(float var1);

    public float getParticleDensity();

    public void setParticleDensity(float var1);

    public float getParticleScale();

    public void setParticleScale(float var1);

    public float getAnimSpeed();

    public void setAnimSpeed(float var1);

    public float getLightningDensity();

    public void setLightningDensity(float var1);

    public int getInnerColor();

    public void setInnerColor(int var1);

    public int getOuterColor();

    public void setOuterColor(int var1);

    public boolean isOuterColorEnabled();

    public void setOuterColorEnabled(boolean var1);

    public boolean isGroundFill();

    public void setGroundFill(boolean var1);

    public float getGroundAlpha();

    public void setGroundAlpha(float var1);

    public boolean isRings();

    public void setRings(boolean var1);

    public int getRingCount();

    public void setRingCount(int var1);

    public boolean isBorder();

    public void setBorder(boolean var1);

    public float getBorderSpeed();

    public void setBorderSpeed(float var1);

    public boolean isAccents();

    public void setAccents(boolean var1);

    public int getAccentStyle();

    public void setAccentStyle(int var1);

    public boolean isLightning();

    public void setLightning(boolean var1);

    public boolean isParticles();

    public void setParticles(boolean var1);

    public int getParticleMotion();

    public void setParticleMotion(int var1);

    public String getParticleDir();

    public void setParticleDir(String var1);

    public int getParticleSize();

    public void setParticleSize(int var1);

    public boolean isParticleGlow();

    public void setParticleGlow(boolean var1);
}

