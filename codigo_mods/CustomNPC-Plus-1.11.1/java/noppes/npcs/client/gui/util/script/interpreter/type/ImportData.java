/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.token.Token;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public final class ImportData {
    private final String fullPath;
    private final String simpleName;
    private final boolean isWildcard;
    private final boolean isStatic;
    private final int startOffset;
    private final int endOffset;
    private final int pathStartOffset;
    private final int pathEndOffset;
    private TypeInfo resolvedType;
    private boolean resolved;
    private int usageCount = 0;
    private final List<Token> referencingTokens = new ArrayList<Token>();

    public ImportData(String fullPath, String simpleName, boolean isWildcard, boolean isStatic, int startOffset, int endOffset, int pathStartOffset, int pathEndOffset) {
        this.fullPath = fullPath;
        this.simpleName = simpleName;
        this.isWildcard = isWildcard;
        this.isStatic = isStatic;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        this.pathStartOffset = pathStartOffset;
        this.pathEndOffset = pathEndOffset;
        this.resolved = false;
    }

    public String getFullPath() {
        return this.fullPath;
    }

    public String getSimpleName() {
        return this.simpleName;
    }

    public boolean isWildcard() {
        return this.isWildcard;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    public int getStartOffset() {
        return this.startOffset;
    }

    public int getEndOffset() {
        return this.endOffset;
    }

    public int getPathStartOffset() {
        return this.pathStartOffset;
    }

    public int getPathEndOffset() {
        return this.pathEndOffset;
    }

    public TypeInfo getResolvedType() {
        return this.resolvedType;
    }

    public boolean isResolved() {
        return this.resolved;
    }

    public void setResolvedType(TypeInfo typeInfo) {
        this.resolvedType = typeInfo;
        this.resolved = typeInfo != null && typeInfo.isResolved();
    }

    public void markResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public void incrementUsage() {
        ++this.usageCount;
    }

    public void addReference(Token token) {
        if (token != null && !this.referencingTokens.contains(token)) {
            this.referencingTokens.add(token);
        }
    }

    public List<Token> getReferencingTokens() {
        return Collections.unmodifiableList(this.referencingTokens);
    }

    public boolean isUsed() {
        return this.usageCount > 0 || !this.referencingTokens.isEmpty();
    }

    public int getUsageCount() {
        return this.usageCount;
    }

    public int getReferenceCount() {
        return this.referencingTokens.size();
    }

    public void clearReferences() {
        this.referencingTokens.clear();
        this.usageCount = 0;
    }

    public String getPackagePortion() {
        if (this.isWildcard) {
            return this.fullPath;
        }
        int lastDot = this.fullPath.lastIndexOf(46);
        return lastDot > 0 ? this.fullPath.substring(0, lastDot) : "";
    }

    public String[] getPathSegments() {
        return this.fullPath.split("\\.");
    }

    public boolean couldProvide(String className) {
        if (this.isWildcard) {
            return true;
        }
        return className.equals(this.simpleName);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("import ");
        if (this.isStatic) {
            sb.append("static ");
        }
        sb.append(this.fullPath);
        if (this.isWildcard) {
            sb.append(".*");
        }
        sb.append(" [").append(this.resolved ? "resolved" : "unresolved").append("]");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ImportData that = (ImportData)o;
        return this.fullPath.equals(that.fullPath) && this.isWildcard == that.isWildcard && this.isStatic == that.isStatic;
    }

    public int hashCode() {
        return this.fullPath.hashCode() * 31 + (this.isWildcard ? 1 : 0) + (this.isStatic ? 2 : 0);
    }
}

