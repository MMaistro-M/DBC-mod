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
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetFilter;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.util.AbilityTargetHelper;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import noppes.npcs.api.ability.type.IAbilityCutter;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityCutter
extends Ability
implements IAbilityCutter {
    private float arcAngle = 90.0f;
    private float range = 6.0f;
    private float damage = 7.0f;
    private float knockback = 1.5f;
    private SweepMode sweepMode = SweepMode.SWIPE;
    private float sweepSpeed = 6.0f;
    private int spinDurationTicks = 60;
    private boolean piercing = true;
    private float innerRadius = 0.0f;
    private transient Set<Integer> hitEntities = new HashSet<Integer>();
    private transient float currentRotation = 0.0f;
    private transient boolean activeSoundPlayed = false;
    private transient float sweepBaseYaw = 0.0f;

    public AbilityCutter() {
        this.typeId = "ability.cnpc.cutter";
        this.name = "Cutter";
        this.targetingMode = TargetingMode.AOE_SELF;
        this.maxRange = 8.0f;
        this.lockMovement = LockMode.NO;
        this.cooldownTicks = 0;
        this.windUpTicks = 20;
        this.telegraphType = TelegraphType.CONE;
        this.windUpSound = "random.bow";
        this.activeSound = "random.break";
        this.windUpAnimationName = "Ability_Cutter_Windup";
        this.activeAnimationName = "Ability_Cutter_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/cutter.png", this::getActiveColor)};
    }

    @Override
    public boolean isTargetingModeLocked() {
        return true;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.AOE_SELF};
    }

    private int getActiveDurationTicks() {
        if (this.sweepMode == SweepMode.SPIN) {
            return this.spinDurationTicks;
        }
        return this.sweepSpeed > 0.0f ? (int)Math.ceil(this.arcAngle / this.sweepSpeed) : 1;
    }

    @Override
    public boolean keepTelegraphDuringActive() {
        return true;
    }

    @Override
    public TelegraphInstance createTelegraph(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.isShowTelegraph() || this.getTelegraphType() == TelegraphType.NONE) {
            return null;
        }
        Telegraph telegraph = Telegraph.cone(this.getTelegraphLength(), this.getTelegraphAngle(), this.getTelegraphInnerRadius());
        int totalDuration = this.windUpTicks + this.getActiveDurationTicks();
        telegraph.setDurationTicks(totalDuration);
        telegraph.setColor(this.windUpColor);
        telegraph.setWarningColor(this.activeColor);
        telegraph.setWarningStartTick(this.windUpTicks);
        telegraph.setHeightOffset(this.telegraphHeightOffset);
        double groundY = AbilityCutter.findGroundLevel(caster.field_70170_p, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v);
        TelegraphInstance instance = new TelegraphInstance(telegraph, caster.field_70165_t, groundY, caster.field_70161_v, caster.field_70177_z);
        instance.setCasterEntityId(caster.func_145782_y());
        instance.setEntityIdToFollow(caster.func_145782_y());
        if (target != null && !this.isRotationLockedDuringWindup()) {
            instance.setTargetEntityId(target.func_145782_y());
        } else {
            instance.setTrackFollowedEntityYaw(true);
        }
        return instance;
    }

    @Override
    public float getTelegraphRadius() {
        return this.range;
    }

    @Override
    public float getTelegraphLength() {
        return this.range;
    }

    @Override
    public float getTelegraphAngle() {
        return this.arcAngle;
    }

    @Override
    public float getTelegraphInnerRadius() {
        return this.innerRadius;
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        double dz;
        double dx;
        this.hitEntities.clear();
        this.currentRotation = -this.arcAngle / 2.0f;
        this.activeSoundPlayed = false;
        this.sweepBaseYaw = target != null ? ((dx = target.field_70165_t - caster.field_70165_t) * dx + (dz = target.field_70161_v - caster.field_70161_v) * dz > 1.0E-4 ? (float)Math.toDegrees(Math.atan2(-dx, dz)) : caster.field_70177_z) : caster.field_70177_z;
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (caster.field_70170_p.field_72995_K && !this.isPreview()) {
            return;
        }
        switch (this.sweepMode) {
            case SWIPE: {
                float prevRotation = this.currentRotation;
                this.currentRotation += this.sweepSpeed;
                if (!this.activeSoundPlayed && this.currentRotation >= 0.0f) {
                    this.playActiveSound(caster, caster.field_70170_p);
                }
                if (this.currentRotation > this.arcAngle / 2.0f) {
                    if (!this.isPreview()) {
                        this.performSweepDamageRange(caster, caster.field_70170_p, this.innerRadius, this.range, prevRotation, this.arcAngle / 2.0f);
                    }
                    this.signalCompletion();
                    return;
                }
                if (this.isPreview()) break;
                this.performSweepDamageRange(caster, caster.field_70170_p, this.innerRadius, this.range, prevRotation, this.currentRotation);
                break;
            }
            case SPIN: {
                if (!this.activeSoundPlayed) {
                    this.playActiveSound(caster, caster.field_70170_p);
                }
                if (tick >= this.spinDurationTicks) {
                    this.signalCompletion();
                    return;
                }
                float prevSpin = this.currentRotation;
                this.currentRotation = (this.currentRotation + this.sweepSpeed) % 360.0f;
                if (this.isPreview()) break;
                this.hitEntities.clear();
                this.performSweepDamageRange(caster, caster.field_70170_p, this.innerRadius, this.range, prevSpin, this.currentRotation);
            }
        }
    }

    private void playActiveSound(EntityLivingBase caster, World world) {
        this.activeSoundPlayed = true;
        if (this.activeSound != null && !this.activeSound.isEmpty()) {
            world.func_72956_a((Entity)caster, this.activeSound, 1.0f, 1.0f);
        }
    }

    private void performSweepDamageRange(EntityLivingBase caster, World world, float minDist, float maxDist, float startAngle, float endAngle) {
        float minAngle = Math.min(startAngle, endAngle);
        float maxAngle = Math.max(startAngle, endAngle);
        AxisAlignedBB searchBox = AxisAlignedBB.func_72330_a((double)(caster.field_70165_t - (double)maxDist), (double)(caster.field_70163_u - 1.0), (double)(caster.field_70161_v - (double)maxDist), (double)(caster.field_70165_t + (double)maxDist), (double)(caster.field_70163_u + 3.0), (double)(caster.field_70161_v + (double)maxDist));
        List entities = world.func_72872_a(EntityLivingBase.class, searchBox);
        for (EntityLivingBase entity : entities) {
            if (entity == caster || this.hitEntities.contains(entity.func_145782_y()) || !AbilityTargetHelper.shouldAffect(caster, (Entity)entity, TargetFilter.ENEMIES, false)) continue;
            if (!this.piercing && !this.hitEntities.isEmpty()) break;
            double dx = entity.field_70165_t - caster.field_70165_t;
            double dz = entity.field_70161_v - caster.field_70161_v;
            double dist = Math.sqrt(dx * dx + dz * dz);
            if (dist < (double)minDist || dist > (double)maxDist || !this.isInSweepRange(dx, dz, this.sweepBaseYaw, minAngle, maxAngle) || !AbilityCutter.hasLineOfSight(world, caster, entity) || AbilityCutter.isBlockedByBarrier(world, caster, entity)) continue;
            this.hitEntities.add(entity.func_145782_y());
            float distFactor = 1.0f - (float)dist / maxDist * 0.3f;
            float actualDamage = this.damage * distFactor;
            boolean wasHit = this.applyAbilityDamage(caster, entity, actualDamage, this.knockback);
            if (!wasHit) continue;
            this.applyEffects(entity);
        }
    }

    private boolean isInSweepRange(double dx, double dz, float casterYaw, float minAngle, float maxAngle) {
        double angleToEntity = Math.toDegrees(Math.atan2(-dx, dz));
        double entityRelative = this.normalizeAngle(angleToEntity - (double)casterYaw);
        return entityRelative >= (double)minAngle && entityRelative <= (double)maxAngle;
    }

    private double normalizeAngle(double angle) {
        while (angle > 180.0) {
            angle -= 360.0;
        }
        while (angle < -180.0) {
            angle += 360.0;
        }
        return angle;
    }

    @Override
    public void onComplete(EntityLivingBase caster, EntityLivingBase target) {
        this.hitEntities.clear();
        this.currentRotation = 0.0f;
        this.activeSoundPlayed = false;
        this.sweepBaseYaw = 0.0f;
    }

    @Override
    public void onInterrupt(EntityLivingBase caster, DamageSource source, float damage) {
        this.hitEntities.clear();
        this.currentRotation = 0.0f;
        this.activeSoundPlayed = false;
        this.sweepBaseYaw = 0.0f;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("arcAngle", this.arcAngle);
        nbt.func_74776_a("range", this.range);
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74776_a("knockback", this.knockback);
        nbt.func_74778_a("sweepMode", this.sweepMode.name());
        nbt.func_74776_a("sweepSpeed", this.sweepSpeed);
        nbt.func_74768_a("spinDurationTicks", this.spinDurationTicks);
        nbt.func_74757_a("piercing", this.piercing);
        nbt.func_74776_a("innerRadius", this.innerRadius);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.arcAngle = nbt.func_74760_g("arcAngle");
        this.range = nbt.func_74760_g("range");
        this.damage = nbt.func_74760_g("damage");
        this.knockback = nbt.func_74760_g("knockback");
        try {
            this.sweepMode = SweepMode.valueOf(nbt.func_74779_i("sweepMode"));
        }
        catch (Exception e) {
            this.sweepMode = SweepMode.SWIPE;
        }
        this.sweepSpeed = nbt.func_74760_g("sweepSpeed");
        this.spinDurationTicks = nbt.func_74762_e("spinDurationTicks");
        this.piercing = nbt.func_74767_n("piercing");
        this.innerRadius = nbt.func_74760_g("innerRadius");
    }

    @Override
    public float getArcAngle() {
        return this.arcAngle;
    }

    @Override
    public void setArcAngle(float arcAngle) {
        this.arcAngle = arcAngle;
    }

    @Override
    public float getRange() {
        return this.range;
    }

    @Override
    public void setRange(float range) {
        this.range = range;
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

    public SweepMode getSweepModeEnum() {
        return this.sweepMode;
    }

    public void setSweepModeEnum(SweepMode sweepMode) {
        this.sweepMode = sweepMode;
    }

    @Override
    public int getSweepMode() {
        return this.sweepMode.ordinal();
    }

    @Override
    public void setSweepMode(int mode) {
        SweepMode[] values = SweepMode.values();
        this.sweepMode = mode >= 0 && mode < values.length ? values[mode] : SweepMode.SWIPE;
    }

    @Override
    public float getSweepSpeed() {
        return this.sweepSpeed;
    }

    @Override
    public void setSweepSpeed(float sweepSpeed) {
        this.sweepSpeed = sweepSpeed;
    }

    @Override
    public int getSpinDurationTicks() {
        return this.spinDurationTicks;
    }

    @Override
    public void setSpinDurationTicks(int spinDurationTicks) {
        this.spinDurationTicks = Math.max(1, spinDurationTicks);
    }

    @Override
    public boolean isPiercing() {
        return this.piercing;
    }

    @Override
    public void setPiercing(boolean piercing) {
        this.piercing = piercing;
    }

    @Override
    public float getInnerRadius() {
        return this.innerRadius;
    }

    @Override
    public void setInnerRadius(float innerRadius) {
        this.innerRadius = innerRadius;
    }

    public float getCurrentRotation() {
        return this.currentRotation;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("gui.range", this::getRange, this::setRange)), FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback), FieldDef.section("ability.section.sweep"), FieldDef.enumField("ability.sweepMode", SweepMode.class, this::getSweepModeEnum, this::setSweepModeEnum).hover("ability.hover.sweepMode"), FieldDef.row(FieldDef.floatField("ability.arcAngle", this::getArcAngle, this::setArcAngle), FieldDef.floatField("ability.innerRadius", this::getInnerRadius, this::setInnerRadius).hover("ability.hover.innerRadius")), FieldDef.floatField("ability.sweepSpeed", this::getSweepSpeed, this::setSweepSpeed), FieldDef.intField("ability.spinDuration", this::getSpinDurationTicks, this::setSpinDurationTicks).range(1.0f, 1000.0f).visibleWhen(() -> this.getSweepModeEnum() == SweepMode.SPIN), FieldDef.boolField("ability.piercing", this::isPiercing, this::setPiercing).hover("ability.hover.piercing"), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
    }

    public static enum SweepMode {
        SWIPE,
        SPIN;


        public String toString() {
            switch (this) {
                case SWIPE: {
                    return "ability.sweep.swipe";
                }
                case SPIN: {
                    return "ability.sweep.spin";
                }
            }
            return this.name();
        }
    }
}

