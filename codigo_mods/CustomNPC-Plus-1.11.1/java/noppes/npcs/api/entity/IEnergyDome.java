/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyBarrier;

public interface IEnergyDome<T extends Entity>
extends IEnergyBarrier<T> {
    public float getDomeRadius();

    public void setDomeRadius(float var1);
}

