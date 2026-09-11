/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.util.TextContainer;
import noppes.npcs.client.gui.util.script.ClassPathFinder;
import noppes.npcs.client.gui.util.script.MethodBlock;

@Deprecated
public class JavaTextContainer
extends TextContainer {
    public static final Pattern MODIFIER = Pattern.compile("\\b(public|protected|private|static|final|abstract|synchronized|native|default)\\b");
    public static final Pattern KEYWORD = Pattern.compile("\\b(null|boolean|int|float|double|long|char|byte|short|void|if|else|switch|case|for|while|do|try|catch|finally|return|throw|var|let|const|function|continue|break|this|new|typeof|instanceof|import)\\b");
    public static final Pattern CLASS_DECL = Pattern.compile("\\b(class|interface|enum)\\s+([A-Za-z_][a-zA-Z0-9_]*)");
    public static final Pattern NEW_TYPE = Pattern.compile("\\bnew\\s+([A-Za-z_][a-zA-Z0-9_]*)");
    public static final Pattern METHOD_DECL = Pattern.compile("\\b([A-Za-z_][a-zA-Z0-9_<>\\[\\]]*)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(");
    public static final Pattern METHOD_CALL = Pattern.compile("([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\(");
    public static final Pattern GLOBAL_FIELD_DECL = Pattern.compile("\\b([A-Za-z_][a-zA-Z0-9_<>\\[\\]]*)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*(=|;)");
    public static final Pattern LOCAL_FIELD_DECL = Pattern.compile("\\b([A-Z][a-zA-Z0-9_<>\\[\\]]*|[a-z][a-zA-Z0-9_]*)\\s+([a-zA-Z_][a-zA-Z0-9_]*)\\s*(=|;)");
    public static final Pattern STRING = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1");
    public static final Pattern COMMENT = Pattern.compile("/\\*[\\s\\S]*?(?:\\*/|$)|//.*|#.*");
    public static final Pattern NUMBER = Pattern.compile("\\b-?(?:0[xX][\\dA-Fa-f]+|0[bB][01]+|0[oO][0-7]+|\\d*\\.?\\d+(?:[Ee][+-]?\\d+)?(?:[fFbBdDlLsS])?|NaN|null|Infinity|true|false)\\b");
    public static final Pattern IMPORT = Pattern.compile("(?m)\\bimport\\s+(?:static\\s+)?([A-Za-z_][A-Za-z0-9_]*(?:\\s*\\.\\s*[A-Za-z_][A-Za-z0-9_]*)*)(?:\\s*\\.\\s*\\*?)?\\s*(?:;|$)");
    private final ClassPathFinder classPathFinder = new ClassPathFinder();
    public List<LineData> lines = new ArrayList<LineData>();
    public List<MethodBlock> methodBlocks = new ArrayList<MethodBlock>();
    private List<String> globalFields = new ArrayList<String>();
    private List<String> localFields = new ArrayList<String>();
    private Map<String, String> importedClasses = new HashMap<String, String>();
    private Set<String> importedPackages = new HashSet<String>();
    private Set<String> unresolvedClasses = new HashSet<String>();
    private List<ImportEntry> importEntries = new ArrayList<ImportEntry>();

    public JavaTextContainer(String text) {
        super(text);
    }

    private int mapNormalizedOffsetToOriginal(String orig, int normalizedOffset, int pathStart) {
        if (orig == null || orig.isEmpty()) {
            return pathStart;
        }
        int norm = 0;
        for (int i = 0; i < orig.length(); ++i) {
            char c = orig.charAt(i);
            if (Character.isLetterOrDigit(c) || c == '_' || c == '.') {
                ++norm;
            }
            if (norm < normalizedOffset) continue;
            return pathStart + i;
        }
        return pathStart + orig.length();
    }

    public void init(String text, int width, int height) {
        this.text = text == null ? "" : text.replaceAll("\\r?\\n|\\r", "\n");
        this.lines.clear();
        String[] split = text.split("\n", -1);
        int totalChars = 0;
        for (String l : split) {
            StringBuilder line = new StringBuilder();
            Matcher m = this.regexWord.matcher(l);
            int i = 0;
            while (m.find()) {
                String word = l.substring(i, m.start());
                if (ClientProxy.Font.width(line + word) > width - 10) {
                    this.lines.add(new LineData(line.toString(), totalChars, totalChars += line.length()));
                    line = new StringBuilder();
                }
                line.append(word);
                i = m.start();
            }
            this.lines.add(new LineData(line.toString(), totalChars, totalChars += line.length() + 1));
        }
        this.linesCount = this.lines.size();
        this.totalHeight = this.linesCount * this.lineHeight;
        this.visibleLines = Math.max(height / this.lineHeight - 1, 1);
    }

    private void collectImports() {
        this.importedClasses.clear();
        this.importedPackages.clear();
        this.importEntries.clear();
        this.unresolvedClasses.clear();
        this.classPathFinder.clearCache();
        List<int[]> excluded = MethodBlock.getExcludedRanges(this.text);
        Matcher m = IMPORT.matcher(this.text);
        while (m.find()) {
            String simpleName;
            if (this.isInExcludedRange(m.start(), excluded)) continue;
            String fullPath = m.group(1).trim();
            String matchText = m.group(0);
            int pathStart = m.start(1);
            int pathEnd = m.end(1);
            int importKwStart = m.start();
            int importKwEnd = Math.min(importKwStart + 6, this.text.length());
            boolean isWildcard = matchText.contains("*");
            int starIndex = matchText.indexOf(42);
            int absStar = starIndex != -1 ? m.start() + starIndex : -1;
            this.importEntries.add(new ImportEntry(fullPath, matchText, pathStart, pathEnd, importKwStart, importKwEnd, absStar, isWildcard));
            if (isWildcard) {
                this.importedPackages.add(fullPath);
                continue;
            }
            ClassPathFinder.ResolveResult result = this.classPathFinder.resolve(fullPath);
            int lastDot = fullPath.lastIndexOf(46);
            String string = simpleName = lastDot >= 0 ? fullPath.substring(lastDot + 1) : fullPath;
            if (!simpleName.isEmpty()) {
                if (result.found && result.classInfo != null) {
                    this.importedClasses.put(simpleName, result.classInfo.resolvedName);
                } else {
                    this.importedClasses.put(simpleName, fullPath);
                    this.unresolvedClasses.add(simpleName);
                }
            }
            ImportEntry last = this.importEntries.get(this.importEntries.size() - 1);
            last.resolveResult = result;
        }
    }

    private void highlightGenericTypes(String genericContent, int contentStart, List<Mark> marks, List<int[]> excluded) {
        if (genericContent == null || genericContent.isEmpty()) {
            return;
        }
        List<ClassPathFinder.TypeOccurrence> occurrences = this.classPathFinder.parseGenericTypes(genericContent, this.importedClasses, this.importedPackages);
        for (ClassPathFinder.TypeOccurrence occ : occurrences) {
            TokenType tokenType;
            int absStart = contentStart + occ.startOffset;
            int absEnd = contentStart + occ.endOffset;
            if (this.isInExcludedRange(absStart, excluded)) continue;
            switch (occ.type) {
                case INTERFACE: {
                    tokenType = TokenType.INTERFACE_DECL;
                    break;
                }
                case ENUM: {
                    tokenType = TokenType.ENUM_DECL;
                    break;
                }
                default: {
                    tokenType = TokenType.IMPORTED_CLASS;
                }
            }
            marks.add(new Mark(absStart, absEnd, tokenType));
        }
    }

    private void collectFields() {
        this.globalFields.clear();
        this.localFields.clear();
        this.methodBlocks.clear();
        this.methodBlocks = MethodBlock.collectMethodBlocks(this.text);
        List<int[]> excludedRanges = MethodBlock.getExcludedRanges(this.text);
        Matcher mGlobal = GLOBAL_FIELD_DECL.matcher(this.text);
        while (mGlobal.find()) {
            String varName = mGlobal.group(2);
            int varPosition = mGlobal.start(2);
            if (this.isInExcludedRange(varPosition, excludedRanges)) continue;
            boolean isInsideMethod = false;
            for (MethodBlock block : this.methodBlocks) {
                if (!block.containsPosition(varPosition)) continue;
                isInsideMethod = true;
                break;
            }
            if (isInsideMethod) continue;
            this.globalFields.add(varName);
        }
        for (MethodBlock block : this.methodBlocks) {
            for (String var : block.localVariables) {
                if (this.globalFields.contains(var) || this.localFields.contains(var)) continue;
                this.localFields.add(var);
            }
        }
    }

    private MethodBlock findMethodBlockAtPosition(int position) {
        for (MethodBlock block : this.methodBlocks) {
            if (!block.containsPosition(position)) continue;
            return block;
        }
        return null;
    }

    private void highlightVariableReferences(List<Mark> marks) {
        HashSet<String> knownIdentifiers = new HashSet<String>(Arrays.asList("boolean", "int", "float", "double", "long", "char", "byte", "short", "void", "null", "true", "false", "if", "else", "switch", "case", "for", "while", "do", "try", "catch", "finally", "return", "throw", "var", "let", "const", "function", "continue", "break", "this", "new", "typeof", "instanceof", "class", "interface", "extends", "implements", "import", "package", "public", "private", "protected", "static", "final", "abstract", "synchronized", "native", "default", "enum", "throws", "super", "assert", "volatile", "transient", "strictfp", "goto", "undefined", "NaN", "Infinity", "arguments", "prototype", "constructor", "String", "Object", "Array", "Math", "System", "Integer", "Double", "Float", "Boolean", "Long", "Byte", "Short", "Character", "List", "Map", "Set"));
        Pattern thisFieldPattern = Pattern.compile("\\bthis\\s*\\.\\s*([a-zA-Z_][a-zA-Z0-9_]*)");
        Matcher thisFieldMatcher = thisFieldPattern.matcher(this.text);
        while (thisFieldMatcher.find()) {
            String fieldName = thisFieldMatcher.group(1);
            int fieldPos = thisFieldMatcher.start(1);
            if (this.globalFields.contains(fieldName)) {
                marks.add(new Mark(fieldPos, thisFieldMatcher.end(1), TokenType.GLOBAL_FIELD));
                continue;
            }
            marks.add(new Mark(fieldPos, thisFieldMatcher.end(1), TokenType.UNDEFINED_VAR));
        }
        HashSet<String> scriptFunctionParams = new HashSet<String>();
        Pattern funcPattern = Pattern.compile("\\bfunction\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*\\(([^)]*)\\)");
        Matcher funcM = funcPattern.matcher(this.text);
        while (funcM.find()) {
            String inside = funcM.group(1);
            if (inside == null || inside.trim().isEmpty()) continue;
            for (String p : inside.split(",")) {
                String pn = p.trim();
                if (pn.isEmpty()) continue;
                scriptFunctionParams.add(pn);
            }
        }
        Pattern chainPattern = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*(?:\\s*\\.\\s*[a-zA-Z_][a-zA-Z0-9_]*)+)\\b");
        Matcher chainMatcher = chainPattern.matcher(this.text);
        while (chainMatcher.find()) {
            MethodBlock mbForRoot;
            String root;
            if (this.isInExcludedRange(chainMatcher.start(), MethodBlock.getExcludedRanges(this.text))) continue;
            String chain = chainMatcher.group(1);
            int absStart = chainMatcher.start(1);
            ArrayList<String> segs = new ArrayList<String>();
            ArrayList<Integer> segPos = new ArrayList<Integer>();
            Matcher segM = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*").matcher(chain);
            while (segM.find()) {
                segs.add(segM.group());
                segPos.add(absStart + segM.start());
            }
            if (segs.size() < 2 || Character.isUpperCase((root = (String)segs.get(0)).charAt(0))) continue;
            boolean rootIsKnown = false;
            if (this.globalFields.contains(root) || this.localFields.contains(root)) {
                rootIsKnown = true;
            }
            if ((mbForRoot = this.findMethodBlockAtPosition((Integer)segPos.get(0))) != null && mbForRoot.parameters.contains(root)) {
                rootIsKnown = true;
            }
            if (scriptFunctionParams.contains(root)) {
                rootIsKnown = true;
            }
            if ("this".equals(root)) {
                rootIsKnown = true;
            }
            if (!rootIsKnown) continue;
            for (int si = 1; si < segs.size(); ++si) {
                int sAbs = (Integer)segPos.get(si);
                int sEnd = sAbs + ((String)segs.get(si)).length();
                marks.add(new Mark(sAbs, sEnd, TokenType.GLOBAL_FIELD));
            }
        }
        Pattern objFieldPattern = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*)\\s*\\.\\s*([a-zA-Z_][a-zA-Z0-9_]*)");
        Matcher objFieldMatcher = objFieldPattern.matcher(this.text);
        while (objFieldMatcher.find()) {
            String fieldName = objFieldMatcher.group(2);
            int fieldPos = objFieldMatcher.start(2);
            String objName = objFieldMatcher.group(1);
            if (objName.equals("this")) continue;
            marks.add(new Mark(fieldPos, objFieldMatcher.end(2), TokenType.GLOBAL_FIELD));
        }
        Pattern identifier = Pattern.compile("\\b([a-zA-Z_][a-zA-Z0-9_]*)\\b");
        Matcher m = identifier.matcher(this.text);
        while (m.find()) {
            String name = m.group(1);
            int position = m.start(1);
            if (knownIdentifiers.contains(name) || this.isFieldAccess(position) || this.isInImportOrPackageStatement(position)) continue;
            MethodBlock methodBlock = this.findMethodBlockAtPosition(position);
            if (methodBlock != null) {
                if (methodBlock.parameters.contains(name)) {
                    marks.add(new Mark(m.start(1), m.end(1), TokenType.PARAMETER));
                    continue;
                }
                if (methodBlock.isLocalDeclaredAtPosition(name, position) || this.localFields.contains(name)) {
                    marks.add(new Mark(m.start(1), m.end(1), TokenType.LOCAL_FIELD));
                    continue;
                }
                if (this.globalFields.contains(name)) {
                    marks.add(new Mark(m.start(1), m.end(1), TokenType.GLOBAL_FIELD));
                    continue;
                }
                if (Character.isUpperCase(name.charAt(0)) || this.isMethodCall(position) || this.isTypeReference(name, position)) continue;
                marks.add(new Mark(m.start(1), m.end(1), TokenType.UNDEFINED_VAR));
                continue;
            }
            if (this.globalFields.contains(name) || this.localFields.contains(name)) {
                marks.add(new Mark(m.start(1), m.end(1), TokenType.GLOBAL_FIELD));
                continue;
            }
            if (Character.isUpperCase(name.charAt(0)) || this.isMethodCall(position) || this.isTypeReference(name, position)) continue;
            marks.add(new Mark(m.start(1), m.end(1), TokenType.UNDEFINED_VAR));
        }
    }

    private boolean isInImportOrPackageStatement(int position) {
        int i;
        if (position < 0 || position >= this.text.length()) {
            return false;
        }
        int lineStart = this.text.lastIndexOf(10, position);
        lineStart = lineStart < 0 ? 0 : lineStart + 1;
        int lineEnd = this.text.indexOf(10, position);
        lineEnd = lineEnd < 0 ? this.text.length() : lineEnd;
        for (i = lineStart; i < lineEnd && Character.isWhitespace(this.text.charAt(i)); ++i) {
        }
        if (i + 6 <= lineEnd && this.text.startsWith("import", i)) {
            int after = i + 6;
            return after == lineEnd || Character.isWhitespace(this.text.charAt(after));
        }
        if (i + 7 <= lineEnd && this.text.startsWith("package", i)) {
            int after = i + 7;
            return after == lineEnd || Character.isWhitespace(this.text.charAt(after));
        }
        return false;
    }

    private boolean isFieldAccess(int position) {
        int i;
        if (position <= 0) {
            return false;
        }
        for (i = position - 1; i >= 0 && Character.isWhitespace(this.text.charAt(i)); --i) {
        }
        return i >= 0 && this.text.charAt(i) == '.';
    }

    private boolean isMethodCall(int position) {
        int i;
        for (i = position; i < this.text.length() && (Character.isLetterOrDigit(this.text.charAt(i)) || this.text.charAt(i) == '_'); ++i) {
        }
        while (i < this.text.length() && Character.isWhitespace(this.text.charAt(i))) {
            ++i;
        }
        return i < this.text.length() && this.text.charAt(i) == '(';
    }

    private boolean isTypeReference(String name, int position) {
        String before;
        if (position > 4 && (before = this.text.substring(Math.max(0, position - 5), position)).endsWith("new ")) {
            return true;
        }
        if (position > 1) {
            int checkPos;
            for (checkPos = position - 1; checkPos > 0 && Character.isWhitespace(this.text.charAt(checkPos)); --checkPos) {
            }
            if (checkPos >= 0 && this.text.charAt(checkPos) == '<') {
                int beforeLt;
                for (beforeLt = checkPos - 1; beforeLt >= 0 && Character.isWhitespace(this.text.charAt(beforeLt)); --beforeLt) {
                }
                if (beforeLt >= 0) {
                    boolean hasSpaceBeforeLt;
                    char beforeChar = this.text.charAt(beforeLt);
                    boolean bl = hasSpaceBeforeLt = checkPos > 0 && Character.isWhitespace(this.text.charAt(checkPos - 1));
                    if (hasSpaceBeforeLt && (Character.isLetterOrDigit(beforeChar) || beforeChar == '_' || beforeChar == ')')) {
                        return false;
                    }
                    if (Character.isLetter(beforeChar) || beforeChar == '>') {
                        return true;
                    }
                }
            }
            if (checkPos >= 0 && this.text.charAt(checkPos) == ',') {
                return true;
            }
        }
        return false;
    }

    private boolean isInExcludedRange(int pos, List<int[]> ranges) {
        for (int[] range : ranges) {
            if (pos < range[0] || pos >= range[1]) continue;
            return true;
        }
        return false;
    }

    private void finalizeImports(List<Mark> marks) {
        if (this.importEntries.isEmpty()) {
            return;
        }
        for (ImportEntry ie : this.importEntries) {
            marks.add(new Mark(ie.importKwStart, ie.importKwEnd, TokenType.KEYWORD));
            String fullPath = ie.fullPath;
            int pathStart = ie.pathStart;
            int pathEnd = ie.pathEnd;
            String matchText = ie.matchText;
            if (!ie.isWildcard) {
                int starIndex;
                boolean hasPackage;
                ClassPathFinder.ResolveResult result = this.classPathFinder.resolve(fullPath);
                String orig = this.text.substring(pathStart, pathEnd);
                ArrayList<String> tokens = new ArrayList<String>();
                ArrayList<Integer> tokenStarts = new ArrayList<Integer>();
                ArrayList<Integer> tokenEnds = new ArrayList<Integer>();
                Matcher idm = Pattern.compile("[A-Za-z_][A-Za-z0-9_]*").matcher(orig);
                while (idm.find()) {
                    tokens.add(idm.group());
                    tokenStarts.add(idm.start());
                    tokenEnds.add(idm.end());
                }
                int pkgSegments = result.packageSegmentCount;
                boolean bl = hasPackage = pkgSegments > 0;
                if (result.found && result.invalidStartOffset < 0) {
                    if (hasPackage && pkgSegments <= tokenEnds.size()) {
                        int pkgEnd = pathStart + (Integer)tokenEnds.get(pkgSegments - 1);
                        marks.add(new Mark(pathStart, pkgEnd, TokenType.TYPE_DECL));
                    }
                    if (result.classSegments != null && !result.classSegments.isEmpty()) {
                        int classTokenStart = hasPackage ? pkgSegments : 0;
                        for (int si = 0; si < result.classSegments.size(); ++si) {
                            TokenType segType;
                            int tokIdx = classTokenStart + si;
                            if (tokIdx < 0 || tokIdx >= tokenStarts.size()) continue;
                            int s = pathStart + (Integer)tokenStarts.get(tokIdx);
                            int e = pathStart + (Integer)tokenEnds.get(tokIdx);
                            ClassPathFinder.ClassSegment seg = result.classSegments.get(si);
                            switch (seg.type) {
                                case INTERFACE: {
                                    segType = TokenType.INTERFACE_DECL;
                                    break;
                                }
                                case ENUM: {
                                    segType = TokenType.ENUM_DECL;
                                    break;
                                }
                                default: {
                                    segType = TokenType.IMPORTED_CLASS;
                                }
                            }
                            marks.add(new Mark(s, e, segType));
                        }
                    }
                } else if (result.classSegments != null && !result.classSegments.isEmpty()) {
                    int invalidAbs;
                    if (hasPackage && pkgSegments <= tokenEnds.size()) {
                        int pkgEnd = pathStart + (Integer)tokenEnds.get(pkgSegments - 1);
                        marks.add(new Mark(pathStart, pkgEnd, TokenType.TYPE_DECL));
                    }
                    int classTokenStart = hasPackage ? pkgSegments : 0;
                    for (int si = 0; si < result.classSegments.size(); ++si) {
                        TokenType segType;
                        int tokIdx = classTokenStart + si;
                        if (tokIdx < 0 || tokIdx >= tokenStarts.size()) continue;
                        int s = pathStart + (Integer)tokenStarts.get(tokIdx);
                        int e = pathStart + (Integer)tokenEnds.get(tokIdx);
                        ClassPathFinder.ClassSegment seg = result.classSegments.get(si);
                        switch (seg.type) {
                            case INTERFACE: {
                                segType = TokenType.INTERFACE_DECL;
                                break;
                            }
                            case ENUM: {
                                segType = TokenType.ENUM_DECL;
                                break;
                            }
                            default: {
                                segType = TokenType.IMPORTED_CLASS;
                            }
                        }
                        marks.add(new Mark(s, e, segType));
                    }
                    if (result.invalidStartOffset >= 0 && (invalidAbs = this.mapNormalizedOffsetToOriginal(orig, result.invalidStartOffset, pathStart)) < pathEnd) {
                        marks.add(new Mark(invalidAbs, pathEnd, TokenType.UNDEFINED_VAR));
                    }
                } else if (result.invalidStartOffset >= 0 && hasPackage) {
                    int invalidAbs;
                    if (pkgSegments <= tokenEnds.size()) {
                        int pkgEnd = pathStart + (Integer)tokenEnds.get(pkgSegments - 1);
                        marks.add(new Mark(pathStart, pkgEnd, TokenType.TYPE_DECL));
                    }
                    if ((invalidAbs = this.mapNormalizedOffsetToOriginal(orig, result.invalidStartOffset, pathStart)) < pathEnd) {
                        marks.add(new Mark(invalidAbs, pathEnd, TokenType.UNDEFINED_VAR));
                    }
                } else if (fullPath.endsWith(".") && hasPackage && result.invalidStartOffset < 0) {
                    marks.add(new Mark(pathStart, pathEnd, TokenType.TYPE_DECL));
                } else if (result.invalidStartOffset < 0 && hasPackage) {
                    marks.add(new Mark(pathStart, pathEnd, TokenType.TYPE_DECL));
                } else {
                    marks.add(new Mark(pathStart, pathEnd, TokenType.UNDEFINED_VAR));
                }
                if ((starIndex = matchText.indexOf(42)) == -1) continue;
                int absStar = ie.importKwStart + (starIndex - 0);
                marks.add(new Mark(absStar, absStar + 1, TokenType.DEFAULT));
                continue;
            }
            boolean marked = false;
            if (this.classPathFinder.isValidPackage(fullPath)) {
                marks.add(new Mark(pathStart, pathEnd, TokenType.TYPE_DECL));
                marked = true;
            } else {
                HashSet<String> uppercaseIds = new HashSet<String>();
                Matcher idm = Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\b").matcher(this.text);
                while (idm.find()) {
                    uppercaseIds.add(idm.group(1));
                }
                for (String ident : uppercaseIds) {
                    String candInner = fullPath + "$" + ident;
                    ClassPathFinder.ClassInfo infoInner = this.classPathFinder.tryResolveClassName(candInner);
                    if (infoInner != null) {
                        ClassPathFinder.ClassInfo outerInfo = this.classPathFinder.tryResolveClassName(fullPath);
                        String orig = this.text.substring(pathStart, pathEnd);
                        ArrayList<String> tokens = new ArrayList<String>();
                        ArrayList<Integer> tokenStarts = new ArrayList<Integer>();
                        ArrayList<Integer> tokenEnds = new ArrayList<Integer>();
                        Matcher tokm = Pattern.compile("[A-Za-z_][A-Za-z0-9_]*").matcher(orig);
                        while (tokm.find()) {
                            tokens.add(tokm.group());
                            tokenStarts.add(tokm.start());
                            tokenEnds.add(tokm.end());
                        }
                        int tokenCount = tokenStarts.size();
                        if (tokenCount > 0) {
                            int pkgEnd = pathStart + (Integer)tokenEnds.get(Math.max(0, tokenCount - 2));
                            if (tokenCount >= 2) {
                                marks.add(new Mark(pathStart, pathStart + (Integer)tokenEnds.get(tokenCount - 2), TokenType.TYPE_DECL));
                            }
                            int s = pathStart + (Integer)tokenStarts.get(tokenCount - 1);
                            int e = pathStart + (Integer)tokenEnds.get(tokenCount - 1);
                            TokenType segType = TokenType.IMPORTED_CLASS;
                            if (outerInfo != null) {
                                switch (outerInfo.type) {
                                    case INTERFACE: {
                                        segType = TokenType.INTERFACE_DECL;
                                        break;
                                    }
                                    case ENUM: {
                                        segType = TokenType.ENUM_DECL;
                                        break;
                                    }
                                    default: {
                                        segType = TokenType.IMPORTED_CLASS;
                                    }
                                }
                            }
                            marks.add(new Mark(s, e, segType));
                        }
                        marked = true;
                        this.importedPackages.add(fullPath);
                        break;
                    }
                    String candDot = fullPath + "." + ident;
                    if (this.classPathFinder.tryResolveClassName(candDot) == null) continue;
                    marks.add(new Mark(pathStart, pathEnd, TokenType.TYPE_DECL));
                    marked = true;
                    this.importedPackages.add(fullPath);
                    break;
                }
            }
            if (!marked) {
                marks.add(new Mark(pathStart, pathEnd, TokenType.UNDEFINED_VAR));
            }
            if (ie.starPos < 0) continue;
            marks.add(new Mark(ie.starPos, ie.starPos + 1, TokenType.DEFAULT));
        }
    }

    private void collectImportedClassUsages(List<Mark> marks) {
        List<int[]> excluded = MethodBlock.getExcludedRanges(this.text);
        Pattern classUsage = Pattern.compile("\\b([A-Z][a-zA-Z0-9_]*)\\s*\\.");
        Matcher m = classUsage.matcher(this.text);
        while (m.find()) {
            boolean isShadowed;
            boolean isImported;
            String className = m.group(1);
            int start = m.start(1);
            int end = m.end(1);
            if (this.isInExcludedRange(start, excluded)) continue;
            if (this.unresolvedClasses.contains(className)) {
                marks.add(new Mark(start, end, TokenType.UNDEFINED_VAR));
                continue;
            }
            boolean bl = isImported = this.importedClasses.containsKey(className) || ClassPathFinder.JAVA_LANG_CLASSES.contains(className);
            if (!isImported && !this.importedPackages.isEmpty()) {
                isImported = true;
            }
            MethodBlock block = this.findMethodBlockAtPosition(start);
            boolean bl2 = isShadowed = this.globalFields.contains(className) || this.localFields.contains(className);
            if (block != null) {
                boolean bl3 = isShadowed = isShadowed || block.parameters.contains(className) || block.isLocalDeclaredAtPosition(className, start);
            }
            if (isImported && !isShadowed) {
                ClassPathFinder.ClassInfo info = this.classPathFinder.resolveSimpleName(className, this.importedClasses, this.importedPackages);
                TokenType tokenType = TokenType.IMPORTED_CLASS;
                if (info != null) {
                    switch (info.type) {
                        case INTERFACE: {
                            tokenType = TokenType.INTERFACE_DECL;
                            break;
                        }
                        case ENUM: {
                            tokenType = TokenType.ENUM_DECL;
                        }
                    }
                }
                marks.add(new Mark(start, end, tokenType));
                continue;
            }
            if (isImported || isShadowed) continue;
            marks.add(new Mark(start, end, TokenType.UNDEFINED_VAR));
        }
    }

    private void collectClassDeclarations(List<Mark> marks) {
        Matcher m = CLASS_DECL.matcher(this.text);
        List<int[]> excluded = MethodBlock.getExcludedRanges(this.text);
        while (m.find()) {
            marks.add(new Mark(m.start(1), m.end(1), TokenType.KEYWORD));
            int nameStart = m.start(2);
            int nameEnd = m.end(2);
            if (this.isInExcludedRange(nameStart, excluded)) continue;
            String kind = m.group(1);
            if ("interface".equals(kind)) {
                marks.add(new Mark(nameStart, nameEnd, TokenType.INTERFACE_DECL));
                continue;
            }
            if ("enum".equals(kind)) {
                marks.add(new Mark(nameStart, nameEnd, TokenType.ENUM_DECL));
                continue;
            }
            marks.add(new Mark(nameStart, nameEnd, TokenType.CLASS_DECL));
        }
    }

    @Override
    public void formatCodeText() {
        List<Mark> marks = new ArrayList<Mark>();
        this.collectImports();
        this.collectFields();
        this.collectPatternMatches(marks, COMMENT, TokenType.COMMENT);
        this.collectPatternMatches(marks, STRING, TokenType.STRING);
        this.collectClassDeclarations(marks);
        this.collectPatternMatches(marks, KEYWORD, TokenType.KEYWORD);
        this.collectPatternMatches(marks, MODIFIER, TokenType.KEYWORD);
        this.collectTypeDeclarations(marks);
        this.collectPatternMatches(marks, NEW_TYPE, TokenType.NEW_TYPE, 1);
        this.collectMethodDeclarations(marks);
        this.collectPatternMatches(marks, METHOD_CALL, TokenType.METHOD_CALL, 1);
        this.collectPatternMatches(marks, NUMBER, TokenType.NUMBER);
        this.highlightVariableReferences(marks);
        this.collectImportedClassUsages(marks);
        this.finalizeImports(marks);
        marks = this.resolveConflicts(marks);
        this.computeIndentGuides(marks);
        for (LineData line : this.lines) {
            line.tokens.clear();
        }
        for (LineData line : this.lines) {
            int cursor = line.start;
            for (Mark mark : marks) {
                if (mark.end <= line.start || mark.start >= line.end) continue;
                int tokenStart = Math.max(mark.start, line.start);
                int tokenEnd = Math.min(mark.end, line.end);
                tokenStart = Math.max(0, Math.min(tokenStart, this.text.length()));
                tokenEnd = Math.max(0, Math.min(tokenEnd, this.text.length()));
                if (cursor < tokenStart) {
                    int end = Math.min(tokenStart, this.text.length());
                    line.tokens.add(new Token(this.text.substring(cursor, end), TokenType.DEFAULT, cursor, end));
                }
                if (tokenStart < tokenEnd) {
                    line.tokens.add(new Token(this.text.substring(tokenStart, Math.min(tokenEnd, this.text.length())), mark.type, tokenStart, Math.min(tokenEnd, this.text.length())));
                }
                cursor = tokenEnd;
            }
            if (cursor >= line.end) continue;
            int end = Math.min(line.end, this.text.length());
            line.tokens.add(new Token(this.text.substring(cursor, end), TokenType.DEFAULT, cursor, end));
        }
    }

    private void collectMethodDeclarations(List<Mark> marks) {
        Matcher m = METHOD_DECL.matcher(this.text);
        while (m.find()) {
            marks.add(new Mark(m.start(2), m.end(2), TokenType.METHOD_DECARE));
            marks.add(new Mark(m.start(1), m.end(1), TokenType.TYPE_DECL));
        }
    }

    private void collectTypeDeclarations(List<Mark> marks) {
        List<int[]> excluded = MethodBlock.getExcludedRanges(this.text);
        Pattern typeStart = Pattern.compile("(?:(?:public|private|protected|static|final|transient|volatile)\\s+)*([A-Z][a-zA-Z0-9_]*)\\s*");
        for (LineData ld : this.lines) {
            int lineStart = ld.start;
            int lineEnd = ld.end;
            if (lineStart >= lineEnd) continue;
            String s = ld.text;
            Matcher m = typeStart.matcher(s);
            int searchFrom = 0;
            while (m.find(searchFrom)) {
                int posAfterType;
                int typeNameStart = lineStart + m.start(1);
                int typeNameEnd = lineStart + m.end(1);
                boolean skip = false;
                for (int[] r : excluded) {
                    if (typeNameStart >= r[1] || typeNameEnd <= r[0]) continue;
                    skip = true;
                    break;
                }
                if (skip) {
                    searchFrom = m.end();
                    continue;
                }
                String typeName = m.group(1);
                for (posAfterType = m.end(1); posAfterType < s.length() && Character.isWhitespace(s.charAt(posAfterType)); ++posAfterType) {
                }
                String genericContent = null;
                int genericStart = -1;
                int genericEnd = -1;
                if (posAfterType < s.length() && s.charAt(posAfterType) == '<') {
                    int i;
                    genericStart = posAfterType;
                    int depth = 1;
                    for (i = posAfterType + 1; i < s.length() && depth > 0; ++i) {
                        char c = s.charAt(i);
                        if (c == '<') {
                            ++depth;
                            continue;
                        }
                        if (c != '>') continue;
                        --depth;
                    }
                    if (depth == 0) {
                        genericEnd = i;
                        genericContent = s.substring(genericStart + 1, genericEnd - 1);
                        posAfterType = genericEnd;
                    }
                }
                while (posAfterType < s.length() && Character.isWhitespace(s.charAt(posAfterType))) {
                    ++posAfterType;
                }
                boolean hasGeneric = genericContent != null && !genericContent.isEmpty();
                boolean atEndOfLine = posAfterType >= s.length();
                boolean followedByVarName = false;
                if (!atEndOfLine) {
                    char nextChar = s.charAt(posAfterType);
                    boolean bl = followedByVarName = Character.isLetter(nextChar) || nextChar == '_';
                }
                if (hasGeneric || followedByVarName) {
                    ClassPathFinder.ClassInfo info = this.classPathFinder.resolveSimpleName(typeName, this.importedClasses, this.importedPackages);
                    TokenType tokenType = TokenType.UNDEFINED_VAR;
                    if (info != null) {
                        switch (info.type) {
                            case INTERFACE: {
                                tokenType = TokenType.INTERFACE_DECL;
                                break;
                            }
                            case ENUM: {
                                tokenType = TokenType.ENUM_DECL;
                                break;
                            }
                            default: {
                                tokenType = TokenType.TYPE_DECL;
                            }
                        }
                    }
                    marks.add(new Mark(typeNameStart, typeNameEnd, tokenType));
                    if (hasGeneric && genericStart >= 0) {
                        int absGenericStart = lineStart + genericStart;
                        int absGenericEnd = lineStart + genericEnd;
                        marks.add(new Mark(absGenericStart, absGenericStart + 1, TokenType.DEFAULT));
                        marks.add(new Mark(absGenericEnd - 1, absGenericEnd, TokenType.DEFAULT));
                        int contentStart = lineStart + genericStart + 1;
                        this.highlightGenericTypes(genericContent, contentStart, marks, excluded);
                    }
                    if (followedByVarName) {
                        int v;
                        for (v = posAfterType; v < s.length() && Character.isWhitespace(s.charAt(v)); ++v) {
                        }
                        int varStart = v;
                        while (v < s.length() && (Character.isLetterOrDigit(s.charAt(v)) || s.charAt(v) == '_')) {
                            ++v;
                        }
                        int varEnd = v;
                        if (varEnd > varStart) {
                            MethodBlock mb;
                            String varName = s.substring(varStart, varEnd);
                            int absVarStart = lineStart + varStart;
                            int absVarEnd = lineStart + varEnd;
                            if (Character.isUpperCase(varName.charAt(0)) && (mb = this.findMethodBlockAtPosition(absVarStart)) != null) {
                                if (!mb.localVariables.contains(varName)) {
                                    mb.localVariables.add(varName);
                                }
                                if (!this.localFields.contains(varName)) {
                                    this.localFields.add(varName);
                                }
                                marks.add(new Mark(absVarStart, absVarEnd, TokenType.LOCAL_FIELD));
                            }
                        }
                    }
                }
                searchFrom = m.end();
            }
        }
    }

    private void collectPatternMatches(List<Mark> marks, Pattern pattern, TokenType type, int group) {
        Matcher m = pattern.matcher(this.text);
        while (m.find()) {
            marks.add(new Mark(m.start(group), m.end(group), type));
        }
    }

    private void collectPatternMatches(List<Mark> marks, Pattern pattern, TokenType type) {
        this.collectPatternMatches(marks, pattern, type, 0);
    }

    private List<Mark> resolveConflicts(List<Mark> marks) {
        marks.sort((a, b) -> {
            if (a.start != b.start) {
                return Integer.compare(a.start, b.start);
            }
            return Integer.compare(b.type.priority, a.type.priority);
        });
        ArrayList<Mark> result = new ArrayList<Mark>();
        for (Mark m : marks) {
            boolean skip = false;
            for (int i = result.size() - 1; i >= 0; --i) {
                Mark r = (Mark)result.get(i);
                if (m.start >= r.end || m.end <= r.start) continue;
                if (r.type.priority < m.type.priority) {
                    result.remove(i);
                    continue;
                }
                skip = true;
                break;
            }
            if (skip) continue;
            result.add(m);
        }
        result.sort((a, b) -> Integer.compare(a.start, b.start));
        return result;
    }

    private void computeIndentGuides(List<Mark> marks) {
        for (LineData lineData : this.lines) {
            lineData.indentCols.clear();
        }
        ArrayList<int[]> ignored = new ArrayList<int[]>();
        for (Mark m : marks) {
            if (m.type != TokenType.STRING && m.type != TokenType.COMMENT) continue;
            ignored.add(new int[]{m.start, m.end});
        }
        Predicate<Integer> predicate = pos -> {
            for (int[] r : ignored) {
                if (pos < r[0] || pos >= r[1]) continue;
                return true;
            }
            return false;
        };
        class OpenBrace {
            int line;
            int col;

            OpenBrace(int l, int c) {
                this.line = l;
                this.col = c;
            }
        }
        ArrayDeque<OpenBrace> stack = new ArrayDeque<OpenBrace>();
        int tabSize = 4;
        for (int li = 0; li < this.lines.size(); ++li) {
            LineData ld = this.lines.get(li);
            String s = ld.text;
            for (int i = 0; i < s.length(); ++i) {
                int absPos = ld.start + i;
                if (predicate.test(absPos)) continue;
                char c = s.charAt(i);
                if (c == '{') {
                    int leading = 0;
                    for (int k = 0; k < i; ++k) {
                        char ch = s.charAt(k);
                        if (ch == '\t') {
                            leading += 4;
                            continue;
                        }
                        ++leading;
                    }
                    stack.push(new OpenBrace(li, leading));
                    continue;
                }
                if (c != '}' || stack.isEmpty()) continue;
                OpenBrace open = (OpenBrace)stack.pop();
                int startLine = open.line;
                int col = open.col;
                if (startLine == li) continue;
                int from = Math.max(0, startLine + 1);
                int to = Math.min(this.lines.size() - 1, li);
                for (int l = from; l <= to; ++l) {
                    List<Integer> list = this.lines.get((int)l).indentCols;
                    if (list.contains(col)) continue;
                    list.add(col);
                }
            }
        }
    }

    public static enum TokenType {
        COMMENT('7', 130),
        STRING('5', 120),
        KEYWORD('c', 100),
        NEW_TYPE('d', 80),
        IMPORTED_CLASS('3', 75),
        INTERFACE_DECL('b', 85),
        ENUM_DECL('d', 85),
        CLASS_DECL('3', 85),
        TYPE_DECL('3', 70),
        METHOD_DECARE('2', 60),
        METHOD_CALL('a', 50),
        NUMBER('7', 40),
        VARIABLE('f', 30),
        GLOBAL_FIELD('b', 35),
        LOCAL_FIELD('e', 25),
        PARAMETER('9', 36),
        UNDEFINED_VAR('4', 105),
        DEFAULT('f', 0);

        public final char color;
        public final int priority;

        private TokenType(char color, int priority) {
            this.color = color;
            this.priority = priority;
        }
    }

    private static class Mark {
        public int start;
        public int end;
        public TokenType type;

        public Mark(int start, int end, TokenType type) {
            this.start = start;
            this.end = end;
            this.type = type;
        }

        public String toString() {
            return "Mark{" + (Object)((Object)this.type) + ", (start=" + this.start + ", end=" + this.end + ")" + '}';
        }
    }

    public static class Token {
        public String text;
        public TokenType type;
        public int start;
        public int end;

        public Token(String text, TokenType type, int start, int end) {
            this.text = text;
            this.type = type;
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return "Token{'" + this.text + '\'' + ", " + (Object)((Object)this.type) + ", (color=" + this.type.color + ", priority=" + this.type.priority + ")" + '}';
        }
    }

    public static class LineData {
        public String text;
        public int start;
        public int end;
        public List<Token> tokens = new ArrayList<Token>();
        public List<Integer> indentCols = new ArrayList<Integer>();

        public LineData(String text, int startIndex, int end) {
            this.text = text;
            this.start = startIndex;
            this.end = end;
        }

        public void drawString(int x, int y, int color) {
            StringBuilder builder = new StringBuilder();
            int lastIndex = 0;
            for (Token t : this.tokens) {
                int tokenStart = t.start - this.start;
                if (tokenStart > lastIndex) {
                    builder.append(this.text, lastIndex, tokenStart);
                }
                builder.append('\u00a7').append(t.type.color).append(t.text).append('\u00a7').append('f');
                lastIndex = tokenStart + t.text.length();
            }
            if (lastIndex < this.text.length()) {
                builder.append(this.text.substring(lastIndex));
            }
            ClientProxy.Font.drawString(builder.toString(), x, y, color);
        }
    }

    private static class ImportEntry {
        public final String fullPath;
        public final String matchText;
        public final int pathStart;
        public final int pathEnd;
        public final int importKwStart;
        public final int importKwEnd;
        public final int starPos;
        public final boolean isWildcard;
        public ClassPathFinder.ResolveResult resolveResult;

        public ImportEntry(String fullPath, String matchText, int pathStart, int pathEnd, int importKwStart, int importKwEnd, int starPos, boolean isWildcard) {
            this.fullPath = fullPath;
            this.matchText = matchText;
            this.pathStart = pathStart;
            this.pathEnd = pathEnd;
            this.importKwStart = importKwStart;
            this.importKwEnd = importKwEnd;
            this.starPos = starPos;
            this.isWildcard = isWildcard;
        }
    }
}

