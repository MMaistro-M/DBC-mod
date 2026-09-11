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
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;

public class CommandSetWardrobeOption
extends ModCommand {
    private static final String[] SUB_OPTIONS = new String[]{"showHeadArmour", "showChestArmour", "showLegArmour", "showFootArmour"};

    public String func_71517_b() {
        return "setWardrobeOption";
    }

    public List func_71516_a(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length == 2) {
            return CommandSetWardrobeOption.func_71530_a((String[])currentCommand, (String[])this.getPlayers());
        }
        if (currentCommand.length == 3) {
            return CommandSetWardrobeOption.func_71530_a((String[])currentCommand, (String[])SUB_OPTIONS);
        }
        if (currentCommand.length == 4) {
            return CommandSetWardrobeOption.func_71530_a((String[])currentCommand, (String[])new String[]{"true", "false"});
        }
        return null;
    }

    public void func_71515_b(ICommandSender commandSender, String[] currentCommand) {
        if (currentCommand.length != 4) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        EntityPlayerMP player = CommandSetWardrobeOption.func_82359_c((ICommandSender)commandSender, (String)currentCommand[1]);
        if (player == null) {
            return;
        }
        String subOption = currentCommand[2];
        boolean value = CommandSetWardrobeOption.func_110662_c((ICommandSender)commandSender, (String)currentCommand[3]);
        int subOptionIndex = -1;
        for (int i = 0; i < SUB_OPTIONS.length; ++i) {
            if (!subOption.equals(SUB_OPTIONS[i])) continue;
            subOptionIndex = i;
            break;
        }
        if (subOptionIndex == -1) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[]{currentCommand});
        }
        ExPropsPlayerSkinData playerEquipmentData = ExPropsPlayerSkinData.get((EntityPlayer)player);
        if (playerEquipmentData != null) {
            EquipmentWardrobeData ewd = playerEquipmentData.getEquipmentWardrobeData();
            if (subOptionIndex < 4) {
                ewd.armourOverride.set(subOptionIndex, !value);
            }
            playerEquipmentData.setSkinInfo(ewd, true);
        }
    }
}

