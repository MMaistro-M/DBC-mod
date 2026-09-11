/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Mouse
 */
package noppes.npcs.client.gui.util.script.autocomplete;

import java.util.List;
import java.util.regex.Pattern;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteMenu;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteProvider;
import noppes.npcs.client.gui.util.script.autocomplete.JSAutocompleteProvider;
import noppes.npcs.client.gui.util.script.autocomplete.JavaAutocompleteProvider;
import noppes.npcs.client.gui.util.script.autocomplete.UsageTracker;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.ScriptTextContainer;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import org.lwjgl.input.Mouse;

public class AutocompleteManager {
    private static final String TRIGGER_CHARS = ".";
    private static final String CLOSE_CHARS = ";{}()[]<>,\"'`";
    private static final int MIN_PREFIX_LENGTH = 1;
    private static final int MAX_SUGGESTIONS = 150;
    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("[a-zA-Z_$][a-zA-Z0-9_$]*");
    private final AutocompleteMenu menu = new AutocompleteMenu();
    private final JavaAutocompleteProvider javaProvider = new JavaAutocompleteProvider();
    private final JSAutocompleteProvider jsProvider = new JSAutocompleteProvider();
    private ScriptTextContainer container;
    private ScriptDocument document;
    private boolean active = false;
    private String currentPrefix = "";
    private int prefixStartPosition = -1;
    private boolean explicitTrigger = false;
    private String currentReceiverFullName = null;
    private boolean currentIsMemberAccess = false;
    private InsertCallback insertCallback;

    public AutocompleteManager() {
        this.menu.setCallback(new AutocompleteMenu.AutocompleteCallback(){

            @Override
            public void onItemSelected(AutocompleteItem item) {
                AutocompleteManager.this.handleItemSelected(item);
            }

            @Override
            public void onDismiss() {
                AutocompleteManager.this.active = false;
            }
        });
    }

    public void setContainer(ScriptTextContainer container) {
        this.container = container;
        if (container != null) {
            this.document = container.getDocument();
            this.javaProvider.setDocument(this.document);
            this.jsProvider.setDocument(this.document);
        }
    }

    public void setInsertCallback(InsertCallback callback) {
        this.insertCallback = callback;
    }

    public void onCharTyped(char c, String text, int cursorPosition) {
        int effectiveCursor;
        if (CLOSE_CHARS.indexOf(c) >= 0 || this.document.isExcludedInclusive(cursorPosition)) {
            this.dismiss();
            return;
        }
        if (TRIGGER_CHARS.indexOf(c) >= 0) {
            this.triggerAfterDot(text, cursorPosition);
            return;
        }
        if (c == ':') {
            effectiveCursor = cursorPosition;
            if (effectiveCursor >= 0 && effectiveCursor < text.length() && text.charAt(effectiveCursor) == c) {
                ++effectiveCursor;
            }
            if ((effectiveCursor = Math.max(0, Math.min(effectiveCursor, text.length()))) >= 2 && text.charAt(effectiveCursor - 2) == ':') {
                this.triggerAfterMethodReference(text, effectiveCursor);
                return;
            }
        }
        if (Character.isJavaIdentifierPart(c)) {
            effectiveCursor = cursorPosition;
            if (effectiveCursor >= 0 && effectiveCursor < text.length() && text.charAt(effectiveCursor) == c) {
                ++effectiveCursor;
            }
            effectiveCursor = Math.max(0, Math.min(effectiveCursor, text.length()));
            if (this.active) {
                this.updatePrefix(text, effectiveCursor);
            } else if (Character.isJavaIdentifierStart(c)) {
                int dotPos = this.findDotBeforeWhitespace(text, effectiveCursor - 1);
                if (dotPos >= 0) {
                    this.triggerAfterDot(text, effectiveCursor);
                } else {
                    this.maybeStartAutocomplete(text, effectiveCursor, false);
                }
            }
            return;
        }
        if (this.active && !Character.isWhitespace(c)) {
            this.dismiss();
        }
    }

