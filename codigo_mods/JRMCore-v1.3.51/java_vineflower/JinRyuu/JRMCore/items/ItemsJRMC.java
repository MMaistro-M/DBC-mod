package JinRyuu.JRMCore.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemsJRMC {
   public static Item ItemBarberSnC;
   public static ArmorMaterial GI2 = EnumHelper.addArmorMaterial("GI", 250, new int[]{3, 5, 5, 5}, 20);
   public static Item Custom_fabric;
   public static Item Costume_creeper;
   public static Item Costume_ender;
   public static final int[] ItemsVanityNum = new int[]{5, 1, 2, 2, 3};
   public static final int[] ItemVanity3 = new int[]{0, 1, 2, 3, 4};
   public static Item[] ItemsVanity = new Item[ItemsVanityNum.length];

   public static void init() {
      ItemBarberSnC = GameRegistry.registerItem(new ItemBarberSnC(20, 10.0F, true).func_77655_b("ItemBarberSnC"), "ItemBarberSnC", null);
      Custom_fabric = GameRegistry.registerItem(new ItemAnyBase().func_77655_b("ItemFabric"), "ItemFabric", null);
      int id = 0;
      Costume_creeper = GameRegistry.registerItem(
         new VanityColor(7064135, GI2, 5, "jrmc_vanity_" + id).func_77655_b("jrmc_vanity_" + id), "jrmc_vanity_" + id, null
      );
      Costume_ender = GameRegistry.registerItem(
         new VanityColor(12686847, GI2, 5, "jrmc_vanity_" + ++id).func_77655_b("jrmc_vanity_" + id), "jrmc_vanity_" + id, null
      );
      id++;

      for (int i = 0; i < ItemsVanityNum.length; i++) {
         int j = i + id;
         ItemsVanity[i] = GameRegistry.registerItem(
            new VanityColor(16777215, GI2, ItemsVanityNum[i], "jrmc_vanity_" + j, ItemVanity3[i]).func_77655_b("jrmc_vanity_" + j), "jrmc_vanity_" + j, null
         );
      }
   }
}
