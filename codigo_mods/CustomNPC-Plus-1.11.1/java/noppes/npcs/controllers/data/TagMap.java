/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class TagMap {
    public int cloneTab;
    public String cloneFolder;
    public HashMap<String, HashSet<UUID>> tagMap;

    public TagMap(int tab) {
        this.cloneTab = tab;
        this.cloneFolder = null;
        this.tagMap = new HashMap();
    }

    public TagMap(String folder) {
        this.cloneTab = -1;
        this.cloneFolder = folder;
        this.tagMap = new HashMap();
    }

    public void readNBT(NBTTagCompound compound) {
        this.tagMap = new HashMap();
        NBTTagList list = compound.func_150295_c("TagMap", 10);
        if (list != null) {
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound = list.func_150305_b(i);
                String cloneName = nbttagcompound.func_74779_i("Clone");
                HashSet<UUID> uuids = new HashSet<UUID>();
                NBTTagList nbtTagList = nbttagcompound.func_150295_c("TagUUIDs", 8);
                for (int j = 0; j < nbtTagList.func_74745_c(); ++j) {
                    String uuid = nbtTagList.func_150307_f(j);
                    if (uuid.isEmpty()) continue;
                    uuids.add(UUID.fromString(uuid));
                }
                this.tagMap.put(cloneName, uuids);
            }
        }
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList cloneList = new NBTTagList();
        for (String key : this.tagMap.keySet()) {
            HashSet<UUID> uuidSet = this.tagMap.get(key);
            if (uuidSet.size() <= 0) continue;
            NBTTagCompound cloneCompound = new NBTTagCompound();
            cloneCompound.func_74778_a("Clone", key);
            NBTTagList nbtTagList = new NBTTagList();
            for (UUID uuid : uuidSet) {
                nbtTagList.func_74742_a((NBTBase)new NBTTagString(uuid.toString()));
            }
            cloneCompound.func_74782_a("TagUUIDs", (NBTBase)nbtTagList);
            cloneList.func_74742_a((NBTBase)cloneCompound);
        }
        nbt.func_74782_a("TagMap", (NBTBase)cloneList);
        return nbt;
    }

    public int getCloneTab() {
        return this.cloneTab;
    }

    public boolean hasClone(String cloneName) {
        return this.tagMap.containsKey(cloneName);
    }

    public HashSet<UUID> getUUIDs(String cloneName) {
        if (this.hasClone(cloneName)) {
            return this.tagMap.get(cloneName);
        }
        return null;
    }

    public List<UUID> getUUIDsList(String cloneName) {
        HashSet<UUID> uuids = this.getUUIDs(cloneName);
        if (uuids != null) {
            ArrayList<UUID> tagUUIDS = new ArrayList<UUID>(uuids);
            Collections.sort(tagUUIDS);
            return tagUUIDS;
        }
        return null;
    }

    public boolean removeClone(String cloneName) {
        return this.tagMap.remove(cloneName) != null;
    }

    public void putClone(String cloneName, HashSet<UUID> uuids) {
        this.tagMap.put(cloneName, uuids);
    }

    public boolean hasTag(String cloneName, UUID tag) {
        HashSet<UUID> uuids = this.getUUIDs(cloneName);
        if (uuids == null) {
            return false;
        }
        return uuids.contains(tag);
    }

    public HashSet<UUID> getAllUUIDs() {
        HashSet<UUID> uuids = new HashSet<UUID>();
        for (HashSet<UUID> uuids1 : this.tagMap.values()) {
            uuids.addAll(uuids1);
        }
        return uuids;
    }
}

