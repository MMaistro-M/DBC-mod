/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

public final class CodeParser {
    private CodeParser() {
    }

    public static int findMatchingBrace(String text, int openBrace) {
        if (openBrace < 0 || openBrace >= text.length() || text.charAt(openBrace) != '{') {
            return -1;
        }
        int depth = 1;
        for (int i = openBrace + 1; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c == '{') {
                ++depth;
                continue;
            }
            if (c != '}' || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    public static int findMatchingParen(String text, int openParen) {
        if (openParen < 0 || openParen >= text.length() || text.charAt(openParen) != '(') {
            return -1;
        }
        int depth = 1;
        for (int i = openParen + 1; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c == '(') {
                ++depth;
                continue;
            }
            if (c != ')' || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    public static String removeStringsAndComments(String code) {
        StringBuilder result = new StringBuilder();
        boolean inString = false;
        boolean inChar = false;
        boolean inLineComment = false;
        boolean inBlockComment = false;
        for (int i = 0; i < code.length(); ++i) {
            char next;
            char c = code.charAt(i);
            char c2 = next = i + 1 < code.length() ? code.charAt(i + 1) : (char)'\u0000';
            if (inLineComment) {
                if (c != '\n') continue;
                inLineComment = false;
                result.append(c);
                continue;
            }
            if (inBlockComment) {
                if (c != '*' || next != '/') continue;
                inBlockComment = false;
                ++i;
                continue;
            }
            if (inString || inChar) {
                if (c == '\\' && i + 1 < code.length()) {
                    ++i;
                    continue;
                }
                if ((!inString || c != '\"') && (!inChar || c != '\'')) continue;
                inString = false;
                inChar = false;
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
            if (c == '\"') {
                inString = true;
                continue;
            }
            if (c == '\'') {
                inChar = true;
                continue;
            }
            result.append(c);
        }
        return result.toString();
    }

    public static String removeComments(String code) {
        StringBuilder result = new StringBuilder();
        boolean inString = false;
        boolean inChar = false;
        boolean inLineComment = false;
        boolean inBlockComment = false;
        for (int i = 0; i < code.length(); ++i) {
            char next;
            char c = code.charAt(i);
            char c2 = next = i + 1 < code.length() ? code.charAt(i + 1) : (char)'\u0000';
            if (inLineComment) {
                if (c == '\n') {
                    inLineComment = false;
                    result.append(c);
                    continue;
                }
                result.append(' ');
                continue;
            }
            if (inBlockComment) {
                if (c == '*' && next == '/') {
                    inBlockComment = false;
                    result.append("  ");
                    ++i;
                    continue;
                }
                result.append(c == '\n' ? (char)'\n' : ' ');
                continue;
            }
            if (inString) {
                result.append(c);
                if (c == '\\' && i + 1 < code.length()) {
                    result.append(code.charAt(++i));
                    continue;
                }
                if (c != '\"') continue;
                inString = false;
                continue;
            }
            if (inChar) {
                result.append(c);
                if (c == '\\' && i + 1 < code.length()) {
                    result.append(code.charAt(++i));
                    continue;
                }
                if (c != '\'') continue;
                inChar = false;
                continue;
            }
            if (c == '/' && next == '/') {
                inLineComment = true;
                result.append("  ");
                ++i;
                continue;
            }
            if (c == '/' && next == '*') {
                inBlockComment = true;
                result.append("  ");
                ++i;
                continue;
            }
            if (c == '\"') {
                inString = true;
            }
            if (c == '\'') {
                inChar = true;
            }
            result.append(c);
        }
        return result.toString();
    }

    public static boolean isKeywordAt(String text, int pos, String keyword) {
        if (pos + keyword.length() > text.length()) {
            return false;
        }
        if (!text.substring(pos).startsWith(keyword)) {
            return false;
        }
        boolean validBefore = pos == 0 || !Character.isLetterOrDigit(text.charAt(pos - 1));
        boolean validAfter = pos + keyword.length() >= text.length() || !Character.isLetterOrDigit(text.charAt(pos + keyword.length()));
        return validBefore && validAfter;
    }

    public static int findReturnKeyword(String text, int start) {
        boolean inString = false;
        boolean inChar = false;
        for (int i = start; i < text.length() - 5; ++i) {
            char c = text.charAt(i);
            if (inString) {
                if (c == '\\' && i + 1 < text.length()) {
                    ++i;
                    continue;
                }
                if (c != '\"') continue;
                inString = false;
                continue;
            }
            if (inChar) {
                if (c == '\\' && i + 1 < text.length()) {
                    ++i;
                    continue;
                }
                if (c != '\'') continue;
                inChar = false;
                continue;
            }
            if (c == '\"') {
                inString = true;
                continue;
            }
            if (c == '\'') {
                inChar = true;
                continue;
            }
            if (!CodeParser.isKeywordAt(text, i, "return")) continue;
            return i;
        }
        return -1;
    }

    public static int findReturnSemicolon(String text, int start) {
        int parenDepth = 0;
        int braceDepth = 0;
        boolean inString = false;
        boolean inChar = false;
        for (int i = start; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (inString) {
                if (c == '\\' && i + 1 < text.length()) {
                    ++i;
                    continue;
                }
                if (c != '\"') continue;
                inString = false;
                continue;
            }
            if (inChar) {
                if (c == '\\' && i + 1 < text.length()) {
                    ++i;
                    continue;
                }
                if (c != '\'') continue;
                inChar = false;
                continue;
            }
            if (c == '\"') {
                inString = true;
                continue;
            }
            if (c == '\'') {
                inChar = true;
                continue;
            }
            if (c == '(') {
                ++parenDepth;
                continue;
            }
            if (c == ')') {
                --parenDepth;
                continue;
            }
            if (c == '{') {
                ++braceDepth;
                continue;
            }
            if (c == '}') {
                --braceDepth;
                continue;
            }
            if (c != ';' || parenDepth != 0 || braceDepth != 0) continue;
            return i;
        }
        return -1;
    }

    public static int skipWhitespace(String text, int pos) {
        while (pos < text.length() && Character.isWhitespace(text.charAt(pos))) {
            ++pos;
        }
        return pos;
    }
}

