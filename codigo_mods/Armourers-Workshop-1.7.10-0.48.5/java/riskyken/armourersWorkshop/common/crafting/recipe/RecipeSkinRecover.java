/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.crafting.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.crafting.recipe.RecipeItemSkinning;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.skin.data.SkinDye;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RecipeSkinRecover
extends RecipeItemSkinning {
    public RecipeSkinRecover() {
        super(null);
    }

    @Override
    public boolean matches(IInventory inventory) {
        return this.getCraftingResult(inventory) != null;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inventory) {
        if (!ConfigHandler.enableRecoveringSkins) {
            return null;
        }
        ItemStack skinStack = null;
        ItemStack blackStack = null;
        for (int slotId = 0; slotId < inventory.func_70302_i_(); ++slotId) {
            ItemStack stack = inventory.func_70301_a(slotId);
            if (stack == null) continue;
            Item item = stack.func_77973_b();
            if (item != ModItems.equipmentSkin && SkinNBTHelper.stackHasSkinData(stack)) {
                if (skinStack != null) {
                    return null;
                }
                skinStack = stack;
                continue;
            }
            if (item == ModItems.equipmentSkinTemplate & !SkinNBTHelper.stackHasSkinData(stack)) {
                if (blackStack != null) {
                    return null;
                }
                blackStack = stack;
                continue;
            }
            return null;
        }
        if (skinStack != null && blackStack != null) {
            ItemStack returnStack = new ItemStack(ModItems.equipmentSkin, 1);
            SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(skinStack);
            SkinNBTHelper.addSkinDataToStack(returnStack, skinData.getIdentifier(), false, new SkinDye(skinData.getSkinDye()));
            return returnStack;
        }
        return null;
    }

    @Override
    public void onCraft(IInventory inventory) {
        if (!ConfigHandler.enableRecoveringSkins) {
            return;
        }
        for (int slotId = 0; slotId < inventory.func_70302_i_(); ++slotId) {
            ItemStack stack = inventory.func_70301_a(slotId);
            Item item = stack.func_77973_b();
            if (!(item == ModItems.equipmentSkinTemplate & !SkinNBTHelper.stackHasSkinData(stack))) continue;
            inventory.func_70298_a(slotId, 1);
        }
    }
}

