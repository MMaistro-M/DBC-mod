/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.client.gui.util.script.interpreter;

import java.util.Collections;
import java.util.Map;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.util.script.JavaTextContainer;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import noppes.npcs.client.gui.util.script.interpreter.token.Token;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.constants.ScriptContext;

public class ScriptTextContainer
extends JavaTextContainer {
    public static boolean USE_NEW_INTERPRETER = true;
    private ScriptDocument document;
    private String language = "ECMAScript";
    private ScriptContext scriptContext = ScriptContext.GLOBAL;
    private Map<String, String> editorGlobals = Collections.emptyMap();

    public ScriptTextContainer(String text) {
        super(text);
        if (USE_NEW_INTERPRETER) {
            this.document = new ScriptDocument(text);
        }
    }

    public ScriptTextContainer(String text, String language) {
        super(text);
        String string = this.language = language != null ? language : "ECMAScript";
        if (USE_NEW_INTERPRETER) {
            this.document = new ScriptDocument(text, this.language);
        }
    }

    public void setLanguage(String language) {
        String string = this.language = language != null ? language : "ECMAScript";
        if (this.document != null) {
            this.document.setLanguage(this.language);
        }
    }

    public String getLanguage() {
        return this.language;
    }

    public void setScriptContext(ScriptContext context) {
        ScriptContext scriptContext = this.scriptContext = context != null ? context : ScriptContext.GLOBAL;
        if (this.document != null) {
            this.document.setScriptContext(this.scriptContext);
        }
    }

    public ScriptContext getScriptContext() {
        return this.scriptContext;
    }

    public void setEditorGlobalsMap(Map<String, String> globals) {
        Map<String, String> map = this.editorGlobals = globals != null ? globals : Collections.emptyMap();
        if (this.document != null) {
            this.document.setEditorGlobals(this.editorGlobals);
        }
    }

    public void addImplicitImports(String ... patterns) {
        if (this.document != null) {
            this.document.addImplicitImports(patterns);
        }
    }

    @Override
    public void init(int width, int height) {
        if (!USE_NEW_INTERPRETER) {
            super.init(width, height);
            return;
        }
        this.lineHeight = ClientProxy.Font.height();
        if (this.lineHeight == 0) {
            this.lineHeight = 12;
        }
        this.document.setText(this.text);
        this.document.init(width, height);
        this.rebuildLineData();
        this.linesCount = this.lines.size();
        this.totalHeight = this.linesCount * this.lineHeight;
        this.visibleLines = Math.max(height / this.lineHeight - 1, 1);
    }

    @Override
    public void init(String text, int width, int height) {
        String string = this.text = text == null ? "" : text.replaceAll("\\r?\\n|\\r", "\n");
        if (!USE_NEW_INTERPRETER) {
            super.init(text, width, height);
            return;
        }
        if (this.document == null) {
            this.document = new ScriptDocument(this.text, this.language);
            this.document.setScriptContext(this.scriptContext);
            this.document.setEditorGlobals(this.editorGlobals);
        }
        this.init(width, height);
    }

    private void rebuildLineData() {
        this.lines.clear();
        for (ScriptLine scriptLine : this.document.getLines()) {
            JavaTextContainer.LineData ld = new JavaTextContainer.LineData(scriptLine.getText(), scriptLine.getGlobalStart(), scriptLine.getGlobalEnd());
            ld.indentCols.addAll(scriptLine.getIndentGuides());
            for (Token interpreterToken : scriptLine.getTokens()) {
                ld.tokens.add(new JavaTextContainer.Token(interpreterToken.getText(), this.toLegacyTokenType(interpreterToken.getType()), interpreterToken.getGlobalStart(), interpreterToken.getGlobalEnd()));
            }
            this.lines.add(ld);
        }
    }

    @Override
    public void formatCodeText() {
        if (!USE_NEW_INTERPRETER) {
            super.formatCodeText();
            return;
        }
        this.document.formatCodeText();
        this.rebuildLineData();
    }

    private JavaTextContainer.TokenType toLegacyTokenType(TokenType type) {
        if (type == TokenType.COMMENT) {
            return JavaTextContainer.TokenType.COMMENT;
        }
        if (type == TokenType.STRING) {
            return JavaTextContainer.TokenType.STRING;
        }
        if (type == TokenType.KEYWORD) {
            return JavaTextContainer.TokenType.KEYWORD;
        }
        if (type == TokenType.INTERFACE_DECL) {
            return JavaTextContainer.TokenType.INTERFACE_DECL;
        }
        if (type == TokenType.ENUM_DECL) {
            return JavaTextContainer.TokenType.ENUM_DECL;
        }
        if (type == TokenType.CLASS_DECL) {
            return JavaTextContainer.TokenType.CLASS_DECL;
        }
        if (type == TokenType.IMPORTED_CLASS) {
            return JavaTextContainer.TokenType.IMPORTED_CLASS;
        }
        if (type == TokenType.TYPE_DECL) {
            return JavaTextContainer.TokenType.TYPE_DECL;
        }
        if (type == TokenType.METHOD_DECL) {
            return JavaTextContainer.TokenType.METHOD_DECARE;
        }
        if (type == TokenType.METHOD_CALL) {
            return JavaTextContainer.TokenType.METHOD_CALL;
        }
        if (type == TokenType.LITERAL) {
            return JavaTextContainer.TokenType.NUMBER;
        }
        if (type == TokenType.GLOBAL_FIELD) {
            return JavaTextContainer.TokenType.GLOBAL_FIELD;
        }
        if (type == TokenType.LOCAL_FIELD) {
            return JavaTextContainer.TokenType.LOCAL_FIELD;
        }
        if (type == TokenType.PARAMETER) {
            return JavaTextContainer.TokenType.PARAMETER;
        }
        if (type == TokenType.UNDEFINED_VAR) {
            return JavaTextContainer.TokenType.UNDEFINED_VAR;
        }
        if (type == TokenType.VARIABLE) {
            return JavaTextContainer.TokenType.VARIABLE;
        }
        return JavaTextContainer.TokenType.DEFAULT;
    }

    public ScriptDocument getDocument() {
        return this.document;
    }

    public Token getInterpreterTokenAt(int globalPosition) {
        if (!USE_NEW_INTERPRETER || this.document == null) {
            return null;
        }
        ScriptLine line = this.document.getLineAt(globalPosition);
        if (line == null) {
            return null;
        }
        return line.getTokenAt(globalPosition);
    }
}

