/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 */
package me.NBArmors.items;

import cpw.mods.fml.common.registry.GameRegistry;
import me.NBArmors.items.ItemHeadNB;
import me.NBArmors.tabs.NBTab;
import net.minecraft.item.Item;

public class NewScouter {
    public static Item BScoutern;
    public static Item GScoutern;
    public static Item PiScoutern;
    public static Item PScoutern;
    public static Item RScoutern;
    public static Item YScoutern;

    public static void mainRegistry() {
        NewScouter.initiliseItem();
        NewScouter.registerItem();
    }

    public static void initiliseItem() {
    }

    public static void registerItem() {
        BScoutern = GameRegistry.registerItem((Item)new ItemHeadNB("BScoutern", "3").func_77655_b("BScoutern").func_77637_a(NBTab.scoutern), (String)"BScoutern", null);
        GScoutern = GameRegistry.registerItem((Item)new ItemHeadNB("GScoutern", "3").func_77655_b("GScoutern").func_77637_a(NBTab.scoutern), (String)"GScoutern", null);
        PiScoutern = GameRegistry.registerItem((Item)new ItemHeadNB("PiScoutern", "3").func_77655_b("PiScoutern").func_77637_a(NBTab.scoutern), (String)"PiScoutern", null);
        PScoutern = GameRegistry.registerItem((Item)new ItemHeadNB("PScoutern", "3").func_77655_b("PScoutern").func_77637_a(NBTab.scoutern), (String)"PScoutern", null);
        RScoutern = GameRegistry.registerItem((Item)new ItemHeadNB("RScoutern", "3").func_77655_b("RScoutern").func_77637_a(NBTab.scoutern), (String)"RScoutern", null);
        YScoutern = GameRegistry.registerItem((Item)new ItemHeadNB("YScoutern", "3").func_77655_b("YScoutern").func_77637_a(NBTab.scoutern), (String)"YScoutern", null);
    }
}

