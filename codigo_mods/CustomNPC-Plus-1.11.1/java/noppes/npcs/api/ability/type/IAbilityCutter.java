/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityCutter
extends IAbility {
    public float getArcAngle();

    public void setArcAngle(float var1);

    public float getRange();

    public void setRange(float var1);

    public float getDamage();

    public void setDamage(float var1);

    public float getKnockback();

    public void setKnockback(float var1);

    public int getSweepMode();

    public void setSweepMode(int var1);

    public float getSweepSpeed();

    public void setSweepSpeed(float var1);

    public int getSpinDurationTicks();

    public void setSpinDurationTicks(int var1);

    public boolean isPiercing();

    public void setPiercing(boolean var1);

    public float getInnerRadius();

    public void setInnerRadius(float var1);
}

