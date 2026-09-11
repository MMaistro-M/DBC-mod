/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.collect;

import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;

@GwtCompatible
public enum BoundType {
    OPEN{

        @Override
        BoundType flip() {
            return CLOSED;
        }
    }
    ,
    CLOSED{

        @Override
        BoundType flip() {
            return OPEN;
        }
    };


    static BoundType forBoolean(boolean inclusive) {
        return inclusive ? CLOSED : OPEN;
    }

    abstract BoundType flip();
}

