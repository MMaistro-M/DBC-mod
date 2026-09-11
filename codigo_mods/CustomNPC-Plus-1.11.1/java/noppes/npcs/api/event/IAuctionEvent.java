/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.event.IPlayerEvent;
import noppes.npcs.api.handler.data.IAuctionClaim;
import noppes.npcs.api.handler.data.IAuctionListing;
import noppes.npcs.api.item.IItemStack;

public interface IAuctionEvent
extends IPlayerEvent {

    @Cancelable
    public static interface ClaimEvent
    extends IAuctionEvent {
        public IAuctionClaim getClaim();
    }

    @Cancelable
    public static interface CancelEvent
    extends IAuctionEvent {
        public IAuctionListing getListing();

        public boolean isAdmin();
    }

    @Cancelable
    public static interface BuyoutEvent
    extends IAuctionEvent {
        public IAuctionListing getListing();
    }

    @Cancelable
    public static interface BidEvent
    extends IAuctionEvent {
        public IAuctionListing getListing();

        public long getBidAmount();
    }

    @Cancelable
    public static interface CreateEvent
    extends IAuctionEvent {
        public IItemStack getItem();

        public long getStartingPrice();

        public long getBuyoutPrice();
    }
}

