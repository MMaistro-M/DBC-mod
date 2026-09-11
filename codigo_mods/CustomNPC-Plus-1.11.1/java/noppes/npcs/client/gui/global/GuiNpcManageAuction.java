/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.network.packets.request.auction.ManageAuctionPacket;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.AuctionClientConfig;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.constants.EnumAuctionSort;
import noppes.npcs.constants.EnumClaimType;
import noppes.npcs.containers.ContainerManageAuction;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.AuctionFilter;
import noppes.npcs.controllers.data.AuctionListing;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.util.AuctionFormatUtil;
import org.lwjgl.opengl.GL11;

public class GuiNpcManageAuction
extends GuiContainerNPCInterface2
implements IGuiData,
ITextfieldListener {
    private static final ResourceLocation SLOT_DEFAULT = new ResourceLocation("customnpcs", "textures/gui/slot.png");
    private static final ResourceLocation SLOT_AUCTION = new ResourceLocation("customnpcs", "textures/gui/auction/auction_slot.png");
    private static final ResourceLocation ICON_X = new ResourceLocation("customnpcs", "textures/gui/auction/x_icon.png");
    private static final ResourceLocation ICON_X_CANCEL = new ResourceLocation("customnpcs", "textures/gui/auction/x_icon_blue.png");
    private static final ResourceLocation ICON_CHECK = new ResourceLocation("customnpcs", "textures/gui/auction/check_icon.png");
    private static final ResourceLocation COIN_ICON = new ResourceLocation("customnpcs", "textures/items/npcCoinGold.png");
    private static final int TINT_BLUE = 1615888639;
    private static final int TINT_GREEN = 1615920976;
    private static final int TINT_RED = 1627344976;
    private static final int BTN_TAB_LISTINGS = 100;
    private static final int BTN_TAB_CREATE = 101;
    private static final int BTN_TAB_CLAIMS = 102;
    private static final int BTN_PAGE_PREV = 110;
    private static final int BTN_PAGE_NEXT = 111;
    private static final int BTN_SORT = 112;
    private static final int BTN_CREATE = 120;
    private static final int TXT_SEARCH = 1;
    private static final int TXT_FAKE_SELLER = 2;
    private static final int TXT_FAKE_START = 3;
    private static final int TXT_FAKE_BUYOUT = 4;
    private static final int TXT_FAKE_DURATION = 5;
    private static final int RIGHT_X = 188;
    private static final int RIGHT_WIDTH = 220;
    private static final int TAB_Y = 10;
    private static final int LISTINGS_SORT_Y = 40;
    private static final int LISTINGS_SEARCH_LABEL_Y = 66;
    private static final int LISTINGS_SEARCH_FIELD_Y = 76;
    private static final int LISTINGS_HINT_Y = 98;
    private static final int LISTINGS_HINT2_Y = 110;
    private static final int LISTINGS_HINT3_Y = 122;
    private static final int LISTINGS_HINT4_Y = 134;
    private static final int LISTINGS_DETAILS_TITLE_Y = 42;
    private static final int DETAILS_PANEL_X = 188;
    private static final int DETAILS_PANEL_Y = 40;
    private static final int DETAILS_PANEL_W = 212;
    private static final int DETAILS_PANEL_H = 138;
    private static final int CREATE_FIELD_X = 270;
    private static final int CREATE_FIELD_WIDTH = 126;
    private static final int CREATE_SELLER_Y = 52;
    private static final int CREATE_START_Y = 78;
    private static final int CREATE_BUYOUT_Y = 104;
    private static final int CREATE_DURATION_Y = 130;
    private static final int CREATE_BUTTON_Y = 178;
    private final ContainerManageAuction manageContainer;
    private final AuctionFilter filter = new AuctionFilter();
    private Tab activeTab = Tab.LISTINGS;
    private String searchText = "";
    private String fakeSeller = "Server";
    private String fakeStartPrice = "1";
    private String fakeBuyoutPrice = "";
    private String fakeDurationHours = Integer.toString(Math.max(1, AuctionClientConfig.getAuctionDurationHours()));
    private int listingsPage = 0;
    private int listingsTotalPages = 1;
    private int listingsTotalCount = 0;
    private int claimsPage = 0;
    private int claimsTotalPages = 1;
    private int claimsTotalCount = 0;
    private int claimsListingCount = 0;
    private int claimsClaimCount = 0;
    private int pendingDisplaySlot = -1;
    private PendingAction pendingAction = PendingAction.NONE;
    private String selectedListingId = null;
    private AuctionListing selectedListing = null;
    private int selectedDisplaySlot = -1;
    private boolean listingsDetailsView = false;
    private String errorMessage = null;
    private boolean createRequestPending = false;

    public GuiNpcManageAuction(EntityNPCInterface npc, ContainerManageAuction container) {
        super(npc, container);
        this.manageContainer = container;
        this.drawDefaultBackground = false;
        this.field_147000_g = 200;
        this.title = "";
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addButton(new GuiNpcButton(100, this.field_147003_i + 188, this.field_147009_r + 10, 70, 20, "global.auction.manage.listings"));
        this.addButton(new GuiNpcButton(101, this.field_147003_i + 188 + 74, this.field_147009_r + 10, 70, 20, "global.auction.manage.create"));
        this.addButton(new GuiNpcButton(102, this.field_147003_i + 188 + 148, this.field_147009_r + 10, 70, 20, "global.auction.manage.claims"));
        this.updateTabButtonState();
        int pageButtonY = this.field_147009_r + this.field_147000_g - 24;
        this.addButton(new GuiNpcButton(110, this.field_147003_i + 188, pageButtonY, 20, 20, "<"));
        this.addButton(new GuiNpcButton(111, this.field_147003_i + 188 + 24, pageButtonY, 20, 20, ">"));
        if (this.activeTab == Tab.LISTINGS && !this.listingsDetailsView) {
            String sortName = this.filter.getSortBy().getDisplayName();
            this.addButton(new GuiNpcButton(112, this.field_147003_i + 188, this.field_147009_r + 40, 212, 20, StatCollector.func_74838_a((String)"global.auction.manage.sort") + ": " + sortName));
            this.addTextField(new GuiNpcTextField(1, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 188, this.field_147009_r + 76, 212, 18, this.searchText));
            this.requestListings();
        } else if (this.activeTab == Tab.LISTINGS) {
            this.manageContainer.setDisplayMode(ContainerManageAuction.DisplayMode.LISTINGS);
        } else if (this.activeTab == Tab.CREATE) {
            this.addTextField(new GuiNpcTextField(2, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 270, this.field_147009_r + 52, 126, 18, this.fakeSeller));
            GuiNpcTextField startField = new GuiNpcTextField(3, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 270, this.field_147009_r + 78, 126, 18, this.fakeStartPrice);
            startField.setIntegersOnly();
            this.addTextField(startField);
            GuiNpcTextField buyoutField = new GuiNpcTextField(4, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 270, this.field_147009_r + 104, 126, 18, this.fakeBuyoutPrice);
            buyoutField.setIntegersOnly();
            this.addTextField(buyoutField);
            GuiNpcTextField durationField = new GuiNpcTextField(5, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 270, this.field_147009_r + 130, 126, 18, this.fakeDurationHours);
            durationField.setIntegersOnly();
            this.addTextField(durationField);
            this.addButton(new GuiNpcButton(120, this.field_147003_i + 188, this.field_147009_r + 178, 212, 20, "global.auction.manage.create.submit"));
        } else if (this.activeTab == Tab.CLAIMS) {
            this.requestClaims();
        }
        this.syncContainerSlots();
        this.updatePageButtons();
    }

    private void switchTab(Tab tab) {
        if (this.activeTab == tab) {
            return;
        }
        this.activeTab = tab;
        this.listingsDetailsView = false;
        if (tab != Tab.LISTINGS) {
            this.selectedListing = null;
            this.selectedListingId = null;
            this.selectedDisplaySlot = -1;
        }
        this.clearPending();
        this.errorMessage = null;
        this.func_73866_w_();
    }

    private void updateTabButtonState() {
        GuiNpcButton listingsBtn = this.getButton(100);
        GuiNpcButton createBtn = this.getButton(101);
        GuiNpcButton claimsBtn = this.getButton(102);
        if (listingsBtn != null) {
            boolean bl = listingsBtn.field_146124_l = this.activeTab != Tab.LISTINGS || this.listingsDetailsView;
        }
        if (createBtn != null) {
            boolean bl = createBtn.field_146124_l = this.activeTab != Tab.CREATE;
        }
        if (claimsBtn != null) {
            claimsBtn.field_146124_l = this.activeTab != Tab.CLAIMS;
        }
    }

    private void syncContainerSlots() {
        boolean showCreate = this.activeTab == Tab.CREATE;
        this.manageContainer.setCreateSlotVisible(showCreate);
        boolean showDetail = this.activeTab == Tab.LISTINGS && this.listingsDetailsView && this.selectedListing != null;
        this.manageContainer.setDetailSlotVisible(showDetail);
        if (showDetail) {
            this.manageContainer.setDetailItem(this.selectedListing.item);
        } else {
            this.manageContainer.clearDetailItem();
        }
    }

    private void updatePageButtons() {
        GuiNpcButton prev = this.getButton(110);
        GuiNpcButton next = this.getButton(111);
        if (prev == null || next == null) {
            return;
        }
        if (this.activeTab == Tab.CREATE) {
            prev.field_146125_m = false;
            next.field_146125_m = false;
            prev.field_146124_l = false;
            next.field_146124_l = false;
            return;
        }
        if (this.activeTab == Tab.LISTINGS && this.listingsDetailsView) {
            prev.field_146125_m = false;
            next.field_146125_m = false;
            prev.field_146124_l = false;
            next.field_146124_l = false;
            return;
        }
        prev.field_146125_m = true;
        next.field_146125_m = true;
        if (this.activeTab == Tab.LISTINGS) {
            prev.field_146124_l = this.listingsPage > 0;
            next.field_146124_l = this.listingsPage < this.listingsTotalPages - 1;
        } else {
            prev.field_146124_l = this.claimsPage > 0;
            next.field_146124_l = this.claimsPage < this.claimsTotalPages - 1;
        }
    }

    private void requestListings() {
        this.filter.setSearchText(this.searchText);
        this.manageContainer.setDisplayMode(ContainerManageAuction.DisplayMode.LISTINGS);
        ManageAuctionPacket.requestListings(this.filter, this.listingsPage);
    }

    private void requestClaims() {
        this.manageContainer.setDisplayMode(ContainerManageAuction.DisplayMode.CLAIMS);
        ManageAuctionPacket.requestGlobalClaims(this.claimsPage);
    }

    private void cycleSort() {
        EnumAuctionSort[] values = EnumAuctionSort.values();
        int next = (this.filter.getSortBy().ordinal() + 1) % values.length;
        this.filter.setSortBy(values[next]);
        this.listingsPage = 0;
        this.clearPending();
        this.requestListings();
        this.func_73866_w_();
    }

    private void createFakeAuction() {
        String seller;
        ItemStack staged;
        GuiNpcTextField sellerField = this.getTextField(2);
        GuiNpcTextField startField = this.getTextField(3);
        GuiNpcTextField buyoutField = this.getTextField(4);
        GuiNpcTextField durationField = this.getTextField(5);
        if (sellerField != null) {
            this.fakeSeller = sellerField.func_146179_b();
        }
        if (startField != null) {
            this.fakeStartPrice = startField.func_146179_b();
        }
        if (buyoutField != null) {
            this.fakeBuyoutPrice = buyoutField.func_146179_b();
        }
        if (durationField != null) {
            this.fakeDurationHours = durationField.func_146179_b();
        }
        if ((staged = this.manageContainer.getCreateItem()) == null) {
            this.errorMessage = StatCollector.func_74838_a((String)"global.auction.manage.error.noItem");
            return;
        }
        long start = this.parseLong(this.fakeStartPrice, 0L);
        long buyout = this.parseLong(this.fakeBuyoutPrice, 0L);
        int duration = (int)this.parseLong(this.fakeDurationHours, AuctionClientConfig.getAuctionDurationHours());
        if (duration <= 0) {
            duration = Math.max(1, AuctionClientConfig.getAuctionDurationHours());
        }
        if (start <= 0L) {
            this.errorMessage = StatCollector.func_74838_a((String)"global.auction.manage.error.invalidStart");
            return;
        }
        if (buyout > 0L && buyout < start) {
            this.errorMessage = StatCollector.func_74838_a((String)"global.auction.manage.error.invalidBuyout");
            return;
        }
        String string = seller = this.fakeSeller != null ? this.fakeSeller.trim() : "";
        if (seller.isEmpty()) {
            this.fakeSeller = seller = "Server";
        }
        ManageAuctionPacket.createFakeListing(staged, seller, start, buyout, duration);
        this.createRequestPending = true;
        this.errorMessage = null;
    }

    private long parseLong(String value, long fallback) {
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        try {
            return Long.parseLong(value.trim());
        }
        catch (NumberFormatException ignored) {
            return fallback;
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        if (!(guibutton instanceof GuiNpcButton)) {
            return;
        }
        if (guibutton.field_146127_k == 100) {
            if (this.activeTab == Tab.LISTINGS && this.listingsDetailsView) {
                this.listingsDetailsView = false;
                this.selectedListing = null;
                this.selectedListingId = null;
                this.selectedDisplaySlot = -1;
                this.clearPending();
                this.func_73866_w_();
            } else {
                this.switchTab(Tab.LISTINGS);
            }
            return;
        }
        if (guibutton.field_146127_k == 101) {
            this.switchTab(Tab.CREATE);
            return;
        }
        if (guibutton.field_146127_k == 102) {
            this.switchTab(Tab.CLAIMS);
            return;
        }
        if (guibutton.field_146127_k == 110) {
            if (this.activeTab == Tab.LISTINGS && this.listingsPage > 0) {
                --this.listingsPage;
                this.clearPending();
                this.requestListings();
                NoppesUtil.clickSound();
            } else if (this.activeTab == Tab.CLAIMS && this.claimsPage > 0) {
                --this.claimsPage;
                this.clearPending();
                this.requestClaims();
                NoppesUtil.clickSound();
            }
            this.updatePageButtons();
            return;
        }
        if (guibutton.field_146127_k == 111) {
            if (this.activeTab == Tab.LISTINGS && this.listingsPage < this.listingsTotalPages - 1) {
                ++this.listingsPage;
                this.clearPending();
                this.requestListings();
                NoppesUtil.clickSound();
            } else if (this.activeTab == Tab.CLAIMS && this.claimsPage < this.claimsTotalPages - 1) {
                ++this.claimsPage;
                this.clearPending();
                this.requestClaims();
                NoppesUtil.clickSound();
            }
            this.updatePageButtons();
            return;
        }
        if (guibutton.field_146127_k == 112) {
            this.cycleSort();
            NoppesUtil.clickSound();
            return;
        }
        if (guibutton.field_146127_k == 120) {
            this.createFakeAuction();
            NoppesUtil.clickSound();
        }
    }

    protected void func_146984_a(Slot slot, int slotIndex, int mouseButton, int clickType) {
        if (slot == null) {
            this.clearPending();
            return;
        }
        if (this.manageContainer.isDisplaySlot(slotIndex)) {
            int displayIndex = this.manageContainer.toDisplayIndex(slotIndex);
            if (this.activeTab == Tab.LISTINGS) {
                this.handleListingsSlotClick(displayIndex, mouseButton);
                return;
            }
            if (this.activeTab == Tab.CLAIMS) {
                this.handleClaimsSlotClick(displayIndex, mouseButton);
                return;
            }
        }
        if (this.activeTab == Tab.CREATE) {
            if (this.manageContainer.isCreateSlot(slotIndex)) {
                if (this.manageContainer.getCreateItem() != null) {
                    boolean removeAll = mouseButton == 0;
                    this.manageContainer.removeFromCreateSlot(removeAll);
                    NoppesUtil.clickSound();
                }
                return;
            }
            if (this.manageContainer.isPlayerSlot(slotIndex)) {
                ItemStack stack = slot.func_75211_c();
                if (stack != null) {
                    boolean fullStack = mouseButton == 0;
                    this.manageContainer.addToCreateSlot(slotIndex, fullStack);
                    NoppesUtil.clickSound();
                }
                return;
            }
        }
        this.clearPending();
    }

    private void handleListingsSlotClick(int displayIndex, int mouseButton) {
        AuctionListing listing = this.manageContainer.getListingAtDisplay(displayIndex);
        if (listing == null) {
            this.clearPending();
            return;
        }
        if (this.pendingAction != PendingAction.NONE) {
            if (displayIndex == this.pendingDisplaySlot && this.isConfirmInput(this.pendingAction, mouseButton)) {
                ManageAuctionPacket.manageListing(listing.id, this.pendingAction == PendingAction.CANCEL);
                this.clearPending();
                this.playConfirmSound();
            } else {
                this.clearPending();
            }
            return;
        }
        if (mouseButton == 0) {
            this.selectListing(displayIndex, listing);
            this.clearPending();
            this.listingsDetailsView = true;
            this.func_73866_w_();
            NoppesUtil.clickSound();
            return;
        }
        if (this.isStopStartInput(mouseButton) || this.isCancelStartInput(mouseButton)) {
            PendingAction action = GuiNpcManageAuction.func_146272_n() ? PendingAction.CANCEL : PendingAction.STOP;
            this.setPending(displayIndex, action);
            NoppesUtil.clickSound();
            return;
        }
        this.clearPending();
    }

    private void handleClaimsSlotClick(int displayIndex, int mouseButton) {
        PendingAction action;
        AuctionListing listing = this.manageContainer.getListingAtDisplay(displayIndex);
        if (listing != null) {
            if (this.pendingAction != PendingAction.NONE) {
                if (displayIndex == this.pendingDisplaySlot && this.isConfirmInput(this.pendingAction, mouseButton)) {
                    ManageAuctionPacket.manageListing(listing.id, this.pendingAction == PendingAction.CANCEL);
                    this.clearPending();
                    this.playConfirmSound();
                } else {
                    this.clearPending();
                }
                return;
            }
            if (this.isStopStartInput(mouseButton) || this.isCancelStartInput(mouseButton)) {
                PendingAction action2 = GuiNpcManageAuction.func_146272_n() ? PendingAction.CANCEL : PendingAction.STOP;
                this.setPending(displayIndex, action2);
                NoppesUtil.clickSound();
            } else {
                this.clearPending();
            }
            return;
        }
        AuctionClaim claim = this.manageContainer.getClaimAtDisplay(displayIndex);
        if (claim == null) {
            this.clearPending();
            return;
        }
        PendingAction pendingAction = action = claim.type.isItem() ? PendingAction.CLAIM_ITEM : PendingAction.CLAIM_CURRENCY;
        if (this.pendingAction != PendingAction.NONE) {
            if (displayIndex == this.pendingDisplaySlot && this.pendingAction == action && this.isConfirmInput(action, mouseButton)) {
                if (action == PendingAction.CLAIM_ITEM) {
                    ManageAuctionPacket.claimGlobalItem(claim.id);
                } else {
                    ManageAuctionPacket.claimGlobalCurrency(claim.id);
                }
                this.clearPending();
                this.playConfirmSound();
            } else {
                this.clearPending();
            }
            return;
        }
        if (mouseButton == 0) {
            this.setPending(displayIndex, action);
            NoppesUtil.clickSound();
            return;
        }
        this.clearPending();
    }

    private boolean isStopStartInput(int mouseButton) {
        return mouseButton == 1 && !GuiNpcManageAuction.func_146272_n();
    }

    private boolean isCancelStartInput(int mouseButton) {
        return mouseButton == 1 && GuiNpcManageAuction.func_146272_n();
    }

    private boolean isConfirmInput(PendingAction action, int mouseButton) {
        if (action == PendingAction.CLAIM_ITEM || action == PendingAction.CLAIM_CURRENCY) {
            return mouseButton == 0;
        }
        if (action == PendingAction.STOP) {
            return mouseButton == 1 && !GuiNpcManageAuction.func_146272_n();
        }
        if (action == PendingAction.CANCEL) {
            return mouseButton == 1 && GuiNpcManageAuction.func_146272_n();
        }
        return false;
    }

    private void selectListing(int displayIndex, AuctionListing listing) {
        this.selectedDisplaySlot = displayIndex;
        this.selectedListingId = listing != null ? listing.id : null;
        this.selectedListing = listing;
        this.syncContainerSlots();
        this.updateTabButtonState();
    }

    private void refreshSelectedListing(List<AuctionListing> pageListings) {
        this.selectedListing = null;
        this.selectedDisplaySlot = -1;
        if (this.selectedListingId == null || pageListings == null) {
            this.syncContainerSlots();
            return;
        }
        for (int i = 0; i < pageListings.size(); ++i) {
            AuctionListing listing = pageListings.get(i);
            if (listing == null || !this.selectedListingId.equals(listing.id)) continue;
            this.selectedListing = listing;
            this.selectedDisplaySlot = i;
            break;
        }
        if (this.selectedListing == null) {
            this.selectedListingId = null;
        }
        if (this.selectedListing == null) {
            this.listingsDetailsView = false;
        }
        this.syncContainerSlots();
        this.updateTabButtonState();
    }

    private void setPending(int displaySlot, PendingAction action) {
        this.pendingDisplaySlot = displaySlot;
        this.pendingAction = action;
        this.manageContainer.setHiddenDisplaySlot(displaySlot);
    }

    private void clearPending() {
        this.manageContainer.clearHiddenDisplaySlot();
        this.pendingDisplaySlot = -1;
        this.pendingAction = PendingAction.NONE;
    }

    private void playConfirmSound() {
        this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("random.orb"), (float)1.0f));
    }

    @Override
    public void func_73869_a(char c, int i) {
        String text;
        GuiNpcTextField search;
        super.func_73869_a(c, i);
        if (this.activeTab == Tab.LISTINGS && (search = this.getTextField(1)) != null && search.func_146206_l() && !(text = search.func_146179_b()).equals(this.searchText)) {
            this.searchText = text;
            this.listingsPage = 0;
            this.clearPending();
            this.requestListings();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield == null) {
            return;
        }
        if (textfield.id == 1) {
            this.searchText = textfield.func_146179_b();
        } else if (textfield.id == 2) {
            this.fakeSeller = textfield.func_146179_b();
        } else if (textfield.id == 3) {
            this.fakeStartPrice = textfield.func_146179_b();
        } else if (textfield.id == 4) {
            this.fakeBuyoutPrice = textfield.func_146179_b();
        } else if (textfield.id == 5) {
            this.fakeDurationHours = textfield.func_146179_b();
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        int i;
        if (compound.func_74764_b("ManageAuctionListingsData")) {
            this.listingsPage = compound.func_74762_e("Page");
            this.listingsTotalPages = Math.max(1, compound.func_74762_e("TotalPages"));
            this.listingsTotalCount = compound.func_74762_e("TotalListings");
            NBTTagList list = compound.func_150295_c("Listings", 10);
            ArrayList<AuctionListing> listings = new ArrayList<AuctionListing>();
            for (i = 0; i < list.func_74745_c(); ++i) {
                listings.add(AuctionListing.fromNBT(list.func_150305_b(i)));
            }
            this.manageContainer.setListingsPage(listings);
            this.refreshSelectedListing(listings);
            this.updatePageButtons();
        }
        if (compound.func_74764_b("ManageAuctionClaimsData")) {
            this.claimsPage = compound.func_74762_e("Page");
            this.claimsTotalPages = Math.max(1, compound.func_74762_e("TotalPages"));
            this.claimsTotalCount = compound.func_74764_b("TotalEntries") ? compound.func_74762_e("TotalEntries") : compound.func_74762_e("TotalClaims");
            this.claimsClaimCount = compound.func_74762_e("TotalClaims");
            this.claimsListingCount = compound.func_74764_b("TotalListings") ? compound.func_74762_e("TotalListings") : 0;
            NBTTagList claimList = compound.func_150295_c("Claims", 10);
            ArrayList<AuctionClaim> claims = new ArrayList<AuctionClaim>();
            for (i = 0; i < claimList.func_74745_c(); ++i) {
                claims.add(AuctionClaim.fromNBT(claimList.func_150305_b(i)));
            }
            NBTTagList listingList = compound.func_150295_c("Listings", 10);
            ArrayList<AuctionListing> listings = new ArrayList<AuctionListing>();
            for (int i2 = 0; i2 < listingList.func_74745_c(); ++i2) {
                listings.add(AuctionListing.fromNBT(listingList.func_150305_b(i2)));
            }
            this.manageContainer.setClaimsAndListingsPage(claims, listings);
            this.updatePageButtons();
        }
        if (compound.func_74767_n("ManageAuctionRefresh")) {
            boolean createSuccess = compound.func_74767_n("ManageAuctionCreateSuccess");
            this.createRequestPending = false;
            if (createSuccess) {
                this.manageContainer.clearCreateSlot();
                this.listingsDetailsView = false;
                this.selectedListing = null;
                this.selectedListingId = null;
                this.selectedDisplaySlot = -1;
                this.activeTab = Tab.LISTINGS;
                this.clearPending();
                this.func_73866_w_();
                return;
            }
            this.clearPending();
            if (this.activeTab == Tab.LISTINGS) {
                this.requestListings();
            } else if (this.activeTab == Tab.CLAIMS) {
                this.requestClaims();
            }
        }
    }

    @Override
    protected void func_146976_a(float f, int mouseX, int mouseY) {
        super.func_146976_a(f, mouseX, mouseY);
        this.drawDisplaySlotBackgrounds();
        this.drawPlayerInventorySlotBackgrounds();
        this.drawRightPanelSlotBackgrounds();
        this.drawClaimsTintOverlay();
        this.drawSelectedListingOverlay();
        this.drawClaimIndicators();
        this.drawPendingOverlay();
    }

    private void drawDisplaySlotBackgrounds() {
        for (int row = 0; row < 5; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = this.field_147003_i + 8 + col * 18;
                int y = this.field_147009_r + 16 + row * 18;
                this.drawAuctionSlot(x, y);
            }
        }
    }

    private void drawPlayerInventorySlotBackgrounds() {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = this.field_147003_i + 8 + col * 18;
                int y = this.field_147009_r + 113 + row * 18;
                this.drawDefaultSlot(x, y);
            }
        }
        for (int col = 0; col < 9; ++col) {
            int x = this.field_147003_i + 8 + col * 18;
            int y = this.field_147009_r + 171;
            this.drawDefaultSlot(x, y);
        }
    }

    private void drawRightPanelSlotBackgrounds() {
        if (this.activeTab == Tab.LISTINGS && this.listingsDetailsView) {
            this.drawDetailsPanelBackground();
        }
        if (this.activeTab == Tab.LISTINGS && this.listingsDetailsView && this.selectedListing != null) {
            this.drawAuctionSlot(this.field_147003_i + 194, this.field_147009_r + 56);
        } else if (this.activeTab == Tab.CREATE) {
            this.drawAuctionSlot(this.field_147003_i + 188, this.field_147009_r + 156);
        }
    }

    private void drawDetailsPanelBackground() {
        int x = this.field_147003_i + 188;
        int y = this.field_147009_r + 40;
        int x2 = x + 212;
        int y2 = y + 138;
        Gui.func_73734_a((int)x, (int)y, (int)x2, (int)y2, (int)-871428337);
        Gui.func_73734_a((int)(x + 1), (int)(y + 1), (int)(x2 - 1), (int)(y2 - 1), (int)-2011160544);
        Gui.func_73734_a((int)x, (int)y, (int)x2, (int)(y + 1), (int)-12961222);
        Gui.func_73734_a((int)x, (int)(y2 - 1), (int)x2, (int)y2, (int)-12961222);
        Gui.func_73734_a((int)x, (int)y, (int)(x + 1), (int)y2, (int)-12961222);
        Gui.func_73734_a((int)(x2 - 1), (int)y, (int)x2, (int)y2, (int)-12961222);
    }

    private void drawSelectedListingOverlay() {
        if (this.activeTab != Tab.LISTINGS || this.selectedDisplaySlot < 0 || this.selectedDisplaySlot >= 45) {
            return;
        }
        int x = this.field_147003_i + 8 + this.selectedDisplaySlot % 9 * 18;
        int y = this.field_147009_r + 16 + this.selectedDisplaySlot / 9 * 18;
        this.drawColoredOverlay(x, y, 1345356031);
        Gui.func_73734_a((int)x, (int)y, (int)(x + 18), (int)(y + 1), (int)-7682817);
        Gui.func_73734_a((int)x, (int)(y + 17), (int)(x + 18), (int)(y + 18), (int)-7682817);
        Gui.func_73734_a((int)x, (int)y, (int)(x + 1), (int)(y + 18), (int)-7682817);
        Gui.func_73734_a((int)(x + 17), (int)y, (int)(x + 18), (int)(y + 18), (int)-7682817);
    }

    private void drawClaimIndicators() {
        if (this.activeTab != Tab.CLAIMS) {
            return;
        }
        for (int i = 0; i < 45; ++i) {
            boolean drawCoin;
            AuctionClaim claim = this.manageContainer.getClaimAtDisplay(i);
            if (claim == null) continue;
            boolean bl = drawCoin = claim.type == EnumClaimType.CURRENCY || claim.type == EnumClaimType.REFUND && claim.item == null;
            if (!drawCoin) continue;
            int x = this.field_147003_i + 8 + i % 9 * 18;
            int y = this.field_147009_r + 16 + i / 9 * 18;
            this.drawIconOverlay(x, y, COIN_ICON);
        }
    }

    private void drawClaimsTintOverlay() {
        if (this.activeTab != Tab.CLAIMS) {
            return;
        }
        for (int i = 0; i < 45; ++i) {
            int tint = this.getClaimsSlotTint(i);
            if (tint == 0) continue;
            int x = this.field_147003_i + 8 + i % 9 * 18;
            int y = this.field_147009_r + 16 + i / 9 * 18;
            this.drawColoredOverlay(x, y, tint);
        }
    }

    private int getClaimsSlotTint(int displayIndex) {
        AuctionListing listing = this.manageContainer.getListingAtDisplay(displayIndex);
        if (listing != null) {
            return 1615888639;
        }
        AuctionClaim claim = this.manageContainer.getClaimAtDisplay(displayIndex);
        if (claim == null) {
            return 0;
        }
        if (claim.type == EnumClaimType.CURRENCY) {
            return 1615920976;
        }
        if (claim.type == EnumClaimType.REFUND) {
            return 1627344976;
        }
        if (claim.type == EnumClaimType.ITEM) {
            return claim.isReturned ? 1627344976 : 1615920976;
        }
        return 1615920976;
    }

    private void drawPendingOverlay() {
        if (this.pendingDisplaySlot < 0 || this.pendingAction == PendingAction.NONE) {
            return;
        }
        int x = this.field_147003_i + 8 + this.pendingDisplaySlot % 9 * 18;
        int y = this.field_147009_r + 16 + this.pendingDisplaySlot / 9 * 18;
        int color = -2130706688;
        ResourceLocation icon = ICON_X;
        float iconR = 1.0f;
        float iconG = 1.0f;
        float iconB = 1.0f;
        if (this.pendingAction == PendingAction.STOP) {
            color = -2142207745;
            icon = ICON_X;
        } else if (this.pendingAction == PendingAction.CANCEL) {
            color = -2136981296;
            icon = ICON_X_CANCEL;
        } else if (this.pendingAction == PendingAction.CLAIM_ITEM || this.pendingAction == PendingAction.CLAIM_CURRENCY) {
            color = -2141847723;
            icon = ICON_CHECK;
        }
        this.drawColoredOverlay(x, y, color);
        this.drawIconOverlay(x, y, icon, iconR, iconG, iconB, 1.0f);
    }

    private void drawAuctionSlot(int x, int y) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(SLOT_AUCTION);
        Gui.func_146110_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)18, (int)18, (float)18.0f, (float)18.0f);
    }

    private void drawDefaultSlot(int x, int y) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(SLOT_DEFAULT);
        this.func_73729_b(x, y, 0, 0, 18, 18);
    }

    private void drawColoredOverlay(int x, int y, int color) {
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Gui.func_73734_a((int)(x + 1), (int)(y + 1), (int)(x + 17), (int)(y + 17), (int)color);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void drawIconOverlay(int x, int y, ResourceLocation icon) {
        this.drawIconOverlay(x, y, icon, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void drawIconOverlay(int x, int y, ResourceLocation icon, float r, float g, float b, float a) {
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)a);
        this.field_146297_k.field_71446_o.func_110577_a(icon);
        Gui.func_146110_a((int)(x + 1), (int)(y + 1), (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    @Override
    protected void func_146979_b(int mouseX, int mouseY) {
        super.func_146979_b(mouseX, mouseY);
        int textColor = CustomNpcResourceListener.DefaultTextColor;
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.title"), 8, 6, textColor);
        if (this.activeTab == Tab.LISTINGS) {
            if (this.listingsDetailsView) {
                this.drawListingsDetails(textColor);
            } else {
                this.drawPageText(this.listingsPage, this.listingsTotalPages, this.listingsTotalCount, textColor);
                this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.search"), 188, 66, textColor);
                this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.hint.listings"), 188, 98, textColor);
                this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.hint.stop"), 188, 110, textColor);
                this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.hint.cancel"), 188, 122, textColor);
                this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.selectListing"), 188, 134, textColor);
            }
        } else if (this.activeTab == Tab.CLAIMS) {
            this.drawPageText(this.claimsPage, this.claimsTotalPages, this.claimsTotalCount, textColor);
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.hint.claims"), 188, 58, textColor);
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.hint.stop"), 188, 70, textColor);
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.hint.cancel"), 188, 82, textColor);
            String totals = this.claimsClaimCount + " claims | " + this.claimsListingCount + " listings";
            this.field_146289_q.func_78276_b(totals, 188, 94, textColor);
        } else {
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.seller"), 188, 57, textColor);
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.startPrice"), 188, 83, textColor);
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.buyoutPrice"), 188, 109, textColor);
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.durationHours"), 188, 135, textColor);
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.stagedItem"), 210, 161, textColor);
            ItemStack staged = this.manageContainer.getCreateItem();
            if (staged != null) {
                int available = this.manageContainer.countItemInPlayerInventory(staged);
                String countText = staged.field_77994_a + "/" + available;
                int countColor = staged.field_77994_a <= available ? 0x55FF55 : 0xFF5555;
                this.field_146289_q.func_78261_a(countText, 210, 172, countColor);
            }
        }
        if (this.errorMessage != null && !this.errorMessage.isEmpty()) {
            this.field_146289_q.func_78276_b(EnumChatFormatting.RED + this.errorMessage, 188, this.field_147000_g - 12, 0xFFFFFF);
        }
        this.drawDynamicTooltip(mouseX, mouseY);
    }

    private void drawPageText(int page, int totalPages, int totalCount, int textColor) {
        String pageText = page + 1 + "/" + totalPages + " (" + totalCount + ")";
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"global.auction.manage.page") + ": " + pageText, 236, this.field_147000_g - 18, textColor);
    }

    private void drawListingsDetails(int textColor) {
        int panelLeft = 194;
        int panelRight = 394;
        int y = 42;
        this.field_146289_q.func_78276_b(EnumChatFormatting.AQUA + StatCollector.func_74838_a((String)"global.auction.manage.details"), panelLeft, y, 0xFFFFFF);
        y += 12;
        if (this.selectedListing == null) {
            this.field_146289_q.func_78276_b(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"global.auction.manage.selectListing"), panelLeft, y, 0xFFFFFF);
            return;
        }
        y = 57;
        this.drawDetailsLine(panelLeft + 24, panelRight, y, EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.seller").replace("%s", ""), EnumChatFormatting.WHITE + this.selectedListing.sellerName);
        long currentValue = this.selectedListing.hasBids() ? this.selectedListing.currentBid : this.selectedListing.startingPrice;
        String currentLabel = this.selectedListing.hasBids() ? StatCollector.func_74838_a((String)"auction.info.currentBid") : StatCollector.func_74838_a((String)"auction.info.startingPrice");
        this.drawDetailsLine(panelLeft + 24, panelRight, y += 11, EnumChatFormatting.YELLOW + currentLabel, EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(currentValue));
        y += 11;
        if (this.selectedListing.hasBuyout()) {
            this.drawDetailsLine(panelLeft + 24, panelRight, y, EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.info.buyoutPrice"), EnumChatFormatting.GREEN + AuctionFormatUtil.formatCurrencyWithName(this.selectedListing.buyoutPrice));
            y += 11;
        }
        this.drawDetailsLine(panelLeft + 24, panelRight, y, EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.info.bidCount"), EnumChatFormatting.WHITE + Integer.toString(this.selectedListing.bidCount));
        long remaining = this.selectedListing.getTimeRemaining();
        EnumChatFormatting timeColor = AuctionFormatUtil.isTimeUrgent(remaining) ? EnumChatFormatting.RED : EnumChatFormatting.WHITE;
        this.drawDetailsLine(panelLeft + 24, panelRight, y += 11, EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.info.timeLeft"), timeColor + AuctionFormatUtil.formatTimeRemaining(remaining));
    }

    private void drawDetailsLine(int leftX, int rightX, int y, String label, String value) {
        this.field_146289_q.func_78276_b(label, leftX, y, 0xFFFFFF);
        int width = this.field_146289_q.func_78256_a(value);
        this.field_146289_q.func_78276_b(value, rightX - width, y, 0xFFFFFF);
    }

    private int getHoveredDisplaySlot(int mouseX, int mouseY) {
        int relX = mouseX - 8;
        int relY = mouseY - 16;
        if (relX < 0 || relY < 0) {
            return -1;
        }
        int col = relX / 18;
        int row = relY / 18;
        if (col < 0 || col >= 9 || row < 0 || row >= 5) {
            return -1;
        }
        return col + row * 9;
    }

    private void drawDynamicTooltip(int mouseX, int mouseY) {
        if (this.hasSubGui()) {
            return;
        }
        int guiMouseX = mouseX - this.field_147003_i;
        int guiMouseY = mouseY - this.field_147009_r;
        int hovered = this.getHoveredDisplaySlot(guiMouseX, guiMouseY);
        ArrayList<String> tooltip = new ArrayList<String>();
        if (hovered >= 0) {
            AuctionClaim claim;
            if (hovered == this.pendingDisplaySlot && this.pendingAction != PendingAction.NONE) {
                if (this.pendingAction == PendingAction.STOP) {
                    tooltip.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)"global.auction.manage.confirm.stop"));
                } else if (this.pendingAction == PendingAction.CANCEL) {
                    tooltip.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"global.auction.manage.confirm.cancel"));
                } else {
                    tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"global.auction.manage.confirm.claim"));
                }
                tooltip.add(EnumChatFormatting.DARK_GRAY + StatCollector.func_74838_a((String)"auction.trades.otherClickCancel"));
            } else if (this.activeTab == Tab.CLAIMS && (claim = this.manageContainer.getClaimAtDisplay(hovered)) != null && claim.item == null) {
                if (claim.type == EnumClaimType.CURRENCY) {
                    tooltip.add(EnumChatFormatting.GREEN + "Sold Auction Claim");
                    if (claim.itemName != null && !claim.itemName.isEmpty()) {
                        tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.soldItem").replace("%s", EnumChatFormatting.WHITE + claim.itemName));
                    }
                    if (claim.otherPlayerName != null && !claim.otherPlayerName.isEmpty()) {
                        tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.buyer").replace("%s", EnumChatFormatting.WHITE + claim.otherPlayerName));
                    }
                    tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.claim.amount").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(claim.currency)));
                } else if (claim.type == EnumClaimType.REFUND) {
                    tooltip.add(EnumChatFormatting.RED + "Outbid Refund Claim");
                    if (claim.otherPlayerName != null && !claim.otherPlayerName.isEmpty()) {
                        tooltip.add(EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"auction.claim.outbidBy").replace("%s", EnumChatFormatting.WHITE + claim.otherPlayerName));
                    }
                    tooltip.add(EnumChatFormatting.YELLOW + StatCollector.func_74838_a((String)"auction.claim.amount").replace("%s", EnumChatFormatting.GOLD + AuctionFormatUtil.formatCurrencyWithName(claim.currency)));
                } else if (claim.type.isCurrency()) {
                    tooltip.add(EnumChatFormatting.GOLD + String.format("%,d", claim.currency) + " " + AuctionClientConfig.getCurrencyName());
                }
                tooltip.add(EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"auction.claim.clickToClaim"));
            }
        }
        if (!tooltip.isEmpty()) {
            this.drawHoveringText(tooltip, guiMouseX, guiMouseY, this.field_146289_q);
        }
    }

    public ContainerManageAuction getManageContainer() {
        return this.manageContainer;
    }

    public Tab getActiveTab() {
        return this.activeTab;
    }

    @Override
    public void save() {
    }

    private static enum PendingAction {
        NONE,
        STOP,
        CANCEL,
        CLAIM_ITEM,
        CLAIM_CURRENCY;

    }

    public static enum Tab {
        LISTINGS,
        CREATE,
        CLAIMS;

    }
}

