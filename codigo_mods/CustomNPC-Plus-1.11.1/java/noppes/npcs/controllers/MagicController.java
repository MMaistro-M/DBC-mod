/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.network.enums.EnumSyncType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.api.handler.IMagicHandler;
import noppes.npcs.constants.EnumDiagramLayout;
import noppes.npcs.controllers.data.Magic;
import noppes.npcs.controllers.data.MagicAssociation;
import noppes.npcs.controllers.data.MagicCycle;

public class MagicController
implements IMagicHandler {
    public HashMap<Integer, Magic> magics = new HashMap();
    public HashMap<Integer, Magic> magicSync = new HashMap();
    public HashMap<Integer, MagicCycle> cycles = new HashMap();
    public HashMap<Integer, MagicCycle> cyclesSync = new HashMap();
    public int lastUsedCycleID = 0;
    private int lastUsedMagicID = 0;
    private static MagicController instance;

    public MagicController() {
        instance = this;
    }

    public static MagicController getInstance() {
        return instance;
    }

    @Override
    public Magic getMagic(int magicId) {
        return this.magics.get(magicId);
    }

    @Override
    public MagicCycle getCycle(int cycleID) {
        return this.cycles.get(cycleID);
    }

    public void load() {
        this.magics.clear();
        this.cycles.clear();
        this.lastUsedCycleID = 0;
        this.lastUsedMagicID = 0;
        File saveDir = CustomNpcs.getWorldSaveDirectory();
        if (saveDir == null) {
            return;
        }
        try {
            File file = new File(saveDir, "magic.dat");
            if (file.exists()) {
                this.loadMagicFile(file);
            }
        }
        catch (Exception e) {
            try {
                File file = new File(saveDir, "magic.dat_old");
                if (file.exists()) {
                    this.loadMagicFile(file);
                }
            }
            catch (Exception file) {
                // empty catch block
            }
        }
        if (this.magics.isEmpty() && this.cycles.isEmpty()) {
            Magic earth = new Magic(this.getUnusedId(), "Earth", 56576);
            earth.setItem(new ItemStack(CustomItems.earthElement));
            Magic water = new Magic(this.getUnusedId(), "Water", 15916288);
            water.setItem(new ItemStack(CustomItems.waterElement));
            Magic fire = new Magic(this.getUnusedId(), "Fire", 0xDD0000);
            fire.setItem(new ItemStack(CustomItems.spellFire));
            Magic air = new Magic(this.getUnusedId(), "Air", 0xDD0000);
            air.setItem(new ItemStack(CustomItems.airElement));
            Magic dark = new Magic(this.getUnusedId(), "Dark", 0xDD0000);
            dark.setItem(new ItemStack(CustomItems.spellDark));
            Magic holy = new Magic(this.getUnusedId(), "Holy", 0xDD0000);
            holy.setItem(new ItemStack(CustomItems.spellHoly));
            Magic nature = new Magic(this.getUnusedId(), "Nature", 0xDD0000);
            nature.setItem(new ItemStack(CustomItems.spellNature));
            Magic arcane = new Magic(this.getUnusedId(), "Arcane", 0xDD0000);
            arcane.setItem(new ItemStack(CustomItems.spellArcane));
            earth.interactions.put(air.id, Float.valueOf(-0.5f));
            air.interactions.put(earth.id, Float.valueOf(0.5f));
            water.interactions.put(earth.id, Float.valueOf(-0.5f));
            earth.interactions.put(water.id, Float.valueOf(0.5f));
            fire.interactions.put(water.id, Float.valueOf(-0.5f));
            water.interactions.put(fire.id, Float.valueOf(0.5f));
            air.interactions.put(fire.id, Float.valueOf(-0.5f));
            fire.interactions.put(air.id, Float.valueOf(0.5f));
            dark.interactions.put(nature.id, Float.valueOf(-0.5f));
            nature.interactions.put(dark.id, Float.valueOf(0.5f));
            nature.interactions.put(holy.id, Float.valueOf(-0.5f));
            holy.interactions.put(nature.id, Float.valueOf(0.5f));
            holy.interactions.put(arcane.id, Float.valueOf(-0.5f));
            arcane.interactions.put(holy.id, Float.valueOf(0.5f));
            arcane.interactions.put(dark.id, Float.valueOf(-0.5f));
            dark.interactions.put(arcane.id, Float.valueOf(0.5f));
            earth.interactions.put(nature.id, Float.valueOf(-0.25f));
            nature.interactions.put(earth.id, Float.valueOf(0.25f));
            water.interactions.put(holy.id, Float.valueOf(-0.25f));
            holy.interactions.put(water.id, Float.valueOf(0.25f));
            fire.interactions.put(arcane.id, Float.valueOf(-0.25f));
            arcane.interactions.put(fire.id, Float.valueOf(0.25f));
            air.interactions.put(dark.id, Float.valueOf(-0.25f));
            dark.interactions.put(air.id, Float.valueOf(0.25f));
            dark.interactions.put(fire.id, Float.valueOf(-0.25f));
            fire.interactions.put(dark.id, Float.valueOf(0.25f));
            nature.interactions.put(air.id, Float.valueOf(-0.25f));
            air.interactions.put(nature.id, Float.valueOf(0.25f));
            holy.interactions.put(earth.id, Float.valueOf(-0.25f));
            earth.interactions.put(holy.id, Float.valueOf(0.25f));
            arcane.interactions.put(water.id, Float.valueOf(-0.25f));
            water.interactions.put(arcane.id, Float.valueOf(0.25f));
            this.magics.put(earth.id, earth);
            this.magics.put(water.id, water);
            this.magics.put(fire.id, fire);
            this.magics.put(air.id, air);
            this.magics.put(dark.id, dark);
            this.magics.put(holy.id, holy);
            this.magics.put(nature.id, nature);
            this.magics.put(arcane.id, arcane);
            MagicCycle defaultCycle = new MagicCycle();
            defaultCycle.id = this.getUnusedCycleId();
            defaultCycle.name = "Universal";
            defaultCycle.layout = EnumDiagramLayout.CIRCULAR_MANUAL;
            defaultCycle.displayName = "&6Elementa Cycle";
            this.cycles.put(defaultCycle.id, defaultCycle);
            this.addMagicToCycle(earth.id, defaultCycle.id, 1, 1);
            this.addMagicToCycle(water.id, defaultCycle.id, 1, 2);
            this.addMagicToCycle(fire.id, defaultCycle.id, 1, 3);
            this.addMagicToCycle(air.id, defaultCycle.id, 1, 0);
            this.addMagicToCycle(dark.id, defaultCycle.id, 0, 3);
            this.addMagicToCycle(holy.id, defaultCycle.id, 0, 1);
            this.addMagicToCycle(nature.id, defaultCycle.id, 0, 0);
            this.addMagicToCycle(arcane.id, defaultCycle.id, 0, 2);
            this.saveMagicData();
        }
    }

    private void loadMagicFile(File file) throws IOException {
        DataInputStream stream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
        this.loadMagic(stream);
        stream.close();
    }

    public void loadMagic(DataInputStream stream) throws IOException {
        NBTTagCompound compound = CompressedStreamTools.func_74794_a((DataInputStream)stream);
        this.lastUsedMagicID = compound.func_74762_e("lastID");
        this.lastUsedCycleID = compound.func_74762_e("lastCycleID");
        this.magics.clear();
        NBTTagList magicList = compound.func_150295_c("Magics", 10);
        for (int i = 0; i < magicList.func_74745_c(); ++i) {
            NBTTagCompound magCompound = magicList.func_150305_b(i);
            Magic mag = new Magic();
            mag.readNBT(magCompound);
            this.magics.put(mag.id, mag);
        }
        this.cycles.clear();
        NBTTagList cycleList = compound.func_150295_c("Cycles", 10);
        for (int i = 0; i < cycleList.func_74745_c(); ++i) {
            NBTTagCompound catCompound = cycleList.func_150305_b(i);
            MagicCycle cycle = new MagicCycle();
            cycle.readNBT(catCompound);
            this.cycles.put(cycle.id, cycle);
        }
    }

    public NBTTagCompound getNBT() {
        NBTTagList magicList = new NBTTagList();
        for (Magic magic : this.magics.values()) {
            NBTTagCompound magCompound = new NBTTagCompound();
            magic.writeNBT(magCompound);
            magicList.func_74742_a((NBTBase)magCompound);
        }
        NBTTagList catList = new NBTTagList();
        for (MagicCycle cat : this.cycles.values()) {
            NBTTagCompound catCompound = new NBTTagCompound();
            cat.writeNBT(catCompound);
            catList.func_74742_a((NBTBase)catCompound);
        }
        NBTTagCompound nBTTagCompound = new NBTTagCompound();
        nBTTagCompound.func_74768_a("lastID", this.lastUsedMagicID);
        nBTTagCompound.func_74768_a("lastCycleID", this.lastUsedCycleID);
        nBTTagCompound.func_74782_a("Magics", (NBTBase)magicList);
        nBTTagCompound.func_74782_a("Cycles", (NBTBase)catList);
        return nBTTagCompound;
    }

    public void saveMagicData() {
        try {
            File saveDir = CustomNpcs.getWorldSaveDirectory();
            File fileNew = new File(saveDir, "magic.dat_new");
            File fileOld = new File(saveDir, "magic.dat_old");
            File fileCurrent = new File(saveDir, "magic.dat");
            CompressedStreamTools.func_74799_a((NBTTagCompound)this.getNBT(), (OutputStream)new FileOutputStream(fileNew));
            if (fileOld.exists()) {
                fileOld.delete();
            }
            fileCurrent.renameTo(fileOld);
            if (fileCurrent.exists()) {
                fileCurrent.delete();
            }
            fileNew.renameTo(fileCurrent);
            if (fileNew.exists()) {
                fileNew.delete();
            }
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }

    public void saveMagic(Magic mag) {
        if (mag.id < 0) {
            mag.id = this.getUnusedId();
            while (this.hasName(mag.name)) {
                mag.name = mag.name + "_";
            }
        } else {
            Magic existing = this.magics.get(mag.id);
            if (existing != null && !existing.name.equals(mag.name)) {
                while (this.hasName(mag.name)) {
                    mag.name = mag.name + "_";
                }
            }
        }
        this.magics.put(mag.id, mag);
        NBTTagCompound magicCompound = new NBTTagCompound();
        mag.writeNBT(magicCompound);
        SyncController.syncUpdate(EnumSyncType.MAGIC, -1, magicCompound);
        this.saveMagicData();
    }

    public void removeMagic(int magicID) {
        if (this.magics.containsKey(magicID)) {
            this.magics.remove(magicID);
            SyncController.syncRemove(EnumSyncType.MAGIC, magicID);
            this.saveMagicData();
        }
    }

    public int getUnusedId() {
        if (this.lastUsedMagicID == 0) {
            for (int id : this.magics.keySet()) {
                if (id <= this.lastUsedMagicID) continue;
                this.lastUsedMagicID = id;
            }
        }
        ++this.lastUsedMagicID;
        return this.lastUsedMagicID;
    }

    public boolean hasName(String newName) {
        if (newName.trim().isEmpty()) {
            return true;
        }
        for (Magic mag : this.magics.values()) {
            if (!mag.name.equalsIgnoreCase(newName)) continue;
            return true;
        }
        return false;
    }

    public int getUnusedCycleId() {
        if (this.lastUsedCycleID == 0) {
            for (int id : this.cycles.keySet()) {
                if (id <= this.lastUsedCycleID) continue;
                this.lastUsedCycleID = id;
            }
        }
        ++this.lastUsedCycleID;
        return this.lastUsedCycleID;
    }

    public boolean containsCategoryName(String title) {
        title = title.toLowerCase();
        for (MagicCycle cat : this.cycles.values()) {
            if (!cat.name.toLowerCase().equals(title)) continue;
            return true;
        }
        return false;
    }

    public void saveCycle(MagicCycle cycle) {
        if (cycle.id < 0) {
            cycle.id = this.getUnusedCycleId();
            while (this.containsCategoryName(cycle.name)) {
                cycle.name = cycle.name + "_";
            }
        } else {
            MagicCycle existing = this.cycles.get(cycle.id);
            if (existing != null && !existing.name.equals(cycle.name)) {
                while (this.containsCategoryName(cycle.name)) {
                    cycle.name = cycle.name + "_";
                }
            }
        }
        this.cycles.put(cycle.id, cycle);
        NBTTagCompound cycleCompound = new NBTTagCompound();
        cycle.writeNBT(cycleCompound);
        SyncController.syncUpdate(EnumSyncType.MAGIC_CYCLE, -1, cycleCompound);
        this.saveMagicData();
    }

    public void removeCycle(int categoryId) {
        if (this.cycles.containsKey(categoryId)) {
            this.cycles.remove(categoryId);
            SyncController.syncRemove(EnumSyncType.MAGIC_CYCLE, categoryId);
            this.saveMagicData();
        }
    }

    @Override
    public void addMagicToCycle(int magicId, int cycleId, int index, int priority) {
        MagicCycle cat = this.cycles.get(cycleId);
        if (cat == null) {
            return;
        }
        Magic magic = this.magics.get(magicId);
        if (magic == null) {
            return;
        }
        MagicAssociation assoc = new MagicAssociation();
        assoc.magicId = magicId;
        assoc.index = index;
        assoc.priority = priority;
        cat.associations.put(magicId, assoc);
        this.saveCycle(cat);
    }

    @Override
    public void removeMagicFromCycle(int magicId, int cycleId) {
        MagicCycle cat = this.cycles.get(cycleId);
        if (cat == null) {
            return;
        }
        cat.associations.remove(magicId);
        this.saveCycle(cat);
    }
}

