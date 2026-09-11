/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.inventory.Slot
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import java.util.ArrayList;
import kamkeel.npcs.network.packets.player.AuctionActionPacket;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.AuctionClientConfig;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.player.GuiAuctionInterface;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.constants.EnumClaimType;
import noppes.npcs.containers.ContainerAuctionTrades;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiAuctionTrades
extends GuiAuctionInterface
implements IGuiData {
    private static final ResourceLocation ICON_X = new ResourceLocation("customnpcs", "textures/gui/auction/x_icon.png");
    private static final ResourceLocation ICON_CHECK = new ResourceLocation("customnpcs", "textures/gui/auction/check_icon.png");
    private static final ResourceLocation ICON_COIN = new ResourceLocation("customnpcs", "textures/items/npcCoinGold.png");
    private static final int GRID_X = 56;
    private static final int GRID_Y = 46;
    private static final int COLS = 9;
    private static final int ROWS = 5;
    private static final int TOTAL_SLOTS = 45;
    private static final int TINT_BLUE = 1615888639;
    private static final int TINT_GREEN = 1615920976;
    private static final int TINT_YELLOW = 1627389776;
    private static final int TINT_RED = 1627344976;
    private final ContainerAuctionTrades tradesContainer;
    private int maxTradeSlots;
    private int pendingSlot = -1;
    private PendingOp pendingOp = PendingOp.NONE;

    public GuiAuctionTrades(EntityNPCInterface npc, ContainerAuctionTrades container) {
        super(npc, container);
        this.tradesContainer = container;
        this.maxTradeSlots = AuctionClientConfig.getMaxActiveListings();
    }

    @Override
    protected int getCurrentPage() {
        return 2;
    }

    @Override
    public void mouseEvent(int mouseX, int mouseY, int mouseButton) {
        if (this.hasSubGui()) {
            return;
        }
        int slot = this.getSlotAt(mouseX, mouseY);
        if (slot < 0 || slot >= 45) {
            this.clearPending();
            return;
        }
        if (slot == this.pendingSlot && this.pendingOp != PendingOp.NONE) {
            this.handlePendingClick(mouseButton);
            return;
        }
        this.clearPending();
        AuctionListing listing = this.tradesContainer.getListingAt(slot);
        AuctionClaim claim = this.tradesContainer.getClaimAt(slot);
        if (listing != null && this.tradesContainer.isSellingAt(slot)) {
            if (mouseButton == 0) {
                AuctionActionPacket.openBidding(listing.id);
                NoppesUtil.clickSound();
            } else if (mouseButton == 1) {
                this.setPending(slot, PendingOp.CANCEL);
                NoppesUtil.clickSound();
            }
        } else if (listing != null && this.tradesContainer.isBiddingAt(slot) && mouseButton == 0) {
            AuctionActionPacket.openBidding(listing.id);
            NoppesUtil.clickSound();
        } else if (claim != null) {
            if (claim.type == EnumClaimType.REFUND && claim.item != null) {
                if (mouseButton == 0) {
                    AuctionActionPacket.openBidding(claim.listingId);
                    NoppesUtil.clickSound();
                } else if (mouseButton == 1) {
                    this.setPending(slot, PendingOp.CLAIM);
                    NoppesUtil.clickSound();
                }
            } else if (mouseButton == 0) {
                this.setPending(slot, PendingOp.CLAIM);
                NoppesUtil.clickSound();
            }
        }
    }

    private void handlePendingClick(int mouseButton) {
        if (this.pendingOp == PendingOp.CANCEL && mouseButton == 1) {
            AuctionListing listing = this.tradesContainer.getListingAt(this.pendingSlot);
            if (listing != null) {
                AuctionActionPacket.cancelListing(listing.id);
                this.playConfirmSound();
            }
            this.clearPending();
        } else if (this.pendingOp == PendingOp.CLAIM) {
            AuctionClaim claim = this.tradesContainer.getClaimAt(this.pendingSlot);
            if (claim != null) {
                boolean shouldClaim;
                boolean bl = claim.type == EnumClaimType.REFUND && claim.item != null ? mouseButton == 1 : (shouldClaim = mouseButton == 0);
                if (shouldClaim) {
                    if (claim.type.isItem()) {
                        AuctionActionPacket.claimItem(claim.id);
                    } else {
                        AuctionActionPacket.claimCurrency(claim.id);
                    }
                    this.playConfirmSound();
                    this.clearPending();
                } else {
                    this.clearPending();
                    NoppesUtil.clickSound();
                }
            } else {
                this.clearPending();
            }
        } else {
            this.clearPending();
            NoppesUtil.clickSound();
        }
    }

    private void setPending(int slot, PendingOp op) {
        this.pendingSlot = slot;
        this.pendingOp = op;
        this.tradesContainer.setHiddenSlot(slot);
    }

    private void clearPending() {
        if (this.pendingSlot >= 0) {
            this.tradesContainer.clearHiddenSlot();
        }
        this.pendingSlot = -1;
        this.pendingOp = PendingOp.NONE;
    }

    private void playConfirmSound() {
        this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("random.orb"), (float)1.0f));
    }

    private int getSlotAt(int mouseX, int mouseY) {
        int relX = mouseX - this.field_147003_i - 56;
        int relY = mouseY - this.field_147009_r - 46;
        if (relX < 0 || relY < 0) {
            return -1;
        }
        int col = relX / 18;
        int row = relY / 18;
        if (col >= 9 || row >= 5) {
            return -1;
        }
        return col + row * 9;
    }

    @Override
    protected void drawAuctionContent(float partialTicks, int mouseX, int mouseY) {
        for (int row = 0; row < 5; ++row) {
            for (int col = 0; col < 9; ++col) {
                int slot = col + row * 9;
                int x = this.field_147003_i + 56 + col * 18;
                int y = this.field_147009_r + 46 + row * 18;
                this.drawAuctionSlot(x, y);
                int tint = this.getSlotTint(slot);
                if (tint != 0 && slot != this.pendingSlot) {
                    this.drawColoredOverlay(x, y, tint);
                }
                if (slot >= this.maxTradeSlots) {
                    this.drawDarkenedOverlay(x, y);
                }
                if (slot == this.pendingSlot) {
                    if (this.pendingOp == PendingOp.CANCEL) {
                        this.drawIconOverlay(x, y, ICON_X);
                        continue;
                    }
                    if (this.pendingOp != PendingOp.CLAIM) continue;
                    this.drawIconOverlay(x, y, ICON_CHECK);
                    continue;
                }
                AuctionClaim claim = this.tradesContainer.getClaimAt(slot);
                if (claim == null || claim.type != EnumClaimType.CURRENCY && (claim.type != EnumClaimType.REFUND || claim.item != null)) continue;
                this.drawIconOverlay(x, y, ICON_COIN);
            }
        }
    }

    private int getSlotTint(int slot) {
        AuctionListing listing = this.tradesContainer.getListingAt(slot);
        AuctionClaim claim = this.tradesContainer.getClaimAt(slot);
        if (listing != null) {
            if (this.tradesContainer.isSellingAt(slot)) {
                return 1615888639;
            }
            return 1627389776;
        }
        if (claim != null) {
            switch (claim.type) {
                case CURRENCY: {
                    return 1615920976;
                }
                case REFUND: {
                    return 1627344976;
                }
                case ITEM: {
                    return claim.isReturned ? 1627344976 : 1615920976;
                }
            }
            return 1615920976;
        }
        return 0;
    }

    @Override
    protected void func_146979_b(int mouseX, int mouseY) {
        super.func_146979_b(mouseX, mouseY);
        if (this.hasSubGui()) {
            return;
        }
        int slot = this.getSlotAt(mouseX, mouseY);
        if (slot >= 0 && slot < 45) {
            this.drawTradeTooltip(slot, mouseX - this.field_147003_i, mouseY - this.field_147009_r);
        }
    }

    private void drawTradeTooltip(int slot, int x, int y) {
        ArrayList<String> tooltip = new ArrayList<String>();
        if (slot == this.pendingSlot) {
            if (this.pendingOp == PendingOp.CANCEL) {
                tooltip.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"auction.trades.confirmCancel"));
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.trades.rightClickConfirm"));
                tooltip.add(EnumChatFormatting.DARK_GRAY + StatCollector.func_74838_a((String)"auction.trades.otherClickCancel"));
            } else if (this.pendingOp == PendingOp.CLAIM) {
                tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.trades.confirmClaim"));
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.trades.leftClickConfirm"));
                tooltip.add(EnumChatFormatting.DARK_GRAY + StatCollector.func_74838_a((String)"auction.trades.otherClickCancel"));
            }
        } else {
            AuctionListing listing = this.tradesContainer.getListingAt(slot);
            AuctionClaim claim = this.tradesContainer.getClaimAt(slot);
            if (listing != null) {
                return;
            }
            if (claim != null && (claim.type == EnumClaimType.ITEM || claim.type == EnumClaimType.REFUND && claim.item != null)) {
                return;
            }
            if (claim != null) {
                switch (claim.type) {
                    case CURRENCY: {
                        tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.trades.soldClaim"));
                        if (!claim.itemName.isEmpty()) {
                            tooltip.add(EnumChatFormatting.WHITE + claim.itemName);
                        }
                        if (!claim.otherPlayerName.isEmpty()) {
                            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.trades.buyer") + ": " + EnumChatFormatting.AQUA + claim.otherPlayerName);
                        }
                        tooltip.add(EnumChatFormatting.GOLD + String.format("%,d", claim.currency) + " " + AuctionClientConfig.getCurrencyName());
                        break;
                    }
                    case REFUND: {
                        tooltip.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"auction.trades.outbid"));
                        if (!claim.otherPlayerName.isEmpty()) {
                            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.trades.outbidBy") + ": " + EnumChatFormatting.AQUA + claim.otherPlayerName);
                        }
                        tooltip.add(EnumChatFormatting.GOLD + String.format("%,d", claim.currency) + " " + AuctionClientConfig.getCurrencyName());
                    }
                }
                tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.trades.leftClickToClaim"));
            } else if (slot >= this.maxTradeSlots) {
                tooltip.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"auction.trades.unavailable"));
            }
        }
        if (!tooltip.isEmpty()) {
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)1048575);
            this.drawHoveringText(tooltip, x, y, this.field_146289_q);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    protected void func_146984_a(Slot slot, int slotIndex, int mouseButton, int clickType) {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("TradesData")) {
            this.tradesContainer.setTradesData(compound);
            this.maxTradeSlots = this.tradesContainer.getMaxTradeSlots();
            this.clearPending();
        } else if (compound.func_74764_b("TradesUpdate")) {
            this.tradesContainer.refreshData();
        }
    }

    public ContainerAuctionTrades getTradesContainer() {
        return this.tradesContainer;
    }

    private static enum PendingOp {
        NONE,
        CANCEL,
        CLAIM;

    }
}

