/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.js_parser;

import java.util.ArrayList;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericContext;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class JSMethodInfo {
    private final String name;
    private final String returnType;
    private TypeInfo returnTypeInfo;
    private final List<JSParameterInfo> parameters;
    private boolean isStatic;
    private JSDocInfo jsDocInfo;
    private JSTypeInfo containingType;

    public JSMethodInfo(String name, String returnType, List<JSParameterInfo> parameters) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters != null ? new ArrayList<JSParameterInfo>(parameters) : new ArrayList();
    }

    public JSMethodInfo setJsDocInfo(JSDocInfo jsDocInfo) {
        this.jsDocInfo = jsDocInfo;
        return this;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    public JSMethodInfo setStatic(boolean isStatic) {
        this.isStatic = isStatic;
        return this;
    }

    public void setReturnTypeInfo(TypeInfo typeInfo) {
        this.returnTypeInfo = typeInfo;
    }

    public void setContainingType(JSTypeInfo containingType) {
        this.containingType = containingType;
        for (JSParameterInfo param : this.parameters) {
            param.setContainingMethod(this);
        }
    }

    public TypeInfo getResolvedReturnType(TypeInfo contextType) {
        TypeResolver resolver = TypeResolver.getInstance();
        TypeInfo resolved = resolver.resolveJSType(this.returnType);
        if (contextType != null) {
            GenericContext ctx = GenericContext.forReceiver(contextType);
            resolved = ctx.substituteType(resolved, this.returnType, resolver);
        }
        return resolved;
    }

    public String getName() {
        return this.name;
    }

    public String getReturnType() {
        return this.returnType;
    }

    public TypeInfo getReturnTypeInfo() {
        return this.returnTypeInfo;
    }

    public List<JSParameterInfo> getParameters() {
        return this.parameters;
    }

    public JSDocInfo getJsDocInfo() {
        return this.jsDocInfo;
    }

    public int getParameterCount() {
        return this.parameters.size();
    }

    public JSTypeInfo getContainingType() {
        return this.containingType;
    }

    public String getDocumentation() {
        return this.jsDocInfo != null ? this.jsDocInfo.getDescription() : null;
    }

    public String getSignature() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name).append("(");
        for (int i = 0; i < this.parameters.size(); ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            JSParameterInfo param = this.parameters.get(i);
            sb.append(param.getName()).append(": ").append(param.getType());
        }
        sb.append("): ").append(this.returnType);
        return sb.toString();
    }

    public String buildHoverInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>").append(this.name).append("</b>(");
        for (int i = 0; i < this.parameters.size(); ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            JSParameterInfo param = this.parameters.get(i);
            sb.append(param.getName()).append(": <i>").append(param.getType()).append("</i>");
        }
        sb.append("): <i>").append(this.returnType).append("</i>");
        if (this.jsDocInfo != null && this.jsDocInfo.getDescription() != null && !this.jsDocInfo.getDescription().isEmpty()) {
            sb.append("<br><br>").append(this.jsDocInfo.getDescription());
        }
        return sb.toString();
    }

    public String toString() {
        return this.getSignature();
    }

    public static class JSParameterInfo {
        private final String name;
        private final String type;
        private TypeInfo typeInfo;
        private JSMethodInfo containingMethod;
        private boolean isVarArg = false;

        public JSParameterInfo(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public void setVarArg(boolean isVarArg) {
            this.isVarArg = isVarArg;
        }

        public boolean isVarArg() {
            return this.isVarArg;
        }

        public void setContainingMethod(JSMethodInfo containingMethod) {
            this.containingMethod = containingMethod;
        }

        public void setTypeInfo(TypeInfo typeInfo) {
            this.typeInfo = typeInfo;
        }

        public TypeInfo getResolvedType(TypeInfo contextType) {
            TypeResolver resolver = TypeResolver.getInstance();
            TypeInfo resolved = resolver.resolveJSType(this.type);
            if (contextType != null) {
                GenericContext ctx = GenericContext.forReceiver(contextType);
                resolved = ctx.substituteType(resolved, this.type, resolver);
            }
            return resolved;
        }

        public String getName() {
            return this.name;
        }

        public String getType() {
            return this.type;
        }

        public TypeInfo getTypeInfo() {
            return this.typeInfo;
        }

        public JSMethodInfo getContainingMethod() {
            return this.containingMethod;
        }

        public String getDisplayType() {
            if (this.typeInfo != null && this.typeInfo.isResolved()) {
                return this.typeInfo.getDisplayName();
            }
            return this.type;
        }

        public String toString() {
            return this.name + ": " + this.type;
        }
    }
}

