/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.tags.TagsGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.data.Tag;

public class SubGuiTagSelect
extends SubGuiInterface
implements IScrollData,
ICustomScrollListener,
ITextfieldListener {
    private GuiCustomScroll scrollAllTags;
    private GuiCustomScroll scrollSelectedTags;
    private final ArrayList<String> allTagNames = new ArrayList();
    private final ArrayList<String> selectedTagNames = new ArrayList();
    private final HashSet<UUID> tagUUIDs;
    private String search = "";

    public SubGuiTagSelect(HashSet<UUID> tagUUIDs) {
        this.tagUUIDs = tagUUIDs;
        this.setBackground("menubg.png");
        this.closeOnEsc = true;
        this.xSize = 430;
        this.ySize = 220;
        TagController tc = TagController.getInstance();
        if (tc != null) {
            for (UUID uuid : tagUUIDs) {
                Tag tag = tc.getTagFromUUID(uuid);
                if (tag == null) continue;
                this.selectedTagNames.add(tag.name);
            }
        }
        PacketClient.sendClient(new TagsGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(1, StatCollector.func_74838_a((String)"tags.allTags"), this.guiLeft + 22, this.guiTop + 11));
        if (this.scrollAllTags == null) {
            this.scrollAllTags = new GuiCustomScroll(this, 0);
            this.scrollAllTags.setSize(150, 155);
        }
        this.scrollAllTags.guiLeft = this.guiLeft + 20;
        this.scrollAllTags.guiTop = this.guiTop + 24;
        this.addScroll(this.scrollAllTags);
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, this.guiLeft + 20, this.guiTop + 24 + 160, 150, 20, this.search));
        this.addLabel(new GuiNpcLabel(2, StatCollector.func_74838_a((String)"tags.selectedTags"), this.guiLeft + 252, this.guiTop + 11));
        if (this.scrollSelectedTags == null) {
            this.scrollSelectedTags = new GuiCustomScroll(this, 1);
            this.scrollSelectedTags.setSize(150, 180);
        }
        this.scrollSelectedTags.guiLeft = this.guiLeft + 250;
        this.scrollSelectedTags.guiTop = this.guiTop + 24;
        this.scrollSelectedTags.setList(this.selectedTagNames);
        this.addScroll(this.scrollSelectedTags);
        this.addButton(new GuiNpcButton(10, this.guiLeft + 185, this.guiTop + 90, 55, 20, ">"));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 185, this.guiTop + 112, 55, 20, "<"));
        this.addButton(new GuiNpcButton(12, this.guiLeft + 185, this.guiTop + 140, 55, 20, ">>"));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 185, this.guiTop + 162, 55, 20, "<<"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 10 && this.scrollAllTags.hasSelected() && !this.selectedTagNames.contains(this.scrollAllTags.getSelected())) {
            this.selectedTagNames.add(this.scrollAllTags.getSelected());
        }
        if (guibutton.field_146127_k == 12) {
            this.selectedTagNames.clear();
            this.selectedTagNames.addAll(this.allTagNames);
        }
        if (guibutton.field_146127_k == 11 && this.scrollSelectedTags.hasSelected()) {
            this.selectedTagNames.remove(this.scrollSelectedTags.getSelected());
        }
        if (guibutton.field_146127_k == 13) {
            this.selectedTagNames.clear();
        }
        this.func_73866_w_();
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        this.allTagNames.clear();
        this.allTagNames.addAll(data.keySet());
        this.allTagNames.sort(String.CASE_INSENSITIVE_ORDER);
        this.scrollAllTags.setList(this.getSearchList());
        this.func_73866_w_();
    }

    @Override
    public void save() {
        this.tagUUIDs.clear();
        TagController tc = TagController.getInstance();
        if (tc != null) {
            for (String name : this.selectedTagNames) {
                Tag tag = tc.getTagFromName(name);
                if (tag == null) continue;
                this.tagUUIDs.add(tag.uuid);
            }
        }
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (k == 0 && this.scrollAllTags != null) {
            this.scrollAllTags.func_73864_a(i, j, k);
        }
    }

    @Override
    public void setSelected(String selected) {
        if (this.scrollAllTags != null) {
            this.scrollAllTags.setSelected(selected);
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
    }

    @Override
    public void func_73869_a(char c, int i) {
        if (i == 1) {
            this.close();
            return;
        }
        super.func_73869_a(c, i);
        if (this.getTextField(4) != null) {
            if (this.search.equals(this.getTextField(4).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(4).func_146179_b().toLowerCase();
            this.scrollAllTags.setList(this.getSearchList());
        }
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
    }

    private List<String> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<String>(this.allTagNames);
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.allTagNames) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }
}