    public void onDeleteKey(String text, int cursorPosition) {
        if (this.active) {
            this.updatePrefix(text, cursorPosition);
            if (this.currentPrefix.isEmpty() && !this.isAfterDot(text, cursorPosition)) {
                this.dismiss();
            }
        }
    }

    public void onCursorMove(String text, int cursorPosition) {
        int wordStart;
        if (!this.active) {
            return;
        }
        if (cursorPosition < 0 || cursorPosition > text.length()) {
            this.dismiss();
            return;
        }
        for (wordStart = cursorPosition; wordStart > 0 && Character.isJavaIdentifierPart(text.charAt(wordStart - 1)); --wordStart) {
        }
        for (int wordEnd = cursorPosition; wordEnd < text.length() && Character.isJavaIdentifierPart(text.charAt(wordEnd)); ++wordEnd) {
        }
        if (cursorPosition < this.prefixStartPosition || cursorPosition > this.prefixStartPosition + this.currentPrefix.length()) {
            boolean isMemberAccess;
            boolean bl = isMemberAccess = wordStart > 0 && text.charAt(wordStart - 1) == '.';
            if (isMemberAccess || wordStart < cursorPosition) {
                this.prefixStartPosition = wordStart;
                this.updatePrefix(text, cursorPosition);
            } else {
                this.dismiss();
            }
        } else {
            this.updatePrefix(text, cursorPosition);
        }
    }

    public void triggerExplicit() {
        if (this.insertCallback == null) {
            return;
        }
        String text = this.insertCallback.getText();
        int cursorPosition = this.insertCallback.getCursorPosition();
        this.explicitTrigger = true;
        if (this.isAfterDot(text, cursorPosition)) {
            this.triggerAfterDot(text, cursorPosition);
        } else {
            this.maybeStartAutocomplete(text, cursorPosition, true);
        }
    }

    private void triggerAfterDot(String text, int cursorPosition) {
        int prefixStart;
        int caretPos = Math.max(0, Math.min(cursorPosition, text.length()));
        int dotPos = -1;
        if (caretPos > 0 && caretPos <= text.length() && text.charAt(caretPos - 1) == '.') {
            dotPos = caretPos - 1;
        } else if (caretPos >= 0 && caretPos < text.length() && text.charAt(caretPos) == '.') {
            dotPos = caretPos;
            caretPos = Math.min(caretPos + 1, text.length());
        } else {
            dotPos = this.findDotBeforeWhitespace(text, caretPos - 1);
        }
        if (dotPos < 0) {
            return;
        }
        String receiverExpr = this.findReceiverExpression(text, dotPos);
        String prefix = this.findCurrentWord(text, caretPos);
        for (prefixStart = dotPos + 1; prefixStart < caretPos && Character.isWhitespace(text.charAt(prefixStart)); ++prefixStart) {
        }
        this.prefixStartPosition = prefixStart;
        this.currentPrefix = prefix;
        this.showSuggestions(text, caretPos, prefix, this.prefixStartPosition, true, receiverExpr, false);
    }

    private void triggerAfterMethodReference(String text, int cursorPosition) {
        int prefixStart;
        int doubleColonPos = cursorPosition - 2;
        String receiverExpr = this.findMethodRefReceiverExpression(text, doubleColonPos);
        if (receiverExpr == null || receiverExpr.isEmpty()) {
            return;
        }
        for (prefixStart = cursorPosition; prefixStart < text.length() && Character.isWhitespace(text.charAt(prefixStart)); ++prefixStart) {
        }
        String prefix = "";
        this.prefixStartPosition = prefixStart;
        this.currentPrefix = prefix;
        this.showSuggestions(text, cursorPosition, prefix, prefixStart, true, receiverExpr, true);
    }

