/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter.token;

import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldAccessInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodCallInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenErrorMessage;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.ImportData;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;

public class Token {
    private final String text;
    private final int globalStart;
    private final int globalEnd;
    private TokenType type;
    private TypeInfo typeInfo;
    private FieldInfo fieldInfo;
    private MethodInfo methodInfo;
    private MethodCallInfo methodCallInfo;
    private ImportData importData;
    private FieldAccessInfo fieldAccessInfo;
    private TokenErrorMessage errorMessage;
    private Token prev;
    private Token next;
    private ScriptLine parentLine;

    public Token(String text, int globalStart, int globalEnd, TokenType type) {
        this.text = text;
        this.globalStart = globalStart;
        this.globalEnd = globalEnd;
        this.type = type;
    }

    public static Token defaultToken(String text, int start, int end) {
        return new Token(text, start, end, TokenType.DEFAULT);
    }

    public static Token keyword(String text, int start, int end) {
        return new Token(text, start, end, TokenType.KEYWORD);
    }

    public static Token comment(String text, int start, int end) {
        return new Token(text, start, end, TokenType.COMMENT);
    }

    public static Token string(String text, int start, int end) {
        return new Token(text, start, end, TokenType.STRING);
    }

    public static Token literal(String text, int start, int end) {
        return new Token(text, start, end, TokenType.LITERAL);
    }

    public static Token typeReference(String text, int start, int end, TypeInfo info) {
        Token t = new Token(text, start, end, info != null ? info.getTokenType() : TokenType.TYPE_DECL);
        t.typeInfo = info;
        return t;
    }

    public static Token methodDecl(String text, int start, int end, MethodInfo info) {
        Token t = new Token(text, start, end, TokenType.METHOD_DECL);
        t.methodInfo = info;
        return t;
    }

    public static Token methodCall(String text, int start, int end, MethodInfo info, boolean resolved) {
        Token t = new Token(text, start, end, resolved ? TokenType.METHOD_CALL : TokenType.DEFAULT);
        t.methodInfo = info;
        return t;
    }

    public static Token globalField(String text, int start, int end, FieldInfo info) {
        Token t = new Token(text, start, end, TokenType.GLOBAL_FIELD);
        t.fieldInfo = info;
        return t;
    }

    public static Token localField(String text, int start, int end, FieldInfo info) {
        Token t = new Token(text, start, end, TokenType.LOCAL_FIELD);
        t.fieldInfo = info;
        return t;
    }

    public static Token parameter(String text, int start, int end, FieldInfo info) {
        Token t = new Token(text, start, end, TokenType.PARAMETER);
        t.fieldInfo = info;
        return t;
    }

    public static Token undefined(String text, int start, int end) {
        return new Token(text, start, end, TokenType.UNDEFINED_VAR);
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

    public int getLength() {
        return this.globalEnd - this.globalStart;
    }

    public TokenType getType() {
        return this.type;
    }

    public TypeInfo getTypeInfo() {
        return this.typeInfo;
    }

    public FieldInfo getFieldInfo() {
        return this.fieldInfo;
    }

    public MethodInfo getMethodInfo() {
        return this.methodInfo;
    }

    public MethodCallInfo getMethodCallInfo() {
        return this.methodCallInfo;
    }

    public FieldAccessInfo getFieldAccessInfo() {
        return this.fieldAccessInfo;
    }

    public ImportData getImportData() {
        return this.importData;
    }

    public TokenErrorMessage getErrorMessage() {
        return this.errorMessage;
    }

    public ScriptLine getParentLine() {
        return this.parentLine;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public void setTypeInfo(TypeInfo info) {
        this.typeInfo = info;
    }

    public void setFieldInfo(FieldInfo info) {
        this.fieldInfo = info;
    }

    public void setFieldAccessInfo(FieldAccessInfo info) {
        this.fieldAccessInfo = info;
    }

    public void setMethodInfo(MethodInfo info) {
        this.methodInfo = info;
    }

    public void setMethodCallInfo(MethodCallInfo info) {
        this.methodCallInfo = info;
    }

    public void setImportData(ImportData data) {
        this.importData = data;
    }

    public void setErrorMessage(TokenErrorMessage message) {
        this.errorMessage = message;
    }

    public void setParentLine(ScriptLine line) {
        this.parentLine = line;
    }

    public void setPrev(Token prev) {
        this.prev = prev;
    }

    public void setNext(Token next) {
        this.next = next;
    }

    public Token prev() {
        if (this.prev != null) {
            return this.prev;
        }
        if (this.parentLine == null) {
            return null;
        }
        for (ScriptLine prevLine = this.parentLine.prev(); prevLine != null; prevLine = prevLine.prev()) {
            Token last = prevLine.getLastToken();
            if (last == null) continue;
            return last;
        }
        return null;
    }

    public Token next() {
        if (this.next != null) {
            return this.next;
        }
        if (this.parentLine == null) {
            return null;
        }
        for (ScriptLine nextLine = this.parentLine.next(); nextLine != null; nextLine = nextLine.next()) {
            Token first = nextLine.getFirstToken();
            if (first == null) continue;
            return first;
        }
        return null;
    }

    public Token prevOnLine() {
        return this.prev;
    }

    public Token nextOnLine() {
        return this.next;
    }

    public boolean isKeyword() {
        return this.type == TokenType.KEYWORD;
    }

    public boolean isTypeReference() {
        return this.type == TokenType.TYPE_DECL || this.type == TokenType.IMPORTED_CLASS || this.type == TokenType.CLASS_DECL || this.type == TokenType.INTERFACE_DECL || this.type == TokenType.ENUM_DECL;
    }

    public boolean isEnumConstant() {
        return this.type == TokenType.ENUM_CONSTANT;
    }

    public boolean isResolved() {
        if (this.typeInfo != null) {
            return this.typeInfo.isResolved();
        }
        if (this.fieldInfo != null) {
            return this.fieldInfo.isResolved();
        }
        if (this.methodInfo != null) {
            return this.methodInfo.isResolved();
        }
        return this.type != TokenType.UNDEFINED_VAR;
    }

    public boolean isIdentifier() {
        if (this.text.isEmpty()) {
            return false;
        }
        char first = this.text.charAt(0);
        return Character.isJavaIdentifierStart(first);
    }

    public boolean startsWithUpperCase() {
        return !this.text.isEmpty() && Character.isUpperCase(this.text.charAt(0));
    }

    public boolean isMethodCall() {
        return this.type == TokenType.METHOD_CALL || this.type == TokenType.DEFAULT && this.methodInfo != null;
    }

    public boolean isField() {
        return this.type == TokenType.GLOBAL_FIELD || this.type == TokenType.LOCAL_FIELD || this.type == TokenType.PARAMETER || this.type == TokenType.STATIC_FINAL_FIELD || this.type == TokenType.ENUM_CONSTANT;
    }

    public int getHexColor() {
        return this.type.getHexColor();
    }

    public char getColorCode() {
        return this.type.toColorCode();
    }

    public String getStylePrefix() {
        StringBuilder sb = new StringBuilder();
        if (this.type.isBold()) {
            sb.append('\u00a7').append('l');
        }
        if (this.type.isItalic()) {
            sb.append('\u00a7').append('o');
        }
        return sb.toString();
    }

    public int getFontStyle() {
        int style = 0;
        if (this.type.isBold()) {
            style |= 1;
        }
        if (this.type.isItalic()) {
            style |= 2;
        }
        return style;
    }

    public String toString() {
        return "Token{'" + this.text + "', " + (Object)((Object)this.type) + ", [" + this.globalStart + "-" + this.globalEnd + "]}";
    }
}

