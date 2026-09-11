/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package foxz.command;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.ParamCheck;
import java.util.List;
import kamkeel.npcs.controllers.SyncController;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.client.EntityUtil;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityDialogNpc;

@Command(name="dialog", desc="dialog operations", usage="help")
public class CmdDialog
extends ChMcLogger {
    public CmdDialog(Object sender) {
        super(sender);
    }

    @SubCommand(desc="force read", usage="<player> <dialog>", permissions={OpOnly.class, ParamCheck.class})
    public boolean read(String[] args) {
        int diagid;
        String playername = args[0];
        try {
            diagid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            this.sendmessage("DialogID must be an integer");
            return false;
        }
        List<PlayerData> data = this.getPlayersData(playername);
        if (data.isEmpty()) {
            this.sendmessage(String.format("Unknow player '%s'", playername));
            return false;
        }
        for (PlayerData playerdata : data) {
            playerdata.dialogData.dialogsRead.add(diagid);
            playerdata.save();
            playerdata.updateClient = true;
        }
        return true;
    }

    @SubCommand(desc="force unread dialog", usage="<player> <dialog>", permissions={OpOnly.class, ParamCheck.class})
    public boolean unread(String[] args) {
        int diagid;
        String playername = args[0];
        try {
            diagid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            this.sendmessage("DialogID must be an integer");
            return false;
        }
        List<PlayerData> data = this.getPlayersData(playername);
        if (data.isEmpty()) {
            this.sendmessage(String.format("Unknow player '%s'", playername));
            return false;
        }
        for (PlayerData playerdata : data) {
            playerdata.dialogData.dialogsRead.remove(diagid);
            playerdata.save();
            playerdata.updateClient = true;
        }
        return true;
    }

    @SubCommand(desc="reload dialogs from disk", permissions={OpOnly.class})
    public boolean reload(String[] args) {
        new DialogController().load();
        SyncController.syncAllDialogs();
        return true;
    }

    @SubCommand(desc="show dialog", usage="<player> <dialog> <name>", permissions={OpOnly.class})
    public void show(String[] args) {
        int diagid;
        EntityPlayerMP player = CommandBase.func_82359_c((ICommandSender)this.pcParam, (String)args[0]);
        if (player == null) {
            this.sendmessage(String.format("Unknow player '%s'", args[0]));
            return;
        }
        try {
            diagid = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException ex) {
            this.sendmessage("DialogID must be an integer: " + args[1]);
            return;
        }
        Dialog dialog = DialogController.Instance.dialogs.get(diagid);
        if (dialog == null) {
            this.sendmessage("Unknown dialog id: " + args[1]);
            return;
        }
        EntityDialogNpc npc = new EntityDialogNpc(this.pcParam.func_130014_f_());
        npc.display.name = args[2];
        EntityUtil.Copy((EntityLivingBase)player, (EntityLivingBase)npc);
        DialogOption option = new DialogOption();
        option.dialogId = diagid;
        option.title = dialog.title;
        npc.dialogs.put(0, option);
        NoppesUtilServer.openDialog((EntityPlayer)player, npc, dialog, 0);
    }
}

