/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.constants.EnumClaimType;
import noppes.npcs.constants.EnumTradeSlotType;
import noppes.npcs.containers.ContainerAuction;
import noppes.npcs.containers.InventoryAuctionDisplay;
import noppes.npcs.containers.SlotAuctionDisplay;
import noppes.npcs.controllers.AuctionController;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.entity.EntityNPCInterface;

public class ContainerAuctionTrades
extends ContainerAuction {
    public static final int GRID_X = 57;
    public static final int GRID_Y = 47;
    public static final int COLS = 9;
    public static final int ROWS = 5;
    public static final int SLOT_COUNT = 45;
    public final InventoryAuctionDisplay displayInventory;
    private List<AuctionListing> activeListings = new ArrayList<AuctionListing>();
    private List<AuctionListing> activeBids = new ArrayList<AuctionListing>();
    private List<AuctionClaim> claims = new ArrayList<AuctionClaim>();
    private int hiddenSlot = -1;
    private int maxTradeSlots = 8;
    private EnumTradeSlotType[] slotTypes = new EnumTradeSlotType[45];
    private int[] slotDataIndex = new int[45];

    public ContainerAuctionTrades(EntityNPCInterface npc, EntityPlayer player) {
        super(npc, player);
        this.displayInventory = new InventoryAuctionDisplay(45);
        for (int row = 0; row < 5; ++row) {
            for (int col = 0; col < 9; ++col) {
                int idx = col + row * 9;
                int x = 57 + col * 18;
                int y = 47 + row * 18;
                this.func_75146_a(new SlotAuctionDisplay(this.displayInventory, idx, x, y));
            }
        }
        this.refreshData();
    }

    public void refreshData() {
        this.clearData();
        if (AuctionController.Instance == null) {
            return;
        }
        List<AuctionListing> newListings = AuctionController.Instance.getPlayerActiveListings(this.player.func_110124_au());
        List<AuctionListing> newBids = AuctionController.Instance.getPlayerActiveBids(this.player.func_110124_au());
        List<AuctionClaim> newClaims = AuctionController.Instance.getPlayerClaims(this.player.func_110124_au());
        this.populateDisplay(newListings, newBids, newClaims);
    }

    public void setTradesData(NBTTagCompound compound) {
        int i;
        this.clearData();
        if (compound.func_74764_b("MaxTradeSlots")) {
            this.maxTradeSlots = compound.func_74762_e("MaxTradeSlots");
        }
        ArrayList<AuctionListing> newListings = new ArrayList<AuctionListing>();
        ArrayList<AuctionListing> newBids = new ArrayList<AuctionListing>();
        ArrayList<AuctionClaim> newClaims = new ArrayList<AuctionClaim>();
        if (compound.func_74764_b("ActiveListings")) {
            NBTTagList listingsNBT = compound.func_150295_c("ActiveListings", 10);
            for (i = 0; i < listingsNBT.func_74745_c(); ++i) {
                newListings.add(AuctionListing.fromNBT(listingsNBT.func_150305_b(i)));
            }
        }
        if (compound.func_74764_b("ActiveBids")) {
            NBTTagList bidsNBT = compound.func_150295_c("ActiveBids", 10);
            for (i = 0; i < bidsNBT.func_74745_c(); ++i) {
                newBids.add(AuctionListing.fromNBT(bidsNBT.func_150305_b(i)));
            }
        }
        if (compound.func_74764_b("Claims")) {
            NBTTagList claimsNBT = compound.func_150295_c("Claims", 10);
            for (i = 0; i < claimsNBT.func_74745_c(); ++i) {
                newClaims.add(AuctionClaim.fromNBT(claimsNBT.func_150305_b(i)));
            }
        }
        this.populateDisplay(newListings, newBids, newClaims);
    }

    private void clearData() {
        this.activeListings.clear();
        this.activeBids.clear();
        this.claims.clear();
        this.displayInventory.clear();
        Arrays.fill((Object[])this.slotTypes, (Object)EnumTradeSlotType.EMPTY);
        for (int i = 0; i < 45; ++i) {
            this.slotDataIndex[i] = -1;
        }
    }

    /*
     * WARNING - void declaration
     */
    private void populateDisplay(List<AuctionListing> newListings, List<AuctionListing> newBids, List<AuctionClaim> allClaims) {
        this.activeListings = new ArrayList<AuctionListing>(newListings);
        this.activeBids = new ArrayList<AuctionListing>(newBids);
        ArrayList<AuctionClaim> soldClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionClaim> outbidClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionClaim> wonClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionClaim> expiredClaims = new ArrayList<AuctionClaim>();
        for (AuctionClaim auctionClaim : allClaims) {
            if (auctionClaim.type == EnumClaimType.CURRENCY) {
                soldClaims.add(auctionClaim);
                continue;
            }
            if (auctionClaim.type == EnumClaimType.REFUND) {
                outbidClaims.add(auctionClaim);
                continue;
            }
            if (auctionClaim.type != EnumClaimType.ITEM) continue;
            if (auctionClaim.isReturned) {
                expiredClaims.add(auctionClaim);
                continue;
            }
            wonClaims.add(auctionClaim);
        }
        int slot = 0;
        for (AuctionClaim auctionClaim : soldClaims) {
            if (slot >= 45) break;
            this.claims.add(auctionClaim);
            this.displayInventory.setClaimItem(slot, auctionClaim);
            this.slotTypes[slot] = EnumTradeSlotType.CLAIM;
            this.slotDataIndex[slot] = this.claims.size() - 1;
            ++slot;
        }
        for (AuctionClaim auctionClaim : outbidClaims) {
            if (slot >= 45) break;
            this.claims.add(auctionClaim);
            this.displayInventory.setClaimItem(slot, auctionClaim);
            this.slotTypes[slot] = EnumTradeSlotType.CLAIM;
            this.slotDataIndex[slot] = this.claims.size() - 1;
            ++slot;
        }
        for (AuctionClaim auctionClaim : wonClaims) {
            if (slot >= 45) break;
            this.claims.add(auctionClaim);
            this.displayInventory.setClaimItem(slot, auctionClaim);
            this.slotTypes[slot] = EnumTradeSlotType.CLAIM;
            this.slotDataIndex[slot] = this.claims.size() - 1;
            ++slot;
        }
        boolean bl = false;
        for (AuctionListing listing : this.activeListings) {
            void var9_15;
            if (slot >= 45) break;
            if (listing.item != null) {
                this.displayInventory.func_70299_a(slot, listing.item.func_77946_l());
            }
            this.slotTypes[slot] = EnumTradeSlotType.SELLING;
            this.slotDataIndex[slot] = var9_15++;
            ++slot;
        }
        boolean bl2 = false;
        for (AuctionListing auctionListing : this.activeBids) {
            void var10_23;
            if (slot >= 45) break;
            if (auctionListing.item != null) {
                this.displayInventory.func_70299_a(slot, auctionListing.item.func_77946_l());
            }
            this.slotTypes[slot] = EnumTradeSlotType.BIDDING;
            this.slotDataIndex[slot] = var10_23++;
            ++slot;
        }
        for (AuctionClaim auctionClaim : expiredClaims) {
            if (slot >= 45) break;
            this.claims.add(auctionClaim);
            this.displayInventory.setClaimItem(slot, auctionClaim);
            this.slotTypes[slot] = EnumTradeSlotType.CLAIM;
            this.slotDataIndex[slot] = this.claims.size() - 1;
            ++slot;
        }
    }

    public AuctionListing getListingAt(int slot) {
        if (slot < 0 || slot >= 45) {
            return null;
        }
        EnumTradeSlotType type = this.slotTypes[slot];
        int idx = this.slotDataIndex[slot];
        if (idx < 0) {
            return null;
        }
        if (type == EnumTradeSlotType.SELLING && idx < this.activeListings.size()) {
            return this.activeListings.get(idx);
        }
        if (type == EnumTradeSlotType.BIDDING && idx < this.activeBids.size()) {
            return this.activeBids.get(idx);
        }
        return null;
    }

    public AuctionClaim getClaimAt(int slot) {
        int idx;
        if (slot < 0 || slot >= 45) {
            return null;
        }
        if (this.slotTypes[slot] == EnumTradeSlotType.CLAIM && (idx = this.slotDataIndex[slot]) >= 0 && idx < this.claims.size()) {
            return this.claims.get(idx);
        }
        return null;
    }

    public boolean isSellingAt(int slot) {
        return slot >= 0 && slot < 45 && this.slotTypes[slot] == EnumTradeSlotType.SELLING;
    }

    public boolean isBiddingAt(int slot) {
        return slot >= 0 && slot < 45 && this.slotTypes[slot] == EnumTradeSlotType.BIDDING;
    }

    public AuctionListing getListingForItem(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        for (AuctionListing listing : this.activeListings) {
            if (listing.item == null || !ItemStack.func_77989_b((ItemStack)listing.item, (ItemStack)stack)) continue;
            return listing;
        }
        for (AuctionListing bid : this.activeBids) {
            if (bid.item == null || !ItemStack.func_77989_b((ItemStack)bid.item, (ItemStack)stack)) continue;
            return bid;
        }
        return null;
    }

    public AuctionListing getSellingListingForItem(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        for (AuctionListing listing : this.activeListings) {
            if (listing.item == null || !ItemStack.func_77989_b((ItemStack)listing.item, (ItemStack)stack)) continue;
            return listing;
        }
        return null;
    }

    public AuctionListing getBiddingListingForItem(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        for (AuctionListing bid : this.activeBids) {
            if (bid.item == null || !ItemStack.func_77989_b((ItemStack)bid.item, (ItemStack)stack)) continue;
            return bid;
        }
        return null;
    }

    public AuctionClaim getClaimForItem(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        for (AuctionClaim claim : this.claims) {
            if (claim.item == null || !ItemStack.func_77989_b((ItemStack)claim.item, (ItemStack)stack)) continue;
            return claim;
        }
        return null;
    }

    public List<AuctionListing> getActiveListings() {
        return this.activeListings;
    }

    public List<AuctionListing> getActiveBids() {
        return this.activeBids;
    }

    public List<AuctionClaim> getClaims() {
        return this.claims;
    }

    public int getTotalTradeCount() {
        return this.activeListings.size() + this.activeBids.size() + this.claims.size();
    }

    public void setHiddenSlot(int slot) {
        if (this.hiddenSlot >= 0 && this.hiddenSlot < 45) {
            this.restoreSlotItem(this.hiddenSlot);
        }
        this.hiddenSlot = slot;
        if (this.hiddenSlot >= 0 && this.hiddenSlot < 45) {
            this.displayInventory.func_70299_a(this.hiddenSlot, null);
        }
    }

    public void clearHiddenSlot() {
        if (this.hiddenSlot >= 0 && this.hiddenSlot < 45) {
            this.restoreSlotItem(this.hiddenSlot);
        }
        this.hiddenSlot = -1;
    }

    private void restoreSlotItem(int slot) {
        if (slot < 0 || slot >= 45) {
            return;
        }
        EnumTradeSlotType type = this.slotTypes[slot];
        int idx = this.slotDataIndex[slot];
        if (idx < 0) {
            return;
        }
        if (type == EnumTradeSlotType.SELLING && idx < this.activeListings.size()) {
            AuctionListing listing = this.activeListings.get(idx);
            if (listing != null && listing.item != null) {
                this.displayInventory.func_70299_a(slot, listing.item.func_77946_l());
            }
        } else if (type == EnumTradeSlotType.BIDDING && idx < this.activeBids.size()) {
            AuctionListing bid = this.activeBids.get(idx);
            if (bid != null && bid.item != null) {
                this.displayInventory.func_70299_a(slot, bid.item.func_77946_l());
            }
        } else if (type == EnumTradeSlotType.CLAIM && idx < this.claims.size()) {
            this.displayInventory.setClaimItem(slot, this.claims.get(idx));
        }
    }

    public int getHiddenSlot() {
        return this.hiddenSlot;
    }

    public int getMaxTradeSlots() {
        return this.maxTradeSlots;
    }
}

