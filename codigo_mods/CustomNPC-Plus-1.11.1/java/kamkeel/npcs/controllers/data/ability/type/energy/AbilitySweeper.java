/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 */
package kamkeel.npcs.controllers.data.ability.type.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityEnergy;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityEnergySweeper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import noppes.npcs.api.ability.type.IAbilitySweeper;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilitySweeper
extends AbilityEnergy
implements IAbilitySweeper {
    private float beamLength = 10.0f;
    private float beamWidth = 0.3f;
    private float beamHeight = 1.0f;
    private float damage = 5.0f;
    private int damageInterval = 5;
    private boolean piercing = true;
    private float sweepSpeed = 3.0f;
    private int numberOfRotations = 2;
    private boolean lockOnTarget = false;
    private transient EntityEnergySweeper activeEntity = null;

    public AbilitySweeper() {
        super(new EnergyDisplayData(0xFF6600, 0xFF0000, true, 1.8f, 0.5f, 0.0f));
        this.typeId = "ability.cnpc.sweeper";
        this.name = "Sweeper";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.maxRange = 15.0f;
        this.minRange = 0.0f;
        this.lockMovement = LockMode.WINDUP_AND_ACTIVE;
        this.cooldownTicks = 0;
        this.windUpTicks = 60;
        this.telegraphType = TelegraphType.CIRCLE;
        this.showTelegraph = true;
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/sweeper.png"), new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/sweeper_overlay.png", () -> this.isOuterColorEnabled() ? this.getOuterColor() : this.getInnerColor())};
    }

    @Override
    public boolean allowBurst() {
        return false;
    }

    @Override
    public boolean isConcurrentCapable() {
        return true;
    }

    @Override
    public boolean allowFreeOnCast() {
        return true;
    }

    @Override
    public void detach() {
        this.activeEntity = null;
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
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        this.activeEntity = new EntityEnergySweeper(caster.field_70170_p, caster, target, this.beamLength, this.beamWidth, this.beamHeight, this.displayData, this.sweepSpeed, this.numberOfRotations, this.damage, this.damageInterval, this.piercing, this.lockOnTarget);
        this.activeEntity.setIgnoreIFrames(this.isIgnoreIFrames());
        this.activeEntity.setSourceAbility(this);
        if (this.isPreview()) {
            this.activeEntity.setupPreview(caster);
        }
        this.spawnAbilityEntity(this.activeEntity);
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (this.activeEntity == null || this.activeEntity.field_70128_L) {
            this.activeEntity = null;
            this.signalCompletion();
            return;
        }
        if (this.isFreeOnCast()) {
            this.signalCompletion();
        }
    }

    @Override
    public void onInterrupt(EntityLivingBase caster, DamageSource source, float damage) {
        this.cleanup();
    }

    @Override
    public void cleanup() {
        if (this.activeEntity != null && !this.activeEntity.field_70128_L) {
            this.activeEntity.func_70106_y();
        }
        this.activeEntity = null;
    }

    @Override
    public float getTelegraphRadius() {
        return this.beamLength;
    }

    @Override
    public TelegraphInstance createTelegraph(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.showTelegraph || this.telegraphType == TelegraphType.NONE) {
            return null;
        }
        Telegraph telegraph = Telegraph.circle(this.beamLength);
        telegraph.setDurationTicks(this.windUpTicks);
        telegraph.setColor(this.windUpColor);
        telegraph.setWarningColor(this.activeColor);
        telegraph.setWarningStartTick(Math.max(5, this.windUpTicks / 4));
        telegraph.setHeightOffset(this.telegraphHeightOffset);
        double groundY = AbilitySweeper.findGroundLevel(caster.field_70170_p, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v);
        TelegraphInstance instance = new TelegraphInstance(telegraph, caster.field_70165_t, groundY, caster.field_70161_v, caster.field_70177_z);
        instance.setCasterEntityId(caster.func_145782_y());
        instance.setEntityIdToFollow(caster.func_145782_y());
        return instance;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("beamLength", this.beamLength);
        nbt.func_74776_a("beamWidth", this.beamWidth);
        nbt.func_74776_a("beamHeight", this.beamHeight);
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74768_a("damageInterval", this.damageInterval);
        nbt.func_74757_a("piercing", this.piercing);
        nbt.func_74776_a("sweepSpeed", this.sweepSpeed);
        nbt.func_74768_a("numberOfRotations", this.numberOfRotations);
        nbt.func_74757_a("lockOnTarget", this.lockOnTarget);
        this.writeEnergyNBT(nbt);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.beamLength = nbt.func_74760_g("beamLength");
        this.beamWidth = nbt.func_74760_g("beamWidth");
        this.beamHeight = nbt.func_74760_g("beamHeight");
        this.damage = nbt.func_74760_g("damage");
        this.damageInterval = nbt.func_74762_e("damageInterval");
        this.piercing = nbt.func_74767_n("piercing");
        this.sweepSpeed = nbt.func_74760_g("sweepSpeed");
        this.numberOfRotations = nbt.func_74762_e("numberOfRotations");
        this.lockOnTarget = nbt.func_74767_n("lockOnTarget");
        this.readEnergyNBT(nbt);
    }

    @Override
    public float getBeamLength() {
        return this.beamLength;
    }

    @Override
    public void setBeamLength(float beamLength) {
        this.beamLength = beamLength;
    }

    @Override
    public float getBeamWidth() {
        return this.beamWidth;
    }

    @Override
    public void setBeamWidth(float beamWidth) {
        this.beamWidth = beamWidth;
    }

    @Override
    public float getBeamHeight() {
        return this.beamHeight;
    }

    @Override
    public void setBeamHeight(float beamHeight) {
        this.beamHeight = beamHeight;
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
    public int getDamageInterval() {
        return this.damageInterval;
    }

    @Override
    public void setDamageInterval(int damageInterval) {
        this.damageInterval = damageInterval;
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
    public float getSweepSpeed() {
        return this.sweepSpeed;
    }

    @Override
    public void setSweepSpeed(float sweepSpeed) {
        this.sweepSpeed = sweepSpeed;
    }

    @Override
    public int getNumberOfRotations() {
        return this.numberOfRotations;
    }

    @Override
    public void setNumberOfRotations(int numberOfRotations) {
        this.numberOfRotations = numberOfRotations;
    }

    @Override
    public boolean isLockOnTarget() {
        return this.lockOnTarget;
    }

    @Override
    public void setLockOnTarget(boolean lockOnTarget) {
        this.lockOnTarget = lockOnTarget;
    }

    @Override
    public int getMaxPreviewDuration() {
        return (int)(360.0f * (float)this.numberOfRotations / this.sweepSpeed) + 10;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.intField("ability.damageInterval", this::getDamageInterval, this::setDamageInterval)), FieldDef.section("ability.section.beam"), FieldDef.row(FieldDef.floatField("gui.length", this::getBeamLength, this::setBeamLength).range(0.5f, 100.0f), FieldDef.floatField("gui.width", this::getBeamWidth, this::setBeamWidth).range(0.1f, 100.0f)), FieldDef.row(FieldDef.floatField("gui.height", this::getBeamHeight, this::setBeamHeight).range(0.1f, 100.0f), FieldDef.floatField("ability.sweepSpeed", this::getSweepSpeed, this::setSweepSpeed).range(0.1f, 30.0f)), FieldDef.intField("ability.rotations", this::getNumberOfRotations, this::setNumberOfRotations).range(1.0f, 20.0f), FieldDef.row(FieldDef.boolField("ability.piercing", this::isPiercing, this::setPiercing).hover("ability.hover.piercing"), FieldDef.boolField("ability.lockTarget", this::isLockOnTarget, this::setLockOnTarget).hover("ability.hover.lockTarget")), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
        this.addEnergyVisualDefinitions(defs);
    }
}

