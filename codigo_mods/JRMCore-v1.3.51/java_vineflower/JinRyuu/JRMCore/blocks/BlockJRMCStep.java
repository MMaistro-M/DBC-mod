package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockJRMCStep extends BlockJRMCHalfSlab {
   @SideOnly(Side.CLIENT)
   private IIcon theIcon;
   private String tex;
   private String tex2;

   public BlockJRMCStep(boolean par2, String tex, String tex2) {
      super(par2, Material.field_151576_e);
      this.func_149711_c(50.0F);
      this.func_149752_b(2000.0F);
      this.func_149647_a(mod_JRMCore.JRMCore);
      this.tex = tex;
      this.tex2 = tex2;
   }

   @SideOnly(Side.CLIENT)
   public IIcon func_149691_a(int par1, int par2) {
      return par1 == 1 ? this.field_149761_L : (par1 == 0 ? this.field_149761_L : (par1 != 2 && par1 != 4 ? this.field_149761_L : this.theIcon));
   }

   public int func_149701_w() {
      return 0;
   }

   @Override
   public boolean func_149662_c() {
      return false;
   }

   @Override
   public boolean func_149686_d() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister par1IconRegister) {
      this.field_149761_L = par1IconRegister.func_94245_a("jinryuumodscore:" + this.tex);
      this.theIcon = par1IconRegister.func_94245_a("jinryuumodscore:" + this.tex2);
   }

   @Override
   public String getFullSlabName(int par1) {
      return super.func_149739_a();
   }
}
