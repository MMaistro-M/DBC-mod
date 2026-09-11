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

public class JRMCoreGuiButtonsA1
extends GuiButton {
    private String string;
    public static boolean clicked;
    private boolean mainTexture;

    public JRMCoreGuiButtonsA1(int par1, int par2, int par3, String par6Str, boolean mainTexture) {
        super(par1, par2, par3, 15, 15, "");
        this.string = par6Str;
        clicked = false;
        this.mainTexture = mainTexture;
    }

    public JRMCoreGuiButtonsA1(int par1, int par2, int par3, String par6Str) {
        this(par1, par2, par3, par6Str, true);
    }

    public boolean func_146116_c(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_) {
        clicked = true;
        return super.func_146116_c(p_146116_1_, p_146116_2_, p_146116_3_);
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (this.field_146125_m) {
            FontRenderer var4 = par1Minecraft.field_71466_p;
            ResourceLocation txx = new ResourceLocation(this.mainTexture ? JRMCoreGuiScreen.button1 : JRMCoreGuiScreen.button2);
            par1Minecraft.func_110434_K().func_110577_a(txx);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
            int var5 = this.func_146114_a(this.field_146123_n);
            int huv = 0;
            int vuv = 0 + var5 * 15;
            if (this.string.equals("<")) {
                huv = 0;
            } else if (this.string.equals(">")) {
                huv = 15;
            } else if (this.string.equals("x")) {
                huv = 120;
            } else if (this.string.equals("o")) {
                huv = 135;
            } else if (this.string.equals("v")) {
                huv = 135;
                vuv = 91 + var5 * 15;
            } else if (this.string.equals("i")) {
                huv = 120;
                vuv = 91 + var5 * 15;
            }
            this.func_73729_b(this.field_146128_h, this.field_146129_i, huv, vuv, this.field_146120_f, this.field_146121_g);
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

