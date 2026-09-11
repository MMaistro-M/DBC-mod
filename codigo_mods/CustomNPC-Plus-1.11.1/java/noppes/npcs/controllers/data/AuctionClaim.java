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
import noppes.npcs.api.handler.data.IAuctionClaim;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.constants.EnumClaimType;
import noppes.npcs.scripted.NpcAPI;

public class AuctionClaim
implements IAuctionClaim {
    public String id = UUID.randomUUID().toString();
    public UUID playerUUID;
    public String playerName;
    public String listingId;
    public EnumClaimType type;
    public ItemStack item;
    public String itemName = "";
    public String otherPlayerName = "";
    public long currency;
    public long createdTime = System.currentTimeMillis();
    public boolean claimed = false;
    public boolean isReturned = false;

    public static AuctionClaim createItemWonClaim(UUID playerUUID, String playerName, String listingId, ItemStack item) {
        AuctionClaim claim = new AuctionClaim();
        claim.playerUUID = playerUUID;
        claim.playerName = playerName;
        claim.listingId = listingId;
        claim.type = EnumClaimType.ITEM;
        claim.item = item != null ? item.func_77946_l() : null;
        claim.currency = 0L;
        claim.isReturned = false;
        return claim;
    }

    public static AuctionClaim createItemReturnedClaim(UUID playerUUID, String playerName, String listingId, ItemStack item) {
        AuctionClaim claim = new AuctionClaim();
        claim.playerUUID = playerUUID;
        claim.playerName = playerName;
        claim.listingId = listingId;
        claim.type = EnumClaimType.ITEM;
        claim.item = item != null ? item.func_77946_l() : null;
        claim.currency = 0L;
        claim.isReturned = true;
        return claim;
    }

    public static AuctionClaim createCurrencyClaim(UUID playerUUID, String playerName, String listingId, long currency, String itemName, String buyerName) {
        AuctionClaim claim = new AuctionClaim();
        claim.playerUUID = playerUUID;
        claim.playerName = playerName;
        claim.listingId = listingId;
        claim.type = EnumClaimType.CURRENCY;
        claim.item = null;
        claim.itemName = itemName != null ? itemName : "";
        claim.otherPlayerName = buyerName != null ? buyerName : "";
        claim.currency = currency;
        claim.isReturned = false;
        return claim;
    }

    public static AuctionClaim createRefundClaim(UUID playerUUID, String playerName, String listingId, long currency, String itemName, String outbidderName, ItemStack item) {
        AuctionClaim claim = new AuctionClaim();
        claim.playerUUID = playerUUID;
        claim.playerName = playerName;
        claim.listingId = listingId;
        claim.type = EnumClaimType.REFUND;
        claim.item = item != null ? item.func_77946_l() : null;
        claim.itemName = itemName != null ? itemName : "";
        claim.otherPlayerName = outbidderName != null ? outbidderName : "";
        claim.currency = currency;
        claim.isReturned = false;
        return claim;
    }

    public boolean isExpired(int expirationDays) {
        long expirationMs = (long)expirationDays * 24L * 60L * 60L * 1000L;
        return System.currentTimeMillis() - this.createdTime > expirationMs;
    }

    public long getDaysUntilExpiration(int expirationDays) {
        long expirationMs = (long)expirationDays * 24L * 60L * 60L * 1000L;
        long remaining = this.createdTime + expirationMs - System.currentTimeMillis();
        return Math.max(0L, remaining / 86400000L);
    }

    public boolean isForPlayer(UUID uuid) {
        return this.playerUUID != null && this.playerUUID.equals(uuid);
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getPlayerUUID() {
        return this.playerUUID != null ? this.playerUUID.toString() : null;
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public String getListingId() {
        return this.listingId;
    }

    @Override
    public int getType() {
        return this.type.ordinal();
    }

    @Override
    public boolean isItemClaim() {
        return this.type == EnumClaimType.ITEM;
    }

    @Override
    public boolean isCurrencyClaim() {
        return this.type.isCurrency();
    }

    @Override
    public boolean isRefundClaim() {
        return this.type == EnumClaimType.REFUND;
    }

    @Override
    public IItemStack getItem() {
        return this.item != null ? NpcAPI.Instance().getIItemStack(this.item) : null;
    }

    @Override
    public String getItemName() {
        return this.itemName;
    }

    @Override
    public long getCurrency() {
        return this.currency;
    }

    @Override
    public String getOtherPlayerName() {
        return this.otherPlayerName;
    }

    @Override
    public long getCreatedTime() {
        return this.createdTime;
    }

    @Override
    public boolean isClaimed() {
        return this.claimed;
    }

    @Override
    public boolean isReturnedItem() {
        return this.isReturned;
    }

    @Override
    public long getDaysUntilExpiration() {
        return this.getDaysUntilExpiration(ConfigMarket.ClaimExpirationDays);
    }

    @Override
    public boolean isExpired() {
        return this.isExpired(ConfigMarket.ClaimExpirationDays);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74778_a("ID", this.id);
        compound.func_74778_a("PlayerUUID", this.playerUUID != null ? this.playerUUID.toString() : "");
        compound.func_74778_a("PlayerName", this.playerName != null ? this.playerName : "");
        compound.func_74778_a("ListingID", this.listingId != null ? this.listingId : "");
        compound.func_74768_a("Type", this.type.ordinal());
        if (this.item != null) {
            NBTTagCompound itemTag = new NBTTagCompound();
            this.item.func_77955_b(itemTag);
            compound.func_74782_a("Item", (NBTBase)itemTag);
        }
        compound.func_74778_a("ItemName", this.itemName != null ? this.itemName : "");
        compound.func_74778_a("OtherPlayerName", this.otherPlayerName != null ? this.otherPlayerName : "");
        compound.func_74772_a("Currency", this.currency);
        compound.func_74772_a("CreatedTime", this.createdTime);
        compound.func_74757_a("Claimed", this.claimed);
        compound.func_74757_a("IsReturned", this.isReturned);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.id = compound.func_74779_i("ID");
        String playerUUIDStr = compound.func_74779_i("PlayerUUID");
        this.playerUUID = playerUUIDStr.isEmpty() ? null : UUID.fromString(playerUUIDStr);
        this.playerName = compound.func_74779_i("PlayerName");
        this.listingId = compound.func_74779_i("ListingID");
        this.type = EnumClaimType.fromOrdinal(compound.func_74762_e("Type"));
        this.item = compound.func_74764_b("Item") ? ItemStack.func_77949_a((NBTTagCompound)compound.func_74775_l("Item")) : null;
        this.itemName = compound.func_74779_i("ItemName");
        this.otherPlayerName = compound.func_74779_i("OtherPlayerName");
        this.currency = compound.func_74763_f("Currency");
        this.createdTime = compound.func_74763_f("CreatedTime");
        this.claimed = compound.func_74767_n("Claimed");
        this.isReturned = compound.func_74767_n("IsReturned");
    }

    public static AuctionClaim fromNBT(NBTTagCompound compound) {
        AuctionClaim claim = new AuctionClaim();
        claim.readFromNBT(compound);
        return claim;
    }
}

