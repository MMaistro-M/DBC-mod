/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.molang.expressions;

import com.eliotlash.mclib.math.IValue;
import com.eliotlash.mclib.math.Variable;
import com.eliotlash.molang.MolangParser;
import com.eliotlash.molang.expressions.MolangExpression;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import software.bernie.geckolib3.molang.MolangRegistrar;

public class MolangAssignment
extends MolangExpression {
    public Variable variable;
    public IValue expression;

    public MolangAssignment() {
    }

    public MolangAssignment(MolangParser context, Variable variable, IValue expression) {
        super(context);
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public double get() {
        double value = this.expression.get();
        this.variable.set(value);
        return value;
    }

    public String toString() {
        return this.variable.getName() + " = " + this.expression.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.variable);
        out.writeObject(this.expression);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.variable = (Variable)in.readObject();
        this.expression = (MolangExpression)in.readObject();
        this.context = MolangRegistrar.getParser();
    }
}

