/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package me.NBArmors.main;

import cpw.mods.fml.common.registry.GameRegistry;
import me.NBArmors.armors.MarksNB;
import me.NBArmors.config.ConfigNB;
import me.NBArmors.items.CItems;
import me.NBArmors.items.FabricCoreColor;
import me.NBArmors.items.ItemBeardNB;
import me.NBArmors.items.ItemBodyNB;
import me.NBArmors.items.ItemVanityNB;
import me.NBArmors.items.TrenchCoatNB;
import me.NBArmors.main.vanitycolorex;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RecipeNB {
    public static void mainRegistry() {
        RecipeNB.initShapedRecipes();
        RecipeNB.initShapelessecipes();
        RecipeNB.initSmeltingsecipes();
    }

    public static void initShapedRecipes() {
        int i;
        if (!ConfigNB.Recipe) {
            GameRegistry.addRecipe((ItemStack)new ItemStack(CItems.FabricCore, 3), (Object[])new Object[]{"WWW", "CCC", "III", Character.valueOf('W'), Blocks.field_150325_L, Character.valueOf('C'), CItems.Core, Character.valueOf('I'), Items.field_151042_j});
            for (i = 0; i < 16; ++i) {
                GameRegistry.addRecipe((ItemStack)new ItemStack(CItems.FabricCoreColor, 1, FabricCoreColor.func_150031_c(i)), (Object[])new Object[]{"XXX", "X#X", "XXX", Character.valueOf('#'), new ItemStack(CItems.FabricCore), Character.valueOf('X'), new ItemStack(Items.field_151100_aR, 1, i)});
            }
        }
        for (i = 0; i < vanitycolorex.colNams.length; ++i) {
            boolean added = false;
            ItemStack dye = new ItemStack(Items.field_151100_aR, 1, i);
            ItemStack head = new ItemStack(CItems.mulehorns);
            ItemStack body = new ItemStack(CItems.scarf);
            ItemStack tail = new ItemStack(CItems.tail);
            ItemStack ssj4 = new ItemStack(CItems.ssj4);
            ItemStack coat = new ItemStack(CItems.coat);
            ItemStack cape = new ItemStack(CItems.capec);
            ItemStack trench = new ItemStack(CItems.trenchcoat);
            ItemStack wristb = new ItemStack(CItems.wristb);
            for (int b = 1; b < 11; ++b) {
                ItemStack beard = new ItemStack(CItems.ItemsBeard[b]);
                ((ItemBeardNB)beard.func_77973_b()).setColor(beard, vanitycolorex.cols[i]);
                GameRegistry.addRecipe((ItemStack)((ItemBeardNB)beard.func_77973_b()).setColor(beard, vanitycolorex.cols[i]), (Object[])new Object[]{"DDD", "DBD", "DDD", Character.valueOf('D'), dye, Character.valueOf('B'), CItems.ItemsBeard[b]});
            }
            ((ItemVanityNB)head.func_77973_b()).setColor(head, vanitycolorex.cols[i]);
            ((MarksNB)body.func_77973_b()).setColor(body, vanitycolorex.cols[i]);
            ((ItemVanityNB)tail.func_77973_b()).setColor(tail, vanitycolorex.cols[i]);
            ((ItemBodyNB)ssj4.func_77973_b()).setColor(ssj4, vanitycolorex.cols[i]);
            ((MarksNB)coat.func_77973_b()).setColor(coat, vanitycolorex.cols[i]);
            ((MarksNB)cape.func_77973_b()).setColor(cape, vanitycolorex.cols[i]);
            ((TrenchCoatNB)trench.func_77973_b()).setColor(trench, vanitycolorex.cols[i]);
            ((MarksNB)wristb.func_77973_b()).setColor(wristb, vanitycolorex.cols[i]);
            GameRegistry.addRecipe((ItemStack)((ItemVanityNB)head.func_77973_b()).setColor(head, vanitycolorex.cols[i]), (Object[])new Object[]{"DDD", "DHD", "DDD", Character.valueOf('D'), dye, Character.valueOf('H'), CItems.mulehorns});
            GameRegistry.addRecipe((ItemStack)((MarksNB)body.func_77973_b()).setColor(body, vanitycolorex.cols[i]), (Object[])new Object[]{"DDD", "DSD", "DDD", Character.valueOf('D'), dye, Character.valueOf('S'), CItems.scarf});
            GameRegistry.addRecipe((ItemStack)((ItemVanityNB)tail.func_77973_b()).setColor(tail, vanitycolorex.cols[i]), (Object[])new Object[]{"DDD", "DTD", "DDD", Character.valueOf('D'), dye, Character.valueOf('T'), CItems.tail});
            GameRegistry.addRecipe((ItemStack)((ItemBodyNB)ssj4.func_77973_b()).setColor(ssj4, vanitycolorex.cols[i]), (Object[])new Object[]{"DDD", "DSD", "DDD", Character.valueOf('D'), dye, Character.valueOf('S'), CItems.ssj4});
            GameRegistry.addRecipe((ItemStack)((MarksNB)coat.func_77973_b()).setColor(coat, vanitycolorex.cols[i]), (Object[])new Object[]{"DDD", "DCD", "DDD", Character.valueOf('D'), dye, Character.valueOf('C'), CItems.coat});
            GameRegistry.addRecipe((ItemStack)((MarksNB)cape.func_77973_b()).setColor(cape, vanitycolorex.cols[i]), (Object[])new Object[]{"DDD", "DCD", "DDD", Character.valueOf('D'), dye, Character.valueOf('C'), CItems.capec});
            GameRegistry.addRecipe((ItemStack)((TrenchCoatNB)trench.func_77973_b()).setColor(trench, vanitycolorex.cols[i]), (Object[])new Object[]{"DDD", "DTD", "DDD", Character.valueOf('D'), dye, Character.valueOf('T'), CItems.trenchcoat});
            GameRegistry.addRecipe((ItemStack)((MarksNB)wristb.func_77973_b()).setColor(wristb, vanitycolorex.cols[i]), (Object[])new Object[]{"DDD", "DWD", "DDD", Character.valueOf('D'), dye, Character.valueOf('W'), CItems.wristb});
        }
    }

    public static void initShapelessecipes() {
    }

    public static void initSmeltingsecipes() {
    }
}

