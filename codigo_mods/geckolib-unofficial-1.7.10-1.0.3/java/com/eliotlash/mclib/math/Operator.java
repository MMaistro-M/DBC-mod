/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.mclib.math;

import com.eliotlash.mclib.math.IValue;
import com.eliotlash.mclib.math.Operation;

public class Operator
implements IValue {
    public Operation operation;
    public IValue a;
    public IValue b;

    public Operator(Operation op, IValue a, IValue b) {
        this.operation = op;
        this.a = a;
        this.b = b;
    }

    @Override
    public double get() {
        return this.operation.calculate(this.a.get(), this.b.get());
    }

    public String toString() {
        return this.a.toString() + " " + this.operation.sign + " " + this.b.toString();
    }
}

