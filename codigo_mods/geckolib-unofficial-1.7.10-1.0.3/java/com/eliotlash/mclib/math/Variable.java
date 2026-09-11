/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.mclib.math;

import com.eliotlash.mclib.math.IValue;

public class Variable
implements IValue {
    private String name;
    private double value;

    public Variable(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public void set(double value) {
        this.value = value;
    }

    @Override
    public double get() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}

