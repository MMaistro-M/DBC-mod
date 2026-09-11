/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityHeavyHit
extends IAbility {
    public float getDamage();

    public void setDamage(float var1);

    public float getKnockback();

    public void setKnockback(float var1);

    public float getHitLength();

    public void setHitLength(float var1);

    public float getHitWidth();

    public void setHitWidth(float var1);
}

