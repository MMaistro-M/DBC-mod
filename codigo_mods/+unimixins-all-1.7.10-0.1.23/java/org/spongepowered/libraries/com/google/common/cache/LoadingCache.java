/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.cache;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.base.Function;
import org.spongepowered.libraries.com.google.common.cache.Cache;
import org.spongepowered.libraries.com.google.common.collect.ImmutableMap;

@GwtCompatible
public interface LoadingCache<K, V>
extends Cache<K, V>,
Function<K, V> {
    public V get(K var1) throws ExecutionException;

    public V getUnchecked(K var1);

    public ImmutableMap<K, V> getAll(Iterable<? extends K> var1) throws ExecutionException;

    @Override
    @Deprecated
    public V apply(K var1);

    public void refresh(K var1);

    @Override
    public ConcurrentMap<K, V> asMap();
}

