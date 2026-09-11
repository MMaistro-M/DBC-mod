/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JGRenderHelper {
    protected static RenderItem itemRender = new RenderItem();

    public static void drawItem(Minecraft mc, float zLevel, FontRenderer fontRendererObj, ItemStack stack, int x, int y, float scale, int stackSize) {
        GL11.glPushMatrix();
        boolean k = false;
        boolean l = false;
        GL11.glDisable((int)32826);
        RenderHelper.func_74518_a();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        RenderHelper.func_74520_c();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)k), (float)((float)l), (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)32826);
        int short1 = 240;
        int short2 = 240;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)short1 / 1.0f), (float)((float)short2 / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2896);
        String s = "";
        if (stack.field_77994_a > 1) {
            s = "" + EnumChatFormatting.WHITE + stackSize;
        }
        JGRenderHelper.drawItemStack(mc, zLevel, fontRendererObj, stack, x, y, s);
        GL11.glPopMatrix();
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        RenderHelper.func_74518_a();
        GL11.glPopMatrix();
    }

    public static void drawItemStack(Minecraft mc, float zLevel, FontRenderer fontRendererObj, ItemStack stack, int x, int y, String s) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)32.0f);
        zLevel = 200.0f;
        JGRenderHelper.itemRender.field_77023_b = 200.0f;
        FontRenderer font = null;
        if (stack != null) {
            font = stack.func_77973_b().getFontRenderer(stack);
        }
        if (font == null) {
            font = fontRendererObj;
        }
        itemRender.func_82406_b(font, mc.func_110434_K(), stack, x, y);
        itemRender.func_94148_a(font, mc.func_110434_K(), stack, x, y, s);
        zLevel = 0.0f;
        JGRenderHelper.itemRender.field_77023_b = 0.0f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    public static void modelScalePositionHelper(float f) {
        GL11.glScalef((float)f, (float)f, (float)f);
        float ff1 = -2.45f;
        float ff2 = 0.6f;
        float diff = ff2 / f * ff1;
        GL11.glTranslatef((float)0.0f, (float)((f - 1.0f) * diff), (float)0.0f);
    }

    public static void glColor4f(int c, float a, float h1) {
        float h2 = (float)(c >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(c >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(c & 0xFF) / 255.0f;
        GL11.glColor4f((float)(h1 * h2 + 0.6f), (float)(h1 * h3 + 0.6f), (float)(h1 * h4 + 0.6f), (float)a);
    }

    public static void glColor4f(int c, float a) {
        float h2 = (float)(c >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(c >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(c & 0xFF) / 255.0f;
        float h1 = 1.0f;
        GL11.glColor4f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4), (float)a);
    }

    public static void cf(int x, int y, float a) {
        float h2 = (float)(x >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(x >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(x & 0xFF) / 255.0f;
        float y2 = (float)(y >> 16 & 0xFF) / 255.0f;
        float y3 = (float)(y >> 8 & 0xFF) / 255.0f;
        float y4 = (float)(y & 0xFF) / 255.0f;
        float pc = 0.5f;
        pc = pc > 1.0f ? 1.0f : pc;
        float pg = 1.0f - pc;
        float ga = h2 * pg + y2 * pc;
        float hr = h3 * pg + y3 * pc;
        float ah = h4 * pg + y4 * pc;
        h2 = ga;
        h3 = hr;
        h4 = ah;
        GL11.glColor4f((float)h2, (float)h3, (float)h4, (float)a);
    }

    public static void drawTexturedModalRect(float x, float y, int u, int v, float width, float height, float z) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)x, (double)(y + 0.0f), (double)z, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78374_a((double)x, (double)(y + height), (double)z, (double)((float)(u + 0) * f), (double)(((float)v + height) * f1));
        tessellator.func_78374_a((double)(x + width), (double)(y + height), (double)z, (double)(((float)u + width) * f), (double)(((float)v + height) * f1));
        tessellator.func_78374_a((double)(x + width), (double)(y + 0.0f), (double)z, (double)(((float)u + width) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78381_a();
    }

    public static void draw_tessellator2(Tessellator tessellator, int brightness, float particleWidth, float width2, float particleHeight, float height2, float red, float green, float blue, float alpha) {
        tessellator.func_78382_b();
        tessellator.func_78380_c(brightness);
        tessellator.func_78369_a(red, green, blue, alpha);
        tessellator.func_78374_a((double)(-particleWidth - width2), (double)(-particleHeight + height2), 0.0, 0.0, 0.0);
        tessellator.func_78374_a((double)(-particleWidth + width2), (double)(particleHeight - height2), 0.0, 0.0, 1.0);
        tessellator.func_78374_a((double)(particleWidth + width2), (double)(particleHeight - height2), 0.0, 1.0, 1.0);
        tessellator.func_78374_a((double)(particleWidth - width2), (double)(-particleHeight + height2), 0.0, 1.0, 0.0);
        tessellator.func_78381_a();
    }

    public static void draw_tessellator(Tessellator tessellator, int brightness, float particleWidth, float width2, float particleHeight, float height2, float red, float green, float blue, float alpha) {
        tessellator.func_78382_b();
        tessellator.func_78380_c(brightness);
        tessellator.func_78369_a(red, green, blue, alpha);
        tessellator.func_78374_a((double)(-particleWidth + width2), (double)(-particleHeight + height2), 0.0, 0.0, 0.0);
        tessellator.func_78374_a((double)(-particleWidth + width2), (double)(particleHeight + height2), 0.0, 0.0, 1.0);
        tessellator.func_78374_a((double)(particleWidth + width2), (double)(particleHeight + height2), 0.0, 1.0, 1.0);
        tessellator.func_78374_a((double)(particleWidth + width2), (double)(-particleHeight + height2), 0.0, 1.0, 0.0);
        tessellator.func_78381_a();
    }

    public static void draw_tessellator(Tessellator tessellator, int brightness, float particleWidth, float particleHeight, float red, float green, float blue, float alpha) {
        JGRenderHelper.draw_tessellator(tessellator, brightness, particleWidth, 0.0f, particleHeight, 0.0f, red, green, blue, alpha);
    }

    public static void tex(RenderManager renderManager, int col) {
        float h2 = (float)(col >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(col >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(col & 0xFF) / 255.0f;
        GL11.glColor4f((float)h2, (float)h3, (float)h4, (float)1.0f);
        ResourceLocation txx = new ResourceLocation("jinryuumodscore:allw.png");
        renderManager.field_78724_e.func_110577_a(txx);
    }

    public static void tex(RenderManager renderManager, int col, float v) {
        float h2 = (float)(col >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(col >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(col & 0xFF) / 255.0f;
        GL11.glColor4f((float)h2, (float)h3, (float)h4, (float)v);
        ResourceLocation txx = new ResourceLocation("jinryuumodscore:allw.png");
        renderManager.field_78724_e.func_110577_a(txx);
    }

    public static boolean isClientPlayer(Entity entity) {
        return entity instanceof EntityPlayerSP;
    }
}

