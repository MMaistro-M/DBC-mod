/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import noppes.npcs.constants.EnumClaimType;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.AuctionListing;

public class InventoryAuctionDisplay
implements IInventory {
    private final ItemStack[] items;
    private final List<AuctionListing> listings;
    private final List<AuctionClaim> claims;
    private final int size;

    public InventoryAuctionDisplay(int size) {
        this.size = size;
        this.items = new ItemStack[size];
        this.listings = new ArrayList<AuctionListing>(size);
        this.claims = new ArrayList<AuctionClaim>(size);
        for (int i = 0; i < size; ++i) {
            this.listings.add(null);
            this.claims.add(null);
        }
    }

    public void setListing(int slot, AuctionListing listing) {
        if (slot >= 0 && slot < this.size) {
            this.listings.set(slot, listing);
            this.claims.set(slot, null);
            this.items[slot] = listing != null ? listing.item : null;
        }
    }

    public AuctionListing getListing(int slot) {
        if (slot >= 0 && slot < this.size) {
            return this.listings.get(slot);
        }
        return null;
    }

    public void setClaimItem(int slot, AuctionClaim claim) {
        if (slot >= 0 && slot < this.size) {
            this.claims.set(slot, claim);
            this.listings.set(slot, null);
            this.items[slot] = claim != null ? (claim.type == EnumClaimType.CURRENCY ? null : (claim.type == EnumClaimType.REFUND ? (claim.item != null ? claim.item.func_77946_l() : null) : (claim.item != null ? claim.item.func_77946_l() : null))) : null;
        }
    }

    public AuctionClaim getClaim(int slot) {
        if (slot >= 0 && slot < this.size) {
            return this.claims.get(slot);
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < this.size; ++i) {
            this.items[i] = null;
            this.listings.set(i, null);
            this.claims.set(i, null);
        }
    }

    public int func_70302_i_() {
        return this.size;
    }

    public ItemStack func_70301_a(int slot) {
        if (slot >= 0 && slot < this.size) {
            return this.items[slot];
        }
        return null;
    }

    public ItemStack func_70298_a(int slot, int amount) {
        return null;
    }

    public ItemStack func_70304_b(int slot) {
        return null;
    }

    public void func_70299_a(int slot, ItemStack stack) {
        if (slot >= 0 && slot < this.size) {
            this.items[slot] = stack;
        }
    }

    public String func_145825_b() {
        return "Auction Display";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {
    }

    public boolean func_70300_a(EntityPlayer player) {
        return true;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int slot, ItemStack stack) {
        return false;
    }
}

