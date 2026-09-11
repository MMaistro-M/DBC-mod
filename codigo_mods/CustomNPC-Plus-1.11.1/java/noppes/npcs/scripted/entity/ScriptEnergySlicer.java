/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityEnergySlicer;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.api.entity.IEnergySlicer;
import noppes.npcs.scripted.entity.ScriptEnergyProjectile;

public class ScriptEnergySlicer<T extends EntityEnergySlicer>
extends ScriptEnergyProjectile<T>
implements IEnergySlicer {
    public ScriptEnergySlicer(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 18;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 18 || super.typeOf(type);
    }

    @Override
    public int getEnergyType() {
        return 4;
    }

    @Override
    public float getSliceWidth() {
        return ((EntityEnergySlicer)this.entity).getSliceWidth();
    }

    @Override
    public void setSliceWidth(float width) {
        ((EntityEnergySlicer)this.entity).setSliceWidth(width);
    }

    @Override
    public float getSliceThickness() {
        return ((EntityEnergySlicer)this.entity).getSliceThickness();
    }

    @Override
    public void setSliceThickness(float thickness) {
        ((EntityEnergySlicer)this.entity).setSliceThickness(thickness);
    }

    @Override
    protected void launchFromOwner(EntityLivingBase target) {
        ((EntityEnergySlicer)this.entity).startMoving(target);
    }
}

