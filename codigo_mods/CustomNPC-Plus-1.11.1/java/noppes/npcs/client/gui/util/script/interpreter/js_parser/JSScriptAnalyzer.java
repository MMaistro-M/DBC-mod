/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.js_parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSFieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

@Deprecated
public class JSScriptAnalyzer {
    private final ScriptDocument document;
    private final String text;
    private final JSTypeRegistry registry;
    private final Map<String, String> variableTypes = new HashMap<String, String>();
    private final Map<String, Map<String, String>> functionParams = new HashMap<String, Map<String, String>>();
    private final List<int[]> excludedRanges = new ArrayList<int[]>();
    private static final Pattern STRING_PATTERN = Pattern.compile("\"(?:[^\"\\\\]|\\\\.)*\"|'(?:[^'\\\\]|\\\\.)*'");
    private static final Pattern COMMENT_PATTERN = Pattern.compile("//.*?$|/\\*.*?\\*/", 40);
    private static final Pattern FUNCTION_PATTERN = Pattern.compile("function\\s+(\\w+)\\s*\\(([^)]*)\\)");
    private static final Pattern VAR_DECL_PATTERN = Pattern.compile("(?:var|let|const)\\s+(\\w+)(?:\\s*=\\s*([^;]+))?");
    private static final Pattern ASSIGNMENT_PATTERN = Pattern.compile("(\\w+)\\s*=\\s*([^;]+)");
    private static final Pattern METHOD_CALL_PATTERN = Pattern.compile("(\\w+(?:\\.\\w+)*)\\s*\\(");
    private static final Pattern MEMBER_ACCESS_PATTERN = Pattern.compile("(\\w+)(?:\\.(\\w+))+");
    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*)\\b");
    private static final Pattern JS_KEYWORD_PATTERN = Pattern.compile("\\b(function|var|let|const|if|else|for|while|do|switch|case|break|continue|return|try|catch|finally|throw|new|typeof|instanceof|in|of|this|null|undefined|true|false|class|extends|import|export|default|async|await|yield)\\b");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\b\\d+\\.?\\d*\\b");

    public JSScriptAnalyzer(ScriptDocument document) {
        this.document = document;
        this.text = document.getText();
        this.registry = JSTypeRegistry.getInstance();
        if (!this.registry.isInitialized()) {
            this.registry.initializeFromResources();
        }
    }

    public List<ScriptLine.Mark> analyze() {
        ArrayList<ScriptLine.Mark> marks = new ArrayList<ScriptLine.Mark>();
        this.findExcludedRanges();
        this.addPatternMarks(marks, COMMENT_PATTERN, TokenType.COMMENT);
        this.addPatternMarks(marks, STRING_PATTERN, TokenType.STRING);
        this.addPatternMarks(marks, JS_KEYWORD_PATTERN, TokenType.KEYWORD);
        this.addPatternMarks(marks, NUMBER_PATTERN, TokenType.LITERAL);
        this.parseFunctions(marks);
        this.parseVariables(marks);
        this.markMemberAccesses(marks);
        this.markMethodCalls(marks);
        this.markIdentifiers(marks);
        return marks;
    }

    private void findExcludedRanges() {
        Matcher m = STRING_PATTERN.matcher(this.text);
        while (m.find()) {
            this.excludedRanges.add(new int[]{m.start(), m.end()});
        }
        m = COMMENT_PATTERN.matcher(this.text);
        while (m.find()) {
            this.excludedRanges.add(new int[]{m.start(), m.end()});
        }
    }

    private boolean isExcluded(int pos) {
        for (int[] range : this.excludedRanges) {
            if (pos < range[0] || pos >= range[1]) continue;
            return true;
        }
        return false;
    }

    private void parseFunctions(List<ScriptLine.Mark> marks) {
        Matcher m = FUNCTION_PATTERN.matcher(this.text);
        while (m.find()) {
            if (this.isExcluded(m.start())) continue;
            String funcName = m.group(1);
            String params = m.group(2);
            int nameStart = m.start(1);
            int nameEnd = m.end(1);
            if (this.registry.isHook(funcName)) {
                List<JSTypeRegistry.HookSignature> sigs = this.registry.getHookSignatures(funcName);
                if (!sigs.isEmpty()) {
                    String[] paramNames;
                    JSTypeRegistry.HookSignature sig = sigs.get(0);
                    TypeInfo paramTypeInfo = this.resolveJSType(sig.paramType);
                    ArrayList<FieldInfo> methodParams = new ArrayList<FieldInfo>();
                    methodParams.add(FieldInfo.reflectionParam(sig.paramName, paramTypeInfo));
                    MethodInfo hookMethod = MethodInfo.declaration(funcName, null, TypeInfo.fromPrimitive("void"), methodParams, nameStart, nameStart, nameStart, -1, -1, 0, sig.doc);
                    marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.METHOD_DECL, hookMethod));
                    if (params.isEmpty() || (paramNames = params.split(",")).length <= 0) continue;
                    String paramName = paramNames[0].trim();
                    String paramType = sig.paramType;
                    HashMap<String, String> funcParamMap = new HashMap<String, String>();
                    funcParamMap.put(paramName, paramType);
                    this.functionParams.put(funcName, funcParamMap);
                    this.variableTypes.put(paramName, paramType);
                    int paramStart = m.start(2) + params.indexOf(paramName);
                    int paramEnd = paramStart + paramName.length();
                    FieldInfo paramFieldInfo = FieldInfo.parameter(paramName, paramTypeInfo, paramStart, hookMethod);
                    marks.add(new ScriptLine.Mark(paramStart, paramEnd, TokenType.PARAMETER, paramFieldInfo));
                    continue;
                }
                marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.METHOD_DECL, funcName));
                continue;
            }
            marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.METHOD_DECL, funcName));
        }
    }

    private void parseVariables(List<ScriptLine.Mark> marks) {
        Matcher m = VAR_DECL_PATTERN.matcher(this.text);
        while (m.find()) {
            if (this.isExcluded(m.start())) continue;
            String varName = m.group(1);
            String initializer = m.group(2);
            String inferredType = null;
            if (initializer != null) {
                inferredType = this.inferTypeFromExpression(initializer.trim());
            }
            if (inferredType != null) {
                this.variableTypes.put(varName, inferredType);
            }
            int varStart = m.start(1);
            int varEnd = m.end(1);
            TypeInfo typeInfo = this.resolveJSType(inferredType != null ? inferredType : "any");
            FieldInfo varFieldInfo = FieldInfo.localField(varName, typeInfo, varStart, null);
            marks.add(new ScriptLine.Mark(varStart, varEnd, TokenType.LOCAL_FIELD, varFieldInfo));
        }
    }

    private String inferTypeFromExpression(String expr) {
        if (expr == null || expr.isEmpty()) {
            return null;
        }
        if (expr.startsWith("\"") || expr.startsWith("'")) {
            return "string";
        }
        if (expr.matches("\\d+\\.?\\d*")) {
            return "number";
        }
        if (expr.equals("true") || expr.equals("false")) {
            return "boolean";
        }
        if (expr.equals("null")) {
            return "null";
        }
        if (expr.equals("undefined")) {
            return "undefined";
        }
        if (expr.startsWith("[")) {
            return "any[]";
        }
        if (expr.startsWith("{")) {
            return "object";
        }
        if (expr.contains(".") && expr.contains("(")) {
            return this.inferTypeFromMethodCall(expr);
        }
        if (this.variableTypes.containsKey(expr)) {
            return this.variableTypes.get(expr);
        }
        return null;
    }

    private String inferTypeFromMethodCall(String expr) {
        String[] parts;
        int parenIndex = expr.indexOf(40);
        if (parenIndex > 0) {
            expr = expr.substring(0, parenIndex);
        }
        if ((parts = expr.split("\\.")).length < 2) {
            return null;
        }
        String currentType = this.variableTypes.get(parts[0]);
        if (currentType == null) {
            return null;
        }
        for (int i = 1; i < parts.length; ++i) {
            JSTypeInfo typeInfo = this.registry.getType(currentType);
            if (typeInfo == null) {
                return null;
            }
            String member = parts[i];
            JSMethodInfo method = typeInfo.getMethod(member);
            if (method != null) {
                currentType = method.getReturnType();
                continue;
            }
            JSFieldInfo f = typeInfo.getField(member);
            if (f != null) {
                currentType = f.getType();
                continue;
            }
            return null;
        }
        return currentType;
    }

    private void markMemberAccesses(List<ScriptLine.Mark> marks) {
        Matcher m = MEMBER_ACCESS_PATTERN.matcher(this.text);
        while (m.find()) {
            String fullAccess;
            String[] parts;
            if (this.isExcluded(m.start()) || (parts = (fullAccess = m.group(0)).split("\\.")).length < 2) continue;
            String receiverName = parts[0];
            String currentType = this.variableTypes.get(receiverName);
            int pos = m.start();
            if (currentType != null) {
                TypeInfo unifiedType = this.resolveJSType(currentType);
                FieldInfo receiverField = FieldInfo.localField(receiverName, unifiedType, pos, null);
                marks.add(new ScriptLine.Mark(pos, pos + receiverName.length(), TokenType.LOCAL_FIELD, receiverField));
            }
            pos += receiverName.length() + 1;
            for (int i = 1; i < parts.length; ++i) {
                String member = parts[i];
                int memberStart = pos;
                int memberEnd = pos + member.length();
                if (currentType != null) {
                    JSTypeInfo jsTypeInfo = this.registry.getType(currentType);
                    if (jsTypeInfo != null) {
                        TypeInfo unifiedContainingType = TypeInfo.fromJSTypeInfo(jsTypeInfo);
                        JSMethodInfo jsMethod = jsTypeInfo.getMethod(member);
                        if (jsMethod != null) {
                            MethodInfo unifiedMethod = MethodInfo.fromJSMethod(jsMethod, unifiedContainingType);
                            marks.add(new ScriptLine.Mark(memberStart, memberEnd, TokenType.METHOD_CALL, unifiedMethod));
                            currentType = jsMethod.getReturnType();
                        } else {
                            JSFieldInfo jsField = jsTypeInfo.getField(member);
                            if (jsField != null) {
                                FieldInfo unifiedField = FieldInfo.fromJSField(jsField, unifiedContainingType);
                                marks.add(new ScriptLine.Mark(memberStart, memberEnd, TokenType.GLOBAL_FIELD, unifiedField));
                                currentType = jsField.getType();
                            } else {
                                marks.add(new ScriptLine.Mark(memberStart, memberEnd, TokenType.UNDEFINED_VAR, "Unknown member '" + member + "' on type " + jsTypeInfo.getFullName()));
                                currentType = null;
                            }
                        }
                    } else {
                        currentType = null;
                    }
                }
                pos = memberEnd + 1;
            }
        }
    }

    private void markMethodCalls(List<ScriptLine.Mark> marks) {
        Matcher m = METHOD_CALL_PATTERN.matcher(this.text);
        while (m.find()) {
            List<JSTypeRegistry.HookSignature> sigs;
            String callExpr;
            if (this.isExcluded(m.start()) || (callExpr = m.group(1)).contains(".")) continue;
            int nameStart = m.start(1);
            int nameEnd = m.end(1);
            if (!this.registry.isHook(callExpr) || (sigs = this.registry.getHookSignatures(callExpr)).isEmpty()) continue;
            JSTypeRegistry.HookSignature sig = sigs.get(0);
            TypeInfo paramTypeInfo = this.resolveJSType(sig.paramType);
            ArrayList<FieldInfo> methodParams = new ArrayList<FieldInfo>();
            methodParams.add(FieldInfo.reflectionParam(sig.paramName, paramTypeInfo));
            MethodInfo hookMethod = MethodInfo.declaration(callExpr, null, TypeInfo.fromPrimitive("void"), methodParams, nameStart, nameStart, nameStart, -1, -1, 0, sig.doc);
            marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.METHOD_CALL, hookMethod));
        }
    }

    private void addPatternMarks(List<ScriptLine.Mark> marks, Pattern pattern, TokenType type) {
        Matcher m = pattern.matcher(this.text);
        while (m.find()) {
            marks.add(new ScriptLine.Mark(m.start(), m.end(), type));
        }
    }

    public String getVariableType(String varName) {
        return this.variableTypes.get(varName);
    }

    public Map<String, String> getVariableTypes() {
        return new HashMap<String, String>(this.variableTypes);
    }

    private TypeInfo resolveJSType(String jsTypeName) {
        JSTypeInfo jsTypeInfo;
        if (jsTypeName == null || jsTypeName.isEmpty() || "void".equals(jsTypeName)) {
            return TypeInfo.fromPrimitive("void");
        }
        switch (jsTypeName) {
            case "string": {
                return TypeInfo.fromClass(String.class);
            }
            case "number": {
                return TypeInfo.fromClass(Double.TYPE);
            }
            case "boolean": {
                return TypeInfo.fromClass(Boolean.TYPE);
            }
            case "any": {
                return TypeInfo.fromClass(Object.class);
            }
            case "void": {
                return TypeInfo.fromPrimitive("void");
            }
        }
        if (jsTypeName.endsWith("[]")) {
            String elementType = jsTypeName.substring(0, jsTypeName.length() - 2);
            TypeInfo elementTypeInfo = this.resolveJSType(elementType);
            return TypeInfo.arrayOf(elementTypeInfo);
        }
        if (this.registry != null && (jsTypeInfo = this.registry.getType(jsTypeName)) != null) {
            return TypeInfo.fromJSTypeInfo(jsTypeInfo);
        }
        return TypeInfo.unresolved(jsTypeName, jsTypeName);
    }

    private void markIdentifiers(List<ScriptLine.Mark> marks) {
        HashSet<Integer> markedPositions = new HashSet<Integer>();
        for (ScriptLine.Mark mark : marks) {
            for (int i = mark.start; i < mark.end; ++i) {
                markedPositions.add(i);
            }
        }
        Matcher matcher = IDENTIFIER_PATTERN.matcher(this.text);
        while (matcher.find()) {
            if (this.isExcluded(matcher.start())) continue;
            int start = matcher.start();
            int end = matcher.end();
            boolean alreadyMarked = false;
            for (int i = start; i < end; ++i) {
                if (!markedPositions.contains(i)) continue;
                alreadyMarked = true;
                break;
            }
            if (alreadyMarked) continue;
            String identifier = matcher.group();
            boolean isParameter = false;
            for (Map<String, String> params : this.functionParams.values()) {
                if (!params.containsKey(identifier)) continue;
                isParameter = true;
                break;
            }
            if (isParameter) {
                marks.add(new ScriptLine.Mark(start, end, TokenType.PARAMETER));
                for (int i = start; i < end; ++i) {
                    markedPositions.add(i);
                }
                continue;
            }
            if (!this.variableTypes.containsKey(identifier)) continue;
            marks.add(new ScriptLine.Mark(start, end, TokenType.LOCAL_FIELD));
            for (int i = start; i < end; ++i) {
                markedPositions.add(i);
            }
        }
    }
}

