/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script;

import java.util.List;
import noppes.npcs.client.gui.util.script.JavaTextContainer;

public class CommentHandler {
    public static ToggleResult toggleLine(String lineText) {
        String newText;
        boolean hasComment;
        int nonWs;
        for (nonWs = 0; nonWs < lineText.length() && Character.isWhitespace(lineText.charAt(nonWs)); ++nonWs) {
        }
        boolean hasContent = nonWs < lineText.length();
        boolean bl = hasComment = hasContent && lineText.startsWith("//", nonWs);
        if (hasComment) {
            int cut = Math.min(nonWs + 2, lineText.length());
            newText = lineText.substring(0, nonWs) + lineText.substring(cut);
        } else {
            newText = hasContent ? lineText.substring(0, nonWs) + "//" + lineText.substring(nonWs) : lineText;
        }
        return new ToggleResult(newText, newText.length() - lineText.length(), nonWs);
    }

    public static SelectionToggleResult toggleCommentSelection(String text, List<JavaTextContainer.LineData> lines, int startSelection, int endSelection) {
        StringBuilder newText = new StringBuilder(text.length() + 100);
        int prevEnd = 0;
        int newStart = -1;
        int newEnd = -1;
        int newIndex = 0;
        for (JavaTextContainer.LineData line : lines) {
            int safeLineStart = Math.max(0, Math.min(line.start, text.length()));
            int safeLineEnd = Math.max(0, Math.min(line.end, text.length()));
            if (safeLineStart > prevEnd) {
                if (newStart == -1 && startSelection >= prevEnd && startSelection <= safeLineStart) {
                    newStart = newIndex + (startSelection - prevEnd);
                }
                if (newEnd == -1 && endSelection >= prevEnd && endSelection <= safeLineStart) {
                    newEnd = newIndex + (endSelection - prevEnd);
                }
                newText.append(text, prevEnd, safeLineStart);
                newIndex += safeLineStart - prevEnd;
            }
            if (safeLineEnd > startSelection && safeLineStart < endSelection) {
                int newOffset;
                int offsetInOld;
                String lineText = text.substring(safeLineStart, safeLineEnd);
                ToggleResult tr = CommentHandler.toggleLine(lineText);
                String newLineText = tr.text;
                int nonWs = tr.nonWsIndex;
                int delta = tr.delta;
                if (newStart == -1 && startSelection >= safeLineStart && startSelection <= safeLineEnd) {
                    newOffset = offsetInOld = startSelection - safeLineStart;
                    if (offsetInOld >= nonWs) {
                        newOffset = offsetInOld + delta;
                    }
                    newStart = newIndex + Math.max(0, newOffset);
                }
                if (newEnd == -1 && endSelection >= safeLineStart && endSelection <= safeLineEnd) {
                    newOffset = offsetInOld = endSelection - safeLineStart;
                    if (offsetInOld >= nonWs) {
                        newOffset = offsetInOld + delta;
                    }
                    newEnd = newIndex + Math.max(0, newOffset);
                }
                newText.append(newLineText);
                newIndex += newLineText.length();
            } else {
                if (newStart == -1 && startSelection >= safeLineStart && startSelection <= safeLineEnd) {
                    newStart = newIndex + (startSelection - safeLineStart);
                }
                if (newEnd == -1 && endSelection >= safeLineStart && endSelection <= safeLineEnd) {
                    newEnd = newIndex + (endSelection - safeLineStart);
                }
                newText.append(text, safeLineStart, safeLineEnd);
                newIndex += safeLineEnd - safeLineStart;
            }
            prevEnd = safeLineEnd;
        }
        if (prevEnd < text.length()) {
            if (newStart == -1 && startSelection >= prevEnd && startSelection <= text.length()) {
                newStart = newIndex + (startSelection - prevEnd);
            }
            if (newEnd == -1 && endSelection >= prevEnd && endSelection <= text.length()) {
                newEnd = newIndex + (endSelection - prevEnd);
            }
            newText.append(text, prevEnd, text.length());
            newIndex += text.length() - prevEnd;
        }
        if (newStart == -1) {
            newStart = Math.max(0, Math.min(startSelection, newText.length()));
        }
        if (newEnd == -1) {
            newEnd = Math.max(0, Math.min(endSelection, newText.length()));
        }
        return new SelectionToggleResult(newText.toString(), newStart, newEnd);
    }

    public static SingleLineToggleResult toggleCommentAtCursor(String text, List<JavaTextContainer.LineData> lines, int cursorPosition) {
        for (JavaTextContainer.LineData line : lines) {
            int lineStart = Math.max(0, Math.min(line.start, text.length()));
            int lineEnd = Math.max(0, Math.min(line.end, text.length()));
            if (cursorPosition < lineStart || cursorPosition > lineEnd) continue;
            String lineText = text.substring(lineStart, lineEnd);
            ToggleResult tr = CommentHandler.toggleLine(lineText);
            boolean hadComment = tr.delta < 0;
            String before = text.substring(0, lineStart);
            String after = lineEnd <= text.length() ? text.substring(lineEnd) : "";
            String newText = before + tr.text + after;
            int cursorDelta = hadComment ? -2 : 2;
            int newCursor = cursorPosition + cursorDelta;
            if (cursorPosition < lineStart + tr.nonWsIndex + (hadComment ? 2 : 0)) {
                newCursor = cursorPosition;
            }
            newCursor = Math.max(lineStart, newCursor);
            return new SingleLineToggleResult(newText, newCursor);
        }
        return new SingleLineToggleResult(text, cursorPosition);
    }

    public static class SingleLineToggleResult {
        public final String newText;
        public final int newCursorPosition;

        public SingleLineToggleResult(String text, int cursor) {
            this.newText = text;
            this.newCursorPosition = cursor;
        }
    }

    public static class SelectionToggleResult {
        public final String newText;
        public final int newStartSelection;
        public final int newEndSelection;

        public SelectionToggleResult(String text, int start, int end) {
            this.newText = text;
            this.newStartSelection = start;
            this.newEndSelection = end;
        }
    }

    public static class ToggleResult {
        public final String text;
        public final int delta;
        public final int nonWsIndex;

        public ToggleResult(String text, int delta, int nonWsIndex) {
            this.text = text;
            this.delta = delta;
            this.nonWsIndex = nonWsIndex;
        }

        public boolean wasCommented() {
            return this.delta < 0;
        }
    }
}

