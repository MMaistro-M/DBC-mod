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
package JinRyuu.DragonBC.common.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class DBCGuiButtons02
extends GuiButton {
    public DBCGuiButtons02(int par1, int par2, int par3, int par4, int par5, String par6Str) {
        super(par1, par2, par3, par4, par5, par6Str);
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (this.field_146125_m) {
            FontRenderer var4 = par1Minecraft.field_71466_p;
            ResourceLocation txx = new ResourceLocation("jinryuudragonbc:guibutton02.png");
            par1Minecraft.func_110434_K().func_110577_a(txx);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
            int var5 = this.func_146114_a(this.field_146123_n);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 46 + var5 * 20, this.field_146120_f / 2, this.field_146121_g);
            this.func_73729_b(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2, 46 + var5 * 20, this.field_146120_f / 2, this.field_146121_g);
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

