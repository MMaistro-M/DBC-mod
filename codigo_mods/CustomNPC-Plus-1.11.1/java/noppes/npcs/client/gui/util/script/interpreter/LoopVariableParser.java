/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import noppes.npcs.client.gui.util.script.ScopeInfo;
import noppes.npcs.client.gui.util.script.interpreter.InnerCallableScope;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

class LoopVariableParser {
    private static final Pattern ENHANCED_FOR = Pattern.compile("\\bfor\\s*\\(\\s*([A-Za-z_][\\w.<>,\\[\\] \\t\\n\\r]*?(?:\\[\\s*\\])*)\\s+(\\w+)\\s*:\\s*([^)]+)\\)");
    private static final Pattern CLASSIC_FOR = Pattern.compile("\\bfor\\s*\\(\\s*([A-Za-z_][\\w.<>,\\[\\] \\t\\n\\r]*?(?:\\[\\s*\\])*)\\s+(\\w+)\\s*=");
    private static final Pattern FOR_IN = Pattern.compile("\\bfor\\s*\\(\\s*(?:var|let|const)\\s+(\\w+)\\s+in\\s+([^)]+)\\)");
    private static final Pattern FOR_OF = Pattern.compile("\\bfor\\s*\\(\\s*(?:var|let|const)\\s+(\\w+)\\s+of\\s+([^)]+)\\)");
    static final Pattern FOR_IN_OF_LOOKAHEAD = Pattern.compile("^\\s+(?:in|of)\\s");
    private static final Set<String> SKIP_TYPES = new HashSet<String>(Arrays.asList("return", "if", "while", "for", "switch", "catch", "new", "throw"));
    private final ScriptDocument doc;
    private final String text;

    LoopVariableParser(ScriptDocument doc, String text) {
        this.doc = doc;
        this.text = text;
    }

    boolean isForInOrOf(String textAfterVarName) {
        return FOR_IN_OF_LOOKAHEAD.matcher(textAfterVarName).find();
    }

    boolean isInsideForHeader(int bodyStart, int absPos) {
        int enclosingParen = this.doc.findEnclosingParenStart(bodyStart, absPos);
        return enclosingParen >= 0 && "for".equals(this.doc.readKeywordBefore(enclosingParen));
    }

    void parse(Map<Integer, Map<String, List<FieldInfo>>> methodLocals) {
        String bodyText;
        int bodyEnd;
        int bodyStart;
        boolean js = this.doc.isJavaScript();
        for (MethodInfo method : this.doc.getAllMethods()) {
            bodyStart = method.getBodyStart();
            bodyEnd = method.getBodyEnd();
            if (bodyStart < 0 || bodyEnd <= bodyStart) continue;
            bodyText = this.text.substring(bodyStart, Math.min(bodyEnd, this.text.length()));
            Map locals = methodLocals.computeIfAbsent(method.getDeclarationOffset(), k -> new HashMap());
            this.scanRange(bodyText, bodyStart, bodyEnd, method, locals, js);
        }
        for (InnerCallableScope scope : this.doc.getInnerScopes()) {
            bodyStart = scope.getBodyStart();
            bodyEnd = scope.getBodyEnd();
            if (bodyStart < 0 || bodyEnd <= bodyStart) continue;
            bodyText = this.text.substring(bodyStart, Math.min(bodyEnd, this.text.length()));
            MethodInfo ownerMethod = this.doc.findMethodAtPosition(bodyStart);
            if (ownerMethod == null) continue;
            Map locals = methodLocals.computeIfAbsent(ownerMethod.getDeclarationOffset(), k -> new HashMap());
            this.scanRange(bodyText, bodyStart, bodyEnd, ownerMethod, locals, js);
        }
        this.scanTopLevelLoops();
    }

