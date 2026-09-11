/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.util.script.interpreter.ErrorUnderlineRenderer;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldAccessInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.TypeParamInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodCallInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.Token;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenErrorMessage;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.ImportData;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class ScriptLine {
    private static final char COLOR_CHAR = '\u00a7';
    private final String text;
    public final int globalStart;
    public final int globalEnd;
    public final int lineIndex;
    private final List<Token> tokens = new ArrayList<Token>();
    private final List<Integer> indentGuides = new ArrayList<Integer>();
    private ScriptLine prev;
    private ScriptLine next;
    private ScriptDocument parent;

    public ScriptLine(String text, int globalStart, int globalEnd, int lineIndex) {
        this.text = text;
        this.globalStart = globalStart;
        this.globalEnd = globalEnd;
        this.lineIndex = lineIndex;
    }

    public String getText() {
        return this.text;
    }

    public int getGlobalStart() {
        return this.globalStart;
    }

    public int getGlobalEnd() {
        return this.globalEnd;
    }

    public int getLineIndex() {
        return this.lineIndex;
    }

    public int getLength() {
        return this.text.length();
    }

    public List<Token> getTokens() {
        return Collections.unmodifiableList(this.tokens);
    }

    public List<Integer> getIndentGuides() {
        return Collections.unmodifiableList(this.indentGuides);
    }

    public ScriptDocument getParent() {
        return this.parent;
    }

    void setParent(ScriptDocument parent) {
        this.parent = parent;
    }

    void setPrev(ScriptLine prev) {
        this.prev = prev;
    }

    void setNext(ScriptLine next) {
        this.next = next;
    }

    public ScriptLine prev() {
        return this.prev;
    }

    public ScriptLine next() {
        return this.next;
    }

    public Token getFirstToken() {
        return this.tokens.isEmpty() ? null : this.tokens.get(0);
    }

    public Token getLastToken() {
        return this.tokens.isEmpty() ? null : this.tokens.get(this.tokens.size() - 1);
    }

    public Token getTokenAt(int globalPosition) {
        for (Token t : this.tokens) {
            if (globalPosition < t.getGlobalStart() || globalPosition > t.getGlobalEnd()) continue;
            return t;
        }
        return null;
    }

    public Token getTokenAt(int globalPosition, Predicate<Token> condition) {
        for (Token t : this.tokens) {
            if (globalPosition < t.getGlobalStart() || globalPosition > t.getGlobalEnd() || !condition.test(t)) continue;
            return t;
        }
        return null;
    }

    public void clearTokens() {
        this.tokens.clear();
    }

    public void addToken(Token token) {
        if (!this.tokens.isEmpty()) {
            Token last = this.tokens.get(this.tokens.size() - 1);
            last.setNext(token);
            token.setPrev(last);
        }
        token.setParentLine(this);
        this.tokens.add(token);
    }

    public void buildTokensFromMarks(List<Mark> marks, String fullText, ScriptDocument document) {
        this.clearTokens();
        int cursor = this.globalStart;
        int displayEnd = Math.min(this.globalStart + this.text.length(), fullText.length());
        for (Mark mark : marks) {
            if (mark.end <= this.globalStart || mark.start >= displayEnd) continue;
            int tokenStart = Math.max(mark.start, this.globalStart);
            int tokenEnd = Math.min(mark.end, displayEnd);
            tokenStart = Math.max(0, Math.min(tokenStart, fullText.length()));
            tokenEnd = Math.max(0, Math.min(tokenEnd, fullText.length()));
            if (cursor < tokenStart) {
                int gapEnd = Math.min(tokenStart, fullText.length());
                String gapText = fullText.substring(cursor, gapEnd);
                this.addToken(Token.defaultToken(gapText, cursor, gapEnd));
            }
            if (tokenStart < tokenEnd) {
                String tokenText = fullText.substring(tokenStart, tokenEnd);
                Token token = new Token(tokenText, tokenStart, tokenEnd, mark.type);
                if (mark.metadata != null) {
                    this.applyTokenMetadata(token, mark.metadata);
                }
                this.addToken(token);
            }
            cursor = tokenEnd;
        }
        if (cursor < displayEnd) {
            String trailingText = fullText.substring(cursor, displayEnd);
            this.addToken(Token.defaultToken(trailingText, cursor, displayEnd));
        }
    }

    public void applyTokenMetadata(Token token, Object metadata) {
        if (metadata instanceof TypeInfo) {
            token.setTypeInfo((TypeInfo)metadata);
        } else if (metadata instanceof TypeParamInfo) {
            TypeParamInfo typeParam = (TypeParamInfo)metadata;
            token.setTypeInfo(TypeInfo.typeParameter(typeParam.getName(), typeParam));
        } else if (metadata instanceof MethodCallInfo) {
            MethodCallInfo callInfo = (MethodCallInfo)metadata;
            if (callInfo.isConstructor()) {
                token.setTypeInfo(callInfo.getReceiverType());
                token.setMethodInfo(callInfo.getResolvedMethod());
            }
            token.setMethodCallInfo(callInfo);
        } else if (metadata instanceof FieldInfo.ArgInfo) {
            FieldInfo.ArgInfo ctx = (FieldInfo.ArgInfo)metadata;
            token.setFieldInfo(ctx.fieldInfo);
            token.setMethodCallInfo(ctx.methodCallInfo);
        } else if (metadata instanceof FieldInfo) {
            token.setFieldInfo((FieldInfo)metadata);
        } else if (metadata instanceof MethodInfo) {
            token.setMethodInfo((MethodInfo)metadata);
        } else if (metadata instanceof ImportData) {
            token.setImportData((ImportData)metadata);
        } else if (metadata instanceof FieldAccessInfo) {
            FieldAccessInfo accessInfo = (FieldAccessInfo)metadata;
            token.setFieldAccessInfo(accessInfo);
            token.setFieldInfo(accessInfo.getResolvedField());
        } else if (metadata instanceof TokenErrorMessage) {
            token.setErrorMessage((TokenErrorMessage)metadata);
        }
    }

    public void clearIndentGuides() {
        this.indentGuides.clear();
    }

    public void addIndentGuide(int column) {
        if (!this.indentGuides.contains(column)) {
            this.indentGuides.add(column);
        }
    }

    public int getRenderedWidth(int localStart, int localEnd) {
        if ((localStart = Math.max(0, Math.min(localStart, this.text.length()))) >= (localEnd = Math.max(localStart, Math.min(localEnd, this.text.length())))) {
            return 0;
        }
        int width = 0;
        int cursor = localStart;
        for (Token t : this.tokens) {
            int gapEnd;
            if (cursor >= localEnd) break;
            int tokenLocalStart = t.getGlobalStart() - this.globalStart;
            int tokenLocalEnd = tokenLocalStart + t.getText().length();
            if (cursor < tokenLocalStart && (gapEnd = Math.min(tokenLocalStart, localEnd)) > cursor) {
                width += ClientProxy.Font.width(this.text.substring(cursor, gapEnd));
                cursor = gapEnd;
            }
            if (cursor >= localEnd) break;
            int overlapStart = Math.max(cursor, tokenLocalStart);
            int overlapEnd = Math.min(tokenLocalEnd, localEnd);
            if (overlapEnd <= overlapStart) continue;
            String substr = this.text.substring(overlapStart, overlapEnd);
            width += ClientProxy.Font.width(substr, t.getFontStyle());
            cursor = overlapEnd;
        }
        if (cursor < localEnd) {
            width += ClientProxy.Font.width(this.text.substring(cursor, localEnd));
        }
        return width;
    }

    public void drawString(int x, int y, int defaultColor) {
        StringBuilder builder = new StringBuilder();
        int lastIndex = 0;
        int currentX = x;
        for (Token t : this.tokens) {
            int tokenStart = t.getGlobalStart() - this.globalStart;
            int tokenWidth = ClientProxy.Font.width(t.getText(), t.getFontStyle());
            if (tokenStart > lastIndex && tokenStart <= this.text.length()) {
                String gapText = this.text.substring(lastIndex, tokenStart);
                builder.append(gapText);
                currentX += ClientProxy.Font.width(gapText);
            }
            String stylePrefix = t.getStylePrefix();
            builder.append('\u00a7').append(t.getColorCode()).append(stylePrefix).append(t.getText()).append('\u00a7').append('r');
            currentX += tokenWidth;
            lastIndex = tokenStart + t.getText().length();
        }
        if (lastIndex < this.text.length()) {
            builder.append(this.text.substring(lastIndex));
        }
        ClientProxy.Font.drawString(builder.toString(), x, y, defaultColor);
        this.drawErrorUnderlines(x, y + ClientProxy.Font.height() - 1);
    }

    public void drawStringHex(int x, int y) {
        int lastIndex = 0;
        ArrayList<TextSegment> segments = new ArrayList<TextSegment>();
        for (Token t : this.tokens) {
            int tokenStart = t.getGlobalStart() - this.globalStart;
            if (tokenStart > lastIndex && tokenStart <= this.text.length()) {
                String gap = this.text.substring(lastIndex, tokenStart);
                segments.add(new TextSegment(lastIndex, gap, -1, false));
            }
            String styledText = t.getStylePrefix() + t.getText();
            segments.add(new TextSegment(tokenStart, styledText, t.getHexColor(), true));
            lastIndex = tokenStart + t.getText().length();
        }
        if (lastIndex < this.text.length()) {
            String remaining = this.text.substring(lastIndex);
            segments.add(new TextSegment(lastIndex, remaining, -1, false));
        }
        for (TextSegment seg : segments) {
            if (seg.text.isEmpty()) continue;
            int prefixWidth = this.getRenderedWidth(0, seg.startPos);
            int color = seg.color;
            ClientProxy.Font.drawString(seg.text, x + prefixWidth, y, color);
        }
        this.drawErrorUnderlines(x, y + ClientProxy.Font.height() - 1);
    }

    private void drawErrorUnderlines(int lineStartX, int baselineY) {
        if (this.parent == null) {
            return;
        }
        ErrorUnderlineRenderer.drawErrorUnderlines(this.parent, this, lineStartX, baselineY, this.getText(), this.getGlobalStart(), this.getGlobalEnd());
    }

    public boolean containsPosition(int globalPosition) {
        return globalPosition >= this.globalStart && globalPosition < this.globalEnd;
    }

    public int toColumn(int globalPosition) {
        return Math.max(0, Math.min(globalPosition - this.globalStart, this.text.length()));
    }

    public int toGlobal(int column) {
        return this.globalStart + Math.max(0, Math.min(column, this.text.length()));
    }

    public String toString() {
        return "ScriptLine{" + this.lineIndex + ": '" + this.text + "' [" + this.globalStart + "-" + this.globalEnd + "], " + this.tokens.size() + " tokens}";
    }

    public static class Mark {
        public final int start;
        public final int end;
        public final TokenType type;
        public final Object metadata;

        public Mark(int start, int end, TokenType type) {
            this.start = start;
            this.end = end;
            this.type = type;
            this.metadata = null;
        }

        public Mark(int start, int end, TokenType type, Object metadata) {
            this.start = start;
            this.end = end;
            this.type = type;
            this.metadata = metadata;
        }

        public String toString() {
            return "Mark{" + (Object)((Object)this.type) + " [" + this.start + "-" + this.end + "]" + (this.metadata != null ? " " + this.metadata.getClass().getSimpleName() : "") + "}";
        }
    }

    private static class TextSegment {
        final int startPos;
        final String text;
        final int color;
        final boolean isToken;

        TextSegment(int startPos, String text, int color, boolean isToken) {
            this.startPos = startPos;
            this.text = text;
            this.color = color;
            this.isToken = isToken;
        }
    }
}

