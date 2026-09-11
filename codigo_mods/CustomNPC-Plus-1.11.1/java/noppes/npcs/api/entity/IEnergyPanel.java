/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyBarrier;

public interface IEnergyPanel<T extends Entity>
extends IEnergyBarrier<T> {
    public float getPanelWidth();

    public void setPanelWidth(float var1);

    public float getPanelHeight();

    public void setPanelHeight(float var1);

    public float getPanelYaw();

    public void setPanelYaw(float var1);

    public int getPanelMode();

    public void setPanelMode(int var1);

    public boolean isLaunched();

    public void spawn();
}

