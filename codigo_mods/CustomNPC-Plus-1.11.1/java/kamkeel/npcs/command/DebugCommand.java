/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 */
package kamkeel.npcs.command;

import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.CNPCDebug;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class DebugCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "debug";
    }

    @Override
    public String getDescription() {
        return "Toggle debug logging types";
    }

    @Override
    public String getUsage() {
        return "<type>";
    }

    @Override
    public boolean runSubCommands() {
        return false;
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            throw new CommandException("Usage: /kam debug <type> (e.g. energy)", new Object[0]);
        }
        String type = args[0].toLowerCase();
        boolean newState = CNPCDebug.toggleServer(type);
        ColorUtil.sendResult(sender, "Server debug '" + type + "' " + (newState ? "\u00a7aENABLED" : "\u00a7cDISABLED"));
    }
}

