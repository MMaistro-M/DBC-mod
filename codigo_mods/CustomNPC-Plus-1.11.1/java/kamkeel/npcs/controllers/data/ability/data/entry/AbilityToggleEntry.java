/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability.data.entry;

import kamkeel.npcs.controllers.data.ability.Ability;

public class AbilityToggleEntry {
    private final Ability ability;
    private int tickCount;
    private int state;

    public AbilityToggleEntry(Ability ability, int state) {
        this.ability = ability;
        this.tickCount = 0;
        this.state = Math.max(1, state);
    }

    public Ability getAbility() {
        return this.ability;
    }

    public int getTickCount() {
        return this.tickCount;
    }

    public void incrementTick() {
        ++this.tickCount;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = Math.max(1, state);
    }
}

