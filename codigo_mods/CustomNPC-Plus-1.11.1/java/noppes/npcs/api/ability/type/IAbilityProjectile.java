/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityProjectile
extends IAbility {
    public float getDamage();

    public void setDamage(float var1);

    public float getSpeed();

    public void setSpeed(float var1);

    public float getKnockback();

    public void setKnockback(float var1);

    public String getProjectileType();

    public void setProjectileType(String var1);

    public boolean isExplosive();

    public void setExplosive(boolean var1);

    public float getExplosionRadius();

    public void setExplosionRadius(float var1);

    public boolean isHoming();

    public void setHoming(boolean var1);

    public float getHomingStrength();

    public void setHomingStrength(float var1);
}

