/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete.weighter;

import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.CompletionWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ScoringContext;

public class ContextFitWeigher
extends CompletionWeigher {
    public ContextFitWeigher() {
        super("contextFit");
    }

    @Override
    public Comparable<?> weigh(AutocompleteItem item, ScoringContext context) {
        if (item.isInheritedObjectMethod()) {
            return ContextFit.OBJECT_METHOD;
        }
        if (context.isMemberAccess && !context.isStaticContext && item.isStatic()) {
            return ContextFit.WRONG_STATIC_CONTEXT;
        }
        return ContextFit.NATURAL;
    }

    public static enum ContextFit {
        NATURAL,
        WRONG_STATIC_CONTEXT,
        OBJECT_METHOD;

    }
}

