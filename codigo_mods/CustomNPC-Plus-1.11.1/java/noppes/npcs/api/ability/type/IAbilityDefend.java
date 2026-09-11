/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityDefend
extends IAbility {
    public int getDurationTicks();

    public void setDurationTicks(int var1);

    public int getMaxHitAmount();

    public void setMaxHitAmount(int var1);

    public boolean isDefending();
}

