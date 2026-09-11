/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.base;

import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class CommonMatcher {
    CommonMatcher() {
    }

    abstract boolean matches();

    abstract boolean find();

    abstract boolean find(int var1);

    abstract String replaceAll(String var1);

    abstract int end();

    abstract int start();
}

