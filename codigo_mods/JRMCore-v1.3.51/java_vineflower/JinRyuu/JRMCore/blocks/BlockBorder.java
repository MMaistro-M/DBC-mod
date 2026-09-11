package JinRyuu.JRMCore.blocks;

import JinRyuu.JRMCore.mod_JRMCore;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBorder extends BlockBarrier {
   public BlockBorder() {
      this.func_149647_a(mod_JRMCore.JRMCore);
      this.func_149711_c(-1.0F);
      this.func_149752_b(6000000.0F);
      this.TileEntity = BlockBorderTileEntity.class;
   }

   @Override
   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      return new BlockBorderTileEntity();
   }
}
