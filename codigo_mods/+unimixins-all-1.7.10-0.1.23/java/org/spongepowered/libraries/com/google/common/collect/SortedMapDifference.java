/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.SortedMap;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.collect.MapDifference;

@GwtCompatible
public interface SortedMapDifference<K, V>
extends MapDifference<K, V> {
    @Override
    public SortedMap<K, V> entriesOnlyOnLeft();

    @Override
    public SortedMap<K, V> entriesOnlyOnRight();

    @Override
    public SortedMap<K, V> entriesInCommon();

    @Override
    public SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering();
}

