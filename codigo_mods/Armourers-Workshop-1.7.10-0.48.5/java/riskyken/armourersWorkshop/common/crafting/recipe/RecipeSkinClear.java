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
import riskyken.armourersWorkshop.common.crafting.recipe.RecipeItemSkinning;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RecipeSkinClear
extends RecipeItemSkinning {
    public RecipeSkinClear() {
        super(null);
    }

    @Override
    public boolean matches(IInventory inventory) {
        return this.getCraftingResult(inventory) != null;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inventory) {
        ItemStack skinItemStack = null;
        ItemStack soapStack = null;
        for (int slotId = 0; slotId < inventory.func_70302_i_(); ++slotId) {
            ItemStack stack = inventory.func_70301_a(slotId);
            if (stack == null) continue;
            Item item = stack.func_77973_b();
            if (item != ModItems.equipmentSkin && SkinNBTHelper.stackHasSkinData(stack) && SkinNBTHelper.getSkinPointerFromStack((ItemStack)stack).lockSkin) {
                if (skinItemStack != null) {
                    return null;
                }
                skinItemStack = stack;
                continue;
            }
            if (item == ModItems.soap) {
                if (soapStack != null) {
                    return null;
                }
                soapStack = stack;
                continue;
            }
            return null;
        }
        if (skinItemStack != null && soapStack != null) {
            ItemStack returnStack = skinItemStack.func_77946_l();
            SkinNBTHelper.removeSkinDataFromStack(returnStack, true);
            return returnStack;
        }
        return null;
    }

    @Override
    public void onCraft(IInventory inventory) {
        for (int slotId = 0; slotId < inventory.func_70302_i_(); ++slotId) {
            ItemStack stack = inventory.func_70301_a(slotId);
            if (stack.func_77973_b() == ModItems.soap) continue;
            inventory.func_70298_a(slotId, 1);
        }
    }
}

