/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package kamkeel.npcs.command.auction;

import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import noppes.npcs.controllers.data.AuctionBlacklist;

public class BlacklistSubCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "blacklist";
    }

    @Override
    public String getDescription() {
        return "Manage auction item blacklist";
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        this.sendHelp(sender);
    }

    private void sendHelp(ICommandSender sender) {
        ColorUtil.sendMessage(sender, "\u00a76=== Auction Blacklist Commands ===");
        ColorUtil.sendMessage(sender, "\u00a7e/cnpc auction blacklist add <item|mod|nbt> <value>");
        ColorUtil.sendMessage(sender, "\u00a7e/cnpc auction blacklist remove <item|mod|nbt> <value>");
        ColorUtil.sendMessage(sender, "\u00a7e/cnpc auction blacklist list [item|mod|nbt]");
        ColorUtil.sendMessage(sender, "\u00a7e/cnpc auction blacklist reload");
        ColorUtil.sendMessage(sender, "\u00a7e/cnpc auction blacklist check \u00a77(checks held item)");
    }

    @CommandKamkeelBase.SubCommand(desc="Add item, mod, or NBT tag to blacklist", usage="<item|mod|nbt> <value>")
    public void add(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2) {
            ColorUtil.sendError(sender, "Usage: /cnpc auction blacklist add <item|mod|nbt> <value>");
            return;
        }
        String type = args[0].toLowerCase();
        String value = args[1];
        switch (type) {
            case "item": {
                if (AuctionBlacklist.addItem(value)) {
                    ColorUtil.sendResult(sender, "Added item to blacklist: " + value);
                    break;
                }
                ColorUtil.sendError(sender, "Failed to add item to blacklist");
                break;
            }
            case "mod": {
                if (AuctionBlacklist.addMod(value)) {
                    ColorUtil.sendResult(sender, "Added mod to blacklist: " + value);
                    break;
                }
                ColorUtil.sendError(sender, "Failed to add mod to blacklist");
                break;
            }
            case "nbt": {
                if (AuctionBlacklist.addNBTTag(value)) {
                    ColorUtil.sendResult(sender, "Added NBT tag to blacklist: " + value);
                    break;
                }
                ColorUtil.sendError(sender, "Failed to add NBT tag to blacklist");
                break;
            }
            default: {
                ColorUtil.sendError(sender, "Unknown type: " + type + ". Use: item, mod, or nbt");
            }
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Remove item, mod, or NBT tag from blacklist", usage="<item|mod|nbt> <value>")
    public void remove(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 2) {
            ColorUtil.sendError(sender, "Usage: /cnpc auction blacklist remove <item|mod|nbt> <value>");
            return;
        }
        String type = args[0].toLowerCase();
        String value = args[1];
        switch (type) {
            case "item": {
                if (AuctionBlacklist.removeItem(value)) {
                    ColorUtil.sendResult(sender, "Removed item from blacklist: " + value);
                    break;
                }
                ColorUtil.sendError(sender, "Item not found in blacklist: " + value);
                break;
            }
            case "mod": {
                if (AuctionBlacklist.removeMod(value)) {
                    ColorUtil.sendResult(sender, "Removed mod from blacklist: " + value);
                    break;
                }
                ColorUtil.sendError(sender, "Mod not found in blacklist: " + value);
                break;
            }
            case "nbt": {
                if (AuctionBlacklist.removeNBTTag(value)) {
                    ColorUtil.sendResult(sender, "Removed NBT tag from blacklist: " + value);
                    break;
                }
                ColorUtil.sendError(sender, "NBT tag not found in blacklist: " + value);
                break;
            }
            default: {
                ColorUtil.sendError(sender, "Unknown type: " + type + ". Use: item, mod, or nbt");
            }
        }
    }

    @CommandKamkeelBase.SubCommand(desc="List blacklisted items, mods, or NBT tags", usage="[item|mod|nbt]")
    public void list(ICommandSender sender, String[] args) throws CommandException {
        String filter;
        String string = filter = args.length > 0 ? args[0].toLowerCase() : "all";
        if (filter.equals("all") || filter.equals("item")) {
            List<String> items = AuctionBlacklist.getBlacklistedItems();
            ColorUtil.sendMessage(sender, "\u00a76=== Blacklisted Items (" + items.size() + ") ===");
            if (items.isEmpty()) {
                ColorUtil.sendMessage(sender, "\u00a77  (none)");
            } else {
                for (String item : items) {
                    ColorUtil.sendMessage(sender, "\u00a7e  " + item);
                }
            }
        }
        if (filter.equals("all") || filter.equals("mod")) {
            List<String> mods = AuctionBlacklist.getBlacklistedMods();
            ColorUtil.sendMessage(sender, "\u00a76=== Blacklisted Mods (" + mods.size() + ") ===");
            if (mods.isEmpty()) {
                ColorUtil.sendMessage(sender, "\u00a77  (none)");
            } else {
                for (String mod : mods) {
                    ColorUtil.sendMessage(sender, "\u00a7e  " + mod);
                }
            }
        }
        if (filter.equals("all") || filter.equals("nbt")) {
            List<String> tags = AuctionBlacklist.getBlacklistedNBTTags();
            ColorUtil.sendMessage(sender, "\u00a76=== Blacklisted NBT Tags (" + tags.size() + ") ===");
            if (tags.isEmpty()) {
                ColorUtil.sendMessage(sender, "\u00a77  (none)");
            } else {
                for (String tag : tags) {
                    ColorUtil.sendMessage(sender, "\u00a7e  " + tag);
                }
            }
        }
        ColorUtil.sendMessage(sender, "\u00a77Note: Soulbound and Profile-Slotbound items are always blocked.");
    }

    @CommandKamkeelBase.SubCommand(desc="Reload blacklist from config")
    public void reload(ICommandSender sender, String[] args) throws CommandException {
        AuctionBlacklist.reload();
        ColorUtil.sendResult(sender, "Blacklist reloaded from config");
    }

    @CommandKamkeelBase.SubCommand(desc="Check if held item is blacklisted", permission=0)
    public void check(ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayer)) {
            ColorUtil.sendError(sender, "This command can only be used by players");
            return;
        }
        EntityPlayer player = (EntityPlayer)sender;
        ItemStack heldItem = player.func_70694_bm();
        if (heldItem == null) {
            ColorUtil.sendError(sender, "You are not holding an item");
            return;
        }
        String registryName = AuctionBlacklist.getRegistryName(heldItem);
        ColorUtil.sendMessage(sender, "\u00a76=== Blacklist Check ===");
        ColorUtil.sendMessage(sender, "\u00a77Item: \u00a7e" + heldItem.func_82833_r());
        ColorUtil.sendMessage(sender, "\u00a77Registry: \u00a7e" + (registryName != null ? registryName : "unknown"));
        boolean isBlacklisted = AuctionBlacklist.isBlacklisted(heldItem);
        boolean canBypass = AuctionBlacklist.canBypass(player);
        if (isBlacklisted) {
            ColorUtil.sendMessage(sender, "\u00a7cStatus: BLACKLISTED");
            if (canBypass) {
                ColorUtil.sendMessage(sender, "\u00a7aYou have bypass permission");
            }
        } else {
            ColorUtil.sendMessage(sender, "\u00a7aStatus: ALLOWED");
        }
    }

    public List func_71516_a(ICommandSender sender, String[] args) {
        String subCmd;
        if (args.length == 1) {
            return BlacklistSubCommand.func_71530_a((String[])args, (String[])this.getAllSubCommandNames());
        }
        if (args.length == 2 && ((subCmd = args[0].toLowerCase()).equals("add") || subCmd.equals("remove") || subCmd.equals("list"))) {
            return BlacklistSubCommand.func_71530_a((String[])args, (String[])new String[]{"item", "mod", "nbt"});
        }
        return null;
    }
}

