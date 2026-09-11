/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete.weighter;

import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.CompletionWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ScoringContext;

public class InheritanceDepthWeigher
extends CompletionWeigher {
    public InheritanceDepthWeigher() {
        super("inheritanceDepth");
    }

    @Override
    public Comparable<?> weigh(AutocompleteItem item, ScoringContext context) {
        int depth = item.getInheritanceDepth();
        if (depth < 0) {
            return null;
        }
        return depth;
    }
}

