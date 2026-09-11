/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.concurrent.ConcurrentMap;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.collect.ForwardingMap;
import org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
public abstract class ForwardingConcurrentMap<K, V>
extends ForwardingMap<K, V>
implements ConcurrentMap<K, V> {
    protected ForwardingConcurrentMap() {
    }

    @Override
    protected abstract ConcurrentMap<K, V> delegate();

    @Override
    @CanIgnoreReturnValue
    public V putIfAbsent(K key, V value) {
        return this.delegate().putIfAbsent(key, value);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean remove(Object key, Object value) {
        return this.delegate().remove(key, value);
    }

    @Override
    @CanIgnoreReturnValue
    public V replace(K key, V value) {
        return this.delegate().replace(key, value);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean replace(K key, V oldValue, V newValue) {
        return this.delegate().replace(key, oldValue, newValue);
    }
}

