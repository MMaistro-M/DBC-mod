/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.command.profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.CustomNpcsPermissions;

public abstract class CommandProfileBase
extends CommandBase {
    public Map<String, Method> subcommands = new HashMap<String, Method>();

    public CommandProfileBase() {
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
        return !this.subcommands.isEmpty();
    }

    public void processSubCommand(ICommandSender sender, String command, String[] args) throws CommandException {
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
        return "profile.kamkeel." + this.func_71517_b().toLowerCase() + "." + subCommand.toLowerCase();
    }

    public String getSubUniversalPermission() {
        return "profile.kamkeel." + this.func_71517_b().toLowerCase() + "*";
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

    @Retention(value=RetentionPolicy.RUNTIME)
    @Target(value={ElementType.METHOD})
    public static @interface SubCommand {
        public String name() default "";

        public String usage() default "";

        public String desc();

        public int permission() default 2;
    }
}

