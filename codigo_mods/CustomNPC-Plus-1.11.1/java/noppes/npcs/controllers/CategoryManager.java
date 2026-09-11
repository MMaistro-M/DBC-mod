/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import noppes.npcs.controllers.data.Category;

public class CategoryManager {
    public static final int UNCATEGORIZED_ID = 0;
    public static final String UNCATEGORIZED_NAME = "Uncategorized";
    private File baseDir;
    private final HashMap<Integer, Category> categories = new HashMap();
    private final HashMap<Integer, Integer> itemCategoryMap = new HashMap();
    private int lastUsedCatID = 0;

    public void loadCategories(File baseDir) {
        this.loadCategories(baseDir, new String[0]);
    }

    public void loadCategories(File baseDir, String ... excludeDirs) {
        this.baseDir = baseDir;
        this.categories.clear();
        this.itemCategoryMap.clear();
        this.lastUsedCatID = 0;
        if (!baseDir.exists()) {
            return;
        }
        HashSet<String> excludeSet = new HashSet<String>(Arrays.asList(excludeDirs));
        File[] files = baseDir.listFiles();
        if (files == null) {
            return;
        }
        for (File sub : files) {
            if (!sub.isDirectory() || excludeSet.contains(sub.getName())) continue;
            ++this.lastUsedCatID;
            this.categories.put(this.lastUsedCatID, new Category(this.lastUsedCatID, sub.getName()));
        }
    }

    public void registerItem(int itemId, int catId) {
        if (catId > 0) {
            this.itemCategoryMap.put(itemId, catId);
        }
    }

    public void removeItem(int itemId) {
        this.itemCategoryMap.remove(itemId);
    }

    public boolean isLoaded() {
        return this.baseDir != null;
    }

    public File getItemDir(int itemId) {
        int catId = this.itemCategoryMap.getOrDefault(itemId, 0);
        return this.getCategoryDir(catId);
    }

    public File getCategoryDir(int catId) {
        if (catId == 0) {
            return this.baseDir;
        }
        Category cat = this.categories.get(catId);
        if (cat != null) {
            return new File(this.baseDir, cat.title);
        }
        return this.baseDir;
    }

    public HashMap<Integer, Category> getCategories() {
        return this.categories;
    }

    public Category createCategory(String name) {
        if (this.baseDir == null) {
            return null;
        }
        while (this.hasCategoryName(name)) {
            name = name + "_";
        }
        ++this.lastUsedCatID;
        Category cat = new Category(this.lastUsedCatID, name);
        this.categories.put(cat.id, cat);
        File dir = new File(this.baseDir, name);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return cat;
    }

    public void saveCategory(Category cat) {
        if (cat.id <= 0 || this.baseDir == null) {
            return;
        }
        Category existing = this.categories.get(cat.id);
        if (existing != null && !existing.title.equals(cat.title)) {
            String newTitle = cat.title;
            while (this.hasCategoryName(newTitle)) {
                newTitle = newTitle + "_";
            }
            File oldDir = new File(this.baseDir, existing.title);
            File newDir = new File(this.baseDir, newTitle);
            if (oldDir.exists() && !newDir.exists()) {
                oldDir.renameTo(newDir);
            }
            cat.title = newTitle;
        }
        this.categories.put(cat.id, cat);
    }

    public boolean removeCategory(int catId) {
        return this.removeCategory(catId, null);
    }

    public boolean removeCategory(int catId, Set<Integer> allItemIds) {
        if (this.baseDir == null) {
            return false;
        }
        if (catId <= 0) {
            return false;
        }
        Category cat = this.categories.get(catId);
        if (cat == null) {
            return false;
        }
        if (allItemIds != null ? !this.isCategoryEmpty(catId, allItemIds) : this.itemCategoryMap.containsValue(catId)) {
            return false;
        }
        File dir = new File(this.baseDir, cat.title);
        if (dir.exists()) {
            String[] contents = dir.list();
            if (contents != null && contents.length > 0) {
                return false;
            }
            dir.delete();
        }
        this.categories.remove(catId);
        Iterator<Map.Entry<Integer, Integer>> it = this.itemCategoryMap.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() != catId) continue;
            it.remove();
        }
        return true;
    }

    public void moveItem(int itemId, String fileName, int destCatId) {
        if (this.baseDir == null) {
            return;
        }
        int oldCatId = this.itemCategoryMap.getOrDefault(itemId, 0);
        if (oldCatId == destCatId) {
            return;
        }
        File oldDir = this.getCategoryDir(oldCatId);
        File newDir = this.getCategoryDir(destCatId);
        if (!newDir.exists()) {
            newDir.mkdirs();
        }
        File oldFile = new File(oldDir, fileName);
        File newFile = new File(newDir, fileName);
        if (oldFile.exists()) {
            oldFile.renameTo(newFile);
        }
        if (destCatId == 0) {
            this.itemCategoryMap.remove(itemId);
        } else {
            this.itemCategoryMap.put(itemId, destCatId);
        }
    }

    public boolean hasCategoryName(String name) {
        for (Category cat : this.categories.values()) {
            if (!cat.title.equalsIgnoreCase(name)) continue;
            return true;
        }
        return false;
    }

    public boolean isCategoryEmpty(int catId, Set<Integer> allItemIds) {
        if (catId == 0) {
            for (int id : allItemIds) {
                if (this.itemCategoryMap.containsKey(id)) continue;
                return false;
            }
            return true;
        }
        for (Map.Entry<Integer, Integer> entry : this.itemCategoryMap.entrySet()) {
            if (entry.getValue() != catId || !allItemIds.contains(entry.getKey())) continue;
            return false;
        }
        return true;
    }

    public Category getCategory(int catId) {
        return this.categories.get(catId);
    }

    public int getItemCategory(int itemId) {
        return this.itemCategoryMap.getOrDefault(itemId, 0);
    }

    public List<Integer> getItemsInCategory(int catId, Set<Integer> allItemIds) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int itemId : allItemIds) {
            int assignedCat = this.itemCategoryMap.getOrDefault(itemId, 0);
            if (assignedCat != catId) continue;
            result.add(itemId);
        }
        return result;
    }

    public Map<String, Integer> getCategoryScrollData() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(UNCATEGORIZED_NAME, 0);
        for (Category cat : this.categories.values()) {
            map.put(cat.title, cat.id);
        }
        return map;
    }

    public void deleteFile(String fileName) {
        if (this.baseDir == null || !this.baseDir.exists()) {
            return;
        }
        File file = new File(this.baseDir, fileName);
        if (file.exists()) {
            file.delete();
            return;
        }
        File[] subs = this.baseDir.listFiles();
        if (subs != null) {
            for (File sub : subs) {
                File subFile;
                if (!sub.isDirectory() || !(subFile = new File(sub, fileName)).exists()) continue;
                subFile.delete();
                return;
            }
        }
    }
}

