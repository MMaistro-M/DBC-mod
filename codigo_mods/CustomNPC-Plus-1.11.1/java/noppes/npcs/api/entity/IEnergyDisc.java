/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyProjectile;

public interface IEnergyDisc<T extends Entity>
extends IEnergyProjectile<T> {
    public float getDiscRadius();

    public void setDiscRadius(float var1);

    public float getDiscThickness();

    public void setDiscThickness(float var1);

    public boolean isVertical();

    public void setVertical(boolean var1);

    public boolean isBoomerang();

    public void setBoomerang(boolean var1);

    public int getBoomerangDelay();

    public void setBoomerangDelay(int var1);

    public boolean isReturning();
}

