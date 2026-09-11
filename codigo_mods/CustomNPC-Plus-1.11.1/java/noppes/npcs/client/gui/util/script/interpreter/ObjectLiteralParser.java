/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import java.util.ArrayList;
import java.util.List;
import noppes.npcs.client.gui.util.script.interpreter.InnerCallableScope;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public final class ObjectLiteralParser {
    private ObjectLiteralParser() {
    }

    public static ObjectLiteralAnalysis parse(String objectLiteral, int baseOffset, boolean inferTypes, boolean assumeComplete, ExpressionTypeResolverFn typeResolver, MethodReferenceResolverFn methodResolver) {
        int endExclusive;
        int trailing;
        int leading;
        if (objectLiteral == null) {
            return null;
        }
        for (leading = 0; leading < objectLiteral.length() && Character.isWhitespace(objectLiteral.charAt(leading)); ++leading) {
        }
        for (trailing = objectLiteral.length(); trailing > leading && Character.isWhitespace(objectLiteral.charAt(trailing - 1)); --trailing) {
        }
        String src = objectLiteral.substring(leading, trailing);
        int absBase = baseOffset + leading;
        if (src.isEmpty() || src.charAt(0) != '{') {
            return null;
        }
        if (assumeComplete) {
            endExclusive = src.length();
        } else {
            endExclusive = ObjectLiteralParser.findMatchingObjectLiteralEnd(src);
            if (endExclusive <= 0) {
                return null;
            }
        }
        boolean supportsInference = endExclusive == src.length();
        int bodyStart = 1;
        int bodyEnd = endExclusive - 1;
        ArrayList<ObjectLiteralProperty> props = new ArrayList<ObjectLiteralProperty>();
        boolean sawUnsupported = false;
        int i = bodyStart;
        while (i < bodyEnd && (i = ObjectLiteralParser.skipSeparatorsAndComments(src, i, bodyEnd)) < bodyEnd) {
            int keyEnd;
            boolean isIdentifierKey;
            String keyName;
            int keyStart;
            block30: {
                char kc;
                block31: {
                    int afterAsync;
                    block29: {
                        keyStart = i;
                        keyName = null;
                        isIdentifierKey = false;
                        kc = src.charAt(i);
                        if (kc != '\"' && kc != '\'') break block29;
                        keyEnd = ObjectLiteralParser.scanStringLiteralEnd(src, i);
                        if (keyEnd < 0 || keyEnd > bodyEnd) {
                            sawUnsupported = true;
                            break;
                        }
                        keyName = src.substring(i + 1, keyEnd - 1);
                        isIdentifierKey = false;
                        if (!ObjectLiteralParser.isSimpleIdentifier(keyName)) {
                            sawUnsupported = true;
                            i = ObjectLiteralParser.scanToNextTopLevelComma(src, keyEnd, bodyEnd);
                            continue;
                        }
                        i = keyEnd;
                        break block30;
                    }
                    if (!Character.isJavaIdentifierStart(kc)) break block31;
                    ++i;
                    while (i < bodyEnd && Character.isJavaIdentifierPart(src.charAt(i))) {
                        ++i;
                    }
                    keyName = src.substring(keyStart, i);
                    isIdentifierKey = true;
                    if (!"async".equals(keyName)) break block30;
                    for (afterAsync = i; afterAsync < bodyEnd && Character.isWhitespace(src.charAt(afterAsync)); ++afterAsync) {
                    }
                    if (afterAsync >= bodyEnd || !Character.isJavaIdentifierStart(src.charAt(afterAsync))) break block30;
                    int realKeyStart = afterAsync++;
                    while (afterAsync < bodyEnd && Character.isJavaIdentifierPart(src.charAt(afterAsync))) {
                        ++afterAsync;
                    }
                    keyStart = realKeyStart;
                    keyName = src.substring(realKeyStart, afterAsync);
                    i = afterAsync;
                    break block30;
                }
                if (kc == '[') {
                    sawUnsupported = true;
                    i = ObjectLiteralParser.scanToNextTopLevelComma(src, i, bodyEnd);
                    continue;
                }
                if (kc == '.' && i + 2 < bodyEnd && src.charAt(i + 1) == '.' && src.charAt(i + 2) == '.') {
                    sawUnsupported = true;
                    i = ObjectLiteralParser.scanToNextTopLevelComma(src, i, bodyEnd);
                    continue;
                }
                sawUnsupported = true;
                i = ObjectLiteralParser.scanToNextTopLevelComma(src, i, bodyEnd);
                continue;
            }
            keyEnd = i;
            while (i < bodyEnd && Character.isWhitespace(src.charAt(i))) {
                ++i;
            }
            if (i < bodyEnd && src.charAt(i) == '(' && isIdentifierKey) {
                int afterParen;
                int parenOpen = i;
                int parenClose = ObjectLiteralParser.findMatchingParen(src, parenOpen);
                if (parenClose < 0 || parenClose >= bodyEnd) {
                    sawUnsupported = true;
                    i = ObjectLiteralParser.scanToNextTopLevelComma(src, i, bodyEnd);
                    continue;
                }
                List<String> params = ObjectLiteralParser.extractParameterNames(src.substring(parenOpen + 1, parenClose));
                for (afterParen = parenClose + 1; afterParen < bodyEnd && Character.isWhitespace(src.charAt(afterParen)); ++afterParen) {
                }
                if (afterParen >= bodyEnd || src.charAt(afterParen) != '{') {
                    sawUnsupported = true;
                    i = ObjectLiteralParser.scanToNextTopLevelComma(src, afterParen, bodyEnd);
                    continue;
                }
                int braceEnd = ObjectLiteralParser.findMatchingObjectLiteralEnd(src.substring(afterParen));
                if (braceEnd <= 0) {
                    sawUnsupported = true;
                    break;
                }
                int methodBodyEnd = afterParen + braceEnd;
                String bodyText = src.substring(afterParen, methodBodyEnd);
                TypeInfo returnType = ObjectLiteralParser.inferReturnTypeFromBlockBody(bodyText, absBase + afterParen, typeResolver);
                CallableInfo callableInfo = new CallableInfo(params, returnType, absBase + afterParen, absBase + methodBodyEnd, true);
                int keyStartAbs = absBase + keyStart;
                int keyEndAbs = absBase + keyEnd;
                int valueStartAbs = absBase + parenOpen;
                int valueEndAbs = absBase + methodBodyEnd;
                props.add(new ObjectLiteralProperty(keyName, true, keyStartAbs, keyEndAbs, valueStartAbs, valueEndAbs, returnType, callableInfo));
                for (i = methodBodyEnd; i < bodyEnd && Character.isWhitespace(src.charAt(i)); ++i) {
                }
                if (i >= bodyEnd || src.charAt(i) != ',') continue;
                ++i;
                continue;
            }
            if (i >= bodyEnd || src.charAt(i) != ':') {
                sawUnsupported = true;
                i = ObjectLiteralParser.scanToNextTopLevelComma(src, i, bodyEnd);
                continue;
            }
            ++i;
            while (i < bodyEnd && Character.isWhitespace(src.charAt(i))) {
                ++i;
            }
            int valueStart = i;
            int valueEnd = ObjectLiteralParser.scanTopLevelValueEnd(src, valueStart, bodyEnd);
            if (valueEnd < valueStart) {
                sawUnsupported = true;
                break;
            }
            TypeInfo valueType = null;
            CallableInfo callableInfo = null;
            if (inferTypes && typeResolver != null) {
                String valueExpr = src.substring(valueStart, valueEnd).trim();
                callableInfo = ObjectLiteralParser.detectCallableExpression(valueExpr, absBase + valueStart, methodResolver, typeResolver);
                valueType = callableInfo != null ? callableInfo.returnType : ObjectLiteralParser.inferValueType(valueExpr, absBase + valueStart, typeResolver);
            }
            int keyStartAbs = absBase + keyStart;
            int keyEndAbs = absBase + keyEnd;
            int valueStartAbs = absBase + valueStart;
            int valueEndAbs = absBase + valueEnd;
            if (keyName != null) {
                props.add(new ObjectLiteralProperty(keyName, isIdentifierKey, keyStartAbs, keyEndAbs, valueStartAbs, valueEndAbs, valueType, callableInfo));
            }
            i = valueEnd;
            i = ObjectLiteralParser.skipSeparatorsAndComments(src, i, bodyEnd);
        }
        if (sawUnsupported) {
            supportsInference = false;
        }
        TypeInfo inferredType = null;
        if (inferTypes && supportsInference) {
            inferredType = TypeInfo.objectLiteral(props);
        }
        return new ObjectLiteralAnalysis(props, supportsInference, inferredType);
    }

    public static String getSyntheticObjectLiteralDisplayName(JSTypeInfo jsType) {
        if (jsType == null) {
            return null;
        }
        String namespace = jsType.getNamespace();
        String simple = jsType.getSimpleName();
        if ("__synthetic__".equals(namespace) && simple != null && simple.startsWith("__ObjectLiteral$")) {
            return "Object";
        }
        return null;
    }

    public static DynamicPropertyAccess parseDynamicPropertyAccess(String lhs, int lhsStart) {
        if (lhs == null || lhs.isEmpty()) {
            return null;
        }
        int dotIndex = lhs.lastIndexOf(46);
        int bracketIndex = lhs.lastIndexOf(91);
        if (dotIndex >= 0 && dotIndex > bracketIndex) {
            String receiverExpr = lhs.substring(0, dotIndex).trim();
            String propertyName = lhs.substring(dotIndex + 1).trim();
            if (!receiverExpr.isEmpty() && ObjectLiteralParser.isSimpleIdentifier(propertyName)) {
                return new DynamicPropertyAccess(receiverExpr, propertyName, lhsStart, false);
            }
            return null;
        }
        if (bracketIndex >= 0 && lhs.endsWith("]")) {
            String receiverExpr = lhs.substring(0, bracketIndex).trim();
            String keyExpr = lhs.substring(bracketIndex + 1, lhs.length() - 1).trim();
            String propertyName = ObjectLiteralParser.extractQuotedPropertyName(keyExpr);
            if (!receiverExpr.isEmpty() && propertyName != null && ObjectLiteralParser.isSimpleIdentifier(propertyName)) {
                return new DynamicPropertyAccess(receiverExpr, propertyName, lhsStart, true);
            }
        }
        return null;
    }

    public static TypeInfo resolveDynamicPropertyReceiverType(DynamicPropertyAccess access, ExpressionTypeResolverFn typeResolver, VariableResolverFn variableResolver) {
        if (access == null) {
            return null;
        }
        TypeInfo receiverType = typeResolver.resolve(access.receiverExpr, access.receiverStart);
        if (receiverType != null && receiverType.isResolved()) {
            return receiverType;
        }
        FieldInfo receiverField = variableResolver.resolve(access.receiverExpr, access.receiverStart);
        if (receiverField != null) {
            return receiverField.getTypeInfo();
        }
        return null;
    }

    public static String extractQuotedPropertyName(String expr) {
        if (expr == null || expr.length() < 2) {
            return null;
        }
        char quote = expr.charAt(0);
        if (quote != '\"' && quote != '\'' || expr.charAt(expr.length() - 1) != quote) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < expr.length() - 1; ++i) {
            char c = expr.charAt(i);
            if (c == '\\') {
                if (i + 1 >= expr.length() - 1) {
                    return null;
                }
                char escaped = expr.charAt(++i);
                sb.append(escaped);
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static boolean extendSyntheticObjectShape(TypeInfo receiverType, String propertyName, TypeInfo propertyType) {
        if (receiverType == null || propertyName == null || propertyName.isEmpty()) {
            return false;
        }
        if (!receiverType.isSyntheticObjectLiteralType()) {
            return false;
        }
        TypeInfo safeType = propertyType != null && propertyType.isResolved() ? propertyType : TypeInfo.ANY;
        receiverType.removeSyntheticField(propertyName);
        receiverType.addSyntheticField(propertyName, safeType);
        return true;
    }

    public static DynamicFieldResult resolveExistingField(DynamicPropertyAccess access, ExpressionTypeResolverFn typeResolver, VariableResolverFn varResolver) {
        TypeInfo receiver = ObjectLiteralParser.resolveDynamicPropertyReceiverType(access, typeResolver, varResolver);
        FieldInfo field = receiver != null && receiver.isResolved() ? receiver.getFieldInfo(access.propertyName) : null;
        return new DynamicFieldResult(receiver, access.propertyName, field);
    }

    public static DynamicFieldResult extendAndGetField(DynamicPropertyAccess access, TypeInfo existingReceiver, TypeInfo sourceType, ExpressionTypeResolverFn typeResolver, VariableResolverFn varResolver) {
        return ObjectLiteralParser.extendAndGetField(access, existingReceiver, sourceType, null, typeResolver, varResolver);
    }

    public static DynamicFieldResult extendAndGetField(DynamicPropertyAccess access, TypeInfo existingReceiver, TypeInfo sourceType, String rhsText, ExpressionTypeResolverFn typeResolver, VariableResolverFn varResolver) {
        CallableInfo callable;
        TypeInfo receiver;
        TypeInfo typeInfo = receiver = existingReceiver != null && existingReceiver.isResolved() ? existingReceiver : ObjectLiteralParser.resolveDynamicPropertyReceiverType(access, typeResolver, varResolver);
        if (receiver == null || !receiver.isSyntheticObjectLiteralType()) {
            return null;
        }
        CallableInfo callableInfo = callable = rhsText != null ? ObjectLiteralParser.detectCallableExpression(rhsText.trim(), -1, null, typeResolver) : null;
        if (callable != null) {
            ObjectLiteralParser.extendSyntheticObjectWithMethod(receiver, access.propertyName, callable);
        } else if (!ObjectLiteralParser.extendSyntheticObjectShape(receiver, access.propertyName, sourceType)) {
            return null;
        }
        return new DynamicFieldResult(receiver, access.propertyName, receiver.getFieldInfo(access.propertyName));
    }

    static CallableInfo detectCallableExpression(String expr, int exprStartAbs, MethodReferenceResolverFn methodResolver, ExpressionTypeResolverFn typeResolver) {
        MethodInfo method;
        if (expr == null || expr.isEmpty()) {
            return null;
        }
        if (expr.startsWith("function")) {
            return ObjectLiteralParser.parseFunctionExpressionCallable(expr, exprStartAbs, typeResolver);
        }
        if (expr.contains("=>")) {
            return ObjectLiteralParser.parseArrowFunctionCallable(expr, exprStartAbs, typeResolver);
        }
        if (ObjectLiteralParser.isSimpleIdentifier(expr.trim()) && methodResolver != null && (method = methodResolver.resolve(expr.trim(), new TypeInfo[0])) != null) {
            TypeInfo returnType = method.getReturnType() != null ? method.getReturnType() : TypeInfo.ANY;
            ArrayList<String> paramNames = new ArrayList<String>();
            for (FieldInfo param : method.getParameters()) {
                paramNames.add(param.getName());
            }
            return new CallableInfo(paramNames, returnType, exprStartAbs, exprStartAbs + expr.trim().length(), false);
        }
        return null;
    }

    private static CallableInfo parseFunctionExpressionCallable(String expr, int exprStartAbs, ExpressionTypeResolverFn typeResolver) {
        int parenOpen = expr.indexOf(40);
        if (parenOpen < 0) {
            return null;
        }
        int parenClose = ObjectLiteralParser.findMatchingParen(expr, parenOpen);
        if (parenClose < 0) {
            return null;
        }
        List<String> params = ObjectLiteralParser.extractParameterNames(expr.substring(parenOpen + 1, parenClose));
        TypeInfo returnType = ObjectLiteralParser.inferReturnTypeFromBody(expr, parenClose + 1, exprStartAbs, typeResolver);
        String rest = expr.substring(parenClose + 1).trim();
        int bodyOffset = expr.indexOf(rest, parenClose + 1);
        return new CallableInfo(params, returnType, exprStartAbs >= 0 ? exprStartAbs + Math.max(bodyOffset, 0) : -1, exprStartAbs >= 0 ? exprStartAbs + expr.length() : -1, rest.startsWith("{"));
    }

    private static CallableInfo parseArrowFunctionCallable(String expr, int exprStartAbs, ExpressionTypeResolverFn typeResolver) {
        ArrayList<String> params;
        int arrowIdx = ObjectLiteralParser.findArrowOperator(expr);
        if (arrowIdx < 0) {
            return null;
        }
        String paramPart = expr.substring(0, arrowIdx).trim();
        if (paramPart.startsWith("(") && paramPart.endsWith(")")) {
            params = ObjectLiteralParser.extractParameterNames(paramPart.substring(1, paramPart.length() - 1));
        } else {
            params = new ArrayList<String>();
            if (ObjectLiteralParser.isSimpleIdentifier(paramPart)) {
                params.add(paramPart);
            }
        }
        String bodyPart = expr.substring(arrowIdx + 2).trim();
        int bodyOffset = expr.indexOf(bodyPart, arrowIdx + 2);
        int bodyStartAbs = exprStartAbs >= 0 ? exprStartAbs + Math.max(bodyOffset, 0) : -1;
        TypeInfo returnType = ObjectLiteralParser.inferReturnTypeFromBody(bodyPart, 0, bodyStartAbs, typeResolver);
        return new CallableInfo(params, returnType, bodyStartAbs, bodyStartAbs >= 0 ? bodyStartAbs + bodyPart.length() : -1, bodyPart.startsWith("{"));
    }

    private static int findArrowOperator(String expr) {
        boolean inString = false;
        char stringChar = '\u0000';
        int parenDepth = 0;
        for (int i = 0; i < expr.length() - 1; ++i) {
            char c = expr.charAt(i);
            if (inString) {
                if (c == '\\') {
                    ++i;
                    continue;
                }
                if (c != stringChar) continue;
                inString = false;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringChar = c;
                continue;
            }
            if (c == '(') {
                ++parenDepth;
            } else if (c == ')') {
                parenDepth = Math.max(0, parenDepth - 1);
            }
            if (c != '=' || expr.charAt(i + 1) != '>' || parenDepth != 0) continue;
            return i;
        }
        return -1;
    }

    private static List<String> extractParameterNames(String paramList) {
        ArrayList<String> params = new ArrayList<String>();
        if (paramList == null || paramList.trim().isEmpty()) {
            return params;
        }
        for (String part : paramList.split(",")) {
            String name = part.trim();
            int spaceIdx = name.indexOf(32);
            if (spaceIdx > 0) {
                name = name.substring(0, spaceIdx);
            }
            if (!ObjectLiteralParser.isSimpleIdentifier(name)) continue;
            params.add(name);
        }
        return params;
    }

    private static int findMatchingParen(String src, int openPos) {
        int depth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = openPos; i < src.length(); ++i) {
            char c = src.charAt(i);
            if (inString) {
                if (c == '\\') {
                    ++i;
                    continue;
                }
                if (c != stringChar) continue;
                inString = false;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringChar = c;
                continue;
            }
            if (c == '(') {
                ++depth;
                continue;
            }
            if (c != ')' || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    private static TypeInfo inferReturnTypeFromBody(String expr, int afterParams, int exprStartAbs, ExpressionTypeResolverFn typeResolver) {
        int restStartAbs;
        String rest = expr.substring(afterParams).trim();
        int restOffset = expr.indexOf(rest, afterParams);
        int n = restStartAbs = exprStartAbs >= 0 ? exprStartAbs + restOffset : -1;
        if (rest.startsWith("{")) {
            return ObjectLiteralParser.inferReturnTypeFromBlockBody(rest, restStartAbs, typeResolver);
        }
        return ObjectLiteralParser.inferReturnTypeFromExpressionBody(rest, restStartAbs, typeResolver);
    }

    private static TypeInfo inferReturnTypeFromBlockBody(String body, int bodyStartAbs, ExpressionTypeResolverFn typeResolver) {
        int returnIdx;
        if (body == null || !body.startsWith("{")) {
            return TypeInfo.ANY;
        }
        int endBrace = ObjectLiteralParser.findMatchingObjectLiteralEnd(body);
        if (endBrace <= 0) {
            return TypeInfo.ANY;
        }
        String inner = body.substring(1, endBrace - 1);
        int innerStartAbs = bodyStartAbs >= 0 ? bodyStartAbs + 1 : -1;
        int i = 0;
        while (i < inner.length() && (returnIdx = inner.indexOf("return", i)) >= 0) {
            TypeInfo resolved;
            String returnExpr;
            int exprEnd;
            if (returnIdx > 0 && Character.isJavaIdentifierPart(inner.charAt(returnIdx - 1))) {
                i = returnIdx + 6;
                continue;
            }
            int afterReturn = returnIdx + 6;
            if (afterReturn >= inner.length()) break;
            char afterChar = inner.charAt(afterReturn);
            if (!Character.isWhitespace(afterChar) && afterChar != ';') {
                i = afterReturn;
                continue;
            }
            while (afterReturn < inner.length() && Character.isWhitespace(inner.charAt(afterReturn))) {
                ++afterReturn;
            }
            int depth = 0;
            for (exprEnd = afterReturn; exprEnd < inner.length(); ++exprEnd) {
                char c = inner.charAt(exprEnd);
                if (c == '{' || c == '(' || c == '[') {
                    ++depth;
                    continue;
                }
                if (c == '}' || c == ')' || c == ']') {
                    if (depth == 0) break;
                    --depth;
                    continue;
                }
                if ((c == ';' || c == '\n' || c == '\r') && depth == 0) break;
            }
            if ((returnExpr = inner.substring(afterReturn, exprEnd).trim()).isEmpty()) {
                i = exprEnd + 1;
                continue;
            }
            if ("this".equals(returnExpr)) {
                return CallableInfo.thisReturnSentinel();
            }
            if (typeResolver != null && innerStartAbs >= 0 && (resolved = typeResolver.resolve(returnExpr, innerStartAbs + afterReturn)) != null && resolved.isResolved()) {
                return resolved;
            }
            TypeInfo literal = ObjectLiteralParser.resolveLiteralType(returnExpr);
            if (literal != TypeInfo.ANY) {
                return literal;
            }
            i = exprEnd + 1;
        }
        return TypeInfo.ANY;
    }

    private static TypeInfo inferReturnTypeFromExpressionBody(String bodyExpr, int bodyStartAbs, ExpressionTypeResolverFn typeResolver) {
        TypeInfo resolved;
        if (bodyExpr == null || bodyExpr.isEmpty()) {
            return TypeInfo.ANY;
        }
        if ("this".equals(bodyExpr = bodyExpr.trim())) {
            return CallableInfo.thisReturnSentinel();
        }
        if (typeResolver != null && bodyStartAbs >= 0 && (resolved = typeResolver.resolve(bodyExpr, bodyStartAbs)) != null && resolved.isResolved()) {
            return resolved;
        }
        return ObjectLiteralParser.resolveLiteralType(bodyExpr);
    }

    private static TypeInfo resolveLiteralType(String expr) {
        if (expr.startsWith("{") && expr.endsWith("}")) {
            return TypeInfo.ANY;
        }
        if (expr.startsWith("\"") && expr.endsWith("\"")) {
            return TypeInfo.string();
        }
        if (expr.startsWith("'") && expr.endsWith("'")) {
            return TypeInfo.fromPrimitive("char");
        }
        if (expr.equals("true") || expr.equals("false")) {
            return TypeInfo.fromPrimitive("boolean");
        }
        if (expr.matches("[-+]?\\d*\\.?\\d+[fFdD]?")) {
            return TypeInfo.NUMBER;
        }
        if (expr.contains("+") || expr.contains("-") || expr.contains("*") || expr.contains("/") || expr.contains("%")) {
            return TypeInfo.NUMBER;
        }
        return TypeInfo.ANY;
    }

    private static void extendSyntheticObjectWithMethod(TypeInfo receiver, String name, CallableInfo callable) {
        ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
        for (String paramName : callable.parameterNames) {
            params.add(FieldInfo.external(paramName, TypeInfo.ANY, null, 1));
        }
        TypeInfo returnType = callable.returnsThis() ? receiver : (callable.returnType != null ? callable.returnType : TypeInfo.ANY);
        receiver.removeSyntheticField(name);
        receiver.addSyntheticField(name, TypeInfo.ANY);
        receiver.removeSyntheticMethod(name);
        receiver.addSyntheticMethod(name, returnType, params);
    }

    static void attachObjectLiteralContext(InnerCallableScope scope, ScriptDocument doc) {
        int depth;
        int searchPos;
        if (!doc.isJavaScript() || scope == null || scope.getContainingObjectType() != null) {
            return;
        }
        String text = doc.getText();
        int headerStart = scope.getHeaderStart();
        if (headerStart < 0 || headerStart >= text.length()) {
            return;
        }
        for (searchPos = headerStart - 1; searchPos >= 0 && Character.isWhitespace(text.charAt(searchPos)); --searchPos) {
        }
        if (searchPos < 0) {
            return;
        }
        int braceStart = -1;
        if (text.charAt(searchPos) == ':') {
            depth = 0;
            boolean inString = false;
            char stringChar = '\u0000';
            for (int i = --searchPos; i >= 0; --i) {
                char c = text.charAt(i);
                if (doc.isExcluded(i)) continue;
                if (inString) {
                    if (c != stringChar || i != 0 && text.charAt(i - 1) == '\\') continue;
                    inString = false;
                    continue;
                }
                if (c == '\"' || c == '\'') {
                    inString = true;
                    stringChar = c;
                    continue;
                }
                if (c == '}') {
                    ++depth;
                    continue;
                }
                if (c != '{') continue;
                if (depth == 0) {
                    braceStart = i;
                    break;
                }
                --depth;
            }
        } else if (text.charAt(searchPos) == '=') {
            --searchPos;
            while (searchPos >= 0 && Character.isWhitespace(text.charAt(searchPos))) {
                --searchPos;
            }
            depth = 0;
            boolean inString = false;
            char stringChar = '\u0000';
            for (int i = searchPos; i >= 0; --i) {
                char c = text.charAt(i);
                if (doc.isExcluded(i)) continue;
                if (inString) {
                    if (c != stringChar || i != 0 && text.charAt(i - 1) == '\\') continue;
                    inString = false;
                    continue;
                }
                if (c == '\"' || c == '\'') {
                    inString = true;
                    stringChar = c;
                    continue;
                }
                if (c == '}') {
                    ++depth;
                    continue;
                }
                if (c != '{') continue;
                if (depth == 0) {
                    braceStart = i;
                    break;
                }
                --depth;
            }
        } else if (scope.getKind() == InnerCallableScope.Kind.JS_SHORTHAND_METHOD) {
            depth = 0;
            boolean inString = false;
            char stringChar = '\u0000';
            for (int i = searchPos; i >= 0; --i) {
                char c = text.charAt(i);
                if (doc.isExcluded(i)) continue;
                if (inString) {
                    if (c != stringChar || i != 0 && text.charAt(i - 1) == '\\') continue;
                    inString = false;
                    continue;
                }
                if (c == '\"' || c == '\'') {
                    inString = true;
                    stringChar = c;
                    continue;
                }
                if (c == '}') {
                    ++depth;
                    continue;
                }
                if (c != '{') continue;
                if (depth == 0) {
                    braceStart = i;
                    break;
                }
                --depth;
            }
        }
        if (braceStart < 0) {
            return;
        }
        ObjectLiteralAnalysis analysis = doc.getObjectLiteral(braceStart);
        if (analysis != null && analysis.inferredType != null) {
            scope.setContainingObjectType(analysis.inferredType);
        }
    }

    private static TypeInfo inferValueType(String valueExpr, int valueStartAbs, ExpressionTypeResolverFn typeResolver) {
        if (valueExpr == null || valueExpr.isEmpty()) {
            return TypeInfo.ANY;
        }
        char first = valueExpr.charAt(0);
        if (first == '{') {
            ObjectLiteralAnalysis nested = ObjectLiteralParser.parse(valueExpr, valueStartAbs, true, false, typeResolver, null);
            if (nested != null && nested.supportsInference && nested.inferredType != null) {
                return nested.inferredType;
            }
            return TypeInfo.ANY;
        }
        TypeInfo inferred = typeResolver.resolve(valueExpr, valueStartAbs);
        return inferred != null ? inferred : TypeInfo.ANY;
    }

    static int scanStringLiteralEnd(String src, int start) {
        if (start < 0 || start >= src.length()) {
            return -1;
        }
        char quote = src.charAt(start);
        int i = start + 1;
        while (i < src.length()) {
            char c = src.charAt(i);
            if (c == '\\') {
                i += i + 1 < src.length() ? 2 : 1;
                continue;
            }
            if (c == quote) {
                return i + 1;
            }
            ++i;
        }
        return -1;
    }

    static boolean isSimpleIdentifier(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        if (!Character.isJavaIdentifierStart(s.charAt(0))) {
            return false;
        }
        for (int i = 1; i < s.length(); ++i) {
            if (Character.isJavaIdentifierPart(s.charAt(i))) continue;
            return false;
        }
        return true;
    }

    static int scanTopLevelValueEnd(String src, int start, int endLimit) {
        int parenDepth = 0;
        int bracketDepth = 0;
        int braceDepth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        boolean inLineComment = false;
        boolean inBlockComment = false;
        int i = start;
        while (i < endLimit) {
            char next;
            char c = src.charAt(i);
            char c2 = next = i + 1 < endLimit ? src.charAt(i + 1) : (char)'\u0000';
            if (inLineComment) {
                if (c == '\n' || c == '\r') {
                    inLineComment = false;
                }
                ++i;
                continue;
            }
            if (inBlockComment) {
                if (c == '*' && next == '/') {
                    inBlockComment = false;
                    i += 2;
                    continue;
                }
                ++i;
                continue;
            }
            if (inString) {
                if (c == '\\') {
                    i += i + 1 < endLimit ? 2 : 1;
                    continue;
                }
                if (c == stringChar) {
                    inString = false;
                }
                ++i;
                continue;
            }
            if (c == '/' && next == '/') {
                inLineComment = true;
                i += 2;
                continue;
            }
            if (c == '/' && next == '*') {
                inBlockComment = true;
                i += 2;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringChar = c;
                ++i;
                continue;
            }
            if (c == '(') {
                ++parenDepth;
            } else if (c == ')') {
                parenDepth = Math.max(0, parenDepth - 1);
            } else if (c == '[') {
                ++bracketDepth;
            } else if (c == ']') {
                bracketDepth = Math.max(0, bracketDepth - 1);
            } else if (c == '{') {
                ++braceDepth;
            } else if (c == '}') {
                braceDepth = Math.max(0, braceDepth - 1);
            }
            if (c == ',' && parenDepth == 0 && bracketDepth == 0 && braceDepth == 0) {
                return i;
            }
            ++i;
        }
        return endLimit;
    }

    private static int skipSeparatorsAndComments(String src, int start, int endLimit) {
        int i = start;
        block0: while (i < endLimit) {
            char next;
            char c = src.charAt(i);
            char c2 = next = i + 1 < endLimit ? src.charAt(i + 1) : (char)'\u0000';
            if (Character.isWhitespace(c) || c == ',') {
                ++i;
                continue;
            }
            if (c == '/' && next == '/') {
                char line;
                i += 2;
                while (i < endLimit && (line = src.charAt(i)) != '\n' && line != '\r') {
                    ++i;
                }
                continue;
            }
            if (c != '/' || next != '*') break;
            i += 2;
            while (i + 1 < endLimit) {
                if (src.charAt(i) == '*' && src.charAt(i + 1) == '/') {
                    i += 2;
                    continue block0;
                }
                ++i;
            }
        }
        return i;
    }

    static boolean isInsideObjectLiteral(int position, ScriptDocument doc) {
        char c;
        int i;
        int braceDepth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        String text = doc.getText();
        for (int i2 = 0; i2 < position && i2 < text.length(); ++i2) {
            if (doc.isExcluded(i2)) continue;
            char c2 = text.charAt(i2);
            if (inString) {
                if (c2 == '\\') {
                    ++i2;
                    continue;
                }
                if (c2 != stringChar) continue;
                inString = false;
                continue;
            }
            if (c2 == '\"' || c2 == '\'') {
                inString = true;
                stringChar = c2;
                continue;
            }
            if (c2 == '{') {
                ++braceDepth;
                continue;
            }
            if (c2 != '}') continue;
            braceDepth = Math.max(0, braceDepth - 1);
        }
        if (braceDepth <= 0) {
            return false;
        }
        int braceStart = -1;
        int depth = 0;
        inString = false;
        stringChar = '\u0000';
        for (i = position - 1; i >= 0; --i) {
            c = text.charAt(i);
            if (doc.isExcluded(i)) continue;
            if (inString) {
                if (c != stringChar || i != 0 && text.charAt(i - 1) == '\\') continue;
                inString = false;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringChar = c;
                continue;
            }
            if (c == '}') {
                ++depth;
                continue;
            }
            if (c != '{') continue;
            if (depth == 0) {
                braceStart = i;
                break;
            }
            --depth;
        }
        if (braceStart < 0) {
            return false;
        }
        for (i = braceStart - 1; i >= 0; --i) {
            c = text.charAt(i);
            if (doc.isExcluded(i) || Character.isWhitespace(c)) continue;
            return c == '=' || c == ':' || c == '(' || c == '[' || c == ',' || c == '{';
        }
        return true;
    }

    static int scanToNextTopLevelComma(String src, int start, int endLimit) {
        int i = start;
        int depth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        while (i < endLimit) {
            char c = src.charAt(i);
            if (inString) {
                if (c == '\\') {
                    i += i + 1 < endLimit ? 2 : 1;
                    continue;
                }
                if (c == stringChar) {
                    inString = false;
                }
                ++i;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringChar = c;
                ++i;
                continue;
            }
            if (c == '{' || c == '[' || c == '(') {
                ++depth;
            } else if (c == '}' || c == ']' || c == ')') {
                depth = Math.max(0, depth - 1);
            }
            if (c == ',' && depth == 0) {
                return i + 1;
            }
            ++i;
        }
        return endLimit;
    }

    static int findMatchingObjectLiteralEnd(String src) {
        int depth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        boolean inLineComment = false;
        boolean inBlockComment = false;
        for (int i = 0; i < src.length(); ++i) {
            char next;
            char c = src.charAt(i);
            char c2 = next = i + 1 < src.length() ? src.charAt(i + 1) : (char)'\u0000';
            if (inLineComment) {
                if (c != '\n' && c != '\r') continue;
                inLineComment = false;
                continue;
            }
            if (inBlockComment) {
                if (c != '*' || next != '/') continue;
                inBlockComment = false;
                ++i;
                continue;
            }
            if (inString) {
                if (c == '\\') {
                    i += i + 1 < src.length() ? 1 : 0;
                    continue;
                }
                if (c != stringChar) continue;
                inString = false;
                continue;
            }
            if (c == '/' && next == '/') {
                inLineComment = true;
                ++i;
                continue;
            }
            if (c == '/' && next == '*') {
                inBlockComment = true;
                ++i;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringChar = c;
                continue;
            }
            if (c == '{') {
                ++depth;
                continue;
            }
            if (c != '}' || --depth != 0) continue;
            return i + 1;
        }
        return -1;
    }

    public static final class CallableInfo {
        private static final TypeInfo THIS_RETURN_SENTINEL = TypeInfo.unresolved("<object_literal_this>", "<object_literal_this>");
        public final List<String> parameterNames;
        public final TypeInfo returnType;
        public final int bodyStartAbs;
        public final int bodyEndAbs;
        public final boolean blockBody;

        CallableInfo(List<String> parameterNames, TypeInfo returnType, int bodyStartAbs, int bodyEndAbs, boolean blockBody) {
            this.parameterNames = parameterNames != null ? parameterNames : new ArrayList();
            this.returnType = returnType;
            this.bodyStartAbs = bodyStartAbs;
            this.bodyEndAbs = bodyEndAbs;
            this.blockBody = blockBody;
        }

        public boolean returnsThis() {
            return this.returnType == THIS_RETURN_SENTINEL;
        }

        static TypeInfo thisReturnSentinel() {
            return THIS_RETURN_SENTINEL;
        }
    }

    public static final class DynamicFieldResult {
        public final TypeInfo receiverType;
        public final String propertyName;
        public final FieldInfo field;

        DynamicFieldResult(TypeInfo receiverType, String propertyName, FieldInfo field) {
            this.receiverType = receiverType;
            this.propertyName = propertyName;
            this.field = field;
        }
    }

    public static final class DynamicPropertyAccess {
        public final String receiverExpr;
        public final String propertyName;
        public final int receiverStart;
        public final boolean bracketAccess;

        DynamicPropertyAccess(String receiverExpr, String propertyName, int receiverStart, boolean bracketAccess) {
            this.receiverExpr = receiverExpr;
            this.propertyName = propertyName;
            this.receiverStart = receiverStart;
            this.bracketAccess = bracketAccess;
        }
    }

    public static final class ObjectLiteralProperty {
        public final String keyName;
        public final boolean isIdentifierKey;
        public final int keyStartAbs;
        public final int keyEndAbs;
        public final int valueStartAbs;
        public final int valueEndAbs;
        public final TypeInfo valueType;
        public final CallableInfo callableInfo;

        ObjectLiteralProperty(String keyName, boolean isIdentifierKey, int keyStartAbs, int keyEndAbs, int valueStartAbs, int valueEndAbs, TypeInfo valueType, CallableInfo callableInfo) {
            this.keyName = keyName;
            this.isIdentifierKey = isIdentifierKey;
            this.keyStartAbs = keyStartAbs;
            this.keyEndAbs = keyEndAbs;
            this.valueStartAbs = valueStartAbs;
            this.valueEndAbs = valueEndAbs;
            this.valueType = valueType;
            this.callableInfo = callableInfo;
        }

        public boolean isCallable() {
            return this.callableInfo != null;
        }
    }

    public static final class ObjectLiteralAnalysis {
        public final List<ObjectLiteralProperty> properties;
        public final boolean supportsInference;
        public final TypeInfo inferredType;

        ObjectLiteralAnalysis(List<ObjectLiteralProperty> properties, boolean supportsInference, TypeInfo inferredType) {
            this.properties = properties;
            this.supportsInference = supportsInference;
            this.inferredType = inferredType;
        }
    }

    @FunctionalInterface
    public static interface VariableResolverFn {
        public FieldInfo resolve(String var1, int var2);
    }

    @FunctionalInterface
    public static interface MethodReferenceResolverFn {
        public MethodInfo resolve(String var1, TypeInfo[] var2);
    }

    @FunctionalInterface
    public static interface ExpressionTypeResolverFn {
        public TypeInfo resolve(String var1, int var2);
    }
}

