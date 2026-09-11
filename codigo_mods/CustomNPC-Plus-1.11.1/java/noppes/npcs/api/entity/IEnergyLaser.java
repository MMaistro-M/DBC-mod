/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyProjectile;

public interface IEnergyLaser<T extends Entity>
extends IEnergyProjectile<T> {
    public float getLaserWidth();

    public void setLaserWidth(float var1);

    public float getExpansionSpeed();

    public void setExpansionSpeed(float var1);

    public float getMaxLength();

    public void setMaxLength(float var1);

    public float getCurrentLength();

    public boolean isFullyExtended();

    public double getDirX();

    public double getDirY();

    public double getDirZ();

    public void setDirection(double var1, double var3, double var5);

    public double getEndX();

    public double getEndY();

    public double getEndZ();
}

