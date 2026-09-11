/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityHandler {
    public void registerType(String var1, Class<? extends IAbility> var2);

    public boolean hasType(String var1);

    public String[] getTypes();

    public IAbility create(String var1);

    public void registerAbility(String var1, IAbility var2);

    public boolean hasAbility(String var1);
}

