/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability;

import noppes.npcs.api.ability.IAbility;

public interface IDataAbilities {
    public boolean isEnabled();

    public void setEnabled(boolean var1);

    public IAbility[] getAbilities();

    public void addAbility(IAbility var1);

    public void addAbilityReference(String var1);

    public void removeAbility(String var1);

    public IAbility getAbility(String var1);

    public boolean hasAbility(String var1);

    public boolean isAbilityReference(String var1);

    public boolean convertToInline(String var1);

    public void clearAbilities();

    public IAbility getCurrentAbility();

    public boolean isExecutingAbility();

    public void interruptCurrentAbility();

    public void completeCurrentAbility();

    public int getGlobalCooldown();

    public void setGlobalCooldown(int var1);

    public void resetCooldowns();

    public boolean forceStartAbility(String var1);

    public boolean forceStartAbility(String var1, Object var2);

    public boolean executeAbility(String var1);

    public boolean executeAbility(String var1, Object var2);

    public IAbility createAbility(String var1);

    public IAbility getSourceAbility(String var1);

    public IAbility getSourceCurrentAbility();
}

