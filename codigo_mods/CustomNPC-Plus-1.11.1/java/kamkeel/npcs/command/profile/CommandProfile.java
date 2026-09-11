/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 */
package kamkeel.npcs.command.profile;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.command.profile.CommandHelpProfile;
import kamkeel.npcs.command.profile.CommandProfileAdmin;
import kamkeel.npcs.command.profile.CommandProfileBase;
import kamkeel.npcs.command.profile.CommandProfileChange;
import kamkeel.npcs.command.profile.CommandProfileCreate;
import kamkeel.npcs.command.profile.CommandProfileList;
import kamkeel.npcs.command.profile.CommandProfileRegion;
import kamkeel.npcs.command.profile.CommandProfileRemove;
import kamkeel.npcs.command.profile.CommandProfileRename;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.scripted.CustomNPCsException;

public class CommandProfile
extends CommandBase {
    public Map<String, CommandProfileBase> map = new HashMap<String, CommandProfileBase>();
    public CommandHelpProfile help = new CommandHelpProfile(this);
    public String[] alias = new String[]{"profile"};

    public CommandProfile() {
        this.registerCommand(this.help);
        this.registerCommand(new CommandProfileChange());
        this.registerCommand(new CommandProfileCreate());
        this.registerCommand(new CommandProfileRemove());
        this.registerCommand(new CommandProfileAdmin());
        this.registerCommand(new CommandProfileRegion());
        this.registerCommand(new CommandProfileList());
        this.registerCommand(new CommandProfileRename());
    }

    public void registerCommand(CommandProfileBase command) {
        String name = command.func_71517_b().toLowerCase();
        if (this.map.containsKey(name)) {
            throw new CustomNPCsException("Already a subcommand with the name: " + name, new Object[0]);
        }
        this.map.put(name, command);
    }

    public String func_71517_b() {
        return "profile";
    }

    public List func_71514_a() {
        return Arrays.asList(this.alias);
    }

    public String func_71518_a(ICommandSender sender) {
        return "Use as /profile subcommand";
    }

    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            this.help.func_71515_b(sender, args);
            return;
        }
        CommandProfileBase command = this.getCommand(args);
        if (command == null) {
            throw new CommandException("Unknown command " + args[0], new Object[0]);
        }
        args = Arrays.copyOfRange(args, 1, args.length);
        if (command.subcommands.isEmpty() || !command.runSubCommands()) {
            if (!CommandProfile.canSendCommand(sender, command)) {
                throw new CommandException("You are not allowed to use this command: " + (Object)((Object)command), new Object[0]);
            }
            command.canRun(sender, command.getUsage(), args);
            command.func_71515_b(sender, args);
            return;
        }
        if (args.length == 0) {
            this.help.func_71515_b(sender, new String[]{command.func_71517_b()});
            return;
        }
        command.processSubCommand(sender, args[0], Arrays.copyOfRange(args, 1, args.length));
    }

    public List func_71516_a(ICommandSender sender, String[] args) {
        String usage;
        Method m;
        if (args.length == 1) {
            return CommandBase.func_71530_a((String[])args, (String[])this.map.keySet().toArray(new String[this.map.size()]));
        }
        CommandProfileBase command = this.getCommand(args);
        if (command == null) {
            return null;
        }
        if (args.length == 2 && command.runSubCommands()) {
            return CommandProfile.func_71530_a((String[])args, (String[])command.subcommands.keySet().toArray(new String[command.subcommands.keySet().size()]));
        }
        String[] useArgs = command.getUsage().split(" ");
        if (command.runSubCommands() && (m = command.subcommands.get(args[1].toLowerCase())) != null) {
            useArgs = m.getAnnotation(CommandProfileBase.SubCommand.class).usage().split(" ");
        }
        if (args.length <= useArgs.length + 2 && args.length - 3 >= 0 && ((usage = useArgs[args.length - 3]).equals("<player>") || usage.equals("[player]") || usage.equals("<targetPlayer>") || usage.equals("<sourcePlayer>") || usage.equals("<destinationPlayer>"))) {
            return CommandBase.func_71530_a((String[])args, (String[])MinecraftServer.func_71276_C().func_71213_z());
        }
        return command.func_71516_a(sender, Arrays.copyOfRange(args, 1, args.length));
    }

    public CommandProfileBase getCommand(String[] args) {
        if (args.length == 0) {
            return null;
        }
        return this.map.get(args[0].toLowerCase());
    }

    public int func_82362_a() {
        return 4;
    }

    public static String getCommandPermission(String command) {
        return "profile.kamkeel." + command.toLowerCase();
    }

    public static String getUniversalPermission() {
        return "profile.kamkeel.*";
    }

    public static boolean canSendCommand(ICommandSender sender, CommandProfileBase command) {
        if (sender.func_70003_b(command.func_82362_a(), CommandProfile.getUniversalPermission())) {
            return true;
        }
        if (sender.func_70003_b(command.func_82362_a(), CommandProfile.getCommandPermission(command.func_71517_b()))) {
            return true;
        }
        if (sender instanceof EntityPlayer) {
            return CustomNpcsPermissions.hasCustomPermission((EntityPlayer)sender, CommandProfile.getCommandPermission(command.func_71517_b()));
        }
        return false;
    }
}

