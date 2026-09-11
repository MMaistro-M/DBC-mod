/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S12PacketEntityVelocity
 *  net.minecraft.util.Vec3
 */
package kamkeel.npcs.controllers.data.ability.type;

import kamkeel.npcs.controllers.data.ability.Ability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.Vec3;

public abstract class AbilityMovement
extends Ability {
    private static final int PLAYER_STALL_GRACE_TICKS = 8;
    private static final int NPC_STALL_GRACE_TICKS = 2;
    protected transient double startX;
    protected transient double startY;
    protected transient double startZ;
    protected transient double prevTickX;
    protected transient double prevTickZ;
    protected transient Vec3 movementDirection;
    protected transient float lockedYaw;
    protected transient int maxActiveTicks;

    @Override
    public boolean hasAbilityMovement() {
        return true;
    }

    @Override
    public void cleanup() {
        this.movementDirection = null;
        this.maxActiveTicks = 0;
    }

    @Override
    public void resetForBurst() {
        this.movementDirection = null;
        this.maxActiveTicks = 0;
    }

    protected void lockDirection(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.isPlayerCaster(caster) && target != null) {
            this.lockDirectionToTarget(caster, target);
        } else {
            this.lockDirectionFromLook(caster);
        }
    }

    protected void lockDirectionFromLook(EntityLivingBase caster) {
        float yaw = (float)Math.toRadians(caster.field_70177_z);
        this.movementDirection = Vec3.func_72443_a((double)(-Math.sin(yaw)), (double)0.0, (double)Math.cos(yaw));
        this.lockedYaw = this.computeYawFromDirection();
    }

    protected void lockDirectionToTarget(EntityLivingBase caster, EntityLivingBase target) {
        double dx = target.field_70165_t - caster.field_70165_t;
        double dz = target.field_70161_v - caster.field_70161_v;
        double len = Math.sqrt(dx * dx + dz * dz);
        if (len > 0.0) {
            this.movementDirection = Vec3.func_72443_a((double)(dx / len), (double)0.0, (double)(dz / len));
            this.lockedYaw = this.computeYawFromDirection();
        } else {
            this.lockDirectionFromLook(caster);
        }
    }

    protected float getBaseYaw(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.isPlayerCaster(caster) && target != null) {
            double dx = target.field_70165_t - caster.field_70165_t;
            double dz = target.field_70161_v - caster.field_70161_v;
            return (float)Math.toDegrees(Math.atan2(-dx, dz));
        }
        return caster.field_70177_z;
    }

    protected void setDirectionFromYaw(float yawDegrees) {
        float yawRad = (float)Math.toRadians(yawDegrees);
        this.movementDirection = Vec3.func_72443_a((double)(-Math.sin(yawRad)), (double)0.0, (double)Math.cos(yawRad));
        this.lockedYaw = yawDegrees;
    }

    protected void initMovement(EntityLivingBase caster, float distance, float speed) {
        this.startX = caster.field_70165_t;
        this.startY = caster.field_70163_u;
        this.startZ = caster.field_70161_v;
        this.prevTickX = caster.field_70165_t;
        this.prevTickZ = caster.field_70161_v;
        this.maxActiveTicks = speed > 0.0f ? (int)(distance / speed) + 10 : 10;
    }

    protected boolean checkTimeout(int tick) {
        return !this.isPreview() && (this.movementDirection == null || tick > this.maxActiveTicks);
    }

    protected boolean checkStall(EntityLivingBase caster, int tick) {
        int graceTicks;
        if (this.isPreview()) {
            return false;
        }
        int n = graceTicks = this.isPlayerCaster(caster) ? 8 : 2;
        if (tick <= graceTicks) {
            return false;
        }
        double dx = caster.field_70165_t - this.prevTickX;
        double dz = caster.field_70161_v - this.prevTickZ;
        return dx * dx + dz * dz < 1.0E-4;
    }

    protected void updatePrevPosition(EntityLivingBase caster) {
        this.prevTickX = caster.field_70165_t;
        this.prevTickZ = caster.field_70161_v;
    }

    protected double getDistanceTraveled(EntityLivingBase caster) {
        double dx = caster.field_70165_t - this.startX;
        double dz = caster.field_70161_v - this.startZ;
        return Math.sqrt(dx * dx + dz * dz);
    }

    protected double getDistanceTraveledSq(EntityLivingBase caster) {
        double dx = caster.field_70165_t - this.startX;
        double dz = caster.field_70161_v - this.startZ;
        return dx * dx + dz * dz;
    }

    protected boolean checkBlocked(EntityLivingBase caster, float speed) {
        if (this.isPreview() || this.movementDirection == null) {
            return false;
        }
        return AbilityMovement.isMovementBlocked(caster, this.movementDirection.field_72450_a, this.movementDirection.field_72449_c, speed) || AbilityMovement.isMovementBlockedByBarrier(caster, this.movementDirection.field_72450_a, this.movementDirection.field_72449_c, speed);
    }

    protected void sendPlayerVelocity(EntityLivingBase caster) {
        if (caster instanceof EntityPlayerMP) {
            ((EntityPlayerMP)caster).field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)caster));
        }
    }

    protected void applyHorizontalMomentum(EntityLivingBase caster, double motionX, double motionZ) {
        caster.field_70159_w = motionX;
        caster.field_70179_y = motionZ;
        if (!this.isPreview()) {
            if (caster instanceof EntityPlayerMP) {
                this.sendPlayerVelocity(caster);
            } else {
                caster.field_70133_I = true;
            }
        }
    }

    protected void applyVelocity(EntityLivingBase caster, float speed) {
        this.applyHorizontalMomentum(caster, this.movementDirection.field_72450_a * (double)speed, this.movementDirection.field_72449_c * (double)speed);
    }

    protected void applyVelocityFlat(EntityLivingBase caster, float speed) {
        caster.field_70181_x = 0.0;
        this.applyHorizontalMomentum(caster, this.movementDirection.field_72450_a * (double)speed, this.movementDirection.field_72449_c * (double)speed);
    }

    protected void stopMomentum(EntityLivingBase caster) {
        this.applyHorizontalMomentum(caster, 0.0, 0.0);
    }

    protected float computeYawFromDirection() {
        if (this.movementDirection == null) {
            return 0.0f;
        }
        return (float)Math.toDegrees(Math.atan2(-this.movementDirection.field_72450_a, this.movementDirection.field_72449_c));
    }

    protected void enforceLockedRotation(EntityLivingBase caster) {
        caster.field_70177_z = this.lockedYaw;
        caster.field_70759_as = this.lockedYaw;
        caster.field_70126_B = this.lockedYaw;
        caster.field_70758_at = this.lockedYaw;
        caster.field_70761_aq = this.lockedYaw;
        caster.field_70760_ar = this.lockedYaw;
    }
}

