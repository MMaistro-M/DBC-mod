/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CompatibleWith
 */
package org.spongepowered.libraries.com.google.common.graph;

import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.graph.Graph;
import org.spongepowered.libraries.com.google.errorprone.annotations.CompatibleWith;

@Beta
public interface ValueGraph<N, V>
extends Graph<N> {
    public V edgeValue(@CompatibleWith(value="N") Object var1, @CompatibleWith(value="N") Object var2);

    public V edgeValueOrDefault(@CompatibleWith(value="N") Object var1, @CompatibleWith(value="N") Object var2, @Nullable V var3);

    @Override
    public boolean equals(@Nullable Object var1);

    @Override
    public int hashCode();
}

