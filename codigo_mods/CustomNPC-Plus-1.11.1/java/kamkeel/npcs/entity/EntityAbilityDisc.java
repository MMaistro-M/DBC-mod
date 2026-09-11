/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import kamkeel.npcs.controllers.data.ability.data.energy.EnergyAnchorData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyCombatData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyHomingData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLifespanData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.util.AnchorPointHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAbilityDisc
extends EntityEnergyProjectile {
    private boolean boomerang = false;
    private int boomerangDelay = 40;
    private boolean returning = false;
    private int ticksSinceMiss = 0;
    private float discRadius = 1.0f;
    private float discThickness = 0.2f;
    private float renderDiscRadius = 1.0f;
    private float renderDiscThickness = 0.2f;
    private float prevRenderDiscRadius = 1.0f;
    private float prevRenderDiscThickness = 0.2f;
    private int returnOwnerNullTicks = 0;
    private boolean vertical = false;
    private float lastTravelYaw = 0.0f;
    private float targetDiscRadius = 1.0f;
    private float targetDiscThickness = 0.2f;

    public EntityAbilityDisc(World world) {
        super(world);
    }

    public EntityAbilityDisc(World world, EntityLivingBase owner, EntityLivingBase target, double x, double y, double z, float discRadius, float discThickness, EnergyDisplayData display, EnergyCombatData combat, EnergyHomingData homing, EnergyLightningData lightning, EnergyLifespanData lifespan, boolean boomerang, int boomerangDelay) {
        super(world);
        this.initProjectile(owner, target, x, y, z, 1.0f, display, combat, lightning, lifespan);
        this.homingData = homing != null ? homing.copy() : new EnergyHomingData();
        this.boomerang = boomerang;
        this.boomerangDelay = boomerangDelay;
        this.discRadius = discRadius;
        this.discThickness = discThickness;
        this.calculateInitialVelocity(owner, target, x, y, z);
    }

    public void setupCharging(EnergyAnchorData anchor, int chargeDuration, boolean vertical) {
        this.targetDiscRadius = this.discRadius;
        this.targetDiscThickness = this.discThickness;
        super.setupCharging(anchor, chargeDuration);
        this.vertical = vertical;
        this.discRadius = 0.01f;
        this.discThickness = 0.01f;
    }

    public void setupPreview(EntityLivingBase owner, float discRadius, float discThickness, EnergyDisplayData display, EnergyLightningData lightning, EnergyAnchorData anchor, int chargeDuration, boolean vertical) {
        this.setupPreviewState(owner, display, lightning, anchor, chargeDuration);
        this.vertical = vertical;
        this.targetDiscRadius = discRadius;
        this.targetDiscThickness = discThickness;
        this.discRadius = 0.01f;
        this.discThickness = 0.01f;
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
    protected void updateRotation() {
        this.rotationValY += this.getRotationSpeed();
        if (this.rotationValY > 360.0f) {
            this.rotationValY -= 360.0f;
        }
    }

    @Override
    protected void updateProjectile() {
        this.prevRenderDiscRadius = this.renderDiscRadius;
        this.prevRenderDiscThickness = this.renderDiscThickness;
        if (this.isCharging()) {
            this.renderDiscRadius = this.discRadius;
            this.renderDiscThickness = this.discThickness;
            this.updateCharging();
            return;
        }
        this.renderDiscRadius += (this.discRadius - this.renderDiscRadius) * 0.15f;
        this.renderDiscThickness += (this.discThickness - this.renderDiscThickness) * 0.15f;
        if (this.previewMode) {
            this.updatePreviewMovement();
            return;
        }
        if (this.field_70170_p.field_72995_K) {
            this.handleClientInterpolation();
        } else {
            if (this.returning) {
                this.updateReturnToOwner();
            } else {
                this.updateHoming();
            }
            this.checkBlockCollision();
            this.checkEntityCollision();
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            if (this.boomerang && !this.returning && !this.hasHit) {
                ++this.ticksSinceMiss;
            }
        }
        this.handleSolidCollisionTermination();
    }

    @Override
    protected boolean checkMaxDistance() {
        double distTraveled = Math.sqrt((this.field_70165_t - this.startX) * (this.field_70165_t - this.startX) + (this.field_70163_u - this.startY) * (this.field_70163_u - this.startY) + (this.field_70161_v - this.startZ) * (this.field_70161_v - this.startZ));
        if (this.boomerang) {
            if (!(this.returning || this.hasHit || !(distTraveled >= (double)this.getMaxDistance() * 0.8) && this.ticksSinceMiss < this.boomerangDelay)) {
                this.returning = true;
            }
            if (this.returning) {
                Entity owner = this.getOwnerEntity();
                if (owner != null) {
                    this.returnOwnerNullTicks = 0;
                    double distToOwner = Math.sqrt((this.field_70165_t - owner.field_70165_t) * (this.field_70165_t - owner.field_70165_t) + (this.field_70163_u - owner.field_70163_u) * (this.field_70163_u - owner.field_70163_u) + (this.field_70161_v - owner.field_70161_v) * (this.field_70161_v - owner.field_70161_v));
                    return distToOwner < 1.5;
                }
                ++this.returnOwnerNullTicks;
                return this.returnOwnerNullTicks > 100;
            }
            return false;
        }
        return distTraveled >= (double)this.getMaxDistance();
    }

    private void updatePreviewMovement() {
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
    }

    private void updateReturnToOwner() {
        double targetZ;
        double targetY;
        double targetX;
        Entity owner = this.getOwnerEntity();
        if (owner != null) {
            targetX = owner.field_70165_t;
            targetY = owner.field_70163_u + (double)owner.field_70131_O * 0.5;
            targetZ = owner.field_70161_v;
        } else {
            targetX = this.startX;
            targetY = this.startY;
            targetZ = this.startZ;
        }
        double dx = targetX - this.field_70165_t;
        double dy = targetY - this.field_70163_u;
        double dz = targetZ - this.field_70161_v;
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (dist > 0.0) {
            double returnStrength = Math.min(1.0, (double)this.getHomingStrength() * 2.5);
            double desiredVX = dx / dist * (double)this.getSpeed();
            double desiredVY = dy / dist * (double)this.getSpeed();
            double desiredVZ = dz / dist * (double)this.getSpeed();
            this.field_70159_w += (desiredVX - this.field_70159_w) * returnStrength;
            this.field_70181_x += (desiredVY - this.field_70181_x) * returnStrength;
            this.field_70179_y += (desiredVZ - this.field_70179_y) * returnStrength;
            double vLen = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
            if (vLen > 0.0) {
                this.field_70159_w = this.field_70159_w / vLen * (double)this.getSpeed();
                this.field_70181_x = this.field_70181_x / vLen * (double)this.getSpeed();
                this.field_70179_y = this.field_70179_y / vLen * (double)this.getSpeed();
            }
        }
    }

    private void checkBlockCollision() {
        this.handleBlockImpact(this.rayTraceBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y), true);
    }

    private void checkEntityCollision() {
        double halfY;
        double halfZ;
        double halfX;
        double nextX = this.field_70165_t + this.field_70159_w;
        double nextY = this.field_70163_u + this.field_70181_x;
        double nextZ = this.field_70161_v + this.field_70179_y;
        double halfRadius = Math.max(0.05, (double)this.discRadius * 0.5);
        double halfThickness = Math.max(0.03, (double)this.discThickness * 0.5);
        if (this.vertical) {
            double horizLen = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            if (horizLen > 1.0E-5) {
                double nx = this.field_70159_w / horizLen;
                double nz = this.field_70179_y / horizLen;
                halfX = Math.abs(nx) * halfThickness + Math.abs(nz) * halfRadius;
                halfZ = Math.abs(nz) * halfThickness + Math.abs(nx) * halfRadius;
            } else {
                halfX = halfRadius;
                halfZ = halfRadius;
            }
            halfY = halfRadius;
        } else {
            halfX = halfRadius;
            halfY = halfThickness;
            halfZ = halfRadius;
        }
        AxisAlignedBB hitBox = AxisAlignedBB.func_72330_a((double)(Math.min(this.field_70165_t, nextX) - halfX), (double)(Math.min(this.field_70163_u, nextY) - halfY), (double)(Math.min(this.field_70161_v, nextZ) - halfZ), (double)(Math.max(this.field_70165_t, nextX) + halfX), (double)(Math.max(this.field_70163_u, nextY) + halfY), (double)(Math.max(this.field_70161_v, nextZ) + halfZ));
        this.processEntitiesInHitBox(hitBox, nextX, nextY, nextZ);
    }

    @Override
    protected void updateCharging() {
        ++this.chargeTick;
        float progress = this.getChargeProgress();
        this.discRadius = this.targetDiscRadius * progress;
        this.discThickness = this.targetDiscThickness * progress;
        this.size = 1.0f;
        Entity owner = this.getOwnerEntity();
        if (owner instanceof EntityLivingBase) {
            Vec3 pos = AnchorPointHelper.calculateAnchorPosition((EntityLivingBase)owner, this.anchorData);
            this.func_70107_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
        }
        this.updateRotation();
    }

    @Override
    protected String debugLogExtra() {
        return String.format("motion=(%.3f,%.3f,%.3f) radius=%.2f thickness=%.2f boomerang=%b returning=%b vertical=%b ticksSinceMiss=%d", this.field_70159_w, this.field_70181_x, this.field_70179_y, Float.valueOf(this.discRadius), Float.valueOf(this.discThickness), this.boomerang, this.returning, this.vertical, this.ticksSinceMiss);
    }

    public float getDiscRadius() {
        return this.discRadius;
    }

    public float getInterpolatedDiscRadius(float partialTicks) {
        return this.prevRenderDiscRadius + (this.renderDiscRadius - this.prevRenderDiscRadius) * partialTicks;
    }

    public void setDiscRadius(float radius) {
        this.discRadius = radius;
    }

    public float getDiscThickness() {
        return this.discThickness;
    }

    public float getInterpolatedDiscThickness(float partialTicks) {
        return this.prevRenderDiscThickness + (this.renderDiscThickness - this.prevRenderDiscThickness) * partialTicks;
    }

    public void setDiscThickness(float thickness) {
        this.discThickness = thickness;
    }

    public boolean isBoomerang() {
        return this.boomerang;
    }

    public void setBoomerang(boolean boomerang) {
        this.boomerang = boomerang;
    }

    public int getBoomerangDelay() {
        return this.boomerangDelay;
    }

    public void setBoomerangDelay(int delay) {
        this.boomerangDelay = delay;
    }

    public boolean isReturning() {
        return this.returning;
    }

    public boolean isVertical() {
        return this.vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public float getTravelYaw() {
        double speed;
        if (this.isCharging()) {
            EntityLivingBase owner;
            Object object = owner = this.previewMode ? this.previewOwner : this.getOwnerEntity();
            if (owner != null) {
                this.lastTravelYaw = owner.field_70177_z;
                return this.lastTravelYaw;
            }
        }
        if ((speed = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y)) > 0.001) {
            this.lastTravelYaw = (float)(Math.atan2(-this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
        }
        return this.lastTravelYaw;
    }

    @Override
    protected boolean reflectFromBarrier(EntityEnergyBarrier barrier, float reflectStrengthPct) {
        boolean reflected = super.reflectFromBarrier(barrier, reflectStrengthPct);
        if (reflected) {
            this.boomerang = false;
            this.returning = false;
        }
        return reflected;
    }

    @Override
    protected void writeProjectileReflectionData(NBTTagCompound nbt) {
        nbt.func_74757_a("Boomerang", this.boomerang);
        nbt.func_74757_a("Returning", this.returning);
    }

    @Override
    protected void applyProjectileReflectionData(NBTTagCompound nbt) {
        this.boomerang = nbt.func_74767_n("Boomerang");
        this.returning = nbt.func_74767_n("Returning");
    }

    @Override
    protected void writeProjectileClientSyncData(NBTTagCompound nbt) {
        nbt.func_74776_a("DiscRadius", this.discRadius);
        nbt.func_74776_a("DiscThickness", this.discThickness);
        nbt.func_74757_a("Vertical", this.vertical);
    }

    @Override
    protected void applyProjectileClientSyncData(NBTTagCompound nbt) {
        this.discRadius = nbt.func_74760_g("DiscRadius");
        this.discThickness = nbt.func_74760_g("DiscThickness");
        this.vertical = nbt.func_74767_n("Vertical");
    }

    @Override
    protected void readProjectileNBT(NBTTagCompound nbt) {
        this.boomerang = nbt.func_74764_b("Boomerang") && nbt.func_74767_n("Boomerang");
        this.boomerangDelay = nbt.func_74764_b("BoomerangDelay") ? nbt.func_74762_e("BoomerangDelay") : 40;
        this.discRadius = EntityAbilityDisc.sanitize(nbt.func_74764_b("DiscRadius") ? nbt.func_74760_g("DiscRadius") : 1.0f, 1.0f, 100.0f);
        this.discThickness = EntityAbilityDisc.sanitize(nbt.func_74764_b("DiscThickness") ? nbt.func_74760_g("DiscThickness") : 0.2f, 0.2f, 100.0f);
        this.renderDiscRadius = this.discRadius;
        this.renderDiscThickness = this.discThickness;
        this.prevRenderDiscRadius = this.discRadius;
        this.prevRenderDiscThickness = this.discThickness;
        this.vertical = nbt.func_74764_b("Vertical") ? nbt.func_74767_n("Vertical") : false;
        this.returning = nbt.func_74764_b("Returning") && nbt.func_74767_n("Returning");
        this.readChargingNBT(nbt);
        this.targetDiscRadius = nbt.func_74764_b("TargetDiscRadius") ? nbt.func_74760_g("TargetDiscRadius") : this.discRadius;
        this.targetDiscThickness = nbt.func_74764_b("TargetDiscThickness") ? nbt.func_74760_g("TargetDiscThickness") : this.discThickness;
        this.size = 1.0f;
        this.renderCurrentSize = 1.0f;
        this.prevRenderSize = 1.0f;
    }

    @Override
    protected void writeProjectileNBT(NBTTagCompound nbt) {
        nbt.func_74757_a("Boomerang", this.boomerang);
        nbt.func_74768_a("BoomerangDelay", this.boomerangDelay);
        nbt.func_74776_a("DiscRadius", this.discRadius);
        nbt.func_74776_a("DiscThickness", this.discThickness);
        nbt.func_74757_a("Vertical", this.vertical);
        nbt.func_74757_a("Returning", this.returning);
        this.writeChargingNBT(nbt);
        nbt.func_74776_a("TargetDiscRadius", this.targetDiscRadius);
        nbt.func_74776_a("TargetDiscThickness", this.targetDiscThickness);
    }
}

