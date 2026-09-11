/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;

@GwtCompatible
public interface ListenableFuture<V>
extends Future<V> {
    public void addListener(Runnable var1, Executor var2);
}

