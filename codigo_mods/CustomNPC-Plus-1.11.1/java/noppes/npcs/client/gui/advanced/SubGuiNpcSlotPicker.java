/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.resources.I18n
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityAction;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiNpcSlotPicker
extends SubGuiInterface
implements ICustomScrollListener {
    private final List<AbilityAction> npcSlots;
    private final List<Integer> validIndices = new ArrayList<Integer>();
    private GuiCustomScroll scroll;
    private int selectedIndex = -1;

    public SubGuiNpcSlotPicker(List<AbilityAction> npcSlots) {
        this.npcSlots = npcSlots;
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 200;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 5;
        this.addLabel(new GuiNpcLabel(0, "ability.selectSlot", this.guiLeft + 10, y));
        y += 14;
        ArrayList<String> displayList = new ArrayList<String>();
        this.validIndices.clear();
        for (int i = 0; i < this.npcSlots.size(); ++i) {
            Ability ability;
            AbilityAction slot = this.npcSlots.get(i);
            if (slot.isChain() || (ability = slot.getAbility()) == null) continue;
            String typeName = I18n.func_135052_a((String)ability.getTypeId(), (Object[])new Object[0]);
            String customName = ability.getName();
            String display = customName != null && !customName.isEmpty() && !customName.equals(typeName) ? i + 1 + ". " + customName + " (" + typeName + ")" : i + 1 + ". " + typeName;
            if (slot.isReference()) {
                display = "\u00a7e" + display + " (Ref)";
            }
            displayList.add(display);
            this.validIndices.add(i);
        }
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(200, 130);
        }
        this.scroll.guiLeft = this.guiLeft + 10;
        this.scroll.guiTop = y;
        this.scroll.setList(displayList);
        this.addScroll(this.scroll);
        this.addButton(new GuiNpcButton(0, this.guiLeft + 10, y += 133, 95, 20, "gui.select"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 115, y, 95, 20, "gui.cancel"));
        this.getButton(0).setEnabled(this.selectedIndex >= 0);
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0 && this.selectedIndex >= 0) {
            this.close();
        } else if (guibutton.field_146127_k == 1) {
            this.selectedIndex = -1;
            this.close();
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0 && guiCustomScroll.selected >= 0 && guiCustomScroll.selected < this.validIndices.size()) {
            this.selectedIndex = this.validIndices.get(guiCustomScroll.selected);
            this.func_73866_w_();
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0 && this.selectedIndex >= 0) {
            this.close();
        }
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }
}

