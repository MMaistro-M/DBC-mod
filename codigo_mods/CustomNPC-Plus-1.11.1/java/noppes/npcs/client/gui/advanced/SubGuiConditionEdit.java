/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.resources.I18n
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldBuilder;
import kamkeel.npcs.util.Register;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class SubGuiConditionEdit
extends SubGuiInterface
implements ITextfieldListener,
ISubGuiListener,
ICustomScrollListener {
    private static final int DECLARATIVE_ID_START = 1000;
    private static final int CLEAR_ID_START = 2000;
    private static final int LABEL_ID_START = 3000;
    private static final int BTN_NAMESPACE = 1;
    private static final int BTN_CANCEL = 10;
    private static final int BTN_DONE = 11;
    private static final int TF_SEARCH = 12;
    private static final int LEFT_WIDTH = 110;
    private static final int LEFT_MARGIN = 5;
    private static final int RIGHT_GAP = 5;
    private String searchText = "";
    private AbilityCondition condition;
    private AbilityCondition result = null;
    private String selectedTypeId;
    private int namespaceFilter = 0;
    private List<String> modNamespaces;
    private GuiCustomScroll scroll;
    private final HashMap<String, String> displayNameToTypeId = new HashMap();
    private String[] filteredTypeIds;
    private AbilityFieldBuilder builder;

    public SubGuiConditionEdit(AbilityCondition existing) {
        this.modNamespaces = this.getModNamespaces();
        if (existing != null) {
            this.condition = existing;
            this.selectedTypeId = existing.getTypeId();
            this.setNamespaceForTypeId(this.selectedTypeId);
        } else {
            String[] allTypes = AbilityController.Instance.getConditionTypes();
            if (allTypes.length > 0) {
                this.selectedTypeId = allTypes[0];
                this.condition = this.spawnCondition(this.selectedTypeId);
            }
        }
        this.setBackground("menubg.png", 217);
        this.xSize = 356;
        this.ySize = 200;
    }

    private List<String> getModNamespaces() {
        ArrayList<String> result = new ArrayList<String>();
        List<String> registered = Register.REGISTERED_NAMESPACES.get("condition");
        if (registered != null) {
            result.addAll(registered);
        }
        return result;
    }

    private void setNamespaceForTypeId(String typeId) {
        if (typeId == null) {
            return;
        }
        String[] parts = typeId.split("\\.", 3);
        if (parts.length < 2) {
            return;
        }
        String ns = parts[1];
        if ("cnpc".equals(ns)) {
            this.namespaceFilter = 1;
        } else {
            for (int i = 0; i < this.modNamespaces.size(); ++i) {
                if (!this.modNamespaces.get(i).equals(ns)) continue;
                this.namespaceFilter = 2 + i;
                return;
            }
            this.namespaceFilter = 0;
        }
    }

    private String getNamespaceFilterLabel() {
        if (this.namespaceFilter == 0) {
            return "ALL";
        }
        if (this.namespaceFilter == 1) {
            return "CNPC";
        }
        int modIdx = this.namespaceFilter - 2;
        if (modIdx >= 0 && modIdx < this.modNamespaces.size()) {
            String ns = this.modNamespaces.get(modIdx);
            String display = Register.NAMESPACE_DISPLAY_NAMES.get(ns);
            return display != null ? display : ns.toUpperCase();
        }
        return "ALL";
    }

    private void cycleNamespaceFilter() {
        int modIdx;
        ++this.namespaceFilter;
        int max = 2 + this.modNamespaces.size();
        if (this.namespaceFilter >= max) {
            this.namespaceFilter = 0;
        }
        if (this.namespaceFilter >= 2 && (modIdx = this.namespaceFilter - 2) >= this.modNamespaces.size()) {
            this.namespaceFilter = 0;
        }
    }

    private String[] getFilteredTypes() {
        if (this.namespaceFilter == 0) {
            return AbilityController.Instance.getConditionTypes();
        }
        if (this.namespaceFilter == 1) {
            return AbilityController.Instance.getConditionTypesByNamespace("cnpc");
        }
        int modIdx = this.namespaceFilter - 2;
        if (modIdx >= 0 && modIdx < this.modNamespaces.size()) {
            return AbilityController.Instance.getConditionTypesByNamespace(this.modNamespaces.get(modIdx));
        }
        return AbilityController.Instance.getConditionTypes();
    }

    private AbilityCondition spawnCondition(String typeId) {
        if (typeId == null) {
            return null;
        }
        Supplier<AbilityCondition> factory = AbilityController.Instance.getConditionType(typeId);
        return factory != null ? factory.get() : null;
    }

    @Override
    public void func_73866_w_() {
        GuiNpcTextField.unfocus();
        super.func_73866_w_();
        int leftX = this.guiLeft + 5;
        int btnY = this.guiTop + this.ySize - 26;
        this.addButton(new GuiNpcButton(1, leftX, this.guiTop + 5, 110, 20, this.getNamespaceFilterLabel()));
        this.filteredTypeIds = this.getFilteredTypes();
        this.displayNameToTypeId.clear();
        ArrayList<String> scrollList = new ArrayList<String>();
        for (String typeId : this.filteredTypeIds) {
            String displayName;
            Supplier<AbilityCondition> factory = AbilityController.Instance.getConditionType(typeId);
            String string = displayName = factory != null ? I18n.func_135052_a((String)factory.get().getName(), (Object[])new Object[0]) : typeId;
            if (!this.searchText.isEmpty() && !displayName.toLowerCase().contains(this.searchText.toLowerCase())) continue;
            scrollList.add(displayName);
            this.displayNameToTypeId.put(displayName, typeId);
        }
        int scrollTop = this.guiTop + 29;
        int scrollBottom = btnY - 24;
        int scrollHeight = scrollBottom - scrollTop;
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll(this, 0);
        }
        this.scroll.guiLeft = leftX;
        this.scroll.guiTop = scrollTop;
        this.scroll.setSize(110, scrollHeight);
        this.scroll.setUnsortedList(scrollList);
        if (this.selectedTypeId != null) {
            for (int i = 0; i < scrollList.size(); ++i) {
                String typeId = this.displayNameToTypeId.get(scrollList.get(i));
                if (typeId == null || !typeId.equals(this.selectedTypeId)) continue;
                this.scroll.selected = i;
                break;
            }
        }
        this.addScroll(this.scroll);
        GuiNpcTextField searchField = new GuiNpcTextField(12, this, this.field_146289_q, leftX, scrollBottom + 2, 110, 20, this.searchText);
        searchField.setHoverText("gui.search");
        this.addTextField(searchField);
        int rightX = leftX + 110 + 5;
        int rightW = this.xSize - 10 - 110 - 5;
        int rightTop = this.guiTop + 5;
        int rightHeight = btnY - rightTop - 4;
        ArrayList<FieldDef> fields = new ArrayList();
        if (this.condition != null) {
            fields = this.condition.getAllDefinitions();
        }
        this.builder = new AbilityFieldBuilder(this, this.field_146289_q);
        this.builder.startIds(1000, 2000, 3000);
        this.builder.contentRight(rightW);
        this.builder.startY(5);
        this.builder.buildScrollWindow(fields, rightX, rightTop, rightW, rightHeight);
        this.addButton(new GuiNpcButton(10, leftX, btnY, 60, 20, "gui.cancel"));
        GuiNpcButton doneBtn = new GuiNpcButton(11, this.guiLeft + this.xSize - 5 - 60, btnY, 60, 20, "gui.done");
        if (this.condition == null || !this.condition.isConfigured()) {
            doneBtn.setEnabled(false);
        }
        this.addButton(doneBtn);
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 1) {
            this.cycleNamespaceFilter();
            this.func_73866_w_();
            return;
        }
        if (id == 10) {
            this.result = null;
            this.close();
            return;
        }
        if (id == 11) {
            this.result = this.condition;
            this.close();
            return;
        }
        if (this.builder != null && this.builder.handleButtonEvent(id, guibutton) && !this.hasSubGui()) {
            this.func_73866_w_();
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        String displayName;
        String typeId;
        if (guiCustomScroll.id == 0 && this.scroll.hasSelected() && (typeId = this.displayNameToTypeId.get(displayName = this.scroll.getSelected())) != null && !typeId.equals(this.selectedTypeId)) {
            this.selectedTypeId = typeId;
            this.condition = this.spawnCondition(this.selectedTypeId);
            this.func_73866_w_();
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll guiCustomScroll) {
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        if (textField.id == 12) {
            String newText = textField.func_146179_b();
            if (!newText.equals(this.searchText)) {
                this.searchText = newText;
                this.func_73866_w_();
            }
            return;
        }
        if (textField.id < 1000) {
            return;
        }
        if (this.builder != null && this.builder.handleTextFieldEvent(textField.id, textField)) {
            this.func_73866_w_();
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (this.builder != null && this.builder.handleSubGuiClosed(subgui)) {
            this.func_73866_w_();
        }
    }

    public AbilityCondition getResult() {
        return this.result;
    }
}

