/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.CodeParser;
import noppes.npcs.client.gui.util.script.interpreter.ControlFlowAnalyzer;
import noppes.npcs.client.gui.util.script.interpreter.bridge.DtsJavaBridge;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodSignature;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericContext;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericParameterAdapter;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public final class MethodInfo {
    private final String name;
    private final TypeInfo returnType;
    private final TypeInfo containingType;
    private final List<FieldInfo> parameters;
    private final int fullDeclarationOffset;
    private final int typeOffset;
    private final int nameOffset;
    private final int bodyStart;
    private final int bodyEnd;
    private final boolean resolved;
    private final boolean isDeclaration;
    private final int modifiers;
    private final String documentation;
    private JSDocInfo jsDocInfo;
    private final Method javaMethod;
    private ErrorType errorType = ErrorType.NONE;
    private String errorMessage;
    private List<ParameterError> parameterErrors = new ArrayList<ParameterError>();
    private List<ReturnStatementError> returnStatementErrors = new ArrayList<ReturnStatementError>();
    private TypeInfo overridesFrom;
    private TypeInfo implementsFrom;
    public MethodSignature cachedSignature;

    private MethodInfo(String name, TypeInfo returnType, TypeInfo containingType, List<FieldInfo> parameters, int fullDeclarationOffset, int typeOffset, int nameOffset, int bodyStart, int bodyEnd, boolean resolved, boolean isDeclaration, int modifiers, String documentation, Method javaMethod) {
        this.name = name;
        this.returnType = returnType;
        this.containingType = containingType;
        this.parameters = parameters != null ? new ArrayList<FieldInfo>(parameters) : new ArrayList();
        this.fullDeclarationOffset = fullDeclarationOffset;
        this.typeOffset = typeOffset;
        this.nameOffset = nameOffset;
        this.bodyStart = bodyStart;
        this.bodyEnd = bodyEnd;
        this.resolved = resolved;
        this.isDeclaration = isDeclaration;
        this.modifiers = modifiers;
        this.documentation = documentation;
        this.javaMethod = javaMethod;
    }

    public static MethodInfo declaration(String name, TypeInfo containingType, TypeInfo returnType, List<FieldInfo> params, int fullDeclOffset, int typeOffset, int nameOffset, int bodyStart, int bodyEnd, int modifiers, String documentation) {
        return new MethodInfo(name, returnType, containingType, params, fullDeclOffset, typeOffset, nameOffset, bodyStart, bodyEnd, true, true, modifiers, documentation, null);
    }

    public static MethodInfo call(String name, TypeInfo containingType, int paramCount) {
        boolean resolved = containingType != null && containingType.isResolved() && containingType.hasMethod(name);
        ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
        for (int i = 0; i < paramCount; ++i) {
            params.add(FieldInfo.unresolved("arg" + i, FieldInfo.Scope.PARAMETER));
        }
        return new MethodInfo(name, null, containingType, params, -1, -1, -1, -1, -1, resolved, false, 0, null, null);
    }

    public static MethodInfo unresolvedCall(String name, int paramCount) {
        ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
        for (int i = 0; i < paramCount; ++i) {
            params.add(FieldInfo.unresolved("arg" + i, FieldInfo.Scope.PARAMETER));
        }
        return new MethodInfo(name, null, null, params, -1, -1, -1, -1, -1, false, false, 0, null, null);
    }

    public static MethodInfo external(String name, TypeInfo returnType, TypeInfo containingType, List<FieldInfo> params, int modifiers, String documentation) {
        return new MethodInfo(name, returnType, containingType, params, -1, -1, -1, -1, -1, true, false, modifiers, documentation, null);
    }

    public static MethodInfo fromReflection(Method method, TypeInfo containingType) {
        Class<?> overrideClass;
        Class<?> reflectedReturn;
        TypeInfo overrideReturnType;
        String name = method.getName();
        TypeInfo returnType = TypeInfo.fromGenericType(method.getGenericReturnType());
        if (returnType == null) {
            returnType = TypeInfo.fromClass(method.getReturnType());
        }
        int modifiers = method.getModifiers();
        JSMethodInfo jsMethod = DtsJavaBridge.findMatchingMethod(method, containingType);
        if (jsMethod != null && (overrideReturnType = DtsJavaBridge.resolveReturnTypeOverride(method, containingType, jsMethod)) != null && overrideReturnType.getJavaClass() != null && (reflectedReturn = method.getReturnType()).isAssignableFrom(overrideClass = overrideReturnType.getJavaClass()) && reflectedReturn != overrideClass) {
            returnType = overrideReturnType;
        }
        GenericContext receiverCtx = null;
        if (GenericContext.hasGenerics(containingType)) {
            receiverCtx = GenericContext.forReceiver(containingType);
            returnType = receiverCtx.substitute(returnType);
        }
        ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
        Type[] genericParamTypes = method.getGenericParameterTypes();
        List<JSMethodInfo.JSParameterInfo> jsParams = jsMethod != null ? jsMethod.getParameters() : null;
        for (int i = 0; i < genericParamTypes.length; ++i) {
            boolean isVarArg;
            String jsName;
            TypeInfo adapted;
            TypeInfo paramType = TypeInfo.fromGenericType(genericParamTypes[i]);
            if (paramType == null) {
                paramType = TypeInfo.fromClass(method.getParameterTypes()[i]);
            }
            if (receiverCtx != null) {
                paramType = receiverCtx.substitute(paramType);
            }
            if ((adapted = GenericParameterAdapter.adaptParameterType(method, containingType, i, paramType)) != null) {
                paramType = adapted;
            }
            String paramName = "arg" + i;
            if (jsParams != null && i < jsParams.size() && (jsName = jsParams.get(i).getName()) != null && !jsName.isEmpty()) {
                paramName = jsName;
            }
            FieldInfo fieldInfo = FieldInfo.reflectionParam(paramName, paramType);
            boolean bl = isVarArg = method.isVarArgs() && i == genericParamTypes.length - 1;
            if (jsParams != null && i < jsParams.size()) {
                isVarArg = isVarArg || jsParams.get(i).isVarArg();
            }
            fieldInfo.setVarArg(isVarArg);
            params.add(fieldInfo);
        }
        String documentation = null;
        JSDocInfo jsDocInfo = null;
        if (jsMethod != null) {
            jsDocInfo = jsMethod.getJsDocInfo();
            String jsDocDesc = jsDocInfo != null ? jsDocInfo.getDescription() : null;
            documentation = jsDocDesc != null ? jsDocDesc : jsMethod.getDocumentation();
        }
        MethodInfo methodInfo = new MethodInfo(name, returnType, containingType, params, -1, -1, -1, -1, -1, true, false, modifiers, documentation, method);
        if (jsDocInfo != null) {
            methodInfo.setJSDocInfo(jsDocInfo);
        }
        return methodInfo;
    }

    public static MethodInfo fromReflectionConstructor(Constructor<?> constructor, TypeInfo containingType) {
        String name = containingType.getSimpleName();
        TypeInfo returnType = containingType;
        int modifiers = constructor.getModifiers();
        ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
        Type[] genericParamTypes = constructor.getGenericParameterTypes();
        for (int i = 0; i < genericParamTypes.length; ++i) {
            TypeInfo paramType = TypeInfo.fromGenericType(genericParamTypes[i]);
            if (paramType == null) {
                paramType = TypeInfo.fromClass(constructor.getParameterTypes()[i]);
            }
            FieldInfo fieldInfo = FieldInfo.reflectionParam("arg" + i, paramType);
            fieldInfo.setVarArg(constructor.isVarArgs() && i == genericParamTypes.length - 1);
            params.add(fieldInfo);
        }
        return new MethodInfo(name, returnType, containingType, params, -1, -1, -1, -1, -1, true, true, modifiers, null, null);
    }

    public static MethodInfo fromJSMethod(JSMethodInfo jsMethod, TypeInfo containingType) {
        JSDocInfo jsDocInfo;
        String name = jsMethod.getName();
        TypeInfo returnType = jsMethod.getResolvedReturnType(containingType);
        ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
        List<JSMethodInfo.JSParameterInfo> jsParams = jsMethod.getParameters();
        for (JSMethodInfo.JSParameterInfo param : jsParams) {
            String paramName = param.getName();
            TypeInfo paramType = param.getResolvedType(containingType);
            FieldInfo fieldInfo = FieldInfo.reflectionParam(paramName, paramType);
            fieldInfo.setVarArg(param.isVarArg());
            params.add(fieldInfo);
        }
        int modifiers = 1;
        if (jsMethod.isStatic()) {
            modifiers |= 8;
        }
        String jsDocDesc = (jsDocInfo = jsMethod.getJsDocInfo()) != null ? jsDocInfo.getDescription() : null;
        String documentation = jsDocDesc != null ? jsDocDesc : jsMethod.getDocumentation();
        MethodInfo methodInfo = new MethodInfo(name, returnType, containingType, params, -1, -1, -1, -1, -1, true, false, modifiers, documentation, null);
        methodInfo.setJSDocInfo(jsDocInfo);
        return methodInfo;
    }

    public String getName() {
        return this.name;
    }

    public TypeInfo getReturnType() {
        return this.returnType;
    }

    public TypeInfo getContainingType() {
        return this.containingType;
    }

    public List<FieldInfo> getParameters() {
        return Collections.unmodifiableList(this.parameters);
    }

    public int getParameterCount() {
        return this.parameters.size();
    }

    public Method getJavaMethod() {
        return this.javaMethod;
    }

    @Deprecated
    public int getDeclarationOffset() {
        return this.typeOffset;
    }

    public int getFullDeclarationOffset() {
        return this.fullDeclarationOffset;
    }

    public int getTypeOffset() {
        return this.typeOffset;
    }

    public int getNameOffset() {
        return this.nameOffset;
    }

    public int getBodyStart() {
        return this.bodyStart;
    }

    public int getBodyEnd() {
        return this.bodyEnd;
    }

    public boolean isResolved() {
        return this.resolved;
    }

    public boolean isDeclaration() {
        return this.isDeclaration;
    }

    public boolean isCall() {
        return !this.isDeclaration;
    }

    public int getModifiers() {
        return this.modifiers;
    }

    public boolean isStatic() {
        return Modifier.isStatic(this.modifiers);
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.modifiers);
    }

    public boolean isAbstract() {
        return Modifier.isAbstract(this.modifiers);
    }

    public boolean isSynchronized() {
        return Modifier.isSynchronized(this.modifiers);
    }

    public boolean isNative() {
        return Modifier.isNative(this.modifiers);
    }

    public boolean isPublic() {
        return Modifier.isPublic(this.modifiers);
    }

    public boolean isPrivate() {
        return Modifier.isPrivate(this.modifiers);
    }

    public boolean isProtected() {
        return Modifier.isProtected(this.modifiers);
    }

    public String getDocumentation() {
        return this.documentation;
    }

    public JSDocInfo getJSDocInfo() {
        return this.jsDocInfo;
    }

    public void setJSDocInfo(JSDocInfo jsDocInfo) {
        this.jsDocInfo = jsDocInfo;
    }

    public boolean isOverride() {
        return this.overridesFrom != null;
    }

    public TypeInfo getOverridesFrom() {
        return this.overridesFrom;
    }

    public void setOverridesFrom(TypeInfo parentType) {
        this.overridesFrom = parentType;
    }

    public boolean isImplements() {
        return this.implementsFrom != null;
    }

    public TypeInfo getImplementsFrom() {
        return this.implementsFrom;
    }

    public void setImplementsFrom(TypeInfo interfaceType) {
        this.implementsFrom = interfaceType;
    }

    public boolean hasInheritanceMarker() {
        return this.isOverride() || this.isImplements();
    }

    public MethodInfo substituteTypeParams(GenericContext context) {
        if (context == null) {
            return this;
        }
        TypeInfo subReturnType = context.substitute(this.returnType);
        ArrayList<FieldInfo> subParams = new ArrayList<FieldInfo>(this.parameters.size());
        for (FieldInfo param : this.parameters) {
            subParams.add(param.substituteTypeParams(context));
        }
        MethodInfo result = new MethodInfo(this.name, subReturnType, this.containingType, subParams, this.fullDeclarationOffset, this.typeOffset, this.nameOffset, this.bodyStart, this.bodyEnd, this.resolved, this.isDeclaration, this.modifiers, this.documentation, this.javaMethod);
        result.jsDocInfo = this.jsDocInfo;
        result.overridesFrom = this.overridesFrom;
        result.implementsFrom = this.implementsFrom;
        return result;
    }

    public boolean containsPosition(int position) {
        return position >= this.bodyStart && position < this.bodyEnd;
    }

    public int getDeclarationEnd() {
        return this.bodyStart > 0 ? this.bodyStart - 1 : this.nameOffset + this.name.length();
    }

    public boolean hasParameter(String paramName) {
        for (FieldInfo p : this.parameters) {
            if (!p.getName().equals(paramName)) continue;
            return true;
        }
        return false;
    }

    public FieldInfo getParameter(String paramName) {
        for (FieldInfo p : this.parameters) {
            if (!p.getName().equals(paramName)) continue;
            return p;
        }
        return null;
    }

    public MethodSignature getSignature() {
        if (this.cachedSignature == null) {
            ArrayList<TypeInfo> paramTypes = new ArrayList<TypeInfo>();
            for (FieldInfo param : this.parameters) {
                paramTypes.add(param.getTypeInfo());
            }
            this.cachedSignature = new MethodSignature(this.name, paramTypes);
        }
        return this.cachedSignature;
    }

    public TokenType getTokenType() {
        if (this.isDeclaration) {
            return TokenType.METHOD_DECL;
        }
        return this.resolved ? TokenType.METHOD_CALL : TokenType.DEFAULT;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MethodInfo{");
        sb.append(this.name).append("(");
        for (int i = 0; i < this.parameters.size(); ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.parameters.get(i).getName());
        }
        sb.append(")");
        if (this.returnType != null) {
            sb.append(" -> ").append(this.returnType.getSimpleName());
        }
        sb.append(", ").append(this.isDeclaration ? "decl" : "call");
        sb.append(", ").append(this.resolved ? "resolved" : "unresolved");
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        MethodInfo that = (MethodInfo)o;
        return this.name.equals(that.name) && this.parameters.size() == that.parameters.size();
    }

    public int hashCode() {
        return this.name.hashCode() * 31 + this.parameters.size();
    }

    public boolean hasError() {
        return this.errorType != ErrorType.NONE || !this.parameterErrors.isEmpty() || !this.returnStatementErrors.isEmpty();
    }

    public boolean hasMissingReturnError() {
        return this.errorType == ErrorType.MISSING_RETURN;
    }

    public boolean hasParameterErrors() {
        return !this.parameterErrors.isEmpty();
    }

    public boolean hasReturnStatementErrors() {
        return !this.returnStatementErrors.isEmpty();
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public List<ParameterError> getParameterErrors() {
        return Collections.unmodifiableList(this.parameterErrors);
    }

    public List<ReturnStatementError> getReturnStatementErrors() {
        return Collections.unmodifiableList(this.returnStatementErrors);
    }

    public void setError(ErrorType type, String message) {
        this.errorType = type;
        this.errorMessage = message;
    }

    public void addParameterError(FieldInfo param, int index, ErrorType type, String message) {
        this.parameterErrors.add(new ParameterError(param, index, type, message));
    }

    public void addSamTypeError(int paramIndex, TypeInfo expectedSamType, TypeInfo actualDeclaredType) {
        if (paramIndex < 0 || paramIndex >= this.parameters.size()) {
            return;
        }
        FieldInfo param = this.parameters.get(paramIndex);
        String message = "Explicit type '" + actualDeclaredType.getSimpleName() + "' is incompatible with SAM parameter type '" + expectedSamType.getSimpleName() + "'";
        this.parameterErrors.add(new ParameterError(param, paramIndex, ErrorType.SAM_TYPE_INCOMPATIBLE, message));
    }

    public void addReturnStatementError(int startOffset, int endOffset, String message, TypeInfo expectedType, TypeInfo actualType) {
        this.returnStatementErrors.add(new ReturnStatementError(startOffset, endOffset, message, expectedType, actualType));
    }

    public void validate(String methodBodyText, boolean hasBody, TypeResolver typeResolver) {
        boolean interfaceMember;
        if (!this.isDeclaration) {
            return;
        }
        if (this.returnType != null && !this.returnType.isResolved()) {
            return;
        }
        this.validateParameters();
        boolean bl = interfaceMember = this.containingType != null && this.containingType.isInterface();
        if (hasBody && (interfaceMember || this.isAbstract() || this.isNative())) {
            this.setError(ErrorType.INTERFACE_METHOD_BODY, "Interface or abstract methods cannot have a body");
            return;
        }
        if (interfaceMember) {
            return;
        }
        if (this.bodyStart == this.bodyEnd && !this.isAbstract() && !this.isNative()) {
            this.setError(ErrorType.MISSING_BODY, "Method must have a body or be declared abstract/native.");
            return;
        }
        if (typeResolver != null) {
            this.validateReturnTypes(methodBodyText, typeResolver);
        }
        this.validateReturnStatement(methodBodyText);
    }

    private void validateParameters() {
        HashSet<String> seenNames = new HashSet<String>();
        for (int i = 0; i < this.parameters.size(); ++i) {
            FieldInfo param = this.parameters.get(i);
            String paramName = param.getName();
            if (seenNames.contains(paramName)) {
                this.addParameterError(param, i, ErrorType.DUPLICATE_PARAMETER, "Duplicate parameter name '" + paramName + "'");
            } else {
                seenNames.add(paramName);
            }
            TypeInfo paramType = param.getTypeInfo();
            if (paramType != null && paramType.isResolved()) continue;
            String typeName = paramType != null ? paramType.getDisplayName() : "unknown";
            this.addParameterError(param, i, ErrorType.PARAMETER_UNDEFINED, "Cannot resolve parameter type '" + typeName + "'");
        }
    }

    private void validateReturnStatement(String bodyText) {
        if (bodyText == null || bodyText.isEmpty()) {
            return;
        }
        if (TypeChecker.isVoidType(this.returnType)) {
            return;
        }
        if (this.isAbstract() || this.isNative()) {
            return;
        }
        if (!ControlFlowAnalyzer.hasGuaranteedReturn(bodyText)) {
            this.setError(ErrorType.MISSING_RETURN, "Missing return statement");
        }
    }

    private void validateReturnTypes(String bodyText, TypeResolver typeResolver) {
        int returnPos;
        if (bodyText == null || bodyText.isEmpty()) {
            return;
        }
        boolean isVoid = TypeChecker.isVoidType(this.returnType);
        String expectedTypeName = isVoid ? "void" : this.returnType.getDisplayName();
        String cleanBody = CodeParser.removeComments(bodyText);
        int pos = 0;
        while (pos < cleanBody.length() && (returnPos = CodeParser.findReturnKeyword(cleanBody, pos)) >= 0) {
            TypeInfo actualType;
            int semiPos = CodeParser.findReturnSemicolon(cleanBody, returnPos + 6);
            if (semiPos < 0) {
                pos = returnPos + 6;
                continue;
            }
            String returnExpr = cleanBody.substring(returnPos + 6, semiPos).trim();
            int absoluteReturnStart = this.bodyStart + 1 + returnPos;
            int absoluteSemiEnd = this.bodyStart + 1 + semiPos + 1;
            if (isVoid && !returnExpr.isEmpty()) {
                String message = "Cannot return a value from a method with void result type";
                this.addReturnStatementError(absoluteReturnStart, absoluteSemiEnd, message, this.returnType, null);
            } else if (!(isVoid || returnExpr.isEmpty() || typeResolver == null || TypeChecker.isTypeCompatible(this.returnType, actualType = typeResolver.resolveExpression(returnExpr, absoluteReturnStart)))) {
                String actualTypeName = actualType != null ? actualType.getSimpleName() : "null";
                String message = "Incompatible types.\nRequired: " + expectedTypeName + "\nFound: " + actualTypeName;
                this.addReturnStatementError(absoluteReturnStart, absoluteSemiEnd, message, this.returnType, actualType);
            }
            pos = semiPos + 1;
        }
    }

    @FunctionalInterface
    public static interface TypeResolver {
        public TypeInfo resolveExpression(String var1, int var2);
    }

    public static class ReturnStatementError {
        private final int startOffset;
        private final int endOffset;
        private final String message;
        private final TypeInfo expectedType;
        private final TypeInfo actualType;

        public ReturnStatementError(int startOffset, int endOffset, String message, TypeInfo expectedType, TypeInfo actualType) {
            this.startOffset = startOffset;
            this.endOffset = endOffset;
            this.message = message;
            this.expectedType = expectedType;
            this.actualType = actualType;
        }

        public int getStartOffset() {
            return this.startOffset;
        }

        public int getEndOffset() {
            return this.endOffset;
        }

        public String getMessage() {
            return this.message;
        }

        public TypeInfo getExpectedType() {
            return this.expectedType;
        }

        public TypeInfo getActualType() {
            return this.actualType;
        }
    }

    public static class ParameterError {
        private final FieldInfo parameter;
        private final int paramIndex;
        private final ErrorType errorType;
        private final String message;

        public ParameterError(FieldInfo parameter, int paramIndex, ErrorType errorType, String message) {
            this.parameter = parameter;
            this.paramIndex = paramIndex;
            this.errorType = errorType;
            this.message = message;
        }

        public FieldInfo getParameter() {
            return this.parameter;
        }

        public int getParamIndex() {
            return this.paramIndex;
        }

        public ErrorType getErrorType() {
            return this.errorType;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static enum ErrorType {
        NONE,
        MISSING_RETURN,
        INTERFACE_METHOD_BODY,
        MISSING_BODY,
        RETURN_TYPE_MISMATCH,
        VOID_METHOD_RETURNS_VALUE,
        DUPLICATE_METHOD,
        DUPLICATE_PARAMETER,
        PARAMETER_UNDEFINED,
        SAM_TYPE_INCOMPATIBLE;

    }
}

