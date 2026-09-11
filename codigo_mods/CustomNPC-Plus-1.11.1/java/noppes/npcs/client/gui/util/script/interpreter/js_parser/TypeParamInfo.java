/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.js_parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class TypeParamInfo {
    private final String name;
    private final String boundType;
    private final String fullBoundType;
    private TypeInfo boundTypeInfo;
    private final List<String> additionalBoundNames;
    private final List<TypeInfo> additionalBoundTypes;

    public TypeParamInfo(String name, String boundType, String fullBoundType) {
        this.name = name;
        this.boundType = boundType;
        this.fullBoundType = fullBoundType;
        this.boundTypeInfo = null;
        this.additionalBoundNames = new ArrayList<String>();
        this.additionalBoundTypes = new ArrayList<TypeInfo>();
    }

    public String getName() {
        return this.name;
    }

    public TypeInfo getBoundTypeInfo() {
        return this.boundTypeInfo;
    }

    public void setBoundTypeInfo(TypeInfo boundTypeInfo) {
        this.boundTypeInfo = boundTypeInfo;
    }

    public void addAdditionalBound(String boundName) {
        this.additionalBoundNames.add(boundName);
    }

    public void addAdditionalBoundType(TypeInfo typeInfo) {
        this.additionalBoundTypes.add(typeInfo);
    }

    public List<String> getAdditionalBoundNames() {
        return Collections.unmodifiableList(this.additionalBoundNames);
    }

    public List<TypeInfo> getAdditionalBoundTypes() {
        return Collections.unmodifiableList(this.additionalBoundTypes);
    }

    public boolean hasAdditionalBounds() {
        return !this.additionalBoundNames.isEmpty();
    }

    public void resolveBoundType() {
        if (this.boundTypeInfo != null) {
            return;
        }
        if (this.fullBoundType != null && !this.fullBoundType.isEmpty()) {
            this.boundTypeInfo = TypeResolver.getInstance().resolveFullName(this.fullBoundType);
        } else if (this.boundType != null && !this.boundType.isEmpty()) {
            this.boundTypeInfo = TypeResolver.getInstance().resolveJSType(this.boundType);
        }
    }

    public String getBoundTypeName() {
        if (this.boundTypeInfo == null) {
            return null;
        }
        return this.boundTypeInfo.getDisplayName();
    }

    public String toString() {
        int i;
        StringBuilder sb = new StringBuilder(this.name);
        if (this.boundTypeInfo != null) {
            sb.append(" extends ").append(this.boundTypeInfo.getDisplayName());
        } else if (this.boundType != null || this.fullBoundType != null) {
            sb.append(" extends ").append(this.boundType != null ? this.boundType : this.fullBoundType);
        }
        for (i = 0; i < this.additionalBoundTypes.size(); ++i) {
            sb.append(" & ").append(this.additionalBoundTypes.get(i).getDisplayName());
        }
        for (i = this.additionalBoundTypes.size(); i < this.additionalBoundNames.size(); ++i) {
            sb.append(" & ").append(this.additionalBoundNames.get(i));
        }
        return sb.toString();
    }
}

