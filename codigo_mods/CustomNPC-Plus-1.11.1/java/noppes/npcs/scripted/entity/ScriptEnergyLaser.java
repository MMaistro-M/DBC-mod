/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityAbilityLaser;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.api.entity.IEnergyLaser;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.scripted.entity.ScriptEnergyProjectile;

public class ScriptEnergyLaser<T extends EntityAbilityLaser>
extends ScriptEnergyProjectile<T>
implements IEnergyLaser {
    public ScriptEnergyLaser(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 17;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 17 || super.typeOf(type);
    }

    @Override
    public int getEnergyType() {
        return 3;
    }

    @Override
    public float getLaserWidth() {
        return ((EntityAbilityLaser)this.entity).getLaserWidth();
    }

    @Override
    public void setLaserWidth(float width) {
        ((EntityAbilityLaser)this.entity).setLaserWidth(width);
    }

    @Override
    public float getExpansionSpeed() {
        return ((EntityAbilityLaser)this.entity).getExpansionSpeed();
    }

    @Override
    public void setExpansionSpeed(float speed) {
        ((EntityAbilityLaser)this.entity).setExpansionSpeed(speed);
    }

    @Override
    public float getMaxLength() {
        return ((EntityAbilityLaser)this.entity).getMaxLength();
    }

    @Override
    public void setMaxLength(float maxLength) {
        ((EntityAbilityLaser)this.entity).setMaxLength(maxLength);
    }

    @Override
    public float getCurrentLength() {
        return ((EntityAbilityLaser)this.entity).getCurrentLength();
    }

    @Override
    public boolean isFullyExtended() {
        return ((EntityAbilityLaser)this.entity).isFullyExtended();
    }

    @Override
    public double getDirX() {
        return ((EntityAbilityLaser)this.entity).getDirX();
    }

    @Override
    public double getDirY() {
        return ((EntityAbilityLaser)this.entity).getDirY();
    }

    @Override
    public double getDirZ() {
        return ((EntityAbilityLaser)this.entity).getDirZ();
    }

    @Override
    public void setDirection(double x, double y, double z) {
        ((EntityAbilityLaser)this.entity).setDirection(x, y, z);
    }

    @Override
    public double getEndX() {
        return ((EntityAbilityLaser)this.entity).getEndX();
    }

    @Override
    public double getEndY() {
        return ((EntityAbilityLaser)this.entity).getEndY();
    }

    @Override
    public double getEndZ() {
        return ((EntityAbilityLaser)this.entity).getEndZ();
    }

    @Override
    protected void launchFromOwner(EntityLivingBase target) {
        ((EntityAbilityLaser)this.entity).startMoving(target);
    }

    @Override
    public void fireAt(IEntity target) {
        ((EntityAbilityLaser)this.entity).setTrackOwnerOrigin(false);
        super.fireAt(target);
    }

    @Override
    public void fireAt(double x, double y, double z) {
        ((EntityAbilityLaser)this.entity).setTrackOwnerOrigin(false);
        double dx = x - ((EntityAbilityLaser)this.entity).field_70165_t;
        double dy = y - ((EntityAbilityLaser)this.entity).field_70163_u;
        double dz = z - ((EntityAbilityLaser)this.entity).field_70161_v;
        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (len > 0.0) {
            ((EntityAbilityLaser)this.entity).setDirection(dx / len, dy / len, dz / len);
        }
        this.ensureSpawned();
    }

    @Override
    public void fireDirection(float yaw, float pitch) {
        ((EntityAbilityLaser)this.entity).setTrackOwnerOrigin(false);
        float yawRad = (float)Math.toRadians(yaw);
        float pitchRad = (float)Math.toRadians(pitch);
        ((EntityAbilityLaser)this.entity).setDirection(-Math.sin(yawRad) * Math.cos(pitchRad), -Math.sin(pitchRad), Math.cos(yawRad) * Math.cos(pitchRad));
        this.ensureSpawned();
    }
}

