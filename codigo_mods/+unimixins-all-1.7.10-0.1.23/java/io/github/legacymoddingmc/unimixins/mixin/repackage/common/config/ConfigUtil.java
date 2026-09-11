/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 */
package io.github.legacymoddingmc.unimixins.mixin.repackage.common.config;

import io.github.legacymoddingmc.unimixins.mixin.repackage.common.config.AnnotatedProperties;
import java.io.File;
import net.minecraft.launchwrapper.Launch;

public class ConfigUtil {
    public static void load(Class<?> cls) {
        File configDir = new File(Launch.minecraftHome, "config");
        File oldConfigFile = new File(configDir, "unimixins.cfg");
        if (oldConfigFile.exists()) {
            oldConfigFile.renameTo(new File(configDir, "unimixins.old.delete_me.cfg"));
        }
        AnnotatedProperties.load(new File(configDir, "unimixins.properties"), cls);
    }
}

