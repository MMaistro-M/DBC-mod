/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.tobiasmjc.dbcadditions.data.races;

import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;

public class DBCARaces {
    public static Set<DBCARace> HOST_RACES = new HashSet<DBCARace>();
    public static Set<DBCARace> RACES = new HashSet<DBCARace>();
    public static final DBCARace BIO_ANDROID = new DBCARace("Bio-Android").setHairType("X").setSkinLimits(new int[]{1, 3, 1, 1, 1, 2}).setTPCosts(new int[]{10000, 20000, 30000, 40000, 50000}).setMindCosts(new int[]{15, 15, 15, 15, 15}).setColorMinRacial(4).setUltimateFormColors(new int[]{16719381, -1, -1, -1});

    public static void registerRace(DBCARace race) {
        HOST_RACES.add(race);
    }

    public static void registerRaces() {
        DBCARaces.registerRace(BIO_ANDROID);
    }

    public static DBCARace getRace(byte raceID) {
        for (DBCARace race : RACES) {
            if (race.ID != raceID) continue;
            return race;
        }
        return null;
    }

    public static boolean isCustomRace(EntityPlayer player) {
        byte raceID = DataUtils.getDBCARace(player);
        return raceID > 0;
    }

    public static boolean isRace(EntityPlayer player, DBCARace race) {
        byte raceID = DataUtils.getDBCARace(player);
        return raceID > 0 && raceID == race.ID;
    }
}

