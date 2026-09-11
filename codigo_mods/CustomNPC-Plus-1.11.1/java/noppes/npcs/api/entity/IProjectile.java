/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.entity;

import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.item.IItemStack;

public interface IProjectile {
    public IItemStack getItem();

    public void setItem(IItemStack var1);

    public boolean getHasGravity();

    public void setHasGravity(boolean var1);

    public int getAccuracy();

    public void setAccuracy(int var1);

    public void setHeading(IEntity var1);

    public void setHeading(double var1, double var3, double var5);

    public void setHeading(float var1, float var2);

    public IEntity getThrower();

    public void enableEvents();
}

