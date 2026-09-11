/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.base;

import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.base.CommonMatcher;

@GwtCompatible
abstract class CommonPattern {
    CommonPattern() {
    }

    abstract CommonMatcher matcher(CharSequence var1);

    abstract String pattern();

    abstract int flags();

    public abstract String toString();

    public abstract int hashCode();

    public abstract boolean equals(Object var1);
}

