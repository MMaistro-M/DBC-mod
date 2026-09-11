/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.databind.type;

import com.relocated.fasterxml.jackson.databind.JavaType;
import com.relocated.fasterxml.jackson.databind.type.TypeBindings;
import com.relocated.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Type;

public abstract class TypeModifier {
    public abstract JavaType modifyType(JavaType var1, Type var2, TypeBindings var3, TypeFactory var4);
}

