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
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.EnergyController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyBarrierData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyPanelData;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import noppes.npcs.NpcDamageSource;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityEnergyPanel
extends EntityEnergyBarrier {
    protected EnergyPanelData panelData = new EnergyPanelData();
    protected PanelMode mode = PanelMode.PLACED;
    protected float panelYaw = 0.0f;
    protected float prevPanelYaw = 0.0f;
    protected float targetPanelWidth;
    protected float targetPanelHeight;

    public EntityEnergyPanel(World world) {
        super(world);
        this.func_70105_a(0.5f, 0.5f);
    }

    public EntityEnergyPanel(World world, EntityLivingBase owner, double x, double y, double z, float yaw, PanelMode mode, EnergyDisplayData display, EnergyLightningData lightning, EnergyBarrierData barrier, EnergyPanelData panel) {
        this(world);
        this.func_70107_b(x, y + (double)panel.heightOffset, z);
        this.ownerEntityId = owner != null ? owner.func_145782_y() : -1;
        this.panelYaw = yaw;
        this.prevPanelYaw = yaw;
        this.mode = mode;
        this.displayData = display != null ? display.copy() : new EnergyDisplayData();
        this.lightningData = lightning != null ? lightning.copy() : new EnergyLightningData();
        this.barrierData = barrier != null ? barrier.copy() : new EnergyBarrierData();
        this.panelData = panel != null ? panel.copy() : new EnergyPanelData();
        this.currentHealth = this.barrierData.maxHealth;
        if (mode == PanelMode.LAUNCHED) {
            float yawRad = (float)Math.toRadians(yaw);
            this.field_70159_w = -Math.sin(yawRad) * (double)panel.launchSpeed;
            this.field_70181_x = 0.0;
            this.field_70179_y = Math.cos(yawRad) * (double)panel.launchSpeed;
        }
    }

    public void func_70107_b(double x, double y, double z) {
        this.field_70165_t = x;
        this.field_70163_u = y;
        this.field_70161_v = z;
        if (this.panelData != null) {
            float halfW = this.panelData.panelWidth * 0.5f;
            float halfH = this.panelData.panelHeight * 0.5f;
            float extent = Math.max(halfW, 0.5f);
            this.field_70121_D.func_72324_b(x - (double)extent, y - (double)halfH, z - (double)extent, x + (double)extent, y + (double)halfH, z + (double)extent);
        } else {
            float f = this.field_70130_N / 2.0f;
            this.field_70121_D.func_72324_b(x - (double)f, y, z - (double)f, x + (double)f, y + (double)this.field_70131_O, z + (double)f);
        }
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.prevPanelYaw = this.panelYaw;
        super.func_70071_h_();
        if (this.isCharging()) {
            ++this.chargeTick;
            float progress = this.getChargeProgress();
            this.panelData.panelWidth = this.targetPanelWidth * progress;
            this.panelData.panelHeight = this.targetPanelHeight * progress;
            if (this.mode == PanelMode.HELD) {
                this.updateHeld();
            }
            return;
        }
        ++this.ticksAlive;
        if (this.updateBarrierTick()) {
            return;
        }
        if ((this.barrierData.solid || this.barrierData.knockbackEnabled) && this.mode != PanelMode.LAUNCHED) {
            this.processEntityPhysics();
        }
        switch (this.mode) {
            case HELD: {
                this.updateHeld();
                break;
            }
            case LAUNCHED: {
                this.updateLaunched();
                break;
            }
        }
        this.debugLogBarrierTick();
    }

    private void updateHeld() {
        Entity owner = this.getOwnerEntity();
        if (owner == null) {
            return;
        }
        this.panelYaw = owner.field_70177_z;
        float frontDist = 1.5f;
        float yawRad = (float)Math.toRadians(this.panelYaw);
        double newX = owner.field_70165_t + -Math.sin(yawRad) * (double)frontDist + (double)this.panelData.offsetX;
        double newY = owner.field_70163_u + (double)this.panelData.heightOffset + (double)(owner.field_70131_O * 0.5f);
        double newZ = owner.field_70161_v + Math.cos(yawRad) * (double)frontDist + (double)this.panelData.offsetZ;
        this.func_70107_b(newX, newY, newZ);
        float prevYawRad = (float)Math.toRadians(owner.field_70126_B);
        this.field_70169_q = owner.field_70169_q + -Math.sin(prevYawRad) * (double)frontDist + (double)this.panelData.offsetX;
        this.field_70167_r = owner.field_70167_r + (double)this.panelData.heightOffset + (double)(owner.field_70131_O * 0.5f);
        this.field_70166_s = owner.field_70166_s + Math.cos(prevYawRad) * (double)frontDist + (double)this.panelData.offsetZ;
    }

    public void func_70056_a(double x, double y, double z, float yaw, float pitch, int posRotationIncrements) {
        if (this.field_70170_p != null && this.field_70170_p.field_72995_K && this.mode == PanelMode.HELD && this.getOwnerEntity() != null) {
            return;
        }
        this.func_70107_b(x, y, z);
        this.func_70101_b(yaw, pitch);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void updateLaunched() {
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        if (!this.field_70170_p.field_72995_K) {
            float halfW = this.panelData.panelWidth * 0.5f;
            float halfH = this.panelData.panelHeight * 0.5f;
            float halfD = 0.5f;
            float searchRadius = Math.max(halfW, halfD);
            AxisAlignedBB hitBox = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)searchRadius), (double)(this.field_70163_u - (double)halfH), (double)(this.field_70161_v - (double)searchRadius), (double)(this.field_70165_t + (double)searchRadius), (double)(this.field_70163_u + (double)halfH), (double)(this.field_70161_v + (double)searchRadius));
            List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, hitBox);
            Entity owner = this.getOwnerEntity();
            boolean ignoreIFrames = this.sourceAbility != null && this.sourceAbility.isIgnoreIFrames();
            for (EntityLivingBase target : entities) {
                double kbZ;
                double kbX;
                double dz;
                double dx;
                double len;
                if (target.func_145782_y() == this.ownerEntityId || this.isAllyOfOwner(target)) continue;
                if (this.panelData.launchDamage > 0.0f) {
                    int previousHurtResistantTime = Ability.clearHurtResistanceIfNeeded(target, ignoreIFrames);
                    try {
                        double dz2;
                        double dx2;
                        boolean handled = false;
                        if (this.sourceAbility != null && owner instanceof EntityLivingBase) {
                            dx2 = target.field_70165_t - this.field_70165_t;
                            dz2 = target.field_70161_v - this.field_70161_v;
                            handled = AbilityController.Instance.fireOnAbilityDamage(this.sourceAbility, (EntityLivingBase)owner, target, this.panelData.launchDamage, this.panelData.launchKnockback, 0.0f, dx2, dz2, 1.0f);
                        }
                        if (!handled && this.customDamageData != null && owner instanceof EntityLivingBase) {
                            dx2 = target.field_70165_t - this.field_70165_t;
                            dz2 = target.field_70161_v - this.field_70161_v;
                            handled = EnergyController.Instance.fireOnEnergyDamage(this, (EntityLivingBase)owner, target, this.panelData.launchDamage, this.panelData.launchKnockback, 0.0f, dx2, dz2, 1.0f, this.customDamageData);
                        }
                        if (!handled) {
                            if (owner instanceof EntityNPCInterface) {
                                target.func_70097_a((DamageSource)new NpcDamageSource("npc_ability", (Entity)((EntityNPCInterface)owner)), this.panelData.launchDamage);
                            } else if (owner instanceof EntityPlayer) {
                                target.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)owner)), this.panelData.launchDamage);
                            } else {
                                target.func_70097_a(DamageSource.field_76377_j, this.panelData.launchDamage);
                            }
                        }
                    }
                    finally {
                        Ability.restoreHurtResistanceIfNeeded(target, ignoreIFrames, previousHurtResistantTime);
                    }
                }
                if (!(this.panelData.launchKnockback > 0.0f) || !((len = Math.sqrt((dx = target.field_70165_t - this.field_70165_t) * dx + (dz = target.field_70161_v - this.field_70161_v) * dz)) > 0.0) || !this.canPushInDirection(target, kbX = dx / len * (double)this.panelData.launchKnockback * 0.5, 0.1, kbZ = dz / len * (double)this.panelData.launchKnockback * 0.5)) continue;
                target.func_70024_g(kbX, 0.1, kbZ);
                target.field_70133_I = true;
            }
            if (this.ticksAlive > 200) {
                this.onBarrierDestroyed();
                this.func_70106_y();
            }
        }
    }

    private boolean isIncomingRay(double currX, double currY, double currZ, double prevX, double prevY, double prevZ, int projOwnerEntityId) {
        if (this.isCharging()) {
            return false;
        }
        if (projOwnerEntityId == this.ownerEntityId) {
            return false;
        }
        float halfW = this.panelData.panelWidth * 0.5f;
        float halfH = this.panelData.panelHeight * 0.5f;
        float panelThickness = 0.5f;
        float yawRad = (float)Math.toRadians(this.panelYaw);
        double normalX = -Math.sin(yawRad);
        double normalZ = Math.cos(yawRad);
        double cos = Math.cos(yawRad);
        double sin = Math.sin(yawRad);
        double relPrevX = prevX - this.field_70165_t;
        double relPrevY = prevY - this.field_70163_u;
        double relPrevZ = prevZ - this.field_70161_v;
        double prevDist = relPrevX * normalX + relPrevZ * normalZ;
        double relCurrX = currX - this.field_70165_t;
        double relCurrY = currY - this.field_70163_u;
        double relCurrZ = currZ - this.field_70161_v;
        double currDist = relCurrX * normalX + relCurrZ * normalZ;
        if (prevDist * currDist > 0.0) {
            if (Math.abs(currDist) <= (double)panelThickness) {
                double localRight = relCurrX * cos + relCurrZ * sin;
                if (Math.abs(localRight) > (double)halfW) {
                    return false;
                }
                if (Math.abs(relCurrY) > (double)halfH) {
                    return false;
                }
                double motX = currX - prevX;
                double motZ = currZ - prevZ;
                double dot = motX * normalX + motZ * normalZ;
                if (currDist > 0.0 && dot >= 0.0) {
                    return false;
                }
                return !(currDist < 0.0) || !(dot <= 0.0);
            }
            return false;
        }
        double rayDirX = currX - prevX;
        double rayDirZ = currZ - prevZ;
        double denom = rayDirX * normalX + rayDirZ * normalZ;
        if (Math.abs(denom) < 1.0E-10) {
            return false;
        }
        double t = -prevDist / denom;
        if (t < 0.0 || t > 1.0) {
            return false;
        }
        double hitX = prevX + (currX - prevX) * t;
        double hitY = prevY + (currY - prevY) * t;
        double hitZ = prevZ + (currZ - prevZ) * t;
        double relHitX = hitX - this.field_70165_t;
        double relHitY = hitY - this.field_70163_u;
        double relHitZ = hitZ - this.field_70161_v;
        double localRight = relHitX * cos + relHitZ * sin;
        if (Math.abs(localRight) > (double)halfW) {
            return false;
        }
        return !(Math.abs(relHitY) > (double)halfH);
    }

    @Override
    public boolean isIncomingProjectile(EntityEnergyProjectile projectile) {
        Entity owner = this.getOwnerEntity();
        Entity projOwner = projectile.getOwnerEntity();
        if (owner instanceof EntityNPCInterface && projOwner instanceof EntityNPCInterface && ((EntityNPCInterface)owner).faction.id == ((EntityNPCInterface)projOwner).faction.id) {
            return false;
        }
        if (this.mode == PanelMode.LAUNCHED) {
            double relMotionX = projectile.field_70159_w - this.field_70159_w;
            double relMotionY = projectile.field_70181_x - this.field_70181_x;
            double relMotionZ = projectile.field_70179_y - this.field_70179_y;
            double nextX = projectile.field_70165_t + relMotionX;
            double nextY = projectile.field_70163_u + relMotionY;
            double nextZ = projectile.field_70161_v + relMotionZ;
            return this.isIncomingRay(nextX, nextY, nextZ, projectile.field_70165_t, projectile.field_70163_u, projectile.field_70161_v, projectile.getOwnerEntityId());
        }
        double nextX = projectile.field_70165_t + projectile.field_70159_w;
        double nextY = projectile.field_70163_u + projectile.field_70181_x;
        double nextZ = projectile.field_70161_v + projectile.field_70179_y;
        return this.isIncomingRay(nextX, nextY, nextZ, projectile.field_70165_t, projectile.field_70163_u, projectile.field_70161_v, projectile.getOwnerEntityId());
    }

    @Override
    public boolean isIncomingGenericProjectile(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, double prevPosX, double prevPosY, double prevPosZ, int ownerEntityId) {
        if (this.mode == PanelMode.LAUNCHED) {
            double relMotionX = motionX - this.field_70159_w;
            double relMotionY = motionY - this.field_70181_x;
            double relMotionZ = motionZ - this.field_70179_y;
            double adjX = prevPosX + relMotionX;
            double adjY = prevPosY + relMotionY;
            double adjZ = prevPosZ + relMotionZ;
            return this.isIncomingRay(adjX, adjY, adjZ, prevPosX, prevPosY, prevPosZ, ownerEntityId);
        }
        return this.isIncomingRay(posX, posY, posZ, prevPosX, prevPosY, prevPosZ, ownerEntityId);
    }

    @Override
    public double[] getSurfaceNormal(double hitX, double hitY, double hitZ, double velX, double velY, double velZ) {
        double nz;
        double relZ;
        double relX = hitX - this.field_70165_t;
        float yawRad = (float)Math.toRadians(this.panelYaw);
        double nx = -Math.sin(yawRad);
        double side = relX * nx + (relZ = hitZ - this.field_70161_v) * (nz = Math.cos(yawRad));
        if (Math.abs(side) < 1.0E-5) {
            side = velX * nx + velZ * nz;
        }
        if (side < 0.0) {
            nx = -nx;
            nz = -nz;
        }
        return new double[]{nx, 0.0, nz};
    }

    @Override
    public double[] getOutsideSurfacePoint(double px, double py, double pz, double velX, double velY, double velZ, float bias) {
        double vDot;
        float yawRad = (float)Math.toRadians(this.panelYaw);
        double normalX = -Math.sin(yawRad);
        double normalZ = Math.cos(yawRad);
        double relX = px - this.field_70165_t;
        double relZ = pz - this.field_70161_v;
        double signedDist = relX * normalX + relZ * normalZ;
        double halfThickness = 0.25;
        double side = Math.abs(signedDist) > 0.035 ? (signedDist >= 0.0 ? 1.0 : -1.0) : (Math.abs(vDot = velX * normalX + velZ * normalZ) > 1.0E-5 ? (vDot < 0.0 ? 1.0 : -1.0) : (signedDist >= 0.0 ? 1.0 : -1.0));
        double targetDist = side * (halfThickness + (double)bias);
        double delta = targetDist - signedDist;
        return new double[]{px + normalX * delta, py, pz + normalZ * delta};
    }

    @Override
    public float getMaxExtent() {
        return Math.max(this.panelData.panelWidth, this.panelData.panelHeight) * 0.5f + 1.0f;
    }

    @Override
    public void setupCharging(int duration) {
        this.targetPanelWidth = this.panelData.panelWidth;
        this.targetPanelHeight = this.panelData.panelHeight;
        this.panelData.panelWidth = 0.01f;
        this.panelData.panelHeight = 0.01f;
        this.chargeDuration = duration;
        this.chargeTick = 0;
        this.setCharging(true);
    }

    @Override
    public void finishCharging() {
        this.panelData.panelWidth = this.targetPanelWidth;
        this.panelData.panelHeight = this.targetPanelHeight;
        this.setCharging(false);
    }

    @Override
    protected void processEntityPhysics() {
        float halfW = this.panelData.panelWidth * 0.5f;
        float halfH = this.panelData.panelHeight * 0.5f;
        float searchExtension = 3.0f;
        boolean solid = this.barrierData.solid;
        boolean knockback = this.barrierData.knockbackEnabled;
        float strength = this.barrierData.knockbackStrength;
        AxisAlignedBB searchBox = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)halfW - (double)searchExtension), (double)(this.field_70163_u - (double)halfH - (double)searchExtension), (double)(this.field_70161_v - (double)halfW - (double)searchExtension), (double)(this.field_70165_t + (double)halfW + (double)searchExtension), (double)(this.field_70163_u + (double)halfH + (double)searchExtension), (double)(this.field_70161_v + (double)halfW + (double)searchExtension));
        EntityPlayer localPlayer = this.getClientPredictionPlayer();
        List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, searchBox);
        float yawRad = (float)Math.toRadians(this.panelYaw);
        float normalX = -((float)Math.sin(yawRad));
        float normalZ = (float)Math.cos(yawRad);
        for (EntityLivingBase ent : entities) {
            double absDist;
            boolean inBounds;
            if (this.shouldSkipBarrierPhysicsTarget(ent, localPlayer)) continue;
            double dx = ent.field_70165_t - this.field_70165_t;
            double dy = ent.field_70163_u + (double)ent.field_70131_O * 0.5 - this.field_70163_u;
            double dz = ent.field_70161_v - this.field_70161_v;
            float localForward = (float)(dx * (double)normalX + dz * (double)normalZ);
            float localRight = (float)(dx * (double)(-normalZ) + dz * (double)normalX);
            if (Math.abs(localRight) > halfW + 1.0f || Math.abs(dy) > (double)(halfH + 1.0f) || Math.abs(localForward) > searchExtension) continue;
            float side = localForward >= 0.0f ? 1.0f : -1.0f;
            boolean bl = inBounds = Math.abs(localRight) <= halfW && Math.abs(dy) <= (double)halfH;
            if (solid && inBounds) {
                double prevDx = ent.field_70169_q - this.field_70169_q;
                double prevDz = ent.field_70166_s - this.field_70166_s;
                float prevLocalForward = (float)(prevDx * (double)normalX + prevDz * (double)normalZ);
                float prevSide = prevLocalForward >= 0.0f ? 1.0f : -1.0f;
                double normalVel = ent.field_70159_w * (double)normalX + ent.field_70179_y * (double)normalZ;
                if (prevSide != side) {
                    double pushDist = 0.3;
                    double newX = this.field_70165_t + (double)(normalX * prevSide) * pushDist + (double)(localRight * -normalZ);
                    double newZ = this.field_70161_v + (double)(normalZ * prevSide) * pushDist + (double)(localRight * normalX);
                    this.teleportEntity(ent, newX, ent.field_70163_u, newZ);
                    ent.field_70159_w -= normalVel * (double)normalX;
                    ent.field_70179_y -= normalVel * (double)normalZ;
                    double pushX = (double)(normalX * prevSide) * 0.15;
                    double pushZ = (double)(normalZ * prevSide) * 0.15;
                    if (this.canPushInDirection(ent, pushX, 0.0, pushZ)) {
                        ent.field_70159_w += pushX;
                        ent.field_70179_y += pushZ;
                    }
                    ent.field_70133_I = true;
                } else {
                    double absDist2 = Math.abs(localForward);
                    if (absDist2 < 1.5) {
                        boolean movingToward;
                        boolean bl2 = movingToward = side > 0.0f && normalVel < -0.01 || side < 0.0f && normalVel > 0.01;
                        if (movingToward) {
                            ent.field_70159_w -= normalVel * (double)normalX;
                            ent.field_70179_y -= normalVel * (double)normalZ;
                            double pushX = (double)(normalX * side) * 0.05;
                            double pushZ = (double)(normalZ * side) * 0.05;
                            if (this.canPushInDirection(ent, pushX, 0.0, pushZ)) {
                                ent.field_70159_w += pushX;
                                ent.field_70179_y += pushZ;
                            }
                            ent.field_70133_I = true;
                        }
                    }
                }
            }
            if (this.field_70170_p.field_72995_K || !knockback || !inBounds || !((absDist = (double)Math.abs(localForward)) < (double)searchExtension)) continue;
            double proximity = 1.0 - absDist / (double)searchExtension;
            double force = proximity * (double)strength * 0.06;
            double pushX = (double)(normalX * side) * force;
            double pushZ = (double)(normalZ * side) * force;
            if (this.canPushInDirection(ent, pushX, 0.0, pushZ)) {
                ent.field_70159_w += pushX;
                ent.field_70179_y += pushZ;
            }
            ent.field_70133_I = true;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        double d = (double)Math.max(this.panelData.panelWidth, this.panelData.panelHeight) * 8.0;
        return distance < (d *= 64.0) * d;
    }

    public double func_70092_e(double x, double y, double z) {
        double dx = x - this.field_70165_t;
        double dy = y - this.field_70163_u;
        double dz = z - this.field_70161_v;
        float yawRad = (float)Math.toRadians(this.panelYaw);
        double cos = Math.cos(yawRad);
        double sin = Math.sin(yawRad);
        double localForward = dx * -sin + dz * cos;
        double localRight = dx * cos + dz * sin;
        double localUp = dy;
        float halfW = this.panelData.panelWidth * 0.5f;
        float halfH = this.panelData.panelHeight * 0.5f;
        float halfThickness = 0.25f;
        double clampedForward = Math.max((double)(-halfThickness), Math.min((double)halfThickness, localForward));
        double clampedRight = Math.max((double)(-halfW), Math.min((double)halfW, localRight));
        double clampedUp = Math.max((double)(-halfH), Math.min((double)halfH, localUp));
        double df = localForward - clampedForward;
        double dr = localRight - clampedRight;
        double du = localUp - clampedUp;
        return df * df + dr * dr + du * du;
    }

    @Override
    protected String debugLogBarrierExtra() {
        return String.format("mode=%s yaw=%.1f w=%.2f h=%.2f", this.mode.name(), Float.valueOf(this.panelYaw), Float.valueOf(this.panelData.panelWidth), Float.valueOf(this.panelData.panelHeight));
    }

    public PanelMode getMode() {
        return this.mode;
    }

    public float getPanelYaw() {
        return this.panelYaw;
    }

    public float getPrevPanelYaw() {
        return this.prevPanelYaw;
    }

    public EnergyPanelData getPanelData() {
        return this.panelData;
    }

    public void setPanelYaw(float yaw) {
        this.panelYaw = yaw;
        this.prevPanelYaw = yaw;
    }

    public void setMode(PanelMode mode) {
        this.mode = mode;
    }

    @Override
    protected void writeBarrierClientSyncData(NBTTagCompound nbt) {
        nbt.func_74776_a("PanelWidth", this.panelData.panelWidth);
        nbt.func_74776_a("PanelHeight", this.panelData.panelHeight);
        nbt.func_74776_a("PanelYaw", this.panelYaw);
    }

    @Override
    protected void applyBarrierClientSyncData(NBTTagCompound nbt) {
        this.panelData.panelWidth = nbt.func_74760_g("PanelWidth");
        this.panelData.panelHeight = nbt.func_74760_g("PanelHeight");
        this.panelYaw = nbt.func_74760_g("PanelYaw");
    }

    @Override
    protected void writeSpawnNBT(NBTTagCompound nbt) {
        this.writeBarrierBaseNBT(nbt);
        nbt.func_74776_a("PanelYaw", this.panelYaw);
        nbt.func_74768_a("PanelMode", this.mode.ordinal());
        nbt.func_74776_a("TargetPanelWidth", this.targetPanelWidth);
        nbt.func_74776_a("TargetPanelHeight", this.targetPanelHeight);
        this.panelData.writeNBT(nbt);
    }

    @Override
    protected void readSpawnNBT(NBTTagCompound nbt) {
        this.readBarrierBaseNBT(nbt);
        this.panelYaw = nbt.func_74760_g("PanelYaw");
        int modeOrdinal = nbt.func_74762_e("PanelMode");
        this.mode = modeOrdinal >= 0 && modeOrdinal < PanelMode.values().length ? PanelMode.values()[modeOrdinal] : PanelMode.PLACED;
        this.panelData.readNBT(nbt);
        this.panelData.panelWidth = EntityEnergyPanel.sanitize(this.panelData.panelWidth, 3.0f, 100.0f);
        this.panelData.panelHeight = EntityEnergyPanel.sanitize(this.panelData.panelHeight, 3.0f, 100.0f);
        this.targetPanelWidth = EntityEnergyPanel.sanitize(nbt.func_74764_b("TargetPanelWidth") ? nbt.func_74760_g("TargetPanelWidth") : this.panelData.panelWidth, 3.0f, 100.0f);
        this.targetPanelHeight = EntityEnergyPanel.sanitize(nbt.func_74764_b("TargetPanelHeight") ? nbt.func_74760_g("TargetPanelHeight") : this.panelData.panelHeight, 3.0f, 100.0f);
    }

    public static enum PanelMode {
        PLACED,
        HELD,
        LAUNCHED;

    }
}

