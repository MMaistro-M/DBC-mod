/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package noppes.npcs.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.EventHooks;
import noppes.npcs.LogWriter;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.IAuctionHandler;
import noppes.npcs.api.handler.data.IAuctionListing;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.constants.EnumAuctionLogAction;
import noppes.npcs.constants.EnumAuctionSort;
import noppes.npcs.constants.EnumAuctionStatus;
import noppes.npcs.constants.EnumClaimType;
import noppes.npcs.constants.EnumNotificationType;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.AuctionBlacklist;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.AuctionFilter;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerDataScript;
import noppes.npcs.controllers.data.PlayerTradeData;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.player.AuctionEvent;
import noppes.npcs.util.CustomNPCsThreader;

public class AuctionController
implements IAuctionHandler {
    public static AuctionController Instance;
    private final Map<String, AuctionListing> listings = new ConcurrentHashMap<String, AuctionListing>();
    private final List<AuctionClaim> globalClaims = new CopyOnWriteArrayList<AuctionClaim>();
    private final Map<UUID, Set<String>> playerListingIds = new ConcurrentHashMap<UUID, Set<String>>();
    private final Map<UUID, Set<String>> playerBidIds = new ConcurrentHashMap<UUID, Set<String>>();
    private final Map<UUID, Integer> playerMaxTradesCache = new ConcurrentHashMap<UUID, Integer>();
    private final AtomicBoolean dirty = new AtomicBoolean(false);
    private final AtomicBoolean saving = new AtomicBoolean(false);
    private String filePath = "";
    private int tickCounter = 0;
    private static final int TICK_INTERVAL = 600;

    public AuctionController() {
        Instance = this;
        AuctionBlacklist.reload();
        this.load();
    }

    public static AuctionController getInstance() {
        if (Instance == null || AuctionController.needsNewInstance()) {
            Instance = new AuctionController();
        }
        return Instance;
    }

    private static boolean needsNewInstance() {
        if (Instance == null) {
            return true;
        }
        File file = CustomNpcs.getWorldSaveDirectory();
        if (file == null) {
            return false;
        }
        return !AuctionController.Instance.filePath.equals(file.getAbsolutePath());
    }

    public void onServerTick() {
        if (!ConfigMarket.AuctionEnabled) {
            return;
        }
        ++this.tickCounter;
        if (this.tickCounter >= 600) {
            this.tickCounter = 0;
            this.processEndedAuctions();
            if (this.dirty.get()) {
                this.saveAsync();
            }
        }
    }

    private void processEndedAuctions() {
        boolean changed = false;
        for (AuctionListing listing : this.listings.values()) {
            if (listing.status != EnumAuctionStatus.ACTIVE || !listing.isExpired()) continue;
            this.endAuction(listing);
            changed = true;
        }
        if (changed) {
            this.markDirty();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void endAuction(AuctionListing listing) {
        AuctionListing auctionListing = listing;
        synchronized (auctionListing) {
            String itemDisplayName;
            if (listing.status != EnumAuctionStatus.ACTIVE) {
                return;
            }
            listing.status = EnumAuctionStatus.ENDED;
            boolean isGlobalListing = listing.isGlobalListing;
            if (!isGlobalListing && listing.sellerUUID != null) {
                this.removeFromPlayerListings(listing.sellerUUID, listing.id);
            }
            if (listing.highBidderUUID != null) {
                this.removeFromPlayerBids(listing.highBidderUUID, listing.id);
            }
            String string = itemDisplayName = listing.item != null ? listing.item.func_82833_r() : "Unknown Item";
            if (listing.hasBids()) {
                AuctionClaim itemClaim = AuctionClaim.createItemWonClaim(listing.highBidderUUID, listing.highBidderName, listing.id, listing.item);
                this.addClaimToPlayer(listing.highBidderUUID, itemClaim);
                long saleAmount = listing.currentBid;
                long tax = (long)((double)saleAmount * ConfigMarket.SalesTaxPercent);
                long sellerReceives = saleAmount - tax;
                AuctionClaim currencyClaim = AuctionClaim.createCurrencyClaim(listing.sellerUUID, listing.sellerName, listing.id, sellerReceives, itemDisplayName, listing.highBidderName);
                if (isGlobalListing) {
                    this.addGlobalClaim(currencyClaim);
                } else {
                    this.addClaimToPlayer(listing.sellerUUID, currencyClaim);
                }
                this.sendNotificationToPlayer(listing.highBidderUUID, EnumNotificationType.AUCTION_WON, listing.id, "You won the auction for " + itemDisplayName + "!");
                if (!isGlobalListing && listing.sellerUUID != null) {
                    this.sendNotificationToPlayer(listing.sellerUUID, EnumNotificationType.AUCTION_SOLD, listing.id, "Your " + itemDisplayName + " sold for " + saleAmount + " " + ConfigMarket.CurrencyName + "!");
                }
                this.logAuction(EnumAuctionLogAction.SOLD, listing.sellerName, itemDisplayName, saleAmount, "Winner: " + listing.highBidderName + ", Tax: " + tax + (isGlobalListing ? ", Global listing" : ""));
            } else {
                AuctionClaim returnClaim = AuctionClaim.createItemReturnedClaim(listing.sellerUUID, listing.sellerName, listing.id, listing.item);
                if (isGlobalListing) {
                    this.addGlobalClaim(returnClaim);
                } else {
                    this.addClaimToPlayer(listing.sellerUUID, returnClaim);
                    if (listing.sellerUUID != null) {
                        this.sendNotificationToPlayer(listing.sellerUUID, EnumNotificationType.AUCTION_EXPIRED, listing.id, "Your auction for " + itemDisplayName + " expired with no bids.");
                    }
                }
                this.logAuction(EnumAuctionLogAction.EXPIRED, listing.sellerName, itemDisplayName, listing.startingPrice, "No bids" + (isGlobalListing ? ", Global listing" : ""));
            }
        }
    }

    private void addToPlayerListings(UUID playerUUID, String listingId) {
        if (playerUUID == null || listingId == null || listingId.isEmpty()) {
            return;
        }
        this.playerListingIds.computeIfAbsent(playerUUID, k -> ConcurrentHashMap.newKeySet()).add(listingId);
    }

    private void removeFromPlayerListings(UUID playerUUID, String listingId) {
        if (playerUUID == null || listingId == null || listingId.isEmpty()) {
            return;
        }
        Set<String> ids = this.playerListingIds.get(playerUUID);
        if (ids != null) {
            ids.remove(listingId);
            if (ids.isEmpty()) {
                this.playerListingIds.remove(playerUUID);
            }
        }
    }

    private void addToPlayerBids(UUID playerUUID, String listingId) {
        if (playerUUID == null || listingId == null || listingId.isEmpty()) {
            return;
        }
        this.playerBidIds.computeIfAbsent(playerUUID, k -> ConcurrentHashMap.newKeySet()).add(listingId);
    }

    private void removeFromPlayerBids(UUID playerUUID, String listingId) {
        if (playerUUID == null || listingId == null || listingId.isEmpty()) {
            return;
        }
        Set<String> ids = this.playerBidIds.get(playerUUID);
        if (ids != null) {
            ids.remove(listingId);
            if (ids.isEmpty()) {
                this.playerBidIds.remove(playerUUID);
            }
        }
    }

    private void addClaimToPlayer(UUID playerUUID, AuctionClaim claim) {
        if (playerUUID == null || claim == null) {
            return;
        }
        PlayerData data = PlayerDataController.Instance.getData(playerUUID);
        if (data != null) {
            data.tradeData.addClaim(claim);
            data.updateClient = true;
            data.save();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void addGlobalClaim(AuctionClaim claim) {
        if (claim == null || claim.claimed) {
            return;
        }
        claim.playerUUID = null;
        claim.playerName = "Global";
        List<AuctionClaim> list = this.globalClaims;
        synchronized (list) {
            for (AuctionClaim existing : this.globalClaims) {
                if (!existing.id.equals(claim.id)) continue;
                return;
            }
            this.globalClaims.add(claim);
        }
        this.markDirty();
    }

    private List<AuctionClaim> getGlobalClaimsSnapshot() {
        ArrayList<AuctionClaim> result = new ArrayList<AuctionClaim>();
        for (AuctionClaim claim : this.globalClaims) {
            if (claim == null || claim.claimed) continue;
            result.add(claim);
        }
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private AuctionClaim getGlobalClaimInternal(String claimId) {
        if (claimId == null || claimId.isEmpty()) {
            return null;
        }
        List<AuctionClaim> list = this.globalClaims;
        synchronized (list) {
            for (AuctionClaim claim : this.globalClaims) {
                if (claim == null || claim.claimed || !claimId.equals(claim.id)) continue;
                return claim;
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean claimAndRemoveGlobal(String claimId) {
        List<AuctionClaim> list = this.globalClaims;
        synchronized (list) {
            AuctionClaim claim = this.getGlobalClaimInternal(claimId);
            if (claim == null) {
                return false;
            }
            claim.claimed = true;
            return this.globalClaims.remove(claim);
        }
    }

    public List<AuctionClaim> getAllGlobalClaims() {
        List<AuctionClaim> snapshot = this.getGlobalClaimsSnapshot();
        snapshot.sort((a, b) -> Long.compare(b.createdTime, a.createdTime));
        return snapshot;
    }

    public List<AuctionListing> getActiveGlobalListings() {
        ArrayList<AuctionListing> result = new ArrayList<AuctionListing>();
        for (AuctionListing listing : this.listings.values()) {
            if (listing == null || !listing.isGlobalListing || !listing.isActive()) continue;
            result.add(listing);
        }
        result.sort((a, b) -> Long.compare(b.createdTime, a.createdTime));
        return result;
    }

    public String createListing(EntityPlayer player, ItemStack item, long startingPrice, long buyoutPrice) {
        IPlayer iCreator;
        AuctionEvent.CreateEvent createEvent;
        int maxTrades;
        if (!ConfigMarket.AuctionEnabled) {
            return "Auction is disabled.";
        }
        if (AuctionBlacklist.isBlacklistedForPlayer(item, player)) {
            return "This item cannot be listed on the Auction House.";
        }
        if (startingPrice <= 0L) {
            return "Starting price must be positive.";
        }
        if (startingPrice < ConfigMarket.MinimumListingPrice) {
            return "Starting price must be at least " + ConfigMarket.MinimumListingPrice + " " + ConfigMarket.CurrencyName + ".";
        }
        if (startingPrice > ConfigMarket.MaxBalance) {
            return "Starting price exceeds maximum allowed value.";
        }
        if (buyoutPrice < 0L) {
            return "Buyout price cannot be negative.";
        }
        if (buyoutPrice > 0L && buyoutPrice < startingPrice) {
            return "Buyout price must be higher than starting price.";
        }
        if (buyoutPrice > ConfigMarket.MaxBalance) {
            return "Buyout price exceeds maximum allowed value.";
        }
        UUID playerUUID = player.func_110124_au();
        String playerName = player.func_70005_c_();
        int currentTrades = this.getPlayerTradeSlotCount(player);
        if (currentTrades >= (maxTrades = this.getMaxTradesForPlayer(player))) {
            return "You have reached your maximum trade slots (" + maxTrades + ").";
        }
        PlayerData playerData = PlayerData.get(player);
        if (playerData == null) {
            return "Could not access player data.";
        }
        PlayerTradeData currency = playerData.tradeData;
        long fee = ConfigMarket.ListingFee;
        if (fee > 0L && !currency.canAfford(fee)) {
            return "You cannot afford the listing fee (" + fee + " " + ConfigMarket.CurrencyName + ").";
        }
        PlayerDataScript createHandler = ScriptController.Instance.getPlayerScripts(player);
        if (EventHooks.onAuctionCreate(createHandler, createEvent = new AuctionEvent.CreateEvent(iCreator = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player), NpcAPI.Instance().getIItemStack(item), startingPrice, buyoutPrice))) {
            return "Auction creation was cancelled.";
        }
        if (fee > 0L && !currency.withdraw(fee)) {
            return "Failed to deduct listing fee.";
        }
        long durationMs = (long)ConfigMarket.AuctionDurationHours * 60L * 60L * 1000L;
        AuctionListing listing = new AuctionListing(playerUUID, playerName, item, startingPrice, buyoutPrice, durationMs);
        this.listings.put(listing.id, listing);
        this.addToPlayerListings(playerUUID, listing.id);
        this.markDirty();
        this.logAuction(EnumAuctionLogAction.CREATED, playerName, item.func_82833_r(), startingPrice, "Buyout: " + (buyoutPrice > 0L ? Long.valueOf(buyoutPrice) : "None") + ", Fee: " + fee);
        return null;
    }

    public String createGlobalListing(EntityPlayer admin, String sellerName, ItemStack item, long startingPrice, long buyoutPrice, int durationHours) {
        String cleanSeller;
        if (!ConfigMarket.AuctionEnabled) {
            return "Auction is disabled.";
        }
        if (admin == null) {
            return "Admin is missing.";
        }
        if (item == null) {
            return "No item provided.";
        }
        if (startingPrice <= 0L) {
            return "Starting price must be positive.";
        }
        if (startingPrice < ConfigMarket.MinimumListingPrice) {
            return "Starting price must be at least " + ConfigMarket.MinimumListingPrice + " " + ConfigMarket.CurrencyName + ".";
        }
        if (buyoutPrice < 0L) {
            return "Buyout price cannot be negative.";
        }
        if (buyoutPrice > 0L && buyoutPrice < startingPrice) {
            return "Buyout price must be higher than starting price.";
        }
        String string = cleanSeller = sellerName != null ? sellerName.trim() : "";
        if (cleanSeller.isEmpty()) {
            cleanSeller = "Server";
        }
        int hours = durationHours > 0 ? durationHours : ConfigMarket.AuctionDurationHours;
        long durationMs = (long)hours * 60L * 60L * 1000L;
        AuctionListing listing = new AuctionListing(null, cleanSeller, item, startingPrice, buyoutPrice, durationMs);
        listing.isGlobalListing = true;
        this.listings.put(listing.id, listing);
        this.markDirty();
        this.logAuction(EnumAuctionLogAction.CREATED, admin.func_70005_c_(), item.func_82833_r(), startingPrice, "Global listing, Seller: " + cleanSeller + ", Buyout: " + (buyoutPrice > 0L ? Long.valueOf(buyoutPrice) : "None") + ", DurationHours: " + hours);
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String cancelListing(String listingId, EntityPlayer player, boolean isAdmin) {
        AuctionListing listing = this.listings.get(listingId);
        if (listing == null) {
            return "Listing not found.";
        }
        if (isAdmin && listing.isGlobalListing) {
            return this.adminStopListingToGlobal(listingId, player, true);
        }
        AuctionListing auctionListing = listing;
        synchronized (auctionListing) {
            IPlayer iCanceller;
            AuctionEvent.CancelEvent cancelEvent;
            if (!listing.status.canCancel()) {
                return "This auction cannot be cancelled.";
            }
            UUID playerUUID = player.func_110124_au();
            if (!isAdmin && !listing.isSeller(playerUUID)) {
                return "You can only cancel your own listings.";
            }
            PlayerDataScript cancelHandler = ScriptController.Instance.getPlayerScripts(player);
            if (EventHooks.onAuctionCancel(cancelHandler, cancelEvent = new AuctionEvent.CancelEvent(iCanceller = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player), listing, isAdmin))) {
                return "Auction cancellation was prevented.";
            }
            if (listing.hasBids()) {
                PlayerData sellerData;
                long penalty = (long)((double)listing.currentBid * ConfigMarket.CancellationPenaltyPercent);
                AuctionClaim refundClaim = AuctionClaim.createRefundClaim(listing.highBidderUUID, listing.highBidderName, listing.id, listing.currentBid, listing.item.func_82833_r(), listing.sellerName, null);
                this.addClaimToPlayer(listing.highBidderUUID, refundClaim);
                this.removeFromPlayerBids(listing.highBidderUUID, listing.id);
                this.sendNotificationToPlayer(listing.highBidderUUID, EnumNotificationType.AUCTION_OUTBID, listing.id, "The auction for " + listing.item.func_82833_r() + " was cancelled. Your bid has been refunded.");
                AuctionClaim itemClaim = AuctionClaim.createItemReturnedClaim(listing.sellerUUID, listing.sellerName, listing.id, listing.item);
                this.addClaimToPlayer(listing.sellerUUID, itemClaim);
                if (penalty > 0L && (sellerData = PlayerDataController.Instance.getData(listing.sellerUUID)) != null) {
                    long available;
                    if (!sellerData.tradeData.withdraw(penalty) && (available = sellerData.tradeData.getBalance()) > 0L) {
                        sellerData.tradeData.withdraw(available);
                    }
                    sellerData.save();
                }
                this.logAuction(EnumAuctionLogAction.CANCELLED, listing.sellerName, listing.item.func_82833_r(), listing.currentBid, "Penalty: " + penalty + ", Bidder refunded: " + listing.highBidderName + (isAdmin ? ", Cancelled by admin: " + player.func_70005_c_() : ""));
            } else {
                AuctionClaim itemClaim = AuctionClaim.createItemReturnedClaim(listing.sellerUUID, listing.sellerName, listing.id, listing.item);
                this.addClaimToPlayer(listing.sellerUUID, itemClaim);
                this.logAuction(EnumAuctionLogAction.CANCELLED, listing.sellerName, listing.item.func_82833_r(), listing.startingPrice, "No bids" + (isAdmin ? ", Cancelled by admin: " + player.func_70005_c_() : ""));
            }
            this.removeFromPlayerListings(listing.sellerUUID, listing.id);
            listing.status = EnumAuctionStatus.CANCELLED;
            this.markDirty();
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String placeBid(String listingId, EntityPlayer player, long bidAmount) {
        if (!ConfigMarket.AuctionEnabled) {
            return "Auction is disabled.";
        }
        if (bidAmount <= 0L) {
            return "Bid amount must be positive.";
        }
        UUID playerUUID = player.func_110124_au();
        String playerName = player.func_70005_c_();
        AuctionListing listing = this.listings.get(listingId);
        if (listing == null) {
            return "Listing not found.";
        }
        AuctionListing auctionListing = listing;
        synchronized (auctionListing) {
            IPlayer iBidder;
            AuctionEvent.BidEvent bidEvent;
            if (!listing.status.canBid()) {
                return "This auction has ended.";
            }
            if (listing.isExpired()) {
                return "This auction has ended.";
            }
            if (listing.isSeller(playerUUID)) {
                return "You cannot bid on your own auction.";
            }
            if (listing.isHighBidder(playerUUID)) {
                return "You are already the highest bidder. Use buyout if you want to purchase immediately.";
            }
            long minBid = listing.getMinimumBid(ConfigMarket.MinBidIncrementPercent);
            if (bidAmount < minBid) {
                return "Bid must be at least " + minBid + " " + ConfigMarket.CurrencyName + ".";
            }
            if (listing.hasBuyout() && bidAmount >= listing.buyoutPrice) {
                return "Your bid meets or exceeds the buyout price. Use Buy Now instead.";
            }
            PlayerData playerData = PlayerData.get(player);
            if (playerData == null) {
                return "Could not access player data.";
            }
            PlayerTradeData currency = playerData.tradeData;
            UUID previousBidder = listing.highBidderUUID;
            long previousBid = listing.currentBid;
            long amountToCharge = bidAmount;
            if (!currency.canAfford(amountToCharge)) {
                return "You cannot afford this bid.";
            }
            PlayerDataScript bidHandler = ScriptController.Instance.getPlayerScripts(player);
            if (EventHooks.onAuctionBid(bidHandler, bidEvent = new AuctionEvent.BidEvent(iBidder = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player), listing, bidAmount))) {
                return "Bid was cancelled.";
            }
            if (!currency.withdraw(amountToCharge)) {
                return "Failed to deduct bid amount.";
            }
            if (listing.hasBids() && previousBidder != null) {
                AuctionClaim refundClaim = AuctionClaim.createRefundClaim(previousBidder, listing.highBidderName, listing.id, previousBid, listing.item.func_82833_r(), playerName, listing.item);
                this.addClaimToPlayer(previousBidder, refundClaim);
                this.removeFromPlayerBids(previousBidder, listing.id);
                this.sendNotificationToPlayer(previousBidder, EnumNotificationType.AUCTION_OUTBID, listing.id, "You were outbid on " + listing.item.func_82833_r() + "!");
            }
            listing.highBidderUUID = playerUUID;
            listing.highBidderName = playerName;
            listing.currentBid = bidAmount;
            ++listing.bidCount;
            this.addToPlayerBids(playerUUID, listing.id);
            listing.extendForSnipeProtection(ConfigMarket.SnipeProtectionMinutes);
        }
        this.markDirty();
        this.logAuction(EnumAuctionLogAction.BID, playerName, listing.item.func_82833_r(), bidAmount, "Bids: " + listing.bidCount + ", Seller: " + listing.sellerName);
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String buyout(String listingId, EntityPlayer player) {
        if (!ConfigMarket.AuctionEnabled) {
            return "Auction is disabled.";
        }
        UUID playerUUID = player.func_110124_au();
        String playerName = player.func_70005_c_();
        AuctionListing listing = this.listings.get(listingId);
        if (listing == null) {
            return "Listing not found.";
        }
        AuctionListing auctionListing = listing;
        synchronized (auctionListing) {
            IPlayer iBuyer;
            AuctionEvent.BuyoutEvent buyoutEvent;
            long amountToCharge;
            if (!listing.status.canBid()) {
                return "This auction has ended.";
            }
            if (listing.isExpired()) {
                return "This auction has ended.";
            }
            if (!listing.hasBuyout()) {
                return "This auction does not have a buyout price.";
            }
            if (listing.isSeller(playerUUID)) {
                return "You cannot buy your own auction.";
            }
            PlayerData playerData = PlayerData.get(player);
            if (playerData == null) {
                return "Could not access player data.";
            }
            PlayerTradeData currency = playerData.tradeData;
            boolean isCurrentBidder = listing.isHighBidder(playerUUID);
            long l = amountToCharge = isCurrentBidder ? listing.buyoutPrice - listing.currentBid : listing.buyoutPrice;
            if (!currency.canAfford(amountToCharge)) {
                if (isCurrentBidder) {
                    return "You cannot afford the remaining " + amountToCharge + " " + ConfigMarket.CurrencyName + " for buyout.";
                }
                return "You cannot afford the buyout price.";
            }
            PlayerDataScript buyoutHandler = ScriptController.Instance.getPlayerScripts(player);
            if (EventHooks.onAuctionBuyout(buyoutHandler, buyoutEvent = new AuctionEvent.BuyoutEvent(iBuyer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player), listing))) {
                return "Buyout was cancelled.";
            }
            if (!currency.withdraw(amountToCharge)) {
                return "Failed to deduct buyout amount.";
            }
            if (listing.hasBids() && listing.highBidderUUID != null && !isCurrentBidder) {
                AuctionClaim refundClaim = AuctionClaim.createRefundClaim(listing.highBidderUUID, listing.highBidderName, listing.id, listing.currentBid, listing.item.func_82833_r(), playerName, null);
                this.addClaimToPlayer(listing.highBidderUUID, refundClaim);
                this.removeFromPlayerBids(listing.highBidderUUID, listing.id);
                this.sendNotificationToPlayer(listing.highBidderUUID, EnumNotificationType.AUCTION_OUTBID, listing.id, "The auction for " + listing.item.func_82833_r() + " was bought out. Your bid has been refunded.");
            } else if (isCurrentBidder) {
                this.removeFromPlayerBids(playerUUID, listing.id);
            }
            AuctionClaim itemClaim = AuctionClaim.createItemWonClaim(playerUUID, playerName, listing.id, listing.item);
            this.addClaimToPlayer(playerUUID, itemClaim);
            long saleAmount = listing.buyoutPrice;
            long tax = (long)((double)saleAmount * ConfigMarket.SalesTaxPercent);
            long sellerReceives = saleAmount - tax;
            AuctionClaim currencyClaim = AuctionClaim.createCurrencyClaim(listing.sellerUUID, listing.sellerName, listing.id, sellerReceives, listing.item.func_82833_r(), playerName);
            if (listing.isGlobalListing) {
                this.addGlobalClaim(currencyClaim);
            } else {
                this.addClaimToPlayer(listing.sellerUUID, currencyClaim);
            }
            if (!listing.isGlobalListing && listing.sellerUUID != null) {
                this.sendNotificationToPlayer(listing.sellerUUID, EnumNotificationType.AUCTION_SOLD, listing.id, "Your " + listing.item.func_82833_r() + " was bought out for " + saleAmount + " " + ConfigMarket.CurrencyName + "!");
            }
            if (!listing.isGlobalListing && listing.sellerUUID != null) {
                this.removeFromPlayerListings(listing.sellerUUID, listing.id);
            }
            listing.status = EnumAuctionStatus.ENDED;
            listing.highBidderUUID = playerUUID;
            listing.highBidderName = playerName;
            listing.currentBid = listing.buyoutPrice;
            this.markDirty();
            this.logAuction(EnumAuctionLogAction.BUYOUT, playerName, listing.item.func_82833_r(), listing.buyoutPrice, "Seller: " + listing.sellerName + ", Tax: " + tax + (listing.isGlobalListing ? ", Global listing" : ""));
        }
        return null;
    }

    public List<AuctionClaim> getPlayerClaims(EntityPlayer player) {
        PlayerData playerData = PlayerData.get(player);
        if (playerData == null) {
            return new ArrayList<AuctionClaim>();
        }
        return playerData.tradeData.getClaimsList();
    }

    public List<AuctionClaim> getPlayerClaims(UUID playerUUID) {
        EntityPlayerMP onlinePlayer = (EntityPlayerMP)PlayerDataController.getPlayerFromUUID(playerUUID);
        if (onlinePlayer != null) {
            return this.getPlayerClaims((EntityPlayer)onlinePlayer);
        }
        return new ArrayList<AuctionClaim>();
    }

    public List<AuctionClaim> getGlobalClaims(int page, int pageSize) {
        List<AuctionClaim> snapshot = this.getGlobalClaimsSnapshot();
        snapshot.sort((a, b) -> Long.compare(b.createdTime, a.createdTime));
        int safePage = Math.max(0, page);
        int safePageSize = Math.max(1, pageSize);
        int start = safePage * safePageSize;
        if (start >= snapshot.size()) {
            return new ArrayList<AuctionClaim>();
        }
        int end = Math.min(start + safePageSize, snapshot.size());
        return new ArrayList<AuctionClaim>(snapshot.subList(start, end));
    }

    public int getTotalGlobalClaims() {
        return this.getGlobalClaimsSnapshot().size();
    }

    public boolean hasGlobalClaims() {
        return this.getTotalGlobalClaims() > 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String claimGlobalItem(String claimId, EntityPlayer player) {
        String itemName;
        String listingId;
        List<AuctionClaim> list = this.globalClaims;
        synchronized (list) {
            AuctionClaim claim = this.getGlobalClaimInternal(claimId);
            if (claim == null) {
                return "Global claim not found.";
            }
            if (!claim.type.isItem()) {
                return "This global claim is not an item claim.";
            }
            if (claim.item == null) {
                return "Item data is missing.";
            }
            if (!this.canFitInInventory(player, claim.item)) {
                return "Not enough inventory space to claim this item.";
            }
            player.field_71071_by.func_70441_a(claim.item.func_77946_l());
            listingId = claim.listingId;
            itemName = claim.item.func_82833_r();
            this.claimAndRemoveGlobal(claim.id);
        }
        this.cleanupListingIfComplete(listingId);
        this.markDirty();
        this.logAuction(EnumAuctionLogAction.CLAIMED, player.func_70005_c_(), itemName, 0L, "Global item claim");
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String claimGlobalCurrency(String claimId, EntityPlayer player) {
        long currency;
        String listingId;
        List<AuctionClaim> list = this.globalClaims;
        synchronized (list) {
            AuctionClaim claim = this.getGlobalClaimInternal(claimId);
            if (claim == null) {
                return "Global claim not found.";
            }
            if (!claim.type.isCurrency()) {
                return "This global claim is not a currency claim.";
            }
            listingId = claim.listingId;
            currency = claim.currency;
            this.claimAndRemoveGlobal(claim.id);
        }
        this.cleanupListingIfComplete(listingId);
        this.markDirty();
        this.logAuction(EnumAuctionLogAction.CLAIMED, player.func_70005_c_(), ConfigMarket.CurrencyName, currency, "Global currency claim acknowledged (no balance change)");
        return null;
    }

    public String claimItem(String claimId, EntityPlayer player) {
        IPlayer iItemClaimer;
        AuctionEvent.ClaimEvent itemClaimEvent;
        PlayerData playerData = PlayerData.get(player);
        if (playerData == null) {
            return "Could not access player data.";
        }
        AuctionClaim claim = playerData.tradeData.getClaimInternal(claimId);
        if (claim == null) {
            return "Claim not found.";
        }
        if (claim.claimed) {
            return "Already claimed.";
        }
        if (!claim.type.isItem()) {
            return "This is not an item claim.";
        }
        if (claim.item == null) {
            return "Item data is missing.";
        }
        PlayerDataScript itemClaimHandler = ScriptController.Instance.getPlayerScripts(player);
        if (EventHooks.onAuctionClaim(itemClaimHandler, itemClaimEvent = new AuctionEvent.ClaimEvent(iItemClaimer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player), claim))) {
            return "Claim was prevented.";
        }
        if (!this.canFitInInventory(player, claim.item)) {
            return "Not enough inventory space to claim this item.";
        }
        player.field_71071_by.func_70441_a(claim.item.func_77946_l());
        playerData.tradeData.claimAndRemove(claimId);
        playerData.updateClient = true;
        playerData.save();
        this.cleanupListingIfComplete(claim.listingId);
        this.logAuction(EnumAuctionLogAction.CLAIMED, player.func_70005_c_(), claim.item.func_82833_r(), 0L, "Item claimed");
        return null;
    }

    public String claimCurrency(String claimId, EntityPlayer player) {
        IPlayer iCurrencyClaimer;
        AuctionEvent.ClaimEvent currencyClaimEvent;
        PlayerData playerData = PlayerData.get(player);
        if (playerData == null) {
            return "Could not access player data.";
        }
        AuctionClaim claim = playerData.tradeData.getClaimInternal(claimId);
        if (claim == null) {
            return "Claim not found.";
        }
        if (claim.claimed) {
            return "Already claimed.";
        }
        if (!claim.type.isCurrency()) {
            return "This is not a currency claim.";
        }
        PlayerDataScript currencyClaimHandler = ScriptController.Instance.getPlayerScripts(player);
        if (EventHooks.onAuctionClaim(currencyClaimHandler, currencyClaimEvent = new AuctionEvent.ClaimEvent(iCurrencyClaimer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)player), claim))) {
            return "Claim was prevented.";
        }
        if (!playerData.tradeData.deposit(claim.currency)) {
            return "Failed to deposit currency.";
        }
        playerData.tradeData.claimAndRemove(claimId);
        playerData.updateClient = true;
        playerData.save();
        this.cleanupListingIfComplete(claim.listingId);
        this.logAuction(EnumAuctionLogAction.CLAIMED, player.func_70005_c_(), ConfigMarket.CurrencyName, claim.currency, claim.type == EnumClaimType.REFUND ? "Refund claimed" : "Sale proceeds claimed");
        return null;
    }

    private boolean canFitInInventory(EntityPlayer player, ItemStack stack) {
        int remaining = stack.field_77994_a;
        int maxStack = stack.func_77976_d();
        ItemStack[] mainInventory = player.field_71071_by.field_70462_a;
        for (int i = 0; i < mainInventory.length; ++i) {
            int spaceInSlot;
            if (remaining <= 0) {
                return true;
            }
            ItemStack slot = mainInventory[i];
            if (slot == null) {
                remaining -= Math.min(remaining, maxStack);
                continue;
            }
            if (slot.func_77973_b() != stack.func_77973_b() || slot.func_77960_j() != stack.func_77960_j() || !ItemStack.func_77970_a((ItemStack)slot, (ItemStack)stack) || (spaceInSlot = maxStack - slot.field_77994_a) <= 0) continue;
            remaining -= Math.min(remaining, spaceInSlot);
        }
        return remaining <= 0;
    }

    private void cleanupListingIfComplete(String listingId) {
        if (listingId == null || listingId.isEmpty()) {
            return;
        }
        AuctionListing listing = this.listings.get(listingId);
        if (listing == null) {
            return;
        }
        if (listing.status == EnumAuctionStatus.ACTIVE) {
            return;
        }
        this.listings.remove(listingId);
        this.markDirty();
    }

    public int claimAll(EntityPlayer player) {
        int claimedCount = 0;
        List<AuctionClaim> playerClaims = this.getPlayerClaims(player);
        for (AuctionClaim claim : playerClaims) {
            String result = claim.type.isItem() ? this.claimItem(claim.id, player) : this.claimCurrency(claim.id, player);
            if (result != null) continue;
            ++claimedCount;
        }
        return claimedCount;
    }

    public List<AuctionListing> getActiveListings(AuctionFilter filter, int page, int pageSize) {
        ArrayList<AuctionListing> result = new ArrayList<AuctionListing>();
        for (AuctionListing listing : this.listings.values()) {
            if (listing.status != EnumAuctionStatus.ACTIVE || listing.isExpired()) continue;
            if (filter.hasSearchText()) {
                String sellerName;
                String itemName = listing.item != null ? listing.item.func_82833_r() : "";
                String string = sellerName = listing.sellerName != null ? listing.sellerName : "";
                if (!filter.matchesSearch(itemName, sellerName)) continue;
            }
            result.add(listing);
        }
        this.sortListings(result, filter.sortBy);
        int start = page * pageSize;
        int end = Math.min(start + pageSize, result.size());
        if (start >= result.size()) {
            return new ArrayList<AuctionListing>();
        }
        return new ArrayList<AuctionListing>(result.subList(start, end));
    }

    private void sortListings(List<AuctionListing> list, EnumAuctionSort sortBy) {
        Comparator<AuctionListing> comparator;
        switch (sortBy) {
            case ENDING_SOON: {
                comparator = Comparator.comparingLong(AuctionListing::getTimeRemaining);
                break;
            }
            case PRICE_LOW: {
                comparator = Comparator.comparingLong(AuctionListing::getEffectivePrice);
                break;
            }
            case PRICE_HIGH: {
                comparator = Comparator.comparingLong(AuctionListing::getEffectivePrice).reversed();
                break;
            }
            case MOST_BIDS: {
                comparator = Comparator.comparingInt(l -> -l.bidCount);
                break;
            }
            default: {
                comparator = Comparator.comparingLong(l -> -l.createdTime);
            }
        }
        list.sort(comparator);
    }

    public int getTotalActiveListings(AuctionFilter filter) {
        int count = 0;
        for (AuctionListing listing : this.listings.values()) {
            if (listing.status != EnumAuctionStatus.ACTIVE || listing.isExpired()) continue;
            if (filter.hasSearchText()) {
                String sellerName;
                String itemName = listing.item != null ? listing.item.func_82833_r() : "";
                String string = sellerName = listing.sellerName != null ? listing.sellerName : "";
                if (!filter.matchesSearch(itemName, sellerName)) continue;
            }
            ++count;
        }
        return count;
    }

    @Override
    public AuctionListing getListing(String listingId) {
        return this.listings.get(listingId);
    }

    public long getPlayerBalance(EntityPlayer player) {
        PlayerData playerData = PlayerData.get(player);
        if (playerData == null || playerData.tradeData == null) {
            return 0L;
        }
        return playerData.tradeData.getBalance();
    }

    public int getPlayerListingCount(UUID playerUUID) {
        Set<String> ids = this.playerListingIds.get(playerUUID);
        return ids != null ? ids.size() : 0;
    }

    public List<AuctionListing> getPlayerActiveListings(UUID playerUUID) {
        Set<String> listingIds = this.playerListingIds.get(playerUUID);
        if (listingIds == null || listingIds.isEmpty()) {
            return new ArrayList<AuctionListing>();
        }
        ArrayList<AuctionListing> result = new ArrayList<AuctionListing>();
        for (String listingId : listingIds) {
            AuctionListing listing = this.listings.get(listingId);
            if (listing == null || listing.status != EnumAuctionStatus.ACTIVE) continue;
            result.add(listing);
        }
        return result;
    }

    public List<AuctionListing> getPlayerActiveBids(UUID playerUUID) {
        Set<String> bidListingIds = this.playerBidIds.get(playerUUID);
        if (bidListingIds == null || bidListingIds.isEmpty()) {
            return new ArrayList<AuctionListing>();
        }
        ArrayList<AuctionListing> result = new ArrayList<AuctionListing>();
        for (String listingId : bidListingIds) {
            AuctionListing listing = this.listings.get(listingId);
            if (listing == null || listing.status != EnumAuctionStatus.ACTIVE || listing.highBidderUUID == null || !listing.highBidderUUID.equals(playerUUID)) continue;
            result.add(listing);
        }
        return result;
    }

    public int getPlayerTradeSlotCount(EntityPlayer player) {
        PlayerData playerData;
        Set<String> bidIds;
        UUID playerUUID = player.func_110124_au();
        int count = 0;
        Set<String> listingIds = this.playerListingIds.get(playerUUID);
        if (listingIds != null) {
            count += listingIds.size();
        }
        if ((bidIds = this.playerBidIds.get(playerUUID)) != null) {
            count += bidIds.size();
        }
        if ((playerData = PlayerData.get(player)) != null) {
            count += playerData.tradeData.getClaimCount();
        }
        return count;
    }

    public int getPlayerTradeSlotCount(UUID playerUUID) {
        Set<String> bidIds;
        int count = 0;
        Set<String> listingIds = this.playerListingIds.get(playerUUID);
        if (listingIds != null) {
            count += listingIds.size();
        }
        if ((bidIds = this.playerBidIds.get(playerUUID)) != null) {
            count += bidIds.size();
        }
        return count;
    }

    public int getMaxTradesForPlayer(EntityPlayer player) {
        UUID playerUUID = player.func_110124_au();
        Integer cached = this.playerMaxTradesCache.get(playerUUID);
        if (cached != null) {
            return cached;
        }
        int maxTrades = this.computeMaxTradesForPlayer(player);
        this.playerMaxTradesCache.put(playerUUID, maxTrades);
        return maxTrades;
    }

    private int computeMaxTradesForPlayer(EntityPlayer player) {
        if (CustomNpcsPermissions.hasCustomPermission(player, "customnpcs.auction.trades.*")) {
            return 45;
        }
        int highestAllowed = 0;
        for (int i = 1; i <= 45; ++i) {
            String perm = "customnpcs.auction.trades." + i;
            if (!CustomNpcsPermissions.hasCustomPermission(player, perm)) continue;
            highestAllowed = i;
        }
        if (highestAllowed == 0 || highestAllowed < ConfigMarket.DefaultMaxTrades) {
            highestAllowed = ConfigMarket.DefaultMaxTrades;
        }
        return Math.min(highestAllowed, 45);
    }

    public void refreshPlayerMaxTrades(EntityPlayer player) {
        UUID playerUUID = player.func_110124_au();
        int maxTrades = this.computeMaxTradesForPlayer(player);
        this.playerMaxTradesCache.put(playerUUID, maxTrades);
    }

    public void clearPlayerMaxTradesCache(UUID playerUUID) {
        this.playerMaxTradesCache.remove(playerUUID);
    }

    private void sendNotificationToPlayer(UUID playerUUID, EnumNotificationType type, String listingId, String message) {
        EntityPlayerMP player = (EntityPlayerMP)PlayerDataController.getPlayerFromUUID(playerUUID);
        if (player != null) {
            this.sendNotificationMessage(player, type, message);
        }
    }

    private void sendNotificationMessage(EntityPlayerMP player, EnumNotificationType type, String message) {
        EnumChatFormatting color;
        switch (type) {
            case AUCTION_WON: 
            case AUCTION_SOLD: {
                color = EnumChatFormatting.GREEN;
                break;
            }
            case AUCTION_OUTBID: {
                color = EnumChatFormatting.YELLOW;
                break;
            }
            case AUCTION_EXPIRED: {
                color = EnumChatFormatting.RED;
                break;
            }
            default: {
                color = EnumChatFormatting.GRAY;
            }
        }
        player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "[Auction] " + color + message));
    }

    public void onPlayerLogin(EntityPlayer player) {
        List<AuctionClaim> global;
        List<AuctionClaim> claims;
        int expired;
        this.refreshPlayerMaxTrades(player);
        PlayerData playerData = PlayerData.get(player);
        if (playerData != null && (expired = playerData.tradeData.processExpiredClaims()) > 0) {
            playerData.updateClient = true;
            playerData.save();
            this.logAuction(EnumAuctionLogAction.CLAIM_EXPIRED, player.func_70005_c_(), "Multiple", expired, expired + " claims expired on login");
        }
        if (!(claims = this.getPlayerClaims(player)).isEmpty()) {
            int itemClaims = 0;
            int currencyClaims = 0;
            long totalCurrency = 0L;
            for (AuctionClaim claim : claims) {
                if (claim.type.isItem()) {
                    ++itemClaims;
                    continue;
                }
                ++currencyClaims;
                totalCurrency += claim.currency;
            }
            StringBuilder msg = new StringBuilder();
            msg.append(EnumChatFormatting.GOLD).append("[Auction] ");
            msg.append(EnumChatFormatting.YELLOW).append("You have ");
            if (itemClaims > 0 && currencyClaims > 0) {
                msg.append(itemClaims).append(" item(s) and ");
                msg.append(String.format("%,d", totalCurrency)).append(" ").append(ConfigMarket.CurrencyName);
            } else if (itemClaims > 0) {
                msg.append(itemClaims).append(" item(s)");
            } else {
                msg.append(String.format("%,d", totalCurrency)).append(" ").append(ConfigMarket.CurrencyName);
            }
            msg.append(" to claim!");
            player.func_145747_a((IChatComponent)new ChatComponentText(msg.toString()));
        }
        if (CustomNpcsPermissions.hasCustomPermission(player, "customnpcs.global.auction") && !(global = this.getGlobalClaimsSnapshot()).isEmpty()) {
            int itemClaims = 0;
            int currencyClaims = 0;
            for (AuctionClaim claim : global) {
                if (claim.type.isItem()) {
                    ++itemClaims;
                    continue;
                }
                ++currencyClaims;
            }
            StringBuilder msg = new StringBuilder();
            msg.append(EnumChatFormatting.GOLD).append("[Auction] ");
            msg.append(EnumChatFormatting.AQUA).append("Global claims available: ");
            msg.append(EnumChatFormatting.GREEN).append(itemClaims).append(" item claim(s)");
            msg.append(EnumChatFormatting.GRAY).append(", ");
            msg.append(EnumChatFormatting.YELLOW).append(currencyClaims).append(" currency claim(s)");
            player.func_145747_a((IChatComponent)new ChatComponentText(msg.toString()));
        }
    }

    public void onPlayerLogout(UUID playerUUID) {
        this.clearPlayerMaxTradesCache(playerUUID);
    }

    private void logAuction(EnumAuctionLogAction action, String playerName, String itemName, long amount, String details) {
        if (!action.shouldLog()) {
            return;
        }
        String logMessage = String.format("[AUCTION:%s] Player: %s, Item: %s, Amount: %d, Details: %s", action.name(), playerName, itemName, amount, details);
        LogWriter.info(logMessage);
    }

    private void markDirty() {
        this.dirty.set(true);
    }

    @Override
    public void save() {
        if (!this.dirty.compareAndSet(true, false)) {
            return;
        }
        this.saveInternal();
    }

    public void saveAsync() {
        if (!this.dirty.compareAndSet(true, false)) {
            return;
        }
        if (!this.saving.compareAndSet(false, true)) {
            this.dirty.set(true);
            return;
        }
        NBTTagCompound compound = this.writeToNBT(new NBTTagCompound());
        CustomNPCsThreader.customNPCThread.execute(() -> {
            try {
                this.saveToFile(compound);
            }
            catch (Exception e) {
                LogWriter.error("Error saving auction data asynchronously", e);
                this.dirty.set(true);
            }
            finally {
                this.saving.set(false);
            }
        });
    }

    private void saveInternal() {
        try {
            NBTTagCompound compound = this.writeToNBT(new NBTTagCompound());
            this.saveToFile(compound);
        }
        catch (Exception e) {
            LogWriter.error("Error saving auction data", e);
        }
    }

    private void saveToFile(NBTTagCompound compound) {
        try {
            File saveDir = CustomNpcs.getWorldSaveDirectory();
            if (saveDir == null) {
                return;
            }
            File fileNew = new File(saveDir, "auction.dat_new");
            File fileOld = new File(saveDir, "auction.dat_old");
            File fileCurrent = new File(saveDir, "auction.dat");
            try (FileOutputStream fos = new FileOutputStream(fileNew);){
                CompressedStreamTools.func_74799_a((NBTTagCompound)compound, (OutputStream)fos);
            }
            if (fileOld.exists()) {
                fileOld.delete();
            }
            if (fileCurrent.exists()) {
                fileCurrent.renameTo(fileOld);
            }
            if (fileCurrent.exists()) {
                fileCurrent.delete();
            }
            fileNew.renameTo(fileCurrent);
            if (fileNew.exists()) {
                fileNew.delete();
            }
        }
        catch (Exception e) {
            LogWriter.error("Error saving auction data to file", e);
        }
    }

    public void load() {
        File file;
        this.listings.clear();
        this.globalClaims.clear();
        this.playerListingIds.clear();
        this.playerBidIds.clear();
        File saveDir = CustomNpcs.getWorldSaveDirectory();
        if (saveDir == null) {
            return;
        }
        this.filePath = saveDir.getAbsolutePath();
        try {
            file = new File(saveDir, "auction.dat");
            if (file.exists()) {
                this.loadFromFile(file);
                this.rebuildIndices();
                return;
            }
        }
        catch (Exception e) {
            LogWriter.error("Error loading auction.dat, trying backup", e);
        }
        try {
            file = new File(saveDir, "auction.dat_old");
            if (file.exists()) {
                this.loadFromFile(file);
                this.rebuildIndices();
                LogWriter.info("Loaded auction data from backup file");
            }
        }
        catch (Exception e) {
            LogWriter.error("Error loading auction.dat_old", e);
        }
    }

    private void rebuildIndices() {
        this.playerListingIds.clear();
        this.playerBidIds.clear();
        for (AuctionListing listing : this.listings.values()) {
            if (listing.status != EnumAuctionStatus.ACTIVE) continue;
            if (!listing.isGlobalListing && listing.sellerUUID != null) {
                this.addToPlayerListings(listing.sellerUUID, listing.id);
            }
            if (listing.highBidderUUID == null) continue;
            this.addToPlayerBids(listing.highBidderUUID, listing.id);
        }
        LogWriter.info("Rebuilt auction indices: " + this.playerListingIds.size() + " sellers, " + this.playerBidIds.size() + " bidders");
    }

    private void loadFromFile(File file) throws Exception {
        NBTTagCompound compound;
        try (FileInputStream fis = new FileInputStream(file);){
            compound = CompressedStreamTools.func_74796_a((InputStream)fis);
        }
        this.readFromNBT(compound);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagList listingsList = new NBTTagList();
        for (AuctionListing listing : this.listings.values()) {
            listingsList.func_74742_a((NBTBase)listing.writeToNBT(new NBTTagCompound()));
        }
        compound.func_74782_a("Listings", (NBTBase)listingsList);
        NBTTagList globalClaimsList = new NBTTagList();
        for (AuctionClaim claim : this.globalClaims) {
            if (claim == null || claim.claimed) continue;
            globalClaimsList.func_74742_a((NBTBase)claim.writeToNBT(new NBTTagCompound()));
        }
        compound.func_74782_a("GlobalClaims", (NBTBase)globalClaimsList);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.globalClaims.clear();
        NBTTagList listingsList = compound.func_150295_c("Listings", 10);
        for (int i = 0; i < listingsList.func_74745_c(); ++i) {
            AuctionListing listing = AuctionListing.fromNBT(listingsList.func_150305_b(i));
            this.listings.put(listing.id, listing);
        }
        if (compound.func_74764_b("GlobalClaims")) {
            NBTTagList claimsList = compound.func_150295_c("GlobalClaims", 10);
            for (int i = 0; i < claimsList.func_74745_c(); ++i) {
                AuctionClaim claim = AuctionClaim.fromNBT(claimsList.func_150305_b(i));
                if (claim.claimed) continue;
                this.globalClaims.add(claim);
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return ConfigMarket.AuctionEnabled;
    }

    @Override
    public IAuctionListing[] getActiveListings() {
        ArrayList<AuctionListing> active = new ArrayList<AuctionListing>();
        for (AuctionListing listing : this.listings.values()) {
            if (listing.status != EnumAuctionStatus.ACTIVE || listing.isExpired()) continue;
            active.add(listing);
        }
        return active.toArray(new IAuctionListing[0]);
    }

    @Override
    public IAuctionListing[] getListingsBySeller(String sellerUUID) {
        if (sellerUUID == null) {
            return new IAuctionListing[0];
        }
        try {
            UUID uuid = UUID.fromString(sellerUUID);
            List<AuctionListing> result = this.getPlayerActiveListings(uuid);
            return result.toArray(new IAuctionListing[0]);
        }
        catch (IllegalArgumentException e) {
            return new IAuctionListing[0];
        }
    }

    @Override
    public IAuctionListing[] getListingsByBidder(String bidderUUID) {
        if (bidderUUID == null) {
            return new IAuctionListing[0];
        }
        try {
            UUID uuid = UUID.fromString(bidderUUID);
            List<AuctionListing> result = this.getPlayerActiveBids(uuid);
            return result.toArray(new IAuctionListing[0]);
        }
        catch (IllegalArgumentException e) {
            return new IAuctionListing[0];
        }
    }

    @Override
    public int getActiveListingCount() {
        int count = 0;
        for (AuctionListing listing : this.listings.values()) {
            if (listing.status != EnumAuctionStatus.ACTIVE || listing.isExpired()) continue;
            ++count;
        }
        return count;
    }

    @Override
    public IAuctionListing createListing(IPlayer<?> player, IItemStack item, long startingPrice, long buyoutPrice) {
        ItemStack mcItem;
        if (player == null || item == null) {
            return null;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        String result = this.createListing(entityPlayer, mcItem = item.getMCItemStack(), startingPrice, buyoutPrice);
        if (result != null) {
            return null;
        }
        Set<String> playerListings = this.playerListingIds.get(entityPlayer.func_110124_au());
        if (playerListings != null && !playerListings.isEmpty()) {
            long latestTime = 0L;
            AuctionListing latestListing = null;
            for (String id : playerListings) {
                AuctionListing listing = this.listings.get(id);
                if (listing == null || listing.createdTime <= latestTime) continue;
                latestTime = listing.createdTime;
                latestListing = listing;
            }
            return latestListing;
        }
        return null;
    }

    @Override
    public String placeBid(String listingId, IPlayer<?> player, long amount) {
        if (player == null) {
            return "Player is null";
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        return this.placeBid(listingId, entityPlayer, amount);
    }

    @Override
    public String buyout(String listingId, IPlayer<?> player) {
        if (player == null) {
            return "Player is null";
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        return this.buyout(listingId, entityPlayer);
    }

    @Override
    public String cancelListing(String listingId, IPlayer<?> player, boolean isAdmin) {
        if (player == null) {
            return "Player is null";
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        return this.cancelListing(listingId, entityPlayer, isAdmin);
    }

    @Override
    public long getListingFee() {
        return ConfigMarket.ListingFee;
    }

    @Override
    public double getSalesTaxPercent() {
        return ConfigMarket.SalesTaxPercent;
    }

    @Override
    public double getMinBidIncrementPercent() {
        return ConfigMarket.MinBidIncrementPercent;
    }

    @Override
    public int getAuctionDurationHours() {
        return ConfigMarket.AuctionDurationHours;
    }

    @Override
    public int getSnipeProtectionMinutes() {
        return ConfigMarket.SnipeProtectionMinutes;
    }

    @Override
    public String getCurrencyName() {
        return ConfigMarket.CurrencyName;
    }

    @Override
    public long getMinimumListingPrice() {
        return ConfigMarket.MinimumListingPrice;
    }

    @Override
    public IAuctionListing[] searchListings(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            return this.getActiveListings();
        }
        String search = searchText.toLowerCase().trim();
        ArrayList<AuctionListing> result = new ArrayList<AuctionListing>();
        for (AuctionListing listing : this.listings.values()) {
            String sellerName;
            if (listing.status != EnumAuctionStatus.ACTIVE || listing.isExpired()) continue;
            String itemName = listing.item != null ? listing.item.func_82833_r().toLowerCase() : "";
            String string = sellerName = listing.sellerName != null ? listing.sellerName.toLowerCase() : "";
            if (!itemName.contains(search) && !sellerName.contains(search)) continue;
            result.add(listing);
        }
        return result.toArray(new IAuctionListing[0]);
    }

    public List<AuctionListing> getAllListings() {
        return new ArrayList<AuctionListing>(this.listings.values());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String adminStopListingToGlobal(String listingId, EntityPlayer admin, boolean cancelCompletely) {
        AuctionListing listing = this.listings.get(listingId);
        if (listing == null) {
            return "Listing not found.";
        }
        if (admin == null) {
            return "Admin player is missing.";
        }
        AuctionListing auctionListing = listing;
        synchronized (auctionListing) {
            String itemName;
            if (!listing.status.canCancel()) {
                return "This auction cannot be managed.";
            }
            String string = itemName = listing.item != null ? listing.item.func_82833_r() : "Unknown Item";
            if (listing.hasBids() && listing.highBidderUUID != null) {
                AuctionClaim refundClaim = AuctionClaim.createRefundClaim(listing.highBidderUUID, listing.highBidderName, listing.id, listing.currentBid, itemName, listing.sellerName, null);
                this.addClaimToPlayer(listing.highBidderUUID, refundClaim);
                this.removeFromPlayerBids(listing.highBidderUUID, listing.id);
            }
            if (listing.item != null) {
                AuctionClaim itemClaim;
                if (cancelCompletely || listing.sellerUUID == null) {
                    itemClaim = AuctionClaim.createItemReturnedClaim(null, "Global", listing.id, listing.item);
                    this.addGlobalClaim(itemClaim);
                } else {
                    itemClaim = AuctionClaim.createItemReturnedClaim(listing.sellerUUID, listing.sellerName, listing.id, listing.item);
                    this.addClaimToPlayer(listing.sellerUUID, itemClaim);
                }
            }
            if (!listing.isGlobalListing && listing.sellerUUID != null) {
                this.removeFromPlayerListings(listing.sellerUUID, listing.id);
            }
            listing.status = cancelCompletely ? EnumAuctionStatus.CANCELLED : EnumAuctionStatus.ENDED;
            this.markDirty();
            this.logAuction(EnumAuctionLogAction.CANCELLED, admin.func_70005_c_(), itemName, listing.currentBid, (cancelCompletely ? "Admin cancelled to global claims" : "Admin stopped to owner claims") + ", OriginalSeller: " + listing.sellerName);
        }
        return null;
    }

    public void adminCancelListing(String listingId, EntityPlayer admin, String reason) {
        AuctionListing listing = this.listings.get(listingId);
        String itemName = listing != null && listing.item != null ? listing.item.func_82833_r() : "Unknown";
        this.adminStopListingToGlobal(listingId, admin, true);
        if (reason != null && !reason.isEmpty()) {
            this.logAuction(EnumAuctionLogAction.CANCELLED, admin.func_70005_c_(), itemName, 0L, "Admin cancel - Reason: " + reason);
        }
    }

    public void clearEndedListings() {
        this.listings.entrySet().removeIf(entry -> ((AuctionListing)entry.getValue()).status == EnumAuctionStatus.CLAIMED || ((AuctionListing)entry.getValue()).status == EnumAuctionStatus.ENDED || ((AuctionListing)entry.getValue()).status == EnumAuctionStatus.CANCELLED);
        this.rebuildIndices();
        this.markDirty();
    }

    public boolean isPlayerSeller(String listingId, UUID playerUUID) {
        AuctionListing listing = this.listings.get(listingId);
        return listing != null && listing.isSeller(playerUUID);
    }

    public void reloadBlacklist() {
        AuctionBlacklist.reload();
    }

    public boolean isItemBlacklisted(ItemStack item) {
        return AuctionBlacklist.isBlacklisted(item);
    }
}

