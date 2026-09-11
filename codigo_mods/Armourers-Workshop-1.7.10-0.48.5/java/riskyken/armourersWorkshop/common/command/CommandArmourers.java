/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.server.MinecraftServer
 */
package riskyken.armourersWorkshop.common.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import riskyken.armourersWorkshop.common.command.CommandAdminPanel;
import riskyken.armourersWorkshop.common.command.CommandClearModelCache;
import riskyken.armourersWorkshop.common.command.CommandClearSkins;
import riskyken.armourersWorkshop.common.command.CommandExportSkin;
import riskyken.armourersWorkshop.common.command.CommandGiveSkin;
import riskyken.armourersWorkshop.common.command.CommandResyncWardrobe;
import riskyken.armourersWorkshop.common.command.CommandSetItemAsSkinnable;
import riskyken.armourersWorkshop.common.command.CommandSetSkin;
import riskyken.armourersWorkshop.common.command.CommandSetUnlockedWardrobeSlots;
import riskyken.armourersWorkshop.common.command.CommandSetWardrobeOption;
import riskyken.armourersWorkshop.common.command.ModCommand;

public class CommandArmourers
extends CommandBase {
    private final ArrayList<ModCommand> subCommands = new ArrayList();

    public CommandArmourers() {
        this.subCommands.add(new CommandClearModelCache());
        this.subCommands.add(new CommandClearSkins());
        this.subCommands.add(new CommandGiveSkin());
        this.subCommands.add(new CommandResyncWardrobe());
        this.subCommands.add(new CommandSetSkin());
        this.subCommands.add(new CommandSetUnlockedWardrobeSlots());
        this.subCommands.add(new CommandAdminPanel());
        this.subCommands.add(new CommandSetItemAsSkinnable());
        this.subCommands.add(new CommandSetWardrobeOption());
        this.subCommands.add(new CommandExportSkin());
    }

    public int func_82362_a() {
        return 2;
    }

    public String func_71517_b() {
        return "armourers";
    }

    public String func_71518_a(ICommandSender commandSender) {
        return "commands.armourers.usage";
    }

    private String[] getSubCommandNames() {
        Object[] subCommandNames = new String[this.subCommands.size()];
        for (int i = 0; i < subCommandNames.length; ++i) {
            subCommandNames[i] = this.subCommands.get(i).func_71517_b();
        }
        Arrays.sort(subCommandNames);
        return subCommandNames;
    }

    private ModCommand getSubCommand(String name) {
        for (int i = 0; i < this.subCommands.size(); ++i) {
            if (!this.subCommands.get(i).func_71517_b().equals(name)) continue;
            return this.subCommands.get(i);
        }
        return null;
    }

    public List func_71516_a(ICommandSender commandSender, String[] currentCommand) {
        String commandName;
        ModCommand command;
        if (currentCommand.length == 1) {
            return CommandArmourers.func_71530_a((String[])currentCommand, (String[])this.getSubCommandNames());
        }
        if (currentCommand.length > 1 && (command = this.getSubCommand(commandName = currentCommand[0])) != null) {
            return command.func_71516_a(commandSender, currentCommand);
        }
        return null;
    }

    public void func_71515_b(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand == null) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        if (currentCommand.length < 1) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        String commandName = currentCommand[0];
        ModCommand command = this.getSubCommand(commandName);
        if (command != null) {
            command.func_71515_b(commandSender, currentCommand);
            return;
        }
        throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
    }

    private String[] getPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }
}

