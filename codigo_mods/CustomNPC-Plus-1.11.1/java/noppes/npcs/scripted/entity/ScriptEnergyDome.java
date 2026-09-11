/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityEnergyDome;
import noppes.npcs.api.entity.IEnergyDome;
import noppes.npcs.scripted.entity.ScriptEnergyBarrier;

public class ScriptEnergyDome<T extends EntityEnergyDome>
extends ScriptEnergyBarrier<T>
implements IEnergyDome {
    public ScriptEnergyDome(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 20;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 20 || super.typeOf(type);
    }

    @Override
    public int getBarrierType() {
        return 0;
    }

    @Override
    public float getDomeRadius() {
        return ((EntityEnergyDome)this.entity).getDomeRadius();
    }

    @Override
    public void setDomeRadius(float radius) {
        ((EntityEnergyDome)this.entity).setDomeRadius(radius);
    }
}

