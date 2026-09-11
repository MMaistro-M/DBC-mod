package JinRyuu.JRMCore.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemJRMCSeedFood extends ItemFood implements IPlantable {
   private final Block theBlockPlant;
   private final Block soilId;

   public ItemJRMCSeedFood(int parHealAmount, float parSaturationModifier, Block parBlockPlant, Block parSoilBlock) {
      super(parHealAmount, parSaturationModifier, false);
      this.theBlockPlant = parBlockPlant;
      this.soilId = parSoilBlock;
   }

   public boolean func_77648_a(
      ItemStack parItemStack, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ, int par7, float par8, float par9, float par10
   ) {
      if (par7 != 1) {
         return false;
      }

      if (parPlayer.func_82247_a(parX, parY + 1, parZ, par7, parItemStack)) {
         if (parWorld.func_147439_a(parX, parY, parZ).canSustainPlant(parWorld, parX, parY, parZ, ForgeDirection.UP, this)
            && parWorld.func_147437_c(parX, parY + 1, parZ)) {
            parWorld.func_147449_b(parX, parY + 1, parZ, this.theBlockPlant);
            parItemStack.field_77994_a--;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
      return EnumPlantType.Crop;
   }

   public Block getPlant(IBlockAccess world, int x, int y, int z) {
      return this.theBlockPlant;
   }

   public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
      return 0;
   }

   public Block getSoilId() {
      return this.soilId;
   }
}
