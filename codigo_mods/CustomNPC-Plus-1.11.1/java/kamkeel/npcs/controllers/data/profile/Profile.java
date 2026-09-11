/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package kamkeel.npcs.controllers.data.profile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kamkeel.npcs.controllers.data.profile.Slot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IProfile;
import noppes.npcs.api.handler.data.ISlot;

public class Profile
implements IProfile {
    public EntityPlayer player;
    public int currentSlotId;
    private final Map<Integer, ISlot> slots = new HashMap<Integer, ISlot>();
    public Map<Integer, Long> sharedQuestTimestamps = new HashMap<Integer, Long>();
    private boolean locked = false;

    public Profile(EntityPlayer player, NBTTagCompound compound) {
        this.player = player;
        this.currentSlotId = compound.func_74764_b("CurrentSlotId") ? compound.func_74762_e("CurrentSlotId") : 0;
        if (compound.func_74764_b("Slots")) {
            NBTTagCompound slotsCompound = compound.func_74775_l("Slots");
            Set keys = slotsCompound.func_150296_c();
            for (String key : keys) {
                try {
                    int slotId = Integer.parseInt(key);
                    NBTTagCompound slotNBT = slotsCompound.func_74775_l(key);
                    Slot slot = Slot.fromNBT(slotId, slotNBT);
                    this.slots.put(slotId, slot);
                }
                catch (NumberFormatException numberFormatException) {}
            }
        }
        if (compound.func_74764_b("SharedQuestTimestamps")) {
            NBTTagList list = compound.func_150295_c("SharedQuestTimestamps", 10);
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound entry = list.func_150305_b(i);
                this.sharedQuestTimestamps.put(entry.func_74762_e("Quest"), entry.func_74763_f("Date"));
            }
        }
    }

    public Profile(EntityPlayer player) {
        this.player = player;
        this.currentSlotId = 0;
        Slot defaultSlot = new Slot(0, "Default Slot");
        defaultSlot.setLastLoaded(System.currentTimeMillis());
        this.slots.put(0, defaultSlot);
    }

    @Override
    public IPlayer getPlayer() {
        return NoppesUtilServer.getIPlayer(this.player);
    }

    @Override
    public int getCurrentSlotId() {
        return this.currentSlotId;
    }

    @Override
    public Map<Integer, ISlot> getSlots() {
        return this.slots;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        if (this.player != null) {
            compound.func_74778_a("Name", this.player.func_70005_c_());
        }
        compound.func_74768_a("CurrentSlotId", this.currentSlotId);
        NBTTagCompound slotsCompound = new NBTTagCompound();
        for (Map.Entry<Integer, ISlot> entry : this.slots.entrySet()) {
            if (entry.getValue().isTemporary()) continue;
            slotsCompound.func_74782_a(String.valueOf(entry.getKey()), (NBTBase)entry.getValue().toNBT());
        }
        compound.func_74782_a("Slots", (NBTBase)slotsCompound);
        NBTTagList questList = new NBTTagList();
        for (Map.Entry<Integer, Long> entry : this.sharedQuestTimestamps.entrySet()) {
            NBTTagCompound questEntry = new NBTTagCompound();
            questEntry.func_74768_a("Quest", entry.getKey().intValue());
            questEntry.func_74772_a("Date", entry.getValue().longValue());
            questList.func_74742_a((NBTBase)questEntry);
        }
        compound.func_74782_a("SharedQuestTimestamps", (NBTBase)questList);
        return compound;
    }
}

