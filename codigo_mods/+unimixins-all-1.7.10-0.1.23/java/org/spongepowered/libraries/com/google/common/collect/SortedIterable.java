/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;

@GwtCompatible
interface SortedIterable<T>
extends Iterable<T> {
    public Comparator<? super T> comparator();

    @Override
    public Iterator<T> iterator();
}

