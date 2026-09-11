/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.js_parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSFieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.TypeParamInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocParamTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocReturnTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTypeTag;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class JSTypeInfo {
    private final String simpleName;
    private final String fullName;
    private final String namespace;
    private String javaFqn;
    private final List<TypeParamInfo> typeParams = new ArrayList<TypeParamInfo>();
    private final Map<String, JSMethodInfo> methods = new LinkedHashMap<String, JSMethodInfo>();
    private final Map<String, JSFieldInfo> fields = new LinkedHashMap<String, JSFieldInfo>();
    private String extendsType;
    private JSTypeInfo resolvedParent;
    private final Map<String, JSTypeInfo> innerTypes = new LinkedHashMap<String, JSTypeInfo>();
    private JSTypeInfo parentType;
    private JSDocInfo jsDocInfo;

    public JSTypeInfo(String simpleName, String namespace) {
        this.simpleName = simpleName;
        this.namespace = namespace;
        this.fullName = namespace != null ? namespace + "." + simpleName : simpleName;
    }

    public JSTypeInfo setExtends(String extendsType) {
        this.extendsType = extendsType;
        return this;
    }

    public JSTypeInfo setJsDocInfo(JSDocInfo jsDocInfo) {
        this.jsDocInfo = jsDocInfo;
        return this;
    }

    public JSTypeInfo setJavaFqn(String javaFqn) {
        this.javaFqn = javaFqn;
        return this;
    }

    public void addMethod(JSMethodInfo method) {
        method.setContainingType(this);
        String key = method.getName();
        if (this.methods.containsKey(key)) {
            int index = 1;
            while (this.methods.containsKey(key + "$" + index)) {
                ++index;
            }
            this.methods.put(key + "$" + index, method);
        } else {
            this.methods.put(key, method);
        }
    }

    public void addField(JSFieldInfo field) {
        field.setContainingType(this);
        this.fields.put(field.getName(), field);
    }

    public void addInnerType(JSTypeInfo inner) {
        inner.parentType = this;
        this.innerTypes.put(inner.getSimpleName(), inner);
    }

    public void setResolvedParent(JSTypeInfo parent) {
        this.resolvedParent = parent;
    }

    public String getSimpleName() {
        return this.simpleName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getJavaFqn() {
        return this.javaFqn;
    }

    public String getExtendsType() {
        return this.extendsType;
    }

    public JSTypeInfo getResolvedParent() {
        return this.resolvedParent;
    }

    public JSDocInfo getJsDocInfo() {
        return this.jsDocInfo;
    }

    public JSTypeInfo getParentType() {
        return this.parentType;
    }

    public List<TypeParamInfo> getTypeParams() {
        return this.typeParams;
    }

    public Map<String, JSMethodInfo> getMethods() {
        return this.methods;
    }

    public Map<String, JSFieldInfo> getFields() {
        return this.fields;
    }

    public Map<String, JSTypeInfo> getInnerTypes() {
        return this.innerTypes;
    }

    public TypeParamInfo getTypeParam(String name) {
        for (TypeParamInfo param : this.typeParams) {
            if (!param.getName().equals(name)) continue;
            return param;
        }
        return null;
    }

    public void addTypeParam(TypeParamInfo param) {
        this.typeParams.add(param);
    }

    public void resolveTypeParameters() {
        for (TypeParamInfo param : this.typeParams) {
            param.resolveBoundType();
        }
    }

    public void resolveMemberTypes() {
        TypeResolver resolver = TypeResolver.getInstance();
        for (JSMethodInfo method : this.methods.values()) {
            TypeInfo returnTypeInfo = resolver.resolveJSType(method.getReturnType());
            method.setReturnTypeInfo(returnTypeInfo);
            for (JSMethodInfo.JSParameterInfo param : method.getParameters()) {
                TypeInfo paramTypeInfo = resolver.resolveJSType(param.getType());
                param.setTypeInfo(paramTypeInfo);
            }
        }
        for (JSFieldInfo field : this.fields.values()) {
            TypeInfo fieldTypeInfo = resolver.resolveJSType(field.getType());
            field.setTypeInfo(fieldTypeInfo);
        }
    }

    public void resolveJSDocTypes() {
        TypeResolver resolver = TypeResolver.getInstance();
        if (this.jsDocInfo != null) {
            this.resolveJSDocInfoTypes(this.jsDocInfo, resolver);
        }
        for (JSMethodInfo method : this.methods.values()) {
            JSDocInfo methodDoc = method.getJsDocInfo();
            if (methodDoc == null) continue;
            this.resolveJSDocInfoTypes(methodDoc, resolver);
        }
        for (JSFieldInfo field : this.fields.values()) {
            JSDocInfo fieldDoc = field.getJsDocInfo();
            if (fieldDoc == null) continue;
            this.resolveJSDocInfoTypes(fieldDoc, resolver);
        }
    }

    private void resolveJSDocInfoTypes(JSDocInfo jsDoc, TypeResolver resolver) {
        JSDocTypeTag typeTag = jsDoc.getTypeTag();
        if (typeTag != null && typeTag.hasType() && typeTag.getTypeInfo() == null) {
            TypeInfo resolved = resolver.resolveJSType(typeTag.getTypeName());
            typeTag.setType(typeTag.getTypeName(), resolved, typeTag.getTypeStart(), typeTag.getTypeEnd());
        }
        for (JSDocParamTag paramTag : jsDoc.getParamTags()) {
            if (!paramTag.hasType() || paramTag.getTypeInfo() != null) continue;
            TypeInfo resolved = resolver.resolveJSType(paramTag.getTypeName());
            paramTag.setType(paramTag.getTypeName(), resolved, paramTag.getTypeStart(), paramTag.getTypeEnd());
        }
        JSDocReturnTag returnTag = jsDoc.getReturnTag();
        if (returnTag != null && returnTag.hasType() && returnTag.getTypeInfo() == null) {
            TypeInfo resolved = resolver.resolveJSType(returnTag.getTypeName());
            returnTag.setType(returnTag.getTypeName(), resolved, returnTag.getTypeStart(), returnTag.getTypeEnd());
        }
    }

    public JSMethodInfo getMethod(String name) {
        JSMethodInfo method = this.methods.get(name);
        if (method != null) {
            return method;
        }
        if (this.resolvedParent != null) {
            return this.resolvedParent.getMethod(name);
        }
        return null;
    }

    public List<JSMethodInfo> getMethodOverloads(String name) {
        ArrayList<JSMethodInfo> overloads = new ArrayList<JSMethodInfo>();
        if (this.methods.containsKey(name)) {
            overloads.add(this.methods.get(name));
        }
        int index = 1;
        while (this.methods.containsKey(name + "$" + index)) {
            overloads.add(this.methods.get(name + "$" + index));
            ++index;
        }
        if (this.resolvedParent != null) {
            overloads.addAll(this.resolvedParent.getMethodOverloads(name));
        }
        return overloads;
    }

    public boolean hasMethod(String name) {
        return this.getMethod(name) != null;
    }

    public JSFieldInfo getField(String name) {
        JSFieldInfo field = this.fields.get(name);
        if (field != null) {
            return field;
        }
        if (this.resolvedParent != null) {
            return this.resolvedParent.getField(name);
        }
        return null;
    }

    public boolean hasField(String name) {
        return this.getField(name) != null;
    }

    public JSTypeInfo getInnerType(String name) {
        return this.innerTypes.get(name);
    }

    public String toString() {
        return "JSTypeInfo{" + this.fullName + "}";
    }
}

