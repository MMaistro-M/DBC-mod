/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.custom.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.custom.GuiCustom;
import noppes.npcs.client.gui.custom.interfaces.IGuiComponent;
import noppes.npcs.client.renderer.AnimationHelper;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.scripted.gui.ScriptGuiTexturedRect;
import org.lwjgl.opengl.GL11;

public class CustomGuiTexturedRect
extends Gui
implements IGuiComponent {
    GuiCustom parent;
    ImageData imageData = null;
    String texture;
    int id;
    int x;
    int y;
    int width;
    int height;
    int textureX;
    int textureY;
    float scale = 1.0f;
    String[] hoverText;
    int color;
    float alpha;
    float rotation;
    boolean animated;
    int frameCount = 1;
    int frametime = 2;

    public CustomGuiTexturedRect(int id, String texture, int x, int y, int width, int height) {
        this(id, texture, x, y, width, height, 0, 0);
    }

    public CustomGuiTexturedRect(int id, String directory, int x, int y, int width, int height, int textureX, int textureY) {
        this.id = id;
        this.texture = directory;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.textureX = textureX;
        this.textureY = textureY;
        if (this.texture != null && !this.texture.isEmpty()) {
            this.imageData = ClientCacheHandler.getImageData(this.texture);
        }
    }

    public void setParent(GuiCustom parent) {
        this.parent = parent;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void onRender(Minecraft mc, int mouseX, int mouseY, int mouseWheel, float partialTicks) {
        boolean hovered;
        boolean bl = hovered = mouseX >= this.x + GuiCustom.guiLeft && mouseY >= this.y + GuiCustom.guiTop && (float)mouseX < (float)(this.x + GuiCustom.guiLeft) + (float)this.width * this.scale && (float)mouseY < (float)(this.y + GuiCustom.guiTop) + (float)this.height * this.scale;
        if (this.imageData != null && this.imageData.imageLoaded()) {
            int totalWidth = this.imageData.getTotalWidth();
            int totalHeight = this.imageData.getTotalHeight();
            float vOff = 0.0f;
            if (this.imageData.isAnimated()) {
                vOff = this.imageData.getCurrentFrameVOffset();
            } else if (this.animated && this.frameCount > 1) {
                vOff = AnimationHelper.getFrameVOffset(totalHeight, this.frameCount, this.frametime);
            }
            float u1 = (float)this.textureX / (float)totalWidth;
            float u2 = u1 + (float)this.width / (float)totalWidth;
            float v1 = vOff + (float)this.textureY / (float)totalHeight;
            float v2 = vOff + ((float)this.textureY + (float)this.height) / (float)totalHeight;
            GL11.glPushMatrix();
            float red = (float)(this.color >> 16 & 0xFF) / 255.0f;
            float green = (float)(this.color >> 8 & 0xFF) / 255.0f;
            float blue = (float)(this.color & 0xFF) / 255.0f;
            GL11.glColor4f((float)red, (float)green, (float)blue, (float)this.alpha);
            GL11.glTranslatef((float)(GuiCustom.guiLeft + this.x), (float)(GuiCustom.guiTop + this.y), (float)this.id);
            GL11.glRotatef((float)this.rotation, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
            this.imageData.bindTexture();
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
            tessellator.func_78369_a(red, green, blue, this.alpha);
            tessellator.func_78374_a(0.0, (double)this.height, 0.0, (double)u1, (double)v2);
            tessellator.func_78374_a((double)this.width, (double)this.height, 0.0, (double)u2, (double)v2);
            tessellator.func_78374_a((double)this.width, 0.0, 0.0, (double)u2, (double)v1);
            tessellator.func_78374_a(0.0, 0.0, 0.0, (double)u1, (double)v1);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
        if (hovered && this.hoverText != null && this.hoverText.length > 0) {
            this.parent.hoverText = this.hoverText;
        }
    }

    @Override
    public ICustomGuiComponent toComponent() {
        ScriptGuiTexturedRect component = new ScriptGuiTexturedRect(this.id, this.texture, this.x, this.y, this.width, this.height, this.textureX, this.textureY);
        component.setHoverText(this.hoverText);
        component.setScale(this.scale);
        component.setColor(this.color);
        component.setAlpha(this.alpha);
        component.setRotation(this.rotation);
        if (this.animated && this.frameCount > 1) {
            component.setAnimation(this.frameCount, this.frametime);
        }
        return component;
    }

    public static CustomGuiTexturedRect fromComponent(ScriptGuiTexturedRect component) {
        CustomGuiTexturedRect rect = component.getTextureX() >= 0 && component.getTextureY() >= 0 ? new CustomGuiTexturedRect(component.getID(), component.getTexture(), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight(), component.getTextureX(), component.getTextureY()) : new CustomGuiTexturedRect(component.getID(), component.getTexture(), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight());
        rect.scale = component.getScale();
        if (component.hasHoverText()) {
            rect.hoverText = component.getHoverText();
        }
        rect.color = component.getColor();
        rect.alpha = component.getAlpha();
        rect.rotation = component.getRotation();
        if (component.isAnimated()) {
            rect.animated = true;
            rect.frameCount = component.getFrameCount();
            rect.frametime = component.getFrameTime();
        }
        return rect;
    }
}

