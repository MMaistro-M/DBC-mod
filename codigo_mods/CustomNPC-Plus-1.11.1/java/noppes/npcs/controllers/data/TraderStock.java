/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.constants.EnumStockReset;

public class TraderStock {
    public boolean enableStock = false;
    public boolean perPlayer = false;
    public EnumStockReset resetType = EnumStockReset.NONE;
    public long customResetTime = 0L;
    public int[] maxStock = new int[18];
    public int[] currentStock = new int[18];
    public long lastResetTime = 0L;
    public HashMap<String, PlayerTraderStock> playerStock = new HashMap();

    public TraderStock() {
        for (int i = 0; i < 18; ++i) {
            this.maxStock[i] = -1;
            this.currentStock[i] = -1;
        }
    }

    public synchronized int getAvailableStock(int slot, String playerName) {
        if (!this.enableStock || slot < 0 || slot >= 18 || this.maxStock[slot] < 0) {
            return Integer.MAX_VALUE;
        }
        if (this.perPlayer) {
            PlayerTraderStock pStock = this.playerStock.get(playerName);
            if (pStock == null) {
                return this.maxStock[slot];
            }
            return pStock.getStock(slot, this.maxStock[slot]);
        }
        if (this.currentStock[slot] < 0) {
            return this.maxStock[slot];
        }
        return Math.max(0, this.currentStock[slot]);
    }

    public boolean hasStock(int slot, String playerName, int amount) {
        return this.getAvailableStock(slot, playerName) >= amount;
    }

    public synchronized boolean consumeStock(int slot, String playerName, int amount) {
        if (!this.enableStock || slot < 0 || slot >= 18 || this.maxStock[slot] < 0) {
            return true;
        }
        if (this.perPlayer) {
            PlayerTraderStock pStock = this.playerStock.computeIfAbsent(playerName, k -> new PlayerTraderStock());
            return pStock.consumeStock(slot, this.maxStock[slot], amount);
        }
        if (this.currentStock[slot] < 0) {
            this.currentStock[slot] = this.maxStock[slot];
        }
        if (this.currentStock[slot] >= amount) {
            int n = slot;
            this.currentStock[n] = this.currentStock[n] - amount;
            return true;
        }
        return false;
    }

    public boolean shouldReset(long currentTime) {
        if (this.resetType == EnumStockReset.NONE) {
            return false;
        }
        long elapsed = currentTime - this.lastResetTime;
        long resetInterval = this.getResetInterval();
        return elapsed >= resetInterval;
    }

    public synchronized void resetStock(long currentTime) {
        this.lastResetTime = currentTime;
        for (int i = 0; i < 18; ++i) {
            this.currentStock[i] = this.maxStock[i];
        }
        this.playerStock.clear();
    }

    public long getResetInterval() {
        switch (this.resetType) {
            case MCDAILY: {
                return 24000L;
            }
            case MCWEEKLY: {
                return 168000L;
            }
            case MCCUSTOM: {
                return this.customResetTime;
            }
            case RLDAILY: {
                return 86400000L;
            }
            case RLWEEKLY: {
                return 604800000L;
            }
            case RLCUSTOM: {
                return this.customResetTime;
            }
        }
        return Long.MAX_VALUE;
    }

    public long getTimeUntilReset(long currentTime) {
        if (this.resetType == EnumStockReset.NONE) {
            return -1L;
        }
        long elapsed = currentTime - this.lastResetTime;
        long interval = this.getResetInterval();
        return Math.max(0L, interval - elapsed);
    }

    public String getTimeUntilResetFormatted(long currentTime) {
        long remaining = this.getTimeUntilReset(currentTime);
        if (remaining < 0L) {
            return "";
        }
        if (this.resetType.isRealTime()) {
            long hours = remaining / 3600000L;
            long days = hours / 24L;
            hours %= 24L;
            if (days > 0L) {
                return days + " day" + (days != 1L ? "s" : "") + (hours > 0L ? ", " + hours + " hour" + (hours != 1L ? "s" : "") : "");
            }
            if (hours > 0L) {
                return hours + " hour" + (hours != 1L ? "s" : "");
            }
            long minutes = remaining / 60000L;
            return minutes + " minute" + (minutes != 1L ? "s" : "");
        }
        long mcHours = remaining / 1000L;
        long mcDays = mcHours / 24L;
        mcHours %= 24L;
        if (mcDays > 0L) {
            return mcDays + " MC day" + (mcDays != 1L ? "s" : "") + (mcHours > 0L ? ", " + mcHours + " MC hour" + (mcHours != 1L ? "s" : "") : "");
        }
        return mcHours + " MC hour" + (mcHours != 1L ? "s" : "");
    }

