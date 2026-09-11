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

public class CommandProfileCreate
extends CommandProfileBase {
    public String func_71517_b() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create a new profile slot.";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public boolean runSubCommands() {
        return false;
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayer)) {
            ColorUtil.sendError(sender, "This command can only be used by a player.");
            return;
        }
        EntityPlayer player = (EntityPlayer)sender;
        if (ProfileController.Instance.getProfile(player) == null) {
            ColorUtil.sendError(sender, "Profile not found.");
            return;
        }
        ProfileOperation result = ProfileController.Instance.createSlotInternal(ProfileController.Instance.getProfile(player));
        if (result.getResult() == EnumProfileOperation.SUCCESS) {
            ColorUtil.sendResult(sender, "New profile slot created successfully.");
        } else if (result.getResult() == EnumProfileOperation.LOCKED) {
            ColorUtil.sendError(sender, "Profile is locked. Details: %s", result.getMessage());
        } else if (result.getResult() == EnumProfileOperation.ERROR) {
            ColorUtil.sendError(sender, "Error creating new profile slot: %s", result.getMessage());
        } else {
            ColorUtil.sendError(sender, "Unexpected error: %s", result.getMessage());
        }
    }
}

