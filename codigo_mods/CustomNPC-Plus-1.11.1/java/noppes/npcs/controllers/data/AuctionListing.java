/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import java.util.UUID;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.handler.data.IAuctionListing;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.constants.EnumAuctionStatus;
import noppes.npcs.scripted.NpcAPI;

public class AuctionListing
implements IAuctionListing {
    public String id = UUID.randomUUID().toString();
    public UUID sellerUUID;
    public String sellerName;
    public ItemStack item;
    public long startingPrice;
    public long buyoutPrice;
    public long currentBid = 0L;
    public UUID highBidderUUID;
    public String highBidderName;
    public int bidCount = 0;
    public long createdTime;
    public long endTime;
    public EnumAuctionStatus status = EnumAuctionStatus.ACTIVE;
    public boolean isGlobalListing = false;

    public AuctionListing() {
        this.createdTime = System.currentTimeMillis();
    }

    public AuctionListing(UUID sellerUUID, String sellerName, ItemStack item, long startingPrice, long buyoutPrice, long durationMs) {
        this();
        this.sellerUUID = sellerUUID;
        this.sellerName = sellerName;
        this.item = item.func_77946_l();
        this.startingPrice = startingPrice;
        this.buyoutPrice = buyoutPrice;
        this.currentBid = 0L;
        this.endTime = this.createdTime + durationMs;
    }

    public long getTimeRemaining() {
        return Math.max(0L, this.endTime - System.currentTimeMillis());
    }

    public long getEffectivePrice() {
        return this.hasBids() ? this.currentBid : this.startingPrice;
    }

    public long getMinimumBid(double minIncrementPercent) {
        if (!this.hasBids()) {
            return this.startingPrice;
        }
        long increment = (long)Math.ceil((double)this.currentBid * minIncrementPercent);
        return this.currentBid + Math.max(1L, increment);
    }

    public boolean isSeller(UUID playerUUID) {
        return this.sellerUUID != null && this.sellerUUID.equals(playerUUID);
    }

    public boolean isHighBidder(UUID playerUUID) {
        return this.highBidderUUID != null && this.highBidderUUID.equals(playerUUID);
    }

    public void extendForSnipeProtection(int snipeProtectionMinutes) {
        long snipeProtectionMs = (long)(snipeProtectionMinutes * 60) * 1000L;
        long timeRemaining = this.getTimeRemaining();
        if (timeRemaining < snipeProtectionMs) {
            this.endTime = System.currentTimeMillis() + snipeProtectionMs;
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getSellerUUID() {
        return this.sellerUUID != null ? this.sellerUUID.toString() : null;
    }

    @Override
    public String getSellerName() {
        return this.sellerName;
    }

    @Override
    public IItemStack getItem() {
        return this.item != null ? NpcAPI.Instance().getIItemStack(this.item) : null;
    }

    @Override
    public long getStartingPrice() {
        return this.startingPrice;
    }

    @Override
    public long getBuyoutPrice() {
        return this.buyoutPrice;
    }

    @Override
    public long getCurrentBid() {
        return this.currentBid;
    }

    @Override
    public String getHighBidderUUID() {
        return this.highBidderUUID != null ? this.highBidderUUID.toString() : null;
    }

    @Override
    public String getHighBidderName() {
        return this.highBidderName;
    }

    @Override
    public int getBidCount() {
        return this.bidCount;
    }

    @Override
    public long getCreatedTime() {
        return this.createdTime;
    }

    @Override
    public long getEndTime() {
        return this.endTime;
    }

    @Override
    public long getRemainingTime() {
        return this.getTimeRemaining();
    }

    @Override
    public int getStatus() {
        return this.status.ordinal();
    }

    @Override
    public long getMinimumBid() {
        return this.getMinimumBid(ConfigMarket.MinBidIncrementPercent);
    }

    @Override
    public boolean isSeller(String playerUUID) {
        if (playerUUID == null || this.sellerUUID == null) {
            return false;
        }
        return this.sellerUUID.toString().equals(playerUUID);
    }

    @Override
    public boolean isHighBidder(String playerUUID) {
        if (playerUUID == null || this.highBidderUUID == null) {
            return false;
        }
        return this.highBidderUUID.toString().equals(playerUUID);
    }

    @Override
    public String getRemainingTimeFormatted() {
        long remaining = this.getTimeRemaining();
        if (remaining <= 0L) {
            return "Ended";
        }
        long hours = remaining / 3600000L;
        long minutes = remaining % 3600000L / 60000L;
        if (hours > 0L) {
            return hours + "h " + minutes + "m";
        }
        if (minutes > 0L) {
            return minutes + "m";
        }
        long seconds = remaining % 60000L / 1000L;
        return seconds + "s";
    }

    @Override
    public boolean hasBids() {
        return this.bidCount > 0 && this.highBidderUUID != null;
    }

    @Override
    public boolean hasBuyout() {
        return this.buyoutPrice > 0L;
    }

    @Override
    public boolean isExpired() {
        return System.currentTimeMillis() >= this.endTime;
    }

    @Override
    public boolean isActive() {
        return this.status == EnumAuctionStatus.ACTIVE && !this.isExpired();
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74778_a("ID", this.id);
        compound.func_74778_a("SellerUUID", this.sellerUUID != null ? this.sellerUUID.toString() : "");
        compound.func_74778_a("SellerName", this.sellerName != null ? this.sellerName : "");
        if (this.item != null) {
            NBTTagCompound itemTag = new NBTTagCompound();
            this.item.func_77955_b(itemTag);
            compound.func_74782_a("Item", (NBTBase)itemTag);
        }
        compound.func_74772_a("StartingPrice", this.startingPrice);
        compound.func_74772_a("BuyoutPrice", this.buyoutPrice);
        compound.func_74772_a("CurrentBid", this.currentBid);
        compound.func_74778_a("HighBidderUUID", this.highBidderUUID != null ? this.highBidderUUID.toString() : "");
        compound.func_74778_a("HighBidderName", this.highBidderName != null ? this.highBidderName : "");
        compound.func_74768_a("BidCount", this.bidCount);
        compound.func_74772_a("CreatedTime", this.createdTime);
        compound.func_74772_a("EndTime", this.endTime);
        compound.func_74768_a("Status", this.status.ordinal());
        compound.func_74757_a("IsGlobalListing", this.isGlobalListing);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.id = compound.func_74779_i("ID");
        String sellerUUIDStr = compound.func_74779_i("SellerUUID");
        this.sellerUUID = sellerUUIDStr.isEmpty() ? null : UUID.fromString(sellerUUIDStr);
        this.sellerName = compound.func_74779_i("SellerName");
        if (compound.func_74764_b("Item")) {
            this.item = ItemStack.func_77949_a((NBTTagCompound)compound.func_74775_l("Item"));
        }
        this.startingPrice = compound.func_74763_f("StartingPrice");
        this.buyoutPrice = compound.func_74763_f("BuyoutPrice");
        this.currentBid = compound.func_74763_f("CurrentBid");
        String highBidderUUIDStr = compound.func_74779_i("HighBidderUUID");
        this.highBidderUUID = highBidderUUIDStr.isEmpty() ? null : UUID.fromString(highBidderUUIDStr);
        this.highBidderName = compound.func_74779_i("HighBidderName");
        this.bidCount = compound.func_74762_e("BidCount");
        this.createdTime = compound.func_74763_f("CreatedTime");
        this.endTime = compound.func_74763_f("EndTime");
        this.status = EnumAuctionStatus.fromOrdinal(compound.func_74762_e("Status"));
        this.isGlobalListing = compound.func_74767_n("IsGlobalListing");
    }

    public static AuctionListing fromNBT(NBTTagCompound compound) {
        AuctionListing listing = new AuctionListing();
        listing.readFromNBT(compound);
        return listing;
    }
}

