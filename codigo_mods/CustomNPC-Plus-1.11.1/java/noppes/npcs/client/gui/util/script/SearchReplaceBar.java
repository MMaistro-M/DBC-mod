/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util.script;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiScriptTextArea;
import noppes.npcs.client.gui.util.GuiUtil;
import noppes.npcs.client.gui.util.key.OverlayKeyPresetViewer;
import noppes.npcs.util.ValueUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class SearchReplaceBar {
    public static final ResourceLocation TEXTURE = OverlayKeyPresetViewer.TEXTURE;
    private int x;
    private int y;
    private int width;
    public GuiNPCInterface ownerGui;
    private int barHeight = 24;
    private int replaceBarHeight = 22;
    private int textFieldWidth = 150;
    private int textFieldHeight = 16;
    private int buttonSize = 16;
    private int buttonSpacing = 2;
    private int padding = 4;
    private boolean visible = false;
    private boolean showReplace = false;
    private boolean searchFieldFocused = true;
    private boolean replaceFieldFocused = false;
    private boolean matchCase = false;
    private boolean wholeWord = false;
    private String searchText = "";
    private String replaceText = "";
    private int searchCursor = 0;
    private int replaceCursor = 0;
    private int searchSelectionStart = 0;
    private int searchSelectionEnd = 0;
    private int replaceSelectionStart = 0;
    private int replaceSelectionEnd = 0;
    private int searchScrollOffset = 0;
    private int replaceScrollOffset = 0;
    private List<GuiScriptTextArea.UndoData> searchUndoList = new ArrayList<GuiScriptTextArea.UndoData>();
    private List<GuiScriptTextArea.UndoData> searchRedoList = new ArrayList<GuiScriptTextArea.UndoData>();
    private List<GuiScriptTextArea.UndoData> replaceUndoList = new ArrayList<GuiScriptTextArea.UndoData>();
    private List<GuiScriptTextArea.UndoData> replaceRedoList = new ArrayList<GuiScriptTextArea.UndoData>();
    private List<int[]> matches = new ArrayList<int[]>();
    private int currentMatchIndex = -1;
    private List<Integer> excludedMatches = new ArrayList<Integer>();
    private boolean hoverClose = false;
    private boolean hoverSearchUnwind = false;
    private boolean hoverMatchCase = false;
    private boolean hoverWholeWord = false;
    private boolean hoverPrev = false;
    private boolean hoverNext = false;
    private boolean hoverReplace = false;
    private boolean hoverReplaceAll = false;
    private boolean hoverExclude = false;
    private int cursorCounter = 0;
    private long lastInputTime = 0L;
    private long lastClickTime = 0L;
    private int clickCount = 0;
    private boolean lastClickWasSearch = true;
    public SearchCallback callback;
    private final FontRenderer font;

    public SearchReplaceBar() {
        this.font = Minecraft.func_71410_x().field_71466_p;
    }

    public void setCallback(SearchCallback callback) {
        this.callback = callback;
    }

    public void initGui(int textAreaX, int textAreaY, int textAreaWidth) {
        this.x = textAreaX;
        this.y = textAreaY;
        this.width = textAreaWidth;
    }

    public void open() {
        boolean oldVisible = this.visible;
        boolean matchHighlight = false;
        this.visible = true;
        if (this.callback != null) {
            this.callback.resizeEditor(true, this.getTotalHeight());
            this.callback.unfocusMainEditor();
            String highlight = this.callback.getHighlightedWord();
            if (highlight != null) {
                this.searchText = highlight;
                matchHighlight = true;
            }
        }
        this.searchSelectionStart = 0;
        this.searchSelectionEnd = this.searchText.length();
        this.searchCursor = this.searchText.length();
        this.markActivity();
        this.updateMatches();
        if (matchHighlight) {
            this.setCurrentMatchToHighlight();
            this.navigateToCurrentMatch();
        }
    }

    public void openSearch() {
        this.showReplace = false;
        this.searchFieldFocused = true;
        this.replaceFieldFocused = false;
        this.open();
    }

    public void openSearchReplace() {
        this.showReplace = true;
        this.searchFieldFocused = false;
        this.replaceFieldFocused = true;
        this.open();
    }

    public void close() {
        if (this.callback != null) {
            this.callback.resizeEditor(false, 0);
            this.callback.focusMainEditor();
        }
        this.visible = false;
        this.searchFieldFocused = false;
        this.replaceFieldFocused = false;
    }

    public void focus(boolean replace) {
        if (replace) {
            this.replaceFieldFocused = true;
        } else {
            this.searchFieldFocused = true;
        }
    }

    public void unfocus() {
        this.searchFieldFocused = false;
        this.replaceFieldFocused = false;
    }

    private void saveState(boolean isSearchField) {
        if (isSearchField) {
            this.searchUndoList.add(new GuiScriptTextArea.UndoData(this.searchText, this.searchCursor));
            this.searchRedoList.clear();
        } else {
            this.replaceUndoList.add(new GuiScriptTextArea.UndoData(this.replaceText, this.replaceCursor));
            this.replaceRedoList.clear();
        }
    }

    public void undo() {
        if (this.searchFieldFocused && !this.searchUndoList.isEmpty()) {
            GuiScriptTextArea.UndoData data = this.searchUndoList.remove(this.searchUndoList.size() - 1);
            this.searchRedoList.add(new GuiScriptTextArea.UndoData(this.searchText, this.searchCursor));
            this.searchText = data.text;
            this.searchSelectionStart = this.searchCursor = data.cursorPosition;
            this.searchSelectionEnd = this.searchCursor;
            this.updateScrollOffset(true);
            this.updateMatches();
        } else if (this.replaceFieldFocused && !this.replaceUndoList.isEmpty()) {
            GuiScriptTextArea.UndoData data = this.replaceUndoList.remove(this.replaceUndoList.size() - 1);
            this.replaceRedoList.add(new GuiScriptTextArea.UndoData(this.replaceText, this.replaceCursor));
            this.replaceText = data.text;
            this.replaceSelectionStart = this.replaceCursor = data.cursorPosition;
            this.replaceSelectionEnd = this.replaceCursor;
            this.updateScrollOffset(false);
        }
    }

    public void redo() {
        if (this.searchFieldFocused && !this.searchRedoList.isEmpty()) {
            GuiScriptTextArea.UndoData data = this.searchRedoList.remove(this.searchRedoList.size() - 1);
            this.searchUndoList.add(new GuiScriptTextArea.UndoData(this.searchText, this.searchCursor));
            this.searchText = data.text;
            this.searchSelectionStart = this.searchCursor = data.cursorPosition;
            this.searchSelectionEnd = this.searchCursor;
            this.updateScrollOffset(true);
            this.updateMatches();
        } else if (this.replaceFieldFocused && !this.replaceRedoList.isEmpty()) {
            GuiScriptTextArea.UndoData data = this.replaceRedoList.remove(this.replaceRedoList.size() - 1);
            this.replaceUndoList.add(new GuiScriptTextArea.UndoData(this.replaceText, this.replaceCursor));
            this.replaceText = data.text;
            this.replaceSelectionStart = this.replaceCursor = data.cursorPosition;
            this.replaceSelectionEnd = this.replaceCursor;
            this.updateScrollOffset(false);
        }
    }

    private void updateScrollOffset(boolean isSearchField) {
        int scrollOffset;
        int fieldWidth = this.textFieldWidth - 8;
        String text = isSearchField ? this.searchText : this.replaceText;
        int cursor = isSearchField ? this.searchCursor : this.replaceCursor;
        String beforeCursor = cursor > 0 ? text.substring(0, cursor) : "";
        int cursorX = this.font.func_78256_a(beforeCursor);
        int n = scrollOffset = isSearchField ? this.searchScrollOffset : this.replaceScrollOffset;
        if (cursorX - scrollOffset * 6 > fieldWidth) {
            scrollOffset = Math.max(0, cursor - fieldWidth / 6);
        } else if (cursor < scrollOffset) {
            scrollOffset = cursor;
        }
        if (isSearchField) {
            this.searchScrollOffset = scrollOffset;
        } else {
            this.replaceScrollOffset = scrollOffset;
        }
    }

    public void toggle() {
        if (this.visible && !this.showReplace) {
            this.close();
        } else {
            this.openSearch();
        }
    }

    public void toggleReplace() {
        if (this.visible && this.showReplace) {
            this.close();
        } else {
            this.openSearchReplace();
        }
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isSearchFocused() {
        return this.visible && this.searchFieldFocused;
    }

    public boolean isReplaceFocused() {
        return this.visible && this.replaceFieldFocused;
    }

    public boolean hasFocus() {
        return this.visible && (this.searchFieldFocused || this.replaceFieldFocused);
    }

    public int getTotalHeight() {
        if (!this.visible) {
            return 0;
        }
        return this.showReplace ? this.barHeight + this.replaceBarHeight : this.barHeight;
    }

    public List<int[]> getMatches() {
        return this.matches;
    }

    public int getCurrentMatchIndex() {
        return this.currentMatchIndex;
    }

    public boolean isMatchExcluded(int index) {
        return this.excludedMatches.contains(index);
    }

    public void updateCursor() {
        ++this.cursorCounter;
    }

    private void markActivity() {
        this.lastInputTime = System.currentTimeMillis();
    }

    private boolean hadRecentInput() {
        return System.currentTimeMillis() - this.lastInputTime < 500L;
    }

    private boolean shouldShowCursor() {
        return this.hadRecentInput() || this.cursorCounter / 10 % 2 == 0;
    }

    public void draw(int mouseX, int mouseY) {
        if (!this.visible) {
            return;
        }
        this.updateHoverStates(mouseX, mouseY);
        int totalHeight = this.getTotalHeight();
        Gui.func_73734_a((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + totalHeight), (int)-13816528);
        Gui.func_73734_a((int)this.x, (int)(this.y + totalHeight - 1), (int)(this.x + this.width), (int)(this.y + totalHeight), (int)-12500671);
        this.drawSearchRow(mouseX, mouseY);
        if (this.showReplace) {
            this.drawReplaceRow(mouseX, mouseY);
        }
    }

    private void drawSearchRow(int mouseX, int mouseY) {
        int rowY = this.y + this.padding;
        int currentX = this.x + this.padding;
        this.textFieldWidth = 100;
        this.drawTextField(currentX, rowY, this.textFieldWidth, this.textFieldHeight, this.searchText, this.searchCursor, this.searchSelectionStart, this.searchSelectionEnd, this.searchFieldFocused, "Search...", this.searchScrollOffset, true);
        String matchInfo = this.matches.isEmpty() ? "0 results" : this.currentMatchIndex + 1 + "/" + this.matches.size();
        int matchInfoWidth = this.font.func_78256_a(matchInfo);
        this.font.func_78276_b(matchInfo, currentX += this.textFieldWidth + this.buttonSpacing + 4, rowY + 4, this.matches.isEmpty() && !this.searchText.isEmpty() ? -39322 : -3355444);
        this.drawToggleButton(currentX += matchInfoWidth + 8, rowY, "Cc", this.matchCase, this.hoverMatchCase, "Match Case");
        this.drawToggleButton(currentX += this.buttonSize + this.buttonSpacing, rowY, "W", this.wholeWord, this.hoverWholeWord, "Whole Word");
        boolean prevEnabled = !this.matches.isEmpty();
        this.drawNavButton(currentX += this.buttonSize + this.buttonSpacing + 4, rowY, "\u25b2", prevEnabled, this.hoverPrev);
        boolean nextEnabled = !this.matches.isEmpty();
        this.drawNavButton(currentX += this.buttonSize + this.buttonSpacing, rowY, "\u25bc", nextEnabled, this.hoverNext);
        currentX += this.buttonSize + this.buttonSpacing;
        int closeX = this.x + this.width - this.buttonSize - this.padding;
        this.drawCloseButton(closeX, rowY, this.hoverClose);
        int replaceUnwindButton = closeX - this.buttonSize - this.buttonSpacing;
        this.drawActionButton(replaceUnwindButton, rowY, this.showReplace ? "\u25bc" : "\u25c0", this.hoverSearchUnwind, true);
    }

    private void drawReplaceRow(int mouseX, int mouseY) {
        int rowY = this.y + this.barHeight + 2;
        int currentX = this.x + this.padding;
        this.drawTextField(currentX, rowY, this.textFieldWidth, this.textFieldHeight, this.replaceText, this.replaceCursor, this.replaceSelectionStart, this.replaceSelectionEnd, this.replaceFieldFocused, "Replace...", this.replaceScrollOffset, false);
        this.drawActionButton(currentX += this.textFieldWidth + this.buttonSpacing + 4, rowY, "Replace", this.hoverReplace, !this.matches.isEmpty());
        this.drawActionButton(currentX += this.font.func_78256_a("Replace") + 12 + this.buttonSpacing, rowY, "Replace All", this.hoverReplaceAll, !this.matches.isEmpty());
        this.drawActionButton(currentX += this.font.func_78256_a("Replace All") + 12 + this.buttonSpacing, rowY, "Exclude", this.hoverExclude, this.currentMatchIndex >= 0);
    }

    private void drawTextField(int fieldX, int fieldY, int fieldWidth, int fieldHeight, String text, int cursor, int selStart, int selEnd, boolean focused, String placeholder, int scrollOffset, boolean isSearchField) {
        int col;
        int cursorPosX;
        Gui.func_73734_a((int)fieldX, (int)fieldY, (int)(fieldX + fieldWidth), (int)(fieldY + fieldHeight), (int)-14803426);
        Gui.func_73734_a((int)fieldX, (int)fieldY, (int)(fieldX + fieldWidth), (int)(fieldY + 1), (int)(focused ? -16745780 : -12829636));
        Gui.func_73734_a((int)fieldX, (int)(fieldY + fieldHeight - 1), (int)(fieldX + fieldWidth), (int)(fieldY + fieldHeight), (int)(focused ? -16745780 : -12829636));
        Gui.func_73734_a((int)fieldX, (int)fieldY, (int)(fieldX + 1), (int)(fieldY + fieldHeight), (int)(focused ? -16745780 : -12829636));
        Gui.func_73734_a((int)(fieldX + fieldWidth - 1), (int)fieldY, (int)(fieldX + fieldWidth), (int)(fieldY + fieldHeight), (int)(focused ? -16745780 : -12829636));
        int textX = fieldX + 4;
        int textY = fieldY + (fieldHeight - 8) / 2;
        int visibleWidth = fieldWidth - 8;
        String visibleText = text;
        int adjustedSelStart = selStart;
        int adjustedSelEnd = selEnd;
        if (scrollOffset > 0 && scrollOffset < text.length()) {
            visibleText = text.substring(scrollOffset);
            adjustedSelStart = Math.max(0, selStart - scrollOffset);
            adjustedSelEnd = Math.max(0, selEnd - scrollOffset);
        }
        if (adjustedSelStart != adjustedSelEnd) {
            int minSel = Math.min(adjustedSelStart, adjustedSelEnd);
            int maxSel = Math.max(adjustedSelStart, adjustedSelEnd);
            String beforeSel = visibleText.substring(0, Math.min(minSel, visibleText.length()));
            String inSel = visibleText.substring(Math.min(minSel, visibleText.length()), Math.min(maxSel, visibleText.length()));
            int selX = textX + this.font.func_78256_a(beforeSel);
            int selW = this.font.func_78256_a(inSel);
            Gui.func_73734_a((int)selX, (int)(textY - 1), (int)(selX + selW), (int)(textY + 9), (int)-14266504);
        }
        if ((cursorPosX = this.font.func_78256_a(text.substring(0, Math.min(cursor, text.length())))) - scrollOffset > visibleWidth - 2) {
            scrollOffset = cursorPosX - visibleWidth + 2;
        } else if (cursorPosX - scrollOffset < 0) {
            scrollOffset = Math.max(0, cursorPosX);
        }
        if (isSearchField) {
            this.searchScrollOffset = scrollOffset;
        } else {
            this.replaceScrollOffset = scrollOffset;
        }
        GL11.glEnable((int)3089);
        GuiUtil.setScissorClip(fieldX + 2, fieldY, fieldWidth - 4, fieldHeight, this.ownerGui);
        if (selStart != selEnd && focused) {
            int startX = textX + this.font.func_78256_a(text.substring(0, Math.min(selStart, text.length()))) - scrollOffset;
            int endX = textX + this.font.func_78256_a(text.substring(0, Math.min(selEnd, text.length()))) - scrollOffset;
            Gui.func_73734_a((int)startX, (int)(textY - 1), (int)endX, (int)(textY + this.font.field_78288_b), (int)-13804378);
        }
        boolean noMatches = !text.isEmpty() && this.matches.isEmpty();
        int n = col = text.isEmpty() ? -6250336 : -2039584;
        if (isSearchField && noMatches) {
            col = -39322;
        }
        this.font.func_78276_b(text.isEmpty() ? placeholder : text, textX - scrollOffset, textY, col);
        if (focused && this.shouldShowCursor()) {
            int cursorDrawX = textX + cursorPosX - scrollOffset;
            Gui.func_73734_a((int)cursorDrawX, (int)(textY - 1), (int)(cursorDrawX + 1), (int)(textY + this.font.field_78288_b), (int)-1);
        }
        GL11.glDisable((int)3089);
    }

    private void oldDrawTextField(int fieldX, int fieldY, int fieldWidth, int fieldHeight, String text, int cursor, int selStart, int selEnd, boolean focused, String placeholder, int scrollOffset, boolean isSearchField) {
        int col;
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        float boxScaleX = (float)fieldWidth / 32.0f;
        float boxScaleY = 1.5f;
        GL11.glScalef((float)boxScaleX, (float)boxScaleY, (float)1.0f);
        if (focused) {
            GL11.glColor4f((float)0.28f, (float)0.45f, (float)0.7f, (float)1.0f);
        } else {
            GL11.glColor4f((float)0.33f, (float)0.33f, (float)0.33f, (float)1.0f);
        }
        GuiUtil.drawTexturedModalRect((float)fieldX / boxScaleX, (float)(fieldY - 16) / boxScaleY, 32.0, 20.0, 0, 492);
        GL11.glPopMatrix();
        int textX = fieldX + 4;
        int textY = fieldY + (fieldHeight - this.font.field_78288_b) / 2;
        int visibleWidth = fieldWidth - 8;
        int cursorPosX = this.font.func_78256_a(text.substring(0, Math.min(cursor, text.length())));
        if (cursorPosX - scrollOffset > visibleWidth - 2) {
            scrollOffset = cursorPosX - visibleWidth + 2;
        } else if (cursorPosX - scrollOffset < 0) {
            scrollOffset = Math.max(0, cursorPosX);
        }
        if (isSearchField) {
            this.searchScrollOffset = scrollOffset;
        } else {
            this.replaceScrollOffset = scrollOffset;
        }
        GL11.glEnable((int)3089);
        GuiUtil.setScissorClip(fieldX + 2, fieldY, fieldWidth - 4, fieldHeight, this.ownerGui);
        if (selStart != selEnd && focused) {
            int startX = textX + this.font.func_78256_a(text.substring(0, Math.min(selStart, text.length()))) - scrollOffset;
            int endX = textX + this.font.func_78256_a(text.substring(0, Math.min(selEnd, text.length()))) - scrollOffset;
            Gui.func_73734_a((int)startX, (int)(textY - 1), (int)endX, (int)(textY + this.font.field_78288_b), (int)-13804378);
        }
        boolean noMatches = !text.isEmpty() && this.matches.isEmpty();
        int n = col = text.isEmpty() ? -6250336 : -2039584;
        if (isSearchField && noMatches) {
            col = -39322;
        }
        this.font.func_78276_b(text.isEmpty() ? placeholder : text, textX - scrollOffset, textY, col);
        if (focused && this.shouldShowCursor()) {
            int cursorDrawX = textX + cursorPosX - scrollOffset;
            Gui.func_73734_a((int)cursorDrawX, (int)(textY - 1), (int)(cursorDrawX + 1), (int)(textY + this.font.field_78288_b), (int)-1);
        }
        GL11.glDisable((int)3089);
    }

    private void drawToggleButton(int btnX, int btnY, String label, boolean active, boolean hovered, String tooltip) {
        int bgColor = active ? -11899481 : (hovered ? -11513776 : -12829636);
        Gui.func_73734_a((int)btnX, (int)btnY, (int)(btnX + this.buttonSize), (int)(btnY + this.buttonSize), (int)bgColor);
        Gui.func_73734_a((int)btnX, (int)btnY, (int)(btnX + this.buttonSize), (int)(btnY + 1), (int)-10461088);
        Gui.func_73734_a((int)btnX, (int)(btnY + this.buttonSize - 1), (int)(btnX + this.buttonSize), (int)(btnY + this.buttonSize), (int)-14013910);
        int textColor = active ? -1 : (hovered ? -3355444 : -6710887);
        int labelWidth = this.font.func_78256_a(label);
        this.font.func_78276_b(label, btnX + (this.buttonSize - labelWidth) / 2, btnY + (this.buttonSize - this.font.field_78288_b) / 2 + 1, textColor);
    }

    private void drawNavButton(int btnX, int btnY, String arrow, boolean enabled, boolean hovered) {
        int bgColor = !enabled ? -13027015 : (hovered ? -11513776 : -12829636);
        Gui.func_73734_a((int)btnX, (int)btnY, (int)(btnX + this.buttonSize), (int)(btnY + this.buttonSize), (int)bgColor);
        Gui.func_73734_a((int)btnX, (int)btnY, (int)(btnX + this.buttonSize), (int)(btnY + 1), (int)-10461088);
        int textColor = !enabled ? -11184811 : (hovered ? -1 : -5592406);
        int arrowWidth = this.font.func_78256_a(arrow);
        this.font.func_78276_b(arrow, btnX + (this.buttonSize - arrowWidth) / 2, btnY + (this.buttonSize - this.font.field_78288_b) / 2, textColor);
    }

    private void drawCloseButton(int btnX, int btnY, boolean hovered) {
        int bgColor = hovered ? -3714992 : -12829636;
        Gui.func_73734_a((int)btnX, (int)btnY, (int)(btnX + this.buttonSize), (int)(btnY + this.buttonSize), (int)bgColor);
        int textColor = hovered ? -1 : -5592406;
        String xChar = "X";
        int xWidth = this.font.func_78256_a(xChar);
        this.font.func_78276_b(xChar, btnX + (this.buttonSize - xWidth) / 2, btnY + (this.buttonSize - this.font.field_78288_b) / 2 + 1, textColor);
    }

    private void drawActionButton(int btnX, int btnY, String label, boolean hovered, boolean enabled) {
        int btnWidth = this.font.func_78256_a(label) + 10;
        int btnHeight = 14;
        int bgColor = !enabled ? -13027015 : (hovered ? -11513776 : -12829636);
        Gui.func_73734_a((int)btnX, (int)(btnY + 1), (int)(btnX + btnWidth), (int)(btnY + btnHeight + 1), (int)bgColor);
        Gui.func_73734_a((int)btnX, (int)(btnY + 1), (int)(btnX + btnWidth), (int)(btnY + 2), (int)-11184811);
        Gui.func_73734_a((int)btnX, (int)(btnY + btnHeight), (int)(btnX + btnWidth), (int)(btnY + btnHeight + 1), (int)-14013910);
        int textColor = !enabled ? -11184811 : (hovered ? -1 : -5592406);
        this.font.func_78276_b(label, btnX + 5, btnY + (btnHeight - this.font.field_78288_b) / 2 + 2, textColor);
    }

    private void updateHoverStates(int mouseX, int mouseY) {
        int rowY = this.y + this.padding;
        int currentX = this.x + this.padding;
        int searchFieldEndX = currentX + this.textFieldWidth;
        currentX = searchFieldEndX + this.buttonSpacing + 4;
        String matchInfo = this.matches.isEmpty() ? "0 results" : this.currentMatchIndex + 1 + "/" + this.matches.size();
        int matchCaseX = currentX += this.font.func_78256_a(matchInfo) + 8;
        this.hoverMatchCase = this.isMouseOver(mouseX, mouseY, matchCaseX, rowY, this.buttonSize, this.buttonSize);
        int wholeWordX = currentX += this.buttonSize + this.buttonSpacing;
        this.hoverWholeWord = this.isMouseOver(mouseX, mouseY, wholeWordX, rowY, this.buttonSize, this.buttonSize);
        int prevX = currentX += this.buttonSize + this.buttonSpacing + 4;
        this.hoverPrev = this.isMouseOver(mouseX, mouseY, prevX, rowY, this.buttonSize, this.buttonSize);
        int nextX = currentX += this.buttonSize + this.buttonSpacing;
        this.hoverNext = this.isMouseOver(mouseX, mouseY, nextX, rowY, this.buttonSize, this.buttonSize);
        int closeX = this.x + this.width - this.buttonSize - this.padding;
        this.hoverClose = this.isMouseOver(mouseX, mouseY, closeX, rowY, this.buttonSize, this.buttonSize);
        int replaceUnwindButton = closeX - this.buttonSize - this.buttonSpacing;
        this.hoverSearchUnwind = this.isMouseOver(mouseX, mouseY, replaceUnwindButton, rowY, this.buttonSize, this.buttonSize);
        if (this.showReplace) {
            int replaceRowY = this.y + this.barHeight + 2;
            currentX = this.x + this.padding + this.textFieldWidth + this.buttonSpacing + 4;
            int replaceWidth = this.font.func_78256_a("Replace") + 12;
            this.hoverReplace = this.isMouseOver(mouseX, mouseY, currentX, replaceRowY + 1, replaceWidth, 14);
            int replaceAllWidth = this.font.func_78256_a("Replace All") + 12;
            this.hoverReplaceAll = this.isMouseOver(mouseX, mouseY, currentX += replaceWidth + this.buttonSpacing, replaceRowY + 1, replaceAllWidth, 14);
            int excludeWidth = this.font.func_78256_a("Exclude") + 12;
            this.hoverExclude = this.isMouseOver(mouseX, mouseY, currentX += replaceAllWidth + this.buttonSpacing, replaceRowY + 1, excludeWidth, 14);
        } else {
            this.hoverExclude = false;
            this.hoverReplaceAll = false;
            this.hoverReplace = false;
        }
    }

    private boolean isMouseOver(int mouseX, int mouseY, int bx, int by, int bw, int bh) {
        return mouseX >= bx && mouseX < bx + bw && mouseY >= by && mouseY < by + bh;
    }

    public void resetSelection() {
        this.searchSelectionStart = this.searchSelectionEnd = this.searchCursor;
        this.replaceSelectionStart = this.replaceSelectionEnd = this.replaceCursor;
    }

    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        boolean isReplaceFieldClick;
        if (!this.visible) {
            return false;
        }
        if (button != 0) {
            return false;
        }
        int totalHeight = this.getTotalHeight();
        if (mouseX < this.x || mouseX > this.x + this.width || mouseY < this.y || mouseY > this.y + totalHeight) {
            this.resetSelection();
            return false;
        }
        int rowY = this.y + this.padding;
        int currentX = this.x + this.padding;
        long now = System.currentTimeMillis();
        boolean isSearchFieldClick = this.isMouseOver(mouseX, mouseY, currentX, rowY - 2, this.textFieldWidth, this.textFieldHeight + 4);
        boolean bl = isReplaceFieldClick = this.showReplace && this.isMouseOver(mouseX, mouseY, this.x + this.padding, this.y + this.barHeight, this.textFieldWidth, this.textFieldHeight + 4);
        if ((isSearchFieldClick || isReplaceFieldClick) && this.callback != null) {
            this.callback.unfocusMainEditor();
        }
        if (isSearchFieldClick) {
            boolean sameField = this.lastClickWasSearch;
            this.clickCount = sameField && now - this.lastClickTime < 300L ? ++this.clickCount : 1;
            this.lastClickTime = now;
            this.lastClickWasSearch = true;
            this.searchFieldFocused = true;
            this.replaceFieldFocused = false;
            this.markActivity();
            if (this.clickCount == 1) {
                int clickX = mouseX - currentX - 4 + this.searchScrollOffset;
                this.searchSelectionStart = this.searchSelectionEnd = (this.searchCursor = this.getTextCursorPosition(this.searchText, clickX));
            } else if (this.clickCount == 2) {
                this.selectWordAt(this.searchText, this.getTextCursorPosition(this.searchText, mouseX - currentX - 4 + this.searchScrollOffset), true);
            } else if (this.clickCount >= 3) {
                this.searchSelectionStart = 0;
                this.searchSelectionEnd = this.searchText.length();
                this.searchCursor = this.searchText.length();
                this.clickCount = 0;
            }
            return true;
        }
        currentX += this.textFieldWidth + this.buttonSpacing + 4;
        String matchInfo = this.matches.isEmpty() ? "0 results" : this.currentMatchIndex + 1 + "/" + this.matches.size();
        currentX += this.font.func_78256_a(matchInfo) + 8;
        if (this.hoverMatchCase) {
            this.matchCase = !this.matchCase;
            this.updateMatches();
            return true;
        }
        currentX += this.buttonSize + this.buttonSpacing;
        if (this.hoverWholeWord) {
            this.wholeWord = !this.wholeWord;
            this.updateMatches();
            return true;
        }
        currentX += this.buttonSize + this.buttonSpacing + 4;
        if (this.hoverPrev && !this.matches.isEmpty()) {
            this.goToPreviousMatch();
            return true;
        }
        currentX += this.buttonSize + this.buttonSpacing;
        if (this.hoverNext && !this.matches.isEmpty()) {
            this.goToNextMatch();
            return true;
        }
        if (this.hoverClose) {
            this.close();
            return true;
        }
        if (this.hoverSearchUnwind) {
            this.showReplace = !this.showReplace;
            return true;
        }
        if (this.showReplace) {
            int replaceRowY = this.y + this.barHeight + 2;
            if (isReplaceFieldClick || this.isMouseOver(mouseX, mouseY, this.x + this.padding, replaceRowY - 2, this.textFieldWidth, this.textFieldHeight + 4)) {
                boolean sameField;
                boolean bl2 = sameField = !this.lastClickWasSearch;
                this.clickCount = sameField && now - this.lastClickTime < 300L ? ++this.clickCount : 1;
                this.lastClickTime = now;
                this.lastClickWasSearch = false;
                this.searchFieldFocused = false;
                this.replaceFieldFocused = true;
                this.markActivity();
                if (this.clickCount == 1) {
                    int clickX = mouseX - this.x - this.padding - 4 + this.replaceScrollOffset;
                    this.replaceSelectionStart = this.replaceSelectionEnd = (this.replaceCursor = this.getTextCursorPosition(this.replaceText, clickX));
                } else if (this.clickCount == 2) {
                    this.selectWordAt(this.replaceText, this.getTextCursorPosition(this.replaceText, mouseX - this.x - this.padding - 4 + this.replaceScrollOffset), false);
                } else if (this.clickCount >= 3) {
                    this.replaceSelectionStart = 0;
                    this.replaceSelectionEnd = this.replaceText.length();
                    this.replaceCursor = this.replaceText.length();
                    this.clickCount = 0;
                }
                return true;
            }
            if (this.hoverReplace && !this.matches.isEmpty()) {
                this.replaceCurrent();
                return true;
            }
            if (this.hoverReplaceAll && !this.matches.isEmpty()) {
                this.replaceAll();
                return true;
            }
            if (this.hoverExclude && this.currentMatchIndex >= 0) {
                this.excludeCurrentMatch();
                return true;
            }
        }
        return true;
    }

    private int getTextCursorPosition(String text, int clickX) {
        if (text.isEmpty() || clickX <= 0) {
            return 0;
        }
        for (int i = 1; i <= text.length(); ++i) {
            int w = this.font.func_78256_a(text.substring(0, i));
            if (clickX >= w) continue;
            int prevW = this.font.func_78256_a(text.substring(0, i - 1));
            return clickX < (prevW + w) / 2 ? i - 1 : i;
        }
        return text.length();
    }

    private void selectWordAt(String text, int pos, boolean isSearchField) {
        int start;
        if (text.isEmpty()) {
            return;
        }
        int end = pos;
        for (start = pos; start > 0 && this.isWordChar(text.charAt(start - 1)); --start) {
        }
        while (end < text.length() && this.isWordChar(text.charAt(end))) {
            ++end;
        }
        if (isSearchField) {
            this.searchSelectionStart = start;
            this.searchSelectionEnd = end;
            this.searchCursor = end;
        } else {
            this.replaceSelectionStart = start;
            this.replaceSelectionEnd = end;
            this.replaceCursor = end;
        }
    }

    private boolean isWordChar(char c) {
        return Character.isLetterOrDigit(c) || c == '_';
    }

    public boolean keyTyped(char c, int keyCode) {
        boolean alt;
        if (keyCode == 1 && this.visible) {
            this.close();
            return true;
        }
        if (!this.visible || !this.searchFieldFocused && !this.replaceFieldFocused) {
            return false;
        }
        boolean ctrl = Keyboard.isKeyDown((int)29) || Keyboard.isKeyDown((int)157);
        boolean shift = Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54);
        boolean bl = alt = Keyboard.isKeyDown((int)56) || Keyboard.isKeyDown((int)184);
        if (keyCode == 29 || keyCode == 157 || keyCode == 42 || keyCode == 54 || keyCode == 56 || keyCode == 184) {
            return true;
        }
        if (keyCode == 15) {
            if (this.showReplace) {
                this.searchFieldFocused = !this.searchFieldFocused;
                this.replaceFieldFocused = !this.replaceFieldFocused;
            } else if (this.searchFieldFocused && this.callback != null) {
                this.searchFieldFocused = false;
                this.callback.focusMainEditor();
            }
            this.markActivity();
            return true;
        }
        if (keyCode == 28) {
            if (shift) {
                if (!this.matches.isEmpty()) {
                    this.goToPreviousMatch();
                }
            } else if (this.replaceFieldFocused && !this.matches.isEmpty()) {
                this.replaceCurrent();
            } else if (!this.matches.isEmpty()) {
                this.goToNextMatch();
            }
            return true;
        }
        if (keyCode == 200 && !this.matches.isEmpty()) {
            this.goToPreviousMatch();
            return true;
        }
        if (keyCode == 208 && !this.matches.isEmpty()) {
            this.goToNextMatch();
            return true;
        }
        if (this.searchFieldFocused) {
            this.handleTextFieldInput(c, keyCode, true);
        } else if (this.replaceFieldFocused) {
            this.handleTextFieldInput(c, keyCode, false);
        }
        return true;
    }

    private void handleTextFieldInput(char c, int keyCode, boolean isSearchField) {
        String selected;
        boolean shift;
        String text = isSearchField ? this.searchText : this.replaceText;
        int cursor = isSearchField ? this.searchCursor : this.replaceCursor;
        int selStart = isSearchField ? this.searchSelectionStart : this.replaceSelectionStart;
        int selEnd = isSearchField ? this.searchSelectionEnd : this.replaceSelectionEnd;
        boolean hasSelection = selStart != selEnd;
        boolean ctrl = Keyboard.isKeyDown((int)29) || Keyboard.isKeyDown((int)157);
        boolean bl = shift = Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54);
        if (ctrl && keyCode == 30) {
            selStart = 0;
            selEnd = text.length();
            cursor = text.length();
        } else if (ctrl && keyCode == 46) {
            if (hasSelection) {
                selected = text.substring(Math.min(selStart, selEnd), Math.max(selStart, selEnd));
                this.setClipboard(selected);
            }
        } else if (ctrl && keyCode == 45) {
            if (hasSelection) {
                selected = text.substring(Math.min(selStart, selEnd), Math.max(selStart, selEnd));
                this.setClipboard(selected);
                this.saveState(isSearchField);
                text = text.substring(0, Math.min(selStart, selEnd)) + text.substring(Math.max(selStart, selEnd));
                selStart = selEnd = (cursor = Math.min(selStart, selEnd));
            }
        } else if (ctrl && keyCode == 47) {
            String clipboard = this.getClipboard();
            if (clipboard != null) {
                clipboard = clipboard.replace("\n", "").replace("\r", "");
                this.saveState(isSearchField);
                if (hasSelection) {
                    text = text.substring(0, Math.min(selStart, selEnd)) + clipboard + text.substring(Math.max(selStart, selEnd));
                    cursor = Math.min(selStart, selEnd) + clipboard.length();
                } else {
                    text = text.substring(0, cursor) + clipboard + text.substring(cursor);
                    cursor += clipboard.length();
                }
                selStart = selEnd = cursor;
            }
        } else if (keyCode == 14) {
            this.saveState(isSearchField);
            if (hasSelection) {
                text = text.substring(0, Math.min(selStart, selEnd)) + text.substring(Math.max(selStart, selEnd));
                cursor = Math.min(selStart, selEnd);
            } else if (cursor > 0) {
                text = text.substring(0, cursor - 1) + text.substring(cursor);
                --cursor;
            }
            selStart = selEnd = cursor;
        } else if (keyCode == 211) {
            this.saveState(isSearchField);
            if (hasSelection) {
                text = text.substring(0, Math.min(selStart, selEnd)) + text.substring(Math.max(selStart, selEnd));
                cursor = Math.min(selStart, selEnd);
            } else if (cursor < text.length()) {
                text = text.substring(0, cursor) + text.substring(cursor + 1);
            }
            selStart = selEnd = cursor;
        } else if (keyCode == 203) {
            if (shift) {
                if (cursor > 0) {
                    --cursor;
                    if (selStart == selEnd) {
                        selEnd = cursor + 1;
                        selStart = cursor;
                    } else if (cursor < selStart) {
                        selStart = cursor;
                    } else {
                        selEnd = cursor;
                    }
                }
            } else {
                if (hasSelection) {
                    cursor = Math.min(selStart, selEnd);
                } else if (cursor > 0) {
                    --cursor;
                }
                selStart = selEnd = cursor;
            }
        } else if (keyCode == 205) {
            if (shift) {
                if (cursor < text.length()) {
                    ++cursor;
                    if (selStart == selEnd) {
                        selStart = cursor - 1;
                        selEnd = cursor;
                    } else if (cursor > selEnd) {
                        selEnd = cursor;
                    } else {
                        selStart = cursor;
                    }
                }
            } else {
                if (hasSelection) {
                    cursor = Math.max(selStart, selEnd);
                } else if (cursor < text.length()) {
                    ++cursor;
                }
                selStart = selEnd = cursor;
            }
        } else if (keyCode == 199) {
            if (shift) {
                selEnd = selStart == selEnd ? cursor : selEnd;
                selStart = 0;
            } else {
                selEnd = 0;
                selStart = 0;
            }
            cursor = 0;
        } else if (keyCode == 207) {
            if (shift) {
                selStart = selStart == selEnd ? cursor : selStart;
                selEnd = text.length();
            } else {
                selStart = selEnd = text.length();
            }
            cursor = text.length();
        } else if (ChatAllowedCharacters.func_71566_a((char)c)) {
            this.saveState(isSearchField);
            if (hasSelection) {
                text = text.substring(0, Math.min(selStart, selEnd)) + c + text.substring(Math.max(selStart, selEnd));
                cursor = Math.min(selStart, selEnd) + 1;
            } else {
                text = text.substring(0, cursor) + c + text.substring(cursor);
                ++cursor;
            }
            selStart = selEnd = cursor;
        }
        if (selStart > selEnd) {
            int temp = selStart;
            selStart = selEnd;
            selEnd = temp;
        }
        this.markActivity();
        if (isSearchField) {
            this.searchText = text;
            this.searchCursor = cursor;
            this.searchSelectionStart = selStart;
            this.searchSelectionEnd = selEnd;
            this.updateMatches();
        } else {
            this.replaceText = text;
            this.replaceCursor = cursor;
            this.replaceSelectionStart = selStart;
            this.replaceSelectionEnd = selEnd;
        }
    }

    private void setClipboard(String text) {
        try {
            StringSelection selection = new StringSelection(text);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private String getClipboard() {
        try {
            return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        }
        catch (Exception e) {
            return null;
        }
    }

    public void updateMatches() {
        this.matches.clear();
        this.excludedMatches.clear();
        if (this.callback == null || this.searchText.isEmpty()) {
            if (this.callback != null) {
                this.callback.onMatchesUpdated();
            }
            return;
        }
        String sourceText = this.callback.getText();
        if (sourceText == null || sourceText.isEmpty()) {
            this.callback.onMatchesUpdated();
            return;
        }
        String searchPattern = this.searchText;
        searchPattern = this.wholeWord ? "\\b" + Pattern.quote(searchPattern) + "\\b" : Pattern.quote(searchPattern);
        int flags = this.matchCase ? 0 : 2;
        try {
            Pattern pattern = Pattern.compile(searchPattern, flags);
            Matcher matcher = pattern.matcher(sourceText);
            while (matcher.find()) {
                this.matches.add(new int[]{matcher.start(), matcher.end()});
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (!this.matches.isEmpty()) {
            // empty if block
        }
        this.currentMatchIndex = ValueUtil.clamp(this.currentMatchIndex, 0, this.matches.size() - 1);
        this.callback.onMatchesUpdated();
    }

    private void setCurrentMatchToHighlight() {
        if (this.callback == null || this.matches.isEmpty()) {
            return;
        }
        try {
            int selStart = this.callback.getSelectionStart();
            int selEnd = this.callback.getSelectionEnd();
            if (selStart >= 0 && selEnd > selStart) {
                for (int i = 0; i < this.matches.size(); ++i) {
                    int[] m = this.matches.get(i);
                    if (m[0] > selStart || m[1] < selEnd) continue;
                    this.currentMatchIndex = i;
                    return;
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void goToNextMatch() {
        if (this.matches.isEmpty()) {
            return;
        }
        this.currentMatchIndex = this.currentMatchIndex < 0 ? 0 : (this.currentMatchIndex + 1) % this.matches.size();
        this.navigateToCurrentMatch();
    }

    public void goToPreviousMatch() {
        if (this.matches.isEmpty()) {
            return;
        }
        this.currentMatchIndex = this.currentMatchIndex < 0 ? this.matches.size() - 1 : (this.currentMatchIndex - 1 + this.matches.size()) % this.matches.size();
        this.navigateToCurrentMatch();
    }

    private void navigateToCurrentMatch() {
        if (this.callback == null || this.currentMatchIndex < 0 || this.currentMatchIndex >= this.matches.size()) {
            return;
        }
        int[] match = this.matches.get(this.currentMatchIndex);
        this.callback.scrollToPosition(match[0]);
        this.callback.setSelection(match[0], match[1]);
    }

    public void replaceCurrent() {
        if (this.callback == null || this.currentMatchIndex < 0 || this.matches.isEmpty()) {
            return;
        }
        if (this.excludedMatches.contains(this.currentMatchIndex)) {
            this.goToNextMatch();
            return;
        }
        String sourceText = this.callback.getText();
        int[] match = this.matches.get(this.currentMatchIndex);
        String newText = sourceText.substring(0, match[0]) + this.replaceText + sourceText.substring(match[1]);
        this.callback.setText(newText);
        this.updateMatches();
        if (!this.matches.isEmpty()) {
            int replacementEnd = match[0] + this.replaceText.length();
            for (int i = 0; i < this.matches.size(); ++i) {
                if (this.matches.get(i)[0] < replacementEnd) continue;
                this.currentMatchIndex = i;
                break;
            }
            this.navigateToCurrentMatch();
        }
    }

    public void replaceAll() {
        if (this.callback == null || this.matches.isEmpty()) {
            return;
        }
        String sourceText = this.callback.getText();
        StringBuilder result = new StringBuilder();
        int lastEnd = 0;
        for (int i = 0; i < this.matches.size(); ++i) {
            if (this.excludedMatches.contains(i)) continue;
            int[] match = this.matches.get(i);
            result.append(sourceText, lastEnd, match[0]);
            result.append(this.replaceText);
            lastEnd = match[1];
        }
        result.append(sourceText.substring(lastEnd));
        this.callback.setText(result.toString());
        this.updateMatches();
    }

    public void excludeCurrentMatch() {
        if (this.currentMatchIndex < 0) {
            return;
        }
        if (this.excludedMatches.contains(this.currentMatchIndex)) {
            this.excludedMatches.remove((Object)this.currentMatchIndex);
        } else {
            this.excludedMatches.add(this.currentMatchIndex);
            this.goToNextMatch();
        }
    }

    public String getSearchText() {
        return this.searchText;
    }

    public boolean isMouseOverBar(int mouseX, int mouseY) {
        if (!this.visible) {
            return false;
        }
        int totalHeight = this.getTotalHeight();
        return mouseX >= this.x && mouseX < this.x + this.width && mouseY >= this.y && mouseY < this.y + totalHeight;
    }

    public static interface SearchCallback {
        public String getText();

        public String getHighlightedWord();

        public int getSelectionStart();

        public int getSelectionEnd();

        public void setText(String var1);

        public void scrollToPosition(int var1);

        public void setSelection(int var1, int var2);

        public int getGutterWidth();

        public void unfocusMainEditor();

        public void focusMainEditor();

        public void onMatchesUpdated();

        public void resizeEditor(boolean var1, int var2);
    }
}

