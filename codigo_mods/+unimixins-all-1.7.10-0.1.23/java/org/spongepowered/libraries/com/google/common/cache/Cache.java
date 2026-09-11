/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package org.spongepowered.libraries.com.google.common.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.cache.CacheStats;
import org.spongepowered.libraries.com.google.common.collect.ImmutableMap;

@GwtCompatible
public interface Cache<K, V> {
    @Nullable
    public V getIfPresent(Object var1);

    public V get(K var1, Callable<? extends V> var2) throws ExecutionException;

    public ImmutableMap<K, V> getAllPresent(Iterable<?> var1);

    public void put(K var1, V var2);

    public void putAll(Map<? extends K, ? extends V> var1);

    public void invalidate(Object var1);

    public void invalidateAll(Iterable<?> var1);

    public void invalidateAll();

    public long size();

    public CacheStats stats();

    public ConcurrentMap<K, V> asMap();

    public void cleanUp();
}

