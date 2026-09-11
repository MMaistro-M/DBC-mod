/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.type.IAbilityDefend;

public interface IAbilityGuard
extends IAbilityDefend {
    public float getDamageReduction();

    public void setDamageReduction(float var1);
}

