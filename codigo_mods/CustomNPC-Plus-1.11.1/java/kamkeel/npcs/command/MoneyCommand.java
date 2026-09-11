/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 */
package kamkeel.npcs.command;

import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerTradeData;

public class MoneyCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "money";
    }

    @Override
    public String getDescription() {
        return "Currency operations";
    }

    @CommandKamkeelBase.SubCommand(desc="Show player's balance", usage="<player>")
    public void balance(ICommandSender sender, String[] args) throws CommandException {
        String playername = args[0];
        List<PlayerData> dataList = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (dataList.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        for (PlayerData playerdata : dataList) {
            PlayerTradeData currency = playerdata.tradeData;
            long balance = currency.getBalance();
            ColorUtil.sendResult(sender, String.format("Player \u00a7b%s\u00a77 has \u00a76%,d %s", playerdata.playername, balance, ConfigMarket.CurrencyName));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Give currency to player", usage="<player> <amount>")
    public void give(ICommandSender sender, String[] args) throws CommandException {
        long amount;
        String playername = args[0];
        try {
            amount = Long.parseLong(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Invalid amount: " + args[1]);
            return;
        }
        if (amount <= 0L) {
            ColorUtil.sendError(sender, "Amount must be positive");
            return;
        }
        List<PlayerData> dataList = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (dataList.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        for (PlayerData playerdata : dataList) {
            PlayerTradeData currency = playerdata.tradeData;
            if (currency.deposit(amount)) {
                playerdata.save();
                ColorUtil.sendResult(sender, String.format("Gave \u00a76%,d %s\u00a77 to player \u00a7b%s\u00a77. New balance: \u00a76%,d", amount, ConfigMarket.CurrencyName, playerdata.playername, currency.getBalance()));
                continue;
            }
            ColorUtil.sendError(sender, String.format("Failed to give currency to %s (would exceed max balance)", playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Withdraw currency from player", usage="<player> <amount>")
    public void withdraw(ICommandSender sender, String[] args) throws CommandException {
        long amount;
        String playername = args[0];
        try {
            amount = Long.parseLong(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Invalid amount: " + args[1]);
            return;
        }
        if (amount <= 0L) {
            ColorUtil.sendError(sender, "Amount must be positive");
            return;
        }
        List<PlayerData> dataList = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (dataList.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        for (PlayerData playerdata : dataList) {
            PlayerTradeData currency = playerdata.tradeData;
            if (currency.withdraw(amount)) {
                playerdata.save();
                ColorUtil.sendResult(sender, String.format("Withdrew \u00a76%,d %s\u00a77 from player \u00a7b%s\u00a77. New balance: \u00a76%,d", amount, ConfigMarket.CurrencyName, playerdata.playername, currency.getBalance()));
                continue;
            }
            ColorUtil.sendError(sender, String.format("Failed to withdraw from %s (insufficient funds)", playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Set player's balance", usage="<player> <amount>")
    public void set(ICommandSender sender, String[] args) throws CommandException {
        long amount;
        String playername = args[0];
        try {
            amount = Long.parseLong(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Invalid amount: " + args[1]);
            return;
        }
        if (amount < 0L) {
            ColorUtil.sendError(sender, "Amount cannot be negative");
            return;
        }
        List<PlayerData> dataList = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (dataList.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        for (PlayerData playerdata : dataList) {
            PlayerTradeData currency = playerdata.tradeData;
            currency.setBalance(amount);
            playerdata.save();
            ColorUtil.sendResult(sender, String.format("Set balance of player \u00a7b%s\u00a77 to \u00a76%,d %s", playerdata.playername, currency.getBalance(), ConfigMarket.CurrencyName));
        }
    }

    public List func_71516_a(ICommandSender par1, String[] args) {
        if (args.length == 1) {
            return MoneyCommand.func_71530_a((String[])args, (String[])new String[]{"balance", "give", "withdraw", "set"});
        }
        return null;
    }
}

