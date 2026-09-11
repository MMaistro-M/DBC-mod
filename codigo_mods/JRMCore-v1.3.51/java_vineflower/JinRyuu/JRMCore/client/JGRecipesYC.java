package JinRyuu.JRMCore.client;

import JinRyuu.JYearsC.JYearsCItems;

public class JGRecipesYC {
   public static void registerRecipes(String mod, String category) {
      mod = "Years C";
      category = "Items";
      JGRecipeHandler.addRecipe(mod, category, JYearsCItems.ItemWatch);
   }
}
