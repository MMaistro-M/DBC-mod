/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import kamkeel.npcs.controllers.data.ability.data.energy.EnergyAnchorData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyCombatData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyHomingData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLifespanData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAbilityOrb
extends EntityEnergyProjectile {
    private static final int HOMING_STARTUP_TICKS = 4;
    private static final int HOMING_RAMP_TICKS = 8;

    public EntityAbilityOrb(World world) {
        super(world);
    }

    public EntityAbilityOrb(World world, EntityLivingBase owner, EntityLivingBase target, double x, double y, double z, float orbSize, EnergyDisplayData display, EnergyCombatData combat, EnergyHomingData homing, EnergyLightningData lightning, EnergyLifespanData lifespan) {
        super(world);
        this.initProjectile(owner, target, x, y, z, orbSize, display, combat, lightning, lifespan);
        this.homingData = homing != null ? homing.copy() : new EnergyHomingData();
        this.calculateInitialVelocity(owner, target, x, y, z);
    }

    public void setupPreview(EntityLivingBase owner, float orbSize, EnergyDisplayData display, EnergyLightningData lightning, EnergyAnchorData anchor, int chargeDuration) {
        this.setupPreviewState(owner, display, lightning, anchor, chargeDuration);
        this.targetSize = orbSize;
        this.setVisualSize(0.01f);
        this.setChargeOriginFromAnchor(owner, this.anchorData);
        this.clearMotion();
    }

    public void startPreviewFiring() {
        this.startPreviewFiringDefault();
    }

    public void startMoving(EntityLivingBase target) {
        this.startMovingTowardTargetDefault(target);
    }

    @Override
    protected void updateProjectile() {
        if (this.isCharging()) {
            this.updateCharging();
            return;
        }
        if (this.previewMode) {
            this.updatePreviewMovement();
            return;
        }
        if (this.field_70170_p.field_72995_K) {
            this.handleClientInterpolation();
        } else {
            this.updateMovement();
            this.checkBlockCollision();
            this.checkEntityCollision();
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        }
        this.handleSolidCollisionTermination();
    }

    private void updatePreviewMovement() {
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
    }

    private void updateMovement() {
        if (this.field_70173_aa > 4) {
            int homingTicks = this.field_70173_aa - 4;
            if (homingTicks >= 8) {
                this.updateHoming();
            } else {
                float ramp = (float)homingTicks / 8.0f;
                this.updateHomingWithRamp(ramp);
            }
        }
    }

    private void updateHomingWithRamp(float ramp) {
        if (ramp <= 0.0f) {
            return;
        }
        float originalStrength = this.homingData.homingStrength;
        this.homingData.homingStrength = originalStrength * ramp;
        this.updateHoming();
        this.homingData.homingStrength = originalStrength;
    }

    private void checkBlockCollision() {
        this.handleBlockImpact(this.rayTraceBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y), true);
    }

    private void checkEntityCollision() {
        double nextX = this.field_70165_t + this.field_70159_w;
        double nextY = this.field_70163_u + this.field_70181_x;
        double nextZ = this.field_70161_v + this.field_70179_y;
        double hitSize = Math.max(0.05, (double)this.size * 0.5);
        AxisAlignedBB hitBox = AxisAlignedBB.func_72330_a((double)(Math.min(this.field_70165_t, nextX) - hitSize), (double)(Math.min(this.field_70163_u, nextY) - hitSize), (double)(Math.min(this.field_70161_v, nextZ) - hitSize), (double)(Math.max(this.field_70165_t, nextX) + hitSize), (double)(Math.max(this.field_70163_u, nextY) + hitSize), (double)(Math.max(this.field_70161_v, nextZ) + hitSize));
        this.processEntitiesInHitBox(hitBox, nextX, nextY, nextZ);
    }

    @Override
    protected String debugLogExtra() {
        return String.format("motion=(%.3f,%.3f,%.3f) homing=%b start=(%.2f,%.2f,%.2f)", this.field_70159_w, this.field_70181_x, this.field_70179_y, this.homingData != null && this.homingData.isHoming(), this.startX, this.startY, this.startZ);
    }

    public float getOrbSize() {
        return this.size;
    }

    @Override
    protected void readProjectileNBT(NBTTagCompound nbt) {
        this.readChargingNBT(nbt);
    }

    @Override
    protected void writeProjectileNBT(NBTTagCompound nbt) {
        this.writeChargingNBT(nbt);
    }
}

