package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockColoredStone extends Block {
   @SideOnly(Side.CLIENT)
   private IIcon[] iconArray;
   private IIcon[] iconArrayS;
   private String tex;

   public BlockColoredStone(String tex) {
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
      return par1 == 1
         ? this.iconArray[par2 % this.iconArray.length]
         : (
            par1 == 0
               ? this.iconArray[par2 % this.iconArray.length]
               : (par1 != 2 && par1 != 4 ? this.iconArray[par2 % this.iconArray.length] : this.iconArrayS[par2 % this.iconArrayS.length])
         );
   }

   public int func_149692_a(int par1) {
      return par1;
   }

   public static int getBlockFromDye(int par0) {
      return ~par0 & 15;
   }

   public static int getDyeFromBlock(int par0) {
      return ~par0 & 15;
   }

   @SideOnly(Side.CLIENT)
   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
      for (int j = 0; j < 16; j++) {
         par3List.add(new ItemStack(par1, 1, j));
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister par1IconRegister) {
      this.iconArray = new IIcon[16];
      this.iconArrayS = new IIcon[16];

      for (int i = 0; i < this.iconArray.length; i++) {
         this.iconArray[i] = par1IconRegister.func_94245_a("jinryuumodscore:" + this.tex + "_" + i);
         this.iconArrayS[i] = par1IconRegister.func_94245_a("jinryuumodscore:" + this.tex + "S_" + i);
      }
   }
}
