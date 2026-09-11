/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyAnchorData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyCombatData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLifespanData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityEnergyProjectile;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyDome;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.util.AnchorPointHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.EventHooks;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAbilityLaser
extends EntityEnergyProjectile {
    private float laserWidth = 0.2f;
    private float renderLaserWidth = 0.2f;
    private float prevRenderLaserWidth = 0.2f;
    private float expansionSpeed = 2.0f;
    private float maxLength = 32.0f;
    private float desiredLength = 0.0f;
    private float currentLength = 0.0f;
    private boolean fullyExtended = false;
    private boolean trackOwnerOrigin = true;
    private double dirX;
    private double dirY;
    private double dirZ;
    private double endX;
    private double endY;
    private double endZ;
    private double prevStartX;
    private double prevStartY;
    private double prevStartZ;
    private double prevEndX;
    private double prevEndY;
    private double prevEndZ;
    private int lastBlockHitX = Integer.MIN_VALUE;
    private int lastBlockHitY = Integer.MIN_VALUE;
    private int lastBlockHitZ = Integer.MIN_VALUE;
    private int lastExplosionTick = -100;
    private static final int EXPLOSION_COOLDOWN = 10;

    public EntityAbilityLaser(World world) {
        super(world);
    }

    public EntityAbilityLaser(World world, EntityLivingBase owner, EntityLivingBase target, double x, double y, double z, float laserWidth, EnergyDisplayData display, EnergyCombatData combat, EnergyLightningData lightning, EnergyLifespanData lifespan, float expansionSpeed, float maxLength) {
        super(world);
        this.initProjectile(owner, target, x, y, z, laserWidth, display, combat, lightning, lifespan);
        this.laserWidth = laserWidth;
        this.expansionSpeed = expansionSpeed;
        this.maxLength = maxLength;
        if (target != null) {
            double dz;
            double dx = target.field_70165_t - x;
            double dy = target.field_70163_u + (double)target.func_70047_e() - 0.4 - y;
            double len = Math.sqrt(dx * dx + dy * dy + (dz = target.field_70161_v - z) * dz);
            if (len > 0.0) {
                this.dirX = dx / len;
                this.dirY = dy / len;
                this.dirZ = dz / len;
            }
        } else {
            Vec3 look;
            Vec3 vec3 = look = owner == null ? null : owner.func_70040_Z();
            if (look != null) {
                this.dirX = look.field_72450_a;
                this.dirY = look.field_72448_b;
                this.dirZ = look.field_72449_c;
            } else if (owner != null) {
                float yaw = (float)Math.toRadians(owner.field_70177_z);
                float pitch = (float)Math.toRadians(owner.field_70125_A);
                this.dirX = -Math.sin(yaw) * Math.cos(pitch);
                this.dirY = -Math.sin(pitch);
                this.dirZ = Math.cos(yaw) * Math.cos(pitch);
            } else {
                this.dirX = 1.0;
                this.dirY = 0.0;
                this.dirZ = 0.0;
            }
        }
        this.endX = x;
        this.endY = y;
        this.endZ = z;
        this.prevStartX = x;
        this.prevStartY = y;
        this.prevStartZ = z;
        this.prevEndX = x;
        this.prevEndY = y;
        this.prevEndZ = z;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        double range = Math.max(128.0, (double)this.currentLength * 2.0 + 64.0);
        return distance < range * range;
    }

    @Override
    protected void updateRotation() {
    }

    @Override
    protected boolean checkMaxDistance() {
        return false;
    }

    @Override
    protected void updateProjectile() {
        this.prevRenderLaserWidth = this.renderLaserWidth;
        if (this.isCharging()) {
            this.renderLaserWidth = this.laserWidth;
            this.updateCharging();
            return;
        }
        this.renderLaserWidth += (this.laserWidth - this.renderLaserWidth) * 0.15f;
        this.prevStartX = this.startX;
        this.prevStartY = this.startY;
        this.prevStartZ = this.startZ;
        this.prevEndX = this.endX;
        this.prevEndY = this.endY;
        this.prevEndZ = this.endZ;
        if (!this.reflected && this.trackOwnerOrigin) {
            this.updateLaserOriginAndDirection();
        }
        if (!this.fullyExtended) {
            this.desiredLength += this.expansionSpeed;
            if (this.desiredLength >= this.maxLength) {
                this.desiredLength = this.maxLength;
                this.fullyExtended = true;
            }
        }
        this.currentLength = this.desiredLength;
        this.checkBlockCollision();
        this.updateEndPoint();
        if (!this.field_70170_p.field_72995_K) {
            if (this.checkLaserBarrierCollision()) {
                return;
            }
            this.checkEntityCollisionAlongLine();
        }
    }

    private void updateEndPoint() {
        this.endX = this.startX + this.dirX * (double)this.currentLength;
        this.endY = this.startY + this.dirY * (double)this.currentLength;
        this.endZ = this.startZ + this.dirZ * (double)this.currentLength;
    }

    private void updateLaserOriginAndDirection() {
        Entity owner = this.getOwnerEntity();
        if (owner == null || !(owner instanceof EntityLivingBase)) {
            if (this.field_70170_p != null && this.field_70170_p.field_72995_K) {
                this.handleClientInterpolation();
            }
            return;
        }
        EntityLivingBase livingOwner = (EntityLivingBase)owner;
        Vec3 look = livingOwner.func_70040_Z();
        if (look == null) {
            float yaw = (float)Math.toRadians(owner.field_70177_z);
            float pitch = (float)Math.toRadians(owner.field_70125_A);
            look = Vec3.func_72443_a((double)(-Math.sin(yaw) * Math.cos(pitch)), (double)(-Math.sin(pitch)), (double)(Math.cos(yaw) * Math.cos(pitch)));
        }
        this.dirX = look.field_72450_a;
        this.dirY = look.field_72448_b;
        this.dirZ = look.field_72449_c;
        double dirLen = Math.sqrt(this.dirX * this.dirX + this.dirY * this.dirY + this.dirZ * this.dirZ);
        if (dirLen > 1.0E-4) {
            this.dirX /= dirLen;
            this.dirY /= dirLen;
            this.dirZ /= dirLen;
        } else {
            this.dirX = 1.0;
            this.dirY = 0.0;
            this.dirZ = 0.0;
        }
        if (this.anchorData.launchFromAnchor) {
            double dz;
            double dy;
            EntityLivingBase target;
            double dx;
            double len;
            Vec3 anchorPos = AnchorPointHelper.calculateAnchorPosition(livingOwner, this.anchorData);
            this.func_70107_b(anchorPos.field_72450_a, anchorPos.field_72448_b, anchorPos.field_72449_c);
            Entity targetEntity = this.getTargetEntity();
            if (targetEntity instanceof EntityLivingBase && targetEntity.func_70089_S() && (len = Math.sqrt((dx = target.field_70165_t - anchorPos.field_72450_a) * dx + (dy = target.field_70163_u + (double)(target = (EntityLivingBase)targetEntity).func_70047_e() - 0.4 - anchorPos.field_72448_b) * dy + (dz = target.field_70161_v - anchorPos.field_72449_c) * dz)) > 1.0E-4) {
                this.dirX = dx / len;
                this.dirY = dy / len;
                this.dirZ = dz / len;
            }
        } else {
            Vec3 direction = Vec3.func_72443_a((double)this.dirX, (double)this.dirY, (double)this.dirZ);
            this.setLookVectorLaunchPosition(livingOwner, direction, false);
            this.field_70163_u -= 0.15;
        }
        this.syncStartPositionToCurrent();
        this.syncPositionStateToCurrent(false);
    }

    public void startMoving(EntityLivingBase target) {
        this.beginLookVectorLaunch(false);
        this.desiredLength = 0.0f;
        this.currentLength = 0.0f;
        this.fullyExtended = false;
        this.lastBlockHitX = Integer.MIN_VALUE;
        this.lastBlockHitY = Integer.MIN_VALUE;
        this.lastBlockHitZ = Integer.MIN_VALUE;
        this.lastExplosionTick = -100;
        if (!this.setDirectionTowardTarget(target, this.startX, this.startY, this.startZ)) {
            Vec3 look = this.getOwnerLookVector();
            if (look != null) {
                this.dirX = look.field_72450_a;
                this.dirY = look.field_72448_b;
                this.dirZ = look.field_72449_c;
            } else {
                this.dirX = 1.0;
                this.dirY = 0.0;
                this.dirZ = 0.0;
            }
        }
        this.endX = this.startX;
        this.endY = this.startY;
        this.endZ = this.startZ;
        this.prevStartX = this.startX;
        this.prevStartY = this.startY;
        this.prevStartZ = this.startZ;
        this.prevEndX = this.startX;
        this.prevEndY = this.startY;
        this.prevEndZ = this.startZ;
    }

    private boolean setDirectionTowardTarget(EntityLivingBase target, double sourceX, double sourceY, double sourceZ) {
        double dz;
        if (target == null) {
            return false;
        }
        double dx = target.field_70165_t - sourceX;
        double dy = target.field_70163_u + (double)target.func_70047_e() - 0.4 - sourceY;
        double len = Math.sqrt(dx * dx + dy * dy + (dz = target.field_70161_v - sourceZ) * dz);
        if (len <= 1.0E-4) {
            return false;
        }
        this.dirX = dx / len;
        this.dirY = dy / len;
        this.dirZ = dz / len;
        return true;
    }

    private void checkBlockCollision() {
        if (this.currentLength <= 0.0f) {
            return;
        }
        double traceEndX = this.startX + this.dirX * (double)this.currentLength;
        double traceEndY = this.startY + this.dirY * (double)this.currentLength;
        double traceEndZ = this.startZ + this.dirZ * (double)this.currentLength;
        MovingObjectPosition blockHit = this.rayTraceBlocks(this.startX, this.startY, this.startZ, traceEndX, traceEndY, traceEndZ);
        if (blockHit != null && blockHit.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            float hitDist = (float)Math.sqrt((blockHit.field_72307_f.field_72450_a - this.startX) * (blockHit.field_72307_f.field_72450_a - this.startX) + (blockHit.field_72307_f.field_72448_b - this.startY) * (blockHit.field_72307_f.field_72448_b - this.startY) + (blockHit.field_72307_f.field_72449_c - this.startZ) * (blockHit.field_72307_f.field_72449_c - this.startZ));
            if (hitDist < this.currentLength) {
                this.currentLength = hitDist;
                this.endX = blockHit.field_72307_f.field_72450_a;
                this.endY = blockHit.field_72307_f.field_72448_b;
                this.endZ = blockHit.field_72307_f.field_72449_c;
            }
            if (!this.field_70170_p.field_72995_K) {
                boolean newBlock;
                int bx = blockHit.field_72311_b;
                int by = blockHit.field_72312_c;
                int bz = blockHit.field_72309_d;
                boolean bl = newBlock = bx != this.lastBlockHitX || by != this.lastBlockHitY || bz != this.lastBlockHitZ;
                if (newBlock) {
                    this.lastBlockHitX = bx;
                    this.lastBlockHitY = by;
                    this.lastBlockHitZ = bz;
                    EventHooks.onEnergyProjectileBlockImpact(this, bx, by, bz);
                }
                if (this.isExplosive() && this.field_70173_aa - this.lastExplosionTick >= 10) {
                    this.lastExplosionTick = this.field_70173_aa;
                    double oldPosX = this.field_70165_t;
                    double oldPosY = this.field_70163_u;
                    double oldPosZ = this.field_70161_v;
                    this.field_70165_t = this.endX;
                    this.field_70163_u = this.endY;
                    this.field_70161_v = this.endZ;
                    this.doExplosion();
                    this.field_70165_t = oldPosX;
                    this.field_70163_u = oldPosY;
                    this.field_70161_v = oldPosZ;
                }
            }
        } else if (!this.field_70170_p.field_72995_K) {
            this.lastBlockHitX = Integer.MIN_VALUE;
            this.lastBlockHitY = Integer.MIN_VALUE;
            this.lastBlockHitZ = Integer.MIN_VALUE;
        }
    }

    private void checkEntityCollisionAlongLine() {
        if (this.currentLength <= 0.0f) {
            return;
        }
        double expand = Math.max((double)this.laserWidth, 1.0);
        double minX = Math.min(this.startX, this.endX) - expand;
        double minY = Math.min(this.startY, this.endY) - expand;
        double minZ = Math.min(this.startZ, this.endZ) - expand;
        double maxX = Math.max(this.startX, this.endX) + expand;
        double maxY = Math.max(this.startY, this.endY) + expand;
        double maxZ = Math.max(this.startZ, this.endZ) + expand;
        AxisAlignedBB searchBox = AxisAlignedBB.func_72330_a((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
        List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, searchBox);
        for (EntityLivingBase entity : entities) {
            if (this.shouldIgnoreEntity((Entity)entity) || !this.canHitEntityNow(entity) || !this.isEntityOnLine(entity) || !this.processEntityHit(entity, entity.field_70165_t, entity.field_70163_u + (double)entity.field_70131_O * 0.5, entity.field_70161_v)) continue;
            double dx = entity.field_70165_t - this.startX;
            double dy = entity.field_70163_u + (double)entity.field_70131_O * 0.5 - this.startY;
            double dz = entity.field_70161_v - this.startZ;
            float impactDist = (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
            this.currentLength = Math.min(impactDist, this.currentLength);
            this.endX = this.startX + this.dirX * (double)this.currentLength;
            this.endY = this.startY + this.dirY * (double)this.currentLength;
            this.endZ = this.startZ + this.dirZ * (double)this.currentLength;
            return;
        }
    }

    private boolean isEntityOnLine(EntityLivingBase entity) {
        Vec3 segmentEnd;
        Vec3 segmentStart;
        AxisAlignedBB bb = entity.field_70121_D;
        if (bb == null) {
            return false;
        }
        double effectiveWidth = Math.max((double)this.laserWidth, 0.65);
        double expand = effectiveWidth * 0.5;
        AxisAlignedBB expanded = bb.func_72314_b(expand, expand, expand);
        MovingObjectPosition intercept = expanded.func_72327_a(segmentStart = Vec3.func_72443_a((double)this.startX, (double)this.startY, (double)this.startZ), segmentEnd = Vec3.func_72443_a((double)this.endX, (double)this.endY, (double)this.endZ));
        if (intercept != null) {
            return true;
        }
        return expanded.func_72318_a(segmentStart) || expanded.func_72318_a(segmentEnd);
    }

    @Override
    protected boolean checkBarrierCollision() {
        return false;
    }

    private boolean checkLaserBarrierCollision() {
        if (this.currentLength <= 0.0f) {
            return false;
        }
        List<EntityEnergyBarrier> barriers = EntityEnergyBarrier.getActiveBarriers(this.field_70170_p);
        for (EntityEnergyBarrier barrier : barriers) {
            float damage;
            EntityEnergyBarrier.ProjectileHitOutcome hitOutcome;
            if (barrier.field_70128_L || barrier.isCharging() || barrier.getOwnerEntityId() == this.ownerEntityId) continue;
            Entity bOwner = barrier.getOwnerEntity();
            Entity lOwner = this.getOwnerEntity();
            if (bOwner instanceof EntityNPCInterface && lOwner instanceof EntityNPCInterface && ((EntityNPCInterface)bOwner).faction.id == ((EntityNPCInterface)lOwner).faction.id) continue;
            if (barrier instanceof EntityEnergyDome) {
                float damage2;
                EntityEnergyBarrier.ProjectileHitOutcome hitOutcome2;
                EntityEnergyDome dome = (EntityEnergyDome)barrier;
                float intersectDist = this.getLineSphereIntersection(dome);
                if (!(intersectDist >= 0.0f) || (hitOutcome2 = dome.onProjectileHitResolved(this, damage2 = this.getModifiedDamage())) == null || hitOutcome2.result == EntityEnergyBarrier.ProjectileHitResult.PASS) continue;
                if (hitOutcome2.result != EntityEnergyBarrier.ProjectileHitResult.BROKEN) {
                    this.currentLength = Math.max(0.0f, intersectDist);
                    this.endX = this.startX + this.dirX * (double)this.currentLength;
                    this.endY = this.startY + this.dirY * (double)this.currentLength;
                    this.endZ = this.startZ + this.dirZ * (double)this.currentLength;
                }
                return this.handleBarrierHitOutcome(dome, hitOutcome2, damage2);
            }
            double segMotionX = this.endX - this.startX;
            double segMotionY = this.endY - this.startY;
            double segMotionZ = this.endZ - this.startZ;
            boolean incoming = barrier.isIncomingGenericProjectile(this.endX, this.endY, this.endZ, segMotionX, segMotionY, segMotionZ, this.startX, this.startY, this.startZ, this.ownerEntityId);
            if (!incoming || (hitOutcome = barrier.onProjectileHitResolved(this, damage = this.getModifiedDamage())) == null || hitOutcome.result == EntityEnergyBarrier.ProjectileHitResult.PASS) continue;
            if (hitOutcome.result != EntityEnergyBarrier.ProjectileHitResult.BROKEN) {
                this.endX = this.startX + this.dirX * (double)this.currentLength;
                this.endY = this.startY + this.dirY * (double)this.currentLength;
                this.endZ = this.startZ + this.dirZ * (double)this.currentLength;
            }
            return this.handleBarrierHitOutcome(barrier, hitOutcome, damage);
        }
        return false;
    }

    @Override
    protected boolean reflectFromBarrier(EntityEnergyBarrier barrier, float reflectStrengthPct) {
        double rLen;
        if (barrier == null) {
            return false;
        }
        double vx = this.dirX;
        double vy = this.dirY;
        double vz = this.dirZ;
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
        double rx = vx - 2.0 * dot * normal[0];
        double ry = vy - 2.0 * dot * normal[1];
        double rz = vz - 2.0 * dot * normal[2];
        if (ry < -0.06) {
            ry = Math.max(0.08, Math.abs(ry) * 0.35);
        }
        if ((rLen = Math.sqrt(rx * rx + ry * ry + rz * rz)) < 1.0E-8 && (rLen = Math.sqrt((rx = -vx) * rx + (ry = -vy) * ry + (rz = -vz) * rz)) < 1.0E-8) {
            return false;
        }
        this.dirX = rx / rLen;
        this.dirY = ry / rLen;
        this.dirZ = rz / rLen;
        this.startX = this.endX;
        this.startY = this.endY;
        this.startZ = this.endZ;
        this.func_70107_b(this.startX, this.startY, this.startZ);
        this.syncPositionStateToCurrent(true);
        this.desiredLength = 0.0f;
        this.currentLength = 0.0f;
        this.fullyExtended = false;
        this.endX = this.startX;
        this.endY = this.startY;
        this.endZ = this.startZ;
        this.lastBlockHitX = Integer.MIN_VALUE;
        this.lastBlockHitY = Integer.MIN_VALUE;
        this.lastBlockHitZ = Integer.MIN_VALUE;
        this.lastExplosionTick = -100;
        this.deathWorldTime = -1L;
        this.barrierImpactPauseTicks = 0;
        this.barrierImpactDestroyOnResume = false;
        if (this.sourceAbility instanceof AbilityEnergyProjectile) {
            ((AbilityEnergyProjectile)this.sourceAbility).detachEntity(this);
        }
        this.sourceAbility = null;
        int originalOwnerId = this.ownerEntityId;
        Entity barrierOwner = barrier.getOwnerEntity();
        if (barrierOwner != null) {
            this.setOwnerEntityId(barrierOwner.func_145782_y());
            EntityAbilityLaser.trackProjectile(this);
        }
        if (barrier.getBarrierData().isTargetOwner() && originalOwnerId != -1) {
            this.setTargetEntityId(originalOwnerId);
        } else {
            this.setTargetEntityId(-1);
        }
        this.setInnerColor(barrier.getInnerColor());
        this.setOuterColor(barrier.getOuterColor());
        float clampedStrength = Math.max(0.0f, Math.min(100.0f, reflectStrengthPct));
        float reducedDamage = this.getDamage() * (1.0f - clampedStrength / 100.0f);
        this.setCombatDamage(Math.max(0.0f, reducedDamage));
        this.hitOnceEntities.clear();
        this.lastHitTickByEntity.clear();
        this.reflected = true;
        return true;
    }

    private float getLineSphereIntersection(EntityEnergyDome dome) {
        double cx = dome.field_70165_t;
        double ocX = this.startX - cx;
        double cy = dome.field_70163_u;
        double ocY = this.startY - cy;
        double cz = dome.field_70161_v;
        double ocZ = this.startZ - cz;
        double originDistSq = ocX * ocX + ocY * ocY + ocZ * ocZ;
        float radius = dome.getDomeRadius();
        if (originDistSq < (double)radius * (double)radius) {
            return -1.0f;
        }
        double b = 2.0 * (this.dirX * ocX + this.dirY * ocY + this.dirZ * ocZ);
        double a = this.dirX * this.dirX + this.dirY * this.dirY + this.dirZ * this.dirZ;
        double c = originDistSq - (double)radius * (double)radius;
        double discriminant = b * b - 4.0 * a * c;
        if (discriminant < 0.0) {
            return -1.0f;
        }
        double sqrtD = Math.sqrt(discriminant);
        double t1 = (-b - sqrtD) / (2.0 * a);
        if (t1 >= 0.0 && t1 <= (double)this.currentLength) {
            return (float)t1;
        }
        return -1.0f;
    }

    @Override
    protected String debugLogExtra() {
        return String.format("dir=(%.3f,%.3f,%.3f) desired=%.2f current=%.2f max=%.2f fullyExtended=%b end=(%.2f,%.2f,%.2f) width=%.2f expSpd=%.2f origin=(%.2f,%.2f,%.2f)", this.dirX, this.dirY, this.dirZ, Float.valueOf(this.desiredLength), Float.valueOf(this.currentLength), Float.valueOf(this.maxLength), this.fullyExtended, this.endX, this.endY, this.endZ, Float.valueOf(this.laserWidth), Float.valueOf(this.expansionSpeed), this.startX, this.startY, this.startZ);
    }

    public float getLaserWidth() {
        return this.laserWidth;
    }

    public float getInterpolatedLaserWidth(float partialTicks) {
        return this.prevRenderLaserWidth + (this.renderLaserWidth - this.prevRenderLaserWidth) * partialTicks;
    }

    public void setLaserWidth(float width) {
        this.laserWidth = width;
    }

    public float getExpansionSpeed() {
        return this.expansionSpeed;
    }

    public void setExpansionSpeed(float speed) {
        this.expansionSpeed = speed;
    }

    public float getMaxLength() {
        return this.maxLength;
    }

    public void setMaxLength(float maxLength) {
        this.maxLength = maxLength;
    }

    public void setDirection(double x, double y, double z) {
        this.dirX = x;
        this.dirY = y;
        this.dirZ = z;
    }

    public boolean isTrackOwnerOrigin() {
        return this.trackOwnerOrigin;
    }

    public void setTrackOwnerOrigin(boolean track) {
        this.trackOwnerOrigin = track;
    }

    public float getCurrentLength() {
        return this.currentLength;
    }

    public boolean isFullyExtended() {
        return this.fullyExtended;
    }

    @Override
    public double getStartX() {
        return this.startX;
    }

    @Override
    public double getStartY() {
        return this.startY;
    }

    @Override
    public double getStartZ() {
        return this.startZ;
    }

    public double getEndX() {
        return this.endX;
    }

    public double getEndY() {
        return this.endY;
    }

    public double getEndZ() {
        return this.endZ;
    }

    public double getInterpolatedStartX(float partialTicks) {
        return this.prevStartX + (this.startX - this.prevStartX) * (double)partialTicks;
    }

    public double getInterpolatedStartY(float partialTicks) {
        return this.prevStartY + (this.startY - this.prevStartY) * (double)partialTicks;
    }

    public double getInterpolatedStartZ(float partialTicks) {
        return this.prevStartZ + (this.startZ - this.prevStartZ) * (double)partialTicks;
    }

    public double getInterpolatedEndX(float partialTicks) {
        return this.prevEndX + (this.endX - this.prevEndX) * (double)partialTicks;
    }

    public double getInterpolatedEndY(float partialTicks) {
        return this.prevEndY + (this.endY - this.prevEndY) * (double)partialTicks;
    }

    public double getInterpolatedEndZ(float partialTicks) {
        return this.prevEndZ + (this.endZ - this.prevEndZ) * (double)partialTicks;
    }

    public double getDirX() {
        return this.dirX;
    }

    public double getDirY() {
        return this.dirY;
    }

    public double getDirZ() {
        return this.dirZ;
    }

    @Override
    protected float getLaunchClearanceRadius() {
        return Math.max(0.1f, this.laserWidth * 0.5f);
    }

    @Override
    public void setupCharging(EnergyAnchorData anchor, int chargeDuration) {
        this.size = this.laserWidth * 0.5f;
        this.desiredLength = 0.0f;
        this.currentLength = 0.0f;
        this.fullyExtended = false;
        super.setupCharging(anchor, chargeDuration);
        this.endX = this.field_70165_t;
        this.endY = this.field_70163_u;
        this.endZ = this.field_70161_v;
        this.prevStartX = this.field_70165_t;
        this.prevStartY = this.field_70163_u;
        this.prevStartZ = this.field_70161_v;
        this.prevEndX = this.field_70165_t;
        this.prevEndY = this.field_70163_u;
        this.prevEndZ = this.field_70161_v;
    }

    @Override
    protected void writeProjectileReflectionData(NBTTagCompound nbt) {
        nbt.func_74780_a("DirX", this.dirX);
        nbt.func_74780_a("DirY", this.dirY);
        nbt.func_74780_a("DirZ", this.dirZ);
        nbt.func_74780_a("StartX", this.startX);
        nbt.func_74780_a("StartY", this.startY);
        nbt.func_74780_a("StartZ", this.startZ);
        nbt.func_74776_a("DesiredLength", this.desiredLength);
        nbt.func_74776_a("CurrentLength", this.currentLength);
        nbt.func_74780_a("EndX", this.endX);
        nbt.func_74780_a("EndY", this.endY);
        nbt.func_74780_a("EndZ", this.endZ);
        nbt.func_74757_a("FullyExtended", this.fullyExtended);
        nbt.func_74776_a("MaxLength", this.maxLength);
    }

    @Override
    protected void applyProjectileReflectionData(NBTTagCompound nbt) {
        this.dirX = nbt.func_74769_h("DirX");
        this.dirY = nbt.func_74769_h("DirY");
        this.dirZ = nbt.func_74769_h("DirZ");
        this.startX = nbt.func_74769_h("StartX");
        this.startY = nbt.func_74769_h("StartY");
        this.startZ = nbt.func_74769_h("StartZ");
        this.desiredLength = nbt.func_74760_g("DesiredLength");
        this.currentLength = nbt.func_74760_g("CurrentLength");
        this.endX = nbt.func_74769_h("EndX");
        this.endY = nbt.func_74769_h("EndY");
        this.endZ = nbt.func_74769_h("EndZ");
        this.fullyExtended = nbt.func_74767_n("FullyExtended");
        if (nbt.func_74764_b("MaxLength")) {
            this.maxLength = nbt.func_74760_g("MaxLength");
        }
    }

    @Override
    protected void writeProjectileClientSyncData(NBTTagCompound nbt) {
        nbt.func_74776_a("LaserWidth", this.laserWidth);
        nbt.func_74776_a("ExpansionSpeed", this.expansionSpeed);
        nbt.func_74776_a("MaxLength", this.maxLength);
        nbt.func_74780_a("DirX", this.dirX);
        nbt.func_74780_a("DirY", this.dirY);
        nbt.func_74780_a("DirZ", this.dirZ);
    }

    @Override
    protected void applyProjectileClientSyncData(NBTTagCompound nbt) {
        this.laserWidth = nbt.func_74760_g("LaserWidth");
        this.expansionSpeed = nbt.func_74760_g("ExpansionSpeed");
        this.maxLength = nbt.func_74760_g("MaxLength");
        this.dirX = nbt.func_74769_h("DirX");
        this.dirY = nbt.func_74769_h("DirY");
        this.dirZ = nbt.func_74769_h("DirZ");
    }

    @Override
    protected void readProjectileNBT(NBTTagCompound nbt) {
        this.renderLaserWidth = this.laserWidth = EntityAbilityLaser.sanitize(nbt.func_74764_b("LaserWidth") ? nbt.func_74760_g("LaserWidth") : 0.2f, 0.2f, 100.0f);
        this.prevRenderLaserWidth = this.laserWidth;
        this.expansionSpeed = EntityAbilityLaser.sanitize(nbt.func_74764_b("ExpansionSpeed") ? nbt.func_74760_g("ExpansionSpeed") : 2.0f, 0.1f, 100.0f);
        this.maxLength = EntityAbilityLaser.sanitize(nbt.func_74764_b("MaxLength") ? nbt.func_74760_g("MaxLength") : 32.0f, 1.0f, 100.0f);
        this.dirX = nbt.func_74769_h("DirX");
        this.dirY = nbt.func_74769_h("DirY");
        this.dirZ = nbt.func_74769_h("DirZ");
        this.desiredLength = nbt.func_74760_g("DesiredLength");
        this.currentLength = nbt.func_74760_g("CurrentLength");
        this.fullyExtended = nbt.func_74767_n("FullyExtended");
        this.trackOwnerOrigin = !nbt.func_74764_b("TrackOwnerOrigin") || nbt.func_74767_n("TrackOwnerOrigin");
        this.endX = nbt.func_74769_h("EndX");
        this.endY = nbt.func_74769_h("EndY");
        this.endZ = nbt.func_74769_h("EndZ");
        this.readChargingNBT(nbt);
    }

    @Override
    protected void writeProjectileNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("LaserWidth", this.laserWidth);
        nbt.func_74776_a("ExpansionSpeed", this.expansionSpeed);
        nbt.func_74776_a("MaxLength", this.maxLength);
        nbt.func_74780_a("DirX", this.dirX);
        nbt.func_74780_a("DirY", this.dirY);
        nbt.func_74780_a("DirZ", this.dirZ);
        nbt.func_74776_a("DesiredLength", this.desiredLength);
        nbt.func_74776_a("CurrentLength", this.currentLength);
        nbt.func_74757_a("FullyExtended", this.fullyExtended);
        nbt.func_74757_a("TrackOwnerOrigin", this.trackOwnerOrigin);
        nbt.func_74780_a("EndX", this.endX);
        nbt.func_74780_a("EndY", this.endY);
        nbt.func_74780_a("EndZ", this.endZ);
        this.writeChargingNBT(nbt);
    }

    public void setupPreview(EntityLivingBase owner, float laserWidth, EnergyDisplayData display, EnergyLightningData lightning, EnergyAnchorData anchor, int chargeDuration, float expansionSpeed, float maxLength) {
        Vec3 look;
        this.setupPreviewState(owner, display, lightning, anchor, chargeDuration);
        this.laserWidth = laserWidth;
        this.expansionSpeed = expansionSpeed;
        this.maxLength = Math.min(maxLength, 5.0f);
        this.desiredLength = 0.0f;
        this.currentLength = 0.0f;
        this.fullyExtended = false;
        this.targetSize = laserWidth * 0.5f;
        this.setVisualSize(0.01f);
        this.setChargeOriginFromAnchor(owner, this.anchorData);
        this.clearMotion();
        this.endX = this.field_70165_t;
        this.endY = this.field_70163_u;
        this.endZ = this.field_70161_v;
        this.prevStartX = this.field_70165_t;
        this.prevStartY = this.field_70163_u;
        this.prevStartZ = this.field_70161_v;
        this.prevEndX = this.field_70165_t;
        this.prevEndY = this.field_70163_u;
        this.prevEndZ = this.field_70161_v;
        Vec3 vec3 = look = owner == null ? null : owner.func_70040_Z();
        if (look == null) {
            if (owner != null) {
                float yaw = (float)Math.toRadians(owner.field_70177_z);
                float pitch = (float)Math.toRadians(owner.field_70125_A);
                look = Vec3.func_72443_a((double)(-Math.sin(yaw) * Math.cos(pitch)), (double)(-Math.sin(pitch)), (double)(Math.cos(yaw) * Math.cos(pitch)));
            } else {
                look = Vec3.func_72443_a((double)1.0, (double)0.0, (double)0.0);
            }
        }
        this.dirX = look.field_72450_a;
        this.dirY = look.field_72448_b;
        this.dirZ = look.field_72449_c;
    }
}

