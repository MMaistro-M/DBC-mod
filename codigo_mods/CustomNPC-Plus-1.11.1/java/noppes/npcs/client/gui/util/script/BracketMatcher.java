/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.ArrayList;
import java.util.List;
import noppes.npcs.client.gui.util.script.JavaTextContainer;

public class BracketMatcher {
    public static int[] findBracketSpanAt(String text, int pos) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        if (pos >= 0 && pos < text.length()) {
            char c = text.charAt(pos);
            int found = 0;
            if (c == '{') {
                found = BracketMatcher.findClosingBracket(text.substring(pos), '{', '}');
            } else if (c == '[') {
                found = BracketMatcher.findClosingBracket(text.substring(pos), '[', ']');
            } else if (c == '(') {
                found = BracketMatcher.findClosingBracket(text.substring(pos), '(', ')');
            } else if (c == '}') {
                found = BracketMatcher.findOpeningBracket(text.substring(0, pos + 1), '{', '}');
            } else if (c == ']') {
                found = BracketMatcher.findOpeningBracket(text.substring(0, pos + 1), '[', ']');
            } else if (c == ')') {
                found = BracketMatcher.findOpeningBracket(text.substring(0, pos + 1), '(', ')');
            }
            if (found != 0) {
                return new int[]{pos, pos + found};
            }
        }
        if (pos > 0) {
            for (int scan = pos - 1; scan >= 0; --scan) {
                char sc = text.charAt(scan);
                if (sc == ' ' || sc == '\t') {
                    continue;
                }
                if (sc == '\n') break;
                int found2 = 0;
                if (sc == '{') {
                    found2 = BracketMatcher.findClosingBracket(text.substring(scan), '{', '}');
                } else if (sc == '[') {
                    found2 = BracketMatcher.findClosingBracket(text.substring(scan), '[', ']');
                } else if (sc == '(') {
                    found2 = BracketMatcher.findClosingBracket(text.substring(scan), '(', ')');
                } else if (sc == '}') {
                    found2 = BracketMatcher.findOpeningBracket(text.substring(0, scan + 1), '{', '}');
                } else if (sc == ']') {
                    found2 = BracketMatcher.findOpeningBracket(text.substring(0, scan + 1), '[', ']');
                } else if (sc == ')') {
                    found2 = BracketMatcher.findOpeningBracket(text.substring(0, scan + 1), '(', ')');
                }
                if (found2 == 0) break;
                return new int[]{scan, scan + found2};
            }
        }
        return null;
    }

    public static List<Integer> findUnmatchedBracePositions(String text) {
        ArrayList<Integer> unmatched = new ArrayList<Integer>();
        if (text == null || text.isEmpty()) {
            return unmatched;
        }
        ArrayList<int[]> stack = new ArrayList<int[]>();
        boolean inLineComment = false;
        boolean inBlockComment = false;
        boolean inString = false;
        boolean escape = false;
        char stringDelimiter = '\u0000';
        for (int pos = 0; pos < text.length(); ++pos) {
            char next;
            char c = text.charAt(pos);
            char c2 = next = pos + 1 < text.length() ? text.charAt(pos + 1) : (char)'\u0000';
            if (inString) {
                if (escape) {
                    escape = false;
                    continue;
                }
                if (c == '\\') {
                    escape = true;
                    continue;
                }
                if (c == stringDelimiter) {
                    inString = false;
                    continue;
                }
                if (c != '\n') continue;
                inString = false;
                continue;
            }
            if (inBlockComment) {
                if (c != '*' || next != '/') continue;
                inBlockComment = false;
                ++pos;
                continue;
            }
            if (inLineComment) {
                if (c != '\n') continue;
                inLineComment = false;
                continue;
            }
            if (c == '/' && next == '/') {
                inLineComment = true;
                ++pos;
                continue;
            }
            if (c == '/' && next == '*') {
                inBlockComment = true;
                ++pos;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringDelimiter = c;
                escape = false;
                continue;
            }
            if (c == '{' || c == '[' || c == '(') {
                stack.add(new int[]{c, pos});
                continue;
            }
            if (c != '}' && c != ']' && c != ')') continue;
            if (!stack.isEmpty()) {
                char expectOpen;
                int[] top = (int[])stack.get(stack.size() - 1);
                char topc = (char)top[0];
                char c3 = c == '}' ? (char)'{' : (expectOpen = c == ']' ? (char)'[' : '(');
                if (topc == expectOpen) {
                    stack.remove(stack.size() - 1);
                    continue;
                }
                unmatched.add(pos);
                continue;
            }
            unmatched.add(pos);
        }
        for (int[] open : stack) {
            unmatched.add(open[1]);
        }
        return unmatched;
    }

    public static int findClosingBracket(String str, char open, char close) {
        int depth = 0;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == open) {
                ++depth;
                continue;
            }
            if (c != close || --depth != 0) continue;
            return i;
        }
        return 0;
    }

    public static int findOpeningBracket(String str, char open, char close) {
        int depth = 0;
        for (int i = str.length() - 1; i >= 0; --i) {
            char c = str.charAt(i);
            if (c == close) {
                ++depth;
                continue;
            }
            if (c != open || --depth != 0) continue;
            return i - str.length() + 1;
        }
        return 0;
    }

    public static List<int[]> computeBraceSpans(String text, List<JavaTextContainer.LineData> lines) {
        ArrayList<int[]> spans = new ArrayList<int[]>();
        if (text == null || text.isEmpty() || lines == null || lines.isEmpty()) {
            return spans;
        }
        int lineIdx = 0;
        int lineEnd = lines.get((int)0).end;
        ArrayList<Integer> openStack = new ArrayList<Integer>();
        boolean inLineComment = false;
        boolean inBlockComment = false;
        boolean inString = false;
        boolean escape = false;
        char stringDelimiter = '\u0000';
        for (int pos = 0; pos < text.length(); ++pos) {
            char next;
            while (lineIdx < lines.size() - 1 && pos >= lineEnd) {
                lineEnd = lines.get((int)(++lineIdx)).end;
            }
            char c = text.charAt(pos);
            char c2 = next = pos + 1 < text.length() ? text.charAt(pos + 1) : (char)'\u0000';
            if (inString) {
                if (escape) {
                    escape = false;
                    continue;
                }
                if (c == '\\') {
                    escape = true;
                    continue;
                }
                if (c == stringDelimiter) {
                    inString = false;
                    continue;
                }
                if (c != '\n') continue;
                inString = false;
                continue;
            }
            if (inBlockComment) {
                if (c != '*' || next != '/') continue;
                inBlockComment = false;
                ++pos;
                continue;
            }
            if (inLineComment) {
                if (c != '\n') continue;
                inLineComment = false;
                continue;
            }
            if (c == '/' && next == '/') {
                inLineComment = true;
                ++pos;
                continue;
            }
            if (c == '/' && next == '*') {
                inBlockComment = true;
                ++pos;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringDelimiter = c;
                escape = false;
                continue;
            }
            if (c == '{') {
                openStack.add(lineIdx);
                continue;
            }
            if (c != '}' || openStack.isEmpty()) continue;
            int openLine = (Integer)openStack.remove(openStack.size() - 1);
            int spanDepth = openStack.size() + 1;
            spans.add(new int[]{spanDepth, openLine, lineIdx});
        }
        if (!openStack.isEmpty()) {
            int baseline = openStack.size();
            ArrayList<int[]> adjusted = new ArrayList<int[]>(spans.size());
            for (int[] span : spans) {
                int adjustedDepth = Math.max(1, span[0] - baseline);
                adjusted.add(new int[]{span[0], span[1], span[2], adjustedDepth});
            }
            return adjusted;
        }
        for (int i = 0; i < spans.size(); ++i) {
            int[] span = (int[])spans.get(i);
            spans.set(i, new int[]{span[0], span[1], span[2], span[0]});
        }
        return spans;
    }

    public static int[] findHighlightedSpan(int bracketPos, List<JavaTextContainer.LineData> lines, List<int[]> spans) {
        if (bracketPos < 0 || lines == null || spans == null) {
            return null;
        }
        int bracketLineIdx = -1;
        for (int i = 0; i < lines.size(); ++i) {
            JavaTextContainer.LineData ld = lines.get(i);
            if (bracketPos < ld.start || bracketPos >= ld.end) continue;
            bracketLineIdx = i;
            break;
        }
        if (bracketLineIdx >= 0) {
            for (int[] span : spans) {
                int openLine = span[1];
                int closeLine = span[2];
                if (bracketLineIdx < openLine || bracketLineIdx > closeLine) continue;
                return new int[]{openLine, closeLine};
            }
        }
        return null;
    }

    public static boolean hasMatchingCloseWithSameIndent(String text, int bracePos, List<JavaTextContainer.LineData> lines, int currentIndent) {
        if (text == null || lines == null || bracePos < 0) {
            return false;
        }
        int openLineIdx = -1;
        for (int i = 0; i < lines.size(); ++i) {
            JavaTextContainer.LineData ld = lines.get(i);
            if (bracePos < ld.start || bracePos >= ld.end) continue;
            openLineIdx = i;
            break;
        }
        if (openLineIdx < 0) {
            return false;
        }
        List<int[]> spans = BracketMatcher.computeBraceSpans(text, lines);
        for (int[] span : spans) {
            int closeLineIdx;
            if (span[1] != openLineIdx || (closeLineIdx = span[2]) >= lines.size()) continue;
            String closeLine = lines.get((int)closeLineIdx).text;
            int closeIndent = BracketMatcher.getLineIndent(closeLine);
            return closeIndent <= currentIndent;
        }
        return false;
    }

    private static int getLineIndent(String line) {
        char c;
        int indent = 0;
        for (int i = 0; i < line.length() && ((c = line.charAt(i)) == ' ' || c == '\t'); ++i) {
            ++indent;
        }
        return indent;
    }
}

