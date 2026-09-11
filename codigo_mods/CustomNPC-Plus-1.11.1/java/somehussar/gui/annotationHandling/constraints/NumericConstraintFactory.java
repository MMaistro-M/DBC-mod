/*
 * Decompiled with CFR 0.152.
 */
package somehussar.gui.annotationHandling.constraints;

import java.lang.reflect.Field;
import somehussar.gui.annotationHandling.FieldConstraint;
import somehussar.gui.annotationHandling.constraints.ConstraintFactory;
import somehussar.gui.annotationHandling.constraints.NumericConstraint;
import somehussar.gui.annotationHandling.constraints.NumericRangeConstraint;

public class NumericConstraintFactory
implements ConstraintFactory<NumericConstraint> {
    @Override
    public FieldConstraint create(Field field, NumericConstraint ann) {
        return new NumericRangeConstraint(ann.min(), ann.max());
    }
}

