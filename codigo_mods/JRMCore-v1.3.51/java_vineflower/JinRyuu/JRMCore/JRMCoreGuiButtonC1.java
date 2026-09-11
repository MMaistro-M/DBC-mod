package JinRyuu.JRMCore;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtonC1 extends GuiButton {
   private int bgCol;

   public int getBgCol() {
      return this.bgCol;
   }

   public JRMCoreGuiButtonC1(int par1, int par2, int par3, int par4, int par5, String par6Str) {
      super(par1, par2, par3, par4, par5, par6Str);
   }

   public JRMCoreGuiButtonC1(int par1, int par2, int par3, int par4, int par5, String par6Str, int par7) {
      super(par1, par2, par3, par4, par5, par6Str);
      this.bgCol = par7;
   }

   public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
      if (this.field_146125_m) {
         FontRenderer var4 = par1Minecraft.field_71466_p;
         ResourceLocation txx = new ResourceLocation(JRMCoreGuiScreen.button1);
         par1Minecraft.func_110434_K().func_110577_a(txx);
         this.field_146123_n = par2 >= this.field_146128_h
            && par3 >= this.field_146129_i
            && par2 < this.field_146128_h + this.field_146120_f
            && par3 < this.field_146129_i + this.field_146121_g;
         int var5 = this.func_146114_a(this.field_146123_n);
         int j = 16449280;
         float h2 = (j >> 16 & 0xFF) / 255.0F;
         float h3 = (j >> 8 & 0xFF) / 255.0F;
         float h4 = (j & 0xFF) / 255.0F;
         float h1 = 1.0F;
         if (var5 == 2) {
            int r = (int)(h2 * 254.0F);
            int g = (int)(h3 * 254.0F);
            int b = (int)(h4 * 254.0F);
            float[] hsb = Color.RGBtoHSB(r, g, b, null);
            float hue = hsb[0];
            float saturation = 0.33F;
            float brightness = hsb[2];
            int rgb = Color.HSBtoRGB(hue, saturation, brightness);
            h2 = (rgb >> 16 & 0xFF) / 255.0F;
            h3 = (rgb >> 8 & 0xFF) / 255.0F;
            h4 = (rgb & 0xFF) / 255.0F;
         }

         GL11.glColor3f(h1 * h2, h1 * h3, h1 * h4);
         this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 0 + var5 * 1, this.field_146120_f, this.field_146121_g);
         h2 = (this.bgCol >> 16 & 0xFF) / 255.0F;
         h3 = (this.bgCol >> 8 & 0xFF) / 255.0F;
         h4 = (this.bgCol & 0xFF) / 255.0F;
         h1 = 1.0F;
         GL11.glColor3f(h1 * h2, h1 * h3, h1 * h4);
         this.func_73729_b(
            this.field_146128_h + (var5 == 2 ? 1 : 0),
            this.field_146129_i + (var5 == 2 ? 1 : 0),
            0,
            0,
            this.field_146120_f - (var5 == 2 ? 2 : 0),
            this.field_146121_g - (var5 == 2 ? 2 : 0)
         );
         this.func_146119_b(par1Minecraft, par2, par3);
         int var6 = 14737632;
         if (!this.field_146124_l) {
            var6 = -6250336;
         } else if (this.field_146123_n) {
            var6 = 16777120;
         }

         this.func_73732_a(var4, this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, var6);
      }
   }
}
