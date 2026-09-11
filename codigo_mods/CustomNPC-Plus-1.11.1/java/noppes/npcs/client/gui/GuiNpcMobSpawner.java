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
import kamkeel.npcs.network.packets.request.clone.CloneFolderListPacket;
import kamkeel.npcs.network.packets.request.clone.CloneListPacket;
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
import noppes.npcs.client.gui.GuiNpcMobSpawnerFullscreen;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiCustomScrollCloner;
import noppes.npcs.client.gui.util.GuiMenuSideButton;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IClonerGui;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.controllers.data.Tag;
import noppes.npcs.controllers.data.TagMap;

public class GuiNpcMobSpawner
extends GuiNPCInterface
implements IGuiData,
ICustomScrollListener,
IClonerGui {
    public static boolean isFullscreen = false;
    public static int activeTab = 1;
    public static String activeFolder = null;
    public static int showingClones = 0;
    public static String search = "";
    public static String tagSearch = "";
    public static HashMap<UUID, Tag> tags = new HashMap();
    public static HashMap<String, UUID> tagNames = new HashMap();
    public static HashSet<String> tagFilters = new HashSet();
    public static byte displayTags = 0;
    public static byte filterCondition = 0;
    public static byte ascending = 0;
    public static byte sortType = 0;
    public static int savedContentScrollY = 0;
    public static int savedNavScrollY = 0;
    public TagMap tagMap;
    private final GuiCustomScrollCloner scroll = new GuiCustomScrollCloner(this, 0);
    private final GuiCustomScroll filterScroll = new GuiCustomScroll(this, 1);
    public int posX;
    public int posY;
    public int posZ;
    private List<String> list;
    private List<String> rawList;
    private List<String> tagList;

    public GuiNpcMobSpawner(int i, int j, int k) {
        this.posX = i;
        this.posY = j;
        this.posZ = k;
        this.closeOnEsc = true;
        this.xSize = 354;
        this.setBackground("menubg.png");
        PacketClient.sendClient(new CloneAllTagsPacket());
        if (showingClones == 2) {
            PacketClient.sendClient(new CloneFolderListPacket());
        }
        if (activeFolder != null) {
            if (activeTab < 1 || activeTab > 15) {
                activeTab = 1;
            }
            activeFolder = null;
        }
    }

    @Override
    public int getShowingClones() {
        return showingClones;
    }

    @Override
    public HashMap<UUID, Tag> getTags() {
        return tags;
    }

    @Override
    public TagMap getTagMap() {
        return this.tagMap;
    }

    @Override
    public void func_73866_w_() {
        savedContentScrollY = this.scroll.scrollY;
        super.func_73866_w_();
        this.guiTop += 10;
        this.guiLeft += 30;
        this.scroll.clear();
        this.scroll.setSize(293, 188);
        this.scroll.guiLeft = this.guiLeft + 4;
        this.scroll.guiTop = this.guiTop + 26;
        this.filterScroll.clear();
        this.filterScroll.setSize(140, 166);
        this.filterScroll.guiLeft = this.guiLeft + 4;
        this.filterScroll.guiTop = this.guiTop + 19;
        this.filterScroll.multipleSelection = true;
        this.filterScroll.setSelectedList(tagFilters);
        GuiMenuTopButton closeBtn = new GuiMenuTopButton(17, this.guiLeft + (this.xSize - 22), this.guiTop - 17, "X");
        this.addTopButton(closeBtn);
        int plusX = closeBtn.field_146128_h - (this.field_146289_q.func_78256_a("+") + 12) - 2;
        GuiMenuTopButton fullscreenBtn = new GuiMenuTopButton(61, plusX, this.guiTop - 17, "+");
        this.addTopButton(fullscreenBtn);
        int filterBtnW = this.field_146289_q.func_78256_a(StatCollector.func_74838_a((String)"gui.filters")) + 12;
        int filterX = fullscreenBtn.field_146128_h - filterBtnW - 2;
        GuiMenuTopButton filterBtn = new GuiMenuTopButton(16, filterX, this.guiTop - 17, "gui.filters");
        filterBtn.active = showingClones == 3;
        this.addTopButton(filterBtn);
        GuiMenuTopButton btn = new GuiMenuTopButton(3, this.guiLeft + 4, this.guiTop - 17, "spawner.clones");
        this.addTopButton(btn);
        btn.active = showingClones == 0;
        btn = new GuiMenuTopButton(5, btn, "gui.server");
        this.addTopButton(btn);
        btn.active = showingClones == 2;
        btn = new GuiMenuTopButton(4, btn, "spawner.entities");
        this.addTopButton(btn);
        boolean bl = btn.active = showingClones == 1;
        if (showingClones < 3) {
            this.addScroll(this.scroll);
            this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 4, 293, 20, search));
            this.addButton(new GuiNpcButton(1, this.guiLeft + 298, this.guiTop + 6, 52, 20, "item.monsterPlacer.name"));
            this.addButton(new GuiNpcButton(2, this.guiLeft + 298, this.guiTop + 140, 52, 20, "spawner.mobspawner"));
            if (showingClones == 0 || showingClones == 2) {
                this.addButton(new GuiNpcButton(6, this.guiLeft + 298, this.guiTop + 190, 52, 20, "gui.remove"));
                this.initTabSideButtons();
                this.showClones();
                this.scroll.scrollY = Math.min(savedContentScrollY, Math.max(0, this.scroll.maxScrollY));
            } else {
                this.showEntities();
                this.scroll.scrollY = Math.min(savedContentScrollY, Math.max(0, this.scroll.maxScrollY));
            }
        } else {
            this.showFiltersPage();
        }
    }

    private void initTabSideButtons() {
        GuiMenuSideButton active;
        this.addSideButton(new GuiMenuSideButton(21, this.guiLeft - 70, this.guiTop + 2, 70, 22, "1"));
        this.addSideButton(new GuiMenuSideButton(22, this.guiLeft - 70, this.guiTop + 23, 70, 22, "2"));
        this.addSideButton(new GuiMenuSideButton(23, this.guiLeft - 70, this.guiTop + 44, 70, 22, "3"));
        this.addSideButton(new GuiMenuSideButton(24, this.guiLeft - 70, this.guiTop + 65, 70, 22, "4"));
        this.addSideButton(new GuiMenuSideButton(25, this.guiLeft - 70, this.guiTop + 86, 70, 22, "5"));
        this.addSideButton(new GuiMenuSideButton(26, this.guiLeft - 70, this.guiTop + 107, 35, 22, "6"));
        this.addSideButton(new GuiMenuSideButton(27, this.guiLeft - 35, this.guiTop + 107, 35, 22, "7"));
        this.addSideButton(new GuiMenuSideButton(28, this.guiLeft - 70, this.guiTop + 128, 35, 22, "8"));
        this.addSideButton(new GuiMenuSideButton(29, this.guiLeft - 35, this.guiTop + 128, 35, 22, "9"));
        this.addSideButton(new GuiMenuSideButton(30, this.guiLeft - 70, this.guiTop + 149, 35, 22, "10"));
        this.addSideButton(new GuiMenuSideButton(31, this.guiLeft - 35, this.guiTop + 149, 35, 22, "11"));
        this.addSideButton(new GuiMenuSideButton(32, this.guiLeft - 70, this.guiTop + 170, 35, 22, "12"));
        this.addSideButton(new GuiMenuSideButton(33, this.guiLeft - 35, this.guiTop + 170, 35, 22, "13"));
        this.addSideButton(new GuiMenuSideButton(34, this.guiLeft - 70, this.guiTop + 191, 35, 22, "14"));
        this.addSideButton(new GuiMenuSideButton(35, this.guiLeft - 35, this.guiTop + 191, 35, 22, "15"));
        if (activeTab >= 1 && activeTab <= 15 && (active = this.getSideButton(20 + activeTab)) != null) {
            active.active = true;
        }
    }

    private void showFiltersPage() {
        int baseY = this.guiTop;
        this.addLabel(new GuiNpcLabel(1, StatCollector.func_74838_a((String)"cloner.tagFilters"), this.guiLeft + 7, baseY + 7));
        this.filterScroll.clear();
        this.filterScroll.setSize(140, 166);
        this.filterScroll.guiLeft = this.guiLeft + 4;
        this.filterScroll.guiTop = baseY + 19;
        this.filterScroll.multipleSelection = true;
        this.filterScroll.setSelectedList(tagFilters);
        this.addScroll(this.filterScroll);
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 4, baseY + 190, 140, 20, tagSearch));
        this.addLabel(new GuiNpcLabel(2, StatCollector.func_74838_a((String)"cloner.tagVisibility"), this.guiLeft + 150, baseY + 27));
        this.addButton(new GuiNpcButton(12, this.guiLeft + 215, baseY + 20, new String[]{"display.show", "display.all", "display.hide"}, displayTags));
        this.addLabel(new GuiNpcLabel(3, StatCollector.func_74838_a((String)"filter.contains"), this.guiLeft + 150, baseY + 50));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 215, baseY + 43, new String[]{"filter.any", "filter.all", "filter.notany", "filter.notall"}, filterCondition));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 150, baseY + 66, 130, 20, "gui.deselectAll"));
        this.addLabel(new GuiNpcLabel(4, StatCollector.func_74838_a((String)"cloner.order"), this.guiLeft + 150, baseY + 112));
        this.addButton(new GuiNpcButton(14, this.guiLeft + 215, baseY + 105, new String[]{"cloner.ascending", "cloner.descending"}, ascending));
        this.addLabel(new GuiNpcLabel(5, StatCollector.func_74838_a((String)"cloner.type"), this.guiLeft + 150, baseY + 135));
        this.addButton(new GuiNpcButton(15, this.guiLeft + 215, baseY + 128, new String[]{"cloner.name", "cloner.date"}, sortType));
        this.getButton((int)12).field_146120_f = 65;
        this.getButton((int)12).field_146121_g = 20;
        this.getButton((int)13).field_146120_f = 65;
        this.getButton((int)13).field_146121_g = 20;
        this.getButton((int)14).field_146120_f = 65;
        this.getButton((int)14).field_146121_g = 20;
        this.getButton((int)15).field_146120_f = 65;
        this.getButton((int)15).field_146121_g = 20;
        this.filterScroll.setList(this.getTagList());
    }

    private void showClones() {
        if (showingClones == 2) {
            PacketClient.sendClient(new CloneTagListPacket(activeTab));
            PacketClient.sendClient(new CloneListPacket(activeTab));
            return;
        }
        this.list = sortType == 0 ? ClientCloneController.Instance.getClones(activeTab) : ClientCloneController.Instance.getClonesDate(activeTab);
        this.tagMap = ClientTagMapController.Instance.getTagMap(activeTab);
        this.populateRawListWithTags();
        this.scroll.setList(this.getSearchList(), ascending == 0, sortType == 0);
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
        this.scroll.setList(this.getSearchList(), ascending == 0, sortType == 0);
    }

    private void populateRawListWithTags() {
        this.rawList = new ArrayList<String>(this.list);
        if (this.tagMap != null && (displayTags == 0 || displayTags == 1)) {
            for (int i = 0; i < this.list.size(); ++i) {
                StringBuilder npcName = new StringBuilder(this.list.get(i));
                if (this.tagMap.hasClone(this.list.get(i))) {
                    for (UUID tagUUID : this.tagMap.getUUIDsList(this.list.get(i))) {
                        Tag tag = tags.get(tagUUID);
                        if (tag == null || displayTags != 1 && tag.getIsHidden()) continue;
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
        if (showingClones == 0) {
            return ClientCloneController.Instance.getCloneData((ICommandSender)this.player, sel, activeTab);
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
        if (tagFilters.size() == 0 || showingClones == 1) {
            if (search.isEmpty()) {
                return new ArrayList<String>(this.list);
            }
            ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < this.list.size(); ++i) {
                if (!this.rawList.get(i).toLowerCase().contains(search)) continue;
                list.add(this.list.get(i));
            }
            return list;
        }
        if (search.isEmpty()) {
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
            if (!this.rawList.get(i).toLowerCase().contains(search) || !this.tagMap.hasClone(npcName = this.list.get(i)) || !this.meetsCondition(npcName)) continue;
            list.add(npcName);
        }
        return list;
    }

    private boolean meetsCondition(String name) {
        if (this.tagMap == null) {
            return true;
        }
        boolean conditionMet = true;
        boolean allRequirement = filterCondition == 1 || filterCondition == 3;
        for (String tagName : tagFilters) {
            UUID tagUUID = tagNames.get(tagName);
            if (tagUUID == null) continue;
            boolean hasTag = this.tagMap.hasTag(name, tagUUID);
            if (!allRequirement) {
                if (!hasTag && filterCondition == 0) {
                    conditionMet = false;
                    break;
                }
                if (!hasTag || filterCondition != 2) continue;
                conditionMet = false;
                break;
            }
            if (!hasTag && filterCondition == 1) {
                conditionMet = false;
                break;
            }
            if (!hasTag || filterCondition != 3) continue;
            conditionMet = false;
            break;
        }
        return conditionMet;
    }

    private List<String> getTagList() {
        if (this.tagList == null) {
            this.tagList = new ArrayList<String>();
        }
        if (tagSearch.isEmpty()) {
            return new ArrayList<String>(tagNames.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.tagList) {
            if (!name.toLowerCase().contains(tagSearch)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll guiCustomScroll) {
    }

    @Override
    public void func_73869_a(char c, int i) {
        String newText;
        GuiNpcTextField tagSearchField;
        String newText2;
        super.func_73869_a(c, i);
        GuiNpcTextField searchField = this.getTextField(1);
        if (searchField != null && !search.equals(newText2 = searchField.func_146179_b().toLowerCase())) {
            search = newText2;
            this.scroll.setList(this.getSearchList(), ascending == 0, sortType == 0);
        }
        if ((tagSearchField = this.getTextField(2)) != null && !tagSearch.equals(newText = tagSearchField.func_146179_b().toLowerCase())) {
            tagSearch = newText;
            this.filterScroll.setList(this.getTagList());
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        NBTTagCompound compound;
        String sel;
        int id = guibutton.field_146127_k;
        if (id >= 21 && id <= 35) {
            activeTab = id - 20;
            activeFolder = null;
            this.func_73866_w_();
            return;
        }
        if (id == 3) {
            savedContentScrollY = 0;
            showingClones = 0;
            this.func_73866_w_();
        }
        if (id == 4) {
            savedContentScrollY = 0;
            showingClones = 1;
            this.func_73866_w_();
        }
        if (id == 5) {
            savedContentScrollY = 0;
            showingClones = 2;
            PacketClient.sendClient(new CloneFolderListPacket());
            this.func_73866_w_();
        }
        if (id == 16) {
            savedContentScrollY = 0;
            showingClones = 3;
            this.func_73866_w_();
        }
        if (id == 17) {
            this.close();
        }
        if (id == 61) {
            isFullscreen = true;
            Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiNpcMobSpawnerFullscreen(this.posX, this.posY, this.posZ));
        }
        if (id == 1) {
            if (showingClones == 2) {
                sel = this.scroll.getSelected();
                if (sel == null) {
                    return;
                }
                SpawnMobPacket.Server(this.posX, this.posY, this.posZ, sel, activeTab);
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
            if (showingClones == 2) {
                sel = this.scroll.getSelected();
                if (sel == null) {
                    return;
                }
                MobSpawnerPacket.Server(this.posX, this.posY, this.posZ, sel, activeTab);
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
            if (showingClones == 2) {
                PacketClient.sendClient(new CloneRemovePacket(activeTab, this.scroll.getSelected()));
                return;
            }
            ClientCloneController.Instance.removeClone(this.scroll.getSelected(), activeTab);
            this.func_73866_w_();
            this.scroll.selected = this.scroll.list != null && !this.scroll.list.isEmpty() ? Math.min(prevSelected, this.scroll.list.size() - 1) : -1;
        }
        if (id == 11) {
            tagFilters = new HashSet();
            this.filterScroll.setSelectedList(tagFilters);
            this.func_73866_w_();
        }
        if (id == 12) {
            displayTags = (byte)((GuiNpcButton)guibutton).getValue();
        }
        if (id == 13) {
            filterCondition = (byte)((GuiNpcButton)guibutton).getValue();
        }
        if (id == 14) {
            ascending = (byte)((GuiNpcButton)guibutton).getValue();
        }
        if (id == 15) {
            sortType = (byte)((GuiNpcButton)guibutton).getValue();
        }
    }

    @Override
    public void save() {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("CloneFolders")) {
            this.func_73866_w_();
        } else if (compound.func_74764_b("CloneTags")) {
            this.tagMap = new TagMap(activeTab);
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
                tags = tagsUpdate;
                tagNames = tagNamesUpdate;
                this.tagList = new ArrayList<String>(tagNames.keySet());
                this.filterScroll.setList(this.getTagList());
            }
        } else {
            ArrayList<String> list = new ArrayList<String>();
            if (sortType == 1) {
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
            this.scroll.setList(this.getSearchList(), ascending == 0, sortType == 0);
        }
    }
}

