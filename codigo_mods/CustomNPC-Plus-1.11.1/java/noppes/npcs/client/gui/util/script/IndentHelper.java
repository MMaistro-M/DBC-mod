/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.Arrays;
import java.util.List;
import noppes.npcs.client.gui.util.script.FormatHelper;
import noppes.npcs.client.gui.util.script.JavaTextContainer;

public class IndentHelper {
    public static final int TAB_SIZE = 4;

    public static int getLineIndent(String lineText) {
        char c;
        int leading = 0;
        for (int i = 0; i < lineText.length() && ((c = lineText.charAt(i)) == ' ' || c == '\t'); ++i) {
            ++leading;
        }
        return leading;
    }

    public static String getIndentString(String lineText) {
        int indent = IndentHelper.getLineIndent(lineText);
        return lineText.substring(0, indent);
    }

    public static String spaces(int n) {
        if (n <= 0) {
            return "";
        }
        char[] arr = new char[n];
        Arrays.fill(arr, ' ');
        return new String(arr);
    }

    public static int getExpectedIndent(JavaTextContainer.LineData currentLine, List<JavaTextContainer.LineData> allLines) {
        if (currentLine == null || allLines == null) {
            return 0;
        }
        int idx = allLines.indexOf(currentLine);
        if (idx <= 0) {
            return 0;
        }
        JavaTextContainer.LineData prevLine = null;
        for (int i = idx - 1; i >= 0; --i) {
            JavaTextContainer.LineData line = allLines.get(i);
            if (line.text.trim().length() <= 0) continue;
            prevLine = line;
            break;
        }
        if (prevLine == null) {
            return 0;
        }
        int prevIndent = IndentHelper.getLineIndent(prevLine.text);
        String prevTrimmed = prevLine.text.trim();
        String currTrimmed = currentLine.text.trim();
        if (currTrimmed.startsWith("}")) {
            return Math.max(0, prevIndent - 4);
        }
        if (prevTrimmed.endsWith("{")) {
            return prevIndent + 4;
        }
        return prevIndent;
    }

    public static String getAutoIndentForEnter(String lineText, int cursorInLine) {
        boolean opensBlock;
        int leading;
        for (leading = 0; leading < lineText.length() && (lineText.charAt(leading) == ' ' || lineText.charAt(leading) == '\t'); ++leading) {
        }
        String baseIndent = lineText.substring(0, leading);
        int relativeCursor = Math.max(0, Math.min(cursorInLine, lineText.length()));
        String beforeCursor = lineText.substring(0, relativeCursor);
        int lastNonWs = -1;
        for (int i = beforeCursor.length() - 1; i >= 0; --i) {
            if (Character.isWhitespace(beforeCursor.charAt(i))) continue;
            lastNonWs = i;
            break;
        }
        boolean bl = opensBlock = lastNonWs >= 0 && lastNonWs >= leading && beforeCursor.charAt(lastNonWs) == '{';
        if (opensBlock) {
            return baseIndent + IndentHelper.spaces(4);
        }
        return baseIndent;
    }

    public static int calculateTabTargetIndent(int currentIndent, int cursorColumn, boolean atTextStart) {
        if (atTextStart) {
            return (currentIndent / 4 + 1) * 4;
        }
        int remainder = currentIndent % 4;
        if (remainder == 0) {
            return currentIndent + 4;
        }
        int down = currentIndent - remainder;
        int up = currentIndent + (4 - remainder);
        int distUp = 4 - remainder;
        int distDown = remainder;
        if (distUp <= distDown) {
            return up;
        }
        return down;
    }

    public static int calculateShiftTabTargetIndent(int currentIndent) {
        return Math.max(0, (currentIndent - 1) / 4 * 4);
    }

    public static FormatResult formatText(String text, int cursorPosition) {
        return IndentHelper.formatText(text, cursorPosition, 0);
    }

    public static FormatResult formatText(String text, int cursorPosition, int maxLineLength) {
        String indented = IndentHelper.formatIndentation(text, cursorPosition);
        FormatHelper helper = new FormatHelper();
        FormatHelper.FormatSettings settings = helper.getSettings();
        if (maxLineLength > 0) {
            settings.wrapLongLines = true;
            settings.wrapComments = true;
            settings.maxLineLength = maxLineLength;
        }
        String formatted = helper.format(indented);
        if (maxLineLength > 0 && settings.wrapLongLines) {
            formatted = helper.wrapLines(formatted, settings.maxLineLength);
        }
        int newCursorPos = Math.min(cursorPosition, formatted.length());
        String[] origLines = text.split("\n", -1);
        String[] newLines = formatted.split("\n", -1);
        int lineStartPos = 0;
        int cursorLine = -1;
        int cursorColInLine = 0;
        for (int li = 0; li < origLines.length; ++li) {
            int lineEndPos = lineStartPos + origLines[li].length();
            if (cursorPosition >= lineStartPos && cursorPosition <= lineEndPos) {
                cursorLine = li;
                cursorColInLine = cursorPosition - lineStartPos;
                break;
            }
            lineStartPos = lineEndPos + 1;
        }
        if (cursorLine >= 0 && cursorLine < newLines.length) {
            int newLineStart = 0;
            for (int li = 0; li < cursorLine; ++li) {
                newLineStart += newLines[li].length() + 1;
            }
            newCursorPos = newLineStart + Math.min(cursorColInLine, newLines[cursorLine].length());
        }
        return new FormatResult(formatted, newCursorPos);
    }

