/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.Token;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class MethodCallInfo {
    private final String methodName;
    private final int methodNameStart;
    private final int methodNameEnd;
    private final int openParenOffset;
    private final int closeParenOffset;
    private final List<Argument> arguments;
    private final TypeInfo receiverType;
    private final MethodInfo resolvedMethod;
    private final boolean isStaticAccess;
    private TypeInfo expectedType;
    private ErrorType errorType = ErrorType.NONE;
    private String errorMessage;
    private int errorArgIndex = -1;
    private List<ArgumentTypeError> argumentTypeErrors = new ArrayList<ArgumentTypeError>();
    private boolean isConstructor;
    public boolean isClassTypeAccess;
    private TypeInfo resolvedReturnType;

    public MethodCallInfo(String methodName, int methodNameStart, int methodNameEnd, int openParenOffset, int closeParenOffset, List<Argument> arguments, TypeInfo receiverType, MethodInfo resolvedMethod) {
        this(methodName, methodNameStart, methodNameEnd, openParenOffset, closeParenOffset, arguments, receiverType, resolvedMethod, false);
    }

    public MethodCallInfo(String methodName, int methodNameStart, int methodNameEnd, int openParenOffset, int closeParenOffset, List<Argument> arguments, TypeInfo receiverType, MethodInfo resolvedMethod, boolean isStaticAccess) {
        this.methodName = methodName;
        this.methodNameStart = methodNameStart;
        this.methodNameEnd = methodNameEnd;
        this.openParenOffset = openParenOffset;
        this.closeParenOffset = closeParenOffset;
        this.arguments = arguments != null ? new ArrayList<Argument>(arguments) : new ArrayList();
        this.receiverType = receiverType;
        this.resolvedMethod = resolvedMethod;
        this.isStaticAccess = isStaticAccess;
    }

    public static MethodCallInfo constructor(TypeInfo typeInfo, MethodInfo constructor, int typeNameStart, int typeNameEnd, int openParenOffset, int closeParenOffset, List<Argument> arguments) {
        return new MethodCallInfo(typeInfo.getSimpleName(), typeNameStart, typeNameEnd, openParenOffset, closeParenOffset, arguments, typeInfo, constructor, false).setConstructor(true);
    }

    public String getMethodName() {
        return this.methodName;
    }

    public int getMethodNameStart() {
        return this.methodNameStart;
    }

    public int getMethodNameEnd() {
        return this.methodNameEnd;
    }

    public int getOpenParenOffset() {
        return this.openParenOffset;
    }

    public int getCloseParenOffset() {
        return this.closeParenOffset;
    }

    public List<Argument> getArguments() {
        return Collections.unmodifiableList(this.arguments);
    }

    public int getArgumentCount() {
        return this.arguments.size();
    }

    public TypeInfo getReceiverType() {
        return this.receiverType;
    }

    public MethodInfo getResolvedMethod() {
        return this.resolvedMethod;
    }

    public boolean isStaticAccess() {
        return this.isStaticAccess;
    }

    public TypeInfo getExpectedType() {
        return this.expectedType;
    }

    public void setExpectedType(TypeInfo expectedType) {
        this.expectedType = expectedType;
    }

    public TypeInfo getResolvedReturnType() {
        if (this.resolvedReturnType != null) {
            return this.resolvedReturnType;
        }
        return this.resolvedMethod != null ? this.resolvedMethod.getReturnType() : null;
    }

    public void setResolvedReturnType(TypeInfo returnType) {
        this.resolvedReturnType = returnType;
    }

    public boolean isConstructor() {
        return this.isConstructor;
    }

    public MethodCallInfo setConstructor(boolean isConstructor) {
        this.isConstructor = isConstructor;
        return this;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public int getFullCallStart() {
        return this.methodNameStart;
    }

    public int getFullCallEnd() {
        return this.closeParenOffset + 1;
    }

    public boolean hasError() {
        return this.errorType != ErrorType.NONE;
    }

    public boolean hasArgCountError() {
        return this.errorType == ErrorType.WRONG_ARG_COUNT;
    }

    public boolean hasArgTypeError() {
        return !this.argumentTypeErrors.isEmpty();
    }

    public boolean hasStaticAccessError() {
        return this.errorType == ErrorType.STATIC_ACCESS_ERROR;
    }

    public boolean hasReturnTypeMismatch() {
        return this.errorType == ErrorType.RETURN_TYPE_MISMATCH;
    }

    public void setError(ErrorType type, String message) {
        this.errorType = type;
        this.errorMessage = message;
    }

    public void setArgTypeError(int argIndex, String message) {
        this.argumentTypeErrors.add(new ArgumentTypeError(this.arguments.get(argIndex), argIndex, message));
    }

    public List<ArgumentTypeError> getArgumentTypeErrors() {
        return this.argumentTypeErrors;
    }

    public void validate() {
        TypeInfo returnType;
        boolean isVarArgMethod;
        if (this.isConstructor) {
            if (this.resolvedMethod == null) {
                boolean isJSType;
                boolean bl = isJSType = this.receiverType != null && this.receiverType.isJSType();
                if (!isJSType && !this.isClassTypeAccess) {
                    if (this.receiverType != null && this.receiverType.hasConstructors()) {
                        this.setError(ErrorType.WRONG_ARG_COUNT, "No constructor in '" + this.receiverType.getSimpleName() + "' matches " + this.arguments.size() + " argument(s)");
                    } else {
                        this.setError(ErrorType.UNRESOLVED_METHOD, "Cannot resolve constructor for '" + this.methodName + "'");
                    }
                }
            } else if (this.receiverType != null) {
                boolean ctorCountOk;
                boolean ctorVarArg;
                List<FieldInfo> cParams = this.resolvedMethod.getParameters();
                boolean bl = ctorVarArg = !cParams.isEmpty() && cParams.get(cParams.size() - 1).isVarArg();
                boolean bl2 = ctorVarArg ? this.arguments.size() >= cParams.size() - 1 : (ctorCountOk = this.arguments.size() == this.resolvedMethod.getParameterCount());
                if (ctorCountOk) {
                    this.validateArgTypeError();
                }
            }
            return;
        }
        if (!this.isConstructor && this.isStaticAccess && !this.resolvedMethod.isStatic()) {
            this.setError(ErrorType.STATIC_ACCESS_ERROR, "Cannot call instance method '" + this.methodName + "' on a class type");
            return;
        }
        List<FieldInfo> params = this.resolvedMethod.getParameters();
        int expectedCount = params.size();
        int actualCount = this.arguments.size();
        boolean bl = isVarArgMethod = expectedCount > 0 && params.get(expectedCount - 1).isVarArg();
        if (isVarArgMethod) {
            if (actualCount < expectedCount - 1) {
                this.setError(ErrorType.WRONG_ARG_COUNT, "Expected at least " + (expectedCount - 1) + " argument(s) but got " + actualCount);
                return;
            }
        } else if (actualCount != expectedCount) {
            this.setError(ErrorType.WRONG_ARG_COUNT, "Expected " + expectedCount + " argument(s) but got " + actualCount);
            return;
        }
        this.validateArgTypeError();
        if (this.expectedType == null || this.resolvedMethod == null || (returnType = this.resolvedMethod.getReturnType()) == null || !TypeChecker.isTypeCompatible(this.expectedType, returnType)) {
            // empty if block
        }
    }

    public void validateArgTypeError() {
        List<FieldInfo> params = this.resolvedMethod.getParameters();
        int paramCount = params.size();
        boolean isVarArgMethod = paramCount > 0 && params.get(paramCount - 1).isVarArg();
        for (int i = 0; i < this.arguments.size(); ++i) {
            TypeInfo paramType;
            FieldInfo para;
            Argument arg = this.arguments.get(i);
            if (!arg.isValid() && arg.getErrorMessage() != null) {
                this.setArgTypeError(i, arg.getErrorMessage());
                continue;
            }
            if (isVarArgMethod && i >= paramCount - 1) {
                para = params.get(paramCount - 1);
                TypeInfo arrType = para.getTypeInfo();
                paramType = arrType != null && arrType.isArray() ? arrType.getElementType() : arrType;
            } else {
                para = params.get(i);
                paramType = para.getTypeInfo();
            }
            TypeInfo argType = arg.getResolvedType();
            if (argType != null && paramType != null) {
                if (TypeChecker.isTypeCompatible(paramType, argType)) continue;
                this.setArgTypeError(i, "Expected " + MethodCallInfo.getTypeDisplayName(paramType) + " but got " + MethodCallInfo.getTypeDisplayName(argType));
                continue;
            }
            if (paramType == null) {
                this.setArgTypeError(i, "Parameter type of '" + para.getName() + "' is unresolved");
                continue;
            }
            if (argType != null) continue;
            this.setArgTypeError(i, "Cannot resolve type of argument '" + arg.getText() + "'");
        }
    }

    public static String getTypeDisplayName(TypeInfo type) {
        TypeInfo bound;
        if (type.isTypeParameter() && (bound = type.getBoundType()) != null) {
            return bound.getDisplayName();
        }
        return type.getDisplayName();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MethodCallInfo{");
        sb.append(this.methodName).append("(");
        for (int i = 0; i < this.arguments.size(); ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.arguments.get(i).getText());
        }
        sb.append(")");
        if (this.hasError()) {
            sb.append(" ERROR: ").append((Object)this.errorType).append(" - ").append(this.errorMessage);
        }
        sb.append("}");
        return sb.toString();
    }

    public class ArgumentTypeError {
        private ErrorType type = ErrorType.WRONG_ARG_TYPE;
        private final Argument arg;
        private final int argIndex;
        private final String message;

        public ArgumentTypeError(Argument arg, int argIndex, String message) {
            this.arg = arg;
            this.argIndex = argIndex;
            this.message = message;
        }

        public int getArgIndex() {
            return this.argIndex;
        }

        public String getMessage() {
            return this.message;
        }

        public Argument getArg() {
            return this.arg;
        }
    }

    public static enum ErrorType {
        NONE,
        WRONG_ARG_COUNT,
        WRONG_ARG_TYPE,
        STATIC_ACCESS_ERROR,
        RETURN_TYPE_MISMATCH,
        UNRESOLVED_METHOD,
        UNRESOLVED_RECEIVER;

    }

    public static class Argument {
        private final String text;
        private final int startOffset;
        private final int endOffset;
        private final TypeInfo resolvedType;
        private final boolean valid;
        private final String errorMessage;

        public Argument(String text, int startOffset, int endOffset, TypeInfo resolvedType, boolean valid, String errorMessage) {
            this.text = text;
            this.startOffset = startOffset;
            this.endOffset = endOffset;
            this.resolvedType = resolvedType;
            this.valid = valid;
            this.errorMessage = errorMessage;
        }

        public String getText() {
            return this.text;
        }

        public int getStartOffset() {
            return this.startOffset;
        }

        public int getEndOffset() {
            return this.endOffset;
        }

        public TypeInfo getResolvedType() {
            return this.resolvedType;
        }

        public boolean isValid() {
            return this.valid;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }

        public boolean equals(Token t) {
            return this.text.equals(t.getText()) && this.startOffset == t.getGlobalStart() && this.endOffset == t.getGlobalEnd();
        }

        public String toString() {
            return "Arg{" + this.text + " [" + this.startOffset + "-" + this.endOffset + "]" + (this.resolvedType != null ? " :" + this.resolvedType.getSimpleName() : "") + (this.valid ? "" : " INVALID: " + this.errorMessage) + "}";
        }
    }
}

