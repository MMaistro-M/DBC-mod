/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete.weighter;

import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.UsageTracker;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.CompletionWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ScoringContext;

public class UsageFrequencyWeigher
extends CompletionWeigher {
    public UsageFrequencyWeigher() {
        super("usageFrequency", true);
    }

    @Override
    public Comparable<?> weigh(AutocompleteItem item, ScoringContext context) {
        if (context.usageTracker == null) {
            return 0;
        }
        int usageCount = context.usageTracker.getUsageCount(item, context.ownerFullName);
        return UsageTracker.calculateUsageBoost(usageCount);
    }
}

