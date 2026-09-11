/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.command;

import java.util.List;
import java.util.UUID;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.command.auction.BlacklistSubCommand;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.controllers.AuctionController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.controllers.data.PlayerData;

public class AuctionCommand
extends CommandKamkeelBase {
    private static final int COMMAND_PAGE_SIZE = 10;

    public AuctionCommand() {
        this.registerNestedCommand(new BlacklistSubCommand());
    }

    public String func_71517_b() {
        return "auction";
    }

    @Override
    public String getDescription() {
        return "Auction operations";
    }

    @CommandKamkeelBase.SubCommand(desc="List active auctions", usage="[page]")
    public void list(ICommandSender sender, String[] args) throws CommandException {
        if (!ConfigMarket.AuctionEnabled) {
            ColorUtil.sendError(sender, "Auction system is disabled");
            return;
        }
        AuctionController controller = AuctionController.getInstance();
        if (controller == null) {
            ColorUtil.sendError(sender, "Auction system is not available");
            return;
        }
        int page = 1;
        if (args.length > 0) {
            try {
                page = Integer.parseInt(args[0]);
            }
            catch (NumberFormatException ex) {
                ColorUtil.sendError(sender, "Invalid page number: " + args[0]);
                return;
            }
        }
        page = Math.max(1, page);
        List<AuctionListing> listings = controller.getActiveListings(null, page - 1, 10);
        int total = controller.getTotalActiveListings(null);
        int totalPages = Math.max(1, (int)Math.ceil((double)total / 10.0));
        if (listings.isEmpty()) {
            ColorUtil.sendMessage(sender, "\u00a77No active auctions found");
            return;
        }
        ColorUtil.sendMessage(sender, String.format("\u00a76=== Active Auctions (Page %d/%d) ===", page, totalPages));
        for (AuctionListing listing : listings) {
            String itemName = listing.item != null ? listing.item.func_82833_r() : "Unknown";
            String price = listing.hasBids() ? String.format("%,d (bid)", listing.currentBid) : String.format("%,d (start)", listing.startingPrice);
            ColorUtil.sendMessage(sender, String.format("\u00a7e%s \u00a77by \u00a7b%s\u00a77 - \u00a76%s %s", itemName, listing.sellerName, price, ConfigMarket.CurrencyName));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="View player's auction activity", usage="<player>")
    public void view(ICommandSender sender, String[] args) throws CommandException {
        if (!ConfigMarket.AuctionEnabled) {
            ColorUtil.sendError(sender, "Auction system is disabled");
            return;
        }
        AuctionController controller = AuctionController.getInstance();
        if (controller == null) {
            ColorUtil.sendError(sender, "Auction system is not available");
            return;
        }
        if (args.length < 1) {
            throw new CommandException("Usage: /auction view <player>", new Object[0]);
        }
        String playername = args[0];
        List<PlayerData> dataList = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (dataList.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        for (PlayerData playerdata : dataList) {
            String itemName;
            UUID playerUUID = UUID.fromString(playerdata.uuid);
            List<AuctionListing> activeListings = controller.getPlayerActiveListings(playerUUID);
            List<AuctionListing> activeBids = controller.getPlayerActiveBids(playerUUID);
            int claimCount = controller.getPlayerClaims(playerUUID).size();
            ColorUtil.sendMessage(sender, String.format("\u00a76=== Auction Activity for %s ===", playerdata.playername));
            ColorUtil.sendMessage(sender, String.format("\u00a77Active Listings: \u00a7e%d", activeListings.size()));
            ColorUtil.sendMessage(sender, String.format("\u00a77Active Bids: \u00a7e%d", activeBids.size()));
            ColorUtil.sendMessage(sender, String.format("\u00a77Pending Claims: \u00a7e%d", claimCount));
            if (!activeListings.isEmpty()) {
                ColorUtil.sendMessage(sender, "\u00a7a--- Selling ---");
                for (AuctionListing listing : activeListings) {
                    itemName = listing.item != null ? listing.item.func_82833_r() : "Unknown";
                    String price = listing.hasBids() ? String.format("%,d (bid)", listing.currentBid) : String.format("%,d (start)", listing.startingPrice);
                    ColorUtil.sendMessage(sender, String.format("  \u00a7e%s \u00a77- \u00a76%s", itemName, price));
                }
            }
            if (activeBids.isEmpty()) continue;
            ColorUtil.sendMessage(sender, "\u00a7e--- Bidding ---");
            for (AuctionListing bid : activeBids) {
                itemName = bid.item != null ? bid.item.func_82833_r() : "Unknown";
                ColorUtil.sendMessage(sender, String.format("  \u00a7e%s \u00a77- \u00a76%,d %s", itemName, bid.currentBid, ConfigMarket.CurrencyName));
            }
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Open the Auction GUI", usage="", permission=0)
    public void open(ICommandSender sender, String[] args) throws CommandException {
        if (!ConfigMarket.AuctionEnabled) {
            ColorUtil.sendError(sender, "Auction system is disabled");
            return;
        }
        if (!(sender instanceof EntityPlayer)) {
            ColorUtil.sendError(sender, "This command can only be used by players");
            return;
        }
        EntityPlayer player = (EntityPlayer)sender;
        NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerAuction, null);
        ColorUtil.sendResult(sender, "Opening Auction...");
    }

    public List func_71516_a(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return AuctionCommand.func_71530_a((String[])args, (String[])this.getAllSubCommandNames());
        }
        List nestedCompletions = this.getNestedTabCompletions(sender, args);
        if (nestedCompletions != null) {
            return nestedCompletions;
        }
        return null;
    }
}

