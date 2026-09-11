/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type.synthetic;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticField;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticMethod;

public class SyntheticType {
    private final String name;
    private final String documentation;
    private final Map<String, SyntheticMethod> methods;
    private final Map<String, SyntheticField> fields;
    private TypeInfo typeInfo;

    SyntheticType(String name, String documentation, List<SyntheticMethod> methods, List<SyntheticField> fields) {
        this.name = name;
        this.documentation = documentation;
        this.methods = new LinkedHashMap<String, SyntheticMethod>();
        for (SyntheticMethod m : methods) {
            this.methods.put(m.name, m);
        }
        this.fields = new LinkedHashMap<String, SyntheticField>();
        for (SyntheticField f : fields) {
            this.fields.put(f.name, f);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getDocumentation() {
        return this.documentation;
    }

    public SyntheticMethod getMethod(String methodName) {
        return this.methods.get(methodName);
    }

    public Collection<SyntheticMethod> getMethods() {
        return this.methods.values();
    }

    public SyntheticField getField(String fieldName) {
        return this.fields.get(fieldName);
    }

    public Collection<SyntheticField> getFields() {
        return this.fields.values();
    }

    public boolean hasMethod(String methodName) {
        return this.methods.containsKey(methodName);
    }

    public boolean hasField(String fieldName) {
        return this.fields.containsKey(fieldName);
    }

    public TypeInfo getTypeInfo() {
        if (this.typeInfo == null) {
            this.typeInfo = TypeInfo.resolved(this.name, this.name, "", TypeInfo.Kind.CLASS, null);
        }
        return this.typeInfo;
    }

    public MethodInfo getMethodInfo(String methodName) {
        SyntheticMethod method = this.methods.get(methodName);
        if (method == null) {
            return null;
        }
        return method.toMethodInfo(this.getTypeInfo());
    }

    public TypeInfo resolveMethodReturnType(String methodName, String[] arguments) {
        SyntheticMethod method = this.methods.get(methodName);
        if (method == null) {
            return null;
        }
        return method.resolveReturnType(arguments);
    }
}

