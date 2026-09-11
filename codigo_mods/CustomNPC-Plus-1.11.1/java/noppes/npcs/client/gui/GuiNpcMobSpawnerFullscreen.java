/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package noppes.npcs.client.gui;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.clone.CloneAllTagsPacket;
import kamkeel.npcs.network.packets.request.clone.CloneFolderCrudPacket;
import kamkeel.npcs.network.packets.request.clone.CloneFolderListPacket;
import kamkeel.npcs.network.packets.request.clone.CloneListPacket;
import kamkeel.npcs.network.packets.request.clone.CloneMovePacket;
import kamkeel.npcs.network.packets.request.clone.CloneRemovePacket;
import kamkeel.npcs.network.packets.request.clone.CloneTagListPacket;
import kamkeel.npcs.network.packets.request.clone.MobSpawnerPacket;
import kamkeel.npcs.network.packets.request.clone.SpawnMobPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import noppes.npcs.client.controllers.ClientCloneController;
import noppes.npcs.client.controllers.ClientTagMapController;
import noppes.npcs.client.gui.GuiNpcMobSpawner;
import noppes.npcs.client.gui.SubGuiCloneFolderName;
import noppes.npcs.client.gui.SubGuiMoveConfirm;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiCustomScrollCloner;
import noppes.npcs.client.gui.util.GuiCustomScrollIcons;
import noppes.npcs.client.gui.util.GuiDirectory;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IClonerGui;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.CloneFolder;
import noppes.npcs.controllers.data.Tag;
import noppes.npcs.controllers.data.TagMap;

