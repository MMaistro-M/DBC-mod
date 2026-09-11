/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 */
package JinRyuu.JYearsC;

import JinRyuu.JYearsC.ItemWatch;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class JYearsCItems {
    public static Item ItemWatch;

    public static void init() {
        ItemWatch = GameRegistry.registerItem((Item)new ItemWatch().func_77655_b("ItemWatch"), (String)"ItemWatch", null);
    }
}

