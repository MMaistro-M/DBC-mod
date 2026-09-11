/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtonsA2
extends GuiButton {
    private String string;

    public JRMCoreGuiButtonsA2(int par1, int par2, int par3, String par6Str) {
        super(par1, par2, par3, 10, 10, "");
        this.string = par6Str;
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (this.field_146125_m) {
            FontRenderer var4 = par1Minecraft.field_71466_p;
            ResourceLocation txx = new ResourceLocation(JRMCoreGuiScreen.button1);
            par1Minecraft.func_110434_K().func_110577_a(txx);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
            int var5 = this.func_146114_a(this.field_146123_n);
            int s = 0;
            if (this.string.equals("<<")) {
                s = 70;
            } else if (this.string.equals(">>")) {
                s = 80;
            } else if (this.string.equals("|<")) {
                s = 90;
            } else if (this.string.equals(">|")) {
                s = 100;
            } else if (this.string.equals("<")) {
                s = 30;
            } else if (this.string.equals(">")) {
                s = 40;
            } else if (this.string.equals("^")) {
                s = 50;
            } else if (this.string.equals("v")) {
                s = 60;
            } else if (this.string.equals("x")) {
                s = 150;
            } else if (this.string.equals("o")) {
                s = 160;
            } else if (this.string.equals("(|)")) {
                s = 170;
            } else if (this.string.equals("(||)")) {
                s = 180;
            }
            this.func_73729_b(this.field_146128_h, this.field_146129_i, s, 5 + var5 * 10, this.field_146120_f, this.field_146121_g);
            this.func_146119_b(par1Minecraft, par2, par3);
            int var6 = 0xE0E0E0;
            if (!this.field_146124_l) {
                var6 = -6250336;
            } else if (this.field_146123_n) {
                var6 = 0xFFFFA0;
            }
        }
    }
}

