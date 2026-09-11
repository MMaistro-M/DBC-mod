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
import java.util.TreeSet;
import java.util.Vector;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.GuiNpcMobSpawnerAdd;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;

public class SubGuiClonerQuickTags
extends SubGuiInterface
implements IScrollData {
    public GuiNpcMobSpawnerAdd parent;
    public GuiCustomScroll quickScroll = new GuiCustomScroll(this, 0);
    public GuiCustomScroll selectedScroll = new GuiCustomScroll(this, 5);
    private static String quickSearch = "";

    public SubGuiClonerQuickTags(GuiNpcMobSpawnerAdd par) {
        this.parent = par;
        this.setBackground("menubg.png");
        this.xSize = 305;
        this.ySize = 220;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.quickScroll.clear();
        this.quickScroll.setSize(140, 166);
        this.quickScroll.guiLeft = this.guiLeft + 4;
        this.quickScroll.guiTop = this.guiTop + 19;
        this.quickScroll.multipleSelection = true;
        this.quickScroll.setSelectedList(GuiNpcMobSpawnerAdd.addTags);
        this.addScroll(this.quickScroll);
        this.addLabel(new GuiNpcLabel(1, StatCollector.func_74838_a((String)"cloner.wandTags"), this.guiLeft + 7, this.guiTop + 7));
        this.addScroll(this.quickScroll);
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 190, 140, 20, quickSearch));
        this.addButton(new GuiNpcButton(10, this.guiLeft + 150, this.guiTop + 20, 120, 20, "gui.selectAll"));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 150, this.guiTop + 43, 120, 20, "gui.deselectAll"));
        this.selectedScroll.clear();
        this.selectedScroll.setSize(145, 103);
        this.selectedScroll.guiLeft = this.guiLeft + 150;
        this.selectedScroll.guiTop = this.guiTop + 82;
        this.selectedScroll.setUnselectable();
        this.addScroll(this.selectedScroll);
        this.addLabel(new GuiNpcLabel(3, StatCollector.func_74838_a((String)"tags.selectedTags"), this.guiLeft + 153, this.guiTop + 68));
        this.addScroll(this.selectedScroll);
        this.addButton(new GuiNpcButton(66, this.guiLeft + 240, this.guiTop + 190, 60, 20, "gui.done"));
        this.setSelected();
        this.refreshSelected();
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 10) {
            GuiNpcMobSpawnerAdd.addTags = new HashSet<String>(GuiNpcMobSpawnerAdd.allTags);
            this.quickScroll.setSelectedList(GuiNpcMobSpawnerAdd.addTags);
            this.refreshSelected();
        }
        if (guibutton.field_146127_k == 11) {
            GuiNpcMobSpawnerAdd.addTags = new HashSet();
            this.quickScroll.setSelectedList(GuiNpcMobSpawnerAdd.addTags);
            this.refreshSelected();
        }
        if (guibutton.field_146127_k == 66) {
            this.close();
        }
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        this.refreshSelected();
    }

    public void setSelected() {
        this.quickScroll.setList(this.getQuickTags());
    }

    public void refreshSelected() {
        ArrayList<String> sortedList = new ArrayList<String>(new TreeSet<String>(GuiNpcMobSpawnerAdd.addTags));
        this.selectedScroll.setList(sortedList);
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(2) != null) {
            if (quickSearch.equals(this.getTextField(2).func_146179_b())) {
                return;
            }
            quickSearch = this.getTextField(2).func_146179_b().toLowerCase();
            this.quickScroll.setList(this.getQuickTags());
        }
    }

    private List<String> getQuickTags() {
        if (quickSearch.isEmpty()) {
            return new ArrayList<String>(GuiNpcMobSpawnerAdd.allTags);
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : GuiNpcMobSpawnerAdd.allTags) {
            if (!name.toLowerCase().contains(quickSearch)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
    }

    @Override
    public void setSelected(String selected) {
    }
}

