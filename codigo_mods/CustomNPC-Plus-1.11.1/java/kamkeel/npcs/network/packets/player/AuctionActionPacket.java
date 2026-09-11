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
package kamkeel.npcs.network.packets.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.List;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
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
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.constants.EnumAuctionPage;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.controllers.AuctionController;
import noppes.npcs.controllers.data.AuctionClaim;
import noppes.npcs.controllers.data.AuctionFilter;
import noppes.npcs.controllers.data.AuctionListing;

public class AuctionActionPacket
extends AbstractPacket {
    public static final String packetName = "Player|Auction";
    private static final int PAGE_SIZE = 45;
    private Action action;
    private String listingId;
    private long amount;
    private NBTTagCompound data;

    public AuctionActionPacket() {
    }

    private AuctionActionPacket(Action action) {
        this.action = action;
    }

    private AuctionActionPacket(Action action, String listingId) {
        this.action = action;
        this.listingId = listingId;
    }

    private AuctionActionPacket(Action action, String listingId, long amount) {
        this.action = action;
        this.listingId = listingId;
        this.amount = amount;
    }

    @Override
    public boolean needsNPC() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public static void placeBid(String listingId, long bidAmount) {
        AuctionActionPacket packet = new AuctionActionPacket(Action.Bid, listingId, bidAmount);
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void buyout(String listingId) {
        AuctionActionPacket packet = new AuctionActionPacket(Action.Buyout, listingId);
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void cancelListing(String listingId) {
        AuctionActionPacket packet = new AuctionActionPacket(Action.Cancel, listingId);
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void claimItem(String claimId) {
        AuctionActionPacket packet = new AuctionActionPacket(Action.ClaimItem, claimId);
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void claimCurrency(String claimId) {
        AuctionActionPacket packet = new AuctionActionPacket(Action.ClaimCurrency, claimId);
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void createListing(ItemStack item, long startingPrice, long buyoutPrice) {
        AuctionActionPacket packet = new AuctionActionPacket(Action.CreateListing);
        packet.amount = startingPrice;
        packet.listingId = String.valueOf(buyoutPrice);
        packet.data = new NBTTagCompound();
        item.func_77955_b(packet.data);
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void openPage(int page) {
        AuctionActionPacket packet = new AuctionActionPacket(Action.OpenPage);
        packet.amount = page;
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void openBidding(String listingId) {
        AuctionActionPacket packet = new AuctionActionPacket(Action.OpenBidding, listingId);
        PacketClient.sendClient(packet);
    }

    @SideOnly(value=Side.CLIENT)
    public static void requestListings(AuctionFilter filter, int page) {
        AuctionActionPacket packet = new AuctionActionPacket(Action.RequestListings);
        packet.amount = page;
        packet.data = filter.writeToNBT(new NBTTagCompound());
        PacketClient.sendClient(packet);
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.AuctionAction;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.action.ordinal());
        switch (this.action) {
            case Bid: {
                ByteBufUtils.writeString(out, this.listingId);
                out.writeLong(this.amount);
                break;
            }
            case Buyout: 
            case Cancel: 
            case ClaimItem: 
            case ClaimCurrency: {
                ByteBufUtils.writeString(out, this.listingId);
                break;
            }
            case CreateListing: {
                out.writeLong(this.amount);
                ByteBufUtils.writeString(out, this.listingId);
                ByteBufUtils.writeNBT(out, this.data);
                break;
            }
            case OpenPage: {
                out.writeInt((int)this.amount);
                break;
            }
            case OpenBidding: {
                ByteBufUtils.writeString(out, this.listingId);
                break;
            }
            case RequestListings: {
                out.writeInt((int)this.amount);
                ByteBufUtils.writeNBT(out, this.data);
            }
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        if (this.npc != null && this.npc.advanced.role != EnumRoleType.Auctioneer) {
            return;
        }
        if (!ConfigMarket.AuctionEnabled) {
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
            case Bid: {
                result = this.handleBid(in, playerMP, controller);
                break;
            }
            case Buyout: {
                result = this.handleBuyout(in, playerMP, controller);
                break;
            }
            case Cancel: {
                result = this.handleCancel(in, playerMP, controller);
                break;
            }
            case ClaimItem: {
                result = this.handleClaimItem(in, playerMP, controller);
                break;
            }
            case ClaimCurrency: {
                result = this.handleClaimCurrency(in, playerMP, controller);
                break;
            }
            case CreateListing: {
                result = this.handleCreateListing(in, playerMP, controller);
                break;
            }
            case OpenPage: {
                this.handleOpenPage(in, playerMP);
                break;
            }
            case OpenBidding: {
                this.handleOpenBidding(in, playerMP, controller);
                break;
            }
            case RequestListings: {
                this.handleRequestListings(in, playerMP, controller);
            }
        }
        if (result != null) {
            this.sendError(playerMP, result);
        }
    }

    private String handleBid(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        long amount;
        String id = ByteBufUtils.readString(in);
        String result = controller.placeBid(id, (EntityPlayer)player, amount = in.readLong());
        if (result == null) {
            this.sendListingsUpdate(player);
        }
        return result;
    }

    private String handleBuyout(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        String id = ByteBufUtils.readString(in);
        String result = controller.buyout(id, (EntityPlayer)player);
        if (result == null) {
            this.sendListingsUpdate(player);
        }
        return result;
    }

    private String handleCancel(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        String id = ByteBufUtils.readString(in);
        String result = controller.cancelListing(id, (EntityPlayer)player, false);
        if (result == null) {
            this.sendTradesUpdate(player);
        }
        return result;
    }

    private String handleClaimItem(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        String id = ByteBufUtils.readString(in);
        String result = controller.claimItem(id, (EntityPlayer)player);
        if (result == null) {
            this.sendTradesUpdate(player);
        }
        return result;
    }

    private String handleClaimCurrency(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        String id = ByteBufUtils.readString(in);
        String result = controller.claimCurrency(id, (EntityPlayer)player);
        if (result == null) {
            this.sendTradesUpdate(player);
        }
        return result;
    }

    private String handleCreateListing(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        long startingPrice = in.readLong();
        String buyoutStr = ByteBufUtils.readString(in);
        long buyoutPrice = 0L;
        try {
            buyoutPrice = Long.parseLong(buyoutStr);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        NBTTagCompound itemNBT = ByteBufUtils.readNBT(in);
        if (itemNBT == null) {
            return "No item data received.";
        }
        ItemStack item = ItemStack.func_77949_a((NBTTagCompound)itemNBT);
        if (item == null) {
            return "Invalid item data.";
        }
        if (item.field_77994_a <= 0) {
            return "Invalid item amount.";
        }
        if (item.field_77994_a > item.func_77976_d()) {
            return "Stack size exceeds item maximum.";
        }
        if (!this.removeItemFromInventory(player, item)) {
            return "Item not found in inventory.";
        }
        String result = controller.createListing((EntityPlayer)player, item, startingPrice, buyoutPrice);
        if (result != null && !player.field_71071_by.func_70441_a(item)) {
            player.func_70099_a(item, 0.5f);
        }
        return result;
    }

    private void handleOpenPage(ByteBuf in, EntityPlayerMP player) {
        AuctionController controller;
        int page = in.readInt();
        EnumAuctionPage auctionPage = EnumAuctionPage.fromOrdinal(page);
        EnumGuiType guiType = auctionPage.getGuiType();
        NoppesUtilServer.sendOpenGui((EntityPlayer)player, guiType, this.npc);
        if (page == 2 && (controller = AuctionController.getInstance()) != null) {
            this.sendTradesData(player, controller);
        }
    }

    private void sendTradesData(EntityPlayerMP player, AuctionController controller) {
        NBTTagCompound response = new NBTTagCompound();
        response.func_74757_a("TradesData", true);
        response.func_74768_a("MaxTradeSlots", controller.getMaxTradesForPlayer((EntityPlayer)player));
        List<AuctionListing> listings = controller.getPlayerActiveListings(player.func_110124_au());
        NBTTagList listingsNBT = new NBTTagList();
        for (AuctionListing listing : listings) {
            listingsNBT.func_74742_a((NBTBase)listing.writeToNBT(new NBTTagCompound()));
        }
        response.func_74782_a("ActiveListings", (NBTBase)listingsNBT);
        List<AuctionListing> bids = controller.getPlayerActiveBids(player.func_110124_au());
        NBTTagList bidsNBT = new NBTTagList();
        for (AuctionListing bid : bids) {
            bidsNBT.func_74742_a((NBTBase)bid.writeToNBT(new NBTTagCompound()));
        }
        response.func_74782_a("ActiveBids", (NBTBase)bidsNBT);
        List<AuctionClaim> claims = controller.getPlayerClaims((EntityPlayer)player);
        NBTTagList claimsNBT = new NBTTagList();
        for (AuctionClaim claim : claims) {
            claimsNBT.func_74742_a((NBTBase)claim.writeToNBT(new NBTTagCompound()));
        }
        response.func_74782_a("Claims", (NBTBase)claimsNBT);
        GuiDataPacket.sendGuiData(player, response);
    }

    private void handleOpenBidding(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        String listingId = ByteBufUtils.readString(in);
        AuctionListing listing = controller.getListing(listingId);
        if (listing == null || !listing.isActive()) {
            this.sendError(player, "Listing not found or has ended.");
            return;
        }
        NoppesUtilServer.sendOpenGui((EntityPlayer)player, EnumGuiType.PlayerAuctionBidding, this.npc);
        boolean isOwnListing = listing.isSeller(player.func_110124_au());
        NBTTagCompound response = new NBTTagCompound();
        response.func_74757_a("BiddingData", true);
        response.func_74782_a("Listing", (NBTBase)listing.writeToNBT(new NBTTagCompound()));
        response.func_74772_a("Balance", controller.getPlayerBalance((EntityPlayer)player));
        response.func_74757_a("IsOwnListing", isOwnListing);
        GuiDataPacket.sendGuiData(player, response);
    }

    private void handleRequestListings(ByteBuf in, EntityPlayerMP player, AuctionController controller) throws IOException {
        int page = in.readInt();
        NBTTagCompound filterNBT = ByteBufUtils.readNBT(in);
        AuctionFilter filter = new AuctionFilter();
        if (filterNBT != null) {
            filter.readFromNBT(filterNBT);
        }
        List<AuctionListing> listings = controller.getActiveListings(filter, page, 45);
        int totalListings = controller.getTotalActiveListings(filter);
        int totalPages = Math.max(1, (int)Math.ceil((double)totalListings / 45.0));
        NBTTagCompound response = new NBTTagCompound();
        response.func_74757_a("ListingsData", true);
        response.func_74768_a("Page", page);
        response.func_74768_a("TotalPages", totalPages);
        response.func_74768_a("TotalListings", totalListings);
        NBTTagList listingsList = new NBTTagList();
        for (AuctionListing listing : listings) {
            listingsList.func_74742_a((NBTBase)listing.writeToNBT(new NBTTagCompound()));
        }
        response.func_74782_a("Listings", (NBTBase)listingsList);
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

    private void sendError(EntityPlayerMP player, String message) {
        player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "[Auction] " + message));
    }

    private void sendTradesUpdate(EntityPlayerMP player) {
        AuctionController controller = AuctionController.getInstance();
        if (controller != null) {
            this.sendTradesData(player, controller);
        }
    }

    private void sendListingsUpdate(EntityPlayerMP player) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("ListingsRefresh", true);
        GuiDataPacket.sendGuiData(player, compound);
    }

    private static enum Action {
        Bid,
        Buyout,
        Cancel,
        ClaimItem,
        ClaimCurrency,
        CreateListing,
        OpenPage,
        OpenBidding,
        RequestListings;

    }
}

