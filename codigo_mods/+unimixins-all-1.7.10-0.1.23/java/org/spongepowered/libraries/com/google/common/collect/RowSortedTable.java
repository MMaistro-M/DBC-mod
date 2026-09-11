/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.collect.Table;

@GwtCompatible
public interface RowSortedTable<R, C, V>
extends Table<R, C, V> {
    @Override
    public SortedSet<R> rowKeySet();

    @Override
    public SortedMap<R, Map<C, V>> rowMap();
}

