/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.Comparator;
import java.util.Spliterator;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.annotations.GwtIncompatible;
import org.spongepowered.libraries.com.google.common.collect.CollectSpliterators;
import org.spongepowered.libraries.com.google.common.collect.ImmutableList;
import org.spongepowered.libraries.com.google.common.collect.ImmutableSortedSet;
import org.spongepowered.libraries.com.google.common.collect.RegularImmutableAsList;
import org.spongepowered.libraries.com.google.common.collect.RegularImmutableSortedSet;
import org.spongepowered.libraries.com.google.common.collect.SortedIterable;

@GwtCompatible(emulated=true)
final class ImmutableSortedAsList<E>
extends RegularImmutableAsList<E>
implements SortedIterable<E> {
    ImmutableSortedAsList(ImmutableSortedSet<E> backingSet, ImmutableList<E> backingList) {
        super(backingSet, backingList);
    }

    @Override
    ImmutableSortedSet<E> delegateCollection() {
        return (ImmutableSortedSet)super.delegateCollection();
    }

    @Override
    public Comparator<? super E> comparator() {
        return ((ImmutableSortedSet)this.delegateCollection()).comparator();
    }

    @Override
    @GwtIncompatible
    public int indexOf(@Nullable Object target) {
        int index = ((ImmutableSortedSet)this.delegateCollection()).indexOf(target);
        return index >= 0 && this.get(index).equals(target) ? index : -1;
    }

    @Override
    @GwtIncompatible
    public int lastIndexOf(@Nullable Object target) {
        return this.indexOf(target);
    }

    @Override
    public boolean contains(Object target) {
        return this.indexOf(target) >= 0;
    }

    @Override
    @GwtIncompatible
    ImmutableList<E> subListUnchecked(int fromIndex, int toIndex) {
        ImmutableList parentSubList = super.subListUnchecked(fromIndex, toIndex);
        return new RegularImmutableSortedSet(parentSubList, this.comparator()).asList();
    }

    @Override
    public Spliterator<E> spliterator() {
        return CollectSpliterators.indexed(this.size(), 1301, this.delegateList()::get, this.comparator());
    }
}

