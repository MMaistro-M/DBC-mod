/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 */
package kamkeel.npcs.controllers.data.ability.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityCustomEffect;
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityPotionEffect;
import kamkeel.npcs.controllers.data.ability.data.effect.IEffectAction;
import kamkeel.npcs.controllers.data.ability.data.entry.AbilityEffectActionEntry;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import noppes.npcs.client.gui.advanced.SubGuiCustomEffectSelect;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.client.gui.builder.FieldType;
import noppes.npcs.client.gui.builder.GuiFieldBuilder;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.constants.EnumPotionType;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.data.CustomEffect;

@SideOnly(value=Side.CLIENT)
public class AbilityFieldBuilder
extends GuiFieldBuilder {
    private final Map<Integer, int[]> effectWidgetMeta = new HashMap<Integer, int[]>();
    private int[] sortedCustomEffectIds;
    private String[] sortedCustomEffectNames;
    private String[] effectActionIds;
    private String[] effectActionDisplayNames;

    public AbilityFieldBuilder(GuiNPCInterface parent, FontRenderer fontRenderer) {
        super(parent, fontRenderer);
    }

    @Override
    protected int build(List<FieldDef> fields) {
        this.effectWidgetMeta.clear();
        this.sortedCustomEffectIds = null;
        this.sortedCustomEffectNames = null;
        this.effectActionIds = null;
        this.effectActionDisplayNames = null;
        return super.build(fields);
    }

    @Override
    protected int buildField(FieldDef def, int y, List<FieldDef> fields, int index) {
        if (def.getType() == FieldType.EFFECTS_LIST) {
            return this.renderEffectsList(def, y);
        }
        if (def.getType() == FieldType.CUSTOM_EFFECTS_LIST) {
            return this.renderCustomEffectsList(def, y);
        }
        if (def.getType() == FieldType.EFFECT_ACTIONS_LIST) {
            return this.renderEffectActionsList(def, y);
        }
        return -1;
    }

    private int renderEffectsList(FieldDef def, int y) {
        this.sw.addLabel(new GuiNpcLabel(this.labelId++, def.getLabel(), this.colLLabel, (y += 3) + 2, 0xFFFF55));
        y += 15;
        ArrayList effects = (ArrayList)def.getValue();
        if (effects == null) {
            effects = new ArrayList();
        }
        String[] typeNames = EnumPotionType.getLangKeysNoNone();
        String[] ampValues = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (int e = 0; e < effects.size() && e < 5; ++e) {
            AbilityPotionEffect effect = (AbilityPotionEffect)effects.get(e);
            int typeIdx = effect.getType().ordinal() - 1;
            GuiNpcButton typeBtn = new GuiNpcButton(this.widgetId, this.colLLabel, y, 100, 20, typeNames, Math.max(0, typeIdx));
            this.sw.addButton(typeBtn);
            this.buttonFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{e, 0});
            ++this.widgetId;
            GuiNpcTextField durField = new GuiNpcTextField(this.widgetId, this.parent, this.fontRenderer, this.colLLabel + 104, y, 50, 20, String.valueOf(effect.getDurationTicks()));
            durField.setIntegersOnly();
            durField.setMinMaxDefault(1, 12000, 60);
            this.sw.addTextField(durField);
            this.textFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{e, 1});
            ++this.widgetId;
            if (effect.getType() != EnumPotionType.Fire) {
                if (effect.getType() == EnumPotionType.Manual) {
                    GuiNpcTextField ampField = new GuiNpcTextField(this.widgetId, this.parent, this.fontRenderer, this.colLLabel + 158, y, 40, 20, String.valueOf(effect.getAmplifier()));
                    ampField.setIntegersOnly();
                    ampField.setMinMaxDefault(0, 255, 0);
                    this.sw.addTextField(ampField);
                    this.textFieldMap.put(this.widgetId, def);
                    this.effectWidgetMeta.put(this.widgetId, new int[]{e, 2});
                    ++this.widgetId;
                } else {
                    GuiNpcButton ampBtn = new GuiNpcButton(this.widgetId, this.colLLabel + 158, y, 40, 20, ampValues, effect.getAmplifier());
                    this.sw.addButton(ampBtn);
                    this.buttonFieldMap.put(this.widgetId, def);
                    this.effectWidgetMeta.put(this.widgetId, new int[]{e, 2});
                    ++this.widgetId;
                }
            } else {
                ++this.widgetId;
            }
            GuiNpcButton delBtn = new GuiNpcButton(this.clearId, this.colLLabel + 202, y, 20, 20, "X");
            this.sw.addButton(delBtn);
            this.clearFieldMap.put(this.clearId, def);
            this.effectWidgetMeta.put(this.clearId, new int[]{e, 3});
            ++this.clearId;
            y += this.rowHeight;
            if (effect.getType() != EnumPotionType.Manual) continue;
            this.sw.addLabel(new GuiNpcLabel(this.labelId++, "effect.potionid", this.colLLabel, y + 5, 0xFFFFFF));
            GuiNpcTextField idField = new GuiNpcTextField(this.widgetId, this.parent, this.fontRenderer, this.colLLabel + 80, y, 50, 20, String.valueOf(effect.getManualPotionId()));
            idField.setIntegersOnly();
            idField.setMinMaxDefault(0, Integer.MAX_VALUE, 0);
            this.sw.addTextField(idField);
            this.textFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{e, 5});
            ++this.widgetId;
            y += this.rowHeight;
        }
        if (effects.size() < 5) {
            GuiNpcButton addBtn = new GuiNpcButton(this.widgetId, this.colLLabel, y, 50, 20, "gui.add");
            this.sw.addButton(addBtn);
            this.buttonFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{effects.size(), 4});
            ++this.widgetId;
            ++this.clearId;
            y += this.rowHeight;
        }
        return y;
    }

    private void ensureCustomEffectCache() {
        if (this.sortedCustomEffectIds != null) {
            return;
        }
        HashMap<Integer, CustomEffect> allEffects = CustomEffectController.getInstance().getCustomEffects();
        TreeMap<String, Integer> nameToId = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
        for (CustomEffect ce : allEffects.values()) {
            if (ce.getName() == null || ce.getName().isEmpty()) continue;
            nameToId.put(ce.getName(), ce.id);
        }
        this.sortedCustomEffectIds = new int[nameToId.size()];
        this.sortedCustomEffectNames = new String[nameToId.size()];
        int i = 0;
        for (Map.Entry entry : nameToId.entrySet()) {
            this.sortedCustomEffectIds[i] = (Integer)entry.getValue();
            this.sortedCustomEffectNames[i] = (String)entry.getKey();
            ++i;
        }
    }

    private String getCustomEffectName(int effectId, int index) {
        CustomEffect ce;
        HashMap<Integer, CustomEffect> allEffects = CustomEffectController.getInstance().getEffectMap(index);
        if (allEffects != null && (ce = allEffects.get(effectId)) != null && ce.getName() != null && !ce.getName().isEmpty()) {
            return ce.getName();
        }
        if (index != 0) {
            return this.getCustomEffectName(effectId, 0);
        }
        return "?";
    }

    private boolean hasAnyEffects() {
        for (HashMap<Integer, CustomEffect> map : CustomEffectController.getInstance().indexMapper.values()) {
            if (map == null || map.isEmpty()) continue;
            return true;
        }
        return false;
    }

    private int renderCustomEffectsList(FieldDef def, int y) {
        this.ensureCustomEffectCache();
        this.sw.addLabel(new GuiNpcLabel(this.labelId++, def.getLabel(), this.colLLabel, (y += 3) + 2, 0xFFFF55));
        y += 15;
        if (!this.hasAnyEffects()) {
            this.sw.addLabel(new GuiNpcLabel(this.labelId++, "ability.noCustomEffects", this.colLLabel, y + 5, 0x888888));
            return y += this.rowHeight;
        }
        ArrayList effects = (ArrayList)def.getValue();
        if (effects == null) {
            effects = new ArrayList();
        }
        String[] levelValues = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int e = 0;
        while (e < effects.size() && e < 5) {
            AbilityCustomEffect effect = (AbilityCustomEffect)effects.get(e);
            String effectName = this.getCustomEffectName(effect.getEffectId(), effect.getIndex());
            GuiNpcButton nameBtn = new GuiNpcButton(this.widgetId, this.colLLabel, y, 100, 20, effectName);
            this.sw.addButton(nameBtn);
            this.buttonFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{e, 0});
            ++this.widgetId;
            GuiNpcTextField durField = new GuiNpcTextField(this.widgetId, this.parent, this.fontRenderer, this.colLLabel + 104, y, 50, 20, String.valueOf(effect.getDurationTicks()));
            durField.setIntegersOnly();
            durField.setMinMaxDefault(1, 12000, 60);
            this.sw.addTextField(durField);
            this.textFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{e, 1});
            ++this.widgetId;
            GuiNpcButton lvlBtn = new GuiNpcButton(this.widgetId, this.colLLabel + 158, y, 40, 20, levelValues, (int)effect.getLevel());
            this.sw.addButton(lvlBtn);
            this.buttonFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{e, 2});
            ++this.widgetId;
            GuiNpcButton delBtn = new GuiNpcButton(this.clearId, this.colLLabel + 202, y, 20, 20, "X");
            this.sw.addButton(delBtn);
            this.clearFieldMap.put(this.clearId, def);
            this.effectWidgetMeta.put(this.clearId, new int[]{e++, 3});
            ++this.clearId;
            y += this.rowHeight;
        }
        if (effects.size() < 5) {
            GuiNpcButton addBtn = new GuiNpcButton(this.widgetId, this.colLLabel, y, 50, 20, "gui.add");
            this.sw.addButton(addBtn);
            this.buttonFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{effects.size(), 4});
            ++this.widgetId;
            ++this.clearId;
            y += this.rowHeight;
        }
        return y;
    }

    private void ensureEffectActionCache() {
        if (this.effectActionIds != null) {
            return;
        }
        String[] ids = AbilityController.Instance.getEffectActionIds();
        this.effectActionIds = ids;
        this.effectActionDisplayNames = new String[ids.length];
        for (int i = 0; i < ids.length; ++i) {
            IEffectAction action = AbilityController.Instance.getEffectAction(ids[i]);
            this.effectActionDisplayNames[i] = action != null ? action.getDisplayName() : ids[i];
        }
    }

    private int findEffectActionIndex(String actionId) {
        this.ensureEffectActionCache();
        for (int i = 0; i < this.effectActionIds.length; ++i) {
            if (!this.effectActionIds[i].equals(actionId)) continue;
            return i;
        }
        return -1;
    }

    private int renderEffectActionsList(FieldDef def, int y) {
        this.ensureEffectActionCache();
        if (this.effectActionIds.length == 0) {
            return y;
        }
        this.sw.addLabel(new GuiNpcLabel(this.labelId++, def.getLabel(), this.colLLabel, (y += 3) + 2, 0xFFFF55));
        y += 15;
        ArrayList entries = (ArrayList)def.getValue();
        if (entries == null) {
            entries = new ArrayList();
        }
        int e = 0;
        while (e < entries.size() && e < 5) {
            AbilityEffectActionEntry entry = (AbilityEffectActionEntry)entries.get(e);
            int selectedIdx = Math.max(0, this.findEffectActionIndex(entry.getActionId()));
            GuiNpcButton actionBtn = new GuiNpcButton(this.widgetId, this.colLLabel, y, 180, 20, this.effectActionDisplayNames, selectedIdx);
            this.sw.addButton(actionBtn);
            this.buttonFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{e, 0});
            ++this.widgetId;
            GuiNpcButton delBtn = new GuiNpcButton(this.clearId, this.colLLabel + 202, y, 20, 20, "X");
            this.sw.addButton(delBtn);
            this.clearFieldMap.put(this.clearId, def);
            this.effectWidgetMeta.put(this.clearId, new int[]{e++, 3});
            ++this.clearId;
            y += this.rowHeight;
        }
        if (entries.size() < 5) {
            GuiNpcButton addBtn = new GuiNpcButton(this.widgetId, this.colLLabel, y, 50, 20, "gui.add");
            this.sw.addButton(addBtn);
            this.buttonFieldMap.put(this.widgetId, def);
            this.effectWidgetMeta.put(this.widgetId, new int[]{entries.size(), 4});
            ++this.widgetId;
            ++this.clearId;
            y += this.rowHeight;
        }
        return y;
    }

    @Override
    public boolean handleButtonEvent(int buttonId, GuiButton button) {
        int[] meta = this.effectWidgetMeta.get(buttonId);
        if (meta != null) {
            List effects;
            FieldDef def = (FieldDef)this.buttonFieldMap.get(buttonId);
            if (def == null) {
                def = (FieldDef)this.clearFieldMap.get(buttonId);
            }
            if (def == null) {
                return false;
            }
            int idx = meta[0];
            int action = meta[1];
            if (def.getType() == FieldType.EFFECTS_LIST) {
                effects = (List)def.getValue();
                if (effects == null) {
                    return false;
                }
                switch (action) {
                    case 0: {
                        if (idx < effects.size()) {
                            EnumPotionType oldType = ((AbilityPotionEffect)effects.get(idx)).getType();
                            EnumPotionType newType = EnumPotionType.fromIndexNoNone(((GuiNpcButton)button).getValue());
                            if (oldType == EnumPotionType.Manual && newType != EnumPotionType.Manual) {
                                ((AbilityPotionEffect)effects.get(idx)).setAmplifier(0);
                            }
                            ((AbilityPotionEffect)effects.get(idx)).setType(newType);
                        }
                        return true;
                    }
                    case 2: {
                        if (idx < effects.size()) {
                            ((AbilityPotionEffect)effects.get(idx)).setAmplifier(((GuiNpcButton)button).getValue());
                        }
                        return true;
                    }
                    case 3: {
                        if (idx < effects.size()) {
                            effects.remove(idx);
                        }
                        return true;
                    }
                    case 4: {
                        if (effects.size() < 5) {
                            effects.add(new AbilityPotionEffect(EnumPotionType.Slowness, 60, 0));
                        }
                        return true;
                    }
                }
            }
            if (def.getType() == FieldType.CUSTOM_EFFECTS_LIST) {
                effects = (List)def.getValue();
                if (effects == null) {
                    return false;
                }
                this.ensureCustomEffectCache();
                switch (action) {
                    case 0: {
                        if (idx < effects.size()) {
                            int currentId = ((AbilityCustomEffect)effects.get(idx)).getEffectId();
                            int currentIndex = ((AbilityCustomEffect)effects.get(idx)).getIndex();
                            this.parent.setSubGuiWithResult(new SubGuiCustomEffectSelect(currentId, currentIndex), sub -> {
                                SubGuiCustomEffectSelect select = (SubGuiCustomEffectSelect)sub;
                                int selectedId = select.getSelectedEffectId();
                                if (selectedId >= 0 && idx < effects.size()) {
                                    ((AbilityCustomEffect)effects.get(idx)).setEffectId(selectedId);
                                    ((AbilityCustomEffect)effects.get(idx)).setIndex(select.getSelectedIndex());
                                }
                            });
                        }
                        return true;
                    }
                    case 2: {
                        if (idx < effects.size()) {
                            ((AbilityCustomEffect)effects.get(idx)).setLevel((byte)((GuiNpcButton)button).getValue());
                        }
                        return true;
                    }
                    case 3: {
                        if (idx < effects.size()) {
                            effects.remove(idx);
                        }
                        return true;
                    }
                    case 4: {
                        if (effects.size() < 5) {
                            this.parent.setSubGuiWithResult(new SubGuiCustomEffectSelect(-1), sub -> {
                                SubGuiCustomEffectSelect select = (SubGuiCustomEffectSelect)sub;
                                int selectedId = select.getSelectedEffectId();
                                if (selectedId >= 0 && effects.size() < 5) {
                                    effects.add(new AbilityCustomEffect(selectedId, 60, 0, select.getSelectedIndex()));
                                }
                            });
                        }
                        return true;
                    }
                }
            }
            if (def.getType() == FieldType.EFFECT_ACTIONS_LIST) {
                List entries = (List)def.getValue();
                if (entries == null) {
                    return false;
                }
                this.ensureEffectActionCache();
                switch (action) {
                    case 0: {
                        int btnVal;
                        if (idx < entries.size() && (btnVal = ((GuiNpcButton)button).getValue()) >= 0 && btnVal < this.effectActionIds.length) {
                            ((AbilityEffectActionEntry)entries.get(idx)).setActionId(this.effectActionIds[btnVal]);
                            IEffectAction ea = AbilityController.Instance.getEffectAction(this.effectActionIds[btnVal]);
                            if (ea != null) {
                                ((AbilityEffectActionEntry)entries.get(idx)).setConfig(ea.createDefaultConfig());
                            }
                        }
                        return true;
                    }
                    case 3: {
                        if (idx < entries.size()) {
                            entries.remove(idx);
                        }
                        return true;
                    }
                    case 4: {
                        if (entries.size() < 5 && this.effectActionIds.length > 0) {
                            entries.add(new AbilityEffectActionEntry(this.effectActionIds[0]));
                        }
                        return true;
                    }
                }
            }
        }
        return super.handleButtonEvent(buttonId, button);
    }

    @Override
    public boolean handleTextFieldEvent(int textFieldId, GuiNpcTextField field) {
        int[] meta = this.effectWidgetMeta.get(textFieldId);
        if (meta != null) {
            FieldDef def = (FieldDef)this.textFieldMap.get(textFieldId);
            if (def == null) {
                return false;
            }
            int idx = meta[0];
            int action = meta[1];
            if (def.getType() == FieldType.EFFECTS_LIST) {
                List effects = (List)def.getValue();
                if (effects != null && idx < effects.size()) {
                    if (action == 1) {
                        ((AbilityPotionEffect)effects.get(idx)).setDurationTicks(field.getInteger());
                    } else if (action == 2) {
                        ((AbilityPotionEffect)effects.get(idx)).setAmplifier(field.getInteger());
                    } else if (action == 5) {
                        ((AbilityPotionEffect)effects.get(idx)).setManualPotionId(field.getInteger());
                    }
                }
                return true;
            }
            if (def.getType() == FieldType.CUSTOM_EFFECTS_LIST) {
                List effects = (List)def.getValue();
                if (effects != null && idx < effects.size() && action == 1) {
                    ((AbilityCustomEffect)effects.get(idx)).setDurationTicks(field.getInteger());
                }
                return true;
            }
        }
        return super.handleTextFieldEvent(textFieldId, field);
    }

    public Map<Integer, int[]> getEffectWidgetMeta() {
        return this.effectWidgetMeta;
    }
}

