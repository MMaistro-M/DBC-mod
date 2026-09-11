/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.attribute;

import kamkeel.npcs.controllers.data.attribute.AttributeValueType;
import noppes.npcs.api.handler.data.IAttributeDefinition;

public class AttributeDefinition
implements IAttributeDefinition {
    private final String key;
    private String translationKey;
    private final String displayName;
    private final char colorCode;
    private final AttributeValueType attributeValueType;
    private final AttributeSection section;

    public AttributeDefinition(String key, String displayName, char colorCode, AttributeValueType attributeValueType, AttributeSection section) {
        this.key = key;
        this.translationKey = "rpgcore:attribute." + key;
        this.displayName = displayName;
        this.attributeValueType = attributeValueType;
        this.section = section;
        this.colorCode = colorCode;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String getTranslationKey() {
        return this.translationKey;
    }

    public void setTranslationKey(String translationKey) {
        this.translationKey = translationKey;
    }

    public AttributeValueType getValueType() {
        return this.attributeValueType;
    }

    public AttributeSection getSection() {
        return this.section;
    }

    @Override
    public char getColorCode() {
        return this.colorCode;
    }

    public static enum AttributeSection {
        BASE,
        MODIFIER,
        STATS,
        INFO,
        EXTRA;

    }
}

