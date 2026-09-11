/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;
import java.util.function.BooleanSupplier;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityAction;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.data.AbilityIconData;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.entry.ChainedAbilityEntry;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.gui.IChainedAbilityFieldProvider;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.ability.CustomAbilitySavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.advanced.SubGuiAbilityConfig;
import noppes.npcs.client.gui.advanced.SubGuiAbilityEditMode;
import noppes.npcs.client.gui.advanced.SubGuiAbilitySelect;
import noppes.npcs.client.gui.advanced.SubGuiAbilityTypeSelect;
import noppes.npcs.client.gui.advanced.SubGuiAbilityVariantSelect;
import noppes.npcs.client.gui.advanced.SubGuiChainedEntrySource;
import noppes.npcs.client.gui.advanced.SubGuiConditionEdit;
import noppes.npcs.client.gui.advanced.SubGuiNpcSlotPicker;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.client.gui.builder.GuiFieldBuilder;
import noppes.npcs.client.gui.script.GuiScriptInterface;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiScrollWindow;
import noppes.npcs.client.gui.util.IAbilityConfigCallback;
import noppes.npcs.client.gui.util.IChainedAbilityConfigCallback;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.data.ChainedAbilityScript;

public class SubGuiChainedAbilityConfig
extends SubGuiInterface
implements ITextfieldListener,
ISubGuiListener,
IAbilityConfigCallback {
    private static final int DECLARATIVE_ID_START = 1000;
    private static final int CLEAR_ID_START = 2000;
    private static final int LABEL_ID_START = 3000;
    private static final int TAB_GENERAL = 0;
    private static final int TAB_ENTRIES = 1;
    private static final int TAB_TARGET = 2;
    private static final int CORE_TAB_COUNT = 3;
    private static final int BTN_TAB_GENERAL = 90;
    private static final int BTN_TAB_ENTRIES = 91;
    private static final int BTN_TAB_TARGET = 92;
    private static final int BTN_TAB_EXTRA_BASE = 93;
    private static final int BTN_SCRIPT = -999;
    private static final int BTN_CLOSE = -1000;
    private static final int ENTRY_BASE = 100;
    private static final int ENTRY_END = 200;
    private static final int ENTRY_STRIDE = 10;
    private static final int BTN_ADD_ENTRY = 200;
    private static final int COND_BASE = 50;
    private static final int COND_STRIDE = 10;
    private static final int COND_END = 100;
    private static final int BTN_ADD_COND = 100;
    private static final int L_LABEL_X = 5;
    private static final int ROW_H = 24;
    private static final int MAX_ENTRIES = 10;
    private final ChainedAbility chain;
    private final IChainedAbilityConfigCallback callback;
    private final boolean npcContext;
    private final boolean readOnlyEntries;
    private final List<AbilityAction> npcSlots;
    private int activeTab = 0;
    private List<FieldDef> fieldDefs;
    private List<ChainedAbilityEntry> entries;
    private List<AbilityCondition> conditions;
    private List<String> extraTabs = new ArrayList<String>();
    private GuiFieldBuilder builder;
    private float[] tabScrollY;
    private int editingEntryIndex = -1;
    private String pendingTypeId;
    private boolean editingParentAbility = false;
    private int editingConditionIndex = -1;
    private boolean saveOnClose = false;
    private final List<Integer> consumedSlotIndices = new ArrayList<Integer>();

    public SubGuiChainedAbilityConfig(ChainedAbility chain, IChainedAbilityConfigCallback callback) {
        this(chain, callback, false, null, false);
    }

    public SubGuiChainedAbilityConfig(ChainedAbility chain, IChainedAbilityConfigCallback callback, boolean npcContext, List<AbilityAction> npcSlots) {
        this(chain, callback, npcContext, npcSlots, false);
    }

    public SubGuiChainedAbilityConfig(ChainedAbility chain, IChainedAbilityConfigCallback callback, boolean npcContext, List<AbilityAction> npcSlots, boolean readOnlyEntries) {
        this.chain = chain;
        this.callback = callback;
        this.npcContext = npcContext;
        this.readOnlyEntries = readOnlyEntries;
        this.npcSlots = npcSlots;
        this.entries = new ArrayList<ChainedAbilityEntry>(chain.getEntries());
        this.conditions = new ArrayList<AbilityCondition>(chain.getConditions());
        this.buildFieldDefs();
        this.setBackground("menubg.png", 217);
        this.xSize = 356;
        this.ySize = 200;
    }

    private void buildFieldDefs() {
        this.fieldDefs = new ArrayList<FieldDef>();
        this.fieldDefs.add(FieldDef.stringField("gui.name", this.chain::getName, this.chain::setName).tab("General"));
        this.fieldDefs.add(FieldDef.stringField("gui.displayName", this.chain::getRawDisplayName, this.chain::setDisplayName).tab("General"));
        this.fieldDefs.add(FieldDef.labelField("ability.validFor", () -> {
            UserType ut = this.computeAllowedBy();
            return "\u00a7e" + StatCollector.func_74838_a((String)("ability.userType." + ut.name()));
        }).tab("General"));
        this.fieldDefs.add(FieldDef.boolField("gui.enabled", this.chain::isEnabled, this.chain::setEnabled).tab("General"));
        this.fieldDefs.add(FieldDef.intField("ability.weight", this.chain::getWeight, this.chain::setWeight).range(1.0f, 100.0f).tab("General"));
        this.fieldDefs.add(FieldDef.boolField("ability.windUpAll", this.chain::isWindUpAll, this.chain::setWindUpAll).tab("General"));
        this.fieldDefs.add(FieldDef.intField("ability.cooldown", this.chain::getCooldownTicks, this.chain::setCooldownTicks).range(0.0f, 12000.0f).tab("General"));
        this.fieldDefs.add(FieldDef.row(FieldDef.floatField("ability.minRange", this.chain::getMinRange, this.chain::setMinRange).range(0.0f, 64.0f), FieldDef.floatField("ability.maxRange", this.chain::getMaxRange, this.chain::setMaxRange).range(0.0f, 64.0f)).tab("Target"));
        AbilityIconData chainIcon = AbilityIconData.fromChainedAbility(this.chain);
        this.fieldDefs.add(FieldDef.row(FieldDef.intField("gui.width", chainIcon::getWidth, chainIcon::setWidth).range(1.0f, 256.0f), FieldDef.intField("gui.height", chainIcon::getHeight, chainIcon::setHeight).range(1.0f, 256.0f)).tab("Icon"));
        this.fieldDefs.add(FieldDef.floatField("gui.scale", chainIcon::getScale, chainIcon::setScale).tab("Icon").range(0.1f, 10.0f));
        this.fieldDefs.add(FieldDef.intField("ability.icon.layers", chainIcon::getLayerCount, chainIcon::setLayerCount).tab("Icon").range(1.0f, 3.0f));
        int i = 0;
        while (i < 3) {
            int idx = i++;
            BooleanSupplier layerVisible = () -> chainIcon.getLayerCount() > idx;
            String sectionLabel = StatCollector.func_74838_a((String)"ability.icon.layer") + " " + (idx + 1);
            this.fieldDefs.add(FieldDef.section(sectionLabel).tab("Icon").visibleWhen(layerVisible));
            this.fieldDefs.add(FieldDef.textureSubGui("gui.texture", () -> chainIcon.getLayer((int)idx).texture, t -> chainIcon.setLayerTexture(idx, (String)t)).tab("Icon").visibleWhen(layerVisible));
            this.fieldDefs.add(FieldDef.row(FieldDef.intField("ability.icon.x", () -> chainIcon.getLayer((int)idx).iconX, x -> chainIcon.setLayerIconX(idx, (int)x)).range(0.0f, 4096.0f), FieldDef.intField("ability.icon.y", () -> chainIcon.getLayer((int)idx).iconY, y -> chainIcon.setLayerIconY(idx, (int)y)).range(0.0f, 4096.0f)).tab("Icon").visibleWhen(layerVisible));
            this.fieldDefs.add(FieldDef.colorSubGui("ability.icon.tint", () -> chainIcon.getLayer((int)idx).tintColor, c -> chainIcon.setLayerTintColor(idx, (int)c)).tab("Icon").visibleWhen(layerVisible));
        }
        this.fieldDefs.add(FieldDef.section("Animation").tab("Icon"));
        this.fieldDefs.add(FieldDef.boolField("gui.animated", chainIcon::isAnimated, chainIcon::setAnimated).tab("Icon").hover("gui.animated.hover"));
        this.fieldDefs.add(FieldDef.intField("gui.frameCount", chainIcon::getFrameCount, chainIcon::setFrameCount).tab("Icon").range(1.0f, 256.0f).visibleWhen(chainIcon::isAnimated));
        this.fieldDefs.add(FieldDef.intField("gui.frameTime", chainIcon::getFrameTime, chainIcon::setFrameTime).tab("Icon").range(1.0f, 100.0f).visibleWhen(chainIcon::isAnimated));
        if (AbilityController.Instance != null) {
            for (IChainedAbilityFieldProvider provider : AbilityController.Instance.getChainedFieldProviders()) {
                provider.addFieldDefinitions(this.chain, this.fieldDefs);
            }
        }
        LinkedHashSet<String> tabNames = new LinkedHashSet<String>();
        for (FieldDef def : this.fieldDefs) {
            String tab = def.getTab();
            if (tab == null || tab.isEmpty() || "General".equals(tab) || "Target".equals(tab)) continue;
            tabNames.add(tab);
        }
        this.extraTabs = new ArrayList<String>(tabNames);
        this.tabScrollY = new float[3 + this.extraTabs.size()];
    }

    private UserType computeAllowedBy() {
        if (this.entries.isEmpty()) {
            return UserType.BOTH;
        }
        boolean allAllowPlayer = true;
        boolean allAllowNpc = true;
        for (ChainedAbilityEntry entry : this.entries) {
            Ability a = entry.resolve();
            if (a == null) continue;
            UserType ut = a.getAllowedBy();
            if (!ut.allowsPlayer()) {
                allAllowPlayer = false;
            }
            if (ut.allowsNpc()) continue;
            allAllowNpc = false;
        }
        if (allAllowPlayer && allAllowNpc) {
            return UserType.BOTH;
        }
        if (allAllowPlayer) {
            return UserType.PLAYER_ONLY;
        }
        if (allAllowNpc) {
            return UserType.NPC_ONLY;
        }
        return UserType.NONE;
    }

    @Override
    public void func_73866_w_() {
        int extraIndex;
        GuiNpcTextField.unfocus();
        GuiScrollWindow oldSw = this.getScrollableGui(0);
        if (oldSw != null && this.activeTab < this.tabScrollY.length) {
            this.tabScrollY[this.activeTab] = oldSw.nextScrollY;
        }
        super.func_73866_w_();
        GuiMenuTopButton generalTab = new GuiMenuTopButton(90, this.guiLeft + 4, this.guiTop - 17, "menu.general");
        generalTab.active = this.activeTab == 0;
        this.addTopButton(generalTab);
        GuiMenuTopButton entriesTab = new GuiMenuTopButton(91, generalTab, "ability.entries");
        entriesTab.active = this.activeTab == 1;
        this.addTopButton(entriesTab);
        GuiMenuTopButton targetTab = new GuiMenuTopButton(92, entriesTab, "script.target");
        targetTab.active = this.activeTab == 2;
        this.addTopButton(targetTab);
        GuiMenuTopButton prevTab = targetTab;
        for (int i = 0; i < this.extraTabs.size(); ++i) {
            int tabIndex = 3 + i;
            GuiMenuTopButton extraTab = new GuiMenuTopButton(93 + i, prevTab, this.extraTabs.get(i));
            extraTab.active = this.activeTab == tabIndex;
            this.addTopButton(extraTab);
            prevTab = extraTab;
        }
        GuiMenuTopButton closeBtn = new GuiMenuTopButton(-1000, this.guiLeft + this.xSize - 22, this.guiTop - 17, "X");
        this.addTopButton(closeBtn);
        GuiMenuTopButton scriptBtn = new GuiMenuTopButton(-999, "script.scripts", closeBtn);
        this.addTopButton(scriptBtn);
        int swX = this.guiLeft + 4;
        int swY = this.guiTop + 5;
        int swW = this.xSize - 8;
        int swH = this.ySize - 10;
        if (this.activeTab == 0) {
            this.buildFieldDefTab("General", swX, swY, swW, swH);
        } else if (this.activeTab == 1) {
            this.buildEntriesTab(swX, swY, swW, swH);
        } else if (this.activeTab == 2) {
            this.buildTargetTab(swX, swY, swW, swH);
        } else if (this.activeTab >= 3 && (extraIndex = this.activeTab - 3) < this.extraTabs.size()) {
            this.buildFieldDefTab(this.extraTabs.get(extraIndex), swX, swY, swW, swH);
        }
    }

    private void buildFieldDefTab(String tabName, int swX, int swY, int swW, int swH) {
        ArrayList<FieldDef> tabFields = new ArrayList<FieldDef>();
        for (FieldDef def : this.fieldDefs) {
            if (!tabName.equals(def.getTab()) || !def.isVisible()) continue;
            tabFields.add(def);
        }
        this.builder = new GuiFieldBuilder(this, this.field_146289_q);
        this.builder.startIds(1000, 2000, 3000);
        this.builder.startY(5);
        GuiScrollWindow sw = this.builder.buildScrollWindow(tabFields, swX, swY, swW, swH);
        this.restoreScroll(sw);
    }

    private void buildEntriesTab(int swX, int swY, int swW, int swH) {
        GuiScrollWindow sw = new GuiScrollWindow(this, swX, swY, swW, swH, 0);
        sw.backgroundColor = -2013265920;
        this.addScrollableGui(0, sw);
        int y = 5;
        int labelCounter = 0;
        for (int i = 0; i < this.entries.size() && i < 10; ++i) {
            String btnLabel;
            ChainedAbilityEntry entry = this.entries.get(i);
            sw.addLabel(new GuiNpcLabel(labelCounter++, i + 1 + ".", 5, y + 5, 0xFFFFFF));
            if (entry.isInline()) {
                Ability a = entry.getInlineAbility();
                btnLabel = a != null ? a.getDisplayName() : "\u00a7cEmpty";
            } else {
                String refName = entry.getAbilityReference();
                if (refName == null || refName.isEmpty()) {
                    btnLabel = StatCollector.func_74838_a((String)"gui.select") + "...";
                } else {
                    Ability resolved = entry.resolve();
                    btnLabel = "\u00a7e> " + (resolved != null ? resolved.getDisplayName() : refName);
                }
            }
            GuiNpcButton entryBtn = new GuiNpcButton(100 + i * 10, 20, y, 140, 20, btnLabel);
            if (this.readOnlyEntries) {
                entryBtn.setEnabled(false);
            }
            sw.addButton(entryBtn);
            if (!this.readOnlyEntries) {
                Ability resolvedAbility = entry.resolve();
                boolean isConcurrentActive = resolvedAbility != null && resolvedAbility.isConcurrentCapable() && entry.isConcurrentEnabled();
                sw.addLabel(new GuiNpcLabel(labelCounter++, "ability.delay", 165, y + 5, isConcurrentActive ? 0x555555 : 0xAAAAAA));
                GuiNpcTextField delayField = new GuiNpcTextField(100 + i * 10 + 1, this, this.field_146289_q, 200, y, 40, 20, String.valueOf(entry.getDelayTicks()));
                delayField.setIntegersOnly();
                delayField.setMinMaxDefault(0, 6000, 0);
                if (isConcurrentActive) {
                    delayField.func_146184_c(false);
                }
                sw.addTextField(delayField);
                if (i > 0) {
                    sw.addButton(new GuiNpcButton(100 + i * 10 + 2, 245, y, 20, 20, "\u2191"));
                }
                if (i < this.entries.size() - 1) {
                    sw.addButton(new GuiNpcButton(100 + i * 10 + 3, 268, y, 20, 20, "\u2193"));
                }
                if (i > 0 && resolvedAbility != null && resolvedAbility.isConcurrentCapable()) {
                    String cLabel = entry.isConcurrentEnabled() ? "\u00a7bC" : "\u00a77C";
                    GuiNpcButton cBtn = new GuiNpcButton(100 + i * 10 + 5, 293, y, 20, 20, cLabel);
                    cBtn.setHoverText("ability.hover.concurrent");
                    sw.addButton(cBtn);
                }
                sw.addButton(new GuiNpcButton(100 + i * 10 + 4, 318, y, 20, 20, "X"));
            } else {
                int delay = entry.getDelayTicks();
                if (delay > 0) {
                    sw.addLabel(new GuiNpcLabel(labelCounter++, delay + "t", 170, y + 5, 0x888888));
                }
            }
            y += 24;
        }
        if (!this.readOnlyEntries && this.entries.size() < 10) {
            sw.addButton(new GuiNpcButton(200, 5, y, 80, 20, "gui.add"));
            y += 24;
        }
        sw.maxScrollY = Math.max(y - swH, 0);
        this.restoreScroll(sw);
    }

    private void buildTargetTab(int swX, int swY, int swW, int swH) {
        ArrayList<FieldDef> tabFields = new ArrayList<FieldDef>();
        for (FieldDef def : this.fieldDefs) {
            if (!"Target".equals(def.getTab()) || !def.isVisible()) continue;
            tabFields.add(def);
        }
        this.builder = new GuiFieldBuilder(this, this.field_146289_q);
        this.builder.startIds(1000, 2000, 3000);
        this.builder.startY(5);
        GuiScrollWindow sw = this.builder.buildScrollWindow(tabFields, swX, swY, swW, swH);
        int y = this.builder.getLastBuildY();
        int condY = this.renderConditions(sw, y, this.builder.getNextLabelId());
        sw.maxScrollY = Math.max(condY - swH, 0);
        this.restoreScroll(sw);
    }

    private int renderConditions(GuiScrollWindow sw, int y, int labelCounter) {
        sw.addLabel(new GuiNpcLabel(labelCounter, "ability.conditions", 5, (y += 3) + 2, 0xFFFF55));
        y += 15;
        for (int i = 0; i < this.conditions.size() && i < 5; ++i) {
            AbilityCondition cond = this.conditions.get(i);
            String condName = this.getConditionDisplayName(cond);
            sw.addButton(new GuiNpcButton(50 + i * 10, 5, y, 140, 20, condName));
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

    private void restoreScroll(GuiScrollWindow sw) {
        if (this.activeTab < this.tabScrollY.length) {
            float restored;
            sw.nextScrollY = restored = Math.min(this.tabScrollY[this.activeTab], (float)sw.maxScrollY);
            sw.scrollY = restored;
        }
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
        if (id == -999) {
            GuiScriptInterface.open(this.parent, new ChainedAbilityScript(this.chain.getId()));
            return;
        }
        if (id == -1000) {
            this.saveOnClose = true;
            this.close();
            return;
        }
        if (id >= 93 && id < 93 + this.extraTabs.size()) {
            this.activeTab = 3 + (id - 93);
            this.func_73866_w_();
            return;
        }
        if (this.activeTab == 1 && this.handleEntryButton(id)) {
            return;
        }
        if (this.activeTab == 2 && this.handleConditionButton(id)) {
            return;
        }
        if (this.isFieldDefTab() && this.builder != null && this.builder.handleButtonEvent(id, guibutton)) {
            if (!this.hasSubGui()) {
                this.func_73866_w_();
            }
            return;
        }
    }

    private boolean isFieldDefTab() {
        return this.activeTab == 0 || this.activeTab == 2 || this.activeTab >= 3;
    }

    private boolean handleEntryButton(int id) {
        if (id == 200) {
            if (this.entries.size() < 10) {
                this.setSubGui(new SubGuiChainedEntrySource(this.npcContext));
            }
            return true;
        }
        if (id >= 100 && id < 200) {
            int entryIndex = (id - 100) / 10;
            int action = (id - 100) % 10;
            if (entryIndex < 0 || entryIndex >= this.entries.size()) {
                return false;
            }
            switch (action) {
                case 0: {
                    ChainedAbilityEntry clickedEntry = this.entries.get(entryIndex);
                    if (clickedEntry.isReference()) {
                        Ability refResolved = clickedEntry.resolve();
                        if (refResolved != null && refResolved.isBuiltIn()) {
                            return true;
                        }
                        this.editingEntryIndex = entryIndex;
                        this.setSubGui(new SubGuiAbilityEditMode());
                    } else if (clickedEntry.isInline() && clickedEntry.getInlineAbility() != null) {
                        Ability inlineAbility = clickedEntry.getInlineAbility();
                        if (inlineAbility.isBuiltIn()) {
                            return true;
                        }
                        this.editingEntryIndex = entryIndex;
                        this.setSubGui(new SubGuiAbilityConfig(inlineAbility, this));
                    }
                    return true;
                }
                case 2: {
                    if (entryIndex > 0) {
                        GuiNpcTextField.unfocus();
                        ChainedAbilityEntry entry = this.entries.remove(entryIndex);
                        this.entries.add(entryIndex - 1, entry);
                        if (entryIndex - 1 == 0) {
                            entry.setConcurrentEnabled(false);
                        }
                        this.func_73866_w_();
                    }
                    return true;
                }
                case 3: {
                    if (entryIndex < this.entries.size() - 1) {
                        GuiNpcTextField.unfocus();
                        ChainedAbilityEntry entry = this.entries.remove(entryIndex);
                        this.entries.add(entryIndex + 1, entry);
                        this.func_73866_w_();
                    }
                    return true;
                }
                case 4: {
                    GuiNpcTextField.unfocus();
                    this.entries.remove(entryIndex);
                    this.func_73866_w_();
                    return true;
                }
                case 5: {
                    ChainedAbilityEntry toggleEntry = this.entries.get(entryIndex);
                    boolean newState = !toggleEntry.isConcurrentEnabled();
                    toggleEntry.setConcurrentEnabled(newState);
                    if (newState) {
                        toggleEntry.setDelayTicks(0);
                    }
                    this.func_73866_w_();
                    return true;
                }
            }
        }
        return false;
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
        int entryIndex;
        int id = textField.id;
        if (this.isFieldDefTab() && this.builder != null && id >= 1000) {
            if (this.builder.handleTextFieldEvent(id, textField)) {
                this.func_73866_w_();
            }
            return;
        }
        if (this.activeTab == 1 && id >= 101 && id < 200 && (entryIndex = (id - 100 - 1) / 10) >= 0 && entryIndex < this.entries.size()) {
            this.entries.get(entryIndex).setDelayTicks(textField.getInteger());
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (this.isFieldDefTab() && this.builder != null && this.builder.handleSubGuiClosed(subgui)) {
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiAbilitySelect) {
            this.handleAbilitySelectClosed((SubGuiAbilitySelect)subgui);
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiChainedEntrySource) {
            this.handleEntrySourceClosed((SubGuiChainedEntrySource)subgui);
            return;
        }
        if (subgui instanceof SubGuiNpcSlotPicker) {
            this.handleSlotPickerClosed((SubGuiNpcSlotPicker)subgui);
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiAbilityVariantSelect) {
            this.handleVariantSelectClosed((SubGuiAbilityVariantSelect)subgui);
            if (!this.hasSubGui()) {
                this.func_73866_w_();
            }
            return;
        }
        if (subgui instanceof SubGuiAbilityTypeSelect) {
            this.handleTypeSelectClosed((SubGuiAbilityTypeSelect)subgui);
            if (!this.hasSubGui()) {
                this.func_73866_w_();
            }
            return;
        }
        if (subgui instanceof SubGuiAbilityEditMode) {
            this.handleEditModeClosed((SubGuiAbilityEditMode)subgui);
            if (!this.hasSubGui()) {
                this.func_73866_w_();
            }
            return;
        }
        if (subgui instanceof SubGuiAbilityConfig) {
            this.editingEntryIndex = -1;
            this.editingParentAbility = false;
            this.func_73866_w_();
            return;
        }
        if (subgui instanceof SubGuiConditionEdit) {
            this.handleConditionEditClosed((SubGuiConditionEdit)subgui);
            this.func_73866_w_();
            return;
        }
    }

    private void handleAbilitySelectClosed(SubGuiAbilitySelect gui) {
        String selectedName = gui.getSelectedName();
        if (selectedName != null) {
            if (this.editingEntryIndex >= 0 && this.editingEntryIndex < this.entries.size()) {
                int delay = this.entries.get(this.editingEntryIndex).getDelayTicks();
                this.entries.set(this.editingEntryIndex, ChainedAbilityEntry.reference(selectedName, delay));
            } else {
                this.entries.add(ChainedAbilityEntry.reference(selectedName, 0));
            }
        }
        this.editingEntryIndex = -1;
    }

    private void handleEntrySourceClosed(SubGuiChainedEntrySource gui) {
        int source = gui.getResult();
        if (source == 0) {
            this.setSubGui(new SubGuiNpcSlotPicker(this.npcSlots));
        } else if (source == 1) {
            this.setSubGui(new SubGuiAbilitySelect(1));
        } else if (source == 2) {
            this.setSubGui(new SubGuiAbilityTypeSelect());
        } else if (source == 3) {
            this.setSubGui(new SubGuiAbilitySelect(2));
        }
    }

    private void handleSlotPickerClosed(SubGuiNpcSlotPicker gui) {
        AbilityAction consumed;
        Ability ability;
        int slotIndex = gui.getSelectedIndex();
        if (slotIndex >= 0 && this.npcSlots != null && slotIndex < this.npcSlots.size() && (ability = (consumed = this.npcSlots.get(slotIndex)).getAbility()) != null) {
            if (consumed.getSlotType() == AbilityAction.SlotType.INLINE_ABILITY) {
                this.entries.add(ChainedAbilityEntry.inline(ability, 0));
            } else {
                this.entries.add(ChainedAbilityEntry.reference(consumed.getReferenceId(), 0));
            }
            if (!this.consumedSlotIndices.contains(slotIndex)) {
                this.consumedSlotIndices.add(slotIndex);
            }
        }
    }

    private void handleTypeSelectClosed(SubGuiAbilityTypeSelect gui) {
        String typeId = gui.getSelectedTypeId();
        if (typeId != null) {
            List<AbilityVariant> variants = AbilityController.Instance.getVariantsForType(typeId);
            if (variants.size() > 1) {
                this.pendingTypeId = typeId;
                this.setSubGui(new SubGuiAbilityVariantSelect(variants));
                return;
            }
            Ability newAbility = AbilityController.Instance.create(typeId);
            if (newAbility != null && !newAbility.isBuiltIn()) {
                if (variants.size() == 1) {
                    variants.get(0).apply(newAbility);
                }
                newAbility.setId(UUID.randomUUID().toString());
                this.entries.add(ChainedAbilityEntry.inline(newAbility, 0));
                this.editingEntryIndex = this.entries.size() - 1;
                this.setSubGui(new SubGuiAbilityConfig(newAbility, this));
            }
        }
    }

    private void handleVariantSelectClosed(SubGuiAbilityVariantSelect gui) {
        Ability newAbility;
        int idx = gui.getSelectedIndex();
        if (idx >= 0 && this.pendingTypeId != null && (newAbility = AbilityController.Instance.create(this.pendingTypeId)) != null && !newAbility.isBuiltIn()) {
            gui.getVariants().get(idx).apply(newAbility);
            newAbility.setId(UUID.randomUUID().toString());
            this.entries.add(ChainedAbilityEntry.inline(newAbility, 0));
            this.editingEntryIndex = this.entries.size() - 1;
            this.pendingTypeId = null;
            this.setSubGui(new SubGuiAbilityConfig(newAbility, this));
            return;
        }
        this.pendingTypeId = null;
    }

    private void handleEditModeClosed(SubGuiAbilityEditMode gui) {
        int mode = gui.getResult();
        if (mode < 0 || this.editingEntryIndex < 0 || this.editingEntryIndex >= this.entries.size()) {
            this.editingEntryIndex = -1;
            return;
        }
        ChainedAbilityEntry entry = this.entries.get(this.editingEntryIndex);
        Ability preCheck = entry.resolve();
        if (preCheck != null && preCheck.isBuiltIn()) {
            this.editingEntryIndex = -1;
            return;
        }
        if (mode == 0) {
            Ability a;
            if (entry.convertToInline() && (a = entry.getInlineAbility()) != null && !a.isBuiltIn()) {
                a.setId(UUID.randomUUID().toString());
                this.setSubGui(new SubGuiAbilityConfig(a, this));
                return;
            }
            this.editingEntryIndex = -1;
        } else if (mode == 1) {
            Ability resolved = entry.resolve();
            if (resolved != null && !resolved.isBuiltIn()) {
                this.editingParentAbility = true;
                this.setSubGui(resolved.createConfigGui(this));
                return;
            }
            this.editingEntryIndex = -1;
        }
    }

    private void handleConditionEditClosed(SubGuiConditionEdit gui) {
        AbilityCondition result = gui.getResult();
        if (result != null && this.editingConditionIndex >= 0) {
            if (this.editingConditionIndex < this.conditions.size()) {
                this.conditions.set(this.editingConditionIndex, result);
            } else {
                this.conditions.add(result);
            }
        }
        this.editingConditionIndex = -1;
    }

    @Override
    public void onAbilitySaved(Ability ability) {
        if (this.editingParentAbility) {
            PacketClient.sendClient(new CustomAbilitySavePacket(ability.writeNBT(false)));
            this.editingParentAbility = false;
            return;
        }
    }

    @Override
    public void close() {
        if (this.saveOnClose) {
            GuiNpcTextField.unfocus();
            this.applyToChain();
            this.callback.onChainedAbilitySaved(this.chain);
        }
        super.close();
    }

    private void applyToChain() {
        this.chain.getEntries().clear();
        for (ChainedAbilityEntry entry : this.entries) {
            this.chain.addEntry(entry);
        }
        this.chain.getConditions().clear();
        for (AbilityCondition c : this.conditions) {
            this.chain.getConditions().add(c);
        }
        if (this.npcSlots != null && !this.consumedSlotIndices.isEmpty()) {
            Collections.sort(this.consumedSlotIndices, Collections.reverseOrder());
            Iterator<Object> iterator = this.consumedSlotIndices.iterator();
            while (iterator.hasNext()) {
                int idx = (Integer)iterator.next();
                if (idx >= this.npcSlots.size()) continue;
                this.npcSlots.remove(idx);
            }
        }
    }
}

