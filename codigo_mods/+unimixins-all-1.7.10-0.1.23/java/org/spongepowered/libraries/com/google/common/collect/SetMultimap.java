/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.collect.Multimap;
import org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
public interface SetMultimap<K, V>
extends Multimap<K, V> {
    @Override
    public Set<V> get(@Nullable K var1);

    @Override
    @CanIgnoreReturnValue
    public Set<V> removeAll(@Nullable Object var1);

    @Override
    @CanIgnoreReturnValue
    public Set<V> replaceValues(K var1, Iterable<? extends V> var2);

    @Override
    public Set<Map.Entry<K, V>> entries();

    @Override
    public Map<K, Collection<V>> asMap();

    @Override
    public boolean equals(@Nullable Object var1);
}

