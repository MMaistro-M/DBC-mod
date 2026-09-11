/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class InnerCallableScope {
    private final Kind kind;
    private final int headerStart;
    private final int headerEnd;
    private final int bodyStart;
    private final int bodyEnd;
    private final List<FieldInfo> parameters = new ArrayList<FieldInfo>();
    private final Map<String, FieldInfo> locals = new HashMap<String, FieldInfo>();
    private InnerCallableScope parentScope;
    private TypeInfo expectedType;
    private TypeInfo containingObjectType;

    public InnerCallableScope(Kind kind, int headerStart, int headerEnd, int bodyStart, int bodyEnd) {
        this.kind = kind;
        this.headerStart = headerStart;
        this.headerEnd = headerEnd;
        this.bodyStart = bodyStart;
        this.bodyEnd = bodyEnd;
    }

    public Kind getKind() {
        return this.kind;
    }

    public int getHeaderStart() {
        return this.headerStart;
    }

    public int getHeaderEnd() {
        return this.headerEnd;
    }

    public int getBodyStart() {
        return this.bodyStart;
    }

    public int getBodyEnd() {
        return this.bodyEnd;
    }

    public List<FieldInfo> getParameters() {
        return this.parameters;
    }

    public Map<String, FieldInfo> getLocals() {
        return this.locals;
    }

    public InnerCallableScope getParentScope() {
        return this.parentScope;
    }

    public TypeInfo getExpectedType() {
        return this.expectedType;
    }

    public TypeInfo getContainingObjectType() {
        return this.containingObjectType;
    }

    public void setParentScope(InnerCallableScope parent) {
        this.parentScope = parent;
    }

    public void setExpectedType(TypeInfo type) {
        this.expectedType = type;
    }

    public void setContainingObjectType(TypeInfo type) {
        this.containingObjectType = type;
    }

    public void addParameter(FieldInfo param) {
        this.parameters.add(param);
    }

    public void addLocal(String name, FieldInfo local) {
        this.locals.put(name, local);
    }

    public FieldInfo getParameter(String name) {
        for (FieldInfo param : this.parameters) {
            if (!param.getName().equals(name)) continue;
            return param;
        }
        return null;
    }

    public boolean hasParameter(String name) {
        return this.getParameter(name) != null;
    }

    public boolean containsPosition(int position) {
        return position >= this.bodyStart && position < this.bodyEnd;
    }

    public boolean containsHeaderPosition(int position) {
        return position >= this.headerStart && position < this.headerEnd;
    }

    public int getFullStart() {
        return this.headerStart;
    }

    public int getFullEnd() {
        return this.bodyEnd;
    }

    public static enum Kind {
        JAVA_LAMBDA,
        JS_FUNCTION_EXPR,
        JS_ARROW_FUNC,
        JS_SHORTHAND_METHOD;

    }
}

