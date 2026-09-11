/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  net.minecraft.launchwrapper.Launch
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package io.github.legacymoddingmc.unimixins.mixin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.launchwrapper.Launch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.transformer.Config;

public final class MixinModidDecorator {
    private static final Logger logger = LogManager.getLogger();
    public static final String KEY_MOD_ID = "fabric-modId";
    private static Map<String, String> jarNameToModid;

    private MixinModidDecorator() {
    }

    public static void apply() {
        if (jarNameToModid == null) {
            jarNameToModid = MixinModidDecorator.createJarNameToModidMap();
            Launch.blackboard.put("unimixins.mixinModidDecorator.refresh", MixinModidDecorator::apply);
        }
        for (Config config : Mixins.getConfigs()) {
            String modid;
            String jarName;
            URL resource;
            if (config.getConfig().hasDecoration(KEY_MOD_ID) || (resource = Launch.classLoader.getResource(config.getName())) == null || (jarName = MixinModidDecorator.getJarNameFromResourceUrl(resource)) == null || (modid = jarNameToModid.get(jarName)) == null) continue;
            config.getConfig().decorate(KEY_MOD_ID, modid);
        }
    }

    private static Map<String, String> createJarNameToModidMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            for (URL url : Collections.list(Launch.classLoader.getResources("mcmod.info"))) {
                String modid;
                List<ModMetadata> metas;
                String fileName = MixinModidDecorator.getJarNameFromResourceUrl(url);
                if (fileName == null || (metas = MixinModidDecorator.parseMcmodInfo(url)).isEmpty() || (modid = metas.get((int)0).modid) == null) continue;
                map.put(fileName, MixinModidDecorator.createUniqueModid(modid, map.values()));
            }
        }
        catch (Exception e) {
            logger.warn("Failed to construct jar name -> modid map, mod names will not be shown in errors.");
        }
        return map;
    }

    private static String createUniqueModid(String modid, Collection<String> registeredModids) {
        String sanitizedModid = "";
        for (int i = 0; i < modid.length(); ++i) {
            char c = modid.charAt(i);
            boolean valid = i == 0 ? MixinModidDecorator.isValidLetter(c) : MixinModidDecorator.isValidLetter(c) || MixinModidDecorator.isValidDigit(c);
            sanitizedModid = sanitizedModid + (valid ? c : (char)'_');
        }
        modid = sanitizedModid;
        int discriminator = 1;
        while (registeredModids.contains(modid)) {
            modid = sanitizedModid + "_" + ++discriminator;
        }
        return modid;
    }

    private static boolean isValidLetter(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    private static boolean isValidDigit(char c) {
        return c >= '0' && c <= '9' || c == '_';
    }

    private static List<ModMetadata> parseMcmodInfo(URL url) {
        try {
            JsonElement root = (JsonElement)new Gson().fromJson((Reader)new InputStreamReader(url.openStream()), JsonElement.class);
            if (root.isJsonArray()) {
                return Arrays.asList((Object[])new Gson().fromJson((Reader)new InputStreamReader(url.openStream()), ModMetadata[].class));
            }
            return ((ModMetadataCollection)new Gson().fromJson((Reader)new InputStreamReader((InputStream)url.openStream()), ModMetadataCollection.class)).modList;
        }
        catch (Exception e) {
            logger.warn("Failed to parse mcmod.info at " + url + ": " + e);
            return Arrays.asList(new ModMetadata[0]);
        }
    }

    private static String getJarNameFromResourceUrl(URL url) {
        String filePath;
        String[] parts;
        if (url.getPath().contains("!/") && (parts = (filePath = url.getPath().split("!/")[0]).split("/")).length != 0) {
            return parts[parts.length - 1];
        }
        return null;
    }

    private static class ModMetadataCollection {
        List<ModMetadata> modList;

        private ModMetadataCollection() {
        }
    }

    private static class ModMetadata {
        String modid;

        private ModMetadata() {
        }
    }
}

