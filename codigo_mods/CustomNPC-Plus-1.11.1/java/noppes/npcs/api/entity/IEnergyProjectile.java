/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyAbility;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;

public interface IEnergyProjectile<T extends Entity>
extends IEnergyAbility<T> {
    public void setOwner(IEntity var1);

    public int getTargetEntityId();

    public IEntity getTarget();

    public void setTarget(IEntity var1);

    public float getSize();

    public void setSize(float var1);

    public float getRotationSpeed();

    public void setRotationSpeed(float var1);

    public float getInterpolatedRotationX(float var1);

    public float getInterpolatedRotationY(float var1);

    public float getInterpolatedRotationZ(float var1);

    public float getInterpolatedSize(float var1);

    public float getMaxDistance();

    public void setMaxDistance(float var1);

    public int getMaxLifetime();

    public void setMaxLifetime(int var1);

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

    public float getSpeed();

    public void setSpeed(float var1);

    public boolean isHoming();

    public void setHoming(boolean var1);

    public float getHomingStrength();

    public void setHomingStrength(float var1);

    public float getHomingRange();

    public void setHomingRange(float var1);

    public int getAnchor();

    public float getAnchorOffsetX();

    public float getAnchorOffsetY();

    public float getAnchorOffsetZ();

    public double getStartX();

    public double getStartY();

    public double getStartZ();

    public boolean hasHit();

    public int getEnergyType();

    public void syncClient();

    public void fireAt(IEntity var1);

    public void fireAt(double var1, double var3, double var5);

    public void fireDirection(float var1, float var2);

    public void fireFrom(IEntityLivingBase var1);

    public void fireFrom(IEntityLivingBase var1, IEntity var2);
}

