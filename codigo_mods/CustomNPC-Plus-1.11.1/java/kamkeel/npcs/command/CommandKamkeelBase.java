/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.CustomNpcsPermissions;

public abstract class CommandKamkeelBase
extends CommandBase {
    public Map<String, Method> subcommands = new HashMap<String, Method>();
    public Map<String, CommandKamkeelBase> nestedCommands = new HashMap<String, CommandKamkeelBase>();

    public CommandKamkeelBase() {
        for (Method m : ((Object)((Object)this)).getClass().getDeclaredMethods()) {
            SubCommand sc = m.getAnnotation(SubCommand.class);
            if (sc == null) continue;
            String name = sc.name();
            if (name.equals("")) {
                name = m.getName();
            }
            this.subcommands.put(name.toLowerCase(), m);
        }
    }

    public void registerNestedCommand(CommandKamkeelBase command) {
        this.nestedCommands.put(command.func_71517_b().toLowerCase(), command);
    }

    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
    }

    public String func_71518_a(ICommandSender sender) {
        return this.getDescription();
    }

    public abstract String getDescription();

    public String getUsage() {
        return "";
    }

    public boolean runSubCommands() {
        return !this.subcommands.isEmpty() || !this.nestedCommands.isEmpty();
    }

    public void processSubCommand(ICommandSender sender, String command, String[] args) throws CommandException {
        CommandKamkeelBase nested = this.nestedCommands.get(command.toLowerCase());
        if (nested != null) {
            if (!this.canSendNestedCommand(sender, nested)) {
                throw new CommandException("You are not allowed to use this command: " + command, new Object[0]);
            }
            if (args.length == 0 || !nested.runSubCommands()) {
                nested.func_71515_b(sender, args);
            } else {
                nested.processSubCommand(sender, args[0], Arrays.copyOfRange(args, 1, args.length));
            }
            return;
        }
        Method m = this.subcommands.get(command.toLowerCase());
        if (m == null) {
            throw new CommandException("Unknown subcommand " + command, new Object[0]);
        }
        SubCommand sc = m.getAnnotation(SubCommand.class);
        if (!this.canSendCommand(sender, sc, command)) {
            throw new CommandException("You are not allowed to use this command: " + command, new Object[0]);
        }
        this.canRun(sender, sc.usage(), args);
        try {
            m.invoke((Object)this, sender, args);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void canRun(ICommandSender sender, String usage, String[] args) throws CommandException {
        String[] np = usage.split(" ");
        ArrayList<String> required = new ArrayList<String>();
        for (int i = 0; i < np.length; ++i) {
            String command = np[i];
            if (command.startsWith("<")) {
                required.add(command);
            }
            if (!command.equals("<player>") || args.length <= i) continue;
            CommandBase.func_82359_c((ICommandSender)sender, (String)args[i]);
        }
        if (args.length < required.size()) {
            throw new CommandException("Missing parameter: " + (String)required.get(args.length), new Object[0]);
        }
    }

    public int func_82362_a() {
        return 2;
    }

    public String getSubCommandPermission(String subCommand) {
        return "cnpc.kamkeel." + this.func_71517_b().toLowerCase() + "." + subCommand.toLowerCase();
    }

    public String getSubUniversalPermission() {
        return "cnpc.kamkeel." + this.func_71517_b().toLowerCase() + "*";
    }

    public boolean canSendCommand(ICommandSender sender, SubCommand command, String subCommand) {
        if (sender.func_70003_b(command.permission(), this.getSubUniversalPermission())) {
            return true;
        }
        if (sender.func_70003_b(command.permission(), this.getSubCommandPermission(subCommand))) {
            return true;
        }
        if (sender instanceof EntityPlayer) {
            return CustomNpcsPermissions.hasCustomPermission((EntityPlayer)sender, this.getSubCommandPermission(subCommand));
        }
        return false;
    }

    public boolean canSendNestedCommand(ICommandSender sender, CommandKamkeelBase nested) {
        int permission = nested.func_82362_a();
        String subCommand = nested.func_71517_b();
        if (sender.func_70003_b(permission, this.getSubUniversalPermission())) {
            return true;
        }
        if (sender.func_70003_b(permission, this.getSubCommandPermission(subCommand))) {
            return true;
        }
        if (sender instanceof EntityPlayer) {
            return CustomNpcsPermissions.hasCustomPermission((EntityPlayer)sender, this.getSubCommandPermission(subCommand));
        }
        return false;
    }

    public String[] getAllSubCommandNames() {
        HashSet<String> names = new HashSet<String>();
        names.addAll(this.subcommands.keySet());
        names.addAll(this.nestedCommands.keySet());
        return names.toArray(new String[0]);
    }

    public List getNestedTabCompletions(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            return null;
        }
        String subCmd = args[0].toLowerCase();
        CommandKamkeelBase nested = this.nestedCommands.get(subCmd);
        if (nested != null && args.length > 1) {
            String[] nestedArgs = Arrays.copyOfRange(args, 1, args.length);
            return nested.func_71516_a(sender, nestedArgs);
        }
        return null;
    }

    @Retention(value=RetentionPolicy.RUNTIME)
    @Target(value={ElementType.METHOD})
    public static @interface SubCommand {
        public String name() default "";

        public String usage() default "";

        public String desc();

        public int permission() default 2;
    }
}

