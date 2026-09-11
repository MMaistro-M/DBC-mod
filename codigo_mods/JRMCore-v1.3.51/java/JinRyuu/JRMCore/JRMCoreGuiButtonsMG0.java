/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.client.minigame.MiniGame;
import JinRyuu.JRMCore.server.config.core.JGConfigMiniGameConcentration;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtonsMG0
extends GuiButton {
    public static boolean clicked = false;

    public JRMCoreGuiButtonsMG0(int par1, int par2, int par3, int par4, int par5, String par6Str, Object c) {
        super(par1, par2, par3, par4, par5, par6Str);
    }

    public void func_146118_a(int p_146118_1_, int p_146118_2_) {
        super.func_146118_a(p_146118_1_, p_146118_2_);
    }

    public boolean func_146116_c(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
        clicked = super.func_146116_c(p_146116_1_, p_146116_2_, p_146116_3_);
        return clicked;
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (JGConfigMiniGameConcentration.ConstantClickOn) {
            if (clicked && !Mouse.isButtonDown((int)0)) {
                clicked = !clicked;
            }
        } else {
            clicked = false;
        }
        if (this.field_146125_m) {
            this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
            boolean var5 = false;
            int j = 0xFAFF00;
            FontRenderer var4 = par1Minecraft.field_71466_p;
            ResourceLocation txx = new ResourceLocation(MiniGame.training1);
            par1Minecraft.func_110434_K().func_110577_a(txx);
            float h2 = (float)(j >> 16 & 0xFF) / 255.0f;
            float h3 = (float)(j >> 8 & 0xFF) / 255.0f;
            float h4 = (float)(j & 0xFF) / 255.0f;
            float h1 = 1.0f;
            if (!var5) {
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
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 0, this.field_146120_f / 2, this.field_146121_g);
            this.func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2, 0, this.field_146120_f / 2, this.field_146121_g);
            this.func_146119_b(par1Minecraft, par2, par3);
            int var6 = 0xE0E0E0;
            if (!this.field_146124_l) {
                var6 = -6250336;
            } else if (this.field_146123_n) {
                var6 = 0xFFFFA0;
            }
            this.func_73732_a(var4, this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, var6);
        }
    }
}

