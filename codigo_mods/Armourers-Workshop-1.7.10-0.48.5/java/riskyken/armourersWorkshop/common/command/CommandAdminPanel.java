/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package riskyken.armourersWorkshop.common.command;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import riskyken.armourersWorkshop.common.command.ModCommand;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerClientCommand;

public class CommandAdminPanel
extends ModCommand {
    public String func_71517_b() {
        return "adminPanel";
    }

    public void func_71515_b(ICommandSender commandSender, String[] currentCommand) {
        EntityPlayerMP player = CommandAdminPanel.func_71521_c((ICommandSender)commandSender);
        if (player == null) {
            return;
        }
        MessageServerClientCommand message = new MessageServerClientCommand(MessageServerClientCommand.CommandType.OPEN_ADMIN_PANEL);
        PacketHandler.networkWrapper.sendTo((IMessage)message, player);
    }
}

