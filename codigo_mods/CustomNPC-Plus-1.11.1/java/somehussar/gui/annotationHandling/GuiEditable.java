/*
 * Decompiled with CFR 0.152.
 */
package somehussar.gui.annotationHandling;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.RUNTIME)
public @interface GuiEditable {

    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface Size {
        public static final Size DEFAULT_SIZE = new Size(){

            @Override
            public int labelWidth() {
                return 60;
            }

            @Override
            public int fieldWidth() {
                return 50;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return Size.class;
            }
        };

        public int labelWidth() default 60;

        public int fieldWidth() default 50;
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface Group {
        public String value();
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    public static @interface Field {
        public String value();

        public int order() default 0;
    }
}

