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
import me.NBArmors.items.ItemHeadNB;
import me.NBArmors.tabs.NBTab;
import net.minecraft.item.Item;

public class supkai {
    public static Item fzamasu_boots;
    public static Item fzamasu_chest;
    public static Item fzamasu_legs;
    public static Item supkaitime1_boots;
    public static Item supkaitime1_chest;
    public static Item supkaitime1_legs;
    public static Item supkaitime2_boots;
    public static Item supkaitime2_chest;
    public static Item supkaitime2_legs;
    public static Item supkaitimepin;
    public static Item[] ItemsSupkai1;
    public static Item[] ItemsSupkai2;
    public static Item[] ItemsSupkai3;
    public static ArrayList<Item> ItemSupkai;
    public static final String[] ItemsSupkaiType;

    public static void mainRegistry() {
        supkai.initiliseItem();
        supkai.registerItem();
    }

    public static void initiliseItem() {
    }

    public static void registerItem() {
        fzamasu_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "fzamasu").func_77655_b("fzamasu_chest").func_77637_a(NBTab.supkai), (String)"fzamasu_chest", null);
        fzamasu_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "fzamasu").func_77655_b("fzamasu_legs").func_77637_a(NBTab.supkai), (String)"fzamasu_legs", null);
        fzamasu_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "fzamasu").func_77655_b("fzamasu_boots").func_77637_a(NBTab.supkai), (String)"fzamasu_boots", null);
        supkaitime1_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "supkaitime1").func_77655_b("supkaitime1_chest").func_77637_a(NBTab.supkai), (String)"supkaitime1_chest", null);
        supkaitime1_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "supkaitime1").func_77655_b("supkaitime1_legs").func_77637_a(NBTab.supkai), (String)"supkaitime1_legs", null);
        supkaitime1_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "supkaitime1").func_77655_b("supkaitime1_boots").func_77637_a(NBTab.supkai), (String)"supkaitime1_boots", null);
        supkaitimepin = GameRegistry.registerItem((Item)new ItemHeadNB("supkaitimepin", "3").func_77655_b("supkaitimepin").func_77637_a(NBTab.supkai), (String)"supkaitimepin", null);
        supkaitime2_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "supkaitime2").func_77655_b("supkaitime2_chest").func_77637_a(NBTab.supkai), (String)"supkaitime2_chest", null);
        supkaitime2_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "supkaitime2").func_77655_b("supkaitime2_legs").func_77637_a(NBTab.supkai), (String)"supkaitime2_legs", null);
        supkaitime2_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "supkaitime2").func_77655_b("supkaitime2_boots").func_77637_a(NBTab.supkai), (String)"supkaitime2_boots", null);
        for (int i = 1; i < 13; ++i) {
            String[] s = ItemsSupkaiType[i].split(",");
            block6: for (int j = 0; j < s.length; ++j) {
                int id = Integer.parseInt(s[j]);
                switch (id) {
                    case 1: {
                        supkai.ItemsSupkai1[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "supkai" + i).func_77655_b("supkai" + i + "_chest").func_77637_a(NBTab.supkai), (String)("supkai" + i + "_chest"), null);
                        ItemSupkai.add(supkai.ItemsSupkai1[i]);
                        continue block6;
                    }
                    case 2: {
                        supkai.ItemsSupkai2[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "supkai" + i).func_77655_b("supkai" + i + "_legs").func_77637_a(NBTab.supkai), (String)("supkai" + i + "_legs"), null);
                        ItemSupkai.add(supkai.ItemsSupkai2[i]);
                        continue block6;
                    }
                    case 3: {
                        supkai.ItemsSupkai3[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "supkai" + i).func_77655_b("supkai" + i + "_boots").func_77637_a(NBTab.supkai), (String)("supkai" + i + "_boots"), null);
                        ItemSupkai.add(supkai.ItemsSupkai3[i]);
                    }
                }
            }
        }
    }

    static {
        ItemsSupkai1 = new Item[13];
        ItemsSupkai2 = new Item[13];
        ItemsSupkai3 = new Item[13];
        ItemSupkai = new ArrayList();
        ItemsSupkaiType = new String[]{"1,2,3", "1,2,3", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2,3", "1,2", "1,2", "1,2", "1,2", "1,2,3"};
    }
}

