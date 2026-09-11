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
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.items.AbstractModItemArmour;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RecipeSkinUpdate
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting invCrafting, World world) {
        ItemStack oldSkinStack = null;
        for (int slotId = 0; slotId < invCrafting.func_70302_i_(); ++slotId) {
            ItemStack stack = invCrafting.func_70301_a(slotId);
            if (stack == null) continue;
            Item item = stack.func_77973_b();
            if (SkinNBTHelper.stackHasLegacySkinData(stack)) {
                if (oldSkinStack != null) {
                    return false;
                }
                oldSkinStack = stack;
                continue;
            }
            return false;
        }
        return oldSkinStack != null;
    }

    public ItemStack func_77572_b(InventoryCrafting invCrafting) {
        ItemStack oldSkinStack = null;
        for (int slotId = 0; slotId < invCrafting.func_70302_i_(); ++slotId) {
            ItemStack stack = invCrafting.func_70301_a(slotId);
            if (stack == null) continue;
            Item item = stack.func_77973_b();
            if (SkinNBTHelper.stackHasLegacySkinData(stack)) {
                if (oldSkinStack != null) {
                    return null;
                }
                oldSkinStack = stack;
                continue;
            }
            return null;
        }
        if (oldSkinStack != null) {
            int skinId = SkinNBTHelper.getLegacyIdFromStack(oldSkinStack);
            ISkinType skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromLegacyId(oldSkinStack.func_77960_j());
            SkinPointer skinPointer = new SkinPointer(new SkinIdentifier(skinId, null, 0, skinType), false);
            if (oldSkinStack.func_77973_b() instanceof AbstractModItemArmour) {
                return SkinNBTHelper.makeArmouerContainerStack(skinPointer);
            }
            return SkinNBTHelper.makeEquipmentSkinStack(skinPointer);
        }
        return null;
    }

    public int func_77570_a() {
        return 1;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

