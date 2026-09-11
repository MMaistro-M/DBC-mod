/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.SubGuiEditText;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiCustomScrollIcons;
import noppes.npcs.client.gui.util.GuiCustomScrollTagged;
import noppes.npcs.client.gui.util.GuiDirectory;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiUtil;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiCategoryMoveConfirm;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.data.Category;
import noppes.npcs.controllers.data.Tag;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class GuiDirectoryCategorized
extends GuiDirectory
implements IScrollData,
IGuiData,
GuiYesNoCallback,
ISubGuiListener {
    protected GuiCustomScrollIcons catScroll = new GuiCustomScrollIcons(this, 2);
    protected GuiCustomScrollTagged itemScroll = new GuiCustomScrollTagged(this, 0);
    protected HashMap<String, Integer> catData = new HashMap();
    protected HashMap<String, Integer> itemData = new HashMap();
    protected String prevCatName = "";
    protected String prevItemName = "";
    protected int selectedCatId = -1;
    protected String catSearch = "";
    protected String itemSearch = "";
    protected float zoomed = 60.0f;
    protected float rotation;
    protected int previewX;
    protected int previewY;
    protected int previewW;
    protected int previewH;
    protected int movePhase = 0;
    protected HashSet<String> moveSelection = new HashSet();
    protected static final int MAX_MOVE_ITEMS = 5;
    protected int pendingNewItemCatId = -1;
    private int pendingMoveDestCatId = -1;
    private String pendingMoveDestName = "";
    private boolean itemDataLoaded = false;
    protected boolean leftCollapsed = false;
    private static final ResourceLocation DIRECTORY_ICON = new ResourceLocation("customnpcs", "textures/gui/directory.png");
    private boolean resetItemScroll = false;
    private List<String> navNames = new ArrayList<String>();
    private List<Integer> navIcons = new ArrayList<Integer>();

    public GuiDirectoryCategorized() {
        this.leftPanelPercent = 0.15f;
        this.rightPanelPercent = 0.35f;
        this.minLeftPanelW = 120;
        this.minRightPanelW = 150;
        this.requestCategoryList();
    }

    protected boolean hasCategories() {
        return true;
    }

    @Override
    protected void computeLayout() {
        if (this.leftCollapsed || !this.hasCategories()) {
            this.leftPanelPercent = 0.0f;
            this.minLeftPanelW = 0;
        } else if (this.leftPanelPercent <= 0.0f) {
            this.leftPanelPercent = 0.15f;
            this.minLeftPanelW = 120;
        }
        super.computeLayout();
        if (this.leftPanelW <= 0) {
            int available = this.usableW - 3 * this.gap;
            this.rightPanelW = available / 2;
            this.contentW = available - this.rightPanelW;
            this.contentX = this.originX + this.gap;
            this.rightX = this.contentX + this.contentW + this.gap;
        }
    }

    @Override
    protected void drawPanels() {
        if (this.leftPanelW <= 0) {
            if (this.rightPanelW > 0) {
                GuiUtil.drawRectD(this.rightX - 1, this.contentY - 1, this.rightX + this.rightPanelW + 1, this.originY + this.usableH + 1, this.panelBorder);
            }
        } else {
            super.drawPanels();
        }
    }

    protected abstract String getTitle();

    protected abstract void requestCategoryList();

    protected abstract void requestItemsInCategory(int var1);

    protected abstract void requestItemData(int var1);

    protected abstract void onSaveCategory(Category var1);

    protected abstract void onRemoveCategory(int var1);

    protected abstract void onAddItem(int var1);

    protected abstract void onRemoveItem(int var1);

    protected abstract void onEditItem();

    protected abstract void onCloneItem();

    protected abstract void onItemReceived(NBTTagCompound var1);

    protected abstract boolean hasSelectedItem();

    protected abstract int getSelectedItemId();

    protected GuiScreen getWindowedVariant() {
        return null;
    }

    protected void drawItemPreview(int centerX, int centerY, int mouseX, int mouseY, float partialTicks) {
    }

    protected void drawItemDetails(int x, int y, int w) {
    }

    protected void saveCurrentItem() {
    }

    protected void onMoveNewItem(int catId) {
    }

    protected void setPrevItemName(String name) {
        this.prevItemName = name;
    }

    protected int initExtraTopBarButtons(int x, int topBtnY) {
        return x;
    }

    @Override
    protected void initTopBar(int topBtnY) {
        int x = this.originX + 2;
        GuiNpcButton collapseBtn = new GuiNpcButton(19, x, topBtnY, this.btnH, this.btnH, "");
        collapseBtn.field_146124_l = this.hasCategories() && this.movePhase == 0;
        this.addButton(collapseBtn);
        x += this.btnH + 2;
        x = this.initExtraTopBarButtons(x, topBtnY);
        int topBtnW = 55;
        GuiNpcButton addBtn = new GuiNpcButton(50, x, topBtnY, topBtnW, this.btnH, "gui.add");
        addBtn.field_146124_l = this.selectedCatId >= 0 && this.movePhase == 0;
        this.addButton(addBtn);
        String moveLabel = this.movePhase > 0 ? "gui.moving" : "gui.move";
        GuiNpcButton moveBtn = new GuiNpcButton(54, x += topBtnW + 2, topBtnY, topBtnW, this.btnH, moveLabel);
        moveBtn.field_146124_l = this.selectedCatId >= 0 && !this.leftCollapsed && this.hasCategories();
        this.addButton(moveBtn);
        x += topBtnW + 2;
        if (this.movePhase == 1) {
            GuiNpcButton confirmBtn = new GuiNpcButton(55, x, topBtnY, topBtnW, this.btnH, "gui.confirm");
            confirmBtn.field_146124_l = !this.moveSelection.isEmpty();
            confirmBtn.setTextColor(0x55FFFF);
            this.addButton(confirmBtn);
            x += topBtnW + 2;
        }
        int closeX = this.originX + this.usableW - this.btnH - 2;
        this.addButton(new GuiNpcButton(17, closeX, topBtnY, this.btnH, this.btnH, "X"));
        int minimizeX = closeX - this.btnH - 2;
        if (this.getWindowedVariant() != null) {
            this.addButton(new GuiNpcButton(18, minimizeX, topBtnY, this.btnH, this.btnH, "-"));
        }
    }

    @Override
    protected void initLeftPanel() {
        if (this.leftCollapsed || !this.hasCategories()) {
            return;
        }
        boolean catSelected = this.selectedCatId > 0;
        int bottomRows = catSelected ? 3 : 2;
        int bottomH = bottomRows * (this.btnH + this.gap);
        int navH = this.contentH - bottomH;
        this.buildNavList();
        int savedCatScrollY = this.catScroll.scrollY;
        this.catScroll.clear();
        this.catScroll.colors.clear();
        this.catScroll.setSize(this.leftPanelW, navH);
        this.catScroll.guiLeft = this.originX;
        this.catScroll.guiTop = this.contentY;
        this.catScroll.setListWithIcons(this.navNames, this.navIcons);
        this.catScroll.setSelected(this.prevCatName);
        this.catScroll.scrollY = Math.max(0, Math.min(savedCatScrollY, this.catScroll.maxScrollY));
        if (this.movePhase == 1) {
            this.catScroll.setSelectable(false);
        }
        if (this.movePhase == 2) {
            this.catScroll.setSelectable(true);
            for (String name : this.navNames) {
                this.catScroll.colors.put(name, name.equals(this.prevCatName) ? 0xFF5555 : 0xFFAA00);
            }
        } else if (this.movePhase == 0) {
            this.catScroll.setSelectable(true);
        }
        this.addScroll(this.catScroll);
        int searchY = this.contentY + navH + this.gap;
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.originX, searchY, this.leftPanelW, this.btnH, this.catSearch));
        int addY = searchY + this.btnH + this.gap;
        GuiNpcButton addCatBtn = new GuiNpcButton(40, this.originX, addY, this.leftPanelW, this.btnH, "gui.addCategory");
        if (this.movePhase > 0) {
            addCatBtn.field_146124_l = false;
        }
        this.addButton(addCatBtn);
        if (catSelected) {
            int crudY = addY + this.btnH + this.gap;
            int halfW = (this.leftPanelW - this.gap) / 2;
            GuiNpcButton renameBtn = new GuiNpcButton(41, this.originX, crudY, halfW, this.btnH, "gui.edit");
            if (this.movePhase > 0) {
                renameBtn.field_146124_l = false;
            }
            this.addButton(renameBtn);
            GuiNpcButton removeBtn = new GuiNpcButton(42, this.originX + halfW + this.gap, crudY, halfW, this.btnH, "gui.remove");
            removeBtn.field_146124_l = this.movePhase == 0 && this.isCategoryEmpty(this.selectedCatId);
            this.addButton(removeBtn);
        }
    }

    @Override
    protected void initCenterPanel() {
        int searchH = this.btnH + this.gap;
        int scrollH = this.contentH - (this.movePhase == 0 ? searchH : 0);
        int savedItemScrollY = this.resetItemScroll ? 0 : this.itemScroll.scrollY;
        this.resetItemScroll = false;
        this.itemScroll.clear();
        this.itemScroll.colors.clear();
        this.itemScroll.setSize(this.contentW, scrollH);
        this.itemScroll.guiLeft = this.contentX;
        this.itemScroll.guiTop = this.contentY;
        if (this.movePhase == 1) {
            this.itemScroll.multipleSelection = true;
            this.itemScroll.setSelectable(true);
            this.itemScroll.setSelectedList(this.moveSelection);
        } else if (this.movePhase == 2) {
            this.itemScroll.multipleSelection = true;
            this.itemScroll.setSelectedList(this.moveSelection);
            this.itemScroll.setSelectable(false);
            for (String name : this.moveSelection) {
                this.itemScroll.colors.put(name, 0xFFFF55);
            }
        } else {
            this.itemScroll.multipleSelection = false;
            this.itemScroll.setSelectable(true);
        }
        this.itemScroll.setList(this.getItemSearchList());
        if (this.movePhase == 0) {
            this.itemScroll.setSelected(this.prevItemName);
        }
        this.itemScroll.scrollY = Math.max(0, Math.min(savedItemScrollY, this.itemScroll.maxScrollY));
        this.addScroll(this.itemScroll);
        if (this.movePhase == 0) {
            int searchY = this.contentY + scrollH + this.gap;
            this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.contentX, searchY, this.contentW, this.btnH, this.itemSearch));
        }
    }

    @Override
    protected void initRightPanel(int startY) {
        int bottomH = (this.btnH + this.gap) * 2 + 14;
        this.previewX = this.rightX;
        this.previewY = this.contentY;
        this.previewW = this.rightPanelW;
        this.previewH = this.contentH - bottomH - this.gap;
        int btnY = this.contentY + this.contentH - this.btnH * 2 - this.gap;
        int halfW = (this.rightPanelW - this.gap) / 2;
        GuiNpcButton editBtn = new GuiNpcButton(51, this.rightX, btnY, halfW, this.btnH, "gui.edit");
        editBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0;
        this.addButton(editBtn);
        GuiNpcButton cloneBtn = new GuiNpcButton(52, this.rightX + halfW + this.gap, btnY, halfW, this.btnH, "gui.copy");
        cloneBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0;
        this.addButton(cloneBtn);
        int removeY = btnY + this.btnH + this.gap;
        GuiNpcButton removeBtn = new GuiNpcButton(53, this.rightX, removeY, this.rightPanelW, this.btnH, "gui.remove");
        removeBtn.field_146124_l = this.hasSelectedItem() && this.movePhase == 0;
        removeBtn.setTextColor(0xFF5555);
        this.addButton(removeBtn);
    }

    @Override
    protected void drawOverlay(int mouseX, int mouseY, float partialTicks) {
        int textW;
        GuiNpcButton collapseBtn = this.getButton(19);
        if (collapseBtn != null && collapseBtn.field_146125_m) {
            int texY;
            this.field_146297_k.func_110434_K().func_110577_a(DIRECTORY_ICON);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            int iconSize = 16;
            int iconX = collapseBtn.field_146128_h + (collapseBtn.field_146120_f - iconSize) / 2;
            int iconY = collapseBtn.field_146129_i + (collapseBtn.field_146121_g - iconSize) / 2;
            if (!collapseBtn.field_146124_l) {
                texY = 32;
            } else {
                boolean hovered = mouseX >= collapseBtn.field_146128_h && mouseY >= collapseBtn.field_146129_i && mouseX < collapseBtn.field_146128_h + collapseBtn.field_146120_f && mouseY < collapseBtn.field_146129_i + collapseBtn.field_146121_g;
                texY = hovered ? 16 : 0;
            }
            GuiDirectoryCategorized.func_152125_a((int)iconX, (int)iconY, (float)0.0f, (float)texY, (int)16, (int)16, (int)iconSize, (int)iconSize, (float)256.0f, (float)256.0f);
            GL11.glDisable((int)3042);
        }
        if (!this.hasSubGui()) {
            GuiUtil.drawRectD(this.previewX, this.previewY, this.previewX + this.previewW, this.previewY + this.previewH, -1609560048);
            if (this.isMouseOverPreview(mouseX, mouseY)) {
                float wheel = (float)Mouse.getDWheel() * 0.035f;
                if (wheel != 0.0f) {
                    this.zoomed += wheel;
                    this.zoomed = Math.max(5.0f, Math.min(200.0f, this.zoomed));
                }
                if (Mouse.isButtonDown((int)0) || Mouse.isButtonDown((int)1)) {
                    this.rotation -= (float)Mouse.getDX() * 0.75f;
                }
            }
            if (this.hasSelectedItem()) {
                int centerX = this.previewX + this.previewW / 2;
                int centerY = this.previewY + (int)((float)this.previewH * 0.75f);
                this.drawItemPreview(centerX, centerY, mouseX, mouseY, partialTicks);
            }
            if (this.hasSelectedItem()) {
                this.drawItemDetails(this.previewX + 4, this.previewY + 4, this.previewW - 8);
            }
            if (this.hasSelectedItem() && this.getSelectedItemId() >= 0) {
                String idStr = StatCollector.func_74838_a((String)"gui.id") + ": " + this.getSelectedItemId();
                int idY = this.contentY + this.contentH - this.btnH * 2 - this.gap - 12;
                this.field_146289_q.func_85187_a(idStr, this.previewX + 4, idY, 0xFFFFFF, true);
            }
        }
        if (this.movePhase == 1) {
            String text = String.format(StatCollector.func_74838_a((String)"gui.move.selectItems"), 5);
            textW = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78261_a(text, this.field_146294_l / 2 - textW / 2, this.field_146295_m - this.pad - 10, 0x55FF55);
        } else if (this.movePhase == 2) {
            String text = StatCollector.func_74838_a((String)"gui.move.selectCategory");
            textW = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78261_a(text, this.field_146294_l / 2 - textW / 2, this.field_146295_m - this.pad - 10, 0xFF5555);
        }
    }

    protected boolean isMouseOverPreview(int mouseX, int mouseY) {
        return mouseX >= this.previewX && mouseX <= this.previewX + this.previewW && mouseY >= this.previewY && mouseY <= this.previewY + this.previewH;
    }

    private void buildNavList() {
        this.navNames.clear();
        this.navIcons.clear();
        String searchLower = this.catSearch.toLowerCase();
        String uncatName = "Uncategorized";
        if (searchLower.isEmpty() || uncatName.toLowerCase().contains(searchLower)) {
            this.navNames.add(uncatName);
            this.navIcons.add(1);
        }
        for (String name : this.catData.keySet()) {
            if (name.equals(uncatName) || !searchLower.isEmpty() && !name.toLowerCase().contains(searchLower)) continue;
            this.navNames.add(name);
            this.navIcons.add(2);
        }
    }

    protected boolean isCategoryEmpty(int catId) {
        if (!this.itemDataLoaded) {
            return false;
        }
        return this.itemData == null || this.itemData.isEmpty();
    }

    @Override
    public void func_73869_a(char c, int i) {
        String newText;
        GuiNpcTextField catSearchField;
        String newText2;
        super.func_73869_a(c, i);
        GuiNpcTextField itemSearchField = this.getTextField(1);
        if (itemSearchField != null && !this.itemSearch.equals(newText2 = itemSearchField.func_146179_b().toLowerCase())) {
            this.itemSearch = newText2;
            this.itemScroll.setList(this.getItemSearchList());
        }
        if ((catSearchField = this.getTextField(3)) != null && !this.catSearch.equals(newText = catSearchField.func_146179_b().toLowerCase())) {
            this.catSearch = newText;
            this.buildNavList();
            this.catScroll.setListWithIcons(this.navNames, this.navIcons);
            this.catScroll.setSelected(this.prevCatName);
        }
    }

    protected List<String> getItemSearchList() {
        if (this.selectedCatId < 0) {
            return new ArrayList<String>();
        }
        if (this.itemSearch.isEmpty()) {
            return new ArrayList<String>(this.itemData.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        HashMap<String, HashSet<UUID>> tagMap = this.itemScroll.getItemTagMap();
        block0: for (String name : this.itemData.keySet()) {
            if (name.toLowerCase().contains(this.itemSearch)) {
                list.add(name);
                continue;
            }
            if (tagMap == null || !tagMap.containsKey(name)) continue;
            HashSet<UUID> uuids = tagMap.get(name);
            TagController tc = TagController.getInstance();
            if (tc == null) continue;
            for (UUID uuid : uuids) {
                Tag tag = tc.getTagFromUUID(uuid);
                if (tag == null || !tag.name.toLowerCase().contains(this.itemSearch.replace("[", "").replace("]", ""))) continue;
                list.add(name);
                continue block0;
            }
        }
        return list;
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiYesNo guiyesno;
        GuiScreen windowed;
        int id = guibutton.field_146127_k;
        if (id == 17) {
            this.close();
        }
        if (id == 19 && this.hasCategories() && this.movePhase == 0) {
            this.leftCollapsed = !this.leftCollapsed;
            this.func_73866_w_();
            return;
        }
        if (id == 18 && (windowed = this.getWindowedVariant()) != null) {
            Minecraft.func_71410_x().func_147108_a(windowed);
            return;
        }
        if (id == 50 && this.selectedCatId >= 0 && this.movePhase == 0) {
            this.pendingNewItemCatId = this.selectedCatId > 0 ? this.selectedCatId : -1;
            this.onAddItem(this.selectedCatId);
            this.requestItemsInCategory(this.selectedCatId);
        }
        if (id == 51 && this.hasSelectedItem() && this.movePhase == 0) {
            this.onEditItem();
        }
        if (id == 52 && this.hasSelectedItem() && this.movePhase == 0) {
            this.onCloneItem();
            this.requestItemsInCategory(this.selectedCatId);
        }
        if (id == 53 && this.hasSelectedItem() && this.movePhase == 0) {
            guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.itemScroll.getSelected(), StatCollector.func_74838_a((String)"gui.delete"), 2);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
        if (id == 54) {
            if (this.movePhase == 0) {
                this.movePhase = 1;
                this.moveSelection.clear();
                this.func_73866_w_();
            } else {
                this.movePhase = 0;
                this.moveSelection.clear();
                this.func_73866_w_();
            }
        }
        if (id == 55 && this.movePhase == 1 && !this.moveSelection.isEmpty()) {
            this.movePhase = 2;
            this.func_73866_w_();
        }
        if (id == 40 && this.movePhase == 0) {
            String name = "New";
            while (this.catData.containsKey(name)) {
                name = name + "_";
            }
            Category cat = new Category(-1, name);
            this.onSaveCategory(cat);
        }
        if (id == 41 && this.selectedCatId > 0 && this.movePhase == 0) {
            this.setSubGui(new SubGuiEditText(this.prevCatName));
        }
        if (id == 42 && this.selectedCatId > 0 && this.movePhase == 0) {
            guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.prevCatName, StatCollector.func_74838_a((String)"gui.delete"), 5);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        String selected;
        if (guiCustomScroll.id == 2) {
            if (this.movePhase == 1) {
                return;
            }
            selected = this.catScroll.getSelected();
            if (selected == null) {
                return;
            }
            if (this.movePhase == 2) {
                Integer destCatId;
                if (!selected.equals(this.prevCatName) && (destCatId = this.catData.get(selected)) != null) {
                    this.pendingMoveDestCatId = destCatId;
                    this.pendingMoveDestName = selected;
                    this.setSubGui(new SubGuiCategoryMoveConfirm(this.moveSelection.size(), selected));
                }
                return;
            }
            if (!selected.equals(this.prevCatName)) {
                this.pendingNewItemCatId = -1;
                this.itemDataLoaded = false;
                Integer catId = this.catData.get(selected);
                if (catId != null) {
                    this.selectedCatId = catId;
                    this.itemScroll.selected = -1;
                    this.prevItemName = "";
                    this.requestItemsInCategory(this.selectedCatId);
                    this.prevCatName = selected;
                    this.resetItemScroll = true;
                    this.func_73866_w_();
                }
            }
        }
        if (guiCustomScroll.id == 0) {
            Integer itemId;
            this.pendingNewItemCatId = -1;
            if (this.movePhase == 1) {
                if (this.moveSelection.size() > 5) {
                    HashSet<String> trimmed = new HashSet<String>();
                    int count = 0;
                    for (String s : this.moveSelection) {
                        if (count++ >= 5) break;
                        trimmed.add(s);
                    }
                    this.moveSelection.clear();
                    this.moveSelection.addAll(trimmed);
                    this.itemScroll.setSelectedList(this.moveSelection);
                }
                if (this.getButton(55) != null) {
                    this.getButton((int)55).field_146124_l = !this.moveSelection.isEmpty();
                }
                return;
            }
            selected = this.itemScroll.getSelected();
            if (selected != null && !selected.equals(this.prevItemName) && (itemId = this.itemData.get(selected)) != null) {
                this.requestItemData(itemId);
                this.prevItemName = selected;
            }
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0 && this.hasSelectedItem() && this.movePhase == 0) {
            this.onEditItem();
        }
    }

    protected abstract void sendMovePacket(int var1, int var2);

    protected void executeMoveItems(int destCatId) {
        for (String name : this.moveSelection) {
            Integer itemId = this.itemData.get(name);
            if (itemId == null) continue;
            this.sendMovePacket(itemId, destCatId);
        }
        this.movePhase = 0;
        this.moveSelection.clear();
        if (this.selectedCatId >= 0) {
            this.requestItemsInCategory(this.selectedCatId);
        }
        this.func_73866_w_();
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.CATEGORY_LIST) {
            String name = this.catScroll.getSelected();
            this.catData = data;
            this.buildNavList();
            this.catScroll.setListWithIcons(this.navNames, this.navIcons);
            if (name != null) {
                this.catScroll.setSelected(name);
            } else {
                this.catScroll.setSelected(this.prevCatName);
            }
            if (this.selectedCatId < 0 && this.catData.containsKey("Uncategorized")) {
                this.selectedCatId = this.catData.get("Uncategorized");
                this.prevCatName = "Uncategorized";
                this.catScroll.setSelected(this.prevCatName);
                this.requestItemsInCategory(this.selectedCatId);
            }
            this.func_73866_w_();
        } else if (type == EnumScrollData.CATEGORY_GROUP) {
            String name = this.itemScroll.getSelected();
            this.itemData = data;
            this.itemDataLoaded = true;
            this.itemScroll.setList(this.getItemSearchList());
            if (name != null) {
                this.itemScroll.setSelected(name);
            }
            this.func_73866_w_();
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("CategoryTagMap")) {
            HashMap<String, HashSet<UUID>> tagMap = new HashMap<String, HashSet<UUID>>();
            NBTTagList entries = compound.func_150295_c("CategoryTagMap", 10);
            for (int i = 0; i < entries.func_74745_c(); ++i) {
                NBTTagCompound entry = entries.func_150305_b(i);
                String itemName = entry.func_74779_i("Name");
                HashSet<UUID> uuids = TagController.readTagUUIDs(entry, "Tags");
                if (uuids.isEmpty()) continue;
                tagMap.put(itemName, uuids);
            }
            this.itemScroll.setItemTagMap(tagMap);
            return;
        }
        this.onItemReceived(compound);
        if (this.pendingNewItemCatId > 0 && this.hasSelectedItem()) {
            if (this.getSelectedItemId() >= 0) {
                this.sendMovePacket(this.getSelectedItemId(), this.pendingNewItemCatId);
                if (this.selectedCatId >= 0) {
                    this.requestItemsInCategory(this.selectedCatId);
                }
            } else {
                this.onMoveNewItem(this.pendingNewItemCatId);
            }
        }
        this.pendingNewItemCatId = -1;
        this.func_73866_w_();
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiEditText) {
            String name;
            if (!(((SubGuiEditText)subgui).cancelled || this.selectedCatId <= 0 || (name = ((SubGuiEditText)subgui).text) == null || name.isEmpty() || name.equals(this.prevCatName) || this.catData.containsKey(name))) {
                Category cat = new Category(this.selectedCatId, name);
                this.onSaveCategory(cat);
                this.catData.remove(this.prevCatName);
                this.catData.put(name, this.selectedCatId);
                this.prevCatName = name;
            }
            this.catScroll.setSelected(this.prevCatName);
        }
        if (subgui instanceof SubGuiCategoryMoveConfirm) {
            SubGuiCategoryMoveConfirm confirm = (SubGuiCategoryMoveConfirm)subgui;
            if (confirm.confirmed && !this.moveSelection.isEmpty() && this.pendingMoveDestCatId >= 0) {
                this.executeMoveItems(this.pendingMoveDestCatId);
            }
            this.pendingMoveDestCatId = -1;
            this.pendingMoveDestName = "";
        }
        this.onSubGuiClosed(subgui);
    }

    protected void onSubGuiClosed(SubGuiInterface subgui) {
    }

    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 5 && this.selectedCatId > 0) {
            this.onRemoveCategory(this.selectedCatId);
            this.selectedCatId = -1;
            this.prevCatName = "";
            this.itemData.clear();
            this.catScroll.selected = -1;
        }
        if (id == 2 && this.itemScroll.getSelected() != null && this.itemData.containsKey(this.itemScroll.getSelected())) {
            this.onRemoveItem(this.itemData.get(this.itemScroll.getSelected()));
            this.itemData.clear();
            this.prevItemName = "";
            if (this.selectedCatId >= 0) {
                this.requestItemsInCategory(this.selectedCatId);
            }
        }
        this.func_73866_w_();
    }

    @Override
    public void setSelected(String selected) {
    }

    @Override
    public void save() {
    }
}