    private String findMethodRefReceiverExpression(String text, int doubleColonPos) {
        int start;
        int pos;
        if (text == null || doubleColonPos <= 0) {
            return "";
        }
        for (pos = doubleColonPos - 1; pos >= 0 && Character.isWhitespace(text.charAt(pos)); --pos) {
        }
        if (pos < 0) {
            return "";
        }
        int end = pos + 1;
        while (pos >= 0) {
            int open;
            if (this.document != null && this.document.isExcluded(pos)) {
                int jumped = this.jumpBeforeExcluded(pos);
                if (jumped == pos) {
                    --pos;
                    continue;
                }
                pos = jumped;
                continue;
            }
            char c = text.charAt(pos);
            if (Character.isWhitespace(c)) {
                --pos;
                continue;
            }
            if (c == ')') {
                open = this.findMatchingBackward(text, pos, '(', ')');
                if (open < 0) break;
                pos = open - 1;
                continue;
            }
            if (c == ']') {
                open = this.findMatchingBackward(text, pos, '[', ']');
                if (open < 0) break;
                pos = open - 1;
                continue;
            }
            if (!Character.isJavaIdentifierPart(c)) break;
            while (pos >= 0 && Character.isJavaIdentifierPart(text.charAt(pos))) {
                --pos;
            }
            int checkPos = pos;
            while (checkPos >= 0) {
                char ch = text.charAt(checkPos);
                if (Character.isWhitespace(ch)) {
                    --checkPos;
                    continue;
                }
                if (this.document == null || !this.document.isExcluded(checkPos)) break;
                int jumped = this.jumpBeforeExcluded(checkPos);
                if (jumped == checkPos) {
                    --checkPos;
                    continue;
                }
                checkPos = jumped;
            }
            if (checkPos < 0 || text.charAt(checkPos) != '.') break;
            pos = checkPos - 1;
        }
        if ((start = pos + 1) < 0) {
            start = 0;
        }
        if (end > text.length()) {
            end = text.length();
        }
        if (start >= end) {
            return "";
        }
        String expr = text.substring(start, end).replaceAll("\\s+", " ").trim();
        return expr;
    }

    private void maybeStartAutocomplete(String text, int cursorPosition, boolean force) {
        String prefix = this.findCurrentWord(text, cursorPosition);
        int prefixStart = cursorPosition - prefix.length();
        if (!force && prefix.length() < 1) {
            return;
        }
        if (!force && prefixStart > 0 && Character.isJavaIdentifierPart(text.charAt(prefixStart - 1))) {
            return;
        }
        this.prefixStartPosition = prefixStart;
        this.currentPrefix = prefix;
        this.showSuggestions(text, cursorPosition, prefix, prefixStart, false, null, false);
    }

    private void updatePrefix(String text, int cursorPosition) {
        if (text == null || cursorPosition < 0 || cursorPosition > text.length()) {
            this.dismiss();
            return;
        }
        if (this.prefixStartPosition < 0 || this.prefixStartPosition > cursorPosition) {
            this.dismiss();
            return;
        }
        String newPrefix = text.substring(this.prefixStartPosition, cursorPosition);
        if (!newPrefix.isEmpty() && !this.isValidPrefix(newPrefix)) {
            this.dismiss();
            return;
        }
        this.currentPrefix = newPrefix;
        boolean isMethodReference = this.isAfterMethodReference(text, this.prefixStartPosition);
        int dotPos = this.findDotBeforeWhitespace(text, this.prefixStartPosition - 1);
        boolean isMemberAccess = dotPos >= 0 || isMethodReference;
        String receiverExpr = null;
        if (isMethodReference) {
            int doubleColonPos = this.findDoubleColonBefore(text, this.prefixStartPosition);
            if (doubleColonPos >= 0) {
                receiverExpr = this.findMethodRefReceiverExpression(text, doubleColonPos);
            }
        } else if (isMemberAccess) {
            receiverExpr = this.findReceiverExpression(text, dotPos);
        }
        this.showSuggestions(text, cursorPosition, this.currentPrefix, this.prefixStartPosition, isMemberAccess, receiverExpr, isMethodReference);
    }

