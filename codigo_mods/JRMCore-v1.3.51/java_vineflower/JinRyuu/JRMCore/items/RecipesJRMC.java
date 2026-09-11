package JinRyuu.JRMCore.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesJRMC {
   public static void init() {
      GameRegistry.addRecipe(
         new ItemStack(ItemsJRMC.ItemBarberSnC, 1), new Object[]{"IP", "SI", 'I', Items.field_151042_j, 'S', Items.field_151097_aZ, 'P', Blocks.field_150344_f}
      );
      GameRegistry.addRecipe(
         new ItemStack(ItemsJRMC.Custom_fabric, 1), new Object[]{" SW", " W ", "W  ", 'S', Items.field_151007_F, 'W', Blocks.field_150325_L}
      );
      GameRegistry.addRecipe(
         new ItemStack(ItemsJRMC.Costume_creeper, 1),
         new Object[]{"F F", "WWW", "WWW", 'F', ItemsJRMC.Custom_fabric, 'W', new ItemStack(Blocks.field_150325_L, 1, 13)}
      );
      GameRegistry.addRecipe(
         new ItemStack(ItemsJRMC.Costume_ender, 1),
         new Object[]{"F F", "WWW", "WWW", 'F', ItemsJRMC.Custom_fabric, 'W', new ItemStack(Blocks.field_150325_L, 1, 15)}
      );
      String[] recept_1 = new String[]{" R ", "R R", "RRR", "RRR", "   "};
      String[] recept_2 = new String[]{"RFR", "RFR", "RFR", "RFR", "WFW"};
      String[] recept_3 = new String[]{" W ", "WWW", "WWW", "W W", "D D"};

      for (int i = 0; i < ItemsJRMC.ItemVanity3.length; i++) {
         GameRegistry.addRecipe(
            new ItemStack(ItemsJRMC.ItemsVanity[i], 1),
            new Object[]{
               recept_1[i],
               recept_2[i],
               recept_3[i],
               'F',
               ItemsJRMC.Custom_fabric,
               'R',
               new ItemStack(Blocks.field_150325_L, 1, 14),
               'W',
               new ItemStack(Blocks.field_150325_L, 1, 0),
               'D',
               new ItemStack(Blocks.field_150325_L, 1, 15)
            }
         );
      }
   }
}
