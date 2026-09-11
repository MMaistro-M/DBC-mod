/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers;

import java.util.Map;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.data.profile.Profile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.config.ConfigMain;

public class ProfileConfigSync {
    public static NBTTagCompound writeToNBT(EntityPlayer player) {
        NBTTagCompound compound = new NBTTagCompound();
        if (!ConfigMain.ProfilesEnabled || ProfileController.Instance == null) {
            return compound;
        }
        Profile profile = ProfileController.Instance.getProfile(player);
        if (profile == null) {
            return compound;
        }
        NBTTagList questList = new NBTTagList();
        for (Map.Entry<Integer, Long> entry : profile.sharedQuestTimestamps.entrySet()) {
            NBTTagCompound questEntry = new NBTTagCompound();
            questEntry.func_74768_a("Quest", entry.getKey().intValue());
            questEntry.func_74772_a("Date", entry.getValue().longValue());
            questList.func_74742_a((NBTBase)questEntry);
        }
        compound.func_74782_a("SharedQuestTimestamps", (NBTBase)questList);
        return compound;
    }
}

