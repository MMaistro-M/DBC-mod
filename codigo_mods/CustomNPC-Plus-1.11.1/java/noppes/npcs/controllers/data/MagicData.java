/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.handler.data.IMagicData;
import noppes.npcs.controllers.data.MagicEntry;

public class MagicData
implements IMagicData {
    private final HashMap<Integer, MagicEntry> magics = new HashMap();

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagCompound magicData = new NBTTagCompound();
        for (int key : this.magics.keySet()) {
            magicData.func_74782_a(String.valueOf(key), (NBTBase)this.magics.get(key).writeToNBT());
        }
        compound.func_74782_a("MagicData", (NBTBase)magicData);
    }

    public void readToNBT(NBTTagCompound compound) {
        NBTTagCompound magicData = compound.func_74775_l("MagicData");
        this.magics.clear();
        if (magicData == null) {
            return;
        }
        for (Object key : magicData.func_150296_c()) {
            int i = Integer.parseInt((String)key);
            MagicEntry entry = new MagicEntry();
            entry.readToNBT(magicData.func_74775_l((String)key));
            this.magics.put(i, entry);
        }
    }

    public MagicEntry getMagic(int id) {
        return this.magics.get(id);
    }

    @Override
    public void removeMagic(int id) {
        this.magics.remove(id);
    }

    @Override
    public boolean hasMagic(int id) {
        return this.magics.containsKey(id);
    }

    public HashMap<Integer, MagicEntry> getMagics() {
        return this.magics;
    }

    @Override
    public void clear() {
        this.magics.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.magics.isEmpty();
    }

    @Override
    public void addMagic(int id, float damage, float split) {
        MagicEntry entry = new MagicEntry();
        entry.damage = damage;
        entry.split = split;
        this.magics.put(id, entry);
    }

    @Override
    public float getMagicDamage(int id) {
        MagicEntry entry = this.magics.get(id);
        return entry == null ? 0.0f : entry.damage;
    }

    @Override
    public float getMagicSplit(int id) {
        MagicEntry entry = this.magics.get(id);
        return entry == null ? 0.0f : entry.split;
    }

    public MagicData copy() {
        MagicData copy = new MagicData();
        for (Map.Entry<Integer, MagicEntry> e : this.magics.entrySet()) {
            MagicEntry entryCopy = new MagicEntry();
            entryCopy.damage = e.getValue().damage;
            entryCopy.split = e.getValue().split;
            copy.magics.put(e.getKey(), entryCopy);
        }
        return copy;
    }
}

