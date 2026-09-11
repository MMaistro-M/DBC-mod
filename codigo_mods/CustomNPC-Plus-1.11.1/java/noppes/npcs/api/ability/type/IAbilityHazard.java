/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.type.IAbilityZone;

public interface IAbilityHazard
extends IAbilityZone {
    public float getRadius();

    public void setRadius(float var1);

    public float getDamagePerSecond();

    public void setDamagePerSecond(float var1);

    public int getDamageInterval();

    public void setDamageInterval(int var1);

    public boolean isAffectsCaster();

    public void setAffectsCaster(boolean var1);
}

