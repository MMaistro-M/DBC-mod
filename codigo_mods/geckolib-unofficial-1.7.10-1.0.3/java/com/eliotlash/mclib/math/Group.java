/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.mclib.math;

import com.eliotlash.mclib.math.IValue;

public class Group
implements IValue {
    private IValue value;

    public Group(IValue value) {
        this.value = value;
    }

    @Override
    public double get() {
        return this.value.get();
    }

    public String toString() {
        return "(" + this.value.toString() + ")";
    }
}

