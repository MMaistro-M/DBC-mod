/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.roles;

import foxz.utils.Market;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.NpcMiscInventory;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumStockReset;
import noppes.npcs.controllers.MarketRegistry;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.TraderStock;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleInterface;

public class RoleTrader
extends RoleInterface {
    public String marketName = "";
    public NpcMiscInventory inventoryCurrency;
    public NpcMiscInventory inventorySold;
    public boolean ignoreDamage = false;
    public boolean ignoreNBT = false;
    public boolean recordHistory = false;
    public int[] purchases;
    public int[] disableSlot;
    public HashMap<String, int[]> playerPurchases;
    public HashMap<String, int[]> playerDisableSlot;
    public TraderStock stock = new TraderStock();
    public long[] currencyCost = new long[18];
    private final Set<EntityPlayerMP> localViewers = new HashSet<EntityPlayerMP>();

    public RoleTrader(EntityNPCInterface npc) {
        super(npc);
        this.inventoryCurrency = new NpcMiscInventory(36);
        this.inventorySold = new NpcMiscInventory(18);
        this.purchases = new int[18];
        this.disableSlot = new int[18];
        this.playerPurchases = new HashMap();
        this.playerDisableSlot = new HashMap();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74778_a("TraderMarket", this.marketName);
        this.writeNBT(nbttagcompound);
        return nbttagcompound;
    }

    public NBTTagCompound writeNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74782_a("TraderCurrency", (NBTBase)this.inventoryCurrency.getToNBT());
        nbttagcompound.func_74782_a("TraderSold", (NBTBase)this.inventorySold.getToNBT());
        nbttagcompound.func_74757_a("TraderIgnoreDamage", this.ignoreDamage);
        nbttagcompound.func_74757_a("TraderIgnoreNBT", this.ignoreNBT);
        nbttagcompound.func_74757_a("RecordHistory", this.recordHistory);
        nbttagcompound.func_74783_a("DisableSlot", this.disableSlot);
        nbttagcompound.func_74782_a("PlayerDisableSlot", (NBTBase)NBTTags.nbtStringIntegerArrayMap(this.playerDisableSlot));
        if (this.recordHistory) {
            nbttagcompound.func_74783_a("Purchases", this.purchases);
            nbttagcompound.func_74782_a("PlayerPurchases", (NBTBase)NBTTags.nbtStringIntegerArrayMap(this.playerPurchases));
        }
        nbttagcompound.func_74782_a("Stock", (NBTBase)this.stock.writeToNBT(new NBTTagCompound()));
        NBTTagList currencyList = new NBTTagList();
        for (long cost : this.currencyCost) {
            NBTTagCompound costTag = new NBTTagCompound();
            costTag.func_74772_a("Cost", cost);
            currencyList.func_74742_a((NBTBase)costTag);
        }
        nbttagcompound.func_74782_a("CurrencyCost", (NBTBase)currencyList);
        return nbttagcompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        String oldMarket = this.marketName;
        this.marketName = nbttagcompound.func_74779_i("TraderMarket");
        this.readNBT(nbttagcompound);
        MarketRegistry.updateTraderMarket(oldMarket, this.marketName, this);
        try {
            Market.getMarket(this, this.marketName);
        }
        catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readNBT(NBTTagCompound nbttagcompound) {
        int i;
        this.inventoryCurrency.setFromNBT(nbttagcompound.func_74775_l("TraderCurrency"));
        this.inventorySold.setFromNBT(nbttagcompound.func_74775_l("TraderSold"));
        this.ignoreDamage = nbttagcompound.func_74767_n("TraderIgnoreDamage");
        this.ignoreNBT = nbttagcompound.func_74767_n("TraderIgnoreNBT");
        this.recordHistory = nbttagcompound.func_74767_n("RecordHistory");
        this.disableSlot = nbttagcompound.func_74759_k("DisableSlot");
        this.playerDisableSlot = NBTTags.getStringIntegerArrayMap(nbttagcompound.func_150295_c("PlayerDisableSlot", 10), 18);
        if (this.recordHistory) {
            this.purchases = nbttagcompound.func_74759_k("Purchases");
            this.playerPurchases = NBTTags.getStringIntegerArrayMap(nbttagcompound.func_150295_c("PlayerPurchases", 10), 18);
        }
        if (this.purchases == null || this.purchases.length != 18) {
            this.purchases = new int[18];
            for (i = 0; i < this.purchases.length; ++i) {
                this.purchases[i] = 0;
            }
        }
        if (this.disableSlot == null || this.disableSlot.length != 18) {
            this.disableSlot = new int[18];
            for (i = 0; i < this.disableSlot.length; ++i) {
                this.disableSlot[i] = 0;
            }
        }
        if (nbttagcompound.func_74764_b("Stock")) {
            this.stock.readFromNBT(nbttagcompound.func_74775_l("Stock"));
        }
        if (nbttagcompound.func_74764_b("CurrencyCost")) {
            NBTTagList currencyList = nbttagcompound.func_150295_c("CurrencyCost", 10);
            for (int i2 = 0; i2 < currencyList.func_74745_c() && i2 < 18; ++i2) {
                this.currencyCost[i2] = currencyList.func_150305_b(i2).func_74763_f("Cost");
            }
        }
    }

    @Override
    public void interact(EntityPlayer player) {
        this.npc.say(player, this.npc.advanced.getInteractLine());
        try {
            Market.getMarket(this, this.marketName);
        }
        catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        if (this.stock.enableStock) {
            long currentTime = this.stock.resetType.isRealTime() ? System.currentTimeMillis() : player.field_70170_p.func_82737_E();
            boolean needsSave = false;
            if (this.stock.shouldReset(currentTime)) {
                this.stock.resetStock(currentTime);
                needsSave = true;
            }
            if (this.stock.validateStock()) {
                needsSave = true;
            }
            if (needsSave && !this.marketName.isEmpty()) {
                Market.save(this, this.marketName);
            }
        }
        NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerTrader, this.npc);
    }

    public boolean hasCurrency(ItemStack itemstack) {
        if (itemstack == null) {
            return false;
        }
        for (ItemStack item : this.inventoryCurrency.items.values()) {
            if (item == null || !NoppesUtilPlayer.compareItems(item, itemstack, this.ignoreDamage, this.ignoreNBT)) continue;
            return true;
        }
        return false;
    }

    public void addPurchase(int slot, String playerName) {
        if (slot >= 18 || slot < 0) {
            return;
        }
        int n = slot;
        this.purchases[n] = this.purchases[n] + 1;
        int[] nArray = this.getArrayByName(playerName, this.playerPurchases);
        int n2 = slot;
        nArray[n2] = nArray[n2] + 1;
        if (this.recordHistory) {
            Market.save(this, this.marketName);
        }
    }

    public boolean isSlotEnabled(int slot, String playerName) {
        if (slot >= 18 || slot < 0) {
            return false;
        }
        if (this.disableSlot[slot] > 0) {
            return false;
        }
        return this.getArrayByName(playerName, this.playerDisableSlot)[slot] <= 0;
    }

    public int[] getArrayByName(String name, HashMap<String, int[]> map) {
        map.computeIfAbsent(name, k -> new int[18]);
        return map.get(name);
    }

    public boolean hasStock(int slot, String playerName, int amount) {
        if (!this.stock.enableStock) {
            return true;
        }
        return this.stock.hasStock(slot, playerName, amount);
    }

    public int getAvailableStock(int slot, String playerName) {
        if (!this.stock.enableStock) {
            return Integer.MAX_VALUE;
        }
        return this.stock.getAvailableStock(slot, playerName);
    }

    public boolean consumeStock(int slot, String playerName, int amount) {
        if (!this.stock.enableStock) {
            return true;
        }
        boolean consumed = this.stock.consumeStock(slot, playerName, amount);
        if (consumed) {
            if (!this.marketName.isEmpty()) {
                Market.save(this, this.marketName);
            }
            if (!this.stock.perPlayer) {
                if (!this.marketName.isEmpty()) {
                    MarketRegistry.syncMarket(this.marketName);
                } else {
                    this.syncLocalViewers();
                }
            }
        }
        return consumed;
    }

    public String getTimeUntilResetFormatted() {
        if (!this.stock.enableStock || this.stock.resetType == EnumStockReset.NONE) {
            return "";
        }
        long currentTime = this.stock.resetType.isRealTime() ? System.currentTimeMillis() : (this.npc != null && this.npc.field_70170_p != null ? this.npc.field_70170_p.func_82737_E() : 0L);
        return this.stock.getTimeUntilResetFormatted(currentTime);
    }

    public long getResetTimeRemainingMillis() {
        long remaining;
        if (!this.stock.enableStock || this.stock.resetType == EnumStockReset.NONE) {
            return -1L;
        }
        if (this.stock.resetType.isRealTime()) {
            long currentTime = System.currentTimeMillis();
            remaining = this.stock.getTimeUntilReset(currentTime);
        } else {
            long currentTime = this.npc != null && this.npc.field_70170_p != null ? this.npc.field_70170_p.func_82737_E() : 0L;
            long remainingTicks = this.stock.getTimeUntilReset(currentTime);
            remaining = remainingTicks * 50L;
        }
        return remaining;
    }

    public void resetCooldown() {
        this.stock.lastResetTime = 0L;
    }

    public long getCurrencyCost(int slot) {
        if (slot < 0 || slot >= 18) {
            return 0L;
        }
        return this.currencyCost[slot];
    }

    public void setCurrencyCost(int slot, long cost) {
        if (slot >= 0 && slot < 18) {
            this.currencyCost[slot] = Math.max(0L, cost);
        }
    }

    public boolean hasCurrencyCost(int slot) {
        return slot >= 0 && slot < 18 && this.currencyCost[slot] > 0L;
    }

    public void registerViewer(EntityPlayerMP player) {
        if (player == null) {
            return;
        }
        if (!this.marketName.isEmpty()) {
            MarketRegistry.registerViewer(this.marketName, player);
        } else {
            this.localViewers.add(player);
        }
    }

    public void unregisterViewer(EntityPlayerMP player) {
        if (player == null) {
            return;
        }
        if (!this.marketName.isEmpty()) {
            MarketRegistry.unregisterViewer(this.marketName, player);
        } else {
            this.localViewers.remove(player);
        }
    }

    public void syncAllViewers() {
        if (!this.marketName.isEmpty()) {
            MarketRegistry.syncMarketViewers(this.marketName);
        } else {
            this.syncLocalViewers();
        }
    }

    private void syncLocalViewers() {
        this.localViewers.removeIf(player -> player.field_71135_a == null);
        for (EntityPlayerMP viewer : this.localViewers) {
            this.syncToPlayer(viewer);
        }
    }

    public void onUnload() {
        if (!this.marketName.isEmpty()) {
            MarketRegistry.unregisterTrader(this.marketName, this);
        }
        this.localViewers.clear();
    }

    public void syncToPlayer(EntityPlayerMP player) {
        int i;
        if (player == null || player.field_71135_a == null) {
            return;
        }
        NBTTagCompound compound = new NBTTagCompound();
        PlayerData data = PlayerData.get((EntityPlayer)player);
        compound.func_74772_a("Balance", data.tradeData.getBalance());
        compound.func_74757_a("StockEnabled", this.stock.enableStock);
        compound.func_74778_a("ResetTime", this.getTimeUntilResetFormatted());
        String playerName = player.func_70005_c_();
        int[] stockArr = new int[18];
        for (i = 0; i < 18; ++i) {
            stockArr[i] = this.getAvailableStock(i, playerName);
        }
        compound.func_74783_a("Stock", stockArr);
        for (i = 0; i < 18; ++i) {
            compound.func_74772_a("Cost" + i, this.getCurrencyCost(i));
        }
        GuiDataPacket.sendGuiData(player, compound);
    }

    @Override
    public void delete() {
        this.onUnload();
        super.delete();
    }
}

