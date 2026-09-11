/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete.weighter;

import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.CompletionWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ScoringContext;

public class AlphabeticalWeigher
extends CompletionWeigher {
    public AlphabeticalWeigher() {
        super("alphabetical");
    }

    @Override
    public Comparable<?> weigh(AutocompleteItem item, ScoringContext context) {
        return item.getName().toLowerCase();
    }
}