    private void scanTopLevelLoops() {
        boolean js = this.doc.isJavaScript();
        if (!js) {
            FieldInfo fi;
            ScopeInfo scope;
            TypeInfo typeInfo;
            String varName;
            String typeName;
            int absForPos;
            Matcher m = ENHANCED_FOR.matcher(this.text);
            while (m.find()) {
                absForPos = m.start();
                if (this.doc.isExcluded(absForPos) || this.isInsideAnyScope(absForPos)) continue;
                typeName = m.group(1).trim();
                varName = m.group(2);
                typeInfo = this.doc.resolveType(typeName);
                scope = this.computeForBodyScope(absForPos, 0, this.text.length());
                fi = FieldInfo.localField(varName, typeInfo, m.start(2), null, -1, -1, 0);
                fi.setScopeInfo(scope);
                this.doc.addTopLevelLocal(fi);
            }
            m = CLASSIC_FOR.matcher(this.text);
            while (m.find()) {
                absForPos = m.start();
                if (this.doc.isExcluded(absForPos) || this.isInsideAnyScope(absForPos)) continue;
                typeName = m.group(1).trim();
                varName = m.group(2);
                if (SKIP_TYPES.contains(typeName)) continue;
                typeInfo = this.doc.resolveType(typeName);
                scope = this.computeForBodyScope(absForPos, 0, this.text.length());
                fi = FieldInfo.localField(varName, typeInfo, m.start(2), null, -1, -1, 0);
                fi.setScopeInfo(scope);
                this.doc.addTopLevelLocal(fi);
            }
        } else {
            String varName;
            int absForPos;
            Matcher m = FOR_IN.matcher(this.text);
            while (m.find()) {
                absForPos = m.start();
                if (this.doc.isExcluded(absForPos) || this.isInsideAnyScope(absForPos)) continue;
                varName = m.group(1);
                ScopeInfo scope = this.computeForBodyScope(absForPos, 0, this.text.length());
                FieldInfo fi = FieldInfo.localField(varName, TypeInfo.fromPrimitive("int"), m.start(1), null, -1, -1, 0);
                fi.setScopeInfo(scope);
                this.doc.addTopLevelLocal(fi);
            }
            m = FOR_OF.matcher(this.text);
            while (m.find()) {
                TypeInfo elementType;
                absForPos = m.start();
                if (this.doc.isExcluded(absForPos) || this.isInsideAnyScope(absForPos)) continue;
                varName = m.group(1);
                String iterableExpr = m.group(2).trim();
                TypeInfo iterableType = this.doc.resolveExpressionType(iterableExpr, absForPos);
                TypeInfo typeInfo = elementType = iterableType != null ? iterableType.getElementType() : null;
                if (elementType == null) {
                    elementType = TypeInfo.ANY;
                }
                ScopeInfo scope = this.computeForBodyScope(absForPos, 0, this.text.length());
                FieldInfo fi = FieldInfo.localField(varName, elementType, m.start(1), null, -1, -1, 0);
                fi.setScopeInfo(scope);
                this.doc.addTopLevelLocal(fi);
            }
        }
    }

    private boolean isInsideAnyScope(int pos) {
        for (MethodInfo method : this.doc.getAllMethods()) {
            if (!method.containsPosition(pos)) continue;
            return true;
        }
        for (InnerCallableScope scope : this.doc.getInnerScopes()) {
            if (!scope.containsPosition(pos)) continue;
            return true;
        }
        return false;
    }

