/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type.synthetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocParamTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocReturnTag;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticParameter;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticTypeBuilder;

public class SyntheticMethod {
    public final String name;
    public final String returnType;
    public final List<SyntheticParameter> parameters;
    public final String documentation;
    public final boolean isStatic;
    public final SyntheticTypeBuilder.ReturnTypeResolver returnTypeResolver;

    SyntheticMethod(String name, String returnType, List<SyntheticParameter> parameters, String documentation, boolean isStatic, SyntheticTypeBuilder.ReturnTypeResolver returnTypeResolver) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = Collections.unmodifiableList(new ArrayList<SyntheticParameter>(parameters));
        this.documentation = documentation;
        this.isStatic = isStatic;
        this.returnTypeResolver = returnTypeResolver;
    }

    public TypeInfo getReturnTypeInfo() {
        TypeInfo returnTypeInfo = TypeResolver.getInstance().resolve(this.returnType);
        if (returnTypeInfo == null) {
            returnTypeInfo = TypeInfo.unresolved(this.returnType, this.returnType);
        }
        return returnTypeInfo;
    }

    public TypeInfo resolveReturnType(String[] arguments) {
        if (this.returnTypeResolver != null) {
            return this.returnTypeResolver.resolve(arguments);
        }
        return this.getReturnTypeInfo();
    }

    public MethodInfo toMethodInfo(TypeInfo containingType) {
        ArrayList<FieldInfo> paramInfos = new ArrayList<FieldInfo>();
        for (SyntheticParameter param : this.parameters) {
            TypeInfo paramType;
            boolean isVarArg;
            String typeName = param.typeName;
            boolean bl = isVarArg = typeName != null && typeName.endsWith("...");
            if (isVarArg) {
                typeName = typeName.substring(0, typeName.length() - 3);
            }
            if ((paramType = TypeResolver.getInstance().resolve(typeName)) == null) {
                paramType = TypeInfo.unresolved(typeName, typeName);
            }
            FieldInfo fieldInfo = FieldInfo.parameter(param.name, paramType, -1, null);
            fieldInfo.setVarArg(isVarArg);
            paramInfos.add(fieldInfo);
        }
        TypeInfo returnTypeInfo = TypeResolver.getInstance().resolve(this.returnType);
        if (returnTypeInfo == null) {
            returnTypeInfo = TypeInfo.unresolved(this.returnType, this.returnType);
        }
        int modifiers = 1;
        if (this.isStatic) {
            modifiers |= 8;
        }
        MethodInfo methodInfo = MethodInfo.external(this.name, returnTypeInfo, containingType, paramInfos, modifiers, null);
        if (this.documentation != null && !this.documentation.isEmpty()) {
            JSDocInfo jsDocInfo = this.createJSDocInfo(returnTypeInfo);
            methodInfo.setJSDocInfo(jsDocInfo);
        }
        return methodInfo;
    }

    private JSDocInfo createJSDocInfo(TypeInfo returnTypeInfo) {
        String[] lines = this.documentation.split("\\n");
        StringBuilder descBuilder = new StringBuilder();
        for (String line : lines) {
            if ((line = line.trim()).startsWith("@param") || line.startsWith("@returns") || line.startsWith("@return")) break;
            if (line.isEmpty()) continue;
            if (descBuilder.length() > 0) {
                descBuilder.append("\n");
            }
            descBuilder.append(line);
        }
        JSDocInfo jsDocInfo = new JSDocInfo(this.documentation, -1, -1);
        jsDocInfo.setDescription(descBuilder.toString());
        for (int i = 0; i < this.parameters.size(); ++i) {
            SyntheticParameter param = this.parameters.get(i);
            TypeInfo paramType = TypeResolver.getInstance().resolve(param.typeName);
            if (paramType == null) {
                paramType = TypeInfo.unresolved(param.typeName, param.typeName);
            }
            JSDocParamTag paramTag = JSDocParamTag.create(-1, -1, -1, param.typeName, paramType, -1, -1, param.name, -1, -1, param.documentation);
            jsDocInfo.addParamTag(paramTag);
        }
        JSDocReturnTag returnTag = JSDocReturnTag.create("returns", -1, -1, -1, returnTypeInfo.getSimpleName(), returnTypeInfo, -1, -1, null);
        jsDocInfo.setReturnTag(returnTag);
        return jsDocInfo;
    }

    public String getSignature() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append("(");
        for (int i = 0; i < this.parameters.size(); ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            SyntheticParameter p = this.parameters.get(i);
            sb.append(p.name).append(": ").append(p.typeName);
        }
        sb.append("): ").append(this.returnType);
        return sb.toString();
    }
}

