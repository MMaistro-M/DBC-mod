/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.builder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.client.gui.builder.FieldType;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcButtonYesNo;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiScrollWindow;
import noppes.npcs.client.gui.util.SubGuiInterface;

@SideOnly(value=Side.CLIENT)
public class GuiFieldBuilder {
    protected int contentRight = 330;
    protected int colLLabel = 5;
    protected int colLField = 75;
    protected int colLWidth = 70;
    protected int colRLabel = 178;
    protected int colRField = 248;
    protected int colRWidth = 70;
    protected int rowHeight = 24;
    protected int labelPadding = 8;
    protected final GuiNPCInterface parent;
    protected GuiScrollWindow sw;
    protected final FontRenderer fontRenderer;
    protected int scrollWindowId = 0;
    protected int widgetId;
    protected int clearId;
    protected int labelId;
    protected int startY = 5;
    protected int lastBuildY = 0;
    protected final Map<Integer, FieldDef> buttonFieldMap = new HashMap<Integer, FieldDef>();
    protected final Map<Integer, FieldDef> textFieldMap = new HashMap<Integer, FieldDef>();
    protected final Map<Integer, FieldDef> clearFieldMap = new HashMap<Integer, FieldDef>();
    private int lastHandledIndex = -1;

    public GuiFieldBuilder(GuiNPCInterface parent, FontRenderer fontRenderer) {
        this.parent = parent;
        this.fontRenderer = fontRenderer;
    }

    public GuiFieldBuilder scrollWindowId(int id) {
        this.scrollWindowId = id;
        return this;
    }

    public GuiFieldBuilder contentRight(int v) {
        this.contentRight = v;
        return this;
    }

    public GuiFieldBuilder startIds(int widget, int clear, int label) {
        this.widgetId = widget;
        this.clearId = clear;
        this.labelId = label;
        return this;
    }

    public GuiFieldBuilder startY(int y) {
        this.startY = y;
        return this;
    }

    public GuiFieldBuilder rowHeight(int h) {
        this.rowHeight = h;
        return this;
    }

    public GuiScrollWindow buildScrollWindow(List<FieldDef> fields, int x, int y, int width, int height) {
        this.sw = new GuiScrollWindow(this.parent, x, y, width, height, 0);
        this.sw.backgroundColor = -2013265920;
        this.parent.addScrollableGui(this.scrollWindowId, this.sw);
        int finalY = this.build(fields);
        this.sw.maxScrollY = Math.max(finalY - height, 0);
        return this.sw;
    }

    protected int build(List<FieldDef> fields) {
        this.buttonFieldMap.clear();
        this.textFieldMap.clear();
        this.clearFieldMap.clear();
        int y = this.startY;
        for (int i = 0; i < fields.size(); ++i) {
            FieldDef def = fields.get(i);
            int customResult = this.buildField(def, y, fields, i);
            if (customResult >= 0) {
                int newIndex = this.getLastHandledIndex();
                if (newIndex > i) {
                    i = newIndex;
                }
                y = customResult;
                continue;
            }
            if (!def.isVisible()) continue;
            if (def.getType() == FieldType.SECTION_HEADER) {
                GuiNpcLabel sectionLabel = new GuiNpcLabel(this.labelId++, def.getLabel(), this.colLLabel, (y += 3) + 2, 0xFFFF55);
                if (def.getHoverText() != null && !def.getHoverText().isEmpty()) {
                    sectionLabel.setHoverText(def.getHoverText());
                }
                this.sw.addLabel(sectionLabel);
                y += 15;
                continue;
            }
            if (def.getType() == FieldType.ROW) {
                boolean rightVis;
                FieldDef left = def.getLeftChild();
                FieldDef right = def.getRightChild();
                boolean leftVis = left != null && left.isVisible();
                boolean bl = rightVis = right != null && right.isVisible();
                if (leftVis && rightVis) {
                    this.renderFieldAt(left, this.colLLabel, this.colLField, this.colLWidth, y);
                    this.renderFieldAt(right, this.colRLabel, this.colRField, this.colRWidth, y);
                } else if (leftVis) {
                    this.renderFullWidth(left, y);
                } else if (rightVis) {
                    this.renderFullWidth(right, y);
                }
                if (!leftVis && !rightVis) continue;
                y += this.rowHeight;
                continue;
            }
            this.renderFullWidth(def, y);
            y += this.rowHeight;
        }
        this.lastBuildY = y;
        return y;
    }

