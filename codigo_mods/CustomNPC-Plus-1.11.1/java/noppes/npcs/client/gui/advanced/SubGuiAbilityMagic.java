/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.data.Magic;
import noppes.npcs.controllers.data.MagicData;

public class SubGuiAbilityMagic
extends SubGuiInterface
implements ICustomScrollListener,
ITextfieldListener {
    public MagicData magicData;
    private GuiCustomScroll availableScroll;
    private GuiCustomScroll assignedScroll;
    private final HashMap<String, Integer> allMagic = new HashMap();
    private final HashMap<String, String> assignedDisplayToName = new HashMap();
    private String search = "";
    private GuiNpcTextField splitField;

    public SubGuiAbilityMagic(MagicData data) {
        this.magicData = data;
        this.setBackground("menubg.png");
        this.xSize = 420;
        this.ySize = 222;
        MagicController controller = MagicController.getInstance();
        for (Map.Entry<Integer, Magic> entry : controller.magics.entrySet()) {
            this.allMagic.put(entry.getValue().getDisplayName(), entry.getKey());
        }
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int scrollW = 150;
        int scrollH = 140;
        int leftX = this.guiLeft + 10;
        int rightX = this.guiLeft + 250;
        int topY = this.guiTop + 20;
        this.addLabel(new GuiNpcLabel(0, "ability.magic.editor", this.guiLeft + 10, this.guiTop + 5));
        if (this.availableScroll == null) {
            this.availableScroll = new GuiCustomScroll(this, 0);
            this.availableScroll.setSize(scrollW, scrollH);
        }
        this.availableScroll.guiLeft = leftX;
        this.availableScroll.guiTop = topY;
        this.availableScroll.setList(this.getSearchList());
        this.addScroll(this.availableScroll);
        this.addTextField(new GuiNpcTextField(4, this, this.field_146289_q, leftX, topY + scrollH + 5, scrollW, 20, this.search));
        if (this.assignedScroll == null) {
            this.assignedScroll = new GuiCustomScroll(this, 1);
            this.assignedScroll.setSize(scrollW, scrollH);
        }
        this.assignedScroll.guiLeft = rightX;
        this.assignedScroll.guiTop = topY;
        this.updateAssignedList();
        this.addScroll(this.assignedScroll);
        int midX = this.guiLeft + 175;
        this.addButton(new GuiNpcButton(70, midX, topY + 40, 55, 20, ">"));
        this.addButton(new GuiNpcButton(71, midX, topY + 62, 55, 20, "<"));
        this.addButton(new GuiNpcButton(72, midX, topY + 90, 55, 20, "magic.dist"));
        this.getButton(72).setHoverText("magic.distInfo");
        int tfY = topY + scrollH + 5;
        this.addLabel(new GuiNpcLabel(5002, "magic.split", rightX - 57, tfY + 5));
        this.splitField = new GuiNpcTextField(73, this, this.field_146289_q, rightX, tfY, 60, 20, "");
        this.splitField.setFloatsOnly();
        this.splitField.setMinMaxDefaultFloat(0.0f, 1.0f, 0.0f);
        this.splitField.enabled = false;
        this.addTextField(this.splitField);
        this.addButton(new GuiNpcButton(80, this.guiLeft + this.xSize / 2 - 40, this.guiTop + this.ySize - 34, 80, 20, "gui.done"));
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 70) {
            String selected;
            Integer id;
            if (this.availableScroll.hasSelected() && (id = this.allMagic.get(selected = this.availableScroll.getSelected())) != null && !this.magicData.hasMagic(id)) {
                this.magicData.addMagic(id, 0.0f, 0.0f);
            }
            this.updateAssignedList();
            return;
        }
        if (guibutton.field_146127_k == 71) {
            Integer id;
            if (this.assignedScroll.hasSelected() && (id = this.resolveAssignedId(this.assignedScroll.getSelected())) != null && this.magicData.hasMagic(id)) {
                this.magicData.removeMagic(id);
            }
            this.updateAssignedList();
            if (this.splitField != null) {
                this.splitField.func_146180_a("");
                this.splitField.enabled = false;
            }
            return;
        }
        if (guibutton.field_146127_k == 72) {
            if (this.magicData.getMagics().size() > 0) {
                Integer id;
                int count = this.magicData.getMagics().size();
                float stdSplit = 1.0f / (float)count;
                for (Integer key : this.magicData.getMagics().keySet()) {
                    this.magicData.getMagic((int)key.intValue()).split = stdSplit;
                }
                if (this.assignedScroll.getSelected() != null && this.splitField != null && (id = this.resolveAssignedId(this.assignedScroll.getSelected())) != null && this.magicData.hasMagic(id)) {
                    this.splitField.func_146180_a(this.magicData.getMagic((int)id.intValue()).split + "");
                }
                this.updateAssignedList();
            }
            return;
        }
        if (guibutton.field_146127_k == 80) {
            this.close();
            return;
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(4) != null && this.getTextField(4).func_146206_l()) {
            if (this.search.equals(this.getTextField(4).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(4).func_146179_b().toLowerCase();
            this.availableScroll.setList(this.getSearchList());
            this.availableScroll.resetScroll();
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        Integer id;
        if (guiCustomScroll == this.assignedScroll && this.assignedScroll.getSelected() != null && (id = this.resolveAssignedId(this.assignedScroll.getSelected())) != null && this.magicData.hasMagic(id) && this.splitField != null) {
            this.splitField.func_146180_a(this.magicData.getMagic((int)id.intValue()).split + "");
            this.splitField.enabled = true;
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        Integer id;
        if (textField.id == 73 && this.assignedScroll != null && this.assignedScroll.getSelected() != null && (id = this.resolveAssignedId(this.assignedScroll.getSelected())) != null && this.magicData.hasMagic(id)) {
            try {
                float split;
                this.magicData.getMagic((int)id.intValue()).split = split = Float.parseFloat(textField.func_146179_b());
                this.updateAssignedList();
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
    }

    private List<String> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<String>(this.allMagic.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.allMagic.keySet()) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }

    private Integer resolveAssignedId(String selection) {
        if (selection == null) {
            return null;
        }
        String baseName = this.assignedDisplayToName.get(selection);
        if (baseName == null) {
            baseName = selection;
        }
        return this.allMagic.get(baseName);
    }

    private void updateAssignedList() {
        this.assignedDisplayToName.clear();
        ArrayList<String> selected = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : this.allMagic.entrySet()) {
            if (!this.magicData.hasMagic(entry.getValue())) continue;
            float split = this.magicData.getMagic((int)entry.getValue().intValue()).split;
            String display = entry.getKey() + " - " + Math.round(split * 100.0f) + "%";
            this.assignedDisplayToName.put(display, entry.getKey());
            selected.add(display);
        }
        if (this.assignedScroll != null) {
            this.assignedScroll.setList(selected);
        }
    }
}

