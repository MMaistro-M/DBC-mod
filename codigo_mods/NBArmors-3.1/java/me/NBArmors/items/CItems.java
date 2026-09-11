/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 */
package me.NBArmors.items;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import me.NBArmors.armors.ArmorNB;
import me.NBArmors.armors.ArmorNB2;
import me.NBArmors.armors.MarksNB;
import me.NBArmors.armors.TrenchNB2;
import me.NBArmors.config.ConfigNB;
import me.NBArmors.items.Core;
import me.NBArmors.items.FabricCore;
import me.NBArmors.items.FabricCoreColor;
import me.NBArmors.items.ItemBeardNB;
import me.NBArmors.items.ItemBodyNB;
import me.NBArmors.items.ItemVanityNB;
import me.NBArmors.items.TrenchCoatNB;
import me.NBArmors.tabs.NBTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class CItems {
    public static Item Core;
    public static Item FabricCore;
    public static Item FabricCoreColor;
    public static ArrayList<Item> ItemNB;
    public static Item ahegao;
    public static Item allcorballs;
    public static Item alldarkballs;
    public static Item antbee_chest;
    public static Item antbee_head;
    public static Item bandaid;
    public static Item barriertime;
    public static Item bluejacket;
    public static Item brave;
    public static Item brolyd;
    public static Item brolyd0;
    public static Item capec;
    public static Item coat;
    public static Item coat21;
    public static Item corruptball1;
    public static Item corruptball2;
    public static Item corruptball3;
    public static Item corruptball4;
    public static Item corruptball5;
    public static Item corruptball6;
    public static Item corruptball7;
    public static Item crimsonm;
    public static Item crimsonrm;
    public static Item darkball1;
    public static Item darkball2;
    public static Item darkball3;
    public static Item darkball4;
    public static Item darkball5;
    public static Item darkball6;
    public static Item darkball7;
    public static Item earing;
    public static Item earingr;
    public static Item ears0;
    public static Item ears1;
    public static Item eyepad;
    public static Item eyetien;
    public static Item ggloves;
    public static Item glovesvgt;
    public static Item ggmask;
    public static Item gherojacket;
    public static Item glasses0;
    public static Item glasses1;
    public static Item glasses2;
    public static Item glassesfu;
    public static Item greenjacket;
    public static Item gscarmark;
    public static Item gshirt;
    public static Item guldoeye;
    public static Item hat4ball_head;
    public static Item hearing;
    public static Item inblack_head;
    public static Item inblack1_head;
    public static Item inblack1r_head;
    public static Item inblack2_head;
    public static Item inblackopen_head;
    public static Item inblackopen1_head;
    public static Item jtail;
    public static Item lmark;
    public static Item makeup0;
    public static Item makeup1;
    public static Item makeup2;
    public static Item makeup3;
    public static Item maskg;
    public static Item mulehorns;
    public static Item okaram;
    public static Item okararm;
    public static Item omnimark;
    public static Item omnimark1;
    public static Item puar;
    public static Item roshiglass;
    public static Item roshiglass1;
    public static Item rrglass;
    public static Item scar0;
    public static Item scar0r;
    public static Item scar1;
    public static Item scar2;
    public static Item scar2r;
    public static Item scar3;
    public static Item scar3r;
    public static Item scarf;
    public static Item shukatana;
    public static Item ssj4;
    public static Item tail;
    public static Item tailcellmax;
    public static Item tailmf;
    public static Item talecape;
    public static Item trenchcoat;
    public static Item turbatp_head;
    public static Item vherojacket;
    public static Item wristb;
    public static Item xicor0;
    public static Item xmark;
    public static Item[] ItemsHakaim;
    public static Item[] ItemsPotaraG;
    public static Item[] ItemsPotaraY;
    private static Item aeoshead;
    public static Item redjacket;
    public static Item pinkjacket;
    public static Item purplejacket;
    public static Item yellowjacket;
    public static Item[] ItemsBeard;
    private static final EntityPlayer AbstractClientPlayer;

    public static void mainRegistry() {
        CItems.initiliseItem();
        CItems.registerItem();
    }

    public static void initiliseItem() {
    }

    public static void registerItem() {
        int i;
        if (!ConfigNB.Recipe) {
            Core = GameRegistry.registerItem((Item)new Core().func_77655_b("Core").func_111206_d("Core").func_77637_a(NBTab.coreitems), (String)"Core", null);
            FabricCore = GameRegistry.registerItem((Item)new FabricCore().func_77655_b("FabricCore").func_111206_d("FabricCore").func_77637_a(NBTab.coreitems), (String)"FabricCore", null);
            FabricCoreColor = GameRegistry.registerItem((Item)new FabricCoreColor().func_77655_b("FabricCoreColor").func_111206_d("FabricCoreColor").func_77637_a(NBTab.coreitems), (String)"FabricCoreColor", null);
        }
        allcorballs = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "allcorballs", -1).func_77655_b("allcorballs").func_77637_a(NBTab.exvani), (String)"allcorballs", null);
        alldarkballs = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "alldarkballs", -1).func_77655_b("alldarkballs").func_77637_a(NBTab.exvani), (String)"alldarkballs", null);
        antbee_chest = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "antbee", -1).func_77655_b("antbee_chest").func_77637_a(NBTab.exvani), (String)"antbee_chest", null);
        antbee_head = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "antbee", -1).func_77655_b("antbee_head").func_77637_a(NBTab.exvani), (String)"antbee_head", null);
        bandaid = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "bandaid", 3).func_77655_b("bandaid").func_77637_a(NBTab.exvani), (String)"bandaid", null);
        barriertime = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "barriertime", 0).func_77655_b("barriertime").func_77637_a(NBTab.exvani), (String)"barriertime", null);
        brave = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "brave", 10).func_77655_b("brave").func_77637_a(NBTab.exvani), (String)"brave", null);
        brolyd0 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "brolyd0", -1).func_77655_b("brolyd0").func_77637_a(NBTab.exvani), (String)"brolyd0", null);
        capec = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "capec", -1).func_77655_b("capec").func_77637_a(NBTab.exvani), (String)"capec", null);
        coat = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "coat", -1).func_77655_b("coat").func_77637_a(NBTab.exvani), (String)"coat", null);
        coat21 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "coat21", -1).func_77655_b("coat21").func_77637_a(NBTab.exvani), (String)"coat21", null);
        corruptball1 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "corruptball1", -1).func_77655_b("corruptball1").func_77637_a(NBTab.exvani), (String)"corruptball1", null);
        corruptball2 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "corruptball2", -1).func_77655_b("corruptball2").func_77637_a(NBTab.exvani), (String)"corruptball2", null);
        corruptball3 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "corruptball3", -1).func_77655_b("corruptball3").func_77637_a(NBTab.exvani), (String)"corruptball3", null);
        corruptball4 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "corruptball4", -1).func_77655_b("corruptball4").func_77637_a(NBTab.exvani), (String)"corruptball4", null);
        corruptball5 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "corruptball5", -1).func_77655_b("corruptball5").func_77637_a(NBTab.exvani), (String)"corruptball5", null);
        corruptball6 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "corruptball6", -1).func_77655_b("corruptball6").func_77637_a(NBTab.exvani), (String)"corruptball6", null);
        corruptball7 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "corruptball7", -1).func_77655_b("corruptball7").func_77637_a(NBTab.exvani), (String)"corruptball7", null);
        crimsonm = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "crimsonm", -1).func_77655_b("crimsonm").func_77637_a(NBTab.exvani), (String)"crimsonm", null);
        crimsonrm = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "crimsonrm", -1).func_77655_b("crimsonrm").func_77637_a(NBTab.exvani), (String)"crimsonrm", null);
        darkball1 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "darkball1", 3).func_77655_b("darkball1").func_77637_a(NBTab.exvani), (String)"darkball1", null);
        darkball2 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "darkball2", 3).func_77655_b("darkball2").func_77637_a(NBTab.exvani), (String)"darkball2", null);
        darkball3 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "darkball3", -1).func_77655_b("darkball3").func_77637_a(NBTab.exvani), (String)"darkball3", null);
        darkball4 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "darkball4", -1).func_77655_b("darkball4").func_77637_a(NBTab.exvani), (String)"darkball4", null);
        darkball5 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "darkball5", -1).func_77655_b("darkball5").func_77637_a(NBTab.exvani), (String)"darkball5", null);
        darkball6 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "darkball6", 3).func_77655_b("darkball6").func_77637_a(NBTab.exvani), (String)"darkball6", null);
        darkball7 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "darkball7", 3).func_77655_b("darkball7").func_77637_a(NBTab.exvani), (String)"darkball7", null);
        earing = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "earing", 4).func_77655_b("earing").func_77637_a(NBTab.exvani), (String)"earing", null);
        earingr = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "earingr", 4).func_77655_b("earingr").func_77637_a(NBTab.exvani), (String)"earingr", null);
        ears0 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "ears0", 2).func_77655_b("ears0").func_77637_a(NBTab.exvani), (String)"ears0", null);
        ears1 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "ears1", 2).func_77655_b("ears1").func_77637_a(NBTab.exvani), (String)"ears1", null);
        eyepad = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "eyepad", 4).func_77655_b("eyepad").func_77637_a(NBTab.exvani), (String)"eyepad", null);
        eyetien = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "eyetien", 3).func_77655_b("eyetien").func_77637_a(NBTab.exvani), (String)"eyetien", null);
        ggloves = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "ggloves", -1).func_77655_b("ggloves").func_77637_a(NBTab.exvani), (String)"ggloves", null);
        glovesvgt = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "glovesvgt", -1).func_77655_b("glovesvgt").func_77637_a(NBTab.exvani), (String)"glovesvgt", null);
        ggmask = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "ggmask", 8).func_77655_b("ggmask").func_77637_a(NBTab.exvani), (String)"ggmask", null);
        gherojacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "gherojacket", -1).func_77655_b("gherojacket").func_77637_a(NBTab.exvani), (String)"gherojacket", null);
        aeoshead = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "aeoshead", 12).func_77655_b("aeoshead").func_77637_a(NBTab.exvani), (String)"aeoshead", null);
        glasses0 = GameRegistry.registerItem((Item)new TrenchCoatNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "glasses0", -1).func_77655_b("glasses0").func_77637_a(NBTab.exvani), (String)"glasses0", null);
        glasses1 = GameRegistry.registerItem((Item)new TrenchCoatNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "glasses1", -1).func_77655_b("glasses1").func_77637_a(NBTab.exvani), (String)"glasses1", null);
        glasses2 = GameRegistry.registerItem((Item)new TrenchCoatNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "glasses2", -1).func_77655_b("glasses2").func_77637_a(NBTab.exvani), (String)"glasses2", null);
        glassesfu = GameRegistry.registerItem((Item)new TrenchCoatNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "glassesfu", -1).func_77655_b("glassesfu").func_77637_a(NBTab.exvani), (String)"glassesfu", null);
        gscarmark = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "gscarmark", 11).func_77655_b("gscarmark").func_77637_a(NBTab.exvani), (String)"gscarmark", null);
        gshirt = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "gshirt", -1).func_77655_b("gshirt").func_77637_a(NBTab.exvani), (String)"gshirt", null);
        guldoeye = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "guldoeye", 3).func_77655_b("guldoeye").func_77637_a(NBTab.exvani), (String)"guldoeye", null);
        hat4ball_head = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "hat4ball").func_77655_b("hat4ball_head").func_77637_a(NBTab.exvani), (String)"hat4ball_head", null);
        inblack_head = GameRegistry.registerItem((Item)new ArmorNB2(ArmorNB.NBmaterial, 1, 0, "inblack").func_77655_b("inblack_head").func_77637_a(NBTab.exvani), (String)"inblack_head", null);
        inblack1_head = GameRegistry.registerItem((Item)new TrenchNB2(ArmorNB.NBmaterial, 1, 0, "inblack1").func_77655_b("inblack1_head").func_77637_a(NBTab.exvani), (String)"inblack1_head", null);
        inblack1r_head = GameRegistry.registerItem((Item)new TrenchNB2(ArmorNB.NBmaterial, 1, 0, "inblack1r").func_77655_b("inblack1r_head").func_77637_a(NBTab.exvani), (String)"inblack1r_head", null);
        inblack2_head = GameRegistry.registerItem((Item)new TrenchNB2(ArmorNB.NBmaterial, 1, 0, "inblack2").func_77655_b("inblack2_head").func_77637_a(NBTab.exvani), (String)"inblack2_head", null);
        inblackopen_head = GameRegistry.registerItem((Item)new ArmorNB2(ArmorNB.NBmaterial, 1, 0, "inblackopen").func_77655_b("inblackopen_head").func_77637_a(NBTab.exvani), (String)"inblackopen_head", null);
        inblackopen1_head = GameRegistry.registerItem((Item)new ArmorNB2(ArmorNB.NBmaterial, 1, 0, "inblackopen1").func_77655_b("inblackopen1_head").func_77637_a(NBTab.exvani), (String)"inblackopen1_head", null);
        jtail = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "jtail", 6).func_77655_b("jtail").func_77637_a(NBTab.exvani), (String)"jtail", null);
        lmark = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "lmark", -1).func_77655_b("lmark").func_77637_a(NBTab.exvani), (String)"lmark", null);
        makeup0 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "makeup0", 11).func_77655_b("makeup0").func_77637_a(NBTab.exvani), (String)"makeup0", null);
        makeup1 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "makeup1", 11).func_77655_b("makeup1").func_77637_a(NBTab.exvani), (String)"makeup1", null);
        makeup2 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "makeup2", 11).func_77655_b("makeup2").func_77637_a(NBTab.exvani), (String)"makeup2", null);
        makeup3 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "makeup3", 11).func_77655_b("makeup3").func_77637_a(NBTab.exvani), (String)"makeup3", null);
        maskg = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "maskg", 4).func_77655_b("maskg").func_77637_a(NBTab.exvani), (String)"maskg", null);
        mulehorns = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "mulehorns", 3).func_77655_b("mulehorns").func_77637_a(NBTab.exvani), (String)"mulehorns", null);
        okaram = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "okaram", 11).func_77655_b("okaram").func_77637_a(NBTab.exvani), (String)"okaram", null);
        okararm = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "okararm", 11).func_77655_b("okararm").func_77637_a(NBTab.exvani), (String)"okararm", null);
        omnimark = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "omnimark", -1).func_77655_b("omnimark").func_77637_a(NBTab.exvani), (String)"omnimark", null);
        omnimark1 = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "omnimark1", -1).func_77655_b("omnimark1").func_77637_a(NBTab.exvani), (String)"omnimark1", null);
        puar = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "puar", 7).func_77655_b("puar").func_77637_a(NBTab.exvani), (String)"puar", null);
        roshiglass = GameRegistry.registerItem((Item)new TrenchCoatNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "roshiglass", -1).func_77655_b("roshiglass").func_77637_a(NBTab.exvani), (String)"roshiglass", null);
        roshiglass1 = GameRegistry.registerItem((Item)new TrenchCoatNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "roshiglass1", -1).func_77655_b("roshiglass1").func_77637_a(NBTab.exvani), (String)"roshiglass1", null);
        rrglass = GameRegistry.registerItem((Item)new TrenchCoatNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "rrglass", -1).func_77655_b("rrglass").func_77637_a(NBTab.exvani), (String)"rrglass", null);
        scar0 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "scar0", 11).func_77655_b("scar0").func_77637_a(NBTab.exvani), (String)"scar0", null);
        scar0r = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "scar0r", 11).func_77655_b("scar0r").func_77637_a(NBTab.exvani), (String)"scar0r", null);
        scar1 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "scar1", 11).func_77655_b("scar1").func_77637_a(NBTab.exvani), (String)"scar1", null);
        scar2 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "scar2", 11).func_77655_b("scar2").func_77637_a(NBTab.exvani), (String)"scar2", null);
        scar2r = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "scar2r", 11).func_77655_b("scar2r").func_77637_a(NBTab.exvani), (String)"scar2r", null);
        scar3 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "scar3", 11).func_77655_b("scar3").func_77637_a(NBTab.exvani), (String)"scar3", null);
        scar3r = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "scar3r", 11).func_77655_b("scar3r").func_77637_a(NBTab.exvani), (String)"scar3r", null);
        scarf = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "scarf", 1).func_77655_b("scarf").func_77637_a(NBTab.exvani), (String)"scarf", null);
        shukatana = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "shukatana", 9).func_77655_b("shukatana").func_77637_a(NBTab.exvani), (String)"shukatana", null);
        ssj4 = GameRegistry.registerItem((Item)new ItemBodyNB(0xFFFFFF, "ssj4").func_77655_b("ssj4").func_77637_a(NBTab.exvani), (String)"ssj4", null);
        tail = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "tail", 6).func_77655_b("tail").func_77637_a(NBTab.exvani), (String)"tail", null);
        tailcellmax = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "tailcellmax", 6).func_77655_b("tailcellmax").func_77637_a(NBTab.exvani), (String)"tailcellmax", null);
        tailmf = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "tailmf", 6).func_77655_b("tailmf").func_77637_a(NBTab.exvani), (String)"tailmf", null);
        talecape = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "talecape", -1).func_77655_b("talecape").func_77637_a(NBTab.exvani), (String)"talecape", null);
        trenchcoat = GameRegistry.registerItem((Item)new TrenchCoatNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "trenchcoat", 0).func_77655_b("trenchcoat").func_77637_a(NBTab.exvani), (String)"trenchcoat", null);
        turbatp_head = GameRegistry.registerItem((Item)new ArmorNB(ArmorNB.NBmaterial, 1, 0, "turbatp").func_77655_b("turbatp_head").func_77637_a(NBTab.exvani), (String)"turbatp_head", null);
        vherojacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "vherojacket", -1).func_77655_b("vherojacket").func_77637_a(NBTab.exvani), (String)"vherojacket", null);
        wristb = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "wristb", -1).func_77655_b("wristb").func_77637_a(NBTab.exvani), (String)"wristb", null);
        xicor0 = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "xicor0", 5).func_77655_b("xicor0").func_77637_a(NBTab.exvani), (String)"xicor0", null);
        xmark = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "xmark", -1).func_77655_b("xmark").func_77637_a(NBTab.exvani), (String)"xmark", null);
        bluejacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "bluejacket", -1).func_77655_b("bluejacket").func_77637_a(NBTab.exvani), (String)"bluejacket", null);
        greenjacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "greenjacket", -1).func_77655_b("greenjacket").func_77637_a(NBTab.exvani), (String)"greenjacket", null);
        redjacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "redjacket", -1).func_77655_b("redjacket").func_77637_a(NBTab.exvani), (String)"redjacket", null);
        pinkjacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "pinkjacket", -1).func_77655_b("pinkjacket").func_77637_a(NBTab.exvani), (String)"pinkjacket", null);
        purplejacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "purplejacket", -1).func_77655_b("purplejacket").func_77637_a(NBTab.exvani), (String)"purplejacket", null);
        yellowjacket = GameRegistry.registerItem((Item)new MarksNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "yellowjacket", -1).func_77655_b("yellowjacket").func_77637_a(NBTab.exvani), (String)"yellowjacket", null);
        for (i = 0; i < 13; ++i) {
            CItems.ItemsPotaraY[i] = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "potaray" + i, -1).func_77655_b("potaray" + i).func_77637_a(NBTab.potara), (String)("potaray" + i), null);
        }
        for (i = 0; i < 13; ++i) {
            CItems.ItemsPotaraG[i] = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "potarag" + i, -1).func_77655_b("potarag" + i).func_77637_a(NBTab.potara), (String)("potarag" + i), null);
        }
        for (i = 1; i < 12; ++i) {
            CItems.ItemsHakaim[i] = GameRegistry.registerItem((Item)new ItemVanityNB(0xFFFFFF, ArmorNB.NBmaterial, 1, "hakaim" + i, -1).func_77655_b("hakaim" + i).func_77637_a(NBTab.exvani), (String)("hakaim" + i), null);
        }
        for (i = 1; i < 11; ++i) {
            CItems.ItemsBeard[i] = GameRegistry.registerItem((Item)new ItemBeardNB(0xFFFFFF, ArmorNB.NBmaterial, 0, "beard" + i, 13).func_77655_b("beard" + i).func_77637_a(NBTab.exvani), (String)("beard" + i), null);
        }
    }

    static {
        ItemNB = new ArrayList();
        ItemsHakaim = new Item[12];
        ItemsPotaraG = new Item[13];
        ItemsPotaraY = new Item[13];
        ItemsBeard = new Item[11];
        AbstractClientPlayer = null;
    }
}

