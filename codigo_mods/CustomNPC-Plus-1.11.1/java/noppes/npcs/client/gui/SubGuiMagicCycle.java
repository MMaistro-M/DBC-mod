/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.magic.MagicCycleSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.gui.global.GuiNpcManageMagic;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumDiagramLayout;
import noppes.npcs.controllers.data.MagicAssociation;
import noppes.npcs.controllers.data.MagicCycle;

public class SubGuiMagicCycle
extends SubGuiInterface
implements ITextfieldListener,
ISubGuiListener,
ICustomScrollListener {
    public GuiNpcManageMagic parent;
    private GuiCustomScroll allMagic;
    private GuiCustomScroll associationScroll;
    private MagicCycle cycle;
    private String search = "";
    private GuiNpcTextField indexField;
    private GuiNpcTextField priorityField;
    private String selectedAssociation;
    private HashMap<String, MagicAssociation> associationMap = new HashMap();

    public SubGuiMagicCycle(GuiNpcManageMagic parent, MagicCycle cycle) {
        this.parent = parent;
        this.cycle = cycle;
        this.setBackground("menubg.png");
        this.xSize = 415;
        this.ySize = 216;
        this.closeOnEsc = true;
        this.processAssociations();
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 6;
        this.addLabel(new GuiNpcLabel(1, "gui.name", this.guiLeft + 4, y + 5));
        this.addTextField(new GuiNpcTextField(1, this, this.field_146289_q, this.guiLeft + 80, y, 200, 20, this.cycle.name));
        this.addLabel(new GuiNpcLabel(-10, "ID", this.guiLeft + 200 + 80 + 5, y + 2));
        this.addLabel(new GuiNpcLabel(-11, this.cycle.id + "", this.guiLeft + 200 + 80 + 5, y + 12));
        this.addLabel(new GuiNpcLabel(3, "magic.diagram", this.guiLeft + 307, y + 5));
        this.addButton(new GuiNpcButton(13, this.guiLeft + 307, y += 25, 100, 20, EnumDiagramLayout.names(), this.cycle.layout.ordinal()));
        this.getButton(13).setHoverText("diagram.info");
        this.addLabel(new GuiNpcLabel(2, "gui.displayName", this.guiLeft + 4, y + 5));
        this.addTextField(new GuiNpcTextField(2, this, this.field_146289_q, this.guiLeft + 80, y, 200, 20, this.cycle.displayName));
        y += 27;
        String index = "gui.unused";
        String priority = "gui.unused";
        if (this.cycle.layout == EnumDiagramLayout.MANUAL) {
            index = "X";
            priority = "Y";
        } else if (this.cycle.layout.isManual()) {
            index = "magic.index";
            priority = "magic.priority";
        }
        this.addLabel(new GuiNpcLabel(50, index, this.guiLeft + 5, y + 5));
        this.indexField = new GuiNpcTextField(91, this, this.field_146289_q, this.guiLeft + 100, y, 45, 20, "");
        this.indexField.setIntegersOnly();
        this.addTextField(this.indexField);
        this.addLabel(new GuiNpcLabel(51, priority, this.guiLeft + 150, y + 5));
        this.priorityField = new GuiNpcTextField(92, this, this.field_146289_q, this.guiLeft + 250, y, 45, 20, "");
        this.priorityField.setIntegersOnly();
        this.addTextField(this.priorityField);
        this.indexField.enabled = false;
        this.priorityField.enabled = false;
        if (this.selectedAssociation != null && this.associationMap.containsKey(this.selectedAssociation)) {
            this.indexField.enabled = true;
            this.priorityField.enabled = true;
            this.indexField.func_146180_a(this.associationMap.get((Object)this.selectedAssociation).index + "");
            this.priorityField.func_146180_a(this.associationMap.get((Object)this.selectedAssociation).priority + "");
        }
        y += 27;
        if (this.allMagic == null) {
            this.allMagic = new GuiCustomScroll(this, 0);
            this.allMagic.setSize(150, 100);
        }
        this.allMagic.guiLeft = this.guiLeft + 5;
        this.allMagic.guiTop = y;
        this.allMagic.setList(this.getAvailableMagicList());
        this.addScroll(this.allMagic);
        this.addTextField(new GuiNpcTextField(34, this, this.field_146289_q, this.guiLeft + 5, y + 105, 150, 20, this.search));
        if (this.associationScroll == null) {
            this.associationScroll = new GuiCustomScroll(this, 1);
            this.associationScroll.setSize(150, 125);
        }
        this.associationScroll.guiLeft = this.guiLeft + 205;
        this.associationScroll.guiTop = y;
        this.associationScroll.setList(new ArrayList<String>(this.associationMap.keySet()));
        this.addScroll(this.associationScroll);
        this.addButton(new GuiNpcButton(60, this.guiLeft + 160, y + 20, 30, 20, ">"));
        this.addButton(new GuiNpcButton(61, this.guiLeft + 160, y + 45, 30, 20, "<"));
        this.addButton(new GuiNpcButton(62, this.guiLeft + 160, y + 70, 30, 20, ">>"));
        this.addButton(new GuiNpcButton(63, this.guiLeft + 160, y + 95, 30, 20, "<<"));
        this.addButton(new GuiNpcButton(99, this.guiLeft + this.xSize - 50 - 4, this.guiTop + this.ySize - 20 - 4, 50, 20, "gui.done"));
    }

    @Override
    public void func_146284_a(GuiButton button) {
        if (button.field_146127_k == 13) {
            this.cycle.layout = EnumDiagramLayout.values()[((GuiNpcButton)button).getValue()];
            this.func_73866_w_();
        }
        if (button.field_146127_k == 60 && this.allMagic.hasSelected() && !this.associationMap.containsKey(this.allMagic.getSelected()) && this.parent.magicData.containsKey(this.allMagic.getSelected())) {
            MagicAssociation assoc = new MagicAssociation();
            assoc.magicId = this.parent.magicData.get(this.allMagic.getSelected());
            assoc.index = 0;
            assoc.priority = 0;
            this.associationMap.put(this.allMagic.getSelected(), assoc);
            this.associationScroll.list.add(this.allMagic.getSelected());
        }
        if (button.field_146127_k == 61 && this.associationScroll.hasSelected()) {
            this.associationMap.remove(this.associationScroll.getSelected());
            this.associationScroll.list.remove(this.associationScroll.selected);
            this.associationScroll.selected = -1;
            this.indexField.enabled = false;
            this.indexField.func_146180_a("");
            this.priorityField.enabled = false;
            this.priorityField.func_146180_a("");
        }
        if (button.field_146127_k == 62) {
            for (String name : this.parent.magicData.keySet()) {
                if (this.associationMap.containsKey(name)) continue;
                MagicAssociation assoc = new MagicAssociation();
                assoc.magicId = this.parent.magicData.get(name);
                assoc.index = 0;
                assoc.priority = 0;
                this.associationMap.put(name, assoc);
            }
            this.associationScroll.setList(new ArrayList<String>(this.associationMap.keySet()));
        }
        if (button.field_146127_k == 63) {
            this.associationMap.clear();
            this.associationScroll.setList(new ArrayList<String>());
            this.associationScroll.selected = -1;
            this.indexField.enabled = false;
            this.indexField.func_146180_a("");
            this.priorityField.enabled = false;
            this.priorityField.func_146180_a("");
        }
        if (button.field_146127_k == 99) {
            this.close();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        if (textField.id == 1) {
            this.cycle.name = textField.func_146179_b();
        }
        if (textField.id == 2) {
            this.cycle.displayName = textField.func_146179_b();
        }
        if (textField.id == 91 && this.selectedAssociation != null) {
            try {
                int idx;
                this.associationMap.get((Object)this.selectedAssociation).index = idx = Integer.parseInt(textField.func_146179_b());
            }
            catch (NumberFormatException idx) {
                // empty catch block
            }
        }
        if (textField.id == 92 && this.selectedAssociation != null) {
            try {
                int prio;
                this.associationMap.get((Object)this.selectedAssociation).priority = prio = Integer.parseInt(textField.func_146179_b());
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(34) != null && this.getTextField(34).func_146206_l()) {
            if (this.search.equals(this.getTextField(34).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(34).func_146179_b().toLowerCase();
            this.allMagic.setList(this.getAvailableMagicList());
            this.allMagic.resetScroll();
        }
    }

    private List<String> getAvailableMagicList() {
        ArrayList<String> original = new ArrayList<String>(this.parent.magicData.keySet());
        ArrayList<String> filtered = new ArrayList<String>();
        for (String name : original) {
            if (this.associationMap.containsKey(name) || !name.toLowerCase().contains(this.search)) continue;
            filtered.add(name);
        }
        return filtered;
    }

    public void processAssociations() {
        this.associationMap.clear();
        block0: for (MagicAssociation assoc : this.cycle.associations.values()) {
            for (String name : this.parent.magicData.keySet()) {
                if (this.parent.magicData.get(name) != assoc.magicId) continue;
                this.associationMap.put(name, assoc);
                continue block0;
            }
        }
    }

    @Override
    public void customScrollClicked(int id, int index, int clickType, GuiCustomScroll scroll) {
        if (scroll.id == 1) {
            this.selectedAssociation = scroll.getSelected();
            if (this.selectedAssociation != null && this.associationMap.containsKey(this.selectedAssociation)) {
                this.indexField.enabled = true;
                this.priorityField.enabled = true;
                this.indexField.func_146180_a(this.associationMap.get((Object)this.selectedAssociation).index + "");
                this.priorityField.func_146180_a(this.associationMap.get((Object)this.selectedAssociation).priority + "");
            }
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
    }

    @Override
    public void close() {
        this.cycle.associations.clear();
        for (String name : this.associationMap.keySet()) {
            this.cycle.associations.put(this.associationMap.get((Object)name).magicId, this.associationMap.get(name));
        }
        NBTTagCompound compound = new NBTTagCompound();
        this.cycle.writeNBT(compound);
        PacketClient.sendClient(new MagicCycleSavePacket(compound));
        super.close();
    }
}

