/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CompatibleWith
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.ObjIntConsumer;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.base.Preconditions;
import org.spongepowered.libraries.com.google.common.collect.Multisets;
import org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.spongepowered.libraries.com.google.errorprone.annotations.CompatibleWith;

@GwtCompatible
public interface Multiset<E>
extends Collection<E> {
    @Override
    public int size();

    public int count(@Nullable @CompatibleWith(value="E") Object var1);

    @CanIgnoreReturnValue
    public int add(@Nullable E var1, int var2);

    @CanIgnoreReturnValue
    public int remove(@Nullable @CompatibleWith(value="E") Object var1, int var2);

    @CanIgnoreReturnValue
    public int setCount(E var1, int var2);

    @CanIgnoreReturnValue
    public boolean setCount(E var1, int var2, int var3);

    public Set<E> elementSet();

    public Set<Entry<E>> entrySet();

    @Beta
    default public void forEachEntry(ObjIntConsumer<? super E> action) {
        Preconditions.checkNotNull(action);
        this.entrySet().forEach((? super T entry) -> action.accept((Object)entry.getElement(), entry.getCount()));
    }

    @Override
    public boolean equals(@Nullable Object var1);

    @Override
    public int hashCode();

    public String toString();

    @Override
    public Iterator<E> iterator();

    @Override
    public boolean contains(@Nullable Object var1);

    @Override
    public boolean containsAll(Collection<?> var1);

    @Override
    @CanIgnoreReturnValue
    public boolean add(E var1);

    @Override
    @CanIgnoreReturnValue
    public boolean remove(@Nullable Object var1);

    @Override
    @CanIgnoreReturnValue
    public boolean removeAll(Collection<?> var1);

    @Override
    @CanIgnoreReturnValue
    public boolean retainAll(Collection<?> var1);

    @Override
    default public void forEach(Consumer<? super E> action) {
        Preconditions.checkNotNull(action);
        this.entrySet().forEach((? super T entry) -> {
            Object elem = entry.getElement();
            int count = entry.getCount();
            for (int i = 0; i < count; ++i) {
                action.accept((Object)elem);
            }
        });
    }

    @Override
    default public Spliterator<E> spliterator() {
        return Multisets.spliteratorImpl(this);
    }

    public static interface Entry<E> {
        public E getElement();

        public int getCount();

        public boolean equals(Object var1);

        public int hashCode();

        public String toString();
    }
}

