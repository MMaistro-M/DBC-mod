/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 */
package com.gtnewhorizon.gtnhmixins;

import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;

public interface ILateMixinLoader {
    public String getMixinConfig();

    @Nonnull
    public List<String> getMixins(Set<String> var1);
}

