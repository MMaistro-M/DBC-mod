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
import java.util.List;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyBarrierData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityEnergy;
import kamkeel.npcs.entity.EntityEnergyBarrier;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.controllers.data.MagicData;

public abstract class AbilityBarrier
extends AbilityEnergy {
    protected final EnergyBarrierData barrierData;
    protected float offsetX = 0.0f;
    protected float offsetY = 0.0f;
    protected float offsetZ = 0.0f;
    protected transient EntityEnergyBarrier barrierEntity;

    protected AbilityBarrier(EnergyDisplayData displayData, EnergyBarrierData barrierData) {
        super(displayData);
        this.barrierData = barrierData;
    }

    protected abstract EntityEnergyBarrier createBarrierEntity(EntityLivingBase var1, EntityLivingBase var2);

    @SideOnly(value=Side.CLIENT)
    protected abstract void addBarrierTypeDefinitions(List<FieldDef> var1);

    protected abstract void writeBarrierTypeNBT(NBTTagCompound var1);

    protected abstract void readBarrierTypeNBT(NBTTagCompound var1);

    @Override
    public boolean allowBurst() {
        return false;
    }

    @Override
    public boolean hasDamage() {
        return false;
    }

    @Override
    public boolean hasMagic() {
        return true;
    }

    @Override
    public boolean allowFreeOnCast() {
        return true;
    }

    @Override
    public void detach() {
        this.barrierEntity = null;
    }

    @Override
    public void onWindUpTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (caster.field_70170_p.field_72995_K) {
            return;
        }
        if (tick == 1) {
            this.barrierEntity = this.createBarrierEntity(caster, target);
            if (this.barrierEntity != null) {
                this.inheritMagicData(this.barrierEntity, caster);
                this.applyBarrierHealthModifiers(caster);
                this.barrierEntity.setupCharging(this.getWindUpTicks());
                this.spawnAbilityEntity(this.barrierEntity);
            }
        }
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        if (this.barrierEntity != null && !this.barrierEntity.field_70128_L) {
            this.barrierEntity.finishCharging();
        } else {
            this.barrierEntity = this.createBarrierEntity(caster, target);
            if (this.barrierEntity != null) {
                this.inheritMagicData(this.barrierEntity, caster);
                this.applyBarrierHealthModifiers(caster);
                this.spawnAbilityEntity(this.barrierEntity);
            }
        }
    }

    private void inheritMagicData(EntityEnergyBarrier entity, EntityLivingBase caster) {
        MagicData resolved = this.resolveMagicData(caster);
        if (resolved != null) {
            entity.setMagicData(resolved.copy());
        }
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (this.barrierEntity == null || this.barrierEntity.field_70128_L) {
            this.signalCompletion();
            return;
        }
        if (this.isFreeOnCast()) {
            this.signalCompletion();
            return;
        }
        if (this.barrierData.useDuration && tick >= this.barrierData.durationTicks) {
            this.cleanup();
            this.signalCompletion();
        }
    }

    @Override
    public void onComplete(EntityLivingBase caster, EntityLivingBase target) {
    }

    private void applyBarrierHealthModifiers(EntityLivingBase caster) {
        float modHealth = AbilityController.Instance.fireModifyBarrierHealth(this, caster, this.barrierData.maxHealth);
        if (modHealth != this.barrierData.maxHealth) {
            this.barrierEntity.setBarrierMaxHealth(modHealth);
        }
    }

    @Override
    public void onInterrupt(EntityLivingBase caster, DamageSource source, float damage) {
        this.cleanup();
    }

    @Override
    public void cleanup() {
        if (this.barrierEntity != null && !this.barrierEntity.field_70128_L) {
            this.barrierEntity.func_70106_y();
        }
        this.barrierEntity = null;
    }

    @Override
    public final void writeTypeNBT(NBTTagCompound nbt) {
        this.writeBarrierTypeNBT(nbt);
        this.writeEnergyNBT(nbt);
        this.barrierData.writeNBT(nbt);
        nbt.func_74776_a("barrierOffsetX", this.offsetX);
        nbt.func_74776_a("barrierOffsetY", this.offsetY);
        nbt.func_74776_a("barrierOffsetZ", this.offsetZ);
    }

    @Override
    public final void readTypeNBT(NBTTagCompound nbt) {
        this.offsetX = nbt.func_74764_b("barrierOffsetX") ? nbt.func_74760_g("barrierOffsetX") : 0.0f;
        this.offsetY = nbt.func_74764_b("barrierOffsetY") ? nbt.func_74760_g("barrierOffsetY") : 0.0f;
        this.offsetZ = nbt.func_74764_b("barrierOffsetZ") ? nbt.func_74760_g("barrierOffsetZ") : 0.0f;
        this.readBarrierTypeNBT(nbt);
        this.readEnergyNBT(nbt);
        this.barrierData.readNBT(nbt);
    }

    public float getBarrierMaxHealth() {
        return this.barrierData.maxHealth;
    }

    public void setBarrierMaxHealth(float maxHealth) {
        this.barrierData.setMaxHealth(maxHealth);
    }

    public boolean isUseHealth() {
        return this.barrierData.useHealth;
    }

    public void setUseHealth(boolean useHealth) {
        this.barrierData.useHealth = useHealth;
    }

    @Override
    public float getDisplayBarrierHealth() {
        return this.barrierData.useHealth ? this.barrierData.maxHealth : 0.0f;
    }

    @Override
    public boolean isDisplayReflect() {
        return this.barrierData.reflect;
    }

    @Override
    public float getDisplayReflectStrength() {
        return this.barrierData.reflectStrengthPct;
    }

    @Override
    public boolean isDisplayAbsorbing() {
        return this.barrierData.absorbing;
    }

    public int getBarrierDuration() {
        return this.barrierData.durationTicks;
    }

    public void setBarrierDuration(int ticks) {
        this.barrierData.setDurationTicks(ticks);
    }

    public boolean isUseDuration() {
        return this.barrierData.useDuration;
    }

    public void setUseDuration(boolean useDuration) {
        this.barrierData.useDuration = useDuration;
    }

    public float getDefaultMultiplier() {
        return this.barrierData.defaultMultiplier;
    }

    public void setDefaultMultiplier(float mult) {
        this.barrierData.defaultMultiplier = mult;
    }

    public void setDamageMultiplier(String typeId, float mult) {
        this.barrierData.setMultiplier(typeId, mult);
    }

    public float getDamageMultiplier(String typeId) {
        return this.barrierData.getMultiplier(typeId);
    }

    public boolean isSolid() {
        return this.barrierData.solid;
    }

    public void setSolid(boolean solid) {
        this.barrierData.solid = solid;
    }

    public boolean isKnockbackEnabled() {
        return this.barrierData.knockbackEnabled;
    }

    public void setKnockbackEnabled(boolean enabled) {
        this.barrierData.knockbackEnabled = enabled;
    }

    public float getKnockbackStrength() {
        return this.barrierData.knockbackStrength;
    }

    public void setKnockbackStrength(float strength) {
        this.barrierData.knockbackStrength = strength;
    }

    public boolean isAbsorbing() {
        return this.barrierData.absorbing;
    }

    public void setAbsorbing(boolean absorbing) {
        this.barrierData.absorbing = absorbing;
    }

    public float getAbsorbRadius() {
        return this.barrierData.absorbRadius;
    }

    public void setAbsorbRadius(float radius) {
        this.barrierData.absorbRadius = radius;
    }

    public boolean isReflect() {
        return this.barrierData.reflect;
    }

    public void setReflect(boolean reflect) {
        this.barrierData.reflect = reflect;
    }

    public float getReflectStrengthPct() {
        return this.barrierData.reflectStrengthPct;
    }

    public void setReflectStrengthPct(float strengthPct) {
        this.barrierData.setReflectStrengthPct(strengthPct);
    }

    public boolean isTargetOwner() {
        return this.barrierData.targetOwner;
    }

    public void setTargetOwner(boolean targetOwner) {
        this.barrierData.targetOwner = targetOwner;
    }

    public boolean isMeleeEnabled() {
        return this.barrierData.meleeEnabled;
    }

    public void setMeleeEnabled(boolean enabled) {
        this.barrierData.meleeEnabled = enabled;
    }

    public float getMeleeDamageMultiplier() {
        return this.barrierData.meleeDamageMultiplier;
    }

    public void setMeleeDamageMultiplier(float mult) {
        this.barrierData.meleeDamageMultiplier = mult;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public float getOffsetZ() {
        return this.offsetZ;
    }

    public void setOffsetZ(float offsetZ) {
        this.offsetZ = offsetZ;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public final void getAbilityDefinitions(List<FieldDef> defs) {
        this.addBarrierTypeDefinitions(defs);
        defs.add(FieldDef.section("ability.section.offset"));
        defs.add(FieldDef.row(FieldDef.floatField("ability.offsetX", this::getOffsetX, this::setOffsetX).min(Float.NEGATIVE_INFINITY), FieldDef.floatField("ability.offsetY", this::getOffsetY, this::setOffsetY).min(Float.NEGATIVE_INFINITY)));
        defs.add(FieldDef.floatField("ability.offsetZ", this::getOffsetZ, this::setOffsetZ).min(Float.NEGATIVE_INFINITY));
        defs.add(FieldDef.section("ability.section.barrier"));
        defs.add(FieldDef.boolField("ability.useHealth", this::isUseHealth, this::setUseHealth));
        defs.add(FieldDef.floatField("ability.maxHealth", this::getBarrierMaxHealth, this::setBarrierMaxHealth).visibleWhen(this::isUseHealth));
        defs.add(FieldDef.boolField("ability.useDuration", this::isUseDuration, this::setUseDuration));
        defs.add(FieldDef.intField("ability.duration", this::getBarrierDuration, this::setBarrierDuration).range(1.0f, 12000.0f).visibleWhen(this::isUseDuration));
        defs.add(FieldDef.floatField("ability.defaultMultiplier", this::getDefaultMultiplier, this::setDefaultMultiplier));
        defs.add(FieldDef.section("ability.section.properties"));
        defs.add(FieldDef.boolField("ability.solid", this::isSolid, this::setSolid).hover("ability.hover.solid"));
        defs.add(FieldDef.boolField("ability.knockbackEnabled", this::isKnockbackEnabled, this::setKnockbackEnabled).hover("ability.hover.knockbackEnabled"));
        defs.add(FieldDef.floatField("ability.knockbackStrength", this::getKnockbackStrength, this::setKnockbackStrength).range(0.0f, 10.0f).visibleWhen(this::isKnockbackEnabled));
        defs.add(FieldDef.boolField("ability.absorbing", this::isAbsorbing, this::setAbsorbing).hover("ability.hover.absorbing"));
        defs.add(FieldDef.floatField("ability.absorbRadius", this::getAbsorbRadius, this::setAbsorbRadius).range(-1.0f, 128.0f).visibleWhen(this::isAbsorbing).hover("ability.hover.absorbRadius"));
        defs.add(FieldDef.boolField("ability.reflect", this::isReflect, this::setReflect).hover("ability.hover.reflect"));
        defs.add(FieldDef.floatField("ability.reflectStrength", this::getReflectStrengthPct, this::setReflectStrengthPct).range(0.0f, 100.0f).visibleWhen(this::isReflect));
        defs.add(FieldDef.boolField("ability.targetOwner", this::isTargetOwner, this::setTargetOwner).hover("ability.hover.targetOwner").visibleWhen(this::isReflect));
        defs.add(FieldDef.boolField("ability.meleeEnabled", this::isMeleeEnabled, this::setMeleeEnabled).hover("ability.hover.meleeEnabled"));
        defs.add(FieldDef.floatField("ability.meleeDamageMultiplier", this::getMeleeDamageMultiplier, this::setMeleeDamageMultiplier).range(0.0f, 10.0f).visibleWhen(this::isMeleeEnabled));
        this.addEnergyVisualDefinitions(defs);
    }
}

