/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.entity.IEnergyAbility;

public interface IEnergyExplosion<T extends Entity>
extends IEnergyAbility<T> {
    public float getRadius();

    public void setRadius(float var1);

    public int getDuration();

    public float getDamage();

    public void setDamage(float var1);

    public float getKnockback();

    public void setKnockback(float var1);

    public float getKnockbackUp();

    public void setKnockbackUp(float var1);

    public float getDamageFalloff();

    public void setDamageFalloff(float var1);

    public void spawn();
}

