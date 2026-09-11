/*
 * Decompiled with CFR 0.152.
 */
package JinRyuu.JRMCore.client;

import JinRyuu.JRMCore.client.JGRecipeHandler;
import JinRyuu.JYearsC.JYearsCItems;

public class JGRecipesYC {
    public static void registerRecipes(String mod, String category) {
        mod = "Years C";
        category = "Items";
        JGRecipeHandler.addRecipe(mod, category, JYearsCItems.ItemWatch);
    }
}

