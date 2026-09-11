/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.HashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.EventHooks;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.IPlayerFactionData;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.player.FactionEvent;

public class PlayerFactionData
implements IPlayerFactionData {
    private final PlayerData parent;
    public HashMap<Integer, Integer> factionData = new HashMap();

    public PlayerFactionData() {
        this.parent = null;
    }

    public PlayerFactionData(PlayerData parent) {
        this.parent = parent;
    }

    public void loadNBTData(NBTTagCompound compound) {
        HashMap<Integer, Integer> factionData = new HashMap<Integer, Integer>();
        if (compound == null) {
            return;
        }
        NBTTagList list = compound.func_150295_c("FactionData", 10);
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = list.func_150305_b(i);
            factionData.put(nbttagcompound.func_74762_e("Faction"), nbttagcompound.func_74762_e("Points"));
        }
        this.factionData = factionData;
    }

    public void saveNBTData(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (int faction : this.factionData.keySet()) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74768_a("Faction", faction);
            nbttagcompound.func_74768_a("Points", this.factionData.get(faction).intValue());
            list.func_74742_a((NBTBase)nbttagcompound);
        }
        compound.func_74782_a("FactionData", (NBTBase)list);
    }

    public int getFactionPoints(int id) {
        if (!this.factionData.containsKey(id)) {
            Faction faction = FactionController.getInstance().get(id);
            this.factionData.put(id, faction == null ? -1 : faction.defaultPoints);
        }
        return this.factionData.get(id);
    }

    public void increasePoints(int factionId, int points, EntityPlayer player) {
        if (EventHooks.onFactionPoints(player, new FactionEvent.FactionPoints((IPlayer)NpcAPI.Instance().getIEntity((Entity)((EntityPlayerMP)player)), FactionController.getInstance().get(factionId), points < 0, points))) {
            return;
        }
        if (!this.factionData.containsKey(factionId)) {
            Faction faction = FactionController.getInstance().get(factionId);
            this.factionData.put(factionId, faction == null ? -1 : faction.defaultPoints);
        }
        this.factionData.put(factionId, this.factionData.get(factionId) + points);
    }

    public NBTTagCompound getPlayerGuiData() {
        NBTTagCompound compound = new NBTTagCompound();
        this.saveNBTData(compound);
        NBTTagList list = new NBTTagList();
        for (int id : this.factionData.keySet()) {
            Faction faction = FactionController.getInstance().get(id);
            if (faction == null || faction.hideFaction) continue;
            NBTTagCompound com = new NBTTagCompound();
            faction.writeNBT(com);
            list.func_74742_a((NBTBase)com);
        }
        compound.func_74782_a("FactionList", (NBTBase)list);
        return compound;
    }

    @Override
    public int getPoints(int id) {
        return this.getFactionPoints(id);
    }

    @Override
    public void addPoints(int id, int points) {
        if (this.parent != null) {
            this.increasePoints(id, points, this.parent.player);
        }
    }

    @Override
    public void setPoints(int id, int points) {
        if (this.parent != null) {
            this.increasePoints(id, points - this.getFactionPoints(id), this.parent.player);
        }
    }
}

