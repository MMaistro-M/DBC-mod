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
import me.NBArmors.armors.MarksNB;
import me.NBArmors.tabs.NBTab;
import net.minecraft.item.Item;

public class dbgt {
    public static Item baby_chest;
    public static Item baby_legs;
    public static Item baby_boots;
    public static Item bra1_chest;
    public static Item bra1_legs;
    public static Item bra1_boots;
    public static Item deric_chest;
    public static Item deric_legs;
    public static Item deric_boots;
    public static Item donkia_chest;
    public static Item donkia_legs;
    public static Item donkia_boots;
    public static Item drmu_chest;
    public static Item drmu_legs;
    public static Item drmu_boots;
    public static Item gohangt_chest;
    public static Item gohangt_legs;
    public static Item gohangt_boots;
    public static Item gokugt_chest;
    public static Item gokugt_legs;
    public static Item gokugt_boots;
    public static Item gotengt_chest;
    public static Item gotengt_legs;
    public static Item gotengt_boots;
    public static Item krillingt_chest;
    public static Item krillingt_legs;
    public static Item krillingt_boots;
    public static Item majinubb_chest;
    public static Item majinubb_legs;
    public static Item panbee_head;
    public static Item panbee_chest;
    public static Item panbee_legs;
    public static Item panbee_boots;
    public static Item rildo0_chest;
    public static Item rildo0_boots;
    public static Item rildo1_head;
    public static Item rildo1_chest;
    public static Item rildo1_boots;
    public static Item trunksgtjacket;
    public static Item trunksgt_chest;
    public static Item trunksgt_legs;
    public static Item trunksgt_boots;
    public static Item ubb_chest;
    public static Item ubb_legs;
    public static Item ubb_boots;
    public static Item vegetagtjacket;
    public static Item vegetagt_chest;
    public static Item vegetagt_legs;
    public static Item vegetagt_boots;

    public static void mainRegistry() {
        dbgt.initiliseItem();
        dbgt.registerItem();
    }

    public static void initiliseItem() {
    }

