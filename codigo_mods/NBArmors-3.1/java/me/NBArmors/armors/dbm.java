/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 */
package me.NBArmors.armors;

import cpw.mods.fml.common.registry.GameRegistry;
import me.NBArmors.armors.ArmorNB;
import me.NBArmors.tabs.NBTab;
import net.minecraft.item.Item;

public class dbm {
    public static Item bra_chest;
    public static Item bra_legs;
    public static Item bra_boots;
    public static Item dbm0_chest;
    public static Item dbm0_legs;
    public static Item dbm0_boots;
    public static Item gast_chest;
    public static Item gast_legs;
    public static Item gast_boots;

    public static void mainRegistry() {
        dbm.initiliseItem();
        dbm.registerItem();
    }

    public static void initiliseItem() {
    }

    public static void registerItem() {
        bra_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bra").func_77655_b("bra_chest").func_77637_a(NBTab.dbm), (String)"bra_chest", null);
        bra_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "bra").func_77655_b("bra_legs").func_77637_a(NBTab.dbm), (String)"bra_legs", null);
        bra_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "bra").func_77655_b("bra_boots").func_77637_a(NBTab.dbm), (String)"bra_boots", null);
        dbm0_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "dbm0").func_77655_b("dbm0_chest").func_77637_a(NBTab.dbm), (String)"dbm0_chest", null);
        dbm0_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "dbm0").func_77655_b("dbm0_legs").func_77637_a(NBTab.dbm), (String)"dbm0_legs", null);
        dbm0_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "dbm0").func_77655_b("dbm0_boots").func_77637_a(NBTab.dbm), (String)"dbm0_boots", null);
        gast_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "gast").func_77655_b("gast_chest").func_77637_a(NBTab.dbm), (String)"gast_chest", null);
        gast_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "gast").func_77655_b("gast_legs").func_77637_a(NBTab.dbm), (String)"gast_legs", null);
        gast_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "gast").func_77655_b("gast_boots").func_77637_a(NBTab.dbm), (String)"gast_boots", null);
    }
}

