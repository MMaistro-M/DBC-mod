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

public class angels {
    public static Item daishinkan_chest;
    public static Item daishinkan_legs;
    public static Item daishinkan_boots;
    public static Item zeno_chest;
    public static Item zeno_legs;
    public static Item zeno_boots;
    public static Item guardzeno_chest;
    public static Item guardzeno_legs;
    public static Item guardzeno_boots;
    public static ArrayList<Item> ItemAngels;
    public static final String[] ItemsAngelsType;
    public static Item[] ItemsAngel1;
    public static Item[] ItemsAngel2;
    public static Item[] ItemsAngel3;

    public static void mainRegistry() {
        angels.initiliseItem();
        angels.registerItem();
    }

    public static void initiliseItem() {
    }

    public static void registerItem() {
        daishinkan_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "daishinkan").func_77655_b("daishinkan_boots").func_77637_a(NBTab.angels), (String)"daishinkan_boots", null);
        daishinkan_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "daishinkan").func_77655_b("daishinkan_chest").func_77637_a(NBTab.angels), (String)"daishinkan_chest", null);
        daishinkan_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "daishinkan").func_77655_b("daishinkan_legs").func_77637_a(NBTab.angels), (String)"daishinkan_legs", null);
        guardzeno_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "guardzeno").func_77655_b("guardzeno_boots").func_77637_a(NBTab.angels), (String)"guardzeno_boots", null);
        guardzeno_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "guardzeno").func_77655_b("guardzeno_chest").func_77637_a(NBTab.angels), (String)"guardzeno_chest", null);
        guardzeno_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "guardzeno").func_77655_b("guardzeno_legs").func_77637_a(NBTab.angels), (String)"guardzeno_legs", null);
        zeno_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "zeno").func_77655_b("zeno_boots").func_77637_a(NBTab.angels), (String)"zeno_boots", null);
        zeno_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "zeno").func_77655_b("zeno_chest").func_77637_a(NBTab.angels), (String)"zeno_chest", null);
        zeno_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "zeno").func_77655_b("zeno_legs").func_77637_a(NBTab.angels), (String)"zeno_legs", null);
        for (int i = 1; i < 13; ++i) {
            String[] s = ItemsAngelsType[i].split(",");
            block6: for (int j = 0; j < s.length; ++j) {
                int id = Integer.parseInt(s[j]);
                switch (id) {
                    case 1: {
                        angels.ItemsAngel1[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "angel" + i).func_77655_b("angel" + i + "_chest").func_77637_a(NBTab.angels), (String)("angel" + i + "_chest"), null);
                        ItemAngels.add(angels.ItemsAngel1[i]);
                        continue block6;
                    }
                    case 2: {
                        angels.ItemsAngel2[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "angel" + i).func_77655_b("angel" + i + "_legs").func_77637_a(NBTab.angels), (String)("angel" + i + "_legs"), null);
                        ItemAngels.add(angels.ItemsAngel2[i]);
                        continue block6;
                    }
                    case 3: {
                        angels.ItemsAngel3[i] = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "angel" + i).func_77655_b("angel" + i + "_boots").func_77637_a(NBTab.angels), (String)("angel" + i + "_boots"), null);
                        ItemAngels.add(angels.ItemsAngel3[i]);
                    }
                }
            }
        }
    }

    static {
        ItemAngels = new ArrayList();
        ItemsAngelsType = new String[]{"1,2,3", "1,2,3", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2", "1,2"};
        ItemsAngel1 = new Item[13];
        ItemsAngel2 = new Item[13];
        ItemsAngel3 = new Item[13];
    }
}

