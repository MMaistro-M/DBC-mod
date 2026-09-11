/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.jsdoc;

import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTag;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class JSDocTypeTag
extends JSDocTag {
    public JSDocTypeTag(int atSignOffset, int tagNameStart, int tagNameEnd) {
        super("type", atSignOffset, tagNameStart, tagNameEnd);
    }

    public static JSDocTypeTag create(int atSignOffset, int tagNameStart, int tagNameEnd, String typeName, TypeInfo typeInfo, int typeStart, int typeEnd, String description) {
        JSDocTypeTag tag = new JSDocTypeTag(atSignOffset, tagNameStart, tagNameEnd);
        tag.setType(typeName, typeInfo, typeStart, typeEnd);
        tag.setDescription(description);
        return tag;
    }

    @Override
    public String toString() {
        return "JSDocTypeTag{@type " + (this.typeName != null ? "{" + this.typeName + "}" : "") + "}";
    }
}

