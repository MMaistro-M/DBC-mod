/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 */
package noppes.npcs.client.gui.advanced;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.data.CustomEffect;

@SideOnly(value=Side.CLIENT)
public class SubGuiCustomEffectSelect
extends SubGuiInterface
implements ICustomScrollListener,
ITextfieldListener {
    private GuiCustomScroll scroll;
    private final HashMap<String, Integer> displayNameToId = new HashMap();
    private final HashMap<String, Integer> allDisplayNameToId = new HashMap();
    private int selectedEffectId = -1;
    private int selectedIndex = 0;
    private String search = "";
    private final int preselectedId;
    private final int preselectedIndex;
    private int viewIndex = 0;
    private List<Integer> availableIndices;

    public SubGuiCustomEffectSelect(int preselectedId) {
        this(preselectedId, 0);
    }

    public SubGuiCustomEffectSelect(int preselectedId, int preselectedIndex) {
        this.preselectedId = preselectedId;
        this.preselectedIndex = preselectedIndex;
        this.viewIndex = preselectedIndex;
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 216;
    }

    @Override
    public void func_73866_w_() {
        int scrollHeight;
        super.func_73866_w_();
        this.buildAvailableIndices();
        this.addTextField(new GuiNpcTextField(10, this, this.field_146289_q, this.guiLeft + 5, this.guiTop + 5, 190, 18, this.search));
        if (this.availableIndices.size() > 1) {
            String label = this.getViewLabel();
            this.addButton(new GuiNpcButton(2, this.guiLeft + 5, this.guiTop + 26, 190, 20, label));
        }
        int scrollTop = this.availableIndices.size() > 1 ? this.guiTop + 49 : this.guiTop + 26;
        int n = scrollHeight = this.availableIndices.size() > 1 ? 122 : 145;
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
        }
        this.scroll.setSize(190, scrollHeight);
        this.scroll.guiLeft = this.guiLeft + 5;
        this.scroll.guiTop = scrollTop;
        this.addScroll(this.scroll);
        this.buildAllEffects();
        List<String> filtered = this.getFilteredList();
        this.scroll.setUnsortedList(filtered);
        if (this.preselectedId >= 0 && this.viewIndex == this.preselectedIndex && !this.scroll.hasSelected()) {
            for (String name : filtered) {
                Integer id = this.displayNameToId.get(name);
                if (id == null || id != this.preselectedId) continue;
                this.scroll.setSelected(name);
                break;
            }
        }
        this.addButton(new GuiNpcButton(0, this.guiLeft + 5, this.guiTop + 188, 90, 20, "gui.select"));
        this.getButton(0).setEnabled(this.scroll.hasSelected());
        this.addButton(new GuiNpcButton(1, this.guiLeft + 105, this.guiTop + 188, 90, 20, "gui.cancel"));
    }

    private void buildAvailableIndices() {
        this.availableIndices = new ArrayList<Integer>();
        CustomEffectController controller = CustomEffectController.getInstance();
        this.availableIndices.add(0);
        HashMap<Integer, String> labels = controller.getIndexLabels();
        for (int idx : labels.keySet()) {
            if (idx == 0 || controller.getEffectMap(idx) == null) continue;
            this.availableIndices.add(idx);
        }
        Collections.sort(this.availableIndices);
        if (!this.availableIndices.contains(this.viewIndex)) {
            this.viewIndex = 0;
        }
    }

    private String getViewLabel() {
        if (this.viewIndex == 0) {
            return "Custom Effects";
        }
        HashMap<Integer, String> labels = CustomEffectController.getInstance().getIndexLabels();
        String label = labels.get(this.viewIndex);
        return label != null ? label : "Index " + this.viewIndex;
    }

    private void buildAllEffects() {
        this.allDisplayNameToId.clear();
        HashMap<Integer, CustomEffect> effects = CustomEffectController.getInstance().getEffectMap(this.viewIndex);
        if (effects == null) {
            return;
        }
        for (CustomEffect ce : effects.values()) {
            if (ce.getName() == null || ce.getName().isEmpty()) continue;
            this.allDisplayNameToId.put(ce.getName(), ce.id);
        }
    }

    private List<String> getFilteredList() {
        ArrayList<String> list = new ArrayList<String>();
        this.displayNameToId.clear();
        for (Map.Entry<String, Integer> entry : this.allDisplayNameToId.entrySet()) {
            String name = entry.getKey();
            if (!this.search.isEmpty() && !name.toLowerCase().contains(this.search)) continue;
            list.add(name);
            this.displayNameToId.put(name, entry.getValue());
        }
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        return list;
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        if (guibutton.field_146127_k == 0 && this.scroll.hasSelected()) {
            String name = this.scroll.getSelected();
            Integer id = this.displayNameToId.get(name);
            this.selectedEffectId = id != null ? id : -1;
            this.selectedIndex = this.viewIndex;
            this.close();
        } else if (guibutton.field_146127_k == 1) {
            this.selectedEffectId = -1;
            this.close();
        } else if (guibutton.field_146127_k == 2) {
            int currentPos = this.availableIndices.indexOf(this.viewIndex);
            int nextPos = (currentPos + 1) % this.availableIndices.size();
            this.viewIndex = this.availableIndices.get(nextPos);
            this.search = "";
            this.scroll = null;
            this.func_73866_w_();
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.getButton(0).setEnabled(this.scroll.hasSelected());
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0 && selection != null) {
            Integer id = this.displayNameToId.get(selection);
            this.selectedEffectId = id != null ? id : -1;
            this.selectedIndex = this.viewIndex;
            this.close();
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        String newSearch;
        super.func_73869_a(c, i);
        if (this.getTextField(10) != null && this.getTextField(10).func_146206_l() && !this.search.equals(newSearch = this.getTextField(10).func_146179_b().toLowerCase())) {
            this.search = newSearch;
            this.scroll.setUnsortedList(this.getFilteredList());
            this.scroll.resetScroll();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
    }

    public int getSelectedEffectId() {
        return this.selectedEffectId;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }
}

