/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.command.profile;

import java.util.Map;
import kamkeel.npcs.command.profile.CommandProfileBase;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.data.profile.Profile;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.api.handler.data.ISlot;

public class CommandProfileList
extends CommandProfileBase {
    public String func_71517_b() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "List all your current profile slots (IDs and names).";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayer)) {
            ColorUtil.sendError(sender, "This command can only be used by a player.");
            return;
        }
        EntityPlayer player = (EntityPlayer)sender;
        Profile profile = ProfileController.Instance.getProfile(player);
        if (profile == null) {
            ColorUtil.sendError(sender, "Profile not found.");
            return;
        }
        if (profile.getSlots().isEmpty()) {
            ColorUtil.sendMessage(sender, "No slots found. Using default slot 0.");
        }
        ColorUtil.sendMessage(sender, "Your Profile Slots:");
        for (Map.Entry<Integer, ISlot> entry : profile.getSlots().entrySet()) {
            int id = entry.getKey();
            String name = entry.getValue().getName();
            String prefix = id == profile.currentSlotId ? "* " : "- ";
            ColorUtil.sendMessage(sender, prefix + "Slot " + id + ": " + name);
        }
    }
}

