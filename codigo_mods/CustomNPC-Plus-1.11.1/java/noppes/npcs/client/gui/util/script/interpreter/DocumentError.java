/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import noppes.npcs.client.gui.util.script.interpreter.token.Token;

public class DocumentError {
    private final Token token;
    private final int startPos;
    private final int endPos;
    private final String message;

    public DocumentError(Token token, int startPos, int endPos, String message) {
        this.token = token;
        this.startPos = startPos;
        this.endPos = endPos;
        this.message = message;
    }

    public DocumentError(int startPos, int endPos, String message) {
        this.token = null;
        this.startPos = startPos;
        this.endPos = endPos;
        this.message = message;
    }

    public Token getToken() {
        return this.token;
    }

    public int getStartPos() {
        return this.startPos;
    }

    public int getEndPos() {
        return this.endPos;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "DocumentError{startPos=" + this.startPos + ", endPos=" + this.endPos + ", message='" + this.message + '\'' + ", token=" + (this.token != null ? this.token.getText() : "null") + '}';
    }
}

