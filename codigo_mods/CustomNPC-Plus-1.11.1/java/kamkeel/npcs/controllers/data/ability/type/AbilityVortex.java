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
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetFilter;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.util.AbilityTargetHelper;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import noppes.npcs.api.ability.type.IAbilityVortex;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityVortex
extends Ability
implements IAbilityVortex {
    private float pullRadius = 8.0f;
    private float pullStrength = 0.8f;
    private float damage = 0.0f;
    private float knockback = 0.0f;
    private boolean aoe = true;
    private boolean damageOnPull = false;
    private float pullDamage = 0.0f;
    private transient Map<Integer, PullState> pulledEntities;
    private transient boolean pullComplete = false;
    private transient int ticksSincePullDamage = 0;

    private Map<Integer, PullState> getPulledEntities() {
        if (this.pulledEntities == null) {
            this.pulledEntities = new HashMap<Integer, PullState>();
        }
        return this.pulledEntities;
    }

    public AbilityVortex() {
        this.typeId = "ability.cnpc.vortex";
        this.name = "Vortex";
        this.targetingMode = TargetingMode.AOE_SELF;
        this.maxRange = 15.0f;
        this.lockMovement = LockMode.WINDUP_AND_ACTIVE;
        this.cooldownTicks = 0;
        this.windUpTicks = 30;
        this.telegraphType = TelegraphType.CIRCLE;
        this.windUpSound = "mob.ghast.charge";
        this.activeSound = "mob.ghast.fireball";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/vortex.png", this::getActiveColor)};
    }

    @Override
    public boolean isTargetingModeLocked() {
        return true;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.AOE_SELF};
    }

    @Override
    public float getTelegraphRadius() {
        return this.pullRadius;
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        this.getPulledEntities().clear();
        this.pullComplete = false;
        this.ticksSincePullDamage = 0;
        if (!this.isPreview() && !caster.field_70170_p.field_72995_K) {
            AxisAlignedBB box = caster.field_70121_D.func_72314_b((double)this.pullRadius, (double)(this.pullRadius / 2.0f), (double)this.pullRadius);
            List entities = caster.field_70170_p.func_72872_a(EntityLivingBase.class, box);
            if (this.aoe) {
                for (EntityLivingBase entity : entities) {
                    double dist;
                    if (entity == caster || entity.field_70128_L || !AbilityTargetHelper.shouldAffect(caster, (Entity)entity, TargetFilter.ENEMIES, false) || !((dist = (double)caster.func_70032_d((Entity)entity)) <= (double)this.pullRadius)) continue;
                    this.getPulledEntities().put(entity.func_145782_y(), new PullState(entity));
                }
            } else {
                double dist;
                if (!this.isPlayerCaster(caster) && target != null && !target.field_70128_L && (dist = (double)caster.func_70032_d((Entity)target)) <= (double)this.pullRadius) {
                    this.getPulledEntities().put(target.func_145782_y(), new PullState(target));
                    return;
                }
                ArrayList<EntityLivingBase> validTargets = new ArrayList<EntityLivingBase>();
                for (EntityLivingBase entity : entities) {
                    double dist2;
                    if (entity == caster || entity.field_70128_L || !AbilityTargetHelper.shouldAffect(caster, (Entity)entity, TargetFilter.ENEMIES, false) || !((dist2 = (double)caster.func_70032_d((Entity)entity)) <= (double)this.pullRadius)) continue;
                    validTargets.add(entity);
                }
                if (!validTargets.isEmpty()) {
                    EntityLivingBase chosen = (EntityLivingBase)validTargets.get(caster.field_70170_p.field_73012_v.nextInt(validTargets.size()));
                    this.getPulledEntities().put(chosen.func_145782_y(), new PullState(chosen));
                }
            }
        }
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        boolean shouldDealPullDamage;
        if (this.isPreview()) {
            if (tick >= 60) {
                this.signalCompletion();
            }
            return;
        }
        if (this.pullComplete || this.getPulledEntities().isEmpty()) {
            this.signalCompletion();
            return;
        }
        if (caster.field_70170_p.field_72995_K) {
            return;
        }
        int maxActiveTicks = Math.max(40, (int)(this.pullRadius / this.pullStrength * 3.0f));
        if (tick >= maxActiveTicks) {
            for (Map.Entry<Integer, PullState> entry : new HashMap<Integer, PullState>(this.getPulledEntities()).entrySet()) {
                EntityLivingBase entity = this.findEntity(caster.field_70170_p, entry.getKey());
                if (entity == null || entity.field_70128_L) continue;
                this.onTargetArrived(caster, entity, caster.field_70170_p);
            }
            this.getPulledEntities().clear();
            this.pullComplete = true;
            this.signalCompletion();
            return;
        }
        double destX = caster.field_70165_t;
        double destY = caster.field_70163_u;
        double destZ = caster.field_70161_v;
        boolean anyStillPulling = false;
        ++this.ticksSincePullDamage;
        boolean bl = shouldDealPullDamage = this.damageOnPull && this.pullDamage > 0.0f && this.ticksSincePullDamage >= 10;
        if (shouldDealPullDamage) {
            this.ticksSincePullDamage = 0;
        }
        for (Map.Entry<Integer, PullState> entry : new HashMap<Integer, PullState>(this.getPulledEntities()).entrySet()) {
            double expectedProgress;
            int entityId = entry.getKey();
            PullState state = entry.getValue();
            EntityLivingBase entity = this.findEntity(caster.field_70170_p, entityId);
            if (entity == null || entity.field_70128_L) {
                this.getPulledEntities().remove(entityId);
                continue;
            }
            double dx = destX - entity.field_70165_t;
            double dy = destY - entity.field_70163_u;
            double dz = destZ - entity.field_70161_v;
            double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
            if (dist <= 1.5) {
                this.getPulledEntities().remove(entityId);
                this.onTargetArrived(caster, entity, caster.field_70170_p);
                continue;
            }
            double lastDx = destX - state.lastX;
            double lastDy = destY - state.lastY;
            double lastDz = destZ - state.lastZ;
            double lastDist = Math.sqrt(lastDx * lastDx + lastDy * lastDy + lastDz * lastDz);
            double progress = lastDist - dist;
            state.stuckTicks = progress < (expectedProgress = Math.min((double)this.pullStrength, dist * 0.5)) * 0.3 ? ++state.stuckTicks : 0;
            if (state.stuckTicks >= 5) {
                this.getPulledEntities().remove(entityId);
                this.onTargetArrived(caster, entity, caster.field_70170_p);
                continue;
            }
            anyStillPulling = true;
            state.lastX = entity.field_70165_t;
            state.lastY = entity.field_70163_u;
            state.lastZ = entity.field_70161_v;
            double maxSpeed = dist * 0.5;
            double effectiveSpeed = Math.min((double)this.pullStrength, maxSpeed);
            double factor = effectiveSpeed / dist;
            double motionX = dx * factor;
            double motionY = dy * factor * 0.5;
            double motionZ = dz * factor;
            if (entity instanceof EntityPlayer) {
                entity.field_70159_w = motionX;
                entity.field_70181_x = motionY;
                entity.field_70179_y = motionZ;
                entity.field_70133_I = true;
            } else {
                entity.func_70091_d(motionX, motionY, motionZ);
            }
            if (!shouldDealPullDamage) continue;
            this.applyAbilityDamage(caster, entity, this.pullDamage * 0.5f, 0.0f);
        }
        if (!anyStillPulling && this.getPulledEntities().isEmpty()) {
            this.pullComplete = true;
            this.signalCompletion();
        }
    }

    private void onTargetArrived(EntityLivingBase caster, EntityLivingBase entity, World world) {
        boolean wasHit = this.applyAbilityDamage(caster, entity, this.damage, this.knockback * 0.5f);
        if (wasHit) {
            this.applyEffects(entity);
        }
    }

    private EntityLivingBase findEntity(World world, int entityId) {
        Entity entity = world.func_73045_a(entityId);
        if (entity instanceof EntityLivingBase) {
            return (EntityLivingBase)entity;
        }
        return null;
    }

    @Override
    public void cleanup() {
        this.getPulledEntities().clear();
        this.pullComplete = false;
        this.ticksSincePullDamage = 0;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("pullRadius", this.pullRadius);
        nbt.func_74776_a("pullStrength", this.pullStrength);
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74776_a("knockback", this.knockback);
        nbt.func_74757_a("aoe", this.aoe);
        nbt.func_74757_a("damageOnPull", this.damageOnPull);
        nbt.func_74776_a("pullDamage", this.pullDamage);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.pullRadius = nbt.func_74760_g("pullRadius");
        this.pullStrength = nbt.func_74760_g("pullStrength");
        this.damage = nbt.func_74760_g("damage");
        this.knockback = nbt.func_74760_g("knockback");
        this.aoe = !nbt.func_74764_b("aoe") || nbt.func_74767_n("aoe");
        this.damageOnPull = nbt.func_74767_n("damageOnPull");
        this.pullDamage = nbt.func_74760_g("pullDamage");
    }

    @Override
    public float getPullRadius() {
        return this.pullRadius;
    }

    @Override
    public void setPullRadius(float pullRadius) {
        this.pullRadius = pullRadius;
    }

    @Override
    public float getPullStrength() {
        return this.pullStrength;
    }

    @Override
    public void setPullStrength(float pullStrength) {
        this.pullStrength = pullStrength;
    }

    @Override
    public float getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public float getDisplayDamage() {
        return this.damage;
    }

    @Override
    public float getKnockback() {
        return this.knockback;
    }

    @Override
    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    @Override
    public boolean isAoe() {
        return this.aoe;
    }

    @Override
    public void setAoe(boolean aoe) {
        this.aoe = aoe;
    }

    @Override
    public boolean isDamageOnPull() {
        return this.damageOnPull;
    }

    @Override
    public void setDamageOnPull(boolean damageOnPull) {
        this.damageOnPull = damageOnPull;
    }

    @Override
    public float getPullDamage() {
        return this.pullDamage;
    }

    @Override
    public void setPullDamage(float pullDamage) {
        this.pullDamage = pullDamage;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.row(FieldDef.floatField("ability.pullRadius", this::getPullRadius, this::setPullRadius), FieldDef.floatField("ability.pullStrength", this::getPullStrength, this::setPullStrength)), FieldDef.section("ability.section.damage"), FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback)), FieldDef.section("ability.section.aoe"), FieldDef.boolField("gui.enabled", this::isAoe, this::setAoe).hover("ability.hover.aoe"), FieldDef.section("ability.section.pullDamage"), FieldDef.boolField("gui.enabled", this::isDamageOnPull, this::setDamageOnPull).hover("ability.hover.dmgOnPull"), FieldDef.floatField("enchantment.damage", this::getPullDamage, this::setPullDamage).visibleWhen(this::isDamageOnPull), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
    }

    private static class PullState {
        double lastX;
        double lastY;
        double lastZ;
        int stuckTicks;

        PullState(EntityLivingBase entity) {
            this.lastX = entity.field_70165_t;
            this.lastY = entity.field_70163_u;
            this.lastZ = entity.field_70161_v;
            this.stuckTicks = 0;
        }
    }
}

