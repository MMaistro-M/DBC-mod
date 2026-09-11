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

public class db {
    public static Item bulma0_chest;
    public static Item bulma0_legs;
    public static Item bulma0_boots;
    public static Item bulma10_chest;
    public static Item bulma10_legs;
    public static Item bulma10_boots;
    public static Item bulma3_chest;
    public static Item bulma3_legs;
    public static Item bulma3_boots;
    public static Item bulma5_chest;
    public static Item bulma6_head;
    public static Item bulma6_chest;
    public static Item bulma6_legs;
    public static Item bulma6_boots;
    public static Item bulma7_head;
    public static Item bulma7_chest;
    public static Item bulma7_legs;
    public static Item bulma7_boots;
    public static Item bulma8_chest;
    public static Item bulma8_legs;
    public static Item bulma8_boots;
    public static Item bulma9_chest;
    public static Item bulma9_legs;
    public static Item bulma9_boots;
    public static Item c08_chest;
    public static Item c08_legs;
    public static Item c08_boots;
    public static Item comred_chest;
    public static Item comred_legs;
    public static Item comred_boots;
    public static Item csilver_chest;
    public static Item csilver_legs;
    public static Item csilver_boots;
    public static Item cviolet_chest;
    public static Item cviolet_legs;
    public static Item cviolet_boots;
    public static Item daimaku_chest;
    public static Item daimaku_legs;
    public static Item daimaku_boots;
    public static Item ggohan_head;
    public static Item ggohan_chest;
    public static Item ggohan_legs;
    public static Item ggohan_boots;
    public static Item jchun_chest;
    public static Item jchun_legs;
    public static Item jchun_boots;
    public static Item kchappa_chest;
    public static Item kchappa_legs;
    public static Item kgokukame_chest;
    public static Item kgokukame_legs;
    public static Item kgokukame_boots;
    public static Item kidgoku_chest;
    public static Item kidgoku_legs;
    public static Item kidgoku_boots;
    public static Item kidmilk_head;
    public static Item kidmilk_chest;
    public static Item kidmilk_legs;
    public static Item kidmilk_boots;
    public static Item launch_chest;
    public static Item launch_legs;
    public static Item launch_boots;
    public static Item mai_chest;
    public static Item mai_legs;
    public static Item mai_boots;
    public static Item murasaki_chest;
    public static Item murasaki_legs;
    public static Item murasaki_boots;
    public static Item oxking_head;
    public static Item oxking_chest;
    public static Item oxking_legs;
    public static Item oxking_boots;
    public static Item shu_head;
    public static Item shu_chest;
    public static Item shu_legs;
    public static Item shu_boots;
    public static Item tao_chest;
    public static Item tao_legs;
    public static Item tao_boots;

    public static void mainRegistry() {
        db.initiliseItem();
        db.registerItem();
    }

    public static void initiliseItem() {
    }

