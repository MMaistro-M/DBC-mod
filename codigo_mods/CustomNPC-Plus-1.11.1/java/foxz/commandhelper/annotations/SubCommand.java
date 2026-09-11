/*
 * Decompiled with CFR 0.152.
 */
package foxz.commandhelper.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface SubCommand {
    public String name() default "";

    public String usage() default "";

    public boolean hasEmptyCall() default false;

    public String desc();

    public Class[] permissions() default {};
}

