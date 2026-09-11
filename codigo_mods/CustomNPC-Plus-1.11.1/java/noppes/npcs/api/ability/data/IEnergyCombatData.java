/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.data;

public interface IEnergyCombatData {
    public float getDamage();

    public void setDamage(float var1);

    public float getKnockback();

    public void setKnockback(float var1);

    public float getKnockbackUp();

    public void setKnockbackUp(float var1);

    public boolean isExplosive();

    public void setExplosive(boolean var1);

    public float getExplosionRadius();

    public void setExplosionRadius(float var1);

    public float getExplosionDamageFalloff();

    public void setExplosionDamageFalloff(float var1);
}

