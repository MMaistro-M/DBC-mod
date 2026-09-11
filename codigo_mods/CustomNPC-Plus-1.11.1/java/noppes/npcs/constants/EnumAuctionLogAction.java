/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.constants;

import noppes.npcs.config.ConfigMarket;

public enum EnumAuctionLogAction {
    CREATED,
    BID,
    BUYOUT,
    SOLD,
    EXPIRED,
    CANCELLED,
    CLAIMED,
    CLAIM_EXPIRED;


    public boolean shouldLog() {
        if (!ConfigMarket.AuctionLoggingEnabled) {
            return false;
        }
        switch (this) {
            case CREATED: {
                return ConfigMarket.LogAuctionCreated;
            }
            case BID: {
                return ConfigMarket.LogAuctionBid;
            }
            case BUYOUT: {
                return ConfigMarket.LogAuctionBuyout;
            }
            case SOLD: {
                return ConfigMarket.LogAuctionSold;
            }
            case EXPIRED: {
                return ConfigMarket.LogAuctionExpired;
            }
            case CANCELLED: {
                return ConfigMarket.LogAuctionCancelled;
            }
            case CLAIMED: 
            case CLAIM_EXPIRED: {
                return ConfigMarket.LogAuctionClaimed;
            }
        }
        return false;
    }
}

