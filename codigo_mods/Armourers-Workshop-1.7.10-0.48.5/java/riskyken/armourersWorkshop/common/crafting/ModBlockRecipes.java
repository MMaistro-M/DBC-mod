/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.crafting.CraftingManager;
import riskyken.armourersWorkshop.common.items.ModItems;

public final class ModBlockRecipes {
    public static void init() {
        CraftingManager.addShapedRecipe(new ItemStack(ModBlocks.colourable, 16, 0), new Object[]{"www", "wiw", "www", Character.valueOf('w'), Blocks.field_150325_L, Character.valueOf('i'), "ingotIron"});
        CraftingManager.addShapedRecipe(new ItemStack(ModBlocks.armourLibrary, 1, 0), new Object[]{"srs", "bcb", "sss", Character.valueOf('r'), new ItemStack(Blocks.field_150325_L, 1, 14), Character.valueOf('s'), "stone", Character.valueOf('c'), ModBlocks.colourable, Character.valueOf('b'), Items.field_151122_aG});
        CraftingManager.addShapedRecipe(new ItemStack(ModBlocks.globalSkinLibrary, 1, 0), new Object[]{"srs", "bcb", "sss", Character.valueOf('r'), Items.field_151079_bi, Character.valueOf('s'), "stone", Character.valueOf('c'), ModBlocks.colourable, Character.valueOf('b'), Items.field_151122_aG});
        CraftingManager.addShapedRecipe(new ItemStack(ModBlocks.hologramProjector, 1, 0), new Object[]{"igi", "ici", "iii", Character.valueOf('i'), Items.field_151042_j, Character.valueOf('g'), "blockGlassLightBlue", Character.valueOf('c'), ModBlocks.colourable});
        CraftingManager.addShapedRecipe(new ItemStack(ModBlocks.colourMixer, 1, 0), new Object[]{"rgb", "scs", "sss", Character.valueOf('r'), "dyeRed", Character.valueOf('g'), "dyeGreen", Character.valueOf('b'), "dyeBlue", Character.valueOf('c'), ModBlocks.colourable, Character.valueOf('s'), "stone"});
        CraftingManager.addShapedRecipe(new ItemStack(ModBlocks.mannequin, 1, 0), new Object[]{" p ", "wcw", " w ", Character.valueOf('w'), "plankWood", Character.valueOf('p'), Blocks.field_150423_aK, Character.valueOf('c'), ModBlocks.colourable});
        CraftingManager.addShapedRecipe(new ItemStack(ModBlocks.skinningTable, 1, 0), new Object[]{"srs", "tct", "sss", Character.valueOf('r'), new ItemStack(Blocks.field_150325_L, 1, 14), Character.valueOf('s'), "stone", Character.valueOf('c'), ModBlocks.colourable, Character.valueOf('t'), ModItems.equipmentSkinTemplate});
        CraftingManager.addShapedRecipe(new ItemStack(ModBlocks.dyeTable, 1, 0), new Object[]{"srs", "dcd", "sss", Character.valueOf('r'), "plankWood", Character.valueOf('s'), "stone", Character.valueOf('c'), ModBlocks.colourable, Character.valueOf('d'), ModItems.dyeBottle});
        CraftingManager.addShapelessRecipe(new ItemStack(ModBlocks.colourableGlowing, 1, 0), new Object[]{new ItemStack(ModBlocks.colourable, 1), new ItemStack(Items.field_151114_aO, 1)});
        CraftingManager.addShapelessRecipe(new ItemStack(ModBlocks.colourableGlass, 1, 0), new Object[]{new ItemStack(ModBlocks.colourable, 1), "blockGlass"});
        CraftingManager.addShapelessRecipe(new ItemStack(ModBlocks.colourableGlassGlowing, 1, 0), new Object[]{new ItemStack(ModBlocks.colourableGlowing, 1), "blockGlass"});
        CraftingManager.addShapelessRecipe(new ItemStack(ModBlocks.colourable, 1, 0), new Object[]{new ItemStack(ModBlocks.colourableGlass, 1)});
        CraftingManager.addShapelessRecipe(new ItemStack(ModBlocks.colourableGlowing, 1, 0), new Object[]{new ItemStack(ModBlocks.colourableGlassGlowing, 1)});
        CraftingManager.addShapelessRecipe(new ItemStack(ModBlocks.colourableGlassGlowing, 1, 0), new Object[]{new ItemStack(ModBlocks.colourableGlass, 1), new ItemStack(Items.field_151114_aO, 1)});
    }
}

