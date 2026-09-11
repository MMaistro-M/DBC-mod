/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.jsdoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocParamTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocReturnTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocSeeTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocTypeTag;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class JSDocInfo {
    private final String rawComment;
    private final int startOffset;
    private final int endOffset;
    private JSDocTypeTag typeTag;
    private final List<JSDocParamTag> paramTags = new ArrayList<JSDocParamTag>();
    private JSDocReturnTag returnTag;
    private final List<JSDocSeeTag> seeTags = new ArrayList<JSDocSeeTag>();
    private String description;
    private final List<JSDocTag> allTags = new ArrayList<JSDocTag>();

    public JSDocInfo(String rawComment, int startOffset, int endOffset) {
        this.rawComment = rawComment;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public void setTypeTag(JSDocTypeTag typeTag) {
        this.typeTag = typeTag;
        this.allTags.add(typeTag);
    }

    public void addParamTag(JSDocParamTag paramTag) {
        this.paramTags.add(paramTag);
        this.allTags.add(paramTag);
    }

    public void setReturnTag(JSDocReturnTag returnTag) {
        this.returnTag = returnTag;
        this.allTags.add(returnTag);
    }

    public void addSeeTag(JSDocSeeTag seeTag) {
        this.seeTags.add(seeTag);
        this.allTags.add(seeTag);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addTag(JSDocTag tag) {
        this.allTags.add(tag);
    }

    public String getRawComment() {
        return this.rawComment;
    }

    public int getStartOffset() {
        return this.startOffset;
    }

    public int getEndOffset() {
        return this.endOffset;
    }

    public JSDocTypeTag getTypeTag() {
        return this.typeTag;
    }

    public boolean hasTypeTag() {
        return this.typeTag != null;
    }

    public TypeInfo getDeclaredType() {
        return this.typeTag != null ? this.typeTag.getTypeInfo() : null;
    }

    public List<JSDocParamTag> getParamTags() {
        return Collections.unmodifiableList(this.paramTags);
    }

    public boolean hasParamTags() {
        return !this.paramTags.isEmpty();
    }

    public JSDocParamTag getParamTag(String paramName) {
        for (JSDocParamTag tag : this.paramTags) {
            String tagName = tag.getParamName();
            if (tagName == null || !tagName.equals(paramName)) continue;
            return tag;
        }
        return null;
    }

    public JSDocReturnTag getReturnTag() {
        return this.returnTag;
    }

    public boolean hasReturnTag() {
        return this.returnTag != null;
    }

    public TypeInfo getReturnType() {
        return this.returnTag != null ? this.returnTag.getTypeInfo() : null;
    }

    public List<JSDocSeeTag> getSeeTags() {
        return Collections.unmodifiableList(this.seeTags);
    }

    public boolean hasSeeTags() {
        return !this.seeTags.isEmpty();
    }

    public String getDescription() {
        return this.description;
    }

    public List<JSDocTag> getAllTags() {
        return Collections.unmodifiableList(this.allTags);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("JSDocInfo{");
        if (this.typeTag != null) {
            sb.append("type=").append(this.typeTag.getTypeName());
        }
        if (!this.paramTags.isEmpty()) {
            sb.append(", params=").append(this.paramTags.size());
        }
        if (this.returnTag != null) {
            sb.append(", return=").append(this.returnTag.getTypeName());
        }
        if (!this.seeTags.isEmpty()) {
            sb.append(", see=").append(this.seeTags.size());
        }
        sb.append("}");
        return sb.toString();
    }
}

