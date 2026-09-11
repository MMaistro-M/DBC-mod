/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.autocomplete.weighter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.CompletionWeigher;
import noppes.npcs.client.gui.util.script.autocomplete.weighter.ScoringContext;

public class WeigherChain {
    private final List<CompletionWeigher> weighers;

    public WeigherChain(List<CompletionWeigher> weighers) {
        this.weighers = Collections.unmodifiableList(new ArrayList<CompletionWeigher>(weighers));
    }

    public Comparator<AutocompleteItem> buildComparator(ScoringContext context) {
        IdentityHashMap cache = new IdentityHashMap();
        int chainLength = this.weighers.size();
        return (a, b) -> {
            Comparable[] weightsA = cache.computeIfAbsent(a, k -> this.computeWeights((AutocompleteItem)k, context));
            Comparable[] weightsB = cache.computeIfAbsent(b, k -> this.computeWeights((AutocompleteItem)k, context));
            for (int i = 0; i < chainLength; ++i) {
                Comparable wA = weightsA[i];
                Comparable wB = weightsB[i];
                if (wA == null && wB == null) continue;
                if (wA == null) {
                    return 1;
                }
                if (wB == null) {
                    return -1;
                }
                int cmp = wA.compareTo(wB);
                if (cmp == 0) continue;
                return this.weighers.get(i).isNegated() ? -cmp : cmp;
            }
            return 0;
        };
    }

    private Comparable<?>[] computeWeights(AutocompleteItem item, ScoringContext context) {
        Comparable[] weights = new Comparable[this.weighers.size()];
        for (int i = 0; i < this.weighers.size(); ++i) {
            weights[i] = this.weighers.get(i).weigh(item, context);
        }
        return weights;
    }

    public Map<String, Comparable<?>> debugWeights(AutocompleteItem item, ScoringContext context) {
        LinkedHashMap result = new LinkedHashMap();
        for (CompletionWeigher weigher : this.weighers) {
            result.put(weigher.getId(), weigher.weigh(item, context));
        }
        return result;
    }

    public List<CompletionWeigher> getWeighers() {
        return this.weighers;
    }
}

