/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type.synthetic;

import java.util.ArrayList;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticField;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticMethod;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticParameter;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticType;

public class SyntheticTypeBuilder {
    private final String name;
    private String documentation;
    private final List<SyntheticMethod> methods = new ArrayList<SyntheticMethod>();
    private final List<SyntheticField> fields = new ArrayList<SyntheticField>();

    public SyntheticTypeBuilder(String name) {
        this.name = name;
    }

    public SyntheticTypeBuilder documentation(String doc) {
        this.documentation = doc;
        return this;
    }

    public MethodBuilder addMethod(String methodName) {
        return new MethodBuilder(this, methodName);
    }

    public SyntheticTypeBuilder addField(String fieldName, String typeName, String doc) {
        this.fields.add(new SyntheticField(fieldName, typeName, doc, false));
        return this;
    }

    public SyntheticTypeBuilder addStaticField(String fieldName, String typeName, String doc) {
        this.fields.add(new SyntheticField(fieldName, typeName, doc, true));
        return this;
    }

    void addBuiltMethod(SyntheticMethod method) {
        this.methods.add(method);
    }

    public SyntheticType build() {
        return new SyntheticType(this.name, this.documentation, this.methods, this.fields);
    }

    @FunctionalInterface
    public static interface ReturnTypeResolver {
        public TypeInfo resolve(String[] var1);
    }

    public static class MethodBuilder {
        private final SyntheticTypeBuilder parent;
        private final String name;
        private final List<SyntheticParameter> parameters = new ArrayList<SyntheticParameter>();
        private String returnType = "void";
        private String documentation;
        private boolean isStatic = false;
        private ReturnTypeResolver returnTypeResolver;

        MethodBuilder(SyntheticTypeBuilder parent, String name) {
            this.parent = parent;
            this.name = name;
        }

        public MethodBuilder parameter(String paramName, String typeName) {
            this.parameters.add(new SyntheticParameter(paramName, typeName, null));
            return this;
        }

        public MethodBuilder parameter(String paramName, String typeName, String doc) {
            this.parameters.add(new SyntheticParameter(paramName, typeName, doc));
            return this;
        }

        public MethodBuilder returns(String typeName) {
            this.returnType = typeName;
            return this;
        }

        public MethodBuilder returnsResolved(ReturnTypeResolver resolver) {
            this.returnTypeResolver = resolver;
            return this;
        }

        public MethodBuilder documentation(String doc) {
            this.documentation = doc;
            return this;
        }

        public MethodBuilder asStatic() {
            this.isStatic = true;
            return this;
        }

        public SyntheticTypeBuilder done() {
            this.parent.addBuiltMethod(new SyntheticMethod(this.name, this.returnType, this.parameters, this.documentation, this.isStatic, this.returnTypeResolver));
            return this.parent;
        }
    }
}

