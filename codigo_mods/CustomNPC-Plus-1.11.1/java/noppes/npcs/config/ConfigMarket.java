/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraftforge.common.config.Configuration
 *  org.apache.logging.log4j.Level
 */
package noppes.npcs.config;

import cpw.mods.fml.common.FMLLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class ConfigMarket {
    public static Configuration config;
    public static final String CURRENCY = "Market.Currency";
    public static final String AUCTION = "Market.Auction";
    public static final String AUCTION_BLACKLIST = "Market.Auction.Blacklist";
    public static final String AUCTION_LOGGING = "Market.Auction.Logging";
    public static final int MAX_TRADE_SLOTS = 45;
    public static final int MIN_TRADE_SLOTS = 1;
    public static boolean UseVault;
    public static String CurrencyName;
    public static long StartingBalance;
    public static long MaxBalance;
    public static boolean AuctionEnabled;
    public static int AuctionDurationHours;
    public static long ListingFee;
    public static long MinimumListingPrice;
    public static double SalesTaxPercent;
    public static int DefaultMaxTrades;
    public static int SnipeProtectionMinutes;
    public static int ClaimExpirationDays;
    public static double MinBidIncrementPercent;
    public static double CancellationPenaltyPercent;
    public static boolean BlacklistEnabled;
    public static String[] BlacklistedItems;
    public static String[] BlacklistedMods;
    public static String[] BlacklistedNBTTags;
    public static boolean AuctionLoggingEnabled;
    public static boolean LogAuctionCreated;
    public static boolean LogAuctionBid;
    public static boolean LogAuctionBuyout;
    public static boolean LogAuctionSold;
    public static boolean LogAuctionExpired;
    public static boolean LogAuctionCancelled;
    public static boolean LogAuctionClaimed;

    public static void init(File configFile) {
        config = new Configuration(configFile);
        try {
            config.load();
            config.setCategoryComment(CURRENCY, "CNPC+ Currency system settings. When UseVault is enabled, all currency operations use Vault instead of the built-in system.");
            UseVault = config.get(CURRENCY, "Use Vault", false, "If true, use Vault API for all currency operations instead of CNPC+ built-in currency. The built-in currency data will be preserved but unused.").getBoolean(false);
            CurrencyName = config.get(CURRENCY, "Currency Name", "Coins", "Display name for the currency (e.g. 'Coins', 'Gold', 'Credits')").getString();
            StartingBalance = ConfigMarket.parseLongConfig(config.get(CURRENCY, "Starting Balance", "0", "Starting currency balance for new players").getString(), 0L);
            MaxBalance = ConfigMarket.parseLongConfig(config.get(CURRENCY, "Max Balance", String.valueOf(Long.MAX_VALUE), "Maximum currency balance a player can have").getString(), Long.MAX_VALUE);
            if (MaxBalance < 0L) {
                MaxBalance = Long.MAX_VALUE;
            }
            config.setCategoryComment(AUCTION, "Auction system settings. The Auction allows players to list items for sale with bidding and buyout options.");
            AuctionEnabled = config.get(AUCTION, "Enable Auction", true, "Enable the Auction system").getBoolean(true);
            AuctionDurationHours = config.get(AUCTION, "Auction Duration Hours", 24, "Default duration for auctions in hours").getInt(24);
            if (AuctionDurationHours < 1) {
                AuctionDurationHours = 1;
            }
            if ((ListingFee = ConfigMarket.parseLongConfig(config.get(AUCTION, "Listing Fee", "10", "Flat fee charged when creating a listing").getString(), 10L)) < 0L) {
                ListingFee = 0L;
            }
            if ((MinimumListingPrice = ConfigMarket.parseLongConfig(config.get(AUCTION, "Minimum Listing Price", "1", "Minimum starting price for auction listings").getString(), 1L)) < 1L) {
                MinimumListingPrice = 1L;
            }
            SalesTaxPercent = config.get(AUCTION, "Sales Tax Percent", 0.05, "Percentage of sale price taken as tax (0.05 = 5%). Tax is deleted as a currency sink.").getDouble(0.05);
            SalesTaxPercent = Math.max(0.0, Math.min(1.0, SalesTaxPercent));
            int maxTrades = config.get(AUCTION, "Default Max Trades", 8, "Default maximum number of trade slots per player (listings + bids + claims). Min: 1, Max: 45. Players can have more via customnpcs.auction.trades.X permissions.").getInt(8);
            DefaultMaxTrades = Math.max(1, Math.min(45, maxTrades));
            SnipeProtectionMinutes = config.get(AUCTION, "Snipe Protection Minutes", 2, "When a bid is placed with less than this many minutes remaining, the auction is extended to this duration").getInt(2);
            if (SnipeProtectionMinutes < 0) {
                SnipeProtectionMinutes = 0;
            }
            if ((ClaimExpirationDays = config.get(AUCTION, "Claim Expiration Days", 20, "Number of days before unclaimed items/currency are deleted").getInt(20)) < 1) {
                ClaimExpirationDays = 1;
            }
            MinBidIncrementPercent = config.get(AUCTION, "Min Bid Increment Percent", 0.05, "Minimum bid increment as a percentage of current bid (0.05 = 5%)").getDouble(0.05);
            MinBidIncrementPercent = Math.max(0.0, Math.min(1.0, MinBidIncrementPercent));
            CancellationPenaltyPercent = config.get(AUCTION, "Cancellation Penalty Percent", 0.1, "Percentage of current bid taken as penalty when seller cancels an auction with bids (0.10 = 10%)").getDouble(0.1);
            CancellationPenaltyPercent = Math.max(0.0, Math.min(1.0, CancellationPenaltyPercent));
            config.setCategoryComment(AUCTION_BLACKLIST, "Item Blacklist settings for the Auction House.\nPrevents specific items, mods, or items with certain NBT tags from being listed.\n\nITEM FORMAT: Use 'modid:itemname' format (e.g., 'minecraft:bedrock', 'customnpcs:npcWand')\nWILDCARDS: Use * for wildcards (e.g., 'customnpcs:npc*' blocks all items starting with 'npc')\nMOD FORMAT: Use just the mod ID (e.g., 'projecte' blocks all items from that mod)\nNBT FORMAT: Use the NBT tag key name (e.g., 'AdminOnly' blocks items with that tag)");
            BlacklistEnabled = config.get(AUCTION_BLACKLIST, "Enable Blacklist", true, "Enable item blacklist checking when creating listings").getBoolean(true);
            BlacklistedItems = config.get(AUCTION_BLACKLIST, "Blacklisted Items", new String[]{"minecraft:bedrock", "minecraft:command_block", "customnpcs:npcWand", "customnpcs:npcMobCloner", "customnpcs:npcScripter", "customnpcs:npcMovingPath", "customnpcs:npcMounter", "customnpcs:npcTeleporter", "customnpcs:npcTool", "customnpcs:npcSoulstoneFilled"}, "Items that cannot be listed on the Auction House.\nFormat: modid:itemname (supports * wildcards)\nExample: 'minecraft:diamond_sword' or 'customnpcs:npc*'").getStringList();
            BlacklistedMods = config.get(AUCTION_BLACKLIST, "Blacklisted Mods", new String[0], "All items from these mods are blocked from the Auction House.\nFormat: modid (e.g., 'projecte', 'thaumcraft')").getStringList();
            BlacklistedNBTTags = config.get(AUCTION_BLACKLIST, "Blacklisted NBT Tags", new String[0], "Items containing any of these NBT tag keys are blocked.\nChecks the root level of the item's NBT compound.\nExample: 'AdminOnly', 'CreativeMode'").getStringList();
            config.setCategoryComment(AUCTION_LOGGING, "Auction logging settings. Enable specific log types to track auction activity.");
            AuctionLoggingEnabled = config.get(AUCTION_LOGGING, "Enable Auction Logging", false, "Master switch for auction logging. If false, no auction events are logged.").getBoolean(false);
            LogAuctionCreated = config.get(AUCTION_LOGGING, "Log Created", true, "Log when auctions are created").getBoolean(true);
            LogAuctionBid = config.get(AUCTION_LOGGING, "Log Bid", true, "Log when bids are placed").getBoolean(true);
            LogAuctionBuyout = config.get(AUCTION_LOGGING, "Log Buyout", true, "Log when auctions are bought out").getBoolean(true);
            LogAuctionSold = config.get(AUCTION_LOGGING, "Log Sold", true, "Log when auctions end with a winner").getBoolean(true);
            LogAuctionExpired = config.get(AUCTION_LOGGING, "Log Expired", true, "Log when auctions expire with no bids").getBoolean(true);
            LogAuctionCancelled = config.get(AUCTION_LOGGING, "Log Cancelled", true, "Log when auctions are cancelled").getBoolean(true);
            LogAuctionClaimed = config.get(AUCTION_LOGGING, "Log Claimed", true, "Log when claims are collected").getBoolean(true);
            config.setCategoryPropertyOrder(CURRENCY, new ArrayList<String>(Arrays.asList("Use Vault", "Currency Name", "Starting Balance", "Max Balance")));
            config.setCategoryPropertyOrder(AUCTION, new ArrayList<String>(Arrays.asList("Enable Auction", "Auction Duration Hours", "Listing Fee", "Minimum Listing Price", "Sales Tax Percent", "Default Max Trades", "Snipe Protection Minutes", "Claim Expiration Days", "Min Bid Increment Percent", "Cancellation Penalty Percent")));
            config.setCategoryPropertyOrder(AUCTION_BLACKLIST, new ArrayList<String>(Arrays.asList("Enable Blacklist", "Blacklisted Items", "Blacklisted Mods", "Blacklisted NBT Tags")));
            config.setCategoryPropertyOrder(AUCTION_LOGGING, new ArrayList<String>(Arrays.asList("Enable Auction Logging", "Log Created", "Log Bid", "Log Buyout", "Log Sold", "Log Expired", "Log Cancelled", "Log Claimed")));
        }
        catch (Exception e) {
            FMLLog.log((Level)Level.ERROR, (Throwable)e, (String)"CNPC+ has had a problem loading its market configuration", (Object[])new Object[0]);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    private static long parseLongConfig(String value, long defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Long.parseLong(value.trim());
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    static {
        UseVault = false;
        CurrencyName = "Coins";
        StartingBalance = 0L;
        MaxBalance = Long.MAX_VALUE;
        AuctionEnabled = true;
        AuctionDurationHours = 24;
        ListingFee = 10L;
        MinimumListingPrice = 1L;
        SalesTaxPercent = 0.05;
        DefaultMaxTrades = 8;
        SnipeProtectionMinutes = 2;
        ClaimExpirationDays = 20;
        MinBidIncrementPercent = 0.05;
        CancellationPenaltyPercent = 0.1;
        BlacklistEnabled = true;
        BlacklistedItems = new String[0];
        BlacklistedMods = new String[0];
        BlacklistedNBTTags = new String[0];
        AuctionLoggingEnabled = false;
        LogAuctionCreated = true;
        LogAuctionBid = true;
        LogAuctionBuyout = true;
        LogAuctionSold = true;
        LogAuctionExpired = true;
        LogAuctionCancelled = true;
        LogAuctionClaimed = true;
    }
}

