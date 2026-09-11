/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 */
package kamkeel.npcs.command.profile;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.command.profile.CommandProfileBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import noppes.npcs.config.ConfigMain;

public class CommandProfileRegion
extends CommandProfileBase {
    public String func_71517_b() {
        return "region";
    }

    @Override
    public String getDescription() {
        return "Manage profile region switching and restricted regions";
    }

    @Override
    public String getUsage() {
        return "<subcommand>";
    }

    @CommandProfileBase.SubCommand(desc="Enable region-based profile switching", usage="")
    public void enable(ICommandSender sender, String[] args) throws CommandException {
        ConfigMain.RegionProfileSwitching = true;
        ConfigMain.RegionProfileSwitchingProperty.set(true);
        if (ConfigMain.config.hasChanged()) {
            ConfigMain.config.save();
        }
        ColorUtil.sendResult(sender, "Profile region switching enabled.");
    }

    @CommandProfileBase.SubCommand(desc="Disable region-based profile switching", usage="")
    public void disable(ICommandSender sender, String[] args) throws CommandException {
        ConfigMain.RegionProfileSwitching = false;
        ConfigMain.RegionProfileSwitchingProperty.set(false);
        if (ConfigMain.config.hasChanged()) {
            ConfigMain.config.save();
        }
        ColorUtil.sendResult(sender, "Profile region switching disabled.");
    }

    @CommandProfileBase.SubCommand(desc="Add a new region. Format: DIM X1 Y1 Z1 X2 Y2 Z2", usage="<dim> <x1> <y1> <z1> <x2> <y2> <z2>")
    public void add(ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 7) {
            ColorUtil.sendError(sender, "Usage: /profile region add <dim> <x1> <y1> <z1> <x2> <y2> <z2>");
            return;
        }
        ArrayList<Integer> regionList = new ArrayList<Integer>();
        try {
            for (String arg : args) {
                regionList.add(Integer.parseInt(arg));
            }
        }
        catch (NumberFormatException e) {
            ColorUtil.sendError(sender, "Invalid number format in region data.");
            return;
        }
        ConfigMain.RestrictedProfileRegions.add(regionList);
        ArrayList<String> regionStrings = new ArrayList<String>();
        for (List<Integer> region : ConfigMain.RestrictedProfileRegions) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < region.size(); ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(region.get(i));
            }
            regionStrings.add(sb.toString());
        }
        ConfigMain.RestrictedProfileRegionsProperty.set(regionStrings.toArray(new String[0]));
        if (ConfigMain.config.hasChanged()) {
            ConfigMain.config.save();
        }
        ColorUtil.sendResult(sender, "Region added successfully.");
    }

    @CommandProfileBase.SubCommand(desc="Remove a region by its index (1-based).", usage="<index>")
    public void remove(ICommandSender sender, String[] args) throws CommandException {
        int index;
        if (args.length < 1) {
            ColorUtil.sendError(sender, "Usage: /profile region remove <index>");
            return;
        }
        try {
            index = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e) {
            ColorUtil.sendError(sender, "Index must be a number.");
            return;
        }
        if (--index < 0 || index >= ConfigMain.RestrictedProfileRegions.size()) {
            ColorUtil.sendError(sender, "Invalid index. Must be between 1 and " + ConfigMain.RestrictedProfileRegions.size());
            return;
        }
        ConfigMain.RestrictedProfileRegions.remove(index);
        ArrayList<String> regionStrings = new ArrayList<String>();
        for (List<Integer> region : ConfigMain.RestrictedProfileRegions) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < region.size(); ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(region.get(i));
            }
            regionStrings.add(sb.toString());
        }
        ConfigMain.RestrictedProfileRegionsProperty.set(regionStrings.toArray(new String[0]));
        if (ConfigMain.config.hasChanged()) {
            ConfigMain.config.save();
        }
        ColorUtil.sendResult(sender, "Region removed successfully.");
    }

    @CommandProfileBase.SubCommand(desc="List all restricted profile regions.", usage="")
    public void list(ICommandSender sender, String[] args) throws CommandException {
        if (ConfigMain.RestrictedProfileRegions.isEmpty()) {
            ColorUtil.sendMessage(sender, "No restricted profile regions configured.");
            return;
        }
        ColorUtil.sendMessage(sender, "Restricted Profile Regions:");
        int i = 1;
        for (List<Integer> region : ConfigMain.RestrictedProfileRegions) {
            StringBuilder sb = new StringBuilder();
            sb.append(i).append(": ");
            for (int j = 0; j < region.size(); ++j) {
                if (j > 0) {
                    sb.append(", ");
                }
                sb.append(region.get(j));
            }
            ColorUtil.sendMessage(sender, sb.toString());
            ++i;
        }
    }
}