    private boolean isAfterMethodReference(String text, int pos) {
        int checkPos;
        for (checkPos = pos - 1; checkPos >= 0 && Character.isWhitespace(text.charAt(checkPos)); --checkPos) {
        }
        return checkPos >= 1 && text.charAt(checkPos) == ':' && text.charAt(checkPos - 1) == ':';
    }

    private int findDoubleColonBefore(String text, int pos) {
        int checkPos;
        for (checkPos = pos - 1; checkPos >= 0 && Character.isWhitespace(text.charAt(checkPos)); --checkPos) {
        }
        if (checkPos >= 1 && text.charAt(checkPos) == ':' && text.charAt(checkPos - 1) == ':') {
            return checkPos - 1;
        }
        return -1;
    }

    private void showSuggestions(String text, int cursorPosition, String prefix, int prefixStart, boolean isMemberAccess, String receiverExpr, boolean methodsOnly) {
        int dotPos;
        int resolvePos;
        TypeInfo receiverType;
        if (this.insertCallback == null || this.document == null) {
            return;
        }
        this.currentIsMemberAccess = isMemberAccess;
        this.currentReceiverFullName = null;
        if (isMemberAccess && receiverExpr != null && (receiverType = this.document.resolveExpressionType(receiverExpr, resolvePos = (dotPos = this.findDotBeforeWhitespace(text, prefixStart - 1)) >= 0 ? dotPos : prefixStart)) != null && receiverType.isResolved()) {
            this.currentReceiverFullName = receiverType.getFullName();
        }
        int lineNumber = this.getLineNumber(text, cursorPosition);
        String currentLine = this.getCurrentLine(text, cursorPosition);
        int columnPosition = this.getColumnPosition(text, cursorPosition);
        AutocompleteProvider.Context context = new AutocompleteProvider.Context(text, cursorPosition, lineNumber, columnPosition, currentLine, prefix, prefixStart, isMemberAccess, receiverExpr, this.explicitTrigger, methodsOnly);
        JavaAutocompleteProvider provider = this.document.isJavaScript() ? this.jsProvider : this.javaProvider;
        List<AutocompleteItem> suggestions = provider.getSuggestions(context);
        if (suggestions.size() > 150) {
            // empty if block
        }
        if (suggestions.isEmpty()) {
            if (this.explicitTrigger) {
                suggestions.add(new AutocompleteItem.Builder().name("No suggestions").kind(AutocompleteItem.Kind.SNIPPET).typeLabel("").build());
            } else {
                this.dismiss();
                return;
            }
        }
        int[] screenPos = this.insertCallback.getCursorScreenPosition();
        int[] viewport = this.insertCallback.getViewportDimensions();
        this.menu.show(screenPos[0], screenPos[1] + 15, suggestions, viewport[0], viewport[1]);
        this.active = true;
        this.explicitTrigger = false;
    }

    public boolean keyPressed(int keyCode) {
        if (!this.active || !this.menu.isVisible()) {
            return false;
        }
        switch (keyCode) {
            case 200: {
                this.menu.selectPrevious();
                return true;
            }
            case 208: {
                this.menu.selectNext();
                return true;
            }
            case 15: 
            case 28: {
                if (this.menu.hasItems()) {
                    this.menu.confirmSelection();
                    return true;
                }
                return false;
            }
            case 1: {
                this.dismiss();
                return true;
            }
        }
        return false;
    }

    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (!this.active) {
            return false;
        }
        return this.menu.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseScrolled(int mouseX, int mouseY, int delta) {
        if (!this.active) {
            return false;
        }
        return this.menu.mouseScrolled(mouseX, mouseY, delta);
    }