    protected int buildField(FieldDef def, int y, List<FieldDef> fields, int index) {
        return -1;
    }

    protected void setLastHandledIndex(int index) {
        this.lastHandledIndex = index;
    }

    protected int getLastHandledIndex() {
        return this.lastHandledIndex;
    }

    protected void renderFieldAt(FieldDef def, int labelX, int fieldX, int fieldW, int y) {
        GuiNpcLabel fieldLabel = new GuiNpcLabel(this.labelId++, def.getLabel(), labelX, y + 5, 0xFFFFFF);
        String hover = def.getHoverText();
        if (hover != null && !hover.isEmpty()) {
            fieldLabel.setHoverText(hover);
        }
        this.sw.addLabel(fieldLabel);
        switch (def.getType()) {
            case FLOAT: {
                float fVal = def.getValue() instanceof Number ? ((Number)def.getValue()).floatValue() : 0.0f;
                GuiNpcTextField tf = new GuiNpcTextField(this.widgetId, this.parent, this.fontRenderer, fieldX, y, fieldW, 20, String.valueOf(fVal));
                tf.setFloatsOnly();
                tf.setMinMaxDefaultFloat(def.getMin(), def.getMax(), fVal);
                if (!def.isEnabled()) {
                    tf.func_146184_c(false);
                }
                if (hover != null) {
                    tf.setHoverText(hover);
                }
                this.sw.addTextField(tf);
                this.textFieldMap.put(this.widgetId, def);
                ++this.widgetId;
                ++this.clearId;
                break;
            }
            case INT: {
                int iVal = def.getValue() instanceof Number ? ((Number)def.getValue()).intValue() : 0;
                GuiNpcTextField tf = new GuiNpcTextField(this.widgetId, this.parent, this.fontRenderer, fieldX, y, fieldW, 20, String.valueOf(iVal));
                tf.setIntegersOnly();
                tf.setMinMaxDefault((int)def.getMin(), (int)def.getMax(), iVal);
                if (!def.isEnabled()) {
                    tf.func_146184_c(false);
                }
                if (hover != null) {
                    tf.setHoverText(hover);
                }
                this.sw.addTextField(tf);
                this.textFieldMap.put(this.widgetId, def);
                ++this.widgetId;
                ++this.clearId;
                break;
            }
            case STRING: {
                String sVal = def.getValue() != null ? def.getValue().toString() : "";
                GuiNpcTextField tf = new GuiNpcTextField(this.widgetId, this.parent, this.fontRenderer, fieldX, y, fieldW, 20, sVal);
                if ("gui.name".equals(def.getLabel())) {
                    tf.setFileNameSafe();
                }
                if (!def.isEnabled()) {
                    tf.func_146184_c(false);
                }
                if (hover != null) {
                    tf.setHoverText(hover);
                }
                this.sw.addTextField(tf);
                this.textFieldMap.put(this.widgetId, def);
                ++this.widgetId;
                ++this.clearId;
                break;
            }
            case LABEL: {
                String text = def.getValue() != null ? def.getValue().toString() : "";
                this.sw.addLabel(new GuiNpcLabel(this.labelId++, text, fieldX, y + 5, 0xAAAAAA));
                ++this.widgetId;
                ++this.clearId;
                break;
            }
            case BOOLEAN: {
                boolean bVal = def.getValue() instanceof Boolean ? (Boolean)def.getValue() : false;
                GuiNpcButtonYesNo btn = new GuiNpcButtonYesNo(this.widgetId, fieldX, y, fieldW, 20, bVal);
                if (!def.isEnabled()) {
                    btn.setEnabled(false);
                }
                if (hover != null) {
                    btn.setHoverText(hover);
                }
                this.sw.addButton(btn);
                this.buttonFieldMap.put(this.widgetId, def);
                ++this.widgetId;
                ++this.clearId;
                break;
            }
            case ENUM: {
                Class<Enum<?>> enumClass = def.getEnumClass();
                if (enumClass != null) {
                    Enum<?>[] constants = enumClass.getEnumConstants();
                    String[] names = new String[constants.length];
                    for (int i = 0; i < constants.length; ++i) {
                        names[i] = constants[i].toString();
                    }
                    int selected = def.getValue() instanceof Enum ? ((Enum)def.getValue()).ordinal() : 0;
                    GuiNpcButton btn = new GuiNpcButton(this.widgetId, fieldX, y, fieldW, 20, names, selected);
                    if (!def.isEnabled()) {
                        btn.setEnabled(false);
                    }
                    if (hover != null) {
                        btn.setHoverText(hover);
                    }
                    this.sw.addButton(btn);
                    this.buttonFieldMap.put(this.widgetId, def);
                }
                ++this.widgetId;
                ++this.clearId;
                break;
            }
            case STRING_ENUM: {
                String[] values = def.getStringEnumValues();
                if (values != null && values.length > 0) {
                    String curVal = def.getStringEnumValue();
                    int selected = 0;
                    for (int i = 0; i < values.length; ++i) {
                        if (!values[i].equals(curVal)) continue;
                        selected = i;
                        break;
                    }
                    GuiNpcButton btn = new GuiNpcButton(this.widgetId, fieldX, y, fieldW, 20, values, selected);
                    if (!def.isEnabled()) {
                        btn.setEnabled(false);
                    }
                    if (hover != null) {
                        btn.setHoverText(hover);
                    }
                    this.sw.addButton(btn);
                    this.buttonFieldMap.put(this.widgetId, def);
                }
                ++this.widgetId;
                ++this.clearId;
                break;
            }
            case SUB_GUI: {
                int btnW;
                String btnText = def.getButtonLabel();
                if (def.hasClearAction()) {
                    btnW = fieldW - 22;
                    GuiNpcButton clearBtn = new GuiNpcButton(this.clearId, fieldX + fieldW - 20, y, 20, 20, "X");
                    this.sw.addButton(clearBtn);
                    this.clearFieldMap.put(this.clearId, def);
                } else {
                    btnW = fieldW;
                }
                GuiNpcButton btn = new GuiNpcButton(this.widgetId, fieldX, y, btnW, 20, btnText);
                Integer textColor = def.getButtonTextColor();
                if (textColor != null) {
                    btn.setTextColor(textColor);
                }
                if (!def.isEnabled()) {
                    btn.setEnabled(false);
                }
                if (hover != null) {
                    btn.setHoverText(hover);
                }
                this.sw.addButton(btn);
                this.buttonFieldMap.put(this.widgetId, def);
                ++this.widgetId;
                ++this.clearId;
                break;
            }
            case STRING_BROWSE: {
                int btnW = 20;
                int tfW = fieldW - btnW - 2;
                String sVal = def.getValue() != null ? def.getValue().toString() : "";
                GuiNpcTextField tf = new GuiNpcTextField(this.widgetId, this.parent, this.fontRenderer, fieldX, y, tfW, 20, sVal);
                if (!def.isEnabled()) {
                    tf.func_146184_c(false);
                }
                if (hover != null) {
                    tf.setHoverText(hover);
                }
                this.sw.addTextField(tf);
                this.textFieldMap.put(this.widgetId, def);
                ++this.widgetId;
                GuiNpcButton browseBtn = new GuiNpcButton(this.clearId, fieldX + tfW + 2, y, btnW, 20, "...");
                if (!def.isEnabled()) {
                    browseBtn.setEnabled(false);
                }
                this.sw.addButton(browseBtn);
                this.buttonFieldMap.put(this.clearId, def);
                ++this.clearId;
                break;
            }
            default: {
                ++this.widgetId;
                ++this.clearId;
            }
        }
    }

