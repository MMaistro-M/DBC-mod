/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.attribute;

import kamkeel.npcs.controllers.data.attribute.AttributeDefinition;
import noppes.npcs.api.handler.data.ICustomAttribute;

public class PlayerAttribute
implements ICustomAttribute {
    private final AttributeDefinition attribute;
    public float value;

    public PlayerAttribute(AttributeDefinition attribute, float value) {
        this.attribute = attribute;
        this.value = value;
    }

    @Override
    public AttributeDefinition getAttribute() {
        return this.attribute;
    }

    @Override
    public float getValue() {
        return this.value;
    }
}

