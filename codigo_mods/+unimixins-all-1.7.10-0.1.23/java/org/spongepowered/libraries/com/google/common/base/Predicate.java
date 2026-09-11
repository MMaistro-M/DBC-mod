/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue
 */
package org.spongepowered.libraries.com.google.common.base;

import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue;

@FunctionalInterface
@GwtCompatible
public interface Predicate<T>
extends java.util.function.Predicate<T> {
    @CanIgnoreReturnValue
    public boolean apply(@Nullable T var1);

    public boolean equals(@Nullable Object var1);

    @Override
    default public boolean test(@Nullable T input) {
        return this.apply(input);
    }
}

