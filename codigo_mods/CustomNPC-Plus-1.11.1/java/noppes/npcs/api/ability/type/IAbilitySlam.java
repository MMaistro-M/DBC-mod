/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilitySlam
extends IAbility {
    public float getDamage();

    public void setDamage(float var1);

    public float getRadius();

    public void setRadius(float var1);

    public float getKnockbackStrength();

    public void setKnockbackStrength(float var1);

    public float getLeapSpeed();

    public void setLeapSpeed(float var1);

    public float getLeapHeight();

    public void setLeapHeight(float var1);
}

