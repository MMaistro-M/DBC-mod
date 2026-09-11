/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.ArrayList;
import java.util.List;

public class FormatHelper {
    private static final String[] MULTI_CHAR_OPERATORS = new String[]{">>>=", "===", "!==", "<<=", ">>=", ">>>", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^=", "&&", "||", "==", "!=", "<=", ">=", "<<", ">>", "++", "--", "->", "=>"};
    private static final String SINGLE_CHAR_OPERATORS = "=+-*/%&|^!~<>?";
    private static final String[] CONTROL_KEYWORDS = new String[]{"if", "while", "for", "switch", "catch", "else", "return", "new", "throw", "typeof", "instanceof", "in", "of", "var", "let", "const", "function", "class", "try", "finally", "do", "case", "default", "break", "continue", "void", "delete", "yield", "await", "async", "extends", "implements", "import", "export", "package", "interface", "enum", "abstract", "static", "final", "synchronized", "volatile", "transient", "native", "this", "super", "null", "true", "false", "undefined", "public", "private", "protected"};
    private static final String[] PAREN_KEYWORDS = new String[]{"if", "while", "for", "switch", "catch"};
    private FormatSettings settings;
    private static final String[] DECLARATION_BREAK_KEYWORDS = new String[]{"extends", "implements", "throws", "permits"};

    public FormatHelper() {
        this.settings = FormatSettings.defaults();
    }

    public FormatHelper(FormatSettings settings) {
        this.settings = settings != null ? settings : FormatSettings.defaults();
    }

    public void setSettings(FormatSettings settings) {
        this.settings = settings;
    }

    public FormatSettings getSettings() {
        return this.settings;
    }

    public String format(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String[] lines = text.split("\n", -1);
        StringBuilder result = new StringBuilder();
        ArrayList<String> lambdaIndentStack = new ArrayList<String>();
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            if (!lambdaIndentStack.isEmpty()) {
                line = this.fixLambdaBlockIndent(line, lambdaIndentStack);
            }
            line = this.formatLine(line);
            if (this.settings.trimTrailingWhitespace) {
                line = this.trimTrailing(line);
            }
            this.updateLambdaBlockStack(line, lambdaIndentStack);
            result.append(line);
            if (i >= lines.length - 1) continue;
            result.append("\n");
        }
        return result.toString();
    }

