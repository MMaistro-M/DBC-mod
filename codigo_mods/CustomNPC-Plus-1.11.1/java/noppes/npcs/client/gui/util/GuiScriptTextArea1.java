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
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
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
import noppes.npcs.client.gui.util.script.GoToLineDialog;
import noppes.npcs.client.gui.util.script.IndentHelper;
import noppes.npcs.client.gui.util.script.JavaTextContainer;
import noppes.npcs.client.gui.util.script.RenameRefactorHandler;
import noppes.npcs.client.gui.util.script.ScrollState;
import noppes.npcs.client.gui.util.script.SearchReplaceBar;
import noppes.npcs.client.gui.util.script.SelectionState;
import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import noppes.npcs.client.gui.util.script.interpreter.ScriptTextContainer;
import noppes.npcs.client.key.impl.ScriptEditorKeys;
import noppes.npcs.util.ValueUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiScriptTextArea1
extends GuiNpcTextField {
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
    private int cursorCounter;
    private ITextChangeListener listener;
    private static int LINE_NUMBER_GUTTER_WIDTH = 25;
    public List<UndoData> undoList = new ArrayList<UndoData>();
    public List<UndoData> redoList = new ArrayList<UndoData>();
    public boolean undoing = false;
    public static final ScriptEditorKeys KEYS = new ScriptEditorKeys();
    public OverlayKeyPresetViewer KEYS_OVERLAY = new OverlayKeyPresetViewer(KEYS);
    public static final SearchReplaceBar searchBar = new SearchReplaceBar();
    private final GoToLineDialog goToLineDialog = new GoToLineDialog();
    private final RenameRefactorHandler renameHandler = new RenameRefactorHandler();

    private int getPaddedLineCount() {
        if (this.container == null) {
            return 0;
        }
        if (this.container.linesCount > this.container.visibleLines - this.bottomPaddingLines) {
            return Math.max(0, this.container.linesCount + this.bottomPaddingLines);
        }
        return this.container.linesCount;
    }

    public GuiScriptTextArea1(GuiScreen guiScreen, int id, int x, int y, int width, int height, String text) {
        super(id, guiScreen, x, y, width, height, null);
        this.init(x, y, width, height, text);
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
        searchBar.initGui(this.x, this.y, this.field_146218_h);
        if (searchBar.isVisible()) {
            GuiScriptTextArea1.searchBar.callback.resizeEditor(true, searchBar.getTotalHeight());
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
                return GuiScriptTextArea1.this.field_146216_j;
            }

            @Override
            public String getHighlightedWord() {
                return GuiScriptTextArea1.this.highlightedWord;
            }

            @Override
            public int getSelectionStart() {
                return GuiScriptTextArea1.this.selection.getStartSelection();
            }

            @Override
            public int getSelectionEnd() {
                return GuiScriptTextArea1.this.selection.getEndSelection();
            }

            @Override
            public void setText(String newText) {
                GuiScriptTextArea1.this.func_146180_a(newText);
            }

            @Override
            public void scrollToPosition(int position) {
                if (GuiScriptTextArea1.this.container == null || ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines == null) {
                    return;
                }
                int searchBarOffset = searchBar.getTotalHeight();
                int effectiveHeight = GuiScriptTextArea1.this.field_146219_i - searchBarOffset;
                int visibleLines = effectiveHeight / ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lineHeight;
                int linesHiddenBySRB = searchBarOffset > 0 ? (int)Math.ceil((double)searchBarOffset / (double)((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lineHeight) : 0;
                for (int i = 0; i < ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines.size(); ++i) {
                    JavaTextContainer.LineData ld = (JavaTextContainer.LineData)((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines.get(i);
                    if (position < ld.start || position >= ld.end) continue;
                    int visible = Math.max(1, visibleLines);
                    int effectiveVisible = Math.max(1, visible - GuiScriptTextArea1.this.bottomPaddingLines);
                    int maxScroll = Math.max(0, GuiScriptTextArea1.this.getPaddedLineCount() - visible);
                    int targetLine = i;
                    int currentScroll = GuiScriptTextArea1.this.scroll.getScrolledLine();
                    int firstVisibleLine = currentScroll + linesHiddenBySRB;
                    if (searchBarOffset > 0 && targetLine < firstVisibleLine) {
                        GuiScriptTextArea1.this.scroll.setTargetScroll(Math.max(0, targetLine - linesHiddenBySRB), maxScroll);
                        break;
                    }
                    GuiScriptTextArea1.this.scroll.scrollToLine(targetLine, effectiveVisible, maxScroll);
                    break;
                }
            }

            @Override
            public void setSelection(int start, int end) {
                GuiScriptTextArea1.this.selection.setSelection(start, end);
                GuiScriptTextArea1.this.selection.setCursorPositionDirect(end);
            }

            @Override
            public int getGutterWidth() {
                return LINE_NUMBER_GUTTER_WIDTH;
            }

            @Override
            public void unfocusMainEditor() {
                GuiScriptTextArea1.this.active = false;
            }

            @Override
            public void focusMainEditor() {
                GuiScriptTextArea1.this.active = true;
                searchBar.resetSelection();
            }

            @Override
            public void onMatchesUpdated() {
            }

            @Override
            public void resizeEditor(boolean open, int barHeight) {
                int targetOffset;
                int desiredOffset = Math.max(0, barHeight);
                if (!GuiScriptTextArea1.this.searchBaseInitialized) {
                    GuiScriptTextArea1.this.searchBaseY = GuiScriptTextArea1.this.y;
                    GuiScriptTextArea1.this.searchBaseHeight = GuiScriptTextArea1.this.field_146219_i;
                    GuiScriptTextArea1.this.searchAppliedOffset = 0;
                    GuiScriptTextArea1.this.searchBaseInitialized = true;
                }
                int n = targetOffset = open ? desiredOffset : 0;
                if (targetOffset == GuiScriptTextArea1.this.searchAppliedOffset) {
                    return;
                }
                int newY = GuiScriptTextArea1.this.searchBaseY + targetOffset;
                int newHeight = Math.max(12, GuiScriptTextArea1.this.searchBaseHeight - targetOffset);
                GuiScriptTextArea1.this.y = newY;
                GuiScriptTextArea1.this.field_146219_i = newHeight;
                GuiScriptTextArea1.this.searchAppliedOffset = targetOffset;
                if (GuiScriptTextArea1.this.container != null) {
                    ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.visibleLines = Math.max(GuiScriptTextArea1.this.field_146219_i / ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lineHeight - 1, 1);
                }
            }
        });
        this.goToLineDialog.setCallback(new GoToLineDialog.GoToLineCallback(){

            @Override
            public int getLineCount() {
                return GuiScriptTextArea1.this.container != null ? ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.linesCount : 0;
            }

            @Override
            public int getColumnCount(int lineIndex) {
                if (GuiScriptTextArea1.this.container == null || ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines == null || lineIndex < 0 || lineIndex >= ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines.size()) {
                    return 0;
                }
                JavaTextContainer.LineData ld = (JavaTextContainer.LineData)((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines.get(lineIndex);
                return ld.end - ld.start;
            }

            @Override
            public void goToLineColumn(int line, int column) {
                if (GuiScriptTextArea1.this.container == null || ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines == null) {
                    return;
                }
                int lineIdx = line - 1;
                if (lineIdx < 0 || lineIdx >= ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines.size()) {
                    return;
                }
                JavaTextContainer.LineData ld = (JavaTextContainer.LineData)((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines.get(lineIdx);
                int lineLength = ld.end - ld.start;
                int col = Math.max(0, Math.min(column - 1, lineLength - 1));
                int position = ld.start + col;
                GuiScriptTextArea1.this.selection.reset(position);
                int visible = GuiScriptTextArea1.this.field_146219_i / (GuiScriptTextArea1.this.container != null ? ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lineHeight : 12);
                int effectiveVisible = Math.max(1, visible - GuiScriptTextArea1.this.bottomPaddingLines);
                int maxScroll = Math.max(0, GuiScriptTextArea1.this.getPaddedLineCount() - visible);
                GuiScriptTextArea1.this.scroll.scrollToLine(lineIdx, effectiveVisible, maxScroll);
            }

            @Override
            public void unfocusMainEditor() {
                GuiScriptTextArea1.this.active = false;
            }

            @Override
            public void focusMainEditor() {
                GuiScriptTextArea1.this.active = true;
                GuiScriptTextArea1.this.selection.markActivity();
            }

            @Override
            public void onDialogClose() {
                GuiScriptTextArea1.this.active = true;
                GuiScriptTextArea1.this.selection.markActivity();
            }
        });
        this.renameHandler.setCallback(new RenameRefactorHandler.RenameCallback(){

            @Override
            public String getText() {
                return GuiScriptTextArea1.this.field_146216_j;
            }

            @Override
            public void setText(String newText) {
                GuiScriptTextArea1.this.func_146180_a(newText);
            }

            @Override
            public List<JavaTextContainer.LineData> getLines() {
                return GuiScriptTextArea1.this.container != null ? ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines : new ArrayList();
            }

            @Override
            public int getCursorPosition() {
                return GuiScriptTextArea1.this.selection.getCursorPosition();
            }

            @Override
            public SelectionState getSelectionState() {
                return GuiScriptTextArea1.this.selection;
            }

            @Override
            public void setCursorPosition(int pos) {
                GuiScriptTextArea1.this.selection.reset(pos);
            }

            @Override
            public void unfocusMainEditor() {
                GuiScriptTextArea1.this.active = false;
            }

            @Override
            public void focusMainEditor() {
                GuiScriptTextArea1.this.active = true;
                GuiScriptTextArea1.this.selection.markActivity();
            }

            @Override
            public int getGutterWidth() {
                return LINE_NUMBER_GUTTER_WIDTH;
            }

            @Override
            public int getLineHeight() {
                return GuiScriptTextArea1.this.container != null ? ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lineHeight : 12;
            }

            @Override
            public int getScrolledLine() {
                return GuiScriptTextArea1.this.scroll.getScrolledLine();
            }

            @Override
            public double getFractionalOffset() {
                return GuiScriptTextArea1.this.scroll.getFractionalOffset();
            }

            @Override
            public void scrollToPosition(int pos) {
                if (GuiScriptTextArea1.this.container == null || ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines == null) {
                    return;
                }
                for (int i = 0; i < ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines.size(); ++i) {
                    JavaTextContainer.LineData ld = (JavaTextContainer.LineData)((GuiScriptTextArea1)GuiScriptTextArea1.this).container.lines.get(i);
                    if (pos < ld.start || pos >= ld.end) continue;
                    int visible = Math.max(1, ((GuiScriptTextArea1)GuiScriptTextArea1.this).container.visibleLines);
                    int effectiveVisible = Math.max(1, visible - GuiScriptTextArea1.this.bottomPaddingLines);
                    int maxScroll = Math.max(0, GuiScriptTextArea1.this.getPaddedLineCount() - visible);
                    GuiScriptTextArea1.this.scroll.scrollToLine(i, effectiveVisible, maxScroll);
                    break;
                }
            }

            @Override
            public ScriptTextContainer getContainer() {
                return GuiScriptTextArea1.this.container;
            }

            @Override
            public void setTextWithoutUndo(String newText) {
                boolean wasUndoing = GuiScriptTextArea1.this.undoing;
                GuiScriptTextArea1.this.undoing = true;
                this.setText(newText);
                GuiScriptTextArea1.this.undoing = wasUndoing;
            }

            @Override
            public void pushUndoState(String textState, int cursor) {
                if (!GuiScriptTextArea1.this.undoing) {
                    GuiScriptTextArea1.this.undoList.add(new UndoData(textState, cursor));
                    GuiScriptTextArea1.this.redoList.clear();
                }
            }

            @Override
            public int getViewportWidth() {
                return GuiScriptTextArea1.this.field_146218_h - LINE_NUMBER_GUTTER_WIDTH - 8;
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
            String maxLineStr = String.valueOf(maxLineNum);
            int digitWidth = ClientProxy.Font.width(maxLineStr);
            LINE_NUMBER_GUTTER_WIDTH = digitWidth + 10;
        }
        int offset = this.fullscreen() ? 2 : 1;
        GuiScriptTextArea1.func_73734_a((int)(this.x - offset), (int)(this.y - offset - searchBar.getTotalHeight()), (int)(this.x + this.field_146218_h + offset), (int)(this.y + this.field_146219_i + offset), (int)-6250336);
        int searchHeight = searchBar.getTotalHeight();
        GuiScriptTextArea1.func_73734_a((int)this.x, (int)this.y, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH), (int)(this.y + this.field_146219_i), (int)-16777216);
        GuiScriptTextArea1.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH), (int)this.y, (int)(this.x + this.field_146218_h), (int)(this.y + this.field_146219_i), (int)-16777216);
        GuiScriptTextArea1.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH - 1), (int)this.y, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH), (int)(this.y + this.field_146219_i), (int)-12828863);
        GL11.glEnable((int)3089);
        this.scissorViewport();
        this.container.visibleLines = this.field_146219_i / this.container.lineHeight;
        int maxScroll = Math.max(0, this.getPaddedLineCount() - this.container.visibleLines);
        int wheelDelta = ((GuiNPCInterface)((Object)this.listener)).mouseScroll = Mouse.getDWheel();
        if (this.listener instanceof GuiNPCInterface) {
            boolean canScroll;
            ((GuiNPCInterface)((Object)this.listener)).mouseScroll = wheelDelta;
            boolean bl = canScroll = !this.KEYS_OVERLAY.isVisible() || this.KEYS_OVERLAY.isVisible() && !this.KEYS_OVERLAY.aboveOverlay;
            if (wheelDelta != 0 && canScroll) {
                this.scroll.applyWheelScroll(wheelDelta, maxScroll);
            }
        }
        if (this.scroll.isClickScrolling()) {
            this.scroll.handleClickScrolling(yMouse, this.x, this.y, this.field_146219_i, this.container.visibleLines, this.getPaddedLineCount(), maxScroll);
        }
        this.scroll.initializeIfNeeded(this.scroll.getScrolledLine());
        this.scroll.update(maxScroll);
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
            String lineNum = String.valueOf(i + 1);
            int lineNumWidth = ClientProxy.Font.width(lineNum);
            int lineNumX = this.x + LINE_NUMBER_GUTTER_WIDTH - lineNumWidth - 5;
            int lineNumY = posY2 + 1;
            int lineNumColor = -10460314;
            if (this.active && this.isEnabled()) {
                for (int li = 0; li < list.size(); ++li) {
                    JavaTextContainer.LineData ld = (JavaTextContainer.LineData)list.get(li);
                    if ((this.selection.getCursorPosition() < ld.start || this.selection.getCursorPosition() >= ld.end) && (li != list.size() - 1 || this.selection.getCursorPosition() != this.field_146216_j.length()) || li != i) continue;
                    lineNumColor = -4601898;
                    break;
                }
            }
            ClientProxy.Font.drawString(lineNum, lineNumX, lineNumY, lineNumColor);
        }
        for (i = renderStart; i <= renderEnd; ++i) {
            int e;
            int s;
            JavaTextContainer.LineData data = (JavaTextContainer.LineData)list.get(i);
            ScriptLine scriptLine = this.container.getDocument().getLineAt(i);
            String line = data.text;
            int w = line.length();
            posY = this.y + (i - this.scroll.getScrolledLine()) * this.container.lineHeight;
            if (i < renderStart || i > renderEnd) continue;
            if (startBracket != endBracket) {
                int e2;
                int s2;
                if (startBracket >= data.start && startBracket < data.end) {
                    s2 = ClientProxy.Font.width(line.substring(0, startBracket - data.start));
                    e2 = ClientProxy.Font.width(line.substring(0, startBracket - data.start + 1)) + 1;
                    GuiScriptTextArea1.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s2), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e2), (int)(posY + this.container.lineHeight + 0), (int)-1728001024);
                }
                if (endBracket >= data.start && endBracket < data.end) {
                    s2 = ClientProxy.Font.width(line.substring(0, endBracket - data.start));
                    e2 = ClientProxy.Font.width(line.substring(0, endBracket - data.start + 1)) + 1;
                    GuiScriptTextArea1.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s2), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e2), (int)(posY + this.container.lineHeight + 0), (int)-1728001024);
                }
            }
            if (unmatchedBraces != null && !unmatchedBraces.isEmpty()) {
                for (int ubPos : unmatchedBraces) {
                    if (ubPos < data.start || ubPos >= data.end) continue;
                    int rel = ubPos - data.start;
                    int s3 = ClientProxy.Font.width(line.substring(0, rel));
                    int e3 = ClientProxy.Font.width(line.substring(0, rel + 1)) + 1;
                    GuiScriptTextArea1.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s3), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e3), (int)(posY + this.container.lineHeight), (int)-3407872);
                }
            }
            if (this.highlightedWord != null) {
                Matcher m = this.container.regexWord.matcher(line);
                while (m.find()) {
                    if (!line.substring(m.start(), m.end()).equals(this.highlightedWord)) continue;
                    int s4 = ClientProxy.Font.width(line.substring(0, m.start()));
                    int e4 = ClientProxy.Font.width(line.substring(0, m.end())) + 1;
                    GuiScriptTextArea1.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s4), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e4), (int)(posY + this.container.lineHeight), (int)-1728033792);
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
                    s = ClientProxy.Font.width(line.substring(0, matchStart));
                    e = ClientProxy.Font.width(line.substring(0, matchEnd)) + 1;
                    boolean isExcluded = searchBar.isMatchExcluded(mi);
                    int n = highlightColor = mi == currentMatchIdx ? -1153136385 : 0x662266AA;
                    if (isExcluded) {
                        highlightColor = 0x33666666;
                    }
                    int highlightX = this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s;
                    int highlightEndX = this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e;
                    GuiScriptTextArea1.func_73734_a((int)highlightX, (int)posY, (int)highlightEndX, (int)(posY + this.container.lineHeight), (int)highlightColor);
                    if (!isExcluded) continue;
                    int strikeY = posY + this.container.lineHeight / 2;
                    GuiScriptTextArea1.func_73734_a((int)highlightX, (int)strikeY, (int)highlightEndX, (int)(strikeY + 1), (int)-5618620);
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
                    s = ClientProxy.Font.width(line.substring(0, occStart));
                    e = isEmpty ? s + 2 : ClientProxy.Font.width(line.substring(0, occEnd)) + 1;
                    int occX = this.x + LINE_NUMBER_GUTTER_WIDTH + s;
                    int occEndX = this.x + LINE_NUMBER_GUTTER_WIDTH + 2 + e;
                    boolean isPrimary = this.renameHandler.isPrimaryOccurrence(occ[0]);
                    int bgColor = isPrimary ? 0x55335577 : 857883750;
                    GuiScriptTextArea1.func_73734_a((int)occX, (int)posY, (int)occEndX, (int)(posY + this.container.lineHeight), (int)bgColor);
                    if (!isPrimary) continue;
                    int borderColor = -570425345;
                    GuiScriptTextArea1.func_73734_a((int)occX, (int)posY, (int)occEndX, (int)(posY + this.container.lineHeight), (int)0x33FF0000);
                    GuiScriptTextArea1.func_73734_a((int)occX, (int)posY, (int)occEndX, (int)(posY + 1), (int)borderColor);
                    GuiScriptTextArea1.func_73734_a((int)occX, (int)(posY + this.container.lineHeight - 1), (int)occEndX, (int)(posY + this.container.lineHeight), (int)borderColor);
                    GuiScriptTextArea1.func_73734_a((int)occX, (int)posY, (int)(occX + 1), (int)(posY + this.container.lineHeight), (int)borderColor);
                    GuiScriptTextArea1.func_73734_a((int)(occEndX - 1), (int)posY, (int)occEndX, (int)(posY + this.container.lineHeight), (int)borderColor);
                    if (!this.renameHandler.shouldShowCursor()) continue;
                    int cursorInWord = this.renameHandler.getCursorInWord();
                    String currentWord = this.renameHandler.getCurrentWord();
                    if (currentWord == null || cursorInWord < 0 || cursorInWord > currentWord.length()) continue;
                    String beforeCursor = currentWord.substring(0, Math.min(cursorInWord, currentWord.length()));
                    int n = occX + ClientProxy.Font.width(beforeCursor);
                }
            }
            if (this.active && this.isEnabled() && (this.selection.getCursorPosition() >= data.start && this.selection.getCursorPosition() < data.end || i == list.size() - 1 && this.selection.getCursorPosition() == this.field_146216_j.length())) {
                GuiScriptTextArea1.func_73734_a((int)this.x, (int)posY, (int)(this.x + this.field_146218_h - 1), (int)(posY + this.container.lineHeight), (int)0x22E0E0E0);
            }
            if (this.selection.hasSelection() && this.selection.getEndSelection() > data.start && this.selection.getStartSelection() <= data.end && this.selection.getStartSelection() < data.end) {
                int s5 = ClientProxy.Font.width(line.substring(0, Math.max(this.selection.getStartSelection() - data.start, 0)));
                int e5 = ClientProxy.Font.width(line.substring(0, Math.min(this.selection.getEndSelection() - data.start, w))) + 1;
                GuiScriptTextArea1.func_73734_a((int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + s5), (int)posY, (int)(this.x + LINE_NUMBER_GUTTER_WIDTH + 1 + e5), (int)(posY + this.container.lineHeight), (int)-1725861121);
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
                    GuiScriptTextArea1.func_73734_a((int)gx, (int)topY, (int)(gx + 1), (int)bottomY, (int)guideColor);
                }
            }
            int yPos = posY + stringYOffset;
            scriptLine.drawString(this.x + LINE_NUMBER_GUTTER_WIDTH + 1, yPos, -2039584);
            boolean recentInput = this.selection.hadRecentInput();
            if (!this.active || !this.isEnabled() || !recentInput && this.cursorCounter / 10 % 2 != 0 || (this.selection.getCursorPosition() < data.start || this.selection.getCursorPosition() >= data.end) && (i != list.size() - 1 || this.selection.getCursorPosition() != this.field_146216_j.length())) continue;
            int posX = this.x + LINE_NUMBER_GUTTER_WIDTH + ClientProxy.Font.width(line.substring(0, Math.min(this.selection.getCursorPosition() - data.start, line.length())));
            GuiScriptTextArea1.func_73734_a((int)(posX + 1), (int)posY, (int)(posX + 2), (int)(posY + this.container.lineHeight), (int)-1);
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
            GuiScriptTextArea1.func_73734_a((int)posX, (int)posY, (int)(posX + 5), (int)(posY + sbSize + 2), (int)-2039584);
        }
        searchBar.draw(xMouse, yMouse);
        this.goToLineDialog.draw(xMouse, yMouse);
        this.KEYS_OVERLAY.draw(xMouse, yMouse, wheelDelta);
    }

    private void scissorViewport() {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int scaleFactor = sr.func_78325_e();
        int scissorX = this.x * scaleFactor;
        int scissorY = (sr.func_78328_b() - (this.y + this.field_146219_i)) * scaleFactor;
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
            int lineWidth = 0;
            char[] chars = data.text.toCharArray();
            for (int j = 1; j <= chars.length; ++j) {
                int w = ClientProxy.Font.width(data.text.substring(0, j));
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
        GuiScriptTextArea1.KEYS.CUT.setTask(e -> {
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
        GuiScriptTextArea1.KEYS.COPY.setTask(e -> {
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            if (this.selection.hasSelection()) {
                NoppesStringUtils.setClipboardContents(this.selection.getSelectedText(this.field_146216_j));
            }
        });
        GuiScriptTextArea1.KEYS.PASTE.setTask(e -> {
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            this.addText(NoppesStringUtils.getClipboardContents());
            this.scrollToCursor();
        });
        GuiScriptTextArea1.KEYS.UNDO.setTask(e -> {
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
        GuiScriptTextArea1.KEYS.REDO.setTask(e -> {
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
        GuiScriptTextArea1.KEYS.FORMAT.setTask(e -> {
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            this.formatText();
        });
        GuiScriptTextArea1.KEYS.TOGGLE_COMMENT.setTask(e -> {
            if (!e.isPress() || !((Boolean)isActive.get()).booleanValue()) {
                return;
            }
            if (this.selection.hasSelection()) {
                this.toggleCommentSelection();
            } else {
                this.toggleCommentLineAtCursor();
            }
        });
        GuiScriptTextArea1.KEYS.DUPLICATE.setTask(e -> {
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
        GuiScriptTextArea1.KEYS.SEARCH.setTask(e -> {
            if (!e.isPress() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            this.unfocusAll();
            searchBar.openSearch();
        });
        GuiScriptTextArea1.KEYS.SEARCH_REPLACE.setTask(e -> {
            if (!e.isPress() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            this.unfocusAll();
            searchBar.openSearchReplace();
        });
        GuiScriptTextArea1.KEYS.GO_TO_LINE.setTask(e -> {
            if (!e.isPress() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            this.unfocusAll();
            this.goToLineDialog.toggle();
        });
        GuiScriptTextArea1.KEYS.RENAME.setTask(e -> {
            if (!e.isPress() || !((Boolean)openBoxes.get()).booleanValue()) {
                return;
            }
            if (!this.renameHandler.isActive()) {
                this.unfocusAll();
                this.active = true;
                this.renameHandler.startRename();
            }
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
                Matcher m = this.container.regexWord.matcher(this.field_146216_j.substring(0, this.selection.getCursorPosition()));
                while (m.find()) {
                    if (m.start() == m.end()) continue;
                    j = this.selection.getCursorPosition() - m.start();
                }
            }
            int newPos = Math.max(this.selection.getCursorPosition() - j, 0);
            this.setCursor(newPos, GuiScreen.func_146272_n());
            return true;
        }
        if (i == 205) {
            int j = 1;
            if (GuiScreen.func_146271_m()) {
                String after = this.field_146216_j.substring(this.selection.getCursorPosition());
                Matcher m = this.container.regexWord.matcher(after);
                j = m.find() ? (m.start() == 0 ? (m.find() ? m.start() : Math.max(1, after.length())) : m.start()) : Math.max(1, after.length());
            }
            int newPos = Math.min(this.selection.getCursorPosition() + j, this.field_146216_j.length());
            this.setCursor(newPos, GuiScreen.func_146272_n());
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
            int cursorPos = this.selection.getCursorPosition();
            for (prevNonWs = cursorPos - 1; prevNonWs >= 0 && prevNonWs < (this.field_146216_j != null ? this.field_146216_j.length() : 0) && Character.isWhitespace(this.field_146216_j.charAt(prevNonWs)); --prevNonWs) {
            }
            if (prevNonWs >= 0 && cursorPos <= (this.field_146216_j != null ? this.field_146216_j.length() : 0) && this.field_146216_j.charAt(prevNonWs) == '{') {
                boolean hasMatchingCloseSameIndent;
                String after;
                String before;
                String childIndent;
                String indent;
                block17: {
                    String leadingSegment;
                    indent = "";
                    for (JavaTextContainer.LineData ld : this.container.lines) {
                        if (prevNonWs < ld.start || prevNonWs >= ld.end) continue;
                        indent = ld.text.substring(0, IndentHelper.getLineIndent(ld.text));
                        break;
                    }
                    if (indent == null) {
                        indent = "";
                    }
                    childIndent = indent + "    ";
                    before = this.getSelectionBeforeText();
                    after = this.getSelectionAfterText();
                    int firstNewline = after.indexOf(10);
                    String string = leadingSegment = firstNewline == -1 ? after : after.substring(0, firstNewline);
                    if (leadingSegment.trim().length() > 0) {
                        this.addText("\n" + childIndent);
                        this.scrollToCursor();
                        return true;
                    }
                    hasMatchingCloseSameIndent = false;
                    try {
                        int openLineIdx = -1;
                        int bracePos = prevNonWs;
                        for (int li = 0; li < this.container.lines.size(); ++li) {
                            JavaTextContainer.LineData ld = (JavaTextContainer.LineData)this.container.lines.get(li);
                            if (bracePos < ld.start || bracePos >= ld.end) continue;
                            openLineIdx = li;
                            break;
                        }
                        if (openLineIdx < 0) break block17;
                        List<int[]> spans = BracketMatcher.computeBraceSpans(this.field_146216_j, this.container.lines);
                        for (int[] span : spans) {
                            int spanOpen = span[1];
                            int spanClose = span[2];
                            if (spanOpen != openLineIdx) continue;
                            int closeIndent = IndentHelper.getLineIndent(((JavaTextContainer.LineData)this.container.lines.get((int)spanClose)).text);
                            if (closeIndent == indent.length()) {
                                hasMatchingCloseSameIndent = true;
                            }
                            break;
                        }
                    }
                    catch (Exception ex) {
                        hasMatchingCloseSameIndent = false;
                    }
                }
                if (hasMatchingCloseSameIndent) {
                    this.addText("\n" + childIndent);
                    this.scrollToCursor();
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

    private boolean handleDeletionKeys(int i) {
        if (i == 211) {
            String s = this.getSelectionAfterText();
            if (!s.isEmpty() && !this.selection.hasSelection()) {
                s = s.substring(1);
            }
            this.func_146180_a(this.getSelectionBeforeText() + s);
            this.selection.reset(this.selection.getStartSelection());
            return true;
        }
        if (this.isKeyComboCtrlBackspace(i)) {
            String s = this.getSelectionBeforeText();
            if (this.selection.getStartSelection() > 0 && !this.selection.hasSelection()) {
                int nearestCondition = this.selection.getCursorPosition();
                boolean cursorInWhitespace = Character.isWhitespace(s.charAt(this.selection.getCursorPosition() - 1));
                if (cursorInWhitespace) {
                    for (int g = this.selection.getCursorPosition() - 1; g >= 0; --g) {
                        char currentChar = s.charAt(g);
                        if (!Character.isWhitespace(currentChar)) {
                            nearestCondition = g;
                            break;
                        }
                        if (g != 0) continue;
                        nearestCondition = 0;
                    }
                } else {
                    for (int g = this.selection.getCursorPosition() - 1; g >= 0; --g) {
                        char currentChar = s.charAt(g);
                        if (Character.isWhitespace(currentChar) || currentChar == '\n') {
                            nearestCondition = g;
                            break;
                        }
                        if (g != 0) continue;
                        nearestCondition = 0;
                    }
                }
                s = s.substring(0, nearestCondition);
                this.selection.setStartSelection(this.selection.getStartSelection() - (this.selection.getCursorPosition() - nearestCondition));
            }
            this.func_146180_a(s + this.getSelectionAfterText());
            this.selection.reset(this.selection.getStartSelection());
            return true;
        }
        if (i == 14) {
            if (this.selection.hasSelection()) {
                String s = this.getSelectionBeforeText();
                this.func_146180_a(s + this.getSelectionAfterText());
                this.selection.reset(this.selection.getStartSelection());
                this.scrollToCursor();
                return true;
            }
            if (this.selection.getStartSelection() <= 0) {
                return true;
            }
            JavaTextContainer.LineData currCheck = this.selection.findCurrentLine(this.container.lines);
            if (currCheck != null && currCheck.text.trim().length() == 0) {
                int removeEnd = this.field_146216_j.indexOf(10, currCheck.start - 1);
                removeEnd = removeEnd == -1 ? this.field_146216_j.length() : ++removeEnd;
                String before = this.field_146216_j.substring(0, ValueUtil.clamp(currCheck.start - 1, 0, this.field_146216_j.length()));
                String after = removeEnd <= this.field_146216_j.length() ? this.field_146216_j.substring(removeEnd) : "";
                this.func_146180_a(before + after);
                int newCursor = Math.max(0, currCheck.start - 1);
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
                        this.func_146180_a(before + after);
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
                    this.func_146180_a(before + spacer + content);
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
                    this.func_146180_a(before + after);
                    this.selection.setStartSelection(this.selection.getStartSelection() - 1);
                    this.selection.reset(this.selection.getStartSelection());
                    this.scrollToCursor();
                    return true;
                }
            }
            String s = this.getSelectionBeforeText();
            s = s.substring(0, s.length() - 1);
            this.selection.setStartSelection(this.selection.getStartSelection() - 1);
            this.func_146180_a(s + this.getSelectionAfterText());
            this.selection.reset(this.selection.getStartSelection());
            this.scrollToCursor();
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
            if ((c == ')' || c == ']' || c == '\"' || c == '\'') && after.length() > 0 && after.charAt(0) == c) {
                this.selection.reset(before.length() + 1);
                this.scrollToCursor();
                return true;
            }
            if (c == '\"') {
                this.func_146180_a(before + "\"\"" + after);
                this.selection.reset(before.length() + 1);
                this.scrollToCursor();
                return true;
            }
            if (c == '\'') {
                this.func_146180_a(before + "''" + after);
                this.selection.reset(before.length() + 1);
                this.scrollToCursor();
                return true;
            }
            if (c == '[') {
                this.func_146180_a(before + "[]" + after);
                this.selection.reset(before.length() + 1);
                this.scrollToCursor();
                return true;
            }
            if (c == '(') {
                this.func_146180_a(before + "()" + after);
                this.selection.reset(before.length() + 1);
                this.scrollToCursor();
                return true;
            }
            this.addText(Character.toString(c));
            this.scrollToCursor();
            return true;
        }
        return false;
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
        return !this.KEYS_OVERLAY.isVisible() && !searchBar.isVisible() && !this.goToLineDialog.isVisible() && !this.renameHandler.isActive();
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
        int viewportWidth = this.field_146218_h - LINE_NUMBER_GUTTER_WIDTH - 10;
        IndentHelper.FormatResult result = IndentHelper.formatText(this.field_146216_j, this.selection.getCursorPosition(), viewportWidth);
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
        JavaTextContainer.LineData currentLine = this.selection.findCurrentLine(this.container.lines);
        if (currentLine == null) {
            return;
        }
        int tab = this.getTabSize();
        int indentLen = IndentHelper.getLineIndent(currentLine.text);
        int textStartPos = currentLine.start + indentLen;
        if (this.selection.getCursorPosition() <= textStartPos) {
            int sepEnd;
            int targetIndent = Math.max(0, (indentLen - 1) / tab * tab);
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
            int pos;
            int column = this.selection.getCursorPosition() - currentLine.start;
            int mod = column % tab;
            int toRemove = mod == 0 ? tab : mod;
            int removed = 0;
            for (pos = this.selection.getCursorPosition() - 1; pos >= currentLine.start && removed < toRemove && this.field_146216_j.charAt(pos) == ' '; --pos, ++removed) {
            }
            if (removed > 0) {
                int removeStart = pos + 1;
                String before = this.field_146216_j.substring(0, removeStart);
                String after = this.field_146216_j.substring(this.selection.getCursorPosition());
                this.func_146180_a(before + after);
                int newCursor = removeStart;
                this.selection.reset(Math.min(newCursor, this.field_146216_j.length()));
            }
        }
    }

    private void setCursor(int i, boolean select) {
        this.selection.setCursor(i, this.field_146216_j != null ? this.field_146216_j.length() : 0, select);
    }

    private void addText(String s) {
        int insertPos = this.selection.getStartSelection();
        this.func_146180_a(this.getSelectionBeforeText() + s + this.getSelectionAfterText());
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
        if (text == null) {
            return;
        }
        text = text.replace("\r", "");
        text = text.replace("\t", "    ");
        if (this.field_146216_j == null || !this.field_146216_j.equals(text)) {
            if (this.listener != null) {
                this.listener.textUpdate(text);
            }
            if (!this.undoing) {
                this.undoList.add(new UndoData(this.field_146216_j, this.selection.getCursorPosition()));
                this.redoList.clear();
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

    public void setListener(ITextChangeListener listener) {
        this.listener = listener;
    }

    private void clampSelectionBounds() {
        this.selection.clamp(this.field_146216_j != null ? this.field_146216_j.length() : 0);
    }

    public static class UndoData {
        public String text;
        public int cursorPosition;

        public UndoData(String text, int cursorPosition) {
            this.text = text;
            this.cursorPosition = cursorPosition;
        }
    }
}

