/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.Teleporter
 */
package com.tobiasmjc.dbcadditions.command;

import com.tobiasmjc.dbcadditions.dimensions.TeleporterDBUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class DimensionCommand
extends CommandBase {
    public String getCommandName() {
        return "dbcdimension";
    }

    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "/dbcdimension <player> <dimension>";
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandBase.getListOfStringsMatchingLastWord((String[])args, (String[])MinecraftServer.getServer().getAllUsernames());
        }
        return new ArrayList<String>();
    }

    public int getRequiredPermissionLevel() {
        return 4;
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.addChatMessage((IChatComponent)new ChatComponentText(this.getCommandUsage(sender)));
            return;
        }
        EntityPlayerMP player = DimensionCommand.getPlayer((ICommandSender)sender, (String)args[0]);
        int id = DimensionCommand.parseInt((ICommandSender)sender, (String)args[1]);
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (!TeleporterDBUtils.transferPlayer(server, player, id)) {
            sender.addChatMessage((IChatComponent)new ChatComponentText("Dimension " + id + " is not available."));
        }
    }
}
