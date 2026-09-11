/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import java.util.HashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NBTTags;
import noppes.npcs.api.handler.IPlayerItemGiverData;
import noppes.npcs.api.jobs.IJobItemGiver;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.roles.JobItemGiver;

public class PlayerItemGiverData
implements IPlayerItemGiverData {
    private final PlayerData parent;
    private HashMap<Integer, Long> itemgivers = new HashMap();
    private HashMap<Integer, Integer> chained = new HashMap();

    public PlayerItemGiverData(PlayerData parent) {
        this.parent = parent;
    }

    public void loadNBTData(NBTTagCompound compound) {
        this.chained = NBTTags.getIntegerIntegerMap(compound.func_150295_c("ItemGiverChained", 10));
        this.itemgivers = NBTTags.getIntegerLongMap(compound.func_150295_c("ItemGiversList", 10));
    }

    public void saveNBTData(NBTTagCompound compound) {
        compound.func_74782_a("ItemGiverChained", (NBTBase)NBTTags.nbtIntegerIntegerMap(this.chained));
        compound.func_74782_a("ItemGiversList", (NBTBase)NBTTags.nbtIntegerLongMap(this.itemgivers));
    }

    public boolean hasInteractedBefore(JobItemGiver jobItemGiver) {
        return this.itemgivers.containsKey(jobItemGiver.itemGiverId);
    }

    public long getTime(JobItemGiver jobItemGiver) {
        return this.itemgivers.get(jobItemGiver.itemGiverId);
    }

    public void setTime(JobItemGiver jobItemGiver, long day) {
        this.itemgivers.put(jobItemGiver.itemGiverId, day);
    }

    public int getItemIndex(JobItemGiver jobItemGiver) {
        if (this.chained.containsKey(jobItemGiver.itemGiverId)) {
            return this.chained.get(jobItemGiver.itemGiverId);
        }
        return 0;
    }

    public void setItemIndex(JobItemGiver jobItemGiver, int i) {
        this.chained.put(jobItemGiver.itemGiverId, i);
    }

    @Override
    public long getTime(IJobItemGiver jobItemGiver) {
        return this.itemgivers.get(((JobItemGiver)((Object)jobItemGiver)).itemGiverId);
    }

    @Override
    public void setTime(IJobItemGiver jobItemGiver, long day) {
        this.itemgivers.put(((JobItemGiver)((Object)jobItemGiver)).itemGiverId, day);
    }

    @Override
    public boolean hasInteractedBefore(IJobItemGiver jobItemGiver) {
        return this.itemgivers.containsKey(((JobItemGiver)((Object)jobItemGiver)).itemGiverId);
    }
}

