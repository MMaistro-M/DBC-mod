/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class ClassTypeInfo
extends TypeInfo {
    private final TypeInfo instanceType;
    private List<MethodInfo> constructorMethod;
    private List<FieldInfo> classField;

    public ClassTypeInfo(Class<?> javaClass) {
        super(javaClass.getSimpleName(), javaClass.getName(), javaClass.getPackage() != null ? javaClass.getPackage().getName() : "", javaClass.isInterface() ? TypeInfo.Kind.INTERFACE : (javaClass.isEnum() ? TypeInfo.Kind.ENUM : TypeInfo.Kind.CLASS), javaClass, true, null, true);
        this.instanceType = TypeInfo.fromClass(javaClass);
    }

    public ClassTypeInfo(TypeInfo instanceType) {
        super(instanceType.getSimpleName(), instanceType.getFullName(), instanceType.getPackageName(), instanceType.getKind(), instanceType.getJavaClass(), instanceType.isResolved(), instanceType.getEnclosingType(), true);
        this.instanceType = instanceType;
    }

    @Override
    public boolean isClassReference() {
        return true;
    }

    public TypeInfo getInstanceType() {
        return this.instanceType;
    }

    @Override
    public List<MethodInfo> getSyntheticMethods() {
        List<MethodInfo> base = super.getSyntheticMethods();
        this.constructorMethod = this.buildConstructorMethod();
        if (this.constructorMethod.isEmpty()) {
            return base;
        }
        ArrayList<MethodInfo> merged = new ArrayList<MethodInfo>(base.size() + this.constructorMethod.size());
        merged.addAll(base);
        merged.addAll(this.constructorMethod);
        return merged;
    }

    @Override
    public List<FieldInfo> getSyntheticFields() {
        List<FieldInfo> base = super.getSyntheticFields();
        if (this.classField == null) {
            this.classField = this.buildClassField();
        }
        if (this.classField.isEmpty()) {
            return base;
        }
        ArrayList<FieldInfo> merged = new ArrayList<FieldInfo>(base.size() + this.classField.size());
        merged.addAll(base);
        merged.addAll(this.classField);
        return merged;
    }

    private List<MethodInfo> buildConstructorMethod() {
        if (this.instanceType == null) {
            return Collections.emptyList();
        }
        FieldInfo argsParam = FieldInfo.parameter("args", TypeInfo.OBJECT, -1, null);
        argsParam.setVarArg(true);
        ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
        params.add(argsParam);
        MethodInfo ctor = MethodInfo.external("constructor", this.instanceType, this, params, 9, "Creates a new instance of " + this.instanceType.getSimpleName() + ".\n\nEquivalent to using the 'new' operator on this class reference.");
        return Collections.singletonList(ctor);
    }

    private List<FieldInfo> buildClassField() {
        if (this.getJavaClass() == null) {
            return Collections.emptyList();
        }
        try {
            TypeInfo classType = TypeInfo.fromClass(Class.class).parameterize(this.instanceType);
            FieldInfo classField = FieldInfo.external("class", classType, "The Class object for " + this.getSimpleName(), 9);
            return Collections.singletonList(classField);
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public String toString() {
        return "Class<" + this.getSimpleName() + ">";
    }
}

