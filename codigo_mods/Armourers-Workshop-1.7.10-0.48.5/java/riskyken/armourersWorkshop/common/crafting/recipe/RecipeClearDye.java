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
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RecipeClearDye
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting invCrafting, World world) {
        return this.func_77572_b(invCrafting) != null;
    }

    public ItemStack func_77572_b(InventoryCrafting invCrafting) {
        ItemStack skinStack = null;
        ItemStack soapStack = null;
        for (int slotId = 0; slotId < invCrafting.func_70302_i_(); ++slotId) {
            ItemStack stack = invCrafting.func_70301_a(slotId);
            if (stack == null) continue;
            Item item = stack.func_77973_b();
            if (item == ModItems.soap) {
                if (soapStack != null) {
                    return null;
                }
                soapStack = stack;
                continue;
            }
            if (item != ModItems.equipmentSkin) continue;
            if (skinStack != null) {
                return null;
            }
            if (SkinNBTHelper.stackHasSkinData(stack)) {
                skinStack = stack;
                continue;
            }
            return null;
        }
        if (skinStack != null && soapStack != null) {
            ItemStack returnStack = skinStack.func_77946_l();
            SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(returnStack);
            ISkinDye dye = skinPointer.getSkinDye();
            for (int i = 0; i < 8; ++i) {
                dye.removeDye(i);
            }
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

