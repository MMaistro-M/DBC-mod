/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.EnergyController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityPotionEffect;
import kamkeel.npcs.controllers.data.ability.enums.TargetFilter;
import kamkeel.npcs.controllers.data.ability.util.AbilityTargetHelper;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import noppes.npcs.LogWriter;
import noppes.npcs.NpcDamageSource;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAbilityZone
extends Entity
implements IEntityAdditionalSpawnData {
    private static final double GROUND_OFFSET = 0.005;
    private static final byte TRIGGER_FLASH_STATUS = 60;
    private ZoneType zoneType = ZoneType.TRAP;
    private ZoneShape shape = ZoneShape.CIRCLE;
    private int ownerEntityId = -1;
    private float radius = 4.0f;
    private int durationTicks = 200;
    private int maxTicks = 200;
    private int innerColor = 0xFF6600;
    private int outerColor = 0xFF0000;
    private boolean outerColorEnabled = true;
    private float zoneHeight = 2.0f;
    private float particleDensity = 1.0f;
    private float particleScale = 1.0f;
    private float animSpeed = 1.0f;
    private float lightningDensity = 1.0f;
    private boolean groundFill = true;
    private float groundAlpha = 0.25f;
    private boolean rings = true;
    private int ringCount = 3;
    private boolean border = true;
    private float borderSpeed = 1.0f;
    private boolean accents = true;
    private int accentStyle = 0;
    private boolean lightning = false;
    private boolean particles = true;
    private int particleMotion = 0;
    private String particleDir = "";
    private int particleSize = 32;
    private boolean particleGlow = true;
    private long deathWorldTime = -1L;
    private List<AbilityPotionEffect> effects = new ArrayList<AbilityPotionEffect>();
    private int armTime = 20;
    private float triggerRadius = 2.0f;
    private int maxTriggers = 1;
    private int triggerCooldown = 20;
    private float damage = 6.0f;
    private float damageRadius = 0.0f;
    private float knockback = 0.5f;
    private boolean visible = true;
    private float damagePerSecond = 1.0f;
    private int damageInterval = 20;
    private boolean ignoreIFrames = false;
    private boolean affectsCaster = false;
    private transient boolean armed = false;
    private transient int triggerCount = 0;
    private transient int ticksSinceLastTrigger = 0;
    private transient Set<Integer> triggeredEntities = new HashSet<Integer>();
    private transient int triggerFlashTick = -1;
    private transient int ticksSinceDamage = 0;
    private transient Set<Integer> damagedThisTick = new HashSet<Integer>();
    private transient Ability sourceAbility = null;
    private NBTTagCompound customDamageData = null;
    private boolean previewMode = false;
    private EntityLivingBase previewOwner = null;

    public EntityAbilityZone(World world) {
        super(world);
        this.func_70105_a(0.1f, 0.1f);
        this.field_70145_X = true;
        this.field_70178_ae = true;
        this.field_70158_ak = true;
    }

    private EntityAbilityZone(World world, ZoneType type, EntityLivingBase owner, double x, double y, double z) {
        this(world);
        this.zoneType = type;
        this.ownerEntityId = owner != null ? owner.func_145782_y() : -1;
        double groundY = Ability.findGroundLevel(world, x, y, z) + 0.005;
        this.func_70107_b(x, groundY, z);
    }

    public void initAsHazard(Entity owner, double x, double y, double z) {
        this.zoneType = ZoneType.HAZARD;
        this.ownerEntityId = owner != null ? owner.func_145782_y() : -1;
        double groundY = Ability.findGroundLevel(this.field_70170_p, x, y, z) + 0.005;
        this.func_70107_b(x, groundY, z);
        this.ticksSinceDamage = this.damageInterval;
    }

    public void initAsTrap(Entity owner, double x, double y, double z) {
        this.zoneType = ZoneType.TRAP;
        this.ownerEntityId = owner != null ? owner.func_145782_y() : -1;
        double groundY = Ability.findGroundLevel(this.field_70170_p, x, y, z) + 0.005;
        this.func_70107_b(x, groundY, z);
        this.ticksSinceLastTrigger = this.triggerCooldown;
    }

    public static EntityAbilityZone createTrap(World world, EntityLivingBase owner, double x, double y, double z, ZoneShape shape, float triggerRadius, int armTime, int maxTriggers, int triggerCooldown, float damage, float damageRadius, float knockback, int durationTicks, boolean ignoreIFrames, int innerColor, int outerColor, boolean outerColorEnabled, float zoneHeight, float particleDensity, float particleScale, float animSpeed, float lightningDensity, boolean visible, List<AbilityPotionEffect> effects) {
        EntityAbilityZone zone = new EntityAbilityZone(world, ZoneType.TRAP, owner, x, y, z);
        zone.shape = shape;
        zone.radius = triggerRadius;
        zone.triggerRadius = triggerRadius;
        zone.armTime = armTime;
        zone.maxTriggers = maxTriggers;
        zone.triggerCooldown = triggerCooldown;
        zone.damage = damage;
        zone.damageRadius = damageRadius;
        zone.knockback = knockback;
        zone.durationTicks = durationTicks;
        zone.maxTicks = durationTicks;
        zone.ignoreIFrames = ignoreIFrames;
        zone.innerColor = innerColor;
        zone.outerColor = outerColor;
        zone.outerColorEnabled = outerColorEnabled;
        zone.zoneHeight = zoneHeight;
        zone.particleDensity = particleDensity;
        zone.particleScale = particleScale;
        zone.animSpeed = animSpeed;
        zone.lightningDensity = lightningDensity;
        zone.visible = visible;
        if (effects != null) {
            for (AbilityPotionEffect e : effects) {
                zone.effects.add(e.copy());
            }
        }
        zone.ticksSinceLastTrigger = triggerCooldown;
        return zone;
    }

    public static EntityAbilityZone createHazard(World world, EntityLivingBase owner, double x, double y, double z, ZoneShape shape, float radius, float damagePerSecond, int damageInterval, boolean ignoreIFrames, boolean affectsCaster, int durationTicks, int innerColor, int outerColor, boolean outerColorEnabled, float zoneHeight, float particleDensity, float particleScale, float animSpeed, float lightningDensity, List<AbilityPotionEffect> effects) {
        EntityAbilityZone zone = new EntityAbilityZone(world, ZoneType.HAZARD, owner, x, y, z);
        zone.shape = shape;
        zone.radius = radius;
        zone.damagePerSecond = damagePerSecond;
        zone.damageInterval = damageInterval;
        zone.ignoreIFrames = ignoreIFrames;
        zone.affectsCaster = affectsCaster;
        zone.durationTicks = durationTicks;
        zone.maxTicks = durationTicks;
        zone.innerColor = innerColor;
        zone.outerColor = outerColor;
        zone.outerColorEnabled = outerColorEnabled;
        zone.zoneHeight = zoneHeight;
        zone.particleDensity = particleDensity;
        zone.particleScale = particleScale;
        zone.animSpeed = animSpeed;
        zone.lightningDensity = lightningDensity;
        if (effects != null) {
            for (AbilityPotionEffect e : effects) {
                zone.effects.add(e.copy());
            }
        }
        zone.ticksSinceDamage = damageInterval;
        return zone;
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound nbt) {
        this.func_70106_y();
    }

    protected void func_70014_b(NBTTagCompound nbt) {
    }

    public void func_70103_a(byte id) {
        if (id == 60) {
            this.triggerFlashTick = this.field_70173_aa;
        } else {
            super.func_70103_a(id);
        }
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        super.func_70071_h_();
        if (this.deathWorldTime < 0L && this.field_70170_p != null) {
            this.deathWorldTime = this.field_70170_p.func_82737_E() + (long)this.maxTicks;
        }
        if (this.deathWorldTime > 0L && this.field_70170_p.func_82737_E() >= this.deathWorldTime) {
            this.func_70106_y();
            return;
        }
        if (!this.field_70170_p.field_72995_K && !this.previewMode) {
            switch (this.zoneType) {
                case TRAP: {
                    this.tickTrap();
                    break;
                }
                case HAZARD: {
                    this.tickHazard();
                }
            }
        }
    }

    private void tickTrap() {
        if (!this.armed) {
            if (this.field_70173_aa >= this.armTime) {
                this.armed = true;
            }
            return;
        }
        ++this.ticksSinceLastTrigger;
        if (this.maxTriggers > 0 && this.triggerCount >= this.maxTriggers) {
            this.func_70106_y();
            return;
        }
        if (this.ticksSinceLastTrigger < this.triggerCooldown) {
            return;
        }
        AxisAlignedBB box = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)this.triggerRadius), (double)(this.field_70163_u - 0.5), (double)(this.field_70161_v - (double)this.triggerRadius), (double)(this.field_70165_t + (double)this.triggerRadius), (double)(this.field_70163_u + (double)this.zoneHeight), (double)(this.field_70161_v + (double)this.triggerRadius));
        Entity owner = this.getOwner();
        List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, box);
        for (EntityLivingBase entity : entities) {
            boolean inRange;
            if (owner != null && entity == owner || entity.field_70128_L || this.maxTriggers == 1 && this.triggeredEntities.contains(entity.func_145782_y()) || owner instanceof EntityLivingBase && !AbilityTargetHelper.shouldAffect((EntityLivingBase)owner, (Entity)entity, TargetFilter.ENEMIES, false)) continue;
            double dx = entity.field_70165_t - this.field_70165_t;
            double dz = entity.field_70161_v - this.field_70161_v;
            if (this.shape == ZoneShape.SQUARE) {
                inRange = Math.abs(dx) <= (double)this.triggerRadius && Math.abs(dz) <= (double)this.triggerRadius;
            } else {
                double dist = Math.sqrt(dx * dx + dz * dz);
                boolean bl = inRange = dist <= (double)this.triggerRadius;
            }
            if (!inRange) continue;
            this.triggerTrap(entity);
            return;
        }
    }

    private void triggerTrap(EntityLivingBase triggerer) {
        double dz;
        double dx;
        Entity owner = this.getOwner();
        HashSet<EntityLivingBase> affected = new HashSet<EntityLivingBase>();
        if (this.damageRadius > 0.0f) {
            AxisAlignedBB box = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)this.damageRadius), (double)(this.field_70163_u - 0.5), (double)(this.field_70161_v - (double)this.damageRadius), (double)(this.field_70165_t + (double)this.damageRadius), (double)(this.field_70163_u + (double)this.zoneHeight), (double)(this.field_70161_v + (double)this.damageRadius));
            List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, box);
            for (EntityLivingBase entity : entities) {
                if (owner != null && entity == owner || owner instanceof EntityLivingBase && !AbilityTargetHelper.shouldAffect((EntityLivingBase)owner, (Entity)entity, TargetFilter.ENEMIES, false)) continue;
                dx = entity.field_70165_t - this.field_70165_t;
                dz = entity.field_70161_v - this.field_70161_v;
                if (this.shape == ZoneShape.SQUARE) {
                    if (!(Math.abs(dx) <= (double)this.damageRadius) || !(Math.abs(dz) <= (double)this.damageRadius)) continue;
                    affected.add(entity);
                    continue;
                }
                double dist = Math.sqrt(dx * dx + dz * dz);
                if (!(dist <= (double)this.damageRadius)) continue;
                affected.add(entity);
            }
        } else {
            affected.add(triggerer);
        }
        boolean anyDamaged = false;
        for (EntityLivingBase entity : affected) {
            double len;
            boolean hit = this.applyDamage(entity, owner, this.damage);
            if (hit) {
                anyDamaged = true;
            }
            if (hit && this.knockback > 0.0f && (len = Math.sqrt((dx = entity.field_70165_t - this.field_70165_t) * dx + (dz = entity.field_70161_v - this.field_70161_v) * dz)) > 0.0) {
                entity.func_70024_g(dx / len * (double)this.knockback * 0.5, 0.1, dz / len * (double)this.knockback * 0.5);
                entity.field_70133_I = true;
            }
            if (!hit) continue;
            this.applyEffects(entity);
        }
        if (anyDamaged) {
            ++this.triggerCount;
            this.ticksSinceLastTrigger = 0;
            this.triggeredEntities.add(triggerer.func_145782_y());
            this.triggerFlashTick = this.field_70173_aa;
            this.field_70170_p.func_72960_a((Entity)this, (byte)60);
        }
    }

    private void tickHazard() {
        this.damagedThisTick.clear();
        ++this.ticksSinceDamage;
        if (this.ticksSinceDamage < this.damageInterval) {
            return;
        }
        this.ticksSinceDamage = 0;
        AxisAlignedBB searchBox = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)this.radius), (double)(this.field_70163_u - 0.5), (double)(this.field_70161_v - (double)this.radius), (double)(this.field_70165_t + (double)this.radius), (double)(this.field_70163_u + (double)this.zoneHeight), (double)(this.field_70161_v + (double)this.radius));
        Entity owner = this.getOwner();
        List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, searchBox);
        for (EntityLivingBase entity : entities) {
            if (owner != null && entity == owner && !this.affectsCaster || this.damagedThisTick.contains(entity.func_145782_y()) || entity != owner && owner instanceof EntityLivingBase && !AbilityTargetHelper.shouldAffect((EntityLivingBase)owner, (Entity)entity, TargetFilter.ENEMIES, false) || !this.isInZone(entity)) continue;
            if (this.damagePerSecond > 0.0f) {
                this.applyDamage(entity, owner, this.damagePerSecond);
            }
            this.applyEffects(entity);
            this.damagedThisTick.add(entity.func_145782_y());
        }
    }

    private boolean isInZone(EntityLivingBase entity) {
        double dx = entity.field_70165_t - this.field_70165_t;
        double dz = entity.field_70161_v - this.field_70161_v;
        switch (this.shape) {
            case CIRCLE: {
                return Math.sqrt(dx * dx + dz * dz) <= (double)this.radius;
            }
            case SQUARE: {
                return Math.abs(dx) <= (double)this.radius && Math.abs(dz) <= (double)this.radius;
            }
        }
        return Math.sqrt(dx * dx + dz * dz) <= (double)this.radius;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean applyDamage(EntityLivingBase target, Entity owner, float dmg) {
        if (dmg <= 0.0f) {
            return false;
        }
        int previousHurtResistantTime = Ability.clearHurtResistanceIfNeeded(target, this.ignoreIFrames);
        try {
            double dz;
            double dx;
            boolean handled = false;
            if (this.sourceAbility != null && owner instanceof EntityLivingBase) {
                dx = target.field_70165_t - this.field_70165_t;
                dz = target.field_70161_v - this.field_70161_v;
                handled = AbilityController.Instance.fireOnAbilityDamage(this.sourceAbility, (EntityLivingBase)owner, target, dmg, 0.0f, 0.0f, dx, dz, 1.0f);
            }
            if (!handled && this.customDamageData != null && owner instanceof EntityLivingBase) {
                dx = target.field_70165_t - this.field_70165_t;
                dz = target.field_70161_v - this.field_70161_v;
                handled = EnergyController.Instance.fireOnEnergyDamage(this, (EntityLivingBase)owner, target, dmg, 0.0f, 0.0f, dx, dz, 1.0f, this.customDamageData);
            }
            if (!handled) {
                if (owner instanceof EntityNPCInterface) {
                    boolean bl = target.func_70097_a((DamageSource)new NpcDamageSource("npc_ability", (Entity)((EntityNPCInterface)owner)), dmg);
                    return bl;
                }
                if (owner instanceof EntityPlayer) {
                    boolean bl = target.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)owner)), dmg);
                    return bl;
                }
                if (owner instanceof EntityLivingBase) {
                    boolean bl = target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)((EntityLivingBase)owner)), dmg);
                    return bl;
                }
                boolean bl = target.func_70097_a((DamageSource)new NpcDamageSource("npc_ability", null), dmg);
                return bl;
            }
            boolean bl = true;
            return bl;
        }
        finally {
            Ability.restoreHurtResistanceIfNeeded(target, this.ignoreIFrames, previousHurtResistantTime);
        }
    }

    private void applyEffects(EntityLivingBase entity) {
        if (entity == null || this.effects.isEmpty()) {
            return;
        }
        for (AbilityPotionEffect effect : this.effects) {
            effect.apply(entity);
        }
    }

    private Entity getOwner() {
        if (this.previewMode && this.previewOwner != null) {
            return this.previewOwner;
        }
        if (this.ownerEntityId == -1) {
            return null;
        }
        return this.field_70170_p.func_73045_a(this.ownerEntityId);
    }

    public void setupPreview(EntityLivingBase owner) {
        this.previewMode = true;
        this.previewOwner = owner;
    }

    public Ability getSourceAbility() {
        return this.sourceAbility;
    }

    public void setSourceAbility(Ability ability) {
        this.sourceAbility = ability;
    }

    public NBTTagCompound getCustomDamageData() {
        return this.customDamageData;
    }

    public void setCustomDamageData(NBTTagCompound data) {
        this.customDamageData = data;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        return distance < 16384.0;
    }

    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    public float func_70013_c(float partialTicks) {
        return 1.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_70070_b(float partialTicks) {
        return 0xF000F0;
    }

    public ZoneType getZoneType() {
        return this.zoneType;
    }

    public ZoneShape getShape() {
        return this.shape;
    }

    public float getRadius() {
        return this.radius;
    }

    public int getInnerColor() {
        return this.innerColor;
    }

    public int getOuterColor() {
        return this.outerColor;
    }

    public boolean isOuterColorEnabled() {
        return this.outerColorEnabled;
    }

    public float getZoneHeight() {
        return this.zoneHeight;
    }

    public boolean isArmed() {
        return this.armed || this.field_70173_aa >= this.armTime;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public int getTriggerFlashTick() {
        return this.triggerFlashTick;
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public int getMaxTicks() {
        return this.maxTicks;
    }

    public float getParticleDensity() {
        return this.particleDensity;
    }

    public float getParticleScale() {
        return this.particleScale;
    }

    public float getAnimSpeed() {
        return this.animSpeed;
    }

    public float getLightningDensity() {
        return this.lightningDensity;
    }

    public boolean isGroundFill() {
        return this.groundFill;
    }

    public float getGroundAlpha() {
        return this.groundAlpha;
    }

    public boolean isRings() {
        return this.rings;
    }

    public int getRingCount() {
        return this.ringCount;
    }

    public boolean isBorder() {
        return this.border;
    }

    public float getBorderSpeed() {
        return this.borderSpeed;
    }

    public boolean isAccents() {
        return this.accents;
    }

    public int getAccentStyle() {
        return this.accentStyle;
    }

    public boolean isLightning() {
        return this.lightning;
    }

    public boolean isParticles() {
        return this.particles;
    }

    public int getParticleMotion() {
        return this.particleMotion;
    }

    public String getParticleDir() {
        return this.particleDir;
    }

    public int getParticleSize() {
        return this.particleSize;
    }

    public boolean isParticleGlow() {
        return this.particleGlow;
    }

    public void applyVisual(boolean groundFill, float groundAlpha, boolean rings, int ringCount, boolean border, float borderSpeed, boolean accents, int accentStyle, boolean lightning, boolean particles, int particleMotion, String particleDir, int particleSize, boolean particleGlow) {
        this.groundFill = groundFill;
        this.groundAlpha = groundAlpha;
        this.rings = rings;
        this.ringCount = ringCount;
        this.border = border;
        this.borderSpeed = borderSpeed;
        this.accents = accents;
        this.accentStyle = accentStyle;
        this.lightning = lightning;
        this.particles = particles;
        this.particleMotion = particleMotion;
        this.particleDir = particleDir != null ? particleDir : "";
        this.particleSize = particleSize;
        this.particleGlow = particleGlow;
    }

    public void setShape(ZoneShape shape) {
        this.shape = shape;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setZoneHeight(float height) {
        this.zoneHeight = height;
    }

    public void setDurationTicks(int ticks) {
        this.durationTicks = ticks;
        this.maxTicks = ticks;
    }

    public void setInnerColor(int color) {
        this.innerColor = color;
    }

    public void setOuterColor(int color) {
        this.outerColor = color;
    }

    public void setOuterColorEnabled(boolean enabled) {
        this.outerColorEnabled = enabled;
    }

    public void setParticleDensity(float density) {
        this.particleDensity = density;
    }

    public void setParticleScale(float scale) {
        this.particleScale = scale;
    }

    public void setAnimSpeed(float speed) {
        this.animSpeed = speed;
    }

    public void setIgnoreIFrames(boolean ignore) {
        this.ignoreIFrames = ignore;
    }

    public float getDamagePerSecond() {
        return this.damagePerSecond;
    }

    public void setDamagePerSecond(float dps) {
        this.damagePerSecond = dps;
    }

    public int getDamageInterval() {
        return this.damageInterval;
    }

    public void setDamageInterval(int ticks) {
        this.damageInterval = ticks;
    }

    public boolean isAffectsCaster() {
        return this.affectsCaster;
    }

    public void setAffectsCaster(boolean affects) {
        this.affectsCaster = affects;
    }

    public float getTriggerRadius() {
        return this.triggerRadius;
    }

    public void setTriggerRadius(float radius) {
        this.triggerRadius = radius;
    }

    public int getArmTime() {
        return this.armTime;
    }

    public void setArmTime(int ticks) {
        this.armTime = ticks;
    }

    public int getMaxTriggers() {
        return this.maxTriggers;
    }

    public void setMaxTriggers(int max) {
        this.maxTriggers = max;
    }

    public int getTriggerCooldown() {
        return this.triggerCooldown;
    }

    public void setTriggerCooldown(int ticks) {
        this.triggerCooldown = ticks;
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamageRadius() {
        return this.damageRadius;
    }

    public void setDamageRadius(float radius) {
        this.damageRadius = radius;
    }

    public float getKnockback() {
        return this.knockback;
    }

    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isIgnoreIFrames() {
        return this.ignoreIFrames;
    }

    public int getOwnerEntityId() {
        return this.ownerEntityId;
    }

    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(this.zoneType.ordinal());
        ByteBufUtils.writeString(buffer, this.shape.name());
        buffer.writeInt(this.ownerEntityId);
        buffer.writeFloat(this.radius);
        buffer.writeInt(this.durationTicks);
        buffer.writeInt(this.maxTicks);
        buffer.writeInt(this.innerColor);
        buffer.writeInt(this.outerColor);
        buffer.writeBoolean(this.outerColorEnabled);
        buffer.writeFloat(this.zoneHeight);
        buffer.writeFloat(this.particleDensity);
        buffer.writeFloat(this.particleScale);
        buffer.writeFloat(this.animSpeed);
        buffer.writeFloat(this.lightningDensity);
        buffer.writeBoolean(this.groundFill);
        buffer.writeFloat(this.groundAlpha);
        buffer.writeBoolean(this.rings);
        buffer.writeInt(this.ringCount);
        buffer.writeBoolean(this.border);
        buffer.writeFloat(this.borderSpeed);
        buffer.writeBoolean(this.accents);
        buffer.writeInt(this.accentStyle);
        buffer.writeBoolean(this.lightning);
        buffer.writeBoolean(this.particles);
        buffer.writeInt(this.particleMotion);
        ByteBufUtils.writeString(buffer, this.particleDir);
        buffer.writeInt(this.particleSize);
        buffer.writeBoolean(this.particleGlow);
        buffer.writeInt(this.armTime);
        buffer.writeBoolean(this.visible);
        NBTTagCompound effectsNbt = new NBTTagCompound();
        NBTTagList effectList = new NBTTagList();
        for (AbilityPotionEffect effect : this.effects) {
            effectList.func_74742_a((NBTBase)effect.writeNBT());
        }
        effectsNbt.func_74782_a("Effects", (NBTBase)effectList);
        try {
            ByteBufUtils.writeNBT(buffer, effectsNbt);
        }
        catch (IOException e) {
            LogWriter.error("Error writing zone effects spawn data", e);
        }
        boolean hasDamageData = this.customDamageData != null;
        buffer.writeBoolean(hasDamageData);
        if (hasDamageData) {
            try {
                ByteBufUtils.writeNBT(buffer, this.customDamageData);
            }
            catch (IOException e) {
                LogWriter.error("Error writing zone custom damage data", e);
            }
        }
    }

    public void readSpawnData(ByteBuf buffer) {
        int zoneTypeOrd = buffer.readInt();
        this.zoneType = zoneTypeOrd >= 0 && zoneTypeOrd < ZoneType.values().length ? ZoneType.values()[zoneTypeOrd] : ZoneType.TRAP;
        String shapeName = ByteBufUtils.readString(buffer);
        try {
            this.shape = ZoneShape.valueOf(shapeName);
        }
        catch (Exception e) {
            this.shape = ZoneShape.CIRCLE;
        }
        this.ownerEntityId = buffer.readInt();
        this.radius = Math.max(0.1f, Math.min(100.0f, buffer.readFloat()));
        if (Float.isNaN(this.radius) || Float.isInfinite(this.radius)) {
            this.radius = 5.0f;
        }
        this.durationTicks = buffer.readInt();
        this.maxTicks = buffer.readInt();
        this.innerColor = buffer.readInt();
        this.outerColor = buffer.readInt();
        this.outerColorEnabled = buffer.readBoolean();
        this.zoneHeight = buffer.readFloat();
        this.particleDensity = buffer.readFloat();
        this.particleScale = buffer.readFloat();
        this.animSpeed = buffer.readFloat();
        this.lightningDensity = buffer.readFloat();
        this.groundFill = buffer.readBoolean();
        this.groundAlpha = buffer.readFloat();
        this.rings = buffer.readBoolean();
        this.ringCount = buffer.readInt();
        this.border = buffer.readBoolean();
        this.borderSpeed = buffer.readFloat();
        this.accents = buffer.readBoolean();
        this.accentStyle = buffer.readInt();
        this.lightning = buffer.readBoolean();
        this.particles = buffer.readBoolean();
        this.particleMotion = buffer.readInt();
        this.particleDir = ByteBufUtils.readString(buffer);
        this.particleSize = buffer.readInt();
        this.particleGlow = buffer.readBoolean();
        this.armTime = buffer.readInt();
        this.visible = buffer.readBoolean();
        NBTTagCompound effectsNbt = null;
        try {
            effectsNbt = ByteBufUtils.readNBT(buffer);
        }
        catch (IOException e) {
            LogWriter.error("Error reading zone effects spawn data", e);
        }
        this.effects.clear();
        if (effectsNbt != null && effectsNbt.func_74764_b("Effects")) {
            NBTTagList effectList = effectsNbt.func_150295_c("Effects", 10);
            for (int i = 0; i < effectList.func_74745_c(); ++i) {
                AbilityPotionEffect effect = AbilityPotionEffect.fromNBT(effectList.func_150305_b(i));
                if (effect == null || !effect.isValid()) continue;
                this.effects.add(effect);
            }
        }
        if (buffer.readableBytes() > 0 && buffer.readBoolean()) {
            try {
                this.customDamageData = ByteBufUtils.readNBT(buffer);
            }
            catch (IOException e) {
                LogWriter.error("Error reading zone custom damage data", e);
            }
        }
    }

    public static enum ParticleMotion {
        RISING,
        DRIFTING,
        SPARKS;


        public String toString() {
            switch (this) {
                case RISING: {
                    return "ability.particleMotion.rising";
                }
                case DRIFTING: {
                    return "ability.particleMotion.drifting";
                }
                case SPARKS: {
                    return "ability.particleMotion.sparks";
                }
            }
            return this.name();
        }
    }

    public static enum AccentStyle {
        STATIC,
        SWAYING,
        FLICKERING;


        public String toString() {
            switch (this) {
                case STATIC: {
                    return "ability.accentStyle.static";
                }
                case SWAYING: {
                    return "ability.accentStyle.swaying";
                }
                case FLICKERING: {
                    return "ability.accentStyle.flickering";
                }
            }
            return this.name();
        }
    }

    public static enum ZoneShape {
        CIRCLE,
        SQUARE;


        public String toString() {
            switch (this) {
                case CIRCLE: {
                    return "ability.shape.circle";
                }
                case SQUARE: {
                    return "ability.shape.square";
                }
            }
            return this.name();
        }
    }

    public static enum ZoneType {
        TRAP,
        HAZARD;

    }
}

