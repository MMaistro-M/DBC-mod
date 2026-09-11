/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers;

import foxz.utils.Market;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.roles.RoleTrader;

public class MarketRegistry {
    private static final Map<String, Set<RoleTrader>> marketTraders = new ConcurrentHashMap<String, Set<RoleTrader>>();
    private static final Map<String, Set<EntityPlayerMP>> marketViewers = new ConcurrentHashMap<String, Set<EntityPlayerMP>>();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void registerTrader(String marketName, RoleTrader trader) {
        Set traders;
        if (marketName == null || marketName.isEmpty() || trader == null) {
            return;
        }
        Set set = traders = marketTraders.computeIfAbsent(marketName, k -> Collections.newSetFromMap(new WeakHashMap()));
        synchronized (set) {
            traders.add(trader);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void unregisterTrader(String marketName, RoleTrader trader) {
        if (marketName == null || marketName.isEmpty() || trader == null) {
            return;
        }
        Set<RoleTrader> traders = marketTraders.get(marketName);
        if (traders != null) {
            Set<RoleTrader> set = traders;
            synchronized (set) {
                traders.remove(trader);
                if (traders.isEmpty()) {
                    marketTraders.remove(marketName);
                }
            }
        }
    }

    public static void updateTraderMarket(String oldMarket, String newMarket, RoleTrader trader) {
        if (trader == null) {
            return;
        }
        if (oldMarket != null && !oldMarket.isEmpty()) {
            MarketRegistry.unregisterTrader(oldMarket, trader);
        }
        if (newMarket != null && !newMarket.isEmpty()) {
            MarketRegistry.registerTrader(newMarket, trader);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void registerViewer(String marketName, EntityPlayerMP player) {
        Set viewers;
        if (marketName == null || marketName.isEmpty() || player == null) {
            return;
        }
        Set set = viewers = marketViewers.computeIfAbsent(marketName, k -> Collections.newSetFromMap(new WeakHashMap()));
        synchronized (set) {
            viewers.add(player);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void unregisterViewer(String marketName, EntityPlayerMP player) {
        if (marketName == null || marketName.isEmpty() || player == null) {
            return;
        }
        Set<EntityPlayerMP> viewers = marketViewers.get(marketName);
        if (viewers != null) {
            Set<EntityPlayerMP> set = viewers;
            synchronized (set) {
                viewers.remove(player);
                if (viewers.isEmpty()) {
                    marketViewers.remove(marketName);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void syncMarket(String marketName) {
        if (marketName == null || marketName.isEmpty()) {
            return;
        }
        NBTTagCompound marketData = Market.getMarketCache(marketName);
        if (marketData == null) {
            return;
        }
        Set<RoleTrader> traders = marketTraders.get(marketName);
        if (traders != null) {
            NBTTagCompound stockData = marketData.func_74775_l("Stock");
            Set<RoleTrader> set = traders;
            synchronized (set) {
                traders.removeIf(t -> t == null || t.npc == null);
                for (RoleTrader trader : traders) {
                    if (stockData == null || !stockData.func_74764_b("EnableStock")) continue;
                    trader.stock.readFromNBT(stockData);
                }
            }
        }
        MarketRegistry.syncMarketViewers(marketName);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void syncMarketViewers(String marketName) {
        if (marketName == null || marketName.isEmpty()) {
            return;
        }
        Set<EntityPlayerMP> viewers = marketViewers.get(marketName);
        if (viewers == null || viewers.isEmpty()) {
            return;
        }
        Set<RoleTrader> traders = marketTraders.get(marketName);
        if (traders == null || traders.isEmpty()) {
            return;
        }
        RoleTrader syncSource = null;
        Set<RoleTrader> set = traders;
        synchronized (set) {
            for (RoleTrader trader : traders) {
                if (trader == null || trader.npc == null) continue;
                syncSource = trader;
                break;
            }
        }
        if (syncSource == null) {
            return;
        }
        set = viewers;
        synchronized (set) {
            viewers.removeIf(p -> p == null || p.field_71135_a == null);
            for (EntityPlayerMP viewer : viewers) {
                syncSource.syncToPlayer(viewer);
            }
        }
    }

    public static int getTraderCount(String marketName) {
        Set<RoleTrader> traders = marketTraders.get(marketName);
        return traders != null ? traders.size() : 0;
    }

    public static int getViewerCount(String marketName) {
        Set<EntityPlayerMP> viewers = marketViewers.get(marketName);
        return viewers != null ? viewers.size() : 0;
    }

    public static void clear() {
        marketTraders.clear();
        marketViewers.clear();
    }
}

