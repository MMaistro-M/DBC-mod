package JinRyuu.JRMCore;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtonEmpty extends GuiButton {
   private boolean categoryIcon;
   private int col;
   private int icon;
   private int mode;
   private int state;
   private float alpha;

   public JRMCoreGuiButtonEmpty(boolean categoryIcon, int mode, int icon, int state, int x, int y, int par3, int par4, int par5, String text, int col) {
      super(x, y, par3, par4, par5, text);
      this.categoryIcon = categoryIcon;
      this.col = col;
      this.icon = icon;
      this.mode = mode;
      this.state = state;
      this.alpha = 1.0F;
   }

   public JRMCoreGuiButtonEmpty(int x, int y, int par3, int par4, int par5, String text) {
      this(false, 0, 0, 0, x, y, par3, par4, par5, text, 0);
   }

   public void func_146112_a(Minecraft minecraft, int par2, int par3) {
      if (this.field_146125_m) {
         FontRenderer var4 = minecraft.field_71466_p;
         boolean select = this.icon == 0;
         this.field_146123_n = par2 >= this.field_146128_h
            && par3 >= this.field_146129_i
            && par2 < this.field_146128_h + this.field_146120_f
            && par3 < this.field_146129_i + this.field_146121_g;
         int hoverState = this.func_146114_a(this.field_146123_n);
         int idY = 0;
         int idX = 0;
         ResourceLocation txx = new ResourceLocation(JRMCoreH.tjjrmc + ":" + (select ? "note_select.png" : "note_category_icons.png"));
         minecraft.func_110434_K().func_110577_a(txx);
         if (!select && this.mode == 0 && hoverState != 2) {
            this.alpha = 0.6F;
         } else {
            this.alpha = 1.0F;
         }

         GL11.glColor4f(1.0F * this.alpha, 1.0F * this.alpha, 1.0F * this.alpha, 1.0F);
         if (this.icon != 0) {
            int TEXTURE_SIZE = 16;
            idY = (this.icon - 1) * 16 / 256;
            idX = (this.icon - 1) * 16 - idY * 256;
            idY *= 16;
         }

         if (select) {
            hoverState = this.func_146114_a(this.field_146123_n);
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 0 + hoverState * this.field_146121_g, this.field_146120_f, this.field_146121_g);
         } else {
            this.func_73729_b(
               this.field_146128_h - 2,
               this.field_146129_i - 2,
               20,
               196 + hoverState * (this.field_146121_g + 4),
               this.field_146120_f + 4,
               this.field_146121_g + 4
            );
            if (this.categoryIcon) {
               this.setColor(hoverState);
            }

            this.func_73729_b(this.field_146128_h, this.field_146129_i, idX, idY, this.field_146120_f, this.field_146121_g);
            if (this.categoryIcon) {
               GL11.glColor4f(1.0F * this.alpha, 1.0F * this.alpha, 1.0F * this.alpha, 1.0F);
            } else {
               this.setColor(hoverState);
            }

            if (this.state > 0) {
               this.func_73729_b(this.field_146128_h, this.field_146129_i, 0 + (this.state - 1) * 16, idY + 16, this.field_146120_f, this.field_146121_g);
            }

            if (this.mode != 0) {
               this.func_73729_b(
                  this.field_146128_h - 2, this.field_146129_i - 2, 0, 196 + 2 * (this.field_146121_g + 4), this.field_146120_f + 4, this.field_146121_g + 4
               );
            }
         }

         this.func_146119_b(minecraft, par2, par3);
         int var6 = 14737632;
         if (!this.field_146124_l) {
            var6 = -6250336;
         } else if (this.field_146123_n) {
            var6 = 16777120;
         }

         if (this.field_146126_j.length() > 0) {
            this.func_73732_a(
               var4, this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, var6
            );
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   private void setColor(int hoverState) {
      if (this.col != 0) {
         float h2 = (this.col >> 16 & 0xFF) / 255.0F;
         float h3 = (this.col >> 8 & 0xFF) / 255.0F;
         float h4 = (this.col & 0xFF) / 255.0F;
         float h1 = 1.0F * this.alpha;
         if (hoverState == 2) {
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
      }
   }
}
