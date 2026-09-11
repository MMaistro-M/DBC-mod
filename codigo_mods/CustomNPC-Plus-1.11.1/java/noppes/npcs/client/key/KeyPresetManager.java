/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.key;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.client.key.KeyPreset;
import noppes.npcs.util.NBTJsonUtil;

public class KeyPresetManager {
    public List<KeyPreset> keys = new ArrayList<KeyPreset>();
    public String fileName;

    public KeyPresetManager(String fileName) {
        this.fileName = fileName;
    }

    public KeyPreset add(String name) {
        KeyPreset preset = new KeyPreset(name);
        this.keys.add(preset);
        return preset;
    }

    public boolean hasMatchingKeyPressed(int keyCode) {
        for (KeyPreset key : this.keys) {
            if (!key.currentState.matches(keyCode, true)) continue;
            return true;
        }
        return false;
    }

    public void tick() {
        for (KeyPreset key : this.keys) {
            key.tick();
        }
    }

    public void load() {
        try {
            File dir = this.getDir();
            File file = new File(dir, this.fileName + ".json");
            if (!file.exists()) {
                for (KeyPreset key : this.keys) {
                    if (key.defaultState.hasState()) continue;
                    key.currentState.readFrom(key.defaultState);
                }
                return;
            }
            NBTTagCompound compound = NBTJsonUtil.LoadFile(file);
            for (KeyPreset key : this.keys) {
                key.readFromNbt(compound);
            }
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }

    public void save() {
        try {
            File dir = this.getDir();
            String filename = this.fileName + ".json";
            File newFile = new File(dir, filename + "_new");
            File originalFile = new File(dir, filename);
            NBTTagCompound compound = new NBTTagCompound();
            for (KeyPreset key : this.keys) {
                key.writeToNbt(compound);
            }
            NBTJsonUtil.SaveFile(newFile, compound);
            if (originalFile.exists()) {
                originalFile.delete();
            }
            newFile.renameTo(originalFile);
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }

    public File getDir() {
        File dir = new File(CustomNpcs.Dir, "keypresets");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }
}

