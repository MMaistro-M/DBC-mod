package JinRyuu.JRMCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtonC extends GuiButton {
   private int bgCol;
   private int defCol = 0;

   public int getBgCol() {
      return this.bgCol;
   }

   public JRMCoreGuiButtonC(int par1, int par2, int par3, int par4, int par5, String par6Str) {
      super(par1, par2, par3, par4, par5, par6Str);
   }

   public JRMCoreGuiButtonC(int par1, int par2, int par3, int par4, int par5, String par6Str, int par7) {
      super(par1, par2, par3, par4, par5, par6Str);
      this.bgCol = par7;
   }

   public JRMCoreGuiButtonC(int par1, int par2, int par3, int par4, int par5, String par6Str, int par7, int par8) {
      this(par1, par2, par3, par4, par5, par6Str, par7);
      this.defCol = par8;
   }

   public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
      if (this.field_146125_m) {
         FontRenderer var4 = par1Minecraft.field_71466_p;
         ResourceLocation txx = new ResourceLocation(JRMCoreGuiScreen.button1);
         par1Minecraft.func_110434_K().func_110577_a(txx);
         int col = this.defCol == 0 ? this.bgCol : this.defCol;
         float h2 = (col >> 16 & 0xFF) / 255.0F;
         float h3 = (col >> 8 & 0xFF) / 255.0F;
         float h4 = (col & 0xFF) / 255.0F;
         float h1 = 1.0F;
         GL11.glColor3f(h1 * h2, h1 * h3, h1 * h4);
         this.field_146123_n = par2 >= this.field_146128_h
            && par3 >= this.field_146129_i
            && par2 < this.field_146128_h + this.field_146120_f
            && par3 < this.field_146129_i + this.field_146121_g;
         int var5 = this.func_146114_a(this.field_146123_n);
         this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, 0 + var5 * 1, this.field_146120_f, this.field_146121_g);
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
