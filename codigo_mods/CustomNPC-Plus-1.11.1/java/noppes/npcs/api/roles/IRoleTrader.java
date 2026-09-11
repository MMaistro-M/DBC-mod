/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.roles;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.roles.IRole;

public interface IRoleTrader
extends IRole {
    public void setSellOption(int var1, IItemStack var2, IItemStack var3, IItemStack var4);

    public void setSellOption(int var1, IItemStack var2, IItemStack var3);

    public IItemStack getSellOption(int var1);

    public IItemStack[] getCurrency(int var1);

    public void removeSellOption(int var1);

    public void setMarket(String var1);

    public String getMarket();

    public int getPurchaseNum(int var1);

    public int getPurchaseNum(int var1, IPlayer var2);

    public void resetPurchaseNum();

    public void resetPurchaseNum(int var1);

    public void resetPurchaseNum(int var1, IPlayer var2);

    public boolean isSlotEnabled(int var1);

    public boolean isSlotEnabled(int var1, IPlayer var2);

    public void disableSlot(int var1);

    public void disableSlot(int var1, IPlayer var2);

    public void enableSlot(int var1);

    public void enableSlot(int var1, IPlayer var2);

    public boolean isStockEnabled();

    public void setStockEnabled(boolean var1);

    public boolean isPerPlayerStock();

    public void setPerPlayerStock(boolean var1);

    public int getStockResetType();

    public void setStockResetType(int var1);

    public long getCustomResetTime();

    public void setCustomResetTime(long var1);

    public int getMaxStock(int var1);

    public void setMaxStock(int var1, int var2);

    public int getAvailableStock(int var1);

    public int getAvailableStock(int var1, IPlayer var2);

    public void resetStock();

    public void resetCooldown();

    public int getCurrentStock(int var1);

    public void setCurrentStock(int var1, int var2);

    public int getPlayerPurchased(int var1, IPlayer var2);

    public void setPlayerPurchased(int var1, IPlayer var2, int var3);

    public long getLastResetTime();

    public long getTimeUntilReset();

    public long getCurrencyCost(int var1);

    public void setCurrencyCost(int var1, long var2);

    public boolean hasCurrencyCost(int var1);
}

