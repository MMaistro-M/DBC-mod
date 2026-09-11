/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.Files
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package com.gtnewhorizon.gtnhmixins;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

public final class MinecraftURLClassPath {
    private static final Path MOD_DIRECTORY_PATH = new File(Launch.minecraftHome, "mods/").toPath();

    public static File getJarInModPath(String jarname) {
        try {
            return Files.walk(MOD_DIRECTORY_PATH, new FileVisitOption[0]).filter(p -> {
                String filename = p.toString();
                String extension = com.google.common.io.Files.getFileExtension((String)filename);
                return com.google.common.io.Files.getNameWithoutExtension((String)filename).contains(jarname) && ("jar".equals(extension) || "litemod".equals(extension));
            }).map(Path::toFile).findFirst().orElse(null);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean findJarInClassPath(String jarname) {
        for (URL url : Launch.classLoader.getURLs()) {
            String filename = url.getFile();
            String extension = com.google.common.io.Files.getFileExtension((String)filename);
            if (!com.google.common.io.Files.getNameWithoutExtension((String)filename).contains(jarname) || !"jar".equals(extension) && !"litemod".equals(extension)) continue;
            return true;
        }
        return false;
    }

    public static void addJar(File pathToJar) throws Exception {
        LaunchClassLoader loader = Launch.classLoader;
        loader.addURL(pathToJar.toURI().toURL());
        loader.getSources().remove(loader.getSources().size() - 1);
    }

    private MinecraftURLClassPath() {
    }
}

