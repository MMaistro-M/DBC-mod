/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.event.ConfigChangedEvent$OnConfigChangedEvent
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.item.Item
 *  net.minecraftforge.common.config.Configuration
 */
package me.NBArmors.main;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import me.NBArmors.armors.angels;
import me.NBArmors.armors.db;
import me.NBArmors.armors.dbgt;
import me.NBArmors.armors.dbh;
import me.NBArmors.armors.dbm;
import me.NBArmors.armors.dbs;
import me.NBArmors.armors.dbz;
import me.NBArmors.armors.hakais;
import me.NBArmors.armors.origin;
import me.NBArmors.armors.supkai;
import me.NBArmors.blocks.NBblocks;
import me.NBArmors.config.ConfigNB;
import me.NBArmors.items.CItems;
import me.NBArmors.items.NewScouter;
import me.NBArmors.main.NB;
import me.NBArmors.main.RecipeNB;
import me.NBArmors.tabs.NBTab;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

@Mod(modid="nbarmors", version="3.1", name="NBArmors Mod", guiFactory="me.NBArmors.config.NBGuiFactory")
public class NBmain {
    @SidedProxy(clientSide="me.NBArmors.main.NBClient", serverSide="me.NBArmors.main.NB")
    public static NB proxy;
    public static String modid;
    public static ArrayList<Item> ItemNB;
    public static final int[] ItemsVanityNum;
    public static final int[] ItemVanity3;
    public static final int[] trenchcoatNum;
    public static final int[] trenchcoat3;
    public static Item[] ItemsVanity;
    public static Configuration config;
    @Mod.Instance(value="nbarmors")
    public static NBmain instance;

    @Mod.EventHandler
    public void pre_init(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        ConfigNB.syncConfig();
        proxy.registerTicks();
        proxy.preInit(event);
        proxy.postInit();
        NBTab.initialiseTabs();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals("nbarmors")) {
            ConfigNB.syncConfig();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register((Object)instance);
        proxy.registerRenderThings();
        NBblocks.mainRegistry();
        NewScouter.mainRegistry();
        CItems.mainRegistry();
        angels.mainRegistry();
        hakais.mainRegistry();
        supkai.mainRegistry();
        db.mainRegistry();
        dbz.mainRegistry();
        dbs.mainRegistry();
        dbgt.mainRegistry();
        dbh.mainRegistry();
        dbm.mainRegistry();
        origin.mainRegistry();
        RecipeNB.mainRegistry();
    }

    @Mod.EventHandler
    public void PostLoad(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    static {
        modid = "nbarmors";
        ItemNB = new ArrayList();
        ItemsVanityNum = new int[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ItemVanity3 = new int[]{0, 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        trenchcoatNum = new int[]{1};
        trenchcoat3 = new int[]{0};
        ItemsVanity = new Item[ItemsVanityNum.length];
    }
}

