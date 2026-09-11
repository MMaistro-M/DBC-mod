/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityVortex
extends IAbility {
    public float getPullRadius();

    public void setPullRadius(float var1);

    public float getPullStrength();

    public void setPullStrength(float var1);

    public float getDamage();

    public void setDamage(float var1);

    public float getKnockback();

    public void setKnockback(float var1);

    public boolean isAoe();

    public void setAoe(boolean var1);

    public boolean isDamageOnPull();

    public void setDamageOnPull(boolean var1);

    public float getPullDamage();

    public void setPullDamage(float var1);
}

