/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package noppes.npcs.client.gui.select;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiSelectionListener;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestCategory;

public class GuiQuestSelection
extends SubGuiInterface
implements ICustomScrollListener {
    private HashMap<String, QuestCategory> categoryData = new HashMap();
    private HashMap<String, Quest> questData = new HashMap();
    private GuiCustomScroll scrollCategories;
    private GuiCustomScroll scrollQuests;
    private QuestCategory selectedCategory;
    public Quest selectedQuest;
    private GuiSelectionListener listener;
    private String catSearch = "";
    private String questSearch = "";

    public GuiQuestSelection(int quest) {
        this.drawDefaultBackground = false;
        this.title = "";
        this.setBackground("menubg.png");
        this.xSize = 366;
        this.ySize = 226;
        this.selectedQuest = QuestController.Instance.quests.get(quest);
        if (this.selectedQuest != null) {
            this.selectedCategory = this.selectedQuest.category;
        }
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        if (this.parent instanceof GuiSelectionListener) {
            this.listener = (GuiSelectionListener)this.parent;
        }
        this.addLabel(new GuiNpcLabel(0, "gui.categories", this.guiLeft + 8, this.guiTop + 4));
        this.addLabel(new GuiNpcLabel(1, "quest.quests", this.guiLeft + 184, this.guiTop + 4));
        this.addButton(new GuiNpcButton(2, this.guiLeft + this.xSize - 56, this.guiTop + this.ySize - 35, 50, 20, "gui.done"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + this.xSize - 108, this.guiTop + this.ySize - 35, 50, 20, "gui.cancel"));
        HashMap<String, QuestCategory> categoryData = new HashMap<String, QuestCategory>();
        HashMap<String, Quest> questData = new HashMap<String, Quest>();
        for (QuestCategory category : QuestController.Instance.categories.values()) {
            categoryData.put(category.title, category);
        }
        this.categoryData = categoryData;
        if (this.selectedCategory != null) {
            for (Quest quest : this.selectedCategory.quests.values()) {
                questData.put(quest.title, quest);
            }
        }
        this.questData = questData;
        if (this.scrollCategories == null) {
            this.scrollCategories = new GuiCustomScroll((GuiScreen)this, 0, 0);
            this.scrollCategories.setSize(177, 153);
        }
        this.scrollCategories.setList(this.getCatSearch());
        if (this.selectedCategory != null) {
            this.scrollCategories.setSelected(this.selectedCategory.title);
        }
        this.scrollCategories.guiLeft = this.guiLeft + 4;
        this.scrollCategories.guiTop = this.guiTop + 14;
        this.addScroll(this.scrollCategories);
        this.addTextField(new GuiNpcTextField(33, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 169, 177, 20, this.catSearch));
        if (this.scrollQuests == null) {
            this.scrollQuests = new GuiCustomScroll((GuiScreen)this, 1, 0);
            this.scrollQuests.setSize(177, 153);
        }
        this.scrollQuests.setList(this.getQuestSearch());
        if (this.selectedQuest != null) {
            this.scrollQuests.setSelected(this.selectedQuest.title);
        }
        this.scrollQuests.guiLeft = this.guiLeft + 182;
        this.scrollQuests.guiTop = this.guiTop + 14;
        this.addScroll(this.scrollQuests);
        this.addTextField(new GuiNpcTextField(44, this, this.field_146289_q, this.guiLeft + 182, this.guiTop + 169, 177, 20, this.questSearch));
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(33) != null && this.getTextField(33).func_146206_l()) {
            if (this.catSearch.equals(this.getTextField(33).func_146179_b())) {
                return;
            }
            this.catSearch = this.getTextField(33).func_146179_b().toLowerCase();
            this.scrollCategories.resetScroll();
            this.scrollCategories.setList(this.getCatSearch());
        }
        if (this.getTextField(44) != null && this.getTextField(44).func_146206_l()) {
            if (this.questSearch.equals(this.getTextField(44).func_146179_b())) {
                return;
            }
            this.questSearch = this.getTextField(44).func_146179_b().toLowerCase();
            this.scrollQuests.resetScroll();
            this.scrollQuests.setList(this.getQuestSearch());
        }
    }

    private List<String> getCatSearch() {
        if (this.catSearch.isEmpty()) {
            return Lists.newArrayList(this.categoryData.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : Lists.newArrayList(this.categoryData.keySet())) {
            if (!name.toLowerCase().contains(this.catSearch)) continue;
            list.add(name);
        }
        return list;
    }

    private List<String> getQuestSearch() {
        if (this.selectedCategory == null) {
            return Lists.newArrayList(this.questData.keySet());
        }
        if (this.questSearch.isEmpty()) {
            return new ArrayList<String>(Lists.newArrayList(this.questData.keySet()));
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : Lists.newArrayList(this.questData.keySet())) {
            if (!name.toLowerCase().contains(this.questSearch)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.selectedCategory = this.categoryData.get(this.scrollCategories.getSelected());
            this.selectedQuest = null;
            this.scrollQuests.selected = -1;
            this.scrollQuests.resetScroll();
            this.getTextField(44).func_146180_a("");
            this.questSearch = "";
        }
        if (guiCustomScroll.id == 1) {
            this.selectedQuest = this.questData.get(this.scrollQuests.getSelected());
        }
        this.func_73866_w_();
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (this.selectedQuest == null) {
            return;
        }
        if (this.listener != null) {
            this.listener.selected(this.selectedQuest.id, this.selectedQuest.title);
        }
        this.close();
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 2) {
            if (this.selectedQuest != null) {
                this.customScrollDoubleClicked(null, null);
            } else {
                this.close();
            }
        }
        if (id == 3) {
            this.close();
        }
    }
}

