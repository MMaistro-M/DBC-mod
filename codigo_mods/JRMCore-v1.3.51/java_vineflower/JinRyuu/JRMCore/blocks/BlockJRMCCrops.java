package JinRyuu.JRMCore.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockJRMCCrops extends BlockBush implements IGrowable {
   protected int maxGrowthStage = 7;
   @SideOnly(Side.CLIENT)
   protected IIcon[] iIcon;

   public BlockJRMCCrops() {
      this.func_149675_a(true);
      float f = 0.5F;
      this.func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
      this.func_149647_a((CreativeTabs)null);
      this.func_149711_c(0.0F);
      this.func_149672_a(field_149779_h);
      this.func_149649_H();
   }

   protected boolean func_149854_a(Block parBlock) {
      return parBlock == Blocks.field_150458_ak;
   }

   public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
      super.func_149674_a(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_5_);
      if (p_149674_1_.func_72957_l(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9) {
         int l = p_149674_1_.func_72805_g(p_149674_2_, p_149674_3_, p_149674_4_);
         if (l < 7) {
            float f = this.func_149864_n(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
            if (p_149674_5_.nextInt((int)(25.0F / f) + 1) == 0) {
               if (++l > this.maxGrowthStage) {
                  l = this.maxGrowthStage;
               }

               p_149674_1_.func_72921_c(p_149674_2_, p_149674_3_, p_149674_4_, l, 2);
            }
         }
      }
   }

   private float func_149864_n(World p_149864_1_, int p_149864_2_, int p_149864_3_, int p_149864_4_) {
      float f = 1.0F;
      Block block = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ - 1);
      Block block1 = p_149864_1_.func_147439_a(p_149864_2_, p_149864_3_, p_149864_4_ + 1);
      Block block2 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_);
      Block block3 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_);
      Block block4 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ - 1);
      Block block5 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ - 1);
      Block block6 = p_149864_1_.func_147439_a(p_149864_2_ + 1, p_149864_3_, p_149864_4_ + 1);
      Block block7 = p_149864_1_.func_147439_a(p_149864_2_ - 1, p_149864_3_, p_149864_4_ + 1);
      boolean flag = block2 == this || block3 == this;
      boolean flag1 = block == this || block1 == this;
      boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

      for (int l = p_149864_2_ - 1; l <= p_149864_2_ + 1; l++) {
         for (int i1 = p_149864_4_ - 1; i1 <= p_149864_4_ + 1; i1++) {
            float f1 = 0.0F;
            if (p_149864_1_.func_147439_a(l, p_149864_3_ - 1, i1).canSustainPlant(p_149864_1_, l, p_149864_3_ - 1, i1, ForgeDirection.UP, this)) {
               f1 = 1.0F;
               if (p_149864_1_.func_147439_a(l, p_149864_3_ - 1, i1).isFertile(p_149864_1_, l, p_149864_3_ - 1, i1)) {
                  f1 = 3.0F;
               }
            }

            if (l != p_149864_2_ || i1 != p_149864_4_) {
               f1 /= 4.0F;
            }

            f += f1;
         }
      }

      if (flag2 || flag && flag1) {
         f /= 2.0F;
      }

      return f;
   }

   public void incrementGrowStage(World parWorld, Random parRand, int parX, int parY, int parZ) {
      int growStage = parWorld.func_72805_g(parX, parY, parZ) + MathHelper.func_76136_a(parRand, 2, 5);
      if (growStage > this.maxGrowthStage) {
         growStage = this.maxGrowthStage;
      }

      parWorld.func_72921_c(parX, parY, parZ, growStage, 2);
   }

   public Item func_149650_a(int p_149650_1_, Random parRand, int parFortune) {
      return Item.func_150898_a(this);
   }

   public int func_149645_b() {
      return 1;
   }

   @SideOnly(Side.CLIENT)
   public IIcon func_149691_a(int parSide, int pargs) {
      if (pargs < 0 || pargs > this.maxGrowthStage) {
         pargs = this.maxGrowthStage;
      }

      return this.iIcon[this.iIcon.length > pargs ? pargs : this.iIcon.length - 1];
   }

   public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {
      return parWorld.func_72805_g(parX, parY, parZ) != 7;
   }

   public boolean func_149852_a(World p_149852_1_, Random parRand, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
      return true;
   }

   public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ) {
      this.incrementGrowStage(parWorld, parRand, parX, parY, parZ);
   }
}
