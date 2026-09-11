/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyBarrierData;
import kamkeel.npcs.controllers.data.ability.util.AbilityTargetHelper;
import kamkeel.npcs.entity.EntityEnergyAbility;
import kamkeel.npcs.entity.EntityEnergyDome;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.network.packets.data.energy.BarrierClientSyncPacket;
import kamkeel.npcs.util.AttributeAttackUtil;
import kamkeel.npcs.util.CNPCDebug;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.EventHooks;
import noppes.npcs.controllers.data.MagicData;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;

public abstract class EntityEnergyBarrier
extends EntityEnergyAbility {
    private static final List<WeakReference<EntityEnergyBarrier>> activeBarriers = new ArrayList<WeakReference<EntityEnergyBarrier>>();
    private boolean tracked = false;
    protected EnergyBarrierData barrierData = new EnergyBarrierData();
    protected float currentHealth;
    protected int ticksAlive = 0;
    protected static final int DW_HEALTH_PERCENT = 21;
    protected static final int DW_HIT_FLASH = 22;

    public static void trackBarrier(EntityEnergyBarrier barrier) {
        activeBarriers.add(new WeakReference<EntityEnergyBarrier>(barrier));
    }

    public static List<EntityEnergyBarrier> getActiveBarriers(World world) {
        ArrayList<EntityEnergyBarrier> result = new ArrayList<EntityEnergyBarrier>();
        Iterator<WeakReference<EntityEnergyBarrier>> it = activeBarriers.iterator();
        while (it.hasNext()) {
            EntityEnergyBarrier b = (EntityEnergyBarrier)((Object)it.next().get());
            if (b == null || b.field_70128_L) {
                it.remove();
                continue;
            }
            if (b.field_70170_p != world) continue;
            result.add(b);
        }
        return result;
    }

    public static void clearAllBarriers() {
        activeBarriers.clear();
    }

    public EntityEnergyBarrier(World world) {
        super(world);
        this.field_70145_X = true;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(21, (Object)Float.valueOf(1.0f));
        this.field_70180_af.func_75682_a(22, (Object)0);
    }

    public abstract boolean isIncomingProjectile(EntityEnergyProjectile var1);

    public abstract void setupCharging(int var1);

    public abstract void finishCharging();

    protected abstract void processEntityPhysics();

    public abstract float getMaxExtent();

    public abstract boolean isIncomingGenericProjectile(double var1, double var3, double var5, double var7, double var9, double var11, double var13, double var15, double var17, int var19);

    public abstract double[] getSurfaceNormal(double var1, double var3, double var5, double var7, double var9, double var11);

    public abstract double[] getOutsideSurfacePoint(double var1, double var3, double var5, double var7, double var9, double var11, float var13);

    public boolean onGenericProjectileHit(Entity projectileEntity, float damage, String typeId) {
        float multiplier = this.barrierData.getMultiplier(typeId);
        float finalDamage = damage * multiplier;
        if (!this.field_70170_p.field_72995_K) {
            float eventDamage = EventHooks.onEnergyBarrierHit(this, this.ownerEntityId, null, finalDamage);
            if (eventDamage < 0.0f) {
                return false;
            }
            finalDamage = eventDamage;
        }
        this.triggerHitFlash();
        if (!this.barrierData.useHealth) {
            return true;
        }
        this.currentHealth -= finalDamage;
        this.syncHealthPercent();
        if (this.currentHealth <= 0.0f) {
            this.onBarrierDestroyed();
            this.func_70106_y();
        }
        return true;
    }

    public ProjectileHitOutcome onGenericProjectileHitResolved(Entity projectileEntity, float damage, String typeId) {
        float multiplier = this.barrierData.getMultiplier(typeId);
        float finalDamage = damage * multiplier;
        if (projectileEntity instanceof EntityEnergyAbility) {
            float magicMultiplier = AttributeAttackUtil.calculateMagicInteractionMultiplier(((EntityEnergyAbility)projectileEntity).getMagicData(), this.magicData);
            finalDamage *= magicMultiplier;
        }
        if (!this.field_70170_p.field_72995_K) {
            float eventDamage = EventHooks.onEnergyBarrierHit(this, this.ownerEntityId, null, finalDamage);
            if (eventDamage < 0.0f) {
                return new ProjectileHitOutcome(ProjectileHitResult.PASS, this.currentHealth, this.currentHealth, 0.0f, 0.0f, this.barrierData.reflect, this.barrierData.reflectStrengthPct, this.barrierData.useHealth);
            }
            finalDamage = eventDamage;
        }
        this.triggerHitFlash();
        float healthBefore = this.currentHealth;
        if (!this.barrierData.useHealth) {
            return new ProjectileHitOutcome(ProjectileHitResult.BLOCKED, healthBefore, healthBefore, 0.0f, 0.0f, this.barrierData.reflect, this.barrierData.reflectStrengthPct, false);
        }
        this.currentHealth -= finalDamage;
        this.syncHealthPercent();
        if (this.currentHealth <= 0.0f) {
            float remaining = Math.max(0.0f, finalDamage - healthBefore);
            this.onBarrierDestroyed();
            this.func_70106_y();
            return new ProjectileHitOutcome(ProjectileHitResult.BROKEN, healthBefore, 0.0f, finalDamage, remaining, this.barrierData.reflect, this.barrierData.reflectStrengthPct, true);
        }
        return new ProjectileHitOutcome(ProjectileHitResult.BLOCKED, healthBefore, this.currentHealth, finalDamage, 0.0f, this.barrierData.reflect, this.barrierData.reflectStrengthPct, true);
    }

    public boolean onProjectileHit(EntityEnergyProjectile projectile, float baseDamage) {
        return this.onProjectileHitDetailed(projectile, baseDamage).isAbsorbed();
    }

    public ProjectileHitResult onProjectileHitDetailed(EntityEnergyProjectile projectile, float baseDamage) {
        return this.onProjectileHitResolved((EntityEnergyProjectile)projectile, (float)baseDamage).result;
    }

    public ProjectileHitOutcome onProjectileHitResolved(EntityEnergyProjectile projectile, float baseDamage) {
        String typeId = "";
        if (projectile.getSourceAbility() != null) {
            typeId = projectile.getSourceAbility().getTypeId();
        }
        float multiplier = this.barrierData.getMultiplier(typeId);
        float damage = baseDamage * multiplier;
        float magicMultiplier = AttributeAttackUtil.calculateMagicInteractionMultiplier(projectile.getMagicData(), this.magicData);
        damage *= magicMultiplier;
        if (!this.field_70170_p.field_72995_K) {
            float eventDamage = EventHooks.onEnergyBarrierHit(this, this.ownerEntityId, projectile, damage);
            if (eventDamage < 0.0f) {
                return new ProjectileHitOutcome(ProjectileHitResult.PASS, this.currentHealth, this.currentHealth, 0.0f, 0.0f, this.barrierData.reflect, this.barrierData.reflectStrengthPct, this.barrierData.useHealth);
            }
            damage = eventDamage;
        }
        this.triggerHitFlash();
        float healthBefore = this.currentHealth;
        if (!this.barrierData.useHealth) {
            return new ProjectileHitOutcome(ProjectileHitResult.BLOCKED, healthBefore, healthBefore, 0.0f, 0.0f, this.barrierData.reflect, this.barrierData.reflectStrengthPct, false);
        }
        this.currentHealth -= damage;
        this.syncHealthPercent();
        if (this.currentHealth <= 0.0f) {
            float remaining = Math.max(0.0f, damage - healthBefore);
            this.onBarrierDestroyed();
            this.func_70106_y();
            return new ProjectileHitOutcome(ProjectileHitResult.BROKEN, healthBefore, 0.0f, damage, remaining, this.barrierData.reflect, this.barrierData.reflectStrengthPct, true);
        }
        return new ProjectileHitOutcome(ProjectileHitResult.BLOCKED, healthBefore, this.currentHealth, damage, 0.0f, this.barrierData.reflect, this.barrierData.reflectStrengthPct, true);
    }

    public boolean func_70067_L() {
        return this.barrierData.meleeEnabled;
    }

    public boolean func_70094_T() {
        return false;
    }

    protected boolean func_145771_j(double x, double y, double z) {
        return false;
    }

    public boolean func_70097_a(DamageSource source, float amount) {
        float eventDamage;
        if (this.field_70170_p.field_72995_K || !this.barrierData.meleeEnabled) {
            return false;
        }
        if (this.isCharging()) {
            return false;
        }
        float damage = amount * this.barrierData.meleeDamageMultiplier;
        Entity attacker = source.func_76346_g();
        if (attacker != null) {
            MagicData attackerMagic = null;
            if (attacker instanceof EntityPlayer) {
                PlayerData data = PlayerData.get((EntityPlayer)attacker);
                if (data != null) {
                    attackerMagic = data.magicData;
                }
            } else if (attacker instanceof EntityNPCInterface) {
                EntityNPCInterface npc = (EntityNPCInterface)attacker;
                if (npc.stats != null) {
                    attackerMagic = npc.stats.magicData;
                }
            }
            if (attackerMagic != null) {
                float magicMultiplier = AttributeAttackUtil.calculateMagicInteractionMultiplier(attackerMagic, this.magicData);
                damage *= magicMultiplier;
            }
        }
        if ((eventDamage = EventHooks.onEnergyBarrierHit(this, this.ownerEntityId, null, damage)) < 0.0f) {
            return false;
        }
        damage = eventDamage;
        this.triggerHitFlash();
        if (!this.barrierData.useHealth) {
            return true;
        }
        this.currentHealth -= damage;
        this.syncHealthPercent();
        if (this.currentHealth <= 0.0f) {
            this.onBarrierDestroyed();
            this.func_70106_y();
        }
        return true;
    }

    protected boolean updateBarrierTick() {
        if (!this.field_70170_p.field_72995_K) {
            Entity owner;
            if (this.ticksAlive == 1) {
                if (!this.tracked) {
                    EntityEnergyBarrier.trackBarrier(this);
                    this.tracked = true;
                }
                EventHooks.onEnergyBarrierSpawned(this, this.ownerEntityId);
            }
            if (this.ownerEntityId >= 0 && this.ticksAlive > 5 && (owner = this.field_70170_p.func_73045_a(this.ownerEntityId)) != null) {
                if (owner.field_70128_L) {
                    this.onBarrierDestroyed();
                    this.func_70106_y();
                    return true;
                }
                if (owner instanceof EntityNPCInterface && ((EntityNPCInterface)owner).isKilled()) {
                    this.onBarrierDestroyed();
                    this.func_70106_y();
                    return true;
                }
            }
            if (this.barrierData.useDuration && this.ticksAlive >= this.barrierData.durationTicks) {
                this.onBarrierDestroyed();
                this.func_70106_y();
                return true;
            }
            if (this.ticksAlive > 12000) {
                this.onBarrierDestroyed();
                this.func_70106_y();
                return true;
            }
            if (this.getHitFlash() > 0) {
                this.setHitFlash((byte)(this.getHitFlash() - 1));
            }
            EventHooks.onEnergyBarrierTick(this, this.ownerEntityId);
        }
        return false;
    }

    protected void onBarrierDestroyed() {
        EventHooks.onEnergyBarrierDestroyed(this, this.ownerEntityId);
    }

    protected void syncHealthPercent() {
        float percent;
        float f = percent = this.barrierData.useHealth && this.barrierData.maxHealth > 0.0f ? Math.max(0.0f, this.currentHealth / this.barrierData.maxHealth) : 1.0f;
        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(21, (Object)Float.valueOf(percent));
        }
    }

    protected void triggerHitFlash() {
        if (!this.field_70170_p.field_72995_K) {
            this.setHitFlash((byte)4);
        }
    }

    protected void setHitFlash(byte value) {
        this.field_70180_af.func_75692_b(22, (Object)value);
    }

    public byte getHitFlash() {
        return this.field_70180_af.func_75683_a(22);
    }

    public float getHealthPercent() {
        return this.field_70180_af.func_111145_d(21);
    }

    protected EntityPlayer getClientPredictionPlayer() {
        return this.field_70170_p != null && this.field_70170_p.field_72995_K ? CustomNpcs.proxy.getPlayer() : null;
    }

    protected boolean shouldSkipBarrierPhysicsTarget(EntityLivingBase entity, EntityPlayer clientPredictionPlayer) {
        if (entity == null || !entity.func_70089_S()) {
            return true;
        }
        if (entity.func_145782_y() == this.ownerEntityId) {
            return true;
        }
        if (this.field_70170_p.field_72995_K && (clientPredictionPlayer == null || entity != clientPredictionPlayer)) {
            return true;
        }
        return this.isAllyOfOwner(entity);
    }

    protected boolean isAllyOfOwner(EntityLivingBase entity) {
        Entity owner = this.getOwnerEntity();
        if (owner == null || !(owner instanceof EntityLivingBase)) {
            return false;
        }
        if (this.field_70170_p != null && this.field_70170_p.field_72995_K) {
            return this.isClientPredictedAlly((EntityLivingBase)owner, entity);
        }
        return AbilityTargetHelper.isAlly((EntityLivingBase)owner, (Entity)entity);
    }

    protected boolean isClientPredictedAlly(EntityLivingBase owner, EntityLivingBase target) {
        if (owner == null || target == null) {
            return false;
        }
        if (owner == target) {
            return true;
        }
        if (target instanceof EntityNPCInterface) {
            EntityNPCInterface targetNpc = (EntityNPCInterface)target;
            if (targetNpc.faction.isPassive) {
                return true;
            }
            if (owner instanceof EntityNPCInterface) {
                EntityNPCInterface ownerNpc = (EntityNPCInterface)owner;
                return ownerNpc.faction.id == targetNpc.faction.id;
            }
        }
        return false;
    }

    public boolean isEntityInside(Entity entity) {
        return false;
    }

    public static EntityEnergyBarrier getAbsorbingBarrier(Entity entity) {
        if (entity == null || entity.field_70170_p == null) {
            return null;
        }
        List<EntityEnergyBarrier> barriers = EntityEnergyBarrier.getActiveBarriers(entity.field_70170_p);
        for (EntityEnergyBarrier barrier : barriers) {
            if (barrier.field_70128_L || !barrier.barrierData.absorbing || barrier.ownerEntityId != entity.func_145782_y()) continue;
            float absorbRadius = barrier.barrierData.absorbRadius;
            if (absorbRadius < 0.0f) {
                return barrier;
            }
            double dx = entity.field_70165_t - barrier.field_70165_t;
            double dy = entity.field_70163_u + (double)entity.field_70131_O * 0.5 - barrier.field_70163_u;
            double dz = entity.field_70161_v - barrier.field_70161_v;
            double distSq = dx * dx + dy * dy + dz * dz;
            float effectiveRadius = absorbRadius > 0.0f ? absorbRadius : (barrier instanceof EntityEnergyDome ? barrier.getMaxExtent() : barrier.getMaxExtent() * 3.0f);
            if (!(distSq <= (double)effectiveRadius * (double)effectiveRadius)) continue;
            return barrier;
        }
        return null;
    }

    public boolean absorbDamage(float amount) {
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        this.triggerHitFlash();
        if (!this.barrierData.useHealth) {
            return true;
        }
        this.currentHealth -= amount;
        this.syncHealthPercent();
        if (this.currentHealth <= 0.0f) {
            this.onBarrierDestroyed();
            this.func_70106_y();
        }
        return true;
    }

    protected void teleportEntity(EntityLivingBase ent, double x, double y, double z) {
        if (!this.isPositionClear(ent, x, y, z)) {
            return;
        }
        if (ent instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)ent;
            player.field_71135_a.func_147364_a(x, y, z, player.field_70177_z, player.field_70125_A);
        } else {
            ent.func_70107_b(x, y, z);
        }
    }

    protected boolean isPositionClear(EntityLivingBase ent, double x, double y, double z) {
        double halfW = (double)ent.field_70130_N * 0.5;
        AxisAlignedBB box = AxisAlignedBB.func_72330_a((double)(x - halfW), (double)y, (double)(z - halfW), (double)(x + halfW), (double)(y + (double)ent.field_70131_O), (double)(z + halfW));
        return this.field_70170_p.func_72945_a((Entity)ent, box).isEmpty();
    }

    protected boolean canPushInDirection(EntityLivingBase ent, double dirX, double dirY, double dirZ) {
        double probe = 0.3;
        double halfW = (double)ent.field_70130_N * 0.5;
        double px = ent.field_70165_t + dirX * probe;
        double py = ent.field_70163_u + dirY * probe;
        double pz = ent.field_70161_v + dirZ * probe;
        if (py < ent.field_70163_u) {
            py = ent.field_70163_u;
        }
        AxisAlignedBB box = AxisAlignedBB.func_72330_a((double)(px - halfW), (double)py, (double)(pz - halfW), (double)(px + halfW), (double)(py + (double)ent.field_70131_O), (double)(pz + halfW));
        return this.field_70170_p.func_72945_a((Entity)ent, box).isEmpty();
    }

    public EnergyBarrierData getBarrierData() {
        return this.barrierData;
    }

    public float getCurrentHealth() {
        return this.currentHealth;
    }

    public void setCurrentHealth(float health) {
        this.currentHealth = health;
        this.syncHealthPercent();
    }

    public void setBarrierMaxHealth(float maxHealth) {
        this.barrierData.setMaxHealth(maxHealth);
        this.currentHealth = maxHealth;
        this.syncHealthPercent();
    }

    public int getTicksAlive() {
        return this.ticksAlive;
    }

    public NBTTagCompound writeClientSyncData() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74780_a("PosX", this.field_70165_t);
        nbt.func_74780_a("PosY", this.field_70163_u);
        nbt.func_74780_a("PosZ", this.field_70161_v);
        nbt.func_74768_a("InnerColor", this.getInnerColor());
        nbt.func_74776_a("InnerAlpha", this.getInnerAlpha());
        nbt.func_74768_a("OuterColor", this.getOuterColor());
        nbt.func_74757_a("OuterColorEnabled", this.isOuterColorEnabled());
        nbt.func_74776_a("OuterColorWidth", this.getOuterColorWidth());
        nbt.func_74776_a("OuterColorAlpha", this.getOuterColorAlpha());
        nbt.func_74776_a("RotationSpeed", this.getRotationSpeed());
        nbt.func_74757_a("LightningEffect", this.hasLightningEffect());
        nbt.func_74776_a("LightningDensity", this.getLightningDensity());
        nbt.func_74776_a("LightningRadius", this.getLightningRadius());
        nbt.func_74768_a("LightningFadeTime", this.getLightningFadeTime());
        this.writeBarrierClientSyncData(nbt);
        return nbt;
    }

    public void applyClientSyncData(NBTTagCompound nbt) {
        this.func_70107_b(nbt.func_74769_h("PosX"), nbt.func_74769_h("PosY"), nbt.func_74769_h("PosZ"));
        this.displayData.setInnerColor(nbt.func_74762_e("InnerColor"));
        this.displayData.setInnerAlpha(nbt.func_74760_g("InnerAlpha"));
        this.displayData.setOuterColor(nbt.func_74762_e("OuterColor"));
        this.displayData.setOuterColorEnabled(nbt.func_74767_n("OuterColorEnabled"));
        this.displayData.setOuterColorWidth(nbt.func_74760_g("OuterColorWidth"));
        this.displayData.setOuterColorAlpha(nbt.func_74760_g("OuterColorAlpha"));
        this.displayData.setRotationSpeed(nbt.func_74760_g("RotationSpeed"));
        this.setLightningEffect(nbt.func_74767_n("LightningEffect"));
        this.setLightningDensity(nbt.func_74760_g("LightningDensity"));
        this.setLightningRadius(nbt.func_74760_g("LightningRadius"));
        this.setLightningFadeTime(nbt.func_74762_e("LightningFadeTime"));
        this.applyBarrierClientSyncData(nbt);
    }

    protected void writeBarrierClientSyncData(NBTTagCompound nbt) {
    }

    protected void applyBarrierClientSyncData(NBTTagCompound nbt) {
    }

    public void sendClientSync() {
        if (this.field_70170_p == null || this.field_70170_p.field_72995_K) {
            return;
        }
        BarrierClientSyncPacket.sendToTracking(this, this.writeClientSyncData());
    }

    protected void writeBarrierBaseNBT(NBTTagCompound nbt) {
        this.writeEnergyBaseNBT(nbt);
        nbt.func_74768_a("TicksAlive", this.ticksAlive);
        nbt.func_74776_a("CurrentHealth", this.currentHealth);
        nbt.func_74757_a("Charging", this.charging);
        nbt.func_74768_a("ChargeTick", this.chargeTick);
        nbt.func_74768_a("ChargeDuration", this.chargeDuration);
        this.barrierData.writeNBT(nbt);
    }

    protected void readBarrierBaseNBT(NBTTagCompound nbt) {
        this.readEnergyBaseNBT(nbt);
        this.ticksAlive = nbt.func_74762_e("TicksAlive");
        this.currentHealth = nbt.func_74760_g("CurrentHealth");
        if (Float.isNaN(this.currentHealth) || Float.isInfinite(this.currentHealth) || this.currentHealth < 0.0f) {
            this.currentHealth = this.barrierData.maxHealth;
        }
        this.charging = nbt.func_74764_b("Charging") && nbt.func_74767_n("Charging");
        this.chargeTick = nbt.func_74762_e("ChargeTick");
        this.chargeDuration = nbt.func_74762_e("ChargeDuration");
        this.field_70180_af.func_75692_b(20, (Object)((byte)(this.charging ? 1 : 0)));
        this.barrierData.readNBT(nbt);
    }

    protected void debugLogBarrierTick() {
        boolean isClient = this.field_70170_p.field_72995_K;
        if (isClient ? !CNPCDebug.isClientEnabled("energy") : !CNPCDebug.isServerEnabled("energy")) {
            return;
        }
        String className = ((Object)((Object)this)).getClass().getSimpleName();
        String base = String.format("[%s id=%d tick=%d] pos=(%.2f,%.2f,%.2f) charging=%b health=%.1f", className, this.func_145782_y(), this.ticksAlive, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.isCharging(), Float.valueOf(this.currentHealth));
        String extra = this.debugLogBarrierExtra();
        String full = extra.isEmpty() ? base : base + " " + extra;
        CNPCDebug.log("energy", isClient, full);
    }

    protected String debugLogBarrierExtra() {
        return "";
    }

    public static class ProjectileHitOutcome {
        public final ProjectileHitResult result;
        public final float barrierHealthBefore;
        public final float barrierHealthAfter;
        public final float appliedBarrierDamage;
        public final float remainingProjectileDamage;
        public final boolean reflectEnabled;
        public final float reflectStrengthPct;
        public final boolean useHealth;

        public ProjectileHitOutcome(ProjectileHitResult result, float barrierHealthBefore, float barrierHealthAfter, float appliedBarrierDamage, float remainingProjectileDamage, boolean reflectEnabled, float reflectStrengthPct, boolean useHealth) {
            this.result = result;
            this.barrierHealthBefore = barrierHealthBefore;
            this.barrierHealthAfter = barrierHealthAfter;
            this.appliedBarrierDamage = appliedBarrierDamage;
            this.remainingProjectileDamage = remainingProjectileDamage;
            this.reflectEnabled = reflectEnabled;
            this.reflectStrengthPct = Math.max(0.0f, Math.min(100.0f, reflectStrengthPct));
            this.useHealth = useHealth;
        }

        public boolean shouldReflect() {
            return this.reflectEnabled && this.result == ProjectileHitResult.BLOCKED;
        }
    }

    public static enum ProjectileHitResult {
        PASS,
        BLOCKED,
        BROKEN;


        public boolean isAbsorbed() {
            return this == BLOCKED || this == BROKEN;
        }
    }
}

