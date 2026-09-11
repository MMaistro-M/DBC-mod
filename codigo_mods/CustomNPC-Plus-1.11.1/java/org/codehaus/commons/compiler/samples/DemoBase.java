/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.commons.compiler.samples;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DemoBase {
    protected DemoBase() {
    }

    public static Object createObject(Class<?> type, String value) throws NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException {
        if (type.isPrimitive()) {
            Class clazz = type == Boolean.TYPE ? Boolean.class : (type == Character.TYPE ? Character.class : (type == Byte.TYPE ? Byte.class : (type == Short.TYPE ? Short.class : (type == Integer.TYPE ? Integer.class : (type == Long.TYPE ? Long.class : (type == Float.TYPE ? Float.class : (type = type == Double.TYPE ? Double.class : Void.TYPE)))))));
        }
        if ("".equals(value)) {
            return type.getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        return type.getConstructor(String.class).newInstance(value);
    }

    public static String[] explode(String s) {
        StringTokenizer st = new StringTokenizer(s, ",");
        ArrayList<String> l = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            l.add(st.nextToken());
        }
        return l.toArray(new String[l.size()]);
    }

    public static Class<?> stringToType(String s) {
        int brackets = 0;
        while (s.endsWith("[]")) {
            ++brackets;
            s = s.substring(0, s.length() - 2);
        }
        if (brackets == 0) {
            if ("void".equals(s)) {
                return Void.TYPE;
            }
            if ("boolean".equals(s)) {
                return Boolean.TYPE;
            }
            if ("char".equals(s)) {
                return Character.TYPE;
            }
            if ("byte".equals(s)) {
                return Byte.TYPE;
            }
            if ("short".equals(s)) {
                return Short.TYPE;
            }
            if ("int".equals(s)) {
                return Integer.TYPE;
            }
            if ("long".equals(s)) {
                return Long.TYPE;
            }
            if ("float".equals(s)) {
                return Float.TYPE;
            }
            if ("double".equals(s)) {
                return Double.TYPE;
            }
        }
        if ("void".equals(s)) {
            s = "V";
        } else if ("boolean".equals(s)) {
            s = "Z";
        } else if ("char".equals(s)) {
            s = "C";
        } else if ("byte".equals(s)) {
            s = "B";
        } else if ("short".equals(s)) {
            s = "S";
        } else if ("int".equals(s)) {
            s = "I";
        } else if ("long".equals(s)) {
            s = "J";
        } else if ("float".equals(s)) {
            s = "F";
        } else if ("double".equals(s)) {
            s = "D";
        }
        while (--brackets >= 0) {
            s = '[' + s;
        }
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.exit(1);
            throw new RuntimeException();
        }
    }

    public static Class<?>[] stringToTypes(String s) {
        StringTokenizer st = new StringTokenizer(s, ",");
        ArrayList l = new ArrayList();
        while (st.hasMoreTokens()) {
            l.add(DemoBase.stringToType(st.nextToken()));
        }
        Class[] res = new Class[l.size()];
        l.toArray(res);
        return res;
    }
}

