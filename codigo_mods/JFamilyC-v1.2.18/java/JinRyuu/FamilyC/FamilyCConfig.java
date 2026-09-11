/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package JinRyuu.FamilyC;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class FamilyCConfig {
    public static int ItemWatchID;
    public static int cls;
    public static int gut;
    public static int pt;
    public static int mc;
    public static boolean dcr;
    public static int ccls;
    public static int cgut;
    public static int cpt;
    public static int cmc;
    public static boolean cdcr;

    public static void init(Configuration config) {
        config.load();
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        Property ls = config.get("general", "Child Life-span in Minecraft Days", 450);
        ls.comment = "Server Sided! Child Life-span in Minecraft Days, where 1 is for 1 MC Day that is 20 minutes. Between 20 and 1000000";
        ccls = ls.getInt(450);
        cls = ccls = ccls < 20 ? 20 : (ccls > 1000000 ? 1000000 : ccls);
        Property GUT = config.get("general", "Child Grow up time in Minecraft Days", 52);
        GUT.comment = "Server Sided! Child Grow up time in Minecraft Days, where 1 is for 1 MC Day that is 20 minutes. Between 10 and 100000";
        cgut = GUT.getInt(52);
        gut = cgut = cgut < 10 ? 10 : (cgut > 100000 ? 100000 : cgut);
        Property PT = config.get("general", "Pregnancy Time", 4);
        PT.comment = "Server Sided! Pregnancy time where 1 is for half MC Day that is 10 minutes. Between 1 and 50";
        cpt = PT.getInt(52);
        pt = cpt = cpt < 1 ? 1 : (cpt > 50 ? 50 : cpt);
        Property MC = config.get("general", "Max Children", 4);
        MC.comment = "Server Sided! Max children one can have, both for players and admins. Berween 0 and 10";
        cmc = MC.getInt(10);
        mc = cmc = cmc < 0 ? 0 : (cmc > 10 ? 10 : cmc);
        MC = config.get("general", "Disable & Remove Children", false);
        MC.comment = "Server Sided! 'true' means all children in the world will get removed and will disable procreation. By defeault this config is disabled (default: false)";
        dcr = cdcr = MC.getBoolean();
        config.save();
    }
}

