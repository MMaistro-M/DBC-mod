/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.crafting.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RecipeSkinDye
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting invCrafting, World world) {
        return this.func_77572_b(invCrafting) != null;
    }

    public ItemStack func_77572_b(InventoryCrafting invCrafting) {
        ItemStack skinStack = null;
        ItemStack dyeStack = null;
        for (int slotId = 0; slotId < invCrafting.func_70302_i_(); ++slotId) {
            ItemStack stack = invCrafting.func_70301_a(slotId);
            if (stack == null) continue;
            Item item = stack.func_77973_b();
            if (item == ModItems.dyeBottle) {
                if (dyeStack != null) {
                    return null;
                }
                if (PaintingHelper.getToolHasPaint(stack)) {
                    dyeStack = stack;
                    continue;
                }
                return null;
            }
            if (item != ModItems.equipmentSkin) continue;
            if (skinStack != null) {
                return null;
            }
            SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
            if (skinPointer != null) {
                if (skinPointer.getSkinDye().getNumberOfDyes() < 8) {
                    skinStack = stack;
                    continue;
                }
                return null;
            }
            return null;
        }
        if (skinStack != null && dyeStack != null) {
            ItemStack returnStack = skinStack.func_77946_l();
            byte[] rgbt = PaintingHelper.getToolPaintData(dyeStack);
            SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(returnStack);
            ISkinDye dye = skinPointer.getSkinDye();
            dye.addDye(rgbt);
            SkinNBTHelper.addSkinDataToStack(returnStack, skinPointer);
            return returnStack;
        }
        return null;
    }

    public int func_77570_a() {
        return 2;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

