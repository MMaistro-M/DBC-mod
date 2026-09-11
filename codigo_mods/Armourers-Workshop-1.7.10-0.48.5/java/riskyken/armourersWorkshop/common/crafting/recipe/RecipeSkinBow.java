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
import riskyken.armourersWorkshop.common.addons.ModAddon;
import riskyken.armourersWorkshop.common.crafting.recipe.RecipeItemSkinning;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentDataManager;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RecipeSkinBow
extends RecipeItemSkinning {
    public RecipeSkinBow() {
        super(SkinTypeRegistry.skinBow);
    }

    @Override
    public boolean matches(IInventory inventory) {
        return this.getCraftingResult(inventory) != null;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inventory) {
        ItemStack skinStack = null;
        ItemStack bowStack = null;
        for (int slotId = 0; slotId < inventory.func_70302_i_(); ++slotId) {
            ItemStack stack = inventory.func_70301_a(slotId);
            if (stack == null) continue;
            Item item = stack.func_77973_b();
            if (this.isValidSkinForType(stack)) {
                if (skinStack != null) {
                    return null;
                }
                skinStack = stack;
                continue;
            }
            if (EntityEquipmentDataManager.INSTANCE.isRenderItem(ModAddon.ItemOverrideType.BOW, item) & !SkinNBTHelper.isSkinLockedOnStack(stack)) {
                if (bowStack != null) {
                    return null;
                }
                bowStack = stack;
                continue;
            }
            return null;
        }
        if (skinStack != null && bowStack != null) {
            ItemStack returnStack = bowStack.func_77946_l();
            SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(skinStack);
            SkinNBTHelper.addSkinDataToStack(returnStack, skinData.getIdentifier(), skinData.getSkinDye(), true);
            return returnStack;
        }
        return null;
    }

    @Override
    public void onCraft(IInventory inventory) {
        for (int slotId = 0; slotId < inventory.func_70302_i_(); ++slotId) {
            inventory.func_70298_a(slotId, 1);
        }
    }
}

