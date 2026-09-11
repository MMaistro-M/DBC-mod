/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.util.concurrent;

import java.util.concurrent.ScheduledFuture;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.annotations.GwtIncompatible;
import org.spongepowered.libraries.com.google.common.util.concurrent.ListenableFuture;

@Beta
@GwtIncompatible
public interface ListenableScheduledFuture<V>
extends ScheduledFuture<V>,
ListenableFuture<V> {
}

