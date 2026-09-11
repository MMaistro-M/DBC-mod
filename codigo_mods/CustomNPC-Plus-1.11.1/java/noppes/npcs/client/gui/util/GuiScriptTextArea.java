/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.ChatAllowedCharacters
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatAllowedCharacters;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.script.GuiScriptInterface;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextChangeListener;
import noppes.npcs.client.gui.util.key.OverlayKeyPresetViewer;
import noppes.npcs.client.gui.util.script.BracketMatcher;
import noppes.npcs.client.gui.util.script.CommentHandler;
import noppes.npcs.client.gui.util.script.CursorNavigation;
import noppes.npcs.client.gui.util.script.FormatHelper;
import noppes.npcs.client.gui.util.script.GoToLineDialog;
import noppes.npcs.client.gui.util.script.IndentHelper;
import noppes.npcs.client.gui.util.script.JavaTextContainer;
import noppes.npcs.client.gui.util.script.RenameRefactorHandler;
import noppes.npcs.client.gui.util.script.ScrollState;
import noppes.npcs.client.gui.util.script.SearchReplaceBar;
import noppes.npcs.client.gui.util.script.SelectionState;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteManager;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteMenu;
import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import noppes.npcs.client.gui.util.script.interpreter.ScriptTextContainer;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldAccessInfo;
import noppes.npcs.client.gui.util.script.interpreter.field.FieldInfo;
import noppes.npcs.client.gui.util.script.interpreter.hover.GutterIconRenderer;
import noppes.npcs.client.gui.util.script.interpreter.hover.HoverState;
import noppes.npcs.client.gui.util.script.interpreter.hover.TokenHoverRenderer;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodCallInfo;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.ScriptColorScheme;
import noppes.npcs.client.gui.util.script.interpreter.token.Token;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.ScriptTypeInfo;
import noppes.npcs.client.key.impl.ScriptEditorKeys;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.util.ValueUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiScriptTextArea
extends GuiNpcTextField {
    private GuiScriptInterface parent;
    public int x;
    public int y;
    public boolean active = false;
    public boolean enabled = true;
    public boolean field_146220_v = true;
    public boolean clicked = false;
    public boolean doubleClicked = false;
    public boolean tripleClicked = false;
    private int clickCount = 0;
    private long lastClicked = 0L;
    public String field_146216_j = null;
    public String highlightedWord;
    private ScriptTextContainer container = null;
    private boolean enableCodeHighlighting = false;
    private int bottomPaddingLines = 6;
    private int searchBaseY = 0;
    private int searchBaseHeight = 0;
    private int searchAppliedOffset = 0;
    private boolean searchBaseInitialized = false;
    private final ScrollState scroll = new ScrollState();
    private final SelectionState selection = new SelectionState();
    private final HoverState hoverState = new HoverState();
    public boolean clickToPinEnabled = false;
    private int cursorCounter;
    private ITextChangeListener listener;
    private static int LINE_NUMBER_GUTTER_WIDTH = 25;
    private MethodInfo hoveredGutterMethod = null;
    public List<UndoData> undoList = new ArrayList<UndoData>();
    public List<UndoData> redoList = new ArrayList<UndoData>();
    public boolean undoing = false;
    private long lastTypingTime = 0L;
    private int lastTypingPos = -1;
    private boolean lastCopyWasLine = false;
    private String lastCopiedLineText = null;
    public static final ScriptEditorKeys KEYS = new ScriptEditorKeys();
    public OverlayKeyPresetViewer KEYS_OVERLAY = new OverlayKeyPresetViewer(KEYS);
    public static final SearchReplaceBar searchBar = new SearchReplaceBar();
    private static final int PASTE_TRAILING_WS_TRIM_THRESHOLD = 16;
    private final GoToLineDialog goToLineDialog = new GoToLineDialog();
    private final RenameRefactorHandler renameHandler = new RenameRefactorHandler();
    private final AutocompleteManager autocompleteManager = new AutocompleteManager();

    private int getPaddedLineCount() {
        if (this.container == null) {
            return 0;
        }
        if (this.container.linesCount > this.container.visibleLines - this.bottomPaddingLines) {
            return Math.max(0, this.container.linesCount + this.bottomPaddingLines);
        }
        return this.container.linesCount;
    }

    public GuiScriptTextArea(GuiScreen guiScreen, int id, int x, int y, int width, int height, String text) {
        super(id, guiScreen, x, y, width, height, null);
        this.init(x, y, width, height, text);
        if (guiScreen instanceof GuiScriptInterface) {
            this.parent = (GuiScriptInterface)guiScreen;
        }
    }

    public void init(int x, int y, int width, int height, String text) {
        this.x = this.field_146209_f = x;
        this.y = this.field_146210_g = y;
        this.field_146218_h = width;
        this.field_146219_i = height;
        this.undoing = true;
        this.func_146180_a(text);
        this.undoing = false;
        this.setCallbacks();
        this.searchBaseY = 0;
        this.searchBaseHeight = 0;
        this.searchAppliedOffset = 0;
        this.searchBaseInitialized = false;
        this.KEYS_OVERLAY.openOnClick = true;
        this.initGui();
        this.initializeKeyBindings();
        this.hoverState.setClickToPinEnabled(this.clickToPinEnabled);
    }

    @Override
    public void initGui() {
        int endX = this.x + this.field_146218_h;
        int endY = this.y + this.field_146219_i;
        int xOffset = this.hasVerticalScrollbar() ? -8 : -2;
        this.KEYS_OVERLAY.scale = 0.75f;
        this.KEYS_OVERLAY.borderCol2 = -12829636;
        this.KEYS_OVERLAY.borderCol1 = -12829636;
        int overlayWidth = 160;
        this.KEYS_OVERLAY.initGui(this.x + (this.field_146218_h - overlayWidth) / 2 + 5, this.y + this.field_146219_i / 10, overlayWidth, this.field_146219_i - this.field_146219_i / 5 - 10);
        this.KEYS_OVERLAY.viewButton.scale = 0.45f;
        this.KEYS_OVERLAY.viewButton.initGui(endX + xOffset, endY - 26);
        if (this.autocompleteManager != null) {
            this.autocompleteManager.dismiss();
        }
        searchBar.initGui(this.x, this.y, this.field_146218_h);
        if (searchBar.isVisible()) {
            GuiScriptTextArea.searchBar.callback.resizeEditor(true, searchBar.getTotalHeight());
            if (!this.active) {
                searchBar.focus(false);
            }
        }
        this.goToLineDialog.initGui(this.x, this.y, this.field_146218_h);
    }

    public void setCallbacks() {
        searchBar.setCallback(new SearchReplaceBar.SearchCallback(){

            @Override
            public String getText() {
                return GuiScriptTextArea.this.field_146216_j;
            }

            @Override
            public String getHighlightedWord() {
                return GuiScriptTextArea.this.highlightedWord;
            }

            @Override
            public int getSelectionStart() {
                return GuiScriptTextArea.this.selection.getStartSelection();
            }

            @Override
            public int getSelectionEnd() {
                return GuiScriptTextArea.this.selection.getEndSelection();
            }

            @Override
            public void setText(String newText) {
                GuiScriptTextArea.this.func_146180_a(newText);
            }

            @Override
            public void scrollToPosition(int position) {
                if (GuiScriptTextArea.this.container == null || ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines == null) {
                    return;
                }
                int searchBarOffset = searchBar.getTotalHeight();
                int effectiveHeight = GuiScriptTextArea.this.field_146219_i - searchBarOffset;
                int visibleLines = effectiveHeight / ((GuiScriptTextArea)GuiScriptTextArea.this).container.lineHeight;
                int linesHiddenBySRB = searchBarOffset > 0 ? (int)Math.ceil((double)searchBarOffset / (double)((GuiScriptTextArea)GuiScriptTextArea.this).container.lineHeight) : 0;
                for (int i = 0; i < ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.size(); ++i) {
                    JavaTextContainer.LineData ld = (JavaTextContainer.LineData)((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.get(i);
                    if (position < ld.start || position >= ld.end) continue;
                    int visible = Math.max(1, visibleLines);
                    int effectiveVisible = Math.max(1, visible - GuiScriptTextArea.this.bottomPaddingLines);
                    int maxScroll = Math.max(0, GuiScriptTextArea.this.getPaddedLineCount() - visible);
                    int targetLine = i;
                    int currentScroll = GuiScriptTextArea.this.scroll.getScrolledLine();
                    int firstVisibleLine = currentScroll + linesHiddenBySRB;
                    if (searchBarOffset > 0 && targetLine < firstVisibleLine) {
                        GuiScriptTextArea.this.scroll.setTargetScroll(Math.max(0, targetLine - linesHiddenBySRB), maxScroll);
                        break;
                    }
                    GuiScriptTextArea.this.scroll.scrollToLine(targetLine, effectiveVisible, maxScroll);
                    break;
                }
            }

            @Override
            public void setSelection(int start, int end) {
                GuiScriptTextArea.this.selection.setSelection(start, end);
                GuiScriptTextArea.this.selection.setCursorPositionDirect(end);
            }

            @Override
            public int getGutterWidth() {
                return LINE_NUMBER_GUTTER_WIDTH;
            }

            @Override
            public void unfocusMainEditor() {
                GuiScriptTextArea.this.active = false;
            }

            @Override
            public void focusMainEditor() {
                GuiScriptTextArea.this.active = true;
                searchBar.resetSelection();
            }

            @Override
            public void onMatchesUpdated() {
            }

            @Override
            public void resizeEditor(boolean open, int barHeight) {
                int targetOffset;
                int desiredOffset = Math.max(0, barHeight);
                if (!GuiScriptTextArea.this.searchBaseInitialized) {
                    GuiScriptTextArea.this.searchBaseY = GuiScriptTextArea.this.y;
                    GuiScriptTextArea.this.searchBaseHeight = GuiScriptTextArea.this.field_146219_i;
                    GuiScriptTextArea.this.searchAppliedOffset = 0;
                    GuiScriptTextArea.this.searchBaseInitialized = true;
                }
                int n = targetOffset = open ? desiredOffset : 0;
                if (targetOffset == GuiScriptTextArea.this.searchAppliedOffset) {
                    return;
                }
                int newY = GuiScriptTextArea.this.searchBaseY + targetOffset;
                int newHeight = Math.max(12, GuiScriptTextArea.this.searchBaseHeight - targetOffset);
                GuiScriptTextArea.this.y = newY;
                GuiScriptTextArea.this.field_146219_i = newHeight;
                GuiScriptTextArea.this.searchAppliedOffset = targetOffset;
                if (GuiScriptTextArea.this.container != null) {
                    ((GuiScriptTextArea)GuiScriptTextArea.this).container.visibleLines = Math.max(GuiScriptTextArea.this.field_146219_i / ((GuiScriptTextArea)GuiScriptTextArea.this).container.lineHeight - 1, 1);
                }
            }
        });
        this.goToLineDialog.setCallback(new GoToLineDialog.GoToLineCallback(){

            @Override
            public int getLineCount() {
                return GuiScriptTextArea.this.container != null ? ((GuiScriptTextArea)GuiScriptTextArea.this).container.linesCount : 0;
            }

            @Override
            public int getColumnCount(int lineIndex) {
                if (GuiScriptTextArea.this.container == null || ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines == null || lineIndex < 0 || lineIndex >= ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.size()) {
                    return 0;
                }
                JavaTextContainer.LineData ld = (JavaTextContainer.LineData)((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.get(lineIndex);
                return ld.end - ld.start;
            }

            @Override
            public void goToLineColumn(int line, int column) {
                if (GuiScriptTextArea.this.container == null || ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines == null) {
                    return;
                }
                int lineIdx = line - 1;
                if (lineIdx < 0 || lineIdx >= ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.size()) {
                    return;
                }
                JavaTextContainer.LineData ld = (JavaTextContainer.LineData)((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.get(lineIdx);
                int lineLength = ld.end - ld.start;
                int col = Math.max(0, Math.min(column - 1, lineLength - 1));
                int position = ld.start + col;
                GuiScriptTextArea.this.selection.reset(position);
                int visible = GuiScriptTextArea.this.field_146219_i / (GuiScriptTextArea.this.container != null ? ((GuiScriptTextArea)GuiScriptTextArea.this).container.lineHeight : 12);
                int effectiveVisible = Math.max(1, visible - GuiScriptTextArea.this.bottomPaddingLines);
                int maxScroll = Math.max(0, GuiScriptTextArea.this.getPaddedLineCount() - visible);
                GuiScriptTextArea.this.scroll.scrollToLine(lineIdx, effectiveVisible, maxScroll);
            }

            @Override
            public void unfocusMainEditor() {
                GuiScriptTextArea.this.active = false;
            }

            @Override
            public void focusMainEditor() {
                GuiScriptTextArea.this.active = true;
                GuiScriptTextArea.this.selection.markActivity();
            }

            @Override
            public void onDialogClose() {
                GuiScriptTextArea.this.active = true;
                GuiScriptTextArea.this.selection.markActivity();
            }
        });
        this.renameHandler.setCallback(new RenameRefactorHandler.RenameCallback(){

            @Override
            public String getText() {
                return GuiScriptTextArea.this.field_146216_j;
            }

            @Override
            public void setText(String newText) {
                GuiScriptTextArea.this.func_146180_a(newText);
            }

            @Override
            public List<JavaTextContainer.LineData> getLines() {
                return GuiScriptTextArea.this.container != null ? ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines : new ArrayList();
            }

            @Override
            public int getCursorPosition() {
                return GuiScriptTextArea.this.selection.getCursorPosition();
            }

            @Override
            public SelectionState getSelectionState() {
                return GuiScriptTextArea.this.selection;
            }

            @Override
            public void setCursorPosition(int pos) {
                GuiScriptTextArea.this.selection.reset(pos);
            }

            @Override
            public void unfocusMainEditor() {
                GuiScriptTextArea.this.active = false;
            }

            @Override
            public void focusMainEditor() {
                GuiScriptTextArea.this.active = true;
                GuiScriptTextArea.this.selection.markActivity();
            }

            @Override
            public int getGutterWidth() {
                return LINE_NUMBER_GUTTER_WIDTH;
            }

            @Override
            public int getLineHeight() {
                return GuiScriptTextArea.this.container != null ? ((GuiScriptTextArea)GuiScriptTextArea.this).container.lineHeight : 12;
            }

            @Override
            public int getScrolledLine() {
                return GuiScriptTextArea.this.scroll.getScrolledLine();
            }

            @Override
            public double getFractionalOffset() {
                return GuiScriptTextArea.this.scroll.getFractionalOffset();
            }

            @Override
            public void scrollToPosition(int pos) {
                if (GuiScriptTextArea.this.container == null || ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines == null) {
                    return;
                }
                for (int i = 0; i < ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.size(); ++i) {
                    JavaTextContainer.LineData ld = (JavaTextContainer.LineData)((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.get(i);
                    if (pos < ld.start || pos >= ld.end) continue;
                    int visible = Math.max(1, ((GuiScriptTextArea)GuiScriptTextArea.this).container.visibleLines);
                    int effectiveVisible = Math.max(1, visible - GuiScriptTextArea.this.bottomPaddingLines);
                    int maxScroll = Math.max(0, GuiScriptTextArea.this.getPaddedLineCount() - visible);
                    GuiScriptTextArea.this.scroll.scrollToLine(i, effectiveVisible, maxScroll);
                    break;
                }
            }

            @Override
            public ScriptTextContainer getContainer() {
                return GuiScriptTextArea.this.container;
            }

            @Override
            public void setTextWithoutUndo(String newText) {
                boolean wasUndoing = GuiScriptTextArea.this.undoing;
                GuiScriptTextArea.this.undoing = true;
                this.setText(newText);
                GuiScriptTextArea.this.undoing = wasUndoing;
            }

            @Override
            public void pushUndoState(String textState, int cursor) {
                if (!GuiScriptTextArea.this.undoing) {
                    GuiScriptTextArea.this.undoList.add(new UndoData(textState, cursor));
                    GuiScriptTextArea.this.redoList.clear();
                }
            }

            @Override
            public int getViewportWidth() {
                return GuiScriptTextArea.this.field_146218_h - LINE_NUMBER_GUTTER_WIDTH - 8;
            }
        });
        this.autocompleteManager.setInsertCallback(new AutocompleteManager.InsertCallback(){

            @Override
            public void insertText(String text, int startPosition) {
                String fullText = GuiScriptTextArea.this.field_146216_j;
                int cursorPos = GuiScriptTextArea.this.selection.getCursorPosition();
                int start = Math.max(0, Math.min(startPosition, fullText.length()));
                int cursor = Math.max(start, Math.min(cursorPos, fullText.length()));
                String before = fullText.substring(0, start);
                String after = fullText.substring(cursor);
                GuiScriptTextArea.this.func_146180_a(before + text + after);
                GuiScriptTextArea.this.selection.reset(start + text.length());
                GuiScriptTextArea.this.scrollToCursor();
            }

            @Override
            public void replaceTextRange(String text, int startPosition, int endPosition) {
                String fullText = GuiScriptTextArea.this.field_146216_j;
                int start = Math.max(0, Math.min(startPosition, fullText.length()));
                int end = Math.max(start, Math.min(endPosition, fullText.length()));
                String before = fullText.substring(0, start);
                String after = fullText.substring(end);
                GuiScriptTextArea.this.func_146180_a(before + text + after);
                GuiScriptTextArea.this.selection.reset(start + text.length());
                GuiScriptTextArea.this.scrollToCursor();
            }

            @Override
            public void addImport(String importPath) {
                GuiScriptTextArea.this.addAndSortImport(importPath);
            }

            @Override
            public int getCursorPosition() {
                return GuiScriptTextArea.this.selection.getCursorPosition();
            }

            @Override
            public void setCursorPosition(int position) {
                GuiScriptTextArea.this.selection.reset(Math.max(0, Math.min(position, GuiScriptTextArea.this.field_146216_j.length())));
                GuiScriptTextArea.this.scrollToCursor();
            }

            @Override
            public String getText() {
                return GuiScriptTextArea.this.field_146216_j;
            }

            @Override
            public int[] getCursorScreenPosition() {
                int cursorLine = GuiScriptTextArea.this.getCursorLineIndex();
                int cursorCol = 0;
                if (GuiScriptTextArea.this.container != null && ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines != null && cursorLine < ((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.size()) {
                    JavaTextContainer.LineData ld = (JavaTextContainer.LineData)((GuiScriptTextArea)GuiScriptTextArea.this).container.lines.get(cursorLine);
                    int cursorOffset = GuiScriptTextArea.this.selection.getCursorPosition() - ld.start;
                    ScriptLine sl = GuiScriptTextArea.this.container.getDocument() != null ? GuiScriptTextArea.this.container.getDocument().getLine(cursorLine) : null;
                    cursorCol = sl != null ? sl.getRenderedWidth(0, Math.min(cursorOffset, ld.text.length())) : ClientProxy.Font.width(ld.text.substring(0, Math.min(cursorOffset, ld.text.length())));
                }
                int screenX = GuiScriptTextArea.this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + cursorCol;
                int lineY = cursorLine - GuiScriptTextArea.this.scroll.getScrolledLine();
                int screenY = GuiScriptTextArea.this.y + lineY * (GuiScriptTextArea.this.container != null ? ((GuiScriptTextArea)GuiScriptTextArea.this).container.lineHeight : 12);
                return new int[]{screenX, screenY};
            }

            @Override
            public int[] getViewportDimensions() {
                Minecraft mc = Minecraft.func_71410_x();
                ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
                return new int[]{sr.func_78326_a(), sr.func_78328_b()};
            }
        });
    }

    public boolean fullscreen() {
        return GuiScriptInterface.isFullscreen;
    }

    @Override
    public void drawTextBox(int xMouse, int yMouse) {
        int posY;
        int i;
        int[] span;
        if (!this.field_146220_v) {
            return;
        }
        this.clampSelectionBounds();
        if (this.container != null && this.container.linesCount > 0) {
            int maxLineNum = this.container.linesCount;
            String maxLineStr = "" + maxLineNum;
            int digitWidth = ClientProxy.Font.width(maxLineStr);
            LINE_NUMBER_GUTTER_WIDTH = digitWidth + 10 + 12;
        }
        int offset = this.fullscreen() ? 2 : 1;
        GuiScriptTextArea.func_73734_a((int)(this.x - offset), (int)(this.y - offset - searchBar.getTotalHeight()), (int)(this.x + this.field_146218_h + offset), (int)(this.y + this.field_146219_i + offset), (int)ScriptColorScheme.getBackgroundStyle().getBorderColor());
        int searchHeight = searchBar.getTotalHeight();
        int viewportX = this.x + LINE_NUMBER_GUTTER_WIDTH;
        GuiScriptTextArea.func_73734_a((int)this.x, (int)this.y, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH), (int)(this.y + this.field_146219_i), (int)ScriptColorScheme.getBackgroundStyle().getGutterColor());
        GuiScriptTextArea.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH), (int)this.y, (int)(this.x + this.field_146218_h), (int)(this.y + this.field_146219_i), (int)ScriptColorScheme.getBackgroundStyle().getBackgroundColor());
        GuiScriptTextArea.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH - 1), (int)this.y, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH), (int)(this.y + this.field_146219_i), (int)ScriptColorScheme.getBackgroundStyle().getGutterSeparatorColor());
        GL11.glEnable((int)3089);
        this.scissorViewport();
        this.container.visibleLines = this.field_146219_i / this.container.lineHeight;
        int maxScroll = Math.max(0, this.getPaddedLineCount() - this.container.visibleLines);
        int wheelDelta = ((GuiNPCInterface)((Object)this.listener)).mouseScroll = Mouse.getDWheel();
        if (this.listener instanceof GuiNPCInterface) {
            ((GuiNPCInterface)((Object)this.listener)).mouseScroll = wheelDelta;
            if (wheelDelta != 0 && this.hoverState.isTooltipVisible()) {
                if (this.hoverState.isMouseOverTooltip(xMouse, yMouse)) {
                    this.hoverState.scrollTooltip(wheelDelta);
                    wheelDelta = 0;
                } else {
                    this.hoverState.clearHover();
                }
            }
            if (wheelDelta == 0 || !this.autocompleteManager.isVisible() || !this.autocompleteManager.mouseScrolled(xMouse, yMouse, wheelDelta)) {
                boolean canScroll;
                boolean bl = canScroll = !this.KEYS_OVERLAY.isVisible() || this.KEYS_OVERLAY.isVisible() && !this.KEYS_OVERLAY.aboveOverlay;
                if (wheelDelta != 0 && canScroll) {
                    int previousScrollLine = this.scroll.getScrolledLine();
                    this.scroll.applyWheelScroll(wheelDelta, maxScroll);
                    if (this.scroll.getScrolledLine() != previousScrollLine) {
                        this.hoverState.clearHover();
                    }
                }
            }
        }
        if (this.scroll.isClickScrolling()) {
            int prevLine = this.scroll.getScrolledLine();
            this.scroll.handleClickScrolling(yMouse, this.x, this.y, this.field_146219_i, this.container.visibleLines, this.getPaddedLineCount(), maxScroll);
            if (this.scroll.getScrolledLine() != prevLine) {
                this.hoverState.clearHover();
            }
        }
        if (this.hoverState.isDraggingScrollbar()) {
            if (Mouse.isButtonDown((int)0)) {
                this.hoverState.updateScrollbarDrag(yMouse);
            } else {
                this.hoverState.releaseScrollbarDrag();
            }
        }
        if (this.hoverState.isDraggingTooltip()) {
            if (Mouse.isButtonDown((int)0)) {
                this.hoverState.updateTooltipDrag(xMouse, yMouse);
            } else {
                this.hoverState.releaseTooltipDrag();
            }
        }
        if (this.hoverState.isResizingTooltip()) {
            if (Mouse.isButtonDown((int)0)) {
                this.hoverState.updateTooltipResize(xMouse, yMouse);
            } else {
                this.hoverState.releaseTooltipResize();
            }
        }
        if (this.autocompleteManager.isDraggingScrollbarMenu()) {
            if (Mouse.isButtonDown((int)0)) {
                this.autocompleteManager.updateScrollbarDrag(yMouse);
            } else {
                this.autocompleteManager.releaseScrollbarDrag();
            }
        }
        if (this.autocompleteManager.isDraggingPanel()) {
            if (Mouse.isButtonDown((int)0)) {
                this.autocompleteManager.updatePanelDrag(xMouse, yMouse);
            } else {
                this.autocompleteManager.releasePanelDrag();
            }
        }
        if (this.autocompleteManager.isResizingPanel()) {
            if (Mouse.isButtonDown((int)0)) {
                this.autocompleteManager.updatePanelResize(xMouse, yMouse);
            } else {
                this.autocompleteManager.releasePanelResize();
            }
        }
        this.scroll.initializeIfNeeded(this.scroll.getScrolledLine());
        this.scroll.update(maxScroll);
        this.updateHoverState(xMouse, yMouse);
        if (this.clicked) {
            this.clicked = Mouse.isButtonDown((int)0);
            int i2 = this.getSelectionPos(xMouse, yMouse);
            if (i2 != this.selection.getCursorPosition()) {
                if (this.doubleClicked || this.tripleClicked) {
                    this.selection.reset(this.selection.getCursorPosition());
                    this.doubleClicked = false;
                    this.tripleClicked = false;
                }
                this.setCursor(i2, true);
            }
        } else if (this.doubleClicked || this.tripleClicked) {
            this.doubleClicked = false;
            this.tripleClicked = false;
        }
        int startBracket = 0;
        int endBracket = 0;
        if (!(this.selection.getStartSelection() < 0 || this.field_146216_j == null || this.field_146216_j.length() <= 0 || this.selection.getEndSelection() - this.selection.getStartSelection() != 1 && this.selection.hasSelection() || (span = BracketMatcher.findBracketSpanAt(this.field_146216_j, this.selection.getStartSelection())) == null)) {
            startBracket = span[0];
            endBracket = span[1];
        }
        ArrayList<JavaTextContainer.LineData> list = new ArrayList<JavaTextContainer.LineData>(this.container.lines);
        List<int[]> braceSpans = BracketMatcher.computeBraceSpans(this.field_146216_j, list);
        List<Integer> unmatchedBraces = BracketMatcher.findUnmatchedBracePositions(this.field_146216_j);
        int highlightedOpenLine = -1;
        int highlightedCloseLine = -1;
        if (startBracket != endBracket && startBracket >= 0) {
            int bracketLineIdx = -1;
            boolean isCurlyBracket = false;
            char bc = this.field_146216_j.charAt(startBracket);
            if (this.field_146216_j != null && startBracket >= 0 && startBracket < this.field_146216_j.length() && (bc == '{' || bc == '}')) {
                isCurlyBracket = true;
            }
            for (int li = 0; li < list.size(); ++li) {
                JavaTextContainer.LineData ld = (JavaTextContainer.LineData)list.get(li);
                if (startBracket < ld.start || startBracket >= ld.end) continue;
                bracketLineIdx = li;
                break;
            }
            if (bracketLineIdx >= 0 && isCurlyBracket) {
                int bestSize = Integer.MAX_VALUE;
                boolean foundExact = false;
                char bracketChar = bc;
                for (int[] span2 : braceSpans) {
                    boolean exactMatch;
                    int openLine = span2[1];
                    int closeLine = span2[2];
                    if (bracketLineIdx < openLine || bracketLineIdx > closeLine) continue;
                    int size = closeLine - openLine;
                    boolean bl = exactMatch = bracketChar == '{' && openLine == bracketLineIdx || bracketChar == '}' && closeLine == bracketLineIdx;
                    if (exactMatch) {
                        if (foundExact && size >= bestSize) continue;
                        foundExact = true;
                        bestSize = size;
                        highlightedOpenLine = openLine;
                        highlightedCloseLine = closeLine;
                        continue;
                    }
                    if (foundExact || size >= bestSize) continue;
                    bestSize = size;
                    highlightedOpenLine = openLine;
                    highlightedCloseLine = closeLine;
                }
            }
        }
        this.highlightedWord = null;
        if (this.selection.hasSelection()) {
            Matcher m = this.container.regexWord.matcher(this.field_146216_j);
            while (m.find()) {
                if (m.start() != this.selection.getStartSelection() || m.end() != this.selection.getEndSelection()) continue;
                this.highlightedWord = this.field_146216_j.substring(this.selection.getStartSelection(), this.selection.getEndSelection());
            }
        }
        double fracOffset = this.scroll.getFractionalOffset();
        float fracPixels = (float)(fracOffset * (double)this.container.lineHeight);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-fracPixels), (float)0.0f);
        int renderStart = Math.max(0, this.scroll.getScrolledLine() - 1);
        int renderEnd = (int)Math.min((float)(list.size() - 1), (float)(this.scroll.getScrolledLine() + this.container.visibleLines) + fracPixels + 1.0f);
        int stringYOffset = 2;
        for (i = renderStart; i <= renderEnd; ++i) {
            int posY2 = this.y + (i - this.scroll.getScrolledLine()) * this.container.lineHeight + stringYOffset;
            String lineNum = "" + (i + 1);
            int lineNumWidth = ClientProxy.Font.width(lineNum);
            int lineNumX = this.x + LINE_NUMBER_GUTTER_WIDTH - lineNumWidth - 5 - 12;
            int lineNumY = posY2 + 1;
            int lineNumColor = ScriptColorScheme.getBackgroundStyle().getLineNumberColor();
            if (this.active && this.isEnabled()) {
                for (int li = 0; li < list.size(); ++li) {
                    JavaTextContainer.LineData ld = (JavaTextContainer.LineData)list.get(li);
                    if ((this.selection.getCursorPosition() < ld.start || this.selection.getCursorPosition() >= ld.end) && (li != list.size() - 1 || this.selection.getCursorPosition() != this.field_146216_j.length()) || li != i) continue;
                    lineNumColor = ScriptColorScheme.getBackgroundStyle().getLineNumberActiveColor();
                    break;
                }
            }
            ClientProxy.Font.drawString(lineNum, lineNumX, lineNumY, lineNumColor);
        }
        for (i = renderStart; i <= renderEnd; ++i) {
            int e;
            int s;
            JavaTextContainer.LineData data = (JavaTextContainer.LineData)list.get(i);
            ScriptLine scriptLine = this.container.getDocument().getLine(i);
            String line = data.text;
            int w = line.length();
            posY = this.y + (i - this.scroll.getScrolledLine()) * this.container.lineHeight;
            if (i < renderStart || i > renderEnd) continue;
            if (startBracket != endBracket) {
                int e2;
                int s2;
                if (startBracket >= data.start && startBracket < data.end) {
                    s2 = scriptLine.getRenderedWidth(0, startBracket - data.start);
                    e2 = scriptLine.getRenderedWidth(0, startBracket - data.start + 1) + 1;
                    GuiScriptTextArea.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s2), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e2), (int)(posY + this.container.lineHeight + 0), (int)-1728001024);
                }
                if (endBracket >= data.start && endBracket < data.end) {
                    s2 = scriptLine.getRenderedWidth(0, endBracket - data.start);
                    e2 = scriptLine.getRenderedWidth(0, endBracket - data.start + 1) + 1;
                    GuiScriptTextArea.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s2), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e2), (int)(posY + this.container.lineHeight + 0), (int)-1728001024);
                }
            }
            if (unmatchedBraces != null && !unmatchedBraces.isEmpty()) {
                for (int ubPos : unmatchedBraces) {
                    if (ubPos < data.start || ubPos >= data.end) continue;
                    int rel = ubPos - data.start;
                    int s3 = scriptLine.getRenderedWidth(0, rel);
                    int e3 = scriptLine.getRenderedWidth(0, rel + 1) + 1;
                    GuiScriptTextArea.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s3), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e3), (int)(posY + this.container.lineHeight), (int)-3407872);
                }
            }
            if (this.highlightedWord != null) {
                Matcher m = this.container.regexWord.matcher(line);
                while (m.find()) {
                    if (!line.substring(m.start(), m.end()).equals(this.highlightedWord)) continue;
                    int s4 = scriptLine.getRenderedWidth(0, m.start());
                    int e4 = scriptLine.getRenderedWidth(0, m.end()) + 1;
                    GuiScriptTextArea.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s4), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e4), (int)(posY + this.container.lineHeight), (int)-1728033792);
                }
            }
            if (searchBar.isVisible()) {
                List<int[]> searchMatches = searchBar.getMatches();
                int currentMatchIdx = searchBar.getCurrentMatchIndex();
                for (int mi = 0; mi < searchMatches.size(); ++mi) {
                    int highlightColor;
                    int matchEnd;
                    int matchStart;
                    int[] match = searchMatches.get(mi);
                    if (match[1] <= data.start || match[0] >= data.end || (matchStart = Math.max(match[0] - data.start, 0)) >= (matchEnd = Math.min(match[1] - data.start, line.length()))) continue;
                    s = scriptLine.getRenderedWidth(0, matchStart);
                    e = scriptLine.getRenderedWidth(0, matchEnd) + 1;
                    boolean isExcluded = searchBar.isMatchExcluded(mi);
                    int n = highlightColor = mi == currentMatchIdx ? -1153136385 : 0x662266AA;
                    if (isExcluded) {
                        highlightColor = 0x33666666;
                    }
                    int highlightX = this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s;
                    int highlightEndX = this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e;
                    GuiScriptTextArea.func_73734_a((int)highlightX, (int)posY, (int)highlightEndX, (int)(posY + this.container.lineHeight), (int)highlightColor);
                    if (!isExcluded) continue;
                    int strikeY = posY + this.container.lineHeight / 2;
                    GuiScriptTextArea.func_73734_a((int)highlightX, (int)strikeY, (int)highlightEndX, (int)(strikeY + 1), (int)-5618620);
                }
            }
            if (this.renameHandler.isActive()) {
                List<int[]> renameOccurrences = this.renameHandler.getOccurrences();
                for (int[] occ : renameOccurrences) {
                    boolean isEmpty;
                    if (occ[1] < data.start || occ[0] > data.end) continue;
                    int occStart = Math.max(occ[0] - data.start, 0);
                    int occEnd = Math.min(occ[1] - data.start, line.length());
                    boolean bl = isEmpty = occ[0] == occ[1];
                    if (occStart > occEnd) continue;
                    s = scriptLine.getRenderedWidth(0, occStart);
                    e = isEmpty ? s + 2 : scriptLine.getRenderedWidth(0, occEnd) + 1;
                    int occX = this.x + LINE_NUMBER_GUTTER_WIDTH + s;
                    int occEndX = this.x + LINE_NUMBER_GUTTER_WIDTH + 2 + e;
                    boolean isPrimary = this.renameHandler.isPrimaryOccurrence(occ[0]);
                    int bgColor = isPrimary ? 0x55335577 : 857883750;
                    GuiScriptTextArea.func_73734_a((int)occX, (int)posY, (int)occEndX, (int)(posY + this.container.lineHeight), (int)bgColor);
                    if (!isPrimary) continue;
                    int borderColor = -570425345;
                    GuiScriptTextArea.func_73734_a((int)occX, (int)posY, (int)occEndX, (int)(posY + this.container.lineHeight), (int)0x33FF0000);
                    GuiScriptTextArea.func_73734_a((int)occX, (int)posY, (int)occEndX, (int)(posY + 1), (int)borderColor);
                    GuiScriptTextArea.func_73734_a((int)occX, (int)(posY + this.container.lineHeight - 1), (int)occEndX, (int)(posY + this.container.lineHeight), (int)borderColor);
                    GuiScriptTextArea.func_73734_a((int)occX, (int)posY, (int)(occX + 1), (int)(posY + this.container.lineHeight), (int)borderColor);
                    GuiScriptTextArea.func_73734_a((int)(occEndX - 1), (int)posY, (int)occEndX, (int)(posY + this.container.lineHeight), (int)borderColor);
                    if (!this.renameHandler.shouldShowCursor()) continue;
                    int cursorInWord = this.renameHandler.getCursorInWord();
                    String currentWord = this.renameHandler.getCurrentWord();
                    if (currentWord == null || cursorInWord < 0 || cursorInWord > currentWord.length()) continue;
                    String beforeCursor = currentWord.substring(0, Math.min(cursorInWord, currentWord.length()));
                    int n = occX + scriptLine.getRenderedWidth(occStart, occStart + beforeCursor.length());
                }
            }
            if (this.active && this.isEnabled() && (this.selection.getCursorPosition() >= data.start && this.selection.getCursorPosition() < data.end || i == list.size() - 1 && this.selection.getCursorPosition() == this.field_146216_j.length())) {
                GuiScriptTextArea.func_73734_a((int)this.x, (int)posY, (int)(this.x + this.field_146218_h - 1), (int)(posY + this.container.lineHeight), (int)0x22E0E0E0);
            }
            if (this.selection.hasSelection() && this.selection.getEndSelection() > data.start && this.selection.getStartSelection() <= data.end && this.selection.getStartSelection() < data.end) {
                int s5 = scriptLine.getRenderedWidth(0, Math.max(this.selection.getStartSelection() - data.start, 0));
                int e5 = scriptLine.getRenderedWidth(0, Math.min(this.selection.getEndSelection() - data.start, w)) + 1;
                GuiScriptTextArea.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s5), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e5), (int)(posY + this.container.lineHeight), (int)-1725861121);
            }
            if (i == Math.max(0, this.scroll.getScrolledLine()) && !braceSpans.isEmpty()) {
                int visStart = Math.max(0, this.scroll.getScrolledLine());
                int visEnd = Math.min(list.size() - 1, visStart + this.container.visibleLines - 0);
                for (int[] span3 : braceSpans) {
                    int endLine;
                    int startLine;
                    int depth;
                    int originalDepth = span3[0];
                    int openLine = span3[1];
                    int closeLine = span3[2];
                    int n = depth = span3.length > 3 ? span3[3] : originalDepth;
                    if (originalDepth <= 1 || (startLine = openLine + 1) > (endLine = closeLine - 1) || endLine < visStart || startLine > visEnd) continue;
                    int drawStart = Math.max(startLine, visStart);
                    int drawEnd = Math.min(endLine, visEnd);
                    int safeDepth = Math.max(1, depth);
                    int spaces = (safeDepth - 1) * 4;
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < spaces; ++k) {
                        sb.append(' ');
                    }
                    int px = ClientProxy.Font.width(sb.toString());
                    int gx = this.x + LINE_NUMBER_GUTTER_WIDTH + 4 + px - 2;
                    boolean highlighted = openLine == highlightedOpenLine && closeLine == highlightedCloseLine;
                    int guideColor = highlighted ? -1724658688 : 0x33FFFFFF;
                    int topY = this.y + (drawStart - this.scroll.getScrolledLine()) * this.container.lineHeight;
                    int bottomY = this.y + (endLine - this.scroll.getScrolledLine() + 1) * this.container.lineHeight;
                    if (highlighted) {
                        bottomY -= 2;
                    }
                    GuiScriptTextArea.func_73734_a((int)gx, (int)topY, (int)(gx + 1), (int)bottomY, (int)guideColor);
                }
            }
            int yPos = posY + stringYOffset;
            scriptLine.drawStringHex(this.x + LINE_NUMBER_GUTTER_WIDTH + 1, yPos);
            boolean recentInput = this.selection.hadRecentInput();
            if (!this.active || !this.isEnabled() || !recentInput && this.cursorCounter / 10 % 2 != 0 || (this.selection.getCursorPosition() < data.start || this.selection.getCursorPosition() >= data.end) && (i != list.size() - 1 || this.selection.getCursorPosition() != this.field_146216_j.length())) continue;
            int posX = this.x + LINE_NUMBER_GUTTER_WIDTH + scriptLine.getRenderedWidth(0, Math.min(this.selection.getCursorPosition() - data.start, line.length()));
            GuiScriptTextArea.func_73734_a((int)(posX + 1), (int)posY, (int)(posX + 2), (int)(posY + this.container.lineHeight), (int)ScriptColorScheme.getBackgroundStyle().getCaretColor());
        }
        if (this.container != null && this.container.getDocument() != null) {
            this.hoveredGutterMethod = GutterIconRenderer.renderIcons(this.container.lineHeight, this.x + LINE_NUMBER_GUTTER_WIDTH - 12 + 1, this.y, renderStart, renderEnd, this.scroll.getScrolledLine(), stringYOffset, this.container.getDocument().getAllMethods(), this.container.lines, xMouse, yMouse, fracPixels);
        }
        GL11.glPopMatrix();
        GL11.glDisable((int)3089);
        if (this.hasVerticalScrollbar()) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(GuiCustomScroll.resource);
            int effLines = Math.max(1, this.getPaddedLineCount());
            int sbSize = Math.max((int)(1.0f * (float)this.container.visibleLines / (float)effLines * (float)this.field_146219_i), 2);
            int posX = this.x + this.field_146218_h - 6;
            double linesCount = effLines;
            posY = (int)((double)this.y + 1.0 * this.scroll.getScrollPos() / linesCount * (double)(this.field_146219_i - 4)) + 1;
            GuiScriptTextArea.func_73734_a((int)posX, (int)posY, (int)(posX + 5), (int)(posY + sbSize + 2), (int)ScriptColorScheme.getBackgroundStyle().getScrollbarColor());
        }
        if (this.parent != null) {
            this.parent.fullscreenButton.draw(xMouse, yMouse);
        }
        GuiScriptTextArea.searchBar.ownerGui = this.parent;
        searchBar.draw(xMouse, yMouse);
        this.goToLineDialog.draw(xMouse, yMouse);
        this.KEYS_OVERLAY.draw(xMouse, yMouse, wheelDelta);
        this.autocompleteManager.getMenu().ownerGui = this.parent;
        this.autocompleteManager.draw(xMouse, yMouse);
        if (this.hoverState.isTooltipVisible()) {
            int xOffset = this.hasVerticalScrollbar() ? -8 : -2;
            int viewportWidth = this.field_146218_h - LINE_NUMBER_GUTTER_WIDTH;
            int viewportY = this.y;
            int viewportHeight = this.field_146219_i;
            TokenHoverRenderer.render(this.hoverState, viewportX, viewportWidth + xOffset, viewportY, viewportHeight, this.parent);
        }
        if (this.hoveredGutterMethod != null) {
            GutterIconRenderer.renderTooltip(this.hoveredGutterMethod, xMouse, yMouse, this.x, this.field_146218_h, this.y, this.field_146219_i);
        }
    }

    private void scissorViewport() {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int scaleFactor = sr.func_78325_e();
        double panX = this.parent != null ? this.parent.getPanX() : 0.0;
        double panY = this.parent != null ? this.parent.getPanY() : 0.0;
        int scissorX = (int)(((double)this.x - panX) * (double)scaleFactor);
        int scissorY = (int)(((double)sr.func_78328_b() - ((double)this.y - panY + (double)this.field_146219_i)) * (double)scaleFactor);
        int scissorW = this.field_146218_h * scaleFactor;
        int scissorH = this.field_146219_i * scaleFactor;
        GL11.glScissor((int)scissorX, (int)scissorY, (int)scissorW, (int)scissorH);
    }

    private int getSelectionPos(int xMouse, int yMouse) {
        xMouse -= this.x + LINE_NUMBER_GUTTER_WIDTH + 1;
        double fracPixels = this.scroll.getFractionalOffset() * (double)this.container.lineHeight;
        double yMouseD = (double)(yMouse -= this.y + 1) + fracPixels;
        ArrayList list = new ArrayList(this.container.lines);
        for (int i = 0; i < list.size(); ++i) {
            double yPos;
            JavaTextContainer.LineData data = (JavaTextContainer.LineData)list.get(i);
            if (i < this.scroll.getScrolledLine() || i > this.scroll.getScrolledLine() + this.container.visibleLines + 1 || !(yMouseD >= (yPos = (double)((i - this.scroll.getScrolledLine()) * this.container.lineHeight))) || !(yMouseD < yPos + (double)this.container.lineHeight)) continue;
            ScriptLine scriptLine = this.container.getDocument() != null ? this.container.getDocument().getLine(i) : null;
            int lineWidth = 0;
            char[] chars = data.text.toCharArray();
            for (int j = 1; j <= chars.length; ++j) {
                int w;
                int n = w = scriptLine != null ? scriptLine.getRenderedWidth(0, j) : ClientProxy.Font.width(data.text.substring(0, j));
                if (xMouse < lineWidth + (w - lineWidth) / 2) {
                    return data.start + j - 1;
                }
                lineWidth = w;
            }
            int posAfterChars = data.start + chars.length;
            return Math.min(posAfterChars, this.field_146216_j.length());
        }
        return this.container.text.length();
    }

    private int getCursorLineIndex() {
        return this.selection.getCursorLineIndex(this.container.lines, this.field_146216_j != null ? this.field_146216_j.length() : 0);
    }

    private Object[] getTokenAtScreenPosition(int xMouse, int yMouse) {
        if (this.container == null || !(this.container instanceof ScriptTextContainer)) {
            return null;
        }
        ScriptTextContainer scriptContainer = this.container;
        int viewportX = this.x + LINE_NUMBER_GUTTER_WIDTH + 1;
        if (xMouse < viewportX || xMouse > this.x + this.field_146218_h || yMouse < this.y || yMouse > this.y + this.field_146219_i) {
            return null;
        }
        int relativeY = yMouse - this.y;
        double fracOffset = this.scroll.getFractionalOffset();
        double fracPixels = fracOffset * (double)this.container.lineHeight;
        double adjustedY = (double)relativeY + fracPixels;
        int lineIdx = this.scroll.getScrolledLine() + (int)(adjustedY / (double)this.container.lineHeight);
        if (lineIdx < 0 || lineIdx >= this.container.lines.size()) {
            return null;
        }
        ScriptLine lineData = this.container.getDocument().getLine(lineIdx);
        String lineText = lineData.getText();
        int lineStart = lineData.getGlobalStart();
        int globalMouseX = this.getSelectionPos(xMouse, yMouse);
        Token token = lineData.getTokenAt(globalMouseX, t -> t.getType() != TokenType.DEFAULT);
        if (token == null) {
            return null;
        }
        int tokenLocalStart = token.getGlobalStart() - lineStart;
        int tokenLocalEnd = token.getGlobalEnd() - lineStart;
        tokenLocalStart = Math.max(0, Math.min(tokenLocalStart, lineText.length()));
        tokenLocalEnd = Math.max(0, Math.min(tokenLocalEnd, lineText.length()));
        int tokenScreenX = viewportX + lineData.getRenderedWidth(0, tokenLocalStart);
        int tokenScreenY = this.y + (lineIdx - this.scroll.getScrolledLine()) * this.container.lineHeight - (int)fracPixels;
        int tokenWidth = lineData.getRenderedWidth(tokenLocalStart, tokenLocalEnd);
        return new Object[]{token, tokenScreenX, tokenScreenY, tokenWidth};
    }

    private void updateHoverState(int xMouse, int yMouse) {
        if (this.hoverState.isDraggingTooltip()) {
            this.hoverState.setLastMousePosition(xMouse, yMouse);
            return;
        }
        if (this.hoverState.isResizingTooltip()) {
            this.hoverState.setLastMousePosition(xMouse, yMouse);
            return;
        }
        if (this.hoverState.hasOverriddenPosition() || this.hoverState.hasOverriddenSize()) {
            this.hoverState.setLastMousePosition(xMouse, yMouse);
            return;
        }
        if (this.hoverState.isTooltipVisible() && this.hoverState.isMouseOverTooltip(xMouse, yMouse)) {
            this.hoverState.setLastMousePosition(xMouse, yMouse);
            return;
        }
        if (!this.isEnabled() || this.clicked || searchBar.isVisible() || this.goToLineDialog.isVisible() || this.KEYS_OVERLAY.isVisible() || this.renameHandler.isActive() || this.autocompleteManager.isVisible()) {
            this.hoverState.clearHover();
            return;
        }
        Object[] tokenInfo = this.getTokenAtScreenPosition(xMouse, yMouse);
        if (tokenInfo != null) {
            Token token = (Token)tokenInfo[0];
            int tokenScreenX = (Integer)tokenInfo[1];
            int tokenScreenY = (Integer)tokenInfo[2];
            int tokenWidth = (Integer)tokenInfo[3];
            this.hoverState.update(xMouse, yMouse, token, tokenScreenX, tokenScreenY, tokenWidth);
        } else {
            if (this.hoverState.isMouseOverTooltip(xMouse, yMouse)) {
                this.hoverState.setLastMousePosition(xMouse, yMouse);
                return;
            }
            this.hoverState.update(xMouse, yMouse, null, 0, 0, 0);
        }
    }

    private void scrollToCursor() {
        if (this.container == null || this.container.lines == null || this.container.lines.isEmpty()) {
            return;
        }
        int lineIdx = this.getCursorLineIndex();
        int visible = Math.max(1, this.container.visibleLines);
        int effectiveVisible = Math.max(1, visible - this.bottomPaddingLines);
        int maxScroll = Math.max(0, this.getPaddedLineCount() - visible);
        this.scroll.scrollToLine(lineIdx, effectiveVisible, maxScroll);
    }

    private void initializeKeyBindings() {
        Supplier<Boolean> isActive = () -> this.active && this.isEnabled() && !this.KEYS_OVERLAY.isVisible();
        Supplier<Boolean> openBoxes = () -> !this.KEYS_OVERLAY.isVisible();
        GuiScriptTextArea.KEYS.CUT.setTask(e -> {
            int pos;
            int cursor;
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            if (this.selection.hasSelection()) {
                NoppesStringUtils.setClipboardContents(this.selection.getSelectedText(this.field_146216_j));
                String s = this.getSelectionBeforeText();
                this.func_146180_a(s + this.getSelectionAfterText());
                this.selection.reset(s.length());
                this.scrollToCursor();
                return;
            }
            if (this.field_146216_j == null || this.field_146216_j.isEmpty()) {
                return;
            }
            int start = cursor = this.selection.getCursorPosition();
            for (pos = cursor - 1; pos >= 0; --pos) {
                char ch = this.field_146216_j.charAt(pos);
                if (ch != '.' && ch != '!' && ch != '?' && ch != '\n') continue;
                start = pos + 1;
                break;
            }
            while (start < cursor && Character.isWhitespace(this.field_146216_j.charAt(start))) {
                ++start;
            }
            int end = cursor;
            for (pos = cursor; pos < this.field_146216_j.length(); ++pos) {
                char ch = this.field_146216_j.charAt(pos);
                if (ch != '.' && ch != '!' && ch != '?' && ch != '\n') continue;
                end = pos + 1;
                break;
            }
            if (end == cursor) {
                end = this.field_146216_j.length();
            }
            while (end > start && Character.isWhitespace(this.field_146216_j.charAt(end - 1))) {
                --end;
            }
            if (start >= end) {
                int ls = this.field_146216_j.lastIndexOf(10, Math.max(0, cursor - 1));
                start = ls == -1 ? 0 : ls + 1;
                int le = this.field_146216_j.indexOf(10, cursor);
                int n = end = le == -1 ? this.field_146216_j.length() : le;
            }
            if (start < end) {
                String cut = this.field_146216_j.substring(start, end);
                NoppesStringUtils.setClipboardContents(cut);
                this.func_146180_a(this.field_146216_j.substring(0, start) + this.field_146216_j.substring(end));
                this.selection.reset(start);
                this.scrollToCursor();
            }
        });
        GuiScriptTextArea.KEYS.COPY.setTask(e -> {
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            if (this.selection.hasSelection()) {
                NoppesStringUtils.setClipboardContents(this.selection.getSelectedText(this.field_146216_j));
                this.lastCopyWasLine = false;
                this.lastCopiedLineText = null;
            } else {
                int cursor = this.selection.getCursorPosition();
                JavaTextContainer.LineData targetLine = null;
                for (JavaTextContainer.LineData line : this.container.lines) {
                    if (cursor < line.start || cursor > line.end) continue;
                    targetLine = line;
                    break;
                }
                if (targetLine != null) {
                    int safeStart = Math.max(0, Math.min(targetLine.start, this.field_146216_j.length()));
                    int safeEnd = Math.max(safeStart, Math.min(targetLine.end, this.field_146216_j.length()));
                    String lineText = this.field_146216_j.substring(safeStart, safeEnd);
                    NoppesStringUtils.setClipboardContents(lineText);
                    this.lastCopyWasLine = true;
                    this.lastCopiedLineText = lineText;
                }
            }
        });
        GuiScriptTextArea.KEYS.PASTE.setTask(e -> {
            JavaTextContainer.LineData currentLine;
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            String clipboardRaw = NoppesStringUtils.getClipboardContents();
            if (clipboardRaw == null) {
                clipboardRaw = "";
            }
            boolean isLinePaste = this.lastCopyWasLine && this.lastCopiedLineText != null && clipboardRaw.equals(this.lastCopiedLineText);
            String clipboard = FormatHelper.sanitizeClipboard(clipboardRaw, 16);
            if (this.selection.hasSelection()) {
                this.addText(clipboard);
                this.lastCopyWasLine = false;
                this.lastCopiedLineText = null;
                this.scrollToCursor();
                return;
            }
            if (isLinePaste && this.container != null && this.container.lines != null && (currentLine = this.selection.findCurrentLine(this.container.lines)) != null) {
                int insertPos = Math.max(0, Math.min(currentLine.end, this.field_146216_j.length()));
                String insertText = clipboard;
                if (insertPos > 0 && this.field_146216_j.charAt(insertPos - 1) != '\n') {
                    insertText = "\n" + insertText;
                }
                if (insertPos < this.field_146216_j.length() && !insertText.endsWith("\n")) {
                    insertText = insertText + "\n";
                }
                String newText = this.field_146216_j.substring(0, insertPos) + insertText + this.field_146216_j.substring(insertPos);
                this.func_146180_a(newText);
                int newCursor = insertPos + (insertText.startsWith("\n") ? 1 : 0);
                this.selection.reset(Math.min(newCursor, newText.length()));
                this.scrollToCursor();
                return;
            }
            this.addText(clipboard);
            this.scrollToCursor();
        });
        GuiScriptTextArea.KEYS.UNDO.setTask(e -> {
            if (!e.isPress() && !e.isHold() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            if (searchBar.hasFocus()) {
                searchBar.undo();
            } else {
                if (this.undoList.isEmpty()) {
                    return;
                }
                this.undoing = true;
                this.redoList.add(new UndoData(this.field_146216_j, this.selection.getCursorPosition()));
                UndoData data = this.undoList.remove(this.undoList.size() - 1);
                this.func_146180_a(data.text);
                this.selection.reset(data.cursorPosition);
                this.undoing = false;
                this.scrollToCursor();
                searchBar.updateMatches();
                if (!this.active) {
                    this.active = true;
                }
            }
        });
        GuiScriptTextArea.KEYS.REDO.setTask(e -> {
            if (!e.isPress() && !e.isHold() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            if (searchBar.hasFocus()) {
                searchBar.redo();
            } else {
                if (this.redoList.isEmpty()) {
                    return;
                }
                this.undoing = true;
                this.undoList.add(new UndoData(this.field_146216_j, this.selection.getCursorPosition()));
                UndoData data = this.redoList.remove(this.redoList.size() - 1);
                this.func_146180_a(data.text);
                this.selection.reset(data.cursorPosition);
                this.undoing = false;
                this.scrollToCursor();
                searchBar.updateMatches();
                if (!this.active) {
                    this.active = true;
                }
            }
        });
        GuiScriptTextArea.KEYS.FORMAT.setTask(e -> {
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            this.formatText();
        });
        GuiScriptTextArea.KEYS.TOGGLE_COMMENT.setTask(e -> {
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            if (this.selection.hasSelection()) {
                this.toggleCommentSelection();
            } else {
                this.toggleCommentLineAtCursor();
            }
        });
        GuiScriptTextArea.KEYS.DUPLICATE.setTask(e -> {
            block5: {
                block4: {
                    if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                        return;
                    }
                    if (!this.selection.hasSelection()) break block4;
                    JavaTextContainer.LineData firstLine = null;
                    JavaTextContainer.LineData lastLine = null;
                    for (JavaTextContainer.LineData line : this.container.lines) {
                        if (line.end <= this.selection.getStartSelection() || line.start >= this.selection.getEndSelection()) continue;
                        if (firstLine == null) {
                            firstLine = line;
                        }
                        lastLine = line;
                    }
                    if (firstLine == null || lastLine == null) break block5;
                    String selectedText = this.field_146216_j.substring(firstLine.start, lastLine.end);
                    int savedStart = this.selection.getStartSelection();
                    int savedEnd = this.selection.getEndSelection();
                    int insertAt = lastLine.end;
                    this.func_146180_a(this.field_146216_j.substring(0, insertAt) + selectedText + this.field_146216_j.substring(insertAt));
                    this.selection.setStartSelection(savedStart);
                    this.selection.setEndSelection(savedEnd);
                    this.selection.setCursorPositionDirect(savedEnd);
                    break block5;
                }
                for (JavaTextContainer.LineData line : this.container.lines) {
                    int safeEnd;
                    if (this.selection.getCursorPosition() < line.start || this.selection.getCursorPosition() > line.end) continue;
                    int safeStart = Math.max(0, Math.min(line.start, this.field_146216_j.length()));
                    String lineText = this.field_146216_j.substring(safeStart, safeEnd = Math.max(safeStart, Math.min(line.end, this.field_146216_j.length())));
                    String insertText = lineText.endsWith("\n") ? lineText : "\n" + lineText;
                    int insertionPoint = Math.min(line.end, this.field_146216_j.length());
                    this.func_146180_a(this.field_146216_j.substring(0, insertionPoint) + insertText + this.field_146216_j.substring(insertionPoint));
                    int newCursor = insertionPoint + insertText.length() - (insertText.endsWith("\n") ? 1 : 0);
                    this.selection.reset(Math.max(0, Math.min(newCursor, this.field_146216_j.length())));
                    break;
                }
            }
        });
        GuiScriptTextArea.KEYS.DELETE_LINE.setTask(e -> {
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            if (this.field_146216_j == null || this.field_146216_j.isEmpty()) {
                return;
            }
            int cursor = this.selection.getCursorPosition();
            JavaTextContainer.LineData targetLine = null;
            int targetIndex = -1;
            for (JavaTextContainer.LineData line : this.container.lines) {
                if (cursor < line.start || cursor > line.end) continue;
                targetLine = line;
                targetIndex = this.container.lines.indexOf(line);
                break;
            }
            if (targetLine == null) {
                return;
            }
            int start = Math.max(0, Math.min(targetLine.start, this.field_146216_j.length()));
            int end = Math.max(start, Math.min(targetLine.end, this.field_146216_j.length()));
            String newText = this.field_146216_j.substring(0, start) + this.field_146216_j.substring(end);
            this.func_146180_a(newText);
            int newCursor = 0;
            if (targetIndex > 0 && targetIndex <= this.container.lines.size() - 1) {
                JavaTextContainer.LineData previousLine = (JavaTextContainer.LineData)this.container.lines.get(targetIndex - 1);
                int prevStart = Math.max(0, Math.min(previousLine.start, newText.length()));
                int prevEnd = Math.max(prevStart, Math.min(previousLine.end, newText.length()));
                int prevLen = Math.max(0, prevEnd - prevStart);
                int offsetInLine = Math.max(0, Math.min(cursor - targetLine.start, prevLen));
                newCursor = Math.min(prevStart + offsetInLine, newText.length());
            }
            this.selection.reset(Math.min(newCursor, newText.length()));
            this.scrollToCursor();
        });
        GuiScriptTextArea.KEYS.MOVE_LINE_UP.setThrottleInterval(50L).setTask(e -> {
            if (!e.isPress() && !e.isHold() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            if (this.field_146216_j == null || this.field_146216_j.isEmpty()) {
                return;
            }
            int tLen = this.field_146216_j.length();
            int cursor = Math.max(0, Math.min(this.selection.getCursorPosition(), tLen));
            int currStart = this.field_146216_j.lastIndexOf(10, Math.max(0, cursor - 1));
            int n = currStart = currStart == -1 ? 0 : currStart + 1;
            if (currStart == 0) {
                return;
            }
            int currEnd = this.field_146216_j.indexOf(10, cursor);
            currEnd = currEnd == -1 ? tLen : currEnd + 1;
            int prevEnd = currStart;
            int prevStart = this.field_146216_j.lastIndexOf(10, Math.max(0, prevEnd - 2));
            prevStart = prevStart == -1 ? 0 : prevStart + 1;
            String previousText = this.field_146216_j.substring(prevStart, prevEnd);
            String currentText = this.field_146216_j.substring(currStart, currEnd);
            if (!currentText.endsWith("\n") && previousText.endsWith("\n") && !previousText.isEmpty()) {
                currentText = currentText + "\n";
                previousText = previousText.substring(0, previousText.length() - 1);
            }
            String before = this.field_146216_j.substring(0, prevStart);
            String after = this.field_146216_j.substring(currEnd);
            String newText = before + currentText + previousText + after;
            this.func_146180_a(newText);
            int currentContentLen = currentText.endsWith("\n") ? Math.max(0, currentText.length() - 1) : currentText.length();
            int offsetInLine = Math.max(0, Math.min(cursor - currStart, currentContentLen));
            int newCursor = Math.min(prevStart + offsetInLine, newText.length());
            this.selection.reset(newCursor);
            this.scrollToCursor();
        });
        GuiScriptTextArea.KEYS.MOVE_LINE_DOWN.setThrottleInterval(50L).setTask(e -> {
            if (!e.isPress() && !e.isHold() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            if (this.field_146216_j == null || this.field_146216_j.isEmpty()) {
                return;
            }
            int tLen = this.field_146216_j.length();
            int cursor = Math.max(0, Math.min(this.selection.getCursorPosition(), tLen));
            int currStart = this.field_146216_j.lastIndexOf(10, Math.max(0, cursor - 1));
            currStart = currStart == -1 ? 0 : currStart + 1;
            int currEnd = this.field_146216_j.indexOf(10, cursor);
            int n = currEnd = currEnd == -1 ? tLen : currEnd + 1;
            if (currEnd >= tLen) {
                return;
            }
            int nextStart = currEnd;
            int nextEnd = this.field_146216_j.indexOf(10, nextStart);
            nextEnd = nextEnd == -1 ? tLen : nextEnd + 1;
            String currentText = this.field_146216_j.substring(currStart, currEnd);
            String nextText = this.field_146216_j.substring(nextStart, nextEnd);
            if (!nextText.endsWith("\n") && currentText.endsWith("\n") && !currentText.isEmpty()) {
                currentText = currentText.substring(0, currentText.length() - 1);
                nextText = nextText + "\n";
            }
            String before = this.field_146216_j.substring(0, currStart);
            String after = this.field_146216_j.substring(nextEnd);
            String newText = before + nextText + currentText + after;
            this.func_146180_a(newText);
            int currentContentLen = currentText.endsWith("\n") ? Math.max(0, currentText.length() - 1) : currentText.length();
            int offsetInLine = Math.max(0, Math.min(cursor - currStart, currentContentLen));
            int newCursor = Math.min(currStart + nextText.length() + offsetInLine, newText.length());
            this.selection.reset(newCursor);
            this.scrollToCursor();
        });
        GuiScriptTextArea.KEYS.SEARCH.setTask(e -> {
            if (!e.isPress() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            this.unfocusAll();
            searchBar.openSearch();
        });
        GuiScriptTextArea.KEYS.SEARCH_REPLACE.setTask(e -> {
            if (!e.isPress() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            this.unfocusAll();
            searchBar.openSearchReplace();
        });
        GuiScriptTextArea.KEYS.GO_TO_LINE.setTask(e -> {
            if (!e.isPress() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            this.unfocusAll();
            this.goToLineDialog.toggle();
        });
        GuiScriptTextArea.KEYS.RENAME.setTask(e -> {
            if (!e.isPress() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            if (!this.renameHandler.isActive()) {
                this.unfocusAll();
                this.active = true;
                this.renameHandler.startRename();
            }
        });
        GuiScriptTextArea.KEYS.AUTOCOMPLETE.setTask(e -> {
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            this.autocompleteManager.triggerExplicit();
        });
    }

    public void unfocusAll() {
        if (searchBar.hasFocus()) {
            searchBar.unfocus();
        }
        if (this.goToLineDialog.hasFocus()) {
            this.goToLineDialog.unfocus();
        }
        if (this.renameHandler.isActive()) {
            this.renameHandler.cancel();
        }
        if (this.autocompleteManager.isVisible()) {
            this.autocompleteManager.dismiss();
        }
    }

    @Override
    public boolean func_146201_a(char c, int i) {
        if (this.KEYS_OVERLAY.keyTyped(c, i)) {
            return true;
        }
        if (this.renameHandler.isActive() && this.renameHandler.keyTyped(c, i)) {
            return true;
        }
        if (this.goToLineDialog.isVisible() && this.goToLineDialog.keyTyped(c, i)) {
            return true;
        }
        if (searchBar.isVisible() && searchBar.keyTyped(c, i)) {
            return true;
        }
        if (this.autocompleteManager.isVisible() && this.autocompleteManager.keyPressed(i)) {
            return true;
        }
        if (i == 1 && this.hoverState.isTooltipVisible() && (this.hoverState.hasOverriddenPosition() || this.hoverState.hasOverriddenSize())) {
            this.hoverState.clearHover();
            return true;
        }
        if (KEYS.hasMatchingKeyPressed(i)) {
            return false;
        }
        if (!this.active) {
            return false;
        }
        if (this.isKeyComboCtrlA(i)) {
            this.selection.selectAll(this.field_146216_j.length());
            return true;
        }
        if (!this.isEnabled()) {
            return false;
        }
        if (this.handleNavigationKeys(i)) {
            return true;
        }
        if (this.handleInsertionKeys(i)) {
            return true;
        }
        if (this.handleDeletionKeys(i)) {
            return true;
        }
        if (this.handleCharacterInput(c)) {
            return true;
        }
        return true;
    }

    private boolean handleNavigationKeys(int i) {
        if (i == 203) {
            int j = 1;
            if (GuiScreen.func_146271_m()) {
                int pos;
                int g = pos = this.selection.getCursorPosition();
                IntPredicate isWordChar = ch -> Character.isLetterOrDigit(ch) || ch == 95;
                if (pos > 0) {
                    char left = this.field_146216_j.charAt(pos - 1);
                    if (Character.isWhitespace(left)) {
                        while (g - 1 >= 0 && Character.isWhitespace(this.field_146216_j.charAt(g - 1))) {
                            --g;
                        }
                    } else if (isWordChar.test(left)) {
                        while (g - 1 >= 0 && isWordChar.test(this.field_146216_j.charAt(g - 1))) {
                            --g;
                        }
                    } else {
                        while (g - 1 >= 0 && !Character.isWhitespace(this.field_146216_j.charAt(g - 1)) && !isWordChar.test(this.field_146216_j.charAt(g - 1))) {
                            --g;
                        }
                    }
                }
                j = Math.max(1, pos - g);
            }
            int newPos = Math.max(this.selection.getCursorPosition() - j, 0);
            this.setCursor(newPos, GuiScreen.func_146272_n());
            if (this.autocompleteManager.isVisible()) {
                this.autocompleteManager.onCursorMove(this.field_146216_j, newPos);
            }
            return true;
        }
        if (i == 205) {
            int j = 1;
            if (GuiScreen.func_146271_m()) {
                int end;
                int pos;
                IntPredicate isWordChar = ch -> Character.isLetterOrDigit(ch) || ch == 95;
                if (pos < this.field_146216_j.length()) {
                    char first = this.field_146216_j.charAt(pos);
                    if (Character.isWhitespace(first)) {
                        for (end = pos = this.selection.getCursorPosition(); end < this.field_146216_j.length() && Character.isWhitespace(this.field_146216_j.charAt(end)); ++end) {
                        }
                    } else if (isWordChar.test(first)) {
                        while (end < this.field_146216_j.length() && isWordChar.test(this.field_146216_j.charAt(end))) {
                            ++end;
                        }
                    } else {
                        while (end < this.field_146216_j.length() && !Character.isWhitespace(this.field_146216_j.charAt(end)) && !isWordChar.test(this.field_146216_j.charAt(end))) {
                            ++end;
                        }
                    }
                }
                j = Math.max(1, end - pos);
            }
            int newPos = Math.min(this.selection.getCursorPosition() + j, this.field_146216_j.length());
            this.setCursor(newPos, GuiScreen.func_146272_n());
            if (this.autocompleteManager.isVisible()) {
                this.autocompleteManager.onCursorMove(this.field_146216_j, newPos);
            }
            return true;
        }
        if (i == 200) {
            this.setCursor(this.cursorUp(), GuiScreen.func_146272_n());
            this.scrollToCursor();
            return true;
        }
        if (i == 208) {
            this.setCursor(this.cursorDown(), GuiScreen.func_146272_n());
            this.scrollToCursor();
            return true;
        }
        return false;
    }

    private boolean handleInsertionKeys(int i) {
        if (i == 15) {
            boolean shift = this.isShiftKeyDown();
            if (shift) {
                this.handleShiftTab();
            } else {
                this.handleTab();
            }
            this.scrollToCursor();
            return true;
        }
        if (i == 28) {
            int prevNonWs;
            int closeBetween;
            int openPos;
            char ch;
            int beforeTrimEnd;
            int cursorPos = this.selection.getCursorPosition();
            if (this.selection.hasSelection() && this.field_146216_j != null && this.selection.getSelectedText(this.field_146216_j).indexOf(10) != -1) {
                this.addText(Character.toString('\n') + this.getAutoIndentForEnter());
                this.scrollToCursor();
                return true;
            }
            String before = this.getSelectionBeforeText();
            for (beforeTrimEnd = before.length(); beforeTrimEnd > 0 && ((ch = before.charAt(beforeTrimEnd - 1)) == ' ' || ch == '\t'); --beforeTrimEnd) {
            }
            if (before.substring(0, beforeTrimEnd).endsWith("/**")) {
                int wsLen;
                String after;
                String indent = this.getLogicalLineIndentAt(Math.max(0, Math.min(cursorPos, this.field_146216_j != null ? this.field_146216_j.length() : 0)));
                String afterTrimmed = after = this.getSelectionAfterText();
                for (wsLen = 0; wsLen < afterTrimmed.length() && (afterTrimmed.charAt(wsLen) == ' ' || afterTrimmed.charAt(wsLen) == '\t'); ++wsLen) {
                }
                if (wsLen < afterTrimmed.length() && afterTrimmed.charAt(wsLen) == '\n') {
                    int closerPos;
                    int idx;
                    for (idx = wsLen + 1; idx < afterTrimmed.length() && (afterTrimmed.charAt(idx) == ' ' || afterTrimmed.charAt(idx) == '\t'); ++idx) {
                    }
                    if (afterTrimmed.startsWith("*/", idx)) {
                        String restAfterCloser = afterTrimmed.substring(Math.min(idx + 2, afterTrimmed.length()));
                        String expanded = "\n" + indent + " * \n" + indent + " */" + restAfterCloser;
                        this.setText(before + expanded, true);
                        int newCursorPos = before.length() + 1 + indent.length() + 3;
                        this.selection.reset(newCursorPos);
                        this.scrollToCursor();
                        return true;
                    }
                    if (idx < afterTrimmed.length() && afterTrimmed.charAt(idx) == '*' && (closerPos = afterTrimmed.indexOf("*/", idx)) != -1) {
                        this.addText("\n" + indent + " * ");
                        int newCursorPos = before.length() + 1 + indent.length() + 3;
                        this.selection.reset(newCursorPos);
                        this.scrollToCursor();
                        return true;
                    }
                }
                if (afterTrimmed.substring(wsLen).startsWith("*/")) {
                    String restAfterCloser = afterTrimmed.substring(wsLen + 2);
                    String expanded = "\n" + indent + " * \n" + indent + " */" + restAfterCloser;
                    this.setText(before + expanded, true);
                    int newCursorPos = before.length() + 1 + indent.length() + 3;
                    this.selection.reset(newCursorPos);
                    this.scrollToCursor();
                    return true;
                }
                String javadocStub = this.generateJSDocStub(after, indent);
                this.addText(javadocStub);
                int newCursorPos = before.length() + 1 + indent.length() + 3;
                this.selection.reset(newCursorPos);
                this.scrollToCursor();
                return true;
            }
            if (!(this.field_146216_j == null || this.field_146216_j.isEmpty() || (openPos = this.field_146216_j.lastIndexOf("/**", Math.max(0, cursorPos - 1))) == -1 || (closeBetween = this.field_146216_j.indexOf("*/", openPos + 3)) != -1 && closeBetween < cursorPos)) {
                String line;
                String trimmed;
                int lineStart = this.field_146216_j.lastIndexOf(10, Math.max(0, cursorPos - 1)) + 1;
                int lineEnd = this.field_146216_j.indexOf(10, lineStart);
                if (lineEnd == -1) {
                    lineEnd = this.field_146216_j.length();
                }
                if ((trimmed = (line = this.field_146216_j.substring(lineStart, lineEnd)).trim()).startsWith("*") && !trimmed.startsWith("*/")) {
                    int openLineStart = this.field_146216_j.lastIndexOf(10, Math.max(0, openPos - 1)) + 1;
                    int openLineEnd = this.field_146216_j.indexOf(10, openLineStart);
                    if (openLineEnd == -1) {
                        openLineEnd = this.field_146216_j.length();
                    }
                    String openLine = this.field_146216_j.substring(openLineStart, openLineEnd);
                    String baseIndent = openLine.substring(0, IndentHelper.getLineIndent(openLine));
                    this.addText("\n" + baseIndent + " * ");
                    int newCursorPos = cursorPos + 1 + baseIndent.length() + 3;
                    this.selection.reset(Math.min(newCursorPos, this.field_146216_j.length()));
                    this.scrollToCursor();
                    return true;
                }
            }
            for (prevNonWs = cursorPos - 1; prevNonWs >= 0 && prevNonWs < (this.field_146216_j != null ? this.field_146216_j.length() : 0) && Character.isWhitespace(this.field_146216_j.charAt(prevNonWs)); --prevNonWs) {
            }
            if (prevNonWs >= 0 && cursorPos <= (this.field_146216_j != null ? this.field_146216_j.length() : 0) && this.field_146216_j.charAt(prevNonWs) == '{') {
                boolean hasMatchingClose;
                String after;
                String childIndent;
                String indent;
                block32: {
                    String leadingSegment;
                    indent = this.getLogicalLineIndentAt(prevNonWs);
                    childIndent = indent + "    ";
                    after = this.getSelectionAfterText();
                    int firstNewline = after.indexOf(10);
                    String string = leadingSegment = firstNewline == -1 ? after : after.substring(0, firstNewline);
                    if (leadingSegment.trim().length() > 0) {
                        this.addText("\n" + childIndent);
                        this.scrollToCursor();
                        return true;
                    }
                    hasMatchingClose = false;
                    try {
                        int openLineIdx = -1;
                        int bracePos = prevNonWs;
                        for (int li = 0; li < this.container.lines.size(); ++li) {
                            JavaTextContainer.LineData ld = (JavaTextContainer.LineData)this.container.lines.get(li);
                            if (bracePos < ld.start || bracePos >= ld.end) continue;
                            openLineIdx = li;
                            break;
                        }
                        if (openLineIdx < 0) break block32;
                        List<int[]> spans = BracketMatcher.computeBraceSpans(this.field_146216_j, this.container.lines);
                        for (int[] span : spans) {
                            int closeIndent;
                            if (span[1] != openLineIdx) continue;
                            int closeLineIdx = span[2];
                            if (closeLineIdx < this.container.lines.size() && (closeIndent = IndentHelper.getLineIndent(((JavaTextContainer.LineData)this.container.lines.get((int)closeLineIdx)).text)) <= indent.length()) {
                                hasMatchingClose = true;
                            }
                            break;
                        }
                    }
                    catch (Exception ex) {
                        hasMatchingClose = false;
                    }
                }
                if (hasMatchingClose) {
                    this.addText("\n" + childIndent);
                    this.scrollToCursor();
                } else if (this.isLambdaOpening(prevNonWs, this.field_146216_j)) {
                    int parenPos = this.findLambdaParentParen(prevNonWs, this.field_146216_j);
                    if (parenPos >= 0) {
                        String parentIndent = this.getParentIndentForLambda(parenPos, this.container.lines);
                        String insert = "\n" + childIndent + "\n" + parentIndent + "});";
                        this.func_146180_a(before + insert + after);
                        this.selection.reset(before.length() + 1 + childIndent.length());
                        this.scrollToCursor();
                    } else {
                        String insert = "\n" + childIndent + "\n" + indent + "}";
                        this.func_146180_a(before + insert + after);
                        this.selection.reset(before.length() + 1 + childIndent.length());
                        this.scrollToCursor();
                    }
                } else {
                    String insert = "\n" + childIndent + "\n" + indent + "}";
                    this.func_146180_a(before + insert + after);
                    int newCursor = before.length() + 1 + childIndent.length();
                    this.selection.reset(newCursor);
                    this.scrollToCursor();
                }
            } else {
                this.addText(Character.toString('\n') + this.getAutoIndentForEnter());
                this.scrollToCursor();
            }
            return true;
        }
        return false;
    }

    private String generateJSDocStub(String after, String indent) {
        StringBuilder stub = new StringBuilder();
        stub.append("\n").append(indent).append(" * ");
        String trimmed = after.replaceFirst("^[\\s\\n\\r]*", "");
        Pattern funcPattern = Pattern.compile("^(?:(\\w+(?:<[^>]+>)?|function)\\s+)?(\\w+)\\s*\\(([^)]*)\\)");
        Matcher funcMatcher = funcPattern.matcher(trimmed);
        if (funcMatcher.find()) {
            boolean isJS;
            String returnOrKeyword = funcMatcher.group(1);
            String funcName = funcMatcher.group(2);
            String paramsStr = funcMatcher.group(3) != null ? funcMatcher.group(3).trim() : "";
            boolean bl = isJS = "function".equals(returnOrKeyword) || returnOrKeyword == null || this.container != null && this.container.getDocument() != null && this.container.getDocument().isJavaScript();
            if (!paramsStr.isEmpty()) {
                String[] params;
                for (String param : params = paramsStr.split(",")) {
                    String paramName;
                    if ((param = param.trim()).isEmpty()) continue;
                    String paramType = "any";
                    String[] parts = param.split("\\s+");
                    if (parts.length >= 2) {
                        paramType = parts[parts.length - 2];
                        paramName = parts[parts.length - 1];
                    } else {
                        paramName = parts[0];
                    }
                    paramName = paramName.replaceAll("[\\[\\]\\.]", "");
                    if (isJS) {
                        stub.append("\n").append(indent).append(" * @param {").append(paramType).append("} ").append(paramName);
                        continue;
                    }
                    stub.append("\n").append(indent).append(" * @param ").append(paramName);
                }
            }
            if (returnOrKeyword != null && !"function".equals(returnOrKeyword) && !"void".equals(returnOrKeyword)) {
                if (isJS) {
                    stub.append("\n").append(indent).append(" * @returns {").append(returnOrKeyword).append("}");
                } else {
                    stub.append("\n").append(indent).append(" * @return");
                }
            } else if (isJS) {
                stub.append("\n").append(indent).append(" * @returns {any}");
            }
        } else {
            Pattern fieldPattern = Pattern.compile("^(?:(?:public|private|protected|static|final|var|let|const)\\s+)*(\\w+(?:<[^>]+>)?)\\s+(\\w+)(?:\\s*=|\\s*;)");
            Matcher fieldMatcher = fieldPattern.matcher(trimmed);
            if (fieldMatcher.find()) {
                boolean isJS;
                String fieldType = fieldMatcher.group(1);
                boolean bl = isJS = this.container != null && this.container.getDocument() != null && this.container.getDocument().isJavaScript();
                if (isJS && fieldType != null) {
                    stub.append("\n").append(indent).append(" * @type {").append("any").append("}");
                }
            }
        }
        stub.append("\n").append(indent).append(" */");
        return stub.toString();
    }

    private boolean handleDeletionKeys(int i) {
        if (i == 211) {
            String s = this.getSelectionAfterText();
            if (!s.isEmpty() && !this.selection.hasSelection()) {
                s = s.substring(1);
            }
            this.setText(this.getSelectionBeforeText() + s, true);
            this.selection.reset(this.selection.getStartSelection());
            return true;
        }
        if (this.isKeyComboCtrlBackspace(i)) {
            String before = this.getSelectionBeforeText();
            if (this.selection.getStartSelection() > 0 && !this.selection.hasSelection()) {
                int pos;
                int g = pos = this.selection.getCursorPosition();
                IntPredicate isWordChar = ch -> Character.isLetterOrDigit(ch) || ch == 95;
                char left = before.charAt(pos - 1);
                if (Character.isWhitespace(left)) {
                    while (g - 1 >= 0 && Character.isWhitespace(before.charAt(g - 1))) {
                        --g;
                    }
                } else if (isWordChar.test(left)) {
                    while (g - 1 >= 0 && isWordChar.test(before.charAt(g - 1))) {
                        --g;
                    }
                } else {
                    while (g - 1 >= 0 && !Character.isWhitespace(before.charAt(g - 1)) && !isWordChar.test(before.charAt(g - 1))) {
                        --g;
                    }
                }
                before = before.substring(0, g);
                this.selection.setStartSelection(this.selection.getStartSelection() - (pos - g));
            }
            this.setText(before + this.getSelectionAfterText(), true);
            this.selection.reset(this.selection.getStartSelection());
            return true;
        }
        if (i == 14) {
            if (this.selection.hasSelection()) {
                String s = this.getSelectionBeforeText();
                this.setText(s + this.getSelectionAfterText(), true);
                this.selection.reset(this.selection.getStartSelection());
                this.scrollToCursor();
                return true;
            }
            if (this.selection.getStartSelection() <= 0) {
                return true;
            }
            JavaTextContainer.LineData currCheck = this.selection.findCurrentLine(this.container.lines);
            if (currCheck != null && currCheck.text.trim().length() == 0) {
                int removeStart = ValueUtil.clamp(currCheck.start - 1, 0, this.field_146216_j.length());
                int contentEnd = Math.min(currCheck.start + currCheck.text.length(), this.field_146216_j.length());
                String before = this.field_146216_j.substring(0, removeStart);
                String after = this.field_146216_j.substring(contentEnd);
                this.setText(before + after, true);
                int newCursor = Math.max(0, removeStart);
                this.selection.reset(newCursor);
                this.scrollToCursor();
                return true;
            }
            JavaTextContainer.LineData curr = this.selection.findCurrentLine(this.container.lines);
            if (curr != null && curr.start > 0) {
                int col = this.selection.getCursorPosition() - curr.start;
                int actualIndent = IndentHelper.getLineIndent(curr.text);
                int expectedIndent = IndentHelper.getExpectedIndent(curr, this.container.lines);
                if (col <= expectedIndent) {
                    boolean lineHasContent = curr.text.trim().length() > 0;
                    int newlinePos = curr.start - 1;
                    if (!lineHasContent) {
                        int removeEnd = this.field_146216_j.indexOf(10, curr.start);
                        removeEnd = removeEnd == -1 ? this.field_146216_j.length() : ++removeEnd;
                        String before = this.field_146216_j.substring(0, curr.start);
                        String after = removeEnd <= this.field_146216_j.length() ? this.field_146216_j.substring(removeEnd) : "";
                        this.setText(before + after, true);
                        int newCursor = Math.max(0, curr.start - 1);
                        this.selection.reset(newCursor);
                        this.scrollToCursor();
                        return true;
                    }
                    int contentStart = curr.start + actualIndent;
                    String before = newlinePos >= 0 ? this.field_146216_j.substring(0, newlinePos) : "";
                    String content = contentStart <= this.field_146216_j.length() ? this.field_146216_j.substring(contentStart) : "";
                    String spacer = "";
                    if (before.length() > 0 && content.length() > 0) {
                        char lastChar = before.charAt(before.length() - 1);
                        char firstChar = content.charAt(0);
                        if (!Character.isWhitespace(lastChar) && lastChar != '{' && lastChar != '(' && lastChar != '[' && firstChar != '}' && firstChar != ')' && firstChar != ']' && firstChar != ';' && firstChar != ',' && firstChar != '.' && firstChar != '\n') {
                            spacer = " ";
                        }
                    }
                    this.setText(before + spacer + content, true);
                    int newCursor = before.length() + spacer.length();
                    this.selection.reset(newCursor);
                    this.scrollToCursor();
                    return true;
                }
            }
            if (this.selection.getStartSelection() > 0 && this.selection.getStartSelection() < this.field_146216_j.length()) {
                char prev = this.field_146216_j.charAt(this.selection.getStartSelection() - 1);
                char nextc = this.field_146216_j.charAt(this.selection.getStartSelection());
                if (prev == '(' && nextc == ')' || prev == '[' && nextc == ']' || prev == '{' && nextc == '}' || prev == '\'' && nextc == '\'' || prev == '\"' && nextc == '\"') {
                    String before = this.field_146216_j.substring(0, this.selection.getStartSelection() - 1);
                    String after = this.selection.getStartSelection() + 1 < this.field_146216_j.length() ? this.field_146216_j.substring(this.selection.getStartSelection() + 1) : "";
                    this.setText(before + after, true);
                    this.selection.setStartSelection(this.selection.getStartSelection() - 1);
                    this.selection.reset(this.selection.getStartSelection());
                    this.scrollToCursor();
                    return true;
                }
            }
            String s = this.getSelectionBeforeText();
            s = s.substring(0, s.length() - 1);
            this.selection.setStartSelection(this.selection.getStartSelection() - 1);
            this.setText(s + this.getSelectionAfterText(), true);
            this.selection.reset(this.selection.getStartSelection());
            this.scrollToCursor();
            this.autocompleteManager.onDeleteKey(this.field_146216_j, this.selection.getCursorPosition());
            return true;
        }
        return false;
    }

    private boolean handleShortcutKeys(int i) {
        if (this.isKeyComboCtrlX(i)) {
            if (this.selection.hasSelection()) {
                NoppesStringUtils.setClipboardContents(this.selection.getSelectedText(this.field_146216_j));
                String s = this.getSelectionBeforeText();
                this.func_146180_a(s + this.getSelectionAfterText());
                this.selection.reset(s.length());
                this.scrollToCursor();
            }
            return true;
        }
        if (this.isKeyComboCtrlC(i)) {
            if (this.selection.hasSelection()) {
                NoppesStringUtils.setClipboardContents(this.selection.getSelectedText(this.field_146216_j));
            }
            return true;
        }
        if (this.isKeyComboCtrlV(i)) {
            this.addText(NoppesStringUtils.getClipboardContents());
            this.scrollToCursor();
            return true;
        }
        if (i == 44 && GuiScreen.func_146271_m()) {
            if (this.undoList.isEmpty()) {
                return false;
            }
            this.undoing = true;
            this.redoList.add(new UndoData(this.field_146216_j, this.selection.getCursorPosition()));
            UndoData data = this.undoList.remove(this.undoList.size() - 1);
            this.func_146180_a(data.text);
            this.selection.reset(data.cursorPosition);
            this.undoing = false;
            this.scrollToCursor();
            return true;
        }
        if (i == 21 && GuiScreen.func_146271_m()) {
            if (this.redoList.isEmpty()) {
                return false;
            }
            this.undoing = true;
            this.undoList.add(new UndoData(this.field_146216_j, this.selection.getCursorPosition()));
            UndoData data = this.redoList.remove(this.redoList.size() - 1);
            this.func_146180_a(data.text);
            this.selection.reset(data.cursorPosition);
            this.undoing = false;
            this.scrollToCursor();
            return true;
        }
        if (i == 33 && GuiScreen.func_146271_m()) {
            this.formatText();
            return true;
        }
        if (i == 53 && GuiScreen.func_146271_m()) {
            if (this.selection.hasSelection()) {
                this.toggleCommentSelection();
            } else {
                this.toggleCommentLineAtCursor();
            }
            return true;
        }
        if (i == 32 && GuiScreen.func_146271_m()) {
            if (this.selection.hasSelection()) {
                JavaTextContainer.LineData firstLine = null;
                JavaTextContainer.LineData lastLine = null;
                for (JavaTextContainer.LineData line : this.container.lines) {
                    if (line.end <= this.selection.getStartSelection() || line.start >= this.selection.getEndSelection()) continue;
                    if (firstLine == null) {
                        firstLine = line;
                    }
                    lastLine = line;
                }
                if (firstLine != null && lastLine != null) {
                    String selectedText;
                    String insertText = selectedText = this.field_146216_j.substring(firstLine.start, lastLine.end);
                    int savedStart = this.selection.getStartSelection();
                    int savedEnd = this.selection.getEndSelection();
                    int insertAt = lastLine.end;
                    this.func_146180_a(this.field_146216_j.substring(0, insertAt) + insertText + this.field_146216_j.substring(insertAt));
                    this.selection.setStartSelection(savedStart);
                    this.selection.setEndSelection(savedEnd);
                    this.selection.setCursorPositionDirect(savedEnd);
                    return true;
                }
            } else {
                for (JavaTextContainer.LineData line : this.container.lines) {
                    if (this.selection.getCursorPosition() < line.start || this.selection.getCursorPosition() > line.end) continue;
                    int lineStart = line.start;
                    int lineEnd = line.end;
                    String lineText = this.field_146216_j.substring(lineStart, lineEnd);
                    String insertText = lineText.endsWith("\n") ? lineText : "\n" + lineText;
                    int insertionPoint = lineEnd;
                    this.func_146180_a(this.field_146216_j.substring(0, insertionPoint) + insertText + this.field_146216_j.substring(insertionPoint));
                    int newCursor = insertionPoint + insertText.length() - (insertText.endsWith("\n") ? 1 : 0);
                    this.selection.reset(Math.max(0, Math.min(newCursor, this.field_146216_j.length())));
                    return true;
                }
            }
            return true;
        }
        return false;
    }

    private boolean handleCharacterInput(char c) {
        if (ChatAllowedCharacters.func_71566_a((char)c)) {
            String before = this.getSelectionBeforeText();
            String after = this.getSelectionAfterText();
            int cursorPos = this.selection.getCursorPosition();
            if (c == '<' && !this.container.getDocument().isExcludedInclusive(cursorPos) && this.isGenericContext(before)) {
                if (this.hasUnmatchedCloserAhead(after, '<', '>')) {
                    this.setText(before + "<" + after, true);
                    this.selection.reset(before.length() + 1);
                } else {
                    this.setText(before + "<>" + after, true);
                    this.selection.reset(before.length() + 1);
                }
                this.scrollToCursor();
                this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                return true;
            }
            if (c == '>' && after.length() > 0 && after.charAt(0) == '>' && this.shouldSkipAngleBracketClose(before)) {
                this.selection.reset(before.length() + 1);
                this.scrollToCursor();
                this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                return true;
            }
            if ((c == ')' || c == ']' || c == '}') && after.length() > 0 && after.charAt(0) == c) {
                this.selection.reset(before.length() + 1);
                this.scrollToCursor();
                this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                return true;
            }
            if (c == '\"' || c == '\'') {
                boolean allowAutoPair;
                if (after.length() > 0 && after.charAt(0) == c) {
                    this.selection.reset(before.length() + 1);
                    this.scrollToCursor();
                    this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                    return true;
                }
                boolean bl = allowAutoPair = !this.container.getDocument().isExcludedInclusive(cursorPos) && !this.isLikelyClosingQuote(before, c);
                if (allowAutoPair) {
                    if (c == '\"') {
                        this.setText(before + "\"\"" + after, true);
                    } else {
                        this.setText(before + "''" + after, true);
                    }
                    this.selection.reset(before.length() + 1);
                    this.scrollToCursor();
                    this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                    return true;
                }
                this.addText(Character.toString(c));
                this.scrollToCursor();
                this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                return true;
            }
            if (!this.container.getDocument().isExcludedInclusive(cursorPos)) {
                if (c == '[') {
                    if (this.hasUnmatchedCloserAhead(after, '[', ']')) {
                        this.setText(before + c + after, true);
                        this.selection.reset(before.length() + 1);
                        this.scrollToCursor();
                        this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                        return true;
                    }
                    this.setText(before + "[]" + after, true);
                    this.selection.reset(before.length() + 1);
                    this.scrollToCursor();
                    this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                    return true;
                }
                if (c == '(') {
                    if (this.hasUnmatchedCloserAhead(after, '(', ')')) {
                        this.setText(before + c + after, true);
                        this.selection.reset(before.length() + 1);
                        this.scrollToCursor();
                        this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                        return true;
                    }
                    this.setText(before + "()" + after, true);
                    this.selection.reset(before.length() + 1);
                    this.scrollToCursor();
                    this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                    return true;
                }
                if (c == '{' && this.hasUnmatchedCloserAhead(after, '{', '}')) {
                    this.setText(before + c + after, true);
                    this.selection.reset(before.length() + 1);
                    this.scrollToCursor();
                    this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
                    return true;
                }
            }
            this.addText(Character.toString(c));
            this.scrollToCursor();
            this.autocompleteManager.onCharTyped(c, this.field_146216_j, cursorPos);
            return true;
        }
        return false;
    }

    private boolean isLikelyClosingQuote(String before, char quote) {
        if (before == null || before.isEmpty()) {
            return false;
        }
        int lineStart = before.lastIndexOf(10);
        int start = lineStart == -1 ? 0 : lineStart + 1;
        int count = 0;
        for (int i = start; i < before.length(); ++i) {
            if (before.charAt(i) != quote || this.isEscaped(before, i)) continue;
            ++count;
        }
        return count % 2 == 1;
    }

    private boolean isEscaped(String s, int index) {
        int backslashes = 0;
        for (int i = index - 1; i >= 0 && s.charAt(i) == '\\'; --i) {
            ++backslashes;
        }
        return backslashes % 2 == 1;
    }

    private boolean isGenericContext(String before) {
        int i;
        if (before == null || before.isEmpty()) {
            return false;
        }
        for (i = before.length() - 1; i >= 0 && (before.charAt(i) == ' ' || before.charAt(i) == '\t'); --i) {
        }
        if (i < 0) {
            return false;
        }
        char last = before.charAt(i);
        if (last == '>' || last == ')' || last == ']') {
            return true;
        }
        if (last == ',') {
            return true;
        }
        if (Character.isJavaIdentifierPart(last)) {
            int end = i + 1;
            while (i >= 0 && Character.isJavaIdentifierPart(before.charAt(i))) {
                --i;
            }
            String word = before.substring(i + 1, end);
            if (this.isComparisonKeyword(word)) {
                return false;
            }
            return !this.isNumericLiteral(word);
        }
        return false;
    }

    private boolean isComparisonKeyword(String word) {
        return "if".equals(word) || "while".equals(word) || "for".equals(word) || "return".equals(word) || "else".equals(word) || "case".equals(word) || "assert".equals(word) || "throw".equals(word) || "true".equals(word) || "false".equals(word) || "null".equals(word);
    }

    private boolean isNumericLiteral(String word) {
        if (word.isEmpty()) {
            return false;
        }
        char first = word.charAt(0);
        return first >= '0' && first <= '9';
    }

    private boolean shouldSkipAngleBracketClose(String before) {
        if (before == null || before.isEmpty()) {
            return false;
        }
        int depth = 0;
        boolean inLineComment = false;
        boolean inBlockComment = false;
        boolean inString = false;
        boolean escape = false;
        char stringDelimiter = '\u0000';
        for (int i = 0; i < before.length(); ++i) {
            char next;
            char c = before.charAt(i);
            char c2 = next = i + 1 < before.length() ? before.charAt(i + 1) : (char)'\u0000';
            if (inString) {
                if (escape) {
                    escape = false;
                    continue;
                }
                if (c == '\\') {
                    escape = true;
                    continue;
                }
                if (c != stringDelimiter && c != '\n') continue;
                inString = false;
                continue;
            }
            if (inBlockComment) {
                if (c != '*' || next != '/') continue;
                inBlockComment = false;
                ++i;
                continue;
            }
            if (inLineComment) {
                if (c != '\n') continue;
                inLineComment = false;
                continue;
            }
            if (c == '/' && next == '/') {
                inLineComment = true;
                ++i;
                continue;
            }
            if (c == '/' && next == '*') {
                inBlockComment = true;
                ++i;
                continue;
            }
            if (c == '\"' || c == '\'') {
                inString = true;
                stringDelimiter = c;
                escape = false;
                continue;
            }
            if (c == '<') {
                ++depth;
                continue;
            }
            if (c != '>') continue;
            --depth;
        }
        return depth > 0;
    }

    private boolean hasUnmatchedCloserAhead(String after, char opener, char closer) {
        char ch;
        int balance = 0;
        int len = after.length();
        for (int idx = 0; idx < len && (ch = after.charAt(idx)) != '\n'; ++idx) {
            char next;
            if (ch == '\"' || ch == '\'') {
                return false;
            }
            if (ch == '/' && idx + 1 < len && ((next = after.charAt(idx + 1)) == '/' || next == '*')) {
                return false;
            }
            if (ch == opener) {
                ++balance;
                continue;
            }
            if (ch != closer) continue;
            if (balance == 0) {
                return true;
            }
            --balance;
        }
        return false;
    }

    private String getLogicalLineIndentAt(int pos) {
        int lineStart;
        char ch;
        int p;
        if (this.field_146216_j == null || this.field_146216_j.isEmpty()) {
            return "";
        }
        int safePos = Math.max(0, Math.min(pos, this.field_146216_j.length()));
        for (p = lineStart = this.field_146216_j.lastIndexOf(10, Math.max(0, safePos - 1)) + 1; p < this.field_146216_j.length() && ((ch = this.field_146216_j.charAt(p)) == ' ' || ch == '\t'); ++p) {
        }
        return this.field_146216_j.substring(lineStart, p);
    }

    private boolean isLambdaOpening(int bracePos, String fullText) {
        int scan;
        if (fullText == null || bracePos <= 1) {
            return false;
        }
        for (scan = bracePos - 1; scan >= 0 && (fullText.charAt(scan) == ' ' || fullText.charAt(scan) == '\t'); --scan) {
        }
        if (scan < 1) {
            return false;
        }
        char c1 = fullText.charAt(scan - 1);
        char c2 = fullText.charAt(scan);
        return c1 == '-' && c2 == '>' || c1 == '=' && c2 == '>';
    }

    private int findLambdaParentParen(int bracePos, String fullText) {
        if (fullText == null || bracePos <= 0) {
            return -1;
        }
        int parenDepth = 0;
        int braceDepth = 0;
        boolean inString = false;
        boolean inBlockComment = false;
        char stringDelim = '\u0000';
        for (int i = bracePos - 1; i >= 0; --i) {
            char prev;
            char c = fullText.charAt(i);
            char c2 = prev = i > 0 ? fullText.charAt(i - 1) : (char)'\u0000';
            if (inBlockComment) {
                if (c != '*' || prev != '/') continue;
                inBlockComment = false;
                --i;
                continue;
            }
            if (c == '/' && prev == '*') {
                inBlockComment = true;
                --i;
                continue;
            }
            if (inString) {
                if (c != stringDelim) continue;
                int bs = 0;
                for (int j = i - 1; j >= 0 && fullText.charAt(j) == '\\'; --j) {
                    ++bs;
                }
                if (bs % 2 != 0) continue;
                inString = false;
                continue;
            }
            if (c == '\"' || c == '\'' || c == '`') {
                inString = true;
                stringDelim = c;
                continue;
            }
            if (c == '\n') {
                int lineStart = fullText.lastIndexOf(10, i - 1) + 1;
                int commentIdx = this.findLineCommentStart(fullText, lineStart, i);
                if (commentIdx < 0) continue;
                i = commentIdx;
                continue;
            }
            if (c == '}') {
                ++braceDepth;
                continue;
            }
            if (c == '{') {
                if (braceDepth > 0) {
                    --braceDepth;
                }
                if (braceDepth != 0) continue;
                return -1;
            }
            if (c == ')') {
                ++parenDepth;
                continue;
            }
            if (c != '(') continue;
            if (parenDepth > 0) {
                --parenDepth;
                continue;
            }
            return i;
        }
        return -1;
    }

    private int findLineCommentStart(String text, int lineStart, int lineEnd) {
        boolean inStr = false;
        char strDelim = '\u0000';
        for (int i = lineStart; i < lineEnd; ++i) {
            char c = text.charAt(i);
            if (inStr) {
                if (c != strDelim || i != 0 && text.charAt(i - 1) == '\\') continue;
                inStr = false;
                continue;
            }
            if (c == '\"' || c == '\'' || c == '`') {
                inStr = true;
                strDelim = c;
                continue;
            }
            if (c != '/' || i + 1 >= lineEnd || text.charAt(i + 1) != '/') continue;
            return i;
        }
        return -1;
    }

    private String getParentIndentForLambda(int parenPos, List<JavaTextContainer.LineData> lines) {
        return this.getLogicalLineIndentAt(parenPos);
    }

    private boolean isShiftKeyDown() {
        return Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54);
    }

    private void toggleCommentSelection() {
        CommentHandler.SelectionToggleResult result = CommentHandler.toggleCommentSelection(this.field_146216_j, this.container.lines, this.selection.getStartSelection(), this.selection.getEndSelection());
        this.func_146180_a(result.newText);
        this.selection.setStartSelection(result.newStartSelection);
        this.selection.setEndSelection(result.newEndSelection);
    }

    private void toggleCommentLineAtCursor() {
        CommentHandler.SingleLineToggleResult result = CommentHandler.toggleCommentAtCursor(this.field_146216_j, this.container.lines, this.selection.getCursorPosition());
        this.func_146180_a(result.newText);
        this.setCursor(result.newCursorPosition, false);
    }

    public boolean closeOnEsc() {
        return !this.KEYS_OVERLAY.isVisible() && !searchBar.isVisible() && !this.goToLineDialog.isVisible() && !this.renameHandler.isActive() && !this.autocompleteManager.isVisible() && (!this.hoverState.isTooltipVisible() || !this.hoverState.hasOverriddenPosition() && !this.hoverState.hasOverriddenSize());
    }

    private boolean isAltKeyDown() {
        return Keyboard.isKeyDown((int)56) || Keyboard.isKeyDown((int)184);
    }

    private boolean isKeyComboCtrlX(int keyID) {
        return keyID == 45 && GuiScreen.func_146271_m() && !this.isShiftKeyDown() && !this.isAltKeyDown();
    }

    private boolean isKeyComboCtrlBackspace(int keyID) {
        return keyID == 14 && GuiScreen.func_146271_m() && !this.isShiftKeyDown() && !this.isAltKeyDown();
    }

    private boolean isKeyComboCtrlV(int keyID) {
        return keyID == 47 && GuiScreen.func_146271_m() && !this.isShiftKeyDown() && !this.isAltKeyDown();
    }

    private boolean isKeyComboCtrlC(int keyID) {
        return keyID == 46 && GuiScreen.func_146271_m() && !this.isShiftKeyDown() && !this.isAltKeyDown();
    }

    private boolean isKeyComboCtrlA(int keyID) {
        return keyID == 30 && GuiScreen.func_146271_m() && !this.isShiftKeyDown() && !this.isAltKeyDown();
    }

    private String getIndentCurrentLine() {
        for (JavaTextContainer.LineData data : this.container.lines) {
            int i;
            if (this.selection.getCursorPosition() <= data.start || this.selection.getCursorPosition() > data.end) continue;
            for (i = 0; i < data.text.length() && data.text.charAt(i) == ' '; ++i) {
            }
            return data.text.substring(0, i);
        }
        return "";
    }

    private String getAutoIndentForEnter() {
        JavaTextContainer.LineData currentLine = this.selection.findCurrentLine(this.container.lines);
        if (currentLine == null) {
            return "";
        }
        return IndentHelper.getAutoIndentForEnter(currentLine.text, this.selection.getCursorPosition() - currentLine.start);
    }

    private int getTabSize() {
        return 4;
    }

    private String repeatSpace(int count) {
        return IndentHelper.spaces(count);
    }

    private void formatText() {
        int viewportPixels = this.field_146218_h - LINE_NUMBER_GUTTER_WIDTH - 10;
        int avgCharWidth = Math.max(1, ClientProxy.Font.width("abcdefghijklmnopqrstuvwxyz") / 26);
        int viewportChars = (int)((float)viewportPixels * 0.8f / (float)avgCharWidth);
        int maxLineLength = Math.max(60, Math.min(viewportChars, 120));
        IndentHelper.FormatResult result = IndentHelper.formatText(this.field_146216_j, this.selection.getCursorPosition(), maxLineLength);
        this.func_146180_a(result.text);
        this.selection.reset(Math.max(0, Math.min(result.cursorPosition, this.field_146216_j.length())));
    }

    private void handleTab() {
        JavaTextContainer.LineData currentLine = this.selection.findCurrentLine(this.container.lines);
        if (currentLine == null) {
            this.addText("    ");
            return;
        }
        int tab = this.getTabSize();
        int indentLen = IndentHelper.getLineIndent(currentLine.text);
        int textStartPos = currentLine.start + indentLen;
        if (this.selection.getCursorPosition() <= textStartPos) {
            int sepEnd;
            int targetIndent;
            if (this.selection.getCursorPosition() == textStartPos) {
                targetIndent = (indentLen / tab + 1) * tab;
            } else {
                int remainder = indentLen % tab;
                if (remainder == 0) {
                    targetIndent = indentLen + tab;
                } else {
                    int down = indentLen - remainder;
                    int up = indentLen + (tab - remainder);
                    int distUp = tab - remainder;
                    int distDown = remainder;
                    targetIndent = distUp < distDown ? up : (distUp > distDown ? down : up);
                }
            }
            if (targetIndent < 0) {
                targetIndent = 0;
            }
            String newIndent = this.repeatSpace(targetIndent);
            String rest = currentLine.text.substring(indentLen);
            String before = this.field_146216_j.substring(0, currentLine.start);
            int contentEnd = Math.min(currentLine.start + currentLine.text.length(), this.field_146216_j.length());
            String sep = contentEnd < (sepEnd = Math.min(currentLine.end, this.field_146216_j.length())) ? this.field_146216_j.substring(contentEnd, sepEnd) : "";
            String after = this.field_146216_j.substring(sepEnd);
            this.func_146180_a(before + newIndent + rest + sep + after);
            int newCursor = currentLine.start + targetIndent;
            this.selection.reset(Math.min(newCursor, this.field_146216_j.length()));
        } else {
            int column = this.selection.getCursorPosition() - currentLine.start;
            int targetColumn = (column / tab + 1) * tab;
            int toInsert = Math.max(0, targetColumn - column);
            if (toInsert > 0) {
                String spaces = this.repeatSpace(toInsert);
                this.addText(spaces);
            }
        }
    }

    private void handleShiftTab() {
        int sepEnd;
        JavaTextContainer.LineData currentLine = this.selection.findCurrentLine(this.container.lines);
        if (currentLine == null) {
            return;
        }
        int tab = this.getTabSize();
        int indentLen = IndentHelper.getLineIndent(currentLine.text);
        if (indentLen == 0) {
            return;
        }
        int targetIndent = Math.max(0, (indentLen - 1) / tab * tab);
        int removed = indentLen - targetIndent;
        String newIndent = this.repeatSpace(targetIndent);
        String rest = currentLine.text.substring(indentLen);
        String before = this.field_146216_j.substring(0, currentLine.start);
        int contentEnd = Math.min(currentLine.start + currentLine.text.length(), this.field_146216_j.length());
        String sep = contentEnd < (sepEnd = Math.min(currentLine.end, this.field_146216_j.length())) ? this.field_146216_j.substring(contentEnd, sepEnd) : "";
        String after = this.field_146216_j.substring(sepEnd);
        this.func_146180_a(before + newIndent + rest + sep + after);
        int newCursor = Math.max(currentLine.start, this.selection.getCursorPosition() - removed);
        this.selection.reset(Math.min(newCursor, this.field_146216_j.length()));
    }

    private void setCursor(int i, boolean select) {
        this.selection.setCursor(i, this.field_146216_j != null ? this.field_146216_j.length() : 0, select);
    }

    private void addText(String s) {
        int insertPos = this.selection.getStartSelection();
        this.setText(this.getSelectionBeforeText() + s + this.getSelectionAfterText(), true);
        this.selection.afterTextInsert(insertPos + s.length());
    }

    private int cursorUp() {
        return CursorNavigation.cursorUp(this.selection.getCursorPosition(), this.container.lines, this.field_146216_j);
    }

    private int cursorDown() {
        return CursorNavigation.cursorDown(this.selection.getCursorPosition(), this.container.lines, this.field_146216_j);
    }

    public String getSelectionBeforeText() {
        return this.selection.getTextBefore(this.field_146216_j);
    }

    public String getSelectionAfterText() {
        return this.selection.getTextAfter(this.field_146216_j);
    }

    @Override
    public void func_146192_a(int xMouse, int yMouse, int mouseButton) {
        if (this.hoverState.isTooltipVisible() && this.hoverState.isMouseOverTooltipPanel(xMouse, yMouse)) {
            if (mouseButton == 0) {
                if (this.hoverState.isMouseOverResizeHandle(xMouse, yMouse)) {
                    this.hoverState.startTooltipResize(xMouse, yMouse);
                } else if (this.hoverState.isMouseOverScrollbarThumb(xMouse, yMouse)) {
                    this.hoverState.startScrollbarDrag(yMouse);
                } else {
                    this.hoverState.startTooltipDrag(xMouse, yMouse);
                }
            }
            return;
        }
        if (mouseButton == 0 && (this.hoverState.hasOverriddenPosition() || this.hoverState.hasOverriddenSize()) && !this.hoverState.isMouseOverTooltipPanel(xMouse, yMouse)) {
            this.hoverState.clearHover();
        }
        if (this.autocompleteManager.isVisible() && this.autocompleteManager.mouseClicked(xMouse, yMouse, mouseButton)) {
            return;
        }
        if (this.autocompleteManager.isVisible()) {
            this.autocompleteManager.dismiss();
        }
        if (this.parent != null && this.parent.fullscreenButton.mouseClicked(xMouse, yMouse, mouseButton)) {
            return;
        }
        if (this.goToLineDialog.isVisible() && this.goToLineDialog.mouseClicked(xMouse, yMouse, mouseButton)) {
            return;
        }
        if (searchBar.isVisible() && searchBar.mouseClicked(xMouse, yMouse, mouseButton)) {
            return;
        }
        if (searchBar.isVisible()) {
            searchBar.unfocus();
        }
        if (this.KEYS_OVERLAY.mouseClicked(xMouse, yMouse, mouseButton)) {
            return;
        }
        boolean bl = this.active = xMouse >= this.x && xMouse < this.x + this.field_146218_h && yMouse >= this.y && yMouse < this.y + this.field_146219_i;
        if (this.active) {
            Object[] tokenInfo;
            if (mouseButton == 0 && GuiScreen.func_146271_m() && (tokenInfo = this.getTokenAtScreenPosition(xMouse, yMouse)) != null) {
                ScriptTypeInfo scriptType;
                Token token = (Token)tokenInfo[0];
                int targetOffset = -1;
                if (token.getMethodInfo() != null) {
                    MethodInfo methodInfo = token.getMethodInfo();
                    if (methodInfo.getNameOffset() >= 0) {
                        targetOffset = methodInfo.getNameOffset();
                    }
                } else if (token.getMethodCallInfo() != null) {
                    MethodCallInfo callInfo = token.getMethodCallInfo();
                    MethodInfo resolvedMethod = callInfo.getResolvedMethod();
                    if (resolvedMethod != null && resolvedMethod.getNameOffset() >= 0) {
                        targetOffset = resolvedMethod.getNameOffset();
                    }
                } else if (token.getFieldInfo() != null) {
                    FieldInfo fieldInfo = token.getFieldInfo();
                    if (fieldInfo.getDeclarationOffset() >= 0) {
                        targetOffset = fieldInfo.getDeclarationOffset();
                    }
                } else if (token.getFieldAccessInfo() != null) {
                    FieldAccessInfo accessInfo = token.getFieldAccessInfo();
                    FieldInfo resolvedField = accessInfo.getResolvedField();
                    if (resolvedField != null && resolvedField.getDeclarationOffset() >= 0) {
                        targetOffset = resolvedField.getDeclarationOffset();
                    }
                } else if (token.getTypeInfo() != null && token.getTypeInfo() instanceof ScriptTypeInfo && (scriptType = (ScriptTypeInfo)token.getTypeInfo()).getDeclarationOffset() >= 0) {
                    targetOffset = scriptType.getDeclarationOffset();
                }
                if (targetOffset >= 0) {
                    this.selection.reset(targetOffset);
                    this.scrollToCursor();
                    this.clicked = false;
                    activeTextfield = this;
                    return;
                }
            }
            int clickPos = this.getSelectionPos(xMouse, yMouse);
            if (this.renameHandler.isActive() && this.renameHandler.handleClick(clickPos)) {
                this.clicked = false;
                activeTextfield = this;
                return;
            }
            this.selection.reset(clickPos);
            this.selection.markActivity();
            this.clicked = mouseButton == 0;
            this.doubleClicked = false;
            this.tripleClicked = false;
            long time = System.currentTimeMillis();
            if (this.clicked && this.getPaddedLineCount() * this.container.lineHeight > this.field_146219_i && xMouse > this.x + this.field_146218_h - 8) {
                this.clicked = false;
                this.scroll.startScrollbarDrag(yMouse, this.y, this.field_146219_i, this.getPaddedLineCount());
            } else {
                this.clickCount = time - this.lastClicked < 300L ? ++this.clickCount : 1;
                if (this.clickCount == 2) {
                    this.doubleClicked = true;
                    this.selection.selectWordAtCursor(this.field_146216_j, this.container.regexWord);
                    this.clicked = false;
                } else if (this.clickCount >= 3) {
                    this.tripleClicked = true;
                    this.selection.selectLineAtCursor(this.container.lines);
                    this.clicked = false;
                    this.clickCount = 0;
                }
            }
            this.lastClicked = time;
            activeTextfield = this;
            if (mouseButton == 0 && this.hoverState.isClickToPinEnabled()) {
                Object[] tokenInfo2 = this.getTokenAtScreenPosition(xMouse, yMouse);
                if (tokenInfo2 != null) {
                    Token clickedToken = (Token)tokenInfo2[0];
                    int tokenScreenX = (Integer)tokenInfo2[1];
                    int tokenScreenY = (Integer)tokenInfo2[2];
                    int tokenWidth = (Integer)tokenInfo2[3];
                    if (this.hoverState.isPinned() && this.hoverState.getHoveredToken() == clickedToken) {
                        this.hoverState.unpin();
                    } else {
                        this.hoverState.pinToken(clickedToken, tokenScreenX, tokenScreenY, tokenWidth);
                    }
                } else if (this.hoverState.isPinned()) {
                    this.hoverState.unpin();
                }
            }
        }
    }

    public void func_146178_a() {
        KEYS.tick();
        searchBar.updateCursor();
        this.goToLineDialog.updateCursor();
        this.renameHandler.updateCursor();
        ++this.cursorCounter;
    }

    public void func_146180_a(String text) {
        this.setText(text, false);
    }

    private void setText(String text, boolean allowAtomic) {
        if (text == null) {
            return;
        }
        text = text.replace("\r", "");
        text = text.replace("\t", "    ");
        if (this.field_146216_j == null || !this.field_146216_j.equals(text)) {
            if (this.listener != null) {
                this.listener.textUpdate(text);
            }
            if (!this.undoing && allowAtomic && !this.undoList.isEmpty()) {
                char lastChar;
                boolean shouldMerge;
                long now = System.currentTimeMillis();
                int cursorPos = this.selection.getCursorPosition();
                boolean bl = shouldMerge = now - this.lastTypingTime < 2000L && Math.abs(cursorPos - this.lastTypingPos) <= 1;
                if (shouldMerge && text.length() > 0 && cursorPos > 0 && cursorPos <= text.length() && Character.isWhitespace(lastChar = text.charAt(cursorPos - 1))) {
                    shouldMerge = false;
                }
                if (!shouldMerge) {
                    this.undoList.add(new UndoData(this.field_146216_j, this.selection.getCursorPosition()));
                    this.redoList.clear();
                }
                this.lastTypingTime = now;
                this.lastTypingPos = cursorPos;
            } else if (!this.undoing) {
                this.undoList.add(new UndoData(this.field_146216_j, this.selection.getCursorPosition()));
                this.redoList.clear();
                this.lastTypingTime = System.currentTimeMillis();
                this.lastTypingPos = this.selection.getCursorPosition();
            }
            this.field_146216_j = text;
            if (this.container == null) {
                this.container = new ScriptTextContainer(text);
            }
            this.container.init(text, this.field_146218_h, this.field_146219_i);
            if (this.enableCodeHighlighting) {
                this.container.formatCodeText();
            }
            int maxScroll = Math.max(0, this.getPaddedLineCount() - this.container.visibleLines);
            this.scroll.clampToBounds(maxScroll);
            this.selection.clamp(this.field_146216_j.length());
            this.selection.markActivity();
            searchBar.updateMatches();
            this.autocompleteManager.setContainer(this.container);
        }
    }

    public String func_146179_b() {
        return this.field_146216_j;
    }

    public boolean isEnabled() {
        return this.enabled && this.field_146220_v;
    }

    public boolean hasVerticalScrollbar() {
        return this.container != null && this.container.visibleLines < this.getPaddedLineCount();
    }

    public void enableCodeHighlighting() {
        this.enableCodeHighlighting = true;
        this.container.formatCodeText();
    }

    public void setLanguage(String language) {
        if (this.container != null) {
            this.container.setLanguage(language);
            if (this.enableCodeHighlighting) {
                this.container.formatCodeText();
            }
        }
    }

    public String getLanguage() {
        return this.container != null ? this.container.getLanguage() : "ECMAScript";
    }

    public void setScriptContext(ScriptContext context) {
        if (this.container != null) {
            this.container.setScriptContext(context);
        }
    }

    public ScriptContext getScriptContext() {
        return this.container != null ? this.container.getScriptContext() : ScriptContext.GLOBAL;
    }

    public ScriptTextContainer getContainer() {
        return this.container;
    }

    public void addImplicitImports(String ... patterns) {
        if (this.container != null) {
            this.container.addImplicitImports(patterns);
        }
    }

    public void setListener(ITextChangeListener listener) {
        this.listener = listener;
    }

    private void clampSelectionBounds() {
        this.selection.clamp(this.field_146216_j != null ? this.field_146216_j.length() : 0);
    }

    public boolean isPointOnAutocompleteMenu(int mouseX, int mouseY) {
        if (this.autocompleteManager == null || !this.autocompleteManager.isVisible()) {
            return false;
        }
        AutocompleteMenu menu = this.autocompleteManager.getMenu();
        if (menu == null) {
            return false;
        }
        int menuX = menu.getX();
        int menuY = menu.getY();
        int menuWidth = menu.getWidth();
        int menuHeight = menu.getHeight();
        return mouseX >= menuX && mouseX <= menuX + menuWidth && mouseY >= menuY && mouseY <= menuY + menuHeight;
    }

    private void addAndSortImport(String importPath) {
        String newText;
        String currentText = this.field_146216_j;
        int savedCursorPos = this.selection.getCursorPosition();
        Pattern importPattern = Pattern.compile("(?m)^\\s*import\\s+(?:static\\s+)?([A-Za-z_][A-Za-z0-9_]*(?:\\s*\\.\\s*[A-Za-z_*][A-Za-z0-9_]*)*)\\s*;\\s*$");
        Matcher matcher = importPattern.matcher(currentText);
        ArrayList<ImportEntry> imports = new ArrayList<ImportEntry>();
        int firstImportStart = -1;
        int lastImportEnd = -1;
        while (matcher.find()) {
            String importStatement = matcher.group(0);
            String importPathFound = matcher.group(1).replaceAll("\\s+", "");
            if (firstImportStart == -1) {
                firstImportStart = matcher.start();
            }
            lastImportEnd = matcher.end();
            if (importPathFound.equals(importPath)) continue;
            imports.add(new ImportEntry(importPathFound, importStatement.trim()));
        }
        imports.add(new ImportEntry(importPath, "import " + importPath + ";"));
        Collections.sort(imports, new Comparator<ImportEntry>(){

            @Override
            public int compare(ImportEntry a, ImportEntry b) {
                boolean aIsJava = a.path.startsWith("java.");
                boolean aIsJavax = a.path.startsWith("javax.");
                boolean bIsJava = b.path.startsWith("java.");
                boolean bIsJavax = b.path.startsWith("javax.");
                if (aIsJava && !bIsJava) {
                    return -1;
                }
                if (!aIsJava && bIsJava) {
                    return 1;
                }
                if (aIsJavax && !bIsJavax && !bIsJava) {
                    return -1;
                }
                if (!aIsJavax && bIsJavax && !aIsJava) {
                    return 1;
                }
                return a.path.compareTo(b.path);
            }
        });
        StringBuilder importBlock = new StringBuilder();
        String prevPackage = "";
        for (ImportEntry entry : imports) {
            String topPackage;
            String string = topPackage = entry.path.contains(".") ? entry.path.substring(0, entry.path.indexOf(46)) : entry.path;
            if (!prevPackage.isEmpty() && !topPackage.equals(prevPackage)) {
                importBlock.append("\n");
            }
            importBlock.append(entry.statement).append("\n");
            prevPackage = topPackage;
        }
        int cursorAdjustment = 0;
        if (firstImportStart != -1) {
            String before = currentText.substring(0, firstImportStart);
            String after = currentText.substring(lastImportEnd);
            newText = before + importBlock.toString() + after;
            int newImportEnd = firstImportStart + importBlock.length();
            if (savedCursorPos >= lastImportEnd) {
                cursorAdjustment = newImportEnd - lastImportEnd;
            }
        } else {
            Pattern packagePattern = Pattern.compile("(?m)^\\s*package\\s+[A-Za-z_][A-Za-z0-9_.]*\\s*;\\s*$");
            Matcher pkgMatcher = packagePattern.matcher(currentText);
            int insertPos = 0;
            if (pkgMatcher.find()) {
                insertPos = pkgMatcher.end();
                newText = currentText.substring(0, insertPos) + "\n" + importBlock.toString() + "\n" + currentText.substring(insertPos);
                cursorAdjustment = importBlock.length() + 2;
            } else {
                newText = importBlock.toString() + "\n" + currentText;
                cursorAdjustment = importBlock.length() + 1;
            }
        }
        this.func_146180_a(newText);
        this.selection.reset(savedCursorPos + cursorAdjustment);
        this.scrollToCursor();
    }

    public void formatCodeText() {
        if (this.enableCodeHighlighting && this.container != null) {
            this.container.formatCodeText();
        }
    }

    public static class UndoData {
        public String text;
        public int cursorPosition;

        public UndoData(String text, int cursorPosition) {
            this.text = text;
            this.cursorPosition = cursorPosition;
        }
    }

    private static class ImportEntry {
        String path;
        String statement;

        ImportEntry(String path, String statement) {
            this.path = path;
            this.statement = statement;
        }
    }
}

