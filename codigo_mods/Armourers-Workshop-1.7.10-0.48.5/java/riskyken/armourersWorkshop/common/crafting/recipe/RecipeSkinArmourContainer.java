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
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.crafting.recipe.RecipeItemSkinning;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RecipeSkinArmourContainer
extends RecipeItemSkinning {
    public RecipeSkinArmourContainer(ISkinType skinType) {
        super(skinType);
    }

    @Override
    public boolean matches(IInventory inventory) {
        return this.getCraftingResult(inventory) != null;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inventory) {
        ItemStack skinStack = null;
        ItemStack armourStack = null;
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
            if (stack.func_77973_b() == ModItems.armourContainerItem) {
                if (armourStack != null) {
                    return null;
                }
                armourStack = stack;
                continue;
            }
            return null;
        }
        if (skinStack != null && armourStack != null) {
            SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(skinStack);
            ItemStack returnStack = SkinNBTHelper.makeArmouerContainerStack(skinPointer);
            return returnStack;
        }
        return null;
    }

    private boolean isValidArmourForSkin(ItemStack armourStack, ItemStack skinStack) {
        SkinPointer sp = SkinNBTHelper.getSkinPointerFromStack(skinStack);
        ISkinType skinType = sp.getIdentifier().getSkinType();
        Item armourItem = armourStack.func_77973_b();
        return armourItem.isValidArmor(armourStack, skinType.getVanillaArmourSlotId(), null);
    }

    @Override
    public void onCraft(IInventory inventory) {
        for (int slotId = 0; slotId < inventory.func_70302_i_(); ++slotId) {
            inventory.func_70298_a(slotId, 1);
        }
    }
}

