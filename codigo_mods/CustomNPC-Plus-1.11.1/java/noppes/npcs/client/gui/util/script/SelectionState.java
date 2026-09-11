/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import noppes.npcs.client.gui.util.script.JavaTextContainer;

public class SelectionState {
    private int startSelection = 0;
    private int endSelection = 0;
    private int cursorPosition = 0;
    private long lastInputTime = 0L;

    public void reset(int position) {
        this.endSelection = this.cursorPosition = position;
        this.startSelection = this.cursorPosition;
    }

    public void clamp(int textLength) {
        if (textLength <= 0) {
            this.cursorPosition = 0;
            this.endSelection = 0;
            this.startSelection = 0;
            return;
        }
        this.startSelection = Math.max(0, Math.min(this.startSelection, textLength));
        this.endSelection = Math.max(0, Math.min(this.endSelection, textLength));
        this.cursorPosition = Math.max(0, Math.min(this.cursorPosition, textLength));
    }

    public void setCursor(int position, int textLength, boolean extendSelection) {
        if ((position = Math.max(0, Math.min(position, textLength))) != this.cursorPosition) {
            if (!extendSelection) {
                this.startSelection = this.cursorPosition = position;
                this.endSelection = this.cursorPosition;
            } else {
                int diff = this.cursorPosition - position;
                if (this.cursorPosition == this.startSelection) {
                    this.startSelection -= diff;
                } else if (this.cursorPosition == this.endSelection) {
                    this.endSelection -= diff;
                }
                if (this.startSelection > this.endSelection) {
                    int temp = this.endSelection;
                    this.endSelection = this.startSelection;
                    this.startSelection = temp;
                }
                this.cursorPosition = position;
            }
            this.clamp(textLength);
            this.markActivity();
        }
    }

    public void setSelection(int start, int end) {
        this.startSelection = Math.min(start, end);
        this.endSelection = Math.max(start, end);
    }

    public void selectAll(int textLength) {
        this.cursorPosition = 0;
        this.startSelection = 0;
        this.endSelection = textLength;
    }

    public boolean hasSelection() {
        return this.startSelection != this.endSelection;
    }

    public String getSelectedText(String text) {
        if (!this.hasSelection() || text == null) {
            return "";
        }
        int start = Math.max(0, Math.min(this.startSelection, text.length()));
        int end = Math.max(0, Math.min(this.endSelection, text.length()));
        return text.substring(start, end);
    }

    public String getTextBefore(String text) {
        if (text == null || this.startSelection <= 0) {
            return "";
        }
        return text.substring(0, Math.min(this.startSelection, text.length()));
    }

    public String getTextAfter(String text) {
        if (text == null) {
            return "";
        }
        int pos = Math.min(this.endSelection, text.length());
        return text.substring(pos);
    }

    public int getCursorLineIndex(List<JavaTextContainer.LineData> lines, int textLength) {
        if (lines == null || lines.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < lines.size(); ++i) {
            boolean isOnLine;
            boolean isLastLine;
            JavaTextContainer.LineData ld = lines.get(i);
            boolean bl = isLastLine = i == lines.size() - 1;
            boolean bl2 = isLastLine ? this.cursorPosition >= ld.start && this.cursorPosition <= ld.end : (isOnLine = this.cursorPosition >= ld.start && this.cursorPosition < ld.end);
            if (!isOnLine) continue;
            return i;
        }
        return lines.size() - 1;
    }

    public boolean isCursorOnLine(int lineIndex, JavaTextContainer.LineData line, int totalLines) {
        boolean isLastLine;
        boolean bl = isLastLine = lineIndex == totalLines - 1;
        return isLastLine ? this.cursorPosition >= line.start && this.cursorPosition <= line.end : this.cursorPosition >= line.start && this.cursorPosition < line.end;
    }

    public JavaTextContainer.LineData findCurrentLine(List<JavaTextContainer.LineData> lines) {
        if (lines == null) {
            return null;
        }
        for (int i = 0; i < lines.size(); ++i) {
            JavaTextContainer.LineData line = lines.get(i);
            if (!this.isCursorOnLine(i, line, lines.size())) continue;
            return line;
        }
        return null;
    }

    public void markActivity() {
        this.lastInputTime = System.currentTimeMillis();
    }

    public boolean hadRecentInput() {
        return System.currentTimeMillis() - this.lastInputTime < 500L;
    }

    public int getStartSelection() {
        return this.startSelection;
    }

    public int getEndSelection() {
        return this.endSelection;
    }

    public int getCursorPosition() {
        return this.cursorPosition;
    }

    public long getLastInputTime() {
        return this.lastInputTime;
    }

    public void setStartSelection(int pos) {
        this.startSelection = pos;
    }

    public void setEndSelection(int pos) {
        this.endSelection = pos;
    }

    public void setCursorPositionDirect(int pos) {
        this.cursorPosition = pos;
    }

    public void afterTextInsert(int newPosition) {
        this.startSelection = this.cursorPosition = newPosition;
        this.endSelection = this.cursorPosition;
        this.markActivity();
    }

    public void selectWordAtCursor(String text, Pattern wordPattern) {
        if (text == null || wordPattern == null) {
            return;
        }
        Matcher m = wordPattern.matcher(text);
        while (m.find()) {
            if (this.cursorPosition < m.start() || this.cursorPosition > m.end()) continue;
            this.setSelection(m.start(), m.end());
            return;
        }
    }

    public void selectLineAtCursor(List<JavaTextContainer.LineData> lines) {
        if (lines == null) {
            return;
        }
        for (JavaTextContainer.LineData line : lines) {
            if (!this.isCursorOnLine(lines.indexOf(line), line, lines.size())) continue;
            this.setSelection(line.start, line.end);
            return;
        }
    }
}

