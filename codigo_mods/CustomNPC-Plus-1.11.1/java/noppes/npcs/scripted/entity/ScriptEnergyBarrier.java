/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityEnergyBarrier;
import kamkeel.npcs.entity.EntityEnergyDome;
import noppes.npcs.api.entity.IEnergyBarrier;
import noppes.npcs.scripted.entity.ScriptEnergyAbility;

public class ScriptEnergyBarrier<T extends EntityEnergyBarrier>
extends ScriptEnergyAbility<T>
implements IEnergyBarrier {
    public ScriptEnergyBarrier(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 19;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 19 || super.typeOf(type);
    }

    @Override
    public float getCurrentHealth() {
        return ((EntityEnergyBarrier)this.entity).getCurrentHealth();
    }

    @Override
    public void setCurrentHealth(float health) {
        ((EntityEnergyBarrier)this.entity).setCurrentHealth(health);
    }

    @Override
    public float getHealthPercent() {
        return ((EntityEnergyBarrier)this.entity).getHealthPercent();
    }

    @Override
    public float getMaxHealth() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().maxHealth;
    }

    @Override
    public void setMaxHealth(float maxHealth) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().setMaxHealth(maxHealth);
    }

    @Override
    public boolean isUseHealth() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().useHealth;
    }

    @Override
    public void setUseHealth(boolean useHealth) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().useHealth = useHealth;
    }

    @Override
    public int getDuration() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().durationTicks;
    }

    @Override
    public void setDuration(int ticks) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().setDurationTicks(ticks);
    }

    @Override
    public boolean isUseDuration() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().useDuration;
    }

    @Override
    public void setUseDuration(boolean useDuration) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().useDuration = useDuration;
    }

    @Override
    public int getTicksAlive() {
        return ((EntityEnergyBarrier)this.entity).getTicksAlive();
    }

    @Override
    public float getDefaultMultiplier() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().defaultMultiplier;
    }

    @Override
    public void setDefaultMultiplier(float multiplier) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().defaultMultiplier = multiplier;
    }

    @Override
    public boolean isSolid() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().solid;
    }

    @Override
    public void setSolid(boolean solid) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().solid = solid;
    }

    @Override
    public boolean isKnockbackEnabled() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().knockbackEnabled;
    }

    @Override
    public void setKnockbackEnabled(boolean enabled) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().knockbackEnabled = enabled;
    }

    @Override
    public float getKnockbackStrength() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().knockbackStrength;
    }

    @Override
    public void setKnockbackStrength(float strength) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().knockbackStrength = strength;
    }

    @Override
    public boolean isAbsorbing() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().absorbing;
    }

    @Override
    public void setAbsorbing(boolean absorbing) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().absorbing = absorbing;
    }

    @Override
    public float getAbsorbRadius() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().absorbRadius;
    }

    @Override
    public void setAbsorbRadius(float radius) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().absorbRadius = radius;
    }

    public boolean isReflect() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().reflect;
    }

    public void setReflect(boolean reflect) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().reflect = reflect;
    }

    public float getReflectStrengthPct() {
        return ((EntityEnergyBarrier)this.entity).getBarrierData().reflectStrengthPct;
    }

    public void setReflectStrengthPct(float strengthPct) {
        ((EntityEnergyBarrier)this.entity).getBarrierData().setReflectStrengthPct(strengthPct);
    }

    @Override
    public int getBarrierType() {
        return this.entity instanceof EntityEnergyDome ? 0 : 1;
    }

    @Override
    public void syncClient() {
        if (((EntityEnergyBarrier)this.entity).field_70175_ag) {
            ((EntityEnergyBarrier)this.entity).sendClientSync();
        }
    }
}

