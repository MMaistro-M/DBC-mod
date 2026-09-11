/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.jsdoc;

import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTag;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class JSDocParamTag
extends JSDocTag {
    private final String paramName;
    private final int paramNameStart;
    private final int paramNameEnd;

    public JSDocParamTag(int atSignOffset, int tagNameStart, int tagNameEnd, String paramName, int paramNameStart, int paramNameEnd) {
        super("param", atSignOffset, tagNameStart, tagNameEnd);
        this.paramName = paramName;
        this.paramNameStart = paramNameStart;
        this.paramNameEnd = paramNameEnd;
    }

    public static JSDocParamTag create(int atSignOffset, int tagNameStart, int tagNameEnd, String typeName, TypeInfo typeInfo, int typeStart, int typeEnd, String paramName, int paramNameStart, int paramNameEnd, String description) {
        JSDocParamTag tag = new JSDocParamTag(atSignOffset, tagNameStart, tagNameEnd, paramName, paramNameStart, paramNameEnd);
        tag.setType(typeName, typeInfo, typeStart, typeEnd);
        tag.setDescription(description);
        return tag;
    }

    public String getParamName() {
        return this.paramName;
    }

    public int getParamNameStart() {
        return this.paramNameStart;
    }

    public int getParamNameEnd() {
        return this.paramNameEnd;
    }

    @Override
    public String toString() {
        return "JSDocParamTag{@param " + (this.typeName != null ? "{" + this.typeName + "} " : "") + this.paramName + "}";
    }
}

