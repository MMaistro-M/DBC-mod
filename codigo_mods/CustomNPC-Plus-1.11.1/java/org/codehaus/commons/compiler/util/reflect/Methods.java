/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.util.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.codehaus.commons.nullanalysis.Nullable;

public final class Methods {
    private Methods() {
    }

    public static <R, EX extends Throwable> R invoke(Method method, @Nullable Object obj, Object ... args) throws EX {
        try {
            Object returnValue = method.invoke(obj, args);
            return (R)returnValue;
        }
        catch (InvocationTargetException ite) {
            Throwable targetException = ite.getTargetException();
            throw targetException;
        }
        catch (Exception e) {
            throw new AssertionError((Object)e);
        }
    }
}

