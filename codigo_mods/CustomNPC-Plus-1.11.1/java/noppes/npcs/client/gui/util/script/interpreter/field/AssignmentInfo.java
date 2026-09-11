/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.field;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import noppes.npcs.client.gui.util.script.ScopeInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodCallInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class AssignmentInfo {
    private final int statementStart;
    private final String targetName;
    private final int lhsStart;
    private final int lhsEnd;
    private final TypeInfo targetType;
    private final int rhsStart;
    private final int rhsEnd;
    private final TypeInfo sourceType;
    private final String sourceExpr;
    private final TypeInfo receiverType;
    private final Field reflectionField;
    private final boolean isFinal;
    private ErrorType errorType = ErrorType.NONE;
    private String errorMessage;
    private String requiredType;
    private String providedType;
    private ScopeInfo scopeInfo;

    public AssignmentInfo(String targetName, int statementStart, int lhsStart, int lhsEnd, TypeInfo targetType, int rhsStart, int rhsEnd, TypeInfo sourceType, String sourceExpr, TypeInfo receiverType, Field reflectionField, boolean isFinal) {
        this.targetName = targetName;
        this.statementStart = statementStart;
        this.lhsStart = lhsStart;
        this.lhsEnd = lhsEnd;
        this.targetType = targetType;
        this.rhsStart = rhsStart;
        this.rhsEnd = rhsEnd;
        this.sourceType = sourceType;
        this.sourceExpr = sourceExpr;
        this.receiverType = receiverType;
        this.reflectionField = reflectionField;
        this.isFinal = isFinal;
    }

    public static AssignmentInfo duplicateDeclaration(String varName, int nameStart, int nameEnd, String errorMessage) {
        AssignmentInfo info = new AssignmentInfo(varName, nameStart, nameStart, nameEnd, null, -1, -1, null, null, null, null, false);
        info.setError(ErrorType.DUPLICATE_DECLARATION, errorMessage);
        return info;
    }

    public void validate() {
        int mods;
        if (this.reflectionField != null && Modifier.isFinal(this.reflectionField.getModifiers())) {
            this.setError(ErrorType.FINAL_REASSIGNMENT, "Cannot assign a value to final variable '" + this.targetName + "'");
            return;
        }
        if (this.isFinal) {
            this.setError(ErrorType.FINAL_REASSIGNMENT, "Cannot assign a value to final variable '" + this.targetName + "'");
            return;
        }
        if (this.reflectionField != null && this.receiverType != null && Modifier.isPrivate(mods = this.reflectionField.getModifiers())) {
            this.setError(ErrorType.PRIVATE_ACCESS, "'" + this.targetName + "' has private access in '" + this.receiverType.getFullName() + "'");
            return;
        }
        if (this.targetType != null && this.sourceType != null && !TypeChecker.isTypeCompatible(this.targetType, this.sourceType)) {
            this.requiredType = MethodCallInfo.getTypeDisplayName(this.targetType);
            this.providedType = MethodCallInfo.getTypeDisplayName(this.sourceType);
            this.setError(ErrorType.TYPE_MISMATCH, this.buildTypeMismatchMessage());
        }
    }

    private String buildTypeMismatchMessage() {
        return "Provided type:     " + this.providedType + "\nRequired:             " + this.requiredType;
    }

    private void setError(ErrorType type, String message) {
        this.errorType = type;
        this.errorMessage = message;
    }

    public boolean containsLhsPosition(int position) {
        return position >= this.statementStart && position < this.lhsEnd;
    }

    public boolean containsRhsPosition(int position) {
        return position >= this.rhsStart && position <= this.rhsEnd;
    }

    public boolean containsPosition(int position) {
        return position >= this.statementStart && position <= this.rhsEnd;
    }

    public boolean isLhsError() {
        return this.errorType == ErrorType.FINAL_REASSIGNMENT || this.errorType == ErrorType.PRIVATE_ACCESS || this.errorType == ErrorType.PROTECTED_ACCESS || this.errorType == ErrorType.STATIC_CONTEXT_ERROR || this.errorType == ErrorType.DUPLICATE_DECLARATION;
    }

    public boolean isRhsError() {
        return false;
    }

    public boolean isFullLineError() {
        return this.errorType == ErrorType.TYPE_MISMATCH;
    }

    public String getTargetName() {
        return this.targetName;
    }

    public int getStatementStart() {
        return this.statementStart;
    }

    public int getLhsStart() {
        return this.lhsStart;
    }

    public int getLhsEnd() {
        return this.lhsEnd;
    }

    public TypeInfo getTargetType() {
        return this.targetType;
    }

    public int getRhsStart() {
        return this.rhsStart;
    }

    public int getRhsEnd() {
        return this.rhsEnd;
    }

    public TypeInfo getSourceType() {
        return this.sourceType;
    }

    public String getSourceExpr() {
        return this.sourceExpr;
    }

    public TypeInfo getReceiverType() {
        return this.receiverType;
    }

    public Field getReflectionField() {
        return this.reflectionField;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getRequiredType() {
        return this.requiredType;
    }

    public String getProvidedType() {
        return this.providedType;
    }

    public boolean hasError() {
        return this.errorType != ErrorType.NONE;
    }

    public boolean hasTypeMismatch() {
        return this.errorType == ErrorType.TYPE_MISMATCH;
    }

    public boolean hasFinalReassignment() {
        return this.errorType == ErrorType.FINAL_REASSIGNMENT;
    }

    public boolean hasAccessError() {
        return this.errorType == ErrorType.PRIVATE_ACCESS || this.errorType == ErrorType.PROTECTED_ACCESS;
    }

    public ScopeInfo getScopeInfo() {
        return this.scopeInfo;
    }

    public void setScopeInfo(ScopeInfo scopeInfo) {
        this.scopeInfo = scopeInfo;
    }

    public String toString() {
        return "AssignmentInfo{target='" + this.targetName + "', stmt=" + this.statementStart + ", lhs=[" + this.lhsStart + "-" + this.lhsEnd + "], rhs=[" + this.rhsStart + "-" + this.rhsEnd + "], targetType=" + this.targetType + ", sourceType=" + this.sourceType + ", error=" + (Object)((Object)this.errorType) + '}';
    }

    public static enum ErrorType {
        NONE,
        TYPE_MISMATCH,
        FINAL_REASSIGNMENT,
        PRIVATE_ACCESS,
        PROTECTED_ACCESS,
        UNRESOLVED_TARGET,
        STATIC_CONTEXT_ERROR,
        DUPLICATE_DECLARATION;

    }
}

