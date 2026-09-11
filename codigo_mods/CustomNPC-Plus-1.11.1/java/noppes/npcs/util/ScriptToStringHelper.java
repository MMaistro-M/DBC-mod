/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Optional
 *  com.google.common.base.Preconditions
 *  javax.annotation.CheckForNull
 */
package noppes.npcs.util;

import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import javax.annotation.CheckForNull;

public final class ScriptToStringHelper {
    private final String className;
    private final ValueHolder holderHead;
    private ValueHolder holderTail;
    private boolean omitNullValues;
    private boolean omitEmptyValues;

    private ScriptToStringHelper(String className) {
        this.holderTail = this.holderHead = new ValueHolder();
        this.omitNullValues = false;
        this.omitEmptyValues = false;
        this.className = (String)Preconditions.checkNotNull((Object)className);
    }

    public static ScriptToStringHelper toStringHelper(Object self) {
        return new ScriptToStringHelper(self.getClass().getSimpleName());
    }

    public ScriptToStringHelper omitNullValues() {
        this.omitNullValues = true;
        return this;
    }

    public ScriptToStringHelper add(String name, @CheckForNull Object value) {
        return this.addHolder(name, value);
    }

    public ScriptToStringHelper add(String name, boolean value) {
        return this.addUnconditionalHolder(name, String.valueOf(value));
    }

    public ScriptToStringHelper add(String name, char value) {
        return this.addUnconditionalHolder(name, String.valueOf(value));
    }

    public ScriptToStringHelper add(String name, double value) {
        return this.addUnconditionalHolder(name, String.valueOf(value));
    }

    public ScriptToStringHelper add(String name, float value) {
        return this.addUnconditionalHolder(name, String.valueOf(value));
    }

    public ScriptToStringHelper add(String name, int value) {
        return this.addUnconditionalHolder(name, String.valueOf(value));
    }

    public ScriptToStringHelper add(String name, long value) {
        return this.addUnconditionalHolder(name, String.valueOf(value));
    }

    public ScriptToStringHelper addValue(@CheckForNull Object value) {
        return this.addHolder(value);
    }

    public ScriptToStringHelper addValue(boolean value) {
        return this.addUnconditionalHolder(String.valueOf(value));
    }

    public ScriptToStringHelper addValue(char value) {
        return this.addUnconditionalHolder(String.valueOf(value));
    }

    public ScriptToStringHelper addValue(double value) {
        return this.addUnconditionalHolder(String.valueOf(value));
    }

    public ScriptToStringHelper addValue(float value) {
        return this.addUnconditionalHolder(String.valueOf(value));
    }

    public ScriptToStringHelper addValue(int value) {
        return this.addUnconditionalHolder(String.valueOf(value));
    }

    public ScriptToStringHelper addValue(long value) {
        return this.addUnconditionalHolder(String.valueOf(value));
    }

    private static boolean isEmpty(Object value) {
        if (value instanceof CharSequence) {
            return ((CharSequence)value).length() == 0;
        }
        if (value instanceof Collection) {
            return ((Collection)value).isEmpty();
        }
        if (value instanceof Map) {
            return ((Map)value).isEmpty();
        }
        if (value instanceof Optional) {
            return !((Optional)value).isPresent();
        }
        if (value instanceof OptionalInt) {
            return !((OptionalInt)value).isPresent();
        }
        if (value instanceof OptionalLong) {
            return !((OptionalLong)value).isPresent();
        }
        if (value instanceof OptionalDouble) {
            return !((OptionalDouble)value).isPresent();
        }
        if (value instanceof com.google.common.base.Optional) {
            return !((com.google.common.base.Optional)value).isPresent();
        }
        if (value.getClass().isArray()) {
            return Array.getLength(value) == 0;
        }
        return false;
    }

    public String toString() {
        boolean omitNullValuesSnapshot = this.omitNullValues;
        boolean omitEmptyValuesSnapshot = this.omitEmptyValues;
        String nextSeparator = "";
        StringBuilder builder = new StringBuilder(32).append(this.className).append('{');
        ValueHolder valueHolder = this.holderHead.next;
        while (valueHolder != null) {
            Object value = valueHolder.value;
            if (valueHolder instanceof UnconditionalValueHolder || !(value == null ? omitNullValuesSnapshot : omitEmptyValuesSnapshot && ScriptToStringHelper.isEmpty(value))) {
                builder.append(nextSeparator);
                nextSeparator = ", ";
                if (valueHolder.name != null) {
                    builder.append(valueHolder.name).append('=');
                }
                if (value != null && value.getClass().isArray()) {
                    Object[] objectArray = new Object[]{value};
                    String arrayString = Arrays.deepToString(objectArray);
                    builder.append(arrayString, 1, arrayString.length() - 1);
                } else {
                    builder.append(value);
                }
            }
            valueHolder = valueHolder.next;
        }
        return builder.append('}').toString();
    }

    private ValueHolder addHolder() {
        ValueHolder valueHolder;
        this.holderTail = this.holderTail.next = (valueHolder = new ValueHolder());
        return valueHolder;
    }

    private ScriptToStringHelper addHolder(@CheckForNull Object value) {
        ValueHolder valueHolder = this.addHolder();
        valueHolder.value = value;
        return this;
    }

    private ScriptToStringHelper addHolder(String name, @CheckForNull Object value) {
        ValueHolder valueHolder = this.addHolder();
        valueHolder.value = value;
        valueHolder.name = (String)Preconditions.checkNotNull((Object)name);
        return this;
    }

    private UnconditionalValueHolder addUnconditionalHolder() {
        UnconditionalValueHolder valueHolder = new UnconditionalValueHolder();
        this.holderTail = this.holderTail.next = valueHolder;
        return valueHolder;
    }

    private ScriptToStringHelper addUnconditionalHolder(Object value) {
        UnconditionalValueHolder valueHolder = this.addUnconditionalHolder();
        valueHolder.value = value;
        return this;
    }

    private ScriptToStringHelper addUnconditionalHolder(String name, Object value) {
        UnconditionalValueHolder valueHolder = this.addUnconditionalHolder();
        valueHolder.value = value;
        valueHolder.name = (String)Preconditions.checkNotNull((Object)name);
        return this;
    }

    private static class ValueHolder {
        @CheckForNull
        String name;
        @CheckForNull
        Object value;
        @CheckForNull
        ValueHolder next;

        private ValueHolder() {
        }
    }

    private static final class UnconditionalValueHolder
    extends ValueHolder {
        private UnconditionalValueHolder() {
        }
    }
}

