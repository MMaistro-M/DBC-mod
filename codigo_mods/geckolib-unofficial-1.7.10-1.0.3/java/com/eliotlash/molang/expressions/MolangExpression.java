/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonPrimitive
 */
package com.eliotlash.molang.expressions;

import com.eliotlash.mclib.math.Constant;
import com.eliotlash.mclib.math.IValue;
import com.eliotlash.mclib.math.Operation;
import com.eliotlash.molang.MolangParser;
import com.eliotlash.molang.expressions.MolangValue;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import software.bernie.geckolib3.molang.MolangRegistrar;

public abstract class MolangExpression
implements IValue,
Externalizable {
    public MolangParser context;

    public MolangExpression() {
    }

    public static boolean isZero(MolangExpression expression) {
        return MolangExpression.isConstant(expression, 0.0);
    }

    public static boolean isOne(MolangExpression expression) {
        return MolangExpression.isConstant(expression, 1.0);
    }

    public static boolean isConstant(MolangExpression expression, double x) {
        if (!(expression instanceof MolangValue)) {
            return false;
        }
        MolangValue value = (MolangValue)expression;
        return value.value instanceof Constant && Operation.equals(value.value.get(), x);
    }

    public static boolean isExpressionConstant(MolangExpression expression) {
        if (expression instanceof MolangValue) {
            MolangValue value = (MolangValue)expression;
            return value.value instanceof Constant;
        }
        return false;
    }

    public MolangExpression(MolangParser context) {
        this.context = context;
    }

    public JsonElement toJson() {
        return new JsonPrimitive(this.toString());
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.context = MolangRegistrar.getParser();
    }
}

