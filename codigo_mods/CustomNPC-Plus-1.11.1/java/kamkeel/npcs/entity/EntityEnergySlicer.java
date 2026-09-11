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
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.util.AnchorPointHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnergySlicer
extends EntityEnergyProjectile {
    private float sliceWidth = 3.0f;
    private float sliceThickness = 0.15f;
    private float lastTravelYaw = 0.0f;
    private float lastTravelPitch = 0.0f;
    private float targetSliceWidth = 3.0f;
    private float targetSliceThickness = 0.15f;

    public EntityEnergySlicer(World world) {
        super(world);
    }

    public EntityEnergySlicer(World world, EntityLivingBase owner, EntityLivingBase target, double x, double y, double z, float sliceWidth, float sliceThickness, EnergyDisplayData display, EnergyCombatData combat, EnergyHomingData homing, EnergyLightningData lightning, EnergyLifespanData lifespan) {
        super(world);
        this.initProjectile(owner, target, x, y, z, sliceWidth, display, combat, lightning, lifespan);
        this.homingData = homing != null ? homing.copy() : new EnergyHomingData();
        this.sliceWidth = sliceWidth;
        this.sliceThickness = sliceThickness;
        this.calculateInitialVelocity(owner, target, x, y, z);
    }

    public void setupSlicerCharging(EnergyAnchorData anchor, int chargeDuration, float width, float thickness) {
        this.targetSliceWidth = width;
        this.targetSliceThickness = thickness;
        this.sliceWidth = 0.01f;
        this.sliceThickness = 0.01f;
        super.setupCharging(anchor, chargeDuration);
    }

    public void setupPreview(EntityLivingBase owner, float width, float thickness, EnergyDisplayData display, EnergyLightningData lightning, EnergyAnchorData anchor, int chargeDuration) {
        this.setupPreviewState(owner, display, lightning, anchor, chargeDuration);
        this.targetSliceWidth = width;
        this.targetSliceThickness = thickness;
        this.sliceWidth = 0.01f;
        this.sliceThickness = 0.01f;
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
    }

    @Override
    protected void updateProjectile() {
        if (this.isCharging()) {
            this.updateCharging();
            return;
        }
        if (this.previewMode) {
            this.field_70165_t += this.field_70159_w;
            this.field_70163_u += this.field_70181_x;
            this.field_70161_v += this.field_70179_y;
            return;
        }
        if (this.field_70170_p.field_72995_K) {
            this.handleClientInterpolation();
        } else {
            if (this.homingData.isHoming()) {
                this.updateHoming();
            }
            this.checkBlockCollision();
            this.checkEntityCollision();
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        }
        this.handleSolidCollisionTermination();
    }

    @Override
    protected void updateCharging() {
        ++this.chargeTick;
        float progress = this.getChargeProgress();
        this.sliceWidth = this.targetSliceWidth * progress;
        this.sliceThickness = this.targetSliceThickness * progress;
        this.size = this.sliceWidth;
        Entity owner = this.getOwnerEntity();
        if (owner instanceof EntityLivingBase) {
            Vec3 pos = AnchorPointHelper.calculateAnchorPosition((EntityLivingBase)owner, this.anchorData);
            this.func_70107_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
        }
    }

    private void checkBlockCollision() {
        this.handleBlockImpact(this.rayTraceBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y), false);
    }

    private void checkEntityCollision() {
        float halfW = this.sliceWidth * 0.5f;
        float halfH = this.sliceThickness * 0.5f;
        AxisAlignedBB hitBox = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)halfW), (double)(this.field_70163_u - (double)halfH), (double)(this.field_70161_v - (double)halfW), (double)(this.field_70165_t + (double)halfW), (double)(this.field_70163_u + (double)halfH), (double)(this.field_70161_v + (double)halfW));
        this.processEntitiesInHitBox(hitBox, this.field_70165_t, this.field_70163_u, this.field_70161_v);
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

    public float getTravelPitch() {
        double horizSpeed = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        if (horizSpeed > 0.001 || Math.abs(this.field_70181_x) > 0.001) {
            this.lastTravelPitch = (float)(-Math.atan2(this.field_70181_x, horizSpeed) * 180.0 / Math.PI);
        }
        return this.lastTravelPitch;
    }

    @Override
    protected String debugLogExtra() {
        return String.format("motion=(%.3f,%.3f,%.3f) sliceW=%.2f sliceT=%.2f", this.field_70159_w, this.field_70181_x, this.field_70179_y, Float.valueOf(this.sliceWidth), Float.valueOf(this.sliceThickness));
    }

    public float getSliceWidth() {
        return this.sliceWidth;
    }

    public void setSliceWidth(float width) {
        this.sliceWidth = width;
    }

    public float getSliceThickness() {
        return this.sliceThickness;
    }

    public void setSliceThickness(float thickness) {
        this.sliceThickness = thickness;
    }

    @Override
    protected void writeProjectileClientSyncData(NBTTagCompound nbt) {
        nbt.func_74776_a("SliceWidth", this.sliceWidth);
        nbt.func_74776_a("SliceThickness", this.sliceThickness);
    }

    @Override
    protected void applyProjectileClientSyncData(NBTTagCompound nbt) {
        this.sliceWidth = nbt.func_74760_g("SliceWidth");
        this.sliceThickness = nbt.func_74760_g("SliceThickness");
    }

    @Override
    protected void readProjectileNBT(NBTTagCompound nbt) {
        this.readChargingNBT(nbt);
        this.sliceWidth = EntityEnergySlicer.sanitize(nbt.func_74764_b("SliceWidth") ? nbt.func_74760_g("SliceWidth") : 3.0f, 3.0f, 100.0f);
        this.sliceThickness = EntityEnergySlicer.sanitize(nbt.func_74764_b("SliceThickness") ? nbt.func_74760_g("SliceThickness") : 0.15f, 0.15f, 100.0f);
        this.targetSliceWidth = EntityEnergySlicer.sanitize(nbt.func_74764_b("TargetSliceWidth") ? nbt.func_74760_g("TargetSliceWidth") : this.sliceWidth, this.sliceWidth, 100.0f);
        this.targetSliceThickness = EntityEnergySlicer.sanitize(nbt.func_74764_b("TargetSliceThickness") ? nbt.func_74760_g("TargetSliceThickness") : this.sliceThickness, this.sliceThickness, 100.0f);
    }

    @Override
    protected void writeProjectileNBT(NBTTagCompound nbt) {
        this.writeChargingNBT(nbt);
        nbt.func_74776_a("SliceWidth", this.sliceWidth);
        nbt.func_74776_a("SliceThickness", this.sliceThickness);
        nbt.func_74776_a("TargetSliceWidth", this.targetSliceWidth);
        nbt.func_74776_a("TargetSliceThickness", this.targetSliceThickness);
    }
}

