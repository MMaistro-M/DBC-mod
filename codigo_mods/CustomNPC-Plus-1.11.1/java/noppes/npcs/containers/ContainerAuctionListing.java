/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import noppes.npcs.containers.ContainerAuction;
import noppes.npcs.containers.InventoryAuctionDisplay;
import noppes.npcs.containers.SlotAuctionDisplay;
import noppes.npcs.controllers.AuctionController;
import noppes.npcs.controllers.data.AuctionFilter;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.entity.EntityNPCInterface;

public class ContainerAuctionListing
extends ContainerAuction {
    public static final int LISTING_START_X = 57;
    public static final int LISTING_START_Y = 47;
    public static final int LISTING_COLS = 9;
    public static final int LISTING_ROWS = 5;
    public static final int LISTING_SLOT_COUNT = 45;
    public final InventoryAuctionDisplay displayInventory;
    private int currentPage = 0;
    private AuctionFilter filter = new AuctionFilter();

    public ContainerAuctionListing(EntityNPCInterface npc, EntityPlayer player) {
        super(npc, player);
        this.displayInventory = new InventoryAuctionDisplay(45);
        for (int row = 0; row < 5; ++row) {
            for (int col = 0; col < 9; ++col) {
                int slotIndex = col + row * 9;
                int x = 57 + col * 18;
                int y = 47 + row * 18;
                this.func_75146_a(new SlotAuctionDisplay(this.displayInventory, slotIndex, x, y));
            }
        }
        this.refreshListings();
    }

    public void refreshListings() {
        if (AuctionController.Instance == null) {
            return;
        }
        List<AuctionListing> listings = AuctionController.Instance.getActiveListings(this.filter, this.currentPage, 45);
        this.displayInventory.clear();
        for (int i = 0; i < listings.size() && i < 45; ++i) {
            this.displayInventory.setListing(i, listings.get(i));
        }
    }

    public AuctionListing getListingAt(int slotIndex) {
        return this.displayInventory.getListing(slotIndex);
    }

    public AuctionListing getListingForItem(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        for (int i = 0; i < 45; ++i) {
            AuctionListing listing = this.displayInventory.getListing(i);
            if (listing == null || listing.item == null || !ItemStack.func_77989_b((ItemStack)listing.item, (ItemStack)stack)) continue;
            return listing;
        }
        return null;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setPage(int page) {
        this.currentPage = Math.max(0, page);
        this.refreshListings();
    }

    public void nextPage() {
        this.setPage(this.currentPage + 1);
    }

    public void prevPage() {
        if (this.currentPage > 0) {
            this.setPage(this.currentPage - 1);
        }
    }

    public int getTotalPages() {
        if (AuctionController.Instance == null) {
            return 1;
        }
        int totalListings = AuctionController.Instance.getTotalActiveListings(this.filter);
        return Math.max(1, (int)Math.ceil((double)totalListings / 45.0));
    }

    public int getTotalListings() {
        if (AuctionController.Instance == null) {
            return 0;
        }
        return AuctionController.Instance.getTotalActiveListings(this.filter);
    }

    public AuctionFilter getFilter() {
        return this.filter;
    }

    public void setFilter(AuctionFilter filter) {
        this.filter = filter;
        this.currentPage = 0;
        this.refreshListings();
    }

    public boolean isDisplaySlot(int slotIndex) {
        int start = this.getDisplaySlotStart();
        return slotIndex >= start && slotIndex < start + 45;
    }

    public int getDisplaySlotStart() {
        return 36;
    }
}

