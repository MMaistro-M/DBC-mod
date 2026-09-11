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
package noppes.npcs.client.gui.advanced;

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
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiNPCTagSetup
extends GuiNPCInterface2
implements IScrollData,
ICustomScrollListener,
IGuiData {
    private GuiCustomScroll scrollTags;
    private GuiCustomScroll npcTags;
    private final ArrayList<String> allTags = new ArrayList();
    private ArrayList<String> tagNames = new ArrayList();
    private String search = "";

    public GuiNPCTagSetup(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new TagsGetPacket());
        PacketClient.sendClient(new TagsNpcGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(1, StatCollector.func_74838_a((String)"tags.allTags"), this.guiLeft + 22, this.guiTop + 11));
        if (this.scrollTags == null) {
            this.scrollTags = new GuiCustomScroll(this, 0);
            this.scrollTags.setSize(150, 155);
        }
        this.scrollTags.guiLeft = this.guiLeft + 20;
        this.scrollTags.guiTop = this.guiTop + 24;
        this.addScroll(this.scrollTags);
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 20, this.guiTop + 24 + 160, 150, 20, this.search));
        this.addLabel(new GuiNpcLabel(2, StatCollector.func_74838_a((String)"tags.selectedTags"), this.guiLeft + 252, this.guiTop + 11));
        if (this.npcTags == null) {
            this.npcTags = new GuiCustomScroll(this, 1);
            this.npcTags.setSize(150, 180);
        }
        this.npcTags.guiLeft = this.guiLeft + 250;
        this.npcTags.guiTop = this.guiTop + 24;
        this.npcTags.setList(this.tagNames);
        this.addScroll(this.npcTags);
        this.addButton(new GuiNpcButton(10, this.guiLeft + 185, this.guiTop + 90, 55, 20, ">"));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 185, this.guiTop + 112, 55, 20, "<"));
        this.addButton(new GuiNpcButton(12, this.guiLeft + 185, this.guiTop + 140, 55, 20, ">>"));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 185, this.guiTop + 162, 55, 20, "<<"));
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
        this.func_73866_w_();
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
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
    }

    @Override
    public void save() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        NBTTagList tagList = new NBTTagList();
        for (String string : this.tagNames) {
            tagList.func_74742_a((NBTBase)new NBTTagString(string));
        }
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

