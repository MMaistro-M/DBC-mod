/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.command.profile;

import java.lang.reflect.Method;
import java.util.Map;
import kamkeel.npcs.command.profile.CommandProfile;
import kamkeel.npcs.command.profile.CommandProfileBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.CustomNpcsPermissions;

public class CommandHelpProfile
extends CommandProfileBase {
    private final CommandProfile parent;

    public CommandHelpProfile(CommandProfile parent) {
        this.parent = parent;
    }

    public String func_71517_b() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help [command]";
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            ColorUtil.sendMessage(sender, "\u00a78------ \u00a7aProfile Commands \u00a78------");
            for (Map.Entry<String, CommandProfileBase> entry : this.parent.map.entrySet()) {
                ColorUtil.sendMessage(sender, "\u00a77> \u00a7e" + entry.getKey() + "\u00a78: \u00a77" + entry.getValue().func_71518_a(sender));
            }
            return;
        }
        CommandProfileBase command = this.parent.getCommand(args);
        if (command == null) {
            ColorUtil.sendError(sender, "Unknown command " + args[0]);
            return;
        }
        if (command.subcommands.isEmpty()) {
            ColorUtil.sendMessage(sender, command.func_71518_a(sender));
            return;
        }
        Method m = null;
        if (args.length > 1) {
            m = command.subcommands.get(args[1].toLowerCase());
        }
        if (m == null) {
            ColorUtil.sendMessage(sender, "\u00a78------ \u00a7a" + command.func_71517_b().toUpperCase() + " SubCommands \u00a78------");
            ColorUtil.sendMessage(sender, "\u00a77Usage: \u00a76" + command.getUsage());
            for (Map.Entry<String, Method> entry : command.subcommands.entrySet()) {
                CommandProfileBase.SubCommand sc = entry.getValue().getAnnotation(CommandProfileBase.SubCommand.class);
                if (sender instanceof EntityPlayer && !command.canSendCommand(sender, sc, entry.getKey())) continue;
                ColorUtil.sendMessage(sender, "\u00a77> \u00a7e" + entry.getKey() + "\u00a78: \u00a77" + sc.desc());
            }
            if (!(sender instanceof EntityPlayer) || CustomNpcsPermissions.hasPermission((EntityPlayer)sender, CustomNpcsPermissions.PROFILE_ADMIN)) {
                ColorUtil.sendMessage(sender, "\u00a78Permission:\u00a77 " + CommandProfile.getCommandPermission(command.func_71517_b()));
            }
        } else {
            ColorUtil.sendMessage(sender, "\u00a78------ \u00a7b" + command.func_71517_b().toUpperCase() + "." + args[1].toUpperCase() + " Command \u00a78------");
            CommandProfileBase.SubCommand sc = m.getAnnotation(CommandProfileBase.SubCommand.class);
            ColorUtil.sendMessage(sender, "\u00a77" + sc.desc());
            if (!sc.usage().isEmpty()) {
                ColorUtil.sendMessage(sender, "\u00a77Usage: \u00a76" + sc.usage());
            }
            if (!(sender instanceof EntityPlayer) || CustomNpcsPermissions.hasPermission((EntityPlayer)sender, CustomNpcsPermissions.PROFILE_ADMIN)) {
                ColorUtil.sendMessage(sender, "\u00a78Permission:\u00a77 " + this.getSubCommandPermission(args[1]));
            }
        }
    }
}

