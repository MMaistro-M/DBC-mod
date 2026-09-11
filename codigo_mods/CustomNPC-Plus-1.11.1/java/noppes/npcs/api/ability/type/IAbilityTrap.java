/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.type.IAbilityZone;

public interface IAbilityTrap
extends IAbilityZone {
    public float getTriggerRadius();

    public void setTriggerRadius(float var1);

    public int getArmTime();

    public void setArmTime(int var1);

    public int getMaxTriggers();

    public void setMaxTriggers(int var1);

    public int getTriggerCooldown();

    public void setTriggerCooldown(int var1);

    public float getDamage();

    public void setDamage(float var1);

    public float getDamageRadius();

    public void setDamageRadius(float var1);

    public float getKnockback();

    public void setKnockback(float var1);

    public boolean isVisible();

    public void setVisible(boolean var1);
}

