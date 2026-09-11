/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.type.synthetic;

import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;

public class SyntheticParameter {
    public final String name;
    public final String typeName;
    public final String documentation;

    SyntheticParameter(String name, String typeName, String documentation) {
        this.name = name;
        this.typeName = typeName;
        this.documentation = documentation;
    }

    public TypeInfo getTypeInfo() {
        TypeInfo type = TypeResolver.getInstance().resolve(this.typeName);
        if (type == null) {
            type = TypeInfo.unresolved(this.typeName, this.typeName);
        }
        return type;
    }
}

