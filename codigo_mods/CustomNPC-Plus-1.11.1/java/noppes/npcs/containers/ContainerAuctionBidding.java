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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import noppes.npcs.containers.ContainerAuction;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.entity.EntityNPCInterface;

public class ContainerAuctionBidding
extends ContainerAuction {
    private static final int ITEM_SLOT_X = 71;
    private static final int ITEM_SLOT_Y = 76;
    private static final int ITEM_SLOT_INDEX = 36;
    private final IInventory displayInventory = new InventoryBasic("Display", false, 1);
    private AuctionListing listing;

    public ContainerAuctionBidding(EntityNPCInterface npc, EntityPlayer player) {
        super(npc, player);
        this.func_75146_a(new Slot(this.displayInventory, 0, 71, 76){

            public boolean func_75214_a(ItemStack stack) {
                return false;
            }

            public boolean func_82869_a(EntityPlayer player) {
                return false;
            }
        });
    }

    public void setListing(AuctionListing listing) {
        this.listing = listing;
        if (listing != null && listing.item != null) {
            this.displayInventory.func_70299_a(0, listing.item.func_77946_l());
        } else {
            this.displayInventory.func_70299_a(0, null);
        }
    }

    public AuctionListing getListing() {
        return this.listing;
    }

    public ItemStack getDisplayItem() {
        return this.displayInventory.func_70301_a(0);
    }

    public boolean isDisplaySlot(int slotIndex) {
        return slotIndex == 36;
    }
}

