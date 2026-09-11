/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.hash;

import java.io.Serializable;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.hash.PrimitiveSink;

@Beta
public interface Funnel<T>
extends Serializable {
    public void funnel(T var1, PrimitiveSink var2);
}

