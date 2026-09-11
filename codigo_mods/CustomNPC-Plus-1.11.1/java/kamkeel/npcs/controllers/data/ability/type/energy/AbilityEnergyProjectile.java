/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.Vec3
 */
package kamkeel.npcs.controllers.data.ability.type.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.data.ProjectileData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyCombatData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyHomingData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLifespanData;
import kamkeel.npcs.controllers.data.ability.enums.AnchorPoint;
import kamkeel.npcs.controllers.data.ability.enums.HitType;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityEnergy;
import kamkeel.npcs.controllers.data.energycharge.EnergyChargeTracker;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityEnergyAbility;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.network.packets.data.energycharge.EnergyChargeRemovePacket;
import kamkeel.npcs.network.packets.data.energycharge.EnergyChargeSpawnPacket;
import kamkeel.npcs.util.AnchorPointHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import noppes.npcs.api.ability.type.IAbilityEnergyProjectile;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.controllers.data.MagicData;

public abstract class AbilityEnergyProjectile<E extends EntityEnergyProjectile>
extends AbilityEnergy
implements IAbilityEnergyProjectile {
    protected static final int MAX_PROJECTILES = 8;
    protected int projectileCount = 1;
    protected int fireDelay = 0;
    protected final EnergyCombatData combatData;
    protected final EnergyHomingData homingData;
    protected final EnergyLifespanData lifespanData;
    protected ProjectileData[] projectiles;
    protected transient E[] entities;
    protected transient boolean[] projectileSpawned;
    protected transient int spawnedCount;
    protected transient String[] chargeVisualIds;
    protected transient EntityLivingBase chargeVisualCaster;

    protected AbilityEnergyProjectile(EnergyDisplayData displayData, EnergyCombatData combatData, EnergyHomingData homingData, EnergyLifespanData lifespanData) {
        super(displayData);
        this.combatData = combatData;
        this.homingData = homingData;
        this.lifespanData = lifespanData;
        this.burstOverlap = true;
        this.initProjectiles(1);
    }

    protected abstract E createEntity(EntityLivingBase var1, EntityLivingBase var2, Vec3 var3, EnergyDisplayData var4, int var5);

    protected abstract void fireEntity(E var1, EntityLivingBase var2);

    protected abstract void setupEntityCharging(E var1, ProjectileData var2, int var3);

    protected abstract void setupEntityPreview(E var1, EntityLivingBase var2, EnergyDisplayData var3, ProjectileData var4, int var5);

    protected abstract E[] createEntityArray(int var1);

    protected abstract float getProjectileTelegraphRadius();

    @SideOnly(value=Side.CLIENT)
    protected abstract void addTypeDefinitions(List<FieldDef> var1);

    protected abstract void writeTypeSpecificNBT(NBTTagCompound var1);

    protected abstract void readTypeSpecificNBT(NBTTagCompound var1);

    protected AnchorPoint getDefaultAnchor(int index) {
        switch (index) {
            case 0: {
                return AnchorPoint.RIGHT_HAND;
            }
            case 1: {
                return AnchorPoint.LEFT_HAND;
            }
        }
        return AnchorPoint.FRONT;
    }

    protected Vec3 getSpawnPosition(EntityLivingBase caster, int index) {
        return AnchorPointHelper.calculateAnchorPosition(caster, this.projectiles[index].anchor);
    }

    protected void initProjectiles(int count) {
        this.projectileCount = Math.max(1, Math.min(count, 8));
        this.projectiles = new ProjectileData[this.projectileCount];
        for (int i = 0; i < this.projectileCount; ++i) {
            this.projectiles[i] = new ProjectileData(this.getDefaultAnchor(i));
        }
    }

    public void setProjectileCount(int count) {
        int newCount = Math.max(1, Math.min(count, 8));
        if (newCount == this.projectileCount) {
            return;
        }
        ProjectileData[] oldProjectiles = this.projectiles;
        this.initProjectiles(newCount);
        if (oldProjectiles != null) {
            int copyCount = Math.min(oldProjectiles.length, newCount);
            for (int i = 0; i < copyCount; ++i) {
                if (oldProjectiles[i] == null) continue;
                this.projectiles[i] = oldProjectiles[i];
            }
        }
    }

    public int getProjectileCount() {
        return this.projectileCount;
    }

    public int getFireDelay() {
        return this.fireDelay;
    }

    public void setFireDelay(int delay) {
        this.fireDelay = Math.max(0, delay);
    }

    protected int clampIndex(int index) {
        return Math.max(0, Math.min(index, this.projectileCount - 1));
    }

    protected void initRuntimeState(EntityLivingBase caster) {
        this.entities = this.createEntityArray(this.projectileCount);
        this.projectileSpawned = new boolean[this.projectileCount];
        this.spawnedCount = 0;
        this.chargeVisualIds = new String[this.projectileCount];
        this.chargeVisualCaster = caster;
    }

    protected E createProjectileEntity(EntityLivingBase caster, EntityLivingBase target, int index) {
        Vec3 spawnPos = this.getSpawnPosition(caster, index);
        EnergyDisplayData resolved = this.projectiles[index].resolveDisplay(this.displayData);
        E entity = this.createEntity(caster, target, spawnPos, resolved, index);
        ((EntityEnergyProjectile)((Object)entity)).setAnchorData(this.projectiles[index].anchor);
        ((EntityEnergyProjectile)((Object)entity)).setEffects(this.effects);
        ((EntityEnergyAbility)((Object)entity)).setIgnoreIFrames(this.isIgnoreIFrames());
        ((EntityEnergyAbility)((Object)entity)).setSourceAbility(this);
        MagicData resolvedMagic = this.resolveMagicData(caster);
        if (resolvedMagic != null) {
            ((EntityEnergyAbility)((Object)entity)).setMagicData(resolvedMagic.copy());
        }
        return entity;
    }

    protected void spawnProjectileEntity(E entity, int index) {
        if (entity == null) {
            return;
        }
        this.spawnAbilityEntity((Entity)entity);
        this.entities[index] = entity;
        if (!this.projectileSpawned[index]) {
            this.projectileSpawned[index] = true;
            ++this.spawnedCount;
        }
    }

    protected void spawnAndFireProjectile(EntityLivingBase caster, EntityLivingBase target, int index) {
        if (this.entities != null && this.entities[index] != null && !((EntityEnergyProjectile)((Object)this.entities[index])).field_70128_L) {
            return;
        }
        E entity = this.createProjectileEntity(caster, target, index);
        this.fireEntitySafe(entity, caster, target);
        this.spawnProjectileEntity(entity, index);
    }

    protected EntityLivingBase getLaunchTarget(EntityLivingBase caster, EntityLivingBase target) {
        return this.isPlayerCaster(caster) ? null : target;
    }

    protected void fireEntitySafe(E entity, EntityLivingBase caster, EntityLivingBase target) {
        if (entity != null && !((EntityEnergyProjectile)((Object)entity)).field_70128_L) {
            if (((EntityEnergyAbility)((Object)entity)).isCharging()) {
                ((EntityEnergyAbility)((Object)entity)).setCharging(false);
            }
            this.fireEntity(entity, this.getLaunchTarget(caster, target));
        }
    }

    protected String getChargeVisualId(EntityLivingBase caster, int index) {
        return "energy_charge:" + caster.func_145782_y() + ":" + this.executionStartTime + ":" + this.burstIndex + ":" + index;
    }

    protected void spawnChargeVisual(EntityLivingBase caster, EntityLivingBase target, int index, int chargeDuration) {
        String id;
        if (this.isPreview() || caster == null || caster.field_70170_p == null || caster.field_70170_p.field_72995_K) {
            return;
        }
        if (this.chargeVisualIds == null || index < 0 || index >= this.chargeVisualIds.length) {
            return;
        }
        E previewEntity = this.createProjectileEntity(caster, target, index);
        ((EntityEnergyAbility)((Object)previewEntity)).setPreviewMode(true);
        ((EntityEnergyAbility)((Object)previewEntity)).setPreviewOwner(caster);
        this.setupEntityCharging(previewEntity, this.projectiles[index], index);
        ((EntityEnergyAbility)((Object)previewEntity)).setChargeDuration(Math.max(1, chargeDuration));
        this.chargeVisualIds[index] = id = this.getChargeVisualId(caster, index);
        this.chargeVisualCaster = caster;
        EnergyChargeSpawnPacket.sendToTracking(id, previewEntity, (Entity)caster);
        EnergyChargeTracker.Instance.add(new EnergyChargeTracker.ChargeEntry(id, previewEntity.getClass().getName(), ((EntityEnergyAbility)((Object)previewEntity)).exportSpawnNBT(), caster.func_145782_y(), (int)caster.field_70170_p.func_82737_E()));
    }

    protected void removeChargeVisual(EntityLivingBase caster, int index) {
        if (this.isPreview() || caster == null || caster.field_70170_p == null || caster.field_70170_p.field_72995_K) {
            return;
        }
        if (this.chargeVisualIds == null || index < 0 || index >= this.chargeVisualIds.length) {
            return;
        }
        String id = this.chargeVisualIds[index];
        if (id != null && !id.isEmpty()) {
            EnergyChargeRemovePacket.sendToTracking(id, (Entity)caster);
            EnergyChargeTracker.Instance.remove(id, caster.func_145782_y());
            this.chargeVisualIds[index] = null;
        }
    }

    protected void removeAllChargeVisuals(EntityLivingBase caster) {
        if (this.chargeVisualIds == null || caster == null) {
            return;
        }
        for (int i = 0; i < this.chargeVisualIds.length; ++i) {
            this.removeChargeVisual(caster, i);
        }
        if (!this.isPreview() && caster.field_70170_p != null && !caster.field_70170_p.field_72995_K) {
            EnergyChargeTracker.Instance.removeAllForCaster(caster.func_145782_y());
        }
    }

    @Override
    public boolean allowOverlap() {
        return true;
    }

    @Override
    public boolean allowFreeOnCast() {
        return true;
    }

    @Override
    public void detach() {
        this.entities = null;
        this.projectileSpawned = null;
        this.spawnedCount = 0;
    }

    public void detachEntity(Entity entity) {
        if (this.entities == null || entity == null) {
            return;
        }
        for (int i = 0; i < this.entities.length; ++i) {
            if (this.entities[i] != entity) continue;
            this.entities[i] = null;
            break;
        }
    }

    @Override
    public boolean isReadyForBurstCompletion(int activeTick) {
        return this.fireDelay <= 0 || activeTick >= this.fireDelay * (this.projectileCount - 1);
    }

    @Override
    public boolean isTargetingModeLocked() {
        return true;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.AGGRO_TARGET};
    }

    @Override
    public void onWindUpTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (caster.field_70170_p.field_72995_K && !this.isPreview()) {
            return;
        }
        if (tick == 1) {
            this.initRuntimeState(caster);
            if (this.isPreview()) {
                for (int i = 0; i < this.projectileCount; ++i) {
                    E previewEntity = this.createProjectileEntity(caster, target, i);
                    EnergyDisplayData resolved = this.projectiles[i].resolveDisplay(this.displayData);
                    this.setupEntityPreview(previewEntity, caster, resolved, this.projectiles[i], i);
                    this.spawnAbilityEntity((Entity)previewEntity);
                    this.entities[i] = previewEntity;
                    this.projectileSpawned[i] = true;
                    ++this.spawnedCount;
                }
                return;
            }
            for (int i = 0; i < this.projectileCount; ++i) {
                this.spawnChargeVisual(caster, target, i, this.windUpTicks);
            }
        }
    }

    @Override
    public void resetForBurst() {
        this.removeAllChargeVisuals(this.chargeVisualCaster);
        if (!this.burstOverlap) {
            this.cleanup();
        }
        this.entities = null;
        this.projectileSpawned = null;
        this.spawnedCount = 0;
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        if (this.isPreview()) {
            int i;
            if (this.entities == null) {
                this.initRuntimeState(caster);
                for (i = 0; i < this.projectileCount; ++i) {
                    E previewEntity = this.createProjectileEntity(caster, target, i);
                    EnergyDisplayData resolved = this.projectiles[i].resolveDisplay(this.displayData);
                    this.setupEntityPreview(previewEntity, caster, resolved, this.projectiles[i], i);
                    this.spawnAbilityEntity((Entity)previewEntity);
                    this.entities[i] = previewEntity;
                    this.projectileSpawned[i] = true;
                    ++this.spawnedCount;
                }
            }
            this.fireEntitySafe(this.entities[0], caster, target);
            if (this.fireDelay <= 0) {
                for (i = 1; i < this.projectileCount; ++i) {
                    this.fireEntitySafe(this.entities[i], caster, target);
                }
            }
            return;
        }
        if (this.entities == null || this.projectileSpawned == null || this.entities.length != this.projectileCount) {
            this.initRuntimeState(caster);
        }
        this.removeAllChargeVisuals(caster);
        this.spawnAndFireProjectile(caster, target, 0);
        if (this.fireDelay <= 0) {
            for (int i = 1; i < this.projectileCount; ++i) {
                this.spawnAndFireProjectile(caster, target, i);
            }
        } else {
            for (int i = 1; i < this.projectileCount; ++i) {
                this.spawnChargeVisual(caster, target, i, this.fireDelay * i);
            }
        }
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        boolean allDead;
        if (this.entities == null || this.projectileSpawned == null) {
            this.signalCompletion();
            return;
        }
        if (this.fireDelay > 0) {
            for (int i = 1; i < this.projectileCount; ++i) {
                if (tick != this.fireDelay * i) continue;
                this.removeChargeVisual(caster, i);
                this.spawnAndFireProjectile(caster, target, i);
            }
        }
        if (this.isFreeOnCast()) {
            int lastFireTick;
            int n = lastFireTick = this.fireDelay > 0 ? this.fireDelay * (this.projectileCount - 1) : 0;
            if (tick >= lastFireTick) {
                this.signalCompletion();
                return;
            }
        }
        boolean bl = allDead = this.spawnedCount >= this.projectileCount;
        if (allDead) {
            for (E entity : this.entities) {
                if (entity == null || ((EntityEnergyProjectile)((Object)entity)).field_70128_L) continue;
                if (!this.isPreview() && tick > 5 && ((EntityEnergyProjectile)((Object)entity)).field_70170_p != null && ((EntityEnergyProjectile)((Object)entity)).field_70170_p.func_73045_a(entity.func_145782_y()) != entity) {
                    entity.func_70106_y();
                    continue;
                }
                allDead = false;
                break;
            }
        }
        if (!allDead) {
            int maxFlightTime;
            int lastFireTick = this.fireDelay > 0 ? this.fireDelay * (this.projectileCount - 1) : 0;
            int n = maxFlightTime = this.lifespanData.maxLifetime > 0 ? this.lifespanData.maxLifetime : 200;
            if (tick > lastFireTick + maxFlightTime + 20) {
                this.cleanup();
                allDead = true;
            }
        }
        if (allDead) {
            this.signalCompletion();
        }
    }

    @Override
    public void onComplete(EntityLivingBase caster, EntityLivingBase target) {
        this.removeAllChargeVisuals(caster);
    }

    @Override
    public void onInterrupt(EntityLivingBase caster, DamageSource source, float damage) {
        this.removeAllChargeVisuals(caster);
        this.cleanup();
    }

    @Override
    public void cleanup() {
        this.removeAllChargeVisuals(this.chargeVisualCaster);
        if (this.entities != null) {
            for (int i = 0; i < this.entities.length; ++i) {
                if (this.entities[i] != null && !((EntityEnergyProjectile)((Object)this.entities[i])).field_70128_L) {
                    this.entities[i].func_70106_y();
                }
                this.entities[i] = null;
            }
        }
        this.entities = null;
        this.projectileSpawned = null;
        this.spawnedCount = 0;
        this.chargeVisualIds = null;
        this.chargeVisualCaster = null;
    }

    @Override
    public TelegraphInstance createTelegraph(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.showTelegraph || this.telegraphType == TelegraphType.NONE || this.isPlayerCaster(caster) || target == null) {
            return null;
        }
        Telegraph telegraph = Telegraph.circle(this.getProjectileTelegraphRadius());
        telegraph.setDurationTicks(this.windUpTicks);
        telegraph.setColor(this.windUpColor);
        telegraph.setWarningColor(this.activeColor);
        telegraph.setWarningStartTick(Math.max(5, this.windUpTicks / 4));
        telegraph.setHeightOffset(this.telegraphHeightOffset);
        double groundY = AbilityEnergyProjectile.findGroundLevel(caster.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v);
        TelegraphInstance instance = new TelegraphInstance(telegraph, target.field_70165_t, groundY, target.field_70161_v, caster.field_70177_z);
        instance.setCasterEntityId(caster.func_145782_y());
        instance.setEntityIdToFollow(target.func_145782_y());
        return instance;
    }

    @Override
    public float getTelegraphRadius() {
        return this.getProjectileTelegraphRadius();
    }

    @Override
    public final void writeTypeNBT(NBTTagCompound nbt) {
        this.writeTypeSpecificNBT(nbt);
        nbt.func_74768_a("projectileCount", this.projectileCount);
        nbt.func_74768_a("fireDelay", this.fireDelay);
        this.writeEnergyNBT(nbt);
        this.combatData.writeNBT(nbt);
        this.homingData.writeNBT(nbt);
        this.lifespanData.writeNBT(nbt);
        for (int i = 0; i < this.projectileCount; ++i) {
            NBTTagCompound projNbt = new NBTTagCompound();
            this.projectiles[i].writeNBT(projNbt);
            nbt.func_74782_a("Projectile_" + i, (NBTBase)projNbt);
        }
    }

    @Override
    public final void readTypeNBT(NBTTagCompound nbt) {
        this.readTypeSpecificNBT(nbt);
        int count = nbt.func_74762_e("projectileCount");
        this.initProjectiles(count);
        this.fireDelay = nbt.func_74762_e("fireDelay");
        this.readEnergyNBT(nbt);
        this.combatData.readNBT(nbt);
        this.homingData.readNBT(nbt);
        this.lifespanData.readNBT(nbt);
        for (int i = 0; i < this.projectileCount; ++i) {
            if (!nbt.func_74764_b("Projectile_" + i)) continue;
            this.projectiles[i].readNBT(nbt.func_74775_l("Projectile_" + i));
        }
    }

    @Override
    public int getMaxPreviewDuration() {
        return this.lifespanData.maxLifetime > 0 ? Math.min(this.lifespanData.maxLifetime, 100) : 100;
    }

    @Override
    public float getDamage() {
        return this.combatData.damage;
    }

    @Override
    public void setDamage(float damage) {
        this.combatData.damage = damage;
    }

    @Override
    public float getDisplayDamage() {
        return this.combatData.damage;
    }

    @Override
    public float getKnockback() {
        return this.combatData.knockback;
    }

    @Override
    public void setKnockback(float knockback) {
        this.combatData.knockback = knockback;
    }

    @Override
    public float getKnockbackUp() {
        return this.combatData.knockbackUp;
    }

    @Override
    public void setKnockbackUp(float knockbackUp) {
        this.combatData.knockbackUp = knockbackUp;
    }

    @Override
    public boolean isExplosive() {
        return this.combatData.explosive;
    }

    @Override
    public void setExplosive(boolean explosive) {
        this.combatData.explosive = explosive;
    }

    @Override
    public float getExplosionRadius() {
        return this.combatData.getExplosionRadius();
    }

    @Override
    public void setExplosionRadius(float explosionRadius) {
        this.combatData.setExplosionRadius(explosionRadius);
    }

    @Override
    public float getExplosionDamageFalloff() {
        return this.combatData.explosionDamageFalloff;
    }

    @Override
    public void setExplosionDamageFalloff(float falloff) {
        this.combatData.explosionDamageFalloff = falloff;
    }

    @Override
    public int getHitType() {
        return this.combatData.hitType.ordinal();
    }

    @Override
    public void setHitType(int hitType) {
        this.combatData.hitType = HitType.fromOrdinal(hitType);
    }

    @Override
    public int getMultiHitDelayTicks() {
        return this.combatData.multiHitDelayTicks;
    }

    @Override
    public void setMultiHitDelayTicks(int delay) {
        this.combatData.multiHitDelayTicks = Math.max(1, delay);
    }

    @Override
    public int getMaxHits() {
        return this.combatData.getMaxHits();
    }

    @Override
    public void setMaxHits(int maxHits) {
        this.combatData.setMaxHits(maxHits);
    }

    @Override
    public float getMaxDistance() {
        return this.lifespanData.maxDistance;
    }

    @Override
    public void setMaxDistance(float maxDistance) {
        this.lifespanData.maxDistance = maxDistance;
    }

    @Override
    public int getMaxLifetime() {
        return this.lifespanData.maxLifetime;
    }

    @Override
    public void setMaxLifetime(int maxLifetime) {
        this.lifespanData.maxLifetime = maxLifetime;
    }

    public boolean isHoming() {
        return this.homingData.homing;
    }

    public void setHoming(boolean homing) {
        this.homingData.homing = homing;
    }

    public float getHomingStrength() {
        return this.homingData.homingStrength;
    }

    public void setHomingStrength(float strength) {
        this.homingData.homingStrength = strength;
    }

    public float getHomingRange() {
        return this.homingData.homingRange;
    }

    public void setHomingRange(float range) {
        this.homingData.homingRange = range;
    }

    public AnchorPoint getAnchorPointEnum() {
        return this.projectiles[0].anchor.anchorPoint;
    }

    public void setAnchorPointEnum(AnchorPoint point) {
        this.projectiles[0].anchor.anchorPoint = point;
    }

    public float getAnchorOffsetX() {
        return this.projectiles[0].anchor.anchorOffsetX;
    }

    public void setAnchorOffsetX(float x) {
        this.projectiles[0].anchor.anchorOffsetX = x;
    }

    public float getAnchorOffsetY() {
        return this.projectiles[0].anchor.anchorOffsetY;
    }

    public void setAnchorOffsetY(float y) {
        this.projectiles[0].anchor.anchorOffsetY = y;
    }

    public float getAnchorOffsetZ() {
        return this.projectiles[0].anchor.anchorOffsetZ;
    }

    public void setAnchorOffsetZ(float z) {
        this.projectiles[0].anchor.anchorOffsetZ = z;
    }

    @Override
    public boolean getLaunchFromAnchor() {
        return this.projectiles[0].anchor.launchFromAnchor;
    }

    @Override
    public void setLaunchFromAnchor(boolean launchFromAnchor) {
        this.projectiles[0].anchor.launchFromAnchor = launchFromAnchor;
    }

    @Override
    public int getAnchorPoint() {
        return this.projectiles[0].anchor.anchorPoint.ordinal();
    }

    @Override
    public void setAnchorPoint(int point) {
        this.projectiles[0].anchor.anchorPoint = AnchorPoint.fromOrdinal(point);
    }

    public int getInnerColor(int index) {
        ProjectileData p = this.projectiles[this.clampIndex(index)];
        return p.colorOverride ? p.innerColor : this.displayData.innerColor;
    }

    public void setInnerColor(int index, int color) {
        ProjectileData p = this.projectiles[this.clampIndex(index)];
        p.colorOverride = true;
        p.innerColor = color;
    }

    public int getOuterColor(int index) {
        ProjectileData p = this.projectiles[this.clampIndex(index)];
        return p.colorOverride ? p.outerColor : this.displayData.outerColor;
    }

    public void setOuterColor(int index, int color) {
        ProjectileData p = this.projectiles[this.clampIndex(index)];
        p.colorOverride = true;
        p.outerColor = color;
    }

    public int getAnchorPoint(int index) {
        return this.projectiles[this.clampIndex((int)index)].anchor.anchorPoint.ordinal();
    }

    public void setAnchorPoint(int index, int point) {
        this.projectiles[this.clampIndex((int)index)].anchor.anchorPoint = AnchorPoint.fromOrdinal(point);
    }

    public void setAnchorOffsetX(int index, float offset) {
        this.projectiles[this.clampIndex((int)index)].anchor.anchorOffsetX = offset;
    }

    public void setAnchorOffsetY(int index, float offset) {
        this.projectiles[this.clampIndex((int)index)].anchor.anchorOffsetY = offset;
    }

    public void setAnchorOffsetZ(int index, float offset) {
        this.projectiles[this.clampIndex((int)index)].anchor.anchorOffsetZ = offset;
    }

    public boolean getLaunchFromAnchor(int index) {
        return this.projectiles[this.clampIndex((int)index)].anchor.launchFromAnchor;
    }

    public void setLaunchFromAnchor(int index, boolean launchFromAnchor) {
        this.projectiles[this.clampIndex((int)index)].anchor.launchFromAnchor = launchFromAnchor;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public final void getAbilityDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.enumField("ability.hitType", HitType.class, () -> HitType.fromOrdinal(this.getHitType()), v -> this.setHitType(v.ordinal())).hover("ability.hover.hitType"));
        defs.add(FieldDef.intField("ability.multiHitDelay", this::getMultiHitDelayTicks, this::setMultiHitDelayTicks).range(1.0f, 200.0f).visibleWhen(() -> this.getHitType() == HitType.MULTI.ordinal()).hover("ability.hover.multiHitDelay"));
        defs.add(FieldDef.intField("ability.maxHits", this::getMaxHits, this::setMaxHits).range(1.0f, 200.0f).visibleWhen(() -> this.getHitType() != HitType.SINGLE.ordinal()).hover("ability.hover.maxHits"));
        this.addTypeDefinitions(defs);
        this.addEnergyColorDefinitions(defs);
        defs.add(FieldDef.section("ability.section.effects").tab("ability.tab.visual"));
        defs.add(FieldDef.floatField("ability.rotationSpeed", this::getRotationSpeed, this::setRotationSpeed).tab("ability.tab.visual"));
        this.addEnergyLightningDefinitions(defs);
        for (int i = 0; i < 8; ++i) {
            int idx = i;
            String sectionKey = "ability.section.projectile" + (i + 1);
            defs.add(FieldDef.section(sectionKey).tab("ability.tab.visual").visibleWhen(() -> idx < this.projectileCount));
            defs.add(FieldDef.enumField("ability.anchorPoint", AnchorPoint.class, () -> this.projectiles[idx].anchor.anchorPoint, v -> {
                this.projectiles[idx].anchor.anchorPoint = v;
            }).tab("ability.tab.visual").visibleWhen(() -> idx < this.projectileCount));
            defs.add(FieldDef.row(FieldDef.floatField("ability.anchor.offsetX", () -> Float.valueOf(this.projectiles[idx].anchor.anchorOffsetX), v -> {
                this.projectiles[idx].anchor.anchorOffsetX = v.floatValue();
            }).min(Float.NEGATIVE_INFINITY), FieldDef.floatField("ability.anchor.offsetY", () -> Float.valueOf(this.projectiles[idx].anchor.anchorOffsetY), v -> {
                this.projectiles[idx].anchor.anchorOffsetY = v.floatValue();
            }).min(Float.NEGATIVE_INFINITY)).tab("ability.tab.visual").visibleWhen(() -> idx < this.projectileCount));
            defs.add(FieldDef.floatField("ability.anchor.offsetZ", () -> Float.valueOf(this.projectiles[idx].anchor.anchorOffsetZ), v -> {
                this.projectiles[idx].anchor.anchorOffsetZ = v.floatValue();
            }).tab("ability.tab.visual").visibleWhen(() -> idx < this.projectileCount).min(Float.NEGATIVE_INFINITY));
            defs.add(FieldDef.boolField("ability.launchFromAnchor", () -> this.projectiles[idx].anchor.launchFromAnchor, v -> {
                this.projectiles[idx].anchor.launchFromAnchor = v;
            }).tab("ability.tab.visual").visibleWhen(() -> idx < this.projectileCount).hover("ability.hover.launchFromAnchor"));
            defs.add(FieldDef.boolField("ability.colorOverride", () -> this.projectiles[idx].colorOverride, v -> {
                this.projectiles[idx].colorOverride = v;
            }).tab("ability.tab.visual").visibleWhen(() -> idx < this.projectileCount && this.projectileCount > 1));
            defs.add(FieldDef.colorSubGui("ability.innerColor", () -> this.projectiles[idx].innerColor, v -> {
                this.projectiles[idx].innerColor = v;
            }).tab("ability.tab.visual").visibleWhen(() -> idx < this.projectileCount && this.projectileCount > 1 && this.projectiles[idx].colorOverride));
            defs.add(FieldDef.colorSubGui("ability.outerColor", () -> this.projectiles[idx].outerColor, v -> {
                this.projectiles[idx].outerColor = v;
            }).tab("ability.tab.visual").visibleWhen(() -> idx < this.projectileCount && this.projectileCount > 1 && this.projectiles[idx].colorOverride));
        }
    }
}

