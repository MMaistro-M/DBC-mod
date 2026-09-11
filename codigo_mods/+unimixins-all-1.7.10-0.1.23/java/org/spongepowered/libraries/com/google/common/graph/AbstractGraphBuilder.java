/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.graph;

import org.spongepowered.libraries.com.google.common.base.Optional;
import org.spongepowered.libraries.com.google.common.graph.ElementOrder;

abstract class AbstractGraphBuilder<N> {
    final boolean directed;
    boolean allowsSelfLoops = false;
    ElementOrder<N> nodeOrder = ElementOrder.insertion();
    Optional<Integer> expectedNodeCount = Optional.absent();

    AbstractGraphBuilder(boolean directed) {
        this.directed = directed;
    }
}

