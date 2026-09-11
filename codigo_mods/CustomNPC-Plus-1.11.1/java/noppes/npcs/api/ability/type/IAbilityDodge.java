/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.type.IAbilityDefend;

public interface IAbilityDodge
extends IAbilityDefend {
    public int getDodgeAnimation1Id();

    public void setDodgeAnimation1Id(int var1);

    public int getDodgeAnimation2Id();

    public void setDodgeAnimation2Id(int var1);

    public int getDodgeAnimation3Id();

    public void setDodgeAnimation3Id(int var1);
}

