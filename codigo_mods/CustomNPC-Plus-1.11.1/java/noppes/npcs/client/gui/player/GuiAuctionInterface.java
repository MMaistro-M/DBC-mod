/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import java.util.List;
import kamkeel.npcs.network.packets.player.AuctionActionPacket;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiAuctionNavButton;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface;
import noppes.npcs.containers.ContainerAuction;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public abstract class GuiAuctionInterface
extends GuiContainerNPCInterface {
    protected static final ResourceLocation AUCTION_BACKGROUND = new ResourceLocation("customnpcs", "textures/gui/auction/auction.png");
    protected static final ResourceLocation AUCTION_SLOT = new ResourceLocation("customnpcs", "textures/gui/auction/auction_slot.png");
    protected static final ResourceLocation ICON_LISTINGS = new ResourceLocation("customnpcs", "textures/items/npcAncientScroll.png");
    protected static final ResourceLocation ICON_SELL = new ResourceLocation("customnpcs", "textures/gui/auction/sell.png");
    protected static final ResourceLocation ICON_CLAIMS = new ResourceLocation("customnpcs", "textures/gui/auction/my_auctions.png");
    protected static final ResourceLocation ICON_FILTER = new ResourceLocation("customnpcs", "textures/gui/auction/search.png");
    public static final int PAGE_LISTINGS = 0;
    public static final int PAGE_SELL = 1;
    public static final int PAGE_CLAIMS = 2;
    protected static final int OVERLAY_DARK = -1073741824;
    protected static final int BTN_NAV_LISTINGS = 100;
    protected static final int BTN_NAV_SELL = 101;
    protected static final int BTN_NAV_CLAIMS = 102;
    protected static final int BTN_NAV_FILTER = 103;
    protected int navX = 35;
    protected int navYListings = 46;
    protected int navYSell = 67;
    protected int navYClaims = 88;
    protected final ContainerAuction auctionContainer;
    protected GuiAuctionNavButton btnListings;
    protected GuiAuctionNavButton btnSell;
    protected GuiAuctionNavButton btnClaims;

    public GuiAuctionInterface(EntityNPCInterface npc, ContainerAuction container) {
        super(npc, container);
        this.auctionContainer = container;
        this.field_146999_f = 256;
        this.field_147000_g = 256;
        this.closeOnEsc = true;
        this.title = "";
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.initNavigationButtons();
    }

    protected void initNavigationButtons() {
        int page = this.getCurrentPage();
        this.btnListings = new GuiAuctionNavButton(100, this.field_147003_i + this.navX, this.field_147009_r + this.navYListings, "auction.nav.listings", ICON_LISTINGS);
        this.btnListings.setSelected(page == 0);
        this.addButton(this.btnListings);
        this.btnSell = new GuiAuctionNavButton(101, this.field_147003_i + this.navX, this.field_147009_r + this.navYSell, "auction.nav.sell", ICON_SELL);
        this.btnSell.setSelected(page == 1);
        this.addButton(this.btnSell);
        this.btnClaims = new GuiAuctionNavButton(102, this.field_147003_i + this.navX, this.field_147009_r + this.navYClaims, "auction.nav.trades", ICON_CLAIMS);
        this.btnClaims.setSelected(page == 2);
        this.addButton(this.btnClaims);
    }

    protected abstract int getCurrentPage();

    @Override
    public void func_146284_a(GuiButton button) {
        super.func_146284_a(button);
        int page = this.getCurrentPage();
        if (button.field_146127_k == 100 && page != 0) {
            AuctionActionPacket.openPage(0);
        } else if (button.field_146127_k == 101 && page != 1) {
            AuctionActionPacket.openPage(1);
        } else if (button.field_146127_k == 102 && page != 2) {
            AuctionActionPacket.openPage(2);
        }
    }

    @Override
    protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
        this.func_146270_b(0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(AUCTION_BACKGROUND);
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        GL11.glDisable((int)2896);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.drawAuctionContent(partialTicks, mouseX, mouseY);
        RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        super.func_146976_a(partialTicks, mouseX, mouseY);
    }

    @Override
    protected void func_146979_b(int mouseX, int mouseY) {
        if (!this.hasSubGui()) {
            this.drawNavButtonTooltips(mouseX, mouseY);
        }
    }

    protected void drawNavButtonTooltips(int mouseX, int mouseY) {
        List<String> tooltip = null;
        if (this.btnListings != null && this.btnListings.isHovered()) {
            tooltip = this.btnListings.getTooltipLines();
        } else if (this.btnSell != null && this.btnSell.isHovered()) {
            tooltip = this.btnSell.getTooltipLines();
        } else if (this.btnClaims != null && this.btnClaims.isHovered()) {
            tooltip = this.btnClaims.getTooltipLines();
        }
        if (tooltip != null) {
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)1048575);
            this.drawHoveringText(tooltip, mouseX - this.field_147003_i, mouseY - this.field_147009_r, this.field_146289_q);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    protected void drawAuctionSlot(int x, int y) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(AUCTION_SLOT);
        Gui.func_146110_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)18, (int)18, (float)18.0f, (float)18.0f);
    }

    protected void drawColoredOverlay(int x, int y, int color) {
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Gui.func_73734_a((int)(x + 1), (int)(y + 1), (int)(x + 17), (int)(y + 17), (int)color);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    protected void drawDarkenedOverlay(int x, int y) {
        this.drawColoredOverlay(x, y, -1073741824);
    }

    protected void drawIconOverlay(int x, int y, ResourceLocation icon) {
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(icon);
        Gui.func_146110_a((int)(x + 1), (int)(y + 1), (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    protected abstract void drawAuctionContent(float var1, int var2, int var3);

    @Override
    public void save() {
    }
}

