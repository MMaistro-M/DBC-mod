/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.attribute;

import java.util.HashMap;
import java.util.Map;
import kamkeel.npcs.controllers.data.attribute.AttributeDefinition;
import kamkeel.npcs.controllers.data.attribute.PlayerAttribute;

public class PlayerAttributeMap {
    public final Map<AttributeDefinition, PlayerAttribute> map = new HashMap<AttributeDefinition, PlayerAttribute>();

    public PlayerAttribute registerAttribute(AttributeDefinition attribute, float baseValue) {
        if (this.map.containsKey(attribute)) {
            throw new IllegalArgumentException("Attribute already registered: " + attribute.getKey());
        }
        PlayerAttribute instance = new PlayerAttribute(attribute, baseValue);
        this.map.put(attribute, instance);
        return instance;
    }

    public PlayerAttribute getAttributeInstance(AttributeDefinition attribute) {
        return this.map.get(attribute);
    }
}

