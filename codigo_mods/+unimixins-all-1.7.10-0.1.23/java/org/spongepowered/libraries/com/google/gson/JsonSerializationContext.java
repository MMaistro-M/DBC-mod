/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.gson;

import java.lang.reflect.Type;
import org.spongepowered.libraries.com.google.gson.JsonElement;

public interface JsonSerializationContext {
    public JsonElement serialize(Object var1);

    public JsonElement serialize(Object var1, Type var2);
}

