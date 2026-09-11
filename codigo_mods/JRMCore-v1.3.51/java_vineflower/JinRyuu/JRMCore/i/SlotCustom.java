package JinRyuu.JRMCore.i;

import JinRyuu.JRMCore.items.ItemBodysuit;
import JinRyuu.JRMCore.items.ItemHeadwear;
import JinRyuu.JRMCore.items.ItemVanity;
import JinRyuu.JRMCore.items.ItemWeight;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCustom extends Slot {
   public SlotCustom(IInventory inventory, int slotIndex, int x, int y) {
      super(inventory, slotIndex, x, y);
   }

   public boolean func_75214_a(ItemStack stack) {
      if (this.getSlotIndex() == 0) {
         return stack.func_77973_b() instanceof ItemWeight;
      } else if (this.getSlotIndex() == 1) {
         return stack.func_77973_b() instanceof ItemBodysuit;
      } else if (this.getSlotIndex() == 2) {
         return stack.func_77973_b() instanceof ItemHeadwear;
      } else {
         return this.getSlotIndex() >= 3 && this.getSlotIndex() <= 10 ? stack.func_77973_b() instanceof ItemVanity : false;
      }
   }
}
