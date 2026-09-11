/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 */
package noppes.npcs.client.gui.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import noppes.npcs.client.AuctionClientConfig;
import noppes.npcs.client.gui.global.GuiNpcManageAuction;
import noppes.npcs.client.gui.player.GuiAuctionListing;
import noppes.npcs.client.gui.player.GuiAuctionSell;
import noppes.npcs.client.gui.player.GuiAuctionTrades;
import noppes.npcs.constants.EnumClaimType;
import noppes.npcs.containers.ContainerAuctionListing;
import noppes.npcs.containers.ContainerAuctionSell;
import noppes.npcs.containers.ContainerAuctionTrades;
import noppes.npcs.containers.ContainerManageAuction;
import noppes.npcs.containers.SlotAuctionDisplay;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.util.AuctionFormatUtil;

@SideOnly(value=Side.CLIENT)
public class AuctionTooltipHandler {
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (event.itemStack == null) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc == null || mc.field_71462_r == null) {
            return;
        }
        GuiScreen screen = mc.field_71462_r;
        if (screen instanceof GuiAuctionListing) {
            AuctionTooltipHandler.addListingTooltip((GuiAuctionListing)screen, event.itemStack, event.toolTip);
        } else if (screen instanceof GuiAuctionTrades) {
            AuctionTooltipHandler.addTradesTooltip((GuiAuctionTrades)screen, event.itemStack, event.toolTip);
        } else if (screen instanceof GuiAuctionSell) {
            AuctionTooltipHandler.addSellTooltip((GuiAuctionSell)screen, event.itemStack, event.toolTip);
        } else if (screen instanceof GuiNpcManageAuction) {
            AuctionTooltipHandler.addManageAuctionTooltip((GuiNpcManageAuction)screen, event.itemStack, event.toolTip);
        }
    }

    private static Slot findHoveredSlot(GuiContainer gui, ItemStack stack) {
        if (stack == null) {
            return null;
        }
        for (Object obj : gui.field_147002_h.field_75151_b) {
            Slot slot = (Slot)obj;
            if (!slot.func_75216_d() || slot.func_75211_c() != stack) continue;
            return slot;
        }
        return null;
    }

    private static void addListingTooltip(GuiAuctionListing gui, ItemStack stack, List<String> tooltip) {
        Slot slot = AuctionTooltipHandler.findHoveredSlot(gui, stack);
        if (slot == null || !(slot instanceof SlotAuctionDisplay)) {
            return;
        }
        ContainerAuctionListing container = gui.getListingContainer();
        if (container == null) {
            return;
        }
        int displayIndex = slot.getSlotIndex();
        AuctionListing listing = container.getListingAt(displayIndex);
        if (listing == null) {
            return;
        }
        tooltip.add("");
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.seller").replace("%s", EnumChatFormatting.WHITE + listing.sellerName));
        if (listing.hasBids()) {
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.currentBid").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(listing.currentBid)));
            tooltip.add(EnumChatFormatting.GRAY + String.format(StatCollector.func_74838_a((String)"auction.bids"), listing.bidCount));
        } else {
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.startingPrice").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(listing.startingPrice)));
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.noBids"));
        }
        if (listing.hasBuyout()) {
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.buyout").replace("%s", EnumChatFormatting.GREEN + AuctionFormatUtil.formatCurrencyWithName(listing.buyoutPrice)));
        }
        long timeRemaining = listing.getTimeRemaining();
        String timeText = StatCollector.func_74838_a((String)"auction.timeLeft").replace("%s", AuctionFormatUtil.formatTimeRemaining(timeRemaining));
        tooltip.add((AuctionFormatUtil.isTimeUrgent(timeRemaining) ? EnumChatFormatting.RED : EnumChatFormatting.WHITE) + timeText);
    }

    private static void addTradesTooltip(GuiAuctionTrades gui, ItemStack stack, List<String> tooltip) {
        Slot slot = AuctionTooltipHandler.findHoveredSlot(gui, stack);
        if (slot == null || !(slot instanceof SlotAuctionDisplay)) {
            return;
        }
        ContainerAuctionTrades container = gui.getTradesContainer();
        if (container == null) {
            return;
        }
        int displayIndex = slot.getSlotIndex();
        AuctionListing listing = container.getListingAt(displayIndex);
        if (listing != null) {
            if (container.isSellingAt(displayIndex)) {
                AuctionTooltipHandler.addSellingTooltip(listing, tooltip);
            } else if (container.isBiddingAt(displayIndex)) {
                AuctionTooltipHandler.addBiddingTooltip(listing, tooltip);
            }
            return;
        }
        AuctionClaim claim = container.getClaimAt(displayIndex);
        if (claim != null) {
            AuctionTooltipHandler.addClaimTooltip(claim, tooltip);
        }
    }

    private static void addSellingTooltip(AuctionListing listing, List<String> tooltip) {
        tooltip.add("");
        tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.trades.selling"));
        if (listing.hasBids()) {
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.currentBid").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(listing.currentBid)));
            tooltip.add(EnumChatFormatting.GRAY + String.format(StatCollector.func_74838_a((String)"auction.bids"), listing.bidCount));
        } else {
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.noBids"));
        }
        long timeRemaining = listing.getTimeRemaining();
        String timeText = StatCollector.func_74838_a((String)"auction.timeLeft").replace("%s", AuctionFormatUtil.formatTimeRemaining(timeRemaining));
        tooltip.add((AuctionFormatUtil.isTimeUrgent(timeRemaining) ? EnumChatFormatting.RED : EnumChatFormatting.WHITE) + timeText);
        tooltip.add("");
        tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.trades.rightClickToCancel"));
    }

    private static void addBiddingTooltip(AuctionListing listing, List<String> tooltip) {
        tooltip.add("");
        tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.trades.bidding"));
        tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.currentBid").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(listing.currentBid)));
        tooltip.add(EnumChatFormatting.GRAY + String.format(StatCollector.func_74838_a((String)"auction.bids"), listing.bidCount));
        long timeRemaining = listing.getTimeRemaining();
        String timeText = StatCollector.func_74838_a((String)"auction.timeLeft").replace("%s", AuctionFormatUtil.formatTimeRemaining(timeRemaining));
        tooltip.add((AuctionFormatUtil.isTimeUrgent(timeRemaining) ? EnumChatFormatting.RED : EnumChatFormatting.WHITE) + timeText);
        tooltip.add("");
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.trades.clickToRebid"));
    }

    private static void addClaimTooltip(AuctionClaim claim, List<String> tooltip) {
        int expirationDays;
        long daysLeft;
        tooltip.add("");
        switch (claim.type) {
            case ITEM: {
                if (claim.isReturned) {
                    tooltip.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"auction.trades.expired"));
                    break;
                }
                tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.trades.won"));
                break;
            }
            case CURRENCY: {
                tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.trades.sold"));
                if (claim.itemName != null && !claim.itemName.isEmpty()) {
                    tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.soldItem").replace("%s", EnumChatFormatting.WHITE + claim.itemName));
                }
                if (claim.otherPlayerName == null || claim.otherPlayerName.isEmpty()) break;
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.buyer").replace("%s", EnumChatFormatting.WHITE + claim.otherPlayerName));
                break;
            }
            case REFUND: {
                tooltip.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"auction.trades.outbid"));
                if (claim.otherPlayerName == null || claim.otherPlayerName.isEmpty()) break;
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.outbidBy").replace("%s", EnumChatFormatting.WHITE + claim.otherPlayerName));
            }
        }
        if (claim.type.isCurrency()) {
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.claim.amount").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(claim.currency)));
        }
        if ((daysLeft = claim.getDaysUntilExpiration(expirationDays = AuctionClientConfig.getClaimExpirationDays())) <= 1L) {
            tooltip.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"auction.claim.expiresSoon"));
        } else {
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.expires").replace("%s", "" + daysLeft));
        }
        tooltip.add("");
        if (claim.type == EnumClaimType.REFUND && claim.item != null) {
            tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.trades.leftClickRebid"));
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.trades.rightClickRefund"));
        } else {
            tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.claim.clickToClaim"));
        }
    }

    private static void addSellTooltip(GuiAuctionSell gui, ItemStack stack, List<String> tooltip) {
        Slot slot = AuctionTooltipHandler.findHoveredSlot(gui, stack);
        if (slot == null) {
            return;
        }
        ContainerAuctionSell container = (ContainerAuctionSell)gui.field_147002_h;
        if (container == null) {
            return;
        }
        if (container.isSellSlot(slot.field_75222_d)) {
            tooltip.add("");
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.sell.leftClear"));
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.sell.rightRemove"));
        } else if (container.isPlayerInventorySlot(slot.field_75222_d)) {
            tooltip.add("");
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.sell.leftAdd"));
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.sell.rightAddOne"));
        }
    }

    private static void addManageAuctionTooltip(GuiNpcManageAuction gui, ItemStack stack, List<String> tooltip) {
        Slot slot = AuctionTooltipHandler.findHoveredSlot(gui, stack);
        if (slot == null) {
            return;
        }
        ContainerManageAuction container = gui.getManageContainer();
        if (container == null) {
            return;
        }
        GuiNpcManageAuction.Tab tab = gui.getActiveTab();
        if (tab == GuiNpcManageAuction.Tab.LISTINGS && container.isDisplaySlot(slot.field_75222_d)) {
            int displayIndex = container.toDisplayIndex(slot.field_75222_d);
            AuctionListing listing = container.getListingAtDisplay(displayIndex);
            if (listing == null) {
                return;
            }
            tooltip.add("");
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.seller").replace("%s", EnumChatFormatting.WHITE + listing.sellerName));
            if (listing.hasBids()) {
                tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.currentBid").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(listing.currentBid)));
                tooltip.add(EnumChatFormatting.GRAY + String.format(StatCollector.func_74838_a((String)"auction.bids"), listing.bidCount));
            } else {
                tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.startingPrice").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(listing.startingPrice)));
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.noBids"));
            }
            if (listing.hasBuyout()) {
                tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.buyout").replace("%s", EnumChatFormatting.GREEN + AuctionFormatUtil.formatCurrencyWithName(listing.buyoutPrice)));
            }
            long timeRemaining = listing.getTimeRemaining();
            String timeText = StatCollector.func_74838_a((String)"auction.timeLeft").replace("%s", AuctionFormatUtil.formatTimeRemaining(timeRemaining));
            tooltip.add((AuctionFormatUtil.isTimeUrgent(timeRemaining) ? EnumChatFormatting.RED : EnumChatFormatting.WHITE) + timeText);
            tooltip.add("");
            tooltip.add(EnumChatFormatting.AQUA + "Left-click: View details");
            tooltip.add(EnumChatFormatting.GOLD + "Right-click: Stop player");
            tooltip.add(EnumChatFormatting.LIGHT_PURPLE + "Shift+Right-click: Cancel to global");
            return;
        }
        if (tab == GuiNpcManageAuction.Tab.CLAIMS && container.isDisplaySlot(slot.field_75222_d)) {
            int displayIndex = container.toDisplayIndex(slot.field_75222_d);
            AuctionClaim claim = container.getClaimAtDisplay(displayIndex);
            if (claim != null) {
                AuctionTooltipHandler.addGlobalClaimTooltip(claim, tooltip);
                return;
            }
            AuctionListing listing = container.getListingAtDisplay(displayIndex);
            if (listing != null) {
                tooltip.add("");
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.seller").replace("%s", EnumChatFormatting.WHITE + listing.sellerName));
                if (listing.hasBids()) {
                    tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.currentBid").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(listing.currentBid)));
                } else {
                    tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.startingPrice").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(listing.startingPrice)));
                }
                if (listing.hasBuyout()) {
                    tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.buyout").replace("%s", EnumChatFormatting.GREEN + AuctionFormatUtil.formatCurrencyWithName(listing.buyoutPrice)));
                }
                tooltip.add("");
                tooltip.add(EnumChatFormatting.GOLD + "Right-click: Stop player");
                tooltip.add(EnumChatFormatting.LIGHT_PURPLE + "Shift+Right-click: Cancel to global");
            }
            return;
        }
        if (tab == GuiNpcManageAuction.Tab.CREATE) {
            if (container.isCreateSlot(slot.field_75222_d)) {
                tooltip.add("");
                tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.sell.leftClear"));
                tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.sell.rightRemove"));
            } else if (container.isPlayerSlot(slot.field_75222_d)) {
                tooltip.add("");
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.sell.leftAdd"));
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.sell.rightAddOne"));
            }
        }
    }

    private static void addGlobalClaimTooltip(AuctionClaim claim, List<String> tooltip) {
        tooltip.add("");
        if (claim.type == EnumClaimType.ITEM) {
            tooltip.add(EnumChatFormatting.AQUA + "Global Item Claim");
        } else if (claim.type == EnumClaimType.CURRENCY) {
            tooltip.add(EnumChatFormatting.GOLD + "Global Currency Claim");
            if (claim.itemName != null && !claim.itemName.isEmpty()) {
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.soldItem").replace("%s", EnumChatFormatting.WHITE + claim.itemName));
            }
            if (claim.otherPlayerName != null && !claim.otherPlayerName.isEmpty()) {
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.buyer").replace("%s", EnumChatFormatting.WHITE + claim.otherPlayerName));
            }
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.claim.amount").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(claim.currency)));
            tooltip.add(EnumChatFormatting.GRAY + "Claiming this does not change your balance.");
        } else if (claim.type == EnumClaimType.REFUND) {
            tooltip.add(EnumChatFormatting.RED + "Global Refund Claim");
            if (claim.otherPlayerName != null && !claim.otherPlayerName.isEmpty()) {
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.outbidBy").replace("%s", EnumChatFormatting.WHITE + claim.otherPlayerName));
            }
            tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.claim.amount").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(claim.currency)));
        }
        tooltip.add("");
        tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.claim.clickToClaim"));
    }
}

