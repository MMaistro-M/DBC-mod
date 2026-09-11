/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 */
package com.tobiasmjc.dbcadditions.items;

import com.tobiasmjc.dbcadditions.items.ItemFeast;
import com.tobiasmjc.dbcadditions.items.ItemPotara;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemsDBCUtils {
    public static final ItemFeast feastTier1 = new ItemFeast(8, 1);
    public static final ItemFeast feastTier2 = new ItemFeast(11, 2);
    public static final ItemFeast feastTier3 = new ItemFeast(14, 3);
    public static final ItemPotara potara_yellow = new ItemPotara("yellow");
    public static final ItemPotara potara_blue = new ItemPotara("blue");
    public static final ItemPotara potara_green = new ItemPotara("green");
    public static final ItemPotara potara_pink = new ItemPotara("pink");
    public static final ItemPotara potara_white = new ItemPotara("white");
    public static final ItemPotara potara_red = new ItemPotara("red");

    public static void registerAll() {
        GameRegistry.registerItem((Item)feastTier1, (String)"feastTier1");
        GameRegistry.registerItem((Item)feastTier2, (String)"feastTier2");
        GameRegistry.registerItem((Item)feastTier3, (String)"feastTier3");
        GameRegistry.registerItem((Item)potara_yellow, (String)"potara_yellow");
        GameRegistry.registerItem((Item)potara_blue, (String)"potara_blue");
        GameRegistry.registerItem((Item)potara_pink, (String)"potara_pink");
        GameRegistry.registerItem((Item)potara_green, (String)"potara_green");
        GameRegistry.registerItem((Item)potara_white, (String)"potara_white");
        GameRegistry.registerItem((Item)potara_red, (String)"potara_red");
    }
}

