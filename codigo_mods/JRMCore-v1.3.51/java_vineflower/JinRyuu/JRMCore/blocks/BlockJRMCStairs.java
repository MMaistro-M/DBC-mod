package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockJRMCStairs extends BlockStairs {
   private static final int[][] field_72159_a = new int[][]{{2, 6}, {3, 7}, {2, 3}, {6, 7}, {0, 4}, {1, 5}, {0, 1}, {4, 5}};
   private final Block modelBlock;
   private final int modelBlockMetadata;
   private boolean field_72156_cr;
   private int field_72160_cs;
   private String tex;

   public BlockJRMCStairs(Block par2Block, int par3, String tex) {
      super(par2Block, par3);
      this.modelBlock = par2Block;
      this.modelBlockMetadata = par3;
      this.func_149722_s();
      this.func_149752_b(6000000.0F);
      this.func_149647_a(mod_JRMCore.JRMCore);
      this.tex = tex;
      this.field_149783_u = true;
   }

   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      if (this.field_72156_cr) {
         this.func_149676_a(
            0.5F * (this.field_72160_cs % 2),
            0.5F * (this.field_72160_cs / 2 % 2),
            0.5F * (this.field_72160_cs / 4 % 2),
            0.5F + 0.5F * (this.field_72160_cs % 2),
            0.5F + 0.5F * (this.field_72160_cs / 2 % 2),
            0.5F + 0.5F * (this.field_72160_cs / 4 % 2)
         );
      } else {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public int func_149645_b() {
      return 10;
   }

   public void func_82541_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      int l = par1IBlockAccess.func_72805_g(par2, par3, par4);
      if ((l & 4) != 0) {
         this.func_149676_a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else {
         this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }
   }

   public void func_149699_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {
      this.modelBlock.func_149699_a(par1World, par2, par3, par4, par5EntityPlayer);
   }

   @SideOnly(Side.CLIENT)
   public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
      this.modelBlock.func_149734_b(par1World, par2, par3, par4, par5Random);
   }

   public void func_149664_b(World par1World, int par2, int par3, int par4, int par5) {
      this.modelBlock.func_149664_b(par1World, par2, par3, par4, par5);
   }

   @SideOnly(Side.CLIENT)
   public int func_149677_c(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      return this.modelBlock.func_149677_c(par1IBlockAccess, par2, par3, par4);
   }

   public float func_149638_a(Entity par1Entity) {
      return this.modelBlock.func_149638_a(par1Entity);
   }

   public int func_149738_a(World par1World) {
      return this.modelBlock.func_149738_a(par1World);
   }

   public void func_149640_a(World par1World, int par2, int par3, int par4, Entity par5Entity, Vec3 par6Vec3) {
      this.modelBlock.func_149640_a(par1World, par2, par3, par4, par5Entity, par6Vec3);
   }

   @SideOnly(Side.CLIENT)
   public int func_149701_w() {
      return 0;
   }

   @SideOnly(Side.CLIENT)
   public IIcon func_149691_a(int par1, int par2) {
      return this.modelBlock.func_149691_a(par1, this.modelBlockMetadata);
   }

   @SideOnly(Side.CLIENT)
   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
      return this.modelBlock.func_149633_g(par1World, par2, par3, par4);
   }

   public boolean func_149703_v() {
      return this.modelBlock.func_149703_v();
   }

   public boolean func_149678_a(int par1, boolean par2) {
      return this.modelBlock.func_149678_a(par1, par2);
   }

   public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
      return this.modelBlock.func_149742_c(par1World, par2, par3, par4);
   }

   public void func_149724_b(World par1World, int par2, int par3, int par4, Entity par5Entity) {
      this.modelBlock.func_149724_b(par1World, par2, par3, par4, par5Entity);
   }

   public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
      this.modelBlock.func_149674_a(par1World, par2, par3, par4, par5Random);
   }

   public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
      return this.modelBlock.func_149727_a(par1World, par2, par3, par4, par5EntityPlayer, 0, 0.0F, 0.0F, 0.0F);
   }

   public void func_149723_a(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {
      this.modelBlock.func_149723_a(par1World, par2, par3, par4, par5Explosion);
   }

   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
      int l = MathHelper.func_76128_c(par5EntityLivingBase.field_70177_z * 4.0F / 360.0F + 0.5) & 3;
      int i1 = par1World.func_72805_g(par2, par3, par4) & 4;
      if (l == 0) {
         par1World.func_72921_c(par2, par3, par4, 2 | i1, 2);
      }

      if (l == 1) {
         par1World.func_72921_c(par2, par3, par4, 1 | i1, 2);
      }

      if (l == 2) {
         par1World.func_72921_c(par2, par3, par4, 3 | i1, 2);
      }

      if (l == 3) {
         par1World.func_72921_c(par2, par3, par4, 0 | i1, 2);
      }
   }

   public int func_149660_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
      return par5 == 0 || par5 != 1 && !(par7 <= 0.5) ? par9 | 4 : par9;
   }

   public MovingObjectPosition func_149731_a(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
      MovingObjectPosition[] amovingobjectposition = new MovingObjectPosition[8];
      int l = par1World.func_72805_g(par2, par3, par4);
      int i1 = l & 3;
      boolean flag = (l & 4) == 4;
      int[] aint = field_72159_a[i1 + (flag ? 4 : 0)];
      this.field_72156_cr = true;

      for (int i2 = 0; i2 < 8; i2++) {
         this.field_72160_cs = i2;
         int[] aint1 = aint;
         int j1 = aint.length;

         for (int k1 = 0; k1 < j1; k1++) {
            int l1 = aint1[k1];
            if (l1 == i2) {
            }
         }

         amovingobjectposition[i2] = super.func_149731_a(par1World, par2, par3, par4, par5Vec3, par6Vec3);
      }

      int[] aint2 = aint;
      int j2 = aint.length;

      for (int j1 = 0; j1 < j2; j1++) {
         int k1 = aint2[j1];
         amovingobjectposition[k1] = null;
      }

      MovingObjectPosition movingobjectposition = null;
      double d0 = 0.0;
      MovingObjectPosition[] amovingobjectposition1 = amovingobjectposition;
      int l1 = amovingobjectposition.length;

      for (int k2 = 0; k2 < l1; k2++) {
         MovingObjectPosition movingobjectposition1 = amovingobjectposition1[k2];
         if (movingobjectposition1 != null) {
            double d1 = movingobjectposition1.field_72307_f.func_72436_e(par6Vec3);
            if (d1 > d0) {
               movingobjectposition = movingobjectposition1;
               d0 = d1;
            }
         }
      }

      return movingobjectposition;
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister par1IconRegister) {
      this.field_149761_L = par1IconRegister.func_94245_a("jinryuumodscore:" + this.tex);
   }

   public int func_149745_a(Random par1Random) {
      return 1;
   }
}
