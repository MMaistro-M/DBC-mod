/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kamkeel.npcs.controllers.AbilityController;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiChainSelect
extends SubGuiInterface
implements ICustomScrollListener {
    private GuiCustomScroll scroll;
    private String selectedName = null;

    public SubGuiChainSelect() {
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 200;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 5;
        this.addLabel(new GuiNpcLabel(0, "ability.loadChain", this.guiLeft + 10, y));
        y += 14;
        List<String> names = this.getChainNames();
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(200, 130);
        }
        this.scroll.guiLeft = this.guiLeft + 10;
        this.scroll.guiTop = y;
        this.scroll.setList(names);
        if (this.selectedName != null) {
            this.scroll.setSelected(this.selectedName);
        }
        this.addScroll(this.scroll);
        this.addButton(new GuiNpcButton(0, this.guiLeft + 10, y += 133, 95, 20, "gui.select"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 115, y, 95, 20, "gui.cancel"));
        this.getButton(0).setEnabled(this.selectedName != null);
    }

    private List<String> getChainNames() {
        Set<String> names = AbilityController.Instance.getChainedAbilityNamesSet();
        return new ArrayList<String>(names);
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0 && this.selectedName != null) {
            this.close();
        } else if (guibutton.field_146127_k == 1) {
            this.selectedName = null;
            this.close();
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.selectedName = guiCustomScroll.getSelected();
            this.func_73866_w_();
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0 && selection != null && !selection.isEmpty()) {
            this.selectedName = selection;
            this.close();
        }
    }

    public String getSelectedName() {
        return this.selectedName;
    }
}

