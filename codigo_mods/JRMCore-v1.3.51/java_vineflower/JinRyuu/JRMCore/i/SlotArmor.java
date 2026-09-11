package JinRyuu.JRMCore.i;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class SlotArmor extends Slot {
   final int armorType;
   final EntityPlayer player;

   public SlotArmor(EntityPlayer player, IInventory inventory, int slot, int x, int y, int armorType) {
      super(inventory, slot, x, y);
      this.player = player;
      this.armorType = armorType;
   }

   public int func_75219_a() {
      return 1;
   }

   public boolean func_75214_a(ItemStack stack) {
      Item item = stack == null ? null : stack.func_77973_b();
      return item != null && item.isValidArmor(stack, this.armorType, this.player);
   }

   @SideOnly(Side.CLIENT)
   public void setBackgroundIconTexture(ResourceLocation texture) {
      this.texture = texture;
   }
}
