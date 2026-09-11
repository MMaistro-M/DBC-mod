/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.List;
import kamkeel.npcs.util.TextSplitter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.CustomNpcResourceListener;
import org.lwjgl.opengl.GL11;

public class GuiNpcLabel
extends Gui {
    public String label;
    public int x;
    public int y;
    public int color;
    public boolean enabled = true;
    public int id;
    public boolean drawShadow = false;
    public String hoverableText = "";
    private boolean wasHovered = false;
    private int hoverCount = 0;

    public GuiNpcLabel(int id, Object label, int x, int y, int color) {
        this.id = id;
        this.label = StatCollector.func_74838_a((String)label.toString());
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public GuiNpcLabel(int id, Object label, int x, int y) {
        this(id, label, x, y, CustomNpcResourceListener.DefaultTextColor);
    }

    public void setHoverText(String text) {
        this.hoverableText = text;
    }

    public void drawLabel(GuiScreen gui, FontRenderer fontRenderer) {
        if (this.enabled) {
            fontRenderer.func_85187_a(this.label, this.x, this.y, this.color, this.drawShadow);
        }
    }

    public void center(int width) {
        int size = Minecraft.func_71410_x().field_71466_p.func_78256_a(this.label);
        this.x += (width - size) / 2;
    }

    public boolean isMouseOver(int mouseX, int mouseY, FontRenderer fr) {
        int width = fr.func_78256_a(this.label);
        return mouseX >= this.x && mouseX <= this.x + width && mouseY >= this.y && mouseY <= this.y + 10;
    }

    public void drawHover(int localMouseX, int localMouseY, int screenMouseX, int screenMouseY, boolean hasSubGui, FontRenderer fr) {
        if (hasSubGui || this.hoverableText.isEmpty()) {
            return;
        }
        boolean hovered = this.isMouseOver(localMouseX, localMouseY, fr);
        if (!hovered) {
            this.wasHovered = false;
            this.hoverCount = 0;
            return;
        }
        if (!this.wasHovered) {
            this.wasHovered = true;
            this.hoverCount = 0;
        }
        if (this.hoverCount < 65) {
            ++this.hoverCount;
        }
        if (this.hoverCount > 60) {
            GL11.glPushMatrix();
            Minecraft mc = Minecraft.func_71410_x();
            String displayString = StatCollector.func_74838_a((String)this.hoverableText);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            List<String> lines = TextSplitter.splitText(displayString, 30);
            this.drawHoveringText(lines, screenMouseX, screenMouseY, mc);
            GL11.glDisable((int)2896);
            GL11.glPopMatrix();
        }
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
}

