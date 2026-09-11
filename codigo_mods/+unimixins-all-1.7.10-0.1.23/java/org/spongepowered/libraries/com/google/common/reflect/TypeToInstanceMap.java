/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue
 */
package org.spongepowered.libraries.com.google.common.reflect;

import java.util.Map;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.reflect.TypeToken;
import org.spongepowered.libraries.com.google.errorprone.annotations.CanIgnoreReturnValue;

@Beta
public interface TypeToInstanceMap<B>
extends Map<TypeToken<? extends B>, B> {
    @Nullable
    public <T extends B> T getInstance(Class<T> var1);

    @Nullable
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(Class<T> var1, @Nullable T var2);

    @Nullable
    public <T extends B> T getInstance(TypeToken<T> var1);

    @Nullable
    @CanIgnoreReturnValue
    public <T extends B> T putInstance(TypeToken<T> var1, @Nullable T var2);
}

