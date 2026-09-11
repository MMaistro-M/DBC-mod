/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.handler.data.IQuestCategory;
import noppes.npcs.controllers.data.Quest;

public class QuestCategory
implements IQuestCategory {
    public HashMap<Integer, Quest> quests = new HashMap();
    public int id = -1;
    public String title = "";

    public void readNBT(NBTTagCompound nbttagcompound) {
        this.id = nbttagcompound.func_74762_e("Slot");
        this.title = nbttagcompound.func_74779_i("Title");
        NBTTagList questList = this.getFirstValidList(nbttagcompound, 10, "Quests", "Dialogs");
        if (questList.func_74745_c() > 0) {
            for (int ii = 0; ii < questList.func_74745_c(); ++ii) {
                NBTTagCompound nbttagcompound2 = questList.func_150305_b(ii);
                Quest quest = new Quest();
                quest.readNBT(nbttagcompound2);
                quest.category = this;
                this.quests.put(quest.id, quest);
            }
        }
    }

    private NBTTagList getFirstValidList(NBTTagCompound nbt, int type, String ... keys) {
        for (String key : keys) {
            NBTTagList list;
            if (!nbt.func_150297_b(key, 9) || (list = nbt.func_150295_c(key, type)).func_74745_c() <= 0) continue;
            return list;
        }
        return new NBTTagList();
    }

    public NBTTagCompound writeNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("Slot", this.id);
        nbttagcompound.func_74778_a("Title", this.title);
        NBTTagList quests = new NBTTagList();
        for (int questID : this.quests.keySet()) {
            Quest quest = this.quests.get(questID);
            quests.func_74742_a((NBTBase)quest.writeToNBT(new NBTTagCompound()));
        }
        nbttagcompound.func_74782_a("Quests", (NBTBase)quests);
        return nbttagcompound;
    }

    public NBTTagCompound writeSmallNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("Slot", this.id);
        nbttagcompound.func_74778_a("Title", this.title);
        return nbttagcompound;
    }

    public void readSmallNBT(NBTTagCompound nbttagcompound) {
        this.id = nbttagcompound.func_74762_e("Slot");
        this.title = nbttagcompound.func_74779_i("Title");
    }

    @Override
    public List<IQuest> quests() {
        return new ArrayList<IQuest>(this.quests.values());
    }

    @Override
    public String getName() {
        return this.title;
    }

    @Override
    public IQuest create() {
        Quest quest = new Quest();
        quest.category = this;
        return quest;
    }

    @Override
    public int getId() {
        return this.id;
    }
}

