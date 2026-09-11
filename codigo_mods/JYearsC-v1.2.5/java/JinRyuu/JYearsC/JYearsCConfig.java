/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package JinRyuu.JYearsC;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class JYearsCConfig {
    public static int ItemWatchID;
    public static int pls;
    public static int pgut;
    public static int cpls;
    public static int cpgut;

    public static void init(Configuration config) {
        config.load();
        ItemWatchID = config.get("item", "ItemWatchID", 9088).getInt();
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        Property ls = config.get("general", "Players Life Spawn in Minecraft Days", 450);
        ls.comment = "Server Sided! Players Life Spawn in Minecraft Days, where 1 MC Day is 20 minutes. Between 20 and 1000000";
        cpls = ls.getInt(450);
        pls = cpls = cpls < 20 ? 20 : (cpls > 1000000 ? 1000000 : cpls);
        Property GUT = config.get("general", "Players Grow up time in Minecraft Days", 52);
        GUT.comment = "Server Sided! Players Grow up time in Minecraft Days, where 1 MC Day is 20 minutes. Between 10 and 100000";
        cpgut = GUT.getInt(52);
        pgut = cpgut = cpgut < 10 ? 10 : (cpgut > 100000 ? 100000 : cpgut);
        config.save();
    }
}

