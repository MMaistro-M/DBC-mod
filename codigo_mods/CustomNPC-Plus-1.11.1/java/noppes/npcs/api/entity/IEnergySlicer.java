/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyProjectile;

public interface IEnergySlicer<T extends Entity>
extends IEnergyProjectile<T> {
    public float getSliceWidth();

    public void setSliceWidth(float var1);

    public float getSliceThickness();

    public void setSliceThickness(float var1);
}

