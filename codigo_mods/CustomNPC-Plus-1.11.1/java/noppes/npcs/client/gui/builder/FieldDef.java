/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package noppes.npcs.client.gui.builder;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;
import noppes.npcs.client.gui.SubGuiColorSelector;
import noppes.npcs.client.gui.builder.FieldType;
import noppes.npcs.client.gui.select.GuiAnimationSelection;
import noppes.npcs.client.gui.select.GuiSoundSelection;
import noppes.npcs.client.gui.select.GuiTexturePathSelection;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.data.Animation;

@SideOnly(value=Side.CLIENT)
public class FieldDef {
    private final String label;
    private final FieldType type;
    private String tab = null;
    private Supplier<Object> getter;
    private Consumer<Object> setter;
    private BooleanSupplier visibleWhen = () -> true;
    private BooleanSupplier enabledWhen = () -> true;
    private String hoverText = null;
    private float min = 0.0f;
    private float max = Float.POSITIVE_INFINITY;
    private Class<? extends Enum<?>> enumClass;
    private String[] stringEnumValues;
    private Supplier<String> stringEnumGetter;
    private Consumer<String> stringEnumSetter;
    private Supplier<SubGuiInterface> subGuiFactory;
    private Consumer<SubGuiInterface> subGuiResultHandler;
    private Supplier<String> buttonLabelSupplier;
    private Supplier<Integer> buttonTextColorSupplier;
    private Runnable clearAction;
    private FieldDef leftChild;
    private FieldDef rightChild;

    private FieldDef(String label, FieldType type) {
        this.label = label;
        this.type = type;
    }

    public static FieldDef section(String label) {
        return new FieldDef(label, FieldType.SECTION_HEADER);
    }

    public static FieldDef floatField(String label, Supplier<Float> getter, Consumer<Float> setter) {
        FieldDef def = new FieldDef(label, FieldType.FLOAT);
        def.getter = () -> (Float)getter.get();
        def.setter = v -> setter.accept(Float.valueOf(((Number)v).floatValue()));
        return def;
    }

    public static FieldDef intField(String label, Supplier<Integer> getter, Consumer<Integer> setter) {
        FieldDef def = new FieldDef(label, FieldType.INT);
        def.getter = () -> (Integer)getter.get();
        def.setter = v -> setter.accept(((Number)v).intValue());
        return def;
    }

    public static FieldDef boolField(String label, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        FieldDef def = new FieldDef(label, FieldType.BOOLEAN);
        def.getter = () -> (Boolean)getter.get();
        def.setter = v -> setter.accept((Boolean)v);
        return def;
    }

    public static <E extends Enum<E>> FieldDef enumField(String label, Class<E> enumClass, Supplier<E> getter, Consumer<E> setter) {
        FieldDef def = new FieldDef(label, FieldType.ENUM);
        def.enumClass = enumClass;
        def.getter = () -> (Enum)getter.get();
        def.setter = v -> setter.accept((Enum)v);
        return def;
    }

    public static FieldDef stringEnumField(String label, String[] values, Supplier<String> getter, Consumer<String> setter) {
        FieldDef def = new FieldDef(label, FieldType.STRING_ENUM);
        def.stringEnumValues = values;
        def.stringEnumGetter = getter;
        def.stringEnumSetter = setter;
        return def;
    }

    public static FieldDef stringField(String label, Supplier<String> getter, Consumer<String> setter) {
        FieldDef def = new FieldDef(label, FieldType.STRING);
        def.getter = () -> (String)getter.get();
        def.setter = v -> setter.accept((String)v);
        return def;
    }

    public static FieldDef labelField(String label, Supplier<String> textSupplier) {
        FieldDef def = new FieldDef(label, FieldType.LABEL);
        def.getter = () -> (String)textSupplier.get();
        return def;
    }

    public static <T> FieldDef subGuiField(String label, Supplier<T> factory, Consumer<T> resultHandler) {
        FieldDef def = new FieldDef(label, FieldType.SUB_GUI);
        def.subGuiFactory = factory;
        def.subGuiResultHandler = resultHandler;
        return def;
    }

