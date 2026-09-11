/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.jsontype;

import com.relocated.fasterxml.jackson.annotation.JsonTypeInfo;
import com.relocated.fasterxml.jackson.databind.DatabindContext;
import com.relocated.fasterxml.jackson.databind.JavaType;
import java.io.IOException;

public interface TypeIdResolver {
    public void init(JavaType var1);

    public String idFromValue(Object var1);

    public String idFromValueAndType(Object var1, Class<?> var2);

    public String idFromBaseType();

    public JavaType typeFromId(DatabindContext var1, String var2) throws IOException;

    public String getDescForKnownTypeIds();

    public JsonTypeInfo.Id getMechanism();
}

