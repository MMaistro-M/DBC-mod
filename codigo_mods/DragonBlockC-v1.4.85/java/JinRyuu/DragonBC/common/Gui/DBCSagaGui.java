/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL14
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.JRMCoreClient;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class DBCSagaGui
extends Gui {
    private Minecraft mc = JRMCoreClient.mc;
    private FontRenderer fontRenderer;
    private static double zDepth = 0.0;

    public DBCSagaGui() {
        this.fontRenderer = this.mc.field_71466_p;
    }

    public void renderDragonRadar() {
        ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        int var6 = var5.func_78326_a();
        int var7 = var5.func_78328_b();
        FontRenderer var8 = this.mc.field_71466_p;
        this.mc.field_71460_t.func_78478_c();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.DragonSys(var6, var7);
        GL11.glDisable((int)3042);
    }

    public void DragonSys(int var6, int var7) {
        this.DragonBack(var6, var7);
    }

    public void DragonBack(int var6, int var7) {
        int width = var6;
        int height = var7;
        int xSize = 146;
        int ySize = 166;
        int guiLeft = (width - xSize / 4) / 1;
        int guiTop = (height - ySize / 4) / 1;
        int guiLeft2 = (width + xSize / 4) / 1;
        int guiTop2 = (int)(((float)height + (float)ySize / 3.1f) / 1.0f);
        String var4 = "jinryuudragonbc:radar.png";
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation(var4);
        this.mc.field_71446_o.func_110577_a(tx);
        double heading = Math.toRadians(this.mc.field_71439_g.field_70177_z) + 1.5707963267948966;
        int pitch = (int)this.mc.field_71439_g.field_70125_A + 60;
        if (pitch > 0) {
            this.func_73729_b(guiLeft - pitch, guiTop - pitch, 0, 0, xSize, ySize);
            DBCSagaGui.drawArrow(guiLeft2 - pitch, guiTop2 - pitch, heading, 10.0, Color.GREEN.getRGB());
        } else {
            this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
            DBCSagaGui.drawArrow(guiLeft2, guiTop2, heading, 10.0, Color.GREEN.getRGB());
        }
    }

    public void DragonBackMask(int var6, int var7) {
        int width = var6;
        int height = var7;
        int xSize = 146;
        int ySize = 166;
        int guiLeft = (width - xSize / 4) / 1;
        int guiTop = (height - ySize / 4) / 1;
        int guiLeft2 = (width + xSize / 4) / 1;
        int guiTop2 = (int)(((double)height + (double)ySize / 4.1) / 1.0);
        String var4 = "jinryuudragonbc:radarm.png";
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation(var4);
        this.mc.field_71446_o.func_110577_a(tx);
        double heading = Math.toRadians(this.mc.field_71439_g.field_70177_z) + 1.5707963267948966;
        int pitch = (int)this.mc.field_71439_g.field_70125_A + 60;
        if (pitch > 0) {
            this.func_73729_b(guiLeft - pitch, guiTop - pitch, 0, 0, xSize, ySize);
        } else {
            this.func_73729_b(guiLeft, guiTop, 0, 0, xSize, ySize);
        }
    }

    public static void drawArrow(double x, double y, double angle, double length, int color) {
        double arrowBackAngle = 2.356194490192345;
        double[] coords = new double[]{x + length * Math.cos(angle), y + length * Math.sin(angle), x + length * 0.5 * Math.cos(angle - arrowBackAngle), y + length * 0.5 * Math.sin(angle - arrowBackAngle), x, y, x + length * 0.5 * Math.cos(angle + arrowBackAngle), y + length * 0.5 * Math.sin(angle + arrowBackAngle)};
        DBCSagaGui.setColor(color);
        DBCSagaGui.drawColoredQuad(coords);
        DBCSagaGui.resetColor();
    }

    public static void setColor(int color) {
        float A = (float)(color >> 24 & 0xFF) / 255.0f;
        float R = (float)(color >> 16 & 0xFF) / 255.0f;
        float G = (float)(color >> 8 & 0xFF) / 255.0f;
        float B = (float)(color >> 0 & 0xFF) / 255.0f;
        GL11.glColor4f((float)R, (float)G, (float)B, (float)A);
    }

    public static void drawColoredQuad(double[] coords) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        Tessellator tes = Tessellator.field_78398_a;
        tes.func_78382_b();
        tes.func_78377_a(coords[0], coords[1], zDepth);
        tes.func_78377_a(coords[2], coords[3], zDepth);
        tes.func_78377_a(coords[4], coords[5], zDepth);
        tes.func_78377_a(coords[6], coords[7], zDepth);
        tes.func_78381_a();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    public static void setZDepth(double z) {
        zDepth = z;
    }

    public static void resetColor() {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void DragonDetect(double x, double y, float f) {
        EntityClientPlayerMP entityplayersp = this.mc.field_71439_g;
        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        int width = scaledresolution.func_78326_a();
        int height = scaledresolution.func_78328_b();
        int xSize = 8;
        int ySize = 8;
        int guiLeft = (width + 32 + 1) / 1;
        int guiTop = (height + 36 + 14) / 1;
        String ic = "jinryuumodscore:icons.png";
        String ic2 = "jinryuudragonbc:icons3.png";
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL14.glBlendFuncSeparate((int)0, (int)1, (int)771, (int)0);
        DBCClient.SagaSys.DragonBackMask(width, height);
        GL11.glBlendFunc((int)773, (int)772);
        String var4 = "jinryuudragonbc:detect.png";
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ResourceLocation tx = new ResourceLocation(var4);
        this.mc.field_71446_o.func_110577_a(tx);
        this.func_73729_b((int)((double)guiLeft + x - (double)f), (int)((double)guiTop + y - (double)f), 0, 0, xSize, ySize);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

