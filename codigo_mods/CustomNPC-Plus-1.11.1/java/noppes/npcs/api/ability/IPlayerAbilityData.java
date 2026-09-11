/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability;

import noppes.npcs.api.ability.IAbility;

public interface IPlayerAbilityData {
    public String[] getUnlockedAbilities();

    public void unlockAbility(String var1);

    public void lockAbility(String var1);

    public boolean hasUnlockedAbility(String var1);

    public int getSelectedIndex();

    public void setSelectedIndex(int var1);

    public String getSelectedAbilityKey();

    public void selectNext();

    public void selectPrevious();

    public boolean isExecutingAbility();

    public IAbility getCurrentAbility();

    public void interruptCurrentAbility();

    public void completeCurrentAbility();

    public boolean isOnCooldown();

    public boolean isOnCooldown(String var1);

    public void resetCooldown();

    public void resetCooldown(String var1);

    public void resetAllCooldowns();

    public boolean activateAbility();

    public boolean activateAbility(String var1);

    public int toggleAbility(String var1);

    public int getToggleState(String var1);

    public void setToggleState(String var1, int var2);

    public boolean isAbilityToggled(String var1);
}

