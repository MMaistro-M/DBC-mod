/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.api.handler.data;

import java.util.Map;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.ISlot;

public interface IProfile {
    public IPlayer getPlayer();

    public int getCurrentSlotId();

    public Map<Integer, ISlot> getSlots();

    public NBTTagCompound writeToNBT();
}

