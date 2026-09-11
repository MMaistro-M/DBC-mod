/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 */
package me.NBArmors.tabs;

import me.NBArmors.config.ConfigNB;
import me.NBArmors.tabs.AngelTab;
import me.NBArmors.tabs.CoreItems;
import me.NBArmors.tabs.ExvaniTab;
import me.NBArmors.tabs.HakaiTab;
import me.NBArmors.tabs.OriginTab;
import me.NBArmors.tabs.PotaraTab;
import me.NBArmors.tabs.ScouterTab;
import me.NBArmors.tabs.SupkaiTab;
import me.NBArmors.tabs.dbTab;
import me.NBArmors.tabs.dbgtTab;
import me.NBArmors.tabs.dbhTab;
import me.NBArmors.tabs.dbmTab;
import me.NBArmors.tabs.dbsTab;
import me.NBArmors.tabs.dbzTab;
import net.minecraft.creativetab.CreativeTabs;

public class NBTab {
    public static CreativeTabs coreitems;
    public static CreativeTabs hakais;
    public static CreativeTabs supkai;
    public static CreativeTabs angels;
    public static CreativeTabs db;
    public static CreativeTabs dbz;
    public static CreativeTabs dbs;
    public static CreativeTabs dbgt;
    public static CreativeTabs dbh;
    public static CreativeTabs dbm;
    public static CreativeTabs origin;
    public static CreativeTabs scoutern;
    public static CreativeTabs exvani;
    public static CreativeTabs potara;

    public static void initialiseTabs() {
        if (!ConfigNB.Recipe) {
            coreitems = new CoreItems("coreitems");
        }
        angels = new AngelTab("angels");
        hakais = new HakaiTab("hakais");
        supkai = new SupkaiTab("supkai");
        db = new dbTab("db");
        dbz = new dbzTab("dbz");
        dbs = new dbsTab("dbs");
        dbgt = new dbgtTab("dbgt");
        dbh = new dbhTab("dbh");
        dbm = new dbmTab("dbm");
        origin = new OriginTab("origin");
        scoutern = new ScouterTab("scoutern");
        exvani = new ExvaniTab("exvani");
        potara = new PotaraTab("potara");
    }
}

