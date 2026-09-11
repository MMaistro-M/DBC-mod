/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kamkeel.npcs.util.TextSplitter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

public class GuiNpcButton
extends GuiButton {
    protected String[] display;
    private int displayValue = 0;
    public int field_146127_k;
    public String hoverableText = "";
    public int oldHover;
    public int hoverCount = 0;
    boolean hasSubGUI = false;
    protected ResourceLocation iconTexture = null;
    protected int iconWidth = 16;
    protected int iconHeight = 16;
    protected int iconPosX = 0;
    protected int iconPosY = 0;
    public boolean rightClickable;
    public AtomicBoolean rightClicked = new AtomicBoolean();

    public GuiNpcButton(int i, int j, int k, String s) {
        super(i, j, k, StatCollector.func_74838_a((String)s));
        this.field_146127_k = i;
    }

    public GuiNpcButton(int i, int j, int k, String[] display, int val) {
        this(i, j, k, display[val]);
        this.display = display;
        this.displayValue = val;
        if (display.length > 1) {
            this.rightClickable = true;
        }
    }

    public GuiNpcButton(int i, int j, int k, int l, int m, String string) {
        super(i, j, k, l, m, StatCollector.func_74838_a((String)string));
        this.field_146127_k = i;
    }

    public GuiNpcButton(int i, int j, int k, int l, int m, String[] display, int val) {
        this(i, j, k, l, m, display.length == 0 ? "" : display[val % display.length]);
        this.display = display;
        int n = this.displayValue = display.length == 0 ? 0 : val % display.length;
        if (display.length > 1) {
            this.rightClickable = true;
        }
    }

    public GuiNpcButton(int i, int j, int k, int l, int m, Enum<?>[] displayEnums, int val) {
        this(i, j, k, l, m, displayEnums.length == 0 ? "" : displayEnums[val % displayEnums.length].toString());
        ArrayList<String> strings = new ArrayList<String>();
        for (Enum<?> e : displayEnums) {
            strings.add(e.toString());
        }
        this.display = strings.toArray(new String[0]);
        int n = this.displayValue = this.display.length == 0 ? 0 : val % this.display.length;
        if (this.display.length > 1) {
            this.rightClickable = true;
        }
    }

    public void setDisplayText(String text) {
        this.field_146126_j = StatCollector.func_74838_a((String)text);
    }

    public void setHoverText(String text) {
        String translated = StatCollector.func_74838_a((String)text);
        this.hoverableText = translated == null ? "" : translated.replace("\\n", "\n");
    }

    public int getValue() {
        return this.displayValue;
    }

    public void setEnabled(boolean bo) {
        this.field_146124_l = bo;
    }

    public void setVisible(boolean b) {
        this.field_146125_m = b;
    }

    public boolean getVisible() {
        return this.field_146125_m;
    }

    public void setDisplay(int value) {
        this.displayValue = value;
        this.setDisplayText(this.display[value]);
    }

    public void setTextColor(int color) {
        this.packedFGColour = color;
    }

    public GuiNpcButton setIconTexture(ResourceLocation texture) {
        this.iconTexture = texture;
        return this;
    }

    public GuiNpcButton setIconPos(int width, int height, int x, int y) {
        this.iconWidth = width;
        this.iconHeight = height;
        this.iconPosX = x;
        this.iconPosY = y;
        return this;
    }

    public boolean func_146116_c(Minecraft minecraft, int i, int j) {
        boolean bo = super.func_146116_c(minecraft, i, j);
        if (bo && this.display != null && this.display.length != 0) {
            if (this.rightClicked.get()) {
                if (this.displayValue <= 0) {
                    this.displayValue = this.display.length;
                }
                --this.displayValue;
            } else {
                this.displayValue = (this.displayValue + 1) % this.display.length;
            }
            this.setDisplayText(this.display[this.displayValue]);
        }
        return bo;
    }

    public int getWidth() {
        return this.field_146120_f;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (!this.field_146125_m) {
            return;
        }
        String original = this.field_146126_j;
        FontRenderer fr = mc.field_71466_p;
        if (fr != null) {
            String ellipsis;
            int targetWidth;
            int maxTextWidth = this.field_146120_f - 6;
            if (fr.func_78256_a(original) > maxTextWidth && (targetWidth = maxTextWidth - fr.func_78256_a(ellipsis = "...")) > 0) {
                this.field_146126_j = fr.func_78269_a(original, targetWidth) + ellipsis;
            }
        }
        if (this.field_146120_f > 396) {
            this.drawWideButton(mc, mouseX, mouseY);
        } else {
            super.func_146112_a(mc, mouseX, mouseY);
        }
        this.field_146126_j = original;
        if (this.iconTexture != null) {
            mc.func_110434_K().func_110577_a(this.iconTexture);
            GL11.glPushMatrix();
            RenderHelper.func_74520_c();
            int iconX = this.field_146128_h + (this.field_146120_f - this.iconWidth) / 2;
            int iconY = this.field_146129_i + (this.field_146121_g - this.iconHeight) / 2;
            this.func_73729_b(iconX, iconY, this.iconPosX, this.iconPosY, this.iconWidth, this.iconHeight);
            RenderHelper.func_74518_a();
            GL11.glPopMatrix();
        }
    }

    private void drawWideButton(Minecraft mc, int mouseX, int mouseY) {
        FontRenderer fontrenderer = mc.field_71466_p;
        mc.func_110434_K().func_110577_a(field_146122_a);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
        int k = this.func_146114_a(this.field_146123_n);
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glBlendFunc((int)770, (int)771);
        int texY = 46 + k * 20;
        int cap = 100;
        this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, texY, cap, this.field_146121_g);
        this.func_73729_b(this.field_146128_h + this.field_146120_f - cap, this.field_146129_i, 200 - cap, texY, cap, this.field_146121_g);
        int gapStart = this.field_146128_h + cap;
        int gapEnd = this.field_146128_h + this.field_146120_f - cap;
        for (int x = gapStart; x < gapEnd; x += 2) {
            int drawW = Math.min(2, gapEnd - x);
            this.func_73729_b(x, this.field_146129_i, 99, texY, drawW, this.field_146121_g);
        }
        this.func_146119_b(mc, mouseX, mouseY);
        int l = 0xE0E0E0;
        if (this.packedFGColour != 0) {
            l = this.packedFGColour;
        } else if (!this.field_146124_l) {
            l = 0xA0A0A0;
        } else if (this.field_146123_n) {
            l = 0xFFFFA0;
        }
        this.func_73732_a(fontrenderer, this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, l);
    }

    public void drawHover(int i, int j, boolean hasSubGui) {
        if (hasSubGui || !this.field_146125_m || this.hoverableText.isEmpty()) {
            return;
        }
        int hoverState = this.func_146114_a(this.field_146123_n);
        if (this.oldHover != hoverState) {
            this.oldHover = hoverState;
            this.hoverCount = 0;
        } else if (this.hoverCount < 65) {
            ++this.hoverCount;
        }
        if (hoverState == 2 && this.hoverCount > 60) {
            GL11.glPushMatrix();
            Minecraft mc = Minecraft.func_71410_x();
            String displayString = StatCollector.func_74838_a((String)this.hoverableText);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            List<String> lines = this.splitHoverText(displayString, 30);
            this.drawHoveringText(lines, i, j, mc);
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

    protected void drawHoveringText(List textLines, int x, int y, Minecraft mc) {
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
        this.field_73735_i = 300.0f;
        int j1 = -267386864;
        this.func_73733_a(j2 - 3, k2 - 4, j2 + maxWidth + 3, k2 - 3, j1, j1);
        this.func_73733_a(j2 - 3, k2 + maxHeight + 3, j2 + maxWidth + 3, k2 + maxHeight + 4, j1, j1);
        this.func_73733_a(j2 - 3, k2 - 3, j2 + maxWidth + 3, k2 + maxHeight + 3, j1, j1);
        this.func_73733_a(j2 - 4, k2 - 3, j2 - 3, k2 + maxHeight + 3, j1, j1);
        this.func_73733_a(j2 + maxWidth + 3, k2 - 3, j2 + maxWidth + 4, k2 + maxHeight + 3, j1, j1);
        int k1 = 0x505000FF;
        int l1 = (k1 & 0xFEFEFE) >> 1 | k1 & 0xFF000000;
        this.func_73733_a(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + maxHeight + 3 - 1, k1, l1);
        this.func_73733_a(j2 + maxWidth + 2, k2 - 3 + 1, j2 + maxWidth + 3, k2 + maxHeight + 3 - 1, k1, l1);
        this.func_73733_a(j2 - 3, k2 - 3, j2 + maxWidth + 3, k2 - 3 + 1, k1, k1);
        this.func_73733_a(j2 - 3, k2 + maxHeight + 2, j2 + maxWidth + 3, k2 + maxHeight + 3, l1, l1);
        for (int i2 = 0; i2 < textLines.size(); ++i2) {
            String s1 = (String)textLines.get(i2);
            mc.field_71466_p.func_78261_a(s1, j2, k2, -1);
            if (i2 == 0) {
                k2 += 2;
            }
            k2 += 10;
        }
        this.field_73735_i = 0.0f;
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        RenderHelper.func_74519_b();
        GL11.glEnable((int)32826);
    }

    public int func_146114_a(boolean mouseOver) {
        int b0 = 1;
        if (!this.field_146124_l) {
            b0 = 0;
        } else if (mouseOver && !this.hasSubGUI) {
            b0 = 2;
        }
        return b0;
    }

    public void updateSubGUI(boolean hasSubGUI) {
        this.hasSubGUI = hasSubGUI;
    }

    public boolean getHasSubGUI() {
        return this.hasSubGUI;
    }
}

