/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.jsdoc;

import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTag;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class JSDocReturnTag
extends JSDocTag {
    public JSDocReturnTag(String tagName, int atSignOffset, int tagNameStart, int tagNameEnd) {
        super(tagName, atSignOffset, tagNameStart, tagNameEnd);
    }

    public static JSDocReturnTag create(String tagName, int atSignOffset, int tagNameStart, int tagNameEnd, String typeName, TypeInfo typeInfo, int typeStart, int typeEnd, String description) {
        JSDocReturnTag tag = new JSDocReturnTag(tagName, atSignOffset, tagNameStart, tagNameEnd);
        tag.setType(typeName, typeInfo, typeStart, typeEnd);
        tag.setDescription(description);
        return tag;
    }

    @Override
    public String toString() {
        return "JSDocReturnTag{@" + this.tagName + " " + (this.typeName != null ? "{" + this.typeName + "}" : "") + "}";
    }
}

