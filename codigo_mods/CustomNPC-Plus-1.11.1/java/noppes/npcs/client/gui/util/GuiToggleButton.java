/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiNpcButton;
import org.lwjgl.opengl.GL11;

public class GuiToggleButton
extends GuiNpcButton {
    private static final int FIXED_WIDTH = 40;
    private static final int FIXED_HEIGHT = 20;
    private boolean toggled;
    private float animProgress;
    private ResourceLocation textureOff = null;
    private int textureOffPosX = 0;
    private int textureOffPosY = 0;
    private static final int ICON_SIZE = 16;

    public GuiToggleButton(int id, int x, int y, boolean initialState) {
        super(id, x, y, 40, 20, "");
        this.toggled = initialState;
        this.animProgress = initialState ? 1.0f : 0.0f;
    }

    public GuiToggleButton(int id, int x, int y, boolean initialState, ResourceLocation textureOn, ResourceLocation textureOff) {
        super(id, x, y, 40, 20, "");
        this.setIconTexture(textureOn);
        this.toggled = initialState;
        this.animProgress = initialState ? 1.0f : 0.0f;
        this.textureOff = textureOff;
    }

    public GuiToggleButton setTextureOff(ResourceLocation texture) {
        this.textureOff = texture;
        return this;
    }

    public GuiToggleButton setTextureOffPos(int posX, int posY) {
        this.textureOffPosX = posX;
        this.textureOffPosY = posY;
        return this;
    }

    @Override
    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
        boolean pressed = super.func_146116_c(mc, mouseX, mouseY);
        if (pressed) {
            this.toggled = !this.toggled;
        }
        return pressed;
    }

    @Override
    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        int texY;
        int texX;
        ResourceLocation iconTex;
        int nonHeadTexX;
        int nonHeadWidth;
        int nonHeadX;
        if (!this.field_146125_m) {
            return;
        }
        FontRenderer fontrenderer = mc.field_71466_p;
        mc.func_110434_K().func_110577_a(field_146122_a);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + 40 && mouseY < this.field_146129_i + 20;
        int k = this.func_146114_a(this.field_146123_n);
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glBlendFunc((int)770, (int)771);
        float target = this.toggled ? 1.0f : 0.0f;
        this.animProgress += (target - this.animProgress) * 0.2f;
        if (Math.abs(target - this.animProgress) < 0.01f) {
            this.animProgress = target;
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 46 + k * 20, 20, 20);
        this.func_73729_b(this.field_146128_h + 20, this.field_146129_i, 180, 46 + k * 20, 20, 20);
        if (this.animProgress > 0.5f) {
            nonHeadX = this.field_146128_h;
            nonHeadWidth = 20;
            nonHeadTexX = 0;
        } else {
            nonHeadX = this.field_146128_h + 20;
            nonHeadWidth = 20;
            nonHeadTexX = 180;
        }
        GL11.glColor4f((float)0.6f, (float)0.6f, (float)0.6f, (float)1.0f);
        this.func_73729_b(nonHeadX, this.field_146129_i, nonHeadTexX, 46 + k * 20, nonHeadWidth, 20);
        int knobWidth = 20;
        int knobX = this.field_146128_h + (int)((float)(40 - knobWidth) * this.animProgress);
        int knobY = this.field_146129_i;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73729_b(knobX, knobY, 0, 46 + k * 20, knobWidth / 2, 20);
        this.func_73729_b(knobX + knobWidth / 2, knobY, 200 - knobWidth / 2, 46 + k * 20, knobWidth / 2, 20);
        int iconX = knobX + (knobWidth - 16) / 2;
        int iconY = knobY + 2;
        if (this.toggled) {
            iconTex = this.iconTexture;
            texX = this.iconPosX;
            texY = this.iconPosY;
        } else if (this.textureOff != null) {
            iconTex = this.textureOff;
            texX = this.textureOffPosX;
            texY = this.textureOffPosY;
        } else {
            iconTex = this.iconTexture;
            texX = this.iconPosX;
            texY = this.iconPosY;
        }
        if (iconTex != null) {
            mc.func_110434_K().func_110577_a(iconTex);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.func_73729_b(iconX, iconY, texX, texY, 16, 16);
        }
        int textColor = 0xE0E0E0;
        if (this.packedFGColour != 0) {
            textColor = this.packedFGColour;
        } else if (!this.field_146124_l) {
            textColor = 0xA0A0A0;
        } else if (this.field_146123_n) {
            textColor = 0xFFFFA0;
        }
        this.func_73732_a(fontrenderer, this.field_146126_j, this.field_146128_h + 20, this.field_146129_i + 6, textColor);
    }
}

