/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.graph;

import org.spongepowered.libraries.com.google.common.graph.AbstractGraphBuilder;
import org.spongepowered.libraries.com.google.common.graph.ConfigurableMutableValueGraph;
import org.spongepowered.libraries.com.google.common.graph.ForwardingGraph;
import org.spongepowered.libraries.com.google.common.graph.Graph;
import org.spongepowered.libraries.com.google.common.graph.GraphConstants;
import org.spongepowered.libraries.com.google.common.graph.MutableGraph;
import org.spongepowered.libraries.com.google.common.graph.MutableValueGraph;

final class ConfigurableMutableGraph<N>
extends ForwardingGraph<N>
implements MutableGraph<N> {
    private final MutableValueGraph<N, GraphConstants.Presence> backingValueGraph;

    ConfigurableMutableGraph(AbstractGraphBuilder<? super N> builder) {
        this.backingValueGraph = new ConfigurableMutableValueGraph<N, GraphConstants.Presence>(builder);
    }

    @Override
    protected Graph<N> delegate() {
        return this.backingValueGraph;
    }

    @Override
    public boolean addNode(N node) {
        return this.backingValueGraph.addNode(node);
    }

    @Override
    public boolean putEdge(N nodeU, N nodeV) {
        return this.backingValueGraph.putEdgeValue(nodeU, nodeV, GraphConstants.Presence.EDGE_EXISTS) == null;
    }

    @Override
    public boolean removeNode(Object node) {
        return this.backingValueGraph.removeNode(node);
    }

    @Override
    public boolean removeEdge(Object nodeU, Object nodeV) {
        return this.backingValueGraph.removeEdge(nodeU, nodeV) != null;
    }
}