    public void setMaxStock(int slot, int amount) {
        if (slot >= 0 && slot < 18) {
            this.maxStock[slot] = amount;
            if (this.currentStock[slot] < 0 || this.currentStock[slot] > amount) {
                this.currentStock[slot] = amount;
            }
        }
    }

    public boolean validateStock() {
        boolean changed = false;
        for (int i = 0; i < 18; ++i) {
            if (this.maxStock[i] < 0) continue;
            if (this.currentStock[i] < 0) {
                this.currentStock[i] = this.maxStock[i];
                changed = true;
                continue;
            }
            if (this.currentStock[i] <= this.maxStock[i]) continue;
            this.currentStock[i] = this.maxStock[i];
            changed = true;
        }
        for (PlayerTraderStock pStock : this.playerStock.values()) {
            for (int i = 0; i < 18; ++i) {
                int available;
                if (this.maxStock[i] < 0 || (available = pStock.getStock(i, this.maxStock[i])) >= 0) continue;
                pStock.purchasedAmounts[i] = this.maxStock[i];
                changed = true;
            }
        }
        return changed;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74757_a("EnableStock", this.enableStock);
        compound.func_74757_a("PerPlayer", this.perPlayer);
        compound.func_74768_a("ResetType", this.resetType.ordinal());
        compound.func_74772_a("CustomResetTime", this.customResetTime);
        compound.func_74783_a("MaxStock", this.maxStock);
        compound.func_74783_a("CurrentStock", this.currentStock);
        compound.func_74772_a("LastResetTime", this.lastResetTime);
        NBTTagList playerList = new NBTTagList();
        for (Map.Entry<String, PlayerTraderStock> entry : this.playerStock.entrySet()) {
            NBTTagCompound playerTag = new NBTTagCompound();
            playerTag.func_74778_a("Player", entry.getKey());
            entry.getValue().writeToNBT(playerTag);
            playerList.func_74742_a((NBTBase)playerTag);
        }
        compound.func_74782_a("PlayerStock", (NBTBase)playerList);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.enableStock = compound.func_74767_n("EnableStock");
        this.perPlayer = compound.func_74767_n("PerPlayer");
        int resetOrdinal = compound.func_74762_e("ResetType");
        if (resetOrdinal >= 0 && resetOrdinal < EnumStockReset.values().length) {
            this.resetType = EnumStockReset.values()[resetOrdinal];
        }
        this.customResetTime = compound.func_74763_f("CustomResetTime");
        int[] loadedMax = compound.func_74759_k("MaxStock");
        int[] loadedCurrent = compound.func_74759_k("CurrentStock");
        if (loadedMax != null && loadedMax.length == 18) {
            this.maxStock = loadedMax;
        }
        if (loadedCurrent != null && loadedCurrent.length == 18) {
            this.currentStock = loadedCurrent;
        }
        this.lastResetTime = compound.func_74763_f("LastResetTime");
        this.playerStock.clear();
        NBTTagList playerList = compound.func_150295_c("PlayerStock", 10);
        for (int i = 0; i < playerList.func_74745_c(); ++i) {
            NBTTagCompound playerTag = playerList.func_150305_b(i);
            String playerName = playerTag.func_74779_i("Player");
            PlayerTraderStock pStock = new PlayerTraderStock();
            pStock.readFromNBT(playerTag);
            this.playerStock.put(playerName, pStock);
        }
    }

    public static class PlayerTraderStock {
        public int[] purchasedAmounts = new int[18];

        public PlayerTraderStock() {
            for (int i = 0; i < 18; ++i) {
                this.purchasedAmounts[i] = 0;
            }
        }

        public int getStock(int slot, int maxStock) {
            if (slot < 0 || slot >= 18) {
                return 0;
            }
            return Math.max(0, maxStock - this.purchasedAmounts[slot]);
        }

        public boolean consumeStock(int slot, int maxStock, int amount) {
            if (slot < 0 || slot >= 18) {
                return false;
            }
            if (this.getStock(slot, maxStock) >= amount) {
                int n = slot;
                this.purchasedAmounts[n] = this.purchasedAmounts[n] + amount;
                return true;
            }
            return false;
        }

        public void writeToNBT(NBTTagCompound compound) {
            compound.func_74783_a("Purchased", this.purchasedAmounts);
        }

        public void readFromNBT(NBTTagCompound compound) {
            int[] loaded = compound.func_74759_k("Purchased");
            if (loaded != null && loaded.length == 18) {
                this.purchasedAmounts = loaded;
            }
        }
    }
}

