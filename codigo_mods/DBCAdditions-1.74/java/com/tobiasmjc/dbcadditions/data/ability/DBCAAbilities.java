/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.tobiasmjc.dbcadditions.data.ability;

import com.tobiasmjc.dbcadditions.data.ability.AbilityData;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public class DBCAAbilities {
    public static Map<String, AbilityData> AbsorbingMap = new HashMap<String, AbilityData>();

    public static boolean isAbsorbing(EntityPlayer bioAndroid) {
        if (bioAndroid == null) {
            return false;
        }
        return AbsorbingMap.containsKey(bioAndroid.func_70005_c_());
    }

    public static void startAbsorbing(EntityPlayer bioAndroid, int target) {
        if (bioAndroid != null) {
            AbsorbingMap.put(bioAndroid.func_70005_c_(), new AbilityData(target));
        }
    }

    public static void stopAbsorbing(EntityPlayer bioAndroid) {
        if (bioAndroid != null) {
            AbsorbingMap.remove(bioAndroid.func_70005_c_());
        }
    }

    public static void stopAbsorption(String playerName) {
        if (playerName != null) {
            AbsorbingMap.remove(playerName);
        }
    }
}

