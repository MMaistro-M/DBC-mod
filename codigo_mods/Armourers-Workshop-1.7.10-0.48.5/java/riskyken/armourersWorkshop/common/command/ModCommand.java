/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.server.MinecraftServer
 */
package riskyken.armourersWorkshop.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import riskyken.armourersWorkshop.ArmourersWorkshop;

public abstract class ModCommand
extends CommandBase {
    public String func_71518_a(ICommandSender commandSender) {
        return "commands.armourers." + this.func_71517_b() + ".usage";
    }

    protected String[] getPlayers() {
        MinecraftServer server = ArmourersWorkshop.proxy.getServer();
        return server.func_71213_z();
    }
}

