/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability;

public interface IAbilityAction {
    public String getName();

    public boolean isEnabled();

    public int getWeight();

    public int getCooldownTicks();

    public float getMinRange();

    public float getMaxRange();

    public boolean isChain();
}

