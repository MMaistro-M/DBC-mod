package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockJRMCFence extends BlockFence {
   private String tex;

   public BlockJRMCFence(String tex) {
      super("", Material.field_151573_f);
      this.func_149722_s();
      this.func_149752_b(6000000.0F);
      this.func_149647_a(mod_JRMCore.JRMCore);
      this.tex = tex;
   }

   public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
      boolean flag = this.func_149826_e(par1World, par2, par3, par4 - 1);
      boolean flag1 = this.func_149826_e(par1World, par2, par3, par4 + 1);
      boolean flag2 = this.func_149826_e(par1World, par2 - 1, par3, par4);
      boolean flag3 = this.func_149826_e(par1World, par2 + 1, par3, par4);
      float f = 0.375F;
      float f1 = 0.625F;
      float f2 = 0.375F;
      float f3 = 0.625F;
      if (flag) {
         f2 = 0.0F;
      }

      if (flag1) {
         f3 = 1.0F;
      }

      if (flag || flag1) {
         this.func_149676_a(f, 0.0F, f2, f1, 1.5F, f3);
         super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
      }

      f2 = 0.375F;
      f3 = 0.625F;
      if (flag2) {
         f = 0.0F;
      }

      if (flag3) {
         f1 = 1.0F;
      }

      if (flag2 || flag3 || !flag && !flag1) {
         this.func_149676_a(f, 0.0F, f2, f1, 1.5F, f3);
         super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
      }

      if (flag) {
         f2 = 0.0F;
      }

      if (flag1) {
         f3 = 1.0F;
      }

      this.func_149676_a(f, 0.0F, f2, f1, 1.0F, f3);
   }

   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      boolean flag = this.func_149826_e(par1IBlockAccess, par2, par3, par4 - 1);
      boolean flag1 = this.func_149826_e(par1IBlockAccess, par2, par3, par4 + 1);
      boolean flag2 = this.func_149826_e(par1IBlockAccess, par2 - 1, par3, par4);
      boolean flag3 = this.func_149826_e(par1IBlockAccess, par2 + 1, par3, par4);
      float f = 0.375F;
      float f1 = 0.625F;
      float f2 = 0.375F;
      float f3 = 0.625F;
      if (flag) {
         f2 = 0.0F;
      }

      if (flag1) {
         f3 = 1.0F;
      }

      if (flag2) {
         f = 0.0F;
      }

      if (flag3) {
         f1 = 1.0F;
      }

      this.func_149676_a(f, 0.0F, f2, f1, 1.0F, f3);
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      return false;
   }

   public int func_149645_b() {
      return 11;
   }

   @SideOnly(Side.CLIENT)
   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister par1IconRegister) {
      this.field_149761_L = par1IconRegister.func_94245_a("jinryuumodscore:" + this.tex);
   }

   public int func_149745_a(Random par1Random) {
      return 1;
   }

   @SideOnly(Side.CLIENT)
   public int func_149701_w() {
      return 0;
   }
}
