/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 */
package kamkeel.npcs.command;

import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerFactionData;

public class FactionCommand
extends CommandKamkeelBase {
    public Faction selectedFaction;
    public List<PlayerData> data;

    public String func_71517_b() {
        return "faction";
    }

    @Override
    public String getDescription() {
        return "Faction operations";
    }

    @Override
    public String getUsage() {
        return "<player> <faction> <command>";
    }

    @Override
    public boolean runSubCommands() {
        return false;
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        String playername = args[0];
        String factionname = args[1];
        this.data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (this.data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        try {
            this.selectedFaction = FactionController.getInstance().getFaction(Integer.parseInt(factionname));
        }
        catch (NumberFormatException e) {
            this.selectedFaction = FactionController.getInstance().getFactionFromName(factionname);
        }
        if (this.selectedFaction == null) {
            ColorUtil.sendError(sender, "Unknown faction: " + factionname);
            return;
        }
        this.processSubCommand(sender, args[2], Arrays.copyOfRange(args, 3, args.length));
        for (PlayerData playerdata : this.data) {
            playerdata.save();
            playerdata.updateClient = true;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Add points", usage="<points>")
    public void add(ICommandSender sender, String[] args) throws CommandException {
        int points;
        try {
            points = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Must be an integer: " + args[0]);
            return;
        }
        int factionid = this.selectedFaction.id;
        for (PlayerData playerdata : this.data) {
            PlayerFactionData playerfactiondata = playerdata.factionData;
            playerfactiondata.increasePoints(factionid, points, playerdata.player);
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Added Points \u00a7a%d\u00a77, Faction \u00a7e%s (%d)\u00a77 for Player \u00a7b%s\u00a77", points, this.selectedFaction.getName(), this.selectedFaction.id, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Substract points", usage="<points>")
    public void subtract(ICommandSender sender, String[] args) throws CommandException {
        int points;
        try {
            points = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Must be an integer: " + args[0]);
            return;
        }
        int factionid = this.selectedFaction.id;
        for (PlayerData playerdata : this.data) {
            PlayerFactionData playerfactiondata = playerdata.factionData;
            playerfactiondata.increasePoints(factionid, -points, playerdata.player);
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Subtracted Points \u00a7a%d\u00a77, Faction \u00a7e%s (%d)\u00a77 for Player \u00a7b%s\u00a77", points, this.selectedFaction.getName(), this.selectedFaction.id, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Reset points to default")
    public void reset(ICommandSender sender, String[] args) {
        for (PlayerData playerdata : this.data) {
            playerdata.factionData.factionData.put(this.selectedFaction.id, this.selectedFaction.defaultPoints);
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Reset Faction \u00a7e%s (%d)\u00a77 for Player \u00a7b%s\u00a77", this.selectedFaction.getName(), this.selectedFaction.id, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Set points", usage="<points>")
    public void set(ICommandSender sender, String[] args) throws CommandException {
        int points;
        try {
            points = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Must be an integer: " + args[0]);
            return;
        }
        for (PlayerData playerdata : this.data) {
            PlayerFactionData playerfactiondata = playerdata.factionData;
            playerfactiondata.factionData.put(this.selectedFaction.id, points);
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Set Points \u00a7a%d\u00a77, Faction \u00a7e%s (%d)\u00a77 for Player \u00a7b%s\u00a77", points, this.selectedFaction.getName(), this.selectedFaction.id, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Drop relationship")
    public void drop(ICommandSender sender, String[] args) {
        for (PlayerData playerdata : this.data) {
            playerdata.factionData.factionData.remove(this.selectedFaction.id);
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Dropped Faction \u00a7e%s (%d)\u00a77 from Player \u00a7b%s\u00a77", this.selectedFaction.getName(), this.selectedFaction.id, playerdata.playername));
        }
    }

    public List func_71516_a(ICommandSender par1, String[] args) {
        if (args.length == 3) {
            return FactionCommand.func_71530_a((String[])args, (String[])new String[]{"add", "subtract", "set", "reset", "drop", "create"});
        }
        return null;
    }
}

