/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.api.handler.data;

import java.util.Map;
import net.minecraft.nbt.NBTTagCompound;

public interface ISlot {
    public int getId();

    public String getName();

    public void setName(String var1);

    public long getLastLoaded();

    public void setLastLoaded(long var1);

    public boolean isTemporary();

    public void setTemporary(boolean var1);

    public Map<String, NBTTagCompound> getComponents();

    public void setComponentData(String var1, NBTTagCompound var2);

    public NBTTagCompound getComponentData(String var1);

    public NBTTagCompound toNBT();
}

