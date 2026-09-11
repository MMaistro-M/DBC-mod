/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityShockwave
extends IAbility {
    public float getPushRadius();

    public void setPushRadius(float var1);

    public float getPushStrength();

    public void setPushStrength(float var1);

    public float getDamage();

    public void setDamage(float var1);

    public boolean isAoe();

    public void setAoe(boolean var1);
}