    private void renderFullWidth(FieldDef def, int y) {
        int fieldW = this.getFullFieldWidth(def);
        String translated = StatCollector.func_74838_a((String)def.getLabel());
        int labelW = this.fontRenderer.func_78256_a(translated);
        int fieldX = this.colLLabel + labelW + this.labelPadding;
        int maxW = this.contentRight - fieldX - 15;
        if ((def.getType() == FieldType.SUB_GUI || def.getType() == FieldType.STRING_BROWSE) && maxW > 0) {
            fieldW = maxW;
        } else if (maxW > 0 && fieldW > maxW) {
            fieldW = maxW;
        }
        this.renderFieldAt(def, this.colLLabel, fieldX, fieldW, y);
    }

    protected int getFullFieldWidth(FieldDef def) {
        switch (def.getType()) {
            case FLOAT: 
            case INT: {
                return 60;
            }
            case STRING: {
                return 200;
            }
            case BOOLEAN: {
                return 50;
            }
            case LABEL: {
                return 60;
            }
            case ENUM: 
            case STRING_ENUM: {
                return 120;
            }
            case SUB_GUI: {
                return def.hasClearAction() ? 175 : 200;
            }
            case STRING_BROWSE: {
                return 200;
            }
        }
        return 80;
    }

    public boolean handleButtonEvent(int buttonId, GuiButton button) {
        FieldDef def;
        if (this.clearFieldMap.containsKey(buttonId) && (def = this.clearFieldMap.get(buttonId)) != null && def.hasClearAction()) {
            def.getClearAction().run();
            return true;
        }
        def = this.buttonFieldMap.get(buttonId);
        if (def == null) {
            return false;
        }
        switch (def.getType()) {
            case BOOLEAN: {
                def.setValue(((GuiNpcButton)button).getValue() == 1);
                return true;
            }
            case ENUM: {
                int idx = ((GuiNpcButton)button).getValue();
                Class<Enum<?>> ec = def.getEnumClass();
                if (ec != null) {
                    Enum<?>[] constants = ec.getEnumConstants();
                    if (idx >= 0 && idx < constants.length) {
                        def.setValue(constants[idx]);
                    }
                }
                return true;
            }
            case STRING_ENUM: {
                int idx = ((GuiNpcButton)button).getValue();
                String[] values = def.getStringEnumValues();
                if (values != null && idx >= 0 && idx < values.length) {
                    def.setStringEnumValue(values[idx]);
                }
                return true;
            }
            case SUB_GUI: {
                if (def.getSubGuiFactory() != null) {
                    this.parent.setSubGuiWithResult(def.getSubGuiFactory().get(), sub -> {
                        if (def.getSubGuiResultHandler() != null) {
                            def.getSubGuiResultHandler().accept((SubGuiInterface)((Object)sub));
                        }
                    });
                }
                return true;
            }
            case STRING_BROWSE: {
                if (def.getSubGuiFactory() != null) {
                    this.parent.setSubGuiWithResult(def.getSubGuiFactory().get(), sub -> {
                        if (def.getSubGuiResultHandler() != null) {
                            def.getSubGuiResultHandler().accept((SubGuiInterface)((Object)sub));
                        }
                    });
                }
                return true;
            }
        }
        return false;
    }

