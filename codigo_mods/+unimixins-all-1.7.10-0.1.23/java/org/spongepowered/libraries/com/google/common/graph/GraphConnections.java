/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue
 */
package org.spongepowered.libraries.com.google.common.graph;

import java.util.Set;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue;

interface GraphConnections<N, V> {
    public Set<N> adjacentNodes();

    public Set<N> predecessors();

    public Set<N> successors();

    @Nullable
    public V value(Object var1);

    public void removePredecessor(Object var1);

    @CanIgnoreReturnValue
    public V removeSuccessor(Object var1);

    public void addPredecessor(N var1, V var2);

    @CanIgnoreReturnValue
    public V addSuccessor(N var1, V var2);
}

