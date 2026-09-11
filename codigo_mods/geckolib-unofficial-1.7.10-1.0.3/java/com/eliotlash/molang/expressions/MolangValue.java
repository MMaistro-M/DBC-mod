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
import com.eliotlash.molang.MolangParser;
import com.eliotlash.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import software.bernie.geckolib3.molang.MolangRegistrar;

public class MolangValue
extends MolangExpression {
    public IValue value;
    public boolean returns;

    public MolangValue() {
    }

    public MolangValue(MolangParser context, IValue value) {
        super(context);
        this.value = value;
    }

    public MolangExpression addReturn() {
        this.returns = true;
        return this;
    }

    @Override
    public double get() {
        return this.value.get();
    }

    public String toString() {
        return (this.returns ? "return " : "") + this.value.toString();
    }

    @Override
    public JsonElement toJson() {
        return this.value instanceof Constant ? new JsonPrimitive((Number)this.value.get()) : super.toJson();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.value);
        out.writeBoolean(this.returns);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.value = (IValue)in.readObject();
        this.returns = in.readBoolean();
        this.context = MolangRegistrar.getParser();
    }
}

