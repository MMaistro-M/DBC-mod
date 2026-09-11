/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.texture.TextureManager
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.TextureManager;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.renderer.AnimationHelper;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.controllers.data.PlayerEffect;
import org.lwjgl.opengl.GL11;

public class GuiEffectBar
extends GuiScreen {
    public int x;
    public int y;
    public int field_146294_l;
    public int field_146295_m;
    public int scrollY = 0;
    public int entryHeight = 28;
    public List<EffectEntry> entries = new ArrayList<EffectEntry>();
    public FontRenderer fontRenderer;

    public GuiEffectBar(int x, int y, int width, int height) {
        this.fontRenderer = Minecraft.func_71410_x().field_71466_p;
        this.x = x;
        this.y = y;
        this.field_146294_l = width;
        this.field_146295_m = height;
    }

    public void func_73866_w_() {
        this.fontRenderer = Minecraft.func_71410_x().field_71466_p;
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        GuiEffectBar.func_73734_a((int)this.x, (int)this.y, (int)(this.x + this.field_146294_l), (int)(this.y + this.field_146295_m), (int)-1879048192);
        int totalHeight = this.entries.size() * (this.entryHeight + 2);
        if (this.scrollY < 0) {
            this.scrollY = 0;
        }
        if (this.scrollY > totalHeight - this.field_146295_m) {
            this.scrollY = Math.max(totalHeight - this.field_146295_m, 0);
        }
        GL11.glEnable((int)3089);
        Minecraft minecraft = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(minecraft, minecraft.field_71443_c, minecraft.field_71440_d);
        int scaleFactor = sr.func_78325_e();
        int scissorX = this.x * scaleFactor;
        int scissorY = (sr.func_78328_b() - (this.y + this.field_146295_m)) * scaleFactor;
        int scissorW = this.field_146294_l * scaleFactor;
        int scissorH = this.field_146295_m * scaleFactor;
        GL11.glScissor((int)scissorX, (int)scissorY, (int)scissorW, (int)scissorH);
        int iconRenderSize = 24;
        int padding = 1;
        int iconYOffset = (this.entryHeight - iconRenderSize) / 2;
        int startIndex = this.scrollY / (this.entryHeight + padding);
        int endIndex = Math.min(this.entries.size(), startIndex + this.field_146295_m / (this.entryHeight + padding) + 1);
        ArrayList<String> hoveredTooltip = null;
        int tooltipX = 0;
        int tooltipY = 0;
        CustomEffect toolTipEffect = null;
        for (int i = startIndex; i < endIndex; ++i) {
            int drawY = this.y + (i - startIndex) * (this.entryHeight + padding) - this.scrollY % (this.entryHeight + padding);
            EffectEntry entry = this.entries.get(i);
            ImageData imageData = ClientCacheHandler.getImageData(entry.effect.getIcon());
            if (imageData != null && imageData.imageLoaded()) {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                imageData.bindTexture();
                int iconU = entry.effect.iconX;
                int iconV = entry.effect.iconY;
                int iconWidth = entry.effect.getWidth();
                int iconHeight = entry.effect.getHeight();
                int texWidth = imageData.getTotalWidth();
                int texHeight = imageData.getTotalHeight();
                if (imageData.isAnimated()) {
                    iconV += (int)(imageData.getCurrentFrameVOffset() * (float)texHeight);
                } else if (entry.effect.animated && entry.effect.frameCount > 1) {
                    iconV += (int)(AnimationHelper.getFrameVOffset(texHeight, entry.effect.frameCount, entry.effect.frametime) * (float)texHeight);
                }
                GuiEffectBar.func_152125_a((int)(this.x + 2), (int)(drawY + iconYOffset), (float)iconU, (float)iconV, (int)iconWidth, (int)iconHeight, (int)iconRenderSize, (int)iconRenderSize, (float)texWidth, (float)texHeight);
                GL11.glDisable((int)2929);
                Minecraft.func_71410_x().func_110434_K().func_110577_a(GuiCNPCInventory.specialIcons);
                GuiEffectBar.func_152125_a((int)(this.x + 2), (int)(drawY + iconYOffset), (float)0.0f, (float)224.0f, (int)16, (int)16, (int)iconRenderSize, (int)iconRenderSize, (float)256.0f, (float)256.0f);
                GL11.glEnable((int)2929);
            }
            if (mouseX < this.x || mouseX >= this.x + this.field_146294_l || mouseY < drawY || mouseY >= drawY + this.entryHeight) continue;
            int seconds = entry.playerEffect.duration;
            hoveredTooltip = new ArrayList<String>();
            hoveredTooltip.add(entry.effect.getMenuName());
            hoveredTooltip.add("Time: " + seconds + "s");
            tooltipX = mouseX;
            tooltipY = mouseY;
            toolTipEffect = entry.effect;
        }
        GL11.glDisable((int)3089);
        if (totalHeight > this.field_146295_m) {
            int scrollBarWidth = 4;
            int scrollBarX = this.x + this.field_146294_l - scrollBarWidth - 2 + 10;
            GuiEffectBar.func_73734_a((int)scrollBarX, (int)this.y, (int)(scrollBarX + scrollBarWidth), (int)(this.y + this.field_146295_m), (int)-13421773);
            int thumbHeight = Math.max(20, this.field_146295_m * this.field_146295_m / totalHeight);
            int thumbY = this.y + this.scrollY * (this.field_146295_m - thumbHeight) / (totalHeight - this.field_146295_m);
            GuiEffectBar.func_73734_a((int)scrollBarX, (int)thumbY, (int)(scrollBarX + scrollBarWidth), (int)(thumbY + thumbHeight), (int)-5592406);
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
        if (hoveredTooltip != null && this.fontRenderer != null && toolTipEffect != null) {
            this.drawCustomHoveringText(hoveredTooltip, tooltipX, tooltipY, this.fontRenderer, toolTipEffect);
        }
    }

    public void mouseScrolled(int delta) {
        int padding = 1;
        int totalHeight = this.entries.size() * (this.entryHeight + padding);
        this.scrollY -= delta * 10;
        if (this.scrollY < 0) {
            this.scrollY = 0;
        }
        if (this.scrollY > totalHeight - this.field_146295_m) {
            this.scrollY = Math.max(totalHeight - this.field_146295_m, 0);
        }
    }

    public void drawCustomHoveringText(List<String> textLines, int x, int y, FontRenderer font, CustomEffect hoveredEffect) {
        if (textLines == null || textLines.isEmpty()) {
            return;
        }
        int maxWidth = 0;
        for (String s : textLines) {
            int lineWidth = font.func_78256_a(s);
            if (lineWidth <= maxWidth) continue;
            maxWidth = lineWidth;
        }
        int iconSize = 32;
        int tooltipHeight = 8 + iconSize + 8;
        if (textLines.size() > 1) {
            tooltipHeight += 2 + (textLines.size() - 1) * 10;
        }
        int screenWidth = Minecraft.func_71410_x().field_71462_r.field_146294_l;
        int screenHeight = Minecraft.func_71410_x().field_71462_r.field_146295_m;
        TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
        int tooltipX = x + 12;
        int tooltipY = y - 12;
        if (tooltipX + maxWidth > screenWidth) {
            tooltipX = x - 28 - maxWidth;
        }
        if (tooltipY + tooltipHeight + 6 > screenHeight) {
            tooltipY = screenHeight - tooltipHeight - 6;
        }
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)2929);
        GL11.glPushMatrix();
        this.field_73735_i = 1000.0f;
        GuiEffectBar.func_73734_a((int)(tooltipX - 3), (int)(tooltipY - 4), (int)(tooltipX + maxWidth + 3), (int)(tooltipY + tooltipHeight), (int)-267386864);
        int textY = tooltipY;
        for (String s : textLines) {
            font.func_78261_a(s, tooltipX, textY, 0xFFFFFF);
            textY += 10;
        }
        ImageData imageData = ClientCacheHandler.getImageData(hoveredEffect.getIcon());
        if (imageData != null && imageData.imageLoaded()) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            imageData.bindTexture();
            int tooltipIconV = hoveredEffect.iconY;
            int tooltipTexH = imageData.getTotalHeight();
            if (imageData.isAnimated()) {
                tooltipIconV += (int)(imageData.getCurrentFrameVOffset() * (float)tooltipTexH);
            } else if (hoveredEffect.animated && hoveredEffect.frameCount > 1) {
                tooltipIconV += (int)(AnimationHelper.getFrameVOffset(tooltipTexH, hoveredEffect.frameCount, hoveredEffect.frametime) * (float)tooltipTexH);
            }
            GuiEffectBar.func_152125_a((int)(tooltipX + (maxWidth - iconSize) / 2), (int)(textY + 4), (float)hoveredEffect.iconX, (float)tooltipIconV, (int)hoveredEffect.getWidth(), (int)hoveredEffect.getHeight(), (int)iconSize, (int)iconSize, (float)imageData.getTotalWidth(), (float)tooltipTexH);
        }
        textureManager.func_110577_a(GuiCNPCInventory.specialIcons);
        GuiEffectBar.func_152125_a((int)(tooltipX + (maxWidth - iconSize) / 2), (int)(textY + 4), (float)0.0f, (float)224.0f, (int)16, (int)16, (int)iconSize, (int)iconSize, (float)256.0f, (float)256.0f);
        GL11.glEnable((int)2929);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    public static class EffectEntry {
        public CustomEffect effect;
        public PlayerEffect playerEffect;

        public EffectEntry(CustomEffect effect, PlayerEffect playerEffect) {
            this.effect = effect;
            this.playerEffect = playerEffect;
        }
    }
}

