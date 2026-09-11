/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package riskyken.armourersWorkshop.common.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.crafting.ItemSkinningRecipes;
import riskyken.armourersWorkshop.common.crafting.ModBlockRecipes;
import riskyken.armourersWorkshop.common.crafting.ModItemRecipes;
import riskyken.armourersWorkshop.common.crafting.recipe.RecipeClearDye;
import riskyken.armourersWorkshop.common.crafting.recipe.RecipeSkinDye;
import riskyken.armourersWorkshop.common.crafting.recipe.RecipeSkinUpdate;
import riskyken.armourersWorkshop.common.handler.DollCraftingHandler;
import riskyken.armourersWorkshop.common.items.ModItems;

public final class CraftingManager {
    public static void init() {
        GameRegistry.addRecipe((IRecipe)new RecipeSkinUpdate());
        GameRegistry.addRecipe((IRecipe)new RecipeSkinDye());
        GameRegistry.addRecipe((IRecipe)new RecipeClearDye());
        RecipeSorter.register((String)"armourersworkshop:shapeless", RecipeSkinUpdate.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"after:minecraft:shapeless");
        RecipeSorter.register((String)"armourersworkshop:shapeless", RecipeSkinDye.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"after:minecraft:shapeless");
        RecipeSorter.register((String)"armourersworkshop:shapeless", RecipeClearDye.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"after:minecraft:shapeless");
        CraftingManager.hideItemsInNEI();
        if (!ConfigHandler.disableSkinningRecipes) {
            ItemSkinningRecipes.init();
        }
        if (!ConfigHandler.disableRecipes) {
            ModBlockRecipes.init();
            ModItemRecipes.init();
        }
        if (!ConfigHandler.disableDollRecipe) {
            new DollCraftingHandler();
        }
    }

    public static void addShapelessRecipe(ItemStack result, Object[] recipe) {
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(result, recipe));
    }

    public static void addShapedRecipe(ItemStack result, Object[] recipe) {
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(result, recipe));
    }

    public static void hideItemsInNEI() {
        if (ConfigHandler.hideDollFromCreativeTabs) {
            CraftingManager.hideItemInNEI(new ItemStack(ModBlocks.doll, 1));
        }
        CraftingManager.hideItemInNEI(new ItemStack(ModBlocks.boundingBox, 1));
        CraftingManager.hideItemInNEI(new ItemStack(ModItems.armourContainer[0], 1));
        CraftingManager.hideItemInNEI(new ItemStack(ModItems.armourContainer[1], 1));
        CraftingManager.hideItemInNEI(new ItemStack(ModItems.armourContainer[2], 1));
        CraftingManager.hideItemInNEI(new ItemStack(ModItems.armourContainer[3], 1));
        CraftingManager.hideItemInNEI(new ItemStack(ModItems.equipmentSkin, 1));
        CraftingManager.hideItemInNEI(new ItemStack(ModBlocks.skinnable, 1));
        CraftingManager.hideItemInNEI(new ItemStack(ModBlocks.skinnableGlowing, 1));
        CraftingManager.hideItemInNEI(new ItemStack(ModBlocks.skinnableChild, 1));
        CraftingManager.hideItemInNEI(new ItemStack(ModBlocks.skinnableChildGlowing, 1));
    }

    private static void hideItemInNEI(ItemStack stack) {
        ModAddonManager.addonNEI.hideItem(stack);
    }
}

