/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiUtil {
    public static void setScissorClip(int x, int y, int width, int height) {
        GuiUtil.setScissorClip(x, y, width, height, null);
    }

    public static void setScissorClip(int x, int y, int width, int height, GuiNPCInterface gui) {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int scale = res.func_78325_e();
        if (gui != null) {
            x -= (int)gui.getPanX();
            y -= (int)gui.getPanY();
        }
        y = mc.field_71440_d - y * scale;
        GL11.glScissor((int)(x * scale), (int)(y - (height *= scale)), (int)(width * scale), (int)height);
    }

    public static void drawHorizontalLine(double y, double startX, double endX, int color) {
        if (endX < startX) {
            double i1 = startX;
            startX = endX;
            endX = i1;
        }
        GuiUtil.drawRectD(startX, y, endX + 1.0, y + 1.0, color);
    }

    public static void drawVerticalLine(double x, double startY, double endY, int color) {
        if (endY < startY) {
            double i1 = startY;
            startY = endY;
            endY = i1;
        }
        GuiUtil.drawRectD(x, startY + 1.0, x + 1.0, endY, color);
    }

    public static void drawRectD(double left, double top, double right, double bottom) {
        double j1;
        if (left < right) {
            j1 = left;
            left = right;
            right = j1;
        }
        if (top < bottom) {
            j1 = top;
            top = bottom;
            bottom = j1;
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        tessellator.func_78382_b();
        tessellator.func_78377_a(left, bottom, 0.0);
        tessellator.func_78377_a(right, bottom, 0.0);
        tessellator.func_78377_a(right, top, 0.0);
        tessellator.func_78377_a(left, top, 0.0);
        tessellator.func_78381_a();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    public static void drawRectD(double left, double top, double right, double bottom, int color) {
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)f, (float)f1, (float)f2, (float)f3);
        GuiUtil.drawRectD(left, top, right, bottom);
    }

    public static void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        double zLevel = 0.0;
        float f = (float)(startColor >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(startColor >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(startColor >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(startColor & 0xFF) / 255.0f;
        float f4 = (float)(endColor >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(endColor >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(endColor >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(endColor & 0xFF) / 255.0f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glShadeModel((int)7425);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(f1, f2, f3, f);
        tessellator.func_78377_a((double)right, (double)top, zLevel);
        tessellator.func_78377_a((double)left, (double)top, zLevel);
        tessellator.func_78369_a(f5, f6, f7, f4);
        tessellator.func_78377_a((double)left, (double)bottom, zLevel);
        tessellator.func_78377_a((double)right, (double)bottom, zLevel);
        tessellator.func_78381_a();
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }

    public static void drawGradientRectHorizontal(int left, int top, int right, int bottom, int startColor, int endColor) {
        double zLevel = 0.0;
        float sA = (float)(startColor >> 24 & 0xFF) / 255.0f;
        float sR = (float)(startColor >> 16 & 0xFF) / 255.0f;
        float sG = (float)(startColor >> 8 & 0xFF) / 255.0f;
        float sB = (float)(startColor & 0xFF) / 255.0f;
        float eA = (float)(endColor >> 24 & 0xFF) / 255.0f;
        float eR = (float)(endColor >> 16 & 0xFF) / 255.0f;
        float eG = (float)(endColor >> 8 & 0xFF) / 255.0f;
        float eB = (float)(endColor & 0xFF) / 255.0f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glShadeModel((int)7425);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(sR, sG, sB, sA);
        tessellator.func_78377_a((double)left, (double)top, zLevel);
        tessellator.func_78377_a((double)left, (double)bottom, zLevel);
        tessellator.func_78369_a(eR, eG, eB, eA);
        tessellator.func_78377_a((double)right, (double)bottom, zLevel);
        tessellator.func_78377_a((double)right, (double)top, zLevel);
        tessellator.func_78381_a();
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }

    public static void drawTexturedModalRect(double x, double y, double width, double height, int textureX, int textureY) {
        double zLevel = 0.0;
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a(x + 0.0, y + height, zLevel, (double)((float)textureX * f), (double)((float)((double)textureY + height) * f1));
        tessellator.func_78374_a(x + width, y + height, zLevel, (double)((float)((double)textureX + width) * f), (double)((float)((double)textureY + height) * f1));
        tessellator.func_78374_a(x + width, y + 0.0, zLevel, (double)((float)((double)textureX + width) * f), (double)((float)textureY * f1));
        tessellator.func_78374_a(x + 0.0, y + 0.0, zLevel, (double)((float)textureX * f), (double)((float)textureY * f1));
        tessellator.func_78381_a();
    }

    public static void drawScaledTexturedRect(int x, int y, int u, int v, int srcWidth, int srcHeight, int destWidth, int destHeight, int textureWidth, int textureHeight) {
        float uScale = 1.0f / (float)textureWidth;
        float vScale = 1.0f / (float)textureHeight;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)((float)u * uScale), (float)((float)v * vScale));
        GL11.glVertex2f((float)x, (float)y);
        GL11.glTexCoord2f((float)((float)u * uScale), (float)((float)(v + srcHeight) * vScale));
        GL11.glVertex2f((float)x, (float)(y + destHeight));
        GL11.glTexCoord2f((float)((float)(u + srcWidth) * uScale), (float)((float)(v + srcHeight) * vScale));
        GL11.glVertex2f((float)(x + destWidth), (float)(y + destHeight));
        GL11.glTexCoord2f((float)((float)(u + srcWidth) * uScale), (float)((float)v * vScale));
        GL11.glVertex2f((float)(x + destWidth), (float)y);
        GL11.glEnd();
        GL11.glDisable((int)3042);
    }

    public static void setMouse(int guiX, int guiY) {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution scaledResolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int factor = scaledResolution.func_78325_e();
        Mouse.setCursorPosition((int)(guiX * factor), (int)(mc.field_71440_d - guiY * factor));
    }

    public static void setMouseX(int guiX) {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution scaledResolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int factor = scaledResolution.func_78325_e();
        Mouse.setCursorPosition((int)(guiX * factor), (int)Mouse.getY());
    }

    public static void setMouseY(int guiY) {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution scaledResolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int factor = scaledResolution.func_78325_e();
        Mouse.setCursorPosition((int)Mouse.getX(), (int)(mc.field_71440_d - guiY * factor));
    }

    public static double preciseMouseX() {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution scaledResolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        double width = scaledResolution.func_78326_a();
        return (double)Mouse.getX() * width / (double)mc.field_71443_c;
    }

    public static double preciseMouseY() {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution scaledResolution = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        double height = scaledResolution.func_78328_b();
        return height - (double)Mouse.getY() * height / (double)mc.field_71440_d - 1.0;
    }
}

