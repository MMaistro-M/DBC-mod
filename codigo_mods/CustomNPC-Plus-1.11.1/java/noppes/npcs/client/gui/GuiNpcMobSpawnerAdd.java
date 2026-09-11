/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.world.World
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.clone.CloneAllTagsShortPacket;
import kamkeel.npcs.network.packets.request.clone.CloneFolderListPacket;
import kamkeel.npcs.network.packets.request.clone.ClonePreSavePacket;
import kamkeel.npcs.network.packets.request.clone.CloneSavePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import noppes.npcs.client.controllers.ClientCloneController;
import noppes.npcs.client.gui.SubGuiClonerNPCTags;
import noppes.npcs.client.gui.SubGuiClonerQuickTags;
import noppes.npcs.client.gui.SubGuiFolderSelect;
import noppes.npcs.client.gui.util.GuiButtonBiDirectional;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.CloneFolder;
import noppes.npcs.controllers.data.Tag;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNpcMobSpawnerAdd
extends GuiNPCInterface
implements GuiYesNoCallback,
IGuiData,
ISubGuiListener {
    private Entity toClone;
    private NBTTagCompound compound;
    private static boolean serverSide = false;
    private static int saveMode = 0;
    private static int tab = 1;
    private static String folder = null;
    public boolean isNPC = false;
    private ArrayList<String> clientFolderNames = new ArrayList();
    private static ArrayList<String> serverFolderNames = new ArrayList();
    public static NBTTagList tagsCompound;
    public static HashSet<String> addTags;
    public static ArrayList<String> allTags;
    public static HashSet<UUID> addTagUUIDs;
    public static HashMap<String, UUID> tagMap;

    public GuiNpcMobSpawnerAdd(NBTTagCompound compound) {
        this.toClone = EntityList.func_75615_a((NBTTagCompound)compound, (World)Minecraft.func_71410_x().field_71441_e);
        this.compound = compound;
        tagsCompound = new NBTTagList();
        if (this.toClone instanceof EntityNPCInterface) {
            this.isNPC = true;
            tagsCompound = this.compound.func_150295_c("TagUUIDs", 8);
        }
        this.setBackground("menubg.png");
        this.xSize = 256;
        this.ySize = 216;
        this.closeOnEsc = true;
        if (addTags == null) {
            addTags = new HashSet();
        }
        if (addTagUUIDs == null) {
            addTagUUIDs = new HashSet();
        }
        if (this.isNPC) {
            PacketClient.sendClient(new CloneAllTagsShortPacket());
        }
        this.buildClientFolderNames();
        if (serverSide) {
            PacketClient.sendClient(new CloneFolderListPacket());
        }
        this.validateFolder();
    }

    private void buildClientFolderNames() {
        this.clientFolderNames.clear();
        if (ClientCloneController.Instance != null) {
            for (CloneFolder f : ClientCloneController.Instance.getFolderList()) {
                this.clientFolderNames.add(f.name);
            }
        }
    }

    private List<String> getActiveFolderList() {
        return serverSide ? serverFolderNames : this.clientFolderNames;
    }

    private void validateFolder() {
        if (saveMode == 1 && folder != null && !this.getActiveFolderList().contains(folder)) {
            folder = null;
        }
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        String name = this.toClone.func_70005_c_();
        this.addLabel(new GuiNpcLabel(0, "Save as", this.guiLeft + 4, this.guiTop + 6));
        this.addTextField(new GuiNpcTextField(0, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 18, 200, 20, name));
        this.addButton(new GuiNpcButton(6, this.guiLeft + 4, this.guiTop + 45, 50, 20, new String[]{"Tab", "Folder"}, saveMode));
        if (saveMode == 0) {
            String[] tabLabels = new String[15];
            for (int i = 0; i < 15; ++i) {
                tabLabels[i] = String.valueOf(i + 1);
            }
            int selectedTab = tab >= 1 && tab <= 15 ? tab - 1 : 0;
            this.addButton(new GuiButtonBiDirectional(2, this.guiLeft + 56, this.guiTop + 45, 90, 20, tabLabels, selectedTab));
        } else {
            String btnText = folder != null ? folder : "Select a Folder";
            this.addButton(new GuiNpcButton(2, this.guiLeft + 56, this.guiTop + 45, 148, 20, btnText));
        }
        GuiNpcButton saveBtn = new GuiNpcButton(0, this.guiLeft + 4, this.guiTop + 70, 80, 20, "gui.save");
        if (saveMode == 1 && folder == null) {
            saveBtn.field_146124_l = false;
        }
        this.addButton(saveBtn);
        this.addButton(new GuiNpcButton(1, this.guiLeft + 86, this.guiTop + 70, 80, 20, "gui.cancel"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 4, this.guiTop + 95, new String[]{"Client side", "Server side"}, serverSide ? 1 : 0));
        if (this.isNPC) {
            this.addButton(new GuiNpcButton(4, this.guiLeft + 4, this.guiTop + 120, 99, 20, "cloner.wandTags"));
            this.addButton(new GuiNpcButton(5, this.guiLeft + 106, this.guiTop + 120, 99, 20, "cloner.npcTags"));
            if (addTags.size() > 0) {
                this.addLabel(new GuiNpcLabel(8, "cloner.wandtagsapplied", this.guiLeft + 10, this.guiTop + 160));
            }
        }
    }

    private void updateDestinationFromSelector() {
        if (saveMode == 0) {
            GuiNpcButton selector = this.getButton(2);
            if (selector == null) {
                return;
            }
            tab = selector.getValue() + 1;
            folder = null;
        }
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0) {
            this.updateDestinationFromSelector();
            String name = this.getTextField(0).func_146179_b();
            if (name.isEmpty()) {
                return;
            }
            if (saveMode == 1 && folder == null) {
                return;
            }
            if (!serverSide) {
                boolean exists;
                if (saveMode == 1 && folder != null && ClientCloneController.Instance != null) {
                    exists = ClientCloneController.Instance.getCloneData(null, name, folder) != null;
                } else {
                    boolean bl = exists = ClientCloneController.Instance.getCloneData(null, name, tab) != null;
                }
                if (exists) {
                    this.displayGuiScreen((GuiScreen)new GuiYesNo((GuiYesNoCallback)this, "Warning", "You are about to overwrite a clone", 1));
                } else {
                    this.func_73878_a(true, 0);
                }
            } else if (saveMode == 1 && folder != null) {
                PacketClient.sendClient(new ClonePreSavePacket(name, folder));
            } else {
                PacketClient.sendClient(new ClonePreSavePacket(name, tab));
            }
        }
        if (id == 1) {
            this.close();
        }
        if (id == 2) {
            if (saveMode == 0) {
                this.updateDestinationFromSelector();
            } else {
                List<String> folders = this.getActiveFolderList();
                this.setSubGui(new SubGuiFolderSelect(folders, folder));
            }
        }
        if (id == 3) {
            boolean wasServerSide = serverSide;
            boolean bl = serverSide = ((GuiNpcButton)guibutton).getValue() == 1;
            if (serverSide && !wasServerSide) {
                PacketClient.sendClient(new CloneFolderListPacket());
            }
            this.validateFolder();
            this.func_73866_w_();
        }
        if (id == 4 && this.isNPC) {
            this.setSubGui(new SubGuiClonerQuickTags(this));
        }
        if (id == 5 && this.isNPC) {
            this.setSubGui(new SubGuiClonerNPCTags((EntityNPCInterface)this.toClone, this));
        }
        if (id == 6) {
            saveMode = ((GuiNpcButton)guibutton).getValue();
            this.func_73866_w_();
        }
    }

    public void func_73878_a(boolean confirm, int id) {
        if (confirm) {
            String name = this.getTextField(0).func_146179_b();
            NBTTagCompound extraTags = new NBTTagCompound();
            if (this.isNPC) {
                extraTags = this.setTempTags();
            }
            if (!serverSide) {
                if (this.isNPC) {
                    this.compound.func_74782_a("TagUUIDs", (NBTBase)tagsCompound);
                }
                if (saveMode == 1 && folder != null && ClientCloneController.Instance != null) {
                    ClientCloneController.Instance.addClone(this.compound, name, folder, extraTags);
                } else {
                    ClientCloneController.Instance.addClone(this.compound, name, tab, extraTags);
                }
            } else {
                NBTTagCompound compounder = new NBTTagCompound();
                if (this.isNPC) {
                    compounder.func_74782_a("TagUUIDs", (NBTBase)tagsCompound);
                }
                if (saveMode == 1 && folder != null) {
                    PacketClient.sendClient(new CloneSavePacket(name, folder, extraTags, compounder));
                } else {
                    PacketClient.sendClient(new CloneSavePacket(name, tab, extraTags, compounder));
                }
            }
            this.close();
        } else {
            this.displayGuiScreen(this);
        }
    }

    @Override
    public void save() {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("NameExists")) {
            if (compound.func_74767_n("NameExists")) {
                this.displayGuiScreen((GuiScreen)new GuiYesNo((GuiYesNoCallback)this, "Warning", "You are about to overwrite a clone", 1));
            } else {
                this.func_73878_a(true, 0);
            }
        } else if (compound.func_74764_b("CloneFolders")) {
            NBTTagList folderList = compound.func_150295_c("CloneFolders", 10);
            serverFolderNames.clear();
            for (int i = 0; i < folderList.func_74745_c(); ++i) {
                CloneFolder f = new CloneFolder();
                f.readNBT(folderList.func_150305_b(i));
                serverFolderNames.add(f.name);
            }
            this.validateFolder();
            this.func_73866_w_();
        } else if (compound.func_74764_b("ShortTags")) {
            NBTTagList validTags = compound.func_150295_c("ShortTags", 10);
            tagMap.clear();
            allTags.clear();
            addTagUUIDs.clear();
            if (validTags != null) {
                for (int j = 0; j < validTags.func_74745_c(); ++j) {
                    NBTTagCompound tagStructure = validTags.func_150305_b(j);
                    Tag tag = new Tag();
                    tag.readShortNBT(tagStructure);
                    tagMap.put(tag.name, tag.uuid);
                    addTagUUIDs.add(tag.uuid);
                }
                allTags.addAll(tagMap.keySet());
                allTags.sort(String.CASE_INSENSITIVE_ORDER);
            }
            this.func_73866_w_();
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiFolderSelect) {
            SubGuiFolderSelect sel = (SubGuiFolderSelect)subgui;
            if (sel.selectedFolder != null) {
                folder = sel.selectedFolder;
            }
        }
        this.func_73866_w_();
    }

    public NBTTagCompound setTempTags() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        NBTTagList nbtTagList = new NBTTagList();
        for (String name : addTags) {
            if (tagMap.containsKey(name)) {
                nbtTagList.func_74742_a((NBTBase)new NBTTagString(tagMap.get(name).toString()));
                continue;
            }
            addTags.remove(name);
        }
        nbtTagCompound.func_74782_a("TempTagUUIDs", (NBTBase)nbtTagList);
        return nbtTagCompound;
    }

    static {
        allTags = new ArrayList();
        tagMap = new HashMap();
    }
}

