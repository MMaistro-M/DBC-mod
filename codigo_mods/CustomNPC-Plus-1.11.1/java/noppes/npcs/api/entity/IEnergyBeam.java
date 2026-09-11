/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyProjectile;

public interface IEnergyBeam<T extends Entity>
extends IEnergyProjectile<T> {
    public float getBeamWidth();

    public void setBeamWidth(float var1);

    public float getHeadSize();

    public void setHeadSize(float var1);

    public boolean isAttachedToOwner();

    public void setAttachedToOwner(boolean var1);

    public boolean shouldRenderTailOrb();
}

