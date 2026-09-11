/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.command;

import java.util.Collection;
import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.client.EntityUtil;
import noppes.npcs.constants.EnumAvailabilityDialog;
import noppes.npcs.constants.EnumAvailabilityQuest;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.Availability;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityDialogNpc;

public class DialogCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "dialog";
    }

    @Override
    public String getDescription() {
        return "Dialog operations";
    }

    @CommandKamkeelBase.SubCommand(desc="force read", usage="<player> <dialog>")
    public void read(ICommandSender sender, String[] args) throws CommandException {
        int diagid;
        String playername = args[0];
        try {
            diagid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "DialogID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        for (PlayerData playerdata : data) {
            playerdata.dialogData.dialogsRead.add(diagid);
            playerdata.save();
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Forced Read for Dialog \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", diagid, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="force unread dialog", usage="<player> <dialog>")
    public void unread(ICommandSender sender, String[] args) throws CommandException {
        int diagid;
        String playername = args[0];
        try {
            diagid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "DialogID must be an integer: " + args[1]);
            return;
        }
        List<PlayerData> data = PlayerDataController.Instance.getPlayersData(sender, playername);
        if (data.isEmpty()) {
            ColorUtil.sendError(sender, "Unknown player: " + playername);
            return;
        }
        for (PlayerData playerdata : data) {
            playerdata.dialogData.dialogsRead.remove(diagid);
            playerdata.save();
            playerdata.updateClient = true;
            ColorUtil.sendResult(sender, String.format("Forced Unread for Dialog \u00a7e%d\u00a77 for Player '\u00a7b%s\u00a77'", diagid, playerdata.playername));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="reload dialogs from disk", permission=4)
    public void reload(ICommandSender sender, String[] args) {
        new DialogController().load();
        SyncController.syncAllDialogs();
        ColorUtil.sendResult(sender, "Dialogs Reloaded");
    }

    @CommandKamkeelBase.SubCommand(desc="show dialog", usage="<player> <dialog> <name>")
    public void show(ICommandSender sender, String[] args) throws CommandException {
        int diagid;
        EntityPlayerMP player = CommandBase.func_82359_c((ICommandSender)sender, (String)args[0]);
        if (player == null) {
            ColorUtil.sendError(sender, "Unknown player: " + args[0]);
            return;
        }
        try {
            diagid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "DialogID must be an integer: " + args[1]);
            return;
        }
        Dialog dialog = DialogController.Instance.dialogs.get(diagid);
        if (dialog == null) {
            ColorUtil.sendError(sender, "Unknown dialog id: " + args[1]);
            return;
        }
        EntityDialogNpc npc = new EntityDialogNpc(sender.func_130014_f_());
        npc.display.setName(args[2]);
        EntityUtil.Copy((EntityLivingBase)player, (EntityLivingBase)npc);
        DialogOption option = new DialogOption();
        option.dialogId = diagid;
        option.title = dialog.title;
        npc.dialogs.put(0, option);
        NoppesUtilServer.openDialog((EntityPlayer)player, npc, dialog, 0);
        ColorUtil.sendResult(sender, String.format("Displayed Dialog \u00a7e%d\u00a77 to Player '\u00a7b%s\u00a77'", diagid, player.func_70005_c_()));
    }

    @CommandKamkeelBase.SubCommand(desc="Find dialog id number by its name", usage="<dialogName>")
    public void id(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            ColorUtil.sendError(sender, "Please provide a name for the dialog");
            return;
        }
        String dialogName = String.join((CharSequence)" ", args).toLowerCase();
        Collection<Dialog> quests = DialogController.Instance.dialogs.values();
        int count = 0;
        for (Dialog dialog : quests) {
            if (!dialog.getName().toLowerCase().contains(dialogName)) continue;
            ColorUtil.sendResult(sender, String.format("Dialog \u00a7e%d\u00a77 - \u00a7c'%s'", dialog.id, dialog.getName()));
            ++count;
        }
        if (count == 0) {
            ColorUtil.sendResult(sender, String.format("No Dialog found with name: \u00a7c'%s'", dialogName));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="List a dialogs availability options", usage="<dialogId>")
    public void availability(ICommandSender sender, String[] args) throws CommandException {
        int diagid;
        if (args.length == 0) {
            ColorUtil.sendError(sender, "Please provide an id for the dialog");
            return;
        }
        try {
            diagid = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "DialogID must be an integer: " + args[0]);
            return;
        }
        Dialog dialog = DialogController.Instance.dialogs.get(diagid);
        if (dialog == null) {
            ColorUtil.sendError(sender, "Unknown dialog id: " + args[0]);
            return;
        }
        Availability avail = dialog.availability;
        if (avail != null) {
            ColorUtil.sendResult(sender, String.format("Availability Options for Dialog: \u00a7c%d", dialog.id));
            ColorUtil.sendResult(sender, "--------------------");
            boolean diagFound = false;
            if (avail.dialogId != -1 && avail.dialogAvailable != EnumAvailabilityDialog.Always) {
                ColorUtil.sendResult(sender, String.format("%s Dialog: \u00a7c%d", avail.dialogAvailable.toString(), avail.dialogId));
                diagFound = true;
            }
            if (avail.dialog2Id != -1 && avail.dialog2Available != EnumAvailabilityDialog.Always) {
                ColorUtil.sendResult(sender, String.format("%s Dialog: \u00a7c%d", avail.dialog2Available.toString(), avail.dialog2Id));
                diagFound = true;
            }
            if (avail.dialog3Id != -1 && avail.dialog3Available != EnumAvailabilityDialog.Always) {
                ColorUtil.sendResult(sender, String.format("%s Dialog: \u00a7c%d", avail.dialog3Available.toString(), avail.dialog3Id));
                diagFound = true;
            }
            if (avail.dialog4Id != -1 && avail.dialog4Available != EnumAvailabilityDialog.Always) {
                ColorUtil.sendResult(sender, String.format("%s Dialog: \u00a7c%d", avail.dialog4Available.toString(), avail.dialog4Id));
                diagFound = true;
            }
            if (!diagFound) {
                ColorUtil.sendResult(sender, "No Dialog Availability Options");
            }
            ColorUtil.sendResult(sender, "--------------------");
            boolean questFound = false;
            if (avail.questId != -1 && avail.questAvailable != EnumAvailabilityQuest.Always) {
                ColorUtil.sendResult(sender, String.format("%s Quest: \u00a7c%d", avail.questAvailable.toString(), avail.questId));
                questFound = true;
            }
            if (avail.quest2Id != -1 && avail.quest2Available != EnumAvailabilityQuest.Always) {
                ColorUtil.sendResult(sender, String.format("%s Quest: \u00a7c%d", avail.quest2Available.toString(), avail.quest2Id));
                questFound = true;
            }
            if (avail.quest3Id != -1 && avail.quest3Available != EnumAvailabilityQuest.Always) {
                ColorUtil.sendResult(sender, String.format("%s Quest: \u00a7c%d", avail.quest3Available.toString(), avail.quest3Id));
                questFound = true;
            }
            if (avail.quest4Id != -1 && avail.quest4Available != EnumAvailabilityQuest.Always) {
                ColorUtil.sendResult(sender, String.format("%s Quest: \u00a7c%d", avail.quest4Available.toString(), avail.quest4Id));
                questFound = true;
            }
            if (!questFound) {
                ColorUtil.sendResult(sender, "No Quest Availability Options");
            }
            ColorUtil.sendResult(sender, "--------------------");
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Quick info on a dialog", usage="<dialogId>")
    public void info(ICommandSender sender, String[] args) throws CommandException {
        int diagid;
        if (args.length == 0) {
            ColorUtil.sendError(sender, "Please provide an id for the quest");
            return;
        }
        try {
            diagid = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "DialogID must be an integer: " + args[0]);
            return;
        }
        Dialog dialog = DialogController.Instance.dialogs.get(diagid);
        if (dialog == null) {
            ColorUtil.sendError(sender, "Unknown dialog id: " + args[0]);
            return;
        }
        ColorUtil.sendResult(sender, "--------------------");
        ColorUtil.sendResult(sender, String.format("\u00a7e%d\u00a77: \u00a7a%s", dialog.id, dialog.title));
        ColorUtil.sendResult(sender, String.format("Category: \u00a7b%s", dialog.category.getName()));
        ColorUtil.sendResult(sender, "--------------------");
    }
}

