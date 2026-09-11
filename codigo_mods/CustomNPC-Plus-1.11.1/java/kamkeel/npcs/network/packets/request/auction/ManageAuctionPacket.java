/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package kamkeel.npcs.network.packets.request.auction;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.constants.EnumClaimType;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.controllers.AuctionController;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.AuctionFilter;
import noppes.npcs.controllers.data.AuctionListing;

public class ManageAuctionPacket
extends AbstractPacket {
    private static final int PAGE_SIZE = 45;
    private Action action;
    private int page;
    private boolean flag;
    private String text;
    private long amount;
    private long amount2;
    private int amountInt;
    private NBTTagCompound data;

    public ManageAuctionPacket() {
    }

    private ManageAuctionPacket(Action action) {
        this.action = action;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.ManageAuctionAction;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.GLOBAL_AUCTION;
    }

    @Override
    public boolean needsNPC() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public static void requestListings(AuctionFilter filter, int page) {
        ManageAuctionPacket packet = new ManageAuctionPacket(Action.REQUEST_LISTINGS);
        packet.page = page;
        packet.data = filter != null ? filter.writeToNBT(new NBTTagCompound()) : new NBTTagCompound();
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void requestGlobalClaims(int page) {
        ManageAuctionPacket packet = new ManageAuctionPacket(Action.REQUEST_CLAIMS);
        packet.page = page;
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void createFakeListing(ItemStack item, String sellerName, long startingPrice, long buyoutPrice, int durationHours) {
        if (item == null) {
            return;
        }
        ManageAuctionPacket packet = new ManageAuctionPacket(Action.CREATE_FAKE);
        packet.text = sellerName != null ? sellerName : "";
        packet.amount = startingPrice;
        packet.amount2 = buyoutPrice;
        packet.amountInt = durationHours;
        packet.data = new NBTTagCompound();
        item.func_77955_b(packet.data);
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void manageListing(String listingId, boolean cancelCompletely) {
        ManageAuctionPacket packet = new ManageAuctionPacket(Action.MANAGE_LISTING);
        packet.text = listingId;
        packet.flag = cancelCompletely;
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void claimGlobalItem(String claimId) {
        ManageAuctionPacket packet = new ManageAuctionPacket(Action.CLAIM_ITEM);
        packet.text = claimId;
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void claimGlobalCurrency(String claimId) {
        ManageAuctionPacket packet = new ManageAuctionPacket(Action.CLAIM_CURRENCY);
        packet.text = claimId;
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void openBidding(String listingId) {
        ManageAuctionPacket packet = new ManageAuctionPacket(Action.OPEN_BIDDING);
        packet.text = listingId;
        PacketClient.sendClient(packet);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.action.ordinal());
        switch (this.action) {
            case REQUEST_LISTINGS: {
                out.writeInt(this.page);
                ByteBufUtils.writeNBT(out, this.data);
                break;
            }
            case REQUEST_CLAIMS: {
                out.writeInt(this.page);
                break;
            }
            case CREATE_FAKE: {
                ByteBufUtils.writeString(out, this.text);
                out.writeLong(this.amount);
                out.writeLong(this.amount2);
                out.writeInt(this.amountInt);
                ByteBufUtils.writeNBT(out, this.data);
                break;
            }
            case MANAGE_LISTING: {
                ByteBufUtils.writeString(out, this.text);
                out.writeBoolean(this.flag);
                break;
            }
            case CLAIM_ITEM: 
            case CLAIM_CURRENCY: 
            case OPEN_BIDDING: {
                ByteBufUtils.writeString(out, this.text);
            }
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        if (!CustomNpcsPermissions.hasCustomPermission((EntityPlayer)playerMP, "customnpcs.global.auction")) {
            this.sendError(playerMP, "Missing permission: customnpcs.global.auction");
            return;
        }
        int actionOrdinal = in.readInt();
        if (actionOrdinal < 0 || actionOrdinal >= Action.values().length) {
            return;
        }
        Action requestedAction = Action.values()[actionOrdinal];
        AuctionController controller = AuctionController.getInstance();
        if (controller == null) {
            this.sendError(playerMP, "Auction system is not available.");
            return;
        }
        String result = null;
        switch (requestedAction) {
            case REQUEST_LISTINGS: {
                this.handleRequestListings(in, playerMP, controller);
                break;
            }
            case REQUEST_CLAIMS: {
                this.handleRequestClaims(in, playerMP, controller);
                break;
            }
            case CREATE_FAKE: {
                result = this.handleCreateFake(in, playerMP, controller);
                break;
            }
            case MANAGE_LISTING: {
                result = this.handleManageListing(in, playerMP, controller);
                break;
            }
            case CLAIM_ITEM: {
                result = this.handleClaimItem(in, playerMP, controller);
                break;
            }
            case CLAIM_CURRENCY: {
                result = this.handleClaimCurrency(in, playerMP, controller);
                break;
            }
            case OPEN_BIDDING: {
                this.handleOpenBidding(in, playerMP, controller);
            }
        }
        if (result != null) {
            this.sendError(playerMP, result);
        }
    }

    private void handleRequestListings(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        int requestPage = Math.max(0, in.readInt());
        NBTTagCompound filterNbt = ByteBufUtils.readNBT(in);
        AuctionFilter filter = new AuctionFilter();
        if (filterNbt != null) {
            filter.readFromNBT(filterNbt);
        }
        int totalListings = controller.getTotalActiveListings(filter);
        int totalPages = Math.max(1, (int)Math.ceil((double)totalListings / 45.0));
        int safePage = Math.min(requestPage, totalPages - 1);
        List<AuctionListing> listings = controller.getActiveListings(filter, safePage, 45);
        NBTTagCompound response = new NBTTagCompound();
        response.func_74757_a("ManageAuctionListingsData", true);
        response.func_74768_a("Page", safePage);
        response.func_74768_a("TotalPages", totalPages);
        response.func_74768_a("TotalListings", totalListings);
        NBTTagList list = new NBTTagList();
        for (AuctionListing listing : listings) {
            list.func_74742_a((NBTBase)listing.writeToNBT(new NBTTagCompound()));
        }
        response.func_74782_a("Listings", (NBTBase)list);
        GuiDataPacket.sendGuiData(player, response);
    }

    private void handleRequestClaims(ByteBuf in, EntityPlayerMP player, AuctionController controller) {
        int requestPage = Math.max(0, in.readInt());
        List<AuctionClaim> allClaims = controller.getAllGlobalClaims();
        List<AuctionListing> allListings = controller.getActiveGlobalListings();
        int totalClaims = allClaims.size();
        int totalListings = allListings.size();
        ArrayList<AuctionClaim> soldClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionClaim> outbidClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionClaim> wonClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionClaim> expiredClaims = new ArrayList<AuctionClaim>();
        for (AuctionClaim auctionClaim : allClaims) {
            if (auctionClaim == null) continue;
            if (auctionClaim.type == EnumClaimType.CURRENCY) {
                soldClaims.add(auctionClaim);
                continue;
            }
            if (auctionClaim.type == EnumClaimType.REFUND) {
                outbidClaims.add(auctionClaim);
                continue;
            }
            if (auctionClaim.type == EnumClaimType.ITEM) {
                if (auctionClaim.isReturned) {
                    expiredClaims.add(auctionClaim);
                    continue;
                }
                wonClaims.add(auctionClaim);
                continue;
            }
            wonClaims.add(auctionClaim);
        }
        ArrayList<Entry> ordered = new ArrayList<Entry>();
        ManageAuctionPacket.appendClaims(ordered, soldClaims);
        ManageAuctionPacket.appendClaims(ordered, outbidClaims);
        ManageAuctionPacket.appendClaims(ordered, wonClaims);
        for (AuctionListing listing : allListings) {
            ordered.add(Entry.forListing(listing));
        }
        ManageAuctionPacket.appendClaims(ordered, expiredClaims);
        int n = ordered.size();
        int totalPages = Math.max(1, (int)Math.ceil((double)n / 45.0));
        int safePage = Math.min(requestPage, totalPages - 1);
        int start = safePage * 45;
        int end = Math.min(n, start + 45);
        ArrayList<AuctionClaim> pageClaims = new ArrayList<AuctionClaim>();
        ArrayList<AuctionListing> pageListings = new ArrayList<AuctionListing>();
        for (int i = start; i < end; ++i) {
            Entry entry = (Entry)ordered.get(i);
            if (entry.claim != null) {
                pageClaims.add(entry.claim);
                continue;
            }
            if (entry.listing == null) continue;
            pageListings.add(entry.listing);
        }
        NBTTagCompound response = new NBTTagCompound();
        response.func_74757_a("ManageAuctionClaimsData", true);
        response.func_74768_a("Page", safePage);
        response.func_74768_a("TotalPages", totalPages);
        response.func_74768_a("TotalClaims", totalClaims);
        response.func_74768_a("TotalListings", totalListings);
        response.func_74768_a("TotalEntries", n);
        NBTTagList claimList = new NBTTagList();
        for (AuctionClaim claim : pageClaims) {
            claimList.func_74742_a((NBTBase)claim.writeToNBT(new NBTTagCompound()));
        }
        response.func_74782_a("Claims", (NBTBase)claimList);
        NBTTagList listingList = new NBTTagList();
        for (AuctionListing listing : pageListings) {
            listingList.func_74742_a((NBTBase)listing.writeToNBT(new NBTTagCompound()));
        }
        response.func_74782_a("Listings", (NBTBase)listingList);
        GuiDataPacket.sendGuiData(player, response);
    }

    private static void appendClaims(List<Entry> out, List<AuctionClaim> claims) {
        if (claims == null || claims.isEmpty()) {
            return;
        }
        for (AuctionClaim claim : claims) {
            out.add(Entry.forClaim(claim));
        }
    }

    private String handleCreateFake(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        if (!ConfigMarket.AuctionEnabled) {
            return "Auction is disabled.";
        }
        String sellerName = ByteBufUtils.readString(in);
        long startingPrice = in.readLong();
        long buyoutPrice = in.readLong();
        int durationHours = in.readInt();
        NBTTagCompound itemNbt = ByteBufUtils.readNBT(in);
        if (itemNbt == null) {
            return "No item data received.";
        }
        ItemStack item = ItemStack.func_77949_a((NBTTagCompound)itemNbt);
        if (item == null) {
            return "Invalid item data.";
        }
        if (!this.removeItemFromInventory(player, item)) {
            return "Item not found in inventory.";
        }
        String result = controller.createGlobalListing((EntityPlayer)player, sellerName, item, startingPrice, buyoutPrice, durationHours);
        if (result != null && !player.field_71071_by.func_70441_a(item)) {
            player.func_70099_a(item, 0.5f);
        }
        if (result == null) {
            this.sendRefresh(player, true);
        }
        return result;
    }

    private String handleManageListing(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        boolean cancelCompletely;
        String listingId = ByteBufUtils.readString(in);
        String result = controller.adminStopListingToGlobal(listingId, (EntityPlayer)player, cancelCompletely = in.readBoolean());
        if (result == null) {
            this.sendRefresh(player, false);
        }
        return result;
    }

    private String handleClaimItem(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        String claimId = ByteBufUtils.readString(in);
        String result = controller.claimGlobalItem(claimId, (EntityPlayer)player);
        if (result == null) {
            this.sendRefresh(player, false);
        }
        return result;
    }

    private String handleClaimCurrency(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        String claimId = ByteBufUtils.readString(in);
        String result = controller.claimGlobalCurrency(claimId, (EntityPlayer)player);
        if (result == null) {
            this.sendRefresh(player, false);
        }
        return result;
    }

    private void handleOpenBidding(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        String listingId = ByteBufUtils.readString(in);
        AuctionListing listing = controller.getListing(listingId);
        if (listing == null || !listing.isActive()) {
            this.sendError(player, "Listing not found or has ended.");
            return;
        }
        NoppesUtilServer.sendOpenGui((EntityPlayer)player, EnumGuiType.PlayerAuctionBidding, this.npc);
        NBTTagCompound response = new NBTTagCompound();
        response.func_74757_a("BiddingData", true);
        response.func_74782_a("Listing", (NBTBase)listing.writeToNBT(new NBTTagCompound()));
        response.func_74772_a("Balance", controller.getPlayerBalance((EntityPlayer)player));
        response.func_74757_a("IsOwnListing", listing.isSeller(player.func_110124_au()));
        GuiDataPacket.sendGuiData(player, response);
    }

    private void sendError(EntityPlayerMP player, String message) {
        player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "[Global Auction] " + message));
    }

    private void sendRefresh(EntityPlayerMP player, boolean created) {
        NBTTagCompound response = new NBTTagCompound();
        response.func_74757_a("ManageAuctionRefresh", true);
        response.func_74757_a("ManageAuctionCreateSuccess", created);
        GuiDataPacket.sendGuiData(player, response);
    }

    private boolean areItemsSameType(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null) {
            return item1 == item2;
        }
        if (item1.func_77973_b() != item2.func_77973_b()) {
            return false;
        }
        if (item1.func_77960_j() != item2.func_77960_j()) {
            return false;
        }
        return ItemStack.func_77970_a((ItemStack)item1, (ItemStack)item2);
    }

    private int countItemsInInventory(EntityPlayerMP player, ItemStack target) {
        int count = 0;
        for (int i = 0; i < player.field_71071_by.field_70462_a.length; ++i) {
            ItemStack stack = player.field_71071_by.field_70462_a[i];
            if (stack == null || !this.areItemsSameType(stack, target)) continue;
            count += stack.field_77994_a;
        }
        return count;
    }

    private boolean removeItemFromInventory(EntityPlayerMP player, ItemStack target) {
        if (target == null) {
            return false;
        }
        int needed = target.field_77994_a;
        int available = this.countItemsInInventory(player, target);
        if (available < needed) {
            return false;
        }
        for (int i = 0; i < player.field_71071_by.field_70462_a.length && needed > 0; ++i) {
            ItemStack stack = player.field_71071_by.field_70462_a[i];
            if (stack == null || !this.areItemsSameType(stack, target)) continue;
            if (needed >= stack.field_77994_a) {
                needed -= stack.field_77994_a;
                player.field_71071_by.field_70462_a[i] = null;
                continue;
            }
            stack.func_77979_a(needed);
            if (stack.field_77994_a <= 0) {
                player.field_71071_by.func_70299_a(i, null);
            }
            needed = 0;
        }
        player.field_71071_by.func_70296_d();
        return true;
    }

    private static enum Action {
        REQUEST_LISTINGS,
        REQUEST_CLAIMS,
        CREATE_FAKE,
        MANAGE_LISTING,
        CLAIM_ITEM,
        CLAIM_CURRENCY,
        OPEN_BIDDING;

    }

    private static class Entry {
        final AuctionClaim claim;
        final AuctionListing listing;

        private Entry(AuctionClaim claim, AuctionListing listing) {
            this.claim = claim;
            this.listing = listing;
        }

        static Entry forClaim(AuctionClaim claim) {
            return new Entry(claim, null);
        }

        static Entry forListing(AuctionListing listing) {
            return new Entry(null, listing);
        }
    }
}

