/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete.weighter;

import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.CompletionWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ScoringContext;

public class MatchQualityWeigher
extends CompletionWeigher {
    public MatchQualityWeigher() {
        super("matchQuality", true);
    }

    @Override
    public Comparable<?> weigh(AutocompleteItem item, ScoringContext context) {
        return item.getMatchScore();
    }
}

