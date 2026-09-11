package JinRyuu.JRMCore;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtons00 extends GuiButton {
   public int col;

   public JRMCoreGuiButtons00(int id, int x, int y, int height, int width, String text, int color) {
      super(id, x, y, height, width, text);
      this.col = color;
   }

   public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
      if (this.field_146125_m) {
         this.field_146123_n = par2 >= this.field_146128_h
            && par3 >= this.field_146129_i
            && par2 < this.field_146128_h + this.field_146120_f
            && par3 < this.field_146129_i + this.field_146121_g;
         int var5 = this.func_146114_a(this.field_146123_n);
         int j = this.col == 0 ? 16449280 : this.col;
         FontRenderer var4 = par1Minecraft.field_71466_p;
         ResourceLocation txx = new ResourceLocation(JRMCoreGuiScreen.button1);
         par1Minecraft.func_110434_K().func_110577_a(txx);
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
         this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 46 + var5 * 20, this.field_146120_f / 2, this.field_146121_g);
         this.func_73729_b(
            this.field_146128_h + this.field_146120_f / 2,
            this.field_146129_i,
            200 - this.field_146120_f / 2,
            46 + var5 * 20,
            this.field_146120_f / 2,
            this.field_146121_g
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
