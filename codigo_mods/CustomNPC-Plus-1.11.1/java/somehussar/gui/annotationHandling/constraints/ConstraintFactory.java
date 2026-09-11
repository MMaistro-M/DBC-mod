/*
 * Decompiled with CFR 0.152.
 */
package somehussar.gui.annotationHandling.constraints;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import somehussar.gui.annotationHandling.FieldConstraint;

public interface ConstraintFactory<A extends Annotation> {
    public FieldConstraint create(Field var1, A var2);
}

