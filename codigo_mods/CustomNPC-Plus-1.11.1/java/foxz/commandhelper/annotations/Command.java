/*
 * Decompiled with CFR 0.152.
 */
package foxz.commandhelper.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Command {
    public String name();

    public String desc();

    public String usage() default "";

    public Class[] sub() default {};
}

