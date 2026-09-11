/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package kamkeel.npcs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.EnergyController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityPotionEffect;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyAnchorData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyCombatData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyHomingData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLifespanData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import kamkeel.npcs.controllers.data.ability.enums.AnchorPoint;
import kamkeel.npcs.controllers.data.ability.enums.HitType;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityEnergyProjectile;
import kamkeel.npcs.entity.EntityEnergyAbility;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyExplosion;
import kamkeel.npcs.network.packets.data.energy.ProjectileClientSyncPacket;
import kamkeel.npcs.network.packets.data.energy.ProjectileReflectPacket;
import kamkeel.npcs.network.packets.data.energyexplosion.EnergyExplosionSpawnPacket;
import kamkeel.npcs.util.AnchorPointHelper;
import kamkeel.npcs.util.AttributeAttackUtil;
import kamkeel.npcs.util.CNPCDebug;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import noppes.npcs.EventHooks;
import noppes.npcs.NpcDamageSource;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.config.ConfigEnergy;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.NpcAPI;

public abstract class EntityEnergyProjectile
extends EntityEnergyAbility {
    private static final Map<Integer, List<WeakReference<EntityEnergyProjectile>>> activeProjectiles = new HashMap<Integer, List<WeakReference<EntityEnergyProjectile>>>();
    protected float size = 1.0f;
    protected EnergyCombatData combatData = new EnergyCombatData();
    protected List<AbilityPotionEffect> effects = new ArrayList<AbilityPotionEffect>();
    protected EnergyAnchorData anchorData = new EnergyAnchorData(AnchorPoint.FRONT);
    protected EnergyLifespanData lifespanData = new EnergyLifespanData();
    protected long deathWorldTime = -1L;
    protected EnergyHomingData homingData = new EnergyHomingData();
    protected double startX;
    protected double startY;
    protected double startZ;
    protected int targetEntityId = -1;
    protected boolean reflected = false;
    protected boolean hasHit = false;
    protected int hitCount = 0;
    protected final Set<Integer> hitOnceEntities = new HashSet<Integer>();
    protected final Map<Integer, Integer> lastHitTickByEntity = new HashMap<Integer, Integer>();
    protected static final int BARRIER_IMPACT_PAUSE_TICKS = 10;
    protected static final int BARRIER_BREAK_SPARK_TICKS = 10;
    protected static final int DW_BARRIER_SPARK_TICKS = 21;
    protected static final int DW_SYNC_INNER_COLOR = 22;
    protected static final int DW_SYNC_OUTER_COLOR = 23;
    protected int barrierImpactPauseTicks = 0;
    protected boolean barrierImpactDestroyOnResume = false;
    protected double pausedMotionX;
    protected double pausedMotionY;
    protected double pausedMotionZ;
    protected float targetSize = 1.0f;
    public float prevRotationValX;
    public float prevRotationValY;
    public float prevRotationValZ;
    public float rotationValX;
    public float rotationValY;
    public float rotationValZ;
    protected float renderCurrentSize;
    protected float prevRenderSize;
    protected double interpTargetX;
    protected double interpTargetY;
    protected double interpTargetZ;
    protected double interpTargetMotionX;
    protected double interpTargetMotionY;
    protected double interpTargetMotionZ;
    protected int interpSteps;
    private float damageMultiplier = 1.0f;

    public static void trackProjectile(EntityEnergyProjectile projectile) {
        int ownerId = projectile.getOwnerEntityId();
        if (ownerId < 0) {
            return;
        }
        List<WeakReference<EntityEnergyProjectile>> refs = activeProjectiles.get(ownerId);
        if (refs == null) {
            refs = new ArrayList<WeakReference<EntityEnergyProjectile>>();
            activeProjectiles.put(ownerId, refs);
        }
        refs.add(new WeakReference<EntityEnergyProjectile>(projectile));
    }

    public static void clearAllProjectiles() {
        activeProjectiles.clear();
    }

    public static List<EntityEnergyProjectile> getActiveProjectiles(int ownerEntityId) {
        List<WeakReference<EntityEnergyProjectile>> refs = activeProjectiles.get(ownerEntityId);
        if (refs == null) {
            return Collections.emptyList();
        }
        ArrayList<EntityEnergyProjectile> result = new ArrayList<EntityEnergyProjectile>();
        Iterator<WeakReference<EntityEnergyProjectile>> it = refs.iterator();
        while (it.hasNext()) {
            EntityEnergyProjectile p = (EntityEnergyProjectile)((Object)it.next().get());
            if (p == null || p.field_70128_L) {
                it.remove();
                continue;
            }
            result.add(p);
        }
        if (refs.isEmpty()) {
            activeProjectiles.remove(ownerEntityId);
        }
        return result;
    }

    public EntityEnergyProjectile(World world) {
        super(world);
        this.func_70105_a(0.5f, 0.5f);
        this.field_70129_M = this.field_70131_O / 2.0f;
        this.field_70145_X = false;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(21, (Object)0);
        this.field_70180_af.func_75682_a(22, (Object)0xFFFFFF);
        this.field_70180_af.func_75682_a(23, (Object)0xFFFFFF);
    }

    protected void initProjectile(EntityLivingBase owner, EntityLivingBase target, double x, double y, double z, float size, EnergyDisplayData display, EnergyCombatData combat, EnergyLightningData lightning, EnergyLifespanData lifespan) {
        this.func_70107_b(x, y, z);
        this.startX = x;
        this.startY = y;
        this.startZ = z;
        this.ownerEntityId = owner != null ? owner.func_145782_y() : -1;
        this.targetEntityId = target != null ? target.func_145782_y() : -1;
        this.size = size;
        this.displayData = display != null ? display.copy() : new EnergyDisplayData();
        this.combatData = combat != null ? combat.copy() : new EnergyCombatData();
        this.lifespanData = lifespan != null ? lifespan.copy() : new EnergyLifespanData();
        this.lightningData = lightning != null ? lightning.copy() : new EnergyLightningData();
        this.syncProjectileColorWatchers();
        this.renderCurrentSize = size;
        this.prevRenderSize = size;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        double d1 = this.field_70121_D.func_72320_b() * 4.0;
        return distance < (d1 *= 128.0) * d1;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.prevRotationValX = this.rotationValX;
        this.prevRotationValY = this.rotationValY;
        this.prevRotationValZ = this.rotationValZ;
        this.prevRenderSize = this.renderCurrentSize;
        if (!this.previewMode) {
            super.func_70071_h_();
            if (this.field_70173_aa == 1 && !this.field_70170_p.field_72995_K) {
                EntityEnergyProjectile.trackProjectile(this);
            }
        } else {
            ++this.field_70173_aa;
            if (this.isCharging() && this.chargeDuration > 0 && this.chargeTick > this.chargeDuration + 3) {
                this.func_70106_y();
                return;
            }
            if (this.field_70173_aa > 1200) {
                this.func_70106_y();
                return;
            }
        }
        if (!this.previewMode) {
            this.syncDisplayColorsFromWatchers();
        }
        this.updateRotation();
        this.renderCurrentSize = this.isCharging() ? this.size : (this.renderCurrentSize += (this.size - this.renderCurrentSize) * 0.15f);
        if (!this.previewMode && !this.field_70170_p.field_72995_K) {
            this.tickBarrierSparkTimer();
        }
        if (!this.previewMode) {
            Entity owner;
            if (this.ownerEntityId >= 0 && this.field_70173_aa > 5 && (owner = this.field_70170_p.func_73045_a(this.ownerEntityId)) != null) {
                if (owner.field_70128_L) {
                    this.func_70106_y();
                    return;
                }
                if (owner instanceof EntityNPCInterface && ((EntityNPCInterface)owner).isKilled()) {
                    this.func_70106_y();
                    return;
                }
            }
            if (this.isCharging() && this.chargeTick > this.chargeDuration + 60) {
                this.func_70106_y();
                return;
            }
            if (this.field_70173_aa > 1200) {
                this.func_70106_y();
                return;
            }
            if (this.deathWorldTime < 0L && this.field_70170_p != null && !this.isCharging()) {
                this.deathWorldTime = this.field_70170_p.func_82737_E() + (long)this.getMaxLifetime();
            }
            if (this.deathWorldTime > 0L && this.field_70170_p.func_82737_E() >= this.deathWorldTime) {
                if (!this.field_70170_p.field_72995_K) {
                    EventHooks.onEnergyProjectileExpired(this);
                }
                this.func_70106_y();
                return;
            }
            if (this.checkMaxDistance()) {
                if (!this.field_70170_p.field_72995_K) {
                    EventHooks.onEnergyProjectileExpired(this);
                }
                this.func_70106_y();
                return;
            }
            if (this.hasHit) {
                this.func_70106_y();
                return;
            }
        }
        if (!this.previewMode && !this.field_70170_p.field_72995_K && !this.isCharging() && this.tickBarrierImpactPause()) {
            return;
        }
        if (!this.previewMode && !this.field_70170_p.field_72995_K && !this.isCharging() && this.checkBarrierCollision()) {
            return;
        }
        this.updateProjectile();
        if (!this.previewMode && !this.field_70170_p.field_72995_K) {
            EventHooks.onEnergyProjectileTick(this, this.field_70173_aa);
        }
        this.debugLogTick();
    }

    protected void updateRotation() {
        this.rotationValX += this.getRotationSpeed() * 0.7f;
        this.rotationValY += this.getRotationSpeed();
        this.rotationValZ += this.getRotationSpeed() * 0.5f;
        if (this.rotationValX > 360.0f) {
            this.rotationValX -= 360.0f;
        }
        if (this.rotationValY > 360.0f) {
            this.rotationValY -= 360.0f;
        }
        if (this.rotationValZ > 360.0f) {
            this.rotationValZ -= 360.0f;
        }
    }

    protected void debugLogTick() {
        String extra;
        boolean isClient = this.field_70170_p.field_72995_K;
        if (isClient ? !CNPCDebug.isClientEnabled("energy") : !CNPCDebug.isServerEnabled("energy")) {
            return;
        }
        String className = ((Object)((Object)this)).getClass().getSimpleName();
        boolean dwCharging = this.isCharging();
        boolean localCharging = this.charging;
        String base = String.format("[%s id=%d tick=%d] pos=(%.2f,%.2f,%.2f) prev=(%.2f,%.2f,%.2f) size=%.3f renderSize=%.3f prevRenderSize=%.3f charging(dw)=%b charging(local)=%b chargeTick=%d/%d chargeProgress=%.3f", className, this.func_145782_y(), this.field_70173_aa, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70169_q, this.field_70167_r, this.field_70166_s, Float.valueOf(this.size), Float.valueOf(this.renderCurrentSize), Float.valueOf(this.prevRenderSize), dwCharging, localCharging, this.chargeTick, this.chargeDuration, Float.valueOf(this.getChargeProgress()));
        if (dwCharging != localCharging) {
            base = base + " !!CHARGE_MISMATCH!!";
        }
        if (isClient) {
            base = base + String.format(" interp(steps=%d target=(%.2f,%.2f,%.2f))", this.interpSteps, this.interpTargetX, this.interpTargetY, this.interpTargetZ);
        }
        String full = (extra = this.debugLogExtra()).isEmpty() ? base : base + " " + extra;
        CNPCDebug.log("energy", isClient, full);
    }

    protected String debugLogExtra() {
        return "";
    }

    protected boolean checkMaxDistance() {
        double distTraveled = Math.sqrt((this.field_70165_t - this.startX) * (this.field_70165_t - this.startX) + (this.field_70163_u - this.startY) * (this.field_70163_u - this.startY) + (this.field_70161_v - this.startZ) * (this.field_70161_v - this.startZ));
        return distTraveled >= (double)this.getMaxDistance();
    }

    protected abstract void updateProjectile();

    protected boolean checkBarrierCollision() {
        List<EntityEnergyBarrier> barriers = EntityEnergyBarrier.getActiveBarriers(this.field_70170_p);
        for (EntityEnergyBarrier barrier : barriers) {
            float damage;
            EntityEnergyBarrier.ProjectileHitOutcome hitOutcome;
            double maxRange;
            double dz;
            double dy;
            double dx;
            double distSq;
            if (barrier.field_70128_L || (distSq = (dx = barrier.field_70165_t - this.field_70165_t) * dx + (dy = barrier.field_70163_u - this.field_70163_u) * dy + (dz = barrier.field_70161_v - this.field_70161_v) * dz) > (maxRange = (double)barrier.getMaxExtent() + 5.0) * maxRange || !barrier.isIncomingProjectile(this) || (hitOutcome = barrier.onProjectileHitResolved(this, damage = this.getModifiedDamage())) == null || hitOutcome.result == EntityEnergyBarrier.ProjectileHitResult.PASS) continue;
            return this.handleBarrierHitOutcome(barrier, hitOutcome, damage);
        }
        return false;
    }

    protected boolean handleBarrierHitOutcome(EntityEnergyBarrier barrier, EntityEnergyBarrier.ProjectileHitOutcome hitOutcome, float fullDamage) {
        if (hitOutcome == null || hitOutcome.result == EntityEnergyBarrier.ProjectileHitResult.PASS) {
            return false;
        }
        if (hitOutcome.result == EntityEnergyBarrier.ProjectileHitResult.BROKEN) {
            if (hitOutcome.remainingProjectileDamage <= 0.0f) {
                this.damageMultiplier = 0.0f;
                this.setBarrierSparkTicks(Math.max(this.getBarrierSparkTicks(), 10));
                this.hasHit = true;
                return true;
            }
            if (fullDamage > 0.0f) {
                this.damageMultiplier *= hitOutcome.remainingProjectileDamage / fullDamage;
            }
            this.setCombatDamage(hitOutcome.remainingProjectileDamage);
            this.setBarrierSparkTicks(Math.max(this.getBarrierSparkTicks(), 10));
            return false;
        }
        if (hitOutcome.shouldReflect()) {
            if (!this.reflectFromBarrier(barrier, hitOutcome.reflectStrengthPct)) {
                this.beginBarrierImpactPause(true, barrier);
            } else {
                this.sendReflectionSync();
            }
            return true;
        }
        this.beginBarrierImpactPause(true, barrier);
        return true;
    }

    protected boolean reflectFromBarrier(EntityEnergyBarrier barrier, float reflectStrengthPct) {
        double rz;
        double ry;
        double rx;
        double rLenSq;
        if (barrier == null) {
            return false;
        }
        double vx = this.field_70159_w;
        double vy = this.field_70181_x;
        double vz = this.field_70179_y;
        double vLenSq = vx * vx + vy * vy + vz * vz;
        if (vLenSq < 1.0E-8) {
            return false;
        }
        double[] normal = this.getBarrierImpactNormal(barrier, vx, vy, vz);
        if (normal == null) {
            return false;
        }
        double dot = vx * normal[0] + vy * normal[1] + vz * normal[2];
        if (dot > 0.0) {
            normal[0] = -normal[0];
            normal[1] = -normal[1];
            normal[2] = -normal[2];
            dot = vx * normal[0] + vy * normal[1] + vz * normal[2];
        }
        if ((rLenSq = (rx = vx - 2.0 * dot * normal[0]) * rx + (ry = vy - 2.0 * dot * normal[1]) * ry + (rz = vz - 2.0 * dot * normal[2]) * rz) < 1.0E-8) {
            rx = -vx;
            ry = -vy;
            rz = -vz;
            rLenSq = rx * rx + ry * ry + rz * rz;
        }
        if (ry < -0.06) {
            double speed = Math.sqrt(Math.max(rLenSq, 1.0E-8));
            double newLen = Math.sqrt(rx * rx + (ry = Math.max(0.08, Math.abs(ry) * 0.35)) * ry + rz * rz);
            if (newLen > 1.0E-8) {
                double scale = speed / newLen;
                rx *= scale;
                ry *= scale;
                rz *= scale;
            }
        }
        this.snapOutsideBarrierForPause(barrier);
        this.field_70159_w = rx;
        this.field_70181_x = ry;
        this.field_70179_y = rz;
        this.pausedMotionX = rx;
        this.pausedMotionY = ry;
        this.pausedMotionZ = rz;
        this.barrierImpactPauseTicks = 0;
        this.barrierImpactDestroyOnResume = false;
        this.startX = this.field_70165_t;
        this.startY = this.field_70163_u;
        this.startZ = this.field_70161_v;
        this.deathWorldTime = -1L;
        if (this.sourceAbility instanceof AbilityEnergyProjectile) {
            ((AbilityEnergyProjectile)this.sourceAbility).detachEntity(this);
        }
        this.sourceAbility = null;
        int originalOwnerId = this.ownerEntityId;
        Entity barrierOwner = barrier.getOwnerEntity();
        if (barrierOwner != null) {
            this.setOwnerEntityId(barrierOwner.func_145782_y());
            EntityEnergyProjectile.trackProjectile(this);
        }
        if (barrier.getBarrierData().isTargetOwner() && originalOwnerId != -1) {
            this.setTargetEntityId(originalOwnerId);
        } else {
            this.setTargetEntityId(-1);
        }
        this.setInnerColor(barrier.getInnerColor());
        this.setOuterColor(barrier.getOuterColor());
        float clampedStrength = Math.max(0.0f, Math.min(100.0f, reflectStrengthPct));
        float reductionFactor = 1.0f - clampedStrength / 100.0f;
        float reducedDamage = this.getDamage() * reductionFactor;
        this.setCombatDamage(Math.max(0.0f, reducedDamage));
        this.damageMultiplier *= reductionFactor;
        this.hitOnceEntities.clear();
        this.lastHitTickByEntity.clear();
        this.hitCount = 0;
        this.reflected = true;
        return true;
    }

    public NBTTagCompound writeReflectionData() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("OwnerId", this.ownerEntityId);
        nbt.func_74780_a("PosX", this.field_70165_t);
        nbt.func_74780_a("PosY", this.field_70163_u);
        nbt.func_74780_a("PosZ", this.field_70161_v);
        nbt.func_74780_a("MotionX", this.field_70159_w);
        nbt.func_74780_a("MotionY", this.field_70181_x);
        nbt.func_74780_a("MotionZ", this.field_70179_y);
        nbt.func_74768_a("InnerColor", this.getInnerColor());
        nbt.func_74768_a("OuterColor", this.getOuterColor());
        nbt.func_74757_a("Reflected", this.reflected);
        this.writeProjectileReflectionData(nbt);
        return nbt;
    }

    public void applyReflectionData(NBTTagCompound nbt) {
        this.ownerEntityId = nbt.func_74762_e("OwnerId");
        double px = nbt.func_74769_h("PosX");
        double py = nbt.func_74769_h("PosY");
        double pz = nbt.func_74769_h("PosZ");
        this.func_70107_b(px, py, pz);
        this.field_70159_w = nbt.func_74769_h("MotionX");
        this.field_70181_x = nbt.func_74769_h("MotionY");
        this.field_70179_y = nbt.func_74769_h("MotionZ");
        this.setInnerColor(nbt.func_74762_e("InnerColor"));
        this.setOuterColor(nbt.func_74762_e("OuterColor"));
        this.reflected = nbt.func_74767_n("Reflected");
        this.syncPositionStateToCurrent(true);
        this.hitOnceEntities.clear();
        this.lastHitTickByEntity.clear();
        this.hitCount = 0;
        this.applyProjectileReflectionData(nbt);
    }

    protected void writeProjectileReflectionData(NBTTagCompound nbt) {
    }

    protected void applyProjectileReflectionData(NBTTagCompound nbt) {
    }

    protected void sendReflectionSync() {
        if (this.field_70170_p == null || this.field_70170_p.field_72995_K) {
            return;
        }
        ProjectileReflectPacket.sendToTracking(this, this.writeReflectionData());
    }

    public NBTTagCompound writeClientSyncData() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74780_a("PosX", this.field_70165_t);
        nbt.func_74780_a("PosY", this.field_70163_u);
        nbt.func_74780_a("PosZ", this.field_70161_v);
        nbt.func_74780_a("MotionX", this.field_70159_w);
        nbt.func_74780_a("MotionY", this.field_70181_x);
        nbt.func_74780_a("MotionZ", this.field_70179_y);
        nbt.func_74776_a("Size", this.size);
        nbt.func_74776_a("RotationSpeed", this.displayData.getRotationSpeed());
        nbt.func_74776_a("InnerAlpha", this.displayData.getInnerAlpha());
        nbt.func_74757_a("OuterColorEnabled", this.displayData.isOuterColorEnabled());
        nbt.func_74776_a("OuterColorWidth", this.displayData.getOuterColorWidth());
        nbt.func_74776_a("OuterColorAlpha", this.displayData.getOuterColorAlpha());
        nbt.func_74757_a("LightningEffect", this.hasLightningEffect());
        nbt.func_74776_a("LightningDensity", this.getLightningDensity());
        nbt.func_74776_a("LightningRadius", this.getLightningRadius());
        nbt.func_74768_a("LightningFadeTime", this.getLightningFadeTime());
        nbt.func_74776_a("Speed", this.getSpeed());
        nbt.func_74757_a("Homing", this.isHoming());
        nbt.func_74776_a("HomingStrength", this.getHomingStrength());
        nbt.func_74776_a("HomingRange", this.getHomingRange());
        this.writeProjectileClientSyncData(nbt);
        return nbt;
    }

    public void applyClientSyncData(NBTTagCompound nbt) {
        this.func_70107_b(nbt.func_74769_h("PosX"), nbt.func_74769_h("PosY"), nbt.func_74769_h("PosZ"));
        this.field_70159_w = nbt.func_74769_h("MotionX");
        this.field_70181_x = nbt.func_74769_h("MotionY");
        this.field_70179_y = nbt.func_74769_h("MotionZ");
        this.syncPositionStateToCurrent(true);
        this.size = nbt.func_74760_g("Size");
        this.displayData.setRotationSpeed(nbt.func_74760_g("RotationSpeed"));
        this.displayData.setInnerAlpha(nbt.func_74760_g("InnerAlpha"));
        this.displayData.setOuterColorEnabled(nbt.func_74767_n("OuterColorEnabled"));
        this.displayData.setOuterColorWidth(nbt.func_74760_g("OuterColorWidth"));
        this.displayData.setOuterColorAlpha(nbt.func_74760_g("OuterColorAlpha"));
        this.setLightningEffect(nbt.func_74767_n("LightningEffect"));
        this.setLightningDensity(nbt.func_74760_g("LightningDensity"));
        this.setLightningRadius(nbt.func_74760_g("LightningRadius"));
        this.setLightningFadeTime(nbt.func_74762_e("LightningFadeTime"));
        this.setSpeed(nbt.func_74760_g("Speed"));
        this.setHomingEnabled(nbt.func_74767_n("Homing"));
        this.setHomingStrength(nbt.func_74760_g("HomingStrength"));
        this.setHomingRange(nbt.func_74760_g("HomingRange"));
        this.applyProjectileClientSyncData(nbt);
    }

    protected void writeProjectileClientSyncData(NBTTagCompound nbt) {
    }

    protected void applyProjectileClientSyncData(NBTTagCompound nbt) {
    }

    public void sendClientSync() {
        if (this.field_70170_p == null || this.field_70170_p.field_72995_K) {
            return;
        }
        ProjectileClientSyncPacket.sendToTracking(this, this.writeClientSyncData());
    }

    protected double[] getBarrierImpactNormal(EntityEnergyBarrier barrier, double velocityX, double velocityY, double velocityZ) {
        return barrier.getSurfaceNormal(this.field_70165_t, this.field_70163_u, this.field_70161_v, velocityX, velocityY, velocityZ);
    }

    protected void beginBarrierImpactPause(boolean destroyOnResume, EntityEnergyBarrier barrier) {
        if (this.barrierImpactPauseTicks <= 0) {
            this.pausedMotionX = this.field_70159_w;
            this.pausedMotionY = this.field_70181_x;
            this.pausedMotionZ = this.field_70179_y;
        }
        this.barrierImpactDestroyOnResume = destroyOnResume;
        this.barrierImpactPauseTicks = 10;
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        int sparkTicks = this.getBarrierSparkTicks();
        if (sparkTicks < 10) {
            this.setBarrierSparkTicks(10);
        }
    }

    protected float getBarrierPauseOutsideDistance() {
        return Math.max(0.06f, Math.min(0.22f, this.size * 0.18f + 0.03f));
    }

    protected void snapOutsideBarrierForPause(EntityEnergyBarrier barrier) {
        if (barrier == null) {
            return;
        }
        float bias = this.getBarrierPauseOutsideDistance();
        double[] point = barrier.getOutsideSurfacePoint(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70159_w, this.field_70181_x, this.field_70179_y, bias);
        this.func_70107_b(point[0], point[1], point[2]);
    }

    protected boolean tickBarrierImpactPause() {
        if (this.barrierImpactPauseTicks <= 0) {
            return false;
        }
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        --this.barrierImpactPauseTicks;
        if (this.barrierImpactPauseTicks <= 0) {
            if (this.barrierImpactDestroyOnResume) {
                this.hasHit = true;
                this.func_70106_y();
            } else {
                this.field_70159_w = this.pausedMotionX;
                this.field_70181_x = this.pausedMotionY;
                this.field_70179_y = this.pausedMotionZ;
            }
            this.barrierImpactDestroyOnResume = false;
        }
        return true;
    }

    protected void tickBarrierSparkTimer() {
        int sparkTicks = this.getBarrierSparkTicks();
        if (sparkTicks > 0) {
            this.setBarrierSparkTicks(sparkTicks - 1);
        }
    }

    protected void setBarrierSparkTicks(int ticks) {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(21, (Object)Math.max(0, ticks));
        }
    }

    public int getBarrierSparkTicks() {
        if (this.field_70180_af == null) {
            return 0;
        }
        return Math.max(0, this.field_70180_af.func_75679_c(21));
    }

    protected void syncProjectileColorWatchers() {
        if (this.field_70180_af == null) {
            return;
        }
        this.field_70180_af.func_75692_b(22, (Object)this.displayData.getInnerColor());
        this.field_70180_af.func_75692_b(23, (Object)this.displayData.getOuterColor());
    }

    protected void syncDisplayColorsFromWatchers() {
        if (this.field_70180_af == null) {
            return;
        }
        this.displayData.setInnerColor(this.field_70180_af.func_75679_c(22));
        this.displayData.setOuterColor(this.field_70180_af.func_75679_c(23));
    }

    @Override
    public void setInnerColor(int color) {
        super.setInnerColor(color);
        if (!this.previewMode) {
            this.syncProjectileColorWatchers();
        }
    }

    @Override
    public void setOuterColor(int color) {
        super.setOuterColor(color);
        if (!this.previewMode) {
            this.syncProjectileColorWatchers();
        }
    }

    protected float getModifiedDamage() {
        Entity owner;
        float damage = this.getDamage();
        if (this.sourceAbility != null && (owner = this.getOwnerEntity()) instanceof EntityLivingBase) {
            damage = AbilityController.Instance.fireModifyProjectileDamage(this.sourceAbility, (EntityLivingBase)owner, damage);
        }
        return damage;
    }

    public void func_70056_a(double x, double y, double z, float yaw, float pitch, int posRotationIncrements) {
        int steps = Math.max(1, posRotationIncrements);
        double dx = x - this.field_70165_t;
        double dy = y - this.field_70163_u;
        double dz = z - this.field_70161_v;
        double dotToCurrentMotion = dx * this.field_70159_w + dy * this.field_70181_x + dz * this.field_70179_y;
        if (this.field_70170_p != null && this.field_70170_p.field_72995_K) {
            double distSq = dx * dx + dy * dy + dz * dz;
            if (this.field_70173_aa <= 24 && distSq <= 4.0 && dotToCurrentMotion < -0.0025) {
                return;
            }
            if (this.field_70173_aa <= 2 || distSq > 16.0) {
                this.func_70107_b(x, y, z);
                this.syncPositionState(x, y, z, true);
                return;
            }
        }
        this.interpTargetX = x;
        this.interpTargetY = y;
        this.interpTargetZ = z;
        if (this.field_70170_p != null && this.field_70170_p.field_72995_K && this.field_70173_aa <= 24 && dotToCurrentMotion < 0.0) {
            this.interpTargetMotionX = this.field_70159_w;
            this.interpTargetMotionY = this.field_70181_x;
            this.interpTargetMotionZ = this.field_70179_y;
        } else {
            this.interpTargetMotionX = dx / (double)steps;
            this.interpTargetMotionY = dy / (double)steps;
            this.interpTargetMotionZ = dz / (double)steps;
        }
        this.interpSteps = steps;
    }

    public void func_70016_h(double motionX, double motionY, double motionZ) {
        this.interpTargetMotionX = motionX;
        this.interpTargetMotionY = motionY;
        this.interpTargetMotionZ = motionZ;
        this.field_70159_w = motionX;
        this.field_70181_x = motionY;
        this.field_70179_y = motionZ;
    }

    protected void handleClientInterpolation() {
        if (this.interpSteps > 0) {
            double dx = this.interpTargetX - this.field_70165_t;
            double dy = this.interpTargetY - this.field_70163_u;
            double dz = this.interpTargetZ - this.field_70161_v;
            double distSq = dx * dx + dy * dy + dz * dz;
            if (distSq < 1.0E-4) {
                this.func_70107_b(this.interpTargetX, this.interpTargetY, this.interpTargetZ);
                this.interpSteps = 0;
                return;
            }
            double newX = this.field_70165_t + dx / (double)this.interpSteps;
            double newY = this.field_70163_u + dy / (double)this.interpSteps;
            double newZ = this.field_70161_v + dz / (double)this.interpSteps;
            this.field_70159_w += (this.interpTargetMotionX - this.field_70159_w) / (double)this.interpSteps;
            this.field_70181_x += (this.interpTargetMotionY - this.field_70181_x) / (double)this.interpSteps;
            this.field_70179_y += (this.interpTargetMotionZ - this.field_70179_y) / (double)this.interpSteps;
            this.func_70107_b(newX, newY, newZ);
            --this.interpSteps;
        }
    }

    protected boolean applyDamage(EntityLivingBase target) {
        if (this.previewMode) {
            return false;
        }
        return this.applyDamage(target, this.getDamage(), this.getKnockback());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected boolean applyDamage(EntityLivingBase target, float dmg, float kb) {
        double dz;
        double dx;
        double len;
        boolean allowSecondaryEffects;
        float kbUp;
        double dz2;
        double dx2;
        if (this.previewMode) {
            return false;
        }
        if (target == null || this.shouldIgnoreEntity((Entity)target)) {
            return false;
        }
        if (!this.field_70170_p.field_72995_K) {
            float result = EventHooks.onEnergyProjectileEntityImpact(this, target, dmg);
            if (result < 0.0f) {
                return false;
            }
            dmg = result;
        }
        Entity owner = this.getOwnerEntity();
        boolean ignoreIFrames = this.isIgnoreIFrames();
        boolean handled = false;
        boolean defaultDamageApplied = false;
        if (this.sourceAbility != null && owner instanceof EntityLivingBase) {
            dx2 = target.field_70165_t - this.field_70165_t;
            dz2 = target.field_70161_v - this.field_70161_v;
            kbUp = this.getKnockbackUp() > 0.0f ? this.getKnockbackUp() : 0.1f;
            handled = AbilityController.Instance.fireOnAbilityDamage(this.sourceAbility, (EntityLivingBase)owner, target, dmg, kb, kbUp, dx2, dz2, this.damageMultiplier);
        }
        if (!handled && this.customDamageData != null && owner instanceof EntityLivingBase) {
            dx2 = target.field_70165_t - this.field_70165_t;
            dz2 = target.field_70161_v - this.field_70161_v;
            kbUp = this.getKnockbackUp() > 0.0f ? this.getKnockbackUp() : 0.1f;
            handled = EnergyController.Instance.fireOnEnergyDamage(this, (EntityLivingBase)owner, target, dmg, kb, kbUp, dx2, dz2, this.damageMultiplier, this.customDamageData);
        }
        int previousHurtResistantTime = Ability.clearHurtResistanceIfNeeded(target, ignoreIFrames);
        try {
            if (!handled) {
                float finalDmg = dmg;
                if (this.magicData != null && !this.magicData.isEmpty() && owner instanceof EntityLivingBase) {
                    finalDmg = AttributeAttackUtil.calculateAbilityDamage((EntityLivingBase)owner, target, dmg, this.magicData);
                }
                defaultDamageApplied = owner instanceof EntityNPCInterface ? target.func_70097_a((DamageSource)new NpcDamageSource("npc_ability", (Entity)((EntityNPCInterface)owner)), finalDmg) : (owner instanceof EntityPlayer ? target.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)owner)), finalDmg) : (owner instanceof EntityLivingBase ? target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)((EntityLivingBase)owner)), finalDmg) : target.func_70097_a((DamageSource)new NpcDamageSource("npc_ability", null), finalDmg)));
            }
        }
        finally {
            Ability.restoreHurtResistanceIfNeeded(target, ignoreIFrames, previousHurtResistantTime);
        }
        boolean bl = allowSecondaryEffects = handled || dmg <= 0.0f || defaultDamageApplied;
        if (!allowSecondaryEffects) {
            return false;
        }
        if (kb > 0.0f && (len = Math.sqrt((dx = target.field_70165_t - this.field_70165_t) * dx + (dz = target.field_70161_v - this.field_70161_v) * dz)) > 0.0) {
            double kbX = dx / len * (double)kb * 0.5;
            double kbZ = dz / len * (double)kb * 0.5;
            double kbY = this.getKnockbackUp() > 0.0f ? (double)this.getKnockbackUp() : 0.1;
            target.func_70024_g(kbX, kbY, kbZ);
            target.field_70133_I = true;
        }
        this.applyEffects(target);
        return true;
    }

    protected void applyEffects(EntityLivingBase target) {
        for (AbilityPotionEffect effect : this.effects) {
            effect.apply(target);
        }
    }

    public void setAnchorData(EnergyAnchorData anchor) {
        this.anchorData = anchor != null ? anchor.copy() : new EnergyAnchorData(AnchorPoint.FRONT);
    }

    public void setEffects(List<AbilityPotionEffect> effects) {
        if (effects == null || effects.isEmpty()) {
            this.effects = new ArrayList<AbilityPotionEffect>();
        } else {
            this.effects = new ArrayList<AbilityPotionEffect>(effects.size());
            for (AbilityPotionEffect effect : effects) {
                this.effects.add(effect.copy());
            }
        }
    }

    protected boolean doExplosion() {
        if (this.previewMode) {
            return false;
        }
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        float explosionRad = this.getExplosionRadius();
        if (Float.isNaN(explosionRad) || explosionRad <= 0.0f) {
            return false;
        }
        double explosionRadSq = explosionRad * explosionRad;
        float baseDamage = this.getDamage();
        float baseKnockback = this.getKnockback();
        float damageFalloff = this.getExplosionDamageFalloff();
        this.spawnExplosionRenderEntity(explosionRad);
        this.spawnExplosionVisuals(explosionRad);
        this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "random.explode", 1.0f, 1.0f);
        Entity owner = this.getOwnerEntity();
        AxisAlignedBB explosionBox = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)explosionRad), (double)(this.field_70163_u - (double)explosionRad), (double)(this.field_70161_v - (double)explosionRad), (double)(this.field_70165_t + (double)explosionRad), (double)(this.field_70163_u + (double)explosionRad), (double)(this.field_70161_v + (double)explosionRad));
        List targets = this.field_70170_p.func_72872_a(EntityLivingBase.class, explosionBox);
        boolean anyDamaged = false;
        for (EntityLivingBase target : targets) {
            double closestZ;
            double dz;
            double closestY;
            double dy;
            double closestX;
            double dx;
            double distSq;
            if (target == owner || this.shouldIgnoreExplosionTarget(target) || target.field_70121_D == null || (distSq = (dx = (closestX = Math.max(target.field_70121_D.field_72340_a, Math.min(this.field_70165_t, target.field_70121_D.field_72336_d))) - this.field_70165_t) * dx + (dy = (closestY = Math.max(target.field_70121_D.field_72338_b, Math.min(this.field_70163_u, target.field_70121_D.field_72337_e))) - this.field_70163_u) * dy + (dz = (closestZ = Math.max(target.field_70121_D.field_72339_c, Math.min(this.field_70161_v, target.field_70121_D.field_72334_f))) - this.field_70161_v) * dz) > explosionRadSq) continue;
            float falloff = 1.0f;
            if (damageFalloff != 0.0f && distSq > 0.0) {
                float dist = (float)Math.sqrt(distSq);
                falloff = 1.0f - dist / explosionRad * damageFalloff;
            }
            if (!this.applyDamage(target, baseDamage * falloff, baseKnockback * falloff)) continue;
            anyDamaged = true;
        }
        this.tryDestroyTerrain(explosionRad);
        return anyDamaged;
    }

    protected boolean shouldIgnoreExplosionTarget(EntityLivingBase target) {
        if (target == null) {
            return true;
        }
        return this.shouldIgnoreEntity((Entity)target);
    }

    protected Vec3 closestPointOnBoundingBox(AxisAlignedBB box, double x, double y, double z) {
        if (box == null) {
            return null;
        }
        double closestX = Math.max(box.field_72340_a, Math.min(x, box.field_72336_d));
        double closestY = Math.max(box.field_72338_b, Math.min(y, box.field_72337_e));
        double closestZ = Math.max(box.field_72339_c, Math.min(z, box.field_72334_f));
        return Vec3.func_72443_a((double)closestX, (double)closestY, (double)closestZ);
    }

    protected void spawnExplosionRenderEntity(float explosionRad) {
        if (this.field_70170_p == null || this.field_70170_p.field_72995_K) {
            return;
        }
        float renderRad = Math.max(0.75f, Math.min(explosionRad, 15.0f));
        EntityEnergyExplosion fx = new EntityEnergyExplosion(this.field_70170_p, this, renderRad);
        EnergyExplosionSpawnPacket.sendToTracking(this.getExplosionVisualInstanceId(), fx, this);
    }

    protected String getExplosionVisualInstanceId() {
        return "energy_explosion_" + UUID.randomUUID();
    }

    protected void spawnExplosionVisuals(float explosionRad) {
        if (this.field_70170_p == null) {
            return;
        }
        float visualRad = Math.max(0.75f, Math.min(explosionRad, 15.0f));
        int coreBursts = Math.max(1, Math.min(4, Math.round(visualRad * 0.35f)));
        int shellCount = Math.max(1, Math.min(3, Math.round(visualRad / 3.5f) + 1));
        this.emitRandomBurstParticle("largeexplode", coreBursts, (double)visualRad * 0.1, (double)visualRad * 0.08, (double)visualRad * 0.1, 0.01);
        for (int i = 1; i <= shellCount; ++i) {
            double frac = (double)i / (double)shellCount;
            double shellRadius = (double)visualRad * (0.35 + 0.65 * frac);
            this.spawnVoxelShell(shellRadius, i, visualRad);
        }
        int smokeCount = Math.max(6, Math.min(40, Math.round(visualRad * 4.5f)));
        this.emitRandomBurstParticle("smoke", smokeCount, (double)visualRad * 0.45, (double)visualRad * 0.3, (double)visualRad * 0.45, 0.01);
        this.spawnVoxelDebris(visualRad);
    }

    protected void emitRandomBurstParticle(String particle, int count, double spreadX, double spreadY, double spreadZ, double speed) {
        if (this.field_70170_p == null || count <= 0 || particle == null || particle.isEmpty()) {
            return;
        }
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < count; ++i) {
                double px = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * 2.0 * spreadX;
                double py = this.field_70163_u + (this.field_70146_Z.nextDouble() - 0.5) * 2.0 * spreadY;
                double pz = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * 2.0 * spreadZ;
                double mx = this.field_70146_Z.nextGaussian() * speed;
                double my = this.field_70146_Z.nextGaussian() * speed;
                double mz = this.field_70146_Z.nextGaussian() * speed;
                this.field_70170_p.func_72869_a(particle, px, py, pz, mx, my, mz);
            }
            return;
        }
        if (this.field_70170_p instanceof WorldServer) {
            ((WorldServer)this.field_70170_p).func_147487_a(particle, this.field_70165_t, this.field_70163_u, this.field_70161_v, count, spreadX, spreadY, spreadZ, speed);
        }
    }

    protected void emitVoxelParticle(String particle, double x, double y, double z, double motionX, double motionY, double motionZ) {
        if (this.field_70170_p == null || particle == null || particle.isEmpty()) {
            return;
        }
        if (this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72869_a(particle, x, y, z, motionX, motionY, motionZ);
            return;
        }
        if (this.field_70170_p instanceof WorldServer) {
            ((WorldServer)this.field_70170_p).func_147487_a(particle, x, y, z, 0, motionX, motionY, motionZ, 1.0);
        }
    }

    protected void spawnVoxelShell(double shellRadius, int shellIndex, float visualRad) {
        int steps = Math.max(2, Math.min(4, (int)Math.ceil(shellRadius * 0.55)));
        double cell = shellRadius / (double)steps;
        double jitter = Math.min(0.15, cell * 0.2);
        double shellSpeed = 0.03 + shellRadius / Math.max(1.0, (double)visualRad) * 0.05;
        for (int ix = -steps; ix <= steps; ++ix) {
            for (int iy = -steps; iy <= steps; ++iy) {
                for (int iz = -steps; iz <= steps; ++iz) {
                    int edge = Math.max(Math.abs(ix), Math.max(Math.abs(iy), Math.abs(iz)));
                    if (edge != steps || (ix + iy + iz + shellIndex & 1) != 0) continue;
                    double px = this.field_70165_t + (double)ix * cell + (this.field_70146_Z.nextDouble() - 0.5) * jitter;
                    double py = this.field_70163_u + (double)iy * cell + (this.field_70146_Z.nextDouble() - 0.5) * jitter;
                    double pz = this.field_70161_v + (double)iz * cell + (this.field_70146_Z.nextDouble() - 0.5) * jitter;
                    double nx = ix;
                    double ny = iy;
                    double nz = iz;
                    double len = Math.sqrt(nx * nx + ny * ny + nz * nz);
                    if (len < 0.001) {
                        ny = 1.0;
                        len = 1.0;
                    }
                    this.emitVoxelParticle("explode", px, py, pz, (nx /= len) * shellSpeed, (ny /= len) * shellSpeed, (nz /= len) * shellSpeed);
                    if ((ix + iy + iz + shellIndex) % 3 != 0) continue;
                    this.emitVoxelParticle("smoke", px, py, pz, nx * shellSpeed * 0.35, ny * shellSpeed * 0.35, nz * shellSpeed * 0.35);
                }
            }
        }
    }

    protected void spawnVoxelDebris(float visualRad) {
        if (this.field_70170_p == null) {
            return;
        }
        int count = Math.max(4, Math.min(24, Math.round(visualRad * 2.5f)));
        for (int i = 0; i < count; ++i) {
            int blockId;
            int bz;
            int by;
            double ox = (this.field_70146_Z.nextDouble() - 0.5) * (double)visualRad * 1.2;
            double oy = (this.field_70146_Z.nextDouble() - 0.35) * (double)visualRad * 0.9;
            double oz = (this.field_70146_Z.nextDouble() - 0.5) * (double)visualRad * 1.2;
            int bx = MathHelper.func_76128_c((double)(this.field_70165_t + ox));
            Block block = this.field_70170_p.func_147439_a(bx, by = MathHelper.func_76128_c((double)(this.field_70163_u + oy)), bz = MathHelper.func_76128_c((double)(this.field_70161_v + oz)));
            if (block == null || block == Blocks.field_150350_a || (blockId = Block.func_149682_b((Block)block)) <= 0) continue;
            int meta = this.field_70170_p.func_72805_g(bx, by, bz);
            String particle = "blockcrack_" + blockId + "_" + Math.max(0, meta);
            this.emitVoxelParticle(particle, this.field_70165_t + ox * 0.55, this.field_70163_u + oy * 0.55, this.field_70161_v + oz * 0.55, ox * 0.03, oy * 0.03, oz * 0.03);
        }
    }

    protected void tryDestroyTerrain(float explosionRad) {
        if (this.field_70170_p == null || this.field_70170_p.field_72995_K) {
            return;
        }
        if (!ConfigEnergy.EnableEnergyExplosionBlockDamage) {
            return;
        }
        if (!this.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
            return;
        }
        float terrainRad = Math.max(0.5f, Math.min(explosionRad, 15.0f));
        int minX = MathHelper.func_76128_c((double)(this.field_70165_t - (double)terrainRad));
        int maxX = MathHelper.func_76128_c((double)(this.field_70165_t + (double)terrainRad));
        int minY = MathHelper.func_76128_c((double)(this.field_70163_u - (double)terrainRad));
        int maxY = MathHelper.func_76128_c((double)(this.field_70163_u + (double)terrainRad));
        int minZ = MathHelper.func_76128_c((double)(this.field_70161_v - (double)terrainRad));
        int maxZ = MathHelper.func_76128_c((double)(this.field_70161_v + (double)terrainRad));
        int worldMaxY = Math.max(0, this.field_70170_p.func_72940_L() - 1);
        if (maxY < 0 || minY > worldMaxY) {
            return;
        }
        float resistanceCutoff = Math.max(4.0f, terrainRad * 6.0f);
        Explosion context = new Explosion(this.field_70170_p, (Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, terrainRad);
        context.field_77286_a = false;
        context.field_82755_b = true;
        int lastChunkX = Integer.MIN_VALUE;
        int lastChunkZ = Integer.MIN_VALUE;
        boolean lastChunkLoaded = false;
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    float resistance;
                    Material material;
                    Block block;
                    double normalized;
                    double cx = (double)x + 0.5 - this.field_70165_t;
                    double cy = (double)y + 0.5 - this.field_70163_u;
                    double cz = (double)z + 0.5 - this.field_70161_v;
                    double chebyshev = Math.max(Math.abs(cx), Math.max(Math.abs(cy), Math.abs(cz)));
                    if (chebyshev > (double)terrainRad || (normalized = chebyshev / (double)terrainRad) > 0.72 && this.field_70146_Z.nextDouble() < (normalized - 0.72) * 1.4 || y < 0 || y > worldMaxY) continue;
                    int chunkX = x >> 4;
                    int chunkZ = z >> 4;
                    if (chunkX != lastChunkX || chunkZ != lastChunkZ) {
                        lastChunkX = chunkX;
                        lastChunkZ = chunkZ;
                        lastChunkLoaded = this.field_70170_p.func_72863_F().func_73149_a(chunkX, chunkZ);
                    }
                    if (!lastChunkLoaded || (block = this.field_70170_p.func_147439_a(x, y, z)) == null || block == Blocks.field_150350_a || block == Blocks.field_150357_h || (material = block.func_149688_o()) == null || material == Material.field_151579_a || (resistance = block.getExplosionResistance((Entity)this, this.field_70170_p, x, y, z, this.field_70165_t, this.field_70163_u, this.field_70161_v)) > resistanceCutoff) continue;
                    try {
                        block.onBlockExploded(this.field_70170_p, x, y, z, context);
                        continue;
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            }
        }
    }

    protected void snapToLookVector() {
        this.setLookVectorLaunchPosition(true);
    }

    protected boolean beginLookVectorLaunch(boolean resetInterpolationState) {
        this.setCharging(false);
        Entity owner = this.getOwnerEntity();
        boolean positioned = false;
        if (owner instanceof EntityLivingBase && this.shouldSnapLaunchToLookVector((EntityLivingBase)owner)) {
            positioned = this.setLookVectorLaunchPosition((EntityLivingBase)owner, this.getOwnerLookVector(), true);
        }
        this.syncStartPositionToCurrent();
        if (resetInterpolationState) {
            this.syncPositionStateToCurrent(true);
        }
        return positioned;
    }

    protected boolean beginLookVectorLaunch(EntityLivingBase owner, Vec3 look, boolean resetInterpolationState) {
        this.setCharging(false);
        boolean positioned = this.shouldSnapLaunchToLookVector(owner) && this.setLookVectorLaunchPosition(owner, look, true);
        this.syncStartPositionToCurrent();
        if (resetInterpolationState) {
            this.syncPositionStateToCurrent(true);
        }
        return positioned;
    }

    protected boolean shouldSnapLaunchToLookVector(EntityLivingBase owner) {
        if (this.anchorData.launchFromAnchor) {
            return false;
        }
        return owner instanceof EntityPlayer;
    }

    protected void startPreviewFiringDefault() {
        this.beginLookVectorLaunch(true);
        this.setMotionAlongLookVectorOrFallback(this.getSpeed(), this.getSpeed(), 0.0, 0.0);
    }

    protected void startMovingTowardTargetDefault(EntityLivingBase target) {
        this.beginLookVectorLaunch(false);
        this.setMotionTowardTargetOrLookVector(target, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.getSpeed(), this.getSpeed(), 0.0, 0.0);
    }

    protected void startMovingTowardTargetFromStartDefault(EntityLivingBase target) {
        this.beginLookVectorLaunch(false);
        this.setMotionTowardTargetOrLookVector(target, this.startX, this.startY, this.startZ, this.getSpeed(), this.getSpeed(), 0.0, 0.0);
    }

    protected void startMovingAlongLookVectorDefault() {
        this.beginLookVectorLaunch(false);
        this.setMotionAlongLookVectorOrFallback(this.getSpeed(), this.getSpeed(), 0.0, 0.0);
    }

    protected Vec3 getOwnerLookVector() {
        Entity owner = this.getOwnerEntity();
        if (!(owner instanceof EntityLivingBase)) {
            return null;
        }
        Vec3 look = owner.func_70040_Z();
        if (look != null) {
            return look;
        }
        float yaw = (float)Math.toRadians(owner.field_70177_z);
        float pitch = (float)Math.toRadians(owner.field_70125_A);
        return Vec3.func_72443_a((double)(-Math.sin(yaw) * Math.cos(pitch)), (double)(-Math.sin(pitch)), (double)(Math.cos(yaw) * Math.cos(pitch)));
    }

    protected boolean setLookVectorLaunchPosition(boolean syncPositionState) {
        Entity owner = this.getOwnerEntity();
        if (!(owner instanceof EntityLivingBase)) {
            return false;
        }
        Vec3 look = this.getOwnerLookVector();
        if (look == null) {
            return false;
        }
        return this.setLookVectorLaunchPosition((EntityLivingBase)owner, look, syncPositionState);
    }

    protected boolean setLookVectorLaunchPosition(EntityLivingBase owner, Vec3 look, boolean syncPositionState) {
        if (owner == null || look == null) {
            return false;
        }
        double originX = owner.field_70165_t;
        double originY = owner.field_70163_u + (double)owner.func_70047_e();
        double originZ = owner.field_70161_v;
        float frontDist = this.computeLaunchFrontDistance(owner, look, originX, originY, originZ);
        double newX = originX + look.field_72450_a * (double)frontDist;
        double newY = originY + look.field_72448_b * (double)frontDist;
        double newZ = originZ + look.field_72449_c * (double)frontDist;
        this.func_70107_b(newX, newY, newZ);
        if (syncPositionState) {
            this.syncPositionState(newX, newY, newZ, true);
        }
        return true;
    }

    protected float getLaunchClearanceRadius() {
        return Math.max(0.1f, this.size * 0.5f);
    }

    private float computeLaunchFrontDistance(EntityLivingBase owner, Vec3 look, double originX, double originY, double originZ) {
        double tz;
        double ty;
        float baseline = this.size * 0.4f;
        float clearance = this.getLaunchClearanceRadius() + 0.05f;
        AxisAlignedBB bb = owner.field_70121_D;
        if (bb == null) {
            return Math.max(baseline, owner.field_70130_N * 0.5f + clearance);
        }
        double dx = look.field_72450_a;
        double dy = look.field_72448_b;
        double dz = look.field_72449_c;
        double tx = this.computeRayExitT(originX, dx, bb.field_72340_a, bb.field_72336_d);
        double tExit = Math.min(tx, Math.min(ty = this.computeRayExitT(originY, dy, bb.field_72338_b, bb.field_72337_e), tz = this.computeRayExitT(originZ, dz, bb.field_72339_c, bb.field_72334_f)));
        if (Double.isInfinite(tExit) || tExit < 0.0) {
            return Math.max(baseline, owner.field_70130_N * 0.5f + clearance);
        }
        return (float)Math.max((double)baseline, tExit + (double)clearance);
    }

    private double computeRayExitT(double origin, double dir, double min, double max) {
        double EPS = 1.0E-6;
        if (dir > 1.0E-6) {
            return (max - origin) / dir;
        }
        if (dir < -1.0E-6) {
            return (min - origin) / dir;
        }
        return Double.POSITIVE_INFINITY;
    }

    protected boolean setMotionAlongLookVector(float speed) {
        Vec3 look = this.getOwnerLookVector();
        if (look == null) {
            return false;
        }
        this.field_70159_w = look.field_72450_a * (double)speed;
        this.field_70181_x = look.field_72448_b * (double)speed;
        this.field_70179_y = look.field_72449_c * (double)speed;
        return true;
    }

    protected void setMotionAlongLookVectorOrFallback(float speed, double fallbackX, double fallbackY, double fallbackZ) {
        if (this.anchorData.launchFromAnchor && this.setMotionTowardLookTarget(speed)) {
            return;
        }
        if (!this.setMotionAlongLookVector(speed)) {
            this.field_70159_w = fallbackX;
            this.field_70181_x = fallbackY;
            this.field_70179_y = fallbackZ;
        }
    }

    protected boolean setMotionTowardLookTarget(float speed) {
        double targetZ;
        double targetY;
        double targetX;
        Vec3 end;
        Entity owner = this.getOwnerEntity();
        if (!(owner instanceof EntityLivingBase)) {
            return false;
        }
        Vec3 look = this.getOwnerLookVector();
        if (look == null) {
            return false;
        }
        EntityLivingBase livingOwner = (EntityLivingBase)owner;
        double eyeX = livingOwner.field_70165_t;
        double eyeY = livingOwner.field_70163_u + (double)livingOwner.func_70047_e();
        double eyeZ = livingOwner.field_70161_v;
        double maxDist = 200.0;
        Vec3 start = Vec3.func_72443_a((double)eyeX, (double)eyeY, (double)eyeZ);
        MovingObjectPosition hit = this.field_70170_p.func_72933_a(start, end = Vec3.func_72443_a((double)(eyeX + look.field_72450_a * maxDist), (double)(eyeY + look.field_72448_b * maxDist), (double)(eyeZ + look.field_72449_c * maxDist)));
        if (hit != null && hit.field_72307_f != null) {
            targetX = hit.field_72307_f.field_72450_a;
            targetY = hit.field_72307_f.field_72448_b;
            targetZ = hit.field_72307_f.field_72449_c;
        } else {
            targetX = eyeX + look.field_72450_a * maxDist;
            targetY = eyeY + look.field_72448_b * maxDist;
            targetZ = eyeZ + look.field_72449_c * maxDist;
        }
        double dx = targetX - this.field_70165_t;
        double dy = targetY - this.field_70163_u;
        double dz = targetZ - this.field_70161_v;
        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len <= 1.0E-4) {
            return false;
        }
        this.field_70159_w = dx / len * (double)speed;
        this.field_70181_x = dy / len * (double)speed;
        this.field_70179_y = dz / len * (double)speed;
        return true;
    }

    protected boolean setMotionTowardTarget(EntityLivingBase target, double sourceX, double sourceY, double sourceZ, float speed) {
        double dz;
        if (target == null) {
            return false;
        }
        double dx = target.field_70165_t - sourceX;
        double dy = target.field_70163_u + (double)target.func_70047_e() - sourceY;
        double len = Math.sqrt(dx * dx + dy * dy + (dz = target.field_70161_v - sourceZ) * dz);
        if (len <= 1.0E-4) {
            return false;
        }
        this.field_70159_w = dx / len * (double)speed;
        this.field_70181_x = dy / len * (double)speed;
        this.field_70179_y = dz / len * (double)speed;
        return true;
    }

    protected void setMotionTowardTargetOrLookVector(EntityLivingBase target, double sourceX, double sourceY, double sourceZ, float speed, double fallbackX, double fallbackY, double fallbackZ) {
        if (!this.setMotionTowardTarget(target, sourceX, sourceY, sourceZ, speed)) {
            this.setMotionAlongLookVectorOrFallback(speed, fallbackX, fallbackY, fallbackZ);
        }
    }

    protected void syncStartPositionToCurrent() {
        this.startX = this.field_70165_t;
        this.startY = this.field_70163_u;
        this.startZ = this.field_70161_v;
    }

    protected void syncPositionState(double x, double y, double z, boolean resetInterpolation) {
        this.field_70169_q = x;
        this.field_70167_r = y;
        this.field_70166_s = z;
        this.field_70142_S = x;
        this.field_70137_T = y;
        this.field_70136_U = z;
        if (resetInterpolation) {
            this.interpTargetX = x;
            this.interpTargetY = y;
            this.interpTargetZ = z;
            this.interpTargetMotionX = this.field_70159_w;
            this.interpTargetMotionY = this.field_70181_x;
            this.interpTargetMotionZ = this.field_70179_y;
            this.interpSteps = 0;
        }
    }

    public void syncPositionStateToCurrent(boolean resetInterpolation) {
        this.syncPositionState(this.field_70165_t, this.field_70163_u, this.field_70161_v, resetInterpolation);
    }

    protected void setupChargingState(EnergyAnchorData anchor, int chargeDuration) {
        this.setCharging(true);
        this.chargeDuration = chargeDuration;
        this.chargeTick = 0;
        this.anchorData = anchor;
    }

    protected void setupPreviewState(EntityLivingBase owner, EnergyDisplayData display, EnergyLightningData lightning, EnergyAnchorData anchor, int chargeDuration) {
        this.setPreviewMode(true);
        this.setPreviewOwner(owner);
        this.displayData = display != null ? display.copy() : new EnergyDisplayData();
        this.lightningData = lightning != null ? lightning.copy() : new EnergyLightningData();
        this.setupChargingState(anchor, chargeDuration);
    }

    protected void setVisualSize(float value) {
        this.size = value;
        this.renderCurrentSize = value;
        this.prevRenderSize = value;
    }

    protected void setChargeOrigin(Vec3 pos) {
        if (pos == null) {
            return;
        }
        this.func_70107_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
        this.syncPositionState(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c, true);
        this.startX = pos.field_72450_a;
        this.startY = pos.field_72448_b;
        this.startZ = pos.field_72449_c;
    }

    protected void setChargeOriginFromAnchor(EntityLivingBase owner, EnergyAnchorData anchor) {
        if (owner == null || anchor == null) {
            return;
        }
        this.setChargeOrigin(AnchorPointHelper.calculateAnchorPosition(owner, anchor));
    }

    protected void setChargeOriginFromAnchor(EntityLivingBase owner, EnergyAnchorData anchor, float offsetDistance) {
        if (owner == null || anchor == null) {
            return;
        }
        this.setChargeOrigin(AnchorPointHelper.calculateAnchorPosition(owner, anchor, offsetDistance));
    }

    protected void clearMotion() {
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
    }

    public void setupCharging(EnergyAnchorData anchor, int chargeDuration) {
        this.setupChargingState(anchor, chargeDuration);
        this.targetSize = this.size;
        this.setVisualSize(0.01f);
        this.clearMotion();
    }

    protected void updateCharging() {
        ++this.chargeTick;
        float progress = this.getChargeProgress();
        this.size = this.targetSize * progress;
        Entity owner = this.getOwnerEntity();
        if (owner instanceof EntityLivingBase) {
            Vec3 pos = AnchorPointHelper.calculateAnchorPosition((EntityLivingBase)owner, this.anchorData);
            this.func_70107_b(pos.field_72450_a, pos.field_72448_b, pos.field_72449_c);
        }
    }

    protected void updateHoming() {
        double dz;
        if (!this.isHoming()) {
            return;
        }
        Entity target = this.getTargetEntity();
        if (target == null || !target.func_70089_S()) {
            return;
        }
        double dx = target.field_70165_t - this.field_70165_t;
        double dy = target.field_70163_u + (double)target.func_70047_e() - this.field_70163_u;
        double dist = Math.sqrt(dx * dx + dy * dy + (dz = target.field_70161_v - this.field_70161_v) * dz);
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

    protected void calculateInitialVelocity(EntityLivingBase owner, EntityLivingBase target, double x, double y, double z) {
        if (target != null) {
            double dz;
            double dx = target.field_70165_t - x;
            double dy = target.field_70163_u + (double)target.func_70047_e() - y;
            double len = Math.sqrt(dx * dx + dy * dy + (dz = target.field_70161_v - z) * dz);
            if (len > 0.0) {
                this.field_70159_w = dx / len * (double)this.getSpeed();
                this.field_70181_x = dy / len * (double)this.getSpeed();
                this.field_70179_y = dz / len * (double)this.getSpeed();
            }
        } else {
            float yaw = (float)Math.toRadians(owner.field_70177_z);
            float pitch = (float)Math.toRadians(owner.field_70125_A);
            this.field_70159_w = -Math.sin(yaw) * Math.cos(pitch) * (double)this.getSpeed();
            this.field_70181_x = -Math.sin(pitch) * (double)this.getSpeed();
            this.field_70179_y = Math.cos(yaw) * Math.cos(pitch) * (double)this.getSpeed();
        }
    }

    protected void readChargingNBT(NBTTagCompound nbt) {
        boolean isChargingVal;
        this.charging = isChargingVal = nbt.func_74764_b("Charging") && nbt.func_74767_n("Charging");
        this.field_70180_af.func_75692_b(20, (Object)((byte)(isChargingVal ? 1 : 0)));
        this.chargeDuration = nbt.func_74764_b("ChargeDuration") ? nbt.func_74762_e("ChargeDuration") : 40;
        this.chargeTick = nbt.func_74764_b("ChargeTick") ? nbt.func_74762_e("ChargeTick") : 0;
        this.targetSize = nbt.func_74764_b("TargetSize") ? nbt.func_74760_g("TargetSize") : this.size;
    }

    protected void writeChargingNBT(NBTTagCompound nbt) {
        nbt.func_74757_a("Charging", this.isCharging());
        nbt.func_74768_a("ChargeDuration", this.chargeDuration);
        nbt.func_74768_a("ChargeTick", this.chargeTick);
        nbt.func_74776_a("TargetSize", this.targetSize);
    }

    public IEntity getOwner() {
        if (this.previewMode) {
            return null;
        }
        if (this.getOwnerEntity() == null) {
            return null;
        }
        return NpcAPI.Instance().getIEntity(this.getOwnerEntity());
    }

    public int getTargetEntityId() {
        return this.targetEntityId;
    }

    public void setTargetEntityId(int id) {
        this.targetEntityId = id;
    }

    public Entity getTargetEntity() {
        if (this.targetEntityId == -1) {
            return null;
        }
        return this.field_70170_p.func_73045_a(this.targetEntityId);
    }

    public IEntity getTarget() {
        if (this.getTargetEntity() == null) {
            return null;
        }
        return NpcAPI.Instance().getIEntity(this.getTargetEntity());
    }

    protected boolean shouldIgnoreEntity(Entity entity) {
        Entity owner = this.getOwnerEntity();
        if (entity == owner) {
            return true;
        }
        if (entity instanceof EntityEnergyProjectile) {
            EntityEnergyProjectile other = (EntityEnergyProjectile)entity;
            if (other.ownerEntityId == this.ownerEntityId) {
                return true;
            }
        }
        if (entity instanceof EntityNPCInterface) {
            EntityNPCInterface targetNpc = (EntityNPCInterface)entity;
            if (targetNpc.faction.isPassive) {
                return true;
            }
            if (owner instanceof EntityNPCInterface) {
                EntityNPCInterface ownerNpc = (EntityNPCInterface)owner;
                if (ownerNpc.faction.id == targetNpc.faction.id) {
                    return true;
                }
            }
            if (owner instanceof EntityPlayer && targetNpc.faction.isFriendlyToPlayer((EntityPlayer)owner)) {
                return true;
            }
        }
        if (owner instanceof EntityPlayer && entity instanceof EntityPlayer) {
            Party party;
            EntityPlayer ownerPlayer = (EntityPlayer)owner;
            EntityPlayer targetPlayer = (EntityPlayer)entity;
            PlayerData ownerData = PlayerData.get(ownerPlayer);
            PlayerData targetData = PlayerData.get(targetPlayer);
            if (ownerData.partyUUID != null && ownerData.partyUUID.equals(targetData.partyUUID) && (party = PartyController.Instance().getParty(ownerData.partyUUID)) != null && !party.friendlyFire()) {
                return true;
            }
        }
        return false;
    }

    public float getSize() {
        return this.size;
    }

    public void setProjectileSize(float size) {
        this.size = size;
        this.renderCurrentSize = size;
        this.prevRenderSize = size;
    }

    public void setStartPosition(double x, double y, double z) {
        this.startX = x;
        this.startY = y;
        this.startZ = z;
    }

    public float getInterpolatedRotationX(float partialTicks) {
        return this.prevRotationValX + (this.rotationValX - this.prevRotationValX) * partialTicks;
    }

    public float getInterpolatedRotationY(float partialTicks) {
        return this.prevRotationValY + (this.rotationValY - this.prevRotationValY) * partialTicks;
    }

    public float getInterpolatedRotationZ(float partialTicks) {
        return this.prevRotationValZ + (this.rotationValZ - this.prevRotationValZ) * partialTicks;
    }

    public float getInterpolatedSize(float partialTicks) {
        return this.prevRenderSize + (this.renderCurrentSize - this.prevRenderSize) * partialTicks;
    }

    public float getMaxDistance() {
        return this.lifespanData.getMaxDistance();
    }

    public void setMaxDistance(float distance) {
        this.lifespanData.setMaxDistance(distance);
    }

    public int getMaxLifetime() {
        return this.lifespanData.getMaxLifetime();
    }

    public void setMaxLifetime(int ticks) {
        this.lifespanData.setMaxLifetime(ticks);
    }

    public float getDamageMultiplier() {
        return this.damageMultiplier;
    }

    public float getDamage() {
        return this.combatData.getDamage();
    }

    public void setCombatDamage(float damage) {
        this.combatData.setDamage(damage);
    }

    public float getKnockback() {
        return this.combatData.knockback;
    }

    public void setCombatKnockback(float knockback) {
        this.combatData.setKnockback(knockback);
    }

    public float getKnockbackUp() {
        return this.combatData.knockbackUp;
    }

    public void setCombatKnockbackUp(float knockbackUp) {
        this.combatData.setKnockbackUp(knockbackUp);
    }

    public boolean isExplosive() {
        return this.combatData.isExplosive();
    }

    public void setExplosive(boolean explosive) {
        this.combatData.setExplosive(explosive);
    }

    public float getExplosionRadius() {
        return this.combatData.getExplosionRadius();
    }

    public void setExplosionRadius(float radius) {
        this.combatData.setExplosionRadius(radius);
    }

    public float getExplosionDamageFalloff() {
        return this.combatData.explosionDamageFalloff;
    }

    public void setExplosionDamageFalloff(float falloff) {
        this.combatData.setExplosionDamageFalloff(falloff);
    }

    public int getHitType() {
        return this.combatData.hitType.ordinal();
    }

    public void setHitType(int hitType) {
        this.combatData.hitType = HitType.fromOrdinal(hitType);
    }

    public int getMultiHitDelayTicks() {
        return this.combatData.multiHitDelayTicks;
    }

    public void setMultiHitDelayTicks(int delayTicks) {
        this.combatData.multiHitDelayTicks = Math.max(1, delayTicks);
    }

    public int getMaxHits() {
        return this.combatData.getMaxHits();
    }

    public void setMaxHits(int maxHits) {
        this.combatData.setMaxHits(maxHits);
    }

    protected boolean canHitEntityNow(EntityLivingBase entity) {
        if (entity == null) {
            return false;
        }
        if (this.combatData.hitType != HitType.SINGLE && this.hasReachedMaxHits()) {
            return false;
        }
        int entityId = entity.func_145782_y();
        switch (this.combatData.hitType) {
            case PIERCE: {
                return !this.hitOnceEntities.contains(entityId);
            }
            case MULTI: {
                Integer lastHitTick = this.lastHitTickByEntity.get(entityId);
                if (lastHitTick == null) {
                    return true;
                }
                int delay = Math.max(1, this.combatData.multiHitDelayTicks);
                return this.field_70173_aa - lastHitTick >= delay;
            }
        }
        return !this.hasHit;
    }

    protected void recordEntityHit(EntityLivingBase entity) {
        if (entity == null) {
            return;
        }
        int entityId = entity.func_145782_y();
        this.hitOnceEntities.add(entityId);
        this.lastHitTickByEntity.put(entityId, this.field_70173_aa);
        ++this.hitCount;
    }

    protected boolean hasReachedMaxHits() {
        if (this.combatData.hitType == HitType.SINGLE) {
            return false;
        }
        return this.hitCount >= this.combatData.getMaxHits();
    }

    protected boolean shouldTerminateAfterHit() {
        if (this.combatData.hitType == HitType.SINGLE) {
            return true;
        }
        return this.hasReachedMaxHits();
    }

    protected MovingObjectPosition rayTraceBlocks(double fromX, double fromY, double fromZ, double toX, double toY, double toZ) {
        Vec3 currentPos = Vec3.func_72443_a((double)fromX, (double)fromY, (double)fromZ);
        Vec3 nextPos = Vec3.func_72443_a((double)toX, (double)toY, (double)toZ);
        return this.field_70170_p.func_147447_a(currentPos, nextPos, false, true, false);
    }

    protected boolean handleBlockImpact(MovingObjectPosition blockHit, boolean explodeAtHitPoint) {
        if (blockHit == null || blockHit.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) {
            return false;
        }
        if (!this.field_70170_p.field_72995_K) {
            EventHooks.onEnergyProjectileBlockImpact(this, blockHit.field_72311_b, blockHit.field_72312_c, blockHit.field_72309_d);
        }
        this.hasHit = true;
        if (this.isExplosive()) {
            if (explodeAtHitPoint && blockHit.field_72307_f != null) {
                this.field_70165_t = blockHit.field_72307_f.field_72450_a;
                this.field_70163_u = blockHit.field_72307_f.field_72448_b;
                this.field_70161_v = blockHit.field_72307_f.field_72449_c;
            }
            this.doExplosion();
        }
        this.func_70106_y();
        return true;
    }

    protected boolean processEntityHit(EntityLivingBase entity, double impactX, double impactY, double impactZ) {
        boolean successfulHit;
        if (entity == null || this.shouldIgnoreEntity((Entity)entity) || !this.canHitEntityNow(entity)) {
            return false;
        }
        if (this.isExplosive()) {
            Vec3 impactPoint = this.resolveEntityImpactPoint(entity, impactX, impactY, impactZ);
            double oldX = this.field_70165_t;
            double oldY = this.field_70163_u;
            double oldZ = this.field_70161_v;
            this.field_70165_t = impactPoint.field_72450_a;
            this.field_70163_u = impactPoint.field_72448_b;
            this.field_70161_v = impactPoint.field_72449_c;
            successfulHit = this.doExplosion();
            this.field_70165_t = oldX;
            this.field_70163_u = oldY;
            this.field_70161_v = oldZ;
        } else {
            successfulHit = this.applyDamage(entity);
        }
        if (successfulHit) {
            this.recordEntityHit(entity);
        }
        if (successfulHit && this.shouldTerminateAfterHit() || !successfulHit && this.combatData.hitType == HitType.SINGLE) {
            this.hasHit = true;
            this.func_70106_y();
            return true;
        }
        return false;
    }

    protected Vec3 resolveEntityImpactPoint(EntityLivingBase entity, double impactX, double impactY, double impactZ) {
        if (entity == null || entity.field_70121_D == null) {
            return Vec3.func_72443_a((double)impactX, (double)impactY, (double)impactZ);
        }
        Vec3 segmentStart = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
        Vec3 segmentEnd = Vec3.func_72443_a((double)impactX, (double)impactY, (double)impactZ);
        AxisAlignedBB expandedBox = entity.field_70121_D.func_72314_b(1.0E-4, 1.0E-4, 1.0E-4);
        MovingObjectPosition intercept = expandedBox.func_72327_a(segmentStart, segmentEnd);
        if (intercept != null && intercept.field_72307_f != null) {
            return intercept.field_72307_f;
        }
        Vec3 closest = this.closestPointOnBoundingBox(entity.field_70121_D, impactX, impactY, impactZ);
        return closest != null ? closest : Vec3.func_72443_a((double)impactX, (double)impactY, (double)impactZ);
    }

    protected boolean processEntitiesInHitBox(AxisAlignedBB hitBox, double impactX, double impactY, double impactZ) {
        List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, hitBox);
        for (EntityLivingBase entity : entities) {
            if (!this.processEntityHit(entity, impactX, impactY, impactZ)) continue;
            return true;
        }
        return false;
    }

    protected boolean handleSolidCollisionTermination() {
        if (!this.field_70123_F && !this.field_70124_G) {
            return false;
        }
        if (!this.field_70170_p.field_72995_K) {
            this.hasHit = true;
            if (this.isExplosive()) {
                this.doExplosion();
            }
        }
        this.func_70106_y();
        return true;
    }

    public boolean isHoming() {
        return this.homingData.isHoming();
    }

    public void setHomingEnabled(boolean homing) {
        this.homingData.setHoming(homing);
    }

    public float getHomingStrength() {
        return this.homingData.getHomingStrength();
    }

    public void setHomingStrength(float strength) {
        this.homingData.setHomingStrength(strength);
    }

    public float getHomingRange() {
        return this.homingData.getHomingRange();
    }

    public void setHomingRange(float range) {
        this.homingData.setHomingRange(range);
    }

    public float getSpeed() {
        return this.homingData.getSpeed();
    }

    public void setSpeed(float speed) {
        double len;
        float oldSpeed = this.homingData.getSpeed();
        this.homingData.setSpeed(speed);
        if (this.field_70175_ag && !this.field_70170_p.field_72995_K && (len = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y)) > 0.0) {
            double scale = (double)speed / len;
            this.field_70159_w *= scale;
            this.field_70181_x *= scale;
            this.field_70179_y *= scale;
        }
    }

    public AnchorPoint getAnchorPoint() {
        return this.anchorData.getAnchorPoint();
    }

    public int getAnchor() {
        return this.anchorData.getAnchor();
    }

    public float getAnchorOffsetX() {
        return this.anchorData.getAnchorOffsetX();
    }

    public float getAnchorOffsetY() {
        return this.anchorData.getAnchorOffsetY();
    }

    public float getAnchorOffsetZ() {
        return this.anchorData.getAnchorOffsetZ();
    }

    public double getStartX() {
        return this.startX;
    }

    public double getStartY() {
        return this.startY;
    }

    public double getStartZ() {
        return this.startZ;
    }

    public boolean getHasHit() {
        return this.hasHit;
    }

    @Override
    public boolean hasLightningEffect() {
        return super.hasLightningEffect() || this.getBarrierSparkTicks() > 0;
    }

    @Override
    protected void writeSpawnNBT(NBTTagCompound nbt) {
        this.writeBaseNBT(nbt);
        this.writeProjectileNBT(nbt);
    }

    @Override
    protected void readSpawnNBT(NBTTagCompound nbt) {
        this.readBaseNBT(nbt);
        this.readProjectileNBT(nbt);
    }

    protected void readBaseNBT(NBTTagCompound nbt) {
        this.readEnergyBaseNBT(nbt);
        this.size = EntityEnergyProjectile.sanitize(nbt.func_74760_g("Size"), 1.0f, 100.0f);
        this.effects.clear();
        if (nbt.func_74764_b("Effects")) {
            NBTTagList effectsList = nbt.func_150295_c("Effects", 10);
            for (int i = 0; i < effectsList.func_74745_c(); ++i) {
                this.effects.add(AbilityPotionEffect.fromNBT(effectsList.func_150305_b(i)));
            }
        }
        this.deathWorldTime = nbt.func_74764_b("DeathWorldTime") ? nbt.func_74763_f("DeathWorldTime") : -1L;
        this.startX = nbt.func_74769_h("StartX");
        this.startY = nbt.func_74769_h("StartY");
        this.startZ = nbt.func_74769_h("StartZ");
        this.targetEntityId = nbt.func_74762_e("TargetId");
        this.reflected = nbt.func_74767_n("Reflected");
        this.field_70159_w = nbt.func_74769_h("MotionX");
        this.field_70181_x = nbt.func_74769_h("MotionY");
        this.field_70179_y = nbt.func_74769_h("MotionZ");
        this.func_70107_b(this.startX, this.startY, this.startZ);
        this.field_70169_q = this.startX;
        this.field_70167_r = this.startY;
        this.field_70166_s = this.startZ;
        this.field_70142_S = this.startX;
        this.field_70137_T = this.startY;
        this.field_70136_U = this.startZ;
        this.interpTargetX = this.startX;
        this.interpTargetY = this.startY;
        this.interpTargetZ = this.startZ;
        this.interpTargetMotionX = this.field_70159_w;
        this.interpTargetMotionY = this.field_70181_x;
        this.interpTargetMotionZ = this.field_70179_y;
        this.interpSteps = 0;
        this.renderCurrentSize = this.size;
        this.prevRenderSize = this.size;
        this.anchorData.readNBT(nbt);
        this.combatData.readNBT(nbt);
        this.homingData.readNBT(nbt);
        this.lifespanData.readNBT(nbt);
        this.syncProjectileColorWatchers();
    }

    protected void writeBaseNBT(NBTTagCompound nbt) {
        this.writeEnergyBaseNBT(nbt);
        nbt.func_74776_a("Size", this.size);
        NBTTagList effectsList = new NBTTagList();
        for (AbilityPotionEffect effect : this.effects) {
            effectsList.func_74742_a((NBTBase)effect.writeNBT());
        }
        nbt.func_74782_a("Effects", (NBTBase)effectsList);
        nbt.func_74772_a("DeathWorldTime", this.deathWorldTime);
        nbt.func_74780_a("StartX", this.startX);
        nbt.func_74780_a("StartY", this.startY);
        nbt.func_74780_a("StartZ", this.startZ);
        nbt.func_74768_a("TargetId", this.targetEntityId);
        nbt.func_74757_a("Reflected", this.reflected);
        nbt.func_74780_a("MotionX", this.field_70159_w);
        nbt.func_74780_a("MotionY", this.field_70181_x);
        nbt.func_74780_a("MotionZ", this.field_70179_y);
        this.anchorData.writeNBT(nbt);
        this.combatData.writeNBT(nbt);
        this.homingData.writeNBT(nbt);
        this.lifespanData.writeNBT(nbt);
    }

    protected abstract void readProjectileNBT(NBTTagCompound var1);

    protected abstract void writeProjectileNBT(NBTTagCompound var1);

    public boolean func_70067_L() {
        return false;
    }
}