    public static FieldDef custom(String label, FieldType type, Supplier<Object> getter, Consumer<Object> setter) {
        FieldDef def = new FieldDef(label, type);
        def.getter = getter;
        def.setter = setter;
        return def;
    }

    public static FieldDef row(FieldDef left, FieldDef right) {
        FieldDef def = new FieldDef("", FieldType.ROW);
        def.leftChild = left;
        def.rightChild = right;
        return def;
    }

    public static FieldDef colorSubGui(String label, Supplier<Integer> getter, Consumer<Integer> setter) {
        return FieldDef.subGuiField(label, () -> new SubGuiColorSelector((int)((Integer)getter.get() & 0xFFFFFF)), gui -> setter.accept(gui.color & 0xFFFFFF | (Integer)getter.get() & 0xFF000000)).buttonLabel(() -> String.format("%06X", (Integer)getter.get() & 0xFFFFFF)).buttonTextColor(() -> (Integer)getter.get() & 0xFFFFFF);
    }

    public static FieldDef soundSubGui(String label, Supplier<String> getter, Consumer<String> setter) {
        return FieldDef.subGuiField(label, () -> new GuiSoundSelection((String)getter.get()), gui -> {
            GuiSoundSelection s = gui;
            if (s.selectedResource != null) {
                setter.accept(s.selectedResource.toString());
            }
        }).buttonLabel(() -> {
            String s = (String)getter.get();
            return s == null || s.isEmpty() ? "gui.none" : s;
        }).clearable(() -> setter.accept(""));
    }

    public static FieldDef textureSubGui(String label, Supplier<String> getter, Consumer<String> setter) {
        FieldDef def = new FieldDef(label, FieldType.STRING_BROWSE);
        def.getter = () -> (String)getter.get();
        def.setter = v -> setter.accept(v != null ? v.toString() : "");
        def.subGuiFactory = () -> new GuiTexturePathSelection((String)getter.get());
        def.subGuiResultHandler = gui -> {
            GuiTexturePathSelection t = (GuiTexturePathSelection)gui;
            if (t.selectedResource != null) {
                setter.accept(t.selectedResource.toString());
            }
        };
        return def;
    }

    public static FieldDef animSubGui(String label, Supplier<Integer> idGetter, Consumer<Integer> idSetter, Supplier<String> nameGetter, Consumer<String> nameSetter) {
        return FieldDef.subGuiField(label, () -> new GuiAnimationSelection((Integer)idGetter.get(), (String)nameGetter.get()), gui -> {
            GuiAnimationSelection sel = gui;
            if (sel.isBuiltInSelected()) {
                nameSetter.accept(sel.selectedBuiltInName);
                idSetter.accept(-1);
            } else {
                idSetter.accept(sel.selectedAnimationId);
                String customName = sel.getSelectedName();
                nameSetter.accept(customName != null ? customName : "");
            }
        }).buttonLabel(() -> {
            String name = (String)nameGetter.get();
            int id = (Integer)idGetter.get();
            if (id < 0 && name != null && !name.isEmpty()) {
                return name;
            }
            if (id >= 0) {
                if (name != null && !name.isEmpty()) {
                    return "(" + id + ") " + name;
                }
                Animation anim = AnimationController.Instance != null ? (Animation)AnimationController.Instance.get(id) : null;
                String animName = anim != null ? anim.getName() : "";
                return animName != null && !animName.isEmpty() ? "(" + id + ") " + animName : String.valueOf(id);
            }
            return "gui.none";
        }).clearable(() -> {
            idSetter.accept(-1);
            nameSetter.accept("");
        });
    }

    public FieldDef tab(String tabName) {
        this.tab = tabName;
        return this;
    }

    public FieldDef range(float min, float max) {
        this.min = min;
        this.max = max;
        return this;
    }

    public FieldDef min(float min) {
        this.min = min;
        return this;
    }

    public FieldDef max(float max) {
        this.max = max;
        return this;
    }

    public FieldDef visibleWhen(BooleanSupplier condition) {
        this.visibleWhen = condition;
        return this;
    }

