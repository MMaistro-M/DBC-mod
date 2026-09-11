/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.js_parser;

import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericContext;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class JSFieldInfo {
    private final String name;
    private final String type;
    private TypeInfo typeInfo;
    private final boolean readonly;
    private boolean isStatic;
    private JSDocInfo jsDocInfo;
    private JSTypeInfo containingType;

    public JSFieldInfo(String name, String type, boolean readonly) {
        this.name = name;
        this.type = type;
        this.readonly = readonly;
    }

    public JSFieldInfo setJsDocInfo(JSDocInfo jsDocInfo) {
        this.jsDocInfo = jsDocInfo;
        return this;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    public JSFieldInfo setStatic(boolean isStatic) {
        this.isStatic = isStatic;
        return this;
    }

    public void setContainingType(JSTypeInfo containingType) {
        this.containingType = containingType;
    }

    public void setTypeInfo(TypeInfo typeInfo) {
        this.typeInfo = typeInfo;
    }

    public TypeInfo getResolvedType(TypeInfo contextType) {
        TypeInfo resolved;
        TypeResolver resolver = TypeResolver.getInstance();
        TypeInfo typeInfo = resolved = this.typeInfo != null ? this.typeInfo : resolver.resolveJSType(this.type);
        if (this.typeInfo == null) {
            this.typeInfo = resolved;
        }
        if (contextType != null) {
            GenericContext ctx = GenericContext.forReceiver(contextType);
            return ctx.substituteType(resolved, this.type, resolver);
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

    public boolean isReadonly() {
        return this.readonly;
    }

    public JSDocInfo getJsDocInfo() {
        return this.jsDocInfo;
    }

    public JSTypeInfo getContainingType() {
        return this.containingType;
    }

    public String getDocumentation() {
        return this.jsDocInfo != null ? this.jsDocInfo.getDescription() : null;
    }

    public String getDisplayType() {
        if (this.typeInfo != null && this.typeInfo.isResolved()) {
            return this.typeInfo.getDisplayName();
        }
        return this.type;
    }

    public String buildHoverInfo() {
        StringBuilder sb = new StringBuilder();
        if (this.readonly) {
            sb.append("<i>readonly</i> ");
        }
        sb.append("<b>").append(this.name).append("</b>: <i>").append(this.type).append("</i>");
        if (this.jsDocInfo != null && this.jsDocInfo.getDescription() != null && !this.jsDocInfo.getDescription().isEmpty()) {
            sb.append("<br><br>").append(this.jsDocInfo.getDescription());
        }
        return sb.toString();
    }

    public String toString() {
        return (this.readonly ? "readonly " : "") + this.name + ": " + this.type;
    }
}

