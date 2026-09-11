/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.client;

import java.util.HashMap;
import java.util.Map;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.data.profile.Profile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.config.ConfigMain;

public class ProfileClientConfig {
    private static volatile Map<Integer, Long> sharedQuestTimestamps = new HashMap<Integer, Long>();

    public static void readFromNBT(NBTTagCompound compound) {
        if (compound == null) {
            return;
        }
        HashMap<Integer, Long> timestamps = new HashMap<Integer, Long>();
        if (compound.func_74764_b("SharedQuestTimestamps")) {
            NBTTagList list = compound.func_150295_c("SharedQuestTimestamps", 10);
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound entry = list.func_150305_b(i);
                timestamps.put(entry.func_74762_e("Quest"), entry.func_74763_f("Date"));
            }
        }
        sharedQuestTimestamps = timestamps;
    }

    public static void reset() {
        sharedQuestTimestamps = new HashMap<Integer, Long>();
    }

    public static boolean isProfilesEnabled() {
        if (ProfileController.Instance != null) {
            return ConfigMain.ProfilesEnabled;
        }
        return ClientCacheHandler.allowProfiles;
    }

    public static boolean hasSharedQuest(EntityPlayer player, int questId) {
        if (ProfileController.Instance != null) {
            Profile profile = ProfileController.Instance.getProfile(player);
            return profile != null && profile.sharedQuestTimestamps.containsKey(questId);
        }
        return sharedQuestTimestamps.containsKey(questId);
    }

    public static Long getSharedQuestTimestamp(EntityPlayer player, int questId) {
        if (ProfileController.Instance != null) {
            Profile profile = ProfileController.Instance.getProfile(player);
            return profile != null ? profile.sharedQuestTimestamps.get(questId) : null;
        }
        return sharedQuestTimestamps.get(questId);
    }
}

