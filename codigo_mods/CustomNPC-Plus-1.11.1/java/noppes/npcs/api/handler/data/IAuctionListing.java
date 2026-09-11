/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.item.IItemStack;

public interface IAuctionListing {
    public String getId();

    public String getSellerUUID();

    public String getSellerName();

    public IItemStack getItem();

    public long getStartingPrice();

    public long getBuyoutPrice();

    public boolean hasBuyout();

    public long getCurrentBid();

    public String getHighBidderUUID();

    public String getHighBidderName();

    public boolean hasBids();

    public int getBidCount();

    public long getCreatedTime();

    public long getEndTime();

    public long getRemainingTime();

    public boolean isExpired();

    public boolean isActive();

    public int getStatus();

    public long getMinimumBid();

    public boolean isSeller(String var1);

    public boolean isHighBidder(String var1);

    public String getRemainingTimeFormatted();
}

