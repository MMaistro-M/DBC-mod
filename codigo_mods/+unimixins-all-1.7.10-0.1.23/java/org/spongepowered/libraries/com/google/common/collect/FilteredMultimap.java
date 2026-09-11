/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.Map;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.base.Predicate;
import org.spongepowered.libraries.com.google.common.collect.Multimap;

@GwtCompatible
interface FilteredMultimap<K, V>
extends Multimap<K, V> {
    public Multimap<K, V> unfiltered();

    public Predicate<? super Map.Entry<K, V>> entryPredicate();
}

