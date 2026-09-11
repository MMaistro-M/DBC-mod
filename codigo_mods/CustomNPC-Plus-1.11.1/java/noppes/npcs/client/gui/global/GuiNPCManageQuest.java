/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.quest.QuestCategoriesGetPacket;
import kamkeel.npcs.network.packets.request.quest.QuestCategoryGetPacket;
import kamkeel.npcs.network.packets.request.quest.QuestCategoryRemovePacket;
import kamkeel.npcs.network.packets.request.quest.QuestCategorySavePacket;
import kamkeel.npcs.network.packets.request.quest.QuestGetPacket;
import kamkeel.npcs.network.packets.request.quest.QuestRemovePacket;
import kamkeel.npcs.network.packets.request.quest.QuestSavePacket;
import kamkeel.npcs.network.packets.request.quest.QuestsGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.SubGuiEditText;
import noppes.npcs.client.gui.SubGuiNpcQuest;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestCategory;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCManageQuest
extends GuiNPCInterface2
implements IScrollData,
ISubGuiListener,
ICustomScrollListener,
IGuiData,
GuiYesNoCallback {
    private GuiCustomScroll catScroll;
    public GuiCustomScroll questScroll;
    private String prevCatName = "";
    private String prevQuestName = "";
    public QuestCategory category = new QuestCategory();
    public static Quest quest = new Quest();
    public String nextQuestName = "";
    private HashMap<String, Integer> catData = new HashMap();
    public HashMap<String, Integer> questData = new HashMap();
    private String catSearch = "";
    private String questSearch = "";
    public static GuiScreen Instance;
    private boolean isResizing = false;
    private int initialDragX = 0;
    private int dividerOffset = 143;
    private final int dividerWidth = 5;
    private final int minScrollWidth = 50;
    private final int dividerLineHeight = 20;
    private final int dividerLineYOffset = 0;

    public GuiNPCManageQuest(EntityNPCInterface npc) {
        super(npc);
        Instance = this;
        quest = new Quest();
        PacketClient.sendClient(new QuestCategoriesGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int regionLeft = this.guiLeft + 64;
        int regionRight = this.guiLeft + 355;
        int dividerX = regionLeft + this.dividerOffset;
        if (this.catScroll == null) {
            this.catScroll = new GuiCustomScroll((GuiScreen)this, 0, 0);
        }
        this.catScroll.guiLeft = regionLeft;
        this.catScroll.guiTop = this.guiTop + 4;
        this.catScroll.setSize(dividerX - regionLeft, 185);
        this.addScroll(this.catScroll);
        if (this.questScroll == null) {
            this.questScroll = new GuiCustomScroll((GuiScreen)this, 1, 0);
        }
        this.questScroll.guiLeft = dividerX + 5;
        this.questScroll.guiTop = this.guiTop + 4;
        this.questScroll.setSize(regionRight - (dividerX + 5), 185);
        this.addScroll(this.questScroll);
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, regionLeft, this.guiTop + 4 + 3 + 185, dividerX - regionLeft, 20, this.catSearch));
        this.addTextField(new GuiNpcTextField(66, this, this.field_146289_q, dividerX + 5, this.guiTop + 4 + 3 + 185, regionRight - (dividerX + 5), 20, this.questSearch));
        this.addButton(new GuiNpcButton(44, this.guiLeft + 3, this.guiTop + 8, 58, 20, "gui.categories"));
        this.getButton(44).setEnabled(false);
        this.addButton(new GuiNpcButton(4, this.guiLeft + 3, this.guiTop + 38, 58, 20, "gui.add"));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 3, this.guiTop + 61, 58, 20, "gui.remove"));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 3, this.guiTop + 94, 58, 20, "gui.edit"));
        this.addButton(new GuiNpcButton(33, this.guiLeft + 358, this.guiTop + 8, 58, 20, "quest.quests"));
        this.getButton(33).setEnabled(false);
        this.addButton(new GuiNpcButton(0, this.guiLeft + 358, this.guiTop + 94, 58, 20, "gui.edit"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 358, this.guiTop + 38, 58, 20, "gui.add"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 358, this.guiTop + 61, 58, 20, "gui.remove"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 358, this.guiTop + 117, 58, 20, "gui.copy"));
        if (quest != null && GuiNPCManageQuest.quest.id != -1) {
            this.addLabel(new GuiNpcLabel(0, "ID", this.guiLeft + 358, this.guiTop + 4 + 3 + 185));
            this.addLabel(new GuiNpcLabel(1, GuiNPCManageQuest.quest.id + "", this.guiLeft + 358, this.guiTop + 4 + 3 + 195));
        }
        this.updateButtons();
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        if (!this.hasSubGui()) {
            int regionLeft = this.guiLeft + 64;
            int dividerX = regionLeft + this.dividerOffset;
            int regionTop = this.guiTop + 4;
            int regionHeight = 185;
            int handleTop = regionTop + (regionHeight - 20) / 2 + 0;
            GuiNPCManageQuest.func_73734_a((int)(dividerX + 1), (int)handleTop, (int)(dividerX + 5 - 1), (int)(handleTop + 20), (int)-9408400);
        }
    }

    @Override
    public void func_73864_a(int mouseX, int mouseY, int mouseButton) {
        if (!this.hasSubGui()) {
            int regionLeft = this.guiLeft + 64;
            int dividerX = regionLeft + this.dividerOffset;
            int regionTop = this.guiTop + 4;
            int regionHeight = 185;
            int handleTop = regionTop + (regionHeight - 20) / 2 + 0;
            int handleBottom = handleTop + 20;
            if (mouseX >= dividerX && mouseX <= dividerX + 5 && mouseY >= handleTop && mouseY <= handleBottom) {
                this.isResizing = true;
                resizingActive = true;
                this.initialDragX = mouseX;
                return;
            }
        }
        super.func_73864_a(mouseX, mouseY, mouseButton);
    }

    @Override
    public void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        if (this.isResizing) {
            int dx = mouseX - this.initialDragX;
            this.initialDragX = mouseX;
            this.dividerOffset += dx;
            int regionLeft = this.guiLeft + 64;
            int regionRight = this.guiLeft + 355;
            int minOffset = 50;
            int maxOffset = regionRight - regionLeft - 5 - 50;
            if (this.dividerOffset < minOffset) {
                this.dividerOffset = minOffset;
            }
            if (this.dividerOffset > maxOffset) {
                this.dividerOffset = maxOffset;
            }
            int dividerX = regionLeft + this.dividerOffset;
            this.catScroll.setSize(dividerX - regionLeft, 185);
            this.questScroll.guiLeft = dividerX + 5;
            this.questScroll.setSize(regionRight - (dividerX + 5), 185);
            if (this.getTextField(55) != null) {
                this.getTextField((int)55).field_146218_h = dividerX - regionLeft;
            }
            if (this.getTextField(66) != null) {
                this.getTextField((int)66).field_146218_h = regionRight - (dividerX + 5);
                this.getTextField((int)66).field_146209_f = dividerX + 5;
            }
            return;
        }
        super.func_146273_a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void func_146286_b(int mouseX, int mouseY, int state) {
        if (this.isResizing) {
            this.isResizing = false;
            resizingActive = false;
            return;
        }
        super.func_146286_b(mouseX, mouseY, state);
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l()) {
            if (this.catSearch.equals(this.getTextField(55).func_146179_b())) {
                return;
            }
            this.catSearch = this.getTextField(55).func_146179_b().toLowerCase();
            this.catScroll.resetScroll();
            this.catScroll.setList(this.getCatSearch());
        }
        if (this.getTextField(66) != null && this.getTextField(66).func_146206_l()) {
            if (this.questSearch.equals(this.getTextField(66).func_146179_b())) {
                return;
            }
            this.questSearch = this.getTextField(66).func_146179_b().toLowerCase();
            this.questScroll.resetScroll();
            this.questScroll.setList(this.getQuestSearch());
        }
    }

    public void resetQuestList() {
        if (this.questScroll != null) {
            this.questSearch = "";
            if (this.getTextField(66) != null) {
                this.getTextField(66).func_146180_a("");
            }
            this.questScroll.setList(this.getQuestSearch());
        }
    }

    private List<String> getCatSearch() {
        if (this.catSearch.isEmpty()) {
            return new ArrayList<String>(this.catData.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.catData.keySet()) {
            if (!name.toLowerCase().contains(this.catSearch)) continue;
            list.add(name);
        }
        return list;
    }

    private List<String> getQuestSearch() {
        if (this.category != null) {
            if (this.category.id < 0) {
                return new ArrayList<String>();
            }
        } else {
            return new ArrayList<String>();
        }
        if (this.questSearch.isEmpty()) {
            return new ArrayList<String>(this.questData.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.questData.keySet()) {
            if (!name.toLowerCase().contains(this.questSearch)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        GuiYesNo guiyesno;
        String name;
        int id = guibutton.field_146127_k;
        if (id == 6) {
            if (this.category != null && this.category.id > -1) {
                this.setSubGui(new SubGuiEditText(this.category.title));
            } else {
                this.getCategory(false);
            }
        }
        if (id == 4) {
            name = "New";
            while (this.catData.containsKey(name)) {
                name = name + "_";
            }
            if (this.catScroll != null) {
                this.setPrevCatName(name);
            }
            QuestCategory category = new QuestCategory();
            category.title = name;
            PacketClient.sendClient(new QuestCategorySavePacket(category.writeNBT(new NBTTagCompound())));
        }
        if (id == 5 && this.catData.containsKey(this.catScroll.getSelected())) {
            guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.catScroll.getSelected(), StatCollector.func_74838_a((String)"gui.delete"), 5);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
        if (this.category != null && this.category.id >= 0) {
            Quest quest;
            if (id == 1) {
                name = "New";
                while (this.questData.containsKey(name)) {
                    name = name + "_";
                }
                if (this.questScroll != null) {
                    this.setPrevQuestName(name);
                }
                quest = new Quest();
                quest.title = name;
                PacketClient.sendClient(new QuestSavePacket(this.category.id, quest.writeToNBT(new NBTTagCompound()), true));
            }
            if (id == 2 && this.questData.containsKey(this.questScroll.getSelected())) {
                guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.questScroll.getSelected(), StatCollector.func_74838_a((String)"gui.delete"), 2);
                this.displayGuiScreen((GuiScreen)guiyesno);
            }
            if (id == 0 && this.questData.containsKey(this.questScroll.getSelected()) && GuiNPCManageQuest.quest != null && GuiNPCManageQuest.quest.id >= 0) {
                this.setSubGui(new SubGuiNpcQuest(this, GuiNPCManageQuest.quest, this.category.id));
            }
            if (id == 3 && this.questData.containsKey(this.questScroll.getSelected()) && GuiNPCManageQuest.quest != null && GuiNPCManageQuest.quest.id >= 0) {
                name = GuiNPCManageQuest.quest.title;
                while (this.questData.containsKey(name)) {
                    name = name + "_";
                }
                if (this.questScroll != null) {
                    this.setPrevQuestName(name);
                }
                quest = new Quest();
                quest.readNBTPartial(GuiNPCManageQuest.quest.writeToNBT(new NBTTagCompound()));
                quest.title = name;
                PacketClient.sendClient(new QuestSavePacket(this.category.id, quest.writeToNBT(new NBTTagCompound()), true));
            }
        }
        this.updateButtons();
    }

    public void updateButtons() {
        boolean questEnabled;
        boolean enabled;
        boolean bl = enabled = this.category != null;
        if (enabled && this.category.id < 0) {
            enabled = false;
        }
        boolean bl2 = questEnabled = this.questData != null;
        if (questEnabled && (quest == null || GuiNPCManageQuest.quest.id < 0)) {
            questEnabled = false;
        }
        this.getButton(6).setEnabled(enabled);
        this.getButton(1).setEnabled(enabled);
        this.getButton(2).setEnabled(enabled);
        this.getButton(0).setEnabled(enabled && questEnabled);
        this.getButton(3).setEnabled(enabled && questEnabled);
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("NextQuestId")) {
            quest.readNBT(compound);
            this.setPrevQuestName(GuiNPCManageQuest.quest.title);
            this.nextQuestName = compound.func_74764_b("NextQuestTitle") ? compound.func_74779_i("NextQuestTitle") : "";
        } else {
            this.category.readNBT(compound);
            this.setPrevCatName(this.category.title);
            PacketClient.sendClient(new QuestsGetPacket(this.category.id, true));
            this.resetQuestList();
        }
        this.func_73866_w_();
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiEditText) {
            String name;
            if (!((SubGuiEditText)subgui).cancelled && this.category != null && this.category.id > -1 && (name = ((SubGuiEditText)subgui).text) != null && !name.equalsIgnoreCase(this.category.title)) {
                if (!name.isEmpty() && !this.catData.containsKey(name)) {
                    String old = this.category.title;
                    this.catData.remove(this.category.title);
                    this.category.title = name;
                    this.catData.put(this.category.title, this.category.id);
                    this.catScroll.replace(old, this.category.title);
                }
                this.saveType(false);
            }
            this.clearCategory();
        }
        if (subgui instanceof SubGuiNpcQuest) {
            this.saveType(true);
            if (quest != null && GuiNPCManageQuest.quest.id >= 0) {
                this.setPrevQuestName(GuiNPCManageQuest.quest.title);
            }
        }
    }

    public void setPrevCatName(String selectedCat) {
        this.prevCatName = selectedCat;
        this.catScroll.setSelected(this.prevCatName);
    }

    public void setPrevQuestName(String selectedQuest) {
        this.prevQuestName = selectedQuest;
        this.questScroll.setSelected(this.prevQuestName);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.getCategory(false);
        }
        if (guiCustomScroll.id == 1) {
            this.getQuest(false);
        }
    }

    public void getCategory(boolean override) {
        String selected;
        if (this.catScroll.selected != -1 && (!(selected = this.catScroll.getSelected()).equals(this.prevCatName) || override)) {
            this.category = new QuestCategory();
            this.questScroll.selected = -1;
            this.questScroll.resetScroll();
            this.questSearch = "";
            quest = null;
            this.getTextField(66).func_146180_a("");
            PacketClient.sendClient(new QuestCategoryGetPacket(this.catData.get(selected)));
            this.setPrevCatName(selected);
        }
    }

    public void getQuest(boolean override) {
        String selected;
        if (this.questScroll.selected != -1 && (!(selected = this.questScroll.getSelected()).equals(this.prevQuestName) || override)) {
            quest = new Quest();
            QuestGetPacket.getQuest(this.questData.get(selected));
            this.setPrevQuestName(selected);
        }
    }

    public void clearCategory() {
        this.catScroll.setList(this.getCatSearch());
        this.catScroll.selected = -1;
        this.prevCatName = "";
        this.category = new QuestCategory();
        this.questData.clear();
        this.resetQuestList();
    }

    public void saveType(boolean saveQuest) {
        if (saveQuest) {
            if (this.questScroll.selected != -1 && GuiNPCManageQuest.quest.id >= 0 && this.catScroll.selected != -1 && this.category.id >= 0) {
                PacketClient.sendClient(new QuestSavePacket(this.category.id, quest.writeToNBT(new NBTTagCompound()), true));
            }
        } else if (this.catScroll.selected != -1 && this.category.id >= 0) {
            PacketClient.sendClient(new QuestCategorySavePacket(this.category.writeNBT(new NBTTagCompound())));
        }
    }

    @Override
    public void save() {
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.QUEST_GROUP) {
            String name = this.questScroll.getSelected();
            this.questData = data;
            this.questScroll.setList(this.getQuestSearch());
            if (name != null) {
                this.questScroll.setSelected(name);
                this.getQuest(false);
            } else {
                this.questScroll.setSelected(this.prevQuestName);
                this.getQuest(true);
            }
        } else {
            String name = this.catScroll.getSelected();
            this.catData = data;
            this.catScroll.setList(this.getCatSearch());
            if (name != null) {
                this.catScroll.setSelected(name);
                this.getCategory(false);
            } else {
                this.catScroll.setSelected(this.prevCatName);
                this.getCategory(true);
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void setSelected(String selected) {
    }

    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 5 && this.catData.containsKey(this.catScroll.getSelected())) {
            PacketClient.sendClient(new QuestCategoryRemovePacket(this.category.id));
            this.clearCategory();
        }
        if (id == 2) {
            PacketClient.sendClient(new QuestRemovePacket(GuiNPCManageQuest.quest.id, true));
            quest = new Quest();
            this.questData.clear();
        }
        this.updateButtons();
    }
}

