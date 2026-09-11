/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CompatibleWith
 */
package org.spongepowered.libraries.com.google.common.graph;

import java.util.Set;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.graph.ElementOrder;
import org.spongepowered.libraries.com.google.common.graph.EndpointPair;
import org.spongepowered.libraries.com.google.errorprone.annotations.CompatibleWith;

@Beta
public interface Graph<N> {
    public Set<N> nodes();

    public Set<EndpointPair<N>> edges();

    public boolean isDirected();

    public boolean allowsSelfLoops();

    public ElementOrder<N> nodeOrder();

    public Set<N> adjacentNodes(@CompatibleWith(value="N") Object var1);

    public Set<N> predecessors(@CompatibleWith(value="N") Object var1);

    public Set<N> successors(@CompatibleWith(value="N") Object var1);

    public int degree(@CompatibleWith(value="N") Object var1);

    public int inDegree(@CompatibleWith(value="N") Object var1);

    public int outDegree(@CompatibleWith(value="N") Object var1);

    public boolean equals(@Nullable Object var1);

    public int hashCode();
}

