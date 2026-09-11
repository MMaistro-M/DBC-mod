/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.api.handler.ILinkedItemHandler;
import noppes.npcs.api.handler.data.ILinkedItem;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.controllers.CategoryManager;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.data.Category;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.LinkedItem;
import noppes.npcs.controllers.data.LinkedItemScript;
import noppes.npcs.util.NBTJsonUtil;

public class LinkedItemController
implements ILinkedItemHandler {
    private static LinkedItemController Instance;
    private int lastUsedID = 0;
    public HashMap<Integer, LinkedItem> linkedItems = new HashMap();
    public HashMap<Integer, LinkedItemScript> linkedItemsScripts = new HashMap();
    private HashMap<Integer, String> bootOrder;
    public CategoryManager categoryManager = new CategoryManager();

    private LinkedItemController() {
    }

    public static LinkedItemController getInstance() {
        if (Instance == null) {
            Instance = new LinkedItemController();
        }
        return Instance;
    }

    @Override
    public ILinkedItem createItem(String name) {
        return new LinkedItem(name);
    }

    @Override
    public IItemStack createItemStack(int id) {
        LinkedItem linkedItem = this.get(id);
        if (linkedItem != null) {
            return linkedItem.createStack();
        }
        return null;
    }

    @Override
    public void add(ILinkedItem linkedItem) {
        String name;
        if (linkedItem != null && (name = linkedItem.getName()) != null && !name.isEmpty()) {
            int linkedItemId = linkedItem.getId();
            int id = this.linkedItems.containsKey(linkedItemId) ? linkedItemId : this.getUnusedId();
            this.linkedItems.put(id, (LinkedItem)linkedItem);
            linkedItem.setId(id);
            this.addScript(id);
        }
    }

    private void addScript(int id) {
        this.linkedItemsScripts.put(id, new LinkedItemScript());
    }

    @Override
    public LinkedItem remove(int id) {
        this.removeScript(id);
        return this.linkedItems.remove(id);
    }

    private void removeScript(int id) {
        this.linkedItemsScripts.remove(id);
    }

    @Override
    public LinkedItem get(int id) {
        return this.linkedItems.get(id);
    }

    @Override
    public boolean contains(int id) {
        return this.linkedItems.containsKey(id);
    }

    @Override
    public boolean contains(ILinkedItem linkedItem) {
        return this.linkedItems.containsValue((LinkedItem)linkedItem);
    }

    public IScriptHandler getScriptHandler(int id) {
        return this.linkedItemsScripts.get(id);
    }

    public void load() {
        this.lastUsedID = 0;
        this.bootOrder = new HashMap();
        this.linkedItems = new HashMap();
        LogWriter.info("Loading linked items...");
        this.readLinkedMap();
        this.loadLinkedItems();
        LogWriter.info("Done loading linked items.");
    }

    private void loadLinkedItems() {
        this.linkedItems.clear();
        File dir = this.getDir();
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.categoryManager.loadCategories(dir);
        this.loadItemsFromDir(dir, 0);
        for (Map.Entry<Integer, Category> entry : this.categoryManager.getCategories().entrySet()) {
            File catDir = this.categoryManager.getCategoryDir(entry.getKey());
            this.loadItemsFromDir(catDir, entry.getKey());
        }
        this.saveLinkedItemsMap();
    }

    private void loadItemsFromDir(File dir, int catId) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (!file.isFile() || !file.getName().endsWith(".json")) continue;
            try {
                LinkedItem linkedItem = new LinkedItem();
                linkedItem.readFromNBT(NBTJsonUtil.LoadFile(file));
                linkedItem.name = file.getName().substring(0, file.getName().length() - 5);
                if (linkedItem.id == -1) {
                    linkedItem.id = this.getUnusedId();
                }
                int originalID = linkedItem.id;
                int setID = linkedItem.id;
                while (!(!this.bootOrder.containsKey(setID) && !this.linkedItems.containsKey(setID) || this.bootOrder.containsKey(setID) && this.bootOrder.get(setID).equals(linkedItem.name))) {
                    ++setID;
                }
                linkedItem.id = setID;
                if (originalID != setID) {
                    LogWriter.info("Found Linked Item ID Mismatch: " + linkedItem.name + ", New ID: " + setID);
                    linkedItem.save();
                }
                this.linkedItems.put(linkedItem.id, linkedItem);
                this.categoryManager.registerItem(linkedItem.id, catId);
            }
            catch (Exception e) {
                LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
            }
        }
    }

    private File getDir() {
        return new File(CustomNpcs.getWorldSaveDirectory(), "linkeditems");
    }

    public int getUnusedId() {
        if (this.lastUsedID == 0) {
            for (int catid : this.linkedItems.keySet()) {
                if (catid <= this.lastUsedID) continue;
                this.lastUsedID = catid;
            }
        }
        ++this.lastUsedID;
        return this.lastUsedID;
    }

    public boolean hasName(String newName) {
        if (newName.trim().isEmpty()) {
            return true;
        }
        for (LinkedItem linkedItem : this.linkedItems.values()) {
            if (!linkedItem.name.equals(newName)) continue;
            return true;
        }
        return false;
    }

    public ILinkedItem saveLinkedItem(ILinkedItem linkedItem) {
        if (linkedItem.getId() < 0) {
            linkedItem.setId(this.getUnusedId());
            while (this.hasName(linkedItem.getName())) {
                linkedItem.setName(linkedItem.getName() + "_");
            }
        }
        while (this.hasOther(linkedItem.getName(), linkedItem.getId())) {
            linkedItem.setName(linkedItem.getName() + "_");
        }
        TagController.validateTagUUIDs(((LinkedItem)linkedItem).tagUUIDs);
        this.linkedItems.remove(linkedItem.getId());
        this.linkedItems.put(linkedItem.getId(), (LinkedItem)linkedItem);
        this.saveLinkedItemsMap();
        File dir = this.categoryManager.getItemDir(linkedItem.getId());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, linkedItem.getName() + ".json_new");
        File file2 = new File(dir, linkedItem.getName() + ".json");
        try {
            NBTJsonUtil.SaveFile(file, ((LinkedItem)linkedItem).writeToNBT(true));
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
        return this.linkedItems.get(linkedItem.getId());
    }

    public LinkedItem cloneLinkedItem(int originalId) {
        LinkedItem original = this.linkedItems.get(originalId);
        if (original == null) {
            return null;
        }
        int originalCatId = this.categoryManager.getItemCategory(originalId);
        NBTTagCompound nbt = original.writeToNBT(true);
        int newId = this.getUnusedId();
        nbt.func_74768_a("Id", newId);
        LinkedItem clone = new LinkedItem();
        clone.readFromNBT(nbt);
        String name = clone.getName();
        while (this.hasName(name)) {
            name = name + "_";
        }
        clone.name = name;
        if (originalCatId > 0) {
            this.categoryManager.registerItem(newId, originalCatId);
        }
        this.saveLinkedItem(clone);
        return clone;
    }

    private boolean hasOther(String name, int id) {
        for (LinkedItem linkedItem : this.linkedItems.values()) {
            if (linkedItem.getId() == id || !linkedItem.getName().equalsIgnoreCase(name)) continue;
            return true;
        }
        return false;
    }

    public void delete(int id) {
        LinkedItem foundItem;
        LinkedItem linkedItem = this.get(id);
        if (linkedItem != null && (foundItem = this.remove(id)) != null && foundItem.name != null) {
            File dir = this.categoryManager.getItemDir(id);
            File file = new File(dir, foundItem.name + ".json");
            if (file.exists()) {
                file.delete();
            }
            this.categoryManager.removeItem(id);
            this.saveLinkedItemsMap();
        }
    }

    public void deleteLinkedItemFile(String prevName) {
        this.categoryManager.deleteFile(prevName + ".json");
    }

    public File getMapDir() {
        File dir = CustomNpcs.getWorldSaveDirectory();
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public void readLinkedMap() {
        this.bootOrder.clear();
        try {
            File file = new File(this.getMapDir(), "linkeditems.dat");
            if (file.exists()) {
                this.loadLinkedMap(file);
            }
        }
        catch (Exception e) {
            try {
                File file = new File(this.getMapDir(), "linkeditems.dat_old");
                if (file.exists()) {
                    this.loadLinkedMap(file);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public NBTTagCompound writeMapNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList linkedList = new NBTTagList();
        for (Integer key : this.linkedItems.keySet()) {
            LinkedItem linkedItem = this.linkedItems.get(key);
            if (linkedItem.getName().isEmpty()) continue;
            NBTTagCompound linkedCompound = new NBTTagCompound();
            linkedCompound.func_74778_a("Name", linkedItem.getName());
            linkedCompound.func_74768_a("ID", key.intValue());
            linkedList.func_74742_a((NBTBase)linkedCompound);
        }
        nbt.func_74782_a("LinkedItems", (NBTBase)linkedList);
        nbt.func_74768_a("lastID", this.lastUsedID);
        return nbt;
    }

    public void readMapNBT(NBTTagCompound compound) {
        this.lastUsedID = compound.func_74762_e("lastID");
        NBTTagList list = compound.func_150295_c("LinkedItems", 10);
        if (list != null) {
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound = list.func_150305_b(i);
                String linkedName = nbttagcompound.func_74779_i("Name");
                Integer key = nbttagcompound.func_74762_e("ID");
                this.bootOrder.put(key, linkedName);
            }
        }
    }

    private void loadLinkedMap(File file) throws IOException {
        DataInputStream var1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
        this.readLinkedMap(var1);
        var1.close();
    }

    public void readLinkedMap(DataInputStream stream) throws IOException {
        NBTTagCompound nbtCompound = CompressedStreamTools.func_74794_a((DataInputStream)stream);
        this.readMapNBT(nbtCompound);
    }

    public void saveLinkedItemsMap() {
        try {
            File saveDir = this.getMapDir();
            File file = new File(saveDir, "linkeditems.dat_new");
            File file1 = new File(saveDir, "linkeditems.dat_old");
            File file2 = new File(saveDir, "linkeditems.dat");
            CompressedStreamTools.func_74799_a((NBTTagCompound)this.writeMapNBT(), (OutputStream)new FileOutputStream(file));
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

    public Map<String, Integer> getCategoryScrollData() {
        return this.categoryManager.getCategoryScrollData();
    }

    public void moveItemToCategory(int itemId, int catId) {
        LinkedItem item = this.linkedItems.get(itemId);
        if (item == null) {
            return;
        }
        this.categoryManager.moveItem(itemId, item.name + ".json", catId);
        this.saveLinkedItemsMap();
    }

    public Map<String, Integer> getItemsByCategoryScrollData(int catId) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<Integer> itemIds = this.categoryManager.getItemsInCategory(catId, this.linkedItems.keySet());
        for (int itemId : itemIds) {
            LinkedItem item = this.linkedItems.get(itemId);
            if (item == null) continue;
            map.put(item.name, item.id);
        }
        return map;
    }

    public HashMap<String, HashSet<UUID>> getItemTagMapForCategory(int catId) {
        HashMap<String, HashSet<UUID>> tagMap = new HashMap<String, HashSet<UUID>>();
        List<Integer> itemIds = this.categoryManager.getItemsInCategory(catId, this.linkedItems.keySet());
        for (int itemId : itemIds) {
            LinkedItem item = this.linkedItems.get(itemId);
            if (item == null || item.tagUUIDs.isEmpty()) continue;
            tagMap.put(item.name, item.tagUUIDs);
        }
        return tagMap;
    }
}

