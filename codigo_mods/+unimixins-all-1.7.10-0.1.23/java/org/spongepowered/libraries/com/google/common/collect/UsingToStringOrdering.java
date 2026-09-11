/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.collect;

import java.io.Serializable;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.collect.Ordering;

@GwtCompatible(serializable=true)
final class UsingToStringOrdering
extends Ordering<Object>
implements Serializable {
    static final UsingToStringOrdering INSTANCE = new UsingToStringOrdering();
    private static final long serialVersionUID = 0L;

    @Override
    public int compare(Object left, Object right) {
        return left.toString().compareTo(right.toString());
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public String toString() {
        return "Ordering.usingToString()";
    }

    private UsingToStringOrdering() {
    }
}

