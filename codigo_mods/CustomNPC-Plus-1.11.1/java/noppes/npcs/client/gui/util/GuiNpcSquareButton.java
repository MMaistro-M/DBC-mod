/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.RenderHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import noppes.npcs.client.gui.util.GuiNpcButton;
import org.lwjgl.opengl.GL11;

public class GuiNpcSquareButton
extends GuiNpcButton {
    public static final int TEXT_BUTTON_HEIGHT = 20;
    public int size;
    public int iconAreaHeight;
    protected int backgroundColor;
    public GuiNpcButton textButton;

    public GuiNpcSquareButton(int id, int x, int y, int size, String label, int backgroundColor) {
        super(id, x, y, size, size, label);
        this.size = size;
        this.iconAreaHeight = size - 20;
        this.backgroundColor = backgroundColor;
        this.textButton = new GuiNpcButton(id, x, y + this.iconAreaHeight, size, 20, label);
    }

    public void updatePositionAndSize(int x, int y, int newSize) {
        this.field_146128_h = x;
        this.field_146129_i = y;
        this.size = newSize - 1;
        this.field_146120_f = newSize;
        this.field_146121_g = newSize;
        this.iconAreaHeight = newSize - 20;
        this.textButton.field_146128_h = x;
        this.textButton.field_146129_i = y + this.iconAreaHeight;
        this.textButton.field_146120_f = newSize;
        this.textButton.field_146121_g = 20;
    }

    @Override
    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (!this.field_146125_m) {
            return;
        }
        Gui.func_73734_a((int)this.field_146128_h, (int)this.field_146129_i, (int)(this.field_146128_h + this.size), (int)(this.field_146129_i + this.iconAreaHeight), (int)this.backgroundColor);
        if (this.iconTexture != null) {
            mc.func_110434_K().func_110577_a(this.iconTexture);
            GL11.glPushMatrix();
            RenderHelper.func_74518_a();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            float centerX = (float)this.field_146128_h + (float)this.size / 2.0f;
            float centerY = (float)this.field_146129_i + (float)this.iconAreaHeight / 2.0f;
            GL11.glTranslatef((float)centerX, (float)centerY, (float)0.0f);
            int iconPadding = 2;
            float scale = (float)(this.iconAreaHeight - 2 * iconPadding) / (float)this.iconHeight;
            GL11.glScalef((float)scale, (float)scale, (float)1.0f);
            GL11.glTranslatef((float)((float)(-this.iconWidth) / 2.0f), (float)((float)(-this.iconHeight) / 2.0f), (float)0.0f);
            this.func_73729_b(0, 0, this.iconPosX, this.iconPosY, this.iconWidth, this.iconHeight);
            GL11.glPopMatrix();
        }
        this.textButton.func_146112_a(mc, mouseX, mouseY);
    }

    @Override
    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
        if (!this.field_146125_m) {
            return false;
        }
        if (mouseX >= this.field_146128_h && mouseX < this.field_146128_h + this.size && mouseY >= this.field_146129_i + this.iconAreaHeight && mouseY < this.field_146129_i + this.size) {
            return this.textButton.func_146116_c(mc, mouseX, mouseY);
        }
        return mouseX >= this.field_146128_h && mouseX < this.field_146128_h + this.size && mouseY >= this.field_146129_i && mouseY < this.field_146129_i + this.iconAreaHeight;
    }

    public void func_146118_a(int mouseX, int mouseY) {
        this.textButton.func_146118_a(mouseX, mouseY);
    }
}

