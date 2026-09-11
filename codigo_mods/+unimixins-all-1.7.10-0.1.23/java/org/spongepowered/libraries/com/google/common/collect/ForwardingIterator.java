/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.util.Iterator;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.collect.ForwardingObject;
import org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue;

@GwtCompatible
public abstract class ForwardingIterator<T>
extends ForwardingObject
implements Iterator<T> {
    protected ForwardingIterator() {
    }

    @Override
    protected abstract Iterator<T> delegate();

    @Override
    public boolean hasNext() {
        return this.delegate().hasNext();
    }

    @Override
    @CanIgnoreReturnValue
    public T next() {
        return (T)this.delegate().next();
    }

    @Override
    public void remove() {
        this.delegate().remove();
    }
}

