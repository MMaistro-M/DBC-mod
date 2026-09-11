/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IAuctionListing;
import noppes.npcs.api.item.IItemStack;

public interface IAuctionHandler {
    public boolean isEnabled();

    public IAuctionListing[] getActiveListings();

    public IAuctionListing getListing(String var1);

    public IAuctionListing[] getListingsBySeller(String var1);

    public IAuctionListing[] getListingsByBidder(String var1);

    public int getActiveListingCount();

    public IAuctionListing createListing(IPlayer<?> var1, IItemStack var2, long var3, long var5);

    public String placeBid(String var1, IPlayer<?> var2, long var3);

    public String buyout(String var1, IPlayer<?> var2);

    public String cancelListing(String var1, IPlayer<?> var2, boolean var3);

    public long getListingFee();

    public double getSalesTaxPercent();

    public double getMinBidIncrementPercent();

    public int getAuctionDurationHours();

    public int getSnipeProtectionMinutes();

    public String getCurrencyName();

    public long getMinimumListingPrice();

    public IAuctionListing[] searchListings(String var1);

    public void save();
}

