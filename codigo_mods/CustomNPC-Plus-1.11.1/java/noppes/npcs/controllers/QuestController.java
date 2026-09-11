/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.network.enums.EnumSyncType;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.api.handler.IQuestHandler;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.handler.data.IQuestCategory;
import noppes.npcs.constants.EnumProfileSync;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestCategory;
import noppes.npcs.util.NBTJsonUtil;

public class QuestController
implements IQuestHandler {
    public HashMap<Integer, QuestCategory> categoriesSync = new HashMap();
    public HashMap<Integer, QuestCategory> categories = new HashMap();
    public HashMap<Integer, Quest> quests = new HashMap();
    public HashMap<Integer, Quest> sharedQuests = new HashMap();
    public static QuestController Instance = new QuestController();
    private int lastUsedCatID = 0;
    private int lastUsedQuestID = 0;

    public QuestController() {
        Instance = this;
    }

    public void load() {
        this.categories.clear();
        this.quests.clear();
        this.lastUsedCatID = 0;
        this.lastUsedQuestID = 0;
        try {
            File file = new File(CustomNpcs.getWorldSaveDirectory(), "quests.dat");
            if (file.exists()) {
                this.loadCategoriesOld(file);
                file.delete();
                file = new File(CustomNpcs.getWorldSaveDirectory(), "quests.dat_old");
                if (file.exists()) {
                    file.delete();
                }
                return;
            }
        }
        catch (Exception file) {
            // empty catch block
        }
        File dir = this.getDir();
        if (!dir.exists()) {
            dir.mkdir();
        } else {
            for (File file : dir.listFiles()) {
                if (!file.isDirectory()) continue;
                QuestCategory category = this.loadCategoryDir(file);
                Iterator<Integer> ite = category.quests.keySet().iterator();
                while (ite.hasNext()) {
                    int id = ite.next();
                    if (id > this.lastUsedQuestID) {
                        this.lastUsedQuestID = id;
                    }
                    Quest quest = category.quests.get(id);
                    if (this.quests.containsKey(id)) {
                        LogWriter.error("Duplicate id " + quest.id + " from category " + category.title);
                        ite.remove();
                        continue;
                    }
                    this.quests.put(id, quest);
                    if (!quest.profileOptions.enableOptions || quest.profileOptions.completeControl != EnumProfileSync.Shared && quest.profileOptions.cooldownControl != EnumProfileSync.Shared) continue;
                    this.sharedQuests.put(id, quest);
                }
                category.id = ++this.lastUsedCatID;
                this.categories.put(category.id, category);
            }
        }
    }

    private QuestCategory loadCategoryDir(File dir) {
        QuestCategory category = new QuestCategory();
        category.title = dir.getName();
        for (File file : dir.listFiles()) {
            if (!file.isFile() || !file.getName().endsWith(".json")) continue;
            try {
                Quest quest = new Quest();
                quest.id = Integer.parseInt(file.getName().substring(0, file.getName().length() - 5));
                quest.readNBTPartial(NBTJsonUtil.LoadFile(file));
                category.quests.put(quest.id, quest);
                quest.category = category;
            }
            catch (Exception e) {
                LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
            }
        }
        return category;
    }

    private void loadCategoriesOld(File file) throws Exception {
        NBTTagCompound nbttagcompound1;
        File dir = this.getDir();
        if (!dir.exists()) {
            dir.mkdir();
        }
        try (FileInputStream fis = new FileInputStream(file);){
            nbttagcompound1 = CompressedStreamTools.func_74796_a((InputStream)fis);
        }
        this.lastUsedCatID = nbttagcompound1.func_74762_e("lastID");
        this.lastUsedQuestID = nbttagcompound1.func_74762_e("lastQuestID");
        NBTTagList list = nbttagcompound1.func_150295_c("Data", 10);
        if (list != null) {
            for (int i = 0; i < list.func_74745_c(); ++i) {
                QuestCategory category = new QuestCategory();
                category.readNBT(list.func_150305_b(i));
                this.categories.put(category.id, category);
                this.saveCategory(category);
                Iterator<Map.Entry<Integer, Quest>> ita = category.quests.entrySet().iterator();
                while (ita.hasNext()) {
                    Map.Entry<Integer, Quest> entry = ita.next();
                    Quest quest = entry.getValue();
                    quest.id = entry.getKey();
                    quest.category = category;
                    if (this.quests.containsKey(quest.id)) {
                        ita.remove();
                        continue;
                    }
                    this.saveQuest(category.id, quest);
                }
            }
        }
    }

    public void removeCategory(int category) {
        QuestCategory cat = this.categories.get(category);
        if (cat == null) {
            return;
        }
        File dir = new File(this.getDir(), cat.title);
        if (!dir.delete()) {
            return;
        }
        for (int dia : cat.quests.keySet()) {
            this.quests.remove(dia);
        }
        this.categories.remove(category);
        SyncController.syncRemove(EnumSyncType.QUEST_CATEGORY, category);
    }

    public void saveCategory(QuestCategory category) {
        category.title = NoppesStringUtils.cleanFileName(category.title);
        if (this.categories.containsKey(category.id)) {
            QuestCategory currentCategory = this.categories.get(category.id);
            if (!currentCategory.title.equals(category.title)) {
                while (this.containsCategoryName(category.title)) {
                    category.title = category.title + "_";
                }
                File newdir = new File(this.getDir(), category.title);
                File olddir = new File(this.getDir(), currentCategory.title);
                if (newdir.exists()) {
                    return;
                }
                if (!olddir.renameTo(newdir)) {
                    return;
                }
            }
            category.quests = currentCategory.quests;
        } else {
            if (category.id < 0) {
                ++this.lastUsedCatID;
                category.id = this.lastUsedCatID;
            }
            while (this.containsCategoryName(category.title)) {
                category.title = category.title + "_";
            }
            File dir = new File(this.getDir(), category.title);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
        this.categories.put(category.id, category);
        SyncController.syncUpdate(EnumSyncType.QUEST_CATEGORY, -1, SyncController.updateQuestCat(category));
    }

    private boolean containsCategoryName(String name) {
        name = name.toLowerCase();
        for (QuestCategory cat : this.categories.values()) {
            if (!cat.title.toLowerCase().equals(name)) continue;
            return true;
        }
        return false;
    }

    private boolean containsQuestName(QuestCategory category, Quest quest) {
        for (Quest q : category.quests.values()) {
            if (q.id == quest.id || !q.title.equalsIgnoreCase(quest.title)) continue;
            return true;
        }
        return false;
    }

    public void saveQuest(int categoryID, Quest quest) {
        File dir;
        QuestCategory category = this.categories.get(categoryID);
        if (category == null) {
            return;
        }
        quest.category = category;
        while (this.containsQuestName(quest.category, quest)) {
            quest.title = quest.title + "_";
        }
        if (quest.id < 0) {
            ++this.lastUsedQuestID;
            quest.id = this.lastUsedQuestID;
        }
        this.quests.put(quest.id, quest);
        category.quests.put(quest.id, quest);
        this.sharedQuests.remove(quest.id);
        if (quest.profileOptions.enableOptions && (quest.profileOptions.completeControl == EnumProfileSync.Shared || quest.profileOptions.cooldownControl == EnumProfileSync.Shared)) {
            this.sharedQuests.put(quest.id, quest);
        }
        if (!(dir = new File(this.getDir(), category.title)).exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, quest.id + ".json_new");
        File file2 = new File(dir, quest.id + ".json");
        try {
            NBTJsonUtil.SaveFile(file, quest.writeToNBTPartial(new NBTTagCompound()));
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
            SyncController.syncUpdate(EnumSyncType.QUEST, category.id, quest.writeToNBT(new NBTTagCompound()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeQuest(Quest quest) {
        File file = new File(new File(this.getDir(), quest.category.title), quest.id + ".json");
        if (!file.delete()) {
            return;
        }
        this.quests.remove(quest.id);
        quest.category.quests.remove(quest.id);
    }

    private File getDir() {
        return new File(CustomNpcs.getWorldSaveDirectory(), "quests");
    }

    @Override
    public List<IQuestCategory> categories() {
        return new ArrayList<IQuestCategory>(this.categories.values());
    }

    @Override
    public IQuest get(int id) {
        return this.quests.get(id);
    }
}

