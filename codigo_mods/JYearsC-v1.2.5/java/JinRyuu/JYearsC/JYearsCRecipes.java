/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.JYearsC;

import JinRyuu.JYearsC.JYearsCItems;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class JYearsCRecipes {
    public static void init() {
        GameRegistry.addRecipe((ItemStack)new ItemStack(JYearsCItems.ItemWatch, 1), (Object[])new Object[]{" I ", "LRL", " I ", Character.valueOf('R'), Items.field_151137_ax, Character.valueOf('I'), Items.field_151042_j, Character.valueOf('L'), Items.field_151116_aA});
    }
}

