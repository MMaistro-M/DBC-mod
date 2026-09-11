/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability;

import java.util.function.Consumer;
import kamkeel.npcs.controllers.data.ability.Ability;

public class AbilityVariant {
    private final String displayKey;
    private final String group;
    private final Consumer<Ability> configurator;

    public AbilityVariant(String displayKey, Consumer<Ability> configurator) {
        this(displayKey, null, configurator);
    }

    public AbilityVariant(String displayKey, String group, Consumer<Ability> configurator) {
        this.displayKey = displayKey;
        this.group = group;
        this.configurator = configurator;
    }

    public String getDisplayKey() {
        return this.displayKey;
    }

    public String getGroup() {
        return this.group;
    }

    public void apply(Ability ability) {
        if (this.configurator != null) {
            this.configurator.accept(ability);
        }
    }
}