    public boolean mouseReleased(int mouseX, int mouseY, int button) {
        if (!this.active) {
            return false;
        }
        return this.menu.mouseReleased(mouseX, mouseY, button);
    }

    public boolean mouseDragged(int mouseX, int mouseY) {
        if (!this.active) {
            return false;
        }
        return this.menu.mouseDragged(mouseX, mouseY);
    }

    public boolean isDraggingPanel() {
        return this.active && this.menu.isDraggingPanel();
    }

    public boolean isDraggingScrollbarMenu() {
        return this.active && this.menu.isDraggingScrollbarMenu();
    }

    public boolean isResizingPanel() {
        return this.active && this.menu.isResizingPanel();
    }

    public boolean isMouseOverResizeHandle(int mx, int my) {
        return this.active && this.menu.isMouseOverResizeHandle(mx, my);
    }

    public void updatePanelDrag(int x, int y) {
        if (this.active) {
            this.menu.updatePanelDrag(x, y);
        }
    }

    public void releasePanelDrag() {
        if (this.active) {
            this.menu.releasePanelDrag();
        }
    }

    public void updateScrollbarDrag(int y) {
        if (this.active) {
            this.menu.updateScrollbarDragDraw(y);
        }
    }

    public void releaseScrollbarDrag() {
        if (this.active) {
            this.menu.releaseScrollbarDrag();
        }
    }

    public void updatePanelResize(int x, int y) {
        if (this.active) {
            this.menu.updatePanelResize(x, y);
        }
    }

    public void releasePanelResize() {
        if (this.active) {
            this.menu.releasePanelResize();
        }
    }

    private void handleItemSelected(AutocompleteItem item) {
        int cursorPos;
        int endPos;
        if (this.insertCallback == null || item == null) {
            return;
        }
        if (item.getName().equals("No suggestions")) {
            return;
        }
        this.recordUsage(item);
        String insertText = item.getInsertText();
        String text = this.insertCallback.getText();
        for (endPos = cursorPos = this.insertCallback.getCursorPosition(); endPos < text.length() && Character.isJavaIdentifierPart(text.charAt(endPos)); ++endPos) {
        }
        if (endPos < text.length() && text.charAt(endPos) == '(') {
            int parenDepth = 1;
            ++endPos;
            while (endPos < text.length() && parenDepth > 0) {
                char c = text.charAt(endPos);
                if (c == '(') {
                    ++parenDepth;
                } else if (c == ')') {
                    --parenDepth;
                }
                ++endPos;
            }
        }
        this.insertCallback.replaceTextRange(insertText, this.prefixStartPosition, endPos);
        if (insertText.endsWith("()") && item.getParameterCount() > 0) {
            int currentCursor = this.insertCallback.getCursorPosition();
            this.insertCallback.setCursorPosition(currentCursor - 1);
        }
        if (item.requiresImport() && item.getImportPath() != null) {
            this.insertCallback.addImport(item.getImportPath());
        }
        this.active = false;
    }

    private void recordUsage(AutocompleteItem item) {
        if (this.document == null) {
            return;
        }
        UsageTracker tracker = this.document.isJavaScript() ? UsageTracker.getJSInstance() : UsageTracker.getJavaInstance();
        String owner = this.currentIsMemberAccess ? this.currentReceiverFullName : null;
        tracker.recordUsage(item, owner);
    }

    public void dismiss() {
        if (this.active) {
            this.menu.hide();
            this.active = false;
            this.currentPrefix = "";
            this.prefixStartPosition = -1;
            this.explicitTrigger = false;
        }
    }

