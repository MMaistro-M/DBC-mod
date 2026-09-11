/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package noppes.npcs.entity;

import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.entity.EntityNPCInterface;

public abstract class EntityNPCFlying
extends EntityNPCInterface {
    public boolean flyLimitAllow = false;

    public EntityNPCFlying(World world) {
        super(world);
    }

    @Override
    public boolean canFly() {
        return this.ais.movementType == 1;
    }

    @Override
    public void func_70069_a(float distance) {
        if (!this.canFly()) {
            super.func_70069_a(distance);
        }
    }

    protected void func_70064_a(double p_180433_1_, boolean p_180433_3_) {
        if (!this.canFly()) {
            super.func_70064_a(p_180433_1_, p_180433_3_);
        }
    }

    public boolean func_70617_f_() {
        return false;
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    @Override
    public void func_70612_e(float p_70612_1_, float p_70612_2_) {
        if (this.handleMountedMovement(p_70612_1_, p_70612_2_)) {
            return;
        }
        if (!this.canFly() || this.field_70737_aN != 0 || !this.canBreathe()) {
            this.setNpcFlyingState(false);
            super.func_70612_e(p_70612_1_, p_70612_2_);
            return;
        }
        boolean aboveLimit = false;
        double heightOffGround = this.field_70163_u - (double)this.field_70170_p.func_72825_h((int)this.field_70165_t, (int)this.field_70161_v);
        if (heightOffGround < 0.0) {
            Vec3 posLimit;
            Vec3 pos = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            MovingObjectPosition mob = this.field_70170_p.func_72901_a(pos, posLimit = Vec3.func_72443_a((double)this.field_70165_t, (double)(this.field_70163_u - (double)this.ais.flyHeightLimit), (double)this.field_70161_v), true);
            if (mob == null || mob.field_72313_a == MovingObjectPosition.MovingObjectType.MISS) {
                aboveLimit = true;
            }
        } else if (heightOffGround > (double)this.ais.flyHeightLimit) {
            aboveLimit = true;
        }
        if (aboveLimit && this.ais.hasFlyLimit) {
            this.flyLimitAllow = false;
            if (this.field_70181_x > 0.0) {
                this.field_70181_x = 0.0;
            }
        } else if (heightOffGround < Math.ceil(this.field_70131_O) && this.field_70181_x == 0.0) {
            this.flyLimitAllow = false;
            this.setNpcFlyingState(false);
            super.func_70612_e(p_70612_1_, p_70612_2_);
            return;
        }
        this.flyLimitAllow = true;
        this.setNpcFlyingState(true);
        double desiredMotionY = this.field_70181_x;
        if (this.func_70661_as().func_75500_f()) {
            desiredMotionY = -Math.abs(this.ais.flyGravity);
        }
        this.field_70181_x = desiredMotionY;
        super.func_70612_e(p_70612_1_, p_70612_2_);
        this.field_70181_x = desiredMotionY;
        this.updateLimbSwing();
    }

    public void updateLimbSwing() {
        this.field_70722_aY = this.field_70721_aZ;
        double distanceX = this.field_70165_t - this.field_70169_q;
        double distanceZ = this.field_70161_v - this.field_70166_s;
        float distance = MathHelper.func_76133_a((double)(distanceX * distanceX + distanceZ * distanceZ)) * 4.0f;
        if (distance > 1.0f) {
            distance = 1.0f;
        }
        this.field_70721_aZ += (distance - this.field_70721_aZ) * 0.4f;
        this.field_70754_ba += this.field_70721_aZ;
    }

    @Override
    public void performMountedMovement(float strafe, float forward, float moveSpeed) {
        if (!this.canFly() || this.field_70737_aN != 0 || !this.canBreathe()) {
            this.setNpcFlyingState(false);
            super.performMountedMovement(strafe, forward, moveSpeed);
            return;
        }
        boolean flightModeActive = this.isMountFlightModeActive();
        boolean aboveLimit = false;
        double heightOffGround = this.field_70163_u - (double)this.field_70170_p.func_72825_h((int)this.field_70165_t, (int)this.field_70161_v);
        if (heightOffGround < 0.0) {
            Vec3 posLimit;
            Vec3 pos = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            MovingObjectPosition mob = this.field_70170_p.func_72901_a(pos, posLimit = Vec3.func_72443_a((double)this.field_70165_t, (double)(this.field_70163_u - (double)this.ais.flyHeightLimit), (double)this.field_70161_v), true);
            if (mob == null || mob.field_72313_a == MovingObjectPosition.MovingObjectType.MISS) {
                aboveLimit = true;
            }
        } else if (heightOffGround > (double)this.ais.flyHeightLimit) {
            aboveLimit = true;
        }
        if (aboveLimit && this.ais.hasFlyLimit) {
            this.flyLimitAllow = false;
            if (this.field_70181_x > 0.0) {
                this.field_70181_x = 0.0;
            }
        } else if (heightOffGround < Math.ceil(this.field_70131_O) && this.field_70181_x == 0.0) {
            this.flyLimitAllow = false;
            this.setNpcFlyingState(false);
            super.performMountedMovement(strafe, forward, moveSpeed);
            return;
        }
        this.flyLimitAllow = true;
        this.setNpcFlyingState(true);
        if (flightModeActive) {
            super.performMountedMovement(strafe, forward, moveSpeed);
            return;
        }
        double desiredMotionY = this.field_70181_x;
        if (this.func_70661_as().func_75500_f()) {
            desiredMotionY = -Math.abs(this.ais.flyGravity);
        }
        this.field_70181_x = desiredMotionY;
        super.performMountedMovement(strafe, forward, moveSpeed);
        this.field_70181_x = desiredMotionY;
        this.updateLimbSwing();
    }
}