public class GuiNpcMobSpawnerFullscreen
extends GuiDirectory
implements IGuiData,
IClonerGui {
    public TagMap tagMap;
    private final GuiCustomScrollCloner scroll = new GuiCustomScrollCloner(this, 0);
    private final GuiCustomScroll filterScroll = new GuiCustomScroll(this, 1);
    private final GuiCustomScrollIcons navScroll = new GuiCustomScrollIcons(this, 2);
    private int posX;
    private int posY;
    private int posZ;
    private List<String> list;
    private List<String> rawList;
    private List<String> tagList;
    private List<String> folderNames = new ArrayList<String>();
    private List<String> navNames = new ArrayList<String>();
    private List<Integer> navIcons = new ArrayList<Integer>();
    private String navSearch = "";
    private int movePhase = 0;
    private HashSet<String> moveSelection = new HashSet();

    public GuiNpcMobSpawnerFullscreen(int posX, int posY, int posZ) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.leftPanelPercent = 0.15f;
        this.rightPanelPercent = 0.08f;
        this.minLeftPanelW = 120;
        this.minRightPanelW = 62;
        PacketClient.sendClient(new CloneAllTagsPacket());
        if (GuiNpcMobSpawner.showingClones == 2) {
            PacketClient.sendClient(new CloneFolderListPacket());
        }
        this.loadFolderNames();
    }

    private void loadFolderNames() {
        this.folderNames.clear();
        if (ClientCloneController.Instance != null) {
            for (CloneFolder folder : ClientCloneController.Instance.getFolderList()) {
                this.folderNames.add(folder.name);
            }
        }
    }

    @Override
    public int getShowingClones() {
        return GuiNpcMobSpawner.showingClones;
    }

    @Override
    public HashMap<UUID, Tag> getTags() {
        return GuiNpcMobSpawner.tags;
    }

    @Override
    public TagMap getTagMap() {
        return this.tagMap;
    }

    @Override
    public void func_73866_w_() {
        GuiNpcMobSpawner.savedContentScrollY = this.scroll.scrollY;
        GuiNpcMobSpawner.savedNavScrollY = this.navScroll.scrollY;
        super.func_73866_w_();
    }

    @Override
    protected void drawPanels() {
        if (GuiNpcMobSpawner.showingClones < 3) {
            super.drawPanels();
        }
    }

    @Override
    protected void initTopBar(int topBtnY) {
        int searchX;
        int searchW;
        int topBtnW = 55;
        int x = this.originX + 2;
        GuiNpcButton clonesBtn = new GuiNpcButton(3, x, topBtnY, topBtnW, this.btnH, "spawner.clones");
        clonesBtn.field_146124_l = GuiNpcMobSpawner.showingClones != 0;
        this.addButton(clonesBtn);
        GuiNpcButton serverBtn = new GuiNpcButton(5, x += topBtnW + 2, topBtnY, topBtnW, this.btnH, "gui.server");
        serverBtn.field_146124_l = GuiNpcMobSpawner.showingClones != 2;
        this.addButton(serverBtn);
        GuiNpcButton entitiesBtn = new GuiNpcButton(4, x += topBtnW + 2, topBtnY, topBtnW, this.btnH, "spawner.entities");
        entitiesBtn.field_146124_l = GuiNpcMobSpawner.showingClones != 1;
        this.addButton(entitiesBtn);
        int closeX = this.originX + this.usableW - this.btnH - 2;
        this.addButton(new GuiNpcButton(17, closeX, topBtnY, this.btnH, this.btnH, "X"));
        int minimizeX = closeX - this.btnH - 2;
        this.addButton(new GuiNpcButton(61, minimizeX, topBtnY, this.btnH, this.btnH, "-"));
        int filterBtnW = 50;
        int filterX = minimizeX - filterBtnW - 2;
        GuiNpcButton filterBtn = new GuiNpcButton(16, filterX, topBtnY, filterBtnW, this.btnH, "gui.filters");
        filterBtn.field_146124_l = GuiNpcMobSpawner.showingClones != 3;
        this.addButton(filterBtn);
        if (GuiNpcMobSpawner.showingClones < 3 && (searchW = filterX - (searchX = x + topBtnW + 6) - 4) > 20) {
            this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, searchX, topBtnY, searchW, this.btnH, GuiNpcMobSpawner.search));
        }
        if (this.movePhase > 0) {
            clonesBtn.field_146124_l = false;
            serverBtn.field_146124_l = false;
            entitiesBtn.field_146124_l = false;
            filterBtn.field_146124_l = false;
        }
    }

    @Override
    protected void initLeftPanel() {
        if (GuiNpcMobSpawner.showingClones >= 3) {
            return;
        }
        boolean folderSelected = GuiNpcMobSpawner.activeFolder != null;
        int bottomRows = folderSelected ? 3 : 2;
        int bottomH = bottomRows * (this.btnH + this.gap);
        int navH = this.contentH - bottomH;
        this.buildNavList();
        int savedNavScroll = GuiNpcMobSpawner.savedNavScrollY;
        this.navScroll.clear();
        this.navScroll.colors.clear();
        this.navScroll.setSize(this.leftPanelW, navH);
        this.navScroll.guiLeft = this.originX;
        this.navScroll.guiTop = this.contentY;
        this.navScroll.setListWithIcons(this.navNames, this.navIcons);
        this.navScroll.setSelected(this.getNavSelection());
        this.navScroll.scrollY = Math.min(savedNavScroll, Math.max(0, this.navScroll.maxScrollY));
        if (this.movePhase == 2) {
            String currentNav = this.getNavSelection();
            for (String name : this.navNames) {
                this.navScroll.colors.put(name, name.equals(currentNav) ? 0xFF5555 : 0xFFAA00);
            }
        }
        this.addScroll(this.navScroll);
        int navSearchY = this.contentY + navH + this.gap;
        this.addTextField(new GuiNpcTextField(3, this, this.field_146289_q, this.originX, navSearchY, this.leftPanelW, this.btnH, this.navSearch));
        int addFolderY = navSearchY + this.btnH + this.gap;
        GuiNpcButton addFolderBtn = new GuiNpcButton(40, this.originX, addFolderY, this.leftPanelW, this.btnH, "+ Add Folder");
        if (this.movePhase > 0) {
            addFolderBtn.field_146124_l = false;
        }
        this.addButton(addFolderBtn);
        if (folderSelected) {
            int folderCrudY = addFolderY + this.btnH + this.gap;
            int halfW = (this.leftPanelW - this.gap) / 2;
            GuiNpcButton renameBtn = new GuiNpcButton(41, this.originX, folderCrudY, halfW, this.btnH, "Rename");
            if (this.movePhase > 0) {
                renameBtn.field_146124_l = false;
            }
            this.addButton(renameBtn);
            GuiNpcButton delBtn = new GuiNpcButton(42, this.originX + halfW + this.gap, folderCrudY, halfW, this.btnH, "gui.remove");
            if (ClientCloneController.Instance != null) {
                List<String> clones = ClientCloneController.Instance.getClones(GuiNpcMobSpawner.activeFolder);
                boolean bl = delBtn.field_146124_l = clones == null || clones.isEmpty();
            }
            if (this.movePhase > 0) {
                delBtn.field_146124_l = false;
            }
            this.addButton(delBtn);
        }
    }

    @Override
    protected void initCenterPanel() {
        if (GuiNpcMobSpawner.showingClones >= 3) {
            this.showFiltersPage();
            return;
        }
        int savedScroll = GuiNpcMobSpawner.savedContentScrollY;
        this.scroll.clear();
        this.scroll.setSize(this.contentW, this.contentH);
        this.scroll.guiLeft = this.contentX;
        this.scroll.guiTop = this.contentY;
        this.addScroll(this.scroll);
        if (GuiNpcMobSpawner.showingClones == 0 || GuiNpcMobSpawner.showingClones == 2) {
            this.scroll.colors.clear();
            if (this.movePhase == 1) {
                this.scroll.multipleSelection = true;
                this.scroll.setSelectable(true);
                this.scroll.setSelectedList(this.moveSelection);
            } else if (this.movePhase == 2) {
                this.scroll.multipleSelection = true;
                this.scroll.setSelectedList(this.moveSelection);
                this.scroll.setSelectable(false);
            } else {
                this.scroll.multipleSelection = false;
                this.scroll.setSelectable(true);
            }
            this.showClones();
            if (this.movePhase == 2) {
                for (String name : this.moveSelection) {
                    this.scroll.colors.put(name, 0xFFFF55);
                }
            }
            this.scroll.scrollY = Math.min(savedScroll, Math.max(0, this.scroll.maxScrollY));
        } else {
            this.showEntities();
            this.scroll.scrollY = Math.min(savedScroll, Math.max(0, this.scroll.maxScrollY));
        }
    }

    @Override
    protected void initRightPanel(int startY) {
        if (GuiNpcMobSpawner.showingClones >= 3) {
            return;
        }
        int btnGap = 4;
        int panelH = this.contentH - 4;
        int midY = startY + panelH / 2;
        int bottomY = this.contentY + this.contentH - this.btnH;
        GuiNpcButton spawnBtn = new GuiNpcButton(1, this.rightX, startY, this.rightPanelW, this.btnH, "item.monsterPlacer.name");
        if (this.movePhase > 0) {
            spawnBtn.field_146124_l = false;
        }
        this.addButton(spawnBtn);
        GuiNpcButton spawnerBtn = new GuiNpcButton(2, this.rightX, midY + btnGap / 2, this.rightPanelW, this.btnH, "spawner.mobspawner");
        if (this.movePhase > 0) {
            spawnerBtn.field_146124_l = false;
        }
        this.addButton(spawnerBtn);
        if (GuiNpcMobSpawner.showingClones == 0 || GuiNpcMobSpawner.showingClones == 2) {
            String moveLabel = this.movePhase > 0 ? "Moving" : "Move";
            GuiNpcButton moveBtn = new GuiNpcButton(50, this.rightX, midY - this.btnH - btnGap / 2, this.rightPanelW, this.btnH, moveLabel);
            this.addButton(moveBtn);
            if (this.movePhase == 1) {
                GuiNpcButton confirmBtn = new GuiNpcButton(51, this.rightX, midY - this.btnH - btnGap / 2 + this.btnH + btnGap, this.rightPanelW, this.btnH, "Confirm");
                confirmBtn.field_146124_l = !this.moveSelection.isEmpty();
                this.addButton(confirmBtn);
            }
            GuiNpcButton removeBtn = new GuiNpcButton(6, this.rightX, bottomY, this.rightPanelW, this.btnH, "gui.remove");
            if (this.movePhase > 0) {
                removeBtn.field_146124_l = false;
            }
            this.addButton(removeBtn);
        }
    }

    @Override
    protected void drawOverlay(int mouseX, int mouseY, float partialTicks) {
        if (this.movePhase == 1) {
            String text = "Select Clones to Move";
            int textW = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78261_a(text, this.field_146294_l / 2 - textW / 2, this.field_146295_m - this.pad - 10, 0x55FF55);
        } else if (this.movePhase == 2) {
            String text = "Select a Tab or Folder";
            int textW = this.field_146289_q.func_78256_a(text);
            this.field_146289_q.func_78261_a(text, this.field_146294_l / 2 - textW / 2, this.field_146295_m - this.pad - 10, 0xFF5555);
        }
    }

    private void buildNavList() {
        this.navNames.clear();
        this.navIcons.clear();
        String searchLower = this.navSearch.toLowerCase();
        for (int i = 1; i <= 15; ++i) {
            String name = "Tab " + i;
            if (!searchLower.isEmpty() && !name.toLowerCase().contains(searchLower)) continue;
            this.navNames.add(name);
            this.navIcons.add(1);
        }
        for (String folder : this.folderNames) {
            if (!searchLower.isEmpty() && !folder.toLowerCase().contains(searchLower)) continue;
            this.navNames.add(folder);
            this.navIcons.add(2);
        }
    }

    private String getNavSelection() {
        if (GuiNpcMobSpawner.activeFolder != null) {
            return GuiNpcMobSpawner.activeFolder;
        }
        if (GuiNpcMobSpawner.activeTab >= 1 && GuiNpcMobSpawner.activeTab <= 15) {
            return "Tab " + GuiNpcMobSpawner.activeTab;
        }
        return "Tab 1";
    }

    private void handleNavClick(String selected) {
        if (selected == null) {
            return;
        }
        if (selected.startsWith("Tab ")) {
            try {
                int tabNum = Integer.parseInt(selected.substring(4));
                if (tabNum >= 1 && tabNum <= 15) {
                    GuiNpcMobSpawner.activeTab = tabNum;
                    GuiNpcMobSpawner.activeFolder = null;
                    this.func_73866_w_();
                    return;
                }
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        GuiNpcMobSpawner.activeFolder = selected;
        GuiNpcMobSpawner.activeTab = -1;
        this.func_73866_w_();
    }

    private void showFiltersPage() {
        int scrollW = Math.min(this.usableW / 2 - this.gap * 2, 240);
        int scrollX = this.originX + this.gap;
        int scrollTopY = this.contentY + 16;
        int scrollH = this.contentH - 16 - this.btnH - this.gap * 2;
        this.addLabel(new GuiNpcLabel(1, StatCollector.func_74838_a((String)"cloner.tagFilters"), scrollX + 2, this.contentY + 4, 0xFFFFFF));
        this.filterScroll.clear();
        this.filterScroll.setSize(scrollW, scrollH);
        this.filterScroll.guiLeft = scrollX;
        this.filterScroll.guiTop = scrollTopY;
        this.filterScroll.multipleSelection = true;
        this.filterScroll.setSelectedList(GuiNpcMobSpawner.tagFilters);
        this.addScroll(this.filterScroll);
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, scrollX, scrollTopY + scrollH + this.gap, scrollW, this.btnH, GuiNpcMobSpawner.tagSearch));
        int col2X = scrollX + scrollW + this.gap * 4;
        int controlW = 90;
        int controlX = col2X + 80;
        int rowH = 28;
        int rowY = scrollTopY;
        this.addLabel(new GuiNpcLabel(2, StatCollector.func_74838_a((String)"cloner.tagVisibility"), col2X, rowY + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(12, controlX, rowY, controlW, this.btnH, new String[]{"display.show", "display.all", "display.hide"}, (int)GuiNpcMobSpawner.displayTags));
        this.addLabel(new GuiNpcLabel(3, StatCollector.func_74838_a((String)"filter.contains"), col2X, (rowY += rowH) + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(13, controlX, rowY, controlW, this.btnH, new String[]{"filter.any", "filter.all", "filter.notany", "filter.notall"}, (int)GuiNpcMobSpawner.filterCondition));
        this.addButton(new GuiNpcButton(11, col2X, rowY += rowH + 4, controlW + 80, this.btnH, "gui.deselectAll"));
        this.addLabel(new GuiNpcLabel(4, StatCollector.func_74838_a((String)"cloner.order"), col2X, (rowY += rowH + 12) + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(14, controlX, rowY, controlW, this.btnH, new String[]{"cloner.ascending", "cloner.descending"}, (int)GuiNpcMobSpawner.ascending));
        this.addLabel(new GuiNpcLabel(5, StatCollector.func_74838_a((String)"cloner.type"), col2X, (rowY += rowH) + 5, 0xFFFFFF));
        this.addButton(new GuiNpcButton(15, controlX, rowY, controlW, this.btnH, new String[]{"cloner.name", "cloner.date"}, (int)GuiNpcMobSpawner.sortType));
        this.filterScroll.setList(this.getTagList());
    }

    private void showClones() {
        if (GuiNpcMobSpawner.activeFolder != null) {
            this.showFolderClones();
            return;
        }
        if (GuiNpcMobSpawner.showingClones == 2) {
            PacketClient.sendClient(new CloneTagListPacket(GuiNpcMobSpawner.activeTab));
            PacketClient.sendClient(new CloneListPacket(GuiNpcMobSpawner.activeTab));
            return;
        }
        this.list = GuiNpcMobSpawner.sortType == 0 ? ClientCloneController.Instance.getClones(GuiNpcMobSpawner.activeTab) : ClientCloneController.Instance.getClonesDate(GuiNpcMobSpawner.activeTab);
        this.tagMap = ClientTagMapController.Instance.getTagMap(GuiNpcMobSpawner.activeTab);
        this.populateRawListWithTags();
        this.scroll.setList(this.getSearchList(), GuiNpcMobSpawner.ascending == 0, GuiNpcMobSpawner.sortType == 0);
    }

    private void showFolderClones() {
        if (GuiNpcMobSpawner.showingClones == 2) {
            PacketClient.sendClient(new CloneTagListPacket(GuiNpcMobSpawner.activeFolder));
            PacketClient.sendClient(new CloneListPacket(GuiNpcMobSpawner.activeFolder));
            return;
        }
        if (ClientCloneController.Instance != null) {
            this.list = GuiNpcMobSpawner.sortType == 0 ? ClientCloneController.Instance.getClones(GuiNpcMobSpawner.activeFolder) : ClientCloneController.Instance.getClonesDate(GuiNpcMobSpawner.activeFolder);
            this.tagMap = ClientTagMapController.Instance.getTagMap(GuiNpcMobSpawner.activeFolder);
        } else {
            this.list = new ArrayList<String>();
            this.tagMap = null;
        }
        this.populateRawListWithTags();
        this.scroll.setList(this.getSearchList(), GuiNpcMobSpawner.ascending == 0, GuiNpcMobSpawner.sortType == 0);
    }

    private void showEntities() {
        Map data = EntityList.field_75625_b;
        ArrayList<String> list = new ArrayList<String>();
        for (Object name : data.keySet()) {
            Class c = (Class)data.get(name);
            try {
                if (!EntityLiving.class.isAssignableFrom(c) || c.getConstructor(World.class) == null || Modifier.isAbstract(c.getModifiers())) continue;
                list.add(name.toString());
            }
            catch (SecurityException e) {
                e.printStackTrace();
            }
            catch (NoSuchMethodException noSuchMethodException) {}
        }
        this.list = list;
        this.rawList = new ArrayList<String>(this.list);
        this.scroll.setList(this.getSearchList(), GuiNpcMobSpawner.ascending == 0, GuiNpcMobSpawner.sortType == 0);
    }

    private void populateRawListWithTags() {
        this.rawList = new ArrayList<String>(this.list);
        if (this.tagMap != null && (GuiNpcMobSpawner.displayTags == 0 || GuiNpcMobSpawner.displayTags == 1)) {
            for (int i = 0; i < this.list.size(); ++i) {
                StringBuilder npcName = new StringBuilder(this.list.get(i));
                if (this.tagMap.hasClone(this.list.get(i))) {
                    for (UUID tagUUID : this.tagMap.getUUIDsList(this.list.get(i))) {
                        Tag tag = GuiNpcMobSpawner.tags.get(tagUUID);
                        if (tag == null || GuiNpcMobSpawner.displayTags != 1 && tag.getIsHidden()) continue;
                        npcName.append(" [" + tag.name + "]");
                    }
                }
                this.rawList.set(i, npcName.toString());
            }
        }
    }

    private NBTTagCompound getCompound() {
        String sel = this.scroll.getSelected();
        if (sel == null) {
            return null;
        }
        if (GuiNpcMobSpawner.showingClones == 0) {
            if (GuiNpcMobSpawner.activeFolder != null && ClientCloneController.Instance != null) {
                return ClientCloneController.Instance.getCloneData((ICommandSender)this.player, sel, GuiNpcMobSpawner.activeFolder);
            }
            return ClientCloneController.Instance.getCloneData((ICommandSender)this.player, sel, GuiNpcMobSpawner.activeTab);
        }
        Entity entity = EntityList.func_75620_a((String)sel, (World)Minecraft.func_71410_x().field_71441_e);
        if (entity == null) {
            return null;
        }
        NBTTagCompound compound = new NBTTagCompound();
        entity.func_70039_c(compound);
        return compound;
    }

    private List<String> getSearchList() {
        if (this.list == null) {
            this.list = new ArrayList<String>();
        }
        if (this.rawList == null) {
            this.rawList = new ArrayList<String>();
        }
        if (GuiNpcMobSpawner.tagFilters.size() == 0 || GuiNpcMobSpawner.showingClones == 1) {
            if (GuiNpcMobSpawner.search.isEmpty()) {
                return new ArrayList<String>(this.list);
            }
            ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < this.list.size(); ++i) {
                if (!this.rawList.get(i).toLowerCase().contains(GuiNpcMobSpawner.search)) continue;
                list.add(this.list.get(i));
            }
            return list;
        }
        if (GuiNpcMobSpawner.search.isEmpty()) {
            ArrayList<String> list = new ArrayList<String>();
            for (String name : this.list) {
                if (!this.tagMap.hasClone(name) || !this.meetsCondition(name)) continue;
                list.add(name);
            }
            return list;
        }
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < this.list.size(); ++i) {
            String npcName;
            if (!this.rawList.get(i).toLowerCase().contains(GuiNpcMobSpawner.search) || !this.tagMap.hasClone(npcName = this.list.get(i)) || !this.meetsCondition(npcName)) continue;
            list.add(npcName);
        }
        return list;
    }

    private boolean meetsCondition(String name) {
        if (this.tagMap == null) {
            return true;
        }
        boolean conditionMet = true;
        boolean allRequirement = GuiNpcMobSpawner.filterCondition == 1 || GuiNpcMobSpawner.filterCondition == 3;
        for (String tagName : GuiNpcMobSpawner.tagFilters) {
            UUID tagUUID = GuiNpcMobSpawner.tagNames.get(tagName);
            if (tagUUID == null) continue;
            boolean hasTag = this.tagMap.hasTag(name, tagUUID);
            if (!allRequirement) {
                if (!hasTag && GuiNpcMobSpawner.filterCondition == 0) {
                    conditionMet = false;
                    break;
                }
                if (!hasTag || GuiNpcMobSpawner.filterCondition != 2) continue;
                conditionMet = false;
                break;
            }
            if (!hasTag && GuiNpcMobSpawner.filterCondition == 1) {
                conditionMet = false;
                break;
            }
            if (!hasTag || GuiNpcMobSpawner.filterCondition != 3) continue;
            conditionMet = false;
            break;
        }
        return conditionMet;
    }

    private List<String> getTagList() {
        if (this.tagList == null) {
            this.tagList = new ArrayList<String>();
        }
        if (GuiNpcMobSpawner.tagSearch.isEmpty()) {
            return new ArrayList<String>(GuiNpcMobSpawner.tagNames.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.tagList) {
            if (!name.toLowerCase().contains(GuiNpcMobSpawner.tagSearch)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0 && this.movePhase == 2) {
            return;
        }
        if (guiCustomScroll.id == 0 && this.movePhase == 1) {
            this.moveSelection = this.scroll.getSelectedList();
            GuiNpcButton confirmBtn = this.getButton(51);
            if (confirmBtn != null) {
                confirmBtn.field_146124_l = !this.moveSelection.isEmpty();
            }
            return;
        }
        if (guiCustomScroll.id == 2) {
            String selected = this.navScroll.getSelected();
            if (selected == null) {
                return;
            }
            if (this.movePhase == 2) {
                if (selected.equals(this.getNavSelection())) {
                    return;
                }
                if (selected.startsWith("Tab ")) {
                    try {
                        int tabNum = Integer.parseInt(selected.substring(4));
                        this.setSubGui(new SubGuiMoveConfirm(this.moveSelection.size(), tabNum, null));
                    }
                    catch (NumberFormatException numberFormatException) {}
                } else {
                    this.setSubGui(new SubGuiMoveConfirm(this.moveSelection.size(), -1, selected));
                }
                return;
            }
            if (this.movePhase == 1) {
                this.movePhase = 0;
                this.moveSelection.clear();
            }
            this.handleNavClick(selected);
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll guiCustomScroll) {
    }

    @Override
    public void func_73869_a(char c, int i) {
        String newNavSearch;
        GuiNpcTextField navSearchField;
        String newText;
        GuiNpcTextField tagSearchField;
        String newText2;
        super.func_73869_a(c, i);
        GuiNpcTextField searchField = this.getTextField(1);
        if (searchField != null && !GuiNpcMobSpawner.search.equals(newText2 = searchField.func_146179_b().toLowerCase())) {
            GuiNpcMobSpawner.search = newText2;
            this.scroll.setList(this.getSearchList(), GuiNpcMobSpawner.ascending == 0, GuiNpcMobSpawner.sortType == 0);
        }
        if ((tagSearchField = this.getTextField(2)) != null && !GuiNpcMobSpawner.tagSearch.equals(newText = tagSearchField.func_146179_b().toLowerCase())) {
            GuiNpcMobSpawner.tagSearch = newText;
            this.filterScroll.setList(this.getTagList());
        }
        if ((navSearchField = this.getTextField(3)) != null && !this.navSearch.equals(newNavSearch = navSearchField.func_146179_b().toLowerCase())) {
            this.navSearch = newNavSearch;
            this.buildNavList();
            this.navScroll.setListWithIcons(this.navNames, this.navIcons);
            this.navScroll.setSelected(this.getNavSelection());
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        NBTTagCompound compound;
        String sel;
        int id = guibutton.field_146127_k;
        if (id == 3) {
            this.movePhase = 0;
            this.moveSelection.clear();
            GuiNpcMobSpawner.savedContentScrollY = 0;
            GuiNpcMobSpawner.savedNavScrollY = 0;
            GuiNpcMobSpawner.showingClones = 0;
            this.loadFolderNames();
            this.func_73866_w_();
        }
        if (id == 4) {
            this.movePhase = 0;
            this.moveSelection.clear();
            GuiNpcMobSpawner.savedContentScrollY = 0;
            GuiNpcMobSpawner.savedNavScrollY = 0;
            GuiNpcMobSpawner.showingClones = 1;
            this.func_73866_w_();
        }
        if (id == 5) {
            this.movePhase = 0;
            this.moveSelection.clear();
            GuiNpcMobSpawner.savedContentScrollY = 0;
            GuiNpcMobSpawner.savedNavScrollY = 0;
            GuiNpcMobSpawner.showingClones = 2;
            PacketClient.sendClient(new CloneFolderListPacket());
            this.func_73866_w_();
        }
        if (id == 16) {
            this.movePhase = 0;
            this.moveSelection.clear();
            GuiNpcMobSpawner.savedContentScrollY = 0;
            GuiNpcMobSpawner.savedNavScrollY = 0;
            GuiNpcMobSpawner.showingClones = 3;
            this.func_73866_w_();
        }
        if (id == 17) {
            this.close();
        }
        if (id == 61) {
            GuiNpcMobSpawner.isFullscreen = false;
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiNpcMobSpawner(this.posX, this.posY, this.posZ));
        }
        if (id == 1) {
            if (GuiNpcMobSpawner.showingClones == 2) {
                sel = this.scroll.getSelected();
                if (sel == null) {
                    return;
                }
                if (GuiNpcMobSpawner.activeFolder != null) {
                    SpawnMobPacket.ServerFolder(this.posX, this.posY, this.posZ, sel, GuiNpcMobSpawner.activeFolder);
                } else {
                    SpawnMobPacket.Server(this.posX, this.posY, this.posZ, sel, GuiNpcMobSpawner.activeTab);
                }
                this.close();
            } else {
                compound = this.getCompound();
                if (compound == null) {
                    return;
                }
                SpawnMobPacket.Client(this.posX, this.posY, this.posZ, compound);
                this.close();
            }
        }
        if (id == 2) {
            if (GuiNpcMobSpawner.showingClones == 2) {
                sel = this.scroll.getSelected();
                if (sel == null) {
                    return;
                }
                if (GuiNpcMobSpawner.activeFolder != null) {
                    MobSpawnerPacket.ServerFolder(this.posX, this.posY, this.posZ, sel, GuiNpcMobSpawner.activeFolder);
                } else {
                    MobSpawnerPacket.Server(this.posX, this.posY, this.posZ, sel, GuiNpcMobSpawner.activeTab);
                }
                this.close();
            } else {
                compound = this.getCompound();
                if (compound == null) {
                    return;
                }
                MobSpawnerPacket.Client(this.posX, this.posY, this.posZ, compound);
                this.close();
            }
        }
        if (id == 6 && this.scroll.getSelected() != null) {
            int prevSelected = this.scroll.selected;
            if (GuiNpcMobSpawner.showingClones == 2) {
                if (GuiNpcMobSpawner.activeFolder != null) {
                    PacketClient.sendClient(new CloneRemovePacket(GuiNpcMobSpawner.activeFolder, this.scroll.getSelected()));
                } else {
                    PacketClient.sendClient(new CloneRemovePacket(GuiNpcMobSpawner.activeTab, this.scroll.getSelected()));
                }
                return;
            }
            if (GuiNpcMobSpawner.activeFolder != null && ClientCloneController.Instance != null) {
                ClientCloneController.Instance.removeClone(this.scroll.getSelected(), GuiNpcMobSpawner.activeFolder);
            } else {
                ClientCloneController.Instance.removeClone(this.scroll.getSelected(), GuiNpcMobSpawner.activeTab);
            }
            this.func_73866_w_();
            this.scroll.selected = this.scroll.list != null && !this.scroll.list.isEmpty() ? Math.min(prevSelected, this.scroll.list.size() - 1) : -1;
        }
        if (id == 50) {
            if (this.movePhase == 0) {
                this.movePhase = 1;
                this.moveSelection.clear();
            } else {
                this.movePhase = 0;
                this.moveSelection.clear();
            }
            this.func_73866_w_();
        }
        if (id == 51 && this.movePhase == 1 && !this.moveSelection.isEmpty()) {
            this.movePhase = 2;
            this.func_73866_w_();
        }
        if (id == 11) {
            GuiNpcMobSpawner.tagFilters = new HashSet();
            this.filterScroll.setSelectedList(GuiNpcMobSpawner.tagFilters);
            this.func_73866_w_();
        }
        if (id == 12) {
            GuiNpcMobSpawner.displayTags = (byte)((GuiNpcButton)guibutton).getValue();
        }
        if (id == 13) {
            GuiNpcMobSpawner.filterCondition = (byte)((GuiNpcButton)guibutton).getValue();
        }
        if (id == 14) {
            GuiNpcMobSpawner.ascending = (byte)((GuiNpcButton)guibutton).getValue();
        }
        if (id == 15) {
            GuiNpcMobSpawner.sortType = (byte)((GuiNpcButton)guibutton).getValue();
        }
        if (id == 40) {
            this.setSubGui(new SubGuiCloneFolderName(""));
        }
        if (id == 41 && GuiNpcMobSpawner.activeFolder != null) {
            this.setSubGui(new SubGuiCloneFolderName(GuiNpcMobSpawner.activeFolder));
        }
        if (id == 42 && GuiNpcMobSpawner.activeFolder != null) {
            if (GuiNpcMobSpawner.showingClones == 2) {
                PacketClient.sendClient(new CloneFolderCrudPacket(2, GuiNpcMobSpawner.activeFolder));
            } else if (ClientCloneController.Instance != null) {
                ClientCloneController.Instance.deleteFolder(GuiNpcMobSpawner.activeFolder);
                this.loadFolderNames();
            }
            GuiNpcMobSpawner.activeFolder = null;
            GuiNpcMobSpawner.activeTab = 1;
            this.func_73866_w_();
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiCloneFolderName) {
            SubGuiCloneFolderName folderGui = (SubGuiCloneFolderName)subgui;
            if (folderGui.cancelled) {
                return;
            }
            String newName = folderGui.getFolderName();
            if (newName == null || newName.isEmpty()) {
                return;
            }
            if (folderGui.isRename()) {
                if (GuiNpcMobSpawner.showingClones == 2) {
                    PacketClient.sendClient(new CloneFolderCrudPacket(1, folderGui.getOriginalName(), newName));
                } else if (ClientCloneController.Instance != null) {
                    ClientCloneController.Instance.renameFolder(folderGui.getOriginalName(), newName);
                    this.loadFolderNames();
                }
                GuiNpcMobSpawner.activeFolder = newName;
                GuiNpcMobSpawner.activeTab = -1;
            } else {
                if (GuiNpcMobSpawner.showingClones == 2) {
                    PacketClient.sendClient(new CloneFolderCrudPacket(0, newName));
                } else if (ClientCloneController.Instance != null) {
                    ClientCloneController.Instance.createFolder(newName);
                    this.loadFolderNames();
                }
                GuiNpcMobSpawner.activeFolder = newName;
                GuiNpcMobSpawner.activeTab = -1;
            }
            this.func_73866_w_();
        }
        if (subgui instanceof SubGuiMoveConfirm) {
            SubGuiMoveConfirm confirm = (SubGuiMoveConfirm)subgui;
            if (confirm.confirmed && !this.moveSelection.isEmpty()) {
                int fromTab = GuiNpcMobSpawner.activeFolder != null ? -1 : GuiNpcMobSpawner.activeTab;
                String fromFolder = GuiNpcMobSpawner.activeFolder;
                int toTab = confirm.destTab;
                String toFolder = confirm.destFolder;
                for (String cloneName : this.moveSelection) {
                    if (GuiNpcMobSpawner.showingClones == 2) {
                        PacketClient.sendClient(new CloneMovePacket(cloneName, fromTab, fromFolder, toTab, toFolder));
                        continue;
                    }
                    if (ClientCloneController.Instance == null) continue;
                    if (fromFolder != null && toFolder != null) {
                        ClientCloneController.Instance.moveClone(cloneName, fromFolder, toFolder);
                        continue;
                    }
                    if (fromFolder != null) {
                        ClientCloneController.Instance.moveClone(cloneName, fromFolder, toTab);
                        continue;
                    }
                    if (toFolder != null) {
                        ClientCloneController.Instance.moveClone(cloneName, fromTab, toFolder);
                        continue;
                    }
                    ClientCloneController.Instance.moveClone(cloneName, fromTab, toTab);
                }
            }
            this.movePhase = 0;
            this.moveSelection.clear();
            this.func_73866_w_();
        }
    }

    @Override
    public void save() {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("CloneFolders")) {
            NBTTagList folderList = compound.func_150295_c("CloneFolders", 10);
            this.folderNames.clear();
            for (int i = 0; i < folderList.func_74745_c(); ++i) {
                CloneFolder folder = new CloneFolder();
                folder.readNBT(folderList.func_150305_b(i));
                this.folderNames.add(folder.name);
            }
            this.func_73866_w_();
        } else if (compound.func_74764_b("MoveSuccess")) {
            this.func_73866_w_();
        } else if (compound.func_74764_b("CloneTags")) {
            this.tagMap = GuiNpcMobSpawner.activeFolder != null ? new TagMap(GuiNpcMobSpawner.activeFolder) : new TagMap(GuiNpcMobSpawner.activeTab);
            NBTTagCompound cloneTags = compound.func_74775_l("CloneTags");
            this.tagMap.readNBT(cloneTags);
        } else if (compound.func_74764_b("AllTags")) {
            NBTTagList validTags = compound.func_150295_c("AllTags", 10);
            if (validTags != null) {
                HashMap<UUID, Tag> tagsUpdate = new HashMap<UUID, Tag>();
                HashMap<String, UUID> tagNamesUpdate = new HashMap<String, UUID>();
                for (int j = 0; j < validTags.func_74745_c(); ++j) {
                    NBTTagCompound tagStructure = validTags.func_150305_b(j);
                    Tag tag = new Tag();
                    tag.readNBT(tagStructure);
                    tagsUpdate.put(tag.uuid, tag);
                    tagNamesUpdate.put(tag.name, tag.uuid);
                }
                GuiNpcMobSpawner.tags = tagsUpdate;
                GuiNpcMobSpawner.tagNames = tagNamesUpdate;
                this.tagList = new ArrayList<String>(GuiNpcMobSpawner.tagNames.keySet());
                this.filterScroll.setList(this.getTagList());
            }
        } else {
            ArrayList<String> list = new ArrayList<String>();
            if (GuiNpcMobSpawner.sortType == 1) {
                NBTTagList nbtlist = compound.func_150295_c("ListDate", 8);
                for (int i = 0; i < nbtlist.func_74745_c(); ++i) {
                    list.add(nbtlist.func_150307_f(i));
                }
            } else {
                NBTTagList nbtlist = compound.func_150295_c("List", 8);
                for (int i = 0; i < nbtlist.func_74745_c(); ++i) {
                    list.add(nbtlist.func_150307_f(i));
                }
            }
            this.list = list;
            this.populateRawListWithTags();
            this.scroll.setList(this.getSearchList(), GuiNpcMobSpawner.ascending == 0, GuiNpcMobSpawner.sortType == 0);
        }
    }
}

