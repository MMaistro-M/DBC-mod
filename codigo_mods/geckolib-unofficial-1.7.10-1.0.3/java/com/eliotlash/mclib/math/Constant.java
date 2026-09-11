/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.mclib.math;

import com.eliotlash.mclib.math.IValue;

public class Constant
implements IValue {
    private double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public double get() {
        return this.value;
    }

    public void set(double value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}

