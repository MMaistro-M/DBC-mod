/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.controllers.data.TagMap;

public class ServerTagMapController {
    public static ServerTagMapController Instance;
    public TagMap tagMap;

    public File getDir() {
        File dir = new File(CustomNpcs.getWorldSaveDirectory(), "clones");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public File getCloneTabDir(int tab) {
        File dir = new File(this.getDir(), tab + "");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public File getCloneFolderDir(String folderName) {
        File dir = new File(this.getDir(), folderName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public TagMap getTagMap(int tab) {
        this.tagMap = new TagMap(tab);
        try {
            File file = new File(this.getCloneTabDir(tab), "___tagmap.dat");
            if (file.exists()) {
                this.loadTagMapFile(file);
            }
        }
        catch (Exception e) {
            try {
                File file = new File(this.getCloneTabDir(tab), "___tagmap.dat_old");
                if (file.exists()) {
                    this.loadTagMapFile(file);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return this.tagMap;
    }

    private void loadTagMapFile(File file) throws IOException {
        DataInputStream var1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
        this.loadTagMaps(var1);
        var1.close();
    }

    public void loadTagMaps(DataInputStream stream) throws IOException {
        NBTTagCompound nbtCompound = CompressedStreamTools.func_74794_a((DataInputStream)stream);
        this.tagMap.readNBT(nbtCompound);
    }

    public TagMap getTagMap(String folderName) {
        this.tagMap = new TagMap(folderName);
        try {
            File file = new File(this.getCloneFolderDir(folderName), "___tagmap.dat");
            if (file.exists()) {
                this.loadTagMapFile(file);
            }
        }
        catch (Exception e) {
            try {
                File file = new File(this.getCloneFolderDir(folderName), "___tagmap.dat_old");
                if (file.exists()) {
                    this.loadTagMapFile(file);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return this.tagMap;
    }

    public void saveTagMap(TagMap tagMap) {
        try {
            File saveDir = tagMap.cloneFolder != null ? this.getCloneFolderDir(tagMap.cloneFolder) : this.getCloneTabDir(tagMap.cloneTab);
            File file = new File(saveDir, "___tagmap.dat_new");
            File file1 = new File(saveDir, "___tagmap.dat_old");
            File file2 = new File(saveDir, "___tagmap.dat");
            CompressedStreamTools.func_74799_a((NBTTagCompound)tagMap.writeNBT(), (OutputStream)new FileOutputStream(file));
            if (file1.exists()) {
                file1.delete();
            }
            file2.renameTo(file1);
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
            if (file.exists()) {
                file.delete();
            }
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }
}

