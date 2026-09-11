package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBarrier extends Block implements ITileEntityProvider {
   Class TileEntity;

   public BlockBarrier() {
      super(Material.field_151576_e);
      this.func_149647_a(mod_JRMCore.JRMCore);
      this.func_149711_c(-1.0F);
      this.func_149752_b(6000000.0F);
      this.TileEntity = BlockBarrierTileEntity.class;
   }

   public String getTextureFile() {
      return JRMCoreH.tjjrmc + ":";
   }

   @SideOnly(Side.CLIENT)
   public int func_149701_w() {
      return 0;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public boolean func_149678_a(int par1, boolean par2) {
      return true;
   }

   public boolean func_149747_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister par1IconRegister) {
      this.field_149761_L = par1IconRegister.func_94245_a(JRMCoreH.tjjrmc + ":" + this.func_149739_a());
   }

   public int idDropped(int par1, Random par2Random, int par3) {
      return 0;
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new BlockBarrierTileEntity();
   }

   public int quanityDropped(Random random) {
      return 1;
   }

   public int func_149645_b() {
      return -1;
   }

   public int tickRate() {
      return 1;
   }
}
