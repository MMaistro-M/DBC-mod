/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiNpcButton;
import org.lwjgl.opengl.GL11;

public class GuiButtonNextPage
extends GuiNpcButton {
    private final boolean field_146151_o;
    private static final ResourceLocation field_110405_a = new ResourceLocation("textures/gui/book.png");

    public GuiButtonNextPage(int par1, int par2, int par3, boolean par4) {
        super(par1, par2, par3, 23, 13, "");
        this.field_146151_o = par4;
    }

    @Override
    public void func_146112_a(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_) {
        if (this.field_146125_m) {
            boolean flag = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            p_146112_1_.func_110434_K().func_110577_a(field_110405_a);
            int k = 0;
            int l = 192;
            if (flag) {
                k += 23;
            }
            if (!this.field_146151_o) {
                l += 13;
            }
            this.func_73729_b(this.field_146128_h, this.field_146129_i, k, l, 23, 13);
        }
    }
}

