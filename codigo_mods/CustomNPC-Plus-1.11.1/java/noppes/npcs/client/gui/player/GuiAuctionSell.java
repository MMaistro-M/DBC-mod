/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.network.packets.player.AuctionActionPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.AuctionClientConfig;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.player.GuiAuctionInterface;
import noppes.npcs.client.gui.util.GuiAuctionNavButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.containers.ContainerAuctionSell;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.util.AuctionFormatUtil;
import org.lwjgl.opengl.GL11;

public class GuiAuctionSell
extends GuiAuctionInterface
implements ITextfieldListener {
    private static final ResourceLocation ICON_COIN = new ResourceLocation("customnpcs", "textures/items/npcCoinBronze.png");
    private static final ResourceLocation ICON_BAG = new ResourceLocation("customnpcs", "textures/items/npcBag.png");
    private int contentX = 58;
    private int contentY = 50;
    private int confirmBtnX = 200;
    private int confirmBtnY = 118;
    private int navYBuyout = 109;
    private int txtStartingPriceId = 1;
    private int txtBuyoutPriceId = 2;
    private int btnConfirmId = 10;
    private int btnAllowBuyoutId = 11;
    private final ContainerAuctionSell sellContainer;
    private long startingPrice = 0L;
    private long buyoutPrice = 0L;
    private boolean allowBuyout = true;
    private String errorMessage = null;
    private GuiAuctionNavButton btnAllowBuyout;
    private GuiNpcLabel lblBuyout;
    private GuiNpcTextField txtBuyout;

    public GuiAuctionSell(EntityNPCInterface npc, ContainerAuctionSell container) {
        super(npc, container);
        this.sellContainer = container;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.field_147009_r + this.contentY;
        this.addLabel(new GuiNpcLabel(1, "auction.sell.startingPrice", this.field_147003_i + this.contentX, y + 5, 0xFFFFFF));
        GuiNpcTextField startingField = new GuiNpcTextField(this.txtStartingPriceId, (GuiScreen)this, this.field_146289_q, this.field_147003_i + this.contentX + 80, y, 80, 18, this.startingPrice > 0L ? "" + this.startingPrice : "");
        startingField.setIntegersOnly();
        this.addTextField(startingField);
        this.lblBuyout = new GuiNpcLabel(2, "auction.sell.buyoutPrice", this.field_147003_i + this.contentX, (y += 25) + 5, 0xFFFFFF);
        this.addLabel(this.lblBuyout);
        this.txtBuyout = new GuiNpcTextField(this.txtBuyoutPriceId, (GuiScreen)this, this.field_146289_q, this.field_147003_i + this.contentX + 80, y, 80, 18, this.buyoutPrice > 0L ? "" + this.buyoutPrice : "");
        this.txtBuyout.setIntegersOnly();
        this.addTextField(this.txtBuyout);
        this.btnAllowBuyout = new GuiAuctionNavButton(this.btnAllowBuyoutId, this.field_147003_i + this.navX, this.field_147009_r + this.navYBuyout, "auction.sell.allowBuyout", ICON_BAG);
        this.btnAllowBuyout.setToggle(true);
        this.btnAllowBuyout.setSelected(this.allowBuyout);
        this.addButton(this.btnAllowBuyout);
        GuiAuctionNavButton btnConfirm = new GuiAuctionNavButton(this.btnConfirmId, this.field_147003_i + this.confirmBtnX - 20, this.field_147009_r + this.confirmBtnY - 10, "auction.sell.confirm", ICON_COIN);
        this.addButton(btnConfirm);
        this.updateBuyoutVisibility();
        this.updateBuyoutTooltip();
    }

    @Override
    protected int getCurrentPage() {
        return 1;
    }

    private void updateBuyoutVisibility() {
        if (this.lblBuyout != null) {
            this.lblBuyout.enabled = this.allowBuyout;
        }
        if (this.txtBuyout != null) {
            this.txtBuyout.func_146189_e(this.allowBuyout);
            if (!this.allowBuyout) {
                this.txtBuyout.func_146180_a("");
                this.buyoutPrice = 0L;
            }
        }
    }

    private void updateBuyoutTooltip() {
        if (this.btnAllowBuyout == null) {
            return;
        }
        ArrayList<String> tooltip = new ArrayList<String>();
        tooltip.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)"auction.sell.allowBuyout"));
        tooltip.add("");
        if (this.allowBuyout) {
            tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.sell.buyoutEnabled"));
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.sell.buyoutEnabledDesc"));
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.sell.buyoutEnabledDesc2"));
        } else {
            tooltip.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"auction.sell.buyoutDisabled"));
            tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.sell.buyoutDisabledDesc"));
        }
        tooltip.add("");
        tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.sell.clickToToggle"));
        this.btnAllowBuyout.setCustomTooltip(tooltip);
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id == this.txtStartingPriceId) {
            try {
                this.startingPrice = Long.parseLong(textfield.func_146179_b());
            }
            catch (NumberFormatException e) {
                this.startingPrice = 0L;
            }
        } else if (textfield.id == this.txtBuyoutPriceId) {
            try {
                String text = textfield.func_146179_b().trim();
                this.buyoutPrice = text.isEmpty() ? 0L : Long.parseLong(text);
            }
            catch (NumberFormatException e) {
                this.buyoutPrice = 0L;
            }
        }
        this.errorMessage = null;
    }

    @Override
    public void func_146284_a(GuiButton button) {
        super.func_146284_a(button);
        if (button.field_146127_k == this.btnConfirmId) {
            this.createListing();
        } else if (button.field_146127_k == this.btnAllowBuyoutId) {
            this.allowBuyout = !this.allowBuyout;
            this.btnAllowBuyout.setSelected(this.allowBuyout);
            this.updateBuyoutVisibility();
            this.updateBuyoutTooltip();
            NoppesUtil.clickSound();
        }
    }

    private void createListing() {
        ItemStack item;
        GuiNpcTextField startingField = this.getTextField(this.txtStartingPriceId);
        GuiNpcTextField buyoutField = this.getTextField(this.txtBuyoutPriceId);
        if (startingField != null) {
            try {
                this.startingPrice = Long.parseLong(startingField.func_146179_b());
            }
            catch (NumberFormatException e) {
                this.startingPrice = 0L;
            }
        }
        if (buyoutField != null && this.allowBuyout) {
            try {
                String text = buyoutField.func_146179_b().trim();
                this.buyoutPrice = text.isEmpty() ? 0L : Long.parseLong(text);
            }
            catch (NumberFormatException e) {
                this.buyoutPrice = 0L;
            }
        } else {
            this.buyoutPrice = 0L;
        }
        if ((item = this.sellContainer.getItemToSell()) == null) {
            this.errorMessage = StatCollector.func_74838_a((String)"auction.sell.noItem");
            return;
        }
        if (this.startingPrice <= 0L) {
            this.errorMessage = StatCollector.func_74838_a((String)"auction.sell.invalidPrice");
            return;
        }
        if (this.allowBuyout && this.buyoutPrice > 0L && this.buyoutPrice < this.startingPrice) {
            this.errorMessage = StatCollector.func_74838_a((String)"auction.sell.buyoutTooLow");
            return;
        }
        int inventoryCount = this.sellContainer.countItemInInventory(item);
        if (item.field_77994_a > inventoryCount) {
            this.errorMessage = StatCollector.func_74838_a((String)"auction.sell.notEnoughItems");
            return;
        }
        AuctionActionPacket.createListing(item, this.startingPrice, this.allowBuyout ? this.buyoutPrice : 0L);
        this.sellContainer.clearSellSlot();
        AuctionActionPacket.openPage(0);
    }

    @Override
    protected void drawAuctionContent(float partialTicks, int mouseX, int mouseY) {
        int slotX = this.field_147003_i + this.contentX;
        int slotY = this.field_147009_r + this.contentY + 50;
        this.drawAuctionSlot(slotX, slotY);
        String feeText = StatCollector.func_74838_a((String)"auction.sell.fee").replace("%s", AuctionFormatUtil.formatCurrency(AuctionClientConfig.getListingFee()) + " " + AuctionClientConfig.getCurrencyName());
        this.field_146289_q.func_78276_b(EnumChatFormatting.GRAY + feeText, this.field_147003_i + this.contentX, this.field_147009_r + this.contentY + 77, 0xFFFFFF);
        if (this.errorMessage != null) {
            this.field_146289_q.func_78276_b(EnumChatFormatting.RED + this.errorMessage, this.field_147003_i + this.contentX, this.field_147009_r + this.contentY + 95, 0xFFFFFF);
        }
        this.drawSellSlotIndicator(slotX, slotY);
    }

    private void drawSellSlotIndicator(int slotX, int slotY) {
        ItemStack sellItem = this.sellContainer.getItemToSell();
        if (sellItem != null) {
            int inventoryCount = this.sellContainer.countItemInInventory(sellItem);
            String countText = sellItem.field_77994_a + "/" + inventoryCount;
            int color = sellItem.field_77994_a <= inventoryCount ? 0x55FF55 : 0xFF5555;
            this.field_146289_q.func_78261_a(countText, slotX + 18 + 4, slotY + 5, color);
        } else {
            String hint = StatCollector.func_74838_a((String)"auction.sell.placeItem");
            this.field_146289_q.func_78276_b(EnumChatFormatting.GRAY + hint, slotX + 20, slotY + 5, 0xFFFFFF);
        }
    }

    protected void func_146984_a(Slot slot, int slotIndex, int mouseButton, int clickType) {
        if (slot == null) {
            return;
        }
        if (this.sellContainer.isSellSlot(slotIndex)) {
            ItemStack sellItem = this.sellContainer.getItemToSell();
            if (sellItem != null) {
                boolean removeAll = mouseButton == 0;
                this.sellContainer.removeFromSellSlot(removeAll);
                NoppesUtil.clickSound();
            }
            return;
        }
        if (this.sellContainer.isPlayerInventorySlot(slotIndex)) {
            ItemStack sourceStack = slot.func_75211_c();
            if (sourceStack != null) {
                boolean fullStack = mouseButton == 0;
                this.sellContainer.addToSellSlot(slotIndex, fullStack);
                NoppesUtil.clickSound();
            }
            return;
        }
    }

    @Override
    protected void func_146979_b(int mouseX, int mouseY) {
        super.func_146979_b(mouseX, mouseY);
        if (this.hasSubGui()) {
            return;
        }
        this.drawSellPageTooltips(mouseX, mouseY);
    }

    private void drawSellPageTooltips(int mouseX, int mouseY) {
        GuiAuctionNavButton btn;
        List<String> tooltip = null;
        GuiNpcButton confirmBtn = this.getButton(this.btnConfirmId);
        if (confirmBtn instanceof GuiAuctionNavButton && (btn = (GuiAuctionNavButton)confirmBtn).isHovered()) {
            tooltip = btn.getTooltipLines();
        }
        if (this.btnAllowBuyout != null && this.btnAllowBuyout.isHovered()) {
            tooltip = this.btnAllowBuyout.getTooltipLines();
        }
        if (tooltip != null && !tooltip.isEmpty()) {
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)1048575);
            this.drawHoveringText(tooltip, mouseX - this.field_147003_i, mouseY - this.field_147009_r, this.field_146289_q);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }
}