    public boolean handleTextFieldEvent(int textFieldId, GuiNpcTextField field) {
        FieldDef def = this.textFieldMap.get(textFieldId);
        if (def == null) {
            return false;
        }
        switch (def.getType()) {
            case FLOAT: {
                try {
                    float oldVal;
                    float newVal = Float.parseFloat(field.func_146179_b());
                    Object old = def.getValue();
                    float f = oldVal = old instanceof Number ? ((Number)old).floatValue() : 0.0f;
                    if (newVal != oldVal) {
                        def.setValue(Float.valueOf(newVal));
                        return true;
                    }
                }
                catch (NumberFormatException newVal) {
                    // empty catch block
                }
                return false;
            }
            case INT: {
                try {
                    int oldVal;
                    int newVal = Integer.parseInt(field.func_146179_b());
                    Object old = def.getValue();
                    int n = oldVal = old instanceof Number ? ((Number)old).intValue() : 0;
                    if (newVal != oldVal) {
                        def.setValue(newVal);
                        return true;
                    }
                }
                catch (NumberFormatException newVal) {
                    // empty catch block
                }
                return false;
            }
            case STRING: 
            case STRING_BROWSE: {
                String oldVal;
                String newVal = field.func_146179_b();
                Object old = def.getValue();
                String string = oldVal = old != null ? old.toString() : "";
                if (!newVal.equals(oldVal)) {
                    def.setValue(newVal);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public boolean handleSubGuiClosed(SubGuiInterface subgui) {
        return false;
    }

    public Map<Integer, FieldDef> getButtonFieldMap() {
        return this.buttonFieldMap;
    }

    public Map<Integer, FieldDef> getTextFieldMap() {
        return this.textFieldMap;
    }

    public Map<Integer, FieldDef> getClearFieldMap() {
        return this.clearFieldMap;
    }

    public GuiScrollWindow getScrollWindow() {
        return this.sw;
    }

    public int getLastBuildY() {
        return this.lastBuildY;
    }

    public int getNextLabelId() {
        return this.labelId;
    }
}

