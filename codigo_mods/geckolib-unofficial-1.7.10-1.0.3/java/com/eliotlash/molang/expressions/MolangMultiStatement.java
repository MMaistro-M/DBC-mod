/*
 * Decompiled with CFR 0.152.
 */
package com.eliotlash.molang.expressions;

import com.eliotlash.mclib.math.Variable;
import com.eliotlash.molang.MolangParser;
import com.eliotlash.molang.expressions.MolangExpression;
import com.eliotlash.molang.expressions.MolangValue;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import software.bernie.geckolib3.molang.MolangRegistrar;

public class MolangMultiStatement
extends MolangExpression {
    public List<MolangExpression> expressions = new ArrayList<MolangExpression>();
    public Map<String, Variable> locals = new HashMap<String, Variable>();

    public MolangMultiStatement() {
    }

    public MolangMultiStatement(MolangParser context) {
        super(context);
    }

    @Override
    public double get() {
        double value = 0.0;
        for (MolangExpression expression : this.expressions) {
            value = expression.get();
        }
        return value;
    }

    public String toString() {
        StringJoiner builder = new StringJoiner("; ");
        for (MolangExpression expression : this.expressions) {
            builder.add(expression.toString());
            if (!(expression instanceof MolangValue) || !((MolangValue)expression).returns) continue;
            break;
        }
        return builder.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.expressions);
        out.writeObject(this.locals);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.expressions = (List)in.readObject();
        this.locals = (Map)in.readObject();
        this.context = MolangRegistrar.getParser();
    }
}

