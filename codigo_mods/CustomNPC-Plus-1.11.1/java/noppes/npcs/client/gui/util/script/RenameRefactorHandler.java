/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  org.lwjgl.input.Keyboard
 */
package noppes.npcs.client.gui.util.script;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.gui.GuiScreen;
import noppes.npcs.client.gui.util.script.JavaTextContainer;
import noppes.npcs.client.gui.util.script.MethodBlock;
import noppes.npcs.client.gui.util.script.ScopeInfo;
import noppes.npcs.client.gui.util.script.SelectionState;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.ScriptTextContainer;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocInfo;
import noppes.npcs.client.gui.util.script.interpreter.jsdoc.JSDocParamTag;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import org.lwjgl.input.Keyboard;

public class RenameRefactorHandler {
    private boolean active = false;
    private String initialWord = "";
    private String originalWord = "";
    private String currentWord = "";
    private int primaryOccurrenceStart = -1;
    private int primaryOccurrenceEnd = -1;
    private List<int[]> allOccurrences = new ArrayList<int[]>();
    private ScopeInfo scope = null;
    private boolean isParameterRename = false;
    private MethodInfo containingMethod = null;
    private List<int[]> localShadowedPositions = new ArrayList<int[]>();
    private String originalText = "";
    private int originalCursorPos = 0;
    private RenameCallback callback;
    private static final Pattern IDENTIFIER = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
    private static final String EMPTY_PLACEHOLDER = "|";

    public void setCallback(RenameCallback callback) {
        this.callback = callback;
    }

    public boolean startRename() {
        if (this.callback == null) {
            return false;
        }
        String text = this.callback.getText();
        int cursor = this.callback.getCursorPosition();
        if (text == null || text.isEmpty()) {
            return false;
        }
        int[] wordBounds = this.findWordAtPosition(text, cursor);
        if (wordBounds == null) {
            return false;
        }
        this.originalText = text;
        this.originalCursorPos = cursor;
        this.originalWord = this.initialWord = text.substring(wordBounds[0], wordBounds[1]);
        this.currentWord = this.initialWord;
        this.scope = this.determineScope(text, wordBounds[0], this.originalWord, this.callback.getContainer());
        this.isParameterRename = false;
        this.containingMethod = null;
        ScriptTextContainer container = this.callback.getContainer();
        if (container != null && container.getDocument() != null) {
            ScriptDocument document = container.getDocument();
            List<MethodInfo> methods = document.getAllMethods();
            for (MethodInfo method : methods) {
                int methodStart = method.getFullDeclarationOffset();
                int methodEnd = method.getBodyEnd();
                if (methodStart < 0 || methodEnd < 0 || wordBounds[0] < methodStart || wordBounds[0] > methodEnd) continue;
                for (FieldInfo param : method.getParameters()) {
                    if (!param.getName().equals(this.originalWord)) continue;
                    this.isParameterRename = true;
                    this.containingMethod = method;
                    break;
                }
                if (!this.isParameterRename) continue;
                break;
            }
        }
        this.localShadowedPositions.clear();
        if (this.scope != null && this.scope.isGlobal) {
            this.findLocalShadowedPositions(text, this.originalWord);
        }
        this.findOccurrences(text, this.originalWord);
        if (this.allOccurrences.isEmpty()) {
            return false;
        }
        this.active = true;
        this.primaryOccurrenceStart = wordBounds[0];
        this.primaryOccurrenceEnd = wordBounds[1];
        this.callback.getSelectionState().setSelection(wordBounds[0], wordBounds[1]);
        this.callback.getSelectionState().markActivity();
        return true;
    }

    public void cancel() {
        if (!this.active) {
            return;
        }
        if (this.callback != null && !this.originalText.isEmpty()) {
            this.callback.setText(this.originalText);
            this.callback.setCursorPosition(this.originalCursorPos);
        }
        this.resetState();
        if (this.callback != null) {
            this.callback.focusMainEditor();
        }
    }

