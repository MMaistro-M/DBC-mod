/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.inventory.Slot
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
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
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.player.GuiAuctionInterface;
import noppes.npcs.client.gui.player.SubGuiAuctionSearch;
import noppes.npcs.client.gui.util.GuiAuctionNavButton;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumAuctionSort;
import noppes.npcs.containers.ContainerAuctionListing;
import noppes.npcs.controllers.data.AuctionFilter;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiAuctionListing
extends GuiAuctionInterface
implements ISubGuiListener,
IGuiData {
    private static final ResourceLocation SMALL_BUTTON = new ResourceLocation("customnpcs", "textures/gui/auction/small_button.png");
    private static final ResourceLocation SMALL_BUTTON_PRESS = new ResourceLocation("customnpcs", "textures/gui/auction/small_button_press.png");
    private static final ResourceLocation ICON_UP = new ResourceLocation("customnpcs", "textures/gui/auction/up.png");
    private static final ResourceLocation ICON_DOWN = new ResourceLocation("customnpcs", "textures/gui/auction/down.png");
    protected int gridX = 56;
    protected int gridY = 46;
    protected int cols = 9;
    protected int rows = 5;
    protected int pageUpX = 33;
    protected int pageDownX = 45;
    protected int pageBtnY = 128;
    protected int navYFilter = 109;
    private static final int BTN_PAGE_UP = 200;
    private static final int BTN_PAGE_DOWN = 201;
    private final ContainerAuctionListing listingContainer;
    private AuctionFilter filter;
    private int currentPage = 0;
    private int totalPages = 1;
    private GuiAuctionNavButton btnFilter;
    private GuiAuctionNavButton btnPageUp;
    private GuiAuctionNavButton btnPageDown;

    public GuiAuctionListing(EntityNPCInterface npc, ContainerAuctionListing container) {
        super(npc, container);
        this.listingContainer = container;
        this.filter = new AuctionFilter();
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.btnFilter = new GuiAuctionNavButton(103, this.field_147003_i + this.navX, this.field_147009_r + this.navYFilter, "auction.nav.filter", ICON_FILTER);
        this.addButton(this.btnFilter);
        this.btnPageUp = new GuiAuctionNavButton(200, this.field_147003_i + this.pageUpX, this.field_147009_r + this.pageBtnY, 10, 8, "auction.page.prev", ICON_UP, SMALL_BUTTON, SMALL_BUTTON_PRESS);
        this.addButton(this.btnPageUp);
        this.btnPageDown = new GuiAuctionNavButton(201, this.field_147003_i + this.pageDownX, this.field_147009_r + this.pageBtnY, 10, 8, "auction.page.next", ICON_DOWN, SMALL_BUTTON, SMALL_BUTTON_PRESS);
        this.addButton(this.btnPageDown);
        this.updateFilterTooltip();
        this.requestListings();
    }

    @Override
    protected int getCurrentPage() {
        return 0;
    }

    private void requestListings() {
        AuctionActionPacket.requestListings(this.filter, this.currentPage);
    }

    private void updatePaginationVisibility() {
        if (this.btnPageUp != null) {
            this.btnPageUp.setVisible(this.currentPage > 0);
        }
        if (this.btnPageDown != null) {
            this.btnPageDown.setVisible(this.currentPage < this.totalPages - 1);
        }
    }

    private void updateFilterTooltip() {
        if (this.btnFilter == null) {
            return;
        }
        ArrayList<String> tooltip = new ArrayList<String>();
        tooltip.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)"auction.nav.filter"));
        tooltip.add("");
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.filter.sort") + ":");
        tooltip.add(EnumChatFormatting.WHITE + "  " + this.filter.getSortBy().getDisplayName());
        tooltip.add("");
        tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.filter.search") + ":");
        if (this.filter.hasSearchText()) {
            tooltip.add(EnumChatFormatting.GREEN + "  \"" + this.filter.getSearchText() + "\"");
        } else {
            tooltip.add(EnumChatFormatting.DARK_GRAY + "  " + StatCollector.func_74838_a((String)"auction.filter.none"));
        }
        tooltip.add("");
        tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.filter.leftClick"));
        tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.filter.rightClick"));
        this.btnFilter.setCustomTooltip(tooltip);
    }

    @Override
    public void mouseEvent(int mouseX, int mouseY, int mouseButton) {
        if (this.btnFilter != null && this.btnFilter.isMouseOver(mouseX, mouseY) && mouseButton == 1) {
            this.cycleSortMode();
        }
    }

    @Override
    public void func_146284_a(GuiButton button) {
        super.func_146284_a(button);
        switch (button.field_146127_k) {
            case 103: {
                this.setSubGui(new SubGuiAuctionSearch(this.filter.getSearchText()));
                break;
            }
            case 200: {
                if (this.currentPage <= 0) break;
                --this.currentPage;
                this.requestListings();
                NoppesUtil.clickSound();
                break;
            }
            case 201: {
                if (this.currentPage >= this.totalPages - 1) break;
                ++this.currentPage;
                this.requestListings();
                NoppesUtil.clickSound();
            }
        }
    }

    private void cycleSortMode() {
        EnumAuctionSort[] values = EnumAuctionSort.values();
        int next = (this.filter.getSortBy().ordinal() + 1) % values.length;
        this.filter.setSortBy(values[next]);
        this.updateFilterTooltip();
        this.currentPage = 0;
        this.requestListings();
        NoppesUtil.clickSound();
    }

    public void onSearchSubmit(String searchText) {
        this.filter.setSearchText(searchText);
        this.updateFilterTooltip();
        this.currentPage = 0;
        this.requestListings();
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("ListingsData")) {
            this.currentPage = compound.func_74762_e("Page");
            this.totalPages = compound.func_74762_e("TotalPages");
            this.listingContainer.displayInventory.clear();
            NBTTagList list = compound.func_150295_c("Listings", 10);
            for (int i = 0; i < list.func_74745_c() && i < 45; ++i) {
                AuctionListing listing = AuctionListing.fromNBT(list.func_150305_b(i));
                this.listingContainer.displayInventory.setListing(i, listing);
            }
            this.updatePaginationVisibility();
        } else if (compound.func_74764_b("ListingsRefresh")) {
            this.requestListings();
        }
    }

    @Override
    protected void drawAuctionContent(float partialTicks, int mouseX, int mouseY) {
        for (int row = 0; row < this.rows; ++row) {
            for (int col = 0; col < this.cols; ++col) {
                int x = this.field_147003_i + this.gridX + col * 18;
                int y = this.field_147009_r + this.gridY + row * 18;
                this.drawAuctionSlot(x, y);
            }
        }
        this.drawPageIndicator();
    }

    private void drawPageIndicator() {
        if (this.totalPages > 1) {
            String text = this.currentPage + 1 + "/" + this.totalPages;
            int centerX = this.field_147003_i + this.pageUpX + 11;
            int x = centerX - this.field_146289_q.func_78256_a(text) / 2;
            this.field_146289_q.func_78276_b(text, x, this.field_147009_r + this.pageBtnY + 12, 0xFFFFFF);
        }
    }

    @Override
    protected void func_146979_b(int mouseX, int mouseY) {
        super.func_146979_b(mouseX, mouseY);
        if (this.hasSubGui()) {
            return;
        }
        this.drawListingPageTooltips(mouseX, mouseY);
    }

    private void drawListingPageTooltips(int mouseX, int mouseY) {
        List<String> tooltip = null;
        if (this.btnFilter != null && this.btnFilter.isHovered()) {
            tooltip = this.btnFilter.getTooltipLines();
        } else if (this.btnPageUp != null && this.btnPageUp.field_146125_m && this.btnPageUp.isHovered()) {
            tooltip = this.btnPageUp.getTooltipLines();
        } else if (this.btnPageDown != null && this.btnPageDown.field_146125_m && this.btnPageDown.isHovered()) {
            tooltip = this.btnPageDown.getTooltipLines();
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
        int listingIndex;
        AuctionListing listing;
        if (slot != null && this.listingContainer.isDisplaySlot(slot.field_75222_d) && (listing = this.listingContainer.displayInventory.getListing(listingIndex = slot.field_75222_d - this.listingContainer.getDisplaySlotStart())) != null) {
            AuctionActionPacket.openBidding(listing.id);
            NoppesUtil.clickSound();
        }
    }

    public AuctionFilter getFilter() {
        return this.filter;
    }

    public ContainerAuctionListing getListingContainer() {
        return this.listingContainer;
    }
}