    public static void registerItem() {
        bulma0_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bulma0").func_77655_b("bulma0_chest").func_77637_a(NBTab.db), (String)"bulma0_chest", null);
        bulma0_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "bulma0").func_77655_b("bulma0_legs").func_77637_a(NBTab.db), (String)"bulma0_legs", null);
        bulma0_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "bulma0").func_77655_b("bulma0_boots").func_77637_a(NBTab.db), (String)"bulma0_boots", null);
        bulma10_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bulma10").func_77655_b("bulma10_chest").func_77637_a(NBTab.db), (String)"bulma10_chest", null);
        bulma10_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "bulma10").func_77655_b("bulma10_legs").func_77637_a(NBTab.db), (String)"bulma10_legs", null);
        bulma10_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "bulma10").func_77655_b("bulma10_boots").func_77637_a(NBTab.db), (String)"bulma10_boots", null);
        bulma3_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bulma3").func_77655_b("bulma3_chest").func_77637_a(NBTab.db), (String)"bulma3_chest", null);
        bulma3_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "bulma3").func_77655_b("bulma3_legs").func_77637_a(NBTab.db), (String)"bulma3_legs", null);
        bulma3_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "bulma3").func_77655_b("bulma3_boots").func_77637_a(NBTab.db), (String)"bulma3_boots", null);
        bulma5_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bulma5").func_77655_b("bulma5_chest").func_77637_a(NBTab.db), (String)"bulma5_chest", null);
        bulma6_head = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "hatb").func_77655_b("bulma6_head").func_77637_a(NBTab.db), (String)"bulma6_head", null);
        bulma6_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bulma6").func_77655_b("bulma6_chest").func_77637_a(NBTab.db), (String)"bulma6_chest", null);
        bulma6_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "bulma6").func_77655_b("bulma6_legs").func_77637_a(NBTab.db), (String)"bulma6_legs", null);
        bulma6_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "bulma6").func_77655_b("bulma6_boots").func_77637_a(NBTab.db), (String)"bulma6_boots", null);
        bulma7_head = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "buears", -1).func_77655_b("bulma7_head").func_77637_a(NBTab.db), (String)"bulma7_head", null);
        bulma7_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bulma7").func_77655_b("bulma7_chest").func_77637_a(NBTab.db), (String)"bulma7_chest", null);
        bulma7_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "bulma7").func_77655_b("bulma7_legs").func_77637_a(NBTab.db), (String)"bulma7_legs", null);
        bulma7_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "bulma7").func_77655_b("bulma7_boots").func_77637_a(NBTab.db), (String)"bulma7_boots", null);
        bulma8_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bulma8").func_77655_b("bulma8_chest").func_77637_a(NBTab.db), (String)"bulma8_chest", null);
        bulma8_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "bulma8").func_77655_b("bulma8_legs").func_77637_a(NBTab.db), (String)"bulma8_legs", null);
        bulma8_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "bulma8").func_77655_b("bulma8_boots").func_77637_a(NBTab.db), (String)"bulma8_boots", null);
        bulma9_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "bulma9").func_77655_b("bulma9_chest").func_77637_a(NBTab.db), (String)"bulma9_chest", null);
        bulma9_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "bulma9").func_77655_b("bulma9_legs").func_77637_a(NBTab.db), (String)"bulma9_legs", null);
        bulma9_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "bulma9").func_77655_b("bulma9_boots").func_77637_a(NBTab.db), (String)"bulma9_boots", null);
        c08_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "c08").func_77655_b("c08_chest").func_77637_a(NBTab.db), (String)"c08_chest", null);
        c08_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "c08").func_77655_b("c08_legs").func_77637_a(NBTab.db), (String)"c08_legs", null);
        c08_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "c08").func_77655_b("c08_boots").func_77637_a(NBTab.db), (String)"c08_boots", null);
        comred_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "comred").func_77655_b("comred_chest").func_77637_a(NBTab.db), (String)"comred_chest", null);
        comred_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "comred").func_77655_b("comred_legs").func_77637_a(NBTab.db), (String)"comred_legs", null);
        comred_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "comred").func_77655_b("comred_boots").func_77637_a(NBTab.db), (String)"comred_boots", null);
        csilver_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "csilver").func_77655_b("csilver_chest").func_77637_a(NBTab.db), (String)"csilver_chest", null);
        csilver_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "csilver").func_77655_b("csilver_legs").func_77637_a(NBTab.db), (String)"csilver_legs", null);
        csilver_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "csilver").func_77655_b("csilver_boots").func_77637_a(NBTab.db), (String)"csilver_boots", null);
        cviolet_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "cviolet").func_77655_b("cviolet_chest").func_77637_a(NBTab.db), (String)"cviolet_chest", null);
        cviolet_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "cviolet").func_77655_b("cviolet_legs").func_77637_a(NBTab.db), (String)"cviolet_legs", null);
        cviolet_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "cviolet").func_77655_b("cviolet_boots").func_77637_a(NBTab.db), (String)"cviolet_boots", null);
        daimaku_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "daimaku").func_77655_b("daimaku_chest").func_77637_a(NBTab.db), (String)"daimaku_chest", null);
        daimaku_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "daimaku").func_77655_b("daimaku_legs").func_77637_a(NBTab.db), (String)"daimaku_legs", null);
        daimaku_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "daimaku").func_77655_b("daimaku_boots").func_77637_a(NBTab.db), (String)"daimaku_boots", null);
        ggohan_head = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "ggohan").func_77655_b("ggohan_head").func_77637_a(NBTab.db), (String)"ggohan_head", null);
        ggohan_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "ggohan").func_77655_b("ggohan_chest").func_77637_a(NBTab.db), (String)"ggohan_chest", null);
        ggohan_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "ggohan").func_77655_b("ggohan_legs").func_77637_a(NBTab.db), (String)"ggohan_legs", null);
        ggohan_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "ggohan").func_77655_b("ggohan_boots").func_77637_a(NBTab.db), (String)"ggohan_boots", null);
        jchun_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "jchun").func_77655_b("jchun_chest").func_77637_a(NBTab.db), (String)"jchun_chest", null);
        jchun_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "jchun").func_77655_b("jchun_legs").func_77637_a(NBTab.db), (String)"jchun_legs", null);
        jchun_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "jchun").func_77655_b("jchun_boots").func_77637_a(NBTab.db), (String)"jchun_boots", null);
        kchappa_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "kchappa").func_77655_b("kchappa_chest").func_77637_a(NBTab.db), (String)"kchappa_chest", null);
        kchappa_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "kchappa").func_77655_b("kchappa_legs").func_77637_a(NBTab.db), (String)"kchappa_legs", null);
        kgokukame_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "kgokukame").func_77655_b("kgokukame_chest").func_77637_a(NBTab.db), (String)"kgokukame_chest", null);
        kgokukame_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "kgokukame").func_77655_b("kgokukame_legs").func_77637_a(NBTab.db), (String)"kgokukame_legs", null);
        kgokukame_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "kgokukame").func_77655_b("kgokukame_boots").func_77637_a(NBTab.db), (String)"kgokukame_boots", null);
        kidgoku_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "kidgoku").func_77655_b("kidgoku_chest").func_77637_a(NBTab.db), (String)"kidgoku_chest", null);
        kidgoku_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "kidgoku").func_77655_b("kidgoku_legs").func_77637_a(NBTab.db), (String)"kidgoku_legs", null);
        kidgoku_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "kidgoku").func_77655_b("kidgoku_boots").func_77637_a(NBTab.db), (String)"kidgoku_boots", null);
        kidmilk_head = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "kidmilk").func_77655_b("kidmilk_head").func_77637_a(NBTab.db), (String)"kidmilk_head", null);
        kidmilk_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "kidmilk").func_77655_b("kidmilk_chest").func_77637_a(NBTab.db), (String)"kidmilk_chest", null);
        kidmilk_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "kidmilk").func_77655_b("kidmilk_legs").func_77637_a(NBTab.db), (String)"kidmilk_legs", null);
        kidmilk_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "kidmilk").func_77655_b("kidmilk_boots").func_77637_a(NBTab.db), (String)"kidmilk_boots", null);
        launch_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "launch").func_77655_b("launch_chest").func_77637_a(NBTab.db), (String)"launch_chest", null);
        launch_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "launch").func_77655_b("launch_legs").func_77637_a(NBTab.db), (String)"launch_legs", null);
        launch_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "launch").func_77655_b("launch_boots").func_77637_a(NBTab.db), (String)"launch_boots", null);
        mai_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "mai").func_77655_b("mai_chest").func_77637_a(NBTab.db), (String)"mai_chest", null);
        mai_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "mai").func_77655_b("mai_legs").func_77637_a(NBTab.db), (String)"mai_legs", null);
        mai_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "mai").func_77655_b("mai_boots").func_77637_a(NBTab.db), (String)"mai_boots", null);
        murasaki_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "murasaki").func_77655_b("murasaki_chest").func_77637_a(NBTab.db), (String)"murasaki_chest", null);
        murasaki_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "murasaki").func_77655_b("murasaki_legs").func_77637_a(NBTab.db), (String)"murasaki_legs", null);
        murasaki_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "murasaki").func_77655_b("murasaki_boots").func_77637_a(NBTab.db), (String)"murasaki_boots", null);
        oxking_head = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "oxking").func_77655_b("oxking_head").func_77637_a(NBTab.db), (String)"oxking_head", null);
        oxking_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "oxking").func_77655_b("oxking_chest").func_77637_a(NBTab.db), (String)"oxking_chest", null);
        oxking_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "oxking").func_77655_b("oxking_legs").func_77637_a(NBTab.db), (String)"oxking_legs", null);
        oxking_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "oxking").func_77655_b("oxking_boots").func_77637_a(NBTab.db), (String)"oxking_boots", null);
        shu_head = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "shu").func_77655_b("shu_head").func_77637_a(NBTab.db), (String)"shu_head", null);
        shu_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "shu").func_77655_b("shu_chest").func_77637_a(NBTab.db), (String)"shu_chest", null);
        shu_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "shu").func_77655_b("shu_legs").func_77637_a(NBTab.db), (String)"shu_legs", null);
        shu_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "shu").func_77655_b("shu_boots").func_77637_a(NBTab.db), (String)"shu_boots", null);
        tao_chest = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 1, "tao").func_77655_b("tao_chest").func_77637_a(NBTab.db), (String)"tao_chest", null);
        tao_legs = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 2, "tao").func_77655_b("tao_legs").func_77637_a(NBTab.db), (String)"tao_legs", null);
        tao_boots = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 3, "tao").func_77655_b("tao_boots").func_77637_a(NBTab.db), (String)"tao_boots", null);
    }
}

