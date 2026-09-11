/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.crafting.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public abstract class RecipeItemSkinning {
    protected ISkinType skinType;

    public RecipeItemSkinning(ISkinType skinType) {
        this.skinType = skinType;
    }

    public abstract boolean matches(IInventory var1);

    public abstract ItemStack getCraftingResult(IInventory var1);

    public abstract void onCraft(IInventory var1);

    protected boolean isValidSkinForType(ItemStack stack) {
        return stack.func_77973_b() == ModItems.equipmentSkin && SkinNBTHelper.stackHasSkinData(stack) && SkinNBTHelper.getSkinTypeFromStack(stack) == this.skinType;
    }
}