    public void draw(int mouseX, int mouseY) {
        if (this.active) {
            this.menu.draw(mouseX, mouseY);
            if (Mouse.isButtonDown((int)0)) {
                this.mouseDragged(mouseX, mouseY);
            } else {
                this.mouseReleased(mouseX, mouseY, 0);
            }
        }
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean isVisible() {
        return this.active && this.menu.isVisible();
    }

    public AutocompleteMenu getMenu() {
        return this.menu;
    }

    private String findCurrentWord(String text, int cursorPos) {
        int start;
        if (text == null || text.isEmpty()) {
            return "";
        }
        int safeCursorPos = Math.max(0, Math.min(cursorPos, text.length()));
        if (safeCursorPos <= 0) {
            return "";
        }
        for (start = safeCursorPos; start > 0 && Character.isJavaIdentifierPart(text.charAt(start - 1)); --start) {
        }
        return text.substring(start, safeCursorPos);
    }

    private String findReceiverExpression(String text, int dotPos) {
        int start;
        int pos;
        if (text == null || dotPos <= 0) {
            return "";
        }
        if (dotPos >= text.length()) {
            return "";
        }
        int end = dotPos;
        for (pos = dotPos - 1; pos >= 0 && Character.isWhitespace(text.charAt(pos)); --pos) {
        }
        if (pos < 0) {
            return "";
        }
        while (pos >= 0) {
            int open;
            if (this.document != null && this.document.isExcluded(pos)) {
                int jumped = this.jumpBeforeExcluded(pos);
                if (jumped == pos) {
                    --pos;
                    continue;
                }
                pos = jumped;
                continue;
            }
            char c = text.charAt(pos);
            if (Character.isWhitespace(c)) {
                --pos;
                continue;
            }
            if (c == ')') {
                open = this.findMatchingBackward(text, pos, '(', ')');
                if (open < 0) break;
                pos = open - 1;
                continue;
            }
            if (c == ']') {
                open = this.findMatchingBackward(text, pos, '[', ']');
                if (open < 0) break;
                pos = open - 1;
                continue;
            }
            if (!Character.isJavaIdentifierPart(c)) break;
            while (pos >= 0 && Character.isJavaIdentifierPart(text.charAt(pos))) {
                --pos;
            }
            int checkPos = pos;
            while (checkPos >= 0) {
                char ch = text.charAt(checkPos);
                if (Character.isWhitespace(ch)) {
                    --checkPos;
                    continue;
                }
                if (this.document == null || !this.document.isExcluded(checkPos)) break;
                int jumped = this.jumpBeforeExcluded(checkPos);
                if (jumped == checkPos) {
                    --checkPos;
                    continue;
                }
                checkPos = jumped;
            }
            if (checkPos < 0 || text.charAt(checkPos) != '.') break;
            pos = checkPos - 1;
        }
        if ((start = pos + 1) < 0) {
            start = 0;
        }
        if (end > text.length()) {
            end = text.length();
        }
        if (start >= end) {
            return "";
        }
        String expr = text.substring(start, end);
        if (this.document != null) {
            char[] chars = expr.toCharArray();
            for (int[] range : this.document.getExcludedRanges()) {
                if (range == null || range.length < 2 || !this.isCommentRange(text, range[0])) continue;
                int a = Math.max(range[0], start) - start;
                int b = Math.min(range[1], end) - start;
                if (b <= 0 || a >= chars.length) continue;
                if (a < 0) {
                    a = 0;
                }
                if (b > chars.length) {
                    b = chars.length;
                }
                for (int i = a; i < b; ++i) {
                    char ch = chars[i];
                    if (ch == '\n' || ch == '\r') continue;
                    chars[i] = 32;
                }
            }
            expr = new String(chars);
        }
        expr = expr.replaceAll("\\s+", " ").trim();
        return expr;
    }

    private boolean isCommentRange(String text, int start) {
        if (start < 0 || start + 1 >= text.length()) {
            return false;
        }
        if (text.charAt(start) != '/') {
            return false;
        }
        char next = text.charAt(start + 1);
        return next == '/' || next == '*';
    }

    private int jumpBeforeExcluded(int position) {
        if (this.document == null) {
            return position;
        }
        for (int[] range : this.document.getExcludedRanges()) {
            if (position < range[0] || position >= range[1]) continue;
            return range[0] - 1;
        }
        return position;
    }

    private int findMatchingBackward(String text, int closePos, char openChar, char closeChar) {
        int depth = 0;
        boolean inString = false;
        char stringChar = '\u0000';
        for (int i = closePos; i >= 0; --i) {
            int j;
            int backslashCount;
            char c = text.charAt(i);
            if (!(inString || c != '\"' && c != '\'')) {
                backslashCount = 0;
                for (j = i - 1; j >= 0 && text.charAt(j) == '\\'; --j) {
                    ++backslashCount;
                }
                if (backslashCount % 2 == 0) {
                    inString = true;
                    stringChar = c;
                }
            } else if (inString && c == stringChar) {
                backslashCount = 0;
                for (j = i - 1; j >= 0 && text.charAt(j) == '\\'; --j) {
                    ++backslashCount;
                }
                if (backslashCount % 2 == 0) {
                    inString = false;
                }
            }
            if (inString || this.document != null && this.document.isExcluded(i)) continue;
            if (c == closeChar) {
                ++depth;
                continue;
            }
            if (c != openChar || --depth != 0) continue;
            return i;
        }
        return -1;
    }

    private int findDotBeforeWhitespace(String text, int fromPos) {
        if (text == null || text.isEmpty()) {
            return -1;
        }
        int pos = Math.min(fromPos, text.length() - 1);
        if (pos < 0) {
            return -1;
        }
        while (pos >= 0 && Character.isWhitespace(text.charAt(pos))) {
            --pos;
        }
        if (pos >= 0 && text.charAt(pos) == '.') {
            if (this.document != null && this.document.isExcluded(pos)) {
                return -1;
            }
            return pos;
        }
        while (pos >= 0 && Character.isJavaIdentifierPart(text.charAt(pos))) {
            --pos;
        }
        if (pos >= 0 && text.charAt(pos) == '.') {
            if (this.document != null && this.document.isExcluded(pos)) {
                return -1;
            }
            return pos;
        }
        return -1;
    }

    private boolean isAfterDot(String text, int cursorPos) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        int pos = Math.min(cursorPos - 1, text.length() - 1);
        if (pos < 0) {
            return false;
        }
        while (pos >= 0 && Character.isJavaIdentifierPart(text.charAt(pos))) {
            --pos;
        }
        while (pos >= 0 && Character.isWhitespace(text.charAt(pos))) {
            --pos;
        }
        return pos >= 0 && text.charAt(pos) == '.';
    }

