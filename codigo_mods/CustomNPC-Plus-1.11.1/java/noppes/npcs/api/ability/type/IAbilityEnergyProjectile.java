/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityEnergyProjectile
extends IAbility {
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

    public int getHitType();

    public void setHitType(int var1);

    public int getMultiHitDelayTicks();

    public void setMultiHitDelayTicks(int var1);

    public int getMaxHits();

    public void setMaxHits(int var1);

    public float getMaxDistance();

    public void setMaxDistance(float var1);

    public int getMaxLifetime();

    public void setMaxLifetime(int var1);

    public int getInnerColor();

    public void setInnerColor(int var1);

    public int getOuterColor();

    public void setOuterColor(int var1);

    public boolean isOuterColorEnabled();

    public void setOuterColorEnabled(boolean var1);

    public float getOuterColorWidth();

    public void setOuterColorWidth(float var1);

    public float getOuterColorAlpha();

    public void setOuterColorAlpha(float var1);

    public boolean hasLightningEffect();

    public void setLightningEffect(boolean var1);

    public float getLightningDensity();

    public void setLightningDensity(float var1);

    public float getLightningRadius();

    public void setLightningRadius(float var1);

    public int getAnchorPoint();

    public void setAnchorPoint(int var1);

    public boolean getLaunchFromAnchor();

    public void setLaunchFromAnchor(boolean var1);
}

