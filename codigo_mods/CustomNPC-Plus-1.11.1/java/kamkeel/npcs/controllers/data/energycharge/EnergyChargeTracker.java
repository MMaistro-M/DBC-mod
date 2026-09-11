/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.energycharge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.energycharge.EnergyChargeSpawnPacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class EnergyChargeTracker {
    public static final EnergyChargeTracker Instance = new EnergyChargeTracker();
    private final Map<Integer, List<ChargeEntry>> activeCharges = new HashMap<Integer, List<ChargeEntry>>();

    public void add(ChargeEntry entry) {
        List<ChargeEntry> list = this.activeCharges.get(entry.casterEntityId);
        if (list == null) {
            list = new ArrayList<ChargeEntry>();
            this.activeCharges.put(entry.casterEntityId, list);
        }
        list.add(entry);
    }

    public void remove(String instanceId, int casterEntityId) {
        List<ChargeEntry> list = this.activeCharges.get(casterEntityId);
        if (list == null) {
            return;
        }
        Iterator<ChargeEntry> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().instanceId.equals(instanceId)) continue;
            it.remove();
            break;
        }
        if (list.isEmpty()) {
            this.activeCharges.remove(casterEntityId);
        }
    }

    public void removeAllForCaster(int casterEntityId) {
        this.activeCharges.remove(casterEntityId);
    }

    public void sendToPlayer(int casterEntityId, EntityPlayerMP player, int currentWorldTick) {
        List<ChargeEntry> entries = this.activeCharges.get(casterEntityId);
        if (entries == null || entries.isEmpty()) {
            return;
        }
        for (ChargeEntry entry : entries) {
            int chargeDuration;
            int elapsed = currentWorldTick - entry.startTick;
            int n = chargeDuration = entry.spawnNbt.func_74764_b("ChargeDuration") ? entry.spawnNbt.func_74762_e("ChargeDuration") : 0;
            if (chargeDuration > 0 && elapsed >= chargeDuration) continue;
            NBTTagCompound adjustedNbt = (NBTTagCompound)entry.spawnNbt.func_74737_b();
            if (elapsed > 0) {
                adjustedNbt.func_74768_a("ChargeTick", elapsed);
            }
            PacketHandler.Instance.sendToPlayer(new EnergyChargeSpawnPacket(entry.instanceId, entry.entityClassName, adjustedNbt), player);
        }
    }

    public boolean hasCharges(int casterEntityId) {
        List<ChargeEntry> list = this.activeCharges.get(casterEntityId);
        return list != null && !list.isEmpty();
    }

    public void clear() {
        this.activeCharges.clear();
    }

    public static class ChargeEntry {
        public final String instanceId;
        public final String entityClassName;
        public final NBTTagCompound spawnNbt;
        public final int casterEntityId;
        public final int startTick;

        public ChargeEntry(String instanceId, String entityClassName, NBTTagCompound spawnNbt, int casterEntityId, int startTick) {
            this.instanceId = instanceId;
            this.entityClassName = entityClassName;
            this.spawnNbt = spawnNbt;
            this.casterEntityId = casterEntityId;
            this.startTick = startTick;
        }
    }
}