    private boolean isValidPrefix(String prefix) {
        if (prefix.isEmpty()) {
            return true;
        }
        if (!Character.isJavaIdentifierStart(prefix.charAt(0))) {
            return false;
        }
        for (int i = 1; i < prefix.length(); ++i) {
            if (Character.isJavaIdentifierPart(prefix.charAt(i))) continue;
            return false;
        }
        return true;
    }

    private int getLineNumber(String text, int position) {
        int line = 0;
        for (int i = 0; i < position && i < text.length(); ++i) {
            if (text.charAt(i) != '\n') continue;
            ++line;
        }
        return line;
    }

    private String getCurrentLine(String text, int position) {
        int lineStart = text.lastIndexOf(10, position - 1) + 1;
        int lineEnd = text.indexOf(10, position);
        if (lineEnd < 0) {
            lineEnd = text.length();
        }
        return text.substring(lineStart, lineEnd);
    }

    private int getColumnPosition(String text, int position) {
        int lineStart = text.lastIndexOf(10, position - 1) + 1;
        return position - lineStart;
    }

    public static interface InsertCallback {
        public void insertText(String var1, int var2);

        public void replaceTextRange(String var1, int var2, int var3);

        public void addImport(String var1);

        public int getCursorPosition();

        public void setCursorPosition(int var1);

        public String getText();

        public int[] getCursorScreenPosition();

        public int[] getViewportDimensions();
    }
}

