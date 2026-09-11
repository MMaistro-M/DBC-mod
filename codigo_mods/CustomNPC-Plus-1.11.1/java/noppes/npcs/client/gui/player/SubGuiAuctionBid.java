/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.player;

import kamkeel.npcs.network.packets.player.AuctionActionPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.AuctionClientConfig;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.util.AuctionFormatUtil;

public class SubGuiAuctionBid
extends SubGuiInterface
implements ITextfieldListener {
    private int txtBidId = 1;
    private int btnBidId = 10;
    private int btnCancelId = 11;
    private final String listingId;
    private final long minimumBid;
    private final long buyoutPrice;
    private final long playerBalance;
    private long bidAmount;
    private boolean successful = false;
    private String errorMessage = null;

    public SubGuiAuctionBid(String listingId, long minimumBid, long buyoutPrice, long playerBalance) {
        this.listingId = listingId;
        this.minimumBid = minimumBid;
        this.buyoutPrice = buyoutPrice;
        this.playerBalance = playerBalance;
        this.bidAmount = minimumBid;
        this.setBackground("menubg.png");
        this.xSize = 200;
        this.ySize = 120;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int centerX = this.guiLeft + this.xSize / 2;
        int y = this.guiTop + 15;
        String currencyName = AuctionClientConfig.getCurrencyName();
        String minText = StatCollector.func_74838_a((String)"auction.bid.minimum").replace("%s", EnumChatFormatting.DARK_RED + AuctionFormatUtil.formatCurrency(this.minimumBid) + " " + currencyName);
        this.addLabel(new GuiNpcLabel(1, minText, this.guiLeft + 15, y));
        String balanceText = StatCollector.func_74838_a((String)"auction.bid.yourBalance").replace("%s", EnumChatFormatting.DARK_GREEN + AuctionFormatUtil.formatCurrency(this.playerBalance) + " " + currencyName);
        this.addLabel(new GuiNpcLabel(2, balanceText, this.guiLeft + 15, y += 14));
        this.addLabel(new GuiNpcLabel(3, "auction.bid.yourBid", this.guiLeft + 15, (y += 18) + 4));
        GuiNpcTextField bidField = new GuiNpcTextField(this.txtBidId, this, this.field_146289_q, this.guiLeft + 80, y, 100, 18, "" + this.minimumBid);
        bidField.setIntegersOnly();
        this.addTextField(bidField);
        this.addLabel(new GuiNpcLabel(4, "", this.guiLeft + 15, y += 28, 0xFF5555));
        int btnWidth = 70;
        int btnSpacing = 20;
        int totalBtnWidth = btnWidth * 2 + btnSpacing;
        int btnX = centerX - totalBtnWidth / 2;
        this.addButton(new GuiNpcButton(this.btnBidId, btnX, y += 16, btnWidth, 20, "auction.bid.place"));
        this.addButton(new GuiNpcButton(this.btnCancelId, btnX + btnWidth + btnSpacing, y, btnWidth, 20, "gui.cancel"));
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == this.txtBidId) {
            try {
                this.bidAmount = Long.parseLong(textfield.func_146179_b());
            }
            catch (NumberFormatException e) {
                this.bidAmount = 0L;
            }
            this.errorMessage = null;
            this.updateErrorLabel();
        }
    }

    @Override
    public void buttonEvent(GuiButton button) {
        if (button.field_146127_k == this.btnBidId) {
            this.placeBid();
        } else if (button.field_146127_k == this.btnCancelId) {
            this.close();
        }
    }

    private void placeBid() {
        GuiNpcTextField bidField = this.getTextField(this.txtBidId);
        if (bidField != null) {
            try {
                this.bidAmount = Long.parseLong(bidField.func_146179_b());
            }
            catch (NumberFormatException e) {
                this.bidAmount = 0L;
            }
        }
        if (this.bidAmount < this.minimumBid) {
            this.errorMessage = StatCollector.func_74838_a((String)"auction.error.bidTooLow").replace("%s", AuctionFormatUtil.formatCurrency(this.minimumBid));
            this.updateErrorLabel();
            return;
        }
        if (this.bidAmount > this.playerBalance) {
            this.errorMessage = StatCollector.func_74838_a((String)"auction.error.notEnoughCurrency");
            this.updateErrorLabel();
            return;
        }
        if (this.buyoutPrice > 0L && this.bidAmount >= this.buyoutPrice) {
            this.errorMessage = StatCollector.func_74838_a((String)"auction.error.bidExceedsBuyout");
            this.updateErrorLabel();
            return;
        }
        AuctionActionPacket.placeBid(this.listingId, this.bidAmount);
        this.successful = true;
        this.close();
    }

    private void updateErrorLabel() {
        GuiNpcLabel errorLabel = this.getLabel(4);
        if (errorLabel != null) {
            errorLabel.label = this.errorMessage != null ? EnumChatFormatting.RED + this.errorMessage : "";
        }
    }

    public boolean wasSuccessful() {
        return this.successful;
    }
}

