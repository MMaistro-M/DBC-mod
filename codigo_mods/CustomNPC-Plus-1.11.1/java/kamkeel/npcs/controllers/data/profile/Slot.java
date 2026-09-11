/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.profile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.handler.data.ISlot;

public class Slot
implements ISlot {
    private int id;
    private String name;
    private long lastLoaded;
    private boolean temporary;
    private Map<String, NBTTagCompound> components = new HashMap<String, NBTTagCompound>();

    public Slot(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Slot(int id, String name, long lastLoaded, boolean temporary, Map<String, NBTTagCompound> components) {
        this.id = id;
        this.name = name;
        this.lastLoaded = lastLoaded;
        this.temporary = temporary;
        this.components = components;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getLastLoaded() {
        return this.lastLoaded;
    }

    @Override
    public void setLastLoaded(long time) {
        this.lastLoaded = time;
    }

    @Override
    public boolean isTemporary() {
        return this.temporary;
    }

    @Override
    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }

    @Override
    public Map<String, NBTTagCompound> getComponents() {
        return this.components;
    }

    @Override
    public void setComponentData(String key, NBTTagCompound data) {
        this.components.put(key, data);
    }

    @Override
    public NBTTagCompound getComponentData(String key) {
        return this.components.get(key);
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound slotNBT = new NBTTagCompound();
        slotNBT.func_74778_a("Name", this.name);
        slotNBT.func_74772_a("LastLoaded", this.lastLoaded);
        slotNBT.func_74757_a("Temporary", this.temporary);
        NBTTagCompound compCompound = new NBTTagCompound();
        for (Map.Entry<String, NBTTagCompound> entry : this.components.entrySet()) {
            compCompound.func_74782_a(entry.getKey(), (NBTBase)entry.getValue());
        }
        slotNBT.func_74782_a("Components", (NBTBase)compCompound);
        return slotNBT;
    }

    public static Slot fromNBT(int id, NBTTagCompound slotNBT) {
        String name = slotNBT.func_74779_i("Name");
        long lastLoaded = slotNBT.func_74763_f("LastLoaded");
        boolean temporary = slotNBT.func_74767_n("Temporary");
        Slot slot = new Slot(id, name);
        slot.setLastLoaded(lastLoaded);
        slot.setTemporary(temporary);
        if (slotNBT.func_74764_b("Components")) {
            NBTTagCompound compCompound = slotNBT.func_74775_l("Components");
            Set keys = compCompound.func_150296_c();
            for (String key : keys) {
                slot.setComponentData(key, compCompound.func_74775_l(key));
            }
        }
        return slot;
    }
}