    private static String formatIndentation(String text, int cursorPosition) {
        String[] linesArr = text.split("\n", -1);
        StringBuilder out = new StringBuilder(text.length() + 32);
        boolean inString = false;
        boolean escape = false;
        boolean inBlockComment = false;
        int depth = 0;
        for (int li = 0; li < linesArr.length; ++li) {
            String line = linesArr[li];
            String trimmedLeading = line.replaceAll("^[ \\t]+", "");
            int originalIndent = IndentHelper.getLineIndent(line);
            int opens = 0;
            int closes = 0;
            boolean startsWithClose = false;
            for (int idx = 0; idx < line.length(); ++idx) {
                char next;
                char c = line.charAt(idx);
                char c2 = next = idx + 1 < line.length() ? line.charAt(idx + 1) : (char)'\u0000';
                if (inString) {
                    if (escape) {
                        escape = false;
                        continue;
                    }
                    if (c == '\\') {
                        escape = true;
                        continue;
                    }
                    if (c != '\"') continue;
                    inString = false;
                    continue;
                }
                if (inBlockComment) {
                    if (c != '*' || next != '/') continue;
                    inBlockComment = false;
                    ++idx;
                    continue;
                }
                if (c == '/' && next == '/') break;
                if (c == '/' && next == '*') {
                    inBlockComment = true;
                    ++idx;
                    continue;
                }
                if (c == '\"') {
                    inString = true;
                    escape = false;
                    continue;
                }
                if (c == '{') {
                    ++opens;
                    continue;
                }
                if (c != '}') continue;
                ++closes;
                if (startsWithClose || !line.substring(0, idx).trim().isEmpty()) continue;
                startsWithClose = true;
            }
            int indentLevel = depth;
            if (startsWithClose) {
                indentLevel = Math.max(0, indentLevel - 1);
            }
            int targetIndent = indentLevel * 4;
            boolean isContinuation = IndentHelper.isContinuationLine(trimmedLeading);
            if (!isContinuation && li > 0) {
                String prevLine = linesArr[li - 1].trim();
                isContinuation = IndentHelper.lineEndsWithContinuationOperator(prevLine);
            }
            if (isContinuation && originalIndent > targetIndent) {
                out.append(IndentHelper.spaces(originalIndent)).append(trimmedLeading);
            } else {
                out.append(IndentHelper.spaces(targetIndent)).append(trimmedLeading);
            }
            if (li < linesArr.length - 1) {
                out.append('\n');
            }
            depth = Math.max(0, depth + opens - closes);
        }
        return out.toString();
    }

    private static boolean isContinuationLine(String trimmedContent) {
        if (trimmedContent.isEmpty()) {
            return false;
        }
        char firstChar = trimmedContent.charAt(0);
        if (firstChar == '.' || firstChar == '+' || firstChar == '-' || firstChar == '*' || firstChar == '/' || firstChar == '%' || firstChar == '&' || firstChar == '|' || firstChar == '^' || firstChar == '?' || firstChar == ':' || firstChar == ',') {
            return true;
        }
        return trimmedContent.startsWith("&&") || trimmedContent.startsWith("||");
    }

    private static boolean lineEndsWithContinuationOperator(String trimmedLine) {
        if (trimmedLine.isEmpty()) {
            return false;
        }
        int commentIdx = trimmedLine.indexOf("//");
        if (commentIdx >= 0) {
            trimmedLine = trimmedLine.substring(0, commentIdx).trim();
        }
        if (trimmedLine.isEmpty()) {
            return false;
        }
        char lastChar = trimmedLine.charAt(trimmedLine.length() - 1);
        if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/' || lastChar == '%' || lastChar == '=' || lastChar == '&' || lastChar == '|' || lastChar == '^' || lastChar == '?' || lastChar == ':' || lastChar == ',' || lastChar == '(' || lastChar == '[') {
            return true;
        }
        return trimmedLine.endsWith("&&") || trimmedLine.endsWith("||");
    }

    public static class FormatResult {
        public final String text;
        public final int cursorPosition;

        public FormatResult(String text, int cursorPosition) {
            this.text = text;
            this.cursorPosition = cursorPosition;
        }
    }
}

