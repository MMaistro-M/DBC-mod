/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.Map;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.annotations.GwtIncompatible;
import org.spongepowered.libraries.com.google.common.collect.Range;

@Beta
@GwtIncompatible
public interface RangeMap<K extends Comparable, V> {
    @Nullable
    public V get(K var1);

    @Nullable
    public Map.Entry<Range<K>, V> getEntry(K var1);

    public Range<K> span();

    public void put(Range<K> var1, V var2);

    public void putAll(RangeMap<K, V> var1);

    public void clear();

    public void remove(Range<K> var1);

    public Map<Range<K>, V> asMapOfRanges();

    public Map<Range<K>, V> asDescendingMapOfRanges();

    public RangeMap<K, V> subRangeMap(Range<K> var1);

    public boolean equals(@Nullable Object var1);

    public int hashCode();

    public String toString();
}

