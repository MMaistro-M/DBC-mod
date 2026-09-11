/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashSet;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyBarrierData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityEnergyDome
extends EntityEnergyBarrier {
    protected float domeRadius = 5.0f;
    protected float targetDomeRadius = 5.0f;
    protected boolean followCaster = false;
    protected float offsetX = 0.0f;
    protected float offsetY = 0.0f;
    protected float offsetZ = 0.0f;
    private boolean inTickMelee = false;
    private final HashSet<Integer> processedMeleeSwings = new HashSet();

    public EntityEnergyDome(World world) {
        super(world);
        this.field_70145_X = true;
        this.field_70138_W = 0.0f;
        this.func_70105_a(1.0f, 1.0f);
    }

    public EntityEnergyDome(World world, EntityLivingBase owner, double x, double y, double z, float domeRadius, EnergyDisplayData display, EnergyLightningData lightning, EnergyBarrierData barrier) {
        this(world);
        this.field_70145_X = true;
        this.field_70138_W = 0.0f;
        this.ownerEntityId = owner != null ? owner.func_145782_y() : -1;
        this.setDomeRadius(domeRadius);
        this.displayData = display != null ? display.copy() : new EnergyDisplayData();
        this.lightningData = lightning != null ? lightning.copy() : new EnergyLightningData();
        this.barrierData = barrier != null ? barrier.copy() : new EnergyBarrierData();
        this.currentHealth = this.barrierData.maxHealth;
        this.func_70107_b(x, y, z);
    }

    protected void func_70105_a(float width, float height) {
        super.func_70105_a(width, height);
    }

    public void func_70108_f(Entity entityIn) {
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        super.func_70071_h_();
        if (this.followCaster) {
            Entity owner;
            Entity entity = owner = this.ownerEntityId >= 0 ? this.field_70170_p.func_73045_a(this.ownerEntityId) : null;
            if (owner != null) {
                this.func_70107_b(owner.field_70165_t + (double)this.offsetX, owner.field_70163_u + (double)this.offsetY, owner.field_70161_v + (double)this.offsetZ);
                this.field_70169_q = owner.field_70169_q + (double)this.offsetX;
                this.field_70167_r = owner.field_70167_r + (double)this.offsetY;
                this.field_70166_s = owner.field_70166_s + (double)this.offsetZ;
            }
        }
        if (this.isCharging()) {
            ++this.chargeTick;
            float progress = this.getChargeProgress();
            this.setDomeRadius(this.targetDomeRadius * progress);
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            return;
        }
        ++this.ticksAlive;
        if (this.updateBarrierTick()) {
            return;
        }
        if (!this.field_70170_p.field_72995_K && this.barrierData.meleeEnabled) {
            this.processMeleeHits();
        }
        if (this.barrierData.solid || this.barrierData.knockbackEnabled) {
            this.processEntityPhysics();
        }
        if (this.field_70170_p.field_72995_K && this.barrierData.meleeEnabled && !this.isCharging()) {
            this.adjustMeleeBoundingBox();
        }
        this.debugLogBarrierTick();
    }

    private boolean isIncomingRay(double currX, double currY, double currZ, double prevX, double prevY, double prevZ, int projOwnerEntityId) {
        if (this.isCharging()) {
            return false;
        }
        if (projOwnerEntityId == this.ownerEntityId) {
            return false;
        }
        double rayDirX = currX - prevX;
        double rayDirY = currY - prevY;
        double rayDirZ = currZ - prevZ;
        double ocX = prevX - this.field_70165_t;
        double ocY = prevY - this.field_70163_u;
        double ocZ = prevZ - this.field_70161_v;
        double r = this.domeRadius;
        double a = rayDirX * rayDirX + rayDirY * rayDirY + rayDirZ * rayDirZ;
        double b = 2.0 * (ocX * rayDirX + ocY * rayDirY + ocZ * rayDirZ);
        double c = ocX * ocX + ocY * ocY + ocZ * ocZ - r * r;
        if (c <= 0.0) {
            return false;
        }
        if (a < 1.0E-10) {
            return false;
        }
        double discriminant = b * b - 4.0 * a * c;
        if (discriminant < 0.0) {
            return false;
        }
        double sqrtDisc = Math.sqrt(discriminant);
        double t1 = (-b - sqrtDisc) / (2.0 * a);
        return t1 >= 0.0 && t1 <= 1.0;
    }

    @Override
    public boolean isIncomingProjectile(EntityEnergyProjectile projectile) {
        Entity owner = this.getOwnerEntity();
        Entity projOwner = projectile.getOwnerEntity();
        if (owner instanceof EntityNPCInterface && projOwner instanceof EntityNPCInterface && ((EntityNPCInterface)owner).faction.id == ((EntityNPCInterface)projOwner).faction.id) {
            return false;
        }
        double nextX = projectile.field_70165_t + projectile.field_70159_w;
        double nextY = projectile.field_70163_u + projectile.field_70181_x;
        double nextZ = projectile.field_70161_v + projectile.field_70179_y;
        return this.isIncomingRay(nextX, nextY, nextZ, projectile.field_70165_t, projectile.field_70163_u, projectile.field_70161_v, projectile.getOwnerEntityId());
    }

    @Override
    public boolean isIncomingGenericProjectile(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, double prevPosX, double prevPosY, double prevPosZ, int ownerEntityId) {
        return this.isIncomingRay(posX, posY, posZ, prevPosX, prevPosY, prevPosZ, ownerEntityId);
    }

    @Override
    public double[] getSurfaceNormal(double hitX, double hitY, double hitZ, double velX, double velY, double velZ) {
        double nx = hitX - this.field_70165_t;
        double ny = hitY - this.field_70163_u;
        double nz = hitZ - this.field_70161_v;
        double lenSq = nx * nx + ny * ny + nz * nz;
        if (lenSq > 1.0E-8) {
            double invLen = 1.0 / Math.sqrt(lenSq);
            return new double[]{nx * invLen, ny * invLen, nz * invLen};
        }
        double vLenSq = velX * velX + velY * velY + velZ * velZ;
        if (vLenSq > 1.0E-8) {
            double invVLen = 1.0 / Math.sqrt(vLenSq);
            return new double[]{-velX * invVLen, -velY * invVLen, -velZ * invVLen};
        }
        return new double[]{0.0, 1.0, 0.0};
    }

    @Override
    public double[] getOutsideSurfacePoint(double px, double py, double pz, double velX, double velY, double velZ, float bias) {
        double nx = px - this.field_70165_t;
        double ny = py - this.field_70163_u;
        double nz = pz - this.field_70161_v;
        double len = Math.sqrt(nx * nx + ny * ny + nz * nz);
        if (len < 1.0E-5) {
            double vLen = Math.sqrt(velX * velX + velY * velY + velZ * velZ);
            if (vLen > 1.0E-5) {
                nx = -velX / vLen;
                ny = -velY / vLen;
                nz = -velZ / vLen;
            } else {
                nx = 0.0;
                ny = 1.0;
                nz = 0.0;
            }
            len = 1.0;
        }
        double invLen = 1.0 / len;
        double target = this.domeRadius + bias;
        return new double[]{this.field_70165_t + nx * invLen * target, this.field_70163_u + ny * invLen * target, this.field_70161_v + nz * invLen * target};
    }

    @Override
    public float getMaxExtent() {
        return this.domeRadius;
    }

    @Override
    public void setupCharging(int duration) {
        this.targetDomeRadius = this.domeRadius;
        this.setDomeRadius(0.01f);
        this.chargeDuration = duration;
        this.chargeTick = 0;
        this.setCharging(true);
    }

    @Override
    public void finishCharging() {
        this.setDomeRadius(this.targetDomeRadius);
        this.setCharging(false);
    }

    @Override
    protected void processEntityPhysics() {
        float r = this.domeRadius;
        boolean solid = this.barrierData.solid;
        boolean knockback = this.barrierData.knockbackEnabled;
        float strength = this.barrierData.knockbackStrength;
        float solidMargin = 1.5f;
        float knockbackMargin = 2.0f;
        AxisAlignedBB searchBox = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)r - (double)knockbackMargin), (double)(this.field_70163_u - (double)r - (double)knockbackMargin), (double)(this.field_70161_v - (double)r - (double)knockbackMargin), (double)(this.field_70165_t + (double)r + (double)knockbackMargin), (double)(this.field_70163_u + (double)r + (double)knockbackMargin), (double)(this.field_70161_v + (double)r + (double)knockbackMargin));
        EntityPlayer localPlayer = this.getClientPredictionPlayer();
        List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, searchBox);
        for (EntityLivingBase ent : entities) {
            double pushZ;
            double pushY;
            double pushX;
            double surfaceDist;
            boolean isInside;
            double dz;
            double dy;
            double dx;
            double dist;
            if (this.shouldSkipBarrierPhysicsTarget(ent, localPlayer) || (dist = Math.sqrt((dx = ent.field_70165_t - this.field_70165_t) * dx + (dy = ent.field_70163_u + (double)ent.field_70131_O * 0.5 - this.field_70163_u) * dy + (dz = ent.field_70161_v - this.field_70161_v) * dz)) < 0.01) continue;
            double nx = dx / dist;
            double ny = dy / dist;
            double nz = dz / dist;
            boolean bl = isInside = dist < (double)r;
            if (solid) {
                double prevDx = ent.field_70169_q - this.field_70169_q;
                double prevDy = ent.field_70167_r + (double)ent.field_70131_O * 0.5 - this.field_70167_r;
                double prevDz = ent.field_70166_s - this.field_70166_s;
                double prevDist = Math.sqrt(prevDx * prevDx + prevDy * prevDy + prevDz * prevDz);
                boolean wasInside = prevDist < (double)r;
                double radialVel = ent.field_70159_w * nx + ent.field_70181_x * ny + ent.field_70179_y * nz;
                if (wasInside != isInside) {
                    double pushForce;
                    double pushDist = wasInside ? (double)r - 1.0 : (double)r + 0.3;
                    double surfaceX = this.field_70165_t + nx * pushDist;
                    double surfaceY = this.field_70163_u + ny * pushDist - (double)ent.field_70131_O * 0.5;
                    double surfaceZ = this.field_70161_v + nz * pushDist;
                    if (ent.field_70122_E && surfaceY < ent.field_70163_u) {
                        surfaceY = ent.field_70163_u;
                    }
                    this.teleportEntity(ent, surfaceX, surfaceY, surfaceZ);
                    ent.field_70159_w -= radialVel * nx;
                    ent.field_70181_x -= radialVel * ny;
                    ent.field_70179_y -= radialVel * nz;
                    double d = pushForce = wasInside ? -0.15 : 0.15;
                    if (this.canPushInDirection(ent, nx * pushForce, ny * pushForce, nz * pushForce)) {
                        ent.field_70159_w += nx * pushForce;
                        ent.field_70181_x += ny * pushForce;
                        ent.field_70179_y += nz * pushForce;
                    }
                    ent.field_70133_I = true;
                } else {
                    double surfaceDist2 = Math.abs(dist - (double)r);
                    if (surfaceDist2 < (double)solidMargin) {
                        boolean movingTowardSurface;
                        boolean bl2 = movingTowardSurface = !isInside && radialVel < -0.01 || isInside && radialVel > 0.01;
                        if (movingTowardSurface) {
                            double pushForce;
                            ent.field_70159_w -= radialVel * nx;
                            ent.field_70181_x -= radialVel * ny;
                            ent.field_70179_y -= radialVel * nz;
                            double d = pushForce = isInside ? -0.05 : 0.05;
                            if (this.canPushInDirection(ent, nx * pushForce, ny * pushForce, nz * pushForce)) {
                                ent.field_70159_w += nx * pushForce;
                                ent.field_70181_x += ny * pushForce;
                                ent.field_70179_y += nz * pushForce;
                            }
                            ent.field_70133_I = true;
                        }
                    }
                }
            }
            if (this.field_70170_p.field_72995_K || !knockback || !((surfaceDist = Math.abs(dist - (double)r)) < (double)knockbackMargin)) continue;
            double proximity = 1.0 - surfaceDist / (double)knockbackMargin;
            double force = proximity * (double)strength * 0.06;
            if (isInside) {
                pushX = -nx * force;
                pushY = -ny * force * 0.5;
                pushZ = -nz * force;
            } else {
                pushX = nx * force;
                pushY = ny * force * 0.5;
                pushZ = nz * force;
            }
            if (this.canPushInDirection(ent, pushX, pushY, pushZ)) {
                ent.field_70159_w += pushX;
                ent.field_70181_x += pushY;
                ent.field_70179_y += pushZ;
            }
            ent.field_70133_I = true;
        }
    }

    @Override
    public boolean isEntityInside(Entity entity) {
        if (entity == null) {
            return false;
        }
        double dx = entity.field_70165_t - this.field_70165_t;
        double dy = entity.field_70163_u + (double)entity.field_70131_O * 0.5 - this.field_70163_u;
        double dz = entity.field_70161_v - this.field_70161_v;
        return dx * dx + dy * dy + dz * dz < (double)(this.domeRadius * this.domeRadius);
    }

    @Override
    public boolean func_70067_L() {
        if (!this.barrierData.meleeEnabled) {
            return false;
        }
        return !this.isCharging();
    }

    private void adjustMeleeBoundingBox() {
        double dz;
        EntityPlayer player = CustomNpcs.proxy.getPlayer();
        if (player == null) {
            this.updateBoundingBox();
            return;
        }
        double dx = player.field_70165_t - this.field_70165_t;
        double dy = player.field_70163_u + (double)player.func_70047_e() - this.field_70163_u;
        double dist = Math.sqrt(dx * dx + dy * dy + (dz = player.field_70161_v - this.field_70161_v) * dz);
        if (dist < 0.01 || dist > (double)this.domeRadius + 6.0) {
            this.updateBoundingBox();
            return;
        }
        double nx = dx / dist;
        double ny = dy / dist;
        double nz = dz / dist;
        double surfX = this.field_70165_t + nx * (double)this.domeRadius;
        double surfY = this.field_70163_u + ny * (double)this.domeRadius;
        double surfZ = this.field_70161_v + nz * (double)this.domeRadius;
        float s = 1.0f;
        this.field_70121_D.func_72324_b(surfX - (double)s, surfY - (double)s, surfZ - (double)s, surfX + (double)s, surfY + (double)s, surfZ + (double)s);
    }

    @Override
    public boolean func_70097_a(DamageSource source, float amount) {
        if (!this.inTickMelee && "player".equals(source.field_76373_n)) {
            return false;
        }
        if (source.func_76346_g() != null) {
            Entity attacker = source.func_76346_g();
            double dx = attacker.field_70165_t - this.field_70165_t;
            double dy = attacker.field_70163_u + (double)attacker.field_70131_O * 0.5 - this.field_70163_u;
            double dz = attacker.field_70161_v - this.field_70161_v;
            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
            double surfaceDist = Math.abs(dist - (double)this.domeRadius);
            if (surfaceDist > 5.0) {
                return false;
            }
        }
        return super.func_70097_a(source, amount);
    }

    private void processMeleeHits() {
        float r = this.domeRadius;
        float maxReach = 6.0f;
        AxisAlignedBB searchBox = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)r - (double)maxReach), (double)(this.field_70163_u - (double)r - (double)maxReach), (double)(this.field_70161_v - (double)r - (double)maxReach), (double)(this.field_70165_t + (double)r + (double)maxReach), (double)(this.field_70163_u + (double)r + (double)maxReach), (double)(this.field_70161_v + (double)r + (double)maxReach));
        List players = this.field_70170_p.func_72872_a(EntityPlayer.class, searchBox);
        for (EntityPlayer player : players) {
            double dz;
            double dy;
            double dx;
            double distSq;
            double dist;
            double surfaceDist;
            if (player.func_145782_y() == this.ownerEntityId || this.isAllyOfOwner((EntityLivingBase)player)) continue;
            int playerId = player.func_145782_y();
            if (!player.field_82175_bq) {
                this.processedMeleeSwings.remove(playerId);
                continue;
            }
            if (this.processedMeleeSwings.contains(playerId) || (surfaceDist = Math.abs((dist = Math.sqrt(distSq = (dx = player.field_70165_t - this.field_70165_t) * dx + (dy = player.field_70163_u + 1.62 - this.field_70163_u) * dy + (dz = player.field_70161_v - this.field_70161_v) * dz)) - (double)r)) > (double)maxReach) continue;
            Vec3 look = player.func_70676_i(1.0f);
            double b = 2.0 * (dx * look.field_72450_a + dy * look.field_72448_b + dz * look.field_72449_c);
            double c = distSq - (double)(r * r);
            double discriminant = b * b - 4.0 * c;
            if (discriminant < 0.0) continue;
            double sqrtDisc = Math.sqrt(discriminant);
            double t1 = (-b - sqrtDisc) / 2.0;
            double t2 = (-b + sqrtDisc) / 2.0;
            double hitDist = t1 >= 0.0 ? t1 : t2;
            if (hitDist < 0.0 || hitDist > (double)maxReach) continue;
            this.processedMeleeSwings.add(playerId);
            this.inTickMelee = true;
            player.func_71059_n((Entity)this);
            this.inTickMelee = false;
        }
    }

    public float func_70111_Y() {
        return 0.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        double d = (double)this.domeRadius * 8.0;
        return distance < (d *= 64.0) * d;
    }

    public double func_70092_e(double x, double y, double z) {
        double dx = this.field_70165_t - x;
        double dy = this.field_70163_u - y;
        double dz = this.field_70161_v - z;
        double centerDist = Math.sqrt(dx * dx + dy * dy + dz * dz);
        double surfaceDist = Math.abs(centerDist - (double)this.domeRadius);
        return surfaceDist * surfaceDist;
    }

    @Override
    protected String debugLogBarrierExtra() {
        return String.format("radius=%.2f targetRadius=%.2f follow=%b", Float.valueOf(this.domeRadius), Float.valueOf(this.targetDomeRadius), this.followCaster);
    }

    public float getDomeRadius() {
        return this.domeRadius;
    }

    public void setDomeRadius(float radius) {
        this.domeRadius = Math.max(0.1f, radius);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    }

    public boolean isFollowCaster() {
        return this.followCaster;
    }

    public void setFollowCaster(boolean follow) {
        this.followCaster = follow;
    }

    public void setOffsets(float x, float y, float z) {
        this.offsetX = x;
        this.offsetY = y;
        this.offsetZ = z;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70056_a(double x, double y, double z, float yaw, float pitch, int increments) {
        this.func_70107_b(x, y, z);
        this.func_70101_b(yaw, pitch);
    }

    public void func_70107_b(double x, double y, double z) {
        this.field_70165_t = x;
        this.field_70163_u = y;
        this.field_70161_v = z;
        this.updateBoundingBox();
    }

    protected void updateBoundingBox() {
        float r = this.domeRadius;
        this.field_70121_D.func_72324_b(this.field_70165_t - (double)r, this.field_70163_u - (double)r, this.field_70161_v - (double)r, this.field_70165_t + (double)r, this.field_70163_u + (double)r, this.field_70161_v + (double)r);
    }

    @Override
    protected void writeBarrierClientSyncData(NBTTagCompound nbt) {
        nbt.func_74776_a("DomeRadius", this.domeRadius);
    }

    @Override
    protected void applyBarrierClientSyncData(NBTTagCompound nbt) {
        this.setDomeRadius(nbt.func_74760_g("DomeRadius"));
    }

    @Override
    protected void writeSpawnNBT(NBTTagCompound nbt) {
        this.writeBarrierBaseNBT(nbt);
        nbt.func_74776_a("DomeRadius", this.domeRadius);
        nbt.func_74776_a("TargetDomeRadius", this.targetDomeRadius);
        nbt.func_74757_a("FollowCaster", this.followCaster);
        nbt.func_74776_a("OffsetX", this.offsetX);
        nbt.func_74776_a("OffsetY", this.offsetY);
        nbt.func_74776_a("OffsetZ", this.offsetZ);
    }

    @Override
    protected void readSpawnNBT(NBTTagCompound nbt) {
        this.readBarrierBaseNBT(nbt);
        this.setDomeRadius(EntityEnergyDome.sanitize(nbt.func_74760_g("DomeRadius"), 5.0f, 50.0f));
        this.targetDomeRadius = EntityEnergyDome.sanitize(nbt.func_74764_b("TargetDomeRadius") ? nbt.func_74760_g("TargetDomeRadius") : this.domeRadius, 5.0f, 50.0f);
        this.followCaster = nbt.func_74764_b("FollowCaster") && nbt.func_74767_n("FollowCaster");
        this.offsetX = nbt.func_74764_b("OffsetX") ? nbt.func_74760_g("OffsetX") : 0.0f;
        this.offsetY = nbt.func_74764_b("OffsetY") ? nbt.func_74760_g("OffsetY") : 0.0f;
        this.offsetZ = nbt.func_74764_b("OffsetZ") ? nbt.func_74760_g("OffsetZ") : 0.0f;
    }
}

