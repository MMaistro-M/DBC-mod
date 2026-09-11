/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.expression;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.InnerCallableScope;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.expression.ExpressionNode;
import noppes.npcs.client.gui.util.script.interpreter.expression.ExpressionParser;
import noppes.npcs.client.gui.util.script.interpreter.expression.ExpressionToken;
import noppes.npcs.client.gui.util.script.interpreter.expression.ExpressionTokenizer;
import noppes.npcs.client.gui.util.script.interpreter.expression.OperatorType;
import noppes.npcs.client.gui.util.script.interpreter.expression.TypeRules;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.ClassTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.OverloadSelector;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class ExpressionTypeResolver {
    private final ExpressionNode.TypeResolverContext context;
    private final ScriptDocument document;
    private final int basePosition;
    public static TypeInfo CURRENT_EXPECTED_TYPE = null;

    public ExpressionTypeResolver(ExpressionNode.TypeResolverContext context) {
        this.context = context;
        this.document = null;
        this.basePosition = 0;
    }

    public ExpressionTypeResolver(ExpressionNode.TypeResolverContext context, ScriptDocument document) {
        this(context, document, 0);
    }

    public ExpressionTypeResolver(ExpressionNode.TypeResolverContext context, ScriptDocument document, int basePosition) {
        this.context = context;
        this.document = document;
        this.basePosition = basePosition;
    }

    public TypeInfo resolve(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            return null;
        }
        try {
            List<ExpressionToken> tokens = ExpressionTokenizer.tokenize(expression);
            if (tokens.isEmpty()) {
                return null;
            }
            ExpressionParser parser = new ExpressionParser(tokens);
            ExpressionNode ast = parser.parse();
            if (ast == null) {
                return null;
            }
            return this.resolveNodeType(ast);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static boolean containsOperators(String expression) {
        if (expression == null) {
            return false;
        }
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            switch (c) {
                case '!': 
                case '%': 
                case '&': 
                case '*': 
                case '+': 
                case '-': 
                case '/': 
                case ':': 
                case '<': 
                case '=': 
                case '>': 
                case '?': 
                case '^': 
                case '|': 
                case '~': {
                    return true;
                }
            }
        }
        return false;
    }

    private TypeInfo resolveNodeType(ExpressionNode node) {
        if (node == null) {
            return null;
        }
        if (node instanceof ExpressionNode.IntLiteralNode) {
            TypeInfo narrowed;
            if (CURRENT_EXPECTED_TYPE != null && (narrowed = TypeChecker.narrowLiteralToExpectedType(((ExpressionNode.IntLiteralNode)node).getValue(), CURRENT_EXPECTED_TYPE)) != null) {
                return narrowed;
            }
            return TypeInfo.fromPrimitive("int");
        }
        if (node instanceof ExpressionNode.LongLiteralNode) {
            return TypeInfo.fromPrimitive("long");
        }
        if (node instanceof ExpressionNode.FloatLiteralNode) {
            return TypeInfo.fromPrimitive("float");
        }
        if (node instanceof ExpressionNode.DoubleLiteralNode) {
            return TypeInfo.fromPrimitive("double");
        }
        if (node instanceof ExpressionNode.BooleanLiteralNode) {
            return TypeInfo.fromPrimitive("boolean");
        }
        if (node instanceof ExpressionNode.CharLiteralNode) {
            return TypeInfo.fromPrimitive("char");
        }
        if (node instanceof ExpressionNode.StringLiteralNode) {
            return TypeInfo.string();
        }
        if (node instanceof ExpressionNode.NullLiteralNode) {
            return TypeInfo.unresolved("null", "<null>");
        }
        if (node instanceof ExpressionNode.IdentifierNode) {
            return this.context.resolveIdentifier(((ExpressionNode.IdentifierNode)node).getName());
        }
        if (node instanceof ExpressionNode.MemberAccessNode) {
            ExpressionNode.MemberAccessNode ma = (ExpressionNode.MemberAccessNode)node;
            TypeInfo targetType = this.resolveNodeType(ma.getTarget());
            if (targetType == null || !targetType.isResolved()) {
                return null;
            }
            if (targetType == TypeInfo.ANY || "any".equals(targetType.getFullName())) {
                return TypeInfo.ANY;
            }
            return this.context.resolveMemberAccess(targetType, ma.getMemberName());
        }
        if (node instanceof ExpressionNode.MethodCallNode) {
            TypeInfo targetType;
            ExpressionNode.MethodCallNode mc = (ExpressionNode.MethodCallNode)node;
            TypeInfo typeInfo = targetType = mc.getTarget() == null ? this.context.resolveIdentifier("this") : this.resolveNodeType(mc.getTarget());
            if (targetType == null || !targetType.isResolved()) {
                return null;
            }
            if (targetType == TypeInfo.ANY || "any".equals(targetType.getFullName())) {
                return TypeInfo.ANY;
            }
            TypeInfo[] argTypes = new TypeInfo[mc.getArguments().size()];
            for (int i = 0; i < argTypes.length; ++i) {
                argTypes[i] = this.resolveNodeType(mc.getArguments().get(i));
            }
            return this.context.resolveMethodCall(targetType, mc.getMethodName(), argTypes);
        }
        if (node instanceof ExpressionNode.ArrayAccessNode) {
            ExpressionNode.ArrayAccessNode aa = (ExpressionNode.ArrayAccessNode)node;
            TypeInfo arrayType = this.resolveNodeType(aa.getArray());
            if (arrayType == null || !arrayType.isResolved()) {
                return null;
            }
            if (arrayType == TypeInfo.ANY || "any".equals(arrayType.getFullName())) {
                return TypeInfo.ANY;
            }
            String typeName = arrayType.getFullName();
            if (typeName.endsWith("[]")) {
                String elementTypeName = typeName.substring(0, typeName.length() - 2);
                return this.context.resolveTypeName(elementTypeName);
            }
            return this.context.resolveArrayAccess(arrayType);
        }
        if (node instanceof ExpressionNode.NewNode) {
            return this.context.resolveTypeName(((ExpressionNode.NewNode)node).getTypeName());
        }
        if (node instanceof ExpressionNode.BinaryOpNode) {
            ExpressionNode.BinaryOpNode bin = (ExpressionNode.BinaryOpNode)node;
            TypeInfo leftType = this.resolveNodeType(bin.getLeft());
            TypeInfo rightType = this.resolveNodeType(bin.getRight());
            return TypeRules.resolveBinaryOperatorType(bin.getOperator(), leftType, rightType);
        }
        if (node instanceof ExpressionNode.UnaryOpNode) {
            String negatedText;
            TypeInfo narrowed;
            ExpressionNode.UnaryOpNode un = (ExpressionNode.UnaryOpNode)node;
            if (un.getOperator() == OperatorType.UNARY_MINUS && un.getOperand() instanceof ExpressionNode.IntLiteralNode && CURRENT_EXPECTED_TYPE != null && (narrowed = TypeChecker.narrowLiteralToExpectedType(negatedText = "-" + ((ExpressionNode.IntLiteralNode)un.getOperand()).getValue(), CURRENT_EXPECTED_TYPE)) != null) {
                return narrowed;
            }
            TypeInfo operandType = this.resolveNodeType(un.getOperand());
            return TypeRules.resolveUnaryOperatorType(un.getOperator(), operandType);
        }
        if (node instanceof ExpressionNode.TernaryNode) {
            ExpressionNode.TernaryNode tern = (ExpressionNode.TernaryNode)node;
            TypeInfo thenType = this.resolveNodeType(tern.getThenExpr());
            TypeInfo elseType = this.resolveNodeType(tern.getElseExpr());
            return TypeRules.resolveTernaryType(thenType, elseType);
        }
        if (node instanceof ExpressionNode.CastNode) {
            return this.context.resolveTypeName(((ExpressionNode.CastNode)node).getTypeName());
        }
        if (node instanceof ExpressionNode.InstanceofNode) {
            return TypeInfo.fromPrimitive("boolean");
        }
        if (node instanceof ExpressionNode.AssignmentNode) {
            return this.resolveNodeType(((ExpressionNode.AssignmentNode)node).getTarget());
        }
        if (node instanceof ExpressionNode.ParenthesizedNode) {
            return this.resolveNodeType(((ExpressionNode.ParenthesizedNode)node).getInner());
        }
        if (node instanceof ExpressionNode.LambdaNode) {
            return this.resolveLambdaType((ExpressionNode.LambdaNode)node);
        }
        if (node instanceof ExpressionNode.JSFunctionNode) {
            return this.resolveJSFunctionType((ExpressionNode.JSFunctionNode)node);
        }
        if (node instanceof ExpressionNode.JSArrowNode) {
            return this.resolveJSArrowType((ExpressionNode.JSArrowNode)node);
        }
        if (node instanceof ExpressionNode.MethodReferenceNode) {
            return this.resolveMethodReferenceType((ExpressionNode.MethodReferenceNode)node);
        }
        return null;
    }

    private TypeInfo resolveLambdaType(ExpressionNode.LambdaNode lambda) {
        TypeInfo expectedType = CURRENT_EXPECTED_TYPE;
        if (expectedType == null) {
            return TypeInfo.fromClass(Object.class);
        }
        MethodInfo sam = expectedType.getSingleAbstractMethod();
        if (sam != null && this.document != null) {
            InnerCallableScope scope = this.findLambdaScopeByPosition(lambda.getStart(), lambda.getParameterNames());
            if (scope != null) {
                scope.setExpectedType(expectedType);
                List<FieldInfo> samParams = sam.getParameters();
                List<FieldInfo> lambdaParams = scope.getParameters();
                if (samParams.size() == lambdaParams.size()) {
                    for (int i = 0; i < lambdaParams.size(); ++i) {
                        FieldInfo lambdaParam = lambdaParams.get(i);
                        TypeInfo inferredType = samParams.get(i).getTypeInfo();
                        if (inferredType == null) continue;
                        lambdaParam.setInferredType(inferredType);
                    }
                }
            }
            if (!lambda.isBlock() && lambda.getBody() != null) {
                TypeInfo typeInfo = this.resolveNodeType(lambda.getBody());
            }
            return expectedType;
        }
        String typeName = expectedType.getFullName();
        if (typeName != null && (typeName.contains("Function") || typeName.contains("Consumer") || typeName.contains("Predicate") || typeName.contains("Supplier") || typeName.equals("java.lang.Runnable"))) {
            if (lambda.getScopeRef() != null) {
                lambda.getScopeRef().setExpectedType(expectedType);
            }
            return expectedType;
        }
        return TypeInfo.fromClass(Object.class);
    }

    private InnerCallableScope findLambdaScopeByPosition(int expressionRelativePos, List<String> paramNames) {
        if (this.document == null) {
            return null;
        }
        int absolutePos = this.basePosition + expressionRelativePos;
        for (InnerCallableScope scope : this.document.getInnerScopes()) {
            if (scope.getKind() != InnerCallableScope.Kind.JAVA_LAMBDA || absolutePos < scope.getHeaderStart() || absolutePos >= scope.getFullEnd() || scope.getParameters().size() != paramNames.size()) continue;
            boolean match = true;
            for (int i = 0; i < paramNames.size(); ++i) {
                if (scope.getParameters().get(i).getName().equals(paramNames.get(i))) continue;
                match = false;
                break;
            }
            if (!match) continue;
            return scope;
        }
        return null;
    }

    private TypeInfo resolveJSFunctionType(ExpressionNode.JSFunctionNode functionNode) {
        TypeInfo expectedType = CURRENT_EXPECTED_TYPE;
        if (expectedType == null) {
            TypeInfo functionType = this.context.resolveTypeName("Function");
            if (functionType != null && functionType.isResolved()) {
                return functionType;
            }
            return TypeInfo.fromClass(Object.class);
        }
        MethodInfo sam = expectedType.getSingleAbstractMethod();
        if (sam != null && this.document != null) {
            InnerCallableScope scope = this.findJSFunctionScopeByPosition(functionNode.getStart(), functionNode.getParameterNames());
            if (scope != null) {
                scope.setExpectedType(expectedType);
                List<FieldInfo> samParams = sam.getParameters();
                List<FieldInfo> functionParams = scope.getParameters();
                if (samParams.size() == functionParams.size()) {
                    for (int i = 0; i < functionParams.size(); ++i) {
                        FieldInfo functionParam = functionParams.get(i);
                        TypeInfo inferredType = samParams.get(i).getTypeInfo();
                        if (inferredType == null) continue;
                        functionParam.setInferredType(inferredType);
                    }
                }
            }
            return expectedType;
        }
        String typeName = expectedType.getFullName();
        if (typeName != null && (typeName.contains("Function") || typeName.contains("Consumer") || typeName.contains("Predicate") || typeName.contains("Supplier") || typeName.equals("java.lang.Runnable"))) {
            if (functionNode.getScopeRef() != null) {
                functionNode.getScopeRef().setExpectedType(expectedType);
            }
            return expectedType;
        }
        if (typeName != null && typeName.equals("Function")) {
            return expectedType;
        }
        return TypeInfo.fromClass(Object.class);
    }

    private InnerCallableScope findJSFunctionScopeByPosition(int expressionRelativePos, List<String> paramNames) {
        if (this.document == null) {
            return null;
        }
        int absolutePos = this.basePosition + expressionRelativePos;
        for (InnerCallableScope scope : this.document.getInnerScopes()) {
            if (scope.getKind() != InnerCallableScope.Kind.JS_FUNCTION_EXPR || absolutePos < scope.getHeaderStart() || absolutePos >= scope.getFullEnd() || scope.getParameters().size() != paramNames.size()) continue;
            boolean match = true;
            for (int i = 0; i < paramNames.size(); ++i) {
                if (scope.getParameters().get(i).getName().equals(paramNames.get(i))) continue;
                match = false;
                break;
            }
            if (!match) continue;
            return scope;
        }
        return null;
    }

    private TypeInfo resolveJSArrowType(ExpressionNode.JSArrowNode arrowNode) {
        TypeInfo expectedType = CURRENT_EXPECTED_TYPE;
        if (expectedType == null) {
            TypeInfo functionType = this.context.resolveTypeName("Function");
            if (functionType != null && functionType.isResolved()) {
                return functionType;
            }
            return TypeInfo.fromClass(Object.class);
        }
        MethodInfo sam = expectedType.getSingleAbstractMethod();
        if (sam != null && this.document != null) {
            InnerCallableScope scope = this.findJSArrowScopeByPosition(arrowNode.getStart(), arrowNode.getParameterNames());
            if (scope != null) {
                scope.setExpectedType(expectedType);
                List<FieldInfo> samParams = sam.getParameters();
                List<FieldInfo> arrowParams = scope.getParameters();
                if (samParams.size() == arrowParams.size()) {
                    for (int i = 0; i < arrowParams.size(); ++i) {
                        TypeInfo inferredType = samParams.get(i).getTypeInfo();
                        if (inferredType == null) continue;
                        arrowParams.get(i).setInferredType(inferredType);
                    }
                }
            }
            return expectedType;
        }
        String typeName = expectedType.getFullName();
        if (typeName != null && (typeName.contains("Function") || typeName.contains("Consumer") || typeName.contains("Predicate") || typeName.contains("Supplier") || typeName.equals("java.lang.Runnable"))) {
            if (arrowNode.getScopeRef() != null) {
                arrowNode.getScopeRef().setExpectedType(expectedType);
            }
            return expectedType;
        }
        if (typeName != null && typeName.equals("Function")) {
            return expectedType;
        }
        return TypeInfo.fromClass(Object.class);
    }

    private InnerCallableScope findJSArrowScopeByPosition(int expressionRelativePos, List<String> paramNames) {
        if (this.document == null) {
            return null;
        }
        int absolutePos = this.basePosition + expressionRelativePos;
        for (InnerCallableScope scope : this.document.getInnerScopes()) {
            if (scope.getKind() != InnerCallableScope.Kind.JS_ARROW_FUNC || absolutePos < scope.getHeaderStart() || absolutePos >= scope.getFullEnd() || scope.getParameters().size() != paramNames.size()) continue;
            boolean match = true;
            for (int i = 0; i < paramNames.size(); ++i) {
                if (scope.getParameters().get(i).getName().equals(paramNames.get(i))) continue;
                match = false;
                break;
            }
            if (!match) continue;
            return scope;
        }
        return null;
    }

    private TypeInfo resolveMethodReferenceType(ExpressionNode.MethodReferenceNode methodRef) {
        TypeInfo expectedType = CURRENT_EXPECTED_TYPE;
        if (expectedType == null || !expectedType.isFunctionalInterface()) {
            return TypeInfo.fromClass(Object.class);
        }
        MethodInfo sam = expectedType.getSingleAbstractMethod();
        if (sam == null) {
            return TypeInfo.fromClass(Object.class);
        }
        MethodReferenceTarget resolvedTarget = this.resolveMethodReferenceTarget(methodRef);
        if (resolvedTarget == null || resolvedTarget.type == null) {
            return expectedType;
        }
        String methodName = methodRef.getMethodName();
        if ("new".equals(methodName)) {
            return this.resolveConstructorReference(resolvedTarget, sam, expectedType);
        }
        return this.resolveMethodReference(resolvedTarget, methodName, sam, expectedType);
    }

    private MethodReferenceTarget resolveMethodReferenceTarget(ExpressionNode.MethodReferenceNode methodRef) {
        TypeInfo arrayType;
        ExpressionNode target = methodRef.getTarget();
        if (target == null) {
            return null;
        }
        if (target instanceof ExpressionNode.IdentifierNode) {
            String name = ((ExpressionNode.IdentifierNode)target).getName();
            return this.resolveSimpleTarget(name);
        }
        if (target instanceof ExpressionNode.MemberAccessNode) {
            return this.resolveQualifiedTarget((ExpressionNode.MemberAccessNode)target);
        }
        if (target instanceof ExpressionNode.ArrayAccessNode && (arrayType = this.resolveNodeType(target)) != null && arrayType.isResolved()) {
            return new MethodReferenceTarget(arrayType, true);
        }
        TypeInfo exprType = this.resolveNodeType(target);
        if (exprType != null && exprType.isResolved()) {
            return new MethodReferenceTarget(exprType, false);
        }
        return null;
    }

    private MethodReferenceTarget resolveSimpleTarget(String name) {
        if ("this".equals(name)) {
            TypeInfo thisType = this.context.resolveIdentifier("this");
            return thisType != null ? new MethodReferenceTarget(thisType, false) : null;
        }
        if ("super".equals(name)) {
            TypeInfo superType = this.context.resolveIdentifier("super");
            return superType != null ? new MethodReferenceTarget(superType, false) : null;
        }
        TypeInfo typeInfo = this.context.resolveTypeName(name);
        if (typeInfo != null && typeInfo.isResolved()) {
            ClassTypeInfo classRef = new ClassTypeInfo(typeInfo);
            return new MethodReferenceTarget(classRef, true);
        }
        TypeInfo varType = this.context.resolveIdentifier(name);
        if (varType != null && varType.isResolved()) {
            boolean isClassRef = varType.isClassReference();
            return new MethodReferenceTarget(varType, isClassRef);
        }
        return null;
    }

    private MethodReferenceTarget resolveQualifiedTarget(ExpressionNode.MemberAccessNode memberAccess) {
        TypeInfo typeInfo;
        String qualifiedName = this.buildQualifiedName(memberAccess);
        if (qualifiedName != null && (typeInfo = this.context.resolveTypeName(qualifiedName)) != null && typeInfo.isResolved()) {
            ClassTypeInfo classRef = new ClassTypeInfo(typeInfo);
            return new MethodReferenceTarget(classRef, true);
        }
        TypeInfo exprType = this.resolveNodeType(memberAccess);
        if (exprType != null && exprType.isResolved()) {
            boolean isClassRef = exprType.isClassReference();
            return new MethodReferenceTarget(exprType, isClassRef);
        }
        return null;
    }

    private String buildQualifiedName(ExpressionNode node) {
        ExpressionNode.MemberAccessNode ma;
        String baseName;
        if (node instanceof ExpressionNode.IdentifierNode) {
            return ((ExpressionNode.IdentifierNode)node).getName();
        }
        if (node instanceof ExpressionNode.MemberAccessNode && (baseName = this.buildQualifiedName((ma = (ExpressionNode.MemberAccessNode)node).getTarget())) != null) {
            return baseName + "." + ma.getMemberName();
        }
        return null;
    }

    private TypeInfo resolveMethodReference(MethodReferenceTarget target, String methodName, MethodInfo sam, TypeInfo expectedType) {
        TypeInfo targetType;
        TypeInfo typeInfo = targetType = target.isClassRef && target.type instanceof ClassTypeInfo ? ((ClassTypeInfo)target.type).getInstanceType() : target.type;
        if (targetType == null || !targetType.hasMethod(methodName)) {
            return TypeInfo.fromClass(Object.class);
        }
        MethodInfo method = this.findBestMethodForReference(targetType, methodName, sam, target.isClassRef);
        if (method == null) {
            return TypeInfo.fromClass(Object.class);
        }
        String error = this.validateMethodSignature(method, sam, target.isClassRef, targetType);
        if (error != null) {
            return TypeInfo.fromClass(Object.class);
        }
        return expectedType;
    }

    private MethodInfo findBestMethodForReference(TypeInfo targetType, String methodName, MethodInfo sam, boolean isClassRef) {
        List<MethodInfo> allOverloads = targetType.getAllMethodOverloads(methodName);
        if (allOverloads.isEmpty()) {
            return null;
        }
        TypeInfo[] samParamTypes = this.extractSamParamTypes(sam, isClassRef, targetType);
        ArrayList<MethodInfo> candidates = new ArrayList<MethodInfo>();
        int expectedArity = samParamTypes.length;
        for (MethodInfo method : allOverloads) {
            int methodArity = method.getParameters().size();
            if (methodArity == expectedArity) {
                candidates.add(method);
                continue;
            }
            if (!isClassRef || methodArity != sam.getParameters().size() - 1 || this.isStaticMethod(method)) continue;
            candidates.add(method);
        }
        if (candidates.isEmpty()) {
            return null;
        }
        if (candidates.size() == 1) {
            return (MethodInfo)candidates.get(0);
        }
        return OverloadSelector.selectBestOverload(candidates, samParamTypes);
    }

    private TypeInfo[] extractSamParamTypes(MethodInfo sam, boolean isClassRef, TypeInfo targetType) {
        List<FieldInfo> samParams = sam.getParameters();
        TypeInfo[] types = new TypeInfo[samParams.size()];
        for (int i = 0; i < samParams.size(); ++i) {
            types[i] = samParams.get(i).getTypeInfo();
        }
        return types;
    }

    private boolean isStaticMethod(MethodInfo method) {
        Method javaMethod = method.getJavaMethod();
        if (javaMethod != null) {
            return Modifier.isStatic(javaMethod.getModifiers());
        }
        return method.isStatic();
    }

    private String validateMethodSignature(MethodInfo method, MethodInfo sam, boolean isClassRef, TypeInfo targetType) {
        TypeInfo firstSamParam;
        boolean isUnbound;
        List<FieldInfo> methodParams = method.getParameters();
        List<FieldInfo> samParams = sam.getParameters();
        int methodArity = methodParams.size();
        int samArity = samParams.size();
        boolean bl = isUnbound = isClassRef && methodArity == samArity - 1 && !this.isStaticMethod(method);
        if (methodArity != samArity && !isUnbound) {
            return "Parameter count mismatch";
        }
        int offset = isUnbound ? 1 : 0;
        for (int i = 0; i < methodArity; ++i) {
            TypeInfo methodParamType = methodParams.get(i).getTypeInfo();
            TypeInfo samParamType = samParams.get(i + offset).getTypeInfo();
            if (methodParamType == null || samParamType == null || TypeChecker.isTypeCompatible(methodParamType, samParamType)) continue;
            return "Parameter type mismatch at position " + (i + 1);
        }
        if (isUnbound && samArity > 0 && (firstSamParam = samParams.get(0).getTypeInfo()) != null && !TypeChecker.isTypeCompatible(targetType, firstSamParam)) {
            return "Receiver type mismatch";
        }
        TypeInfo methodReturn = method.getReturnType();
        TypeInfo samReturn = sam.getReturnType();
        if (samReturn != null && methodReturn != null) {
            boolean samIsVoid;
            boolean bl2 = samIsVoid = "void".equals(samReturn.getFullName()) || samReturn.getJavaClass() == Void.TYPE;
            if (!samIsVoid && !TypeChecker.isTypeCompatible(samReturn, methodReturn)) {
                return "Return type mismatch";
            }
        }
        return null;
    }

    private TypeInfo resolveConstructorReference(MethodReferenceTarget target, MethodInfo sam, TypeInfo expectedType) {
        TypeInfo classType;
        TypeInfo targetType = target.type;
        if (this.isArrayType(targetType)) {
            return this.resolveArrayConstructorReference(targetType, sam, expectedType);
        }
        TypeInfo typeInfo = classType = target.type instanceof ClassTypeInfo ? ((ClassTypeInfo)target.type).getInstanceType() : target.type;
        if (classType == null) {
            return TypeInfo.fromClass(Object.class);
        }
        if (classType.getKind() == TypeInfo.Kind.INTERFACE) {
            return TypeInfo.fromClass(Object.class);
        }
        List<FieldInfo> samParams = sam.getParameters();
        TypeInfo[] paramTypes = new TypeInfo[samParams.size()];
        for (int i = 0; i < samParams.size(); ++i) {
            paramTypes[i] = samParams.get(i).getTypeInfo();
        }
        MethodInfo constructor = classType.findConstructor(paramTypes);
        if (constructor == null && samParams.size() > 0) {
            constructor = classType.findConstructor(samParams.size());
        }
        if (constructor == null) {
            return TypeInfo.fromClass(Object.class);
        }
        TypeInfo samReturn = sam.getReturnType();
        if (samReturn != null && !TypeChecker.isTypeCompatible(samReturn, classType)) {
            return TypeInfo.fromClass(Object.class);
        }
        return expectedType;
    }

    private TypeInfo resolveArrayConstructorReference(TypeInfo arrayType, MethodInfo sam, TypeInfo expectedType) {
        List<FieldInfo> samParams = sam.getParameters();
        if (samParams.size() != 1) {
            return TypeInfo.fromClass(Object.class);
        }
        TypeInfo sizeParam = samParams.get(0).getTypeInfo();
        if (sizeParam == null || !this.isIntLike(sizeParam)) {
            return TypeInfo.fromClass(Object.class);
        }
        TypeInfo samReturn = sam.getReturnType();
        if (samReturn != null && !TypeChecker.isTypeCompatible(samReturn, arrayType)) {
            return TypeInfo.fromClass(Object.class);
        }
        return expectedType;
    }

    private boolean isArrayType(TypeInfo type) {
        if (type == null) {
            return false;
        }
        String name = type.getFullName();
        return name != null && name.endsWith("[]");
    }

    private boolean isIntLike(TypeInfo type) {
        if (type == null) {
            return false;
        }
        String name = type.getFullName();
        return "int".equals(name) || "java.lang.Integer".equals(name) || "long".equals(name) || "java.lang.Long".equals(name);
    }

    public static ExpressionNode.TypeResolverContext createBasicContext() {
        return new ExpressionNode.TypeResolverContext(){

            @Override
            public TypeInfo resolveIdentifier(String name) {
                if ("true".equals(name) || "false".equals(name)) {
                    return TypeInfo.fromPrimitive("boolean");
                }
                if ("null".equals(name)) {
                    return TypeInfo.unresolved("null", "<null>");
                }
                return null;
            }

            @Override
            public TypeInfo resolveMemberAccess(TypeInfo targetType, String memberName) {
                return null;
            }

            @Override
            public TypeInfo resolveMethodCall(TypeInfo targetType, String methodName, TypeInfo[] argTypes) {
                return null;
            }

            @Override
            public TypeInfo resolveArrayAccess(TypeInfo arrayType) {
                String typeName = arrayType.getFullName();
                if (typeName.endsWith("[]")) {
                    String elementType;
                    switch (elementType = typeName.substring(0, typeName.length() - 2)) {
                        case "int": 
                        case "long": 
                        case "float": 
                        case "double": 
                        case "byte": 
                        case "short": 
                        case "char": 
                        case "boolean": {
                            return TypeInfo.fromPrimitive(elementType);
                        }
                    }
                    return TypeInfo.unresolved(elementType, elementType);
                }
                return null;
            }

            @Override
            public TypeInfo resolveTypeName(String typeName) {
                switch (typeName) {
                    case "int": 
                    case "long": 
                    case "float": 
                    case "double": 
                    case "byte": 
                    case "short": 
                    case "char": 
                    case "boolean": 
                    case "void": {
                        return TypeInfo.fromPrimitive(typeName);
                    }
                }
                return TypeInfo.unresolved(typeName, typeName);
            }
        };
    }

    private static class MethodReferenceTarget {
        final TypeInfo type;
        final boolean isClassRef;

        MethodReferenceTarget(TypeInfo type, boolean isClassRef) {
            this.type = type;
            this.isClassRef = isClassRef;
        }
    }
}

