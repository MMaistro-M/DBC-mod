/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete.weighter;

import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ScoringContext;

public abstract class CompletionWeigher {
    private final String id;
    private final boolean negated;

    protected CompletionWeigher(String id, boolean negated) {
        this.id = id;
        this.negated = negated;
    }

    protected CompletionWeigher(String id) {
        this(id, false);
    }

    public abstract Comparable<?> weigh(AutocompleteItem var1, ScoringContext var2);

    public String getId() {
        return this.id;
    }

    public boolean isNegated() {
        return this.negated;
    }

    public String toString() {
        return this.id;
    }
}

