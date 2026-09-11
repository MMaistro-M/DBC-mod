/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityAbilityZone;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.INbt;
import noppes.npcs.api.entity.IEnergyZone;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptEntity;

public class ScriptEnergyZone<T extends EntityAbilityZone>
extends ScriptEntity<T>
implements IEnergyZone {
    public ScriptEnergyZone(T entity) {
        super(entity);
    }

    @Override
    public int getZoneType() {
        return ((EntityAbilityZone)this.entity).getZoneType().ordinal();
    }

    @Override
    public int getZoneShape() {
        return ((EntityAbilityZone)this.entity).getShape().ordinal();
    }

    @Override
    public void setZoneShape(int shape) {
        EntityAbilityZone.ZoneShape[] values = EntityAbilityZone.ZoneShape.values();
        if (shape >= 0 && shape < values.length) {
            ((EntityAbilityZone)this.entity).setShape(values[shape]);
        }
    }

    @Override
    public float getRadius() {
        return ((EntityAbilityZone)this.entity).getRadius();
    }

    @Override
    public void setRadius(float radius) {
        ((EntityAbilityZone)this.entity).setRadius(radius);
    }

    @Override
    public float getZoneHeight() {
        return ((EntityAbilityZone)this.entity).getZoneHeight();
    }

    @Override
    public void setZoneHeight(float height) {
        ((EntityAbilityZone)this.entity).setZoneHeight(height);
    }

    @Override
    public int getDuration() {
        return ((EntityAbilityZone)this.entity).getDurationTicks();
    }

    @Override
    public void setDuration(int ticks) {
        ((EntityAbilityZone)this.entity).setDurationTicks(ticks);
    }

    @Override
    public int getInnerColor() {
        return ((EntityAbilityZone)this.entity).getInnerColor();
    }

    @Override
    public void setInnerColor(int color) {
        ((EntityAbilityZone)this.entity).setInnerColor(color);
    }

    @Override
    public int getOuterColor() {
        return ((EntityAbilityZone)this.entity).getOuterColor();
    }

    @Override
    public void setOuterColor(int color) {
        ((EntityAbilityZone)this.entity).setOuterColor(color);
    }

    @Override
    public boolean isOuterColorEnabled() {
        return ((EntityAbilityZone)this.entity).isOuterColorEnabled();
    }

    @Override
    public void setOuterColorEnabled(boolean enabled) {
        ((EntityAbilityZone)this.entity).setOuterColorEnabled(enabled);
    }

    @Override
    public float getParticleDensity() {
        return ((EntityAbilityZone)this.entity).getParticleDensity();
    }

    @Override
    public void setParticleDensity(float density) {
        ((EntityAbilityZone)this.entity).setParticleDensity(density);
    }

    @Override
    public float getParticleScale() {
        return ((EntityAbilityZone)this.entity).getParticleScale();
    }

    @Override
    public void setParticleScale(float scale) {
        ((EntityAbilityZone)this.entity).setParticleScale(scale);
    }

    @Override
    public float getAnimSpeed() {
        return ((EntityAbilityZone)this.entity).getAnimSpeed();
    }

    @Override
    public void setAnimSpeed(float speed) {
        ((EntityAbilityZone)this.entity).setAnimSpeed(speed);
    }

    @Override
    public boolean isIgnoreIFrames() {
        return ((EntityAbilityZone)this.entity).isIgnoreIFrames();
    }

    @Override
    public void setIgnoreIFrames(boolean ignore) {
        ((EntityAbilityZone)this.entity).setIgnoreIFrames(ignore);
    }

    @Override
    public float getDamagePerSecond() {
        return ((EntityAbilityZone)this.entity).getDamagePerSecond();
    }

    @Override
    public void setDamagePerSecond(float dps) {
        ((EntityAbilityZone)this.entity).setDamagePerSecond(dps);
    }

    @Override
    public int getDamageInterval() {
        return ((EntityAbilityZone)this.entity).getDamageInterval();
    }

    @Override
    public void setDamageInterval(int ticks) {
        ((EntityAbilityZone)this.entity).setDamageInterval(ticks);
    }

    @Override
    public boolean isAffectsCaster() {
        return ((EntityAbilityZone)this.entity).isAffectsCaster();
    }

    @Override
    public void setAffectsCaster(boolean affects) {
        ((EntityAbilityZone)this.entity).setAffectsCaster(affects);
    }

    @Override
    public float getTriggerRadius() {
        return ((EntityAbilityZone)this.entity).getTriggerRadius();
    }

    @Override
    public void setTriggerRadius(float radius) {
        ((EntityAbilityZone)this.entity).setTriggerRadius(radius);
    }

    @Override
    public int getArmTime() {
        return ((EntityAbilityZone)this.entity).getArmTime();
    }

    @Override
    public void setArmTime(int ticks) {
        ((EntityAbilityZone)this.entity).setArmTime(ticks);
    }

    @Override
    public int getMaxTriggers() {
        return ((EntityAbilityZone)this.entity).getMaxTriggers();
    }

    @Override
    public void setMaxTriggers(int max) {
        ((EntityAbilityZone)this.entity).setMaxTriggers(max);
    }

    @Override
    public int getTriggerCooldown() {
        return ((EntityAbilityZone)this.entity).getTriggerCooldown();
    }

    @Override
    public void setTriggerCooldown(int ticks) {
        ((EntityAbilityZone)this.entity).setTriggerCooldown(ticks);
    }

    @Override
    public float getDamage() {
        return ((EntityAbilityZone)this.entity).getDamage();
    }

    @Override
    public void setDamage(float damage) {
        ((EntityAbilityZone)this.entity).setDamage(damage);
    }

    @Override
    public float getKnockback() {
        return ((EntityAbilityZone)this.entity).getKnockback();
    }

    @Override
    public void setKnockback(float knockback) {
        ((EntityAbilityZone)this.entity).setKnockback(knockback);
    }

    @Override
    public boolean isVisible() {
        return ((EntityAbilityZone)this.entity).isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        ((EntityAbilityZone)this.entity).setVisible(visible);
    }

    @Override
    public INbt getDamageData() {
        NBTTagCompound data = ((EntityAbilityZone)this.entity).getCustomDamageData();
        if (data == null) {
            data = new NBTTagCompound();
            ((EntityAbilityZone)this.entity).setCustomDamageData(data);
        }
        return NpcAPI.Instance().getINbt(data);
    }

    @Override
    public void setDamageData(INbt data) {
        if (data == null) {
            ((EntityAbilityZone)this.entity).setCustomDamageData(null);
        } else {
            ((EntityAbilityZone)this.entity).setCustomDamageData(data.getMCNBT());
        }
    }

    @Override
    public void spawn() {
        if (!((EntityAbilityZone)this.entity).field_70175_ag && ((EntityAbilityZone)this.entity).field_70170_p != null) {
            ((EntityAbilityZone)this.entity).field_70170_p.func_72838_d(this.entity);
        }
    }
}

