/*
 * Decompiled with CFR 0.152.
 */
package org.codehaus.janino;

public enum Access {
    PRIVATE,
    PROTECTED,
    DEFAULT,
    PUBLIC;


    public static Access fromString(String s) {
        if ("private".equals(s)) {
            return PRIVATE;
        }
        if ("protected".equals(s)) {
            return PROTECTED;
        }
        if ("public".equals(s)) {
            return PUBLIC;
        }
        throw new IllegalArgumentException(s);
    }

    public String toString() {
        return this.name().toLowerCase();
    }
}

