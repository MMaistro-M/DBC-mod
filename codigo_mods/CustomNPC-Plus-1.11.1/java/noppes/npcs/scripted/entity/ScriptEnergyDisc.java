/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityAbilityDisc;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.api.entity.IEnergyDisc;
import noppes.npcs.scripted.entity.ScriptEnergyProjectile;

public class ScriptEnergyDisc<T extends EntityAbilityDisc>
extends ScriptEnergyProjectile<T>
implements IEnergyDisc {
    public ScriptEnergyDisc(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 16;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 16 || super.typeOf(type);
    }

    @Override
    public int getEnergyType() {
        return 2;
    }

    @Override
    public float getDiscRadius() {
        return ((EntityAbilityDisc)this.entity).getDiscRadius();
    }

    @Override
    public void setDiscRadius(float radius) {
        ((EntityAbilityDisc)this.entity).setDiscRadius(radius);
    }

    @Override
    public float getDiscThickness() {
        return ((EntityAbilityDisc)this.entity).getDiscThickness();
    }

    @Override
    public void setDiscThickness(float thickness) {
        ((EntityAbilityDisc)this.entity).setDiscThickness(thickness);
    }

    @Override
    public boolean isVertical() {
        return ((EntityAbilityDisc)this.entity).isVertical();
    }

    @Override
    public void setVertical(boolean vertical) {
        ((EntityAbilityDisc)this.entity).setVertical(vertical);
    }

    @Override
    public boolean isBoomerang() {
        return ((EntityAbilityDisc)this.entity).isBoomerang();
    }

    @Override
    public void setBoomerang(boolean boomerang) {
        ((EntityAbilityDisc)this.entity).setBoomerang(boomerang);
    }

    @Override
    public int getBoomerangDelay() {
        return ((EntityAbilityDisc)this.entity).getBoomerangDelay();
    }

    @Override
    public void setBoomerangDelay(int ticks) {
        ((EntityAbilityDisc)this.entity).setBoomerangDelay(ticks);
    }

    @Override
    public boolean isReturning() {
        return ((EntityAbilityDisc)this.entity).isReturning();
    }

    @Override
    protected void launchFromOwner(EntityLivingBase target) {
        ((EntityAbilityDisc)this.entity).startMoving(target);
    }
}

