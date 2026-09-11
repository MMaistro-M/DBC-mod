/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityEnergySweeper;
import noppes.npcs.api.entity.IEnergySweeper;
import noppes.npcs.scripted.entity.ScriptEnergyAbility;

public class ScriptEnergySweeper<T extends EntityEnergySweeper>
extends ScriptEnergyAbility<T>
implements IEnergySweeper {
    public ScriptEnergySweeper(T entity) {
        super(entity);
    }

    @Override
    public float getBeamLength() {
        return ((EntityEnergySweeper)this.entity).getBeamLength();
    }

    @Override
    public void setBeamLength(float length) {
        ((EntityEnergySweeper)this.entity).setBeamLength(length);
    }

    @Override
    public float getBeamWidth() {
        return ((EntityEnergySweeper)this.entity).getBeamWidth();
    }

    @Override
    public void setBeamWidth(float width) {
        ((EntityEnergySweeper)this.entity).setBeamWidth(width);
    }

    @Override
    public float getBeamHeight() {
        return ((EntityEnergySweeper)this.entity).getBeamHeight();
    }

    @Override
    public void setBeamHeight(float height) {
        ((EntityEnergySweeper)this.entity).setBeamHeight(height);
    }

    @Override
    public float getSweepSpeed() {
        return ((EntityEnergySweeper)this.entity).getSweepSpeed();
    }

    @Override
    public void setSweepSpeed(float degreesPerTick) {
        ((EntityEnergySweeper)this.entity).setSweepSpeed(degreesPerTick);
    }

    @Override
    public int getNumberOfRotations() {
        return ((EntityEnergySweeper)this.entity).getNumberOfRotations();
    }

    @Override
    public void setNumberOfRotations(int rotations) {
        ((EntityEnergySweeper)this.entity).setNumberOfRotations(rotations);
    }

    @Override
    public boolean isLockOnTarget() {
        return ((EntityEnergySweeper)this.entity).isLockOnTarget();
    }

    @Override
    public void setLockOnTarget(boolean lock) {
        ((EntityEnergySweeper)this.entity).setLockOnTarget(lock);
    }

    @Override
    public float getDamage() {
        return ((EntityEnergySweeper)this.entity).getDamage();
    }

    @Override
    public void setDamage(float damage) {
        ((EntityEnergySweeper)this.entity).setDamage(damage);
    }

    @Override
    public int getDamageInterval() {
        return ((EntityEnergySweeper)this.entity).getDamageInterval();
    }

    @Override
    public void setDamageInterval(int ticks) {
        ((EntityEnergySweeper)this.entity).setDamageInterval(ticks);
    }

    @Override
    public boolean isPiercing() {
        return ((EntityEnergySweeper)this.entity).isPiercing();
    }

    @Override
    public void setPiercing(boolean piercing) {
        ((EntityEnergySweeper)this.entity).setPiercing(piercing);
    }

    @Override
    public void spawn() {
        if (!((EntityEnergySweeper)this.entity).field_70175_ag && ((EntityEnergySweeper)this.entity).field_70170_p != null) {
            ((EntityEnergySweeper)this.entity).field_70170_p.func_72838_d(this.entity);
        }
    }
}

