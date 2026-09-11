/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ModContainer
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.AbstractResourcePack
 *  net.minecraft.client.resources.FallbackResourceManager
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.ResourcePackRepository
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 */
package noppes.npcs.client;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.NPCResourceHelper;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import org.apache.commons.lang3.StringUtils;

public class AssetsBrowser {
    public boolean isRoot;
    private int depth;
    private String folder;
    public HashSet<String> folders = new HashSet();
    public HashSet<String> files = new HashSet();
    private String[] extensions;

    public AssetsBrowser(String folder, String[] extensions) {
        this.extensions = extensions;
        this.setFolder(folder);
    }

    public void setFolder(String folder) {
        if (!folder.endsWith("/")) {
            folder = folder + "/";
        }
        this.isRoot = folder.length() <= 1;
        this.folder = "/assets" + folder;
        this.depth = StringUtils.countMatches((CharSequence)this.folder, "/");
        this.getFiles();
    }

    public AssetsBrowser(String[] extensions) {
        this.extensions = extensions;
    }

    private void getFiles() {
        this.folders.clear();
        this.files.clear();
        ResourcePackRepository repos = Minecraft.func_71410_x().func_110438_M();
        SimpleReloadableResourceManager simplemanager = (SimpleReloadableResourceManager)Minecraft.func_71410_x().func_110442_L();
        Map map = (Map)ObfuscationReflectionHelper.getPrivateValue(SimpleReloadableResourceManager.class, (Object)simplemanager, (int)2);
        HashSet<String> set = new HashSet<String>();
        for (String name : map.keySet()) {
            if (!(map.get(name) instanceof FallbackResourceManager)) continue;
            FallbackResourceManager manager = (FallbackResourceManager)map.get(name);
            List list = (List)ObfuscationReflectionHelper.getPrivateValue(FallbackResourceManager.class, (Object)manager, (int)0);
            for (IResourcePack pack : list) {
                AbstractResourcePack p;
                File file;
                if (!(pack instanceof AbstractResourcePack) || (file = NPCResourceHelper.getPackFile(p = (AbstractResourcePack)pack)) == null) continue;
                set.add(file.getAbsolutePath());
            }
        }
        for (String file : set) {
            this.progressFile(new File(file));
        }
        for (ModContainer mod : Loader.instance().getModList()) {
            if (!mod.getSource().exists()) continue;
            this.progressFile(mod.getSource());
        }
    }

    private void progressFile(File file) {
        try {
            if (!file.isDirectory() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
                ZipFile zip = new ZipFile(file);
                Enumeration<? extends ZipEntry> entries = zip.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry zipentry = entries.nextElement();
                    String entryName = zipentry.getName();
                    this.checkFile(entryName);
                }
                zip.close();
            } else if (file.isDirectory()) {
                int length = file.getAbsolutePath().length();
                this.checkFolder(file, length);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkFolder(File file, int length) {
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            String name = f.getAbsolutePath().substring(length);
            if (!(name = name.replace("\\", "/")).startsWith("/")) {
                name = "/" + name;
            }
            if (f.isDirectory() && (this.folder.startsWith(name) || name.startsWith(this.folder))) {
                this.checkFile(name + "/");
                this.checkFolder(f, length);
                continue;
            }
            this.checkFile(name);
        }
    }

    private void checkFile(String name) {
        if (!name.startsWith("/")) {
            name = "/" + name;
        }
        if (!name.startsWith(this.folder)) {
            return;
        }
        String[] split = name.split("/");
        int count = split.length;
        if (count == this.depth + 1) {
            if (this.validExtension(name)) {
                this.files.add(split[this.depth]);
            }
        } else if (this.depth + 1 < count) {
            this.folders.add(split[this.depth]);
        }
    }

    private boolean validExtension(String entryName) {
        int index = entryName.indexOf(".");
        if (index < 0) {
            return false;
        }
        String extension = entryName.substring(index + 1);
        for (String ex : this.extensions) {
            if (!ex.equalsIgnoreCase(extension)) continue;
            return true;
        }
        return false;
    }

    public String getAsset(String asset) {
        String[] split = this.folder.split("/");
        if (split.length < 3) {
            return null;
        }
        String texture = split[2] + ":";
        texture = texture + this.folder.substring(texture.length() + 8) + asset;
        return texture;
    }

    public static String getRoot(String asset) {
        String location;
        String mod = "minecraft";
        int index = asset.indexOf(":");
        if (index > 0) {
            mod = asset.substring(0, index);
            asset = asset.substring(index + 1);
        }
        if (asset.startsWith("/")) {
            asset = asset.substring(1);
        }
        if ((index = (location = "/" + mod + "/" + asset).lastIndexOf("/")) > 0) {
            location = location.substring(0, index);
        }
        return location;
    }
}