    public void confirm() {
        if (!this.active || this.callback == null) {
            return;
        }
        if (this.currentWord.isEmpty()) {
            this.cancel();
            return;
        }
        if (this.originalText != null && !this.originalText.isEmpty()) {
            this.callback.pushUndoState(this.originalText, this.originalCursorPos);
        }
        int cursorInWord = this.callback.getCursorPosition() - this.primaryOccurrenceStart;
        cursorInWord = Math.max(0, Math.min(cursorInWord, this.currentWord.length()));
        int newCursorPos = this.primaryOccurrenceStart + cursorInWord;
        this.resetState();
        this.callback.focusMainEditor();
        this.callback.setCursorPosition(Math.max(0, newCursorPos));
    }

    private void resetState() {
        this.active = false;
        this.initialWord = "";
        this.originalWord = "";
        this.currentWord = "";
        this.primaryOccurrenceStart = -1;
        this.primaryOccurrenceEnd = -1;
        this.allOccurrences.clear();
        this.localShadowedPositions.clear();
        this.scope = null;
        this.isParameterRename = false;
        this.containingMethod = null;
        this.originalText = "";
        this.originalCursorPos = 0;
    }

    public boolean keyTyped(char c, int keyCode) {
        boolean isFirstChar;
        if (!this.active || this.callback == null) {
            return false;
        }
        if (keyCode == 1) {
            this.cancel();
            return true;
        }
        if (keyCode == 28) {
            this.confirm();
            return true;
        }
        if (keyCode == 15) {
            this.confirm();
            return true;
        }
        SelectionState sel = this.callback.getSelectionState();
        boolean hasSelection = sel.hasSelection();
        int cursorInWord = this.callback.getCursorPosition() - this.primaryOccurrenceStart;
        cursorInWord = Math.max(0, Math.min(cursorInWord, this.currentWord.length()));
        if (keyCode == 14) {
            if (hasSelection || GuiScreen.func_146271_m()) {
                this.currentWord = "";
                this.applyLiveRename();
                sel.setSelection(this.primaryOccurrenceStart, this.primaryOccurrenceStart);
                this.callback.setCursorPosition(this.primaryOccurrenceStart);
            } else if (cursorInWord > 0) {
                String before = this.currentWord.substring(0, cursorInWord - 1);
                String after = this.currentWord.substring(cursorInWord);
                this.currentWord = before + after;
                this.applyLiveRename();
                this.callback.setCursorPosition(this.primaryOccurrenceStart + cursorInWord - 1);
            }
            sel.markActivity();
            return true;
        }
        if (keyCode == 211) {
            if (hasSelection) {
                this.currentWord = "";
                this.applyLiveRename();
                sel.setSelection(this.primaryOccurrenceStart, this.primaryOccurrenceStart);
                this.callback.setCursorPosition(this.primaryOccurrenceStart);
            } else if (cursorInWord < this.currentWord.length()) {
                String before = this.currentWord.substring(0, cursorInWord);
                String after = this.currentWord.substring(cursorInWord + 1);
                this.currentWord = before + after;
                this.applyLiveRename();
            }
            sel.markActivity();
            return true;
        }
        if (keyCode == 57 && hasSelection) {
            this.currentWord = "";
            this.applyLiveRename();
            sel.setSelection(this.primaryOccurrenceStart, this.primaryOccurrenceStart);
            this.callback.setCursorPosition(this.primaryOccurrenceStart);
        }
        if (keyCode == 203) {
            if (cursorInWord > 0) {
                this.callback.setCursorPosition(this.primaryOccurrenceStart + cursorInWord - 1);
                sel.setSelection(0, 0);
            }
            sel.markActivity();
            return true;
        }
        if (keyCode == 205) {
            if (cursorInWord < this.currentWord.length()) {
                this.callback.setCursorPosition(this.primaryOccurrenceStart + cursorInWord + 1);
                sel.setSelection(0, 0);
            }
            sel.markActivity();
            return true;
        }
        if (keyCode == 199) {
            this.callback.setCursorPosition(this.primaryOccurrenceStart);
            sel.setSelection(0, 0);
            sel.markActivity();
            return true;
        }
        if (keyCode == 207) {
            this.callback.setCursorPosition(this.primaryOccurrenceStart + this.currentWord.length());
            sel.setSelection(0, 0);
            sel.markActivity();
            return true;
        }
        if (keyCode == 30 && Keyboard.isKeyDown((int)29)) {
            sel.setSelection(this.primaryOccurrenceStart, this.primaryOccurrenceStart + this.currentWord.length());
            this.callback.setCursorPosition(this.primaryOccurrenceStart + this.currentWord.length());
            sel.markActivity();
            return true;
        }
        boolean bl = isFirstChar = hasSelection || this.currentWord.isEmpty() && cursorInWord == 0;
        if (this.isValidIdentifierChar(c, isFirstChar)) {
            if (hasSelection) {
                this.currentWord = "" + c;
                this.applyLiveRename();
                this.callback.setCursorPosition(this.primaryOccurrenceStart + 1);
                sel.setSelection(0, 0);
            } else {
                String before = this.currentWord.substring(0, cursorInWord);
                String after = this.currentWord.substring(cursorInWord);
                this.currentWord = before + c + after;
                this.applyLiveRename();
                this.callback.setCursorPosition(this.primaryOccurrenceStart + cursorInWord + 1);
            }
            sel.markActivity();
            return true;
        }
        return true;
    }

