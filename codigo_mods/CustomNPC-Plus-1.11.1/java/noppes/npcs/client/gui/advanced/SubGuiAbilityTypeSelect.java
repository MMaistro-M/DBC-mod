/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.resources.I18n
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.util.Register;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import noppes.npcs.client.gui.advanced.GuiNPCAbilities;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiAbilityTypeSelect
extends SubGuiInterface
implements ICustomScrollListener,
ITextfieldListener {
    private static final int BTN_ADD = 0;
    private static final int BTN_CANCEL = 1;
    private static final int BTN_SCROLL_TYPE = 2;
    private static final int TF_SEARCH = 10;
    private GuiCustomScroll scroll;
    private final HashMap<String, String> displayNameToTypeId = new HashMap();
    private final HashMap<String, String> allDisplayNameToTypeId = new HashMap();
    private String selectedTypeId = null;
    private String search = "";

    public SubGuiAbilityTypeSelect() {
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 216;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 5;
        this.addButton(new GuiNpcButton(2, this.guiLeft + 5, y, 190, 20, GuiNPCAbilities.scrollType.toString()));
        this.addTextField(new GuiNpcTextField(10, this, this.field_146289_q, this.guiLeft + 5, y += 24, 190, 18, this.search));
        y += 20;
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
            this.scroll.setSize(190, 121);
        }
        this.scroll.guiLeft = this.guiLeft + 5;
        this.scroll.guiTop = y;
        this.addScroll(this.scroll);
        this.buildAllTypes();
        this.scroll.setUnsortedList(this.getFilteredTypeList());
        this.addButton(new GuiNpcButton(0, this.guiLeft + 5, this.guiTop + 188, 90, 20, "gui.add"));
        this.getButton(0).setEnabled(this.scroll.hasSelected());
        this.addButton(new GuiNpcButton(1, this.guiLeft + 105, this.guiTop + 188, 90, 20, "gui.cancel"));
    }

    private void buildAllTypes() {
        String[] types;
        this.allDisplayNameToTypeId.clear();
        for (String typeId : types = AbilityController.Instance.getTypes()) {
            if (AbilityController.Instance.isBuiltInType(typeId) || !this.matchesScrollType(typeId)) continue;
            String displayName = I18n.func_135052_a((String)typeId, (Object[])new Object[0]);
            if (typeId.equals("ability.cnpc.custom")) {
                displayName = "\u00a7d" + displayName;
            } else if (AbilityController.Instance.isConcurrentCapableType(typeId)) {
                displayName = "\u00a7b" + displayName;
            }
            this.allDisplayNameToTypeId.put(displayName, typeId);
        }
    }

    private boolean matchesScrollType(String typeId) {
        GuiNPCAbilities.ScrollType type = GuiNPCAbilities.scrollType;
        if (type == GuiNPCAbilities.ScrollType.ALL) {
            return true;
        }
        if (type == GuiNPCAbilities.ScrollType.CNPC) {
            return typeId.startsWith("ability.cnpc.");
        }
        if (type == GuiNPCAbilities.ScrollType.MODDED) {
            List<String> registerList;
            if (!Register.isEmpty("ability") && !(registerList = Register.REGISTERED_NAMESPACES.get("ability")).isEmpty()) {
                String namespace = registerList.get(GuiNPCAbilities.modIndex);
                return typeId.startsWith("ability." + namespace + ".");
            }
            return false;
        }
        return true;
    }

    private List<String> getFilteredTypeList() {
        ArrayList<String> list = new ArrayList<String>();
        this.displayNameToTypeId.clear();
        for (Map.Entry<String, String> entry : this.allDisplayNameToTypeId.entrySet()) {
            String displayName = entry.getKey();
            String stripped = displayName.replaceAll("\u00a7.", "");
            if (!this.search.isEmpty() && !stripped.toLowerCase().contains(this.search) && !entry.getValue().toLowerCase().contains(this.search)) continue;
            list.add(displayName);
            this.displayNameToTypeId.put(displayName, entry.getValue());
        }
        Collections.sort(list, (a, b) -> String.CASE_INSENSITIVE_ORDER.compare(a.replaceAll("\u00a7.", ""), b.replaceAll("\u00a7.", "")));
        String customEntry = null;
        for (String name : list) {
            String tid = this.displayNameToTypeId.get(name);
            if (tid == null || !tid.equals("ability.cnpc.custom")) continue;
            customEntry = name;
            break;
        }
        if (customEntry != null) {
            list.remove(customEntry);
            list.add(0, customEntry);
        }
        return list;
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 0 && this.scroll.hasSelected()) {
            String displayName = this.scroll.getSelected();
            this.selectedTypeId = this.displayNameToTypeId.get(displayName);
            this.close();
        } else if (id == 1) {
            this.selectedTypeId = null;
            this.close();
        } else if (id == 2) {
            this.cycleScrollType();
            if (this.scroll != null) {
                this.scroll.resetScroll();
            }
            this.func_73866_w_();
        }
    }

    private void cycleScrollType() {
        if (GuiNPCAbilities.scrollType != GuiNPCAbilities.ScrollType.MODDED) {
            GuiNPCAbilities.ScrollType[] values = GuiNPCAbilities.ScrollType.values();
            GuiNPCAbilities.ScrollType next = values[(GuiNPCAbilities.scrollType.ordinal() + 1) % values.length];
            if (next == GuiNPCAbilities.ScrollType.MODDED && Register.isEmpty("ability")) {
                next = GuiNPCAbilities.ScrollType.ALL;
            }
            GuiNPCAbilities.scrollType = next;
        } else {
            List<String> list = Register.REGISTERED_NAMESPACES.get("ability");
            if (list != null && !list.isEmpty()) {
                if (GuiNPCAbilities.modIndex == list.size() - 1) {
                    GuiNPCAbilities.scrollType = GuiNPCAbilities.ScrollType.ALL;
                } else {
                    GuiNPCAbilities.modIndex = (GuiNPCAbilities.modIndex + 1) % list.size();
                }
            } else {
                GuiNPCAbilities.modIndex = 0;
                GuiNPCAbilities.scrollType = GuiNPCAbilities.ScrollType.ALL;
            }
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
            this.selectedTypeId = this.displayNameToTypeId.get(selection);
            this.close();
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(10) != null && this.getTextField(10).func_146206_l() && !this.search.equals(this.getTextField(10).func_146179_b())) {
            this.search = this.getTextField(10).func_146179_b().toLowerCase();
            this.scroll.setList(this.getFilteredTypeList());
            this.scroll.resetScroll();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
    }

    public String getSelectedTypeId() {
        return this.selectedTypeId;
    }
}

