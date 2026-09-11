/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.ability.IChainedAbility;

public interface IAbilityHandler {
    public String[] getTypes();

    public boolean hasType(String var1);

    public String[] getAbilityNameArray();

    public boolean hasAbilityName(String var1);

    public String[] getCustomAbilityNameArray();

    public boolean hasCustomAbilityName(String var1);

    public boolean deleteCustomAbilityByName(String var1);

    public String[] getChainedAbilityNames();

    public boolean hasChainedAbilityName(String var1);

    public boolean deleteChainedAbilityByName(String var1);

    public IChainedAbility getChainedAbility(String var1);

    public boolean saveChainedAbility(IChainedAbility var1);
}

