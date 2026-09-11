/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type.synthetic;

import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class SyntheticField {
    public final String name;
    public final String typeName;
    public final String documentation;
    public final boolean isStatic;

    SyntheticField(String name, String typeName, String documentation, boolean isStatic) {
        this.name = name;
        this.typeName = typeName;
        this.documentation = documentation;
        this.isStatic = isStatic;
    }

    public TypeInfo getTypeInfo() {
        TypeInfo type = TypeResolver.getInstance().resolve(this.typeName);
        if (type == null) {
            type = TypeInfo.unresolved(this.typeName, this.typeName);
        }
        return type;
    }

    public FieldInfo toFieldInfo() {
        TypeInfo type = TypeResolver.getInstance().resolve(this.typeName);
        if (type == null) {
            type = TypeInfo.unresolved(this.typeName, this.typeName);
        }
        int modifiers = 1;
        if (this.isStatic) {
            modifiers |= 8;
        }
        return FieldInfo.external(this.name, type, this.documentation, modifiers);
    }
}

