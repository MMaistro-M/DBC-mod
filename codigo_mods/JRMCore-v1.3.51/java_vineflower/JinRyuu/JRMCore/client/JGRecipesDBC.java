package JinRyuu.JRMCore.client;

import JinRyuu.DragonBC.common.RecipesDBC;
import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import net.minecraft.item.Item;

public class JGRecipesDBC {
   public static void registerRecipes(String mod, String category) {
      mod = "Dragon Block C";
      category = "Blocks";
      JGRecipeHandler.addRecipe(mod, category, BlocksDBC.ArtGravDevBlock);
      JGRecipeHandler.addRecipe(mod, category, BlocksDBC.BlockNamekStone);

      for (int i = 0; i < BlocksDBC.BlockOWnams.length; i++) {
         JGRecipeHandler.addRecipe(mod, category, BlocksDBC.BlockOW[i]);
      }

      JGRecipeHandler.addRecipe(mod, category, BlocksDBC.BlockKachin[0]);
      JGRecipeHandler.addRecipe(mod, category, BlocksDBC.BlockKachin[1]);
      JGRecipeHandler.addRecipe(mod, category, BlocksDBC.BlockKachin[2]);
      category = "Items";
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemAlienTechChipTier1);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemChipTier2);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemChipTier3);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemWarenaiFabric);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemStrongFabric);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemKatchin);
      category = "Armors";
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorHelmet00);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorHelmet01);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorHelmet02);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorHelmet03);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorHelmet04);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorHelmet05);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorChest00);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorChest01);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorChest02);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorChest03);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorChest04);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorChest05);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorLegs00);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorLegs01);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorLegs02);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorLegs03);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorLegs04);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorLegs05);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorBoots00);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorBoots01);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorBoots02);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorBoots03);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorBoots04);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.BattleArmorBoots05);

      for (int i = 0; i < RecipesDBC.lesz.length; i++) {
         JGRecipeHandler.addRecipe(mod, category, RecipesDBC.lesz[i]);
      }

      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFighter3Torso);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.KameTorso1);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.KameBoots1);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFighter1Torso);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFighter1Leg);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFighter1Boots);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiNamekTorso);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiNamekLeg);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiNamekBoots);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFutureTorso);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFutureLeg);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFutureBoots);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFighter2Torso);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFighter2Leg);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.GiFighter2Boots);

      for (int i = 0; i < ItemsDBC.ItemBodysuits.length; i++) {
         JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemBodysuits[i]);
      }

      for (int i = 0; i < 3; i++) {
         JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[i * 4 + 13]);
         JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[i * 4 + 14]);
         JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[i * 4 + 15]);
         JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[i * 4 + 16]);
      }

      category = "Weapons";
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemKatana);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemBraveSword);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemZSword);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemKatanaHandle);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemKatanaSwordBlade);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemBraveSwordBlade);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemBraveSwordHandle);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemZSwordBlade);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemZSwordHandle);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemDimensionSword);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[5]);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[4]);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[6]);
      category = "Tools";
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.SpacePod01Item);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemBucketMedLiq);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemDragonRadar);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[11]);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[8]);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[10]);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[7]);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[12]);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemsOW[9]);
      category = "Vanities";

      for (int i = 0; i < ItemsDBC.ItemGiBody.length; i++) {
         JGRecipeHandler.addRecipe(mod, category, ItemsDBC.ItemGiBody[i]);
      }

      for (int i = 0; i < 12; i++) {
         for (int j = 0; j < 4; j++) {
            if (ItemsDBC.ItemsGiType[i].contains("" + j)) {
               Item item;
               if (j == 0) {
                  item = ItemsDBC.ItemsGi0[i];
               } else if (j == 1) {
                  item = ItemsDBC.ItemsGi1[i];
               } else if (j == 2) {
                  item = ItemsDBC.ItemsGi2[i];
               } else {
                  item = ItemsDBC.ItemsGi3[i];
               }

               JGRecipeHandler.addRecipe(mod, category, item);
            }
         }
      }

      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.Coat);
      JGRecipeHandler.addRecipe(mod, category, ItemsDBC.Coat_2);

      for (int i = 0; i < 2; i++) {
         int j = 1;
         if (ItemsDBC.ItemsGiType2[i].contains("" + j)) {
            Item item = ItemsDBC.ItemsGi1_2[i];
            JGRecipeHandler.addRecipe(mod, category, item);
         }
      }
   }
}
