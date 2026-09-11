/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityEffect
extends IAbility {
    public int getDurationTicks();

    public void setDurationTicks(int var1);

    public float getHealAmount();

    public void setHealAmount(float var1);

    public float getHealPercent();

    public void setHealPercent(float var1);

    public boolean isIncludeSelf();

    public void setIncludeSelf(boolean var1);

    public float getRadius();

    public void setRadius(float var1);

    public boolean isInstantHeal();

    public void setInstantHeal(boolean var1);

    public int getTargetFilterType();

    public void setTargetFilterType(int var1);

    public int getCustomEffectCount();

    public int getEffectActionCount();
}

