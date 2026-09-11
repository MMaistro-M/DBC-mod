/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAbilityBeam
extends EntityEnergyProjectile {
    private float beamWidth = 0.3f;
    private float headSize = 0.5f;
    private float renderBeamWidth = 0.3f;
    private float renderHeadSize = 0.5f;
    private float prevRenderBeamWidth = 0.3f;
    private float prevRenderHeadSize = 0.5f;
    private List<Vec3> trailPoints = new ArrayList<Vec3>();
    private static final int MAX_TRAIL_POINTS = 200;
    private static final double MIN_POINT_DISTANCE = 0.2;
    private static final int TRAIL_COMPACT_THRESHOLD = 128;
    private int trailStartIndex = 0;
    private double headOffsetX;
    private double headOffsetY;
    private double headOffsetZ;
    private double prevHeadOffsetX;
    private double prevHeadOffsetY;
    private double prevHeadOffsetZ;
    private BeamMode beamMode = BeamMode.ANCHORED;
    private boolean freeAim = false;
    private static final float FREE_AIM_STRENGTH = 0.15f;
    private float chargeOffsetDistance = 1.0f;
    private int trailFadeTime = 20;
    private List<Integer> trailPointAges = new ArrayList<Integer>();

    public EntityAbilityBeam(World world) {
        super(world);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        double headDist = Math.sqrt(this.headOffsetX * this.headOffsetX + this.headOffsetY * this.headOffsetY + this.headOffsetZ * this.headOffsetZ);
        double range = Math.max(128.0, headDist * 2.0 + 64.0);
        return distance < range * range;
    }

    private static BeamMode modeFromAnchored(boolean anchored) {
        return anchored ? BeamMode.ANCHORED : BeamMode.FREE_TRAIL;
    }

    private boolean isAnchoredMode() {
        return this.beamMode == BeamMode.ANCHORED;
    }

    private boolean isFadingMode() {
        return this.beamMode == BeamMode.FREE_TRAIL;
    }

    private int getActiveTrailSize() {
        return Math.max(0, this.trailPoints.size() - this.trailStartIndex);
    }

    private void resetHeadOffsets() {
        this.headOffsetX = 0.0;
        this.headOffsetY = 0.0;
        this.headOffsetZ = 0.0;
        this.prevHeadOffsetX = 0.0;
        this.prevHeadOffsetY = 0.0;
        this.prevHeadOffsetZ = 0.0;
    }

    private void resetTrailStorage() {
        this.trailPoints.clear();
        this.trailPointAges.clear();
        this.trailStartIndex = 0;
    }

    private void setBeamMode(BeamMode mode) {
        BeamMode beamMode = this.beamMode = mode != null ? mode : BeamMode.ANCHORED;
        if (this.isFadingMode()) {
            while (this.trailPointAges.size() < this.trailPoints.size()) {
                this.trailPointAges.add(0);
            }
            while (this.trailPointAges.size() > this.trailPoints.size()) {
                this.trailPointAges.remove(this.trailPointAges.size() - 1);
            }
        } else {
            if (this.trailStartIndex > 0 && this.trailStartIndex < this.trailPoints.size()) {
                this.trailPoints = new ArrayList<Vec3>(this.trailPoints.subList(this.trailStartIndex, this.trailPoints.size()));
            }
            this.trailStartIndex = 0;
            this.trailPointAges.clear();
        }
    }

    public EntityAbilityBeam(World world, EntityLivingBase owner, EntityLivingBase target, double x, double y, double z, float beamWidth, float headSize, EnergyDisplayData display, EnergyCombatData combat, EnergyHomingData homing, EnergyLightningData lightning, EnergyLifespanData lifespan, boolean anchoredMode) {
        super(world);
        this.initProjectile(owner, target, x, y, z, headSize, display, combat, lightning, lifespan);
        this.homingData = homing != null ? homing.copy() : new EnergyHomingData();
        this.beamWidth = beamWidth;
        this.headSize = headSize;
        this.setBeamMode(EntityAbilityBeam.modeFromAnchored(anchoredMode));
        this.resetHeadOffsets();
        this.trailPoints.add(Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0));
        this.calculateInitialVelocity(owner, target, x, y, z);
    }

    public void setupCharging(EnergyAnchorData anchor, int chargeDuration, float chargeOffsetDistance) {
        this.setupChargingState(anchor, chargeDuration);
        this.chargeOffsetDistance = chargeOffsetDistance;
        this.clearMotion();
    }

    public void setupPreview(EntityLivingBase owner, float beamWidth, float headSize, EnergyDisplayData display, EnergyLightningData lightning, EnergyAnchorData anchor, int chargeDuration, float chargeOffsetDistance) {
        this.setupPreviewState(owner, display, lightning, anchor, chargeDuration);
        this.beamWidth = beamWidth;
        this.headSize = headSize;
        this.setVisualSize(headSize);
        this.chargeOffsetDistance = chargeOffsetDistance;
        this.setChargeOriginFromAnchor(owner, this.anchorData, chargeOffsetDistance);
        this.resetHeadOffsets();
        this.clearMotion();
    }

    public void startPreviewFiring() {
        this.startPreviewFiringDefault();
        this.resetHeadOffsets();
        this.resetTrailStorage();
        this.trailPoints.add(Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0));
        if (this.isFadingMode()) {
            this.trailPointAges.add(0);
        }
    }

    public void startFiring(EntityLivingBase target) {
        this.startMovingTowardTargetFromStartDefault(target);
        this.resetHeadOffsets();
        this.resetTrailStorage();
        this.trailPoints.add(Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0));
        if (this.isFadingMode()) {
            this.trailPointAges.add(0);
        }
    }

    @Override
    protected boolean checkMaxDistance() {
        double distFromOrigin = Math.sqrt(this.headOffsetX * this.headOffsetX + this.headOffsetY * this.headOffsetY + this.headOffsetZ * this.headOffsetZ);
        return distFromOrigin >= (double)this.getMaxDistance();
    }

    @Override
    protected void updateProjectile() {
        this.prevRenderBeamWidth = this.renderBeamWidth;
        this.prevRenderHeadSize = this.renderHeadSize;
        if (this.isCharging()) {
            this.renderBeamWidth = this.beamWidth;
            this.renderHeadSize = this.headSize;
            this.updateCharging();
            return;
        }
        this.renderBeamWidth += (this.beamWidth - this.renderBeamWidth) * 0.15f;
        this.renderHeadSize += (this.headSize - this.renderHeadSize) * 0.15f;
        this.prevHeadOffsetX = this.headOffsetX;
        this.prevHeadOffsetY = this.headOffsetY;
        this.prevHeadOffsetZ = this.headOffsetZ;
        if (this.isFadingMode()) {
            this.ageTrailPoints();
        }
        if (this.previewMode) {
            this.updatePreviewMovement();
            return;
        }
        if (this.field_70170_p.field_72995_K) {
            this.handleClientInterpolation();
            this.headOffsetX = this.field_70165_t - this.startX;
            this.headOffsetY = this.field_70163_u - this.startY;
            this.headOffsetZ = this.field_70161_v - this.startZ;
            this.addTrailPoint();
        } else {
            Entity owner;
            this.updateHoming();
            this.headOffsetX += this.field_70159_w;
            this.headOffsetY += this.field_70181_x;
            this.headOffsetZ += this.field_70179_y;
            if (this.isAnchoredMode() && (owner = this.getOwnerEntity()) != null) {
                this.startX = owner.field_70165_t;
                this.startY = owner.field_70163_u + (double)owner.func_70047_e() * 0.7;
                this.startZ = owner.field_70161_v;
            }
            double headWorldX = this.startX + this.headOffsetX;
            double headWorldY = this.startY + this.headOffsetY;
            double headWorldZ = this.startZ + this.headOffsetZ;
            this.func_70107_b(headWorldX, headWorldY, headWorldZ);
            this.addTrailPoint();
            if (this.field_70173_aa > 2) {
                this.checkBlockCollision(headWorldX, headWorldY, headWorldZ);
                this.checkEntityCollision(headWorldX, headWorldY, headWorldZ);
            }
        }
    }

    @Override
    protected void updateCharging() {
        ++this.chargeTick;
        Entity owner = this.getOwnerEntity();
        if (owner != null) {
            Vec3 pos;
            if (owner instanceof EntityLivingBase) {
                pos = AnchorPointHelper.calculateAnchorPosition((EntityLivingBase)owner, this.anchorData, this.chargeOffsetDistance);
            } else {
                float yaw = (float)Math.toRadians(owner.field_70177_z);
                double offsetX = -Math.sin(yaw) * (double)this.chargeOffsetDistance;
                double offsetZ = Math.cos(yaw) * (double)this.chargeOffsetDistance;
                pos = Vec3.func_72443_a((double)(owner.field_70165_t + offsetX), (double)(owner.field_70163_u + (double)owner.func_70047_e() * 0.7), (double)(owner.field_70161_v + offsetZ));
            }
            this.func_70107_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
            this.startX = pos.field_72450_a;
            this.startY = pos.field_72448_b;
            this.startZ = pos.field_72449_c;
        }
    }

    private void ageTrailPoints() {
        if (this.trailStartIndex >= this.trailPoints.size()) {
            this.resetTrailStorage();
            return;
        }
        for (int i = this.trailStartIndex; i < this.trailPointAges.size(); ++i) {
            this.trailPointAges.set(i, this.trailPointAges.get(i) + 1);
        }
        while (this.trailStartIndex < this.trailPointAges.size() && this.trailPointAges.get(this.trailStartIndex) >= this.trailFadeTime) {
            ++this.trailStartIndex;
        }
        if (this.trailStartIndex >= this.trailPoints.size()) {
            this.resetTrailStorage();
            return;
        }
        if (this.trailStartIndex >= 128 && this.trailStartIndex * 2 >= this.trailPoints.size()) {
            this.trailPoints = new ArrayList<Vec3>(this.trailPoints.subList(this.trailStartIndex, this.trailPoints.size()));
            this.trailPointAges = new ArrayList<Integer>(this.trailPointAges.subList(this.trailStartIndex, this.trailPointAges.size()));
            this.trailStartIndex = 0;
        }
    }

    private void addTrailPoint() {
        if (this.trailStartIndex >= this.trailPoints.size()) {
            this.resetTrailStorage();
        }
        if (this.trailPoints.isEmpty()) {
            this.trailPoints.add(Vec3.func_72443_a((double)this.headOffsetX, (double)this.headOffsetY, (double)this.headOffsetZ));
            if (this.isFadingMode()) {
                this.trailPointAges.add(0);
            }
            return;
        }
        Vec3 lastPoint = this.trailPoints.get(this.trailPoints.size() - 1);
        double dist = Math.sqrt((this.headOffsetX - lastPoint.field_72450_a) * (this.headOffsetX - lastPoint.field_72450_a) + (this.headOffsetY - lastPoint.field_72448_b) * (this.headOffsetY - lastPoint.field_72448_b) + (this.headOffsetZ - lastPoint.field_72449_c) * (this.headOffsetZ - lastPoint.field_72449_c));
        if (dist >= 0.2) {
            this.trailPoints.add(Vec3.func_72443_a((double)this.headOffsetX, (double)this.headOffsetY, (double)this.headOffsetZ));
            if (this.isFadingMode()) {
                this.trailPointAges.add(0);
            }
            if (!this.isFadingMode()) {
                while (this.trailPoints.size() > 200) {
                    this.trailPoints.remove(0);
                }
            }
        }
    }

    private void updatePreviewMovement() {
        Entity owner;
        this.headOffsetX += this.field_70159_w;
        this.headOffsetY += this.field_70181_x;
        this.headOffsetZ += this.field_70179_y;
        if (this.isAnchoredMode() && (owner = this.getOwnerEntity()) != null) {
            this.startX = owner.field_70165_t;
            this.startY = owner.field_70163_u + (double)owner.func_70047_e() * 0.7;
            this.startZ = owner.field_70161_v;
        }
        double headWorldX = this.startX + this.headOffsetX;
        double headWorldY = this.startY + this.headOffsetY;
        double headWorldZ = this.startZ + this.headOffsetZ;
        this.func_70107_b(headWorldX, headWorldY, headWorldZ);
        this.addTrailPoint();
    }

    private void updateFreeAim() {
        Entity owner = this.getOwnerEntity();
        if (!(owner instanceof EntityPlayer)) {
            return;
        }
        Vec3 look = this.getOwnerLookVector();
        if (look == null) {
            return;
        }
        double speed = this.getSpeed();
        double desiredVX = look.field_72450_a * speed;
        double desiredVY = look.field_72448_b * speed;
        double desiredVZ = look.field_72449_c * speed;
        this.field_70159_w += (desiredVX - this.field_70159_w) * (double)0.15f;
        this.field_70181_x += (desiredVY - this.field_70181_x) * (double)0.15f;
        this.field_70179_y += (desiredVZ - this.field_70179_y) * (double)0.15f;
        double vLen = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
        if (vLen > 0.0) {
            this.field_70159_w = this.field_70159_w / vLen * speed;
            this.field_70181_x = this.field_70181_x / vLen * speed;
            this.field_70179_y = this.field_70179_y / vLen * speed;
        }
    }

    @Override
    protected void updateHoming() {
        double dz;
        Entity owner;
        if (this.freeAim && (owner = this.getOwnerEntity()) instanceof EntityPlayer) {
            this.updateFreeAim();
            return;
        }
        if (!this.isHoming()) {
            return;
        }
        Entity target = this.getTargetEntity();
        if (target == null || !target.func_70089_S()) {
            return;
        }
        double headWorldX = this.startX + this.headOffsetX;
        double headWorldY = this.startY + this.headOffsetY;
        double headWorldZ = this.startZ + this.headOffsetZ;
        double dx = target.field_70165_t - headWorldX;
        double dy = target.field_70163_u + (double)target.func_70047_e() - headWorldY;
        double dist = Math.sqrt(dx * dx + dy * dy + (dz = target.field_70161_v - headWorldZ) * dz);
        if (dist <= (double)this.getHomingRange() && dist > 0.0) {
            float effectiveStrength = this.getHomingStrength();
            if (dist < (double)this.getHomingRange() * 0.3) {
                effectiveStrength = Math.min(1.0f, this.getHomingStrength() * 2.5f);
            } else if (dist < (double)this.getHomingRange() * 0.6) {
                effectiveStrength = Math.min(0.8f, this.getHomingStrength() * 1.5f);
            }
            double desiredVX = dx / dist * (double)this.getSpeed();
            double desiredVY = dy / dist * (double)this.getSpeed();
            double desiredVZ = dz / dist * (double)this.getSpeed();
            this.field_70159_w += (desiredVX - this.field_70159_w) * (double)effectiveStrength;
            this.field_70181_x += (desiredVY - this.field_70181_x) * (double)effectiveStrength;
            this.field_70179_y += (desiredVZ - this.field_70179_y) * (double)effectiveStrength;
            double vLen = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
            if (vLen > 0.0) {
                this.field_70159_w = this.field_70159_w / vLen * (double)this.getSpeed();
                this.field_70181_x = this.field_70181_x / vLen * (double)this.getSpeed();
                this.field_70179_y = this.field_70179_y / vLen * (double)this.getSpeed();
            }
        }
    }

    private void checkBlockCollision(double headX, double headY, double headZ) {
        double prevHeadWorldX = this.startX + this.prevHeadOffsetX;
        double prevHeadWorldY = this.startY + this.prevHeadOffsetY;
        double prevHeadWorldZ = this.startZ + this.prevHeadOffsetZ;
        this.handleBlockImpact(this.rayTraceBlocks(prevHeadWorldX, prevHeadWorldY, prevHeadWorldZ, headX, headY, headZ), true);
    }

    private void checkEntityCollision(double headX, double headY, double headZ) {
        double prevHeadWorldX = this.startX + this.prevHeadOffsetX;
        double prevHeadWorldY = this.startY + this.prevHeadOffsetY;
        double prevHeadWorldZ = this.startZ + this.prevHeadOffsetZ;
        double hitSize = Math.max(0.05, (double)this.headSize * 0.5);
        AxisAlignedBB hitBox = AxisAlignedBB.func_72330_a((double)(Math.min(prevHeadWorldX, headX) - hitSize), (double)(Math.min(prevHeadWorldY, headY) - hitSize), (double)(Math.min(prevHeadWorldZ, headZ) - hitSize), (double)(Math.max(prevHeadWorldX, headX) + hitSize), (double)(Math.max(prevHeadWorldY, headY) + hitSize), (double)(Math.max(prevHeadWorldZ, headZ) + hitSize));
        this.processEntitiesInHitBox(hitBox, headX, headY, headZ);
    }

    @Override
    protected String debugLogExtra() {
        double headWorldX = this.startX + this.headOffsetX;
        double headWorldY = this.startY + this.headOffsetY;
        double headWorldZ = this.startZ + this.headOffsetZ;
        return String.format("headOff=(%.2f,%.2f,%.2f) prevHead=(%.2f,%.2f,%.2f) origin=(%.2f,%.2f,%.2f) headWorld=(%.2f,%.2f,%.2f) trail=%d mode=%s anchored=%b beamW=%.2f headSz=%.2f motion=(%.3f,%.3f,%.3f)", this.headOffsetX, this.headOffsetY, this.headOffsetZ, this.prevHeadOffsetX, this.prevHeadOffsetY, this.prevHeadOffsetZ, this.startX, this.startY, this.startZ, headWorldX, headWorldY, headWorldZ, this.getActiveTrailSize(), this.beamMode.name(), this.isAnchoredMode(), Float.valueOf(this.beamWidth), Float.valueOf(this.headSize), this.field_70159_w, this.field_70181_x, this.field_70179_y);
    }

    public float getBeamWidth() {
        return this.beamWidth;
    }

    public float getInterpolatedBeamWidth(float partialTicks) {
        return this.prevRenderBeamWidth + (this.renderBeamWidth - this.prevRenderBeamWidth) * partialTicks;
    }

    public void setBeamWidth(float beamWidth) {
        this.beamWidth = beamWidth;
    }

    public float getHeadSize() {
        return this.headSize;
    }

    public float getInterpolatedHeadSize(float partialTicks) {
        return this.prevRenderHeadSize + (this.renderHeadSize - this.prevRenderHeadSize) * partialTicks;
    }

    public void setHeadSize(float headSize) {
        this.headSize = headSize;
    }

    public void setAttachedToOwner(boolean attached) {
        this.setBeamMode(EntityAbilityBeam.modeFromAnchored(attached));
    }

    public boolean isFreeAim() {
        return this.freeAim;
    }

    public void setFreeAim(boolean freeAim) {
        this.freeAim = freeAim;
    }

    public double getInterpolatedHeadOffsetX(float partialTicks) {
        return this.prevHeadOffsetX + (this.headOffsetX - this.prevHeadOffsetX) * (double)partialTicks;
    }

    public double getInterpolatedHeadOffsetY(float partialTicks) {
        return this.prevHeadOffsetY + (this.headOffsetY - this.prevHeadOffsetY) * (double)partialTicks;
    }

    public double getInterpolatedHeadOffsetZ(float partialTicks) {
        return this.prevHeadOffsetZ + (this.headOffsetZ - this.prevHeadOffsetZ) * (double)partialTicks;
    }

    public List<Vec3> getTrailPoints() {
        if (this.trailStartIndex <= 0) {
            return this.trailPoints;
        }
        if (this.trailStartIndex >= this.trailPoints.size()) {
            return Collections.emptyList();
        }
        return this.trailPoints.subList(this.trailStartIndex, this.trailPoints.size());
    }

    public double getOriginX() {
        return this.startX;
    }

    public double getOriginY() {
        return this.startY;
    }

    public double getOriginZ() {
        return this.startZ;
    }

    public boolean shouldRenderTailOrb() {
        return this.isAnchoredMode();
    }

    public boolean isAttachedToOwner() {
        return this.isAnchoredMode();
    }

    public boolean hasFadingTrail() {
        return this.isFadingMode();
    }

    public List<Integer> getTrailPointAges() {
        if (this.trailStartIndex <= 0) {
            return this.trailPointAges;
        }
        if (this.trailStartIndex >= this.trailPointAges.size()) {
            return Collections.emptyList();
        }
        return this.trailPointAges.subList(this.trailStartIndex, this.trailPointAges.size());
    }

    public int getTrailFadeTime() {
        return this.trailFadeTime;
    }

    @Override
    protected boolean reflectFromBarrier(EntityEnergyBarrier barrier, float reflectStrengthPct) {
        boolean reflected = super.reflectFromBarrier(barrier, reflectStrengthPct);
        if (reflected) {
            this.setBeamMode(BeamMode.FREE_TRAIL);
            this.startX = this.field_70165_t;
            this.startY = this.field_70163_u;
            this.startZ = this.field_70161_v;
            this.resetHeadOffsets();
            this.resetTrailStorage();
            this.freeAim = false;
            if (!barrier.getBarrierData().isTargetOwner()) {
                this.homingData.setHoming(false);
                this.setTargetEntityId(-1);
            }
        }
        return reflected;
    }

    @Override
    protected void writeProjectileReflectionData(NBTTagCompound nbt) {
        nbt.func_74774_a("BeamMode", (byte)this.beamMode.ordinal());
        nbt.func_74780_a("StartX", this.startX);
        nbt.func_74780_a("StartY", this.startY);
        nbt.func_74780_a("StartZ", this.startZ);
    }

    @Override
    protected void applyProjectileReflectionData(NBTTagCompound nbt) {
        byte mode = nbt.func_74771_c("BeamMode");
        this.setBeamMode(mode >= 0 && mode < BeamMode.values().length ? BeamMode.values()[mode] : BeamMode.FREE_TRAIL);
        this.startX = nbt.func_74769_h("StartX");
        this.startY = nbt.func_74769_h("StartY");
        this.startZ = nbt.func_74769_h("StartZ");
        this.resetHeadOffsets();
        this.resetTrailStorage();
        this.homingData.setHoming(false);
        this.freeAim = false;
        this.setTargetEntityId(-1);
    }

    @Override
    protected void writeProjectileClientSyncData(NBTTagCompound nbt) {
        nbt.func_74776_a("BeamWidth", this.beamWidth);
        nbt.func_74776_a("HeadSize", this.headSize);
        nbt.func_74757_a("AttachedToOwner", this.isAttachedToOwner());
    }

    @Override
    protected void applyProjectileClientSyncData(NBTTagCompound nbt) {
        this.beamWidth = nbt.func_74760_g("BeamWidth");
        this.headSize = nbt.func_74760_g("HeadSize");
        this.setAttachedToOwner(nbt.func_74767_n("AttachedToOwner"));
    }

    @Override
    protected void readProjectileNBT(NBTTagCompound nbt) {
        this.beamWidth = EntityAbilityBeam.sanitize(nbt.func_74764_b("BeamWidth") ? nbt.func_74760_g("BeamWidth") : 0.3f, 0.3f, 100.0f);
        this.headSize = EntityAbilityBeam.sanitize(nbt.func_74764_b("HeadSize") ? nbt.func_74760_g("HeadSize") : 0.5f, 0.5f, 100.0f);
        this.renderBeamWidth = this.beamWidth;
        this.renderHeadSize = this.headSize;
        this.prevRenderBeamWidth = this.beamWidth;
        this.prevRenderHeadSize = this.headSize;
        this.headOffsetX = nbt.func_74764_b("HeadOffsetX") ? nbt.func_74769_h("HeadOffsetX") : 0.0;
        this.headOffsetY = nbt.func_74764_b("HeadOffsetY") ? nbt.func_74769_h("HeadOffsetY") : 0.0;
        this.headOffsetZ = nbt.func_74764_b("HeadOffsetZ") ? nbt.func_74769_h("HeadOffsetZ") : 0.0;
        this.prevHeadOffsetX = this.headOffsetX;
        this.prevHeadOffsetY = this.headOffsetY;
        this.prevHeadOffsetZ = this.headOffsetZ;
        this.readChargingNBT(nbt);
        this.chargeOffsetDistance = nbt.func_74764_b("ChargeOffsetDistance") ? nbt.func_74760_g("ChargeOffsetDistance") : 1.0f;
        this.trailFadeTime = nbt.func_74764_b("TrailFadeTime") ? nbt.func_74762_e("TrailFadeTime") : 20;
        this.freeAim = nbt.func_74767_n("FreeAim");
        boolean attachedLegacy = !nbt.func_74764_b("AttachedToOwner") || nbt.func_74767_n("AttachedToOwner");
        BeamMode loadedMode = EntityAbilityBeam.modeFromAnchored(attachedLegacy);
        if (nbt.func_74764_b("BeamMode")) {
            try {
                loadedMode = BeamMode.valueOf(nbt.func_74779_i("BeamMode"));
            }
            catch (IllegalArgumentException ignored) {
                loadedMode = EntityAbilityBeam.modeFromAnchored(attachedLegacy);
            }
        } else if (nbt.func_74764_b("FadeTrail")) {
            loadedMode = nbt.func_74767_n("FadeTrail") ? BeamMode.FREE_TRAIL : EntityAbilityBeam.modeFromAnchored(attachedLegacy);
        }
        this.trailPoints.clear();
        this.trailPointAges.clear();
        this.trailStartIndex = 0;
        if (nbt.func_74764_b("Trail")) {
            NBTTagList trailList = nbt.func_150295_c("Trail", 10);
            for (int i = 0; i < trailList.func_74745_c(); ++i) {
                NBTTagCompound point = trailList.func_150305_b(i);
                this.trailPoints.add(Vec3.func_72443_a((double)point.func_74769_h("X"), (double)point.func_74769_h("Y"), (double)point.func_74769_h("Z")));
            }
        }
        if (nbt.func_74764_b("TrailAges")) {
            int[] ages;
            for (int age : ages = nbt.func_74759_k("TrailAges")) {
                this.trailPointAges.add(age);
            }
        }
        this.setBeamMode(loadedMode);
    }

    @Override
    protected void writeProjectileNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("BeamWidth", this.beamWidth);
        nbt.func_74776_a("HeadSize", this.headSize);
        nbt.func_74780_a("HeadOffsetX", this.headOffsetX);
        nbt.func_74780_a("HeadOffsetY", this.headOffsetY);
        nbt.func_74780_a("HeadOffsetZ", this.headOffsetZ);
        nbt.func_74757_a("AttachedToOwner", this.isAnchoredMode());
        nbt.func_74778_a("BeamMode", this.beamMode.name());
        this.writeChargingNBT(nbt);
        nbt.func_74776_a("ChargeOffsetDistance", this.chargeOffsetDistance);
        nbt.func_74757_a("FreeAim", this.freeAim);
        nbt.func_74757_a("FadeTrail", this.isFadingMode());
        nbt.func_74768_a("TrailFadeTime", this.trailFadeTime);
        NBTTagList trailList = new NBTTagList();
        for (int i = this.trailStartIndex; i < this.trailPoints.size(); ++i) {
            Vec3 point = this.trailPoints.get(i);
            NBTTagCompound pointNbt = new NBTTagCompound();
            pointNbt.func_74780_a("X", point.field_72450_a);
            pointNbt.func_74780_a("Y", point.field_72448_b);
            pointNbt.func_74780_a("Z", point.field_72449_c);
            trailList.func_74742_a((NBTBase)pointNbt);
        }
        nbt.func_74782_a("Trail", (NBTBase)trailList);
        int activeSize = this.isFadingMode() ? Math.max(0, this.trailPoints.size() - this.trailStartIndex) : 0;
        int[] ages = new int[activeSize];
        for (int i = 0; i < activeSize; ++i) {
            int idx = this.trailStartIndex + i;
            ages[i] = idx < this.trailPointAges.size() ? this.trailPointAges.get(idx) : 0;
        }
        nbt.func_74783_a("TrailAges", ages);
    }

    private static enum BeamMode {
        ANCHORED,
        FREE_TRAIL;

    }
}

