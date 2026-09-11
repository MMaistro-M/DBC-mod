/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityDash
extends IAbility {
    public int getDashMode();

    public void setDashMode(int var1);

    public float getDashDistance();

    public void setDashDistance(float var1);

    public float getDashSpeed();

    public void setDashSpeed(float var1);

    public float getDashAngle();

    public void setDashAngle(float var1);

    public int getDashDirection();

    public void setDashDirection(int var1);
}

