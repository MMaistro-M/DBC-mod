/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.util;

import org.codehaus.commons.nullanalysis.Nullable;

public final class SystemProperties {
    private SystemProperties() {
    }

    public static boolean getBooleanClassProperty(Class<?> targetClass, String classPropertyName) {
        return SystemProperties.getBooleanClassProperty(targetClass, classPropertyName, false);
    }

    public static boolean getBooleanClassProperty(Class<?> targetClass, String classPropertyName, boolean defaultValue) {
        String s = SystemProperties.getClassProperty(targetClass, classPropertyName);
        return s != null ? Boolean.parseBoolean(s) : defaultValue;
    }

    public static int getIntegerClassProperty(Class<?> targetClass, String classPropertyName, int defaultValue) {
        String s = SystemProperties.getClassProperty(targetClass, classPropertyName);
        return s != null ? Integer.parseInt(s) : defaultValue;
    }

    @Nullable
    public static String getClassProperty(Class<?> targetClass, String classPropertyName) {
        return SystemProperties.getClassProperty(targetClass, classPropertyName, null);
    }

    @Nullable
    public static String getClassProperty(Class<?> targetClass, String classPropertyName, @Nullable String defaultValue) {
        String result2;
        try {
            result2 = System.getProperty(targetClass.getName() + "." + classPropertyName);
            if (result2 != null) {
                return result2;
            }
        }
        catch (SecurityException result2) {
            // empty catch block
        }
        try {
            result2 = System.getProperty(targetClass.getSimpleName() + "." + classPropertyName);
            if (result2 != null) {
                return result2;
            }
        }
        catch (SecurityException securityException) {
            // empty catch block
        }
        return defaultValue;
    }
}

