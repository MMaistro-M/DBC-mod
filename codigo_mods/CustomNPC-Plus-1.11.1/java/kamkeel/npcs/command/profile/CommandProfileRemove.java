/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.command.profile;

import kamkeel.npcs.command.profile.CommandProfileBase;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.data.profile.EnumProfileOperation;
import kamkeel.npcs.controllers.data.profile.ProfileOperation;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandProfileRemove
extends CommandProfileBase {
    public String func_71517_b() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Remove a slot from your profile. You cannot remove your active slot.";
    }

    @Override
    public String getUsage() {
        return "<slotId>";
    }

    @Override
    public boolean runSubCommands() {
        return false;
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        int slotId;
        if (!(sender instanceof EntityPlayer)) {
            ColorUtil.sendError(sender, "This command can only be used by a player.");
            return;
        }
        if (args.length < 1) {
            ColorUtil.sendError(sender, "Usage: " + this.getUsage());
            return;
        }
        try {
            slotId = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "Slot ID must be a number: " + args[0]);
            return;
        }
        EntityPlayer player = (EntityPlayer)sender;
        ProfileOperation result = ProfileController.Instance.removeSlot(player, slotId);
        if (result.getResult() == EnumProfileOperation.SUCCESS) {
            ColorUtil.sendResult(sender, "Successfully removed slot %d from your profile.", slotId);
        } else if (result.getResult() == EnumProfileOperation.LOCKED) {
            ColorUtil.sendError(sender, "Profile is locked. Details: %s", result.getMessage());
        } else if (result.getResult() == EnumProfileOperation.ERROR) {
            ColorUtil.sendError(sender, "Error removing slot: %s", result.getMessage());
        } else {
            ColorUtil.sendError(sender, "Unexpected error: %s", result.getMessage());
        }
    }
}

