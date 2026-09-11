/*
 * Decompiled with CFR 0.152.
 */
package io.github.legacymoddingmc.unimixins.gtnhmixins.repackage.common.config;

import io.github.legacymoddingmc.unimixins.gtnhmixins.repackage.common.config.PropertyCollection;
import io.github.legacymoddingmc.unimixins.gtnhmixins.repackage.common.config.PropertyToken;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class AnnotatedProperties {
    public static void load(File file, Class<?> cls) {
        PropertyCollection properties = PropertyCollection.fromFile(file);
        try {
            for (Field f : cls.getFields()) {
                if (!f.isAnnotationPresent(ConfigString.class)) continue;
                ConfigString ann = f.getAnnotation(ConfigString.class);
                ArrayList<String> comment = new ArrayList<String>(Arrays.asList(ann.com().split("\n")));
                String def = ann.def();
                String cat = ann.cat();
                comment.removeIf(s -> s.startsWith("[default:"));
                comment.add("[default: " + def + "]");
                String key = cat + "." + f.getName();
                PropertyToken prop = properties.get(key);
                if (prop == null) {
                    prop = new PropertyToken(comment, key, def);
                    prop.setDirty();
                } else {
                    prop.setComment(comment);
                }
                AnnotatedProperties.setFieldValue(f, prop.getValue());
                properties.put(key, prop);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        properties.writeToFile(file);
    }

    private static void setFieldValue(Field f, String value) throws Exception {
        if (f.getType() != Boolean.TYPE) {
            throw new UnsupportedOperationException();
        }
        f.set(null, Boolean.parseBoolean(value));
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    @Target(value={ElementType.FIELD})
    public static @interface ConfigString {
        public String def();

        public String com() default "";

        public String cat();
    }
}

