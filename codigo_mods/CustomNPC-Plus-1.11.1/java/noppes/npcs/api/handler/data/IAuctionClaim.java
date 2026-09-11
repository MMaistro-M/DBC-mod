/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.item.IItemStack;

public interface IAuctionClaim {
    public String getId();

    public String getPlayerUUID();

    public String getPlayerName();

    public String getListingId();

    public int getType();

    public boolean isItemClaim();

    public boolean isCurrencyClaim();

    public boolean isRefundClaim();

    public IItemStack getItem();

    public String getItemName();

    public long getCurrency();

    public String getOtherPlayerName();

    public long getCreatedTime();

    public boolean isClaimed();

    public boolean isReturnedItem();

    public long getDaysUntilExpiration();

    public boolean isExpired();
}

