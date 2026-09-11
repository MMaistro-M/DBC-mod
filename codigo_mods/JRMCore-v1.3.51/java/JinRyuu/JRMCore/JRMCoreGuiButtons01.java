/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtons01
extends GuiButton {
    public int col;
    private boolean shadow = true;

    public Object setShadow(boolean b) {
        this.shadow = b;
        return this;
    }

    public JRMCoreGuiButtons01(int par1, int par2, int par3, int par4, String par6Str, int c) {
        super(par1, par2, par3, par4, 10, par6Str);
        this.field_146121_g = 10;
        this.col = c;
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (this.field_146125_m) {
            int width;
            FontRenderer var4 = par1Minecraft.field_71466_p;
            this.field_146120_f = width = var4.func_78256_a(this.field_146126_j);
            this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
            int var5 = this.func_146114_a(this.field_146123_n);
            int j = this.col == 0 ? 0xE0E0E0 : this.col;
            float h2 = (float)(j >> 16 & 0xFF) / 255.0f;
            float h3 = (float)(j >> 8 & 0xFF) / 255.0f;
            float h4 = (float)(j & 0xFF) / 255.0f;
            float h1 = 1.0f;
            if (var5 == 2) {
                int r = (int)(h2 * 254.0f);
                int g = (int)(h3 * 254.0f);
                int b = (int)(h4 * 254.0f);
                float[] hsb = Color.RGBtoHSB(r, g, b, null);
                float hue = hsb[0];
                float saturation = 0.33f;
                float brightness = hsb[2];
                int rgb = Color.HSBtoRGB(hue, saturation, brightness);
                h2 = (float)(rgb >> 16 & 0xFF) / 255.0f;
                h3 = (float)(rgb >> 8 & 0xFF) / 255.0f;
                h4 = (float)(rgb & 0xFF) / 255.0f;
            }
            GL11.glColor3f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4));
            this.func_146119_b(par1Minecraft, par2, par3);
            int var6 = j;
            if (!this.field_146124_l) {
                var6 = -6250336;
            } else if (this.field_146123_n) {
                var6 = 0xFFFFA0;
            }
            if (this.shadow) {
                this.func_73731_b(var4, this.field_146126_j, this.field_146128_h, this.field_146129_i, var6);
            } else {
                var4.func_85187_a(this.field_146126_j, this.field_146128_h, this.field_146129_i, var6, false);
            }
        }
    }
}

