/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package riskyken.armourersWorkshop.common.command;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import riskyken.armourersWorkshop.common.command.ModCommand;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;

public class CommandResyncWardrobe
extends ModCommand {
    public String func_71517_b() {
        return "resyncWardrobe";
    }

    public List func_71516_a(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length == 2) {
            return CommandResyncWardrobe.func_71530_a((String[])currentCommand, (String[])this.getPlayers());
        }
        return null;
    }

    public void func_71515_b(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length != 2) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        String playerName = currentCommand[1];
        EntityPlayerMP player = CommandResyncWardrobe.func_82359_c((ICommandSender)commandSender, (String)playerName);
        if (player == null) {
            return;
        }
        ExPropsPlayerSkinData.get((EntityPlayer)player).updateEquipmentDataToPlayersAround();
    }
}

