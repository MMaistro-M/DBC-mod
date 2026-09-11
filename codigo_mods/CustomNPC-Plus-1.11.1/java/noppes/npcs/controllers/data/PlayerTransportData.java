/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.HashSet;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.IPlayerTransportData;
import noppes.npcs.api.handler.data.ITransportLocation;
import noppes.npcs.controllers.TransportController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.TransportLocation;

public class PlayerTransportData
implements IPlayerTransportData {
    private final PlayerData parent;
    public HashSet<Integer> transports = new HashSet();

    public PlayerTransportData(PlayerData parent) {
        this.parent = parent;
    }

    public void loadNBTData(NBTTagCompound compound) {
        HashSet<Integer> dialogsRead = new HashSet<Integer>();
        if (compound == null) {
            return;
        }
        NBTTagList list = compound.func_150295_c("TransportData", 10);
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = list.func_150305_b(i);
            dialogsRead.add(nbttagcompound.func_74762_e("Transport"));
        }
        this.transports = dialogsRead;
    }

    public void saveNBTData(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (int dia : this.transports) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Transport", dia);
            list.func_74742_a((NBTBase)nbttagcompound);
        }
        compound.func_74782_a("TransportData", (NBTBase)list);
    }

    @Override
    public boolean hasTransport(int id) {
        return this.transports.contains(id);
    }

    @Override
    public void addTransport(int id) {
        this.transports.add(id);
    }

    @Override
    public void addTransport(ITransportLocation location) {
        this.transports.add(location.getId());
    }

    @Override
    public ITransportLocation getTransport(int id) {
        return TransportController.getInstance().getTransport(id);
    }

    @Override
    public ITransportLocation[] getTransports() {
        ArrayList<TransportLocation> list = new ArrayList<TransportLocation>();
        for (int id : this.transports) {
            TransportLocation location = TransportController.getInstance().getTransport(id);
            list.add(location);
        }
        return list.toArray(new ITransportLocation[0]);
    }

    @Override
    public void removeTransport(int id) {
        this.transports.remove(id);
    }
}