    private void updateLambdaBlockStack(String line, List<String> stack) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return;
        }
        String indent = this.extractIndent(line);
        String contentNoStrings = this.stripStringLiterals(trimmed);
        int opens = 0;
        int closes = 0;
        for (int i = 0; i < contentNoStrings.length(); ++i) {
            char c = contentNoStrings.charAt(i);
            if (c == '{') {
                ++opens;
                continue;
            }
            if (c != '}') continue;
            ++closes;
        }
        boolean hasArrowBlock = this.endsWithArrowBlock(contentNoStrings);
        int netCloses = closes - opens;
        if (netCloses > 0) {
            for (int j = 0; j < netCloses && !stack.isEmpty(); ++j) {
                stack.remove(stack.size() - 1);
            }
        }
        if (hasArrowBlock) {
            stack.add(indent);
        } else if (opens > closes) {
            int netOpens = opens - closes;
            if (!stack.isEmpty()) {
                for (int j = 0; j < netOpens; ++j) {
                    stack.add(indent);
                }
            }
        }
    }

    private String fixLambdaBlockIndent(String line, List<String> stack) {
        String requiredIndent;
        if (stack.isEmpty()) {
            return line;
        }
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return line;
        }
        String currentIndent = this.extractIndent(line);
        String parentIndent = stack.get(stack.size() - 1);
        String bodyIndent = parentIndent + "    ";
        boolean isClosingBrace = trimmed.startsWith("}");
        String string = requiredIndent = isClosingBrace ? parentIndent : bodyIndent;
        if (this.getVisualLength(currentIndent) < this.getVisualLength(requiredIndent)) {
            return requiredIndent + trimmed;
        }
        return line;
    }

    private boolean endsWithArrowBlock(String contentNoStrings) {
        int searchEnd;
        int lastOpenBrace = contentNoStrings.lastIndexOf(123);
        if (lastOpenBrace < 0) {
            return false;
        }
        int matchingClose = contentNoStrings.indexOf(125, lastOpenBrace);
        if (matchingClose >= 0) {
            return false;
        }
        for (searchEnd = lastOpenBrace; searchEnd > 0 && contentNoStrings.charAt(searchEnd - 1) == ' '; --searchEnd) {
        }
        if (searchEnd >= 2) {
            String beforeBrace = contentNoStrings.substring(searchEnd - 2, searchEnd);
            return beforeBrace.equals("->") || beforeBrace.equals("=>");
        }
        return false;
    }

    private String stripStringLiterals(String content) {
        StringBuilder sb = new StringBuilder(content.length());
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = 0; i < content.length(); ++i) {
            char c = content.charAt(i);
            if (!inString) {
                if (c == '\"' || c == '\'') {
                    inString = true;
                    stringChar = c;
                    sb.append(' ');
                    continue;
                }
                sb.append(c);
                continue;
            }
            sb.append(' ');
            if (c == '\\' && i + 1 < content.length()) {
                ++i;
                sb.append(' ');
                continue;
            }
            if (c != stringChar) continue;
            inString = false;
        }
        return sb.toString();
    }

    private String splitStatementsOntoSeparateLines(String text) {
        String[] lines = text.split("\n", -1);
        StringBuilder result = new StringBuilder();
        for (int lineIdx = 0; lineIdx < lines.length; ++lineIdx) {
            int indent;
            String line = lines[lineIdx];
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("//") || trimmed.startsWith("/*") || trimmed.startsWith("*")) {
                result.append(line);
                if (lineIdx >= lines.length - 1) continue;
                result.append("\n");
                continue;
            }
            for (indent = 0; indent < line.length() && (line.charAt(indent) == ' ' || line.charAt(indent) == '\t'); ++indent) {
            }
            String indentation = line.substring(0, indent);
            String content = line.substring(indent);
            List<String> statements = this.splitBySemicolons(content);
            for (int si = 0; si < statements.size(); ++si) {
                String afterBrace;
                String stmt = statements.get(si).trim();
                if (stmt.isEmpty()) continue;
                int braceIdx = stmt.indexOf(123);
                if (braceIdx >= 0 && braceIdx < stmt.length() - 1 && !(afterBrace = stmt.substring(braceIdx + 1).trim()).isEmpty() && !afterBrace.equals("}")) {
                    result.append(indentation).append(stmt, 0, braceIdx + 1).append("\n");
                    String newIndent = indentation + "    ";
                    result.append(newIndent).append(afterBrace);
                    if (si >= statements.size() - 1) continue;
                    result.append("\n");
                    continue;
                }
                result.append(indentation).append(stmt);
                if (si >= statements.size() - 1) continue;
                result.append("\n");
            }
            if (lineIdx >= lines.length - 1) continue;
            result.append("\n");
        }
        return result.toString();
    }

    private List<String> splitBySemicolons(String content) {
        ArrayList<String> statements = new ArrayList<String>();
        StringBuilder current = new StringBuilder();
        int parenDepth = 0;
        boolean inString = false;
        boolean inChar = false;
        boolean escaped = false;
        int forLoopDepth = 0;
        for (int i = 0; i < content.length(); ++i) {
            char c = content.charAt(i);
            if (c == '\"' && !escaped && !inChar) {
                inString = !inString;
            } else if (c == '\'' && !escaped && !inString) {
                inChar = !inChar;
            }
            boolean bl = escaped = c == '\\' && !escaped;
            if (inString || inChar) {
                current.append(c);
                continue;
            }
            if (c == '(' && i >= 3 && content.substring(Math.max(0, i - 3), i + 1).matches(".*\\bfor\\s*\\(")) {
                ++forLoopDepth;
                ++parenDepth;
            } else if (c == '(') {
                ++parenDepth;
            } else if (c == ')' && forLoopDepth > 0 && --parenDepth < forLoopDepth) {
                forLoopDepth = 0;
            }
            if (c == ';' && forLoopDepth == 0) {
                current.append(c);
                statements.add(current.toString());
                current = new StringBuilder();
                continue;
            }
            current.append(c);
        }
        if (current.length() > 0) {
            statements.add(current.toString());
        }
        return statements;
    }

    private String formatLine(String line) {
        int indent;
        if (line.trim().isEmpty()) {
            return line;
        }
        for (indent = 0; indent < line.length() && (line.charAt(indent) == ' ' || line.charAt(indent) == '\t'); ++indent) {
        }
        String indentation = line.substring(0, indent);
        String content = line.substring(indent);
        if (content.isEmpty()) {
            return line;
        }
        String trimmed = content.trim();
        if (trimmed.startsWith("//") || trimmed.startsWith("*") || trimmed.startsWith("/*")) {
            return line;
        }
        if (this.isContinuationLine(trimmed)) {
            return line;
        }
        String formatted = this.formatContent(content);
        return indentation + formatted;
    }

    private boolean isContinuationLine(String trimmedContent) {
        if (trimmedContent.isEmpty()) {
            return false;
        }
        char firstChar = trimmedContent.charAt(0);
        if (firstChar == '.' || firstChar == '+' || firstChar == '-' || firstChar == '*' || firstChar == '/' || firstChar == '%' || firstChar == '&' || firstChar == '|' || firstChar == '^' || firstChar == '?' || firstChar == ':' || firstChar == ',') {
            return true;
        }
        return trimmedContent.startsWith("&&") || trimmedContent.startsWith("||");
    }

    private String formatContent(String content) {
        List<int[]> preserveRegions = this.findPreserveRegions(content);
        if (preserveRegions.isEmpty()) {
            return this.formatCodeSegment(content);
        }
        StringBuilder result = new StringBuilder();
        int lastEnd = 0;
        for (int[] region : preserveRegions) {
            String preserved;
            if (region[0] > lastEnd) {
                String codeSegment = content.substring(lastEnd, region[0]);
                String formatted = this.formatCodeSegment(codeSegment);
                result.append(formatted);
                if (!formatted.isEmpty() && !formatted.endsWith(" ")) {
                    char lastChar = formatted.charAt(formatted.length() - 1);
                    boolean operatorEnd = SINGLE_CHAR_OPERATORS.indexOf(lastChar) >= 0;
                    boolean identifierEnd = Character.isJavaIdentifierPart(lastChar);
                    if (operatorEnd || identifierEnd) {
                        result.append(' ');
                    }
                }
            }
            if ((preserved = content.substring(region[0], region[1])).startsWith("//") && result.length() > 0 && result.charAt(result.length() - 1) != ' ') {
                result.append(' ');
            }
            if (preserved.startsWith("//") && preserved.length() > 2 && preserved.charAt(2) != ' ') {
                preserved = "// " + preserved.substring(2);
            }
            result.append(preserved);
            lastEnd = region[1];
        }
        if (lastEnd < content.length()) {
            String codeSegment = content.substring(lastEnd);
            String formatted = this.formatCodeSegment(codeSegment);
            if (result.length() > 1 && !formatted.isEmpty()) {
                String tail = result.substring(result.length() - 2);
                char nextChar = formatted.charAt(0);
                if (tail.equals("*/") && nextChar != ';' && nextChar != ',' && nextChar != ')') {
                    result.append(' ');
                }
            }
            result.append(formatted);
        }
        return result.toString();
    }

    private List<int[]> findPreserveRegions(String content) {
        ArrayList<int[]> regions = new ArrayList<int[]>();
        int i = 0;
        while (i < content.length()) {
            int end;
            char c = content.charAt(i);
            if (c == '\"') {
                end = this.findStringEnd(content, i, '\"');
                regions.add(new int[]{i, end});
                i = end;
                continue;
            }
            if (c == '\'') {
                end = this.findStringEnd(content, i, '\'');
                regions.add(new int[]{i, end});
                i = end;
                continue;
            }
            if (c == '/' && i + 1 < content.length() && content.charAt(i + 1) == '/') {
                regions.add(new int[]{i, content.length()});
                break;
            }
            if (c == '/' && i + 1 < content.length() && content.charAt(i + 1) == '*') {
                end = content.indexOf("*/", i + 2);
                if (end >= 0) {
                    regions.add(new int[]{i, end + 2});
                    i = end + 2;
                    continue;
                }
                regions.add(new int[]{i, content.length()});
                break;
            }
            ++i;
        }
        return regions;
    }

    private int findStringEnd(String content, int start, char quote) {
        int i = start + 1;
        while (i < content.length()) {
            char c = content.charAt(i);
            if (c == '\\' && i + 1 < content.length()) {
                i += 2;
                continue;
            }
            if (c == quote) {
                return i + 1;
            }
            ++i;
        }
        return content.length();
    }

    private String formatCodeSegment(String code) {
        if (code.isEmpty()) {
            return code;
        }
        List<Token> tokens = this.tokenize(code);
        if (this.settings.normalizeWhitespace) {
            tokens = this.mergeSplitOperators(tokens);
        }
        tokens = this.splitGenericShiftOperators(tokens);
        List<Token> formatted = this.formatTokens(tokens);
        return this.reconstruct(formatted);
    }

    private List<Token> tokenize(String code) {
        ArrayList<Token> tokens = new ArrayList<Token>();
        int i = 0;
        int len = code.length();
        while (i < len) {
            char c = code.charAt(i);
            if (c == ' ' || c == '\t') {
                int start = i;
                while (i < len && (code.charAt(i) == ' ' || code.charAt(i) == '\t')) {
                    ++i;
                }
                String ws = this.settings.normalizeWhitespace ? " " : code.substring(start, i);
                tokens.add(new Token(TokenType.WHITESPACE, ws, start));
                continue;
            }
            String multiOp = this.matchMultiCharOperator(code, i);
            if (multiOp != null) {
                tokens.add(new Token(TokenType.OPERATOR, multiOp, i));
                i += multiOp.length();
                continue;
            }
            if (c == '(') {
                tokens.add(new Token(TokenType.PAREN_OPEN, "(", i));
                ++i;
                continue;
            }
            if (c == ')') {
                tokens.add(new Token(TokenType.PAREN_CLOSE, ")", i));
                ++i;
                continue;
            }
            if (c == '[') {
                tokens.add(new Token(TokenType.BRACKET_OPEN, "[", i));
                ++i;
                continue;
            }
            if (c == ']') {
                tokens.add(new Token(TokenType.BRACKET_CLOSE, "]", i));
                ++i;
                continue;
            }
            if (c == '{') {
                tokens.add(new Token(TokenType.BRACE_OPEN, "{", i));
                ++i;
                continue;
            }
            if (c == '}') {
                tokens.add(new Token(TokenType.BRACE_CLOSE, "}", i));
                ++i;
                continue;
            }
            if (c == ',') {
                tokens.add(new Token(TokenType.COMMA, ",", i));
                ++i;
                continue;
            }
            if (c == ';') {
                tokens.add(new Token(TokenType.SEMICOLON, ";", i));
                ++i;
                continue;
            }
            if (c == ':' && i + 1 < len && code.charAt(i + 1) == ':') {
                tokens.add(new Token(TokenType.OPERATOR, "::", i));
                i += 2;
                continue;
            }
            if (c == ':') {
                tokens.add(new Token(TokenType.COLON, ":", i));
                ++i;
                continue;
            }
            if (c == '.') {
                tokens.add(new Token(TokenType.DOT, ".", i));
                ++i;
                continue;
            }
            if (SINGLE_CHAR_OPERATORS.indexOf(c) >= 0) {
                tokens.add(new Token(TokenType.OPERATOR, String.valueOf(c), i));
                ++i;
                continue;
            }
            if (Character.isDigit(c) || c == '.' && i + 1 < len && Character.isDigit(code.charAt(i + 1))) {
                char next;
                int start = i;
                if (c == '0' && i + 1 < len && ((next = code.charAt(i + 1)) == 'x' || next == 'X' || next == 'b' || next == 'B' || next == 'o' || next == 'O')) {
                    i += 2;
                    while (i < len && this.isHexDigit(code.charAt(i))) {
                        ++i;
                    }
                    tokens.add(new Token(TokenType.NUMBER, code.substring(start, i), start));
                    continue;
                }
                while (i < len && (Character.isDigit(code.charAt(i)) || code.charAt(i) == '.')) {
                    ++i;
                }
                if (i < len && "LlFfDd".indexOf(code.charAt(i)) >= 0) {
                    ++i;
                }
                tokens.add(new Token(TokenType.NUMBER, code.substring(start, i), start));
                continue;
            }
            if (c == '@') {
                int start = i++;
                while (i < len && (code.charAt(i) == ' ' || code.charAt(i) == '\t')) {
                    ++i;
                }
                if (i < len && Character.isJavaIdentifierStart(code.charAt(i))) {
                    while (i < len && Character.isJavaIdentifierPart(code.charAt(i))) {
                        ++i;
                    }
                    String annotation = "@" + code.substring(start + 1, i).trim();
                    tokens.add(new Token(TokenType.IDENTIFIER, annotation, start));
                    continue;
                }
                tokens.add(new Token(TokenType.IDENTIFIER, "@", start));
                continue;
            }
            if (Character.isJavaIdentifierStart(c)) {
                int start = i;
                while (i < len && Character.isJavaIdentifierPart(code.charAt(i))) {
                    ++i;
                }
                String word = code.substring(start, i);
                if (this.isKeyword(word)) {
                    tokens.add(new Token(TokenType.KEYWORD, word, start));
                    continue;
                }
                tokens.add(new Token(TokenType.IDENTIFIER, word, start));
                continue;
            }
            tokens.add(new Token(TokenType.IDENTIFIER, String.valueOf(c), i));
            ++i;
        }
        return tokens;
    }

    private String matchMultiCharOperator(String code, int pos) {
        for (String op : MULTI_CHAR_OPERATORS) {
            if (pos + op.length() > code.length() || !code.substring(pos, pos + op.length()).equals(op)) continue;
            return op;
        }
        return null;
    }

    private boolean isHexDigit(char c) {
        return c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F' || c == '_';
    }

    private boolean isKeyword(String word) {
        for (String kw : CONTROL_KEYWORDS) {
            if (!kw.equals(word)) continue;
            return true;
        }
        return false;
    }

    private List<Token> splitGenericShiftOperators(List<Token> tokens) {
        int genericDepth = 0;
        ArrayList<Token> result = new ArrayList<Token>();
        for (int i = 0; i < tokens.size(); ++i) {
            Token before;
            Token t = tokens.get(i);
            if (t.type == TokenType.OPERATOR && t.value.equals("<") && (before = this.findPrevNonWhitespaceInList(result)) != null && before.type == TokenType.IDENTIFIER && !before.value.isEmpty() && Character.isUpperCase(before.value.charAt(0))) {
                ++genericDepth;
            }
            if (genericDepth > 0 && t.type == TokenType.OPERATOR && (t.value.equals(">>") || t.value.equals(">>>"))) {
                for (int c = 0; c < t.value.length(); ++c) {
                    result.add(new Token(TokenType.OPERATOR, ">", t.position + c));
                    if (--genericDepth >= 0) continue;
                    genericDepth = 0;
                }
                continue;
            }
            if (t.type == TokenType.OPERATOR && t.value.equals(">") && genericDepth > 0) {
                --genericDepth;
            }
            result.add(t);
        }
        return result;
    }

    private Token findPrevNonWhitespaceInList(List<Token> tokens) {
        for (int i = tokens.size() - 1; i >= 0; --i) {
            if (tokens.get((int)i).type == TokenType.WHITESPACE) continue;
            return tokens.get(i);
        }
        return null;
    }

    private List<Token> mergeSplitOperators(List<Token> tokens) {
        ArrayList<Token> result = new ArrayList<Token>();
        int i = 0;
        while (i < tokens.size()) {
            String merged;
            Token t = tokens.get(i);
            if (t.type == TokenType.OPERATOR && (merged = this.tryMergeOperators(tokens, i)) != null && merged.length() > t.value.length()) {
                result.add(new Token(TokenType.OPERATOR, merged, t.position));
                i = this.skipMergedTokens(tokens, i, merged);
                continue;
            }
            if (t.type == TokenType.COLON) {
                int next;
                for (next = i + 1; next < tokens.size() && tokens.get((int)next).type == TokenType.WHITESPACE; ++next) {
                }
                if (next < tokens.size() && tokens.get((int)next).type == TokenType.COLON) {
                    result.add(new Token(TokenType.OPERATOR, "::", t.position));
                    i = next + 1;
                    continue;
                }
            }
            result.add(t);
            ++i;
        }
        return result;
    }

    private String tryMergeOperators(List<Token> tokens, int startIdx) {
        StringBuilder ops = new StringBuilder();
        ArrayList<Integer> opIndices = new ArrayList<Integer>();
        boolean hasWhitespaceBetween = false;
        for (int i = startIdx; i < tokens.size(); ++i) {
            Token t = tokens.get(i);
            if (t.type == TokenType.OPERATOR) {
                ops.append(t.value);
                opIndices.add(i);
                continue;
            }
            if (t.type != TokenType.WHITESPACE) break;
            if (opIndices.isEmpty()) continue;
            hasWhitespaceBetween = true;
        }
        String allOps = ops.toString();
        for (String multiOp : MULTI_CHAR_OPERATORS) {
            if (!allOps.startsWith(multiOp) || multiOp.length() <= 1 || hasWhitespaceBetween && (multiOp.equals("++") || multiOp.equals("--"))) continue;
            return multiOp;
        }
        return null;
    }

    private int skipMergedTokens(List<Token> tokens, int startIdx, String merged) {
        int i;
        int charsConsumed = 0;
        for (i = startIdx; i < tokens.size() && charsConsumed < merged.length(); ++i) {
            Token t = tokens.get(i);
            if (t.type != TokenType.OPERATOR) continue;
            charsConsumed += t.value.length();
        }
        return i;
    }

    private List<Token> formatTokens(List<Token> tokens) {
        ArrayList<Token> stripped = new ArrayList<Token>();
        for (Token t : tokens) {
            if (t.type == TokenType.WHITESPACE) continue;
            stripped.add(t);
        }
        if (stripped.isEmpty()) {
            return tokens;
        }
        ArrayList<Token> result = new ArrayList<Token>();
        result.add((Token)stripped.get(0));
        for (int i = 1; i < stripped.size(); ++i) {
            Token curr;
            Token prev;
            Token prevPrev = i >= 2 ? (Token)stripped.get(i - 2) : null;
            String spacing = this.determineSpacing(prevPrev, prev = (Token)stripped.get(i - 1), curr = (Token)stripped.get(i), stripped, i);
            if (!spacing.isEmpty()) {
                result.add(new Token(TokenType.WHITESPACE, spacing, -1));
            }
            result.add(curr);
        }
        return result;
    }

    private String determineSpacing(Token prevPrev, Token prev, Token curr, List<Token> allTokens, int currIdx) {
        if (prev.type == TokenType.DOT || curr.type == TokenType.DOT) {
            return "";
        }
        if (prev.type == TokenType.OPERATOR || curr.type == TokenType.OPERATOR) {
            return this.determineOperatorSpacing(prevPrev, prev, curr, allTokens, currIdx);
        }
        if (prev.type == TokenType.KEYWORD && curr.type == TokenType.PAREN_OPEN) {
            return this.determineKeywordParenSpacing(prev);
        }
        if (prev.type == TokenType.IDENTIFIER && curr.type == TokenType.PAREN_OPEN) {
            return this.settings.spaceBeforeMethodParens ? " " : "";
        }
        if (prev.type == TokenType.PAREN_CLOSE && curr.type == TokenType.BRACE_OPEN) {
            return this.settings.spaceBeforeOpenBrace ? " " : "";
        }
        if (prev.type == TokenType.KEYWORD && curr.type == TokenType.BRACE_OPEN) {
            return this.settings.spaceBeforeOpenBrace ? " " : "";
        }
        if ((prev.type == TokenType.IDENTIFIER || prev.type == TokenType.NUMBER || prev.type == TokenType.OPERATOR && prev.value.equals(">")) && curr.type == TokenType.BRACE_OPEN) {
            return this.settings.spaceBeforeOpenBrace ? " " : "";
        }
        if (prev.type == TokenType.PAREN_OPEN) {
            if (curr.type == TokenType.PAREN_CLOSE) {
                return "";
            }
            return this.settings.spaceWithinParens ? " " : "";
        }
        if (curr.type == TokenType.PAREN_CLOSE) {
            return this.settings.spaceWithinParens ? " " : "";
        }
        if (prev.type == TokenType.BRACKET_OPEN) {
            if (curr.type == TokenType.BRACKET_CLOSE) {
                return "";
            }
            return this.settings.spaceWithinBrackets ? " " : "";
        }
        if (curr.type == TokenType.BRACKET_CLOSE) {
            return this.settings.spaceWithinBrackets ? " " : "";
        }
        if (prev.type == TokenType.COMMA) {
            return this.settings.spaceAfterComma ? " " : "";
        }
        if (curr.type == TokenType.COMMA) {
            return this.settings.spaceBeforeComma ? " " : "";
        }
        if (prev.type == TokenType.SEMICOLON) {
            return this.settings.spaceAfterSemicolon ? " " : "";
        }
        if (curr.type == TokenType.SEMICOLON) {
            return this.settings.spaceBeforeSemicolon ? " " : "";
        }
        if (prev.type == TokenType.COLON) {
            if (this.isTernaryColon(allTokens, currIdx - 1)) {
                return " ";
            }
            if (this.isEnhancedForLoopColon(allTokens, currIdx - 1)) {
                return " ";
            }
            return this.settings.spaceAfterColon ? " " : "";
        }
        if (curr.type == TokenType.COLON) {
            if (this.isTernaryColon(allTokens, currIdx)) {
                return " ";
            }
            if (this.isEnhancedForLoopColon(allTokens, currIdx)) {
                return " ";
            }
            return this.settings.spaceBeforeColon ? " " : "";
        }
        if ((prev.type == TokenType.IDENTIFIER || prev.type == TokenType.NUMBER || prev.type == TokenType.PAREN_CLOSE) && curr.type == TokenType.BRACKET_OPEN) {
            return "";
        }
        if (prev.type == TokenType.BRACKET_CLOSE && (curr.type == TokenType.IDENTIFIER || curr.type == TokenType.KEYWORD || curr.type == TokenType.NUMBER)) {
            return " ";
        }
        if (prev.type == TokenType.KEYWORD && (curr.type == TokenType.KEYWORD || curr.type == TokenType.IDENTIFIER || curr.type == TokenType.NUMBER)) {
            return " ";
        }
        if (curr.type == TokenType.KEYWORD && (prev.type == TokenType.KEYWORD || prev.type == TokenType.IDENTIFIER || prev.type == TokenType.NUMBER || prev.type == TokenType.PAREN_CLOSE || prev.type == TokenType.BRACE_CLOSE)) {
            return " ";
        }
        if (!(prev.type != TokenType.IDENTIFIER && prev.type != TokenType.NUMBER || curr.type != TokenType.IDENTIFIER && curr.type != TokenType.NUMBER)) {
            return " ";
        }
        if (prev.type == TokenType.PAREN_CLOSE && (curr.type == TokenType.IDENTIFIER || curr.type == TokenType.NUMBER)) {
            return " ";
        }
        if (prev.type == TokenType.BRACE_OPEN) {
            if (curr.type == TokenType.BRACE_CLOSE) {
                return "";
            }
            return this.settings.spaceWithinBraces ? " " : "";
        }
        if (curr.type == TokenType.BRACE_CLOSE) {
            return this.settings.spaceWithinBraces ? " " : "";
        }
        return "";
    }

    private String determineOperatorSpacing(Token prevPrev, Token prev, Token curr, List<Token> allTokens, int currIdx) {
        if (prev.type == TokenType.OPERATOR) {
            String op = prev.value;
            if (op.equals("++") || op.equals("--")) {
                return this.settings.spaceAroundUnaryIncDec ? " " : "";
            }
            if (op.equals("::")) {
                return "";
            }
            if (op.equals("->") || op.equals("=>")) {
                return " ";
            }
            if (this.isAssignmentOperator(op)) {
                return this.settings.spaceAroundAssignment ? " " : "";
            }
            if (this.isComparisonOperator(op)) {
                if ((op.equals("<") || op.equals(">")) && this.isGenericContext(allTokens, currIdx - 1)) {
                    if (op.equals(">") && (curr.type == TokenType.IDENTIFIER || curr.type == TokenType.KEYWORD)) {
                        return " ";
                    }
                    return "";
                }
                return this.settings.spaceAroundComparison ? " " : "";
            }
            if (this.isLogicalOperator(op)) {
                return this.settings.spaceAroundLogical ? " " : "";
            }
            if (op.equals("!") || op.equals("~")) {
                return this.settings.spaceAroundUnaryNot ? " " : "";
            }
            if (this.isBitwiseOperator(op)) {
                return this.settings.spaceAroundBitwise ? " " : "";
            }
            if (this.isArithmeticOperator(op)) {
                if (this.isUnaryOperatorToken(op, null, prevPrev)) {
                    return "";
                }
                return this.settings.spaceAroundArithmetic ? " " : "";
            }
            if (op.equals("?")) {
                return " ";
            }
            return " ";
        }
        if (curr.type == TokenType.OPERATOR) {
            String op = curr.value;
            if (op.equals("++") || op.equals("--")) {
                return this.settings.spaceAroundUnaryIncDec ? " " : "";
            }
            if (op.equals("::")) {
                return "";
            }
            if (op.equals("->") || op.equals("=>")) {
                return " ";
            }
            if (this.isAssignmentOperator(op)) {
                return this.settings.spaceAroundAssignment ? " " : "";
            }
            if (this.isComparisonOperator(op)) {
                if ((op.equals("<") || op.equals(">")) && this.isGenericContext(allTokens, currIdx)) {
                    if (op.equals("<") && prev.type == TokenType.KEYWORD) {
                        return " ";
                    }
                    return "";
                }
                return this.settings.spaceAroundComparison ? " " : "";
            }
            if (this.isLogicalOperator(op)) {
                return this.settings.spaceAroundLogical ? " " : "";
            }
            if (op.equals("!") || op.equals("~")) {
                return this.settings.spaceAroundUnaryNot ? " " : "";
            }
            if (this.isBitwiseOperator(op)) {
                return this.settings.spaceAroundBitwise ? " " : "";
            }
            if (this.isArithmeticOperator(op)) {
                if (this.isUnaryOperatorToken(op, prevPrev, prev)) {
                    return "";
                }
                return this.settings.spaceAroundArithmetic ? " " : "";
            }
            if (op.equals("?")) {
                return " ";
            }
            return " ";
        }
        return " ";
    }

    private String determineKeywordParenSpacing(Token keyword) {
        String kw = keyword.value;
        if (kw.equals("if")) {
            return this.settings.spaceBeforeIfParens ? " " : "";
        }
        if (kw.equals("while")) {
            return this.settings.spaceBeforeWhileParens ? " " : "";
        }
        if (kw.equals("for")) {
            return this.settings.spaceBeforeForParens ? " " : "";
        }
        if (kw.equals("switch")) {
            return this.settings.spaceBeforeSwitchParens ? " " : "";
        }
        if (kw.equals("catch")) {
            return this.settings.spaceBeforeCatchParens ? " " : "";
        }
        return " ";
    }

    private boolean isTernaryColon(List<Token> tokens, int colonIdx) {
        for (int i = colonIdx - 1; i >= 0; --i) {
            Token t = tokens.get(i);
            if (t.type == TokenType.OPERATOR && t.value.equals("?")) {
                return true;
            }
            if (t.type != TokenType.SEMICOLON && t.type != TokenType.BRACE_OPEN && t.type != TokenType.BRACE_CLOSE) continue;
            return false;
        }
        return false;
    }

    private boolean isEnhancedForLoopColon(List<Token> tokens, int colonIdx) {
        int depth = 0;
        for (int i = colonIdx - 1; i >= 0; --i) {
            Token t = tokens.get(i);
            if (t.type == TokenType.PAREN_CLOSE) {
                ++depth;
            } else if (t.type == TokenType.PAREN_OPEN) {
                if (depth > 0) {
                    --depth;
                } else {
                    for (int j = i - 1; j >= 0; --j) {
                        if (tokens.get((int)j).type == TokenType.WHITESPACE) continue;
                        return tokens.get((int)j).type == TokenType.KEYWORD && tokens.get((int)j).value.equals("for");
                    }
                    return false;
                }
            }
            if (t.type != TokenType.SEMICOLON && t.type != TokenType.BRACE_OPEN && t.type != TokenType.BRACE_CLOSE) continue;
            return false;
        }
        return false;
    }

    private boolean isAssignmentOperator(String op) {
        switch (op) {
            case "=": 
            case "+=": 
            case "-=": 
            case "*=": 
            case "/=": 
            case "%=": 
            case "&=": 
            case "|=": 
            case "^=": 
            case "<<=": 
            case ">>=": 
            case ">>>=": {
                return true;
            }
        }
        return false;
    }

    private boolean isComparisonOperator(String op) {
        switch (op) {
            case "==": 
            case "!=": 
            case "===": 
            case "!==": 
            case "<": 
            case ">": 
            case "<=": 
            case ">=": {
                return true;
            }
        }
        return false;
    }

    private boolean isLogicalOperator(String op) {
        return op.equals("&&") || op.equals("||");
    }

    private boolean isBitwiseOperator(String op) {
        switch (op) {
            case "&": 
            case "|": 
            case "^": 
            case "~": 
            case "<<": 
            case ">>": 
            case ">>>": {
                return true;
            }
        }
        return false;
    }

    private boolean isArithmeticOperator(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("%");
    }

    private boolean isUnaryOperatorToken(String op, Token prevPrev, Token prevToken) {
        if (!op.equals("+") && !op.equals("-")) {
            return false;
        }
        if (prevToken == null) {
            return true;
        }
        switch (prevToken.type) {
            case OPERATOR: 
            case PAREN_OPEN: 
            case BRACKET_OPEN: 
            case BRACE_OPEN: 
            case COMMA: 
            case SEMICOLON: 
            case COLON: 
            case KEYWORD: {
                return true;
            }
        }
        return false;
    }

    private boolean isGenericContext(List<Token> tokens, int opIdx) {
        if (opIdx < 0 || opIdx >= tokens.size()) {
            return false;
        }
        Token opToken = tokens.get(opIdx);
        String op = opToken.value;
        if (op.equals("<")) {
            Token before = this.findPrevNonWhitespace(tokens, opIdx);
            if (before != null) {
                if (before.type == TokenType.IDENTIFIER && !before.value.isEmpty() && Character.isUpperCase(before.value.charAt(0))) {
                    return true;
                }
                if (before.type == TokenType.KEYWORD && (before.value.equals("var") || before.value.equals("let") || before.value.equals("const"))) {
                    return true;
                }
            }
            int depth = 1;
            for (int i = opIdx + 1; i < tokens.size() && depth > 0; ++i) {
                Token t = tokens.get(i);
                if (t.type == TokenType.WHITESPACE) continue;
                if (t.type == TokenType.OPERATOR && t.value.equals("<")) {
                    ++depth;
                    continue;
                }
                if (t.type == TokenType.OPERATOR && t.value.equals(">")) {
                    --depth;
                    continue;
                }
                if (t.type == TokenType.SEMICOLON || t.type == TokenType.BRACE_OPEN || t.type == TokenType.BRACE_CLOSE) break;
            }
            return depth == 0;
        }
        if (op.equals(">")) {
            int depth = 1;
            for (int i = opIdx - 1; i >= 0 && depth > 0; --i) {
                Token t = tokens.get(i);
                if (t.type == TokenType.WHITESPACE) continue;
                if (t.type == TokenType.OPERATOR && t.value.equals(">")) {
                    ++depth;
                    continue;
                }
                if (t.type == TokenType.OPERATOR && t.value.equals("<")) {
                    --depth;
                    continue;
                }
                if (t.type == TokenType.SEMICOLON || t.type == TokenType.BRACE_OPEN || t.type == TokenType.BRACE_CLOSE) break;
            }
            return depth == 0;
        }
        return false;
    }

    private Token findPrevNonWhitespace(List<Token> tokens, int idx) {
        for (int i = idx - 1; i >= 0; --i) {
            if (tokens.get((int)i).type == TokenType.WHITESPACE) continue;
            return tokens.get(i);
        }
        return null;
    }

    private String reconstruct(List<Token> tokens) {
        StringBuilder sb = new StringBuilder();
        for (Token t : tokens) {
            sb.append(t.value);
        }
        return sb.toString();
    }

    private String trimTrailing(String line) {
        int end;
        for (end = line.length(); end > 0 && (line.charAt(end - 1) == ' ' || line.charAt(end - 1) == '\t'); --end) {
        }
        return line.substring(0, end);
    }

    public static String formatCode(String code) {
        return new FormatHelper().format(code);
    }

    public static String formatCode(String code, FormatSettings settings) {
        return new FormatHelper(settings).format(code);
    }

    public static String sanitizeClipboard(String clipboard, int trimThreshold) {
        if (clipboard == null || clipboard.isEmpty()) {
            return clipboard;
        }
        String normalized = clipboard.replace("\r\n", "\n").replace("\r", "\n");
        String[] lines = normalized.split("\n", -1);
        StringBuilder result = new StringBuilder(normalized.length());
        for (int i = 0; i < lines.length; ++i) {
            int end;
            String line = lines[i];
            for (end = line.length(); end > 0 && (line.charAt(end - 1) == ' ' || line.charAt(end - 1) == '\t'); --end) {
            }
            int trailingLen = line.length() - end;
            if (trailingLen >= trimThreshold) {
                result.append(line, 0, end);
            } else {
                result.append(line);
            }
            if (i >= lines.length - 1) continue;
            result.append('\n');
        }
        return result.toString();
    }

    public String wrapLines(String text, int maxWidth) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        int width = maxWidth > 0 ? maxWidth : this.settings.maxLineLength;
        String joined = this.format(this.joinContinuationLines(text));
        String[] lines = joined.split("\n", -1);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            if (this.getVisualLength(line) <= width) {
                result.append(line);
            } else {
                String trimmed = line.trim();
                if (trimmed.startsWith("//")) {
                    result.append(this.wrapCommentLine(line, width));
                } else if (trimmed.startsWith("/*") || trimmed.startsWith("*")) {
                    result.append(this.wrapBlockCommentLine(line, width));
                } else {
                    result.append(this.wrapCodeLine(line, width));
                }
            }
            if (i >= lines.length - 1) continue;
            result.append("\n");
        }
        return result.toString();
    }

    private String joinContinuationLines(String text) {
        String[] lines = text.split("\n", -1);
        ArrayList<String> out = new ArrayList<String>();
        for (int i = 0; i < lines.length; ++i) {
            int last;
            String parentTrimmed;
            int idx;
            String line = lines[i];
            String trimmed = line.trim();
            if (trimmed.isEmpty() || trimmed.startsWith("//") || trimmed.startsWith("/*") || trimmed.startsWith("*")) {
                out.add(line);
                continue;
            }
            boolean shouldJoin = false;
            int parentIdx = -1;
            if ((this.isContinuationLine(trimmed) || this.startsWithDeclarationKeyword(trimmed)) && !out.isEmpty()) {
                for (idx = out.size() - 1; idx >= 0 && ((String)out.get(idx)).trim().isEmpty(); --idx) {
                }
                if (idx >= 0) {
                    parentTrimmed = ((String)out.get(idx)).trim();
                    int n = last = parentTrimmed.isEmpty() ? 0 : (int)parentTrimmed.charAt(parentTrimmed.length() - 1);
                    if (last != 123 && last != 125 && last != 59) {
                        shouldJoin = true;
                        parentIdx = idx;
                    }
                }
            }
            if (!shouldJoin && !out.isEmpty()) {
                for (idx = out.size() - 1; idx >= 0 && ((String)out.get(idx)).trim().isEmpty(); --idx) {
                }
                if (!(idx < 0 || (parentTrimmed = ((String)out.get(idx)).trim()).isEmpty() || (last = (int)parentTrimmed.charAt(parentTrimmed.length() - 1)) != 38 && last != 124 && last != 60 && last != 43 && last != 45 && last != 63 && last != 44 && last != 62)) {
                    shouldJoin = true;
                    parentIdx = idx;
                }
            }
            if (shouldJoin) {
                out.set(parentIdx, this.trimTrailing((String)out.get(parentIdx)) + " " + trimmed);
                continue;
            }
            out.add(line);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < out.size(); ++i) {
            sb.append((String)out.get(i));
            if (i >= out.size() - 1) continue;
            sb.append("\n");
        }
        return sb.toString();
    }

    private boolean startsWithDeclarationKeyword(String trimmed) {
        for (String kw : DECLARATION_BREAK_KEYWORDS) {
            if (!trimmed.startsWith(kw) || trimmed.length() != kw.length() && Character.isJavaIdentifierPart(trimmed.charAt(kw.length()))) continue;
            return true;
        }
        return false;
    }

    private int getVisualLength(String line) {
        int len = 0;
        for (char c : line.toCharArray()) {
            if (c == '\t') {
                len += 4;
                continue;
            }
            ++len;
        }
        return len;
    }

    private String extractIndent(String line) {
        int i;
        for (i = 0; i < line.length() && (line.charAt(i) == ' ' || line.charAt(i) == '\t'); ++i) {
        }
        return line.substring(0, i);
    }

    private String wrapCommentLine(String line, int maxWidth) {
        if (!this.settings.wrapComments) {
            return line;
        }
        String indent = this.extractIndent(line);
        String content = line.substring(indent.length());
        if (!content.startsWith("//")) {
            return line;
        }
        String commentContent = content.substring(2).trim();
        String prefix = indent + "// ";
        int prefixLen = this.getVisualLength(prefix);
        int contentWidth = maxWidth - prefixLen;
        if (contentWidth <= 10) {
            return line;
        }
        StringBuilder result = new StringBuilder();
        result.append(indent).append("// ");
        String[] words = commentContent.split("\\s+");
        int lineLen = prefixLen;
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            int wordLen = word.length();
            if (lineLen + wordLen > maxWidth && lineLen > prefixLen) {
                result.append("\n").append(prefix);
                lineLen = prefixLen;
            }
            if (lineLen > prefixLen) {
                result.append(" ");
                ++lineLen;
            }
            result.append(word);
            lineLen += wordLen;
        }
        return result.toString();
    }

    private String wrapBlockCommentLine(String line, int maxWidth) {
        int prefixLen;
        int contentWidth;
        String textContent;
        String prefix;
        if (!this.settings.wrapComments) {
            return line;
        }
        String indent = this.extractIndent(line);
        String content = line.substring(indent.length()).trim();
        if (content.startsWith("/**")) {
            prefix = indent + " * ";
            textContent = content.substring(3).trim();
        } else if (content.startsWith("/*")) {
            prefix = indent + " * ";
            textContent = content.substring(2).trim();
        } else if (content.startsWith("*")) {
            prefix = indent + " * ";
            textContent = content.substring(1).trim();
        } else {
            return line;
        }
        boolean hasClose = textContent.endsWith("*/");
        if (hasClose) {
            textContent = textContent.substring(0, textContent.length() - 2).trim();
        }
        if ((contentWidth = maxWidth - (prefixLen = this.getVisualLength(prefix))) <= 10) {
            return line;
        }
        StringBuilder result = new StringBuilder();
        if (content.startsWith("/**")) {
            result.append(indent).append("/** ");
        } else if (content.startsWith("/*")) {
            result.append(indent).append("/* ");
        } else {
            result.append(indent).append(" * ");
        }
        String[] words = textContent.split("\\s+");
        int lineLen = prefixLen;
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            int wordLen = word.length();
            if (lineLen + wordLen > maxWidth && lineLen > prefixLen) {
                result.append("\n").append(prefix);
                lineLen = prefixLen;
            }
            if (lineLen > prefixLen) {
                result.append(" ");
                ++lineLen;
            }
            result.append(word);
            lineLen += wordLen;
        }
        if (hasClose) {
            result.append(" */");
        }
        return result.toString();
    }

    private int findInlineCommentStart(String content) {
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = 0; i < content.length(); ++i) {
            int endBlock;
            char prev;
            char c = content.charAt(i);
            char c2 = prev = i > 0 ? content.charAt(i - 1) : (char)'\u0000';
            if ((c == '\"' || c == '\'') && prev != '\\') {
                if (!inString) {
                    inString = true;
                    stringChar = c;
                } else if (c == stringChar) {
                    inString = false;
                }
            }
            if (inString) continue;
            if (c == '/' && i + 1 < content.length() && content.charAt(i + 1) == '*' && (endBlock = content.indexOf("*/", i + 2)) >= 0) {
                i = endBlock + 1;
                continue;
            }
            if (c != '/' || i + 1 >= content.length() || content.charAt(i + 1) != '/') continue;
            return i;
        }
        return -1;
    }

    private String wordWrapComment(String commentText, String prefix, int maxWidth, int firstLineCapacity) {
        if (commentText.isEmpty()) {
            return "//";
        }
        String[] words = commentText.split("\\s+");
        int prefixLen = this.getVisualLength(prefix);
        int contentWidth = maxWidth - prefixLen;
        if (contentWidth <= 10) {
            return "// " + commentText;
        }
        StringBuilder result = new StringBuilder();
        boolean onFirstLine = firstLineCapacity > 0;
        int capacity = onFirstLine ? firstLineCapacity : contentWidth;
        int lineLen = 0;
        if (!onFirstLine) {
            result.append(prefix);
        } else {
            result.append("// ");
            lineLen = 3;
        }
        for (int i = 0; i < words.length; ++i) {
            String word;
            int wordLen;
            if (lineLen + (lineLen > (onFirstLine ? 3 : 0) ? 1 : 0) + (wordLen = (word = words[i]).length()) > capacity && lineLen > 0) {
                result.append("\n").append(prefix);
                lineLen = 0;
                capacity = contentWidth;
                onFirstLine = false;
            }
            if (lineLen > 0) {
                result.append(" ");
                ++lineLen;
            }
            result.append(word);
            lineLen += wordLen;
        }
        return result.toString();
    }

    private String wrapCodeLine(String line, int maxWidth) {
        int commentLen;
        String indent = this.extractIndent(line);
        String content = line.substring(indent.length());
        int commentStart = this.findInlineCommentStart(content);
        String codePart = content;
        String commentPart = null;
        if (commentStart >= 0) {
            codePart = content.substring(0, commentStart);
            commentPart = content.substring(commentStart);
            while (!codePart.isEmpty() && codePart.charAt(codePart.length() - 1) == ' ') {
                codePart = codePart.substring(0, codePart.length() - 1);
            }
        }
        String wrappedCode = this.wrapCodeContent(codePart, indent, maxWidth);
        if (commentPart == null) {
            return wrappedCode;
        }
        int lastNewline = wrappedCode.lastIndexOf(10);
        String lastLine = lastNewline >= 0 ? wrappedCode.substring(lastNewline + 1) : wrappedCode;
        int lastLineLen = this.getVisualLength(lastLine);
        if (lastLineLen + (commentLen = 1 + this.getVisualLength(commentPart)) <= maxWidth) {
            return wrappedCode + " " + commentPart;
        }
        String commentIndent = indent;
        String commentText = commentPart.startsWith("//") ? commentPart.substring(2).trim() : commentPart;
        String commentPrefix = commentIndent + "// ";
        int availableWidth = maxWidth - this.getVisualLength(commentPrefix);
        if (availableWidth > 10 && this.getVisualLength(commentText) > availableWidth) {
            String wrappedComment = this.wordWrapComment(commentText, commentPrefix, maxWidth, -1);
            return wrappedCode + "\n" + wrappedComment;
        }
        return wrappedCode + "\n" + commentPrefix + commentText;
    }

    private String wrapCodeContent(String content, String indent, int maxWidth) {
        String argWrapped;
        if (this.settings.wrapMethodArguments && (argWrapped = this.tryArgumentAwareWrap(content, indent, maxWidth)) != null) {
            return argWrapped;
        }
        String wrapIndent = indent + this.spaces(this.settings.wrapIndentSpaces);
        String fullLine = indent + content;
        if (this.getVisualLength(fullLine) <= maxWidth) {
            return fullLine;
        }
        List<BreakPoint> breakPoints = this.findBreakPoints(content);
        if (breakPoints.isEmpty()) {
            return fullLine;
        }
        StringBuilder result = new StringBuilder();
        result.append(indent);
        int currentLen = this.getVisualLength(indent);
        int lastBreak = 0;
        for (BreakPoint bp : breakPoints) {
            int segmentEnd = bp.position;
            String segment = content.substring(lastBreak, segmentEnd);
            int segmentLen = this.getVisualLength(segment);
            if (currentLen + segmentLen > maxWidth && lastBreak > 0) {
                result.append("\n").append(wrapIndent);
                currentLen = this.getVisualLength(wrapIndent);
                segment = this.trimLeading(segment);
            }
            result.append(segment);
            currentLen += this.getVisualLength(segment);
            lastBreak = segmentEnd;
        }
        if (lastBreak < content.length()) {
            String remaining = content.substring(lastBreak);
            int remLen = this.getVisualLength(remaining);
            if (currentLen + remLen > maxWidth && lastBreak > 0) {
                result.append("\n").append(wrapIndent);
                remaining = this.trimLeading(remaining);
            }
            result.append(remaining);
        }
        return result.toString();
    }

    private String tryArgumentAwareWrap(String content, String indent, int maxWidth) {
        String fullLine = indent + content;
        if (this.getVisualLength(fullLine) <= maxWidth) {
            return null;
        }
        int callParenIdx = this.findOutermostCallParen(content);
        if (callParenIdx < 0) {
            return null;
        }
        int matchingClose = this.findMatchingClose(content, callParenIdx);
        if (matchingClose < 0 || matchingClose >= content.length()) {
            return null;
        }
        String prefix = content.substring(0, callParenIdx + 1);
        String argsBody = content.substring(callParenIdx + 1, matchingClose);
        String suffix = content.substring(matchingClose);
        List<String> args = this.splitArguments(argsBody);
        if (args.size() < 2) {
            return null;
        }
        String argIndent = indent + this.spaces(this.settings.wrapIndentSpaces);
        String closeIndent = indent;
        StringBuilder result = new StringBuilder();
        result.append(indent).append(prefix).append("\n");
        for (int i = 0; i < args.size(); ++i) {
            String arg = args.get(i).trim();
            if (arg.isEmpty()) continue;
            result.append(argIndent).append(arg);
            if (i < args.size() - 1) {
                result.append(",");
            }
            result.append("\n");
        }
        result.append(closeIndent).append(suffix);
        String wrapped = result.toString();
        if (this.getVisualLength(indent + prefix) <= maxWidth) {
            return wrapped;
        }
        return wrapped;
    }

    private int findOutermostCallParen(String content) {
        int bestIdx = -1;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = 0; i < content.length(); ++i) {
            int prev;
            char c = content.charAt(i);
            int n = prev = i > 0 ? (int)content.charAt(i - 1) : 32;
            if ((c == '\"' || c == '\'') && prev != 92) {
                if (!inString) {
                    inString = true;
                    stringChar = c;
                } else if (c == stringChar) {
                    inString = false;
                }
            }
            if (inString || c != '(' || i <= 0 || !Character.isJavaIdentifierPart(content.charAt(i - 1)) && content.charAt(i - 1) != '>') continue;
            bestIdx = i;
            break;
        }
        return bestIdx;
    }

    private int findMatchingClose(String content, int openIdx) {
        int depth = 1;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = openIdx + 1; i < content.length(); ++i) {
            int prev;
            char c = content.charAt(i);
            int n = prev = i > 0 ? (int)content.charAt(i - 1) : 32;
            if ((c == '\"' || c == '\'') && prev != 92) {
                if (!inString) {
                    inString = true;
                    stringChar = c;
                } else if (c == stringChar) {
                    inString = false;
                }
            }
            if (inString) continue;
            if (c == '(') {
                ++depth;
                continue;
            }
            if (c != ')' || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    private List<String> splitArguments(String argsBody) {
        ArrayList<String> args = new ArrayList<String>();
        StringBuilder current = new StringBuilder();
        int depth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = 0; i < argsBody.length(); ++i) {
            int prev;
            char c = argsBody.charAt(i);
            int n = prev = i > 0 ? (int)argsBody.charAt(i - 1) : 32;
            if ((c == '\"' || c == '\'') && prev != 92) {
                if (!inString) {
                    inString = true;
                    stringChar = c;
                } else if (c == stringChar) {
                    inString = false;
                }
            }
            if (inString) {
                current.append(c);
                continue;
            }
            if (c == '(' || c == '[' || c == '{') {
                ++depth;
            } else if (c == ')' || c == ']' || c == '}') {
                --depth;
            }
            if (c == ',' && depth == 0) {
                args.add(current.toString());
                current = new StringBuilder();
                continue;
            }
            current.append(c);
        }
        if (current.length() > 0) {
            args.add(current.toString());
        }
        return args;
    }

    private String trimLeading(String s) {
        int start;
        for (start = 0; start < s.length() && Character.isWhitespace(s.charAt(start)); ++start) {
        }
        return s.substring(start);
    }

    private List<BreakPoint> findBreakPoints(String content) {
        ArrayList<BreakPoint> points = new ArrayList<BreakPoint>();
        int parenDepth = 0;
        int bracketDepth = 0;
        int braceDepth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        block0: for (int i = 0; i < content.length(); ++i) {
            int prev;
            char c = content.charAt(i);
            int n = prev = i > 0 ? (int)content.charAt(i - 1) : 32;
            if ((c == '\"' || c == '\'') && prev != 92) {
                if (!inString) {
                    inString = true;
                    stringChar = c;
                } else if (c == stringChar) {
                    inString = false;
                }
            }
            if (inString) continue;
            if (c == '(') {
                ++parenDepth;
            } else if (c == ')') {
                --parenDepth;
            } else if (c == '[') {
                ++bracketDepth;
            } else if (c == ']') {
                --bracketDepth;
            } else if (c == '{') {
                ++braceDepth;
            } else if (c == '}') {
                --braceDepth;
            }
            int depth = parenDepth + bracketDepth + braceDepth;
            if (this.settings.wrapAfterComma && c == ',') {
                points.add(new BreakPoint(i + 1, 1 + depth));
            }
            if ((c == '-' || c == '=') && i + 1 < content.length() && content.charAt(i + 1) == '>') {
                ++i;
                continue;
            }
            if (this.settings.wrapAfterOperator) {
                char next;
                if ((c == '+' || c == '-') && i > 0 && i < content.length() - 1) {
                    next = content.charAt(i + 1);
                    if (prev != 40 && prev != 91 && prev != 44 && prev != 61 && next != '+' && next != '-' && next != '=' && next != '>') {
                        points.add(new BreakPoint(i + 1, 3 + depth));
                    }
                }
                if (c == '&' && i + 1 < content.length() && content.charAt(i + 1) == '&') {
                    points.add(new BreakPoint(i + 2, 2 + depth));
                    ++i;
                    continue;
                }
                if (c == '|' && i + 1 < content.length() && content.charAt(i + 1) == '|') {
                    points.add(new BreakPoint(i + 2, 2 + depth));
                    ++i;
                    continue;
                }
                if (c == '&' && i > 0 && i < content.length() - 1 && content.charAt(i + 1) != '&' && content.charAt(i + 1) != '=') {
                    points.add(new BreakPoint(i + 1, 3 + depth));
                }
                if (c == '?' && depth == 0 && i > 0 && i < content.length() - 1) {
                    points.add(new BreakPoint(i, 2));
                }
                if (c == ':' && depth == 0 && i > 0 && i < content.length() - 1 && prev != 58 && (i + 1 >= content.length() || content.charAt(i + 1) != ':')) {
                    points.add(new BreakPoint(i, 2));
                }
                if (c == '=' && depth == 0 && i > 0 && i < content.length() - 1) {
                    next = content.charAt(i + 1);
                    if (prev != 61 && prev != 33 && prev != 60 && prev != 62 && prev != 43 && prev != 45 && prev != 42 && prev != 47 && prev != 37 && prev != 38 && prev != 124 && prev != 94 && next != '=' && next != '>') {
                        points.add(new BreakPoint(i + 1, 4 + depth));
                    }
                }
            }
            if (this.settings.wrapBeforeDot && c == '.') {
                points.add(new BreakPoint(i, 2 + depth));
            }
            if (c == '{') {
                if (i + 1 < content.length()) {
                    points.add(new BreakPoint(i + 1, 1));
                }
                if (i > 0) {
                    points.add(new BreakPoint(i, 2));
                }
            }
            if (c != ' ' || i + 1 >= content.length() || !Character.isLetter(content.charAt(i + 1))) continue;
            for (String kw : DECLARATION_BREAK_KEYWORDS) {
                if (i + 1 + kw.length() > content.length() || !content.substring(i + 1, i + 1 + kw.length()).equals(kw) || i + 1 + kw.length() < content.length() && Character.isJavaIdentifierPart(content.charAt(i + 1 + kw.length()))) continue;
                points.add(new BreakPoint(i + 1, 2));
                continue block0;
            }
        }
        points.sort((a, b) -> Integer.compare(a.position, b.position));
        return points;
    }

    private String spaces(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public String formatAndWrap(String text, int maxWidth) {
        String formatted = this.format(text);
        if (this.settings.wrapLongLines) {
            return this.wrapLines(formatted, maxWidth);
        }
        return formatted;
    }

    private static class BreakPoint {
        int position;
        int priority;

        BreakPoint(int pos, int prio) {
            this.position = pos;
            this.priority = prio;
        }
    }

    public static class FormatSettings {
        public boolean spaceAroundAssignment = true;
        public boolean spaceAroundArithmetic = true;
        public boolean spaceAroundComparison = true;
        public boolean spaceAroundLogical = true;
        public boolean spaceAroundBitwise = true;
        public boolean spaceWithinParens = false;
        public boolean spaceWithinBrackets = false;
        public boolean spaceWithinBraces = true;
        public boolean spaceAfterComma = true;
        public boolean spaceBeforeComma = false;
        public boolean spaceAfterSemicolon = true;
        public boolean spaceBeforeSemicolon = false;
        public boolean spaceAfterColon = true;
        public boolean spaceBeforeColon = false;
        public boolean spaceBeforeMethodParens = false;
        public boolean spaceAfterTypeCast = true;
        public boolean spaceBeforeIfParens = true;
        public boolean spaceBeforeWhileParens = true;
        public boolean spaceBeforeForParens = true;
        public boolean spaceBeforeSwitchParens = true;
        public boolean spaceBeforeCatchParens = true;
        public boolean spaceBeforeOpenBrace = true;
        public int maxLineLength = 120;
        public boolean wrapLongLines = false;
        public int wrapIndentSpaces = 4;
        public boolean wrapComments = true;
        public boolean wrapAfterComma = true;
        public boolean wrapAfterOperator = true;
        public boolean wrapBeforeDot = true;
        public boolean wrapMethodArguments = true;
        public boolean normalizeWhitespace = true;
        public boolean trimTrailingWhitespace = true;
        public boolean spaceAroundUnaryNot = false;
        public boolean spaceAroundUnaryIncDec = false;

        public static FormatSettings defaults() {
            return new FormatSettings();
        }

        public static FormatSettings compact() {
            FormatSettings s = new FormatSettings();
            s.spaceWithinParens = false;
            s.spaceWithinBrackets = false;
            s.spaceWithinBraces = false;
            s.wrapLongLines = false;
            return s;
        }
    }

    static class Token {
        final TokenType type;
        final String value;
        final int position;

        Token(TokenType type, String value, int position) {
            this.type = type;
            this.value = value;
            this.position = position;
        }

        public String toString() {
            return (Object)((Object)this.type) + "(" + this.value + ")@" + this.position;
        }
    }

    static enum TokenType {
        STRING_LITERAL,
        BLOCK_COMMENT,
        LINE_COMMENT,
        OPERATOR,
        KEYWORD,
        PAREN_OPEN,
        PAREN_CLOSE,
        BRACKET_OPEN,
        BRACKET_CLOSE,
        BRACE_OPEN,
        BRACE_CLOSE,
        COMMA,
        SEMICOLON,
        COLON,
        DOT,
        IDENTIFIER,
        NUMBER,
        WHITESPACE;

    }
}

