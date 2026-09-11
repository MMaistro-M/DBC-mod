/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChunkCoordinates
 */
package kamkeel.npcs.command;

import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.math.BlockPos;
import noppes.npcs.EventHooks;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.CustomNPCsEvent;

public class CommandCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "command";
    }

    @Override
    public String getDescription() {
        return "command CommandName [arg1 arg2 ... argn]";
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            ColorUtil.sendError(sender, "You must specify the command name");
            return;
        }
        String commandId = args[0];
        String[] commandArgs = new String[args.length - 1];
        System.arraycopy(args, 1, commandArgs, 0, args.length - 1);
        IWorld senderWorld = NpcAPI.Instance().getIWorld(sender.func_130014_f_());
        ChunkCoordinates senderCoords = sender.func_82114_b();
        IPos senderPos = NpcAPI.Instance().getIPos(new BlockPos(senderCoords.field_71574_a, senderCoords.field_71572_b, senderCoords.field_71573_c));
        CustomNPCsEvent.ScriptedCommandEvent event = new CustomNPCsEvent.ScriptedCommandEvent(senderWorld, senderPos, sender.func_70005_c_(), commandId, commandArgs);
        EventHooks.onScriptedCommand(sender, event);
        if (!event.replyMessage.isEmpty()) {
            ColorUtil.sendMessage(sender, event.replyMessage);
        }
    }
}

