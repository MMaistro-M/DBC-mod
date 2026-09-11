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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.gui.util.key.OverlayKeyPresetViewer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GoToLineDialog {
    public static final ResourceLocation TEXTURE = OverlayKeyPresetViewer.TEXTURE;
    private int x;
    private int y;
    private int width;
    private int dialogWidth = 200;
    private int dialogHeight = 51;
    private int textFieldWidth = 180;
    private int textFieldHeight = 16;
    private int padding = 8;
    private boolean visible = false;
    private boolean focused = true;
    private String inputText = "";
    private int cursor = 0;
    private int selectionStart = 0;
    private int selectionEnd = 0;
    private int scrollOffset = 0;
    private int cursorCounter = 0;
    private long lastInputTime = 0L;
    private GoToLineCallback callback;
    private final FontRenderer font;

    public GoToLineDialog() {
        this.font = Minecraft.func_71410_x().field_71466_p;
    }

    public void setCallback(GoToLineCallback callback) {
        this.callback = callback;
    }

    public void initGui(int guiX, int guiY, int guiWidth) {
        this.width = guiWidth;
        this.x = guiX + (guiWidth - this.dialogWidth) / 2;
        this.y = guiY + 40;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void show() {
        this.visible = true;
        this.focused = true;
        this.inputText = "";
        this.cursor = 0;
        this.selectionStart = 0;
        this.selectionEnd = 0;
        this.scrollOffset = 0;
        this.markActivity();
        if (this.callback != null) {
            this.callback.unfocusMainEditor();
        }
    }

    public void close() {
        this.visible = false;
        this.focused = false;
        if (this.callback != null) {
            this.callback.onDialogClose();
            this.callback.focusMainEditor();
        }
    }

    public void toggle() {
        if (this.visible && this.focused) {
            this.close();
        } else {
            this.show();
        }
    }

    public boolean hasFocus() {
        return this.visible && this.focused;
    }

    public void unfocus() {
        this.focused = false;
    }

    public void draw(int mouseX, int mouseY) {
        if (!this.visible) {
            return;
        }
        ParseResult result = null;
        if (!this.inputText.isEmpty()) {
            result = this.parseInput(this.inputText);
        }
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.drawDialogBackground(result != null && !result.valid);
        String title = "Go to Line";
        this.font.func_78276_b(title, this.x + this.padding + 1, this.y + this.padding + 4, -2039584);
        int lineCount = this.callback != null ? this.callback.getLineCount() : 0;
        String hint = "[" + lineCount + " lines]";
        int hintWidth = this.font.func_78256_a(hint);
        this.dialogWidth = 200;
        this.font.func_78276_b(hint, this.x + this.dialogWidth - this.padding - hintWidth, this.y + this.padding + 4, -7829368);
        int fieldX = this.x + this.padding + 0;
        int fieldY = this.y + this.padding + 14 + 5;
        this.textFieldWidth = this.dialogWidth - this.padding * 2;
        this.drawTextField(fieldX, fieldY, this.textFieldWidth, this.textFieldHeight, this.inputText, this.cursor, this.selectionStart, this.selectionEnd, this.scrollOffset, this.focused);
        if (this.inputText.isEmpty()) {
            this.font.func_78276_b("[Line][:Column]", this.x + this.padding + 6, this.y + this.padding + 23, -7829368);
        }
        if (result != null && !result.valid) {
            this.font.func_78276_b(result.error, this.x + this.padding + 2, this.y + this.dialogHeight - 3, -39322);
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    private void drawDialogBackground(boolean resultInvalid) {
        int dialogHeight = this.dialogHeight;
        if (resultInvalid) {
            dialogHeight += 5;
        }
        int y = this.y + 5;
        Gui.func_73734_a((int)(this.x + 2), (int)(y + 2), (int)(this.x + this.dialogWidth + 2), (int)(y + dialogHeight + 2), (int)Integer.MIN_VALUE);
        Gui.func_73734_a((int)this.x, (int)y, (int)(this.x + this.dialogWidth), (int)(y + dialogHeight), (int)-13816528);
        Gui.func_73734_a((int)this.x, (int)y, (int)(this.x + this.dialogWidth), (int)(y + 1), (int)-12829636);
        Gui.func_73734_a((int)this.x, (int)(y + dialogHeight - 1), (int)(this.x + this.dialogWidth), (int)(y + dialogHeight), (int)-12829636);
        Gui.func_73734_a((int)this.x, (int)y, (int)(this.x + 1), (int)(y + dialogHeight), (int)-12829636);
        Gui.func_73734_a((int)(this.x + this.dialogWidth - 1), (int)y, (int)(this.x + this.dialogWidth), (int)(y + dialogHeight), (int)-12829636);
    }

    private void drawTextField(int fx, int fy, int fw, int fh, String text, int cursor, int selStart, int selEnd, int scrollOff, boolean isFocused) {
        Gui.func_73734_a((int)fx, (int)fy, (int)(fx + fw), (int)(fy + fh), (int)-14803426);
        Gui.func_73734_a((int)fx, (int)fy, (int)(fx + fw), (int)(fy + 1), (int)(isFocused ? -16745780 : -12829636));
        Gui.func_73734_a((int)fx, (int)(fy + fh - 1), (int)(fx + fw), (int)(fy + fh), (int)(isFocused ? -16745780 : -12829636));
        Gui.func_73734_a((int)fx, (int)fy, (int)(fx + 1), (int)(fy + fh), (int)(isFocused ? -16745780 : -12829636));
        Gui.func_73734_a((int)(fx + fw - 1), (int)fy, (int)(fx + fw), (int)(fy + fh), (int)(isFocused ? -16745780 : -12829636));
        int textX = fx + 4;
        int textY = fy + (fh - 8) / 2;
        int visibleWidth = fw - 8;
        String visibleText = text;
        int adjustedCursor = cursor;
        int adjustedSelStart = selStart;
        int adjustedSelEnd = selEnd;
        if (scrollOff > 0 && scrollOff < text.length()) {
            visibleText = text.substring(scrollOff);
            adjustedCursor = cursor - scrollOff;
            adjustedSelStart = Math.max(0, selStart - scrollOff);
            adjustedSelEnd = Math.max(0, selEnd - scrollOff);
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
        this.font.func_78276_b(visibleText, textX, textY, -2039584);
        if (isFocused && this.shouldShowCursor()) {
            String beforeCursor = adjustedCursor > 0 && adjustedCursor <= visibleText.length() ? visibleText.substring(0, adjustedCursor) : "";
            int cursorX = textX + this.font.func_78256_a(beforeCursor);
            Gui.func_73734_a((int)cursorX, (int)(textY - 1), (int)(cursorX + 1), (int)(textY + 9), (int)-2039584);
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (!this.visible) {
            return false;
        }
        if (mouseX >= this.x && mouseX < this.x + this.dialogWidth && mouseY >= this.y && mouseY < this.y + this.dialogHeight) {
            int fieldX = this.x + this.padding;
            int fieldY = this.y + this.padding + 14;
            if (mouseX >= fieldX && mouseX < fieldX + this.textFieldWidth && mouseY >= fieldY && mouseY < fieldY + this.textFieldHeight) {
                this.focused = true;
                if (this.callback != null) {
                    this.callback.unfocusMainEditor();
                }
                int clickX = mouseX - fieldX - 4;
                String visibleText = this.scrollOffset > 0 && this.scrollOffset < this.inputText.length() ? this.inputText.substring(this.scrollOffset) : this.inputText;
                int newCursor = this.getCharIndexAtX(visibleText, clickX) + this.scrollOffset;
                this.selectionStart = this.cursor = Math.max(0, Math.min(newCursor, this.inputText.length()));
                this.selectionEnd = this.cursor;
                this.markActivity();
            }
            return true;
        }
        this.close();
        return false;
    }

    private int getCharIndexAtX(String text, int targetX) {
        int x = 0;
        for (int i = 0; i < text.length(); ++i) {
            int charWidth = this.font.func_78263_a(text.charAt(i));
            if (x + charWidth / 2 > targetX) {
                return i;
            }
            x += charWidth;
        }
        return text.length();
    }

    public boolean keyTyped(char c, int keyCode) {
        boolean shift;
        if (keyCode == 1 && this.visible) {
            this.close();
            return true;
        }
        if (!this.visible || !this.focused) {
            return false;
        }
        boolean ctrl = Keyboard.isKeyDown((int)29) || Keyboard.isKeyDown((int)157);
        boolean bl = shift = Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54);
        if (keyCode == 28 || keyCode == 156) {
            this.executeGoToLine();
            return true;
        }
        if (ctrl && keyCode == 30) {
            this.selectionStart = 0;
            this.selectionEnd = this.inputText.length();
            this.cursor = this.inputText.length();
            this.markActivity();
            return true;
        }
        if (ctrl && keyCode == 46) {
            if (this.selectionStart != this.selectionEnd) {
                int minSel = Math.min(this.selectionStart, this.selectionEnd);
                int maxSel = Math.max(this.selectionStart, this.selectionEnd);
                NoppesStringUtils.setClipboardContents(this.inputText.substring(minSel, maxSel));
            }
            return true;
        }
        if (ctrl && keyCode == 45) {
            if (this.selectionStart != this.selectionEnd) {
                int minSel = Math.min(this.selectionStart, this.selectionEnd);
                int maxSel = Math.max(this.selectionStart, this.selectionEnd);
                NoppesStringUtils.setClipboardContents(this.inputText.substring(minSel, maxSel));
                this.inputText = this.inputText.substring(0, minSel) + this.inputText.substring(maxSel);
                this.selectionStart = this.cursor = minSel;
                this.selectionEnd = this.cursor;
                this.updateScrollOffset();
                this.markActivity();
            }
            return true;
        }
        if (ctrl && keyCode == 47) {
            String clipboard = NoppesStringUtils.getClipboardContents();
            if (clipboard != null) {
                StringBuilder filtered = new StringBuilder();
                for (char ch : clipboard.toCharArray()) {
                    if (!Character.isDigit(ch) && ch != ':') continue;
                    filtered.append(ch);
                }
                this.insertText(filtered.toString());
            }
            return true;
        }
        if (keyCode == 14) {
            if (this.selectionStart != this.selectionEnd) {
                this.deleteSelection();
            } else if (this.cursor > 0) {
                this.inputText = this.inputText.substring(0, this.cursor - 1) + this.inputText.substring(this.cursor);
                --this.cursor;
                this.selectionStart = this.cursor;
                this.selectionEnd = this.cursor;
                this.updateScrollOffset();
            }
            this.markActivity();
            return true;
        }
        if (keyCode == 211) {
            if (this.selectionStart != this.selectionEnd) {
                this.deleteSelection();
            } else if (this.cursor < this.inputText.length()) {
                this.inputText = this.inputText.substring(0, this.cursor) + this.inputText.substring(this.cursor + 1);
            }
            this.markActivity();
            return true;
        }
        if (keyCode == 203) {
            if (shift) {
                if (this.cursor > 0) {
                    --this.cursor;
                    this.selectionEnd = this.cursor;
                }
            } else {
                if (this.selectionStart != this.selectionEnd) {
                    this.cursor = Math.min(this.selectionStart, this.selectionEnd);
                } else if (this.cursor > 0) {
                    --this.cursor;
                }
                this.selectionStart = this.cursor;
                this.selectionEnd = this.cursor;
            }
            this.updateScrollOffset();
            this.markActivity();
            return true;
        }
        if (keyCode == 205) {
            if (shift) {
                if (this.cursor < this.inputText.length()) {
                    ++this.cursor;
                    this.selectionEnd = this.cursor;
                }
            } else {
                if (this.selectionStart != this.selectionEnd) {
                    this.cursor = Math.max(this.selectionStart, this.selectionEnd);
                } else if (this.cursor < this.inputText.length()) {
                    ++this.cursor;
                }
                this.selectionStart = this.cursor;
                this.selectionEnd = this.cursor;
            }
            this.updateScrollOffset();
            this.markActivity();
            return true;
        }
        if (keyCode == 199) {
            if (shift) {
                this.selectionEnd = 0;
                this.cursor = 0;
            } else {
                this.selectionStart = this.cursor = 0;
                this.selectionEnd = this.cursor;
            }
            this.updateScrollOffset();
            this.markActivity();
            return true;
        }
        if (keyCode == 207) {
            if (shift) {
                this.selectionEnd = this.inputText.length();
                this.cursor = this.inputText.length();
            } else {
                this.selectionStart = this.cursor = this.inputText.length();
                this.selectionEnd = this.cursor;
            }
            this.updateScrollOffset();
            this.markActivity();
            return true;
        }
        if (ChatAllowedCharacters.func_71566_a((char)c) && (Character.isDigit(c) || c == ':')) {
            this.insertText("" + c);
            this.markActivity();
            return true;
        }
        return true;
    }

    private void insertText(String text) {
        if (this.selectionStart != this.selectionEnd) {
            this.deleteSelection();
        }
        this.inputText = this.inputText.substring(0, this.cursor) + text + this.inputText.substring(this.cursor);
        this.cursor += text.length();
        this.selectionStart = this.cursor;
        this.selectionEnd = this.cursor;
        this.updateScrollOffset();
    }

    private void deleteSelection() {
        int minSel = Math.min(this.selectionStart, this.selectionEnd);
        int maxSel = Math.max(this.selectionStart, this.selectionEnd);
        this.inputText = this.inputText.substring(0, minSel) + this.inputText.substring(maxSel);
        this.selectionStart = this.cursor = minSel;
        this.selectionEnd = this.cursor;
        this.updateScrollOffset();
    }

    private void updateScrollOffset() {
        int fieldWidth = this.textFieldWidth - 8;
        String beforeCursor = this.cursor > 0 ? this.inputText.substring(0, this.cursor) : "";
        int cursorX = this.font.func_78256_a(beforeCursor);
        if (cursorX - this.scrollOffset * 6 > fieldWidth) {
            this.scrollOffset = Math.max(0, this.cursor - fieldWidth / 6);
        } else if (this.cursor < this.scrollOffset) {
            this.scrollOffset = this.cursor;
        }
    }

    private void executeGoToLine() {
        ParseResult result = this.parseInput(this.inputText);
        if (result.valid && this.callback != null) {
            this.callback.goToLineColumn(result.line, result.column);
            this.close();
        }
    }

    private ParseResult parseInput(String input) {
        int lineCount;
        int line;
        if (input == null || input.trim().isEmpty()) {
            return ParseResult.invalid("Enter line number");
        }
        input = input.trim();
        int column = 1;
        int colonIndex = input.indexOf(58);
        if (colonIndex >= 0) {
            String linePart = input.substring(0, colonIndex);
            String colPart = input.substring(colonIndex + 1);
            if (linePart.isEmpty()) {
                return ParseResult.invalid("Invalid line number");
            }
            try {
                line = Integer.parseInt(linePart);
            }
            catch (NumberFormatException e) {
                return ParseResult.invalid("Invalid line number");
            }
            if (!colPart.isEmpty()) {
                try {
                    column = Integer.parseInt(colPart);
                }
                catch (NumberFormatException e) {
                    return ParseResult.invalid("Invalid column number");
                }
            }
        } else {
            try {
                line = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                return ParseResult.invalid("Invalid line number");
            }
        }
        int n = lineCount = this.callback != null ? this.callback.getLineCount() : 0;
        if (line < 1) {
            return ParseResult.invalid("Line must be >= 1");
        }
        if (line > lineCount) {
            return ParseResult.invalid("Line must be <= " + lineCount);
        }
        if (column < 1) {
            return ParseResult.invalid("Column must be >= 1");
        }
        return ParseResult.of(line, column);
    }

    public void updateCursor() {
        ++this.cursorCounter;
    }

    private void markActivity() {
        this.lastInputTime = System.currentTimeMillis();
    }

    private boolean shouldShowCursor() {
        if (System.currentTimeMillis() - this.lastInputTime < 500L) {
            return true;
        }
        return this.cursorCounter / 10 % 2 == 0;
    }

    public int getTotalHeight() {
        return this.visible ? this.dialogHeight + 10 : 0;
    }

    private static class ParseResult {
        boolean valid;
        int line;
        int column;
        String error;

        private ParseResult() {
        }

        static ParseResult invalid(String error) {
            ParseResult r = new ParseResult();
            r.valid = false;
            r.error = error;
            return r;
        }

        static ParseResult of(int line, int column) {
            ParseResult r = new ParseResult();
            r.valid = true;
            r.line = line;
            r.column = column;
            return r;
        }
    }

    public static interface GoToLineCallback {
        public int getLineCount();

        public int getColumnCount(int var1);

        public void goToLineColumn(int var1, int var2);

        public void onDialogClose();

        public void unfocusMainEditor();

        public void focusMainEditor();
    }
}

