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
package kamkeel.npcs.command;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.command.AbilityCommand;
import kamkeel.npcs.command.AnimationCommand;
import kamkeel.npcs.command.AttributeCommand;
import kamkeel.npcs.command.AuctionCommand;
import kamkeel.npcs.command.CloneCommand;
import kamkeel.npcs.command.CommandCommand;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.command.ConfigCommand;
import kamkeel.npcs.command.DebugCommand;
import kamkeel.npcs.command.DialogCategoryCommand;
import kamkeel.npcs.command.DialogCommand;
import kamkeel.npcs.command.EffectCommand;
import kamkeel.npcs.command.FactionCommand;
import kamkeel.npcs.command.HelpCommand;
import kamkeel.npcs.command.MoneyCommand;
import kamkeel.npcs.command.NpcCommand;
import kamkeel.npcs.command.OverlayCommand;
import kamkeel.npcs.command.QuestCategoryCommand;
import kamkeel.npcs.command.QuestCommand;
import kamkeel.npcs.command.ScriptCommand;
import kamkeel.npcs.command.SlayCommand;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.controllers.data.attribute.AttributeDefinition;
import kamkeel.npcs.controllers.data.attribute.requirement.RequirementCheckerRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.scripted.CustomNPCsException;

public class CommandKamkeel
extends CommandBase {
    public Map<String, CommandKamkeelBase> map = new HashMap<String, CommandKamkeelBase>();
    public HelpCommand help = new HelpCommand(this);
    public String[] alias = new String[]{"kam"};

    public CommandKamkeel() {
        this.registerCommand(this.help);
        this.registerCommand(new ScriptCommand());
        this.registerCommand(new SlayCommand());
        this.registerCommand(new QuestCommand());
        this.registerCommand(new QuestCategoryCommand());
        this.registerCommand(new DialogCommand());
        this.registerCommand(new DialogCategoryCommand());
        this.registerCommand(new FactionCommand());
        this.registerCommand(new NpcCommand());
        this.registerCommand(new CloneCommand());
        this.registerCommand(new ConfigCommand());
        this.registerCommand(new AnimationCommand());
        this.registerCommand(new OverlayCommand());
        this.registerCommand(new CommandCommand());
        this.registerCommand(new EffectCommand());
        this.registerCommand(new AbilityCommand());
        this.registerCommand(new MoneyCommand());
        if (ConfigMarket.AuctionEnabled) {
            this.registerCommand(new AuctionCommand());
        }
        if (ConfigMain.AttributesEnabled) {
            this.registerCommand(new AttributeCommand());
        }
        this.registerCommand(new DebugCommand());
    }

    public void registerCommand(CommandKamkeelBase command) {
        String name = command.func_71517_b().toLowerCase();
        if (this.map.containsKey(name)) {
            throw new CustomNPCsException("Already a subcommand with the name: " + name, new Object[0]);
        }
        this.map.put(name, command);
    }

    public String func_71517_b() {
        return "kamkeel";
    }

    public List func_71514_a() {
        return Arrays.asList(this.alias);
    }

    public String func_71518_a(ICommandSender sender) {
        return "Use as /kamkeel subcommand";
    }

    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            this.help.func_71515_b(sender, args);
            return;
        }
        CommandKamkeelBase command = this.getCommand(args);
        if (command == null) {
            throw new CommandException("Unknown command " + args[0], new Object[0]);
        }
        args = Arrays.copyOfRange(args, 1, args.length);
        if (command.subcommands.isEmpty() || !command.runSubCommands()) {
            if (!CommandKamkeel.canSendCommand(sender, command)) {
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
        Method m;
        if (args.length == 1) {
            return CommandBase.func_71530_a((String[])args, (String[])this.map.keySet().toArray(new String[this.map.size()]));
        }
        CommandKamkeelBase command = this.getCommand(args);
        if (command == null) {
            return null;
        }
        if (args.length == 2 && command.runSubCommands()) {
            return CommandBase.func_71530_a((String[])args, (String[])command.subcommands.keySet().toArray(new String[command.subcommands.keySet().size()]));
        }
        String[] useArgs = command.getUsage().split(" ");
        if (command.runSubCommands() && (m = command.subcommands.get(args[1].toLowerCase())) != null) {
            useArgs = m.getAnnotation(CommandKamkeelBase.SubCommand.class).usage().split(" ");
        }
        if (args.length <= useArgs.length + 2 && args.length - 3 >= 0) {
            String usage = useArgs[args.length - 3];
            if (usage.equals("<player>") || usage.equals("[player]")) {
                return CommandBase.func_71530_a((String[])args, (String[])MinecraftServer.func_71276_C().func_71213_z());
            }
            if (usage.equals("<attribute>")) {
                ArrayList<String> keys = new ArrayList<String>();
                for (AttributeDefinition def : AttributeController.getAllAttributes()) {
                    keys.add(def.getKey());
                }
                return CommandBase.func_71530_a((String[])args, (String[])keys.toArray(new String[keys.size()]));
            }
            if (usage.equals("<requirement>")) {
                ArrayList<String> keys = new ArrayList<String>(RequirementCheckerRegistry.getAllKeys());
                return CommandBase.func_71530_a((String[])args, (String[])keys.toArray(new String[keys.size()]));
            }
            if (usage.equals("<effectName>")) {
                List<String> keys = CommandKamkeel.stripColorCodes(EffectCommand.getSortedEffectNames());
                return CommandBase.func_71530_a((String[])args, (String[])keys.toArray(new String[keys.size()]));
            }
            if (usage.equals("<ability>")) {
                List<String> keys = CommandKamkeel.stripColorCodes(AbilityCommand.getPlayerAbilityNames());
                return CommandBase.func_71530_a((String[])args, (String[])keys.toArray(new String[keys.size()]));
            }
            if (usage.equals("<chain>")) {
                List<String> keys = CommandKamkeel.stripColorCodes(AbilityCommand.getPlayerChainNames());
                return CommandBase.func_71530_a((String[])args, (String[])keys.toArray(new String[keys.size()]));
            }
        }
        return command.func_71516_a(sender, Arrays.copyOfRange(args, 1, args.length));
    }

    private static List<String> stripColorCodes(List<String> list) {
        ArrayList<String> result = new ArrayList<String>(list.size());
        for (String s : list) {
            result.add(s.replaceAll("\u00a7[0-9a-fk-or]", ""));
        }
        return result;
    }

    public CommandKamkeelBase getCommand(String[] args) {
        if (args.length == 0) {
            return null;
        }
        return this.map.get(args[0].toLowerCase());
    }

    public int func_82362_a() {
        return 2;
    }

    public static String getCommandPermission(String command) {
        return "cnpc.kamkeel." + command.toLowerCase();
    }

    public static String getUniversalPermission() {
        return "cnpc.kamkeel.*";
    }

    public static boolean canSendCommand(ICommandSender sender, CommandKamkeelBase command) {
        if (sender.func_70003_b(command.func_82362_a(), CommandKamkeel.getUniversalPermission())) {
            return true;
        }
        if (sender.func_70003_b(command.func_82362_a(), CommandKamkeel.getCommandPermission(command.func_71517_b()))) {
            return true;
        }
        if (sender instanceof EntityPlayer) {
            return CustomNpcsPermissions.hasCustomPermission((EntityPlayer)sender, CommandKamkeel.getCommandPermission(command.func_71517_b()));
        }
        return false;
    }
}

