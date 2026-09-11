/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.type.IAbilityDefend;

public interface IAbilityCounter
extends IAbilityDefend {
    public int getCounterType();

    public void setCounterType(int var1);

    public float getCounterValue();

    public void setCounterValue(float var1);

    public int getCounterAnimationId();

    public void setCounterAnimationId(int var1);
}

