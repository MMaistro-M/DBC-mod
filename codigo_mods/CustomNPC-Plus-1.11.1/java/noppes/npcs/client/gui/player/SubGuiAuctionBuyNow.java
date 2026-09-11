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
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.util.AuctionFormatUtil;

public class SubGuiAuctionBuyNow
extends SubGuiInterface {
    private int btnConfirmId = 10;
    private int btnCancelId = 11;
    private final String listingId;
    private final long buyoutPrice;
    private final long playerBalance;
    private boolean successful = false;

    public SubGuiAuctionBuyNow(String listingId, long buyoutPrice, long playerBalance) {
        this.listingId = listingId;
        this.buyoutPrice = buyoutPrice;
        this.playerBalance = playerBalance;
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 130;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int centerX = this.guiLeft + this.xSize / 2;
        int y = this.guiTop + 15;
        String currencyName = AuctionClientConfig.getCurrencyName();
        String title = StatCollector.func_74838_a((String)"auction.buyout.title");
        int titleWidth = this.field_146289_q.func_78256_a(title);
        this.addLabel(new GuiNpcLabel(0, title, centerX - titleWidth / 2, y));
        String confirmMsg = StatCollector.func_74838_a((String)"auction.buyout.confirm");
        int confirmWidth = this.field_146289_q.func_78256_a(confirmMsg);
        this.addLabel(new GuiNpcLabel(1, confirmMsg, centerX - confirmWidth / 2, y += 20));
        String priceText = EnumChatFormatting.DARK_RED + AuctionFormatUtil.formatCurrency(this.buyoutPrice) + " " + currencyName;
        int priceWidth = this.field_146289_q.func_78256_a(priceText);
        this.addLabel(new GuiNpcLabel(2, priceText, centerX - priceWidth / 2, y += 16));
        String balanceLabel = StatCollector.func_74838_a((String)"auction.bid.yourBalance");
        String balanceText = balanceLabel.replace("%s", EnumChatFormatting.DARK_GREEN + AuctionFormatUtil.formatCurrency(this.playerBalance) + " " + currencyName);
        this.addLabel(new GuiNpcLabel(3, balanceText, this.guiLeft + 20, y += 20));
        y += 14;
        if (this.playerBalance < this.buyoutPrice) {
            String warning = StatCollector.func_74838_a((String)"auction.error.notEnoughCurrency");
            this.addLabel(new GuiNpcLabel(4, EnumChatFormatting.RED + warning, this.guiLeft + 20, y, 0xFFFFFF));
        }
        int btnWidth = 70;
        int btnSpacing = 20;
        int totalBtnWidth = btnWidth * 2 + btnSpacing;
        int btnX = centerX - totalBtnWidth / 2;
        GuiNpcButton confirmBtn = new GuiNpcButton(this.btnConfirmId, btnX, y += 20, btnWidth, 20, "gui.yes");
        confirmBtn.field_146124_l = this.playerBalance >= this.buyoutPrice;
        this.addButton(confirmBtn);
        this.addButton(new GuiNpcButton(this.btnCancelId, btnX + btnWidth + btnSpacing, y, btnWidth, 20, "gui.cancel"));
    }

    @Override
    public void buttonEvent(GuiButton button) {
        if (button.field_146127_k == this.btnConfirmId) {
            if (this.playerBalance >= this.buyoutPrice) {
                this.confirmPurchase();
            }
        } else if (button.field_146127_k == this.btnCancelId) {
            this.close();
        }
    }

    private void confirmPurchase() {
        AuctionActionPacket.buyout(this.listingId);
        this.successful = true;
        this.close();
    }

    public boolean wasSuccessful() {
        return this.successful;
    }
}

