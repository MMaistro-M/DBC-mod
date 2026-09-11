/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.util.concurrent;

import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.util.concurrent.ListenableFuture;

@Beta
@GwtCompatible
public interface AsyncCallable<V> {
    public ListenableFuture<V> call() throws Exception;
}

