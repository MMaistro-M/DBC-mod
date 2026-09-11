/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilitySweeper
extends IAbility {
    public float getBeamLength();

    public void setBeamLength(float var1);

    public float getBeamWidth();

    public void setBeamWidth(float var1);

    public float getBeamHeight();

    public void setBeamHeight(float var1);

    public float getDamage();

    public void setDamage(float var1);

    public int getDamageInterval();

    public void setDamageInterval(int var1);

    public boolean isPiercing();

    public void setPiercing(boolean var1);

    public float getSweepSpeed();

    public void setSweepSpeed(float var1);

    public int getNumberOfRotations();

    public void setNumberOfRotations(int var1);

    public boolean isLockOnTarget();

    public void setLockOnTarget(boolean var1);

    public int getInnerColor();

    public void setInnerColor(int var1);

    public int getOuterColor();

    public void setOuterColor(int var1);

    public float getOuterColorWidth();

    public void setOuterColorWidth(float var1);

    public boolean isOuterColorEnabled();

    public void setOuterColorEnabled(boolean var1);
}

