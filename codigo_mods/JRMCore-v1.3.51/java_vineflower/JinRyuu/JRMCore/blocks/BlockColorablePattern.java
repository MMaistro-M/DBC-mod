package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockColorablePattern extends Block {
   @SideOnly(Side.CLIENT)
   private IIcon icon;
   private String tex;

   public BlockColorablePattern(String tex) {
      super(Material.field_151573_f);
      this.func_149722_s();
      this.func_149752_b(6000000.0F);
      this.func_149647_a(mod_JRMCore.JRMCore);
      this.tex = tex;
   }

   @SideOnly(Side.CLIENT)
   public int func_149701_w() {
      return 0;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return JRMCoreConfig.BuildingBlocksRenderAsNormalBlock;
   }

   public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public IIcon func_149691_a(int par1, int par2) {
      return this.icon;
   }

   public int func_149692_a(int par1) {
      return par1;
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister par1IconRegister) {
      this.icon = par1IconRegister.func_94245_a("jinryuumodscore:" + this.tex);
   }
}