    public FieldDef enabledWhen(BooleanSupplier condition) {
        this.enabledWhen = condition;
        return this;
    }

    public FieldDef hover(String hoverText) {
        this.hoverText = hoverText;
        return this;
    }

    public FieldDef buttonLabel(Supplier<String> supplier) {
        this.buttonLabelSupplier = supplier;
        return this;
    }

    public FieldDef buttonTextColor(Supplier<Integer> supplier) {
        this.buttonTextColorSupplier = supplier;
        return this;
    }

    public FieldDef clearable(Runnable action) {
        this.clearAction = action;
        return this;
    }

    public String getLabel() {
        return this.label;
    }

    public FieldType getType() {
        return this.type;
    }

    public String getTab() {
        return this.tab;
    }

    public boolean isVisible() {
        return this.visibleWhen.getAsBoolean();
    }

    public boolean isEnabled() {
        return this.enabledWhen.getAsBoolean();
    }

    public String getHoverText() {
        return this.hoverText;
    }

    public float getMin() {
        return this.min;
    }

    public float getMax() {
        return this.max;
    }

    public boolean hasRange() {
        return this.min != 0.0f || this.max != Float.POSITIVE_INFINITY;
    }

    public Class<? extends Enum<?>> getEnumClass() {
        return this.enumClass;
    }

    public String[] getStringEnumValues() {
        return this.stringEnumValues;
    }

    public Supplier<SubGuiInterface> getSubGuiFactory() {
        return this.subGuiFactory;
    }

    public Consumer<SubGuiInterface> getSubGuiResultHandler() {
        return this.subGuiResultHandler;
    }

    public boolean hasClearAction() {
        return this.clearAction != null;
    }

    public Runnable getClearAction() {
        return this.clearAction;
    }

    public Object getValue() {
        return this.getter != null ? this.getter.get() : null;
    }

    public void setValue(Object value) {
        if (this.setter != null) {
            this.setter.accept(value);
        }
    }

    public String getStringEnumValue() {
        return this.stringEnumGetter != null ? this.stringEnumGetter.get() : null;
    }

    public void setStringEnumValue(String value) {
        if (this.stringEnumSetter != null) {
            this.stringEnumSetter.accept(value);
        }
    }

    public String getButtonLabel() {
        if (this.buttonLabelSupplier != null) {
            return this.buttonLabelSupplier.get();
        }
        return this.label;
    }

    public Integer getButtonTextColor() {
        return this.buttonTextColorSupplier != null ? this.buttonTextColorSupplier.get() : null;
    }

    public FieldDef getLeftChild() {
        return this.leftChild;
    }

    public FieldDef getRightChild() {
        return this.rightChild;
    }

    public static boolean insertBefore(List<FieldDef> fields, String targetLabel, FieldDef newField) {
        for (int i = 0; i < fields.size(); ++i) {
            if (!FieldDef.matchesLabel(fields.get(i), targetLabel)) continue;
            fields.add(i, newField);
            return true;
        }
        return false;
    }

    public static boolean insertAfter(List<FieldDef> fields, String targetLabel, FieldDef newField) {
        for (int i = 0; i < fields.size(); ++i) {
            if (!FieldDef.matchesLabel(fields.get(i), targetLabel)) continue;
            fields.add(i + 1, newField);
            return true;
        }
        return false;
    }

    public static boolean modifyVisibility(List<FieldDef> fields, String targetLabel, BooleanSupplier condition) {
        for (FieldDef field : fields) {
            if (!FieldDef.matchesLabel(field, targetLabel)) continue;
            field.visibleWhen(condition);
            return true;
        }
        return false;
    }

    private static boolean matchesLabel(FieldDef def, String targetLabel) {
        if (targetLabel.equals(def.label)) {
            return true;
        }
        if (def.type == FieldType.ROW) {
            if (def.leftChild != null && targetLabel.equals(def.leftChild.label)) {
                return true;
            }
            if (def.rightChild != null && targetLabel.equals(def.rightChild.label)) {
                return true;
            }
        }
        return false;
    }
}

