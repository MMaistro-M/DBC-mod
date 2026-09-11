/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.field;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import noppes.npcs.client.gui.util.script.ScopeInfo;
import noppes.npcs.client.gui.util.script.interpreter.bridge.DtsJavaBridge;
import noppes.npcs.client.gui.util.script.interpreter.field.AssignmentInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.EnumConstantInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSFieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodCallInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericContext;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public final class FieldInfo {
    private final String name;
    private final Scope scope;
    private final TypeInfo declaredType;
    private final int declarationOffset;
    private final boolean resolved;
    private final String documentation;
    private JSDocInfo jsDocInfo;
    private final int initStart;
    private final int initEnd;
    private final MethodInfo containingMethod;
    private final int modifiers;
    private final Field reflectionField;
    private ScopeInfo scopeInfo;
    private final List<AssignmentInfo> assignments = new ArrayList<AssignmentInfo>();
    private AssignmentInfo declarationAssignment;
    private EnumConstantInfo enumConstantInfo;
    private boolean isVarArg = false;
    private TypeInfo inferredType;

    private FieldInfo(String name, Scope scope, TypeInfo declaredType, int declarationOffset, boolean resolved, MethodInfo containingMethod, String documentation, int initStart, int initEnd, int modifiers, Field reflectionField) {
        this.name = name;
        this.scope = scope;
        this.declaredType = declaredType;
        this.declarationOffset = declarationOffset;
        this.resolved = resolved;
        this.containingMethod = containingMethod;
        this.documentation = documentation;
        this.initStart = initStart;
        this.initEnd = initEnd;
        this.modifiers = modifiers;
        this.reflectionField = reflectionField;
    }

    public static FieldInfo globalField(String name, TypeInfo type, int declOffset) {
        return new FieldInfo(name, Scope.GLOBAL, type, declOffset, type != null && type.isResolved(), null, null, -1, -1, 0, null);
    }

    public static FieldInfo globalField(String name, TypeInfo type, int declOffset, String documentation) {
        return new FieldInfo(name, Scope.GLOBAL, type, declOffset, type != null && type.isResolved(), null, documentation, -1, -1, 0, null);
    }

    public static FieldInfo globalField(String name, TypeInfo type, int declOffset, String documentation, int initStart, int initEnd) {
        return new FieldInfo(name, Scope.GLOBAL, type, declOffset, type != null && type.isResolved(), null, documentation, initStart, initEnd, 0, null);
    }

    public static FieldInfo globalField(String name, TypeInfo type, int declOffset, String documentation, int initStart, int initEnd, int modifiers) {
        return new FieldInfo(name, Scope.GLOBAL, type, declOffset, type != null && type.isResolved(), null, documentation, initStart, initEnd, modifiers, null);
    }

    public static FieldInfo localField(String name, TypeInfo type, int declOffset, MethodInfo method) {
        return new FieldInfo(name, Scope.LOCAL, type, declOffset, type != null && type.isResolved(), method, null, -1, -1, 0, null);
    }

    public static FieldInfo localField(String name, TypeInfo type, int declOffset, MethodInfo method, int initStart, int initEnd) {
        return new FieldInfo(name, Scope.LOCAL, type, declOffset, type != null && type.isResolved(), method, null, initStart, initEnd, 0, null);
    }

    public static FieldInfo localField(String name, TypeInfo type, int declOffset, MethodInfo method, int initStart, int initEnd, int modifiers) {
        return new FieldInfo(name, Scope.LOCAL, type, declOffset, type != null && type.isResolved(), method, null, initStart, initEnd, modifiers, null);
    }

    public static FieldInfo parameter(String name, TypeInfo type, int declOffset, MethodInfo method) {
        return new FieldInfo(name, Scope.PARAMETER, type, declOffset, type != null && type.isResolved(), method, null, -1, -1, 0, null);
    }

    public static FieldInfo unresolved(String name, Scope scope) {
        return new FieldInfo(name, scope, null, -1, false, null, null, -1, -1, 0, null);
    }

    public static FieldInfo reflectionParam(String name, TypeInfo type) {
        return new FieldInfo(name, Scope.PARAMETER, type, -1, true, null, null, -1, -1, 0, null);
    }

    public static FieldInfo external(String name, TypeInfo type, String documentation, int modifiers) {
        return new FieldInfo(name, Scope.GLOBAL, type, -1, true, null, documentation, -1, -1, modifiers, null);
    }

    public static FieldInfo fromReflection(Field field, TypeInfo containingType) {
        EnumConstantInfo constantInfo;
        String name = field.getName();
        TypeInfo type = TypeInfo.fromGenericType(field.getGenericType());
        if (type == null) {
            type = TypeInfo.fromClass(field.getType());
        }
        if (GenericContext.hasGenerics(containingType)) {
            type = GenericContext.forReceiver(containingType).substitute(type);
        }
        if (field.isEnumConstant() && (constantInfo = EnumConstantInfo.fromReflection(name, containingType, field)) != null) {
            return constantInfo.getFieldInfo();
        }
        JSFieldInfo jsField = DtsJavaBridge.findMatchingField(field, containingType);
        String documentation = null;
        JSDocInfo jsDocInfo = null;
        if (jsField != null) {
            jsDocInfo = jsField.getJsDocInfo();
            String jsDocDesc = jsDocInfo != null ? jsDocInfo.getDescription() : null;
            documentation = jsDocDesc != null ? jsDocDesc : jsField.getDocumentation();
        }
        FieldInfo fieldInfo = new FieldInfo(name, Scope.GLOBAL, type, -1, true, null, documentation, -1, -1, field.getModifiers(), field);
        if (jsDocInfo != null) {
            fieldInfo.setJSDocInfo(jsDocInfo);
        }
        return fieldInfo;
    }

    public static FieldInfo fromJSField(JSFieldInfo jsField, TypeInfo containingType) {
        JSDocInfo jsDocInfo;
        String name = jsField.getName();
        TypeInfo type = jsField.getResolvedType(containingType);
        if (type == null || !type.isResolved()) {
            type = FieldInfo.resolveJSType(jsField.getType());
        }
        int modifiers = 1;
        if (jsField.isReadonly()) {
            modifiers |= 0x10;
        }
        if (jsField.isStatic()) {
            modifiers |= 8;
        }
        String jsDocDesc = (jsDocInfo = jsField.getJsDocInfo()) != null ? jsDocInfo.getDescription() : null;
        String documentation = jsDocDesc != null ? jsDocDesc : jsField.getDocumentation();
        FieldInfo fieldInfo = new FieldInfo(name, Scope.GLOBAL, type, -1, true, null, documentation, -1, -1, modifiers, null);
        fieldInfo.setJSDocInfo(jsDocInfo);
        return fieldInfo;
    }

    private static TypeInfo resolveJSType(String jsTypeName) {
        JSTypeInfo jsTypeInfo;
        if (jsTypeName == null || jsTypeName.isEmpty() || "void".equals(jsTypeName)) {
            return TypeInfo.fromPrimitive("void");
        }
        switch (jsTypeName) {
            case "string": {
                return TypeInfo.fromClass(String.class);
            }
            case "number": {
                return TypeInfo.fromClass(Double.TYPE);
            }
            case "boolean": {
                return TypeInfo.fromClass(Boolean.TYPE);
            }
            case "any": {
                return TypeInfo.fromClass(Object.class);
            }
            case "void": {
                return TypeInfo.fromPrimitive("void");
            }
        }
        if (jsTypeName.endsWith("[]")) {
            String elementType = jsTypeName.substring(0, jsTypeName.length() - 2);
            TypeInfo elementTypeInfo = FieldInfo.resolveJSType(elementType);
            return TypeInfo.arrayOf(elementTypeInfo);
        }
        JSTypeRegistry registry = JSTypeRegistry.getInstance();
        if (registry != null && (jsTypeInfo = registry.getType(jsTypeName)) != null) {
            return TypeInfo.fromJSTypeInfo(jsTypeInfo);
        }
        return TypeInfo.unresolved(jsTypeName, jsTypeName);
    }

    public static FieldInfo enumConstant(String name, TypeInfo type, int declOffset, int initStart, int initEnd, Field javaField) {
        int modifiers = 25;
        return new FieldInfo(name, Scope.ENUM_CONSTANT, type, declOffset, true, null, null, initStart, initEnd, modifiers, javaField);
    }

    public void setEnumConstantInfo(EnumConstantInfo enumConstantInfo) {
        this.enumConstantInfo = enumConstantInfo;
    }

    public EnumConstantInfo getEnumInfo() {
        return this.enumConstantInfo;
    }

    public boolean isEnumConstant() {
        return this.scope == Scope.ENUM_CONSTANT;
    }

    public void addAssignment(AssignmentInfo assignment) {
        this.assignments.add(assignment);
    }

    public List<AssignmentInfo> getAssignments() {
        return Collections.unmodifiableList(this.assignments);
    }

    public void setDeclarationAssignment(AssignmentInfo assignment) {
        this.declarationAssignment = assignment;
    }

    public AssignmentInfo getDeclarationAssignment() {
        return this.declarationAssignment;
    }

    public AssignmentInfo findAssignmentAtPosition(int position) {
        if (this.declarationAssignment != null && this.declarationAssignment.containsPosition(position) && this.declarationAssignment.containsLhsPosition(position)) {
            return this.declarationAssignment;
        }
        for (AssignmentInfo assign : this.assignments) {
            if (!assign.containsLhsPosition(position)) continue;
            return assign;
        }
        if (this.declarationAssignment != null && this.declarationAssignment.containsRhsPosition(position)) {
            return this.declarationAssignment;
        }
        for (AssignmentInfo assign : this.assignments) {
            if (!assign.containsRhsPosition(position)) continue;
            return assign;
        }
        return null;
    }

    public List<AssignmentInfo> getErroredAssignments() {
        ArrayList<AssignmentInfo> errored = new ArrayList<AssignmentInfo>();
        if (this.declarationAssignment != null && this.declarationAssignment.hasError()) {
            errored.add(this.declarationAssignment);
        }
        for (AssignmentInfo assign : this.assignments) {
            if (!assign.hasError()) continue;
            errored.add(assign);
        }
        return errored;
    }

    public void clearAssignments() {
        this.assignments.clear();
        this.declarationAssignment = null;
    }

    public boolean isFinal() {
        if (this.reflectionField != null) {
            return Modifier.isFinal(this.reflectionField.getModifiers());
        }
        return Modifier.isFinal(this.modifiers);
    }

    public boolean isStatic() {
        if (this.reflectionField != null) {
            return Modifier.isStatic(this.reflectionField.getModifiers());
        }
        return Modifier.isStatic(this.modifiers);
    }

    public boolean isPrivate() {
        if (this.reflectionField != null) {
            return Modifier.isPrivate(this.reflectionField.getModifiers());
        }
        return Modifier.isPrivate(this.modifiers);
    }

    public boolean isProtected() {
        if (this.reflectionField != null) {
            return Modifier.isProtected(this.reflectionField.getModifiers());
        }
        return Modifier.isProtected(this.modifiers);
    }

    public boolean isPublic() {
        if (this.reflectionField != null) {
            return Modifier.isPublic(this.reflectionField.getModifiers());
        }
        return Modifier.isPublic(this.modifiers);
    }

    public int getModifiers() {
        if (this.reflectionField != null) {
            return this.reflectionField.getModifiers();
        }
        return this.modifiers;
    }

    public Field getReflectionField() {
        return this.reflectionField;
    }

    public TypeInfo getInferredType() {
        return this.inferredType;
    }

    public void setVarArg(boolean isVarArg) {
        this.isVarArg = isVarArg;
    }

    public boolean isVarArg() {
        return this.isVarArg;
    }

    public void setInferredType(TypeInfo inferredType) {
        this.inferredType = inferredType;
    }

    public boolean canInferType() {
        return this.declaredType != null && "any".equals(this.declaredType.getFullName());
    }

    public TypeInfo getEffectiveType() {
        if (this.inferredType != null) {
            return this.inferredType;
        }
        return this.declaredType;
    }

    public String getName() {
        return this.name;
    }

    public Scope getScope() {
        return this.scope;
    }

    public TypeInfo getDeclaredType() {
        return this.declaredType;
    }

    public TypeInfo getTypeInfo() {
        return this.getEffectiveType();
    }

    public int getDeclarationOffset() {
        return this.declarationOffset;
    }

    public boolean isResolved() {
        TypeInfo effectiveType = this.getEffectiveType();
        return effectiveType != null && effectiveType.isResolved();
    }

    public MethodInfo getContainingMethod() {
        return this.containingMethod;
    }

    public String getDocumentation() {
        return this.documentation;
    }

    public JSDocInfo getJSDocInfo() {
        return this.jsDocInfo;
    }

    public void setJSDocInfo(JSDocInfo jsDocInfo) {
        this.jsDocInfo = jsDocInfo;
    }

    public FieldInfo substituteTypeParams(GenericContext context) {
        if (context == null || this.declaredType == null) {
            return this;
        }
        TypeInfo subType = context.substitute(this.declaredType);
        if (subType == this.declaredType) {
            return this;
        }
        FieldInfo result = new FieldInfo(this.name, this.scope, subType, this.declarationOffset, this.resolved, this.containingMethod, this.documentation, this.initStart, this.initEnd, this.modifiers, this.reflectionField);
        result.jsDocInfo = this.jsDocInfo;
        result.scopeInfo = this.scopeInfo;
        result.isVarArg = this.isVarArg;
        result.inferredType = this.inferredType;
        return result;
    }

    public int getInitStart() {
        return this.initStart;
    }

    public int getInitEnd() {
        return this.initEnd;
    }

    public boolean hasInitializer() {
        return this.initStart >= 0 && this.initEnd > this.initStart;
    }

    public boolean isGlobal() {
        return this.scope == Scope.GLOBAL;
    }

    public boolean isLocal() {
        return this.scope == Scope.LOCAL;
    }

    public boolean isParameter() {
        return this.scope == Scope.PARAMETER;
    }

    public boolean isVisibleAt(int position) {
        if (this.scopeInfo != null && !this.scopeInfo.containsPosition(position)) {
            return false;
        }
        if (this.scope == Scope.GLOBAL || this.scope == Scope.PARAMETER) {
            return true;
        }
        return position >= this.declarationOffset;
    }

    public ScopeInfo getScopeInfo() {
        return this.scopeInfo;
    }

    public void setScopeInfo(ScopeInfo scopeInfo) {
        this.scopeInfo = scopeInfo;
    }

    public TokenType getTokenType() {
        if (!this.resolved) {
            return TokenType.UNDEFINED_VAR;
        }
        switch (this.scope) {
            case GLOBAL: {
                if (this.isStatic() && this.isFinal()) {
                    return TokenType.STATIC_FINAL_FIELD;
                }
                return TokenType.GLOBAL_FIELD;
            }
            case LOCAL: {
                return TokenType.LOCAL_FIELD;
            }
            case PARAMETER: {
                return TokenType.PARAMETER;
            }
            case ENUM_CONSTANT: {
                return TokenType.ENUM_CONSTANT;
            }
        }
        return TokenType.VARIABLE;
    }

    public String toString() {
        return "FieldInfo{" + this.name + ", " + (Object)((Object)this.scope) + ", type=" + this.declaredType + ", final=" + this.isFinal() + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        FieldInfo fieldInfo = (FieldInfo)o;
        return this.name.equals(fieldInfo.name) && this.scope == fieldInfo.scope;
    }

    public int hashCode() {
        return this.name.hashCode() * 31 + this.scope.ordinal();
    }

    public static class ArgInfo {
        public final FieldInfo fieldInfo;
        public final MethodCallInfo methodCallInfo;

        public ArgInfo(FieldInfo fieldInfo, MethodCallInfo methodCallInfo) {
            this.fieldInfo = fieldInfo;
            this.methodCallInfo = methodCallInfo;
        }
    }

    public static enum Scope {
        GLOBAL,
        LOCAL,
        PARAMETER,
        ENUM_CONSTANT;

    }
}

