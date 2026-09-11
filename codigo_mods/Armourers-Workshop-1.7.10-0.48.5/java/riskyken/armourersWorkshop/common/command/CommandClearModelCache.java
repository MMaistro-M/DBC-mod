/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package riskyken.armourersWorkshop.common.command;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import riskyken.armourersWorkshop.common.command.ModCommand;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerClientCommand;

public class CommandClearModelCache
extends ModCommand {
    public String func_71517_b() {
        return "clearModelCache";
    }

    public List func_71516_a(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length == 2) {
            return CommandClearModelCache.func_71530_a((String[])currentCommand, (String[])this.getPlayers());
        }
        return null;
    }

    public void func_71515_b(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length != 2) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        String playerName = currentCommand[1];
        EntityPlayerMP player = CommandClearModelCache.func_82359_c((ICommandSender)commandSender, (String)playerName);
        if (player == null) {
            return;
        }
        PacketHandler.networkWrapper.sendTo((IMessage)new MessageServerClientCommand(MessageServerClientCommand.CommandType.CLEAR_MODEL_CACHE), player);
    }
}

