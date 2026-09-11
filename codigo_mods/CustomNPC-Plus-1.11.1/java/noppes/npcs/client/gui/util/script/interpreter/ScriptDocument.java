/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.util.script.ScopeInfo;
import noppes.npcs.client.gui.util.script.interpreter.DocumentError;
import noppes.npcs.client.gui.util.script.interpreter.FieldChainMarker;
import noppes.npcs.client.gui.util.script.interpreter.InnerCallableScope;
import noppes.npcs.client.gui.util.script.interpreter.LoopVariableParser;
import noppes.npcs.client.gui.util.script.interpreter.MultiDeclaratorParser;
import noppes.npcs.client.gui.util.script.interpreter.ObjectLiteralParser;
import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import noppes.npcs.client.gui.util.script.interpreter.expression.CastExpressionResolver;
import noppes.npcs.client.gui.util.script.interpreter.expression.ExpressionNode;
import noppes.npcs.client.gui.util.script.interpreter.expression.ExpressionTypeResolver;
import noppes.npcs.client.gui.util.script.interpreter.field.AssignmentInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.EnumConstantInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldAccessInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSMethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSScriptAnalyzer;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.TypeParamInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocParamTag;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocParser;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodCallInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodSignature;
import noppes.npcs.client.gui.util.script.interpreter.token.Token;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenErrorMessage;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.ClassTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericContext;
import noppes.npcs.client.gui.util.script.interpreter.type.GenericTypeParser;
import noppes.npcs.client.gui.util.script.interpreter.type.ImportData;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeChecker;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeResolver;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeStringNormalizer;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticField;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticMethod;
import noppes.npcs.client.gui.util.script.interpreter.type.synthetic.SyntheticType;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.ScriptHookController;

public class ScriptDocument {
    public static ScriptDocument INSTANCE = null;
    private static final Pattern WORD_PATTERN = Pattern.compile("[\\p{L}\\p{N}_-]+|\\n|$");
    private static final Pattern STRING_PATTERN = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1");
    private static final Pattern COMMENT_PATTERN = Pattern.compile("/\\*[\\s\\S]*?(?:\\*/|$)|//.*|#.*");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\b-?(?:0[xX][\\dA-Fa-f]+|0[bB][01]+|0[oO][0-7]+|\\d*\\.?\\d+(?:[Ee][+-]?\\d+)?(?:[fFbBdDlLsS])?|NaN|null|Infinity|true|false)\\b");
    private static final Pattern MODIFIER_PATTERN = Pattern.compile("\\b(public|protected|private|static|final|abstract|synchronized|native|default)\\b");
    private static final Pattern KEYWORD_PATTERN = Pattern.compile("\\b(null|boolean|int|float|double|long|char|byte|short|void|if|else|switch|case|for|while|do|try|catch|finally|return|throw|var|let|const|function|continue|break|this|super|new|typeof|instanceof|import)\\b");
    private static final Pattern KEYWORD_JS_PATTERN = Pattern.compile("\\b(function|var|let|const|if|else|for|while|do|switch|case|break|continue|return|try|catch|finally|throw|delete|new|typeof|instanceof|in|of|this|null|undefined|true|false|class|extends|import|export|default|async|await|yield)\\b");
    private static final Pattern IMPORT_PATTERN = Pattern.compile("(?m)\\bimport\\s+(?:static\\s+)?([A-Za-z_][A-Za-z0-9_]*(?:\\s*\\.\\s*[A-Za-z_][A-Za-z0-9_]*)*)(?:\\s*\\.\\s*\\*?)?\\s*(?:;|$)");
    private static final Pattern CLASS_DECL_PATTERN = Pattern.compile("\\b(class|interface|enum)\\s+([A-Za-z_][a-zA-Z0-9_]*)");
    private static final Pattern METHOD_DECL_PATTERN = Pattern.compile("\\b([A-Za-z_][a-zA-Z0-9_.<>,[ \\t]\\[\\]]*)[ \\t]+([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(");
    private static final Pattern METHOD_CALL_PATTERN = Pattern.compile("([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(");
    private static final Pattern FIELD_DECL_PATTERN = Pattern.compile("\\b([A-Za-z_][a-zA-Z0-9_.<>,[ \\t]\\[\\]]*)[ \\t]+([a-zA-Z_][a-zA-Z0-9_]*)[ \\t]*(=|;|,)");
    private static final Pattern NEW_TYPE_PATTERN = Pattern.compile("\\bnew\\s+([A-Za-z_][a-zA-Z0-9_]*)\\s*(?:<([^>]*)>)?");
    private static final Pattern CAST_TYPE_PATTERN = Pattern.compile("\\(\\s*([A-Za-z_][a-zA-Z0-9_]*(?:\\s*\\.\\s*[A-Za-z_][a-zA-Z0-9_]*)*)\\s*(?:<[^>]*>)?\\s*(\\[\\s*\\])*\\s*\\)\\s*(?=[a-zA-Z_\"'(\\d!~+-])");
    private static final Pattern FUNC_PARAMS_PATTERN = Pattern.compile("\\bfunction\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*\\(([^)]*)\\)");
    private String text = "";
    private final List<ScriptLine> lines = new ArrayList<ScriptLine>();
    private final List<ImportData> imports = new ArrayList<ImportData>();
    private final Set<String> wildcardPackages = new HashSet<String>();
    private Map<String, ImportData> importsBySimpleName = new HashMap<String, ImportData>();
    private final Map<String, ScriptTypeInfo> scriptTypes = new HashMap<String, ScriptTypeInfo>();
    private final Map<String, ScriptTypeInfo> scriptTypesByFullName = new HashMap<String, ScriptTypeInfo>();
    private final Map<String, ScriptTypeInfo> scriptTypesByDotName = new HashMap<String, ScriptTypeInfo>();
    private final List<MethodInfo> methods = new ArrayList<MethodInfo>();
    private final Map<String, FieldInfo> globalFields = new HashMap<String, FieldInfo>();
    private final Map<Integer, Map<String, List<FieldInfo>>> methodLocals = new HashMap<Integer, Map<String, List<FieldInfo>>>();
    private final Map<String, List<FieldInfo>> topLevelLocals = new HashMap<String, List<FieldInfo>>();
    private final List<InnerCallableScope> innerScopes = new ArrayList<InnerCallableScope>();
    private final List<MethodCallInfo> methodCalls = new ArrayList<MethodCallInfo>();
    private final List<FieldAccessInfo> fieldAccesses = new ArrayList<FieldAccessInfo>();
    private final List<AssignmentInfo> externalFieldAssignments = new ArrayList<AssignmentInfo>();
    private final List<AssignmentInfo> declarationErrors = new ArrayList<AssignmentInfo>();
    private final List<DocumentError> errors = new ArrayList<DocumentError>();
    private final List<int[]> excludedRanges = new ArrayList<int[]>();
    private final Map<String, MethodInfo> scriptMethodSamContexts = new HashMap<String, MethodInfo>();
    private final Map<Integer, ObjectLiteralParser.ObjectLiteralAnalysis> objectLiterals = new HashMap<Integer, ObjectLiteralParser.ObjectLiteralAnalysis>();
    private final Map<Integer, LambdaCacheEntry> lambdaCache = new HashMap<Integer, LambdaCacheEntry>();
    private static final ThreadLocal<String> CURRENT_SAM_CONFLICT_ERROR = new ThreadLocal();
    private final TypeResolver typeResolver;
    private final JSDocParser jsDocParser = new JSDocParser(this);
    private String language = "ECMAScript";
    private ScriptContext scriptContext = ScriptContext.GLOBAL;
    private Map<String, String> editorGlobals = Collections.emptyMap();
    private final Set<String> implicitImports = new HashSet<String>();
    public int lineHeight = 13;
    public int totalHeight;
    public int visibleLines = 1;
    public int linesCount;
    @Deprecated
    private JSScriptAnalyzer currentJSAnalyzer;
    private static final Pattern METHOD_REF_PATTERN = Pattern.compile("([a-zA-Z_$][a-zA-Z0-9_$]*(?:\\.[a-zA-Z_$][a-zA-Z0-9_$]*)*)\\s*::\\s*([a-zA-Z_$][a-zA-Z0-9_$]*|new)");
    private static final Set<String> GENERIC_KEYWORDS = new HashSet<String>(Arrays.asList("extends", "super"));

    public ScriptDocument(String text) {
        this.typeResolver = TypeResolver.getInstance();
        this.setText(text);
    }

    public ScriptDocument(String text, TypeResolver resolver) {
        this.typeResolver = resolver != null ? resolver : TypeResolver.getInstance();
        this.setText(text);
    }

    public ScriptDocument(String text, String language) {
        this.typeResolver = TypeResolver.getInstance();
        this.language = language != null ? language : "ECMAScript";
        this.setText(text);
    }

    public void setLanguage(String language) {
        this.language = language != null ? language : "ECMAScript";
    }

    public String getLanguage() {
        return this.language;
    }

    public boolean isJavaScript() {
        return "ECMAScript".equalsIgnoreCase(this.language);
    }

    public void setScriptContext(ScriptContext context) {
        this.scriptContext = context != null ? context : ScriptContext.GLOBAL;
    }

    public ScriptContext getScriptContext() {
        return this.scriptContext;
    }

    public void setEditorGlobals(Map<String, String> globals) {
        if (globals == null || globals.isEmpty()) {
            this.editorGlobals = Collections.emptyMap();
            return;
        }
        this.editorGlobals = new LinkedHashMap<String, String>(globals);
    }

    public Map<String, String> getEditorGlobals() {
        return Collections.unmodifiableMap(this.editorGlobals);
    }

    public void addImplicitImports(String ... patterns) {
        if (patterns != null) {
            for (String pattern : patterns) {
                if (pattern == null || pattern.isEmpty()) continue;
                this.implicitImports.add(pattern);
            }
        }
    }

    public void setText(String text) {
        this.text = text != null ? text.replaceAll("\\r?\\n|\\r", "\n") : "";
    }

    public String getText() {
        return this.text;
    }

    public void init(int width, int height) {
        this.lines.clear();
        this.lineHeight = ClientProxy.Font.height();
        if (this.lineHeight == 0) {
            this.lineHeight = 12;
        }
        String[] sourceLines = this.text.split("\n", -1);
        int totalChars = 0;
        int lineIndex = 0;
        for (String sourceLine : sourceLines) {
            StringBuilder currentLine = new StringBuilder();
            Matcher m = WORD_PATTERN.matcher(sourceLine);
            int i = 0;
            while (m.find()) {
                String word = sourceLine.substring(i, m.start());
                if (ClientProxy.Font.width(currentLine + word) > width - 10) {
                    int lineStart = totalChars;
                    int lineEnd = totalChars + currentLine.length();
                    this.lines.add(new ScriptLine(currentLine.toString(), lineStart, lineEnd, lineIndex++));
                    totalChars = lineEnd;
                    currentLine = new StringBuilder();
                }
                currentLine.append(word);
                i = m.start();
            }
            int lineStart = totalChars;
            int lineEnd = totalChars + currentLine.length() + 1;
            this.lines.add(new ScriptLine(currentLine.toString(), lineStart, lineEnd, lineIndex++));
            totalChars = lineEnd;
        }
        for (int li = 0; li < this.lines.size(); ++li) {
            ScriptLine line = this.lines.get(li);
            line.setParent(this);
            if (li <= 0) continue;
            line.setPrev(this.lines.get(li - 1));
            this.lines.get(li - 1).setNext(line);
        }
        this.linesCount = this.lines.size();
        this.totalHeight = this.linesCount * this.lineHeight;
        this.visibleLines = Math.max(height / this.lineHeight - 1, 1);
        INSTANCE = this;
    }

    public void formatCodeText() {
        this.errors.clear();
        this.imports.clear();
        this.methods.clear();
        this.globalFields.clear();
        this.wildcardPackages.clear();
        this.excludedRanges.clear();
        this.methodLocals.clear();
        this.topLevelLocals.clear();
        this.scriptTypes.clear();
        this.scriptTypesByFullName.clear();
        this.scriptTypesByDotName.clear();
        this.innerScopes.clear();
        this.methodCalls.clear();
        this.externalFieldAssignments.clear();
        this.declarationErrors.clear();
        this.scriptMethodSamContexts.clear();
        this.objectLiterals.clear();
        this.lambdaCache.clear();
        List<ScriptLine.Mark> marks = this.formatUnified();
        marks = this.resolveConflicts(marks);
        for (ScriptLine line : this.lines) {
            line.buildTokensFromMarks(marks, this.text, this);
        }
        this.removeUnusedImplicitImports();
        this.computeIndentGuides(marks);
        this.populateErrors();
    }

    @Deprecated
    public JSScriptAnalyzer getJSAnalyzer() {
        return this.currentJSAnalyzer;
    }

    private List<ScriptLine.Mark> formatUnified() {
        TypeChecker.enterTypeCheckingContext(this.isJavaScript());
        this.findExcludedRanges();
        this.parseImports();
        this.parseStructure();
        return this.buildMarks();
    }

    @Deprecated
    private List<ScriptLine.Mark> formatJavaScript() {
        this.currentJSAnalyzer = new JSScriptAnalyzer(this);
        return this.currentJSAnalyzer.analyze();
    }

    @Deprecated
    private List<ScriptLine.Mark> formatJava() {
        return this.formatUnified();
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
        this.excludedRanges.sort(Comparator.comparingInt(a -> a[0]));
        this.mergeOverlappingRanges();
    }

    private void mergeOverlappingRanges() {
        if (this.excludedRanges.size() < 2) {
            return;
        }
        ArrayList<int[]> merged = new ArrayList<int[]>();
        int[] current = this.excludedRanges.get(0);
        for (int i = 1; i < this.excludedRanges.size(); ++i) {
            int[] next = this.excludedRanges.get(i);
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
                continue;
            }
            merged.add(current);
            current = next;
        }
        merged.add(current);
        this.excludedRanges.clear();
        this.excludedRanges.addAll(merged);
    }

    public boolean isExcluded(int position) {
        for (int[] range : this.excludedRanges) {
            if (position < range[0] || position >= range[1]) continue;
            return true;
        }
        return false;
    }

    public boolean isExcludedInclusive(int position) {
        for (int[] range : this.excludedRanges) {
            if (position < range[0] || position > range[1]) continue;
            return true;
        }
        return false;
    }

    public boolean hasExcludedRange(int start, int end) {
        for (int[] range : this.excludedRanges) {
            if (start >= range[1] || range[0] >= end) continue;
            return true;
        }
        return false;
    }

    private boolean isInsideNestedType(int position, ScriptTypeInfo type) {
        for (ScriptTypeInfo inner : type.getInnerClasses()) {
            if (!inner.containsPosition(position)) continue;
            return true;
        }
        return false;
    }

    public List<int[]> getExcludedRanges() {
        return this.excludedRanges;
    }

    private boolean isInCommentRange(int position) {
        Matcher m = COMMENT_PATTERN.matcher(this.text);
        while (m.find()) {
            if (position < m.start() || position >= m.end()) continue;
            return true;
        }
        return false;
    }

    private void parseImports() {
        if (this.isJavaScript()) {
            return;
        }
        Matcher m = IMPORT_PATTERN.matcher(this.text);
        while (m.find()) {
            if (this.isExcluded(m.start())) continue;
            String fullPath = m.group(1).replaceAll("\\s+", "").trim();
            String matchText = m.group(0);
            boolean isWildcard = matchText.contains("*");
            boolean isStatic = matchText.contains("static");
            if (fullPath.endsWith(".")) {
                // empty if block
            }
            int lastDot = fullPath.lastIndexOf(46);
            String simpleName = isWildcard ? null : (lastDot >= 0 ? fullPath.substring(lastDot + 1) : fullPath);
            ImportData importData = new ImportData(fullPath, simpleName, isWildcard, isStatic, m.start(), m.end(), m.start(1), m.end(1));
            this.imports.add(importData);
            if (!isWildcard) continue;
            this.wildcardPackages.add(fullPath);
        }
        this.addAllImplicitImportsToList();
        this.importsBySimpleName = this.typeResolver.resolveImports(this.imports);
    }

    private void addAllImplicitImportsToList() {
        for (String pattern : this.implicitImports) {
            ImportData importData;
            String simpleName;
            String fullPath;
            if (pattern == null || pattern.isEmpty()) continue;
            boolean isWildcard = pattern.endsWith(".*");
            if (isWildcard) {
                fullPath = pattern.substring(0, pattern.length() - 2);
                simpleName = null;
                this.wildcardPackages.add(fullPath);
            } else {
                fullPath = pattern.replace('$', '.');
                int lastDot = fullPath.lastIndexOf(46);
                String string = simpleName = lastDot >= 0 ? fullPath.substring(lastDot + 1) : fullPath;
            }
            if (this.imports.contains(importData = new ImportData(fullPath, simpleName, isWildcard, false, -1, -1, -1, -1))) continue;
            this.imports.add(importData);
        }
    }

    private void removeUnusedImplicitImports() {
        ArrayList<ImportData> toRemove = new ArrayList<ImportData>();
        for (ImportData imp : this.imports) {
            String fullPath;
            String simpleName;
            if (imp.getStartOffset() != -1 || imp.isWildcard() || this.isTypeReferenced(simpleName = imp.getSimpleName(), fullPath = imp.getFullPath())) continue;
            toRemove.add(imp);
        }
        this.imports.removeAll(toRemove);
        this.importsBySimpleName = this.typeResolver.resolveImports(this.imports);
    }

    private boolean isTypeReferenced(String simpleName, String fullPath) {
        if (simpleName == null || simpleName.isEmpty()) {
            return false;
        }
        for (ScriptLine line : this.lines) {
            for (Token token : line.getTokens()) {
                String lastPathPart;
                String lastTokenPart;
                String tokenText = token.getText();
                if (tokenText.equals(simpleName)) {
                    return true;
                }
                if (fullPath.contains("$") && tokenText.equals(simpleName)) {
                    return true;
                }
                if (!fullPath.contains(".") || !tokenText.contains(".")) continue;
                String[] pathParts = fullPath.replace('$', '.').split("\\.");
                String[] tokenParts = tokenText.split("\\.");
                if (pathParts.length <= 0 || tokenParts.length <= 0 || !(lastTokenPart = tokenParts[tokenParts.length - 1]).equals(lastPathPart = pathParts[pathParts.length - 1])) continue;
                return true;
            }
        }
        return false;
    }

    private void parseStructure() {
        for (ImportData imp : this.imports) {
            imp.clearReferences();
        }
        if (!this.isJavaScript()) {
            this.parseScriptTypes();
        }
        this.parseMethodDeclarations();
        this.parseInnerCallableScopes();
        this.parseLocalVariables();
        this.parseGlobalFields();
        this.inferLambdaParameterTypes();
        this.parseAssignments();
        this.parseObjectLiterals();
        if (!this.isJavaScript()) {
            this.detectMethodInheritance();
        }
    }

    private void parseScriptTypes() {
        Pattern typeDecl = Pattern.compile("(?:(?:public|private|protected|static|final|abstract)\\s+)*(class|interface|enum)\\s+([A-Za-z_][a-zA-Z0-9_]*)");
        ArrayList<RawTypeDeclaration> rawDeclarations = new ArrayList<RawTypeDeclaration>();
        Matcher m = typeDecl.matcher(this.text);
        while (m.find()) {
            TypeInfo.Kind kind;
            int bodyStart;
            int bodyEnd;
            int bracePos;
            char c;
            int scanPos;
            if (this.isExcluded(m.start())) continue;
            String kindStr = m.group(1);
            String typeName = m.group(2);
            for (scanPos = m.end(); scanPos < this.text.length() && Character.isWhitespace(this.text.charAt(scanPos)); ++scanPos) {
            }
            String typeParamsClause = null;
            int typeParamsClauseOffset = -1;
            if (scanPos < this.text.length() && this.text.charAt(scanPos) == '<') {
                int depth = 1;
                int i = scanPos + 1;
                while (i < this.text.length() && depth > 0) {
                    if ((c = this.text.charAt(i++)) == '<') {
                        ++depth;
                        continue;
                    }
                    if (c != '>') continue;
                    --depth;
                }
                if (depth == 0) {
                    typeParamsClauseOffset = scanPos + 1;
                    typeParamsClause = this.text.substring(scanPos + 1, i - 1);
                    for (scanPos = i; scanPos < this.text.length() && Character.isWhitespace(this.text.charAt(scanPos)); ++scanPos) {
                    }
                }
            }
            int angleDepth = 0;
            for (bracePos = scanPos; bracePos < this.text.length(); ++bracePos) {
                c = this.text.charAt(bracePos);
                if (c == '<') {
                    ++angleDepth;
                    continue;
                }
                if (c == '>') {
                    --angleDepth;
                    continue;
                }
                if (c == '{' && angleDepth == 0) break;
            }
            if (bracePos >= this.text.length()) continue;
            String betweenParamsAndBrace = this.text.substring(scanPos, bracePos).trim();
            String extendsClause = null;
            String implementsClause = null;
            int extIdx = ScriptDocument.indexOfAtDepthZero(betweenParamsAndBrace, "extends");
            int implIdx = ScriptDocument.indexOfAtDepthZero(betweenParamsAndBrace, "implements");
            if (extIdx >= 0) {
                int extEnd = implIdx >= 0 ? implIdx : betweenParamsAndBrace.length();
                extendsClause = betweenParamsAndBrace.substring(extIdx + 7, extEnd).trim();
            }
            if (implIdx >= 0) {
                implementsClause = betweenParamsAndBrace.substring(implIdx + 10).trim();
            }
            if ((bodyEnd = this.findMatchingBrace(bodyStart = bracePos)) < 0) {
                bodyEnd = this.text.length();
            }
            switch (kindStr) {
                case "interface": {
                    kind = TypeInfo.Kind.INTERFACE;
                    break;
                }
                case "enum": {
                    kind = TypeInfo.Kind.ENUM;
                    break;
                }
                default: {
                    kind = TypeInfo.Kind.CLASS;
                }
            }
            String fullMatch = this.text.substring(m.start(), bodyStart + 1);
            int modifiers = this.parseModifiers(fullMatch);
            JSDocInfo jsDoc = this.jsDocParser.extractJSDocBefore(this.text, m.start());
            rawDeclarations.add(new RawTypeDeclaration(typeName, kind, m.start(), bodyStart, bodyEnd, modifiers, typeParamsClause, typeParamsClauseOffset, extendsClause, implementsClause, jsDoc));
        }
        rawDeclarations.sort(Comparator.comparingInt(r -> r.bodyStart));
        ArrayList<ScriptTypeInfo> registeredTypes = new ArrayList<ScriptTypeInfo>(rawDeclarations.size());
        ArrayDeque<ScriptTypeInfo> preRegStack = new ArrayDeque<ScriptTypeInfo>();
        for (RawTypeDeclaration raw : rawDeclarations) {
            ScriptTypeInfo scriptType;
            while (!preRegStack.isEmpty() && ((ScriptTypeInfo)preRegStack.peek()).getBodyEnd() <= raw.bodyStart) {
                preRegStack.pop();
            }
            if (preRegStack.isEmpty()) {
                scriptType = ScriptTypeInfo.create(raw.name, raw.kind, raw.declOffset, raw.bodyStart, raw.bodyEnd, raw.modifiers);
                this.scriptTypes.put(raw.name, scriptType);
            } else {
                ScriptTypeInfo parent = (ScriptTypeInfo)preRegStack.peek();
                scriptType = ScriptTypeInfo.createInner(raw.name, raw.kind, parent, raw.declOffset, raw.bodyStart, raw.bodyEnd, raw.modifiers);
            }
            if (raw.jsDoc != null) {
                scriptType.setJSDocInfo(raw.jsDoc);
            }
            if (raw.typeParamsClause != null && !raw.typeParamsClause.trim().isEmpty()) {
                this.parseTypeParamsClause(raw.typeParamsClause, scriptType, raw.bodyStart + 1, raw.typeParamsClauseOffset);
            }
            this.scriptTypesByFullName.put(scriptType.getFullName(), scriptType);
            this.scriptTypesByDotName.put(scriptType.getDotSeparatedName(), scriptType);
            preRegStack.push(scriptType);
            registeredTypes.add(scriptType);
        }
        for (int i = 0; i < rawDeclarations.size(); ++i) {
            RawTypeDeclaration raw = (RawTypeDeclaration)rawDeclarations.get(i);
            ScriptTypeInfo scriptType = (ScriptTypeInfo)registeredTypes.get(i);
            if (raw.extendsClause != null && !raw.extendsClause.trim().isEmpty()) {
                String argsContent;
                TypeInfo parentType;
                String parentName = raw.extendsClause.trim();
                int genIdx = parentName.indexOf(60);
                String parentGenericArgs = null;
                if (genIdx >= 0) {
                    parentGenericArgs = parentName.substring(genIdx);
                    parentName = parentName.substring(0, genIdx);
                }
                if ((parentType = this.resolveType(parentName, raw.bodyStart + 1)) == null) {
                    parentType = TypeInfo.unresolved(parentName, parentName);
                }
                if (parentGenericArgs != null && parentType.isResolved() && !(argsContent = parentGenericArgs.substring(1, parentGenericArgs.length() - 1).trim()).isEmpty()) {
                    parentType = this.parameterizeWithClause(parentType, argsContent, raw.bodyStart + 1);
                }
                scriptType.setSuperClass(parentType, parentName);
            }
            if (raw.implementsClause != null && !raw.implementsClause.trim().isEmpty()) {
                List<String> interfaceNames = ScriptDocument.splitAtDepthZero(raw.implementsClause, ',');
                for (String ifaceName : interfaceNames) {
                    String argsContent;
                    TypeInfo ifaceType;
                    String trimmedName = ifaceName.trim();
                    if (trimmedName.isEmpty()) continue;
                    int genIdx = trimmedName.indexOf(60);
                    String ifaceGenericArgs = null;
                    if (genIdx >= 0) {
                        ifaceGenericArgs = trimmedName.substring(genIdx);
                        trimmedName = trimmedName.substring(0, genIdx);
                    }
                    if ((ifaceType = this.resolveType(trimmedName, raw.bodyStart + 1)) == null) {
                        ifaceType = TypeInfo.unresolved(trimmedName, trimmedName);
                    }
                    if (ifaceGenericArgs != null && ifaceType.isResolved() && !(argsContent = ifaceGenericArgs.substring(1, ifaceGenericArgs.length() - 1).trim()).isEmpty()) {
                        ifaceType = this.parameterizeWithClause(ifaceType, argsContent, raw.bodyStart + 1);
                    }
                    scriptType.addImplementedInterface(ifaceType, trimmedName);
                }
            }
            this.parseScriptTypeMembers(scriptType);
        }
    }

    private void parseTypeParamsClause(String clause, ScriptTypeInfo scriptType, int declOffset, int clauseStartOffset) {
        List<String> params = ScriptDocument.splitAtDepthZero(clause, ',');
        int paramTextPos = 0;
        Iterator<String> iterator = params.iterator();
        while (iterator.hasNext()) {
            TypeInfo boundTypeInfo;
            int endIdx;
            String param;
            String trimmed = (param = iterator.next()).trim();
            int leadingSpaces = param.indexOf(trimmed.isEmpty() ? param : trimmed);
            if (trimmed.isEmpty()) {
                paramTextPos += param.length() + 1;
                continue;
            }
            int extendsIdx = ScriptDocument.indexOfAtDepthZero(trimmed, "extends");
            String paramName = extendsIdx >= 0 ? trimmed.substring(0, extendsIdx).trim() : trimmed;
            for (endIdx = 0; endIdx < paramName.length() && (Character.isJavaIdentifierPart(paramName.charAt(endIdx)) || paramName.charAt(endIdx) == '.'); ++endIdx) {
            }
            paramName = paramName.substring(0, endIdx);
            String boundName = null;
            ArrayList<String> additionalBoundNames = new ArrayList<String>();
            if (extendsIdx >= 0) {
                String afterExtends = trimmed.substring(extendsIdx + 7).trim();
                List<String> bounds = ScriptDocument.splitAtDepthZero(afterExtends, '&');
                int searchFrom = extendsIdx + 7;
                for (int bi = 0; bi < bounds.size(); ++bi) {
                    TypeInfo addBoundCheck;
                    int boundEndIdx;
                    String bound = bounds.get(bi).trim();
                    if (bound.isEmpty()) continue;
                    for (boundEndIdx = 0; boundEndIdx < bound.length() && (Character.isJavaIdentifierPart(bound.charAt(boundEndIdx)) || bound.charAt(boundEndIdx) == '.'); ++boundEndIdx) {
                    }
                    if (boundEndIdx <= 0) continue;
                    String name = bound.substring(0, boundEndIdx);
                    int nameIdxInTrimmed = trimmed.indexOf(name, searchFrom);
                    searchFrom = nameIdxInTrimmed + name.length();
                    if (bi == 0) {
                        boundName = name;
                        continue;
                    }
                    additionalBoundNames.add(name);
                    if (clauseStartOffset < 0 || nameIdxInTrimmed < 0 || (addBoundCheck = this.resolveType(name, declOffset)) == null || !addBoundCheck.isResolved() || this.isInterfaceType(addBoundCheck)) continue;
                    int errorStart = clauseStartOffset + paramTextPos + leadingSpaces + nameIdxInTrimmed;
                    int errorEnd = errorStart + name.length();
                    this.addError(null, errorStart, errorEnd, "Interface expected here");
                }
            }
            TypeParamInfo typeParam = new TypeParamInfo(paramName, boundName, null);
            if (boundName != null && (boundTypeInfo = this.resolveType(boundName, declOffset)) != null && boundTypeInfo.isResolved()) {
                typeParam.setBoundTypeInfo(boundTypeInfo);
            }
            for (String addBound : additionalBoundNames) {
                typeParam.addAdditionalBound(addBound);
                TypeInfo addBoundType = this.resolveType(addBound, declOffset);
                if (addBoundType == null || !addBoundType.isResolved()) continue;
                typeParam.addAdditionalBoundType(addBoundType);
            }
            scriptType.addDeclaredTypeParam(typeParam);
            paramTextPos += param.length() + 1;
        }
    }

    private boolean isInterfaceType(TypeInfo typeInfo) {
        if (typeInfo.isInterface()) {
            return true;
        }
        Class<?> javaClass = typeInfo.getJavaClass();
        if (javaClass != null) {
            return javaClass.isInterface();
        }
        return false;
    }

    private static List<String> splitAtDepthZero(String s, char delimiter) {
        ArrayList<String> result = new ArrayList<String>();
        int depth = 0;
        int start = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '<') {
                ++depth;
                continue;
            }
            if (c == '>') {
                --depth;
                continue;
            }
            if (c != delimiter || depth != 0) continue;
            result.add(s.substring(start, i));
            start = i + 1;
        }
        result.add(s.substring(start));
        return result;
    }

    private static String normalizeGenericNewlines(String src) {
        StringBuilder sb = new StringBuilder(src.length());
        int depth = 0;
        for (int i = 0; i < src.length(); ++i) {
            char c = src.charAt(i);
            if (c == '<') {
                ++depth;
            } else if (c == '>' && depth > 0) {
                --depth;
            }
            if ((c == '\n' || c == '\r') && depth > 0) {
                sb.append(' ');
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static int indexOfAtDepthZero(String s, String keyword) {
        int depth = 0;
        int keyLen = keyword.length();
        for (int i = 0; i <= s.length() - keyLen; ++i) {
            boolean endOk;
            char c = s.charAt(i);
            if (c == '<') {
                ++depth;
                continue;
            }
            if (c == '>') {
                --depth;
                continue;
            }
            if (depth != 0 || !s.regionMatches(i, keyword, 0, keyLen)) continue;
            boolean startOk = i == 0 || !Character.isJavaIdentifierPart(s.charAt(i - 1));
            boolean bl = endOk = i + keyLen >= s.length() || !Character.isJavaIdentifierPart(s.charAt(i + keyLen));
            if (!startOk || !endOk) continue;
            return i;
        }
        return -1;
    }

    private TypeInfo parameterizeWithClause(TypeInfo baseType, String typeArgsClause, int position) {
        return this.parameterizeWithClause(baseType, typeArgsClause, position, -1);
    }

    private TypeInfo parameterizeWithClause(TypeInfo baseType, String typeArgsClause, int position, int clauseTextOffset) {
        List<String> argSegments = ScriptDocument.splitAtDepthZero(typeArgsClause, ',');
        ArrayList<TypeInfo> typeArgs = new ArrayList<TypeInfo>();
        for (String argName : argSegments) {
            String nestedArgs;
            String trimmed = argName.trim();
            if (trimmed.isEmpty()) continue;
            int ltIdx = trimmed.indexOf(60);
            String baseName = ltIdx >= 0 ? trimmed.substring(0, ltIdx).trim() : trimmed;
            TypeInfo argType = this.resolveType(baseName, position);
            if (argType == null) {
                argType = TypeInfo.unresolved(baseName, baseName);
            }
            if (ltIdx >= 0 && argType.isResolved() && trimmed.endsWith(">") && !(nestedArgs = trimmed.substring(ltIdx + 1, trimmed.length() - 1).trim()).isEmpty()) {
                argType = this.parameterizeWithClause(argType, nestedArgs, position);
            }
            typeArgs.add(argType);
        }
        if (!typeArgs.isEmpty()) {
            if (clauseTextOffset >= 0) {
                this.validateTypeArgBounds(baseType, typeArgs, argSegments, clauseTextOffset);
            }
            return baseType.parameterize(typeArgs);
        }
        return baseType;
    }

    private void validateTypeArgBounds(TypeInfo baseType, List<TypeInfo> typeArgs, List<String> argSegments, int clauseTextOffset) {
        List<TypeParamInfo> typeParams = baseType.getTypeParams();
        if (typeParams == null || typeParams.isEmpty()) {
            return;
        }
        int count = Math.min(typeArgs.size(), typeParams.size());
        int charPos = 0;
        int segIdx = 0;
        for (int i = 0; i < count; ++i) {
            int leading;
            TypeInfo argType = typeArgs.get(i);
            TypeParamInfo param = typeParams.get(i);
            String segment = segIdx < argSegments.size() ? argSegments.get(segIdx) : "";
            String trimmedSeg = segment.trim();
            for (leading = 0; leading < segment.length() && Character.isWhitespace(segment.charAt(leading)); ++leading) {
            }
            if (argType != null && argType.isResolved() && !trimmedSeg.isEmpty()) {
                TypeInfo primaryBound = param.getBoundTypeInfo();
                if (primaryBound != null && primaryBound.isResolved() && !TypeChecker.isTypeCompatible(primaryBound, argType)) {
                    int errorStart = clauseTextOffset + charPos + leading;
                    int errorEnd = errorStart + trimmedSeg.length();
                    this.addError(null, errorStart, errorEnd, "Type argument " + argType.getSimpleName() + " is not within bounds of type parameter " + param.getName() + " (expected extends " + primaryBound.getSimpleName() + ")");
                }
                for (TypeInfo addBound : param.getAdditionalBoundTypes()) {
                    if (addBound == null || !addBound.isResolved() || TypeChecker.isTypeCompatible(addBound, argType)) continue;
                    int errorStart = clauseTextOffset + charPos + leading;
                    int errorEnd = errorStart + trimmedSeg.length();
                    this.addError(null, errorStart, errorEnd, "Type argument " + argType.getSimpleName() + " does not implement required interface " + addBound.getSimpleName());
                    break;
                }
            }
            charPos += segment.length() + 1;
            ++segIdx;
        }
    }

    private void markTypeParamDeclarations(List<ScriptLine.Mark> marks, int afterNameEnd, ScriptTypeInfo typeInfo) {
        int ltPos = this.text.indexOf(60, afterNameEnd);
        if (ltPos < 0 || ltPos > afterNameEnd + 5) {
            return;
        }
        int depth = 1;
        int i = ltPos + 1;
        while (i < this.text.length() && depth > 0) {
            char c;
            if ((c = this.text.charAt(i++)) == '<') {
                ++depth;
                continue;
            }
            if (c != '>') continue;
            --depth;
        }
        if (depth != 0) {
            return;
        }
        int gtPos = i - 1;
        String clause = this.text.substring(ltPos + 1, gtPos);
        List<String> paramSegments = ScriptDocument.splitAtDepthZero(clause, ',');
        int segmentOffset = ltPos + 1;
        int paramIndex = 0;
        List<TypeParamInfo> declaredParams = typeInfo.getDeclaredTypeParams();
        for (String segment : paramSegments) {
            TypeParamInfo param;
            int paramIdx;
            if (paramIndex >= declaredParams.size()) break;
            if ((paramIdx = segment.indexOf((param = declaredParams.get(paramIndex++)).getName())) < 0) continue;
            int paramStart = segmentOffset + paramIdx;
            int paramEnd = paramStart + param.getName().length();
            marks.add(new ScriptLine.Mark(paramStart, paramEnd, TokenType.GENERIC_TYPE_PARAM, param));
            int extendsIdx = ScriptDocument.indexOfAtDepthZero(segment, "extends");
            if (extendsIdx >= 0) {
                String afterExtends;
                int extendsStart = segmentOffset + extendsIdx;
                marks.add(new ScriptLine.Mark(extendsStart, extendsStart + 7, TokenType.KEYWORD));
                TypeInfo boundType = param.getBoundTypeInfo();
                if (boundType != null && !(afterExtends = segment.substring(extendsIdx + 7).trim()).isEmpty()) {
                    int boundScanPos;
                    List<String> boundSegments = ScriptDocument.splitAtDepthZero(afterExtends, '&');
                    for (boundScanPos = extendsStart + 7; boundScanPos < gtPos && Character.isWhitespace(this.text.charAt(boundScanPos)); ++boundScanPos) {
                    }
                    for (int bi = 0; bi < boundSegments.size(); ++bi) {
                        int nameEnd;
                        String boundSeg = boundSegments.get(bi).trim();
                        if (boundSeg.isEmpty()) continue;
                        if (bi > 0) {
                            int ampSearch;
                            for (ampSearch = boundScanPos - 1; ampSearch >= 0 && this.text.charAt(ampSearch) != '&'; --ampSearch) {
                            }
                            if (ampSearch >= 0) {
                                marks.add(new ScriptLine.Mark(ampSearch, ampSearch + 1, TokenType.DEFAULT));
                            }
                        }
                        for (nameEnd = 0; nameEnd < boundSeg.length() && (Character.isJavaIdentifierPart(boundSeg.charAt(nameEnd)) || boundSeg.charAt(nameEnd) == '.'); ++nameEnd) {
                        }
                        if (nameEnd > 0) {
                            TypeInfo bType;
                            String bName = boundSeg.substring(0, nameEnd);
                            if (bi == 0) {
                                bType = boundType;
                            } else {
                                List<TypeInfo> addBounds = param.getAdditionalBoundTypes();
                                TypeInfo typeInfo2 = bType = bi - 1 < addBounds.size() ? addBounds.get(bi - 1) : null;
                            }
                            if (bType != null) {
                                marks.add(new ScriptLine.Mark(boundScanPos, boundScanPos + bName.length(), TokenType.getByType(bType), bType));
                            }
                            if (nameEnd < boundSeg.length() && boundSeg.charAt(nameEnd) == '<') {
                                int nestedStart = boundScanPos + nameEnd;
                                this.markNestedTypeParamUsages(marks, nestedStart, gtPos, declaredParams);
                            }
                        }
                        boundScanPos += boundSeg.length();
                        while (boundScanPos < gtPos && (Character.isWhitespace(this.text.charAt(boundScanPos)) || this.text.charAt(boundScanPos) == '&')) {
                            ++boundScanPos;
                        }
                    }
                }
            }
            segmentOffset += segment.length() + 1;
        }
    }

    private void markNestedTypeParamUsages(List<ScriptLine.Mark> marks, int start, int limit, List<TypeParamInfo> typeParams) {
        HashMap<String, TypeParamInfo> paramMap = new HashMap<String, TypeParamInfo>();
        for (TypeParamInfo tp : typeParams) {
            paramMap.put(tp.getName(), tp);
        }
        Pattern ident = Pattern.compile("\\b([A-Za-z_][a-zA-Z0-9_]*)\\b");
        String region = this.text.substring(start, Math.min(limit, this.text.length()));
        Matcher m = ident.matcher(region);
        while (m.find()) {
            TypeParamInfo tp = (TypeParamInfo)paramMap.get(m.group(1));
            if (tp == null) continue;
            int absStart = start + m.start(1);
            marks.add(new ScriptLine.Mark(absStart, absStart + m.group(1).length(), TokenType.GENERIC_TYPE_PARAM, tp));
        }
    }

    private void parseScriptTypeMembers(ScriptTypeInfo scriptType) {
        int bodyStart = scriptType.getBodyStart();
        int bodyEnd = scriptType.getBodyEnd();
        if (bodyStart < 0 || bodyEnd <= bodyStart) {
            return;
        }
        String bodyText = this.text.substring(bodyStart + 1, Math.min(bodyEnd, this.text.length()));
        String typeName = scriptType.getSimpleName();
        Pattern constructorPattern = Pattern.compile("\\b" + Pattern.quote(typeName) + "\\s*\\(([^)]*)\\)\\s*\\{");
        Matcher cm = constructorPattern.matcher(bodyText);
        while (cm.find()) {
            int nameOffset;
            int absPos = bodyStart + 1 + cm.start();
            if (this.isExcluded(absPos) || this.isInsideNestedType(absPos, scriptType)) continue;
            String paramList = cm.group(1);
            int constructorBodyStart = bodyStart + 1 + cm.end() - 1;
            int constructorBodyEnd = this.findMatchingBrace(constructorBodyStart);
            if (constructorBodyEnd < 0) {
                constructorBodyEnd = bodyEnd;
            }
            String documentation = this.extractDocumentationBefore(absPos);
            int modifiers = this.extractModifiersBackwards(cm.start() - 1, bodyText);
            List<FieldInfo> params = this.parseParametersWithPositions(paramList, bodyStart + 1 + cm.start(1));
            int typeOffset = nameOffset = bodyStart + 1 + cm.start();
            int fullDeclOffset = this.findFullDeclarationStart(cm.start(), bodyText);
            fullDeclOffset = fullDeclOffset >= 0 ? (fullDeclOffset += bodyStart + 1) : nameOffset;
            MethodInfo constructorInfo = MethodInfo.declaration(typeName, scriptType, scriptType, params, fullDeclOffset, typeOffset, nameOffset, constructorBodyStart, constructorBodyEnd, modifiers, documentation);
            scriptType.addConstructor(constructorInfo);
        }
        if (scriptType.getKind() == TypeInfo.Kind.ENUM) {
            List<EnumConstantInfo> constants = EnumConstantInfo.parseEnumConstants(scriptType, bodyText, bodyStart + 1, KEYWORD_PATTERN);
            for (EnumConstantInfo constant : constants) {
                scriptType.addEnumConstant(constant);
                if (constant.getConstructorCall() == null) continue;
            }
        }
    }

    private boolean isInsideNestedMethod(int position, int classBodyStart, int classBodyEnd) {
        String bodyText = this.text.substring(classBodyStart + 1, Math.min(classBodyEnd, this.text.length()));
        int relativePos = position - classBodyStart - 1;
        Pattern methodPattern = Pattern.compile("\\b[A-Za-z_][a-zA-Z0-9_<>\\[\\]]*\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*\\([^)]*\\)\\s*\\{");
        Matcher m = methodPattern.matcher(bodyText);
        while (m.find()) {
            int methodBodyStart = m.end() - 1;
            int absMethodBodyStart = classBodyStart + 1 + methodBodyStart;
            int absMethodBodyEnd = this.findMatchingBrace(absMethodBodyStart);
            if (absMethodBodyEnd <= 0 || position <= absMethodBodyStart || position >= absMethodBodyEnd) continue;
            return true;
        }
        return false;
    }

    private void parseMethodDeclarations() {
        if (this.isJavaScript()) {
            Pattern funcPattern = Pattern.compile("function\\s+(\\w+)\\s*\\(([^)]*)\\)\\s*\\{");
            Matcher m = funcPattern.matcher(this.text);
            while (m.find()) {
                String hookContext;
                if (this.isExcluded(m.start())) continue;
                String funcName = m.group(1);
                String paramList = m.group(2);
                int nameStart = m.start(1);
                int bodyStart = this.text.indexOf(123, m.end() - 1);
                int bodyEnd = this.findMatchingBrace(bodyStart);
                if (bodyEnd < 0) {
                    bodyEnd = this.text.length();
                }
                String documentation = this.extractDocumentationBefore(m.start());
                JSDocInfo jsDoc = this.jsDocParser.extractJSDocBefore(this.text, m.start());
                ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
                TypeInfo returnType = TypeInfo.fromPrimitive("void");
                List<String> namespaces = this.scriptContext != null ? this.scriptContext.getNamespaces() : Collections.singletonList("Global");
                boolean runtimeHook = false;
                if (ScriptHookController.Instance != null && this.scriptContext != null && (hookContext = this.scriptContext.hookContext) != null && !hookContext.isEmpty()) {
                    runtimeHook = ScriptHookController.Instance.hasHook(hookContext, funcName);
                }
                if (runtimeHook) {
                    String[] paramNames;
                    if (this.typeResolver.isJSHook(namespaces, funcName)) {
                        List<JSTypeRegistry.HookSignature> sigs = this.typeResolver.getJSHookSignatures(namespaces, funcName);
                        if (!sigs.isEmpty()) {
                            String[] paramNames2;
                            JSTypeRegistry.HookSignature hookSignature = sigs.get(0);
                            documentation = hookSignature.doc;
                            if (paramList != null && !paramList.trim().isEmpty() && (paramNames2 = paramList.split(",")).length > 0) {
                                String paramName = paramNames2[0].trim();
                                TypeInfo paramType = this.typeResolver.resolveJSType(hookSignature.paramType);
                                int paramStart = m.start(2) + paramList.indexOf(paramName);
                                params.add(FieldInfo.parameter(paramName, paramType, paramStart, null));
                            }
                        }
                    } else if (paramList != null && !paramList.trim().isEmpty() && (paramNames = paramList.split(",")).length > 0) {
                        String string = paramNames[0].trim();
                        int paramStart = m.start(2) + paramList.indexOf(string);
                        params.add(FieldInfo.parameter(string, TypeInfo.ANY, paramStart, null));
                    }
                } else {
                    if (paramList != null && !paramList.trim().isEmpty()) {
                        int paramOffset = m.start(2);
                        for (String p : paramList.split(",")) {
                            JSDocParamTag paramTag;
                            String pn = p.trim();
                            if (pn.isEmpty()) continue;
                            int paramStart = paramOffset + paramList.indexOf(pn);
                            TypeInfo paramType = TypeInfo.ANY;
                            if (jsDoc != null && (paramTag = jsDoc.getParamTag(pn)) != null && paramTag.getTypeInfo() != null) {
                                paramType = paramTag.getTypeInfo();
                            }
                            params.add(FieldInfo.parameter(pn, paramType, paramStart, null));
                        }
                    }
                    if (jsDoc != null && jsDoc.hasReturnTag() && jsDoc.getReturnType() != null) {
                        returnType = jsDoc.getReturnType();
                    }
                }
                MethodInfo methodInfo = MethodInfo.declaration(funcName, null, returnType, params, m.start(), nameStart, nameStart, bodyStart, bodyEnd, 0, documentation);
                if (jsDoc != null) {
                    methodInfo.setJSDocInfo(jsDoc);
                }
                this.methods.add(methodInfo);
            }
        } else {
            Pattern methodWithBody = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_.<>,[ \\t]\\[\\]]*)[ \\t]+([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(([^)]*)\\)\\s*(\\{|;)");
            Matcher m = methodWithBody.matcher(ScriptDocument.normalizeGenericNewlines(this.text));
            while (m.find()) {
                int bodyEnd;
                String returnType;
                String methodName = m.group(2);
                if (this.isExcluded(m.start()) || this.isKeyword(methodName) || this.isExcluded(m.start(2)) || this.hasExcludedRange(m.start(1), m.end(1)) || (returnType = m.group(1)).equals("class") || returnType.equals("interface") || returnType.equals("enum") || returnType.equals("new") || TypeResolver.isModifier(returnType)) continue;
                String paramList = m.group(3);
                String delimiter = m.group(4);
                boolean hasBody = delimiter.equals("{");
                int bodyStart = !hasBody ? m.end() : this.text.indexOf(123, m.end() - 1);
                int n = bodyEnd = !hasBody ? m.end() : this.findMatchingBrace(bodyStart);
                if (bodyEnd < 0) {
                    bodyEnd = this.text.length();
                }
                String documentation = this.extractDocumentationBefore(m.start());
                int modifiers = this.extractModifiersBackwards(m.start() - 1, this.text);
                int typeOffset = m.start(1);
                int nameOffset = m.start(2);
                int fullDeclOffset = this.findFullDeclarationStart(m.start(1), this.text);
                ScriptTypeInfo scriptTypeInfo = this.findEnclosingScriptType(bodyStart);
                List<FieldInfo> params = this.parseParametersWithPositions(paramList, m.start(3));
                MethodInfo methodInfo = MethodInfo.declaration(methodName, scriptTypeInfo, this.resolveType(returnType, m.start(1)), params, fullDeclOffset, typeOffset, nameOffset, bodyStart, bodyEnd, modifiers, documentation);
                if (scriptTypeInfo != null) {
                    scriptTypeInfo.addMethod(methodInfo);
                } else {
                    this.methods.add(methodInfo);
                }
                String methodBodyText = bodyEnd > bodyStart + 1 ? this.text.substring(bodyStart + 1, bodyEnd) : "";
                methodInfo.validate(methodBodyText, hasBody, (expr, pos) -> this.resolveExpressionType(expr, pos));
            }
        }
        this.checkDuplicateMethods();
    }

    private int findFullDeclarationStart(int typeStart, String sourceText) {
        int pos;
        for (pos = typeStart - 1; pos >= 0 && Character.isWhitespace(sourceText.charAt(pos)); --pos) {
        }
        int earliestPos = typeStart;
        while (pos >= 0) {
            String word;
            while (pos >= 0 && Character.isWhitespace(sourceText.charAt(pos))) {
                --pos;
            }
            if (pos < 0) break;
            int wordEnd = pos + 1;
            while (pos >= 0 && Character.isJavaIdentifierPart(sourceText.charAt(pos))) {
                --pos;
            }
            int wordStart = pos + 1;
            if (wordStart >= wordEnd || !TypeResolver.isModifier(word = sourceText.substring(wordStart, wordEnd))) break;
            earliestPos = wordStart;
        }
        return earliestPos;
    }

    private void checkDuplicateMethods() {
        HashMap<TypeInfo, List> methodsByType = new HashMap<TypeInfo, List>();
        for (MethodInfo method : this.getAllMethods()) {
            TypeInfo containingType = method.getContainingType();
            methodsByType.computeIfAbsent(containingType, k -> new ArrayList()).add(method);
        }
        for (List typeMethods : methodsByType.values()) {
            HashMap<MethodSignature, List> signatureMap = new HashMap<MethodSignature, List>();
            for (MethodInfo methodInfo : typeMethods) {
                MethodSignature signature = methodInfo.getSignature();
                signatureMap.computeIfAbsent(signature, k -> new ArrayList()).add(methodInfo);
            }
            for (Map.Entry entry : signatureMap.entrySet()) {
                List duplicates = (List)entry.getValue();
                if (duplicates.size() <= 1) continue;
                for (MethodInfo duplicate : duplicates) {
                    duplicate.setError(MethodInfo.ErrorType.DUPLICATE_METHOD, duplicate.getSignature() + " is already defined in this scope");
                }
            }
        }
    }

    private int calculateScopeDepth(int position) {
        int depth = 0;
        int braceDepth = 0;
        boolean inString = false;
        boolean inComment = false;
        boolean inLineComment = false;
        for (int i = 0; i < position && i < this.text.length(); ++i) {
            char next;
            char c = this.text.charAt(i);
            char c2 = next = i + 1 < this.text.length() ? this.text.charAt(i + 1) : (char)'\u0000';
            if (c == '\"' && !inComment && !inLineComment) {
                inString = !inString;
                continue;
            }
            if (inString) continue;
            if (!inComment && !inLineComment && c == '/' && next == '/') {
                inLineComment = true;
                ++i;
                continue;
            }
            if (!inComment && !inLineComment && c == '/' && next == '*') {
                inComment = true;
                ++i;
                continue;
            }
            if (inComment && c == '*' && next == '/') {
                inComment = false;
                ++i;
                continue;
            }
            if (inLineComment && c == '\n') {
                inLineComment = false;
                continue;
            }
            if (inComment || inLineComment) continue;
            if (c == '{') {
                depth = Math.max(depth, ++braceDepth);
                continue;
            }
            if (c != '}') continue;
            --braceDepth;
        }
        return depth;
    }

    private List<FieldInfo> parseParametersWithPositions(String paramList, int paramListStart) {
        ArrayList<FieldInfo> params = new ArrayList<FieldInfo>();
        if (paramList == null || paramList.trim().isEmpty()) {
            return params;
        }
        StringBuilder normalized = new StringBuilder(paramList.length());
        int[] posMap = new int[paramList.length() + 1];
        boolean lastWasSpace = false;
        for (int i = 0; i < paramList.length(); ++i) {
            char c = paramList.charAt(i);
            if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                if (lastWasSpace) continue;
                posMap[normalized.length()] = i;
                normalized.append(' ');
                lastWasSpace = true;
                continue;
            }
            posMap[normalized.length()] = i;
            normalized.append(c);
            lastWasSpace = false;
        }
        posMap[normalized.length()] = paramList.length();
        String normalizedStr = normalized.toString();
        ArrayList<int[]> paramRanges = new ArrayList<int[]>();
        int depth = 0;
        int start = 0;
        for (int i = 0; i < normalizedStr.length(); ++i) {
            char c = normalizedStr.charAt(i);
            if (c == '<') {
                ++depth;
                continue;
            }
            if (c == '>') {
                --depth;
                continue;
            }
            if (c != ',' || depth != 0) continue;
            paramRanges.add(new int[]{start, i});
            start = i + 1;
        }
        paramRanges.add(new int[]{start, normalizedStr.length()});
        Pattern paramPattern = Pattern.compile("\\s*([a-zA-Z_][a-zA-Z0-9_.<>,? \\[\\]]*)(?:\\.{3})?\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*");
        for (int[] range : paramRanges) {
            Matcher m;
            String segment = normalizedStr.substring(range[0], range[1]);
            if (segment.trim().isEmpty() || !(m = paramPattern.matcher(segment)).matches()) continue;
            String rawType = m.group(1).replaceAll("\\s+", "");
            String paramName = m.group(2);
            String between = segment.substring(m.end(1), m.start(2));
            boolean isVarArg = between.contains("...");
            TypeInfo typeInfo = this.resolveType(rawType, paramListStart);
            int normalizedNamePos = range[0] + m.start(2);
            int paramNameStart = paramListStart + posMap[normalizedNamePos];
            FieldInfo fieldInfo = FieldInfo.parameter(paramName, typeInfo, paramNameStart, null);
            fieldInfo.setVarArg(isVarArg);
            params.add(fieldInfo);
        }
        return params;
    }

    private ScopeInfo computeBlockScope(int bodyStart, int bodyEnd, int declarationOffset) {
        int end = Math.min(bodyEnd, this.text.length());
        int pos = Math.min(Math.max(declarationOffset, bodyStart), end);
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = bodyStart; i < pos && i < this.text.length(); ++i) {
            if (this.isExcluded(i)) continue;
            char c = this.text.charAt(i);
            if (c == '{') {
                stack.push(i);
                continue;
            }
            if (c != '}' || stack.isEmpty()) continue;
            stack.pop();
        }
        if (stack.isEmpty()) {
            ScopeInfo parenScope = this.computeParenStatementScope(bodyStart, bodyEnd, pos);
            if (parenScope != null) {
                return parenScope;
            }
            return new ScopeInfo(bodyStart, bodyEnd, false, "method");
        }
        int openBrace = (Integer)stack.peek();
        int closeBrace = this.findMatchingBrace(openBrace);
        if (closeBrace < 0 || closeBrace > bodyEnd) {
            return new ScopeInfo(bodyStart, bodyEnd, false, "method");
        }
        return new ScopeInfo(openBrace + 1, closeBrace, false, "block");
    }

    private ScopeInfo computeParenStatementScope(int bodyStart, int bodyEnd, int position) {
        int openParen = this.findEnclosingParenStart(bodyStart, position);
        if (openParen < 0) {
            return null;
        }
        int closeParen = this.findMatchingParen(openParen, bodyEnd);
        if (closeParen < 0 || position > closeParen) {
            return null;
        }
        String keyword = this.readKeywordBefore(openParen);
        if (!"for".equals(keyword) && !"catch".equals(keyword)) {
            return null;
        }
        int after = this.skipWhitespaceAndExcluded(closeParen + 1, bodyEnd);
        if (after < 0 || after >= bodyEnd) {
            return null;
        }
        if (this.text.charAt(after) == '{') {
            int closeBrace = this.findMatchingBrace(after);
            if (closeBrace > 0) {
                return new ScopeInfo(openParen, closeBrace, false, "block");
            }
            return null;
        }
        int stmtEnd = this.findStatementEnd(after, bodyEnd);
        if (stmtEnd > after) {
            return new ScopeInfo(openParen, stmtEnd, false, "block");
        }
        return null;
    }

    int skipWhitespaceAndExcluded(int pos, int limit) {
        int i = Math.max(pos, 0);
        int max = Math.min(limit, this.text.length());
        while (i < max) {
            if (this.isExcluded(i)) {
                ++i;
                continue;
            }
            if (!Character.isWhitespace(this.text.charAt(i))) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    int findStatementEnd(int start, int limit) {
        int max = Math.min(limit, this.text.length());
        int parenDepth = 0;
        int bracketDepth = 0;
        for (int i = start; i < max; ++i) {
            if (this.isExcluded(i)) continue;
            char c = this.text.charAt(i);
            if (c == '(') {
                ++parenDepth;
                continue;
            }
            if (c == ')') {
                parenDepth = Math.max(0, parenDepth - 1);
                continue;
            }
            if (c == '[') {
                ++bracketDepth;
                continue;
            }
            if (c == ']') {
                bracketDepth = Math.max(0, bracketDepth - 1);
                continue;
            }
            if (c == '{') {
                int closeBrace = this.findMatchingBrace(i);
                return closeBrace > 0 ? closeBrace : -1;
            }
            if (c != ';' || parenDepth != 0 || bracketDepth != 0) continue;
            return i + 1;
        }
        return -1;
    }

    int findEnclosingParenStart(int min, int position) {
        int depth = 0;
        for (int i = Math.min(position - 1, this.text.length() - 1); i >= min; --i) {
            if (this.isExcluded(i)) continue;
            char c = this.text.charAt(i);
            if (c == ')') {
                ++depth;
                continue;
            }
            if (c != '(') continue;
            if (depth == 0) {
                return i;
            }
            --depth;
        }
        return -1;
    }

    int findMatchingParen(int openParenIndex, int limit) {
        if (openParenIndex < 0 || openParenIndex >= this.text.length()) {
            return -1;
        }
        int max = Math.min(limit, this.text.length());
        int depth = 0;
        for (int i = openParenIndex; i < max; ++i) {
            if (this.isExcluded(i)) continue;
            char c = this.text.charAt(i);
            if (c == '(') {
                ++depth;
                continue;
            }
            if (c != ')' || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    String readKeywordBefore(int position) {
        int i = position - 1;
        while (i >= 0) {
            if (this.isExcluded(i)) {
                --i;
                continue;
            }
            if (!Character.isWhitespace(this.text.charAt(i))) break;
            --i;
        }
        if (i < 0) {
            return "";
        }
        int end = i + 1;
        while (i >= 0 && Character.isJavaIdentifierPart(this.text.charAt(i))) {
            --i;
        }
        int start = i + 1;
        if (start >= end) {
            return "";
        }
        return this.text.substring(start, end);
    }

    private FieldInfo pickVisibleLocal(List<FieldInfo> candidates, int position) {
        if (candidates == null || candidates.isEmpty()) {
            return null;
        }
        FieldInfo best = null;
        for (FieldInfo f : candidates) {
            if (f == null || !f.isVisibleAt(position) || best != null && f.getDeclarationOffset() <= best.getDeclarationOffset()) continue;
            best = f;
        }
        return best;
    }

    private FieldInfo pickVisibleTopLevelLocal(String name, int position) {
        return this.pickVisibleLocal(this.topLevelLocals.get(name), position);
    }

    void addTopLevelLocal(FieldInfo fieldInfo) {
        if (fieldInfo == null) {
            return;
        }
        this.topLevelLocals.computeIfAbsent(fieldInfo.getName(), k -> new ArrayList()).add(fieldInfo);
    }

    private Collection<FieldInfo> getVisibleTopLevelLocals(int position) {
        ArrayList<FieldInfo> visible = new ArrayList<FieldInfo>();
        for (List<FieldInfo> candidates : this.topLevelLocals.values()) {
            FieldInfo best = this.pickVisibleLocal(candidates, position);
            if (best == null) continue;
            visible.add(best);
        }
        return visible;
    }

    Collection<FieldInfo> getTopLevelLocalCandidates() {
        ArrayList<FieldInfo> all = new ArrayList<FieldInfo>();
        for (List<FieldInfo> fields : this.topLevelLocals.values()) {
            all.addAll(fields);
        }
        return all;
    }

    private void parseLocalVariables() {
        List<MethodInfo> allMethodsAndConstructors = this.getAllMethods();
        allMethodsAndConstructors.addAll(this.getAllConstructors());
        for (MethodInfo method : allMethodsAndConstructors) {
            HashMap<String, List> locals = new HashMap<String, List>();
            this.methodLocals.put(method.getDeclarationOffset(), locals);
            int bodyStart = method.getBodyStart();
            int bodyEnd = method.getBodyEnd();
            if (bodyStart < 0 || bodyEnd <= bodyStart) continue;
            String bodyText = this.text.substring(bodyStart, Math.min(bodyEnd, this.text.length()));
            if (this.isJavaScript()) {
                Pattern varPattern = Pattern.compile("(var|let|const)\\s+(\\w+)(?:\\s*(=))?");
                Matcher m = varPattern.matcher(bodyText);
                while (m.find()) {
                    List existing;
                    TypeInfo inferred;
                    String rest;
                    int afterVar;
                    int absPos = bodyStart + m.start(2);
                    if (this.isExcluded(absPos) || (afterVar = m.end(2)) < bodyText.length() && (rest = bodyText.substring(afterVar)).length() > 0 && Pattern.compile("^\\s+(?:in|of)\\s").matcher(rest).find()) continue;
                    boolean insideInner = false;
                    for (InnerCallableScope scope : this.innerScopes) {
                        if (!scope.containsPosition(absPos)) continue;
                        insideInner = true;
                        break;
                    }
                    if (insideInner) continue;
                    String kind = m.group(1);
                    String varName = m.group(2);
                    String initializer = null;
                    int initializerStart = -1;
                    int initializerEnd = -1;
                    if (m.group(3) != null && (initializerEnd = this.findJsInitializerEnd(bodyText, initializerStart = this.skipSegmentWhitespace(bodyText, m.end(3)), true)) > initializerStart && (initializer = bodyText.substring(initializerStart, initializerEnd).trim()).isEmpty()) {
                        initializer = null;
                    }
                    int absStart = bodyStart + m.start();
                    JSDocInfo jsDoc = this.jsDocParser.extractJSDocBefore(this.text, absStart);
                    TypeInfo typeInfo = null;
                    if (jsDoc != null && jsDoc.hasTypeTag()) {
                        typeInfo = jsDoc.getDeclaredType();
                    }
                    if (typeInfo == null && initializer != null && !initializer.isEmpty() && !TypeInfo.NULL.equals(inferred = this.resolveExpressionType(initializer, bodyStart + initializerStart))) {
                        typeInfo = inferred;
                    }
                    if (typeInfo == null) {
                        typeInfo = TypeInfo.ANY;
                    }
                    int initStart = -1;
                    int initEnd = -1;
                    if (m.group(3) != null) {
                        initStart = bodyStart + m.start(3);
                        initEnd = initializerEnd >= 0 ? bodyStart + initializerEnd : bodyStart + m.end(3);
                    }
                    FieldInfo fieldInfo = FieldInfo.localField(varName, typeInfo, absPos, method, initStart, initEnd, 0);
                    ScopeInfo scopeInfo = "var".equals(kind) ? new ScopeInfo(bodyStart, bodyEnd, false, "method") : this.computeBlockScope(bodyStart, bodyEnd, absPos);
                    fieldInfo.setScopeInfo(scopeInfo);
                    if (jsDoc != null) {
                        fieldInfo.setJSDocInfo(jsDoc);
                    }
                    if ((existing = (List)locals.get(varName)) != null && !existing.isEmpty()) {
                        boolean dup = false;
                        if ("let".equals(kind) || "const".equals(kind)) {
                            for (FieldInfo prev : existing) {
                                ScopeInfo prevScope = prev.getScopeInfo();
                                if (prevScope == null || prevScope.startOffset != scopeInfo.startOffset || prevScope.endOffset != scopeInfo.endOffset) continue;
                                dup = true;
                                break;
                            }
                        }
                        if (dup || this.globalFields.containsKey(varName)) {
                            AssignmentInfo dupError = AssignmentInfo.duplicateDeclaration(varName, absPos, absPos + varName.length(), "Variable '" + varName + "' is already defined in the scope");
                            dupError.setScopeInfo(scopeInfo);
                            this.declarationErrors.add(dupError);
                            continue;
                        }
                        if ("var".equals(kind)) {
                            continue;
                        }
                    } else if (this.globalFields.containsKey(varName)) {
                        AssignmentInfo dupError = AssignmentInfo.duplicateDeclaration(varName, absPos, absPos + varName.length(), "Variable '" + varName + "' is already defined in the scope");
                        dupError.setScopeInfo(scopeInfo);
                        this.declarationErrors.add(dupError);
                        continue;
                    }
                    locals.computeIfAbsent(varName, k -> new ArrayList()).add(fieldInfo);
                    String fKind = kind;
                    MethodInfo fMethod = method;
                    int fBodyStart = bodyStart;
                    int fBodyEnd = bodyEnd;
                    HashMap<String, List> fLocals = locals;
                    int contScanStart = MultiDeclaratorParser.jsDeclaratorScanStart(bodyText, m.end(2), m.group(3) != null, initializerEnd);
                    MultiDeclaratorParser.scanJSContinuationDeclarators(this, bodyText, contScanStart, bodyStart, (cVarName, cAbsNamePos, cInit, cAbsInitStart, cAbsInitEnd) -> {
                        TypeInfo inferred;
                        if (this.isExcluded(cAbsNamePos)) {
                            return;
                        }
                        boolean cInsideInner = false;
                        for (InnerCallableScope sc : this.innerScopes) {
                            if (!sc.containsPosition(cAbsNamePos)) continue;
                            cInsideInner = true;
                            break;
                        }
                        if (cInsideInner) {
                            return;
                        }
                        TypeInfo cType = null;
                        if (cInit != null && !cInit.isEmpty() && !TypeInfo.NULL.equals(inferred = this.resolveExpressionType(cInit, cAbsInitStart))) {
                            cType = inferred;
                        }
                        if (cType == null) {
                            cType = TypeInfo.ANY;
                        }
                        FieldInfo cField = FieldInfo.localField(cVarName, cType, cAbsNamePos, fMethod, cAbsInitStart, cAbsInitEnd, 0);
                        ScopeInfo cScope = "var".equals(fKind) ? new ScopeInfo(fBodyStart, fBodyEnd, false, "method") : this.computeBlockScope(fBodyStart, fBodyEnd, cAbsNamePos);
                        cField.setScopeInfo(cScope);
                        if (fLocals.containsKey(cVarName) || this.globalFields.containsKey(cVarName)) {
                            AssignmentInfo dupErr = AssignmentInfo.duplicateDeclaration(cVarName, cAbsNamePos, cAbsNamePos + cVarName.length(), "Variable '" + cVarName + "' is already defined in the scope");
                            dupErr.setScopeInfo(cScope);
                            this.declarationErrors.add(dupErr);
                            return;
                        }
                        fLocals.computeIfAbsent(cVarName, k -> new ArrayList()).add(cField);
                    });
                }
                continue;
            }
            Matcher m = FIELD_DECL_PATTERN.matcher(ScriptDocument.normalizeGenericNewlines(bodyText));
            while (m.find()) {
                TypeInfo typeInfo;
                int enclosingParen;
                ScopeInfo prevScope;
                int absTypeStart;
                String typeName = m.group(1);
                String varName = m.group(2);
                String delimiter = m.group(3);
                int absPos = bodyStart + m.start(2);
                if (this.isExcluded(absPos) || this.isExcluded(absTypeStart = bodyStart + m.start(1)) || this.hasExcludedRange(absTypeStart, bodyStart + m.end(1))) continue;
                int commaInType = MultiDeclaratorParser.findFirstDepthZeroComma(typeName);
                if (commaInType >= 0) {
                    int enclosingParen1;
                    String realTypeName = MultiDeclaratorParser.extractRealTypeFromGreedyMatch(typeName, commaInType);
                    String firstVar = MultiDeclaratorParser.extractFirstVarFromGreedyMatch(typeName, commaInType);
                    int firstVarAbsPos = MultiDeclaratorParser.findFirstVarPosition(bodyText, m.start(1), typeName, commaInType) + bodyStart;
                    if (realTypeName.equals("return") || realTypeName.equals("if") || realTypeName.equals("while") || realTypeName.equals("for") || realTypeName.equals("switch") || realTypeName.equals("catch") || realTypeName.equals("new") || realTypeName.equals("throw") || (enclosingParen1 = this.findEnclosingParenStart(bodyStart, firstVarAbsPos)) >= 0) continue;
                    int greedyModifiers = this.parseModifiers(realTypeName);
                    TypeInfo greedyTypeInfo = this.resolveType(realTypeName, firstVarAbsPos);
                    FieldInfo firstField = FieldInfo.localField(firstVar, greedyTypeInfo, firstVarAbsPos, method, -1, -1, greedyModifiers);
                    ScopeInfo firstScope = this.computeBlockScope(bodyStart, bodyEnd, firstVarAbsPos);
                    firstField.setScopeInfo(firstScope);
                    List existingFirst = (List)locals.get(firstVar);
                    if (existingFirst != null) {
                        boolean dup = false;
                        for (FieldInfo prev : existingFirst) {
                            prevScope = prev.getScopeInfo();
                            if (prevScope == null || !prevScope.containsPosition(firstVarAbsPos)) continue;
                            dup = true;
                            break;
                        }
                        if (dup || this.globalFields.containsKey(firstVar)) {
                            AssignmentInfo dupErr = AssignmentInfo.duplicateDeclaration(firstVar, firstVarAbsPos, firstVarAbsPos + firstVar.length(), "Variable '" + firstVar + "' is already defined in the scope");
                            dupErr.setScopeInfo(firstScope);
                            this.declarationErrors.add(dupErr);
                        } else {
                            locals.computeIfAbsent(firstVar, k -> new ArrayList()).add(firstField);
                        }
                    } else if (this.globalFields.containsKey(firstVar)) {
                        AssignmentInfo dupErr = AssignmentInfo.duplicateDeclaration(firstVar, firstVarAbsPos, firstVarAbsPos + firstVar.length(), "Variable '" + firstVar + "' is already defined in the scope");
                        dupErr.setScopeInfo(firstScope);
                        this.declarationErrors.add(dupErr);
                    } else {
                        locals.computeIfAbsent(firstVar, k -> new ArrayList()).add(firstField);
                    }
                    TypeInfo greedySharedType = greedyTypeInfo;
                    int greedySharedMod = greedyModifiers;
                    MethodInfo fMethod3 = method;
                    HashMap<String, List> fLocals3 = locals;
                    int greedyScanStart = m.start(1) + commaInType;
                    MultiDeclaratorParser.scanJavaContinuationDeclarators(bodyText, greedyScanStart, bodyStart, (cVarName, cAbsNamePos, cAbsInitStart, cAbsInitEnd) -> {
                        if (this.isExcluded(cAbsNamePos)) {
                            return;
                        }
                        if (this.findEnclosingParenStart(bodyStart, cAbsNamePos) >= 0) {
                            return;
                        }
                        FieldInfo cField = FieldInfo.localField(cVarName, greedySharedType, cAbsNamePos, fMethod3, cAbsInitStart, cAbsInitEnd, greedySharedMod);
                        ScopeInfo cScope = this.computeBlockScope(bodyStart, bodyEnd, cAbsNamePos);
                        cField.setScopeInfo(cScope);
                        if (fLocals3.containsKey(cVarName) || this.globalFields.containsKey(cVarName)) {
                            AssignmentInfo dupErr = AssignmentInfo.duplicateDeclaration(cVarName, cAbsNamePos, cAbsNamePos + cVarName.length(), "Variable '" + cVarName + "' is already defined in the scope");
                            dupErr.setScopeInfo(cScope);
                            this.declarationErrors.add(dupErr);
                            return;
                        }
                        fLocals3.computeIfAbsent(cVarName, k -> new ArrayList()).add(cField);
                    });
                    continue;
                }
                if (typeName.equals("return") || typeName.equals("if") || typeName.equals("while") || typeName.equals("for") || typeName.equals("switch") || typeName.equals("catch") || typeName.equals("new") || typeName.equals("throw") || (enclosingParen = this.findEnclosingParenStart(bodyStart, absPos)) >= 0 && "for".equals(this.readKeywordBefore(enclosingParen))) continue;
                int modifiers = this.parseModifiers(typeName);
                if ((typeName.equals("var") || typeName.equals("let") || typeName.equals("const")) && delimiter.equals("=")) {
                    int rhsStart = bodyStart + m.end();
                    typeInfo = this.inferTypeFromExpression(rhsStart);
                } else {
                    typeInfo = this.resolveType(typeName, bodyStart + m.start(2));
                }
                int initStart = -1;
                int initEnd = -1;
                if ("=".equals(delimiter)) {
                    initStart = bodyStart + m.start(3);
                    int depth = 0;
                    int angleDepth = 0;
                    for (int searchPos = bodyStart + m.end(3); searchPos < this.text.length(); ++searchPos) {
                        char c = this.text.charAt(searchPos);
                        if (c == '(' || c == '[' || c == '{') {
                            ++depth;
                            continue;
                        }
                        if (c == ')' || c == ']' || c == '}') {
                            --depth;
                            continue;
                        }
                        if (c == '<') {
                            ++angleDepth;
                            continue;
                        }
                        if (c == '>') {
                            --angleDepth;
                            continue;
                        }
                        if (c != ';' && c != ',' || depth != 0 || angleDepth != 0) continue;
                        initEnd = searchPos;
                        break;
                    }
                }
                int declPos = bodyStart + m.start(2);
                FieldInfo fieldInfo = FieldInfo.localField(varName, typeInfo, declPos, method, initStart, initEnd, modifiers);
                ScopeInfo scopeInfo = this.computeBlockScope(bodyStart, bodyEnd, declPos);
                fieldInfo.setScopeInfo(scopeInfo);
                List existing = (List)locals.get(varName);
                if (existing != null) {
                    boolean dup = false;
                    for (FieldInfo prev : existing) {
                        prevScope = prev.getScopeInfo();
                        if (prevScope == null || !prevScope.containsPosition(declPos)) continue;
                        dup = true;
                        break;
                    }
                    if (dup || this.globalFields.containsKey(varName)) {
                        AssignmentInfo dupError = AssignmentInfo.duplicateDeclaration(varName, declPos, declPos + varName.length(), "Variable '" + varName + "' is already defined in the scope");
                        dupError.setScopeInfo(scopeInfo);
                        this.declarationErrors.add(dupError);
                        continue;
                    }
                } else if (this.globalFields.containsKey(varName)) {
                    AssignmentInfo dupError = AssignmentInfo.duplicateDeclaration(varName, declPos, declPos + varName.length(), "Variable '" + varName + "' is already defined in the scope");
                    dupError.setScopeInfo(scopeInfo);
                    this.declarationErrors.add(dupError);
                    continue;
                }
                locals.computeIfAbsent(varName, k -> new ArrayList()).add(fieldInfo);
                TypeInfo sharedType = typeInfo;
                int sharedModifiers = modifiers;
                MethodInfo fMethod2 = method;
                HashMap<String, List> fLocals2 = locals;
                int javaScanStart = MultiDeclaratorParser.javaDeclaratorScanStart(bodyText, delimiter, m.end(3), initEnd >= 0 ? initEnd - bodyStart : -1);
                if (javaScanStart < 0) continue;
                MultiDeclaratorParser.scanJavaContinuationDeclarators(bodyText, javaScanStart, bodyStart, (cVarName, cAbsNamePos, cAbsInitStart, cAbsInitEnd) -> {
                    if (this.isExcluded(cAbsNamePos)) {
                        return;
                    }
                    FieldInfo cField = FieldInfo.localField(cVarName, sharedType, cAbsNamePos, fMethod2, cAbsInitStart, cAbsInitEnd, sharedModifiers);
                    ScopeInfo cScope = this.computeBlockScope(bodyStart, bodyEnd, cAbsNamePos);
                    cField.setScopeInfo(cScope);
                    if (fLocals2.containsKey(cVarName) || this.globalFields.containsKey(cVarName)) {
                        AssignmentInfo dupErr = AssignmentInfo.duplicateDeclaration(cVarName, cAbsNamePos, cAbsNamePos + cVarName.length(), "Variable '" + cVarName + "' is already defined in the scope");
                        dupErr.setScopeInfo(cScope);
                        this.declarationErrors.add(dupErr);
                        return;
                    }
                    fLocals2.computeIfAbsent(cVarName, k -> new ArrayList()).add(cField);
                });
            }
        }
        for (InnerCallableScope scope : this.innerScopes) {
            this.parseLocalVariablesInScope(scope);
        }
        new LoopVariableParser(this, this.text).parse(this.methodLocals);
    }

    private void parseLocalVariablesInScope(InnerCallableScope scope) {
        int start = scope.getBodyStart();
        int end = scope.getBodyEnd();
        if (start < 0 || end <= start) {
            return;
        }
        if (this.isJavaScript()) {
            this.parseJSLocalsInRange(start, end, scope);
        } else {
            this.parseJavaLocalsInRange(start, end, scope);
        }
    }

    private void parseJavaLocalsInRange(int start, int end, InnerCallableScope scope) {
        String rangeText = this.text.substring(start, Math.min(end, this.text.length()));
        Matcher m = FIELD_DECL_PATTERN.matcher(ScriptDocument.normalizeGenericNewlines(rangeText));
        while (m.find()) {
            int declPos = start + m.start();
            if (declPos < start || declPos >= end || this.isExcluded(declPos) || this.hasExcludedRange(start + m.start(1), start + m.end(1))) continue;
            String typeNameRaw = m.group(1);
            String varName = m.group(2);
            String delimiter = m.group(3);
            int commaInType = MultiDeclaratorParser.findFirstDepthZeroComma(typeNameRaw);
            if (commaInType >= 0) {
                String realTypeNameRaw = MultiDeclaratorParser.extractRealTypeFromGreedyMatch(typeNameRaw, commaInType);
                String firstVar = MultiDeclaratorParser.extractFirstVarFromGreedyMatch(typeNameRaw, commaInType);
                int firstVarAbsPos = MultiDeclaratorParser.findFirstVarPosition(rangeText, m.start(1), typeNameRaw, commaInType) + start;
                if (realTypeNameRaw.equals("return") || realTypeNameRaw.equals("if") || realTypeNameRaw.equals("while") || realTypeNameRaw.equals("for") || realTypeNameRaw.equals("switch") || realTypeNameRaw.equals("catch") || realTypeNameRaw.equals("new") || realTypeNameRaw.equals("throw")) continue;
                String greedyTypeName = this.stripModifiers(realTypeNameRaw);
                int greedyModifiers = this.parseModifiers(realTypeNameRaw);
                TypeInfo greedyVarType = this.resolveType(greedyTypeName, firstVarAbsPos);
                FieldInfo firstField = FieldInfo.localField(firstVar, greedyVarType, firstVarAbsPos, null, -1, -1, greedyModifiers);
                scope.addLocal(firstVar, firstField);
                TypeInfo greedySharedType = greedyVarType;
                int greedySharedMod = greedyModifiers;
                InnerCallableScope fScope3 = scope;
                int greedyScanStart = m.start(1) + commaInType;
                MultiDeclaratorParser.scanJavaContinuationDeclarators(rangeText, greedyScanStart, start, (cVarName, cAbsNamePos, cAbsInitStart, cAbsInitEnd) -> {
                    if (this.isExcluded(cAbsNamePos)) {
                        return;
                    }
                    FieldInfo cField = FieldInfo.localField(cVarName, greedySharedType, cAbsNamePos, null, cAbsInitStart, cAbsInitEnd, greedySharedMod);
                    fScope3.addLocal(cVarName, cField);
                });
                continue;
            }
            if (typeNameRaw.equals("return") || typeNameRaw.equals("if") || typeNameRaw.equals("while") || typeNameRaw.equals("for") || typeNameRaw.equals("switch") || typeNameRaw.equals("catch") || typeNameRaw.equals("new") || typeNameRaw.equals("throw")) continue;
            String typeName = this.stripModifiers(typeNameRaw);
            int modifiers = this.parseModifiers(typeNameRaw);
            TypeInfo varType = null;
            if (!(typeName.equals("var") || typeName.equals("let") || typeName.equals("const"))) {
                varType = this.resolveType(typeName);
            } else if (delimiter.equals("=")) {
                int rhsStart = start + m.end();
                varType = this.inferTypeFromExpression(rhsStart);
            }
            int initStart = -1;
            int initEnd = -1;
            if ("=".equals(delimiter)) {
                initStart = start + m.start(3);
                int depth = 0;
                int angleDepth = 0;
                for (int searchPos = start + m.end(3); searchPos < end; ++searchPos) {
                    char c = this.text.charAt(searchPos);
                    if (c == '(' || c == '[' || c == '{') {
                        ++depth;
                        continue;
                    }
                    if (c == ')' || c == ']' || c == '}') {
                        --depth;
                        continue;
                    }
                    if (c == '<') {
                        ++angleDepth;
                        continue;
                    }
                    if (c == '>') {
                        --angleDepth;
                        continue;
                    }
                    if (c != ';' && c != ',' || depth != 0 || angleDepth != 0) continue;
                    initEnd = searchPos;
                    break;
                }
            }
            int absPos = start + m.start(2);
            FieldInfo localVar = FieldInfo.localField(varName, varType, absPos, null, initStart, initEnd, modifiers);
            scope.addLocal(varName, localVar);
            TypeInfo sharedType = varType;
            int sharedModifiers = modifiers;
            InnerCallableScope fScope2 = scope;
            int javaScanStart = MultiDeclaratorParser.javaDeclaratorScanStart(rangeText, delimiter, m.end(3), initEnd >= 0 ? initEnd - start : -1);
            if (javaScanStart < 0) continue;
            MultiDeclaratorParser.scanJavaContinuationDeclarators(rangeText, javaScanStart, start, (cVarName, cAbsNamePos, cAbsInitStart, cAbsInitEnd) -> {
                if (this.isExcluded(cAbsNamePos)) {
                    return;
                }
                FieldInfo cField = FieldInfo.localField(cVarName, sharedType, cAbsNamePos, null, cAbsInitStart, cAbsInitEnd, sharedModifiers);
                fScope2.addLocal(cVarName, cField);
            });
        }
    }

    private void parseJSLocalsInRange(int start, int end, InnerCallableScope scope) {
        String rangeText = this.text.substring(start, Math.min(end, this.text.length()));
        Pattern varPattern = Pattern.compile("(?:var|let|const)\\s+(\\w+)(?:\\s*(=))?");
        Matcher m = varPattern.matcher(rangeText);
        while (m.find()) {
            TypeInfo inferred;
            String rest;
            int afterVar;
            int declPos = start + m.start();
            if (declPos < start || declPos >= end || this.isExcluded(declPos) || (afterVar = m.end(1)) < rangeText.length() && (rest = rangeText.substring(afterVar)).length() > 0 && Pattern.compile("^\\s+(?:in|of)\\s").matcher(rest).find()) continue;
            String varName = m.group(1);
            String initializer = null;
            int initializerStart = -1;
            int initializerEnd = -1;
            if (m.group(2) != null && (initializerEnd = this.findJsInitializerEnd(rangeText, initializerStart = this.skipSegmentWhitespace(rangeText, m.end(2)), true)) > initializerStart && (initializer = rangeText.substring(initializerStart, initializerEnd).trim()).isEmpty()) {
                initializer = null;
            }
            JSDocInfo jsDoc = this.jsDocParser.extractJSDocBefore(this.text, declPos);
            TypeInfo varType = null;
            if (jsDoc != null && jsDoc.hasTypeTag()) {
                varType = jsDoc.getDeclaredType();
            }
            if (varType == null && initializer != null && !initializer.isEmpty() && !TypeInfo.NULL.equals(inferred = this.resolveExpressionType(initializer, start + initializerStart))) {
                varType = inferred;
            }
            if (varType == null) {
                varType = TypeInfo.ANY;
            }
            int initStart = -1;
            int initEnd = -1;
            if (m.group(2) != null) {
                initStart = start + m.start(2);
                initEnd = initializerEnd >= 0 ? start + initializerEnd : start + m.end(2);
            }
            int absPos = start + m.start(1);
            FieldInfo localVar = FieldInfo.localField(varName, varType, absPos, null, initStart, initEnd, 0);
            if (jsDoc != null) {
                localVar.setJSDocInfo(jsDoc);
            }
            scope.addLocal(varName, localVar);
            InnerCallableScope fScope = scope;
            int contScanStart = MultiDeclaratorParser.jsDeclaratorScanStart(rangeText, m.end(1), m.group(2) != null, initializerEnd);
            MultiDeclaratorParser.scanJSContinuationDeclarators(this, rangeText, contScanStart, start, (cVarName, cAbsNamePos, cInit, cAbsInitStart, cAbsInitEnd) -> {
                TypeInfo inferred;
                if (this.isExcluded(cAbsNamePos)) {
                    return;
                }
                TypeInfo cType = null;
                if (cInit != null && !cInit.isEmpty() && !TypeInfo.NULL.equals(inferred = this.resolveExpressionType(cInit, cAbsInitStart))) {
                    cType = inferred;
                }
                if (cType == null) {
                    cType = TypeInfo.ANY;
                }
                FieldInfo cField = FieldInfo.localField(cVarName, cType, cAbsNamePos, null, cAbsInitStart, cAbsInitEnd, 0);
                fScope.addLocal(cVarName, cField);
            });
        }
    }

    int skipSegmentWhitespace(String source, int pos) {
        while (pos < source.length() && Character.isWhitespace(source.charAt(pos))) {
            ++pos;
        }
        return pos;
    }

    int findJsInitializerEnd(String source, int rhsStart) {
        return this.findJsInitializerEnd(source, rhsStart, false);
    }

    int findJsInitializerEnd(String source, int rhsStart, boolean stopAtTopLevelComma) {
        if (rhsStart < 0 || rhsStart >= source.length()) {
            return rhsStart;
        }
        int pos = rhsStart;
        int lineExprStart = rhsStart;
        int parenDepth = 0;
        int bracketDepth = 0;
        int braceDepth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        boolean inLineComment = false;
        boolean inBlockComment = false;
        while (pos < source.length()) {
            char next;
            char c = source.charAt(pos);
            char c2 = next = pos + 1 < source.length() ? source.charAt(pos + 1) : (char)'\u0000';
            if (inLineComment) {
                if (c == '\n' || c == '\r') {
                    inLineComment = false;
                } else {
                    ++pos;
                    continue;
                }
            }
            if (inBlockComment) {
                if (c == '*' && next == '/') {
                    inBlockComment = false;
                    pos += 2;
                    continue;
                }
                ++pos;
                continue;
            }
            if (inString) {
                if (c == '\\') {
                    pos += pos + 1 < source.length() ? 2 : 1;
                    continue;
                }
                if (c == stringChar) {
                    inString = false;
                }
                ++pos;
                continue;
            }
            if (c == '/' && next == '/') {
                inLineComment = true;
                pos += 2;
                continue;
            }
            if (c == '/' && next == '*') {
                inBlockComment = true;
                pos += 2;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringChar = c;
                ++pos;
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
            if (c == ';' && parenDepth == 0 && bracketDepth == 0 && braceDepth == 0) {
                return pos;
            }
            if (stopAtTopLevelComma && c == ',' && parenDepth == 0 && bracketDepth == 0 && braceDepth == 0) {
                return pos;
            }
            if (c == '\n' || c == '\r') {
                char n;
                int nextLineEnd;
                int currentLineEnd;
                int lineBreakPos = pos;
                int nextPos = pos + 1;
                if (c == '\r' && nextPos < source.length() && source.charAt(nextPos) == '\n') {
                    ++nextPos;
                }
                if (parenDepth != 0 || bracketDepth != 0 || braceDepth != 0) {
                    pos = nextPos;
                    lineExprStart = nextPos;
                    continue;
                }
                for (currentLineEnd = lineBreakPos; currentLineEnd > lineExprStart && Character.isWhitespace(source.charAt(currentLineEnd - 1)); --currentLineEnd) {
                }
                String currentLineExpr = source.substring(lineExprStart, currentLineEnd);
                int nextExprStart = nextPos;
                while (nextExprStart < source.length()) {
                    char n2 = source.charAt(nextExprStart);
                    if (n2 == ' ' || n2 == '\t') {
                        ++nextExprStart;
                        continue;
                    }
                    if (n2 == ';') {
                        return nextExprStart;
                    }
                    if (n2 != '\n' && n2 != '\r') break;
                    if (n2 != '\r' || ++nextExprStart >= source.length() || source.charAt(nextExprStart) != '\n') continue;
                    ++nextExprStart;
                }
                if (nextExprStart >= source.length()) {
                    return source.length();
                }
                for (nextLineEnd = nextExprStart; nextLineEnd < source.length() && (n = source.charAt(nextLineEnd)) != '\n' && n != '\r' && n != ';'; ++nextLineEnd) {
                }
                String nextLineExpr = source.substring(nextExprStart, nextLineEnd).trim();
                if (!this.shouldContinueJsInitializer(currentLineExpr, nextLineExpr)) {
                    return lineBreakPos;
                }
                pos = nextExprStart;
                lineExprStart = nextExprStart;
                continue;
            }
            ++pos;
        }
        return source.length();
    }

    private boolean shouldContinueJsInitializer(String currentLineExpr, String nextLineExpr) {
        if (nextLineExpr.isEmpty()) {
            return false;
        }
        if (nextLineExpr.startsWith(".") || nextLineExpr.startsWith("?.") || nextLineExpr.startsWith("[")) {
            return true;
        }
        String current = currentLineExpr.trim();
        if (current.isEmpty()) {
            return false;
        }
        char tail = current.charAt(current.length() - 1);
        return tail == '+' || tail == '-' || tail == '*' || tail == '/' || tail == '%' || tail == '&' || tail == '|' || tail == '^' || tail == '!' || tail == '=' || tail == '<' || tail == '>' || tail == '?' || tail == ':' || tail == ',' || tail == '.' || tail == '(' || tail == '[' || tail == '{';
    }

    private TypeInfo inferTypeFromExpression(int position) {
        while (position < this.text.length() && Character.isWhitespace(this.text.charAt(position))) {
            ++position;
        }
        if (position >= this.text.length()) {
            return null;
        }
        if (this.text.startsWith("new ", position)) {
            position += 4;
            while (position < this.text.length() && Character.isWhitespace(this.text.charAt(position))) {
                ++position;
            }
            int typeStart = position;
            while (position < this.text.length() && Character.isJavaIdentifierPart(this.text.charAt(position))) {
                ++position;
            }
            if (position > typeStart) {
                String typeName = this.text.substring(typeStart, position);
                return this.resolveType(typeName, typeStart);
            }
            return null;
        }
        if (position < this.text.length() && (this.text.charAt(position) == '\"' || this.text.charAt(position) == '\'')) {
            return this.resolveType("String");
        }
        if (position < this.text.length() && Character.isDigit(this.text.charAt(position))) {
            int numEnd;
            boolean hasDecimal = false;
            for (numEnd = position; numEnd < this.text.length() && (Character.isDigit(this.text.charAt(numEnd)) || this.text.charAt(numEnd) == '.' || this.text.charAt(numEnd) == 'f' || this.text.charAt(numEnd) == 'd' || this.text.charAt(numEnd) == 'F' || this.text.charAt(numEnd) == 'D' || this.text.charAt(numEnd) == 'L' || this.text.charAt(numEnd) == 'l'); ++numEnd) {
                if (this.text.charAt(numEnd) != '.') continue;
                hasDecimal = true;
            }
            String num = this.text.substring(position, numEnd).toLowerCase();
            if (num.endsWith("f")) {
                return this.resolveType("float");
            }
            if (num.endsWith("d") || hasDecimal) {
                return this.resolveType("double");
            }
            if (num.endsWith("l")) {
                return this.resolveType("long");
            }
            return this.resolveType("int");
        }
        if (this.text.startsWith("true", position) || this.text.startsWith("false", position)) {
            return this.resolveType("boolean");
        }
        if (this.text.startsWith("null", position)) {
            return null;
        }
        if (Character.isJavaIdentifierStart(this.text.charAt(position))) {
            int identStart = position;
            while (position < this.text.length() && Character.isJavaIdentifierPart(this.text.charAt(position))) {
                ++position;
            }
            String ident = this.text.substring(identStart, position);
            while (position < this.text.length() && Character.isWhitespace(this.text.charAt(position))) {
                ++position;
            }
            if (position < this.text.length() && this.text.charAt(position) == '.') {
                return this.inferChainType(ident, identStart, position);
            }
            if (position < this.text.length() && this.text.charAt(position) == '(') {
                if (this.isScriptMethod(ident)) {
                    MethodInfo methodInfo = this.getScriptMethodInfo(ident);
                    return methodInfo != null ? methodInfo.getReturnType() : null;
                }
                return null;
            }
            FieldInfo varInfo = this.resolveVariable(ident, identStart);
            return varInfo != null ? varInfo.getTypeInfo() : null;
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private TypeInfo inferChainType(String firstIdent, int identStart, int dotPosition) {
        TypeInfo currentType = null;
        if ("this".equals(firstIdent)) {
            currentType = this.resolveThisType(identStart);
        }
        TypeInfo typeCheck = this.resolveType(firstIdent, identStart);
        if (currentType == null && typeCheck != null && typeCheck.isResolved()) {
            currentType = typeCheck;
        } else if (currentType == null) {
            FieldInfo varInfo = this.resolveVariable(firstIdent, identStart);
            if (varInfo == null) return null;
            TypeInfo typeInfo = varInfo.getTypeInfo();
            currentType = typeInfo;
        }
        if (currentType == null) return null;
        if (!currentType.isResolved()) {
            return null;
        }
        int pos = dotPosition;
        block0: while (pos < this.text.length()) {
            if (this.text.charAt(pos) != '.') return currentType;
            ++pos;
            while (pos < this.text.length() && Character.isWhitespace(this.text.charAt(pos))) {
                ++pos;
            }
            if (pos >= this.text.length()) return currentType;
            if (!Character.isJavaIdentifierStart(this.text.charAt(pos))) return currentType;
            int segStart = pos;
            while (pos < this.text.length() && Character.isJavaIdentifierPart(this.text.charAt(pos))) {
                ++pos;
            }
            String segment = this.text.substring(segStart, pos);
            while (pos < this.text.length() && Character.isWhitespace(this.text.charAt(pos))) {
                ++pos;
            }
            if (pos < this.text.length() && this.text.charAt(pos) == '(') {
                if (!currentType.hasMethod(segment)) return null;
                int closeParen = this.findMatchingParen(pos);
                if (closeParen < 0) {
                    return null;
                }
                String argsText = this.text.substring(pos + 1, closeParen).trim();
                TypeInfo[] argTypes = this.parseArgumentTypes(argsText, pos + 1);
                SyntheticType syntheticType = null;
                if (this.isJavaScript() && this.typeResolver.isSyntheticType(firstIdent)) {
                    syntheticType = this.typeResolver.getSyntheticType(firstIdent);
                }
                if (syntheticType != null && syntheticType.hasMethod(segment)) {
                    SyntheticMethod synMethod = syntheticType.getMethod(segment);
                    if (synMethod != null) {
                        String[] strArgs = TypeResolver.parseStringArguments(argsText);
                        TypeInfo dynamicType = synMethod.resolveReturnType(strArgs);
                        currentType = dynamicType != null ? dynamicType : synMethod.getReturnTypeInfo();
                    }
                } else {
                    MethodInfo methodInfo = currentType.getBestMethodOverload(segment, argTypes);
                    currentType = methodInfo != null ? methodInfo.getReturnType() : null;
                }
                pos = closeParen + 1;
            } else {
                if (!currentType.hasField(segment)) return null;
                FieldInfo fieldInfo = currentType.getFieldInfo(segment);
                if (fieldInfo == null) return null;
                TypeInfo typeInfo = fieldInfo.getTypeInfo();
                currentType = typeInfo;
            }
            if (currentType == null) return null;
            if (!currentType.isResolved()) {
                return null;
            }
            while (true) {
                if (pos >= this.text.length() || !Character.isWhitespace(this.text.charAt(pos))) continue block0;
                ++pos;
            }
            break;
        }
        return currentType;
    }

    private void parseInnerCallableScopes() {
        if (this.isJavaScript()) {
            this.parseJSFunctionExpressions();
            this.parseJSArrowFunctions();
            this.parseJSShorthandMethods();
        } else {
            this.parseJavaLambdas();
        }
        this.innerScopes.sort((a, b) -> Integer.compare(a.getHeaderStart(), b.getHeaderStart()));
        this.setupScopeParents();
    }

    private void parseJavaLambdas() {
        String arrowPattern = "->";
        int pos = 0;
        while ((pos = this.text.indexOf(arrowPattern, pos)) >= 0) {
            int bodyEnd;
            int bodyStart;
            if (this.isExcluded(pos)) {
                pos += 2;
                continue;
            }
            int arrowPos = pos;
            int headerStart = this.findLambdaHeaderStart(arrowPos);
            if (headerStart < 0) {
                pos += 2;
                continue;
            }
            for (bodyStart = arrowPos + 2; bodyStart < this.text.length() && Character.isWhitespace(this.text.charAt(bodyStart)); ++bodyStart) {
            }
            if (bodyStart >= this.text.length()) {
                pos += 2;
                continue;
            }
            bodyEnd = this.text.charAt(bodyStart) == '{' ? ((bodyEnd = this.findMatchingBrace(bodyStart)) < 0 ? this.text.length() : ++bodyEnd) : this.findLambdaExpressionEnd(bodyStart);
            InnerCallableScope scope = new InnerCallableScope(InnerCallableScope.Kind.JAVA_LAMBDA, headerStart, arrowPos + 2, bodyStart, bodyEnd);
            this.parseLambdaParameters(scope, headerStart, arrowPos);
            this.innerScopes.add(scope);
            pos = bodyEnd;
        }
    }

    private int findLambdaHeaderStart(int arrowPos) {
        int pos;
        for (pos = arrowPos - 1; pos >= 0 && Character.isWhitespace(this.text.charAt(pos)); --pos) {
        }
        if (pos < 0) {
            return -1;
        }
        if (this.text.charAt(pos) == ')') {
            int depth = 1;
            --pos;
            while (pos >= 0 && depth > 0) {
                if (this.isExcluded(pos)) {
                    --pos;
                    continue;
                }
                char c = this.text.charAt(pos);
                if (c == ')') {
                    ++depth;
                } else if (c == '(') {
                    --depth;
                }
                --pos;
            }
            return pos + 1;
        }
        if (Character.isJavaIdentifierPart(this.text.charAt(pos))) {
            while (pos >= 0 && Character.isJavaIdentifierPart(this.text.charAt(pos))) {
                --pos;
            }
            return pos + 1;
        }
        return -1;
    }

    private int findLambdaExpressionEnd(int start) {
        int depth = 0;
        int angleDepth = 0;
        int pos = start;
        boolean inString = false;
        char stringChar = '\u0000';
        while (pos < this.text.length()) {
            char c = this.text.charAt(pos);
            if (!(inString || c != '\"' && c != '\'')) {
                inString = true;
                stringChar = c;
                ++pos;
                continue;
            }
            if (inString) {
                if (c == stringChar && (pos == 0 || this.text.charAt(pos - 1) != '\\')) {
                    inString = false;
                }
                ++pos;
                continue;
            }
            if (c == '(' || c == '[' || c == '{') {
                ++depth;
            } else if (c == ')' || c == ']' || c == '}') {
                if (depth == 0) {
                    return pos;
                }
                --depth;
            } else if (c == '<') {
                ++angleDepth;
            } else if (c == '>') {
                angleDepth = Math.max(0, angleDepth - 1);
            } else if ((c == ';' || c == ',') && depth == 0 && angleDepth == 0) {
                return pos;
            }
            ++pos;
        }
        return pos;
    }

    private void parseLambdaParameters(InnerCallableScope scope, int headerStart, int arrowPos) {
        String headerText = this.text.substring(headerStart, arrowPos).trim();
        if (headerText.startsWith("(") && headerText.endsWith(")")) {
            String paramsText = headerText.substring(1, headerText.length() - 1).trim();
            if (!paramsText.isEmpty()) {
                String[] params = paramsText.split(",");
                int offset = headerStart + 1;
                for (String param : params) {
                    String paramName;
                    int nextClose;
                    int nextComma;
                    int endBound;
                    if ((param = param.trim()).isEmpty()) continue;
                    int paramOffset = this.text.indexOf(param, offset);
                    if (paramOffset < 0) {
                        paramOffset = offset;
                    }
                    if (paramOffset >= (endBound = Math.min((nextComma = this.text.indexOf(44, offset)) >= 0 ? nextComma : Integer.MAX_VALUE, (nextClose = this.text.indexOf(41, offset)) >= 0 ? nextClose : Integer.MAX_VALUE))) {
                        paramOffset = offset;
                    }
                    String[] parts = param.split("\\s+");
                    TypeInfo paramType = null;
                    if (parts.length >= 2) {
                        paramName = parts[parts.length - 1];
                        String typeName = parts[parts.length - 2];
                        boolean isVarArg = typeName.endsWith("...");
                        if (isVarArg) {
                            typeName = typeName.substring(0, typeName.length() - 3);
                        }
                        paramType = this.resolveType(typeName);
                        int nameOffset = paramOffset + param.lastIndexOf(paramName);
                        if (nameOffset < paramOffset || nameOffset >= endBound) {
                            nameOffset = paramOffset + param.length() - paramName.length();
                        }
                        FieldInfo paramInfo = FieldInfo.parameter(paramName, paramType, nameOffset, null);
                        paramInfo.setVarArg(isVarArg);
                        scope.addParameter(paramInfo);
                    } else {
                        paramName = parts[0];
                        int nameOffset = paramOffset;
                        FieldInfo paramInfo = FieldInfo.parameter(paramName, paramType, nameOffset, null);
                        scope.addParameter(paramInfo);
                    }
                    offset = Math.max(paramOffset + param.length(), offset + 1);
                }
            }
        } else {
            String paramName = headerText;
            int nameOffset = this.text.indexOf(paramName, headerStart);
            if (nameOffset < 0 || nameOffset >= arrowPos) {
                nameOffset = headerStart;
            }
            FieldInfo paramInfo = FieldInfo.parameter(paramName, null, nameOffset, null);
            scope.addParameter(paramInfo);
        }
    }

    private void parseJSFunctionExpressions() {
        Pattern funcExprPattern = Pattern.compile("function\\s*(?:\\w+)?\\s*\\(([^)]*)\\)\\s*\\{");
        Matcher m = funcExprPattern.matcher(this.text);
        while (m.find()) {
            int start = m.start();
            if (this.isExcluded(start) || this.isFunctionDeclaration(start)) continue;
            int headerStart = start;
            int headerEnd = m.end() - 1;
            int bodyStart = m.end() - 1;
            int bodyEnd = this.findMatchingBrace(bodyStart);
            bodyEnd = bodyEnd < 0 ? this.text.length() : ++bodyEnd;
            InnerCallableScope scope = new InnerCallableScope(InnerCallableScope.Kind.JS_FUNCTION_EXPR, headerStart, headerEnd, bodyStart, bodyEnd);
            String paramsText = m.group(1).trim();
            if (!paramsText.isEmpty()) {
                String[] params = paramsText.split(",");
                int offset = m.start(1);
                for (String param : params) {
                    if ((param = param.trim()).isEmpty()) continue;
                    int nameOffset = this.text.indexOf(param, offset);
                    if (nameOffset < 0) {
                        nameOffset = offset;
                    }
                    FieldInfo paramInfo = FieldInfo.parameter(param, TypeInfo.ANY, nameOffset, null);
                    scope.addParameter(paramInfo);
                    offset = nameOffset + param.length();
                }
            }
            this.innerScopes.add(scope);
        }
    }

    private void parseJSArrowFunctions() {
        String arrowToken = "=>";
        int pos = 0;
        while ((pos = this.text.indexOf(arrowToken, pos)) >= 0) {
            int bodyEnd;
            int bodyStart;
            if (this.isExcluded(pos)) {
                pos += 2;
                continue;
            }
            int arrowPos = pos;
            int headerStart = this.findArrowFunctionHeaderStart(arrowPos);
            if (headerStart < 0) {
                pos += 2;
                continue;
            }
            for (bodyStart = arrowPos + 2; bodyStart < this.text.length() && Character.isWhitespace(this.text.charAt(bodyStart)); ++bodyStart) {
            }
            if (bodyStart >= this.text.length()) {
                pos += 2;
                continue;
            }
            bodyEnd = this.text.charAt(bodyStart) == '{' ? ((bodyEnd = this.findMatchingBrace(bodyStart)) < 0 ? this.text.length() : ++bodyEnd) : this.findLambdaExpressionEnd(bodyStart);
            InnerCallableScope scope = new InnerCallableScope(InnerCallableScope.Kind.JS_ARROW_FUNC, headerStart, arrowPos + 2, bodyStart, bodyEnd);
            this.parseArrowFunctionParameters(scope, headerStart, arrowPos);
            this.innerScopes.add(scope);
            pos = bodyEnd;
        }
    }

    private int findArrowFunctionHeaderStart(int arrowPos) {
        int pos;
        for (pos = arrowPos - 1; pos >= 0 && Character.isWhitespace(this.text.charAt(pos)); --pos) {
        }
        if (pos < 0) {
            return -1;
        }
        if (this.text.charAt(pos) == ')') {
            int depth = 1;
            --pos;
            while (pos >= 0 && depth > 0) {
                if (this.isExcluded(pos)) {
                    --pos;
                    continue;
                }
                char c = this.text.charAt(pos);
                if (c == ')') {
                    ++depth;
                } else if (c == '(') {
                    --depth;
                }
                --pos;
            }
            return pos + 1;
        }
        if (Character.isJavaIdentifierPart(this.text.charAt(pos))) {
            while (pos >= 0 && Character.isJavaIdentifierPart(this.text.charAt(pos))) {
                --pos;
            }
            return pos + 1;
        }
        return -1;
    }

    private void parseArrowFunctionParameters(InnerCallableScope scope, int headerStart, int arrowPos) {
        String headerText = this.text.substring(headerStart, arrowPos).trim();
        if (headerText.startsWith("(") && headerText.endsWith(")")) {
            String paramsText = headerText.substring(1, headerText.length() - 1).trim();
            if (!paramsText.isEmpty()) {
                String[] params = paramsText.split(",");
                int offset = headerStart + 1;
                for (String param : params) {
                    if ((param = param.trim()).isEmpty()) continue;
                    int nameOffset = this.text.indexOf(param, offset);
                    if (nameOffset < 0) {
                        nameOffset = offset;
                    }
                    FieldInfo paramInfo = FieldInfo.parameter(param, TypeInfo.ANY, nameOffset, null);
                    scope.addParameter(paramInfo);
                    offset = nameOffset + param.length();
                }
            }
        } else if (ObjectLiteralParser.isSimpleIdentifier(headerText)) {
            int nameOffset = this.text.indexOf(headerText, headerStart);
            if (nameOffset < 0) {
                nameOffset = headerStart;
            }
            FieldInfo paramInfo = FieldInfo.parameter(headerText, TypeInfo.ANY, nameOffset, null);
            scope.addParameter(paramInfo);
        }
    }

    private void parseJSShorthandMethods() {
        Pattern shorthandPattern = Pattern.compile("(\\w+)\\s*\\(([^)]*)\\)\\s*\\{");
        Matcher m = shorthandPattern.matcher(this.text);
        while (m.find()) {
            String methodName;
            int start = m.start();
            if (this.isExcluded(start) || !ObjectLiteralParser.isInsideObjectLiteral(start, this) || TypeChecker.isJavaScriptKeyword(methodName = m.group(1))) continue;
            int headerStart = m.start(1);
            int bodyStart = m.end() - 1;
            int bodyEnd = this.findMatchingBrace(bodyStart);
            bodyEnd = bodyEnd < 0 ? this.text.length() : ++bodyEnd;
            InnerCallableScope scope = new InnerCallableScope(InnerCallableScope.Kind.JS_SHORTHAND_METHOD, headerStart, bodyStart, bodyStart, bodyEnd);
            String paramsText = m.group(2).trim();
            if (!paramsText.isEmpty()) {
                String[] params = paramsText.split(",");
                int offset = m.start(2);
                for (String param : params) {
                    if ((param = param.trim()).isEmpty()) continue;
                    int nameOffset = this.text.indexOf(param, offset);
                    if (nameOffset < 0) {
                        nameOffset = offset;
                    }
                    FieldInfo paramInfo = FieldInfo.parameter(param, TypeInfo.ANY, nameOffset, null);
                    scope.addParameter(paramInfo);
                    offset = nameOffset + param.length();
                }
            }
            this.innerScopes.add(scope);
        }
    }

    private boolean isFunctionDeclaration(int funcStart) {
        int pos;
        for (pos = funcStart - 1; pos >= 0 && Character.isWhitespace(this.text.charAt(pos)) && this.text.charAt(pos) != '\n'; --pos) {
        }
        if (pos < 0) {
            return true;
        }
        char prev = this.text.charAt(pos);
        if (prev == '=' || prev == '(' || prev == ',' || prev == ':' || prev == '[' || prev == '?') {
            return false;
        }
        if (prev == '\n' || prev == '{' || prev == ';' || prev == '}') {
            return true;
        }
        return true;
    }

    private void setupScopeParents() {
        for (int i = 0; i < this.innerScopes.size(); ++i) {
            InnerCallableScope scope = this.innerScopes.get(i);
            InnerCallableScope parent = null;
            for (int j = 0; j < this.innerScopes.size(); ++j) {
                InnerCallableScope candidate;
                if (i == j || (candidate = this.innerScopes.get(j)).getBodyStart() >= scope.getHeaderStart() || candidate.getBodyEnd() <= scope.getFullEnd() || parent != null && candidate.getBodyStart() <= parent.getBodyStart()) continue;
                parent = candidate;
            }
            scope.setParentScope(parent);
        }
    }

    /*
     * Could not resolve type clashes
     */
    private void parseGlobalFields() {
        if (this.isJavaScript()) {
            Pattern varPattern = Pattern.compile("(?:var|let|const)\\s+(\\w+)(?:\\s*(=))?");
            Matcher m = varPattern.matcher(this.text);
            while (m.find()) {
                TypeInfo inferred;
                int afterVar;
                Object constructor2;
                int position = m.start(1);
                if (this.isExcluded(position)) continue;
                boolean insideMethod = false;
                for (MethodInfo method : this.getAllMethods()) {
                    if (!method.containsPosition(position)) continue;
                    insideMethod = true;
                    break;
                }
                if (!insideMethod) {
                    for (Object constructor2 : this.getAllConstructors()) {
                        if (!((MethodInfo)constructor2).containsPosition(position)) continue;
                        insideMethod = true;
                        break;
                    }
                }
                if (insideMethod) continue;
                boolean insideInner = false;
                constructor2 = this.innerScopes.iterator();
                while (constructor2.hasNext()) {
                    InnerCallableScope scope = (InnerCallableScope)constructor2.next();
                    if (!scope.containsPosition(position)) continue;
                    insideInner = true;
                    break;
                }
                if (insideInner || (afterVar = m.end(1)) < this.text.length() && LoopVariableParser.FOR_IN_OF_LOOKAHEAD.matcher(this.text.substring(afterVar)).find()) continue;
                String varName = m.group(1);
                String initializer = null;
                int initializerStart = -1;
                int initializerEnd = -1;
                if (m.group(2) != null && (initializerEnd = this.findJsInitializerEnd(this.text, initializerStart = this.skipSegmentWhitespace(this.text, m.end(2)), true)) > initializerStart && (initializer = this.text.substring(initializerStart, initializerEnd).trim()).isEmpty()) {
                    initializer = null;
                }
                String documentation = this.extractDocumentationBefore(m.start());
                JSDocInfo jsDoc = this.jsDocParser.extractJSDocBefore(this.text, m.start());
                TypeInfo typeInfo = null;
                if (jsDoc != null && jsDoc.hasTypeTag()) {
                    typeInfo = jsDoc.getDeclaredType();
                }
                if (typeInfo == null && initializer != null && !initializer.isEmpty() && !TypeInfo.NULL.equals(inferred = this.resolveExpressionType(initializer, initializerStart))) {
                    typeInfo = inferred;
                }
                if (typeInfo == null) {
                    typeInfo = TypeInfo.ANY;
                }
                int initStart = -1;
                int initEnd = -1;
                if (m.group(2) != null) {
                    initStart = m.start(2);
                    initEnd = initializerEnd >= 0 ? initializerEnd : m.end(2);
                }
                FieldInfo fieldInfo = FieldInfo.globalField(varName, typeInfo, position, documentation, initStart, initEnd, 0);
                if (jsDoc != null) {
                    fieldInfo.setJSDocInfo(jsDoc);
                }
                if (this.globalFields.containsKey(varName)) {
                    AssignmentInfo dupError = AssignmentInfo.duplicateDeclaration(varName, position, position + varName.length(), "Variable '" + varName + "' is already defined in the scope");
                    this.declarationErrors.add(dupError);
                } else {
                    this.globalFields.put(varName, fieldInfo);
                }
                int contScanStart = MultiDeclaratorParser.jsDeclaratorScanStart(this.text, m.end(1), m.group(2) != null, initializerEnd);
                MultiDeclaratorParser.scanJSContinuationDeclarators(this, this.text, contScanStart, 0, (cVarName, cAbsNamePos, cInit, cAbsInitStart, cAbsInitEnd) -> {
                    void var8_17;
                    void var8_15;
                    TypeInfo inferred;
                    if (this.isExcluded(cAbsNamePos)) {
                        return;
                    }
                    boolean cInsideMethod = false;
                    for (MethodInfo methodInfo : this.getAllMethods()) {
                        if (!methodInfo.containsPosition(cAbsNamePos)) continue;
                        cInsideMethod = true;
                        break;
                    }
                    if (!cInsideMethod) {
                        for (MethodInfo methodInfo : this.getAllConstructors()) {
                            if (!methodInfo.containsPosition(cAbsNamePos)) continue;
                            cInsideMethod = true;
                            break;
                        }
                    }
                    if (cInsideMethod) {
                        return;
                    }
                    boolean cInsideInner = false;
                    for (InnerCallableScope sc : this.innerScopes) {
                        if (!sc.containsPosition(cAbsNamePos)) continue;
                        cInsideInner = true;
                        break;
                    }
                    if (cInsideInner) {
                        return;
                    }
                    Object var8_13 = null;
                    if (cInit != null && !cInit.isEmpty() && !TypeInfo.NULL.equals(inferred = this.resolveExpressionType(cInit, cAbsInitStart))) {
                        TypeInfo typeInfo = inferred;
                    }
                    if (var8_15 == null) {
                        TypeInfo typeInfo = TypeInfo.ANY;
                    }
                    FieldInfo cField = FieldInfo.globalField(cVarName, (TypeInfo)var8_17, cAbsNamePos, null, cAbsInitStart, cAbsInitEnd, 0);
                    if (this.globalFields.containsKey(cVarName)) {
                        AssignmentInfo dupErr = AssignmentInfo.duplicateDeclaration(cVarName, cAbsNamePos, cAbsNamePos + cVarName.length(), "Variable '" + cVarName + "' is already defined in the scope");
                        this.declarationErrors.add(dupErr);
                    } else {
                        this.globalFields.put(cVarName, cField);
                    }
                });
            }
        } else {
            Matcher m = FIELD_DECL_PATTERN.matcher(ScriptDocument.normalizeGenericNewlines(this.text));
            while (m.find()) {
                String typeNameRaw = m.group(1);
                String fieldName = m.group(2);
                String delimiter = m.group(3);
                int position = m.start(2);
                if (this.isExcluded(position) || this.isExcluded(m.start(1)) || this.hasExcludedRange(m.start(1), m.end(1))) continue;
                int commaInType = MultiDeclaratorParser.findFirstDepthZeroComma(typeNameRaw);
                if (commaInType >= 0) {
                    int enclosingParen1;
                    String realTypeNameRaw = MultiDeclaratorParser.extractRealTypeFromGreedyMatch(typeNameRaw, commaInType);
                    String firstVar = MultiDeclaratorParser.extractFirstVarFromGreedyMatch(typeNameRaw, commaInType);
                    int firstVarAbsPos = MultiDeclaratorParser.findFirstVarPosition(this.text, m.start(1), typeNameRaw, commaInType);
                    if (this.isExcluded(firstVarAbsPos) || (enclosingParen1 = this.findEnclosingParenStart(0, firstVarAbsPos)) >= 0 && "for".equals(this.readKeywordBefore(enclosingParen1))) continue;
                    int greedyModifiers = this.parseModifiers(realTypeNameRaw);
                    Iterator<MethodInfo> greedyTypeName = this.stripModifiers(realTypeNameRaw);
                    ScriptTypeInfo greedyContainingType = this.findEnclosingScriptType(firstVarAbsPos);
                    boolean greedyInsideMethod = false;
                    if (greedyContainingType != null && this.isInsideNestedMethod(firstVarAbsPos, greedyContainingType.getBodyStart(), greedyContainingType.getBodyEnd())) {
                        greedyInsideMethod = true;
                    } else {
                        for (MethodInfo method : this.getAllMethods()) {
                            if (!method.containsPosition(firstVarAbsPos)) continue;
                            greedyInsideMethod = true;
                            break;
                        }
                        if (!greedyInsideMethod) {
                            for (MethodInfo constructor : this.getAllConstructors()) {
                                if (!constructor.containsPosition(firstVarAbsPos)) continue;
                                greedyInsideMethod = true;
                                break;
                            }
                        }
                    }
                    if (greedyInsideMethod) continue;
                    String greedyDocumentation = this.extractDocumentationBefore(m.start());
                    TypeInfo greedyTypeInfo = this.resolveType((String)((Object)greedyTypeName), firstVarAbsPos);
                    FieldInfo firstField = FieldInfo.globalField(firstVar, greedyTypeInfo, firstVarAbsPos, greedyDocumentation, -1, -1, greedyModifiers);
                    if (greedyContainingType != null) {
                        greedyContainingType.addField(firstField);
                    } else if (this.globalFields.containsKey(firstVar)) {
                        AssignmentInfo dupErr = AssignmentInfo.duplicateDeclaration(firstVar, firstVarAbsPos, firstVarAbsPos + firstVar.length(), "Variable '" + firstVar + "' is already defined in the scope");
                        this.declarationErrors.add(dupErr);
                    } else {
                        this.globalFields.put(firstVar, firstField);
                    }
                    TypeInfo greedySharedType = greedyTypeInfo;
                    int greedySharedMod = greedyModifiers;
                    ScriptTypeInfo fGreedyContType = greedyContainingType;
                    int greedyScanStart = m.start(1) + commaInType;
                    MultiDeclaratorParser.scanJavaContinuationDeclarators(this.text, greedyScanStart, 0, (cVarName, cAbsNamePos, cAbsInitStart, cAbsInitEnd) -> {
                        if (this.isExcluded(cAbsNamePos)) {
                            return;
                        }
                        boolean cInsideMethod = false;
                        if (fGreedyContType != null && this.isInsideNestedMethod(cAbsNamePos, fGreedyContType.getBodyStart(), fGreedyContType.getBodyEnd())) {
                            cInsideMethod = true;
                        } else {
                            for (MethodInfo method : this.getAllMethods()) {
                                if (!method.containsPosition(cAbsNamePos)) continue;
                                cInsideMethod = true;
                                break;
                            }
                            if (!cInsideMethod) {
                                for (MethodInfo constructor : this.getAllConstructors()) {
                                    if (!constructor.containsPosition(cAbsNamePos)) continue;
                                    cInsideMethod = true;
                                    break;
                                }
                            }
                        }
                        if (cInsideMethod) {
                            return;
                        }
                        FieldInfo cField = FieldInfo.globalField(cVarName, greedySharedType, cAbsNamePos, null, cAbsInitStart, cAbsInitEnd, greedySharedMod);
                        if (fGreedyContType != null) {
                            fGreedyContType.addField(cField);
                        } else if (this.globalFields.containsKey(cVarName)) {
                            AssignmentInfo dupErr = AssignmentInfo.duplicateDeclaration(cVarName, cAbsNamePos, cAbsNamePos + cVarName.length(), "Variable '" + cVarName + "' is already defined in the scope");
                            this.declarationErrors.add(dupErr);
                        } else {
                            this.globalFields.put(cVarName, cField);
                        }
                    });
                    continue;
                }
                int enclosingParen = this.findEnclosingParenStart(0, position);
                if (enclosingParen >= 0 && "for".equals(this.readKeywordBefore(enclosingParen))) continue;
                int modifiers = this.parseModifiers(typeNameRaw);
                String typeName = this.stripModifiers(typeNameRaw);
                ScriptTypeInfo containingScriptType = this.findEnclosingScriptType(position);
                boolean insideMethod = false;
                if (containingScriptType != null && this.isInsideNestedMethod(position, containingScriptType.getBodyStart(), containingScriptType.getBodyEnd())) {
                    insideMethod = true;
                } else {
                    for (MethodInfo method : this.getAllMethods()) {
                        if (!method.containsPosition(position)) continue;
                        insideMethod = true;
                        break;
                    }
                    if (!insideMethod) {
                        for (MethodInfo constructor : this.getAllConstructors()) {
                            if (!constructor.containsPosition(position)) continue;
                            insideMethod = true;
                            break;
                        }
                    }
                }
                if (insideMethod) continue;
                String documentation = this.extractDocumentationBefore(m.start());
                int initStart = -1;
                int initEnd = -1;
                if ("=".equals(delimiter)) {
                    initStart = m.start(3);
                    int depth = 0;
                    for (int searchPos = m.end(3); searchPos < this.text.length(); ++searchPos) {
                        char c = this.text.charAt(searchPos);
                        if (c == '(' || c == '[' || c == '{') {
                            ++depth;
                            continue;
                        }
                        if (c == ')' || c == ']' || c == '}') {
                            --depth;
                            continue;
                        }
                        if (c != ';' && c != ',' || depth != 0) continue;
                        initEnd = searchPos;
                        break;
                    }
                }
                TypeInfo typeInfo = this.resolveType(typeName, position);
                FieldInfo fieldInfo = FieldInfo.globalField(fieldName, typeInfo, position, documentation, initStart, initEnd, modifiers);
                if (containingScriptType != null) {
                    containingScriptType.addField(fieldInfo);
                } else if (this.globalFields.containsKey(fieldName)) {
                    AssignmentInfo dupError = AssignmentInfo.duplicateDeclaration(fieldName, position, position + fieldName.length(), "Variable '" + fieldName + "' is already defined in the scope");
                    this.declarationErrors.add(dupError);
                } else {
                    this.globalFields.put(fieldName, fieldInfo);
                }
                TypeInfo sharedType = typeInfo;
                int sharedModifiers = modifiers;
                ScriptTypeInfo fContainingType = containingScriptType;
                int javaScanStart = MultiDeclaratorParser.javaDeclaratorScanStart(this.text, delimiter, m.end(3), initEnd >= 0 ? initEnd : -1);
                if (javaScanStart < 0) continue;
                MultiDeclaratorParser.scanJavaContinuationDeclarators(this.text, javaScanStart, 0, (cVarName, cAbsNamePos, cAbsInitStart, cAbsInitEnd) -> {
                    if (this.isExcluded(cAbsNamePos)) {
                        return;
                    }
                    boolean cInsideMethod = false;
                    if (fContainingType != null && this.isInsideNestedMethod(cAbsNamePos, fContainingType.getBodyStart(), fContainingType.getBodyEnd())) {
                        cInsideMethod = true;
                    } else {
                        for (MethodInfo method : this.getAllMethods()) {
                            if (!method.containsPosition(cAbsNamePos)) continue;
                            cInsideMethod = true;
                            break;
                        }
                        if (!cInsideMethod) {
                            for (MethodInfo constructor : this.getAllConstructors()) {
                                if (!constructor.containsPosition(cAbsNamePos)) continue;
                                cInsideMethod = true;
                                break;
                            }
                        }
                    }
                    if (cInsideMethod) {
                        return;
                    }
                    FieldInfo cField = FieldInfo.globalField(cVarName, sharedType, cAbsNamePos, null, cAbsInitStart, cAbsInitEnd, sharedModifiers);
                    if (fContainingType != null) {
                        fContainingType.addField(cField);
                    } else if (this.globalFields.containsKey(cVarName)) {
                        AssignmentInfo dupErr = AssignmentInfo.duplicateDeclaration(cVarName, cAbsNamePos, cAbsNamePos + cVarName.length(), "Variable '" + cVarName + "' is already defined in the scope");
                        this.declarationErrors.add(dupErr);
                    } else {
                        this.globalFields.put(cVarName, cField);
                    }
                });
            }
        }
    }

    private String stripModifiers(String typeName) {
        if (typeName == null) {
            return null;
        }
        String[] parts = typeName.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (TypeResolver.isModifier(part)) continue;
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(part);
        }
        return result.toString();
    }

    private int parseModifiers(String declaration) {
        String[] parts;
        if (declaration == null) {
            return 0;
        }
        int modifiers = 0;
        for (String part : parts = declaration.trim().split("\\s+")) {
            if (part.equals("public")) {
                modifiers |= 1;
                continue;
            }
            if (part.equals("private")) {
                modifiers |= 2;
                continue;
            }
            if (part.equals("protected")) {
                modifiers |= 4;
                continue;
            }
            if (part.equals("static")) {
                modifiers |= 8;
                continue;
            }
            if (part.equals("final")) {
                modifiers |= 0x10;
                continue;
            }
            if (part.equals("abstract")) {
                modifiers |= 0x400;
                continue;
            }
            if (part.equals("synchronized")) {
                modifiers |= 0x20;
                continue;
            }
            if (part.equals("volatile")) {
                modifiers |= 0x40;
                continue;
            }
            if (part.equals("transient")) {
                modifiers |= 0x80;
                continue;
            }
            if (part.equals("native")) {
                modifiers |= 0x100;
                continue;
            }
            if (!part.equals("strictfp")) continue;
            modifiers |= 0x800;
        }
        return modifiers;
    }

    private int extractModifiersBackwards(int scanStart, String sourceText) {
        while (scanStart >= 0 && Character.isWhitespace(sourceText.charAt(scanStart))) {
            --scanStart;
        }
        StringBuilder modifiersText = new StringBuilder();
        while (scanStart >= 0) {
            String word;
            while (scanStart >= 0 && Character.isWhitespace(sourceText.charAt(scanStart))) {
                --scanStart;
            }
            if (scanStart < 0) break;
            int wordEnd = scanStart + 1;
            while (scanStart >= 0 && Character.isJavaIdentifierPart(sourceText.charAt(scanStart))) {
                --scanStart;
            }
            int wordStart = scanStart + 1;
            if (wordStart >= wordEnd || !TypeResolver.isModifier(word = sourceText.substring(wordStart, wordEnd))) break;
            if (modifiersText.length() > 0) {
                modifiersText.insert(0, " ");
            }
            modifiersText.insert(0, word);
        }
        return this.parseModifiers(modifiersText.toString());
    }

    private String extractDocumentationBefore(int position) {
        int searchStart;
        if (position <= 0) {
            return null;
        }
        for (searchStart = position - 1; searchStart >= 0 && this.text.charAt(searchStart) != '\n'; --searchStart) {
        }
        if (searchStart < 0) {
            return null;
        }
        --searchStart;
        while (searchStart >= 0 && Character.isWhitespace(this.text.charAt(searchStart))) {
            --searchStart;
        }
        if (searchStart < 0) {
            return null;
        }
        if (searchStart > 0 && this.text.charAt(searchStart) == '/' && this.text.charAt(searchStart - 1) == '*') {
            int commentEnd = searchStart + 1;
            int commentStart = this.text.lastIndexOf("/*", searchStart - 1);
            if (commentStart >= 0) {
                String comment = this.text.substring(commentStart, commentEnd);
                return this.cleanDocumentation(comment);
            }
        }
        StringBuilder doc = new StringBuilder();
        int currentPos = searchStart;
        while (currentPos >= 0) {
            int lineEnd;
            int lineStart;
            for (lineStart = currentPos; lineStart > 0 && this.text.charAt(lineStart - 1) != '\n'; --lineStart) {
            }
            for (lineEnd = currentPos; lineEnd < this.text.length() && this.text.charAt(lineEnd) != '\n'; ++lineEnd) {
            }
            String line = this.text.substring(lineStart, lineEnd).trim();
            if (!line.startsWith("//") && !line.startsWith("#")) break;
            String commentText = line.substring(2).trim();
            if (doc.length() > 0) {
                doc.insert(0, "\n");
            }
            doc.insert(0, commentText);
            if (lineStart <= 0) break;
            for (currentPos = lineStart - 1; currentPos >= 0 && this.text.charAt(currentPos) == '\n'; --currentPos) {
            }
        }
        if (doc.length() > 0) {
            return doc.toString();
        }
        return null;
    }

    private String cleanDocumentation(String comment) {
        if (comment == null || comment.isEmpty()) {
            return null;
        }
        comment = comment.replaceAll("^/\\*+\\s*", "").replaceAll("\\s*\\*+/$", "");
        String[] lines = comment.split("\n");
        StringBuilder cleaned = new StringBuilder();
        for (String line : lines) {
            line = line.trim().replaceAll("^\\*+\\s*", "");
            if (cleaned.length() > 0 && !line.isEmpty()) {
                cleaned.append("\n");
            }
            if (line.isEmpty()) continue;
            cleaned.append(line);
        }
        String result = cleaned.toString().trim();
        return result.isEmpty() ? null : result;
    }

    public TypeInfo resolveType(String typeName) {
        if (this.isJavaScript()) {
            return this.typeResolver.resolveJSType(typeName);
        }
        return this.resolveTypeAndTrackUsage(typeName);
    }

    private TypeInfo substituteTypeParams(TypeInfo type, int position) {
        List<TypeInfo> args;
        if (type == null) {
            return null;
        }
        if (!type.isResolved() && type.getSimpleName() != null && !type.getSimpleName().contains(".")) {
            TypeInfo sub = this.resolveType(type.getSimpleName(), position);
            return sub != null && sub.isResolved() ? sub : type;
        }
        if (type.isParameterized() && (args = type.getAppliedTypeArgs()) != null && !args.isEmpty()) {
            ArrayList<TypeInfo> substituted = new ArrayList<TypeInfo>(args.size());
            boolean anyChanged = false;
            for (TypeInfo arg : args) {
                TypeInfo sub = this.substituteTypeParams(arg, position);
                if (sub != arg) {
                    anyChanged = true;
                }
                substituted.add(sub);
            }
            if (anyChanged) {
                TypeInfo raw = type.getRawType();
                return raw != null ? raw.parameterize(substituted) : type.parameterize(substituted);
            }
        }
        return type;
    }

    public TypeInfo resolveType(String typeName, int position) {
        TypeInfo resolved = this.resolveType(typeName);
        if (resolved != null && resolved.isResolved()) {
            return this.substituteTypeParams(resolved, position);
        }
        if (typeName != null && !typeName.contains(".")) {
            TypeStringNormalizer.ArraySplit arraySplit = TypeStringNormalizer.splitArraySuffixes(typeName);
            String baseTypeName = arraySplit.base;
            int arrayDims = arraySplit.dimensions;
            for (ScriptTypeInfo enclosing = this.findEnclosingScriptType(position); enclosing != null; enclosing = enclosing.getOuterClass()) {
                TypeParamInfo typeParam = enclosing.getDeclaredTypeParam(baseTypeName);
                if (typeParam != null) {
                    TypeInfo result = TypeInfo.typeParameter(baseTypeName, typeParam);
                    for (int i = 0; i < arrayDims; ++i) {
                        result = TypeInfo.arrayOf(result);
                    }
                    return result;
                }
                ScriptTypeInfo inner = enclosing.getInnerClass(typeName);
                if (inner == null) continue;
                return inner;
            }
        }
        return resolved;
    }

    private TypeInfo resolveTypeAndTrackUsage(String typeName) {
        TypeInfo resolved;
        if (typeName == null || typeName.isEmpty()) {
            return TypeInfo.unresolved(typeName, typeName);
        }
        String normalized = typeName.trim();
        String normalizedFinal = this.stripLeadingModifiers(normalized);
        TypeStringNormalizer.ArraySplit arraySplit = TypeStringNormalizer.splitArraySuffixes(normalizedFinal);
        String baseExpr = arraySplit.base;
        int arrayDims = arraySplit.dimensions;
        Function<String, TypeInfo> resolveBase = baseName -> {
            TypeInfo resolved;
            block8: {
                block13: {
                    TypeInfo outerType;
                    block14: {
                        block12: {
                            block11: {
                                block10: {
                                    block9: {
                                        if (!TypeResolver.isPrimitiveType(baseName)) break block9;
                                        resolved = TypeInfo.fromPrimitive(baseName);
                                        break block8;
                                    }
                                    if (!"String".equals(baseName)) break block10;
                                    resolved = this.typeResolver.resolveFullName("java.lang.String");
                                    break block8;
                                }
                                if (baseName.contains(".") || !this.scriptTypes.containsKey(baseName)) break block11;
                                resolved = this.scriptTypes.get(baseName);
                                break block8;
                            }
                            if (!baseName.contains(".") || !this.scriptTypesByDotName.containsKey(baseName)) break block12;
                            resolved = this.scriptTypesByDotName.get(baseName);
                            break block8;
                        }
                        if (!baseName.contains(".")) break block13;
                        resolved = this.typeResolver.resolveFullName((String)baseName);
                        if (resolved != null && resolved.isResolved()) break block8;
                        int firstDot = baseName.indexOf(46);
                        String outerSimple = baseName.substring(0, firstDot);
                        String innerPath = baseName.substring(firstDot + 1);
                        outerType = this.typeResolver.resolveSimpleName(outerSimple, this.importsBySimpleName, this.wildcardPackages);
                        if (outerType == null || !outerType.isResolved() || outerType.getJavaClass() == null) break block8;
                        Class<?> currentClass = outerType.getJavaClass();
                        for (String part : innerPath.split("\\.")) {
                            Class<?> found = null;
                            for (Class<?> inner : currentClass.getClasses()) {
                                if (!inner.getSimpleName().equals(part)) continue;
                                found = inner;
                                break;
                            }
                            if ((currentClass = found) == null) break;
                        }
                        if (currentClass == null) break block8;
                        resolved = TypeInfo.fromClass(currentClass);
                        ImportData usedImport = this.importsBySimpleName.get(outerSimple);
                        if (usedImport == null) break block14;
                        usedImport.incrementUsage();
                        break block8;
                    }
                    if (this.wildcardPackages == null) break block8;
                    String resultPkg = outerType.getPackageName();
                    for (ImportData imp : this.imports) {
                        if (!imp.isWildcard() || resultPkg == null || !resultPkg.equals(imp.getFullPath())) continue;
                        imp.incrementUsage();
                        break block8;
                    }
                    break block8;
                }
                resolved = this.typeResolver.resolveSimpleName((String)baseName, this.importsBySimpleName, this.wildcardPackages);
                if (resolved != null && resolved.isResolved()) {
                    ImportData usedImport = this.importsBySimpleName.get(baseName);
                    if (usedImport != null) {
                        usedImport.incrementUsage();
                    } else if (this.wildcardPackages != null) {
                        String resultPkg = resolved.getPackageName();
                        for (ImportData imp : this.imports) {
                            if (!imp.isWildcard() || resultPkg == null || !resultPkg.equals(imp.getFullPath())) continue;
                            imp.incrementUsage();
                            break;
                        }
                    }
                }
            }
            return resolved != null ? resolved : TypeInfo.unresolved(baseName, normalizedFinal);
        };
        if (!baseExpr.contains("<")) {
            resolved = resolveBase.apply(baseExpr);
        } else {
            GenericTypeParser.ParsedType parsed = GenericTypeParser.parse(baseExpr);
            if (parsed != null) {
                String baseName2 = parsed.baseName.replaceAll("\\s*\\.\\s*", ".").trim();
                resolved = resolveBase.apply(baseName2);
                if (parsed.hasTypeArgs() && resolved != null && resolved.isResolved()) {
                    ArrayList<TypeInfo> resolvedArgs = new ArrayList<TypeInfo>();
                    for (GenericTypeParser.ParsedType argParsed : parsed.typeArgs) {
                        if (argParsed == null) {
                            resolvedArgs.add(TypeInfo.fromClass(Object.class));
                            continue;
                        }
                        TypeInfo argType = this.resolveTypeAndTrackUsage(argParsed.rawString);
                        resolvedArgs.add(argType != null ? argType : TypeInfo.unresolved(argParsed.baseName, argParsed.baseName));
                    }
                    if (!resolvedArgs.isEmpty()) {
                        resolved = resolved.parameterize(resolvedArgs);
                    }
                }
            } else {
                resolved = resolveBase.apply(baseExpr);
            }
        }
        for (int i = 0; i < arrayDims; ++i) {
            resolved = TypeInfo.arrayOf(resolved);
        }
        return resolved;
    }

    private String stripLeadingModifiers(String typeExpr) {
        if (typeExpr == null) {
            return null;
        }
        int i = 0;
        int len = typeExpr.length();
        while (i < len) {
            while (i < len && Character.isWhitespace(typeExpr.charAt(i))) {
                ++i;
            }
            int start = i;
            while (i < len && Character.isJavaIdentifierPart(typeExpr.charAt(i))) {
                ++i;
            }
            if (start == i) break;
            String word = typeExpr.substring(start, i);
            if (TypeResolver.isModifier(word)) continue;
            return typeExpr.substring(start).trim();
        }
        return typeExpr.trim();
    }

    private List<ScriptLine.Mark> buildMarks() {
        ArrayList<ScriptLine.Mark> marks = new ArrayList<ScriptLine.Mark>();
        this.addPatternMarks(marks, STRING_PATTERN, TokenType.STRING);
        this.markJSDocElements(marks);
        this.markNonJSDocComments(marks);
        this.addPatternMarks(marks, KEYWORD_PATTERN, TokenType.KEYWORD);
        if (this.isJavaScript()) {
            this.addPatternMarks(marks, KEYWORD_JS_PATTERN, TokenType.KEYWORD);
        }
        this.addPatternMarks(marks, NUMBER_PATTERN, TokenType.LITERAL);
        if (this.isJavaScript()) {
            this.markObjectLiteralKeys(marks);
        }
        if (!this.isJavaScript()) {
            this.markImports(marks);
        }
        if (!this.isJavaScript()) {
            this.markClassDeclarations(marks);
            this.markEnumConstants(marks);
        }
        if (!this.isJavaScript()) {
            this.addPatternMarks(marks, MODIFIER_PATTERN, TokenType.KEYWORD);
        }
        if (!this.isJavaScript()) {
            this.markTypeDeclarations(marks);
        }
        this.markMethodDeclarations(marks);
        this.markMethodCalls(marks);
        this.markVariables(marks);
        this.markChainedFieldAccesses(marks);
        this.markImportedClassUsages(marks);
        if (!this.isJavaScript()) {
            this.markCastTypes(marks);
            this.markUnusedImports(marks);
            this.markMethodReferences(marks);
        }
        this.markLambdaOperators(marks);
        this.markInnerScopeParameters(marks);
        this.validateLambdaReturnTypes(marks);
        this.markUndefinedIdentifiers(marks);
        return marks;
    }

    private void markObjectLiteralKeys(List<ScriptLine.Mark> marks) {
        for (ObjectLiteralParser.ObjectLiteralAnalysis analysis : this.objectLiterals.values()) {
            for (ObjectLiteralParser.ObjectLiteralProperty p : analysis.properties) {
                if (!p.isIdentifierKey) continue;
                marks.add(new ScriptLine.Mark(p.keyStartAbs, p.keyEndAbs, TokenType.LOCAL_FIELD));
            }
        }
    }

    private void parseObjectLiterals() {
        ObjectLiteralParser.ObjectLiteralAnalysis analysis;
        String objectLiteral;
        if (!this.isJavaScript()) {
            return;
        }
        List<int[]> braceRanges = this.findObjectLiteralBraces();
        if (braceRanges.isEmpty()) {
            return;
        }
        ObjectLiteralParser.ExpressionTypeResolverFn nullResolver = (expr, pos) -> null;
        for (int[] range : braceRanges) {
            objectLiteral = this.text.substring(range[0], range[1]);
            analysis = ObjectLiteralParser.parse(objectLiteral, range[0], true, true, nullResolver, this::getScriptMethodInfo);
            if (analysis == null) continue;
            this.objectLiterals.put(range[0], analysis);
        }
        for (InnerCallableScope scope : this.innerScopes) {
            ObjectLiteralParser.attachObjectLiteralContext(scope, this);
        }
        for (int[] range : braceRanges) {
            objectLiteral = this.text.substring(range[0], range[1]);
            analysis = ObjectLiteralParser.parse(objectLiteral, range[0], true, true, this::resolveExpressionType, this::getScriptMethodInfo);
            if (analysis == null) continue;
            this.objectLiterals.put(range[0], analysis);
        }
        for (InnerCallableScope scope : this.innerScopes) {
            scope.setContainingObjectType(null);
            ObjectLiteralParser.attachObjectLiteralContext(scope, this);
        }
    }

    private List<int[]> findObjectLiteralBraces() {
        int brace;
        ArrayList<int[]> ranges = new ArrayList<int[]>();
        int i = 0;
        while (i < this.text.length() && (brace = this.text.indexOf(123, i)) >= 0) {
            if (this.isExcluded(brace) || !this.isLikelyObjectLiteralStart(brace)) {
                i = brace + 1;
                continue;
            }
            int end = this.findMatchingBraceEndInDocument(brace);
            if (end <= brace) {
                i = brace + 1;
                continue;
            }
            ranges.add(new int[]{brace, end});
            i = brace + 1;
        }
        return ranges;
    }

    private boolean isLikelyObjectLiteralStart(int bracePos) {
        int prev;
        for (prev = bracePos - 1; prev >= 0 && Character.isWhitespace(this.text.charAt(prev)); --prev) {
        }
        if (prev < 0) {
            return false;
        }
        char pc = this.text.charAt(prev);
        if (pc == '=' || pc == '(' || pc == '[' || pc == ',' || pc == ':' || pc == '?' || pc == '!' || pc == '+' || pc == '-' || pc == '*' || pc == '/' || pc == '%' || pc == '&' || pc == '|' || pc == '^') {
            return true;
        }
        if (Character.isJavaIdentifierPart(pc)) {
            int start;
            int end = prev + 1;
            for (start = prev; start >= 0 && Character.isJavaIdentifierPart(this.text.charAt(start)); --start) {
            }
            String word = this.text.substring(++start, end);
            return "return".equals(word);
        }
        return false;
    }

    private int findMatchingBraceEndInDocument(int braceStart) {
        int depth = 0;
        for (int pos = braceStart; pos < this.text.length(); ++pos) {
            if (this.isExcluded(pos)) continue;
            char c = this.text.charAt(pos);
            if (c == '{') {
                ++depth;
                continue;
            }
            if (c != '}' || --depth != 0) continue;
            return pos + 1;
        }
        return -1;
    }

    private void markLambdaOperators(List<ScriptLine.Mark> marks) {
        int i;
        for (i = 0; i < this.text.length() - 1; ++i) {
            if (this.text.charAt(i) != '-' || this.text.charAt(i + 1) != '>' || this.isExcluded(i)) continue;
            marks.add(new ScriptLine.Mark(i, i + 2, TokenType.KEYWORD));
        }
        for (i = 0; i < this.text.length() - 1; ++i) {
            if (this.text.charAt(i) != ':' || this.text.charAt(i + 1) != ':' || this.isExcluded(i)) continue;
            marks.add(new ScriptLine.Mark(i, i + 2, TokenType.DEFAULT));
        }
    }

    private void markMethodReferences(List<ScriptLine.Mark> marks) {
        Matcher m = METHOD_REF_PATTERN.matcher(this.text);
        while (m.find()) {
            int targetStart = m.start(1);
            int targetEnd = m.end(1);
            int methodStart = m.start(2);
            int methodEnd = m.end(2);
            if (this.isExcluded(targetStart) || this.isExcluded(methodStart)) continue;
            String target = m.group(1);
            String methodName = m.group(2);
            if (methodEnd < this.text.length() && this.text.charAt(methodEnd) == '(') {
                int doubleColonPos;
                for (doubleColonPos = targetEnd; doubleColonPos < methodStart && this.text.charAt(doubleColonPos) != ':'; ++doubleColonPos) {
                }
                if (doubleColonPos < methodStart) {
                    marks.add(new ScriptLine.Mark(doubleColonPos, doubleColonPos + 2, TokenType.UNDEFINED_VAR, TokenErrorMessage.from("Method references cannot have parentheses. Use '" + target + "::" + methodName + "' instead of '" + target + "::" + methodName + "()'")));
                }
                marks.add(new ScriptLine.Mark(methodStart, methodEnd, TokenType.UNDEFINED_VAR, TokenErrorMessage.from("Method references cannot have parentheses")));
                marks.add(new ScriptLine.Mark(methodEnd, methodEnd + 1, TokenType.UNDEFINED_VAR, TokenErrorMessage.from("Remove parentheses from method reference")));
                continue;
            }
            this.markMethodRefTarget(marks, target, targetStart, targetEnd);
            TypeInfo targetType = this.resolveMethodRefTargetType(target, targetStart);
            if (targetType != null && targetType.isResolved()) {
                if ("new".equals(methodName)) {
                    if (targetType.hasConstructors()) {
                        List<MethodInfo> ctors;
                        MethodInfo ctorInfo = targetType.findConstructor(0);
                        if (ctorInfo == null && !(ctors = targetType.getConstructors()).isEmpty()) {
                            ctorInfo = ctors.get(0);
                        }
                        marks.add(new ScriptLine.Mark(methodStart, methodEnd, TokenType.METHOD_CALL, ctorInfo));
                        continue;
                    }
                    marks.add(new ScriptLine.Mark(methodStart, methodEnd, TokenType.UNDEFINED_VAR, TokenErrorMessage.from("No constructor found for '" + targetType.getSimpleName() + "'")));
                    continue;
                }
                if (targetType.hasMethod(methodName)) {
                    List<MethodInfo> overloads;
                    MethodInfo methodInfo = targetType.getMethodInfo(methodName);
                    if (methodInfo == null && !(overloads = targetType.getAllMethodOverloads(methodName)).isEmpty()) {
                        methodInfo = overloads.get(0);
                    }
                    marks.add(new ScriptLine.Mark(methodStart, methodEnd, TokenType.METHOD_CALL, methodInfo));
                    continue;
                }
                marks.add(new ScriptLine.Mark(methodStart, methodEnd, TokenType.UNDEFINED_VAR, TokenErrorMessage.from("Method '" + methodName + "' not found in '" + targetType.getSimpleName() + "'")));
                continue;
            }
            marks.add(new ScriptLine.Mark(methodStart, methodEnd, TokenType.UNDEFINED_VAR));
        }
    }

    private TypeInfo resolveMethodRefTargetType(String target, int position) {
        if ("this".equals(target)) {
            ScriptTypeInfo enclosingType = this.findEnclosingScriptType(position);
            if (enclosingType != null) {
                return enclosingType;
            }
            MethodInfo containingMethod = this.findContainingMethod(position);
            if (containingMethod != null && containingMethod.getContainingType() != null) {
                return containingMethod.getContainingType();
            }
            return null;
        }
        if ("super".equals(target)) {
            ScriptTypeInfo enclosingType = this.findEnclosingScriptType(position);
            if (enclosingType != null && enclosingType.hasSuperClass()) {
                return enclosingType.getSuperClass();
            }
            return null;
        }
        if (target.contains(".")) {
            TypeInfo typeInfo = this.resolveType(target, position);
            if (typeInfo != null && typeInfo.isResolved()) {
                return typeInfo;
            }
            return null;
        }
        FieldInfo varInfo = this.resolveVariable(target, position);
        if (varInfo != null && varInfo.getTypeInfo() != null) {
            return varInfo.getTypeInfo();
        }
        TypeInfo typeInfo = this.resolveType(target, position);
        if (typeInfo != null && typeInfo.isResolved()) {
            return typeInfo;
        }
        return null;
    }

    private void markMethodRefTarget(List<ScriptLine.Mark> marks, String target, int start, int end) {
        if ("this".equals(target)) {
            marks.add(new ScriptLine.Mark(start, end, TokenType.KEYWORD));
            return;
        }
        if ("super".equals(target)) {
            marks.add(new ScriptLine.Mark(start, end, TokenType.KEYWORD));
            return;
        }
        if (target.contains(".")) {
            TypeInfo typeInfo = this.resolveType(target);
            if (typeInfo != null && typeInfo.isResolved()) {
                this.addTypeMark(marks, start, end, TokenType.IMPORTED_CLASS, typeInfo);
                return;
            }
            this.markQualifiedTargetParts(marks, target, start);
            return;
        }
        FieldInfo varInfo = this.resolveVariable(target, start);
        if (varInfo != null) {
            TokenType tokenType = varInfo.isParameter() ? TokenType.PARAMETER : (varInfo.isGlobal() ? TokenType.GLOBAL_FIELD : TokenType.LOCAL_FIELD);
            marks.add(new ScriptLine.Mark(start, end, tokenType, varInfo));
            return;
        }
        TypeInfo typeInfo = this.resolveType(target);
        if (typeInfo != null && typeInfo.isResolved()) {
            this.addTypeMark(marks, start, end, TokenType.IMPORTED_CLASS, typeInfo);
            return;
        }
    }

    private void markQualifiedTargetParts(List<ScriptLine.Mark> marks, String qualifiedName, int baseStart) {
        String[] parts = qualifiedName.split("\\.");
        int offset = baseStart;
        for (int i = 0; i < parts.length; ++i) {
            String part = parts[i];
            int partEnd = offset + part.length();
            if (i == parts.length - 1) {
                marks.add(new ScriptLine.Mark(offset, partEnd, TokenType.IMPORTED_CLASS));
            } else {
                marks.add(new ScriptLine.Mark(offset, partEnd, TokenType.TYPE_DECL));
            }
            offset = partEnd + 1;
        }
    }

    private void markInnerScopeParameters(List<ScriptLine.Mark> marks) {
        for (InnerCallableScope scope : this.innerScopes) {
            for (FieldInfo param : scope.getParameters()) {
                int start = param.getDeclarationOffset();
                int end = start + param.getName().length();
                marks.add(new ScriptLine.Mark(start, end, TokenType.PARAMETER, param));
            }
        }
    }

    private void validateLambdaReturnTypes(List<ScriptLine.Mark> marks) {
        for (InnerCallableScope scope : this.innerScopes) {
            if (scope.getKind() != InnerCallableScope.Kind.JAVA_LAMBDA && scope.getKind() != InnerCallableScope.Kind.JS_FUNCTION_EXPR && scope.getKind() != InnerCallableScope.Kind.JS_ARROW_FUNC && scope.getKind() != InnerCallableScope.Kind.JS_SHORTHAND_METHOD) continue;
            this.validateLambdaReturnType(scope, marks);
        }
    }

    private void validateLambdaReturnType(InnerCallableScope lambda, List<ScriptLine.Mark> marks) {
        TypeInfo expectedType = lambda.getExpectedType();
        if (expectedType == null || !expectedType.isFunctionalInterface()) {
            return;
        }
        MethodInfo sam = expectedType.getSingleAbstractMethod();
        if (sam == null) {
            return;
        }
        TypeInfo expectedReturnType = sam.getReturnType();
        int bodyStart = lambda.getBodyStart();
        int bodyEnd = lambda.getBodyEnd();
        if (bodyStart < 0 || bodyEnd <= bodyStart || bodyEnd > this.text.length()) {
            return;
        }
        String bodyText = this.text.substring(bodyStart, bodyEnd).trim();
        if (bodyText.startsWith("{")) {
            this.validateBlockLambdaReturns(lambda, bodyStart, bodyEnd, expectedReturnType, marks);
        } else {
            try {
                TypeInfo bodyType = this.resolveExpressionType(bodyText, bodyStart);
                if (bodyType != null && expectedReturnType != null && !this.isCompatibleType(bodyType, expectedReturnType)) {
                    String error = "Incompatible return type: expected " + expectedReturnType.getSimpleName() + " but was " + bodyType.getSimpleName();
                    marks.add(new ScriptLine.Mark(bodyStart, bodyEnd, TokenType.UNDEFINED_VAR, TokenErrorMessage.from(error)));
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private void validateBlockLambdaReturns(InnerCallableScope lambda, int blockStart, int blockEnd, TypeInfo expectedReturnType, List<ScriptLine.Mark> marks) {
        String blockText = this.text.substring(blockStart, blockEnd);
        Pattern returnPattern = Pattern.compile("\\breturn\\b(\\s*;|\\s+[^;]+;)");
        Matcher m = returnPattern.matcher(blockText);
        while (m.find()) {
            int returnStart = blockStart + m.start();
            int returnEnd = blockStart + m.end();
            if (this.isExcluded(returnStart)) continue;
            String returnStmt = m.group(1).trim();
            if (returnStmt.equals(";")) {
                if (expectedReturnType == null || expectedReturnType.getSimpleName().equals("void")) continue;
                String error = "Cannot return void from lambda expecting " + expectedReturnType.getSimpleName();
                marks.add(new ScriptLine.Mark(returnStart, returnEnd, TokenType.UNDEFINED_VAR, TokenErrorMessage.from(error)));
                continue;
            }
            String expr = returnStmt.substring(0, returnStmt.length() - 1).trim();
            if (expectedReturnType != null && expectedReturnType.getSimpleName().equals("void")) {
                String error = "Cannot return a value from void lambda";
                marks.add(new ScriptLine.Mark(returnStart, returnEnd, TokenType.UNDEFINED_VAR, TokenErrorMessage.from(error)));
                continue;
            }
            try {
                int exprStart = returnStart + m.group(0).indexOf(expr);
                TypeInfo returnType = this.resolveExpressionType(expr, exprStart);
                if (returnType == null || expectedReturnType == null || this.isCompatibleType(returnType, expectedReturnType)) continue;
                String error = "Incompatible return type: expected " + expectedReturnType.getSimpleName() + " but returned " + returnType.getSimpleName();
                int exprEnd = exprStart + expr.length();
                marks.add(new ScriptLine.Mark(exprStart, exprEnd, TokenType.UNDEFINED_VAR, TokenErrorMessage.from(error)));
            }
            catch (Exception exception) {}
        }
    }

    private boolean isCompatibleType(TypeInfo actual, TypeInfo expected) {
        if (actual == null || expected == null) {
            return false;
        }
        if (actual.equals(expected)) {
            return true;
        }
        if (actual.getSimpleName().equals(expected.getSimpleName())) {
            return true;
        }
        if (expected.getSimpleName().equals("void")) {
            return true;
        }
        if (expected.getSimpleName().equals("Object") || expected.getFullName().equals("java.lang.Object")) {
            return true;
        }
        if (this.isBoxingCompatible(actual.getSimpleName(), expected.getSimpleName())) {
            return true;
        }
        return TypeChecker.isTypeCompatible(expected, actual);
    }

    private boolean isBoxingCompatible(String actualName, String expectedName) {
        if (actualName.equals("int") && expectedName.equals("Integer")) {
            return true;
        }
        if (actualName.equals("Integer") && expectedName.equals("int")) {
            return true;
        }
        if (actualName.equals("boolean") && expectedName.equals("Boolean")) {
            return true;
        }
        if (actualName.equals("Boolean") && expectedName.equals("boolean")) {
            return true;
        }
        if (actualName.equals("long") && expectedName.equals("Long")) {
            return true;
        }
        if (actualName.equals("Long") && expectedName.equals("long")) {
            return true;
        }
        if (actualName.equals("double") && expectedName.equals("Double")) {
            return true;
        }
        if (actualName.equals("Double") && expectedName.equals("double")) {
            return true;
        }
        if (actualName.equals("float") && expectedName.equals("Float")) {
            return true;
        }
        if (actualName.equals("Float") && expectedName.equals("float")) {
            return true;
        }
        if (actualName.equals("byte") && expectedName.equals("Byte")) {
            return true;
        }
        if (actualName.equals("Byte") && expectedName.equals("byte")) {
            return true;
        }
        if (actualName.equals("short") && expectedName.equals("Short")) {
            return true;
        }
        if (actualName.equals("Short") && expectedName.equals("short")) {
            return true;
        }
        if (actualName.equals("char") && expectedName.equals("Character")) {
            return true;
        }
        return actualName.equals("Character") && expectedName.equals("char");
    }

    private void markUnusedImports(List<ScriptLine.Mark> marks) {
        for (ImportData imp : this.imports) {
            if (imp.isUsed() || !imp.isResolved() || imp.isWildcard()) continue;
            marks.add(new ScriptLine.Mark(imp.getStartOffset(), imp.getEndOffset(), TokenType.UNUSED_IMPORT, imp));
        }
    }

    private void markUndefinedIdentifiers(List<ScriptLine.Mark> marks) {
        boolean[] markedPositions = new boolean[this.text.length()];
        for (ScriptLine.Mark mark : marks) {
            for (int i = mark.start; i < mark.end && i < markedPositions.length; ++i) {
                markedPositions[i] = true;
            }
        }
        HashSet<String> knownKeywords = new HashSet<String>(Arrays.asList("boolean", "int", "float", "double", "long", "char", "byte", "short", "void", "null", "true", "false", "if", "else", "switch", "case", "for", "while", "do", "try", "catch", "finally", "return", "throw", "var", "let", "const", "function", "continue", "break", "this", "new", "typeof", "instanceof", "class", "interface", "extends", "implements", "import", "package", "public", "private", "protected", "static", "final", "abstract", "synchronized", "native", "default", "enum", "throws", "super", "assert", "volatile", "transient"));
        if (this.isJavaScript()) {
            knownKeywords.add("delete");
            knownKeywords.add("undefined");
        }
        Pattern identifier = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*)\\b");
        Matcher m = identifier.matcher(this.text);
        while (m.find()) {
            int start = m.start(1);
            int end = m.end(1);
            String name = m.group(1);
            if (start < markedPositions.length && markedPositions[start] || this.isExcluded(start) || knownKeywords.contains(name)) continue;
            marks.add(new ScriptLine.Mark(start, end, TokenType.UNDEFINED_VAR, null));
        }
    }

    private void addPatternMarks(List<ScriptLine.Mark> marks, Pattern pattern, TokenType type) {
        this.addPatternMarks(marks, pattern, type, 0);
    }

    private void addPatternMarks(List<ScriptLine.Mark> marks, Pattern pattern, TokenType type, int group) {
        Matcher m = pattern.matcher(this.text);
        while (m.find()) {
            marks.add(new ScriptLine.Mark(m.start(group), m.end(group), type));
        }
    }

    private void markImports(List<ScriptLine.Mark> marks) {
        for (ImportData imp : this.imports) {
            int firstUnresolved;
            TokenType tokenType;
            if (imp.getStartOffset() < 0) continue;
            marks.add(new ScriptLine.Mark(imp.getStartOffset(), imp.getStartOffset() + 6, TokenType.KEYWORD, imp));
            int pathStart = imp.getPathStartOffset();
            int pathEnd = imp.getPathEndOffset();
            String pathText = this.text.substring(pathStart, Math.min(pathEnd, this.text.length()));
            if (pathText.trim().endsWith(".")) continue;
            ArrayList<String> tokens = new ArrayList<String>();
            ArrayList<Integer> tokenStarts = new ArrayList<Integer>();
            ArrayList<Integer> tokenEnds = new ArrayList<Integer>();
            Pattern idPattern = Pattern.compile("[A-Za-z_][A-Za-z0-9_]*");
            Matcher idm = idPattern.matcher(pathText);
            while (idm.find()) {
                tokens.add(idm.group());
                tokenStarts.add(pathStart + idm.start());
                tokenEnds.add(pathStart + idm.end());
            }
            if (tokens.isEmpty()) continue;
            if (imp.isWildcard()) {
                boolean pkgValid = this.typeResolver.isValidPackage(imp.getFullPath());
                TypeInfo outerType = this.typeResolver.resolveFullName(imp.getFullPath());
                if (pkgValid || outerType != null) {
                    int pkgTokenCount = outerType != null ? tokens.size() - 1 : tokens.size();
                    for (int i = 0; i < Math.min(pkgTokenCount, tokens.size()); ++i) {
                        marks.add(new ScriptLine.Mark((Integer)tokenStarts.get(i), (Integer)tokenEnds.get(i), TokenType.TYPE_DECL, imp));
                    }
                    if (outerType == null || tokens.size() <= 0) continue;
                    int lastIdx = tokens.size() - 1;
                    marks.add(new ScriptLine.Mark((Integer)tokenStarts.get(lastIdx), (Integer)tokenEnds.get(lastIdx), outerType.getTokenType(), imp));
                    continue;
                }
                marks.add(new ScriptLine.Mark(pathStart, pathEnd, TokenType.UNDEFINED_VAR, imp));
                continue;
            }
            TypeInfo resolvedType = imp.getResolvedType();
            if (resolvedType != null && resolvedType.isResolved()) {
                int i;
                String fullPath = imp.getFullPath();
                String resolvedName = resolvedType.getFullName();
                String pkgName = resolvedType.getPackageName();
                int pkgSegments = pkgName != null && !pkgName.isEmpty() ? pkgName.split("\\.").length : 0;
                for (i = 0; i < Math.min(pkgSegments, tokens.size()); ++i) {
                    marks.add(new ScriptLine.Mark((Integer)tokenStarts.get(i), (Integer)tokenEnds.get(i), TokenType.TYPE_DECL, imp));
                }
                for (i = pkgSegments; i < tokens.size(); ++i) {
                    String segmentName = (String)tokens.get(i);
                    StringBuilder classPath = new StringBuilder();
                    if (pkgName != null && !pkgName.isEmpty()) {
                        classPath.append(pkgName).append(".");
                    }
                    for (int j = pkgSegments; j <= i; ++j) {
                        if (j > pkgSegments) {
                            classPath.append("$");
                        }
                        classPath.append((String)tokens.get(j));
                    }
                    TypeInfo segmentType = this.typeResolver.resolveFullName(classPath.toString());
                    tokenType = segmentType != null ? segmentType.getTokenType() : resolvedType.getTokenType();
                    marks.add(new ScriptLine.Mark((Integer)tokenStarts.get(i), (Integer)tokenEnds.get(i), tokenType, imp));
                }
                continue;
            }
            int lastValidPkg = -1;
            StringBuilder pkgBuilder = new StringBuilder();
            for (int i = 0; i < tokens.size(); ++i) {
                if (i > 0) {
                    pkgBuilder.append(".");
                }
                pkgBuilder.append((String)tokens.get(i));
                if (!this.typeResolver.isValidPackage(pkgBuilder.toString())) continue;
                lastValidPkg = i;
            }
            int lastValidClass = -1;
            TypeInfo lastValidType = null;
            StringBuilder classPath = new StringBuilder();
            if (lastValidPkg >= 0) {
                classPath.append(pkgBuilder.substring(0, pkgBuilder.toString().indexOf((String)tokens.get(lastValidPkg)) + ((String)tokens.get(lastValidPkg)).length()));
            }
            int i = lastValidPkg + 1;
            while (i < tokens.size()) {
                if (classPath.length() > 0) {
                    classPath.append(i == lastValidPkg + 1 ? "." : "$");
                }
                classPath.append((String)tokens.get(i));
                TypeInfo segmentType = this.typeResolver.resolveFullName(classPath.toString());
                if (segmentType == null || !segmentType.isResolved()) break;
                lastValidClass = i++;
                lastValidType = segmentType;
            }
            for (i = 0; i <= lastValidPkg; ++i) {
                marks.add(new ScriptLine.Mark((Integer)tokenStarts.get(i), (Integer)tokenEnds.get(i), TokenType.TYPE_DECL, imp));
            }
            StringBuilder resolvedPath = new StringBuilder();
            if (lastValidPkg >= 0) {
                for (int i2 = 0; i2 <= lastValidPkg; ++i2) {
                    if (i2 > 0) {
                        resolvedPath.append(".");
                    }
                    resolvedPath.append((String)tokens.get(i2));
                }
            }
            for (int i3 = lastValidPkg + 1; i3 <= lastValidClass; ++i3) {
                if (resolvedPath.length() > 0) {
                    resolvedPath.append(i3 == lastValidPkg + 1 ? "." : "$");
                }
                resolvedPath.append((String)tokens.get(i3));
                TypeInfo segmentType = this.typeResolver.resolveFullName(resolvedPath.toString());
                tokenType = segmentType != null ? segmentType.getTokenType() : TokenType.IMPORTED_CLASS;
                marks.add(new ScriptLine.Mark((Integer)tokenStarts.get(i3), (Integer)tokenEnds.get(i3), tokenType, imp));
            }
            for (int i4 = firstUnresolved = Math.max(lastValidPkg + 1, lastValidClass + 1); i4 < tokens.size(); ++i4) {
                marks.add(new ScriptLine.Mark((Integer)tokenStarts.get(i4), (Integer)tokenEnds.get(i4), TokenType.UNDEFINED_VAR, imp));
            }
            if (lastValidPkg >= 0 || lastValidClass >= 0) continue;
            marks.add(new ScriptLine.Mark(pathStart, pathEnd, TokenType.UNDEFINED_VAR, imp));
        }
    }

    private void markClassDeclarations(List<ScriptLine.Mark> marks) {
        Pattern classKeyword = Pattern.compile("\\b(class|interface|enum)\\s+([A-Za-z_][a-zA-Z0-9_]*)");
        Matcher m = classKeyword.matcher(this.text);
        while (m.find()) {
            int implListStart;
            int implIdx;
            int bracePos;
            int scanPos;
            if (this.isExcluded(m.start())) continue;
            marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.KEYWORD));
            String kind = m.group(1);
            TokenType nameType = "interface".equals(kind) ? TokenType.INTERFACE_DECL : ("enum".equals(kind) ? TokenType.ENUM_DECL : TokenType.CLASS_DECL);
            String typeName = m.group(2);
            ScriptTypeInfo typeInfo = this.scriptTypes.get(typeName);
            if (typeInfo == null) {
                for (ScriptTypeInfo candidate : this.scriptTypesByFullName.values()) {
                    if (!candidate.getSimpleName().equals(typeName) || m.start(2) < candidate.getDeclarationOffset() || m.start(2) >= candidate.getBodyEnd()) continue;
                    typeInfo = candidate;
                    break;
                }
            }
            marks.add(new ScriptLine.Mark(m.start(2), m.end(2), nameType, typeInfo));
            for (scanPos = m.end(2); scanPos < this.text.length() && Character.isWhitespace(this.text.charAt(scanPos)); ++scanPos) {
            }
            if (typeInfo != null && typeInfo.hasDeclaredTypeParams()) {
                this.markTypeParamDeclarations(marks, m.end(2), typeInfo);
            }
            if (scanPos < this.text.length() && this.text.charAt(scanPos) == '<') {
                int depth = 1;
                int i = scanPos + 1;
                while (i < this.text.length() && depth > 0) {
                    char c;
                    if ((c = this.text.charAt(i++)) == '<') {
                        ++depth;
                        continue;
                    }
                    if (c != '>') continue;
                    --depth;
                }
                if (depth == 0) {
                    scanPos = i;
                }
            }
            while (scanPos < this.text.length() && Character.isWhitespace(this.text.charAt(scanPos))) {
                ++scanPos;
            }
            if (scanPos + 1 < this.text.length() && this.text.charAt(scanPos) == '(' && this.text.charAt(scanPos + 1) == ')') {
                scanPos += 2;
            }
            for (bracePos = scanPos; bracePos < this.text.length() && this.text.charAt(bracePos) != '{'; ++bracePos) {
            }
            if (bracePos >= this.text.length()) continue;
            String betweenParamsAndBrace = this.text.substring(scanPos, bracePos);
            int extIdx = ScriptDocument.indexOfAtDepthZero(betweenParamsAndBrace, "extends");
            if (extIdx >= 0) {
                int nameEnd;
                int extendsAbsStart = scanPos + extIdx;
                marks.add(new ScriptLine.Mark(extendsAbsStart, extendsAbsStart + 7, TokenType.KEYWORD));
                String afterExtends = betweenParamsAndBrace.substring(extIdx + 7).trim();
                int implIdx2 = ScriptDocument.indexOfAtDepthZero(afterExtends, "implements");
                String parentSection = implIdx2 >= 0 ? afterExtends.substring(0, implIdx2).trim() : afterExtends.trim();
                for (nameEnd = 0; nameEnd < parentSection.length() && (Character.isJavaIdentifierPart(parentSection.charAt(nameEnd)) || parentSection.charAt(nameEnd) == '.'); ++nameEnd) {
                }
                if (nameEnd > 0) {
                    int parentAbsStart;
                    String parentName = parentSection.substring(0, nameEnd);
                    for (parentAbsStart = extendsAbsStart + 7; parentAbsStart < bracePos && Character.isWhitespace(this.text.charAt(parentAbsStart)); ++parentAbsStart) {
                    }
                    this.markExtendsClause(marks, parentAbsStart, parentName);
                    if (nameEnd < parentSection.length() && parentSection.charAt(nameEnd) == '<') {
                        int genStart = parentAbsStart + nameEnd;
                        this.markGenericArgsInExtendsClause(marks, genStart, typeInfo);
                    }
                }
            }
            if ((implIdx = ScriptDocument.indexOfAtDepthZero(betweenParamsAndBrace, "implements")) < 0) continue;
            int implAbsStart = scanPos + implIdx;
            marks.add(new ScriptLine.Mark(implAbsStart, implAbsStart + 10, TokenType.KEYWORD));
            String afterImpl = betweenParamsAndBrace.substring(implIdx + 10).trim();
            for (implListStart = implAbsStart + 10; implListStart < bracePos && Character.isWhitespace(this.text.charAt(implListStart)); ++implListStart) {
            }
            this.markImplementsClause(marks, implListStart, afterImpl);
        }
    }

    private void markGenericArgsInExtendsClause(List<ScriptLine.Mark> marks, int ltPos, ScriptTypeInfo childType) {
        if (ltPos >= this.text.length() || this.text.charAt(ltPos) != '<') {
            return;
        }
        int depth = 1;
        int i = ltPos + 1;
        while (i < this.text.length() && depth > 0) {
            char c;
            if ((c = this.text.charAt(i++)) == '<') {
                ++depth;
                continue;
            }
            if (c != '>') continue;
            --depth;
        }
        if (depth != 0) {
            return;
        }
        int gtPos = i - 1;
        if (childType != null && childType.hasDeclaredTypeParams()) {
            List<TypeParamInfo> typeParams = childType.getDeclaredTypeParams();
            this.markNestedTypeParamUsages(marks, ltPos + 1, gtPos, typeParams);
        }
    }

    private void markEnumConstants(List<ScriptLine.Mark> marks) {
        for (ScriptTypeInfo scriptType : this.scriptTypesByFullName.values()) {
            if (!scriptType.isEnum()) continue;
            for (EnumConstantInfo constant : scriptType.getEnumConstants().values()) {
                FieldInfo fieldInfo = constant.getFieldInfo();
                int start = fieldInfo.getDeclarationOffset();
                int end = start + fieldInfo.getName().length();
                marks.add(new ScriptLine.Mark(start, end, TokenType.ENUM_CONSTANT, fieldInfo));
            }
        }
    }

    private void markExtendsClause(List<ScriptLine.Mark> marks, int clauseStart, String parentName) {
        int actualStart;
        TokenType tokenType;
        String trimmedName = parentName.trim();
        if (trimmedName.isEmpty()) {
            return;
        }
        TypeInfo parentType = this.resolveType(trimmedName, clauseStart);
        if (parentType != null && parentType.isResolved()) {
            tokenType = parentType.getTokenType();
        } else {
            tokenType = TokenType.UNDEFINED_VAR;
            if (parentType == null) {
                parentType = TypeInfo.unresolved(trimmedName, trimmedName);
            }
        }
        String fullClause = parentName;
        for (actualStart = clauseStart; actualStart < clauseStart + fullClause.length() && Character.isWhitespace(this.text.charAt(actualStart)); ++actualStart) {
        }
        marks.add(new ScriptLine.Mark(actualStart, actualStart + trimmedName.length(), tokenType, parentType));
    }

    private void markImplementsClause(List<ScriptLine.Mark> marks, int clauseStart, String implementsList) {
        String[] interfaces = implementsList.split(",");
        int currentPos = clauseStart;
        for (String ifaceName : interfaces) {
            TokenType tokenType;
            int leadingSpaces;
            String trimmedName = ifaceName.trim();
            if (trimmedName.isEmpty()) {
                currentPos += ifaceName.length() + 1;
                continue;
            }
            for (leadingSpaces = 0; leadingSpaces < ifaceName.length() && Character.isWhitespace(ifaceName.charAt(leadingSpaces)); ++leadingSpaces) {
            }
            int actualStart = currentPos + leadingSpaces;
            TypeInfo ifaceType = this.resolveType(trimmedName, actualStart);
            if (ifaceType != null && ifaceType.isResolved()) {
                tokenType = ifaceType.getTokenType();
            } else {
                tokenType = TokenType.UNDEFINED_VAR;
                if (ifaceType == null) {
                    ifaceType = TypeInfo.unresolved(trimmedName, trimmedName);
                }
            }
            marks.add(new ScriptLine.Mark(actualStart, actualStart + trimmedName.length(), tokenType, ifaceType));
            currentPos += ifaceName.length() + 1;
        }
    }

    private void markTypeDeclarations(List<ScriptLine.Mark> marks) {
        if (this.isJavaScript()) {
            this.markJavaTypeVariables(marks);
        }
        Pattern typeStart = Pattern.compile("(?:(?:public|private|protected|static|final|transient|volatile)\\s+)*([a-zA-Z][a-zA-Z0-9_]*)\\s*");
        Matcher m = typeStart.matcher(this.text);
        int searchFrom = 0;
        while (m.find(searchFrom)) {
            boolean atEndOfLine;
            int posAfterType;
            int typeNameStart = m.start(1);
            int typeNameEnd = m.end(1);
            if (this.isExcluded(typeNameStart) || this.isInImportOrPackage(typeNameStart)) {
                searchFrom = m.end();
                continue;
            }
            String typeName = m.group(1);
            for (posAfterType = m.end(1); posAfterType < this.text.length() && Character.isWhitespace(this.text.charAt(posAfterType)); ++posAfterType) {
            }
            String genericContent = null;
            int genericStart = -1;
            int genericEnd = -1;
            if (posAfterType < this.text.length() && this.text.charAt(posAfterType) == '<') {
                int i;
                genericStart = posAfterType;
                int depth = 1;
                for (i = posAfterType + 1; i < this.text.length() && depth > 0; ++i) {
                    char c = this.text.charAt(i);
                    if (c == '<') {
                        ++depth;
                        continue;
                    }
                    if (c != '>') continue;
                    --depth;
                }
                if (depth == 0) {
                    genericEnd = i;
                    genericContent = this.text.substring(genericStart + 1, genericEnd - 1);
                    posAfterType = genericEnd;
                }
            }
            while (posAfterType < this.text.length() && Character.isWhitespace(this.text.charAt(posAfterType))) {
                ++posAfterType;
            }
            int varScanPos = posAfterType;
            while (this.text.startsWith("[]", varScanPos)) {
                marks.add(new ScriptLine.Mark(varScanPos, varScanPos + 2, TokenType.DEFAULT, null));
                varScanPos += 2;
                while (varScanPos < this.text.length() && Character.isWhitespace(this.text.charAt(varScanPos))) {
                    ++varScanPos;
                }
            }
            boolean hasGeneric = genericContent != null && !genericContent.isEmpty();
            boolean followedByVarName = false;
            boolean bl = atEndOfLine = varScanPos >= this.text.length() || this.text.charAt(varScanPos) == '\n';
            if (!atEndOfLine && varScanPos < this.text.length()) {
                char nextChar = this.text.charAt(varScanPos);
                boolean bl2 = followedByVarName = Character.isLetter(nextChar) || nextChar == '_';
            }
            if (hasGeneric || followedByVarName) {
                TypeInfo info = this.resolveType(typeName, typeNameStart);
                TokenType tokenType = info != null && info.isResolved() ? info.getTokenType() : TokenType.UNDEFINED_VAR;
                marks.add(new ScriptLine.Mark(typeNameStart, typeNameEnd, tokenType, info));
                if (hasGeneric && genericStart >= 0) {
                    int contentStart = genericStart + 1;
                    this.markGenericTypesRecursive(genericContent, contentStart, marks, typeNameStart);
                }
            }
            searchFrom = m.end();
        }
    }

    private void markJavaTypeVariables(List<ScriptLine.Mark> marks) {
        Pattern javaTypePattern = Pattern.compile("\\b(var|let|const)\\s+(\\w+)\\s*=\\s*Java\\.type\\s*\\(\\s*[\"']([^\"']+)[\"']\\s*\\)");
        Matcher m = javaTypePattern.matcher(this.text);
        while (m.find()) {
            if (this.isExcluded(m.start())) continue;
            String varName = m.group(2);
            String className = m.group(3);
            int varStart = m.start(2);
            int varEnd = m.end(2);
            TypeInfo classType = this.typeResolver.resolveFullName(className);
            if (classType == null || !classType.isResolved()) continue;
            ClassTypeInfo classRef = new ClassTypeInfo(classType);
            marks.add(new ScriptLine.Mark(varStart, varEnd, TokenType.LOCAL_FIELD, classRef));
        }
    }

    private void markGenericTypesRecursive(String content, int baseOffset, List<ScriptLine.Mark> marks) {
        this.markGenericTypesRecursive(content, baseOffset, marks, -1);
    }

    private void markGenericTypesRecursive(String content, int baseOffset, List<ScriptLine.Mark> marks, int positionContext) {
        if (content == null || content.isEmpty()) {
            return;
        }
        int i = 0;
        while (i < content.length()) {
            TypeInfo typeCheck;
            char c = content.charAt(i);
            if (!Character.isJavaIdentifierStart(c)) {
                ++i;
                continue;
            }
            int start = i;
            while (i < content.length() && Character.isJavaIdentifierPart(content.charAt(i))) {
                ++i;
            }
            String typeName = content.substring(start, i);
            if (GENERIC_KEYWORDS.contains(typeName)) continue;
            int absStart = baseOffset + start;
            int absEnd = baseOffset + i;
            TypeInfo typeInfo = typeCheck = positionContext >= 0 ? this.resolveType(typeName, positionContext) : this.resolveType(typeName);
            if (typeCheck != null && typeCheck.isResolved() && !this.isExcluded(absStart)) {
                TokenType tokenType = typeCheck.getTokenType();
                marks.add(new ScriptLine.Mark(absStart, absEnd, tokenType, typeCheck));
            }
            while (i < content.length() && Character.isWhitespace(content.charAt(i))) {
                ++i;
            }
            if (i >= content.length() || content.charAt(i) != '<') continue;
            int nestedStart = i + 1;
            int depth = 1;
            ++i;
            while (i < content.length() && depth > 0) {
                if (content.charAt(i) == '<') {
                    ++depth;
                } else if (content.charAt(i) == '>') {
                    --depth;
                }
                ++i;
            }
            if (nestedStart >= i - 1) continue;
            String nestedContent = content.substring(nestedStart, i - 1);
            this.markGenericTypesRecursive(nestedContent, baseOffset + nestedStart, marks, positionContext);
        }
    }

    private void markJSDocElements(List<ScriptLine.Mark> marks) {
        Pattern jsDocPattern = Pattern.compile("/\\*\\*([\\s\\S]*?)\\*/");
        Matcher jsDocMatcher = jsDocPattern.matcher(this.text);
        while (jsDocMatcher.find()) {
            int commentStart = jsDocMatcher.start();
            int commentEnd = jsDocMatcher.end();
            boolean insideString = false;
            for (ScriptLine.Mark mark : marks) {
                if (mark.type != TokenType.STRING || commentStart < mark.start || commentEnd > mark.end) continue;
                insideString = true;
                break;
            }
            if (insideString) continue;
            String commentContent = jsDocMatcher.group(0);
            MethodInfo associatedMethod = this.findMethodAfterPosition(commentEnd);
            HashSet<String> methodParamNames = new HashSet<String>();
            if (associatedMethod != null) {
                for (FieldInfo param : associatedMethod.getParameters()) {
                    methodParamNames.add(param.getName());
                }
            }
            ArrayList<int[]> specialRanges = new ArrayList<int[]>();
            Pattern tagPattern = Pattern.compile("@(\\w+)");
            Matcher tagMatcher = tagPattern.matcher(commentContent);
            while (tagMatcher.find()) {
                String afterTagText;
                int afterTag;
                int tagStart = commentStart + tagMatcher.start();
                int tagEnd = commentStart + tagMatcher.end();
                specialRanges.add(new int[]{tagStart, tagEnd});
                String tagName = tagMatcher.group(1);
                TypeInfo tagTypeInfo = null;
                if ("type".equals(tagName)) {
                    afterTag = tagMatcher.end();
                    afterTagText = commentContent.substring(afterTag);
                    Pattern typeRefPattern = Pattern.compile("^\\s*\\{([^}]+)\\}");
                    Matcher typeRefMatcher = typeRefPattern.matcher(afterTagText);
                    if (typeRefMatcher.find()) {
                        String typeName = typeRefMatcher.group(1).trim();
                        tagTypeInfo = this.resolveType(typeName);
                    }
                }
                marks.add(new ScriptLine.Mark(tagStart, tagEnd, TokenType.JSDOC_TAG, tagTypeInfo));
                if (!"param".equals(tagName)) continue;
                afterTag = tagMatcher.end();
                afterTagText = commentContent.substring(afterTag);
                Pattern paramNamePattern = Pattern.compile("^\\s*(?:\\{[^}]*\\}\\s*)?([a-zA-Z_][a-zA-Z0-9_]*)");
                Matcher paramNameMatcher = paramNamePattern.matcher(afterTagText);
                if (!paramNameMatcher.find()) continue;
                String paramName = paramNameMatcher.group(1);
                int paramNameStart = commentStart + afterTag + paramNameMatcher.start(1);
                int paramNameEnd = commentStart + afterTag + paramNameMatcher.end(1);
                boolean paramExists = methodParamNames.contains(paramName);
                TokenType paramTokenType = paramExists ? TokenType.PARAMETER : TokenType.UNDEFINED_VAR;
                specialRanges.add(new int[]{paramNameStart, paramNameEnd});
                marks.add(new ScriptLine.Mark(paramNameStart, paramNameEnd, paramTokenType, null));
            }
            Pattern typePattern = Pattern.compile("\\{([^}]+)\\}");
            Matcher typeMatcher = typePattern.matcher(commentContent);
            while (typeMatcher.find()) {
                int braceStart = commentStart + typeMatcher.start();
                int braceEnd = commentStart + typeMatcher.end();
                specialRanges.add(new int[]{braceStart, braceEnd});
                marks.add(new ScriptLine.Mark(braceStart, braceStart + 1, TokenType.JSDOC_TYPE, null));
                marks.add(new ScriptLine.Mark(braceEnd - 1, braceEnd, TokenType.JSDOC_TYPE, null));
                String typeName = typeMatcher.group(1).trim();
                int typeStart = commentStart + typeMatcher.start(1);
                int typeEnd = commentStart + typeMatcher.end(1);
                TypeInfo resolvedType = this.resolveType(typeName);
                this.addTypeMark(marks, typeStart, typeEnd, resolvedType != null ? resolvedType.getTokenType() : TokenType.UNDEFINED_VAR, resolvedType);
            }
            specialRanges.sort((a, b) -> Integer.compare(a[0], b[0]));
            int lastPos = commentStart;
            for (int[] range : specialRanges) {
                if (lastPos < range[0]) {
                    marks.add(new ScriptLine.Mark(lastPos, range[0], TokenType.COMMENT, null));
                }
                lastPos = range[1];
            }
            if (lastPos >= commentEnd) continue;
            marks.add(new ScriptLine.Mark(lastPos, commentEnd, TokenType.COMMENT, null));
        }
    }

    private MethodInfo findMethodAfterPosition(int position) {
        for (int searchStart = position; searchStart < this.text.length() && Character.isWhitespace(this.text.charAt(searchStart)); ++searchStart) {
        }
        MethodInfo closestMethod = null;
        int closestDistance = Integer.MAX_VALUE;
        for (MethodInfo method : this.methods) {
            int distance;
            if (!method.isDeclaration()) continue;
            int methodStart = method.getFullDeclarationOffset();
            if (methodStart < 0) {
                methodStart = method.getNameOffset();
            }
            if (methodStart < 0 || methodStart < position || methodStart >= position + 200 || (distance = methodStart - position) >= closestDistance) continue;
            closestDistance = distance;
            closestMethod = method;
        }
        for (ScriptTypeInfo scriptType : this.scriptTypesByFullName.values()) {
            for (MethodInfo method : scriptType.getAllMethodsFlat()) {
                int distance;
                if (!method.isDeclaration()) continue;
                int methodStart = method.getFullDeclarationOffset();
                if (methodStart < 0) {
                    methodStart = method.getNameOffset();
                }
                if (methodStart < 0 || methodStart < position || methodStart >= position + 200 || (distance = methodStart - position) >= closestDistance) continue;
                closestDistance = distance;
                closestMethod = method;
            }
        }
        return closestMethod;
    }

    private void markNonJSDocComments(List<ScriptLine.Mark> marks) {
        Matcher m = COMMENT_PATTERN.matcher(this.text);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            String comment = m.group();
            if (comment.startsWith("/**")) continue;
            marks.add(new ScriptLine.Mark(start, end, TokenType.COMMENT, null));
        }
    }

    private void addTypeMark(List<ScriptLine.Mark> marks, int start, int end, TokenType type, Object metadata) {
        int coreEnd;
        int bracketStart = this.text.indexOf(91, start);
        int n = coreEnd = bracketStart >= start && bracketStart < end ? bracketStart : end;
        if (coreEnd < end) {
            marks.add(new ScriptLine.Mark(coreEnd, end, TokenType.DEFAULT, null));
        }
        marks.add(new ScriptLine.Mark(start, coreEnd, type, metadata));
    }

    private void markMethodDeclarations(List<ScriptLine.Mark> marks) {
        Matcher m = METHOD_DECL_PATTERN.matcher(ScriptDocument.normalizeGenericNewlines(this.text));
        while (m.find()) {
            String returnType;
            if (this.isExcluded(m.start()) || this.isExcluded(m.start(2)) || this.hasExcludedRange(m.start(1), m.end(1)) || (returnType = m.group(1)).equals("class") || returnType.equals("interface") || returnType.equals("enum") || returnType.equals("new")) continue;
            int methodDeclStart = m.start();
            int methodNameStart = m.start(2);
            MethodInfo methodInfo = null;
            for (MethodInfo method : this.getAllMethods()) {
                boolean matchesNameStart;
                boolean matchesDeclStart = method.getTypeOffset() == methodDeclStart || method.getFullDeclarationOffset() == methodDeclStart;
                boolean bl = matchesNameStart = method.getNameOffset() == methodNameStart;
                if (!matchesDeclStart && !matchesNameStart) continue;
                methodInfo = method;
                break;
            }
            TokenType returnToken = TokenType.UNDEFINED_VAR;
            if (methodInfo != null) {
                returnToken = methodInfo.getReturnType().getTokenType();
            }
            int returnStart = m.start(1);
            int returnEnd = m.end(1);
            this.addTypeMark(marks, returnStart, returnEnd, returnToken, methodInfo != null ? methodInfo.getReturnType() : null);
            marks.add(new ScriptLine.Mark(m.start(2), m.end(2), TokenType.METHOD_DECL, methodInfo));
        }
    }

    private void markMethodCalls(List<ScriptLine.Mark> marks) {
        Matcher m = METHOD_CALL_PATTERN.matcher(this.text);
        while (m.find()) {
            TypeInfo expectedType;
            MethodCallInfo callInfo;
            int closeParen;
            int openParen;
            int nameStart = m.start(1);
            int nameEnd = m.end(1);
            String methodName = m.group(1);
            if (this.isExcluded(nameStart) || this.isKeyword(methodName) || this.isInImportOrPackage(nameStart)) continue;
            boolean skip = false;
            for (MethodInfo decl : this.methods) {
                if (decl.getNameOffset() != nameStart) continue;
                skip = true;
            }
            if (skip) continue;
            for (openParen = nameEnd; openParen < this.text.length() && Character.isWhitespace(this.text.charAt(openParen)); ++openParen) {
            }
            if (openParen >= this.text.length() || this.text.charAt(openParen) != '(' || (closeParen = this.findMatchingParen(openParen)) < 0) continue;
            if (methodName.equals("super")) {
                this.handleSuperConstructorCall(marks, nameStart, nameEnd, openParen, closeParen);
                continue;
            }
            List<MethodCallInfo.Argument> arguments = this.parseMethodArguments(openParen + 1, closeParen, null, null);
            boolean isStaticAccess = this.isStaticAccessCall(nameStart);
            TypeInfo receiverType = this.resolveReceiverChain(nameStart);
            MethodInfo resolvedMethod = null;
            if (receiverType != null) {
                SyntheticType syntheticType = null;
                if (this.isJavaScript()) {
                    syntheticType = this.typeResolver.getSyntheticType(receiverType.getSimpleName());
                }
                if (syntheticType != null && syntheticType.hasMethod(methodName)) {
                    resolvedMethod = syntheticType.getMethodInfo(methodName);
                    SyntheticMethod synMethod = syntheticType.getMethod(methodName);
                    TypeInfo dynamicReturnType = null;
                    if (synMethod != null) {
                        String argsText = this.text.substring(openParen + 1, closeParen);
                        String[] strArgs = TypeResolver.parseStringArguments(argsText);
                        dynamicReturnType = synMethod.resolveReturnType(strArgs);
                    }
                    MethodCallInfo callInfo2 = new MethodCallInfo(methodName, nameStart, nameEnd, openParen, closeParen, arguments, receiverType, resolvedMethod, false);
                    if (dynamicReturnType != null) {
                        callInfo2.setResolvedReturnType(dynamicReturnType);
                    }
                    callInfo2.validate();
                    this.methodCalls.add(callInfo2);
                    marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.METHOD_CALL, callInfo2));
                    continue;
                }
                boolean hasMethod = false;
                TypeInfo rawReceiver = receiverType.getRawType();
                hasMethod = rawReceiver instanceof ScriptTypeInfo ? ((ScriptTypeInfo)rawReceiver).hasMethodInHierarchy(methodName) : receiverType.hasMethod(methodName);
                if (hasMethod) {
                    TypeInfo[] argTypes = (TypeInfo[])arguments.stream().map(MethodCallInfo.Argument::getResolvedType).toArray(TypeInfo[]::new);
                    resolvedMethod = receiverType.getBestMethodOverload(methodName, argTypes);
                    if (isStaticAccess && resolvedMethod != null && !resolvedMethod.isStatic()) {
                        TokenErrorMessage errorMsg = TokenErrorMessage.from("Cannot call non-static method '" + methodName + "' from static context '" + receiverType.getSimpleName() + "'").clearOtherErrors();
                        marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.UNDEFINED_VAR, errorMsg));
                        continue;
                    }
                    if (resolvedMethod != null && resolvedMethod.getParameters().size() == arguments.size()) {
                        arguments = this.parseMethodArguments(openParen + 1, closeParen, resolvedMethod, receiverType);
                    }
                    callInfo = new MethodCallInfo(methodName, nameStart, nameEnd, openParen, closeParen, arguments, receiverType, resolvedMethod, isStaticAccess);
                    if (!this.isFollowedByDot(closeParen) && (expectedType = this.findExpectedTypeAtPosition(nameStart)) != null) {
                        callInfo.setExpectedType(expectedType);
                    }
                    callInfo.validate();
                    this.methodCalls.add(callInfo);
                    marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.METHOD_CALL, callInfo));
                    continue;
                }
                marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.UNDEFINED_VAR));
                continue;
            }
            boolean hasDot = this.isPrecededByDot(nameStart);
            if (hasDot) {
                marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.UNDEFINED_VAR));
                continue;
            }
            if (this.isScriptMethod(methodName)) {
                TypeInfo expectedType2;
                TypeInfo[] argTypes = (TypeInfo[])arguments.stream().map(MethodCallInfo.Argument::getResolvedType).toArray(TypeInfo[]::new);
                resolvedMethod = this.getScriptMethodInfo(methodName, argTypes);
                if (resolvedMethod != null && resolvedMethod.getParameters().size() == arguments.size()) {
                    arguments = this.parseMethodArguments(openParen + 1, closeParen, resolvedMethod, null);
                }
                if (resolvedMethod != null && this.isMethodFromScriptType(resolvedMethod) && !resolvedMethod.isStatic()) {
                    marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.UNDEFINED_VAR));
                    continue;
                }
                MethodCallInfo callInfo3 = new MethodCallInfo(methodName, nameStart, nameEnd, openParen, closeParen, arguments, null, resolvedMethod);
                if (!this.isFollowedByDot(closeParen) && (expectedType2 = this.findExpectedTypeAtPosition(nameStart)) != null) {
                    callInfo3.setExpectedType(expectedType2);
                }
                callInfo3.validate();
                this.methodCalls.add(callInfo3);
                marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.METHOD_CALL, callInfo3));
                continue;
            }
            if (this.isGlobalEngineFunction(methodName)) {
                JSMethodInfo jsGlobalMethod = JSTypeRegistry.getInstance().getGlobalEngineFunction(methodName);
                MethodInfo resolvedGlobal = MethodInfo.fromJSMethod(jsGlobalMethod, null);
                List<MethodCallInfo.Argument> globalArguments = arguments;
                if (resolvedGlobal.getParameters().size() == arguments.size()) {
                    globalArguments = this.parseMethodArguments(openParen + 1, closeParen, resolvedGlobal, null);
                }
                callInfo = new MethodCallInfo(methodName, nameStart, nameEnd, openParen, closeParen, globalArguments, null, resolvedGlobal);
                if (!this.isFollowedByDot(closeParen) && (expectedType = this.findExpectedTypeAtPosition(nameStart)) != null) {
                    callInfo.setExpectedType(expectedType);
                }
                callInfo.validate();
                this.methodCalls.add(callInfo);
                marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.METHOD_CALL, callInfo));
                continue;
            }
            ScriptTypeInfo callerType = this.findEnclosingScriptType(nameStart);
            if (callerType != null && callerType.hasMethodInHierarchy(methodName)) {
                TypeInfo[] argTypes = (TypeInfo[])arguments.stream().map(MethodCallInfo.Argument::getResolvedType).toArray(TypeInfo[]::new);
                MethodInfo implicitMethod = callerType.getBestMethodOverload(methodName, argTypes);
                if (implicitMethod != null && implicitMethod.getParameters().size() == arguments.size()) {
                    arguments = this.parseMethodArguments(openParen + 1, closeParen, implicitMethod, callerType);
                }
                callInfo = new MethodCallInfo(methodName, nameStart, nameEnd, openParen, closeParen, arguments, callerType, implicitMethod);
                if (!this.isFollowedByDot(closeParen) && (expectedType = this.findExpectedTypeAtPosition(nameStart)) != null) {
                    callInfo.setExpectedType(expectedType);
                }
                callInfo.validate();
                this.methodCalls.add(callInfo);
                marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.METHOD_CALL, callInfo));
                continue;
            }
            marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.UNDEFINED_VAR));
        }
    }

    public boolean isKeyword(String word) {
        HashSet<String> keywords = new HashSet<String>(Arrays.asList(TypeChecker.getJavaKeywords()));
        if (this.isJavaScript()) {
            keywords.addAll(Arrays.asList(TypeChecker.getJavaScriptKeywords()));
        }
        return keywords.contains(word);
    }

    private boolean isStaticAccessCall(int methodNameStart) {
        int pos;
        for (pos = methodNameStart - 1; pos >= 0 && Character.isWhitespace(this.text.charAt(pos)); --pos) {
        }
        if (pos < 0 || this.isExcluded(pos) || this.text.charAt(pos) != '.') {
            return false;
        }
        --pos;
        while (pos >= 0 && Character.isWhitespace(this.text.charAt(pos))) {
            --pos;
        }
        if (pos < 0 || this.isExcluded(pos)) {
            return false;
        }
        char c = this.text.charAt(pos);
        if (c == ')' || c == ']') {
            return false;
        }
        if (!Character.isJavaIdentifierPart(c)) {
            return false;
        }
        int identEnd = pos + 1;
        while (pos >= 0 && Character.isJavaIdentifierPart(this.text.charAt(pos))) {
            --pos;
        }
        int identStart = pos + 1;
        String ident = this.text.substring(identStart, identEnd);
        while (pos >= 0 && Character.isWhitespace(this.text.charAt(pos))) {
            --pos;
        }
        if (pos >= 0 && !this.isExcluded(pos) && this.text.charAt(pos) == '.') {
            return false;
        }
        if (ident.isEmpty()) {
            return false;
        }
        return TypeResolver.isStaticAccessExpression(ident, identStart, this);
    }

    private MethodCallInfo findMethodCallContainingPosition(int position) {
        for (MethodCallInfo call : this.methodCalls) {
            if (position < call.getOpenParenOffset() || position > call.getCloseParenOffset()) continue;
            for (MethodCallInfo.Argument arg : call.getArguments()) {
                if (position < arg.getStartOffset() || position > arg.getEndOffset()) continue;
                return call;
            }
        }
        return null;
    }

    private int findMatchingParen(int openPos) {
        if (openPos < 0 || openPos >= this.text.length() || this.text.charAt(openPos) != '(') {
            return -1;
        }
        int depth = 1;
        boolean inString = false;
        boolean inChar = false;
        char stringChar = '\u0000';
        for (int i = openPos + 1; i < this.text.length(); ++i) {
            char prev;
            char c = this.text.charAt(i);
            char c2 = prev = i > 0 ? this.text.charAt(i - 1) : (char)'\u0000';
            if (!(inChar || c != '\"' && c != '\'' || prev == '\\')) {
                if (!inString) {
                    inString = true;
                    stringChar = c;
                    continue;
                }
                if (c != stringChar) continue;
                inString = false;
                continue;
            }
            if (inString || this.isExcluded(i)) continue;
            if (c == '(') {
                ++depth;
                continue;
            }
            if (c != ')' || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    public List<MethodCallInfo.Argument> parseMethodArguments(int start, int end, MethodInfo methodInfo, TypeInfo receiverType) {
        ArrayList<MethodCallInfo.Argument> args = new ArrayList<MethodCallInfo.Argument>();
        if (start >= end) {
            return args;
        }
        end = Math.min(end, this.text.length());
        int depth = 0;
        int argStart = start;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = start; i <= end; ++i) {
            char prev;
            if (i == end || depth == 0 && !inString && this.text.charAt(i) == ',') {
                String argText = this.text.substring(argStart, i).trim();
                if (!argText.isEmpty()) {
                    String samConflictError;
                    GenericContext ctx;
                    TypeInfo substituted;
                    int actualEnd;
                    int actualStart;
                    for (actualStart = argStart; actualStart < i && Character.isWhitespace(this.text.charAt(actualStart)); ++actualStart) {
                    }
                    for (actualEnd = i; actualEnd > actualStart && Character.isWhitespace(this.text.charAt(actualEnd - 1)); --actualEnd) {
                    }
                    TypeInfo expectedParamType = null;
                    if (methodInfo != null && args.size() < methodInfo.getParameters().size()) {
                        FieldInfo parameter = methodInfo.getParameters().get(args.size());
                        expectedParamType = parameter.getTypeInfo();
                    }
                    TypeInfo argType = this.resolveArgumentType(argText, actualStart, expectedParamType);
                    if (receiverType != null && argType != null && GenericContext.hasGenerics(receiverType) && (substituted = (ctx = GenericContext.forReceiver(receiverType)).substitute(argType)) != null && substituted.isResolved()) {
                        argType = substituted;
                    }
                    if ((samConflictError = CURRENT_SAM_CONFLICT_ERROR.get()) != null) {
                        CURRENT_SAM_CONFLICT_ERROR.remove();
                        args.add(new MethodCallInfo.Argument(argText, actualStart, actualEnd, argType, false, samConflictError));
                    } else {
                        args.add(new MethodCallInfo.Argument(argText, actualStart, actualEnd, argType, true, null));
                    }
                }
                argStart = i + 1;
                continue;
            }
            char c = this.text.charAt(i);
            char c2 = prev = i > start ? this.text.charAt(i - 1) : (char)'\u0000';
            if (!(inString || c != '\"' && c != '\'' || prev == '\\')) {
                inString = true;
                stringChar = c;
            } else if (inString && c == stringChar && prev != '\\') {
                inString = false;
            }
            if (inString) continue;
            if (c == '(' || c == '[' || c == '{' || c == '<') {
                ++depth;
                continue;
            }
            if (c == ')' || c == ']' || c == '}') {
                --depth;
                continue;
            }
            if (c != '>' || prev == '-') continue;
            --depth;
        }
        return args;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private TypeInfo resolveArgumentType(String argText, int position, TypeInfo expectedType) {
        String[] parts;
        if ((argText = argText.trim()).matches("^[A-Za-z_][a-zA-Z0-9_<>\\[\\],\\s\\n\\r]*\\s+[a-zA-Z_][a-zA-Z0-9_]*$") && (parts = argText.split("\\s+")).length >= 2) {
            StringBuilder typeBuilder = new StringBuilder();
            for (int i = 0; i < parts.length - 1; ++i) {
                if (i > 0) {
                    typeBuilder.append(" ");
                }
                typeBuilder.append(parts[i]);
            }
            String typeName = typeBuilder.toString();
            return this.resolveType(typeName);
        }
        if (expectedType != null) {
            ExpressionTypeResolver.CURRENT_EXPECTED_TYPE = expectedType;
            try {
                TypeInfo typeInfo = this.resolveExpressionType(argText, position);
                return typeInfo;
            }
            finally {
                ExpressionTypeResolver.CURRENT_EXPECTED_TYPE = null;
            }
        }
        return this.resolveExpressionType(argText, position);
    }

    private boolean hasExcessivePrecision(String numLiteral, int maxDigits) {
        String cleaned = numLiteral.trim();
        if (cleaned.startsWith("-") || cleaned.startsWith("+")) {
            cleaned = cleaned.substring(1);
        }
        if (cleaned.endsWith("f") || cleaned.endsWith("F") || cleaned.endsWith("d") || cleaned.endsWith("D") || cleaned.endsWith("l") || cleaned.endsWith("L")) {
            cleaned = cleaned.substring(0, cleaned.length() - 1);
        }
        cleaned = cleaned.replace(".", "");
        while (cleaned.startsWith("0") && cleaned.length() > 1) {
            cleaned = cleaned.substring(1);
        }
        if (cleaned.equals("0") || cleaned.isEmpty()) {
            return false;
        }
        int significantDigits = cleaned.length();
        return significantDigits > maxDigits;
    }

    private TypeInfo narrowIntLiteral(String literalText) {
        TypeInfo narrowed;
        if (this.isJavaScript()) {
            return TypeInfo.NUMBER;
        }
        TypeInfo expectedType = ExpressionTypeResolver.CURRENT_EXPECTED_TYPE;
        if (expectedType != null && (narrowed = TypeChecker.narrowLiteralToExpectedType(literalText, expectedType)) != null) {
            return narrowed;
        }
        return TypeInfo.fromPrimitive("int");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public TypeInfo resolveExpressionType(String expr, int position) {
        ScriptTypeInfo enclosingType;
        Matcher newMatcher;
        expr = expr.trim();
        if ((expr = this.stripLineComments(expr)).endsWith(".")) {
            expr = expr.substring(0, expr.length() - 1).trim();
        }
        if (expr.isEmpty()) {
            return null;
        }
        if (this.isJavaScript() && expr.charAt(0) == '{') {
            ObjectLiteralParser.ObjectLiteralAnalysis analysis = this.objectLiterals.get(position);
            if (analysis == null) {
                analysis = ObjectLiteralParser.parse(expr, position, true, false, this::resolveExpressionType, this::getScriptMethodInfo);
            }
            if (analysis == null) {
                return TypeInfo.ANY;
            }
            if (analysis.supportsInference && analysis.inferredType != null) return analysis.inferredType;
            return TypeInfo.ANY;
        }
        LambdaCacheEntry cachedLambda = this.lambdaCache.get(position);
        if (cachedLambda != null && cachedLambda.expectedType != null) {
            return cachedLambda.expectedType;
        }
        if (this.containsOperators(expr) || expr.contains("::") || expr.contains("->") || expr.contains("=>") || expr.startsWith("(") || this.looksLikeFunctionOrLambda(expr)) {
            return this.resolveExpressionWithParserAPI(expr, position);
        }
        if (this.isJavaScript() && expr.startsWith("[") && expr.endsWith("]")) {
            String inner = expr.substring(1, expr.length() - 1).trim();
            return TypeInfo.arrayOf(this.unifyJsArrayElementType(inner, position + 1));
        }
        if (expr.startsWith("[") || expr.startsWith("]")) {
            return null;
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
        if (expr.equals("null")) {
            return TypeInfo.NULL;
        }
        if (expr.matches("-?\\d*\\.?\\d+[fF]")) {
            if (this.isJavaScript()) {
                return TypeInfo.NUMBER;
            }
            if (!this.hasExcessivePrecision(expr, 7)) return TypeInfo.fromPrimitive("float");
            return TypeInfo.fromPrimitive("double");
        }
        if (expr.matches("-?\\d*\\.\\d+[dD]?") || expr.matches("-?\\d+\\.[dD]?") || expr.matches("-?\\d+[dD]")) {
            if (this.isJavaScript()) {
                return TypeInfo.NUMBER;
            }
            if (!this.hasExcessivePrecision(expr, 15)) return TypeInfo.fromPrimitive("double");
            return null;
        }
        if (expr.matches("-?\\d+[lL]")) {
            if (!this.isJavaScript()) return TypeInfo.fromPrimitive("long");
            return TypeInfo.NUMBER;
        }
        if (expr.matches("[-+]?0[xX][0-9a-fA-F][0-9a-fA-F_]*")) {
            return this.narrowIntLiteral(expr);
        }
        if (expr.matches("[-+]?0[bB][01][01_]*")) {
            return this.narrowIntLiteral(expr);
        }
        if (expr.matches("[-+]?\\d[\\d_]*")) {
            return this.narrowIntLiteral(expr);
        }
        if (expr.equals("this")) {
            return this.resolveThisType(position);
        }
        if (expr.startsWith("new ") && (newMatcher = NEW_TYPE_PATTERN.matcher(expr)).find()) {
            int i;
            String typeName = newMatcher.group(1);
            FieldInfo varInfo = this.resolveVariable(typeName, position);
            if (varInfo != null && varInfo.getTypeInfo() instanceof ClassTypeInfo) {
                return ((ClassTypeInfo)varInfo.getTypeInfo()).getInstanceType();
            }
            if (this.isJavaScript() && typeName.equals("Array")) {
                String rest = expr.substring(newMatcher.end()).trim();
                String argsText = rest.startsWith("(") && rest.endsWith(")") ? rest.substring(1, rest.length() - 1).trim() : "";
                return TypeInfo.arrayOf(this.unifyJsArrayElementType(argsText, position));
            }
            TypeInfo baseType = this.resolveType(typeName, position);
            if (baseType == null) {
                return null;
            }
            String typeArgsClause = newMatcher.group(2);
            if (typeArgsClause != null && !typeArgsClause.trim().isEmpty()) {
                int typeArgsTextOffset = position + newMatcher.start(2);
                baseType = this.parameterizeWithClause(baseType, typeArgsClause, position, typeArgsTextOffset);
            }
            String rest = expr.substring(newMatcher.end());
            int dims = 0;
            for (i = 0; i < rest.length() && rest.charAt(i) != '('; ++i) {
                if (rest.charAt(i) != '[') continue;
                ++dims;
                i = rest.indexOf(93, i);
            }
            for (i = 0; i < dims; ++i) {
                baseType = TypeInfo.arrayOf(baseType);
            }
            return baseType;
        }
        List<ChainSegment> segments = this.parseExpressionChain(expr);
        if (segments.isEmpty()) {
            return null;
        }
        ChainSegment first = segments.get(0);
        TypeInfo currentType = null;
        if (first.name.equals("this")) {
            String fieldName;
            currentType = this.resolveThisType(position);
            if (currentType == null && segments.size() > 1 && !segments.get((int)1).isMethodCall && this.globalFields.containsKey(fieldName = segments.get((int)1).name)) {
                currentType = this.globalFields.get(fieldName).getTypeInfo();
                currentType = this.applyBracketAccess(currentType, segments.get(1));
                for (int i = 2; i < segments.size(); ++i) {
                    currentType = this.resolveChainSegment(currentType, segments.get(i));
                    if ((currentType = this.applyBracketAccess(currentType, segments.get(i))) != null) continue;
                    return null;
                }
                return currentType;
            }
        } else if (first.name.equals("super")) {
            enclosingType = this.findEnclosingScriptType(position);
            if (enclosingType == null || !enclosingType.hasSuperClass()) return null;
            currentType = enclosingType.getSuperClass();
        } else {
            TypeInfo typeCheck = this.resolveType(first.name, position);
            if (typeCheck != null && typeCheck.isResolved()) {
                currentType = typeCheck;
            }
        }
        if (currentType == null && !first.isMethodCall) {
            FieldInfo varInfo = this.resolveVariable(first.name, position);
            if (varInfo != null) {
                currentType = varInfo.getTypeInfo();
            } else if (this.isScriptMethod(first.name)) {
                TypeInfo expectedType = ExpressionTypeResolver.CURRENT_EXPECTED_TYPE;
                TypeInfo samType = this.resolveScriptMethodAsSam(first.name, expectedType);
                if (samType != null) {
                    currentType = samType;
                } else if (this.isJavaScript()) {
                    currentType = TypeInfo.unresolved("<script_method_ref>", "__script_method_ref__");
                }
            }
        } else {
            MethodInfo implicitMethod;
            MethodInfo scriptMethod;
            if (this.isScriptMethod(first.name) && (scriptMethod = this.getScriptMethodInfo(first.name)) != null) {
                currentType = scriptMethod.getReturnType();
            }
            if (currentType == null && this.isGlobalEngineFunction(first.name)) {
                currentType = this.getGlobalEngineFunctionReturnType(first.name);
            }
            if (currentType == null && (enclosingType = this.findEnclosingScriptType(position)) != null && enclosingType.hasMethodInHierarchy(first.name) && (implicitMethod = enclosingType.getMethodInfoInHierarchy(first.name)) != null) {
                currentType = implicitMethod.getReturnType();
            }
        }
        currentType = this.applyBracketAccess(currentType, first);
        for (int i = 1; i < segments.size(); ++i) {
            currentType = this.resolveChainSegment(currentType, segments.get(i));
            if ((currentType = this.applyBracketAccess(currentType, segments.get(i))) != null) continue;
            return null;
        }
        return currentType;
    }

    private TypeInfo resolveCastOrParenthesizedExpression(String expr, int position) {
        return CastExpressionResolver.resolveCastOrParenthesizedExpression(expr, position, this::resolveType, this::resolveExpressionType, this::parseExpressionChain, this::resolveChainSegment);
    }

    private List<ChainSegment> parseExpressionChain(String expr) {
        ArrayList<ChainSegment> segments = new ArrayList<ChainSegment>();
        int i = 0;
        while (i < expr.length()) {
            while (i < expr.length() && Character.isWhitespace(expr.charAt(i))) {
                ++i;
            }
            if (i >= expr.length()) break;
            int start = i;
            while (i < expr.length() && Character.isJavaIdentifierPart(expr.charAt(i))) {
                ++i;
            }
            if (i == start) {
                ++i;
                continue;
            }
            String name = expr.substring(start, i);
            while (i < expr.length() && Character.isWhitespace(expr.charAt(i))) {
                ++i;
            }
            boolean isMethodCall = false;
            String arguments = null;
            if (i < expr.length() && expr.charAt(i) == '(') {
                isMethodCall = true;
                int argsStart = i + 1;
                int depth = 1;
                ++i;
                while (i < expr.length() && depth > 0) {
                    char c = expr.charAt(i);
                    if (c == '(') {
                        ++depth;
                    } else if (c == ')') {
                        --depth;
                    }
                    ++i;
                }
                int argsEnd = i - 1;
                arguments = argsEnd > argsStart ? expr.substring(argsStart, argsEnd) : "";
            }
            boolean hasArrayAccess = false;
            String bracketKey = null;
            while (i < expr.length() && Character.isWhitespace(expr.charAt(i))) {
                ++i;
            }
            if (i < expr.length() && expr.charAt(i) == '[') {
                char q;
                hasArrayAccess = true;
                int bracketStart = i + 1;
                int depth = 1;
                ++i;
                while (i < expr.length() && depth > 0) {
                    char c = expr.charAt(i);
                    if (c == '[') {
                        ++depth;
                    } else if (c == ']') {
                        --depth;
                    }
                    ++i;
                }
                String bracketContent = expr.substring(bracketStart, i - 1).trim();
                if (bracketContent.length() >= 2 && ((q = bracketContent.charAt(0)) == '\"' || q == '\'') && bracketContent.charAt(bracketContent.length() - 1) == q) {
                    bracketKey = bracketContent.substring(1, bracketContent.length() - 1);
                }
            }
            segments.add(new ChainSegment(name, start, i, isMethodCall, arguments, hasArrayAccess, bracketKey));
            while (i < expr.length() && Character.isWhitespace(expr.charAt(i))) {
                ++i;
            }
            if (i >= expr.length() || expr.charAt(i) != '.') continue;
            ++i;
        }
        return segments;
    }

    TypeInfo resolveReceiverChain(int methodNameStart) {
        int scanPos;
        for (scanPos = methodNameStart - 1; scanPos >= 0 && Character.isWhitespace(this.text.charAt(scanPos)); --scanPos) {
        }
        if (scanPos < 0 || this.isExcluded(scanPos) || this.text.charAt(scanPos) != '.') {
            return null;
        }
        int[] bounds = this.findReceiverBoundsBefore(scanPos);
        if (bounds == null) {
            return null;
        }
        int start = bounds[0];
        int end = bounds[1];
        String receiverExpr = this.text.substring(start, end).trim();
        if (receiverExpr.isEmpty()) {
            return null;
        }
        return this.resolveExpressionType(receiverExpr, start);
    }

    private TypeInfo[] parseArgumentTypes(String argsText, int position) {
        if (argsText == null || argsText.trim().isEmpty()) {
            return new TypeInfo[0];
        }
        ArrayList<String> args = new ArrayList<String>();
        int depth = 0;
        int start = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = 0; i < argsText.length(); ++i) {
            char c = argsText.charAt(i);
            if (!(c != '\"' && c != '\'' || i != 0 && argsText.charAt(i - 1) == '\\')) {
                if (!inString) {
                    inString = true;
                    stringChar = c;
                    continue;
                }
                if (c != stringChar) continue;
                inString = false;
                continue;
            }
            if (inString) continue;
            if (c == '(' || c == '[' || c == '{') {
                ++depth;
                continue;
            }
            if (c == ')' || c == ']' || c == '}') {
                --depth;
                continue;
            }
            if (c != ',' || depth != 0) continue;
            args.add(argsText.substring(start, i).trim());
            start = i + 1;
        }
        if (start < argsText.length()) {
            args.add(argsText.substring(start).trim());
        }
        TypeInfo[] argTypes = new TypeInfo[args.size()];
        for (int i = 0; i < args.size(); ++i) {
            argTypes[i] = this.resolveExpressionType((String)args.get(i), position);
        }
        return argTypes;
    }

    private TypeInfo unifyJsArrayElementType(String argsText, int position) {
        if (argsText.isEmpty()) {
            return TypeInfo.ANY;
        }
        TypeInfo[] elementTypes = this.parseArgumentTypes(argsText, position);
        if (elementTypes.length == 1 && TypeInfo.NUMBER.equals(elementTypes[0])) {
            return TypeInfo.ANY;
        }
        TypeInfo common = null;
        boolean allSame = true;
        for (TypeInfo t : elementTypes) {
            boolean isUnresolvable;
            boolean bl = isUnresolvable = t == null || TypeInfo.NULL.equals(t);
            if (isUnresolvable) continue;
            if (common == null) {
                common = t;
                continue;
            }
            if (common.equals(t)) continue;
            allSame = false;
            break;
        }
        return allSame && common != null ? common : TypeInfo.ANY;
    }

    private String stripLineComments(String expr) {
        if (!expr.contains("//")) {
            return expr;
        }
        String[] lines = expr.split("\n", -1);
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            int ci = ScriptDocument.findLineCommentStart(line);
            String part = (ci >= 0 ? line.substring(0, ci) : line).trim();
            if (part.isEmpty()) continue;
            if (result.length() > 0) {
                result.append(' ');
            }
            result.append(part);
        }
        return result.toString().trim();
    }

    private static int findLineCommentStart(String line) {
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = 0; i < line.length(); ++i) {
            char c = line.charAt(i);
            if (inString) {
                if (c == '\\' && i + 1 < line.length()) {
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
            if (c != '/' || i + 1 >= line.length() || line.charAt(i + 1) != '/') continue;
            return i;
        }
        return -1;
    }

    private TypeInfo applyBracketAccess(TypeInfo type, ChainSegment seg) {
        if (type == null || !seg.hasArrayAccess) {
            return type;
        }
        if (seg.bracketKey != null) {
            FieldInfo f = type.getFieldInfo(seg.bracketKey);
            return f != null ? f.getTypeInfo() : TypeInfo.ANY;
        }
        if (type.isSyntheticObjectLiteralType()) {
            return TypeInfo.ANY;
        }
        return this.unwrapArrayElement(type);
    }

    private TypeInfo unwrapArrayElement(TypeInfo arrayType) {
        if (arrayType == null) {
            return null;
        }
        TypeInfo el = arrayType.getElementType();
        if (el != null) {
            return el;
        }
        String name = arrayType.getFullName();
        if (name != null && name.endsWith("[]")) {
            return this.resolveType(name.substring(0, name.length() - 2));
        }
        if (arrayType.getJavaClass() != null && arrayType.getJavaClass().isArray()) {
            return TypeInfo.fromClass(arrayType.getJavaClass().getComponentType());
        }
        return TypeInfo.ANY;
    }

    private TypeInfo resolveChainSegment(TypeInfo currentType, ChainSegment segment) {
        ScriptTypeInfo innerClass;
        SyntheticType syntheticType;
        if (currentType == null || !currentType.isResolved()) {
            return null;
        }
        if (this.isJavaScript() && (syntheticType = this.typeResolver.getSyntheticType(currentType.getSimpleName())) != null) {
            return this.resolveSyntheticChainSegment(syntheticType, segment);
        }
        if (segment.isMethodCall) {
            boolean hasMethod = false;
            TypeInfo rawType = currentType.getRawType();
            hasMethod = rawType instanceof ScriptTypeInfo ? ((ScriptTypeInfo)rawType).hasMethodInHierarchy(segment.name) : currentType.hasMethod(segment.name);
            if (hasMethod) {
                TypeInfo[] argTypes = this.parseArgumentTypes(segment.arguments, segment.start);
                MethodInfo methodInfo = currentType.getBestMethodOverload(segment.name, argTypes);
                return methodInfo != null ? methodInfo.getReturnType() : null;
            }
            return null;
        }
        boolean hasField = false;
        FieldInfo fieldInfo = null;
        TypeInfo rawType = currentType.getRawType();
        if (rawType instanceof ScriptTypeInfo) {
            hasField = ((ScriptTypeInfo)rawType).hasFieldInHierarchy(segment.name);
            if (hasField) {
                fieldInfo = currentType.getFieldInfo(segment.name);
            }
        } else {
            hasField = currentType.hasField(segment.name);
            if (hasField) {
                fieldInfo = currentType.getFieldInfo(segment.name);
            }
        }
        if (hasField) {
            return fieldInfo != null ? fieldInfo.getTypeInfo() : null;
        }
        if (rawType instanceof ScriptTypeInfo && (innerClass = ((ScriptTypeInfo)rawType).getInnerClass(segment.name)) != null) {
            return innerClass;
        }
        if (currentType.getJavaClass() != null) {
            try {
                for (Class<?> nested : currentType.getJavaClass().getDeclaredClasses()) {
                    if (!Modifier.isPublic(nested.getModifiers()) || !nested.getSimpleName().equals(segment.name)) continue;
                    return TypeInfo.fromClass(nested);
                }
            }
            catch (SecurityException securityException) {
                // empty catch block
            }
        }
        return null;
    }

    private TypeInfo resolveSyntheticChainSegment(SyntheticType syntheticType, ChainSegment segment) {
        if (segment.isMethodCall) {
            SyntheticMethod method = syntheticType.getMethod(segment.name);
            if (method != null) {
                String[] args;
                TypeInfo resolved;
                if (segment.arguments != null && (resolved = method.resolveReturnType(args = TypeResolver.parseStringArguments(segment.arguments))) != null) {
                    return resolved;
                }
                TypeInfo returnType = this.typeResolver.resolve(method.returnType);
                return returnType != null ? returnType : TypeInfo.unresolved(method.returnType, method.returnType);
            }
        } else {
            SyntheticField field = syntheticType.getField(segment.name);
            if (field != null) {
                TypeInfo fieldType = this.typeResolver.resolve(field.typeName);
                return fieldType != null ? fieldType : TypeInfo.unresolved(field.typeName, field.typeName);
            }
        }
        return null;
    }

    TypeInfo resolveThisType(int position) {
        TypeInfo objectType;
        Object innermostScope = this.findInnermostScopeAt(position);
        if (innermostScope instanceof InnerCallableScope && (objectType = ((InnerCallableScope)innermostScope).getContainingObjectType()) != null && objectType.isResolved()) {
            return objectType;
        }
        return this.findEnclosingScriptType(position);
    }

    public boolean containsOperators(String expr) {
        if (expr == null) {
            return false;
        }
        boolean inString = false;
        boolean inChar = false;
        block13: for (int i = 0; i < expr.length(); ++i) {
            char next;
            char c = expr.charAt(i);
            char c2 = next = i + 1 < expr.length() ? expr.charAt(i + 1) : (char)'\u0000';
            if (c == '\"' && !inChar) {
                if (!inString) {
                    inString = true;
                    continue;
                }
                if (i <= 0 || expr.charAt(i - 1) == '\\') continue;
                inString = false;
                continue;
            }
            if (c == '\'' && !inString) {
                if (!inChar) {
                    inChar = true;
                    continue;
                }
                if (i <= 0 || expr.charAt(i - 1) == '\\') continue;
                inChar = false;
                continue;
            }
            if (inString || inChar) continue;
            switch (c) {
                case '+': {
                    char prev;
                    int prevIdx;
                    if (next == '+') {
                        return true;
                    }
                    if (next == '=') {
                        return true;
                    }
                    for (prevIdx = i - 1; prevIdx >= 0 && Character.isWhitespace(expr.charAt(prevIdx)); --prevIdx) {
                    }
                    if (prevIdx < 0 || !Character.isJavaIdentifierPart(prev = expr.charAt(prevIdx)) && prev != ')' && prev != ']' && prev != '\"' && prev != '\'' && !Character.isDigit(prev)) continue block13;
                    return true;
                }
                case '-': {
                    char prev;
                    int prevIdx;
                    if (next == '-') {
                        return true;
                    }
                    if (next == '=') {
                        return true;
                    }
                    for (prevIdx = i - 1; prevIdx >= 0 && Character.isWhitespace(expr.charAt(prevIdx)); --prevIdx) {
                    }
                    if (prevIdx < 0 || !Character.isJavaIdentifierPart(prev = expr.charAt(prevIdx)) && prev != ')' && prev != ']' && !Character.isDigit(prev)) continue block13;
                    return true;
                }
                case '%': 
                case '*': 
                case '/': {
                    return true;
                }
                case '&': {
                    if (next == '&' || next == '=') {
                        return true;
                    }
                    return true;
                }
                case '|': {
                    if (next == '|' || next == '=') {
                        return true;
                    }
                    return true;
                }
                case '^': 
                case '~': {
                    return true;
                }
                case '<': {
                    int nextNonSpace;
                    char prevC;
                    int prevIdx;
                    if (next == '<' || next == '=') {
                        return true;
                    }
                    for (prevIdx = i - 1; prevIdx >= 0 && Character.isWhitespace(expr.charAt(prevIdx)); --prevIdx) {
                    }
                    if (prevIdx >= 0 && (Character.isDigit(prevC = expr.charAt(prevIdx)) || prevC == ')' || prevC == ']')) {
                        return true;
                    }
                    for (nextNonSpace = i + 1; nextNonSpace < expr.length() && Character.isWhitespace(expr.charAt(nextNonSpace)); ++nextNonSpace) {
                    }
                    if (nextNonSpace >= expr.length() || Character.isUpperCase(expr.charAt(nextNonSpace))) continue block13;
                    return true;
                }
                case '>': {
                    char prevC;
                    int prevIdx;
                    if (next == '>' || next == '=') {
                        return true;
                    }
                    for (prevIdx = i - 1; prevIdx >= 0 && Character.isWhitespace(expr.charAt(prevIdx)); --prevIdx) {
                    }
                    if (prevIdx < 0 || !Character.isDigit(prevC = expr.charAt(prevIdx)) && prevC != ')' && prevC != ']') continue block13;
                    return true;
                }
                case '!': {
                    if (next == '=') {
                        return true;
                    }
                    return true;
                }
                case '=': {
                    if (next == '=') {
                        return true;
                    }
                    return true;
                }
                case '?': {
                    for (int j = i + 1; j < expr.length(); ++j) {
                        if (expr.charAt(j) != ':') continue;
                        return true;
                    }
                    continue block13;
                }
            }
        }
        return expr.contains(" instanceof ");
    }

    private boolean looksLikeFunctionOrLambda(String expr) {
        if (expr == null || expr.isEmpty()) {
            return false;
        }
        String trimmed = expr.trim();
        if (trimmed.startsWith("function")) {
            return true;
        }
        if (trimmed.contains("=>")) {
            return true;
        }
        return trimmed.contains("->");
    }

    private TypeInfo resolveExpressionWithParserAPI(String expr, int position) {
        ExpressionNode.TypeResolverContext context = this.createExpressionResolverContext(position);
        ExpressionTypeResolver resolver = new ExpressionTypeResolver(context, this, position);
        TypeInfo result = resolver.resolve(expr);
        if (result != null && "<null>".equals(result.getFullName())) {
            return result;
        }
        if (result == null || !result.isResolved()) {
            return this.resolveSimpleExpression(expr, position);
        }
        return result;
    }

    private ExpressionNode.TypeResolverContext createExpressionResolverContext(final int position) {
        return new ExpressionNode.TypeResolverContext(){

            @Override
            public TypeInfo resolveIdentifier(String name) {
                TypeInfo typeCheck;
                if ("this".equals(name)) {
                    return ScriptDocument.this.resolveThisType(position);
                }
                if ("super".equals(name)) {
                    ScriptTypeInfo enclosingType = ScriptDocument.this.findEnclosingScriptType(position);
                    if (enclosingType != null && enclosingType.hasSuperClass()) {
                        return enclosingType.getSuperClass();
                    }
                    return null;
                }
                if ("true".equals(name) || "false".equals(name)) {
                    return TypeInfo.fromPrimitive("boolean");
                }
                if ("null".equals(name)) {
                    return TypeInfo.unresolved("null", "<null>");
                }
                FieldInfo varInfo = ScriptDocument.this.resolveVariable(name, position);
                if (varInfo != null) {
                    return varInfo.getTypeInfo();
                }
                if (name.length() > 0 && (typeCheck = ScriptDocument.this.resolveType(name, position)) != null && typeCheck.isResolved()) {
                    return typeCheck;
                }
                TypeInfo expectedType = ExpressionTypeResolver.CURRENT_EXPECTED_TYPE;
                TypeInfo samType = ScriptDocument.this.resolveScriptMethodAsSam(name, expectedType);
                if (samType != null) {
                    return samType;
                }
                return null;
            }

            @Override
            public TypeInfo resolveMemberAccess(TypeInfo targetType, String memberName) {
                if (targetType == null || !targetType.isResolved()) {
                    return null;
                }
                if (targetType.hasField(memberName)) {
                    FieldInfo fieldInfo = targetType.getFieldInfo(memberName);
                    return fieldInfo != null ? fieldInfo.getTypeInfo() : null;
                }
                return null;
            }

            @Override
            public TypeInfo resolveMethodCall(TypeInfo targetType, String methodName, TypeInfo[] argTypes) {
                if (targetType == null || !targetType.isResolved()) {
                    MethodInfo scriptMethod;
                    if (ScriptDocument.this.isScriptMethod(methodName) && (scriptMethod = ScriptDocument.this.getScriptMethodInfo(methodName)) != null) {
                        return scriptMethod.getReturnType();
                    }
                    if (ScriptDocument.this.isGlobalEngineFunction(methodName)) {
                        return ScriptDocument.this.getGlobalEngineFunctionReturnType(methodName);
                    }
                    return null;
                }
                if (targetType.hasMethod(methodName)) {
                    MethodInfo methodInfo = targetType.getBestMethodOverload(methodName, argTypes);
                    return methodInfo != null ? methodInfo.getReturnType() : null;
                }
                return null;
            }

            @Override
            public TypeInfo resolveArrayAccess(TypeInfo arrayType) {
                if (arrayType == null || !arrayType.isResolved()) {
                    return null;
                }
                String typeName = arrayType.getFullName();
                if (typeName.endsWith("[]")) {
                    String elementTypeName = typeName.substring(0, typeName.length() - 2);
                    return ScriptDocument.this.resolveType(elementTypeName);
                }
                return null;
            }

            @Override
            public TypeInfo resolveTypeName(String typeName) {
                return ScriptDocument.this.resolveType(typeName);
            }
        };
    }

    private TypeInfo resolveSimpleExpression(String expr, int position) {
        Matcher newMatcher;
        if (expr.startsWith("\"") && expr.endsWith("\"")) {
            return TypeInfo.string();
        }
        if (expr.startsWith("'") && expr.endsWith("'")) {
            return TypeInfo.fromPrimitive("char");
        }
        if (expr.equals("true") || expr.equals("false")) {
            return TypeInfo.fromPrimitive("boolean");
        }
        if (expr.equals("null")) {
            return null;
        }
        if (expr.matches("-?\\d*\\.?\\d+[fF]")) {
            if (this.hasExcessivePrecision(expr, 7)) {
                return TypeInfo.fromPrimitive("double");
            }
            return TypeInfo.fromPrimitive("float");
        }
        if (expr.matches("-?\\d*\\.\\d+[dD]?") || expr.matches("-?\\d+\\.[dD]?") || expr.matches("-?\\d+[dD]")) {
            if (this.hasExcessivePrecision(expr, 15)) {
                return null;
            }
            return TypeInfo.fromPrimitive("double");
        }
        if (expr.matches("-?\\d+[lL]")) {
            return TypeInfo.fromPrimitive("long");
        }
        if (expr.matches("[-+]?0[xX][0-9a-fA-F][0-9a-fA-F_]*")) {
            return this.narrowIntLiteral(expr);
        }
        if (expr.matches("[-+]?0[bB][01][01_]*")) {
            return this.narrowIntLiteral(expr);
        }
        if (expr.matches("[-+]?\\d[\\d_]*")) {
            return this.narrowIntLiteral(expr);
        }
        if (expr.equals("this")) {
            return this.resolveThisType(position);
        }
        if (expr.startsWith("new ") && (newMatcher = NEW_TYPE_PATTERN.matcher(expr)).find()) {
            String typeArgsClause;
            String typeName = newMatcher.group(1);
            FieldInfo varInfo = this.resolveVariable(typeName, position);
            if (varInfo != null && varInfo.getTypeInfo() instanceof ClassTypeInfo) {
                ClassTypeInfo classRef = (ClassTypeInfo)varInfo.getTypeInfo();
                return classRef.getInstanceType();
            }
            TypeInfo baseType = this.resolveType(typeName);
            if (baseType != null && (typeArgsClause = newMatcher.group(2)) != null && !typeArgsClause.trim().isEmpty()) {
                int typeArgsTextOffset = position + newMatcher.start(2);
                baseType = this.parameterizeWithClause(baseType, typeArgsClause, position, typeArgsTextOffset);
            }
            return baseType;
        }
        List<ChainSegment> segments = this.parseExpressionChain(expr);
        if (segments.isEmpty()) {
            return null;
        }
        ChainSegment first = segments.get(0);
        TypeInfo currentType = null;
        if (first.name.equals("this")) {
            currentType = this.resolveThisType(position);
        } else {
            TypeInfo typeCheck = this.resolveType(first.name, position);
            if (typeCheck != null && typeCheck.isResolved()) {
                currentType = typeCheck;
            }
        }
        if (currentType == null && !first.isMethodCall) {
            FieldInfo varInfo = this.resolveVariable(first.name, position);
            if (varInfo != null) {
                currentType = varInfo.getTypeInfo();
            }
        } else {
            MethodInfo scriptMethod;
            if (this.isScriptMethod(first.name) && (scriptMethod = this.getScriptMethodInfo(first.name)) != null) {
                currentType = scriptMethod.getReturnType();
            }
            if (currentType == null && this.isGlobalEngineFunction(first.name)) {
                currentType = this.getGlobalEngineFunctionReturnType(first.name);
            }
        }
        currentType = this.applyBracketAccess(currentType, first);
        for (int i = 1; i < segments.size(); ++i) {
            currentType = this.resolveChainSegment(currentType, segments.get(i));
            if ((currentType = this.applyBracketAccess(currentType, segments.get(i))) != null) continue;
            return null;
        }
        return currentType;
    }

    private int findMatchingParenBackward(int closeParenPos) {
        if (closeParenPos < 0 || closeParenPos >= this.text.length() || this.text.charAt(closeParenPos) != ')') {
            return -1;
        }
        int depth = 1;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = closeParenPos - 1; i >= 0; --i) {
            int j;
            int backslashCount;
            char next;
            char c = this.text.charAt(i);
            char c2 = next = i < this.text.length() - 1 ? this.text.charAt(i + 1) : (char)'\u0000';
            if (!(inString || c != '\"' && c != '\'')) {
                backslashCount = 0;
                for (j = i - 1; j >= 0 && this.text.charAt(j) == '\\'; --j) {
                    ++backslashCount;
                }
                if (backslashCount % 2 == 0) {
                    inString = true;
                    stringChar = c;
                }
            } else if (inString && c == stringChar) {
                backslashCount = 0;
                for (j = i - 1; j >= 0 && this.text.charAt(j) == '\\'; --j) {
                    ++backslashCount;
                }
                if (backslashCount % 2 == 0) {
                    inString = false;
                }
            }
            if (inString || this.isExcluded(i)) continue;
            if (c == ')') {
                ++depth;
                continue;
            }
            if (c != '(' || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    private int findMatchingBracketBackward(int closePos, char openChar, char closeChar) {
        if (closePos < 0 || closePos >= this.text.length() || this.text.charAt(closePos) != closeChar) {
            return -1;
        }
        int depth = 1;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = closePos - 1; i >= 0; --i) {
            int j;
            int backslashCount;
            char c = this.text.charAt(i);
            if (!(inString || c != '\"' && c != '\'')) {
                backslashCount = 0;
                for (j = i - 1; j >= 0 && this.text.charAt(j) == '\\'; --j) {
                    ++backslashCount;
                }
                if (backslashCount % 2 == 0) {
                    inString = true;
                    stringChar = c;
                }
            } else if (inString && c == stringChar) {
                backslashCount = 0;
                for (j = i - 1; j >= 0 && this.text.charAt(j) == '\\'; --j) {
                    ++backslashCount;
                }
                if (backslashCount % 2 == 0) {
                    inString = false;
                }
            }
            if (inString || this.isExcluded(i)) continue;
            if (c == closeChar) {
                ++depth;
                continue;
            }
            if (c != openChar || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    int[] findReceiverBoundsBefore(int dotIndex) {
        int end;
        int start;
        int pos;
        if (dotIndex < 0 || dotIndex >= this.text.length() || this.text.charAt(dotIndex) != '.') {
            return null;
        }
        for (pos = dotIndex - 1; pos >= 0 && Character.isWhitespace(this.text.charAt(pos)); --pos) {
        }
        if (pos < 0) {
            return null;
        }
        block1: while (pos >= 0) {
            if (this.isExcluded(pos)) {
                for (int[] range : this.excludedRanges) {
                    if (pos < range[0] || pos >= range[1]) continue;
                    pos = range[0] - 1;
                    continue block1;
                }
                continue;
            }
            char c = this.text.charAt(pos);
            if (Character.isWhitespace(c)) {
                --pos;
                continue;
            }
            if (c == ')') {
                int open = this.findMatchingParenBackward(pos);
                if (open < 0) {
                    return null;
                }
                pos = open - 1;
                continue;
            }
            if (c == ']') {
                int open = this.findMatchingBracketBackward(pos, '[', ']');
                if (open < 0) {
                    return null;
                }
                pos = open - 1;
                continue;
            }
            if (!Character.isJavaIdentifierPart(c)) break;
            while (pos >= 0 && Character.isJavaIdentifierPart(this.text.charAt(pos))) {
                --pos;
            }
            int checkPos = pos;
            block4: while (checkPos >= 0) {
                if (this.isExcluded(checkPos)) {
                    for (int[] range : this.excludedRanges) {
                        if (checkPos < range[0] || checkPos >= range[1]) continue;
                        checkPos = range[0] - 1;
                        continue block4;
                    }
                    continue;
                }
                if (!Character.isWhitespace(this.text.charAt(checkPos))) break;
                --checkPos;
            }
            if (checkPos < 0 || this.isExcluded(checkPos) || this.text.charAt(checkPos) != '.') break;
            pos = checkPos - 1;
        }
        if ((start = pos + 1) >= (end = dotIndex)) {
            return null;
        }
        return new int[]{start, end};
    }

    public ScriptTypeInfo findEnclosingScriptType(int position) {
        for (ScriptTypeInfo type : this.scriptTypes.values()) {
            if (!type.containsPosition(position)) continue;
            return this.findInnermostType(type, position);
        }
        return null;
    }

    private ScriptTypeInfo findInnermostType(ScriptTypeInfo parent, int position) {
        for (ScriptTypeInfo inner : parent.getInnerClasses()) {
            if (!inner.containsPosition(position)) continue;
            return this.findInnermostType(inner, position);
        }
        return parent;
    }

    private MethodInfo findEnclosingConstructor(int position) {
        ScriptTypeInfo enclosingType = this.findEnclosingScriptType(position);
        if (enclosingType == null) {
            return null;
        }
        for (MethodInfo constructor : enclosingType.getConstructors()) {
            int bodyStart = constructor.getBodyStart();
            int bodyEnd = constructor.getBodyEnd();
            if (bodyStart < 0 || bodyEnd <= bodyStart || position < bodyStart || position >= bodyEnd) continue;
            return constructor;
        }
        return null;
    }

    private void handleSuperConstructorCall(List<ScriptLine.Mark> marks, int nameStart, int nameEnd, int openParen, int closeParen) {
        MethodInfo parentConstructor;
        TokenErrorMessage errorMsg = null;
        ScriptTypeInfo enclosingType = this.findEnclosingScriptType(nameStart);
        MethodInfo enclosingConstructor = this.findEnclosingConstructor(nameStart);
        TypeInfo superClass = enclosingType != null ? enclosingType.getSuperClass() : null;
        List<MethodCallInfo.Argument> arguments = this.parseMethodArguments(openParen + 1, closeParen, null, superClass);
        TypeInfo[] argTypes = (TypeInfo[])arguments.stream().map(MethodCallInfo.Argument::getResolvedType).toArray(TypeInfo[]::new);
        MethodInfo methodInfo = parentConstructor = superClass != null ? superClass.findConstructor(argTypes) : null;
        if (enclosingType == null || !enclosingType.hasSuperClass()) {
            errorMsg = TokenErrorMessage.from(enclosingType == null ? "'super()' can only be used within a class" : "Class '" + enclosingType.getSimpleName() + "' does not have a parent class").clearOtherErrors();
        } else if (enclosingConstructor == null) {
            errorMsg = TokenErrorMessage.from("Call to 'super()' only allowed in constructor body").clearOtherErrors();
        } else if (superClass == null || !superClass.isResolved()) {
            errorMsg = TokenErrorMessage.from("Cannot resolve parent class '" + enclosingType.getSuperClassName() + "'");
        } else if (parentConstructor == null) {
            String argTypeStr = Arrays.stream(argTypes).map(t -> t != null ? t.getSimpleName() : "unknown").collect(Collectors.joining(", "));
            errorMsg = TokenErrorMessage.from("No constructor found in '" + superClass.getSimpleName() + "' matching super(" + argTypeStr + ")").clearOtherErrors();
        }
        MethodCallInfo callInfo = new MethodCallInfo("super", nameStart, nameEnd, openParen, closeParen, arguments, superClass, parentConstructor, false).setConstructor(true);
        callInfo.validate();
        this.methodCalls.add(callInfo);
        marks.add(new ScriptLine.Mark(nameStart, nameEnd, TokenType.KEYWORD, errorMsg != null ? errorMsg : callInfo));
    }

    private boolean isScriptMethod(String methodName) {
        for (MethodInfo method : this.methods) {
            if (!method.getName().equals(methodName)) continue;
            return true;
        }
        return false;
    }

    private TypeInfo resolveScriptMethodAsSam(String methodName, TypeInfo expectedType) {
        if (methodName == null || expectedType == null || !expectedType.isFunctionalInterface() || !this.isScriptMethod(methodName)) {
            return null;
        }
        if (!this.isJavaScript()) {
            CURRENT_SAM_CONFLICT_ERROR.set("Bare method name '" + methodName + "' cannot be used as callback in Java. Use this::" + methodName);
            return expectedType;
        }
        MethodInfo sam = expectedType.getSingleAbstractMethod();
        if (sam == null) {
            return null;
        }
        int samArity = sam.getParameters().size();
        MethodInfo bestMatch = this.getScriptMethodBySamArity(methodName, samArity);
        if (bestMatch == null) {
            return null;
        }
        this.injectSamParameterTypes(bestMatch, sam);
        return expectedType;
    }

    private MethodInfo getScriptMethodInfo(String methodName) {
        for (MethodInfo method : this.methods) {
            if (!method.getName().equals(methodName)) continue;
            return method;
        }
        return null;
    }

    MethodInfo getScriptMethodInfo(String methodName, TypeInfo[] argTypes) {
        TypeInfo argType;
        TypeInfo paramType;
        int i;
        List<FieldInfo> params;
        ArrayList<MethodInfo> candidates = new ArrayList<MethodInfo>();
        for (MethodInfo method : this.methods) {
            if (!method.getName().equals(methodName)) continue;
            candidates.add(method);
        }
        if (candidates.isEmpty()) {
            return null;
        }
        if (candidates.size() == 1) {
            return (MethodInfo)candidates.get(0);
        }
        for (MethodInfo method : candidates) {
            params = method.getParameters();
            if (params.size() != argTypes.length) continue;
            boolean exactMatch = true;
            for (i = 0; i < argTypes.length; ++i) {
                paramType = params.get(i).getDeclaredType();
                argType = argTypes[i];
                if (argType != null && paramType != null && argType.equals(paramType)) continue;
                exactMatch = false;
                break;
            }
            if (!exactMatch) continue;
            return method;
        }
        for (MethodInfo method : candidates) {
            params = method.getParameters();
            if (params.size() != argTypes.length) continue;
            boolean compatible = true;
            for (i = 0; i < argTypes.length; ++i) {
                paramType = params.get(i).getDeclaredType();
                argType = argTypes[i];
                if (argType == null || paramType == null || TypeChecker.isTypeCompatible(paramType, argType)) continue;
                compatible = false;
                break;
            }
            if (!compatible) continue;
            return method;
        }
        return (MethodInfo)candidates.get(0);
    }

    private MethodInfo getScriptMethodBySamArity(String methodName, int samArity) {
        ArrayList<MethodInfo> matchingByArity = new ArrayList<MethodInfo>();
        for (MethodInfo method : this.methods) {
            if (!method.getName().equals(methodName) || method.getParameters().size() != samArity) continue;
            matchingByArity.add(method);
        }
        if (matchingByArity.size() == 1) {
            return (MethodInfo)matchingByArity.get(0);
        }
        if (matchingByArity.isEmpty()) {
            return this.getScriptMethodInfo(methodName);
        }
        CURRENT_SAM_CONFLICT_ERROR.set("Ambiguous overload for '" + methodName + "' with " + samArity + " parameter(s): " + matchingByArity.size() + " overloads match");
        return null;
    }

    private boolean isGlobalEngineFunction(String name) {
        return this.isJavaScript() && JSTypeRegistry.getInstance().isGlobalEngineFunction(name);
    }

    private TypeInfo getGlobalEngineFunctionReturnType(String name) {
        JSMethodInfo m = JSTypeRegistry.getInstance().getGlobalEngineFunction(name);
        return m != null ? m.getResolvedReturnType(null) : null;
    }

    private void injectSamParameterTypes(MethodInfo scriptMethod, MethodInfo sam) {
        String methodName = scriptMethod.getName();
        MethodInfo previousSam = this.scriptMethodSamContexts.get(methodName);
        if (previousSam != null) {
            if (!this.areSamSignaturesCompatible(previousSam, sam)) {
                String existingSig = this.formatSamSignature(previousSam);
                String newSig = this.formatSamSignature(sam);
                CURRENT_SAM_CONFLICT_ERROR.set("Function '" + methodName + "' used in incompatible SAM contexts: " + existingSig + " vs " + newSig);
                return;
            }
        } else {
            this.scriptMethodSamContexts.put(methodName, sam);
        }
        List<FieldInfo> scriptParams = scriptMethod.getParameters();
        List<FieldInfo> samParams = sam.getParameters();
        if (scriptParams.size() != samParams.size()) {
            return;
        }
        for (int i = 0; i < scriptParams.size(); ++i) {
            boolean hasExplicitType;
            FieldInfo scriptParam = scriptParams.get(i);
            TypeInfo samParamType = samParams.get(i).getTypeInfo();
            if (samParamType == null) continue;
            TypeInfo declaredType = scriptParam.getDeclaredType();
            boolean bl = hasExplicitType = declaredType != null && declaredType.isResolved() && !"any".equals(declaredType.getFullName());
            if (hasExplicitType) {
                if (TypeChecker.isTypeCompatible(declaredType, samParamType)) continue;
                scriptMethod.addSamTypeError(i, samParamType, declaredType);
                continue;
            }
            scriptParam.setInferredType(samParamType);
        }
    }

    private boolean areSamSignaturesCompatible(MethodInfo sam1, MethodInfo sam2) {
        List<FieldInfo> params1 = sam1.getParameters();
        List<FieldInfo> params2 = sam2.getParameters();
        if (params1.size() != params2.size()) {
            return false;
        }
        for (int i = 0; i < params1.size(); ++i) {
            TypeInfo type1 = params1.get(i).getTypeInfo();
            TypeInfo type2 = params2.get(i).getTypeInfo();
            if (type1 == null || type2 == null || TypeChecker.isTypeCompatible(type1, type2) || TypeChecker.isTypeCompatible(type2, type1)) continue;
            return false;
        }
        TypeInfo return1 = sam1.getReturnType();
        TypeInfo return2 = sam2.getReturnType();
        return return1 == null || return2 == null || TypeChecker.isTypeCompatible(return1, return2) || TypeChecker.isTypeCompatible(return2, return1);
    }

    private String formatSamSignature(MethodInfo sam) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        List<FieldInfo> params = sam.getParameters();
        for (int i = 0; i < params.size(); ++i) {
            TypeInfo type;
            if (i > 0) {
                sb.append(", ");
            }
            sb.append((type = params.get(i).getTypeInfo()) != null ? type.getSimpleName() : "?");
        }
        sb.append(") -> ");
        TypeInfo returnType = sam.getReturnType();
        sb.append(returnType != null ? returnType.getSimpleName() : "void");
        return sb.toString();
    }

    private boolean isMethodFromScriptType(MethodInfo method) {
        if (method == null || !method.isDeclaration()) {
            return false;
        }
        int declPos = method.getDeclarationOffset();
        if (declPos < 0) {
            return false;
        }
        for (ScriptTypeInfo scriptType : this.scriptTypesByFullName.values()) {
            if (!scriptType.containsPosition(declPos)) continue;
            return true;
        }
        return false;
    }

    private void markVariables(List<ScriptLine.Mark> marks) {
        HashSet<String> knownKeywords = new HashSet<String>(Arrays.asList("boolean", "int", "float", "double", "long", "char", "byte", "short", "void", "null", "true", "false", "if", "else", "switch", "case", "for", "while", "do", "try", "catch", "finally", "return", "throw", "var", "let", "const", "function", "continue", "break", "this", "new", "typeof", "instanceof", "class", "interface", "extends", "implements", "import", "package", "public", "private", "protected", "static", "final", "abstract", "synchronized", "native", "default", "enum", "throws", "super", "assert", "volatile", "transient"));
        if (this.isJavaScript()) {
            knownKeywords.add("delete");
            knownKeywords.add("undefined");
        }
        List<MethodInfo> allMethods = this.getAllMethods();
        allMethods.addAll(this.getAllConstructors());
        for (MethodInfo method : allMethods) {
            for (FieldInfo param : method.getParameters()) {
                int pos = param.getDeclarationOffset();
                String name = param.getName();
                marks.add(new ScriptLine.Mark(pos, pos + name.length(), TokenType.PARAMETER, param));
            }
        }
        Pattern identifier = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*)\\b");
        Matcher m = identifier.matcher(this.text);
        block2: while (m.find()) {
            MethodInfo scriptMethod;
            FieldInfo fieldInfo;
            Object metadata;
            FieldInfo fieldInfo2;
            Object enclosingType;
            String name = m.group(1);
            int position = m.start(1);
            if (this.isExcluded(position) || knownKeywords.contains(name) || this.isPrecededByDot(position) || this.isInImportOrPackage(position) || this.isFollowedByParen(m.end(1))) continue;
            MethodInfo containingMethod = this.findMethodAtPosition(position);
            MethodCallInfo callInfo = this.findMethodCallContainingPosition(position);
            boolean isUppercase = Character.isUpperCase(name.charAt(0));
            boolean breakOuterLoop = false;
            for (InnerCallableScope scope : this.innerScopes) {
                for (FieldInfo param : scope.getParameters()) {
                    int declStart = param.getDeclarationOffset();
                    if (declStart != m.start(1) || !name.equals(param.getName())) continue;
                    marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.PARAMETER, param));
                    breakOuterLoop = true;
                    break;
                }
                if (!breakOuterLoop) continue;
                break;
            }
            if (breakOuterLoop) continue;
            Object innermostScope = this.findInnermostScopeAt(m.start(1));
            if (innermostScope instanceof InnerCallableScope) {
                InnerCallableScope innerScope = (InnerCallableScope)innermostScope;
                FieldInfo innerParam = innerScope.getParameter(name);
                if (innerParam != null) {
                    marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.PARAMETER, innerParam));
                    continue;
                }
                FieldInfo innerLocal = innerScope.getLocals().get(name);
                if (innerLocal != null) {
                    marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.LOCAL_FIELD, innerLocal));
                    continue;
                }
            }
            if (containingMethod != null && containingMethod.hasParameter(name)) {
                FieldInfo paramInfo = containingMethod.getParameter(name);
                marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.PARAMETER, paramInfo));
                continue;
            }
            FieldInfo localInfo = null;
            if (containingMethod != null) {
                Map<String, List<FieldInfo>> locals = this.methodLocals.get(containingMethod.getDeclarationOffset());
                if (locals != null) {
                    localInfo = this.pickVisibleLocal(locals.get(name), position);
                }
            } else {
                localInfo = this.pickVisibleTopLevelLocal(name, position);
            }
            if (localInfo != null) {
                Object metadata2 = callInfo != null ? new FieldInfo.ArgInfo(localInfo, callInfo) : localInfo;
                marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.LOCAL_FIELD, metadata2));
                continue;
            }
            if (containingMethod != null && (enclosingType = this.findEnclosingScriptType(position)) != null && ((ScriptTypeInfo)enclosingType).hasField(name) && (fieldInfo2 = ((ScriptTypeInfo)enclosingType).getFieldInfo(name)).isVisibleAt(position)) {
                Object metadata3 = callInfo != null ? new FieldInfo.ArgInfo(fieldInfo2, callInfo) : fieldInfo2;
                marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.GLOBAL_FIELD, metadata3));
                continue;
            }
            enclosingType = this.scriptTypesByFullName.values().iterator();
            while (enclosingType.hasNext()) {
                FieldInfo fieldInfo3;
                ScriptTypeInfo scriptType = (ScriptTypeInfo)enclosingType.next();
                if (!scriptType.hasField(name) || position < scriptType.getBodyStart() || position > scriptType.getBodyEnd() || (fieldInfo3 = scriptType.getFieldInfo(name)).isEnumConstant() || !fieldInfo3.isVisibleAt(position)) continue;
                metadata = callInfo != null ? new FieldInfo.ArgInfo(fieldInfo3, callInfo) : fieldInfo3;
                marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.GLOBAL_FIELD, metadata));
                continue block2;
            }
            if (this.globalFields.containsKey(name) && (fieldInfo = this.globalFields.get(name)).isVisibleAt(position)) {
                Object metadata4 = callInfo != null ? new FieldInfo.ArgInfo(fieldInfo, callInfo) : fieldInfo;
                marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.GLOBAL_FIELD, metadata4));
                continue;
            }
            if (this.isJavaScript()) {
                FieldInfo fieldInfo4;
                JSTypeRegistry registry = JSTypeRegistry.getInstance();
                String globalObjectType = registry.getGlobalObjectType(name);
                if (globalObjectType != null && (fieldInfo4 = this.resolveVariable(name, position)) != null && fieldInfo4.isResolved()) {
                    metadata = callInfo != null ? new FieldInfo.ArgInfo(fieldInfo4, callInfo) : fieldInfo4;
                    marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.GLOBAL_FIELD, metadata));
                    continue;
                }
                if (!this.editorGlobals.isEmpty() && this.editorGlobals.containsKey(name) && (fieldInfo = this.resolveVariable(name, position)) != null) {
                    metadata = callInfo != null ? new FieldInfo.ArgInfo(fieldInfo, callInfo) : fieldInfo;
                    marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.GLOBAL_FIELD, metadata));
                    continue;
                }
            }
            if (isUppercase) continue;
            if (this.isScriptMethod(name) && (scriptMethod = this.getScriptMethodInfo(name)) != null) {
                marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.METHOD_CALL, scriptMethod));
                continue;
            }
            if (containingMethod == null) continue;
            marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.UNDEFINED_VAR, callInfo));
        }
    }

    private void markChainedFieldAccesses(List<ScriptLine.Mark> marks) {
        FieldChainMarker marker = new FieldChainMarker(this, this.text);
        marker.markChainedFieldAccesses(marks);
    }

    private void parseAssignments() {
        int n;
        for (FieldInfo fieldInfo : this.globalFields.values()) {
            fieldInfo.clearAssignments();
        }
        for (Map map : this.methodLocals.values()) {
            for (List fields : map.values()) {
                for (FieldInfo field : fields) {
                    field.clearAssignments();
                }
            }
        }
        for (List list : this.topLevelLocals.values()) {
            for (FieldInfo field : list) {
                field.clearAssignments();
            }
        }
        for (ScriptTypeInfo scriptTypeInfo : this.scriptTypesByFullName.values()) {
            for (FieldInfo field : scriptTypeInfo.getFields().values()) {
                field.clearAssignments();
            }
        }
        this.externalFieldAssignments.clear();
        int pos = 0;
        while (pos < this.text.length() && (n = this.text.indexOf(61, pos)) >= 0) {
            int stmtEnd;
            int stmtStart;
            if (this.isExcluded(n)) {
                pos = n + 1;
                continue;
            }
            if (n > 0 && "!<>=+-*/%&|^".indexOf(this.text.charAt(n - 1)) >= 0) {
                pos = n + 1;
                continue;
            }
            if (n < this.text.length() - 1 && this.text.charAt(n + 1) == '=') {
                pos = n + 2;
                continue;
            }
            int backDepth = 0;
            for (stmtStart = n - 1; stmtStart >= 0; --stmtStart) {
                char c = this.text.charAt(stmtStart);
                if (c == ')' || c == ']') {
                    ++backDepth;
                    continue;
                }
                if (c == '(' || c == '[') {
                    if (backDepth == 0) {
                        ++stmtStart;
                        break;
                    }
                    --backDepth;
                    continue;
                }
                if (c == ';' || c == '{' || c == '}') {
                    ++stmtStart;
                    break;
                }
                if (c != ',' || backDepth != 0) continue;
                ++stmtStart;
                break;
            }
            if (stmtStart < 0) {
                stmtStart = 0;
            }
            while (stmtStart < n && Character.isWhitespace(this.text.charAt(stmtStart))) {
                ++stmtStart;
            }
            if (this.isJavaScript()) {
                int rhsBoundaryStart;
                for (rhsBoundaryStart = n + 1; rhsBoundaryStart < this.text.length() && Character.isWhitespace(this.text.charAt(rhsBoundaryStart)); ++rhsBoundaryStart) {
                }
                boolean isMultiDecl = MultiDeclaratorParser.startsWithJSKeyword(this.text, stmtStart, n);
                stmtEnd = this.findJsInitializerEnd(this.text, rhsBoundaryStart, isMultiDecl);
                if (stmtEnd < n + 1) {
                    stmtEnd = n + 1;
                }
            } else {
                stmtEnd = this.findJsInitializerEnd(this.text, n + 1, true);
                if (stmtEnd < n + 1) {
                    stmtEnd = n + 1;
                }
            }
            String lhsRaw = this.text.substring(stmtStart, n).trim();
            String rhsRaw = this.text.substring(n + 1, stmtEnd).trim();
            if (lhsRaw.isEmpty() || rhsRaw.isEmpty()) {
                pos = n + 1;
                continue;
            }
            boolean isDeclaration = this.isVariableDeclaration(lhsRaw);
            if (isDeclaration) {
                this.createAndAttachDeclarationAssignment(lhsRaw, rhsRaw, stmtStart, n, stmtEnd);
            } else {
                this.createAndAttachAssignment(lhsRaw, rhsRaw, stmtStart, n, stmtEnd);
            }
            pos = stmtEnd + 1;
        }
    }

    private boolean isVariableDeclaration(String lhs) {
        String[] parts = lhs.trim().split("\\s+");
        if (parts.length == 1) {
            return false;
        }
        String potentialType = parts[parts.length - 2];
        String potentialVar = parts[parts.length - 1];
        return (TypeResolver.isPrimitiveType(potentialType) || Character.isUpperCase(potentialType.charAt(0)) && potentialType.matches("[A-Za-z_][A-Za-z0-9_<>\\[\\],\\s]*") || potentialType.equals("var") || potentialType.equals("let") || potentialType.equals("const")) && potentialVar.matches("[a-zA-Z_][a-zA-Z0-9_]*") && !potentialVar.contains(".");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void createAndAttachAssignment(String lhs, String rhs, int stmtStart, int equalsPos, int stmtEnd) {
        ScriptTypeInfo enclosingType;
        MethodInfo enclosingCtor;
        ObjectLiteralParser.DynamicFieldResult res;
        TypeInfo sourceType;
        int rhsStart;
        int lhsEnd;
        int lhsStart;
        lhs = lhs.trim();
        rhs = rhs.trim();
        ObjectLiteralParser.DynamicPropertyAccess dynamicPropertyAccess = this.isJavaScript() ? ObjectLiteralParser.parseDynamicPropertyAccess(lhs, stmtStart) : null;
        String targetName = lhs;
        FieldInfo targetField = null;
        TypeInfo targetType = null;
        TypeInfo receiverType = null;
        Field reflectionField = null;
        for (lhsStart = stmtStart; lhsStart < equalsPos && Character.isWhitespace(this.text.charAt(lhsStart)); ++lhsStart) {
        }
        for (lhsEnd = equalsPos; lhsEnd > lhsStart && Character.isWhitespace(this.text.charAt(lhsEnd - 1)); --lhsEnd) {
        }
        for (rhsStart = equalsPos + 1; rhsStart < stmtEnd && Character.isWhitespace(this.text.charAt(rhsStart)); ++rhsStart) {
        }
        int rhsEnd = stmtEnd;
        if (lhs.contains(".")) {
            String receiverExpr;
            String[] segments = lhs.split("\\.");
            targetName = segments[segments.length - 1].trim();
            boolean lastSegmentHasArrayAccess = targetName.contains("[");
            if (lastSegmentHasArrayAccess) {
                targetName = targetName.substring(0, targetName.indexOf(91)).trim();
            }
            if ((receiverType = this.resolveExpressionType(receiverExpr = lhs.substring(0, lhs.lastIndexOf(46)).trim(), lhsStart)) != null && receiverType.isResolved() && receiverType.hasField(targetName)) {
                targetField = receiverType.getFieldInfo(targetName);
                targetType = targetField.getTypeInfo();
                if (lastSegmentHasArrayAccess) {
                    targetType = this.unwrapArrayElement(targetType);
                }
                reflectionField = targetField.getReflectionField();
            }
        } else if (lhs.contains("[")) {
            String varName;
            targetName = varName = lhs.substring(0, lhs.indexOf(91)).trim();
            if (dynamicPropertyAccess != null) {
                ObjectLiteralParser.DynamicFieldResult res2 = ObjectLiteralParser.resolveExistingField(dynamicPropertyAccess, this::resolveExpressionType, this::resolveVariable);
                receiverType = res2.receiverType;
                targetName = res2.propertyName;
                targetField = res2.field;
                if (targetField != null) {
                    targetType = targetField.getTypeInfo();
                    reflectionField = targetField.getReflectionField();
                }
            }
            if (targetField == null) {
                targetField = this.resolveVariable(varName, lhsStart);
            }
            if (targetField != null && targetType == null) {
                targetType = this.unwrapArrayElement(targetField.getTypeInfo());
                reflectionField = targetField.getReflectionField();
            }
        } else {
            targetField = this.resolveVariable(targetName, lhsStart);
            if (targetField != null) {
                targetType = targetField.getTypeInfo();
                reflectionField = targetField.getReflectionField();
            }
        }
        ExpressionTypeResolver.CURRENT_EXPECTED_TYPE = targetType;
        try {
            sourceType = this.resolveExpressionType(rhs, equalsPos + 1);
        }
        finally {
            ExpressionTypeResolver.CURRENT_EXPECTED_TYPE = null;
        }
        if (dynamicPropertyAccess != null && (res = ObjectLiteralParser.extendAndGetField(dynamicPropertyAccess, receiverType, sourceType, rhs, this::resolveExpressionType, this::resolveVariable)) != null) {
            receiverType = res.receiverType;
            targetName = res.propertyName;
            targetField = res.field;
            if (targetField != null) {
                targetType = targetField.getTypeInfo();
                reflectionField = targetField.getReflectionField();
            }
        }
        if (targetField != null && targetField.canInferType() && targetField.getInferredType() == null && sourceType != null && sourceType.isResolved() && !"any".equals(sourceType.getFullName())) {
            targetField.setInferredType(sourceType);
        }
        FieldInfo finalTargetField = targetField;
        boolean isScriptField = targetField != null && (this.globalFields.containsValue(targetField) || this.methodLocals.values().stream().anyMatch(m -> m.values().stream().anyMatch(list -> list.contains(finalTargetField))) || this.topLevelLocals.values().stream().anyMatch(list -> list.contains(finalTargetField)));
        boolean isFinal = false;
        if (targetField != null && !lhs.contains("[")) {
            isFinal = targetField.isFinal();
        }
        if (isFinal && targetField != null && (enclosingCtor = this.findEnclosingConstructor(stmtStart)) != null && (enclosingType = this.findEnclosingScriptType(stmtStart)) != null && enclosingType.getFields().containsValue(targetField)) {
            isFinal = false;
        }
        AssignmentInfo info = new AssignmentInfo(targetName, stmtStart, lhsStart, lhsEnd, targetType, rhsStart, rhsEnd, sourceType, rhs, receiverType, reflectionField, isFinal);
        if (targetField != null) {
            info.setScopeInfo(targetField.getScopeInfo());
        }
        info.validate();
        if (isScriptField && targetField != null) {
            targetField.addAssignment(info);
        } else {
            this.externalFieldAssignments.add(info);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void createAndAttachDeclarationAssignment(String lhs, String rhs, int stmtStart, int equalsPos, int stmtEnd) {
        TypeInfo sourceType;
        TypeInfo targetType;
        int rhsStart;
        int found;
        lhs = lhs.trim();
        rhs = rhs.trim();
        String[] parts = lhs.split("\\s+");
        if (parts.length < 2) {
            return;
        }
        String varName = parts[parts.length - 1].trim();
        int varNameStart = stmtStart;
        int searchStart = stmtStart;
        while (searchStart < equalsPos && (found = this.text.indexOf(varName, searchStart)) >= 0 && found < equalsPos) {
            int afterVar;
            if ((found == stmtStart || Character.isWhitespace(this.text.charAt(found - 1))) && ((afterVar = found + varName.length()) >= this.text.length() || Character.isWhitespace(this.text.charAt(afterVar)) || this.text.charAt(afterVar) == '=')) {
                varNameStart = found;
                break;
            }
            searchStart = found + 1;
        }
        int varNameEnd = varNameStart + varName.length();
        for (rhsStart = equalsPos + 1; rhsStart < stmtEnd && Character.isWhitespace(this.text.charAt(rhsStart)); ++rhsStart) {
        }
        int rhsEnd = stmtEnd;
        FieldInfo targetField = this.resolveVariable(varName, varNameStart);
        if (targetField == null || targetField.getDeclarationAssignment() != null) {
            return;
        }
        ExpressionTypeResolver.CURRENT_EXPECTED_TYPE = targetType = targetField.getTypeInfo();
        try {
            sourceType = this.resolveExpressionType(rhs, rhsStart);
        }
        finally {
            ExpressionTypeResolver.CURRENT_EXPECTED_TYPE = null;
        }
        if (targetField.canInferType() && targetField.getInferredType() == null && sourceType != null && sourceType.isResolved() && !"any".equals(sourceType.getFullName())) {
            targetField.setInferredType(sourceType);
        }
        AssignmentInfo info = new AssignmentInfo(varName, stmtStart, varNameStart, varNameEnd, targetType, rhsStart, rhsEnd, sourceType, rhs, null, targetField.getReflectionField(), false);
        info.setScopeInfo(targetField.getScopeInfo());
        info.validate();
        targetField.setDeclarationAssignment(info);
    }

    private void detectMethodInheritance() {
        for (ScriptTypeInfo scriptType : this.scriptTypesByFullName.values()) {
            scriptType.clearErrors();
            this.detectMethodInheritanceForType(scriptType);
            scriptType.validate();
        }
    }

    private void detectMethodInheritanceForType(ScriptTypeInfo scriptType) {
        for (List<MethodInfo> overloads : scriptType.getMethods().values()) {
            block1: for (MethodInfo method : overloads) {
                TypeInfo overrideSource;
                TypeInfo superClass;
                if (scriptType.hasSuperClass() && (superClass = scriptType.getSuperClass()) != null && superClass.isResolved() && (overrideSource = this.findMethodInHierarchy(superClass, method, false)) != null) {
                    method.setOverridesFrom(overrideSource);
                }
                if (method.isOverride() || !scriptType.hasImplementedInterfaces()) continue;
                for (TypeInfo iface : scriptType.getImplementedInterfaces()) {
                    TypeInfo implementsSource;
                    if (iface == null || !iface.isResolved() || (implementsSource = this.findMethodInInterface(iface, method)) == null) continue;
                    method.setImplementsFrom(implementsSource);
                    continue block1;
                }
            }
        }
    }

    private TypeInfo findMethodInHierarchy(TypeInfo type, MethodInfo method, boolean includeInterfaces) {
        TypeInfo superType;
        TypeInfo result;
        Class<?> superClass;
        TypeInfo result2;
        ScriptTypeInfo scriptType;
        if (type == null || !type.isResolved()) {
            return null;
        }
        if (this.hasMatchingMethod(type, method)) {
            return type;
        }
        if (type instanceof ScriptTypeInfo && (scriptType = (ScriptTypeInfo)type).hasSuperClass() && (result2 = this.findMethodInHierarchy(scriptType.getSuperClass(), method, includeInterfaces)) != null) {
            return result2;
        }
        Class<?> javaClass = type.getJavaClass();
        if (javaClass != null && (superClass = javaClass.getSuperclass()) != null && superClass != Object.class && (result = this.findMethodInHierarchy(superType = TypeInfo.fromClass(superClass), method, includeInterfaces)) != null) {
            return result;
        }
        return null;
    }

    private TypeInfo findMethodInInterface(TypeInfo iface, MethodInfo method) {
        if (iface == null || !iface.isResolved()) {
            return null;
        }
        if (this.hasMatchingMethod(iface, method)) {
            return iface;
        }
        Class<?> javaClass = iface.getJavaClass();
        if (javaClass != null && javaClass.isInterface()) {
            for (Class<?> superIface : javaClass.getInterfaces()) {
                TypeInfo superType = TypeInfo.fromClass(superIface);
                TypeInfo result = this.findMethodInInterface(superType, method);
                if (result == null) continue;
                return result;
            }
        }
        return null;
    }

    private boolean hasMatchingMethod(TypeInfo type, MethodInfo method) {
        String methodName = method.getName();
        int paramCount = method.getParameterCount();
        if (type instanceof ScriptTypeInfo) {
            ScriptTypeInfo scriptType = (ScriptTypeInfo)type;
            List<MethodInfo> overloads = scriptType.getAllMethodOverloads(methodName);
            for (MethodInfo candidate : overloads) {
                if (!this.signaturesMatch(method, candidate)) continue;
                return true;
            }
            return false;
        }
        Class<?> javaClass = type.getJavaClass();
        if (javaClass == null) {
            return false;
        }
        try {
            for (Method javaMethod : javaClass.getMethods()) {
                if (!javaMethod.getName().equals(methodName) || javaMethod.getParameterCount() != paramCount || !this.parameterTypesMatch(method, javaMethod)) continue;
                return true;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return false;
    }

    private boolean signaturesMatch(MethodInfo m1, MethodInfo m2) {
        if (!m1.getName().equals(m2.getName())) {
            return false;
        }
        if (m1.getParameterCount() != m2.getParameterCount()) {
            return false;
        }
        List<FieldInfo> params1 = m1.getParameters();
        List<FieldInfo> params2 = m2.getParameters();
        for (int i = 0; i < params1.size(); ++i) {
            TypeInfo type1 = params1.get(i).getDeclaredType();
            TypeInfo type2 = params2.get(i).getDeclaredType();
            if (type1 == null && type2 == null) continue;
            if (type1 == null || type2 == null) {
                return false;
            }
            if (type1.getFullName().equals(type2.getFullName())) continue;
            return false;
        }
        return true;
    }

    private boolean parameterTypesMatch(MethodInfo methodInfo, Method javaMethod) {
        List<FieldInfo> params = methodInfo.getParameters();
        Class<?>[] javaParams = javaMethod.getParameterTypes();
        if (params.size() != javaParams.length) {
            return false;
        }
        for (int i = 0; i < params.size(); ++i) {
            TypeInfo paramType = params.get(i).getDeclaredType();
            if (paramType == null) continue;
            Class<?> javaParamClass = javaParams[i];
            String javaParamName = javaParamClass.getName();
            if (paramType.getFullName().equals(javaParamName) || paramType.getSimpleName().equals(javaParamClass.getSimpleName())) continue;
            return false;
        }
        return true;
    }

    private void markCastTypes(List<ScriptLine.Mark> marks) {
        Matcher m = CAST_TYPE_PATTERN.matcher(this.text);
        while (m.find()) {
            String typeName = m.group(1);
            int typeStart = m.start(1);
            int typeEnd = m.end(1);
            if (this.isExcluded(typeStart) || this.isInImportOrPackage(typeStart)) continue;
            TypeInfo info = this.resolveType(typeName, typeStart);
            if (info != null && info.isResolved()) {
                marks.add(new ScriptLine.Mark(typeStart, typeEnd, info.getTokenType(), info));
                continue;
            }
            if (this.isPrimitiveType(typeName)) {
                marks.add(new ScriptLine.Mark(typeStart, typeEnd, TokenType.KEYWORD));
                continue;
            }
            marks.add(new ScriptLine.Mark(typeStart, typeEnd, TokenType.UNDEFINED_VAR));
        }
    }

    private boolean isPrimitiveType(String typeName) {
        switch (typeName) {
            case "byte": 
            case "short": 
            case "int": 
            case "long": 
            case "float": 
            case "double": 
            case "char": 
            case "boolean": 
            case "void": {
                return true;
            }
        }
        return false;
    }

    private void inferLambdaParameterTypes() {
        this.inferMethodCallLambdaTypes();
        this.inferConstructorLambdaTypes();
        this.inferFieldAssignmentLambdaTypes();
        this.populateLambdaCache();
    }

    private void inferMethodCallLambdaTypes() {
        Matcher m = METHOD_CALL_PATTERN.matcher(this.text);
        while (m.find()) {
            boolean hasLambda;
            int closeParen;
            int openParen;
            int nameStart = m.start(1);
            String methodName = m.group(1);
            if (this.isExcluded(nameStart) || this.isKeyword(methodName) || this.isInImportOrPackage(nameStart)) continue;
            boolean isDecl = false;
            for (MethodInfo decl : this.methods) {
                if (decl.getNameOffset() != nameStart) continue;
                isDecl = true;
            }
            if (isDecl) continue;
            for (openParen = m.end(1); openParen < this.text.length() && Character.isWhitespace(this.text.charAt(openParen)); ++openParen) {
            }
            if (openParen >= this.text.length() || this.text.charAt(openParen) != '(' || (closeParen = this.findMatchingParen(openParen)) < 0) continue;
            String argsText = this.text.substring(openParen + 1, closeParen);
            boolean bl = this.isJavaScript() ? argsText.contains("function") || argsText.contains("=>") : (hasLambda = argsText.contains("->"));
            if (!hasLambda) continue;
            List<MethodCallInfo.Argument> arguments = this.parseMethodArguments(openParen + 1, closeParen, null, null);
            TypeInfo[] argTypes = (TypeInfo[])arguments.stream().map(MethodCallInfo.Argument::getResolvedType).toArray(TypeInfo[]::new);
            TypeInfo receiverType = this.resolveReceiverChain(nameStart);
            MethodInfo resolvedMethod = null;
            if (receiverType != null) {
                if (receiverType.hasMethod(methodName)) {
                    resolvedMethod = receiverType.getBestMethodOverload(methodName, argTypes);
                }
            } else if (!this.isPrecededByDot(nameStart)) {
                JSMethodInfo jsGlobal;
                if (this.isScriptMethod(methodName)) {
                    resolvedMethod = this.getScriptMethodInfo(methodName, argTypes);
                } else if (this.isGlobalEngineFunction(methodName) && (jsGlobal = JSTypeRegistry.getInstance().getGlobalEngineFunction(methodName)) != null) {
                    resolvedMethod = MethodInfo.fromJSMethod(jsGlobal, null);
                }
            }
            if (resolvedMethod == null || resolvedMethod.getParameters().size() != arguments.size()) continue;
            this.parseMethodArguments(openParen + 1, closeParen, resolvedMethod, receiverType);
        }
    }

    private void inferConstructorLambdaTypes() {
        Pattern newExpr = Pattern.compile("\\bnew\\s+([A-Za-z][a-zA-Z0-9_.]*?)\\s*(?:<[^>]*>)?\\s*\\(");
        Matcher m = newExpr.matcher(this.text);
        while (m.find()) {
            TypeInfo info;
            if (this.isExcluded(m.start()) || this.isInImportOrPackage(m.start())) continue;
            String className = m.group(1);
            int openParen = m.end() - 1;
            int closeParen = this.findMatchingParen(openParen);
            if (closeParen < 0) continue;
            String argsText = this.text.substring(openParen + 1, closeParen);
            boolean bl = this.isJavaScript() ? argsText.contains("function") || argsText.contains("=>") : argsText.contains("->");
            boolean hasLambda = bl;
            if (!hasLambda || (info = this.resolveType(className, m.start(1))) == null || !info.isResolved() || !info.hasConstructors()) continue;
            List<MethodCallInfo.Argument> arguments = this.parseMethodArguments(openParen + 1, closeParen, null, info);
            TypeInfo[] argTypes = (TypeInfo[])arguments.stream().map(MethodCallInfo.Argument::getResolvedType).toArray(TypeInfo[]::new);
            MethodInfo constructor = info.findConstructor(argTypes);
            if (constructor == null) {
                constructor = info.findConstructor(arguments.size());
            }
            if (constructor == null || constructor.getParameters().size() != arguments.size()) continue;
            this.parseMethodArguments(openParen + 1, closeParen, constructor, info);
        }
    }

    private void inferFieldAssignmentLambdaTypes() {
        if (this.isJavaScript()) {
            this.inferJSFieldAssignmentLambdaTypes();
        } else {
            this.inferJavaFieldAssignmentLambdaTypes();
        }
    }

    private void inferJavaFieldAssignmentLambdaTypes() {
        Matcher m = FIELD_DECL_PATTERN.matcher(ScriptDocument.normalizeGenericNewlines(this.text));
        while (m.find()) {
            MethodInfo sam;
            TypeInfo declaredType;
            String rhs;
            int rhsEnd;
            int rhsStart;
            String typeName = m.group(1);
            String varName = m.group(2);
            String delimiter = m.group(3);
            if (!"=".equals(delimiter)) continue;
            int absTypeStart = m.start(1);
            int absVarStart = m.start(2);
            if (this.isExcluded(absTypeStart) || this.isExcluded(absVarStart) || this.hasExcludedRange(absTypeStart, m.end(1)) || this.isInImportOrPackage(absTypeStart) || typeName.equals("return") || typeName.equals("if") || typeName.equals("while") || typeName.equals("for") || typeName.equals("switch") || typeName.equals("catch") || typeName.equals("new") || typeName.equals("throw")) continue;
            for (rhsStart = m.end(3); rhsStart < this.text.length() && Character.isWhitespace(this.text.charAt(rhsStart)); ++rhsStart) {
            }
            if (rhsStart >= this.text.length() || (rhsEnd = this.findStatementEnd(rhsStart)) <= rhsStart || !(rhs = this.text.substring(rhsStart, rhsEnd).trim()).contains("->") || (declaredType = this.resolveType(this.stripModifiers(typeName), absVarStart)) == null || !declaredType.isResolved() || !declaredType.isFunctionalInterface() || (sam = declaredType.getSingleAbstractMethod()) == null) continue;
            List<FieldInfo> samParams = sam.getParameters();
            for (InnerCallableScope scope : this.innerScopes) {
                if (scope.getKind() != InnerCallableScope.Kind.JAVA_LAMBDA || scope.getHeaderStart() < rhsStart || scope.getFullEnd() > rhsEnd + 1 || scope.getExpectedType() != null) continue;
                List<FieldInfo> lambdaParams = scope.getParameters();
                if (samParams.size() != lambdaParams.size()) continue;
                scope.setExpectedType(declaredType);
                for (int i = 0; i < lambdaParams.size(); ++i) {
                    TypeInfo inferredType = samParams.get(i).getTypeInfo();
                    if (inferredType == null) continue;
                    lambdaParams.get(i).setInferredType(inferredType);
                }
            }
        }
    }

    private void inferJSFieldAssignmentLambdaTypes() {
        Pattern varPattern = Pattern.compile("(?:var|let|const)\\s+(\\w+)(?:\\s*(=))?");
        Matcher m = varPattern.matcher(this.text);
        while (m.find()) {
            MethodInfo sam;
            TypeInfo declaredType;
            JSDocInfo jsDoc;
            String initializer;
            int initializerStart;
            int initializerEnd;
            int position;
            if (m.group(2) == null || this.isExcluded(position = m.start(1)) || (initializerEnd = this.findJsInitializerEnd(this.text, initializerStart = this.skipSegmentWhitespace(this.text, m.end(2)), true)) <= initializerStart || !(initializer = this.text.substring(initializerStart, initializerEnd).trim()).contains("function") && !initializer.contains("=>") || (jsDoc = this.jsDocParser.extractJSDocBefore(this.text, m.start())) == null || !jsDoc.hasTypeTag() || (declaredType = jsDoc.getDeclaredType()) == null || !declaredType.isResolved() || !declaredType.isFunctionalInterface() || (sam = declaredType.getSingleAbstractMethod()) == null) continue;
            List<FieldInfo> samParams = sam.getParameters();
            for (InnerCallableScope scope : this.innerScopes) {
                if (scope.getKind() != InnerCallableScope.Kind.JS_FUNCTION_EXPR && scope.getKind() != InnerCallableScope.Kind.JS_ARROW_FUNC || scope.getHeaderStart() < initializerStart || scope.getFullEnd() > initializerEnd + 1 || scope.getExpectedType() != null) continue;
                List<FieldInfo> funcParams = scope.getParameters();
                if (samParams.size() != funcParams.size()) continue;
                scope.setExpectedType(declaredType);
                for (int i = 0; i < funcParams.size(); ++i) {
                    TypeInfo inferredType = samParams.get(i).getTypeInfo();
                    if (inferredType == null) continue;
                    funcParams.get(i).setInferredType(inferredType);
                }
            }
        }
    }

    private void populateLambdaCache() {
        for (InnerCallableScope scope : this.innerScopes) {
            if (scope.getExpectedType() == null) continue;
            this.lambdaCache.put(scope.getHeaderStart(), new LambdaCacheEntry(scope, scope.getExpectedType()));
        }
    }

    private int findStatementEnd(int startPos) {
        int depth = 0;
        for (int i = startPos; i < this.text.length(); ++i) {
            char c = this.text.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                ++depth;
                continue;
            }
            if (!(c == ')' || c == '}' || c == ']' ? --depth < 0 : c == ';' && depth == 0)) continue;
            return i;
        }
        return this.text.length();
    }

    private void markImportedClassUsages(List<ScriptLine.Mark> marks) {
        Pattern classUsage = Pattern.compile("\\b([A-Za-z][a-zA-Z0-9_]*)\\s*\\.");
        Matcher m = classUsage.matcher(this.text);
        while (m.find()) {
            TypeInfo info;
            String className = m.group(1);
            int start = m.start(1);
            int end = m.end(1);
            if (this.isExcluded(start) || this.isInImportOrPackage(start)) continue;
            if (this.isJavaScript()) {
                if (this.typeResolver.isSyntheticType(className)) {
                    SyntheticType syntheticType = this.typeResolver.getSyntheticType(className);
                    marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.IMPORTED_CLASS, syntheticType.getTypeInfo()));
                    continue;
                }
                JSTypeRegistry jsRegistry = JSTypeRegistry.getInstance();
                if (jsRegistry.isGlobalImport(className)) {
                    TypeInfo globalType = TypeInfo.fromJSTypeInfo(jsRegistry.getGlobalImportType(className));
                    marks.add(new ScriptLine.Mark(m.start(1), m.end(1), TokenType.INTERFACE_DECL, globalType));
                    continue;
                }
            }
            if ((info = this.resolveType(className, start)) != null && info.isResolved()) {
                marks.add(new ScriptLine.Mark(start, end, info.getTokenType(), info));
                continue;
            }
            marks.add(new ScriptLine.Mark(start, end, TokenType.UNDEFINED_VAR));
        }
        Pattern typeUsage = Pattern.compile("\\b(new\\s+)?([A-Za-z][a-zA-Z0-9_]*)(?:\\s*<[^>]*>)?\\s*(?:\\(|\\[|\\b[a-z])");
        Matcher tm = typeUsage.matcher(this.text);
        while (tm.find()) {
            TypeInfo info;
            String className = tm.group(2);
            String newKeyword = tm.group(1);
            int start = tm.start(2);
            int end = tm.end(2);
            if (this.isExcluded(start) || this.isInImportOrPackage(start)) continue;
            String resolveKey = className;
            int outerQualStart = start;
            if (this.isPrecededByDot(start)) {
                int pos;
                for (pos = start - 1; pos >= 0 && Character.isWhitespace(this.text.charAt(pos)); --pos) {
                }
                while (pos >= 0 && this.text.charAt(pos) == '.') {
                    --pos;
                    while (pos >= 0 && Character.isWhitespace(this.text.charAt(pos))) {
                        --pos;
                    }
                    int identEnd = pos + 1;
                    while (pos > 0 && (Character.isLetterOrDigit(this.text.charAt(pos - 1)) || this.text.charAt(pos - 1) == '_')) {
                        --pos;
                    }
                    resolveKey = this.text.substring(pos, identEnd) + "." + resolveKey;
                    outerQualStart = pos--;
                    while (pos >= 0 && Character.isWhitespace(this.text.charAt(pos))) {
                        --pos;
                    }
                }
            }
            if ((info = this.resolveType(resolveKey, start)) == null || !info.isResolved()) {
                info = this.resolveType(className, start);
            }
            boolean isClassTypeInfo = false;
            ClassTypeInfo classRef = null;
            FieldInfo varInfo = null;
            if ((info == null || !info.isResolved()) && this.isJavaScript() && (varInfo = this.resolveVariable(className, start)) != null && varInfo.getTypeInfo() instanceof ClassTypeInfo) {
                classRef = (ClassTypeInfo)varInfo.getTypeInfo();
                info = classRef.getInstanceType();
                isClassTypeInfo = true;
            }
            if (info != null && info.isResolved()) {
                boolean isConstructorDecl;
                boolean isNewCreation;
                boolean bl = isNewCreation = newKeyword != null && newKeyword.trim().equals("new");
                if (!isNewCreation && outerQualStart < start) {
                    int checkPos;
                    for (checkPos = outerQualStart - 1; checkPos >= 0 && Character.isWhitespace(this.text.charAt(checkPos)); --checkPos) {
                    }
                    if (checkPos >= 2 && "new".equals(this.text.substring(checkPos - 2, checkPos + 1))) {
                        char beforeNew;
                        char c = beforeNew = checkPos >= 3 ? this.text.charAt(checkPos - 3) : (char)' ';
                        if (!Character.isLetterOrDigit(beforeNew) && beforeNew != '_') {
                            isNewCreation = true;
                        }
                    }
                }
                boolean bl2 = isConstructorDecl = info instanceof ScriptTypeInfo && className.equals(info.getSimpleName());
                if (isNewCreation || isConstructorDecl) {
                    int openParen;
                    int closeParen;
                    int closeAngle;
                    int searchPos;
                    for (searchPos = end; searchPos < this.text.length() && Character.isWhitespace(this.text.charAt(searchPos)); ++searchPos) {
                    }
                    if (searchPos < this.text.length() && this.text.charAt(searchPos) == '<' && (closeAngle = this.text.indexOf(62, searchPos)) >= 0) {
                        for (searchPos = closeAngle + 1; searchPos < this.text.length() && Character.isWhitespace(this.text.charAt(searchPos)); ++searchPos) {
                        }
                    }
                    if (searchPos < this.text.length() && this.text.charAt(searchPos) == '(' && (closeParen = this.findMatchingParen(openParen = searchPos)) >= 0) {
                        MethodCallInfo ctorCall;
                        if (isConstructorDecl && !isNewCreation) {
                            int braceSearch;
                            for (braceSearch = closeParen + 1; braceSearch < this.text.length() && Character.isWhitespace(this.text.charAt(braceSearch)); ++braceSearch) {
                            }
                            if (braceSearch >= this.text.length() || this.text.charAt(braceSearch) != '{') {
                                marks.add(new ScriptLine.Mark(start, end, info.getTokenType(), info));
                                continue;
                            }
                        }
                        List<MethodCallInfo.Argument> arguments = this.parseMethodArguments(openParen + 1, closeParen, null, info);
                        int argCount = arguments.size();
                        TypeInfo[] argTypes = (TypeInfo[])arguments.stream().map(MethodCallInfo.Argument::getResolvedType).toArray(TypeInfo[]::new);
                        MethodInfo constructor = null;
                        if (info.hasConstructors() && (constructor = info.findConstructor(argTypes)) == null) {
                            constructor = info.findConstructor(argCount);
                        }
                        if (constructor != null && constructor.getParameters().size() == arguments.size()) {
                            arguments = this.parseMethodArguments(openParen + 1, closeParen, constructor, info);
                        }
                        if (isClassTypeInfo) {
                            ctorCall = new MethodCallInfo(className, start, end, openParen, closeParen, arguments, classRef, constructor, false).setConstructor(true);
                            ctorCall.isClassTypeAccess = true;
                        } else {
                            ctorCall = MethodCallInfo.constructor(info, constructor, start, end, openParen, closeParen, arguments);
                        }
                        ctorCall.validate();
                        this.methodCalls.add(ctorCall);
                        TokenType type = isClassTypeInfo ? (varInfo.isGlobal() ? TokenType.GLOBAL_FIELD : TokenType.LOCAL_FIELD) : TokenType.METHOD_CALL;
                        marks.add(new ScriptLine.Mark(start, end, type, isClassTypeInfo ? varInfo : ctorCall));
                        continue;
                    }
                }
                marks.add(new ScriptLine.Mark(start, end, info.getTokenType(), info));
                continue;
            }
            marks.add(new ScriptLine.Mark(start, end, TokenType.UNDEFINED_VAR));
        }
    }

    private List<ScriptLine.Mark> resolveConflicts(List<ScriptLine.Mark> marks) {
        marks.sort((a, b) -> {
            if (a.start != b.start) {
                return Integer.compare(a.start, b.start);
            }
            return Integer.compare(b.type.getPriority(), a.type.getPriority());
        });
        ArrayList<ScriptLine.Mark> result = new ArrayList<ScriptLine.Mark>();
        for (ScriptLine.Mark m2 : marks) {
            boolean skip = false;
            for (int i = result.size() - 1; i >= 0; --i) {
                ScriptLine.Mark r = (ScriptLine.Mark)result.get(i);
                if (m2.start >= r.end || m2.end <= r.start) continue;
                if (r.type.getPriority() < m2.type.getPriority()) {
                    result.remove(i);
                    continue;
                }
                skip = true;
                break;
            }
            if (skip) continue;
            result.add(m2);
        }
        result.sort(Comparator.comparingInt(m -> m.start));
        return result;
    }

    private void computeIndentGuides(List<ScriptLine.Mark> marks) {
        for (ScriptLine scriptLine : this.lines) {
            scriptLine.clearIndentGuides();
        }
        HashSet<int[]> ignored = new HashSet<int[]>();
        for (ScriptLine.Mark m : marks) {
            if (m.type != TokenType.STRING && m.type != TokenType.COMMENT) continue;
            ignored.add(new int[]{m.start, m.end});
        }
        class OpenBrace {
            int lineIdx;
            int col;

            OpenBrace(int l, int c) {
                this.lineIdx = l;
                this.col = c;
            }
        }
        ArrayDeque<OpenBrace> arrayDeque = new ArrayDeque<OpenBrace>();
        int tabSize = 4;
        for (int li = 0; li < this.lines.size(); ++li) {
            ScriptLine line = this.lines.get(li);
            String s = line.getText();
            for (int i = 0; i < s.length(); ++i) {
                int absPos = line.getGlobalStart() + i;
                boolean isIgnored = false;
                for (int[] range : ignored) {
                    if (absPos < range[0] || absPos >= range[1]) continue;
                    isIgnored = true;
                    break;
                }
                if (isIgnored) continue;
                char c = s.charAt(i);
                if (c == '{') {
                    int leading = 0;
                    for (int k = 0; k < i; ++k) {
                        char ch = s.charAt(k);
                        leading += ch == '\t' ? 4 : 1;
                    }
                    arrayDeque.push(new OpenBrace(li, leading));
                    continue;
                }
                if (c != '}' || arrayDeque.isEmpty()) continue;
                OpenBrace open = (OpenBrace)arrayDeque.pop();
                if (open.lineIdx == li) continue;
                int from = Math.max(0, open.lineIdx + 1);
                int to = Math.min(this.lines.size() - 1, li);
                for (int l = from; l <= to; ++l) {
                    this.lines.get(l).addIndentGuide(open.col);
                }
            }
        }
    }

    FieldAccessInfo createFieldAccessInfo(String name, int start, int end, TypeInfo receiverType, FieldInfo fieldInfo, boolean isLastSegment, boolean isStaticAccess) {
        TypeInfo expectedType;
        FieldAccessInfo accessInfo = new FieldAccessInfo(name, start, end, receiverType, fieldInfo, isStaticAccess);
        if (isLastSegment && (expectedType = this.findExpectedTypeAtPosition(start)) != null) {
            accessInfo.setExpectedType(expectedType);
        }
        accessInfo.validate();
        this.fieldAccesses.add(accessInfo);
        return accessInfo;
    }

    public FieldInfo resolveVariable(String name, int position) {
        ScriptTypeInfo enclosingType;
        FieldInfo topLocal;
        Object innermostScope = this.findInnermostScopeAt(position);
        if (innermostScope instanceof InnerCallableScope) {
            InnerCallableScope scope;
            for (InnerCallableScope currentScope = scope = (InnerCallableScope)innermostScope; currentScope != null; currentScope = currentScope.getParentScope()) {
                FieldInfo param = currentScope.getParameter(name);
                if (param != null) {
                    return param;
                }
                FieldInfo local = currentScope.getLocals().get(name);
                if (local == null) continue;
                return local;
            }
            innermostScope = this.findMethodAtPosition(position);
        }
        if (innermostScope instanceof MethodInfo) {
            FieldInfo localInfo;
            MethodInfo method = (MethodInfo)innermostScope;
            if (method.hasParameter(name)) {
                return method.getParameter(name);
            }
            Map<String, List<FieldInfo>> locals = this.methodLocals.get(method.getDeclarationOffset());
            if (locals != null && (localInfo = this.pickVisibleLocal(locals.get(name), position)) != null) {
                return localInfo;
            }
        }
        if ((topLocal = this.pickVisibleTopLevelLocal(name, position)) != null) {
            return topLocal;
        }
        if (!this.isJavaScript() && (enclosingType = this.findEnclosingScriptType(position)) != null && enclosingType.hasField(name)) {
            return enclosingType.getFieldInfo(name);
        }
        if (this.globalFields.containsKey(name)) {
            return this.globalFields.get(name);
        }
        if (this.isJavaScript()) {
            TypeInfo typeInfo;
            JSTypeRegistry registry = JSTypeRegistry.getInstance();
            String globalObjectType = registry.getGlobalObjectType(name);
            if (globalObjectType != null && (typeInfo = this.resolveType(globalObjectType)) != null && typeInfo.isResolved()) {
                return FieldInfo.globalField(name, typeInfo, -1);
            }
            String editorGlobalType = this.editorGlobals.get(name);
            if (editorGlobalType != null) {
                TypeInfo typeInfo2 = this.resolveType(editorGlobalType);
                if (typeInfo2 == null || !typeInfo2.isResolved()) {
                    typeInfo2 = TypeInfo.ANY;
                }
                return FieldInfo.globalField(name, typeInfo2, -1);
            }
        }
        return null;
    }

    boolean isPrecededByDot(int position) {
        int i;
        if (position <= 0) {
            return false;
        }
        for (i = position - 1; i >= 0 && Character.isWhitespace(this.text.charAt(i)); --i) {
        }
        return i >= 0 && this.text.charAt(i) == '.' && !this.isExcluded(i);
    }

    boolean isInImportOrPackage(int position) {
        int lineStart;
        int i;
        if (position < 0 || position >= this.text.length()) {
            return false;
        }
        for (i = lineStart = (lineStart = this.text.lastIndexOf(10, position)) < 0 ? 0 : lineStart + 1; i < this.text.length() && Character.isWhitespace(this.text.charAt(i)); ++i) {
        }
        return this.text.startsWith("import", i) || this.text.startsWith("package", i);
    }

    boolean isFollowedByParen(int pos) {
        int check = this.skipWhitespace(pos);
        return check < this.text.length() && this.text.charAt(check) == '(';
    }

    boolean isFollowedByDot(int pos) {
        int check = this.skipWhitespace(pos);
        return check < this.text.length() && this.text.charAt(check) == '.';
    }

    int skipWhitespace(int pos) {
        while (pos < this.text.length() && Character.isWhitespace(this.text.charAt(pos))) {
            ++pos;
        }
        return pos;
    }

    MethodInfo findMethodAtPosition(int position) {
        for (MethodInfo method : this.getAllMethods()) {
            if (!method.containsPosition(position)) continue;
            return method;
        }
        for (MethodInfo constructor : this.getAllConstructors()) {
            if (!constructor.containsPosition(position)) continue;
            return constructor;
        }
        return null;
    }

    public Object findInnermostScopeAt(int position) {
        InnerCallableScope innermost = null;
        for (InnerCallableScope scope : this.innerScopes) {
            if (!scope.containsPosition(position) && !scope.containsHeaderPosition(position) || innermost != null && scope.getBodyStart() <= innermost.getBodyStart()) continue;
            innermost = scope;
        }
        if (innermost != null) {
            return innermost;
        }
        return this.findMethodAtPosition(position);
    }

    int findMatchingBrace(int openBraceIndex) {
        if (openBraceIndex < 0 || openBraceIndex >= this.text.length()) {
            return -1;
        }
        int depth = 0;
        for (int i = openBraceIndex; i < this.text.length(); ++i) {
            if (this.isExcluded(i)) continue;
            char c = this.text.charAt(i);
            if (c == '{') {
                ++depth;
                continue;
            }
            if (c != '}' || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    public List<ScriptLine> getLines() {
        return Collections.unmodifiableList(this.lines);
    }

    public List<ImportData> getImports() {
        return Collections.unmodifiableList(this.imports);
    }

    public List<MethodInfo> getMethods() {
        return Collections.unmodifiableList(this.methods);
    }

    public List<InnerCallableScope> getInnerScopes() {
        return Collections.unmodifiableList(this.innerScopes);
    }

    public ObjectLiteralParser.ObjectLiteralAnalysis getObjectLiteral(int braceOffset) {
        return this.objectLiterals.get(braceOffset);
    }

    public LambdaCacheEntry getLambdaCacheEntry(int headerOffset) {
        return this.lambdaCache.get(headerOffset);
    }

    public List<ScriptTypeInfo> getScriptTypes() {
        return new ArrayList<ScriptTypeInfo>(this.scriptTypesByFullName.values());
    }

    public List<MethodInfo> getAllMethods() {
        ArrayList<MethodInfo> allMethods = new ArrayList<MethodInfo>(this.methods);
        for (ScriptTypeInfo scriptType : this.scriptTypesByFullName.values()) {
            allMethods.addAll(scriptType.getAllMethodsFlat());
        }
        return allMethods;
    }

    public List<MethodInfo> getAllConstructors() {
        ArrayList<MethodInfo> allConstructors = new ArrayList<MethodInfo>();
        for (ScriptTypeInfo scriptType : this.scriptTypesByFullName.values()) {
            allConstructors.addAll(scriptType.getConstructors());
        }
        return allConstructors;
    }

    public List<MethodCallInfo> getMethodCalls() {
        ArrayList<MethodCallInfo> allCalls = new ArrayList<MethodCallInfo>(this.methodCalls);
        for (EnumConstantInfo constant : this.getAllEnumConstants()) {
            if (constant.getConstructorCall() == null) continue;
            allCalls.add(constant.getConstructorCall());
        }
        return allCalls;
    }

    public List<FieldAccessInfo> getFieldAccesses() {
        return Collections.unmodifiableList(this.fieldAccesses);
    }

    public List<EnumConstantInfo> getAllEnumConstants() {
        ArrayList<EnumConstantInfo> enums = new ArrayList<EnumConstantInfo>();
        for (ScriptTypeInfo scriptType : this.scriptTypesByFullName.values()) {
            if (!scriptType.hasEnumConstants()) continue;
            enums.addAll(scriptType.getEnumConstants().values());
        }
        return enums;
    }

    public AssignmentInfo findAssignmentAtPosition(int position) {
        AssignmentInfo assign;
        for (FieldInfo fieldInfo : this.globalFields.values()) {
            AssignmentInfo assign2 = fieldInfo.findAssignmentAtPosition(position);
            if (assign2 == null) continue;
            return assign2;
        }
        for (Map map : this.methodLocals.values()) {
            for (List fields : map.values()) {
                for (FieldInfo field : fields) {
                    AssignmentInfo assign3 = field.findAssignmentAtPosition(position);
                    if (assign3 == null) continue;
                    return assign3;
                }
            }
        }
        for (List list : this.topLevelLocals.values()) {
            for (FieldInfo field : list) {
                assign = field.findAssignmentAtPosition(position);
                if (assign == null) continue;
                return assign;
            }
        }
        for (AssignmentInfo assignmentInfo : this.declarationErrors) {
            if (!assignmentInfo.containsLhsPosition(position)) continue;
            return assignmentInfo;
        }
        for (AssignmentInfo assignmentInfo : this.externalFieldAssignments) {
            if (!assignmentInfo.containsLhsPosition(position)) continue;
            return assignmentInfo;
        }
        for (AssignmentInfo assignmentInfo : this.externalFieldAssignments) {
            if (!assignmentInfo.containsRhsPosition(position)) continue;
            return assignmentInfo;
        }
        for (ScriptTypeInfo scriptTypeInfo : this.scriptTypesByFullName.values()) {
            for (FieldInfo field : scriptTypeInfo.getFields().values()) {
                assign = field.findAssignmentAtPosition(position);
                if (assign == null) continue;
                return assign;
            }
        }
        return null;
    }

    public Map<String, FieldInfo> getGlobalFields() {
        return Collections.unmodifiableMap(this.globalFields);
    }

    public List<FieldInfo> getAvailableVariablesAt(int position) {
        ScriptTypeInfo enclosingType;
        ArrayList<FieldInfo> variables = new ArrayList<FieldInfo>();
        Object innermostScope = this.findInnermostScopeAt(position);
        if (innermostScope instanceof InnerCallableScope) {
            InnerCallableScope scope;
            for (InnerCallableScope currentScope = scope = (InnerCallableScope)innermostScope; currentScope != null; currentScope = currentScope.getParentScope()) {
                variables.addAll(currentScope.getParameters());
                for (FieldInfo fieldInfo : currentScope.getLocals().values()) {
                    if (!fieldInfo.isVisibleAt(position)) continue;
                    variables.add(fieldInfo);
                }
            }
            innermostScope = this.findMethodAtPosition(position);
        }
        if (innermostScope instanceof MethodInfo) {
            MethodInfo method = (MethodInfo)innermostScope;
            variables.addAll(method.getParameters());
            Map<String, List<FieldInfo>> locals = this.methodLocals.get(method.getDeclarationOffset());
            if (locals != null) {
                for (List list : locals.values()) {
                    FieldInfo best = this.pickVisibleLocal(list, position);
                    if (best == null) continue;
                    variables.add(best);
                }
            }
        }
        variables.addAll(this.getVisibleTopLevelLocals(position));
        for (FieldInfo globalField : this.globalFields.values()) {
            if (!globalField.isVisibleAt(position)) continue;
            variables.add(globalField);
        }
        if (!this.isJavaScript() && (enclosingType = this.findEnclosingScriptType(position)) != null) {
            for (FieldInfo field : enclosingType.getFields().values()) {
                if (!field.isVisibleAt(position)) continue;
                variables.add(field);
            }
        }
        if (this.isJavaScript()) {
            JSTypeRegistry registry = JSTypeRegistry.getInstance();
            for (String globalName : registry.getGlobalEngineObjects().keySet()) {
                FieldInfo fieldInfo = this.resolveVariable(globalName, position);
                if (fieldInfo == null || !fieldInfo.isResolved()) continue;
                variables.add(fieldInfo);
            }
            for (String globalName : this.editorGlobals.keySet()) {
                FieldInfo fieldInfo = this.resolveVariable(globalName, position);
                if (fieldInfo == null || !fieldInfo.isResolved()) continue;
                variables.add(fieldInfo);
            }
        }
        return variables;
    }

    @Deprecated
    public List<AssignmentInfo> getAllErroredAssignments() {
        ArrayList<AssignmentInfo> errored = new ArrayList<AssignmentInfo>();
        for (FieldInfo fieldInfo : this.globalFields.values()) {
            errored.addAll(fieldInfo.getErroredAssignments());
        }
        for (Map map : this.methodLocals.values()) {
            for (List fields : map.values()) {
                for (FieldInfo field : fields) {
                    errored.addAll(field.getErroredAssignments());
                }
            }
        }
        for (List list : this.topLevelLocals.values()) {
            for (FieldInfo field : list) {
                errored.addAll(field.getErroredAssignments());
            }
        }
        for (AssignmentInfo assignmentInfo : this.externalFieldAssignments) {
            if (!assignmentInfo.hasError()) continue;
            errored.add(assignmentInfo);
        }
        for (ScriptTypeInfo scriptTypeInfo : this.scriptTypesByFullName.values()) {
            for (FieldInfo field : scriptTypeInfo.getFields().values()) {
                errored.addAll(field.getErroredAssignments());
            }
        }
        for (InnerCallableScope innerCallableScope : this.getInnerScopes()) {
            for (FieldInfo field : innerCallableScope.getParameters()) {
                errored.addAll(field.getErroredAssignments());
            }
            for (FieldInfo field : innerCallableScope.getLocals().values()) {
                errored.addAll(field.getErroredAssignments());
            }
        }
        errored.addAll(this.declarationErrors);
        return errored;
    }

    public void addError(Token token, int startPos, int endPos, String message) {
        this.errors.add(new DocumentError(token, startPos, endPos, message));
    }

    public List<DocumentError> getErrors() {
        return Collections.unmodifiableList(this.errors);
    }

    private void populateErrors() {
        List<MethodInfo> allMethods = this.getAllMethods();
        for (MethodCallInfo call : this.getMethodCalls()) {
            boolean isDeclaration = false;
            int methodStart = call.getMethodNameStart();
            for (MethodInfo methodInfo : allMethods) {
                if (call.isConstructor() || methodInfo.getDeclarationOffset() > methodStart || methodInfo.getBodyStart() < methodStart) continue;
                isDeclaration = true;
                break;
            }
            if (isDeclaration) continue;
            if (call.hasArgCountError()) {
                int methodEnd = methodStart + call.getMethodName().length();
                this.addError(null, methodStart, methodEnd, call.getErrorMessage());
                continue;
            }
            if (call.hasArgTypeError()) {
                for (MethodCallInfo.ArgumentTypeError argumentTypeError : call.getArgumentTypeErrors()) {
                    MethodCallInfo.Argument arg = argumentTypeError.getArg();
                    this.addError(null, arg.getStartOffset(), arg.getEndOffset(), argumentTypeError.getMessage());
                }
                continue;
            }
            if (!call.hasError()) continue;
            this.addError(null, call.getMethodNameStart(), call.getCloseParenOffset() + 1, call.getErrorMessage());
        }
        for (AssignmentInfo assign : this.getAllErroredAssignments()) {
            int underlineEnd;
            int underlineStart;
            if (assign.isLhsError()) {
                underlineStart = assign.getLhsStart();
                underlineEnd = assign.getLhsEnd();
            } else if (assign.isRhsError()) {
                underlineStart = assign.getRhsStart();
                underlineEnd = assign.getRhsEnd();
            } else {
                if (!assign.isFullLineError()) continue;
                underlineStart = assign.getStatementStart();
                underlineEnd = assign.getRhsEnd();
            }
            this.addError(null, underlineStart, underlineEnd, assign.getErrorMessage());
        }
        for (MethodInfo method : allMethods) {
            if (!method.isDeclaration() || !method.hasError()) continue;
            if (method.hasReturnStatementErrors()) {
                for (MethodInfo.ReturnStatementError returnError : method.getReturnStatementErrors()) {
                    this.addError(null, returnError.getStartOffset(), returnError.getEndOffset(), returnError.getMessage());
                }
                continue;
            }
            if (method.hasMissingReturnError()) {
                int methodNameStart = method.getNameOffset();
                int methodNameEnd = methodNameStart + method.getName().length();
                this.addError(null, methodNameStart, methodNameEnd, method.getErrorMessage());
                continue;
            }
            if (method.hasParameterErrors()) {
                for (MethodInfo.ParameterError paramError : method.getParameterErrors()) {
                    FieldInfo param = paramError.getParameter();
                    if (param == null || param.getDeclarationOffset() < 0) continue;
                    int n = param.getDeclarationOffset();
                    int paramEnd = n + param.getName().length();
                    this.addError(null, n, paramEnd, paramError.getMessage());
                }
                continue;
            }
            if (!method.hasError()) continue;
            this.addError(null, method.getFullDeclarationOffset(), method.getDeclarationEnd(), method.getErrorMessage());
        }
        for (EnumConstantInfo enumConst : this.getAllEnumConstants()) {
            if (enumConst == null || !enumConst.hasError()) continue;
            this.addError(null, 0, 0, enumConst.getErrorMessage());
        }
        for (ScriptTypeInfo type : this.getScriptTypes()) {
            if (!type.hasError()) continue;
            int typeStart = type.getDeclarationOffset();
            int typeEnd = type.getBodyStart();
            for (ScriptTypeInfo.MissingMethodError missingMethodError : type.getMissingMethodErrors()) {
                this.addError(null, typeStart, typeEnd, missingMethodError.getMessage());
            }
            for (ScriptTypeInfo.ConstructorMismatchError constructorMismatchError : type.getConstructorMismatchErrors()) {
                this.addError(null, typeStart, typeEnd, constructorMismatchError.getMessage());
            }
            String msg = type.getErrorMessage();
            if (msg == null || msg.isEmpty()) continue;
            this.addError(null, typeStart, typeEnd, type.getErrorMessage());
        }
    }

    public TypeResolver getTypeResolver() {
        return this.typeResolver;
    }

    public ScriptLine getLine(int index) {
        for (ScriptLine line : this.lines) {
            if (line.getLineIndex() != index) continue;
            return line;
        }
        return null;
    }

    public ScriptLine getLineAt(int globalPosition) {
        for (ScriptLine line : this.lines) {
            if (!line.containsPosition(globalPosition)) continue;
            return line;
        }
        return null;
    }

    public int getLineIndexAt(int globalPosition) {
        for (int i = 0; i < this.lines.size(); ++i) {
            if (!this.lines.get(i).containsPosition(globalPosition)) continue;
            return i;
        }
        return -1;
    }

    public List<Token> getTokensInRange(int start, int end) {
        ArrayList<Token> result = new ArrayList<Token>();
        if (start < 0 || end <= start) {
            return result;
        }
        for (ScriptLine line : this.lines) {
            if (line.getGlobalEnd() <= start) continue;
            if (line.getGlobalStart() >= end) break;
            for (Token token : line.getTokens()) {
                if (token.getGlobalEnd() <= start || token.getGlobalStart() >= end) continue;
                result.add(token);
            }
        }
        return result;
    }

    public TypeInfo findExpectedTypeAtPosition(int position) {
        int typeStart;
        int varNameStart;
        int pos;
        for (pos = position - 1; pos >= 0 && Character.isWhitespace(this.text.charAt(pos)); --pos) {
        }
        int equalsPos = -1;
        int depth = 0;
        for (int i = pos; i >= 0; --i) {
            if (this.isExcluded(i)) continue;
            char c = this.text.charAt(i);
            if (c == ')' || c == ']') {
                ++depth;
                continue;
            }
            if (c == '(' || c == '[') {
                --depth;
                continue;
            }
            if (c == '=' && depth == 0) {
                if (i > 0 && "!<>=".indexOf(this.text.charAt(i - 1)) >= 0 || i < this.text.length() - 1 && this.text.charAt(i + 1) == '=') continue;
                equalsPos = i;
                break;
            }
            if (c == ';' || c == '{' || c == '}') break;
        }
        if (equalsPos < 0) {
            return null;
        }
        for (pos = equalsPos - 1; pos >= 0 && Character.isWhitespace(this.text.charAt(pos)); --pos) {
        }
        if (pos < 0) {
            return null;
        }
        int varNameEnd = pos + 1;
        for (varNameStart = pos; varNameStart >= 0 && Character.isJavaIdentifierPart(this.text.charAt(varNameStart)); --varNameStart) {
        }
        if (++varNameStart >= varNameEnd) {
            return null;
        }
        String varName = this.text.substring(varNameStart, varNameEnd);
        for (pos = varNameStart - 1; pos >= 0 && Character.isWhitespace(this.text.charAt(pos)); --pos) {
        }
        if (pos < 0) {
            return this.lookupVariableType(varName, position);
        }
        int typeEnd = pos + 1;
        for (typeStart = pos; typeStart >= 0 && (Character.isJavaIdentifierPart(this.text.charAt(typeStart)) || this.text.charAt(typeStart) == '<' || this.text.charAt(typeStart) == '>' || this.text.charAt(typeStart) == '[' || this.text.charAt(typeStart) == ']' || this.text.charAt(typeStart) == ','); --typeStart) {
        }
        if (++typeStart >= typeEnd) {
            return this.lookupVariableType(varName, position);
        }
        String typeStr = this.text.substring(typeStart, typeEnd).trim();
        if (typeStr.equals("var") || typeStr.equals("let") || typeStr.equals("const")) {
            return null;
        }
        return this.resolveType(typeStr);
    }

    private TypeInfo lookupVariableType(String varName, int position) {
        FieldInfo field;
        Map<String, List<FieldInfo>> locals;
        MethodInfo containingMethod = this.findMethodAtPosition(position);
        if (containingMethod != null && (locals = this.methodLocals.get(containingMethod.getDeclarationOffset())) != null && (field = this.pickVisibleLocal(locals.get(varName), position)) != null) {
            return field.getTypeInfo();
        }
        FieldInfo topLevelLocal = this.pickVisibleTopLevelLocal(varName, position);
        if (topLevelLocal != null) {
            return topLevelLocal.getTypeInfo();
        }
        if (this.globalFields.containsKey(varName)) {
            return this.globalFields.get(varName).getTypeInfo();
        }
        return null;
    }

    public MethodInfo findContainingMethod(int position) {
        return this.findMethodAtPosition(position);
    }

    public Map<String, FieldInfo> getLocalsForMethod(MethodInfo method) {
        if (method == null) {
            return null;
        }
        Map<String, List<FieldInfo>> locals = this.methodLocals.get(method.getDeclarationOffset());
        if (locals == null) {
            return null;
        }
        HashMap<String, FieldInfo> flattened = new HashMap<String, FieldInfo>();
        for (Map.Entry<String, List<FieldInfo>> e : locals.entrySet()) {
            List<FieldInfo> fields = e.getValue();
            if (fields == null || fields.isEmpty()) continue;
            flattened.put(e.getKey(), fields.get(0));
        }
        return flattened;
    }

    public Set<TypeInfo> getImportedTypes() {
        HashSet<TypeInfo> types = new HashSet<TypeInfo>();
        for (ImportData imp : this.imports) {
            TypeInfo type;
            if (imp.isWildcard() || !imp.isResolved() || (type = this.typeResolver.resolveSimpleName(imp.getSimpleName(), this.importsBySimpleName, this.wildcardPackages)) == null || !type.isResolved()) continue;
            types.add(type);
        }
        for (String pkg : this.wildcardPackages) {
            TypeInfo outerType = this.typeResolver.resolveFullName(pkg);
            if (outerType == null || !outerType.isResolved()) continue;
            types.add(outerType);
        }
        return types;
    }

    public Map<String, ScriptTypeInfo> getScriptTypesMap() {
        return Collections.unmodifiableMap(this.scriptTypes);
    }

    public Map<String, ScriptTypeInfo> getAllScriptTypes() {
        return Collections.unmodifiableMap(this.scriptTypesByFullName);
    }

    private static class ChainSegment {
        final String name;
        final int start;
        final int end;
        final boolean isMethodCall;
        final String arguments;
        final boolean hasArrayAccess;
        final String bracketKey;

        ChainSegment(String name, int start, int end, boolean isMethodCall, String arguments, boolean hasArrayAccess, String bracketKey) {
            this.name = name;
            this.start = start;
            this.end = end;
            this.isMethodCall = isMethodCall;
            this.arguments = arguments;
            this.hasArrayAccess = hasArrayAccess;
            this.bracketKey = bracketKey;
        }
    }

    private static class RawTypeDeclaration {
        final String name;
        final TypeInfo.Kind kind;
        final int declOffset;
        final int bodyStart;
        final int bodyEnd;
        final int modifiers;
        final String typeParamsClause;
        final int typeParamsClauseOffset;
        final String extendsClause;
        final String implementsClause;
        final JSDocInfo jsDoc;

        RawTypeDeclaration(String name, TypeInfo.Kind kind, int declOffset, int bodyStart, int bodyEnd, int modifiers, String typeParamsClause, int typeParamsClauseOffset, String extendsClause, String implementsClause, JSDocInfo jsDoc) {
            this.name = name;
            this.kind = kind;
            this.declOffset = declOffset;
            this.bodyStart = bodyStart;
            this.bodyEnd = bodyEnd;
            this.modifiers = modifiers;
            this.typeParamsClause = typeParamsClause;
            this.typeParamsClauseOffset = typeParamsClauseOffset;
            this.extendsClause = extendsClause;
            this.implementsClause = implementsClause;
            this.jsDoc = jsDoc;
        }
    }

    static class LambdaCacheEntry {
        final InnerCallableScope scope;
        final TypeInfo expectedType;

        LambdaCacheEntry(InnerCallableScope scope, TypeInfo expectedType) {
            this.scope = scope;
            this.expectedType = expectedType;
        }
    }
}

