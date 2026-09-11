/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.skin.data;

import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperties;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperty;

public class SkinProperty<T>
implements ISkinProperty<T> {
    private String key;
    private T defaultValue;
    private T property;

    public SkinProperty(String key, T defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public String getKey() {
        return this.key;
    }

    public T getValue(ISkinProperties properties) {
        return (T)properties.getProperty(this.key, this.defaultValue);
    }

    public void setValue(ISkinProperties properties, T value) {
        properties.setProperty(this.key, value);
    }

    public void clearValue(ISkinProperties properties) {
        properties.removeProperty(this.key);
    }

    public T getValue(ISkinProperties properties, int index) {
        if (properties.haveProperty(this.key + String.valueOf(index))) {
            return (T)properties.getProperty(this.key + String.valueOf(index), this.defaultValue);
        }
        if (properties.haveProperty(this.key)) {
            return (T)properties.getProperty(this.key, this.defaultValue);
        }
        return this.defaultValue;
    }

    public void setValue(ISkinProperties properties, T value, int index) {
        properties.setProperty(this.key + String.valueOf(index), value);
    }

    public void clearValue(ISkinProperties properties, int index) {
        properties.removeProperty(this.key + String.valueOf(index));
    }
}

