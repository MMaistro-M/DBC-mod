/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.collect;

import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.collect.FilteredMultimap;
import org.spongepowered.libraries.com.google.common.collect.SetMultimap;

@GwtCompatible
interface FilteredSetMultimap<K, V>
extends FilteredMultimap<K, V>,
SetMultimap<K, V> {
    @Override
    public SetMultimap<K, V> unfiltered();
}

