/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 */
package foxz.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import noppes.npcs.api.ICommand;

public class ScriptedCommand
extends CommandBase
implements ICommand {
    private String commandName;
    private int permissionLevel;
    private String commandUsage;
    private final ArrayList<String> aliases;

    public ScriptedCommand(String commandName, int permissionLevel) {
        this.commandName = commandName;
        this.permissionLevel = permissionLevel;
        this.commandUsage = "";
        this.aliases = new ArrayList();
    }

    @Override
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    @Override
    public void setCommandUsage(String commandUsage) {
        this.commandUsage = commandUsage;
    }

    @Override
    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String func_71517_b() {
        return this.commandName;
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return this.commandUsage;
    }

    @Override
    public String getCommandUsage() {
        return this.commandUsage;
    }

    public void func_71515_b(ICommandSender p_71515_1_, String[] p_71515_2_) {
    }

    public int func_82362_a() {
        return this.permissionLevel;
    }

    @Override
    public int getPermissionLevel() {
        return this.func_82362_a();
    }

    public List<?> func_71514_a() {
        return this.aliases;
    }

    @Override
    public String[] getAliases() {
        return this.aliases.toArray(new String[0]);
    }

    @Override
    public void addAliases(String ... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
    }

    @Override
    public boolean hasAlias(String alias) {
        return this.aliases.contains(alias);
    }

    @Override
    public void removeAlias(String alias) {
        this.aliases.remove(alias);
    }
}

