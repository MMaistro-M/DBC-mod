package JinRyuu.JRMCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtonsA3 extends GuiButton {
   private String string;
   private int type;

   public JRMCoreGuiButtonsA3(int par1, int par2, int par3, int width, int type, boolean b) {
      this(par1, par2, par3, width, type);
      this.field_146124_l = b;
   }

   public JRMCoreGuiButtonsA3(int par1, int par2, int par3, int width, int type) {
      super(par1, par2, par3, width, 10, "");
      this.field_146120_f = width;
      this.string = "+";
      this.type = type;
   }

   public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
      if (this.field_146125_m) {
         FontRenderer var4 = par1Minecraft.field_71466_p;
         ResourceLocation txx = new ResourceLocation(JRMCoreGuiScreen.button1);
         par1Minecraft.func_110434_K().func_110577_a(txx);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_146123_n = par2 >= this.field_146128_h
            && par3 >= this.field_146129_i
            && par2 < this.field_146128_h + this.field_146120_f
            && par3 < this.field_146129_i + this.field_146121_g;
         int var5 = this.func_146114_a(this.field_146123_n);
         this.func_73729_b(
            this.field_146128_h,
            this.field_146129_i,
            this.type == 3 ? 50 : (this.type == 2 ? 0 : 10 + (this.type == 0 ? 20 : 0)),
            106 + (this.field_146124_l ? var5 * 10 : 0),
            this.field_146120_f,
            this.field_146121_g
         );
         this.func_146119_b(par1Minecraft, par2, par3);
         int var6 = 14737632;
         if (!this.field_146124_l) {
            var6 = -6250336;
         } else if (this.field_146123_n) {
            var6 = 16777120;
         }
      }
   }
}
