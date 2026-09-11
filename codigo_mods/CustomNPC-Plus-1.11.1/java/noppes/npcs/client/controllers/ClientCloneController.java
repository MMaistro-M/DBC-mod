/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.client.controllers;

import java.io.File;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.CustomNpcs;
import noppes.npcs.client.controllers.ClientTagMapController;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.controllers.data.TagMap;

public class ClientCloneController
extends ServerCloneController {
    public static ClientCloneController Instance;

    @Override
    public File getDir() {
        File dir = new File(CustomNpcs.Dir, "clones");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    @Override
    public boolean addToTagMap(NBTTagCompound nbttagcompound, String name, int tab) {
        HashSet<UUID> tagUUIDs = new HashSet<UUID>();
        if (nbttagcompound.func_74764_b("TagUUIDs")) {
            NBTTagList nbtTagList = nbttagcompound.func_150295_c("TagUUIDs", 8);
            for (int i = 0; i < nbtTagList.func_74745_c(); ++i) {
                tagUUIDs.add(UUID.fromString(nbtTagList.func_150307_f(i)));
            }
        }
        TagMap tagMap = ClientTagMapController.Instance.getTagMap(tab);
        if (!tagUUIDs.isEmpty()) {
            tagMap.putClone(name, tagUUIDs);
        } else {
            tagMap.removeClone(name);
        }
        ClientTagMapController.Instance.saveTagMap(tagMap);
        return true;
    }

    @Override
    public boolean addToTagMap(NBTTagCompound nbttagcompound, String name, String folderName) {
        HashSet<UUID> tagUUIDs = new HashSet<UUID>();
        if (nbttagcompound.func_74764_b("TagUUIDs")) {
            NBTTagList nbtTagList = nbttagcompound.func_150295_c("TagUUIDs", 8);
            for (int i = 0; i < nbtTagList.func_74745_c(); ++i) {
                tagUUIDs.add(UUID.fromString(nbtTagList.func_150307_f(i)));
            }
        }
        TagMap tagMap = ClientTagMapController.Instance.getTagMap(folderName);
        if (!tagUUIDs.isEmpty()) {
            tagMap.putClone(name, tagUUIDs);
        } else {
            tagMap.removeClone(name);
        }
        ClientTagMapController.Instance.saveTagMap(tagMap);
        return true;
    }

    @Override
    public boolean removeFromTagMap(String name, int tab) {
        TagMap tagMap = ClientTagMapController.Instance.getTagMap(tab);
        if (tagMap.removeClone(name)) {
            ClientTagMapController.Instance.saveTagMap(tagMap);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFromTagMap(String name, String folderName) {
        TagMap tagMap = ClientTagMapController.Instance.getTagMap(folderName);
        if (tagMap.removeClone(name)) {
            ClientTagMapController.Instance.saveTagMap(tagMap);
            return true;
        }
        return false;
    }

    @Override
    protected HashSet<UUID> getTagUUIDsFromTab(String cloneName, int tab) {
        TagMap tagMap = ClientTagMapController.Instance.getTagMap(tab);
        HashSet<UUID> uuids = tagMap.getUUIDs(cloneName);
        return uuids != null ? new HashSet<UUID>(uuids) : new HashSet();
    }

    @Override
    protected HashSet<UUID> getTagUUIDsFromFolder(String cloneName, String folderName) {
        TagMap tagMap = ClientTagMapController.Instance.getTagMap(folderName);
        HashSet<UUID> uuids = tagMap.getUUIDs(cloneName);
        return uuids != null ? new HashSet<UUID>(uuids) : new HashSet();
    }

    @Override
    protected void setTagUUIDsForTab(String cloneName, int tab, HashSet<UUID> tagUUIDs) {
        if (tagUUIDs == null || tagUUIDs.isEmpty()) {
            return;
        }
        TagMap tagMap = ClientTagMapController.Instance.getTagMap(tab);
        tagMap.putClone(cloneName, tagUUIDs);
        ClientTagMapController.Instance.saveTagMap(tagMap);
    }

    @Override
    protected void setTagUUIDsForFolder(String cloneName, String folderName, HashSet<UUID> tagUUIDs) {
        if (tagUUIDs == null || tagUUIDs.isEmpty()) {
            return;
        }
        TagMap tagMap = ClientTagMapController.Instance.getTagMap(folderName);
        tagMap.putClone(cloneName, tagUUIDs);
        ClientTagMapController.Instance.saveTagMap(tagMap);
    }
}

