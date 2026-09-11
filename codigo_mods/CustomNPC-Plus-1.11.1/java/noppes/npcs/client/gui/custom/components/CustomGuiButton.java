/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.custom.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.custom.GuiCustom;
import noppes.npcs.client.gui.custom.interfaces.IClickListener;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.scripted.gui.ScriptGuiButton;
import org.lwjgl.opengl.GL11;

public class CustomGuiButton
extends GuiButton
implements IClickListener {
    GuiCustom parent;
    ImageData imageData = null;
    String texture;
    public int textureX;
    public int textureY;
    boolean field_146123_n;
    String label;
    String[] hoverText;
    int color;
    float alpha;
    float scale = 1.0f;

    public CustomGuiButton(int id, String buttonText, int x, int y) {
        super(id, x, y, buttonText);
    }

    public CustomGuiButton(int id, String buttonText, int x, int y, int width, int height) {
        super(id, x, y, width, height, buttonText);
    }

    public CustomGuiButton(int buttonId, String buttonText, int x, int y, int width, int height, String texture) {
        this(buttonId, buttonText, x, y, width, height, texture, 0, 0);
    }

    public CustomGuiButton(int buttonId, String buttonText, int x, int y, int width, int height, String texture, int textureX, int textureY) {
        this(buttonId, buttonText, x, y, width, height);
        this.texture = texture;
        this.textureX = textureX;
        this.textureY = textureY;
        this.label = buttonText;
        if (texture != null && !texture.isEmpty()) {
            this.imageData = ClientCacheHandler.getImageData(texture);
        }
    }

    public void setParent(GuiCustom parent) {
        this.parent = parent;
    }

    public static CustomGuiButton fromComponent(ScriptGuiButton component) {
        CustomGuiButton btn = component.hasTexture() ? (component.getTextureX() >= 0 && component.getTextureY() >= 0 ? new CustomGuiButton(component.getID(), component.getLabel(), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight(), component.getTexture(), component.getTextureX(), component.getTextureY()) : new CustomGuiButton(component.getID(), component.getLabel(), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight(), component.getTexture())) : (component.getWidth() >= 0 && component.getHeight() >= 0 ? new CustomGuiButton(component.getID(), component.getLabel(), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight()) : new CustomGuiButton(component.getID(), component.getLabel(), component.getPosX(), component.getPosY()));
        btn.color = component.getColor();
        btn.alpha = component.getAlpha();
        btn.scale = component.getScale();
        btn.field_146124_l = component.isEnabled();
        if (component.hasHoverText()) {
            btn.hoverText = component.getHoverText();
        }
        return btn;
    }

    @Override
    public int getID() {
        return this.field_146127_k;
    }

    @Override
    public void onRender(Minecraft mc, int mouseX, int mouseY, int mouseWheel, float partialTicks) {
        this.field_146123_n = mouseX >= this.field_146128_h + GuiCustom.guiLeft && mouseY >= this.field_146129_i + GuiCustom.guiTop && (float)mouseX < (float)(this.field_146128_h + GuiCustom.guiLeft) + (float)this.field_146120_f * this.scale && (float)mouseY < (float)(this.field_146129_i + GuiCustom.guiTop) + (float)this.field_146121_g * this.scale;
        FontRenderer fontRenderer = mc.field_71466_p;
        float red = (float)(this.color >> 16 & 0xFF) / 255.0f;
        float green = (float)(this.color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(this.color & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)this.alpha);
        if (this.imageData != null && this.imageData.imageLoaded()) {
            int totalWidth = this.imageData.getTotalWidth();
            int totalHeight = this.imageData.getTotalHeight();
            float u1 = (float)this.textureX / (float)totalWidth;
            float u2 = u1 + (float)this.field_146120_f / (float)totalWidth;
            float v1 = (float)this.textureY / (float)totalHeight;
            float v2 = v1 + (float)this.field_146121_g / (float)totalHeight;
            if (this.field_146124_l) {
                v1 = this.field_146123_n ? (float)(this.textureY + 2 * this.field_146121_g) / (float)totalHeight : (float)(this.textureY + this.field_146121_g) / (float)totalHeight;
                v2 = v1 + (float)this.field_146121_g / (float)totalHeight;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)(GuiCustom.guiLeft + this.field_146128_h) - u1 * (float)totalWidth * this.scale), (float)((float)(GuiCustom.guiTop + this.field_146129_i) - v1 * (float)totalHeight * this.scale), (float)this.field_146127_k);
            GL11.glScalef((float)this.scale, (float)this.scale, (float)1.0f);
            this.imageData.bindTexture();
            GL11.glBlendFunc((int)770, (int)771);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
            tessellator.func_78369_a(red, green, blue, this.alpha);
            tessellator.func_78374_a((double)(u2 * (float)totalWidth), (double)(v2 * (float)totalHeight), 0.0, (double)u2, (double)v2);
            tessellator.func_78374_a((double)(u2 * (float)totalWidth), (double)(v1 * (float)totalHeight), 0.0, (double)u2, (double)v1);
            tessellator.func_78374_a((double)(u1 * (float)totalWidth), (double)(v1 * (float)totalHeight), 0.0, (double)u1, (double)v1);
            tessellator.func_78374_a((double)(u1 * (float)totalWidth), (double)(v2 * (float)totalHeight), 0.0, (double)u1, (double)v2);
            tessellator.func_78381_a();
            GL11.glTranslated((double)0.0, (double)0.0, (double)0.1);
            this.func_73732_a(fontRenderer, this.label, this.field_146120_f / 2, (this.field_146121_g - 8) / 2, this.color);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(GuiCustom.guiLeft + this.field_146128_h), (float)(GuiCustom.guiTop + this.field_146129_i), (float)this.field_146127_k);
            GL11.glScalef((float)this.scale, (float)this.scale, (float)1.0f);
            mc.func_110434_K().func_110577_a(field_146122_a);
            int i = this.func_146114_a(this.field_146123_n);
            GL11.glBlendFunc((int)770, (int)771);
            this.func_73729_b(0, 0, 0, 46 + i * 20, this.field_146120_f / 2, this.field_146121_g);
            this.func_73729_b(this.field_146120_f / 2, 0, 200 - this.field_146120_f / 2, 46 + i * 20, this.field_146120_f / 2, this.field_146121_g);
            this.func_146119_b(mc, mouseX, mouseY);
            int j = 0xE0E0E0;
            if (this.packedFGColour != 0) {
                j = this.packedFGColour;
            } else if (!this.field_146124_l) {
                j = 0xA0A0A0;
            } else if (this.field_146123_n) {
                j = 0xFFFFA0;
            }
            GL11.glTranslated((double)0.0, (double)0.0, (double)0.1);
            this.func_73732_a(fontRenderer, this.field_146126_j, this.field_146120_f / 2, (this.field_146121_g - 8) / 2, j);
            GL11.glPopMatrix();
        }
        if (this.field_146123_n && this.hoverText != null && this.hoverText.length > 0) {
            this.parent.hoverText = this.hoverText;
        }
    }

    @Override
    public ICustomGuiComponent toComponent() {
        ScriptGuiButton component = new ScriptGuiButton(this.field_146127_k, this.label, this.field_146128_h, this.field_146129_i, this.field_146120_f, this.field_146121_g, this.texture, this.textureX, this.textureY);
        component.setHoverText(this.hoverText);
        component.setColor(this.color);
        component.setAlpha(this.alpha);
        return component;
    }

    @Override
    public boolean mouseClicked(GuiCustom gui, int mouseX, int mouseY, int mouseButton) {
        if (this.field_146123_n && this.field_146124_l) {
            gui.buttonClick(this);
            return true;
        }
        return false;
    }
}

