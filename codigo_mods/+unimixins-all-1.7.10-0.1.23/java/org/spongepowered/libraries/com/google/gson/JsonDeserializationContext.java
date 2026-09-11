/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.gson;

import java.lang.reflect.Type;
import org.spongepowered.libraries.com.google.gson.JsonElement;
import org.spongepowered.libraries.com.google.gson.JsonParseException;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface JsonDeserializationContext {
    public <T> T deserialize(JsonElement var1, Type var2) throws JsonParseException;
}

