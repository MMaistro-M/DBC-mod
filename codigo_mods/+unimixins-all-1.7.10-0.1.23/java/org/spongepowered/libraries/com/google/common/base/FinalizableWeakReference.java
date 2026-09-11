/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.base;

import java.lang.ref.WeakReference;
import org.spongepowered.libraries.com.google.common.annotations.GwtIncompatible;
import org.spongepowered.libraries.com.google.common.base.FinalizableReference;
import org.spongepowered.libraries.com.google.common.base.FinalizableReferenceQueue;

@GwtIncompatible
public abstract class FinalizableWeakReference<T>
extends WeakReference<T>
implements FinalizableReference {
    protected FinalizableWeakReference(T referent, FinalizableReferenceQueue queue) {
        super(referent, queue.queue);
        queue.cleanUp();
    }
}

