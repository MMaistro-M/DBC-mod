/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.items.ItemsJRMC;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipesJRMC {
    public static void init() {
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsJRMC.ItemBarberSnC, 1), (Object[])new Object[]{"IP", "SI", Character.valueOf('I'), Items.field_151042_j, Character.valueOf('S'), Items.field_151097_aZ, Character.valueOf('P'), Blocks.field_150344_f});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsJRMC.Custom_fabric, 1), (Object[])new Object[]{" SW", " W ", "W  ", Character.valueOf('S'), Items.field_151007_F, Character.valueOf('W'), Blocks.field_150325_L});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsJRMC.Costume_creeper, 1), (Object[])new Object[]{"F F", "WWW", "WWW", Character.valueOf('F'), ItemsJRMC.Custom_fabric, Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 13)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsJRMC.Costume_ender, 1), (Object[])new Object[]{"F F", "WWW", "WWW", Character.valueOf('F'), ItemsJRMC.Custom_fabric, Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 15)});
        String[] recept_1 = new String[]{" R ", "R R", "RRR", "RRR", "   "};
        String[] recept_2 = new String[]{"RFR", "RFR", "RFR", "RFR", "WFW"};
        String[] recept_3 = new String[]{" W ", "WWW", "WWW", "W W", "D D"};
        for (int i = 0; i < ItemsJRMC.ItemVanity3.length; ++i) {
            GameRegistry.addRecipe((ItemStack)new ItemStack(ItemsJRMC.ItemsVanity[i], 1), (Object[])new Object[]{recept_1[i], recept_2[i], recept_3[i], Character.valueOf('F'), ItemsJRMC.Custom_fabric, Character.valueOf('R'), new ItemStack(Blocks.field_150325_L, 1, 14), Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 0), Character.valueOf('D'), new ItemStack(Blocks.field_150325_L, 1, 15)});
        }
    }
}

