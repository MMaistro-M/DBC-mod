/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package io.github.legacymoddingmc.unimixins.gtnhmixins.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class LaunchClassLoaderUtils {
    private static Map<String, byte[]> resourceCache;
    private static Set<String> negativeResourceCache;

    public static void putInResourceCache(String key, byte[] value) {
        resourceCache.put(key, value);
        negativeResourceCache.remove(key);
    }

    static {
        try {
            Field resourceCacheField = LaunchClassLoader.class.getDeclaredField("resourceCache");
            resourceCacheField.setAccessible(true);
            resourceCache = (Map)resourceCacheField.get(Launch.classLoader);
            Field negativeResourceCacheField = LaunchClassLoader.class.getDeclaredField("negativeResourceCache");
            negativeResourceCacheField.setAccessible(true);
            negativeResourceCache = (Set)negativeResourceCacheField.get(Launch.classLoader);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

