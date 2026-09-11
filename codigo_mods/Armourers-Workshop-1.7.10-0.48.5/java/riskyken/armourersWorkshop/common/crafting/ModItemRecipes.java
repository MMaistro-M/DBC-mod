/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.crafting.CraftingManager;
import riskyken.armourersWorkshop.common.items.ModItems;

public final class ModItemRecipes {
    public static void init() {
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.paintbrush, 1, 0), new Object[]{"  w", " i ", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.paintRoller, 1, 0), new Object[]{" w ", " iw", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.colourPicker, 1, 0), new Object[]{" lg", "lwl", "ll ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('g'), "blockGlass", Character.valueOf('l'), Items.field_151116_aA});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.burnTool, 1, 0), new Object[]{" wd", " iw", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('d'), "dyeBlack", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.dodgeTool, 1, 0), new Object[]{" wd", " iw", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('d'), "dyeWhite", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.blendingTool, 1, 0), new Object[]{" wc", " ib", "s  ", Character.valueOf('c'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('w'), "dyeWhite", Character.valueOf('b'), "dyeBlack", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.blendingTool, 1, 0), new Object[]{" bc", " iw", "s  ", Character.valueOf('c'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('w'), "dyeWhite", Character.valueOf('b'), "dyeBlack", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.blockMarker, 1, 0), new Object[]{"  b", " c ", "b  ", Character.valueOf('c'), ModBlocks.colourable, Character.valueOf('b'), "dyeBlack"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.shadeNoiseTool, 1, 0), new Object[]{" wd", " iw", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('d'), "cobblestone", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.hueTool, 1, 0), new Object[]{" wd", " iw", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('d'), "dyeGray", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.colourNoiseTool, 1, 0), new Object[]{" wd", " iw", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('d'), Blocks.field_150341_Y, Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.mannequinTool, 1, 0), new Object[]{" wd", " iw", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('d'), "plankWood", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.equipmentSkinTemplate, 8, 0), new Object[]{"cc", "cc", Character.valueOf('c'), ModBlocks.colourable});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.dyeBottle, 1, 0), new Object[]{"gcg", "g g", "ggg", Character.valueOf('c'), ModBlocks.colourable, Character.valueOf('g'), "paneGlass"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.armourersHammer, 1, 0), new Object[]{" iw", " ii", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapedRecipe(new ItemStack(ModItems.linkingTool, 1, 0), new Object[]{" iw", " ci", "s  ", Character.valueOf('w'), ModBlocks.colourable, Character.valueOf('i'), "ingotIron", Character.valueOf('c'), Blocks.field_150486_ae, Character.valueOf('s'), "stickWood"});
        CraftingManager.addShapelessRecipe(new ItemStack(ModItems.guideBook, 1, 0), new Object[]{new ItemStack(Items.field_151122_aG, 1), new ItemStack(ModBlocks.colourable, 1)});
        CraftingManager.addShapelessRecipe(new ItemStack(ModItems.soap, 1, 0), new Object[]{new ItemStack(Items.field_151131_as, 1), new ItemStack(Items.field_151078_bh, 1), "slimeball"});
        CraftingManager.addShapelessRecipe(new ItemStack(ModItems.skinUnlock, 1, 0), new Object[]{new ItemStack((Item)Items.field_151161_ac, 1), new ItemStack(ModItems.equipmentSkinTemplate, 1), new ItemStack(Items.field_151156_bN, 1)});
        CraftingManager.addShapelessRecipe(new ItemStack(ModItems.skinUnlock, 1, 1), new Object[]{new ItemStack((Item)Items.field_151163_ad, 1), new ItemStack(ModItems.equipmentSkinTemplate, 1), new ItemStack(Items.field_151156_bN, 1)});
        CraftingManager.addShapelessRecipe(new ItemStack(ModItems.skinUnlock, 1, 2), new Object[]{new ItemStack((Item)Items.field_151173_ae, 1), new ItemStack(ModItems.equipmentSkinTemplate, 1), new ItemStack(Items.field_151156_bN, 1)});
        CraftingManager.addShapelessRecipe(new ItemStack(ModItems.skinUnlock, 1, 3), new Object[]{new ItemStack((Item)Items.field_151175_af, 1), new ItemStack(ModItems.equipmentSkinTemplate, 1), new ItemStack(Items.field_151156_bN, 1)});
        CraftingManager.addShapelessRecipe(new ItemStack(ModItems.skinUnlock, 1, 4), new Object[]{new ItemStack(Items.field_151008_G, 1), new ItemStack(ModItems.equipmentSkinTemplate, 1), new ItemStack(Items.field_151156_bN, 1)});
    }
}