    private boolean isValidIdentifier(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return IDENTIFIER.matcher(s).matches();
    }

    private boolean isValidIdentifierChar(char c, boolean isFirst) {
        if (isFirst) {
            return Character.isLetter(c) || c == '_';
        }
        return Character.isLetterOrDigit(c) || c == '_';
    }

    private void applyLiveRename() {
        if (this.callback == null) {
            return;
        }
        String text = this.callback.getText();
        int occurrencesBefore = 0;
        for (int[] occ : this.allOccurrences) {
            if (occ[0] >= this.primaryOccurrenceStart) continue;
            ++occurrencesBefore;
        }
        int lengthDiff = this.currentWord.length() - this.originalWord.length();
        int newPrimaryStart = this.primaryOccurrenceStart + occurrencesBefore * lengthDiff;
        if (this.currentWord.isEmpty()) {
            this.callback.setTextWithoutUndo(text);
            this.setSelection(newPrimaryStart, newPrimaryStart);
            return;
        }
        String newText = this.applyRename(text, this.currentWord);
        this.callback.setTextWithoutUndo(newText);
        this.originalWord = this.currentWord;
        this.findOccurrences(newText, this.currentWord);
        if (!this.allOccurrences.isEmpty()) {
            for (int[] occ : this.allOccurrences) {
                if (Math.abs(occ[0] - newPrimaryStart) >= 2) continue;
                this.setSelection(occ[0], occ[1]);
                break;
            }
        } else if (this.currentWord.isEmpty()) {
            this.setSelection(newPrimaryStart, newPrimaryStart);
        }
    }

    private void setSelection(int start, int end) {
        this.primaryOccurrenceStart = start;
        this.primaryOccurrenceEnd = end;
        if (this.callback != null) {
            this.callback.getSelectionState().setSelection(start, end);
        }
    }

    private void updateOccurrencePositionsForEmpty(int newPrimaryStart) {
    }

    private String applyRename(String text, String newName) {
        if (this.allOccurrences.isEmpty()) {
            return text;
        }
        StringBuilder result = new StringBuilder();
        int lastEnd = 0;
        ArrayList<int[]> sorted = new ArrayList<int[]>(this.allOccurrences);
        sorted.sort((a, b) -> Integer.compare(a[0], b[0]));
        for (int[] occ : sorted) {
            if (occ[0] < lastEnd || occ[1] > text.length()) continue;
            result.append(text, lastEnd, occ[0]);
            result.append(newName);
            lastEnd = occ[1];
        }
        result.append(text.substring(lastEnd));
        return result.toString();
    }

