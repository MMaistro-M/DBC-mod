/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.util;

import java.lang.reflect.Field;

public class SizeOfObjectUtil {
    public static long sizeOfObject(Object obj) {
        long size = 0L;
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Class<?> type = field.getType();
                if (type == Integer.TYPE || type == Integer.class) {
                    size += 4L;
                    continue;
                }
                if (type == Long.TYPE || type == Long.class) {
                    size += 8L;
                    continue;
                }
                if (type == Byte.TYPE || type == Byte.class) {
                    ++size;
                    continue;
                }
                if (type == Boolean.TYPE || type == Boolean.class) {
                    ++size;
                    continue;
                }
                Object value = field.get(obj);
                if (value == null) continue;
                size += SizeOfObjectUtil.sizeOfObject(value);
            }
            catch (IllegalAccessException illegalAccessException) {
                // empty catch block
            }
        }
        return size;
    }
}

