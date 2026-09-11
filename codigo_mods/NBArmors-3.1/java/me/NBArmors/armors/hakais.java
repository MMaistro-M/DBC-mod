/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 */
package me.NBArmors.armors;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import me.NBArmors.armors.ArmorNB;
import me.NBArmors.tabs.NBTab;
import net.minecraft.item.Item;

public class hakais {
    public static ArrayList<Item> ItemHakai = new ArrayList();
    public static final String[] ItemsHakaiType = new String[]{"0,1,2,3", "0,1,2,3", "0,1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2,3", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2"};
    public static Item[] ItemsHakai0 = new Item[13];
    public static Item[] ItemsHakai1 = new Item[13];
    public static Item[] ItemsHakai2 = new Item[13];
    public static Item[] ItemsHakai3 = new Item[13];

    public static void mainRegistry() {
        hakais.initiliseItem();
        hakais.registerItem();
    }

    public static void initiliseItem() {
    }

    public static void registerItem() {
        for (int i = 1; i < 13; ++i) {
            String[] s = ItemsHakaiType[i].split(",");
            block7: for (int j = 0; j < s.length; ++j) {
                int id = Integer.parseInt(s[j]);
                switch (id) {
                    case 0: {
                        hakais.ItemsHakai0[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "hakai" + i).func_77655_b("hakai" + i + "_head").func_77637_a(NBTab.hakais), (String)("hakai" + i + "_head"), null);
                        ItemHakai.add(hakais.ItemsHakai0[i]);
                        continue block7;
                    }
                    case 1: {
                        hakais.ItemsHakai1[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "hakai" + i).func_77655_b("hakai" + i + "_chest").func_77637_a(NBTab.hakais), (String)("hakai" + i + "_chest"), null);
                        ItemHakai.add(hakais.ItemsHakai1[i]);
                        continue block7;
                    }
                    case 2: {
                        hakais.ItemsHakai2[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "hakai" + i).func_77655_b("hakai" + i + "_legs").func_77637_a(NBTab.hakais), (String)("hakai" + i + "_legs"), null);
                        ItemHakai.add(hakais.ItemsHakai2[i]);
                        continue block7;
                    }
                    case 3: {
                        hakais.ItemsHakai3[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "hakai" + i).func_77655_b("hakai" + i + "_boots").func_77637_a(NBTab.hakais), (String)("hakai" + i + "_boots"), null);
                        ItemHakai.add(hakais.ItemsHakai3[i]);
                    }
                }
            }
        }
    }
}

