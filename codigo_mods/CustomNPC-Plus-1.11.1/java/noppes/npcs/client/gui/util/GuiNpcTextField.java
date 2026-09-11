/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.util.FileNameHelper;
import kamkeel.npcs.util.TextSplitter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.util.ITextfieldListener;
import org.lwjgl.opengl.GL11;

public class GuiNpcTextField
extends GuiTextField {
    public boolean enabled = true;
    public boolean inMenu = true;
    public boolean integersOnly = false;
    public boolean doublesOnly = false;
    public boolean floatsOnly = false;
    private ITextfieldListener listener;
    public int id;
    public int min = 0;
    public int max = Integer.MAX_VALUE;
    public int def = 0;
    public double minDouble = 0.0;
    public double maxDouble = Double.MAX_VALUE;
    public double defDouble = 0.0;
    public float minFloat = 0.0f;
    public float maxFloat = Float.MAX_VALUE;
    public float defFloat = 0.0f;
    protected static GuiNpcTextField activeTextfield = null;
    public boolean canEdit = true;
    private boolean fileNameSafe = false;
    public String hoverableText = "";
    private boolean wasHovered = false;
    private int hoverCount = 0;
    private final int[] allowedSpecialChars = new int[]{14, 211, 203, 205, 199, 207};

    public GuiNpcTextField(int id, GuiScreen parent, FontRenderer fontRenderer, int i, int j, int k, int l, String s) {
        super(fontRenderer, i, j, k, l);
        this.func_146203_f(500);
        this.func_146180_a(s);
        this.func_146196_d();
        this.id = id;
        if (parent instanceof ITextfieldListener) {
            this.listener = (ITextfieldListener)parent;
        }
    }

    public static boolean isFieldActive() {
        return activeTextfield != null;
    }

    public GuiNpcTextField(int id, GuiScreen parent, int i, int j, int k, int l, String s) {
        this(id, parent, Minecraft.func_71410_x().field_71466_p, i, j, k, l, s);
    }

    public void initGui() {
    }

    protected boolean charAllowed(char c, int i) {
        if (!this.integersOnly || Character.isDigit(c)) {
            return true;
        }
        if (this.integersOnly && this.func_146179_b().isEmpty() && c == '-') {
            return true;
        }
        if ((this.floatsOnly || this.doublesOnly) && this.func_146179_b().isEmpty() && c == '-') {
            return true;
        }
        for (int j : this.allowedSpecialChars) {
            if (j != i) continue;
            return true;
        }
        return false;
    }

    public boolean func_146201_a(char c, int i) {
        if (!this.canEdit || !this.func_146206_l()) {
            return false;
        }
        if (this.fileNameSafe && !GuiScreen.func_146271_m() && !this.isFileNameCharAllowed(c, i)) {
            return false;
        }
        if (GuiScreen.func_146271_m()) {
            if (i == 47 && this.isNumericField()) {
                String clipboard = GuiScreen.func_146277_j();
                if (clipboard != null) {
                    StringBuilder filtered = new StringBuilder();
                    for (char ch : clipboard.toCharArray()) {
                        if (!Character.isDigit(ch) && ch != '-' && (!this.doublesOnly && !this.floatsOnly || ch != '.')) continue;
                        filtered.append(ch);
                    }
                    if (filtered.length() > 0) {
                        this.func_146191_b(filtered.toString());
                    }
                }
                return true;
            }
            if (i == 47 && this.fileNameSafe) {
                String filtered;
                String clipboard = GuiScreen.func_146277_j();
                if (clipboard != null && !(filtered = this.filterFileNameClipboard(clipboard)).isEmpty()) {
                    this.func_146191_b(filtered);
                }
                return true;
            }
            boolean handled = super.func_146201_a(c, i);
            return handled;
        }
        if (!this.charAllowed(c, i)) {
            return false;
        }
        return super.func_146201_a(c, i);
    }

    private boolean isNumericField() {
        return this.integersOnly || this.doublesOnly || this.floatsOnly;
    }

    public boolean isEmpty() {
        return this.func_146179_b().trim().length() == 0;
    }

    public int getInteger() {
        return Integer.parseInt(this.func_146179_b());
    }

    public boolean isInteger() {
        try {
            Integer.parseInt(this.func_146179_b());
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public double getDouble() {
        return Double.parseDouble(this.func_146179_b());
    }

    public boolean isDouble() {
        try {
            Double.parseDouble(this.func_146179_b());
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public float getFloat() {
        return Float.parseFloat(this.func_146179_b());
    }

    public boolean isFloat() {
        try {
            Float.parseFloat(this.func_146179_b());
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public void func_146192_a(int i, int j, int k) {
        if (!this.canEdit) {
            return;
        }
        boolean wasFocused = this.func_146206_l();
        super.func_146192_a(i, j, k);
        if (wasFocused != this.func_146206_l() && wasFocused) {
            this.unFocused();
        }
        if (this.func_146206_l()) {
            activeTextfield = this;
        }
    }

    public void unFocused() {
        if (this.integersOnly && !this.doublesOnly && !this.floatsOnly) {
            if (this.isEmpty() || !this.isInteger()) {
                this.func_146180_a(this.def + "");
            } else if (this.getInteger() < this.min) {
                this.func_146180_a(this.min + "");
            } else if (this.getInteger() > this.max) {
                this.func_146180_a(this.max + "");
            }
        } else if (this.doublesOnly && !this.floatsOnly) {
            if (this.isEmpty() || !this.isDouble()) {
                this.func_146180_a(this.defDouble + "");
            } else if (this.getDouble() < this.minDouble) {
                this.func_146180_a(this.minDouble + "");
            } else if (this.getDouble() > this.maxDouble) {
                this.func_146180_a(this.maxDouble + "");
            }
        } else if (this.floatsOnly) {
            if (this.isEmpty() || !this.isFloat()) {
                this.func_146180_a(this.defFloat + "");
            } else if (this.getFloat() < this.minFloat) {
                this.func_146180_a(this.minFloat + "");
            } else if (this.getFloat() > this.maxFloat) {
                this.func_146180_a(this.maxFloat + "");
            }
        }
        if (this.fileNameSafe) {
            this.normalizeFileNameText();
        }
        this.func_146196_d();
        if (this.listener != null) {
            this.listener.unFocused(this);
        }
        if (this == activeTextfield) {
            activeTextfield = null;
        }
    }

    public void func_146194_f() {
        if (this.enabled) {
            super.func_146194_f();
        }
    }

    public void setMinMaxDefault(int min, int max, int def) {
        this.min = min;
        this.max = max;
        this.def = def;
    }

    public void setMinMaxDefaultDouble(double min, double max, double def) {
        this.minDouble = min;
        this.maxDouble = max;
        this.defDouble = def;
    }

    public void setMinMaxDefaultFloat(float min, float max, float def) {
        this.minFloat = min;
        this.maxFloat = max;
        this.defFloat = def;
    }

    public static void unfocus() {
        GuiNpcTextField prev = activeTextfield;
        activeTextfield = null;
        if (prev != null) {
            prev.unFocused();
        }
    }

    public void drawTextBox(int mousX, int mousY) {
        this.func_146194_f();
    }

    public GuiNpcTextField setIntegersOnly() {
        this.integersOnly = true;
        return this;
    }

    public GuiNpcTextField setDoublesOnly() {
        this.doublesOnly = true;
        return this;
    }

    public GuiNpcTextField setFloatsOnly() {
        this.floatsOnly = true;
        return this;
    }

    public GuiNpcTextField setFileNameSafe() {
        this.fileNameSafe = true;
        this.normalizeFileNameText();
        return this;
    }

    private boolean isSpecialNavigationKey(int keyCode) {
        for (int allowed : this.allowedSpecialChars) {
            if (allowed != keyCode) continue;
            return true;
        }
        return false;
    }

    private boolean isFileNameCharAllowed(char c, int keyCode) {
        if (this.isSpecialNavigationKey(keyCode) || c < ' ') {
            return true;
        }
        return Character.isLetterOrDigit(c) || c == '_' || c == '-' || c == ' ';
    }

    private String filterFileNameClipboard(String clipboard) {
        StringBuilder filtered = new StringBuilder(clipboard.length());
        for (int idx = 0; idx < clipboard.length(); ++idx) {
            char c = clipboard.charAt(idx);
            if (Character.isLetterOrDigit(c) || c == '_' || c == '-') {
                filtered.append(c);
                continue;
            }
            if (!Character.isWhitespace(c)) continue;
            filtered.append(' ');
        }
        return filtered.toString();
    }

    private void normalizeFileNameText() {
        String current = this.func_146179_b();
        String sanitized = FileNameHelper.sanitizeTextInput(current);
        if (sanitized.equals(current)) {
            return;
        }
        int cursor = this.func_146198_h();
        this.func_146180_a(sanitized);
        this.func_146190_e(Math.min(cursor, sanitized.length()));
    }

    public void setHoverText(String text) {
        String translated = StatCollector.func_74838_a((String)text);
        this.hoverableText = translated == null ? "" : translated.replace("\\n", "\n");
    }

    public boolean hasHoverText() {
        return this.hoverableText != null && !this.hoverableText.isEmpty();
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= this.field_146209_f && mouseX < this.field_146209_f + this.field_146218_h && mouseY >= this.field_146210_g && mouseY < this.field_146210_g + this.field_146219_i;
    }

    public void drawHover(int mouseX, int mouseY, boolean hasSubGui) {
        this.drawHover(mouseX, mouseY, mouseX, mouseY, hasSubGui);
    }

    public void drawHover(int hitMouseX, int hitMouseY, int tooltipMouseX, int tooltipMouseY, boolean hasSubGui) {
        if (hasSubGui || !this.enabled || this.hoverableText.isEmpty()) {
            return;
        }
        boolean isHovered = this.isMouseOver(hitMouseX, hitMouseY);
        if (!isHovered) {
            this.wasHovered = false;
            this.hoverCount = 0;
            return;
        }
        if (!this.wasHovered) {
            this.wasHovered = true;
            this.hoverCount = 0;
        } else if (this.hoverCount < 65) {
            ++this.hoverCount;
        }
        if (this.hoverCount > 60) {
            GL11.glPushMatrix();
            Minecraft mc = Minecraft.func_71410_x();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            List<String> lines = this.splitHoverText(this.hoverableText, 30);
            this.drawHoveringText(lines, tooltipMouseX, tooltipMouseY, mc);
            GL11.glDisable((int)2896);
            GL11.glPopMatrix();
        }
    }

    private List<String> splitHoverText(String text, int maxLineLength) {
        String[] explicitLines;
        ArrayList<String> lines = new ArrayList<String>();
        if (text == null || text.isEmpty()) {
            return lines;
        }
        String normalized = text.replace("\\n", "\n");
        for (String line : explicitLines = normalized.split("\\r?\\n", -1)) {
            if (line.isEmpty()) {
                lines.add("");
                continue;
            }
            lines.addAll(TextSplitter.splitText(line, maxLineLength));
        }
        return lines;
    }

    protected void drawHoveringText(List<String> textLines, int x, int y, Minecraft mc) {
        if (mc.field_71466_p == null || textLines.isEmpty()) {
            return;
        }
        GL11.glDisable((int)32826);
        RenderHelper.func_74518_a();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        int maxWidth = 0;
        for (String s : textLines) {
            int lineWidth = mc.field_71466_p.func_78256_a(s);
            if (lineWidth <= maxWidth) continue;
            maxWidth = lineWidth;
        }
        int j2 = x + 12;
        int k2 = y - 12;
        int maxHeight = 8;
        if (textLines.size() > 1) {
            maxHeight += 2 + (textLines.size() - 1) * 10;
        }
        ScaledResolution scaledRes = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int screenWidth = scaledRes.func_78326_a();
        int screenHeight = scaledRes.func_78328_b();
        if (j2 + maxWidth + 6 > screenWidth) {
            j2 = x - maxWidth - 16;
        }
        if (j2 < 4) {
            j2 = 4;
        }
        if (k2 + maxHeight + 6 > screenHeight) {
            k2 = screenHeight - maxHeight - 6;
        }
        if (k2 < 4) {
            k2 = 4;
        }
        int zLevel = 300;
        int bgColor = -267386864;
        int borderColor1 = 0x505000FF;
        int borderColor2 = (borderColor1 & 0xFEFEFE) >> 1 | borderColor1 & 0xFF000000;
        this.drawGradientRect(j2 - 3, k2 - 4, j2 + maxWidth + 3, k2 - 3, bgColor, bgColor, zLevel);
        this.drawGradientRect(j2 - 3, k2 + maxHeight + 3, j2 + maxWidth + 3, k2 + maxHeight + 4, bgColor, bgColor, zLevel);
        this.drawGradientRect(j2 - 3, k2 - 3, j2 + maxWidth + 3, k2 + maxHeight + 3, bgColor, bgColor, zLevel);
        this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + maxHeight + 3, bgColor, bgColor, zLevel);
        this.drawGradientRect(j2 + maxWidth + 3, k2 - 3, j2 + maxWidth + 4, k2 + maxHeight + 3, bgColor, bgColor, zLevel);
        this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + maxHeight + 3 - 1, borderColor1, borderColor2, zLevel);
        this.drawGradientRect(j2 + maxWidth + 2, k2 - 3 + 1, j2 + maxWidth + 3, k2 + maxHeight + 3 - 1, borderColor1, borderColor2, zLevel);
        this.drawGradientRect(j2 - 3, k2 - 3, j2 + maxWidth + 3, k2 - 3 + 1, borderColor1, borderColor1, zLevel);
        this.drawGradientRect(j2 - 3, k2 + maxHeight + 2, j2 + maxWidth + 3, k2 + maxHeight + 3, borderColor2, borderColor2, zLevel);
        for (int l = 0; l < textLines.size(); ++l) {
            String s1 = textLines.get(l);
            mc.field_71466_p.func_78261_a(s1, j2, k2, -1);
            if (l == 0) {
                k2 += 2;
            }
            k2 += 10;
        }
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        RenderHelper.func_74519_b();
        GL11.glEnable((int)32826);
    }

    protected void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor, int zLevel) {
        float f = (float)(startColor >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(startColor >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(startColor >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(startColor & 0xFF) / 255.0f;
        float f4 = (float)(endColor >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(endColor >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(endColor >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(endColor & 0xFF) / 255.0f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(f1, f2, f3, f);
        tessellator.func_78377_a((double)right, (double)top, (double)zLevel);
        tessellator.func_78377_a((double)left, (double)top, (double)zLevel);
        tessellator.func_78369_a(f5, f6, f7, f4);
        tessellator.func_78377_a((double)left, (double)bottom, (double)zLevel);
        tessellator.func_78377_a((double)right, (double)bottom, (double)zLevel);
        tessellator.func_78381_a();
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }
}

