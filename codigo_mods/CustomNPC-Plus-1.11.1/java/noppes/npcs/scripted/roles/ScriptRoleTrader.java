/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.scripted.roles;

import foxz.utils.Market;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.roles.IRoleTrader;
import noppes.npcs.constants.EnumStockReset;
import noppes.npcs.controllers.data.TraderStock;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleTrader;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.roles.ScriptRoleInterface;

public class ScriptRoleTrader
extends ScriptRoleInterface
implements IRoleTrader {
    private RoleTrader role;

    public ScriptRoleTrader(EntityNPCInterface npc) {
        super(npc);
        this.role = (RoleTrader)npc.roleInterface;
    }

    @Override
    public void setSellOption(int slot, IItemStack currency, IItemStack currency2, IItemStack sold) {
        if (sold == null || slot >= 18 || slot < 0) {
            return;
        }
        if (currency == null) {
            currency = currency2;
        }
        if (currency != null) {
            this.role.inventoryCurrency.items.put(slot, currency.getMCItemStack());
        } else {
            this.role.inventoryCurrency.items.remove(slot);
        }
        if (currency2 != null) {
            this.role.inventoryCurrency.items.put(slot + 18, currency2.getMCItemStack());
        } else {
            this.role.inventoryCurrency.items.remove(slot + 18);
        }
        this.role.inventorySold.items.put(slot, sold.getMCItemStack());
    }

    @Override
    public void setSellOption(int slot, IItemStack currency, IItemStack sold) {
        this.setSellOption(slot, currency, null, sold);
    }

    @Override
    public IItemStack getSellOption(int slot) {
        if (slot >= 18 || slot < 0) {
            return null;
        }
        if (this.role.inventorySold.items.get(slot) == null) {
            return null;
        }
        return NpcAPI.Instance().getIItemStack(this.role.inventorySold.items.get(slot));
    }

    @Override
    public IItemStack[] getCurrency(int slot) {
        if (slot >= 18 || slot < 0) {
            return null;
        }
        IItemStack[] currency = new IItemStack[2];
        if (this.role.inventoryCurrency.items.get(slot) != null) {
            currency[0] = NpcAPI.Instance().getIItemStack(this.role.inventoryCurrency.items.get(slot));
        }
        if (this.role.inventoryCurrency.items.get(slot + 18) != null) {
            currency[1] = NpcAPI.Instance().getIItemStack(this.role.inventoryCurrency.items.get(slot + 18));
        }
        return currency;
    }

    @Override
    public void removeSellOption(int slot) {
        if (slot >= 18 || slot < 0) {
            return;
        }
        this.role.inventoryCurrency.items.remove(slot);
        this.role.inventoryCurrency.items.remove(slot + 18);
        this.role.inventorySold.items.remove(slot);
    }

    @Override
    public void setMarket(String name) {
        this.role.marketName = name;
        Market.getMarket(this.role, name);
    }

    @Override
    public String getMarket() {
        return this.role.marketName;
    }

    @Override
    public int getPurchaseNum(int slot) {
        if (slot >= 18 || slot < 0) {
            return -1;
        }
        return this.role.purchases[slot];
    }

    @Override
    public int getPurchaseNum(int slot, IPlayer player) {
        if (slot >= 18 || slot < 0) {
            return -1;
        }
        return this.role.getArrayByName(player.getDisplayName(), this.role.playerPurchases)[slot];
    }

    @Override
    public void resetPurchaseNum() {
        for (int i = 0; i < this.role.purchases.length; ++i) {
            this.role.purchases[i] = 0;
        }
    }

    @Override
    public void resetPurchaseNum(int slot) {
        if (slot >= 18 || slot < 0) {
            return;
        }
        this.role.purchases[slot] = 0;
    }

    @Override
    public void resetPurchaseNum(int slot, IPlayer player) {
        if (slot >= 18 || slot < 0) {
            return;
        }
        this.role.getArrayByName((String)player.getDisplayName(), this.role.playerPurchases)[slot] = 0;
    }

    @Override
    public boolean isSlotEnabled(int slot) {
        if (slot >= 18 || slot < 0) {
            return false;
        }
        return this.role.disableSlot[slot] <= 0;
    }

    @Override
    public boolean isSlotEnabled(int slot, IPlayer player) {
        if (slot >= 18 || slot < 0) {
            return false;
        }
        return this.role.isSlotEnabled(slot, player.getDisplayName());
    }

    @Override
    public void disableSlot(int slot) {
        if (slot >= 18 || slot < 0) {
            return;
        }
        this.role.disableSlot[slot] = 1;
    }

    @Override
    public void disableSlot(int slot, IPlayer player) {
        if (slot >= 18 || slot < 0) {
            return;
        }
        this.role.getArrayByName((String)player.getDisplayName(), this.role.playerDisableSlot)[slot] = 1;
    }

    @Override
    public void enableSlot(int slot) {
        if (slot >= 18 || slot < 0) {
            return;
        }
        this.role.disableSlot[slot] = 0;
    }

    @Override
    public void enableSlot(int slot, IPlayer player) {
        if (slot >= 18 || slot < 0) {
            return;
        }
        this.role.getArrayByName((String)player.getDisplayName(), this.role.playerDisableSlot)[slot] = 0;
    }

    @Override
    public boolean isStockEnabled() {
        return this.role.stock.enableStock;
    }

    @Override
    public void setStockEnabled(boolean enabled) {
        this.role.stock.enableStock = enabled;
    }

    @Override
    public boolean isPerPlayerStock() {
        return this.role.stock.perPlayer;
    }

    @Override
    public void setPerPlayerStock(boolean perPlayer) {
        this.role.stock.perPlayer = perPlayer;
    }

    @Override
    public int getStockResetType() {
        return this.role.stock.resetType.ordinal();
    }

    @Override
    public void setStockResetType(int type) {
        EnumStockReset[] values = EnumStockReset.values();
        if (type >= 0 && type < values.length) {
            this.role.stock.resetType = values[type];
        }
    }

    @Override
    public long getCustomResetTime() {
        return this.role.stock.customResetTime;
    }

    @Override
    public void setCustomResetTime(long time) {
        this.role.stock.customResetTime = Math.max(0L, time);
    }

    @Override
    public int getMaxStock(int slot) {
        if (slot < 0 || slot >= 18) {
            return -1;
        }
        return this.role.stock.maxStock[slot];
    }

    @Override
    public void setMaxStock(int slot, int amount) {
        if (slot >= 0 && slot < 18) {
            this.role.stock.setMaxStock(slot, amount);
        }
    }

    @Override
    public int getAvailableStock(int slot) {
        if (slot < 0 || slot >= 18) {
            return 0;
        }
        return this.role.stock.getAvailableStock(slot, "");
    }

    @Override
    public int getAvailableStock(int slot, IPlayer player) {
        if (slot < 0 || slot >= 18) {
            return 0;
        }
        return this.role.stock.getAvailableStock(slot, player.getDisplayName());
    }

    @Override
    public void resetStock() {
        long currentTime = this.role.stock.resetType.isRealTime() ? System.currentTimeMillis() : (this.npc.field_70170_p != null ? this.npc.field_70170_p.func_82737_E() : 0L);
        this.role.stock.resetStock(currentTime);
    }

    @Override
    public void resetCooldown() {
        long currentTime;
        this.role.stock.lastResetTime = currentTime = this.role.stock.resetType.isRealTime() ? System.currentTimeMillis() : (this.npc.field_70170_p != null ? this.npc.field_70170_p.func_82737_E() : 0L);
    }

    @Override
    public int getCurrentStock(int slot) {
        if (slot < 0 || slot >= 18) {
            return -1;
        }
        return this.role.stock.currentStock[slot];
    }

    @Override
    public void setCurrentStock(int slot, int amount) {
        if (slot >= 0 && slot < 18) {
            this.role.stock.currentStock[slot] = amount;
        }
    }

    @Override
    public int getPlayerPurchased(int slot, IPlayer player) {
        if (slot < 0 || slot >= 18) {
            return 0;
        }
        TraderStock.PlayerTraderStock pStock = this.role.stock.playerStock.get(player.getDisplayName());
        if (pStock == null) {
            return 0;
        }
        return pStock.purchasedAmounts[slot];
    }

    @Override
    public void setPlayerPurchased(int slot, IPlayer player, int amount) {
        if (slot < 0 || slot >= 18) {
            return;
        }
        TraderStock.PlayerTraderStock pStock = this.role.stock.playerStock.computeIfAbsent(player.getDisplayName(), k -> new TraderStock.PlayerTraderStock());
        pStock.purchasedAmounts[slot] = Math.max(0, amount);
    }

    @Override
    public long getLastResetTime() {
        return this.role.stock.lastResetTime;
    }

    @Override
    public long getTimeUntilReset() {
        long currentTime = this.role.stock.resetType.isRealTime() ? System.currentTimeMillis() : (this.npc.field_70170_p != null ? this.npc.field_70170_p.func_82737_E() : 0L);
        return this.role.stock.getTimeUntilReset(currentTime);
    }

    @Override
    public long getCurrencyCost(int slot) {
        return this.role.getCurrencyCost(slot);
    }

    @Override
    public void setCurrencyCost(int slot, long cost) {
        this.role.setCurrencyCost(slot, cost);
    }

    @Override
    public boolean hasCurrencyCost(int slot) {
        return this.role.hasCurrencyCost(slot);
    }

    @Override
    public int getType() {
        return 1;
    }
}

