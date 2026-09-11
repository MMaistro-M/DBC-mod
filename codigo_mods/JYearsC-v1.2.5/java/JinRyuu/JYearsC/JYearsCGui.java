/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JYearsC;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JYearsC.JYearsCClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class JYearsCGui
extends Gui {
    protected FontRenderer fontRenderer;
    private Minecraft mc;

    public JYearsCGui() {
        this.fontRenderer = JYearsCClient.mc.field_71466_p;
        this.mc = JRMCoreClient.mc;
    }

    public void watch(int i) {
        ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        int var6 = var5.func_78326_a();
        int var7 = var5.func_78328_b();
        FontRenderer var8 = this.mc.field_71466_p;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_73735_i = -89.0f;
        int s = (int)(this.mc.field_71439_g.field_70170_p.func_72820_D() % 24000L / 1000L) + 6;
        int w = s > 24 ? s - 24 : s;
        int m = (int)(this.mc.field_71439_g.field_70170_p.func_72820_D() % 24000L - (long)((int)(this.mc.field_71439_g.field_70170_p.func_72820_D() % 24000L / 1000L) * 1000));
        float mi = (float)m / 16.67f;
        int min = (int)mi;
        String var34 = (w < 10 ? "0" + w : Integer.valueOf(w)) + ":" + (min < 10 ? "0" + min : Integer.valueOf(min));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var38 = var6 / 2 + 80 + var8.func_78256_a(var34) / 2;
        int var37 = var7 - 10;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        var8.func_78276_b(var34, var38, var37, 15388564);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

