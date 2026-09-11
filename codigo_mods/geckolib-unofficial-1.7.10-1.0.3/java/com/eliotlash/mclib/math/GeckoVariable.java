/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.mclib.math;

import com.eliotlash.mclib.math.Variable;
import software.bernie.geckolib3.molang.MolangRegistrar;

public class GeckoVariable
extends Variable {
    public GeckoVariable(String name, double value) {
        super(name, value);
    }

    @Override
    public double get() {
        if (this != MolangRegistrar.getParser().getVariable(this.getName())) {
            return MolangRegistrar.getParser().getVariable(this.getName()).get();
        }
        return super.get();
    }
}

