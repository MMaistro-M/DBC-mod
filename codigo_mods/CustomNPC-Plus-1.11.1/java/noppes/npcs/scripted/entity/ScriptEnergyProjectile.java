/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityEnergyProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.EventHooks;
import noppes.npcs.api.entity.IEnergyProjectile;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.scripted.entity.ScriptEnergyAbility;

public class ScriptEnergyProjectile<T extends EntityEnergyProjectile>
extends ScriptEnergyAbility<T>
implements IEnergyProjectile {
    public ScriptEnergyProjectile(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 13;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 13 || super.typeOf(type);
    }

    @Override
    public int getEnergyType() {
        return -1;
    }

    @Override
    public void setOwner(IEntity owner) {
        if (owner != null) {
            ((EntityEnergyProjectile)this.entity).setOwnerEntityId(owner.getMCEntity().func_145782_y());
        }
    }

    @Override
    public int getTargetEntityId() {
        return ((EntityEnergyProjectile)this.entity).getTargetEntityId();
    }

    @Override
    public IEntity getTarget() {
        return ((EntityEnergyProjectile)this.entity).getTarget();
    }

    @Override
    public void setTarget(IEntity target) {
        if (target != null) {
            ((EntityEnergyProjectile)this.entity).setTargetEntityId(target.getMCEntity().func_145782_y());
        } else {
            ((EntityEnergyProjectile)this.entity).setTargetEntityId(-1);
        }
    }

    @Override
    public float getSize() {
        return ((EntityEnergyProjectile)this.entity).getSize();
    }

    @Override
    public void setSize(float size) {
        ((EntityEnergyProjectile)this.entity).setProjectileSize(size);
    }

    @Override
    public float getRotationSpeed() {
        return ((EntityEnergyProjectile)this.entity).getRotationSpeed();
    }

    @Override
    public void setRotationSpeed(float speed) {
        ((EntityEnergyProjectile)this.entity).setRotationSpeed(speed);
    }

    @Override
    public float getInterpolatedRotationX(float partialTicks) {
        return ((EntityEnergyProjectile)this.entity).getInterpolatedRotationX(partialTicks);
    }

    @Override
    public float getInterpolatedRotationY(float partialTicks) {
        return ((EntityEnergyProjectile)this.entity).getInterpolatedRotationY(partialTicks);
    }

    @Override
    public float getInterpolatedRotationZ(float partialTicks) {
        return ((EntityEnergyProjectile)this.entity).getInterpolatedRotationZ(partialTicks);
    }

    @Override
    public float getInterpolatedSize(float partialTicks) {
        return ((EntityEnergyProjectile)this.entity).getInterpolatedSize(partialTicks);
    }

    @Override
    public float getMaxDistance() {
        return ((EntityEnergyProjectile)this.entity).getMaxDistance();
    }

    @Override
    public void setMaxDistance(float distance) {
        ((EntityEnergyProjectile)this.entity).setMaxDistance(distance);
    }

    @Override
    public int getMaxLifetime() {
        return ((EntityEnergyProjectile)this.entity).getMaxLifetime();
    }

    @Override
    public void setMaxLifetime(int ticks) {
        ((EntityEnergyProjectile)this.entity).setMaxLifetime(ticks);
    }

    @Override
    public float getDamage() {
        return ((EntityEnergyProjectile)this.entity).getDamage();
    }

    @Override
    public void setDamage(float damage) {
        ((EntityEnergyProjectile)this.entity).setCombatDamage(damage);
    }

    @Override
    public float getKnockback() {
        return ((EntityEnergyProjectile)this.entity).getKnockback();
    }

    @Override
    public void setKnockback(float knockback) {
        ((EntityEnergyProjectile)this.entity).setCombatKnockback(knockback);
    }

    @Override
    public float getKnockbackUp() {
        return ((EntityEnergyProjectile)this.entity).getKnockbackUp();
    }

    @Override
    public void setKnockbackUp(float knockbackUp) {
        ((EntityEnergyProjectile)this.entity).setCombatKnockbackUp(knockbackUp);
    }

    @Override
    public boolean isExplosive() {
        return ((EntityEnergyProjectile)this.entity).isExplosive();
    }

    @Override
    public void setExplosive(boolean explosive) {
        ((EntityEnergyProjectile)this.entity).setExplosive(explosive);
    }

    @Override
    public float getExplosionRadius() {
        return ((EntityEnergyProjectile)this.entity).getExplosionRadius();
    }

    @Override
    public void setExplosionRadius(float radius) {
        ((EntityEnergyProjectile)this.entity).setExplosionRadius(radius);
    }

    @Override
    public float getExplosionDamageFalloff() {
        return ((EntityEnergyProjectile)this.entity).getExplosionDamageFalloff();
    }

    @Override
    public void setExplosionDamageFalloff(float falloff) {
        ((EntityEnergyProjectile)this.entity).setExplosionDamageFalloff(falloff);
    }

    @Override
    public int getHitType() {
        return ((EntityEnergyProjectile)this.entity).getHitType();
    }

    @Override
    public void setHitType(int hitType) {
        ((EntityEnergyProjectile)this.entity).setHitType(hitType);
    }

    @Override
    public int getMultiHitDelayTicks() {
        return ((EntityEnergyProjectile)this.entity).getMultiHitDelayTicks();
    }

    @Override
    public void setMultiHitDelayTicks(int delayTicks) {
        ((EntityEnergyProjectile)this.entity).setMultiHitDelayTicks(delayTicks);
    }

    @Override
    public int getMaxHits() {
        return ((EntityEnergyProjectile)this.entity).getMaxHits();
    }

    @Override
    public void setMaxHits(int maxHits) {
        ((EntityEnergyProjectile)this.entity).setMaxHits(maxHits);
    }

    @Override
    public float getSpeed() {
        return ((EntityEnergyProjectile)this.entity).getSpeed();
    }

    @Override
    public void setSpeed(float speed) {
        ((EntityEnergyProjectile)this.entity).setSpeed(speed);
    }

    @Override
    public boolean isHoming() {
        return ((EntityEnergyProjectile)this.entity).isHoming();
    }

    @Override
    public void setHoming(boolean homing) {
        ((EntityEnergyProjectile)this.entity).setHomingEnabled(homing);
    }

    @Override
    public float getHomingStrength() {
        return ((EntityEnergyProjectile)this.entity).getHomingStrength();
    }

    @Override
    public void setHomingStrength(float strength) {
        ((EntityEnergyProjectile)this.entity).setHomingStrength(strength);
    }

    @Override
    public float getHomingRange() {
        return ((EntityEnergyProjectile)this.entity).getHomingRange();
    }

    @Override
    public void setHomingRange(float range) {
        ((EntityEnergyProjectile)this.entity).setHomingRange(range);
    }

    @Override
    public int getAnchor() {
        return ((EntityEnergyProjectile)this.entity).getAnchor();
    }

    @Override
    public float getAnchorOffsetX() {
        return ((EntityEnergyProjectile)this.entity).getAnchorOffsetX();
    }

    @Override
    public float getAnchorOffsetY() {
        return ((EntityEnergyProjectile)this.entity).getAnchorOffsetY();
    }

    @Override
    public float getAnchorOffsetZ() {
        return ((EntityEnergyProjectile)this.entity).getAnchorOffsetZ();
    }

    @Override
    public double getStartX() {
        return ((EntityEnergyProjectile)this.entity).getStartX();
    }

    @Override
    public double getStartY() {
        return ((EntityEnergyProjectile)this.entity).getStartY();
    }

    @Override
    public double getStartZ() {
        return ((EntityEnergyProjectile)this.entity).getStartZ();
    }

    @Override
    public boolean hasHit() {
        return ((EntityEnergyProjectile)this.entity).getHasHit();
    }

    @Override
    public void fireAt(IEntity target) {
        if (target != null) {
            EntityLivingBase living = ScriptEnergyProjectile.resolveLivingTarget(target);
            if (living != null) {
                ((EntityEnergyProjectile)this.entity).setTargetEntityId(living.func_145782_y());
                this.setMotionToward(living.field_70165_t, living.field_70163_u + (double)living.func_70047_e(), living.field_70161_v);
            } else {
                Object mcTarget = target.getMCEntity();
                this.setMotionToward(((Entity)mcTarget).field_70165_t, ((Entity)mcTarget).field_70163_u + (double)mcTarget.func_70047_e(), ((Entity)mcTarget).field_70161_v);
            }
        }
        this.ensureSpawned();
    }

    @Override
    public void fireAt(double x, double y, double z) {
        this.setMotionToward(x, y, z);
        this.ensureSpawned();
    }

    @Override
    public void fireDirection(float yaw, float pitch) {
        this.setMotionFromDirection(yaw, pitch);
        this.ensureSpawned();
    }

    @Override
    public void fireFrom(IEntityLivingBase caster) {
        if (caster == null) {
            return;
        }
        this.initFromCaster(caster);
        this.launchFromOwner(null);
        this.ensureSpawned();
    }

    @Override
    public void fireFrom(IEntityLivingBase caster, IEntity target) {
        if (caster == null) {
            return;
        }
        this.initFromCaster(caster);
        this.launchFromOwner(ScriptEnergyProjectile.resolveLivingTarget(target));
        this.ensureSpawned();
    }

    private void initFromCaster(IEntityLivingBase caster) {
        Object mc = caster.getMCEntity();
        ((EntityEnergyProjectile)this.entity).setOwnerEntityId(mc.func_145782_y());
        double eyeY = ((EntityLivingBase)mc).field_70163_u + (double)mc.func_70047_e();
        ((EntityEnergyProjectile)this.entity).func_70107_b(((EntityLivingBase)mc).field_70165_t, eyeY, ((EntityLivingBase)mc).field_70161_v);
        ((EntityEnergyProjectile)this.entity).setStartPosition(((EntityLivingBase)mc).field_70165_t, eyeY, ((EntityLivingBase)mc).field_70161_v);
    }

    protected void launchFromOwner(EntityLivingBase target) {
        if (target != null) {
            this.setMotionToward(target.field_70165_t, target.field_70163_u + (double)target.func_70047_e(), target.field_70161_v);
        } else {
            Entity owner = ((EntityEnergyProjectile)this.entity).getOwnerEntity();
            if (owner != null) {
                this.setMotionFromDirection(owner.field_70177_z, owner.field_70125_A);
            }
        }
    }

    private void setMotionFromDirection(float yaw, float pitch) {
        float yawRad = (float)Math.toRadians(yaw);
        float pitchRad = (float)Math.toRadians(pitch);
        ((EntityEnergyProjectile)this.entity).field_70159_w = -Math.sin(yawRad) * Math.cos(pitchRad) * (double)((EntityEnergyProjectile)this.entity).getSpeed();
        ((EntityEnergyProjectile)this.entity).field_70181_x = -Math.sin(pitchRad) * (double)((EntityEnergyProjectile)this.entity).getSpeed();
        ((EntityEnergyProjectile)this.entity).field_70179_y = Math.cos(yawRad) * Math.cos(pitchRad) * (double)((EntityEnergyProjectile)this.entity).getSpeed();
    }

    protected static EntityLivingBase resolveLivingTarget(IEntity target) {
        if (target != null && target.getMCEntity() instanceof EntityLivingBase) {
            return (EntityLivingBase)target.getMCEntity();
        }
        return null;
    }

    private void setMotionToward(double x, double y, double z) {
        double dx = x - ((EntityEnergyProjectile)this.entity).field_70165_t;
        double dy = y - ((EntityEnergyProjectile)this.entity).field_70163_u;
        double dz = z - ((EntityEnergyProjectile)this.entity).field_70161_v;
        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len > 0.0) {
            ((EntityEnergyProjectile)this.entity).field_70159_w = dx / len * (double)((EntityEnergyProjectile)this.entity).getSpeed();
            ((EntityEnergyProjectile)this.entity).field_70181_x = dy / len * (double)((EntityEnergyProjectile)this.entity).getSpeed();
            ((EntityEnergyProjectile)this.entity).field_70179_y = dz / len * (double)((EntityEnergyProjectile)this.entity).getSpeed();
        }
    }

    @Override
    public void syncClient() {
        if (((EntityEnergyProjectile)this.entity).field_70175_ag) {
            ((EntityEnergyProjectile)this.entity).sendClientSync();
        }
    }

    protected void ensureSpawned() {
        if (!((EntityEnergyProjectile)this.entity).field_70175_ag && ((EntityEnergyProjectile)this.entity).field_70170_p != null) {
            ((EntityEnergyProjectile)this.entity).field_70170_p.func_72838_d(this.entity);
            if (!((EntityEnergyProjectile)this.entity).field_70170_p.field_72995_K) {
                EventHooks.onEnergyProjectileFired((EntityEnergyProjectile)this.entity);
            }
        } else if (((EntityEnergyProjectile)this.entity).field_70175_ag) {
            ((EntityEnergyProjectile)this.entity).sendClientSync();
        }
    }
}

