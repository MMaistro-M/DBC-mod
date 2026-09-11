/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.cache;

import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.cache.RemovalNotification;

@GwtCompatible
public interface RemovalListener<K, V> {
    public void onRemoval(RemovalNotification<K, V> var1);
}