    private void findOccurrences(String text, String word) {
        this.allOccurrences.clear();
        if (word.isEmpty() || this.scope == null) {
            return;
        }
        List<int[]> excluded = MethodBlock.getExcludedRanges(text);
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b");
        Matcher m = pattern.matcher(text);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            if (this.isInExcludedRange(start, excluded)) continue;
            if (this.scope.isGlobal) {
                if (this.isInLocalShadowedRange(start)) continue;
                this.allOccurrences.add(new int[]{start, end});
                continue;
            }
            if (!this.scope.containsPosition(start)) continue;
            this.allOccurrences.add(new int[]{start, end});
        }
        if (this.isParameterRename && this.containingMethod != null) {
            this.findJSDocParamOccurrences(text, word);
        }
    }

    private void findJSDocParamOccurrences(String text, String paramName) {
        if (this.containingMethod == null) {
            return;
        }
        JSDocInfo jsDocInfo = this.containingMethod.getJSDocInfo();
        if (jsDocInfo == null) {
            return;
        }
        JSDocParamTag paramTag = jsDocInfo.getParamTag(this.initialWord);
        if (paramTag == null) {
            return;
        }
        int originalJsDocStart = jsDocInfo.getStartOffset();
        int originalJsDocEnd = jsDocInfo.getEndOffset();
        if (originalJsDocStart < 0 || originalJsDocEnd < 0) {
            return;
        }
        int shiftBeforeJsDoc = 0;
        int lengthDiff = paramName.length() - this.initialWord.length();
        for (int[] occ : this.allOccurrences) {
            int approxOriginalPos = occ[0] - shiftBeforeJsDoc;
            if (approxOriginalPos >= originalJsDocStart) continue;
            shiftBeforeJsDoc += lengthDiff;
        }
        int adjustedJsDocStart = originalJsDocStart + shiftBeforeJsDoc;
        int adjustedJsDocEnd = originalJsDocEnd + shiftBeforeJsDoc;
        if ((adjustedJsDocStart = Math.max(0, adjustedJsDocStart)) >= (adjustedJsDocEnd = Math.min(text.length(), adjustedJsDocEnd + 50))) {
            return;
        }
        String jsDocRegion = text.substring(adjustedJsDocStart, adjustedJsDocEnd);
        Pattern pattern = Pattern.compile("@param\\s+(?:\\{[^}]*\\}\\s+)?(" + Pattern.quote(paramName) + ")(?:\\s|$|-)");
        Matcher m = pattern.matcher(jsDocRegion);
        while (m.find()) {
            int paramNameStart = adjustedJsDocStart + m.start(1);
            int paramNameEnd = adjustedJsDocStart + m.end(1);
            this.allOccurrences.add(new int[]{paramNameStart, paramNameEnd});
        }
    }

    private void findLocalShadowedPositions(String text, String varName) {
        this.localShadowedPositions.clear();
        List<MethodBlock> methods = MethodBlock.collectMethodBlocks(text);
        List<int[]> excluded = MethodBlock.getExcludedRanges(text);
        for (MethodBlock method : methods) {
            int scopeStart;
            boolean hasLocalDecl = method.localVariables.contains(varName);
            boolean hasParamDecl = this.isParameterInMethod(text, varName, method);
            if (!hasLocalDecl && !hasParamDecl) continue;
            if (hasParamDecl) {
                scopeStart = method.startOffset;
            } else {
                scopeStart = this.findLocalDeclarationPosition(text, varName, method);
                if (scopeStart < 0) {
                    scopeStart = method.startOffset;
                }
            }
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(varName) + "\\b");
            Matcher m = pattern.matcher(text);
            while (m.find()) {
                int pos = m.start();
                if (this.isInExcludedRange(pos, excluded) || pos < scopeStart || pos >= method.endOffset) continue;
                this.localShadowedPositions.add(new int[]{m.start(), m.end()});
            }
        }
    }

    private boolean isInLocalShadowedRange(int pos) {
        for (int[] range : this.localShadowedPositions) {
            if (pos < range[0] || pos >= range[1]) continue;
            return true;
        }
        return false;
    }

    private boolean isParameterInMethod(String text, String varName, MethodBlock method) {
        String methodHeader = text.substring(method.startOffset, Math.min(method.startOffset + 500, method.endOffset));
        int parenStart = methodHeader.indexOf(40);
        int parenEnd = methodHeader.indexOf(41);
        if (parenStart >= 0 && parenEnd > parenStart) {
            String params = methodHeader.substring(parenStart + 1, parenEnd);
            Pattern paramPattern = Pattern.compile("\\b" + Pattern.quote(varName) + "\\b");
            return paramPattern.matcher(params).find();
        }
        return false;
    }

    private boolean isInExcludedRange(int pos, List<int[]> ranges) {
        for (int[] range : ranges) {
            if (pos < range[0] || pos >= range[1]) continue;
            return true;
        }
        return false;
    }

    private int[] findWordAtPosition(String text, int pos) {
        int end;
        int start;
        if (pos < 0 || pos > text.length()) {
            return null;
        }
        for (start = pos; start > 0 && this.isIdentifierChar(text.charAt(start - 1)); --start) {
        }
        for (end = pos; end < text.length() && this.isIdentifierChar(text.charAt(end)); ++end) {
        }
        if (start == end) {
            return null;
        }
        String word = text.substring(start, end);
        if (!IDENTIFIER.matcher(word).matches()) {
            return null;
        }
        return new int[]{start, end};
    }

    private boolean isIdentifierChar(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    private ScopeInfo determineScope(String text, int position, String varName, JavaTextContainer container) {
        List<MethodBlock> methods = MethodBlock.collectMethodBlocks(text);
        boolean isGlobalField = this.isGlobalFieldDeclaration(text, varName, methods);
        boolean insideMethod = false;
        MethodBlock containingMethod = null;
        for (MethodBlock method : methods) {
            if (!method.containsPosition(position)) continue;
            insideMethod = true;
            containingMethod = method;
            break;
        }
        if (!insideMethod || containingMethod == null) {
            return new ScopeInfo(0, text.length(), true, "global");
        }
        String methodHeader = text.substring(containingMethod.startOffset, Math.min(containingMethod.startOffset + 500, containingMethod.endOffset));
        int parenStart = methodHeader.indexOf(40);
        int parenEnd = methodHeader.indexOf(41);
        if (parenStart >= 0 && parenEnd > parenStart) {
            String params = methodHeader.substring(parenStart + 1, parenEnd);
            Pattern paramPattern = Pattern.compile("\\b" + Pattern.quote(varName) + "\\b");
            if (paramPattern.matcher(params).find()) {
                return new ScopeInfo(containingMethod.startOffset, containingMethod.endOffset, false, "parameter");
            }
        }
        if (containingMethod.localVariables.contains(varName)) {
            int localDeclPosition = this.findLocalDeclarationPosition(text, varName, containingMethod);
            if (localDeclPosition >= 0 && position >= localDeclPosition) {
                int[] blockBounds = this.findInnermostBlockWithDeclaration(text, position, varName, containingMethod);
                if (blockBounds != null) {
                    return new ScopeInfo(blockBounds[0], blockBounds[1], false, "block");
                }
                return new ScopeInfo(containingMethod.startOffset, containingMethod.endOffset, false, "local");
            }
            if (isGlobalField) {
                return new ScopeInfo(0, text.length(), true, "global");
            }
        }
        if (isGlobalField) {
            return new ScopeInfo(0, text.length(), true, "global");
        }
        return new ScopeInfo(containingMethod.startOffset, containingMethod.endOffset, false, "method");
    }

    private boolean isGlobalFieldDeclaration(String text, String varName, List<MethodBlock> methods) {
        Matcher m = JavaTextContainer.GLOBAL_FIELD_DECL.matcher(text);
        while (m.find()) {
            if (!m.group(2).equals(varName)) continue;
            int pos = m.start(2);
            boolean insideMethod = false;
            for (MethodBlock method : methods) {
                if (!method.containsPosition(pos)) continue;
                insideMethod = true;
                break;
            }
            if (insideMethod) continue;
            return true;
        }
        return false;
    }

    private int findLocalDeclarationPosition(String text, String varName, MethodBlock method) {
        Pattern localDecl = Pattern.compile("\\b\\w+\\s+" + Pattern.quote(varName) + "\\s*[=;,)]");
        String methodText = text.substring(method.startOffset, Math.min(method.endOffset, text.length()));
        Matcher m = localDecl.matcher(methodText);
        int bodyStart = methodText.indexOf(123);
        if (bodyStart < 0) {
            bodyStart = 0;
        }
        while (m.find()) {
            if (m.start() < bodyStart) continue;
            return method.startOffset + m.start();
        }
        return -1;
    }

    private int[] findInnermostBlockWithDeclaration(String text, int position, String varName, MethodBlock method) {
        List<int[]> excluded = MethodBlock.getExcludedRanges(text);
        ArrayList<int[]> blocks = new ArrayList<int[]>();
        ArrayList<Integer> braceStack = new ArrayList<Integer>();
        for (int i = method.startOffset; i < method.endOffset && i < text.length(); ++i) {
            if (this.isInExcludedRange(i, excluded)) continue;
            char c = text.charAt(i);
            if (c == '{') {
                braceStack.add(i);
                continue;
            }
            if (c != '}' || braceStack.isEmpty()) continue;
            int openPos = (Integer)braceStack.remove(braceStack.size() - 1);
            blocks.add(new int[]{openPos, i + 1});
        }
        int[] best = null;
        int bestSize = Integer.MAX_VALUE;
        Pattern declPattern = Pattern.compile("\\b\\w+\\s+" + Pattern.quote(varName) + "\\s*[=;,)]");
        for (int[] block : blocks) {
            String blockText;
            int size;
            if (block[0] > position || block[1] < position || (size = block[1] - block[0]) >= bestSize || !declPattern.matcher(blockText = text.substring(block[0], Math.min(block[1], text.length()))).find()) continue;
            best = block;
            bestSize = size;
        }
        return best;
    }

    public List<int[]> getOccurrences() {
        return this.allOccurrences;
    }

    public int[] getPrimaryOccurrence() {
        if (!this.active) {
            return null;
        }
        return new int[]{this.primaryOccurrenceStart, this.primaryOccurrenceEnd};
    }

    public boolean isPrimaryOccurrence(int start) {
        return start == this.primaryOccurrenceStart;
    }

    public int getCursorInWord() {
        if (this.callback == null) {
            return 0;
        }
        int cursorPos = this.callback.getCursorPosition();
        return Math.max(0, Math.min(cursorPos - this.primaryOccurrenceStart, this.currentWord.length()));
    }

    public boolean isWordFullySelected() {
        if (this.callback == null) {
            return false;
        }
        SelectionState selection = this.callback.getSelectionState();
        return selection.hasSelection() && selection.getStartSelection() == this.primaryOccurrenceStart && selection.getEndSelection() == this.primaryOccurrenceEnd;
    }

    public String getCurrentWord() {
        return this.currentWord;
    }

    public boolean handleClick(int clickPosInText) {
        if (!this.active || this.callback == null) {
            return false;
        }
        return false;
    }

    public boolean isPositionInPrimaryOccurrence(int pos) {
        if (!this.active) {
            return false;
        }
        return pos >= this.primaryOccurrenceStart && pos <= this.primaryOccurrenceEnd;
    }

    public boolean isActive() {
        return this.active;
    }

    public void updateCursor() {
    }

    public boolean shouldShowCursor() {
        if (this.callback != null) {
            return this.callback.getSelectionState().hadRecentInput();
        }
        return true;
    }

    public String getScopeDescription() {
        if (this.scope == null) {
            return "";
        }
        switch (this.scope.scopeType) {
            case "global": {
                return "Renaming in file (" + this.allOccurrences.size() + " occurrences)";
            }
            case "parameter": {
                return "Renaming parameter (" + this.allOccurrences.size() + " occurrences)";
            }
            case "local": {
                return "Renaming local variable (" + this.allOccurrences.size() + " occurrences)";
            }
            case "method": {
                return "Renaming in method (" + this.allOccurrences.size() + " occurrences)";
            }
            case "block": {
                return "Renaming in block (" + this.allOccurrences.size() + " occurrences)";
            }
        }
        return "Renaming (" + this.allOccurrences.size() + " occurrences)";
    }

    public int getOccurrenceCount() {
        return this.allOccurrences.size();
    }

    public void drawStatusBox() {
    }

    public static interface RenameCallback {
        public String getText();

        public void setText(String var1);

        public void setTextWithoutUndo(String var1);

        public void pushUndoState(String var1, int var2);

        public List<JavaTextContainer.LineData> getLines();

        public int getCursorPosition();

        public SelectionState getSelectionState();

        public void setCursorPosition(int var1);

        public void unfocusMainEditor();

        public void focusMainEditor();

        public int getGutterWidth();

        public int getLineHeight();

        public int getScrolledLine();

        public double getFractionalOffset();

        public void scrollToPosition(int var1);

        public ScriptTextContainer getContainer();

        public int getViewportWidth();
    }
}

