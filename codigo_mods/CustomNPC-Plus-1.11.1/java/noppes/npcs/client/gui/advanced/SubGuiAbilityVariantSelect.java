/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiAbilityVariantSelect
extends SubGuiInterface
implements ICustomScrollListener {
    private final List<AbilityVariant> variants;
    private int selectedIndex = -1;
    private final List<Integer> scrollToVariantIndex = new ArrayList<Integer>();

    public SubGuiAbilityVariantSelect(List<AbilityVariant> variants) {
        this.variants = variants;
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 216;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(0, "ability.selectVariant", this.guiLeft + 10, this.guiTop + 8));
        ArrayList<String> scrollList = new ArrayList<String>();
        this.scrollToVariantIndex.clear();
        boolean hasBaseVariants = false;
        boolean hasGroupedVariants = false;
        for (AbilityVariant v : this.variants) {
            if (v.getGroup() == null) {
                hasBaseVariants = true;
                continue;
            }
            hasGroupedVariants = true;
        }
        for (int i = 0; i < this.variants.size(); ++i) {
            if (this.variants.get(i).getGroup() != null) continue;
            scrollList.add(this.variants.get(i).getDisplayKey());
            this.scrollToVariantIndex.add(i);
        }
        if (hasBaseVariants && hasGroupedVariants) {
            scrollList.add("");
            this.scrollToVariantIndex.add(-1);
        }
        String lastGroup = null;
        for (int i = 0; i < this.variants.size(); ++i) {
            AbilityVariant v = this.variants.get(i);
            if (v.getGroup() == null) continue;
            if (!v.getGroup().equals(lastGroup)) {
                scrollList.add(v.getGroup());
                this.scrollToVariantIndex.add(-1);
                lastGroup = v.getGroup();
            }
            scrollList.add(v.getDisplayKey());
            this.scrollToVariantIndex.add(i);
        }
        GuiCustomScroll scroll = new GuiCustomScroll(this, 0);
        scroll.guiLeft = this.guiLeft + 5;
        scroll.guiTop = this.guiTop + 22;
        scroll.setUnsortedList(scrollList);
        scroll.setSize(190, 155);
        scroll.nonInteractive.add("");
        scroll.colors.put("", 0);
        if (lastGroup != null) {
            for (int i = 0; i < this.variants.size(); ++i) {
                String group = this.variants.get(i).getGroup();
                if (group == null) continue;
                scroll.nonInteractive.add(group);
                scroll.colors.put(group, 0xFFFF00);
            }
        }
        if (this.selectedIndex >= 0) {
            for (int s = 0; s < this.scrollToVariantIndex.size(); ++s) {
                if (this.scrollToVariantIndex.get(s) != this.selectedIndex) continue;
                scroll.selected = s;
                break;
            }
        }
        this.addScroll(scroll);
        this.addButton(new GuiNpcButton(0, this.guiLeft + 5, this.guiTop + 188, 90, 20, "gui.select"));
        this.getButton(0).setEnabled(this.selectedIndex >= 0);
        this.addButton(new GuiNpcButton(1, this.guiLeft + 105, this.guiTop + 188, 90, 20, "gui.cancel"));
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        int variantIdx;
        if (scroll.id == 0 && scroll.selected >= 0 && scroll.selected < this.scrollToVariantIndex.size() && (variantIdx = this.scrollToVariantIndex.get(scroll.selected).intValue()) >= 0) {
            this.selectedIndex = variantIdx;
            this.getButton(0).setEnabled(true);
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (this.selectedIndex >= 0) {
            this.close();
        }
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0 && this.selectedIndex >= 0) {
            this.close();
        } else if (id == 1) {
            this.selectedIndex = -1;
            this.close();
        }
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public List<AbilityVariant> getVariants() {
        return this.variants;
    }
}

