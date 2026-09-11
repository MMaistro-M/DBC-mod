/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.Map;
import java.util.Set;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.base.Predicate;
import org.spongepowered.libraries.com.google.common.collect.FilteredEntryMultimap;
import org.spongepowered.libraries.com.google.common.collect.FilteredSetMultimap;
import org.spongepowered.libraries.com.google.common.collect.SetMultimap;
import org.spongepowered.libraries.com.google.common.collect.Sets;

@GwtCompatible
final class FilteredEntrySetMultimap<K, V>
extends FilteredEntryMultimap<K, V>
implements FilteredSetMultimap<K, V> {
    FilteredEntrySetMultimap(SetMultimap<K, V> unfiltered, Predicate<? super Map.Entry<K, V>> predicate) {
        super(unfiltered, predicate);
    }

    @Override
    public SetMultimap<K, V> unfiltered() {
        return (SetMultimap)this.unfiltered;
    }

    @Override
    public Set<V> get(K key) {
        return (Set)super.get(key);
    }

    @Override
    public Set<V> removeAll(Object key) {
        return (Set)super.removeAll(key);
    }

    @Override
    public Set<V> replaceValues(K key, Iterable<? extends V> values) {
        return (Set)super.replaceValues(key, values);
    }

    @Override
    Set<Map.Entry<K, V>> createEntries() {
        return Sets.filter(this.unfiltered().entries(), this.entryPredicate());
    }

    @Override
    public Set<Map.Entry<K, V>> entries() {
        return (Set)super.entries();
    }
}

