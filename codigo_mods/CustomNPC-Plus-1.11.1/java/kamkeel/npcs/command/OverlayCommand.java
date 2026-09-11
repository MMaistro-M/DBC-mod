/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 */
package kamkeel.npcs.command;

import java.util.Iterator;
import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import noppes.npcs.api.ISkinOverlay;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.SkinOverlay;

public class OverlayCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "overlay";
    }

    @Override
    public String getDescription() {
        return "Overlay operations";
    }

    @CommandKamkeelBase.SubCommand(desc="set an overlay to a player", usage="<player> <num> <texture>")
    public void set(ICommandSender sender, String[] args) throws CommandException {
        int overlayID;
        String playername = args[0];
        try {
            overlayID = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Overlay num must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        Iterator<PlayerData> iterator = data.iterator();
        if (iterator.hasNext()) {
            PlayerData playerdata = iterator.next();
            SkinOverlay skinOverlay = new SkinOverlay(args[2]);
            playerdata.skinOverlays.add(overlayID, skinOverlay);
            playerdata.save();
            ColorUtil.sendResult(sender, String.format("Overlay added to Player '\u00a7b%s\u00a77' on ID \u00a7d%d", playerdata.playername, overlayID));
            return;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="remove an overlay from a player", usage="<player> <num>")
    public void remove(ICommandSender sender, String[] args) throws CommandException {
        int overlayID;
        String playername = args[0];
        try {
            overlayID = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Overlay num must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        Iterator<PlayerData> iterator = data.iterator();
        if (iterator.hasNext()) {
            PlayerData playerdata = iterator.next();
            if (playerdata.skinOverlays.has(overlayID)) {
                playerdata.skinOverlays.remove(overlayID);
                playerdata.save();
                ColorUtil.sendResult(sender, String.format("Overlay removed to Player '\u00a7b%s\u00a77' on ID \u00a7d%d", playerdata.playername, overlayID));
            } else {
                ColorUtil.sendError(sender, String.format("No overlay found for Player '\u00a7b%s\u00a74' on ID \u00a7d%d", playerdata.playername, overlayID));
            }
            return;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="modify an overlay for a player", usage="<player> <num> <blend/glow> <true/false>")
    public void modify(ICommandSender sender, String[] args) throws CommandException {
        int overlayID;
        String playername = args[0];
        try {
            overlayID = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Overlay num must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        if (!args[2].equalsIgnoreCase("blend") && !args[2].equalsIgnoreCase("glow")) {
            ColorUtil.sendError(sender, "Unknown Entry: " + args[2]);
            return;
        }
        boolean isBlend = args[2].equalsIgnoreCase("blend");
        if (!args[3].equalsIgnoreCase("true") && !args[3].equalsIgnoreCase("false")) {
            ColorUtil.sendError(sender, "Unknown Bool: " + args[3]);
            return;
        }
        boolean isTrue = args[3].equalsIgnoreCase("true");
        Iterator<PlayerData> iterator = data.iterator();
        if (iterator.hasNext()) {
            PlayerData playerdata = iterator.next();
            if (!playerdata.skinOverlays.has(overlayID)) {
                ColorUtil.sendError(sender, String.format("Player '\u00a7b%s\u00a7c' does not have Overlay ID \u00a7d%d", playerdata.playername, overlayID));
                return;
            }
            ISkinOverlay skinOverlay = playerdata.skinOverlays.get(overlayID);
            if (skinOverlay == null) {
                ColorUtil.sendError(sender, String.format("Player '\u00a7b%s\u00a7c' does not have Overlay ID \u00a7d%d", playerdata.playername, overlayID));
                return;
            }
            if (isBlend) {
                skinOverlay.setBlend(isTrue);
            } else {
                skinOverlay.setGlow(isTrue);
            }
            playerdata.skinOverlays.add(overlayID, skinOverlay);
            playerdata.save();
            ColorUtil.sendResult(sender, String.format("Overlay ID \u00a7d%d \u00a77Player '\u00a7b%s\u00a77' Updated ", overlayID, playerdata.playername));
            return;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="clears all overlays from a player", usage="<player>")
    public void clear(ICommandSender sender, String[] args) throws CommandException {
        String playername = args[0];
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        Iterator<PlayerData> iterator = data.iterator();
        if (iterator.hasNext()) {
            PlayerData playerdata = iterator.next();
            playerdata.skinOverlays.clear();
            playerdata.save();
            ColorUtil.sendResult(sender, String.format("Overlays cleared from Player '\u00a7b%s\u00a77'", playerdata.playername));
            return;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="List all overlays on a player", usage="<player>")
    public void info(ICommandSender sender, String[] args) throws CommandException {
        String playername = args[0];
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        Iterator<PlayerData> iterator = data.iterator();
        if (iterator.hasNext()) {
            PlayerData playerdata = iterator.next();
            ColorUtil.sendResult(sender, "--------------------");
            if (playerdata.skinOverlays.size() == 0) {
                ColorUtil.sendResult(sender, String.format("No Overlays found for Player '\u00a7b%s\u00a77'", playerdata.playername));
            } else {
                for (ISkinOverlay overlay : playerdata.skinOverlays.overlayList.values()) {
                    if (overlay == null) continue;
                    ColorUtil.sendResult(sender, String.format("%s", overlay.getTexture()));
                }
            }
            ColorUtil.sendResult(sender, "--------------------");
            return;
        }
    }
}

