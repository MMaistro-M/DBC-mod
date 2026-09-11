/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldBuilder;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.SubGuiTagSelect;
import noppes.npcs.client.gui.advanced.SubGuiConditionEdit;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.client.gui.script.GuiScriptInterface;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiScrollWindow;
import noppes.npcs.client.gui.util.IAbilityConfigCallback;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.AbilityScript;

public class SubGuiAbilityConfig
extends SubGuiInterface
implements ITextfieldListener,
ISubGuiListener {
    private static final int DECLARATIVE_ID_START = 1000;
    private static final int CLEAR_ID_START = 2000;
    private static final int LABEL_ID_START = 3000;
    private static final int TAB_GENERAL = 0;
    private static final int TAB_TYPE = 1;
    private static final int TAB_TARGET = 2;
    private static final int TAB_EFFECTS = 3;
    private static final String TAB_NAME_GENERAL = "General";
    private static final String TAB_NAME_TYPE = "Type";
    private static final String TAB_NAME_TARGET = "Target";
    private static final String TAB_NAME_EFFECTS = "Effects";
    private static final int L_LABEL_X = 5;
    private static final int ROW_H = 24;
    private static final int COND_BASE = 50;
    private static final int COND_STRIDE = 10;
    private static final int COND_END = 100;
    private static final int BTN_ADD_COND = 100;
    private final Ability ability;
    private final IAbilityConfigCallback callback;
    private int activeTab = 0;
    private List<FieldDef> fieldDefs;
    private List<String> customTabNames;
    private List<AbilityCondition> conditions;
    private int editingConditionIndex = -1;
    private AbilityFieldBuilder builder;
    private float[] tabScrollY;

    public SubGuiAbilityConfig(Ability ability, IAbilityConfigCallback callback) {
        this.ability = ability;
        this.callback = callback;
        this.conditions = new ArrayList<AbilityCondition>(ability.getConditions());
        this.fieldDefs = ability.getAllDefinitions();
        this.discoverCustomTabs();
        this.setBackground("menubg.png", 217);
        this.xSize = 356;
        this.ySize = 200;
    }

    private void discoverCustomTabs() {
        this.customTabNames = new ArrayList<String>();
        for (FieldDef def : this.fieldDefs) {
            String tab = def.getTab();
            if (tab == null || TAB_NAME_GENERAL.equals(tab) || TAB_NAME_TYPE.equals(tab) || TAB_NAME_TARGET.equals(tab) || TAB_NAME_EFFECTS.equals(tab) || this.customTabNames.contains(tab)) continue;
            this.customTabNames.add(tab);
        }
        this.tabScrollY = new float[4 + this.customTabNames.size()];
    }

    @Override
    public void func_73866_w_() {
        GuiNpcTextField.unfocus();
        GuiScrollWindow oldSw = this.getScrollableGui(0);
        if (oldSw != null && this.activeTab < this.tabScrollY.length) {
            this.tabScrollY[this.activeTab] = oldSw.nextScrollY;
        }
        super.func_73866_w_();
        GuiMenuTopButton generalTab = new GuiMenuTopButton(90, this.guiLeft + 4, this.guiTop - 17, "menu.general");
        generalTab.active = this.activeTab == 0;
        this.addTopButton(generalTab);
        GuiMenuTopButton lastTab = generalTab;
        GuiMenuTopButton typeTab = new GuiMenuTopButton(91, lastTab, "gui.type");
        typeTab.active = this.activeTab == 1;
        this.addTopButton(typeTab);
        lastTab = typeTab;
        GuiMenuTopButton targetTab = new GuiMenuTopButton(92, lastTab, "script.target");
        targetTab.active = this.activeTab == 2;
        this.addTopButton(targetTab);
        lastTab = targetTab;
        GuiMenuTopButton effectsTab = new GuiMenuTopButton(93, lastTab, "ability.tab.effects");
        effectsTab.active = this.activeTab == 3;
        this.addTopButton(effectsTab);
        lastTab = effectsTab;
        for (int i = 0; i < this.customTabNames.size(); ++i) {
            GuiMenuTopButton ct = new GuiMenuTopButton(94 + i, lastTab, this.customTabNames.get(i));
            ct.active = this.activeTab == 4 + i;
            this.addTopButton(ct);
            lastTab = ct;
        }
        GuiMenuTopButton closeBtn = new GuiMenuTopButton(-1000, this.guiLeft + this.xSize - 22, this.guiTop - 17, "X");
        this.addTopButton(closeBtn);
        GuiMenuTopButton scriptBtn = new GuiMenuTopButton(-999, "script.scripts", closeBtn);
        this.addTopButton(scriptBtn);
        int swX = this.guiLeft + 4;
        int swY = this.guiTop + 5;
        int swW = this.xSize - 8;
        int swH = this.ySize - 10;
        int y = 5;
        int labelCounter = 3000;
        if (this.activeTab == 0) {
            y += 24;
            if ("ability.cnpc.custom".equals(this.ability.getTypeId())) {
                y += 15;
            }
            if (this.ability.isConcurrentCapable()) {
                y += 15;
            }
        }
        List<FieldDef> tabFields = this.getVisibleFieldsForTab(this.activeTab);
        this.builder = new AbilityFieldBuilder(this, this.field_146289_q);
        this.builder.startIds(1000, 2000, labelCounter);
        this.builder.startY(y);
        GuiScrollWindow sw = this.builder.buildScrollWindow(tabFields, swX, swY, swW, swH);
        if (this.activeTab == 0) {
            int baseId = this.builder.getNextLabelId();
            int infoY = 25;
            sw.addLabel(new GuiNpcLabel(baseId, "gui.type", 5, 10, 0xFFFFFF));
            sw.addLabel(new GuiNpcLabel(baseId + 1, this.ability.getTypeId(), 55, 10, 0xFFFFFF));
            if ("ability.cnpc.custom".equals(this.ability.getTypeId())) {
                sw.addLabel(new GuiNpcLabel(baseId + 2, StatCollector.func_74838_a((String)"ability.customScriptInfo"), 5, infoY, 0xFF55FF));
                infoY += 15;
            }
            if (this.ability.isConcurrentCapable()) {
                sw.addLabel(new GuiNpcLabel(baseId + 3, StatCollector.func_74838_a((String)"ability.concurrentInfo"), 5, infoY, 0x55FFFF));
            }
        }
        if (this.activeTab == 0) {
            sw.addButton(new GuiNpcButton(40, sw.clipWidth - 95, 5, 80, 20, "gui.tags"));
        }
        if (this.activeTab == 2) {
            int condY = this.renderConditions(sw, this.builder.getLastBuildY(), this.builder.getNextLabelId());
            sw.maxScrollY = Math.max(condY - swH, 0);
        }
        if (this.activeTab < this.tabScrollY.length) {
            float restored;
            sw.nextScrollY = restored = Math.min(this.tabScrollY[this.activeTab], (float)sw.maxScrollY);
            sw.scrollY = restored;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private List<FieldDef> getVisibleFieldsForTab(int tabIndex) {
        String tabName;
        ArrayList<FieldDef> result = new ArrayList<FieldDef>();
        if (tabIndex >= 4) {
            int customIndex = tabIndex - 4;
            if (customIndex < 0 || customIndex >= this.customTabNames.size()) return result;
            tabName = this.customTabNames.get(customIndex);
        } else {
            switch (tabIndex) {
                case 0: {
                    tabName = TAB_NAME_GENERAL;
                    break;
                }
                case 1: {
                    tabName = TAB_NAME_TYPE;
                    break;
                }
                case 2: {
                    tabName = TAB_NAME_TARGET;
                    break;
                }
                case 3: {
                    tabName = TAB_NAME_EFFECTS;
                    break;
                }
                default: {
                    tabName = TAB_NAME_TYPE;
                }
            }
        }
        for (FieldDef def : this.fieldDefs) {
            if (!tabName.equals(def.getTab()) || !def.isVisible()) continue;
            result.add(def);
        }
        return result;
    }

    private int renderConditions(GuiScrollWindow sw, int y, int labelCounter) {
        sw.addLabel(new GuiNpcLabel(labelCounter, "ability.conditions", 5, (y += 3) + 2, 0xFFFF55));
        y += 15;
        for (int i = 0; i < this.conditions.size() && i < 5; ++i) {
            AbilityCondition cond = this.conditions.get(i);
            String condName = this.getConditionDisplayName(cond);
            GuiNpcButton condBtn = new GuiNpcButton(50 + i * 10, 5, y, 140, 20, condName);
            condBtn.hoverableText = cond.getConditionSummary();
            sw.addButton(condBtn);
            sw.addButton(new GuiNpcButton(50 + i * 10 + 1, 150, y, 40, 20, "gui.edit"));
            sw.addButton(new GuiNpcButton(50 + i * 10 + 2, 195, y, 20, 20, "X"));
            y += 22;
        }
        if (this.conditions.size() < 5) {
            sw.addButton(new GuiNpcButton(100, 5, y, 50, 20, "gui.add"));
            y += 24;
        }
        return y;
    }

    private String getConditionDisplayName(AbilityCondition cond) {
        if (cond == null) {
            return "None";
        }
        String name = cond.getName();
        return StatCollector.func_74838_a((String)name);
    }

    @Override
    public void buttonEvent(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 90) {
            this.activeTab = 0;
            this.func_73866_w_();
            return;
        }
        if (id == 91) {
            this.activeTab = 1;
            this.func_73866_w_();
            return;
        }
        if (id == 92) {
            this.activeTab = 2;
            this.func_73866_w_();
            return;
        }
        if (id == 93) {
            this.activeTab = 3;
            this.func_73866_w_();
            return;
        }
        if (id >= 94 && id < 94 + this.customTabNames.size()) {
            this.activeTab = 4 + (id - 94);
            this.func_73866_w_();
            return;
        }
        if (id == -999) {
            GuiNpcTextField.unfocus();
            this.applyToAbility();
            this.callback.onAbilitySaved(this.ability);
            GuiScriptInterface.open(this.parent, new AbilityScript(this.ability.getId()));
            return;
        }
        if (id == 40) {
            this.setSubGui(new SubGuiTagSelect(this.ability.getTagUUIDs()));
            return;
        }
        if (id == -1000) {
            this.close();
            return;
        }
        if (this.handleConditionButton(id)) {
            return;
        }
        if (this.builder.handleButtonEvent(id, guibutton)) {
            if (!this.hasSubGui()) {
                this.func_73866_w_();
            }
            return;
        }
    }

    private boolean handleConditionButton(int id) {
        if (id >= 50 && id < 100) {
            int condIndex = (id - 50) / 10;
            int action = (id - 50) % 10;
            if (action == 0 || action == 1) {
                if (condIndex < this.conditions.size()) {
                    this.editingConditionIndex = condIndex;
                    this.setSubGui(new SubGuiConditionEdit(this.conditions.get(condIndex)));
                }
            } else if (action == 2 && condIndex < this.conditions.size()) {
                this.conditions.remove(condIndex);
                this.func_73866_w_();
            }
            return true;
        }
        if (id == 100) {
            if (this.conditions.size() < 5) {
                this.editingConditionIndex = this.conditions.size();
                this.setSubGui(new SubGuiConditionEdit(null));
            }
            return true;
        }
        return false;
    }

    @Override
    public void unFocused(GuiNpcTextField textField) {
        if (textField.id < 1000) {
            return;
        }
        if (this.builder.handleTextFieldEvent(textField.id, textField)) {
            this.func_73866_w_();
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (this.builder.handleSubGuiClosed(subgui)) {
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiConditionEdit) {
            SubGuiConditionEdit condEdit = (SubGuiConditionEdit)subgui;
            AbilityCondition result = condEdit.getResult();
            if (result != null && this.editingConditionIndex >= 0) {
                if (this.editingConditionIndex < this.conditions.size()) {
                    this.conditions.set(this.editingConditionIndex, result);
                } else {
                    this.conditions.add(result);
                }
            }
            this.editingConditionIndex = -1;
            this.func_73866_w_();
        }
    }

    @Override
    public void close() {
        GuiNpcTextField.unfocus();
        this.applyToAbility();
        this.callback.onAbilitySaved(this.ability);
        super.close();
    }

    protected void applyToAbility() {
        this.ability.getConditions().clear();
        for (AbilityCondition c : this.conditions) {
            this.ability.addCondition(c);
        }
    }

    public void loadAbility(Ability loadedAbility) {
        if (loadedAbility == null) {
            return;
        }
        NBTTagCompound nbt = loadedAbility.writeNBT(false);
        nbt.func_74778_a("typeId", this.ability.getTypeId());
        this.ability.readNBT(nbt);
        this.conditions = new ArrayList<AbilityCondition>(this.ability.getConditions());
        this.fieldDefs = this.ability.getAllDefinitions();
        this.discoverCustomTabs();
        this.func_73866_w_();
    }

    protected Ability getAbility() {
        return this.ability;
    }
}

