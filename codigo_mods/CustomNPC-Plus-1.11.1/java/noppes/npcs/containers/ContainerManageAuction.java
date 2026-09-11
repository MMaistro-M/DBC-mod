/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.constants.EnumClaimType;
import noppes.npcs.containers.ContainerNpcInterface;
import noppes.npcs.containers.InventoryAuctionDisplay;
import noppes.npcs.containers.SlotAuctionDisplay;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.AuctionListing;

public class ContainerManageAuction
extends ContainerNpcInterface {
    public static final int DISPLAY_COLS = 9;
    public static final int DISPLAY_ROWS = 5;
    public static final int DISPLAY_SLOT_COUNT = 45;
    public static final int DISPLAY_X = 8;
    public static final int DISPLAY_Y = 16;
    public static final int PLAYER_INV_X = 8;
    public static final int PLAYER_INV_Y = 113;
    public static final int HOTBAR_Y = 171;
    public static final int PLAYER_SLOT_COUNT = 36;
    public static final int CREATE_SLOT_X = 188;
    public static final int CREATE_SLOT_Y = 156;
    public static final int DETAIL_SLOT_X = 194;
    public static final int DETAIL_SLOT_Y = 56;
    private static final int MAX_STACK = 64;
    public static final int DISPLAY_SLOT_START = 0;
    public static final int PLAYER_SLOT_START = 45;
    public static final int DETAIL_SLOT_INDEX = 81;
    public static final int CREATE_SLOT_INDEX = 82;
    private static final int HIDDEN_SLOT_X = -1000;
    private static final int HIDDEN_SLOT_Y = -1000;
    public final InventoryAuctionDisplay displayInventory;
    private final IInventory detailInventory;
    private final IInventory createInventory;
    private DisplayMode displayMode = DisplayMode.LISTINGS;
    private Slot detailSlot;
    private Slot createSlot;
    private int hiddenDisplaySlot = -1;

    public ContainerManageAuction(EntityPlayer player) {
        super(player);
        int y;
        int x;
        int col;
        int row;
        this.player = player;
        this.displayInventory = new InventoryAuctionDisplay(45);
        this.detailInventory = new InventoryBasic("GlobalAuctionDetail", false, 1);
        this.createInventory = new InventoryBasic("GlobalAuctionCreate", false, 1);
        for (row = 0; row < 5; ++row) {
            for (col = 0; col < 9; ++col) {
                int slotIndex = col + row * 9;
                x = 8 + col * 18;
                y = 16 + row * 18;
                this.func_75146_a(new SlotAuctionDisplay(this.displayInventory, slotIndex, x, y));
            }
        }
        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 9; ++col) {
                int playerIndex = col + row * 9 + 9;
                x = 8 + col * 18;
                y = 113 + row * 18;
                this.func_75146_a(new Slot((IInventory)player.field_71071_by, playerIndex, x, y));
            }
        }
        for (int col2 = 0; col2 < 9; ++col2) {
            int x2 = 8 + col2 * 18;
            this.func_75146_a(new Slot((IInventory)player.field_71071_by, col2, x2, 171));
        }
        this.detailSlot = new SlotAuctionDisplay(this.detailInventory, 0, 194, 56);
        this.func_75146_a(this.detailSlot);
        this.createSlot = new Slot(this.createInventory, 0, 188, 156){

            public boolean func_75214_a(ItemStack stack) {
                return false;
            }

            public boolean func_82869_a(EntityPlayer player) {
                return false;
            }
        };
        this.func_75146_a(this.createSlot);
        this.setDetailSlotVisible(false);
        this.setCreateSlotVisible(false);
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotIndex) {
        return null;
    }

    public ItemStack func_75144_a(int slotIndex, int mouseButton, int mode, EntityPlayer player) {
        return null;
    }

    public void setDisplayMode(DisplayMode mode) {
        this.displayMode = mode != null ? mode : DisplayMode.LISTINGS;
    }

    public DisplayMode getDisplayMode() {
        return this.displayMode;
    }

    public void clearDisplay() {
        this.displayInventory.clear();
    }

    public void setListingsPage(List<AuctionListing> listings) {
        this.displayMode = DisplayMode.LISTINGS;
        this.clearHiddenDisplaySlot();
        this.displayInventory.clear();
        if (listings == null) {
            return;
        }
        for (int i = 0; i < listings.size() && i < 45; ++i) {
            this.displayInventory.setListing(i, listings.get(i));
        }
    }

    public void setClaimsPage(List<AuctionClaim> claims) {
        this.displayMode = DisplayMode.CLAIMS;
        this.clearHiddenDisplaySlot();
        this.displayInventory.clear();
        if (claims == null) {
            return;
        }
        for (int i = 0; i < claims.size() && i < 45; ++i) {
            this.displayInventory.setClaimItem(i, claims.get(i));
        }
    }

    public void setClaimsAndListingsPage(List<AuctionClaim> claims, List<AuctionListing> listings) {
        this.displayMode = DisplayMode.CLAIMS;
        this.clearHiddenDisplaySlot();
        this.displayInventory.clear();
        ArrayList<AuctionClaim> soldClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionClaim> outbidClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionClaim> wonClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionClaim> expiredClaims = new ArrayList<AuctionClaim>();
        if (claims != null) {
            for (AuctionClaim claim : claims) {
                if (claim == null) continue;
                if (claim.type == EnumClaimType.CURRENCY) {
                    soldClaims.add(claim);
                    continue;
                }
                if (claim.type == EnumClaimType.REFUND) {
                    outbidClaims.add(claim);
                    continue;
                }
                if (claim.type == EnumClaimType.ITEM) {
                    if (claim.isReturned) {
                        expiredClaims.add(claim);
                        continue;
                    }
                    wonClaims.add(claim);
                    continue;
                }
                wonClaims.add(claim);
            }
        }
        int slot = 0;
        slot = this.fillClaims(slot, soldClaims);
        slot = this.fillClaims(slot, outbidClaims);
        slot = this.fillClaims(slot, wonClaims);
        if (listings != null) {
            for (int i = 0; i < listings.size() && slot < 45; ++slot, ++i) {
                this.displayInventory.setListing(slot, listings.get(i));
            }
        }
        this.fillClaims(slot, expiredClaims);
    }

    private int fillClaims(int slot, List<AuctionClaim> claims) {
        if (claims == null) {
            return slot;
        }
        for (int i = 0; i < claims.size() && slot < 45; ++slot, ++i) {
            this.displayInventory.setClaimItem(slot, claims.get(i));
        }
        return slot;
    }

    public boolean isDisplaySlot(int containerSlotIndex) {
        return containerSlotIndex >= 0 && containerSlotIndex < 45;
    }

    public int toDisplayIndex(int containerSlotIndex) {
        if (!this.isDisplaySlot(containerSlotIndex)) {
            return -1;
        }
        return containerSlotIndex - 0;
    }

    public AuctionListing getListingAtDisplay(int displayIndex) {
        if (displayIndex < 0 || displayIndex >= 45) {
            return null;
        }
        return this.displayInventory.getListing(displayIndex);
    }

    public AuctionClaim getClaimAtDisplay(int displayIndex) {
        if (displayIndex < 0 || displayIndex >= 45) {
            return null;
        }
        return this.displayInventory.getClaim(displayIndex);
    }

    public boolean isPlayerSlot(int containerSlotIndex) {
        return containerSlotIndex >= 45 && containerSlotIndex < 81;
    }

    public boolean isCreateSlot(int containerSlotIndex) {
        return containerSlotIndex == 82;
    }

    public boolean isDetailSlot(int containerSlotIndex) {
        return containerSlotIndex == 81;
    }

    public void setDetailItem(ItemStack stack) {
        this.detailInventory.func_70299_a(0, stack != null ? stack.func_77946_l() : null);
    }

    public void clearDetailItem() {
        this.detailInventory.func_70299_a(0, null);
    }

    public void setDetailSlotVisible(boolean visible) {
        if (this.detailSlot == null) {
            return;
        }
        this.detailSlot.field_75223_e = visible ? 194 : -1000;
        this.detailSlot.field_75221_f = visible ? 56 : -1000;
    }

    public void setCreateSlotVisible(boolean visible) {
        if (this.createSlot == null) {
            return;
        }
        this.createSlot.field_75223_e = visible ? 188 : -1000;
        this.createSlot.field_75221_f = visible ? 156 : -1000;
    }

    public void setHiddenDisplaySlot(int displayIndex) {
        if (displayIndex < 0 || displayIndex >= 45) {
            this.clearHiddenDisplaySlot();
            return;
        }
        if (this.hiddenDisplaySlot == displayIndex) {
            return;
        }
        this.clearHiddenDisplaySlot();
        this.hiddenDisplaySlot = displayIndex;
        this.displayInventory.func_70299_a(displayIndex, null);
    }

    public void clearHiddenDisplaySlot() {
        if (this.hiddenDisplaySlot < 0 || this.hiddenDisplaySlot >= 45) {
            this.hiddenDisplaySlot = -1;
            return;
        }
        this.restoreDisplaySlot(this.hiddenDisplaySlot);
        this.hiddenDisplaySlot = -1;
    }

    public int getHiddenDisplaySlot() {
        return this.hiddenDisplaySlot;
    }

    private void restoreDisplaySlot(int displayIndex) {
        if (displayIndex < 0 || displayIndex >= 45) {
            return;
        }
        AuctionClaim claim = this.displayInventory.getClaim(displayIndex);
        if (claim != null) {
            this.displayInventory.setClaimItem(displayIndex, claim);
            return;
        }
        AuctionListing listing = this.displayInventory.getListing(displayIndex);
        if (listing != null) {
            this.displayInventory.setListing(displayIndex, listing);
            return;
        }
        this.displayInventory.func_70299_a(displayIndex, null);
    }

    public ItemStack getCreateItem() {
        return this.createInventory.func_70301_a(0);
    }

    public void clearCreateSlot() {
        this.createInventory.func_70299_a(0, null);
    }

    public void addToCreateSlot(int containerPlayerSlot, boolean fullStack) {
        int toAdd;
        if (!this.isPlayerSlot(containerPlayerSlot)) {
            return;
        }
        Slot slot = (Slot)this.field_75151_b.get(containerPlayerSlot);
        if (slot == null) {
            return;
        }
        ItemStack source = slot.func_75211_c();
        if (source == null) {
            return;
        }
        ItemStack staged = this.createInventory.func_70301_a(0);
        int n = toAdd = fullStack ? source.field_77994_a : 1;
        if (staged == null) {
            ItemStack copy = source.func_77946_l();
            copy.field_77994_a = Math.min(64, toAdd);
            this.createInventory.func_70299_a(0, copy);
            return;
        }
        if (!this.itemsMatch(staged, source)) {
            ItemStack copy = source.func_77946_l();
            copy.field_77994_a = Math.min(64, toAdd);
            this.createInventory.func_70299_a(0, copy);
            return;
        }
        int space = 64 - staged.field_77994_a;
        int add = Math.min(space, toAdd);
        if (add > 0) {
            staged.field_77994_a += add;
        }
    }

    public void removeFromCreateSlot(boolean removeAll) {
        ItemStack staged = this.createInventory.func_70301_a(0);
        if (staged == null) {
            return;
        }
        if (removeAll || staged.field_77994_a <= 1) {
            this.createInventory.func_70299_a(0, null);
        } else {
            --staged.field_77994_a;
        }
    }

    public int countItemInPlayerInventory(ItemStack target) {
        if (target == null || this.player == null || this.player.field_71071_by == null) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < this.player.field_71071_by.field_70462_a.length; ++i) {
            ItemStack stack = this.player.field_71071_by.field_70462_a[i];
            if (stack == null || !this.itemsMatch(target, stack)) continue;
            total += stack.field_77994_a;
        }
        return total;
    }

    private boolean itemsMatch(ItemStack a, ItemStack b) {
        return NoppesUtilPlayer.compareItems(a, b, false, false);
    }

    public void func_75134_a(EntityPlayer player) {
        super.func_75134_a(player);
        this.clearHiddenDisplaySlot();
        this.clearDetailItem();
        this.clearCreateSlot();
    }

    public static enum DisplayMode {
        LISTINGS,
        CLAIMS;

    }
}

