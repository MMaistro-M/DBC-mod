/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.controllers.data.RecipeAnvil;
import noppes.npcs.controllers.data.RecipeCarpentry;

public class ContainerManageRecipes
extends Container {
    private InventoryBasic craftingMatrix;
    public RecipeCarpentry recipe;
    public RecipeAnvil recipeAnvil;
    public int size;
    public int width;
    private boolean init = false;

    public ContainerManageRecipes(EntityPlayer player, int size) {
        this.size = size * size;
        this.width = size;
        this.craftingMatrix = new InventoryBasic("crafting", false, this.size + 1);
        this.recipe = new RecipeCarpentry("");
        this.recipeAnvil = new RecipeAnvil();
        if (this.width == 1) {
            this.func_75146_a(new Slot((IInventory)this.craftingMatrix, 0, 102, 35));
        } else {
            this.func_75146_a(new Slot((IInventory)this.craftingMatrix, 0, 87, 61));
        }
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                this.func_75146_a(new Slot((IInventory)this.craftingMatrix, i * this.width + j + 1, j * 18 + 8, i * 18 + 35));
            }
        }
        for (int i1 = 0; i1 < 3; ++i1) {
            for (int l1 = 0; l1 < 9; ++l1) {
                this.func_75146_a(new Slot((IInventory)player.field_71071_by, l1 + i1 * 9 + 9, 8 + l1 * 18, 113 + i1 * 18));
            }
        }
        for (int j1 = 0; j1 < 9; ++j1) {
            this.func_75146_a(new Slot((IInventory)player.field_71071_by, j1, 8 + j1 * 18, 171));
        }
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int i) {
        return null;
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return true;
    }

    public void setRecipe(RecipeCarpentry recipe) {
        this.craftingMatrix.func_70299_a(0, recipe.func_77571_b());
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.width; ++j) {
                if (j >= recipe.field_77576_b) {
                    this.craftingMatrix.func_70299_a(i * this.width + j + 1, null);
                    continue;
                }
                this.craftingMatrix.func_70299_a(i * this.width + j + 1, recipe.getCraftingItem(i * recipe.field_77576_b + j));
            }
        }
        this.recipe = recipe;
    }

    public void setRecipe(RecipeAnvil recipe) {
        if (recipe.itemToRepair != null) {
            ItemStack output = recipe.itemToRepair.func_77946_l();
            if (output.func_77984_f()) {
                output.func_77964_b(0);
            }
            this.craftingMatrix.func_70299_a(0, output);
        } else {
            this.craftingMatrix.func_70299_a(0, null);
        }
        this.craftingMatrix.func_70299_a(1, recipe.repairMaterial);
        for (int i = 2; i < this.craftingMatrix.func_70302_i_(); ++i) {
            this.craftingMatrix.func_70299_a(i, null);
        }
        this.recipeAnvil = recipe;
    }

    public void saveRecipe() {
        if (this.width == 1) {
            ItemStack output = this.craftingMatrix.func_70301_a(0);
            ItemStack repairMat = this.craftingMatrix.func_70301_a(1);
            if (output == null || repairMat == null) {
                return;
            }
            RecipeAnvil r = new RecipeAnvil();
            r.copy(this.recipeAnvil);
            r.name = this.recipeAnvil.name;
            r.itemToRepair = output.func_77946_l();
            r.repairMaterial = repairMat.func_77946_l();
            this.recipeAnvil = RecipeAnvil.saveRecipe(r, output, repairMat);
            return;
        }
        int nextChar = 0;
        char[] chars = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'};
        HashMap<ItemStack, Character> nameMapping = new HashMap<ItemStack, Character>();
        int firstRow = this.width;
        int lastRow = 0;
        int firstColumn = this.width;
        int lastColumn = 0;
        boolean seenRow = false;
        for (int i = 0; i < this.width; ++i) {
            boolean seenColumn = false;
            for (int j = 0; j < this.width; ++j) {
                ItemStack item = this.craftingMatrix.func_70301_a(i * this.width + j + 1);
                if (item == null) continue;
                if (!seenColumn && j < firstColumn) {
                    firstColumn = j;
                }
                if (j > lastColumn) {
                    lastColumn = j;
                }
                seenColumn = true;
                Character letter = null;
                for (ItemStack mapped : nameMapping.keySet()) {
                    if (!NoppesUtilPlayer.compareItems(mapped, item, this.recipe.ignoreDamage, this.recipe.ignoreNBT)) continue;
                    letter = (Character)nameMapping.get(mapped);
                }
                if (letter != null) continue;
                letter = Character.valueOf(chars[nextChar]);
                ++nextChar;
                nameMapping.put(item, letter);
            }
            if (!seenColumn) continue;
            if (!seenRow) {
                firstRow = i;
                lastRow = i;
                seenRow = true;
                continue;
            }
            lastRow = i;
        }
        ArrayList<Object> recipeList = new ArrayList<Object>();
        for (int i = 0; i < this.width; ++i) {
            if (i < firstRow || i > lastRow) continue;
            String row = "";
            for (int j = 0; j < this.width; ++j) {
                if (j < firstColumn || j > lastColumn) continue;
                ItemStack item = this.craftingMatrix.func_70301_a(i * this.width + j + 1);
                if (item == null) {
                    row = row + " ";
                    continue;
                }
                for (ItemStack mapped : nameMapping.keySet()) {
                    if (!NoppesUtilPlayer.compareItems(mapped, item, false, false)) continue;
                    row = row + nameMapping.get(mapped);
                }
            }
            recipeList.add(row);
        }
        if (nameMapping.isEmpty()) {
            RecipeCarpentry r = new RecipeCarpentry(this.recipe.name);
            r.copy(this.recipe);
            this.recipe = r;
            return;
        }
        for (ItemStack mapped : nameMapping.keySet()) {
            Character letter = (Character)nameMapping.get(mapped);
            recipeList.add(letter);
            recipeList.add(mapped);
        }
        this.recipe = RecipeCarpentry.saveRecipe(this.recipe, this.craftingMatrix.func_70301_a(0), recipeList.toArray());
    }
}

