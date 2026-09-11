package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.mod_JRMCore;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ItemAnyBase extends Item {
   public ItemAnyBase() {
      this.func_77637_a(mod_JRMCore.JRMCore);
   }

   public void func_94581_a(IIconRegister iconRegister) {
      this.field_77791_bV = iconRegister.func_94245_a("jinryuumodscore:" + this.func_77658_a().replaceAll("item.", ""));
   }
}
