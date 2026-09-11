/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 */
package noppes.npcs.controllers;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.api.handler.ITagHandler;
import noppes.npcs.api.handler.data.ITag;
import noppes.npcs.controllers.data.Tag;

public class TagController
implements ITagHandler {
    public HashMap<Integer, Tag> tags;
    private static TagController instance;
    private int lastUsedID = 0;

    public TagController() {
        instance = this;
        this.tags = new HashMap();
        this.loadTags();
    }

    public static TagController getInstance() {
        return instance;
    }

    private void loadTags() {
        File saveDir = CustomNpcs.getWorldSaveDirectory();
        if (saveDir == null) {
            return;
        }
        try {
            File file = new File(saveDir, "tags.dat");
            if (file.exists()) {
                this.loadTagsFile(file);
            }
        }
        catch (Exception e) {
            try {
                File file = new File(saveDir, "tags.dat_old");
                if (file.exists()) {
                    this.loadTagsFile(file);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private void loadTagsFile(File file) throws IOException {
        DataInputStream var1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
        this.loadTags(var1);
        var1.close();
    }

    public void loadTags(DataInputStream stream) throws IOException {
        HashMap<Integer, Tag> tags = new HashMap<Integer, Tag>();
        HashMap<UUID, Integer> tagUUIDs = new HashMap<UUID, Integer>();
        NBTTagCompound nbttagcompound1 = CompressedStreamTools.func_74794_a((DataInputStream)stream);
        this.lastUsedID = nbttagcompound1.func_74762_e("lastID");
        NBTTagList list = nbttagcompound1.func_150295_c("NPCTags", 10);
        if (list != null) {
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound = list.func_150305_b(i);
                Tag tag = new Tag();
                tag.readNBT(nbttagcompound);
                tags.put(tag.id, tag);
                tagUUIDs.put(tag.uuid, tag.id);
            }
        }
        this.tags = tags;
    }

    public NBTTagCompound getNBT() {
        NBTTagList list = new NBTTagList();
        for (int slot : this.tags.keySet()) {
            Tag tag = this.tags.get(slot);
            NBTTagCompound nbtfactions = new NBTTagCompound();
            tag.writeNBT(nbtfactions);
            list.func_74742_a((NBTBase)nbtfactions);
        }
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.func_74768_a("lastID", this.lastUsedID);
        nbttagcompound.func_74782_a("NPCTags", (NBTBase)list);
        return nbttagcompound;
    }

    public void saveTags() {
        try {
            File saveDir = CustomNpcs.getWorldSaveDirectory();
            File file = new File(saveDir, "tags.dat_new");
            File file1 = new File(saveDir, "tags.dat_old");
            File file2 = new File(saveDir, "tags.dat");
            CompressedStreamTools.func_74799_a((NBTTagCompound)this.getNBT(), (OutputStream)new FileOutputStream(file));
            if (file1.exists()) {
                file1.delete();
            }
            file2.renameTo(file1);
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
            if (file.exists()) {
                file.delete();
            }
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }

    @Override
    public Tag get(int tagSlot) {
        return this.tags.get(tagSlot);
    }

    @Override
    public List<ITag> list() {
        return new ArrayList<ITag>(this.tags.values());
    }

    public void saveTag(Tag tag) {
        if (tag.id < 0) {
            tag.id = this.getUnusedId();
            while (this.hasName(tag.name)) {
                tag.name = tag.name + "_";
            }
        } else {
            Tag existing = this.tags.get(tag.id);
            if (existing != null && !existing.name.equals(tag.name)) {
                while (this.hasName(tag.name)) {
                    tag.name = tag.name + "_";
                }
            }
        }
        this.tags.remove(tag.id);
        this.tags.put(tag.id, tag);
        this.saveTags();
    }

    public Tag create(Tag tag) {
        this.saveTag(tag);
        return tag;
    }

    @Override
    public Tag create(String name, int color) {
        Tag tag = new Tag();
        tag.name = name;
        tag.color = color;
        this.saveTag(tag);
        return tag;
    }

    public int getUnusedId() {
        if (this.lastUsedID == 0) {
            for (int catid : this.tags.keySet()) {
                if (catid <= this.lastUsedID) continue;
                this.lastUsedID = catid;
            }
        }
        ++this.lastUsedID;
        return this.lastUsedID;
    }

    @Override
    public ITag delete(int id) {
        if (id >= 0 && this.tags.size() > 1) {
            Tag tag = this.tags.remove(id);
            this.saveTags();
            if (tag == null) {
                return null;
            }
            this.saveTags();
            tag.id = -1;
            return tag;
        }
        return null;
    }

    public boolean hasName(String newName) {
        if (newName.trim().isEmpty()) {
            return true;
        }
        for (Tag tag : this.tags.values()) {
            if (!tag.name.equals(newName)) continue;
            return true;
        }
        return false;
    }

    public Tag getTagFromName(String tagname) {
        for (Map.Entry<Integer, Tag> entryTag : TagController.getInstance().tags.entrySet()) {
            if (!entryTag.getValue().name.equalsIgnoreCase(tagname)) continue;
            return entryTag.getValue();
        }
        return null;
    }

    public Tag getTagFromUUID(UUID uuid) {
        for (Map.Entry<Integer, Tag> entryTag : TagController.getInstance().tags.entrySet()) {
            if (!entryTag.getValue().uuid.equals(uuid)) continue;
            return entryTag.getValue();
        }
        return null;
    }

    public String[] getNames() {
        String[] names = new String[this.tags.size()];
        int i = 0;
        for (Tag tag : this.tags.values()) {
            names[i] = tag.name.toLowerCase();
            ++i;
        }
        return names;
    }

    public HashSet<Tag> getAllTags() {
        return new HashSet<Tag>(this.tags.values());
    }

    public static void writeTagUUIDs(NBTTagCompound compound, String key, HashSet<UUID> tagUUIDs) {
        if (tagUUIDs != null && !tagUUIDs.isEmpty()) {
            NBTTagList list = new NBTTagList();
            for (UUID uuid : tagUUIDs) {
                list.func_74742_a((NBTBase)new NBTTagString(uuid.toString()));
            }
            compound.func_74782_a(key, (NBTBase)list);
        }
    }

    public static HashSet<UUID> readTagUUIDs(NBTTagCompound compound, String key) {
        HashSet<UUID> uuids = new HashSet<UUID>();
        if (compound.func_74764_b(key)) {
            NBTTagList list = compound.func_150295_c(key, 8);
            for (int i = 0; i < list.func_74745_c(); ++i) {
                try {
                    uuids.add(UUID.fromString(list.func_150307_f(i)));
                    continue;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    // empty catch block
                }
            }
        }
        return uuids;
    }

    public static void validateTagUUIDs(HashSet<UUID> tagUUIDs) {
        TagController tc = TagController.getInstance();
        if (tc == null) {
            return;
        }
        tagUUIDs.removeIf(uuid -> tc.getTagFromUUID((UUID)uuid) == null);
    }

    public static void sendCategoryTagMap(EntityPlayerMP player, HashMap<String, HashSet<UUID>> itemTags) {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList entries = new NBTTagList();
        TagController tc = TagController.getInstance();
        for (Map.Entry<String, HashSet<UUID>> entry : itemTags.entrySet()) {
            HashSet<UUID> valid = new HashSet<UUID>();
            for (UUID uuid : entry.getValue()) {
                if (tc == null || tc.getTagFromUUID(uuid) == null) continue;
                valid.add(uuid);
            }
            if (valid.isEmpty()) continue;
            NBTTagCompound itemEntry = new NBTTagCompound();
            itemEntry.func_74778_a("Name", entry.getKey());
            TagController.writeTagUUIDs(itemEntry, "Tags", valid);
            entries.func_74742_a((NBTBase)itemEntry);
        }
        compound.func_74782_a("CategoryTagMap", (NBTBase)entries);
        GuiDataPacket.sendGuiData(player, compound);
    }
}

