/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.field;

import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class FieldAccessInfo {
    private final String fieldName;
    private final int fieldNameStart;
    private final int fieldNameEnd;
    private final TypeInfo receiverType;
    private final FieldInfo resolvedField;
    private final boolean isStaticAccess;
    private TypeInfo expectedType;
    private ErrorType errorType = ErrorType.NONE;
    private String errorMessage;

    public FieldAccessInfo(String fieldName, int fieldNameStart, int fieldNameEnd, TypeInfo receiverType, FieldInfo resolvedField, boolean isStaticAccess) {
        this.fieldName = fieldName;
        this.fieldNameStart = fieldNameStart;
        this.fieldNameEnd = fieldNameEnd;
        this.receiverType = receiverType;
        this.resolvedField = resolvedField;
        this.isStaticAccess = isStaticAccess;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public int getFieldNameStart() {
        return this.fieldNameStart;
    }

    public int getFieldNameEnd() {
        return this.fieldNameEnd;
    }

    public TypeInfo getReceiverType() {
        return this.receiverType;
    }

    public FieldInfo getResolvedField() {
        return this.resolvedField;
    }

    public boolean isStaticAccess() {
        return this.isStaticAccess;
    }

    public boolean isEnumConstantAccess() {
        return this.resolvedField != null && this.resolvedField.isEnumConstant();
    }

    public TypeInfo getExpectedType() {
        return this.expectedType;
    }

    public void setExpectedType(TypeInfo expectedType) {
        this.expectedType = expectedType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void validate() {
        TypeInfo fieldType;
        if (this.resolvedField == null) {
            this.setError(ErrorType.UNRESOLVED_FIELD, "Cannot resolve field '" + this.fieldName + "'");
            return;
        }
        if (this.expectedType == null || this.resolvedField == null || (fieldType = this.resolvedField.getTypeInfo()) == null || !TypeChecker.isTypeCompatible(this.expectedType, fieldType)) {
            // empty if block
        }
    }

    private void setError(ErrorType type, String message) {
        this.errorType = type;
        this.errorMessage = message;
    }

    public boolean hasError() {
        return this.errorType != ErrorType.NONE;
    }

    public boolean hasTypeMismatch() {
        return this.errorType == ErrorType.TYPE_MISMATCH;
    }

    public boolean hasUnresolvedField() {
        return this.errorType == ErrorType.UNRESOLVED_FIELD;
    }

    public boolean hasStaticAccessError() {
        return this.errorType == ErrorType.STATIC_ACCESS_ERROR;
    }

    public String toString() {
        return "FieldAccessInfo{fieldName='" + this.fieldName + "', receiverType=" + this.receiverType + ", resolvedField=" + this.resolvedField + ", errorType=" + (Object)((Object)this.errorType) + '}';
    }

    public static enum ErrorType {
        NONE,
        TYPE_MISMATCH,
        UNRESOLVED_FIELD,
        STATIC_ACCESS_ERROR;

    }
}

