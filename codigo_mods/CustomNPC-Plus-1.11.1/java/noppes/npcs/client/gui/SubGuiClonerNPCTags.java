/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.tags.TagSetPacket;
import kamkeel.npcs.network.packets.request.tags.TagsGetPacket;
import kamkeel.npcs.network.packets.request.tags.TagsNpcGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.GuiNpcMobSpawnerAdd;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.entity.EntityNPCInterface;

public class SubGuiClonerNPCTags
extends SubGuiInterface
implements IGuiData,
IScrollData {
    private GuiCustomScroll scrollTags;
    private GuiCustomScroll npcTags;
    private final ArrayList<String> allTags = new ArrayList();
    private final ArrayList<String> tagNames = new ArrayList();
    private String search = "";
    private final EntityNPCInterface npc;
    private final GuiNpcMobSpawnerAdd mobSpawnerAdd;

    public SubGuiClonerNPCTags(EntityNPCInterface npc, GuiNpcMobSpawnerAdd guiNpcMobSpawnerAdd) {
        this.parent = guiNpcMobSpawnerAdd;
        this.mobSpawnerAdd = guiNpcMobSpawnerAdd;
        PacketClient.sendClient(new TagsGetPacket());
        PacketClient.sendClient(new TagsNpcGetPacket());
        this.setBackground("menubg.png");
        this.xSize = 305;
        this.ySize = 220;
        this.npc = npc;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(5, this.npc.display.name + " " + StatCollector.func_74838_a((String)"tags.tags"), this.guiLeft + 10, this.guiTop + 8));
        this.addLabel(new GuiNpcLabel(1, StatCollector.func_74838_a((String)"tags.allTags"), this.guiLeft + 10, this.guiTop + 22));
        if (this.scrollTags == null) {
            this.scrollTags = new GuiCustomScroll(this, 0);
            this.scrollTags.setSize(110, 145);
        }
        this.scrollTags.guiLeft = this.guiLeft + 10;
        this.scrollTags.guiTop = this.guiTop + 34;
        this.addScroll(this.scrollTags);
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 10, this.guiTop + 24 + 160, 110, 20, this.search));
        this.addLabel(new GuiNpcLabel(2, StatCollector.func_74838_a((String)"tags.selectedTags"), this.guiLeft + 185, this.guiTop + 22));
        if (this.npcTags == null) {
            this.npcTags = new GuiCustomScroll(this, 1);
            this.npcTags.setSize(110, 170);
        }
        this.npcTags.guiLeft = this.guiLeft + 185;
        this.npcTags.guiTop = this.guiTop + 34;
        this.npcTags.setList(this.tagNames);
        this.addScroll(this.npcTags);
        this.addButton(new GuiNpcButton(66, this.guiLeft + 125, this.guiTop + 34, 55, 20, "gui.save"));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 125, this.guiTop + 90, 55, 20, ">"));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 125, this.guiTop + 112, 55, 20, "<"));
        this.addButton(new GuiNpcButton(12, this.guiLeft + 125, this.guiTop + 140, 55, 20, ">>"));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 125, this.guiTop + 162, 55, 20, "<<"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 10 && this.scrollTags.hasSelected() && !this.tagNames.contains(this.scrollTags.getSelected())) {
            this.tagNames.add(this.scrollTags.getSelected());
        }
        if (guibutton.field_146127_k == 12) {
            this.tagNames.clear();
            this.tagNames.addAll(this.allTags);
        }
        if (guibutton.field_146127_k == 11 && this.npcTags.hasSelected()) {
            this.tagNames.remove(this.npcTags.getSelected());
        }
        if (guibutton.field_146127_k == 13) {
            this.tagNames.clear();
        }
        if (guibutton.field_146127_k == 66) {
            this.close();
        }
        this.func_73866_w_();
    }

    @Override
    public void close() {
        super.close();
        this.save();
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        this.allTags.addAll(data.keySet());
        this.allTags.sort(String.CASE_INSENSITIVE_ORDER);
        this.scrollTags.setList(this.allTags);
        this.func_73866_w_();
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        NBTTagList tagList = compound.func_150295_c("TagNames", 8);
        this.tagNames.clear();
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            this.tagNames.add(tagList.func_150307_f(i));
        }
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (k == 0 && this.scrollTags != null) {
            this.scrollTags.func_73864_a(i, j, k);
        }
    }

    @Override
    public void setSelected(String selected) {
        this.scrollTags.setSelected(selected);
    }

    @Override
    public void save() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        NBTTagList tagList = new NBTTagList();
        NBTTagList UUIDTagList = new NBTTagList();
        for (String string : this.tagNames) {
            tagList.func_74742_a((NBTBase)new NBTTagString(string));
            if (!GuiNpcMobSpawnerAdd.tagMap.containsKey(string)) continue;
            UUIDTagList.func_74742_a((NBTBase)new NBTTagString(GuiNpcMobSpawnerAdd.tagMap.get(string).toString()));
        }
        GuiNpcMobSpawnerAdd.tagsCompound = UUIDTagList;
        tagCompound.func_74782_a("TagNames", (NBTBase)tagList);
        PacketClient.sendClient(new TagSetPacket(tagCompound));
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(4) != null) {
            if (this.search.equals(this.getTextField(4).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(4).func_146179_b().toLowerCase();
            this.scrollTags.setList(this.getSearchList());
        }
    }

    private List<String> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<String>(this.allTags);
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.allTags) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }
}

