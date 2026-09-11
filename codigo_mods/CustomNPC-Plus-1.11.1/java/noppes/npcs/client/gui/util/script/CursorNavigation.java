/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import noppes.npcs.client.gui.util.script.JavaTextContainer;

public class CursorNavigation {
    public static int cursorUp(int cursorPosition, List<JavaTextContainer.LineData> lines, String text) {
        if (lines == null || lines.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < lines.size(); ++i) {
            boolean isOnLine;
            boolean isLastLine;
            JavaTextContainer.LineData data = lines.get(i);
            boolean bl = isLastLine = i == lines.size() - 1;
            boolean bl2 = isLastLine ? cursorPosition >= data.start && cursorPosition <= data.end : (isOnLine = cursorPosition >= data.start && cursorPosition < data.end);
            if (!isOnLine) continue;
            if (i == 0) {
                return 0;
            }
            int column = Math.min(cursorPosition - data.start, data.text.length());
            JavaTextContainer.LineData target = lines.get(i - 1);
            int targetColumn = Math.min(column, target.text.length());
            int targetIndent = CursorNavigation.getLineIndent(target.text);
            int minPos = target.start + Math.min(targetIndent, target.text.length());
            int targetPos = target.start + targetColumn;
            return Math.max(minPos, targetPos);
        }
        return 0;
    }

    public static int cursorDown(int cursorPosition, List<JavaTextContainer.LineData> lines, String text) {
        if (lines == null || lines.isEmpty()) {
            return text != null ? text.length() : 0;
        }
        if (text != null && cursorPosition == text.length()) {
            return cursorPosition;
        }
        for (int i = 0; i < lines.size(); ++i) {
            boolean isOnLine;
            boolean isLastLine;
            JavaTextContainer.LineData data = lines.get(i);
            boolean bl = isLastLine = i == lines.size() - 1;
            boolean bl2 = isLastLine ? cursorPosition >= data.start && cursorPosition <= data.end : (isOnLine = cursorPosition >= data.start && cursorPosition < data.end);
            if (!isOnLine) continue;
            if (i >= lines.size() - 1) {
                return cursorPosition;
            }
            int column = Math.min(cursorPosition - data.start, data.text.length());
            JavaTextContainer.LineData target = lines.get(i + 1);
            int targetColumn = Math.min(column, target.text.length());
            int targetIndent = CursorNavigation.getLineIndent(target.text);
            int minPos = target.start + Math.min(targetIndent, target.text.length());
            int targetPos = target.start + targetColumn;
            return Math.max(minPos, targetPos);
        }
        return text != null ? text.length() : 0;
    }

    public static int cursorLeft(int cursorPosition, String text, Pattern wordPattern, boolean byWord) {
        if (text == null || cursorPosition <= 0) {
            return 0;
        }
        int moveAmount = 1;
        if (byWord && wordPattern != null) {
            Matcher m = wordPattern.matcher(text.substring(0, cursorPosition));
            while (m.find()) {
                if (m.start() == m.end()) continue;
                moveAmount = cursorPosition - m.start();
            }
        }
        return Math.max(cursorPosition - moveAmount, 0);
    }

    public static int cursorRight(int cursorPosition, String text, Pattern wordPattern, boolean byWord) {
        if (text == null) {
            return 0;
        }
        int textLen = text.length();
        if (cursorPosition >= textLen) {
            return textLen;
        }
        int moveAmount = 1;
        if (byWord && wordPattern != null) {
            Matcher m = wordPattern.matcher(text.substring(cursorPosition));
            if (m.find() && m.start() > 0) {
                moveAmount = m.start();
            } else if (m.find()) {
                moveAmount = m.start();
            }
        }
        return Math.min(cursorPosition + moveAmount, textLen);
    }

    public static int getPositionFromMouse(int mouseX, int mouseY, int areaX, int areaY, int gutterWidth, int lineHeight, int scrolledLine, double scrollFracOffset, List<JavaTextContainer.LineData> lines, String text, FontWidthProvider fontWidth) {
        int xMouse = mouseX - (areaX + gutterWidth + 1);
        int yMouse = mouseY - areaY - 1;
        double fracPixels = scrollFracOffset * (double)lineHeight;
        yMouse = (int)Math.round((double)yMouse + fracPixels);
        if (lines == null || lines.isEmpty()) {
            return text != null ? text.length() : 0;
        }
        int visibleLines = Math.min(lines.size(), scrolledLine + 50);
        for (int i = 0; i < lines.size(); ++i) {
            int yPos;
            JavaTextContainer.LineData data = lines.get(i);
            if (i < scrolledLine || i > visibleLines || yMouse < (yPos = (i - scrolledLine) * lineHeight) || yMouse >= yPos + lineHeight) continue;
            int lineWidth = 0;
            char[] chars = data.text.toCharArray();
            for (int j = 1; j <= chars.length; ++j) {
                int w = fontWidth.getWidth(data.text.substring(0, j));
                if (xMouse < lineWidth + (w - lineWidth) / 2) {
                    return data.start + j - 1;
                }
                lineWidth = w;
            }
            int posAfterChars = data.start + chars.length;
            return Math.min(posAfterChars, text.length());
        }
        return text != null ? text.length() : 0;
    }

    private static int getLineIndent(String lineText) {
        char c;
        int indent = 0;
        for (int i = 0; i < lineText.length() && ((c = lineText.charAt(i)) == ' ' || c == '\t'); ++i) {
            ++indent;
        }
        return indent;
    }

    public static int findLineIndex(int position, List<JavaTextContainer.LineData> lines) {
        if (lines == null || lines.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < lines.size(); ++i) {
            boolean isOnLine;
            boolean isLastLine;
            JavaTextContainer.LineData line = lines.get(i);
            boolean bl = isLastLine = i == lines.size() - 1;
            boolean bl2 = isLastLine ? position >= line.start && position <= line.end : (isOnLine = position >= line.start && position < line.end);
            if (!isOnLine) continue;
            return i;
        }
        return lines.size() - 1;
    }

    public static JavaTextContainer.LineData findLineAt(int position, List<JavaTextContainer.LineData> lines) {
        int idx = CursorNavigation.findLineIndex(position, lines);
        if (idx >= 0 && idx < lines.size()) {
            return lines.get(idx);
        }
        return null;
    }

    public static interface FontWidthProvider {
        public int getWidth(String var1);
    }
}