    public static void registerItem() {
        baby_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "baby").func_77655_b("baby_chest").func_77637_a(NBTab.dbgt), (String)"baby_chest", null);
        baby_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "baby").func_77655_b("baby_legs").func_77637_a(NBTab.dbgt), (String)"baby_legs", null);
        baby_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "baby").func_77655_b("baby_boots").func_77637_a(NBTab.dbgt), (String)"baby_boots", null);
        bra1_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bra1").func_77655_b("bra1_chest").func_77637_a(NBTab.dbgt), (String)"bra1_chest", null);
        bra1_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "bra1").func_77655_b("bra1_legs").func_77637_a(NBTab.dbgt), (String)"bra1_legs", null);
        bra1_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "bra1").func_77655_b("bra1_boots").func_77637_a(NBTab.dbgt), (String)"bra1_boots", null);
        deric_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "deric").func_77655_b("deric_chest").func_77637_a(NBTab.dbgt), (String)"deric_chest", null);
        deric_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "deric").func_77655_b("deric_legs").func_77637_a(NBTab.dbgt), (String)"deric_legs", null);
        deric_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "deric").func_77655_b("deric_boots").func_77637_a(NBTab.dbgt), (String)"deric_boots", null);
        donkia_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "donkia").func_77655_b("donkia_chest").func_77637_a(NBTab.dbgt), (String)"donkia_chest", null);
        donkia_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "donkia").func_77655_b("donkia_legs").func_77637_a(NBTab.dbgt), (String)"donkia_legs", null);
        donkia_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "donkia").func_77655_b("donkia_boots").func_77637_a(NBTab.dbgt), (String)"donkia_boots", null);
        drmu_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "drmu").func_77655_b("drmu_chest").func_77637_a(NBTab.dbgt), (String)"drmu_chest", null);
        drmu_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "drmu").func_77655_b("drmu_legs").func_77637_a(NBTab.dbgt), (String)"drmu_legs", null);
        drmu_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "drmu").func_77655_b("drmu_boots").func_77637_a(NBTab.dbgt), (String)"drmu_boots", null);
        gohangt_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "gohangt").func_77655_b("gohangt_chest").func_77637_a(NBTab.dbgt), (String)"gohangt_chest", null);
        gohangt_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "gohangt").func_77655_b("gohangt_legs").func_77637_a(NBTab.dbgt), (String)"gohangt_legs", null);
        gohangt_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "gohangt").func_77655_b("gohangt_boots").func_77637_a(NBTab.dbgt), (String)"gohangt_boots", null);
        gokugt_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "gokugt").func_77655_b("gokugt_chest").func_77637_a(NBTab.dbgt), (String)"gokugt_chest", null);
        gokugt_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "gokugt").func_77655_b("gokugt_legs").func_77637_a(NBTab.dbgt), (String)"gokugt_legs", null);
        gokugt_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "gokugt").func_77655_b("gokugt_boots").func_77637_a(NBTab.dbgt), (String)"gokugt_boots", null);
        gotengt_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "gotengt").func_77655_b("gotengt_chest").func_77637_a(NBTab.dbgt), (String)"gotengt_chest", null);
        gotengt_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "gotengt").func_77655_b("gotengt_legs").func_77637_a(NBTab.dbgt), (String)"gotengt_legs", null);
        gotengt_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "gotengt").func_77655_b("gotengt_boots").func_77637_a(NBTab.dbgt), (String)"gotengt_boots", null);
        krillingt_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "krillingt").func_77655_b("krillingt_chest").func_77637_a(NBTab.dbgt), (String)"krillingt_chest", null);
        krillingt_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "krillingt").func_77655_b("krillingt_legs").func_77637_a(NBTab.dbgt), (String)"krillingt_legs", null);
        krillingt_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "krillingt").func_77655_b("krillingt_boots").func_77637_a(NBTab.dbgt), (String)"krillingt_boots", null);
        majinubb_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "majinubb").func_77655_b("majinubb_chest").func_77637_a(NBTab.dbgt), (String)"majinubb_chest", null);
        majinubb_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "majinubb").func_77655_b("majinubb_legs").func_77637_a(NBTab.dbgt), (String)"majinubb_legs", null);
        panbee_head = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "panbee").func_77655_b("panbee_head").func_77637_a(NBTab.dbgt), (String)"panbee_head", null);
        panbee_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "panbee").func_77655_b("panbee_chest").func_77637_a(NBTab.dbgt), (String)"panbee_chest", null);
        panbee_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "panbee").func_77655_b("panbee_legs").func_77637_a(NBTab.dbgt), (String)"panbee_legs", null);
        panbee_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "panbee").func_77655_b("panbee_boots").func_77637_a(NBTab.dbgt), (String)"panbee_boots", null);
        rildo0_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "rildo0").func_77655_b("rildo0_chest").func_77637_a(NBTab.dbgt), (String)"rildo0_chest", null);
        rildo0_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "rildo0").func_77655_b("rildo0_boots").func_77637_a(NBTab.dbgt), (String)"rildo0_boots", null);
        rildo1_head = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "rildo1").func_77655_b("rildo1_head").func_77637_a(NBTab.dbgt), (String)"rildo1_head", null);
        rildo1_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "rildo1").func_77655_b("rildo1_chest").func_77637_a(NBTab.dbgt), (String)"rildo1_chest", null);
        rildo1_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "rildo1").func_77655_b("rildo1_boots").func_77637_a(NBTab.dbgt), (String)"rildo1_boots", null);
        trunksgtjacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "trunksgtjacket", -1).func_77655_b("trunksgtjacket").func_77637_a(NBTab.dbgt), (String)"trunksgtjacket", null);
        trunksgt_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "trunksgt").func_77655_b("trunksgt_chest").func_77637_a(NBTab.dbgt), (String)"trunksgt_chest", null);
        trunksgt_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "trunksgt").func_77655_b("trunksgt_legs").func_77637_a(NBTab.dbgt), (String)"trunksgt_legs", null);
        trunksgt_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "trunksgt").func_77655_b("trunksgt_boots").func_77637_a(NBTab.dbgt), (String)"trunksgt_boots", null);
        ubb_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "ubb").func_77655_b("ubb_chest").func_77637_a(NBTab.dbgt), (String)"ubb_chest", null);
        ubb_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "ubb").func_77655_b("ubb_legs").func_77637_a(NBTab.dbgt), (String)"ubb_legs", null);
        ubb_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "ubb").func_77655_b("ubb_boots").func_77637_a(NBTab.dbgt), (String)"ubb_boots", null);
        vegetagtjacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "vegetagtjacket", -1).func_77655_b("vegetagtjacket").func_77637_a(NBTab.dbgt), (String)"vegetagtjacket", null);
        vegetagt_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "vegetagt").func_77655_b("vegetagt_chest").func_77637_a(NBTab.dbgt), (String)"vegetagt_chest", null);
        vegetagt_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "vegetagt").func_77655_b("vegetagt_legs").func_77637_a(NBTab.dbgt), (String)"vegetagt_legs", null);
        vegetagt_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "vegetagt").func_77655_b("vegetagt_boots").func_77637_a(NBTab.dbgt), (String)"vegetagt_boots", null);
    }
}