    private void scanRange(String bodyText, int bodyStart, int bodyEnd, MethodInfo method, Map<String, List<FieldInfo>> locals, boolean js) {
        if (!js) {
            FieldInfo fi;
            ScopeInfo scope;
            TypeInfo typeInfo;
            int absVarPos;
            String varName;
            String typeName;
            int absForPos;
            Matcher m = ENHANCED_FOR.matcher(bodyText);
            while (m.find()) {
                absForPos = bodyStart + m.start();
                if (this.doc.isExcluded(absForPos)) continue;
                typeName = m.group(1).trim();
                varName = m.group(2);
                absVarPos = bodyStart + m.start(2);
                typeInfo = this.doc.resolveType(typeName);
                scope = this.computeForBodyScope(absForPos, bodyStart, bodyEnd);
                fi = FieldInfo.localField(varName, typeInfo, absVarPos, method, -1, -1, 0);
                fi.setScopeInfo(scope);
                locals.computeIfAbsent(varName, k -> new ArrayList()).add(fi);
            }
            m = CLASSIC_FOR.matcher(bodyText);
            while (m.find()) {
                absForPos = bodyStart + m.start();
                if (this.doc.isExcluded(absForPos)) continue;
                typeName = m.group(1).trim();
                varName = m.group(2);
                if (SKIP_TYPES.contains(typeName)) continue;
                absVarPos = bodyStart + m.start(2);
                typeInfo = this.doc.resolveType(typeName);
                scope = this.computeForBodyScope(absForPos, bodyStart, bodyEnd);
                fi = FieldInfo.localField(varName, typeInfo, absVarPos, method, -1, -1, 0);
                fi.setScopeInfo(scope);
                locals.computeIfAbsent(varName, k -> new ArrayList()).add(fi);
            }
        } else {
            String varName;
            int absForPos;
            Matcher m = FOR_IN.matcher(bodyText);
            while (m.find()) {
                absForPos = bodyStart + m.start();
                if (this.doc.isExcluded(absForPos)) continue;
                varName = m.group(1);
                int absVarPos = bodyStart + m.start(1);
                ScopeInfo scope = this.computeForBodyScope(absForPos, bodyStart, bodyEnd);
                FieldInfo fi = FieldInfo.localField(varName, TypeInfo.fromPrimitive("int"), absVarPos, method, -1, -1, 0);
                fi.setScopeInfo(scope);
                locals.computeIfAbsent(varName, k -> new ArrayList()).add(fi);
            }
            m = FOR_OF.matcher(bodyText);
            while (m.find()) {
                TypeInfo elementType;
                absForPos = bodyStart + m.start();
                if (this.doc.isExcluded(absForPos)) continue;
                varName = m.group(1);
                String iterableExpr = m.group(2).trim();
                int absVarPos = bodyStart + m.start(1);
                TypeInfo iterableType = this.doc.resolveExpressionType(iterableExpr, absForPos);
                TypeInfo typeInfo = elementType = iterableType != null ? iterableType.getElementType() : null;
                if (elementType == null) {
                    elementType = TypeInfo.ANY;
                }
                ScopeInfo scope = this.computeForBodyScope(absForPos, bodyStart, bodyEnd);
                FieldInfo fi = FieldInfo.localField(varName, elementType, absVarPos, method, -1, -1, 0);
                fi.setScopeInfo(scope);
                locals.computeIfAbsent(varName, k -> new ArrayList()).add(fi);
            }
        }
    }

    private ScopeInfo computeForBodyScope(int forKeywordPos, int bodyStart, int bodyEnd) {
        int max = Math.min(bodyEnd, this.text.length());
        int openParen = -1;
        for (int i = forKeywordPos; i < max; ++i) {
            if (this.doc.isExcluded(i)) continue;
            char c = this.text.charAt(i);
            if (c == '(') {
                openParen = i;
                break;
            }
            if (!Character.isWhitespace(c) && c != 'f' && c != 'o' && c != 'r') break;
        }
        if (openParen < 0) {
            return new ScopeInfo(forKeywordPos, bodyEnd, false, "block");
        }
        int closeParen = this.doc.findMatchingParen(openParen, max);
        if (closeParen < 0) {
            return new ScopeInfo(forKeywordPos, bodyEnd, false, "block");
        }
        int after = this.doc.skipWhitespaceAndExcluded(closeParen + 1, max);
        if (after < 0 || after >= max) {
            return new ScopeInfo(forKeywordPos, bodyEnd, false, "block");
        }
        if (this.text.charAt(after) == '{') {
            int closeBrace = this.doc.findMatchingBrace(after);
            if (closeBrace > 0) {
                return new ScopeInfo(forKeywordPos, closeBrace + 1, false, "block");
            }
            return new ScopeInfo(forKeywordPos, bodyEnd, false, "block");
        }
        int stmtEnd = this.doc.findStatementEnd(after, max);
        if (stmtEnd > after) {
            return new ScopeInfo(forKeywordPos, stmtEnd, false, "block");
        }
        return new ScopeInfo(forKeywordPos, bodyEnd, false, "block");
    }
}

