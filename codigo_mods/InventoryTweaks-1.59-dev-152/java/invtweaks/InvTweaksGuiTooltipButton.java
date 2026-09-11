/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 */
package invtweaks;

import invtweaks.InvTweaksObfuscation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class InvTweaksGuiTooltipButton
extends GuiButton {
    public static final int DEFAULT_BUTTON_WIDTH = 200;
    public static final int LINE_HEIGHT = 11;
    private int hoverTime = 0;
    private long prevSystemTime = 0L;
    private String tooltip = null;
    private String[] tooltipLines = null;
    private int tooltipWidth = -1;
    private boolean drawBackground = true;

    public InvTweaksGuiTooltipButton(int id, int x, int y, String displayString) {
        this(id, x, y, 150, 20, displayString, null);
    }

    public InvTweaksGuiTooltipButton(int id, int x, int y, String displayString, String tooltip) {
        this(id, x, y, 150, 20, displayString, tooltip);
    }

    public InvTweaksGuiTooltipButton(int id, int x, int y, int w, int h, String displayString) {
        this(id, x, y, w, h, displayString, null);
    }

    public InvTweaksGuiTooltipButton(int id, int x, int y, int w, int h, String displayString, String tooltip) {
        super(id, x, y, w, h, displayString);
        if (tooltip != null) {
            this.setTooltip(tooltip);
        }
    }

    public InvTweaksGuiTooltipButton(int id, int x, int y, int w, int h, String displayString, String tooltip, boolean drawBackground) {
        super(id, x, y, w, h, displayString);
        if (tooltip != null) {
            this.setTooltip(tooltip);
        }
        this.drawBackground = drawBackground;
    }

    public void func_146112_a(Minecraft minecraft, int i, int j) {
        if (this.drawBackground) {
            super.func_146112_a(minecraft, i, j);
        } else {
            this.func_73731_b(minecraft.field_71466_p, this.field_146126_j, this.field_146128_h, this.field_146129_i + (this.field_146121_g - 8) / 2, 0x999999);
        }
        InvTweaksObfuscation obf = new InvTweaksObfuscation(minecraft);
        if (this.tooltipLines != null) {
            if (this.isMouseOverButton(i, j)) {
                long systemTime = System.currentTimeMillis();
                if (this.prevSystemTime != 0L) {
                    this.hoverTime = (int)((long)this.hoverTime + (systemTime - this.prevSystemTime));
                }
                this.prevSystemTime = systemTime;
            } else {
                this.hoverTime = 0;
                this.prevSystemTime = 0L;
            }
            if (this.hoverTime > 800 && this.tooltipLines != null) {
                FontRenderer fontRenderer = obf.getFontRenderer();
                int x = i + 12;
                int y = j - 11 * this.tooltipLines.length;
                if (this.tooltipWidth == -1) {
                    for (String line : this.tooltipLines) {
                        this.tooltipWidth = Math.max(fontRenderer.func_78256_a(line), this.tooltipWidth);
                    }
                }
                if (x + this.tooltipWidth > obf.getCurrentScreen().field_146294_l) {
                    x = obf.getCurrentScreen().field_146294_l - this.tooltipWidth;
                }
                this.func_73733_a(x - 3, y - 3, x + this.tooltipWidth + 3, y + 11 * this.tooltipLines.length, -1073741824, -1073741824);
                int lineCount = 0;
                for (String line : this.tooltipLines) {
                    int j1 = y + lineCount++ * 11;
                    int k = -1;
                    fontRenderer.func_78261_a(line, x, j1, k);
                }
            }
        }
    }

    protected boolean isMouseOverButton(int i, int j) {
        return i >= this.field_146128_h && j >= this.field_146129_i && i < this.field_146128_h + this.field_146120_f && j < this.field_146129_i + this.field_146121_g;
    }

    protected int getTextColor(int i, int j) {
        int textColor = -2039584;
        if (!this.field_146124_l) {
            textColor = -6250336;
        } else if (this.isMouseOverButton(i, j)) {
            textColor = -96;
        }
        return textColor;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip = tooltip.replace("\\n", "\n");
        this.tooltipLines = tooltip.split("\n");
    }

    public String getTooltip() {
        return this.tooltip;
    }
}

