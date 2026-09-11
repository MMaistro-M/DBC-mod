/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.type.IAbilityEnergyProjectile;

public interface IAbilityLaser
extends IAbilityEnergyProjectile {
    public float getLaserWidth();

    public void setLaserWidth(float var1);

    public float getExpansionSpeed();

    public void setExpansionSpeed(float var1);

    public float getMaxLength();

    public void setMaxLength(float var1);
}

