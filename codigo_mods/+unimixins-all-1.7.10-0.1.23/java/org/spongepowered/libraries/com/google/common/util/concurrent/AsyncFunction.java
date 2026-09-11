/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package org.spongepowered.libraries.com.google.common.util.concurrent;

import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.util.concurrent.ListenableFuture;

@FunctionalInterface
@GwtCompatible
public interface AsyncFunction<I, O> {
    public ListenableFuture<O> apply(@Nullable I var1) throws Exception;
}

