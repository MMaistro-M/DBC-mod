/*
 * Decompiled with CFR 0.152.
 */
package somehussar.gui.annotationHandling;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import somehussar.gui.annotationHandling.FieldConstraint;
import somehussar.gui.annotationHandling.FieldConstraints;
import somehussar.gui.annotationHandling.GuiEditable;
import somehussar.gui.annotationHandling.constraints.ConstraintFactory;
import somehussar.gui.annotationHandling.constraints.NumericConstraint;
import somehussar.gui.annotationHandling.constraints.NumericConstraintFactory;
import somehussar.gui.annotationHandling.field.EditableField;

public final class GuiFieldHandler {
    private static final Map<Class<?>, ClassMetadata> CACHE = new HashMap();
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    public static final Map<Class<? extends Annotation>, ConstraintFactory<?>> registry = new HashMap();

    private GuiFieldHandler() {
    }

    public static ClassMetadata getMetadata(Class<?> type) {
        return CACHE.computeIfAbsent(type, GuiFieldHandler::scanClass);
    }

    private static ClassMetadata scanClass(Class<?> type) {
        if (!type.isAnnotationPresent(GuiEditable.class)) {
            return null;
        }
        ClassMetadata parent = null;
        Class<?> superCls = type.getSuperclass();
        if (superCls != null && superCls != Object.class) {
            parent = GuiFieldHandler.getMetadata(superCls);
        }
        ArrayList<EditableField> list = new ArrayList<EditableField>();
        for (Field field : type.getDeclaredFields()) {
            EditableField editableField = GuiFieldHandler.createEditableField(field);
            if (editableField == null) continue;
            list.add(editableField);
        }
        return new ClassMetadata(type, list.toArray(list.toArray(new EditableField[0])), parent);
    }

    private static EditableField createEditableField(Field field) {
        try {
            GuiEditable.Field fieldConfig = field.getAnnotation(GuiEditable.Field.class);
            if (fieldConfig == null) {
                return null;
            }
            if (Modifier.isFinal(field.getModifiers())) {
                return null;
            }
            GuiEditable.Size size = field.getAnnotation(GuiEditable.Size.class);
            if (size == null) {
                size = GuiEditable.Size.DEFAULT_SIZE;
            }
            field.setAccessible(true);
            EditableField.Builder builder = new EditableField.Builder().name(fieldConfig.value()).type(field.getType()).getter(LOOKUP.unreflectGetter(field)).setter(LOOKUP.unreflectSetter(field)).constraints(GuiFieldHandler.buildConstraints(field)).order(fieldConfig.order()).size(size);
            GuiEditable.Group group = field.getAnnotation(GuiEditable.Group.class);
            if (group != null) {
                builder.group(group.value());
            }
            return builder.build();
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to bind field: " + field, e);
        }
    }

    private static FieldConstraints buildConstraints(Field field) {
        ArrayList<FieldConstraint> list = new ArrayList<FieldConstraint>();
        for (Annotation ann : field.getAnnotations()) {
            ConstraintFactory<?> factory = registry.get(ann.annotationType());
            if (factory == null) continue;
            FieldConstraint c = factory.create(field, ann);
            list.add(c);
        }
        if (list.isEmpty()) {
            return FieldConstraints.empty();
        }
        return new FieldConstraints(list.toArray(new FieldConstraint[0]));
    }

    static {
        registry.put(NumericConstraint.class, new NumericConstraintFactory());
    }

    public static final class ClassMetadata {
        final Class<?> type;
        final EditableField[] fields;
        final ClassMetadata parent;

        ClassMetadata(Class<?> type, EditableField[] fields, ClassMetadata parent) {
            this.type = type;
            this.fields = fields;
            this.parent = parent;
        }

        public ClassMetadata getParent() {
            return this.parent;
        }

        public EditableField[] getDeclaredFields() {
            return this.fields;
        }

        public Class<?> getType() {
            return this.type;
        }
    }
}

