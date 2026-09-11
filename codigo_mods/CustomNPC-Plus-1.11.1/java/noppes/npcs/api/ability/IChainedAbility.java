/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability;

import noppes.npcs.api.ability.IAbilityAction;

public interface IChainedAbility
extends IAbilityAction {
    public boolean isWindUpAll();

    public int getEntryCount();

    public String getEntryReference(int var1);

    public int getEntryDelay(int var1);

    public boolean isEntryInline(int var1);
}

