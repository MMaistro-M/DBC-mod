/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
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
import net.minecraft.client.gui.GuiButton;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.AuctionClientConfig;
import noppes.npcs.client.gui.player.GuiAuctionInterface;
import noppes.npcs.client.gui.player.SubGuiAuctionBid;
import noppes.npcs.client.gui.player.SubGuiAuctionBuyNow;
import noppes.npcs.client.gui.util.GuiAuctionNavButton;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.containers.ContainerAuctionBidding;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.util.AuctionFormatUtil;
import org.lwjgl.opengl.GL11;

public class GuiAuctionBidding
extends GuiAuctionInterface
implements ISubGuiListener,
IGuiData {
    private static final ResourceLocation ICON_BID = new ResourceLocation("customnpcs", "textures/items/npcCoinDiamond.png");
    private static final ResourceLocation ICON_BUYOUT = new ResourceLocation("customnpcs", "textures/items/npcCoinEmerald.png");
    private int itemSlotX = 70;
    private int itemSlotY = 75;
    private int btnBidX = 58;
    private int btnBidY = 110;
    private int btnBuyoutX = 82;
    private int btnBuyoutY = 110;
    private int infoX = 105;
    private int infoY = 50;
    private int btnBidId = 200;
    private int btnBuyoutId = 201;
    private final ContainerAuctionBidding biddingContainer;
    private AuctionListing listing;
    private long playerBalance = 0L;
    private boolean dataLoaded = false;
    private boolean isOwnListing = false;
    private GuiAuctionNavButton btnBid;
    private GuiAuctionNavButton btnBuyout;

    public GuiAuctionBidding(EntityNPCInterface npc, ContainerAuctionBidding container) {
        super(npc, container);
        this.biddingContainer = container;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.btnBid = new GuiAuctionNavButton(this.btnBidId, this.field_147003_i + this.btnBidX, this.field_147009_r + this.btnBidY, "auction.bid.place", ICON_BID);
        this.addButton(this.btnBid);
        this.btnBuyout = new GuiAuctionNavButton(this.btnBuyoutId, this.field_147003_i + this.btnBuyoutX, this.field_147009_r + this.btnBuyoutY, "auction.bid.buyout", ICON_BUYOUT);
        this.addButton(this.btnBuyout);
        this.updateButtonState();
    }

    @Override
    protected int getCurrentPage() {
        return -1;
    }

    private void updateButtonState() {
        if (this.listing == null) {
            if (this.btnBid != null) {
                this.btnBid.setVisible(false);
            }
            if (this.btnBuyout != null) {
                this.btnBuyout.setVisible(false);
            }
            return;
        }
        if (this.isOwnListing) {
            if (this.btnBid != null) {
                this.btnBid.setVisible(false);
            }
            if (this.btnBuyout != null) {
                this.btnBuyout.setVisible(false);
            }
            return;
        }
        if (this.btnBid != null) {
            this.btnBid.setVisible(true);
        }
        if (this.btnBuyout != null) {
            this.btnBuyout.setVisible(this.listing.hasBuyout());
        }
    }

    @Override
    public void func_146284_a(GuiButton button) {
        super.func_146284_a(button);
        if (this.listing == null) {
            return;
        }
        if (button.field_146127_k == this.btnBidId) {
            this.openBidSubGui();
        } else if (button.field_146127_k == this.btnBuyoutId && this.listing.hasBuyout()) {
            this.openBuyNowSubGui();
        }
    }

    private void openBidSubGui() {
        if (this.listing == null) {
            return;
        }
        long minBid = this.listing.getMinimumBid(AuctionClientConfig.getMinBidIncrement());
        this.setSubGui(new SubGuiAuctionBid(this.listing.id, minBid, this.listing.buyoutPrice, this.playerBalance));
    }

    private void openBuyNowSubGui() {
        if (this.listing == null || !this.listing.hasBuyout()) {
            return;
        }
        this.setSubGui(new SubGuiAuctionBuyNow(this.listing.id, this.listing.buyoutPrice, this.playerBalance));
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        SubGuiAuctionBuyNow buyGui;
        if (subgui instanceof SubGuiAuctionBid) {
            SubGuiAuctionBid bidGui = (SubGuiAuctionBid)subgui;
            if (bidGui.wasSuccessful()) {
                AuctionActionPacket.openPage(0);
            }
        } else if (subgui instanceof SubGuiAuctionBuyNow && (buyGui = (SubGuiAuctionBuyNow)subgui).wasSuccessful()) {
            AuctionActionPacket.openPage(2);
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("BiddingData")) {
            this.playerBalance = compound.func_74763_f("Balance");
            this.isOwnListing = compound.func_74767_n("IsOwnListing");
            if (compound.func_74764_b("Listing")) {
                this.listing = AuctionListing.fromNBT(compound.func_74775_l("Listing"));
                this.biddingContainer.setListing(this.listing);
            }
            this.dataLoaded = true;
            this.updateButtonState();
        }
    }

    @Override
    protected void drawAuctionContent(float partialTicks, int mouseX, int mouseY) {
        this.drawAuctionSlot(this.field_147003_i + this.itemSlotX, this.field_147009_r + this.itemSlotY);
        if (!this.dataLoaded || this.listing == null) {
            String loading = StatCollector.func_74838_a((String)"gui.loading");
            this.field_146289_q.func_78276_b(loading, this.field_147003_i + this.infoX, this.field_147009_r + this.infoY, 0xFFFFFF);
            return;
        }
        this.drawAuctionInfo();
    }

    private void drawAuctionInfo() {
        int x = this.field_147003_i + this.infoX;
        int y = this.field_147009_r + this.infoY;
        int xRight = this.field_147003_i + 215;
        int lineHeight = 9;
        String sellerLabel = StatCollector.func_74838_a((String)"auction.seller").replace("%s", "");
        this.field_146289_q.func_78276_b(EnumChatFormatting.GRAY + sellerLabel, x, y, 0xFFFFFF);
        String label = this.listing.sellerName;
        int length = this.field_146289_q.func_78256_a(label);
        this.field_146289_q.func_78276_b(EnumChatFormatting.WHITE + this.listing.sellerName, xRight - length, y, 0xFFFFFF);
        y += lineHeight + 4;
        if (this.listing.hasBids()) {
            String bidLabel = StatCollector.func_74838_a((String)"auction.info.currentBid") + ":";
            this.field_146289_q.func_78276_b(EnumChatFormatting.YELLOW + bidLabel, x, y, 0xFFFFFF);
            label = AuctionFormatUtil.formatCurrency(this.listing.currentBid);
            length = this.field_146289_q.func_78256_a(label);
            this.field_146289_q.func_78276_b(EnumChatFormatting.GOLD + label, xRight - length, y, 0xFFFFFF);
            String bidderLabel = StatCollector.func_74838_a((String)"auction.info.highBidder");
            this.field_146289_q.func_78276_b(EnumChatFormatting.GRAY + bidderLabel, x, y += lineHeight + 4, 0xFFFFFF);
            label = this.listing.highBidderName != null ? this.listing.highBidderName : "???";
            length = this.field_146289_q.func_78256_a(label);
            this.field_146289_q.func_78276_b(EnumChatFormatting.WHITE + label, xRight - length, y, 0xFFFFFF);
            y += lineHeight + 4;
        } else {
            String startLabel = StatCollector.func_74838_a((String)"auction.info.startingPrice");
            this.field_146289_q.func_78276_b(EnumChatFormatting.YELLOW + startLabel, x, y, 0xFFFFFF);
            label = AuctionFormatUtil.formatCurrency(this.listing.startingPrice);
            length = this.field_146289_q.func_78256_a(label);
            this.field_146289_q.func_78276_b(EnumChatFormatting.GOLD + label, xRight - length, y, 0xFFFFFF);
            y += lineHeight + 4;
        }
        if (this.listing.hasBuyout()) {
            String buyoutLabel = StatCollector.func_74838_a((String)"auction.info.buyoutPrice");
            this.field_146289_q.func_78276_b(EnumChatFormatting.GREEN + buyoutLabel, x, y, 0xFFFFFF);
            label = AuctionFormatUtil.formatCurrency(this.listing.buyoutPrice);
            length = this.field_146289_q.func_78256_a(label);
            this.field_146289_q.func_78276_b(EnumChatFormatting.GREEN + label, xRight - length, y, 0xFFFFFF);
            y += lineHeight + 4;
        }
        String timeLabel = StatCollector.func_74838_a((String)"auction.info.timeLeft");
        this.field_146289_q.func_78276_b(EnumChatFormatting.GRAY + timeLabel, x, y, 0xFFFFFF);
        long timeRemaining = this.listing.getTimeRemaining();
        EnumChatFormatting timeColor = AuctionFormatUtil.isTimeUrgent(timeRemaining) ? EnumChatFormatting.RED : EnumChatFormatting.WHITE;
        label = timeColor + AuctionFormatUtil.formatTimeRemaining(timeRemaining);
        length = this.field_146289_q.func_78256_a(label);
        this.field_146289_q.func_78276_b(label, xRight - length, y, 0xFFFFFF);
        String bidCountLabel = StatCollector.func_74838_a((String)"auction.info.bidCount");
        this.field_146289_q.func_78276_b(EnumChatFormatting.GRAY + bidCountLabel, x, y += lineHeight + 4, 0xFFFFFF);
        label = "" + this.listing.bidCount;
        length = this.field_146289_q.func_78256_a(label);
        this.field_146289_q.func_78276_b(EnumChatFormatting.WHITE + label, xRight - length, y, 0xFFFFFF);
    }

    @Override
    protected void func_146979_b(int mouseX, int mouseY) {
        super.func_146979_b(mouseX, mouseY);
        if (this.hasSubGui()) {
            return;
        }
        this.drawBiddingPageTooltips(mouseX, mouseY);
    }

    private void drawBiddingPageTooltips(int mouseX, int mouseY) {
        ArrayList<String> tooltip = null;
        if (this.btnBid != null && this.btnBid.field_146125_m && this.btnBid.isHovered()) {
            tooltip = new ArrayList<String>();
            tooltip.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)"auction.bid.place"));
            if (this.listing != null) {
                long minBid = this.listing.getMinimumBid(AuctionClientConfig.getMinBidIncrement());
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.bid.minimum").replace("%s", EnumChatFormatting.WHITE + AuctionFormatUtil.formatCurrency(minBid) + " " + AuctionClientConfig.getCurrencyName()));
            }
        } else if (this.btnBuyout != null && this.btnBuyout.field_146125_m && this.btnBuyout.isHovered()) {
            tooltip = new ArrayList();
            tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.bid.buyout"));
            if (this.listing != null && this.listing.hasBuyout()) {
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.info.buyoutPrice") + ":");
                tooltip.add(EnumChatFormatting.GREEN + AuctionFormatUtil.formatCurrency(this.listing.buyoutPrice) + " " + AuctionClientConfig.getCurrencyName());
            }
        }
        if (tooltip != null && !tooltip.isEmpty()) {
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)1048575);
            this.drawHoveringText(tooltip, mouseX - this.field_147003_i, mouseY - this.field_147009_r, this.field_146289_q);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    protected void func_146984_a(Slot slot, int slotIndex, int mouseButton, int clickType) {
    }

    public AuctionListing getListing() {
        return this.listing;
    }
}

