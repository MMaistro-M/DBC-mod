/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.jsdoc;

import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class JSDocTag {
    protected final String tagName;
    protected final int atSignOffset;
    protected final int tagNameStart;
    protected final int tagNameEnd;
    protected String typeName;
    protected TypeInfo typeInfo;
    protected int typeStart = -1;
    protected int typeEnd = -1;
    protected String description;

    public JSDocTag(String tagName, int atSignOffset, int tagNameStart, int tagNameEnd) {
        this.tagName = tagName;
        this.atSignOffset = atSignOffset;
        this.tagNameStart = tagNameStart;
        this.tagNameEnd = tagNameEnd;
    }

    public void setType(String typeName, TypeInfo typeInfo, int typeStart, int typeEnd) {
        this.typeName = typeName;
        this.typeInfo = typeInfo;
        this.typeStart = typeStart;
        this.typeEnd = typeEnd;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagName() {
        return this.tagName;
    }

    public int getAtSignOffset() {
        return this.atSignOffset;
    }

    public int getTagNameStart() {
        return this.tagNameStart;
    }

    public int getTagNameEnd() {
        return this.tagNameEnd;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public TypeInfo getTypeInfo() {
        return this.typeInfo;
    }

    public boolean hasType() {
        return this.typeName != null && !this.typeName.isEmpty();
    }

    public int getTypeStart() {
        return this.typeStart;
    }

    public int getTypeEnd() {
        return this.typeEnd;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "JSDocTag{@" + this.tagName + (this.typeName != null ? " {" + this.typeName + "}" : "") + "}";
    }
}

