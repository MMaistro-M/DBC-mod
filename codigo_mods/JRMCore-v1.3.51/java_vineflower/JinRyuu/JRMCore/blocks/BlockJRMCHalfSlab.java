package JinRyuu.JRMCore.blocks;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockJRMCHalfSlab extends Block {
   protected final boolean isDoubleSlab;

   public BlockJRMCHalfSlab(boolean par2, Material par3Material) {
      super(par3Material);
      this.isDoubleSlab = par2;
      if (!par2) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }
   }

   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      if (this.isDoubleSlab) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         boolean flag = (par1IBlockAccess.func_72805_g(par2, par3, par4) & 8) != 0;
         if (flag) {
            this.func_149676_a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
         } else {
            this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
         }
      }
   }

   public void func_149683_g() {
      if (this.isDoubleSlab) {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }
   }

   public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
      this.func_149719_a(par1World, par2, par3, par4);
      super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
   }

   public boolean func_149662_c() {
      return this.isDoubleSlab;
   }

   public int func_149660_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
      return this.isDoubleSlab ? par9 : (par5 == 0 || par5 != 1 && !(par7 <= 0.5) ? par9 | 8 : par9);
   }

   public int func_149745_a(Random par1Random) {
      return this.isDoubleSlab ? 2 : 1;
   }

   public int func_149692_a(int par1) {
      return par1;
   }

   public boolean func_149686_d() {
      return this.isDoubleSlab;
   }

   public abstract String getFullSlabName(int var1);

   public int func_149643_k(World par1World, int par2, int par3, int par4) {
      return super.func_149643_k(par1World, par2, par3, par4) & 7;
   }
}
