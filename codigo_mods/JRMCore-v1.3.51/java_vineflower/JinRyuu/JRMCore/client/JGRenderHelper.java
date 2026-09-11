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
      int k = 0;
      int l = 0;
      GL11.glDisable(32826);
      RenderHelper.func_74518_a();
      GL11.glDisable(2896);
      GL11.glDisable(2929);
      RenderHelper.func_74520_c();
      GL11.glPushMatrix();
      GL11.glTranslatef(k, l, 0.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(32826);
      short short1 = 240;
      short short2 = 240;
      OpenGlHelper.func_77475_a(OpenGlHelper.field_77476_b, short1 / 1.0F, short2 / 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(2896);
      String s = "";
      if (stack.field_77994_a > 1) {
         s = "" + EnumChatFormatting.WHITE + stackSize;
      }

      drawItemStack(mc, zLevel, fontRendererObj, stack, x, y, s);
      GL11.glPopMatrix();
      GL11.glEnable(2896);
      GL11.glEnable(2929);
      RenderHelper.func_74518_a();
      GL11.glPopMatrix();
   }

   public static void drawItemStack(Minecraft mc, float zLevel, FontRenderer fontRendererObj, ItemStack stack, int x, int y, String s) {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.0F, 32.0F);
      zLevel = 200.0F;
      itemRender.field_77023_b = 200.0F;
      FontRenderer font = null;
      if (stack != null) {
         font = stack.func_77973_b().getFontRenderer(stack);
      }

      if (font == null) {
         font = fontRendererObj;
      }

      itemRender.func_82406_b(font, mc.func_110434_K(), stack, x, y);
      itemRender.func_94148_a(font, mc.func_110434_K(), stack, x, y, s);
      zLevel = 0.0F;
      itemRender.field_77023_b = 0.0F;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public static void modelScalePositionHelper(float f) {
      GL11.glScalef(f, f, f);
      float ff1 = -2.45F;
      float ff2 = 0.6F;
      float diff = ff2 / f * ff1;
      GL11.glTranslatef(0.0F, (f - 1.0F) * diff, 0.0F);
   }

   public static void glColor4f(int c, float a, float h1) {
      float h2 = (c >> 16 & 0xFF) / 255.0F;
      float h3 = (c >> 8 & 0xFF) / 255.0F;
      float h4 = (c & 0xFF) / 255.0F;
      GL11.glColor4f(h1 * h2 + 0.6F, h1 * h3 + 0.6F, h1 * h4 + 0.6F, a);
   }

   public static void glColor4f(int c, float a) {
      float h2 = (c >> 16 & 0xFF) / 255.0F;
      float h3 = (c >> 8 & 0xFF) / 255.0F;
      float h4 = (c & 0xFF) / 255.0F;
      float h1 = 1.0F;
      GL11.glColor4f(h1 * h2, h1 * h3, h1 * h4, a);
   }

   public static void cf(int x, int y, float a) {
      float h2 = (x >> 16 & 0xFF) / 255.0F;
      float h3 = (x >> 8 & 0xFF) / 255.0F;
      float h4 = (x & 0xFF) / 255.0F;
      float y2 = (y >> 16 & 0xFF) / 255.0F;
      float y3 = (y >> 8 & 0xFF) / 255.0F;
      float y4 = (y & 0xFF) / 255.0F;
      float pc = 0.5F;
      pc = pc > 1.0F ? 1.0F : pc;
      float pg = 1.0F - pc;
      float ga = h2 * pg + y2 * pc;
      float hr = h3 * pg + y3 * pc;
      float ah = h4 * pg + y4 * pc;
      h2 = ga;
      h3 = hr;
      h4 = ah;
      GL11.glColor4f(h2, h3, h4, a);
   }

   public static void drawTexturedModalRect(float x, float y, int u, int v, float width, float height, float z) {
      float f = 0.00390625F;
      float f1 = 0.00390625F;
      Tessellator tessellator = Tessellator.field_78398_a;
      tessellator.func_78382_b();
      tessellator.func_78374_a(x, y + 0.0F, z, (u + 0) * f, (v + 0) * f1);
      tessellator.func_78374_a(x, y + height, z, (u + 0) * f, (v + height) * f1);
      tessellator.func_78374_a(x + width, y + height, z, (u + width) * f, (v + height) * f1);
      tessellator.func_78374_a(x + width, y + 0.0F, z, (u + width) * f, (v + 0) * f1);
      tessellator.func_78381_a();
   }

   public static void draw_tessellator2(
      Tessellator tessellator,
      int brightness,
      float particleWidth,
      float width2,
      float particleHeight,
      float height2,
      float red,
      float green,
      float blue,
      float alpha
   ) {
      tessellator.func_78382_b();
      tessellator.func_78380_c(brightness);
      tessellator.func_78369_a(red, green, blue, alpha);
      tessellator.func_78374_a(-particleWidth - width2, -particleHeight + height2, 0.0, 0.0, 0.0);
      tessellator.func_78374_a(-particleWidth + width2, particleHeight - height2, 0.0, 0.0, 1.0);
      tessellator.func_78374_a(particleWidth + width2, particleHeight - height2, 0.0, 1.0, 1.0);
      tessellator.func_78374_a(particleWidth - width2, -particleHeight + height2, 0.0, 1.0, 0.0);
      tessellator.func_78381_a();
   }

   public static void draw_tessellator(
      Tessellator tessellator,
      int brightness,
      float particleWidth,
      float width2,
      float particleHeight,
      float height2,
      float red,
      float green,
      float blue,
      float alpha
   ) {
      tessellator.func_78382_b();
      tessellator.func_78380_c(brightness);
      tessellator.func_78369_a(red, green, blue, alpha);
      tessellator.func_78374_a(-particleWidth + width2, -particleHeight + height2, 0.0, 0.0, 0.0);
      tessellator.func_78374_a(-particleWidth + width2, particleHeight + height2, 0.0, 0.0, 1.0);
      tessellator.func_78374_a(particleWidth + width2, particleHeight + height2, 0.0, 1.0, 1.0);
      tessellator.func_78374_a(particleWidth + width2, -particleHeight + height2, 0.0, 1.0, 0.0);
      tessellator.func_78381_a();
   }

   public static void draw_tessellator(
      Tessellator tessellator, int brightness, float particleWidth, float particleHeight, float red, float green, float blue, float alpha
   ) {
      draw_tessellator(tessellator, brightness, particleWidth, 0.0F, particleHeight, 0.0F, red, green, blue, alpha);
   }

   public static void tex(RenderManager renderManager, int col) {
      float h2 = (col >> 16 & 0xFF) / 255.0F;
      float h3 = (col >> 8 & 0xFF) / 255.0F;
      float h4 = (col & 0xFF) / 255.0F;
      GL11.glColor4f(h2, h3, h4, 1.0F);
      ResourceLocation txx = new ResourceLocation("jinryuumodscore:allw.png");
      renderManager.field_78724_e.func_110577_a(txx);
   }

   public static void tex(RenderManager renderManager, int col, float v) {
      float h2 = (col >> 16 & 0xFF) / 255.0F;
      float h3 = (col >> 8 & 0xFF) / 255.0F;
      float h4 = (col & 0xFF) / 255.0F;
      GL11.glColor4f(h2, h3, h4, v);
      ResourceLocation txx = new ResourceLocation("jinryuumodscore:allw.png");
      renderManager.field_78724_e.func_110577_a(txx);
   }

   public static boolean isClientPlayer(Entity entity) {
      return entity instanceof EntityPlayerSP;
   }
}
