/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.SortedSet;
import org.spongepowered.libraries.com.google.common.annotations.GwtIncompatible;
import org.spongepowered.libraries.com.google.common.collect.Multiset;

@GwtIncompatible
interface SortedMultisetBridge<E>
extends Multiset<E> {
    @Override
    public SortedSet<E> elementSet();
}

