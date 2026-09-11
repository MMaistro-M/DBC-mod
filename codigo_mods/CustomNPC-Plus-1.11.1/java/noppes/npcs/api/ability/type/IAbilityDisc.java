/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.type.IAbilityEnergyProjectile;

public interface IAbilityDisc
extends IAbilityEnergyProjectile {
    public float getSpeed();

    public void setSpeed(float var1);

    public float getDiscRadius();

    public void setDiscRadius(float var1);

    public float getDiscThickness();

    public void setDiscThickness(float var1);

    public boolean isVertical();

    public void setVertical(boolean var1);

    public boolean isBoomerang();

    public void setBoomerang(boolean var1);

    public int getBoomerangDelay();

    public void setBoomerangDelay(int var1);

    public boolean isHoming();

    public void setHoming(boolean var1);

    public float getHomingStrength();

    public void setHomingStrength(float var1);

    public float getHomingRange();

    public void setHomingRange(float var1);

    public float getRotationSpeed();

    public void setRotationSpeed(float var1);

    public int getProjectileCount();

    public void setProjectileCount(int var1);

    public int getFireDelay();

    public void setFireDelay(int var1);

    public int getInnerColor(int var1);

    public void setInnerColor(int var1, int var2);

    public int getOuterColor(int var1);

    public void setOuterColor(int var1, int var2);

    public int getAnchorPoint(int var1);

    public void setAnchorPoint(int var1, int var2);
}

