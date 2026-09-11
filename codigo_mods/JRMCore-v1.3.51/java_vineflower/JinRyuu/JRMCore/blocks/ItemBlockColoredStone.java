package JinRyuu.JRMCore.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

public class ItemBlockColoredStone extends ItemBlock {
   public ItemBlockColoredStone(Block par1) {
      super(par1);
      this.func_77656_e(0);
      this.func_77627_a(true);
   }

   public int func_77647_b(int par1) {
      return par1;
   }

   public String func_77667_c(ItemStack par1ItemStack) {
      int i = BlockColoredStone.getBlockFromDye(par1ItemStack.func_77960_j());
      return super.func_77658_a() + "." + ItemDye.field_150923_a[i];
   }
}
