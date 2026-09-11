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
    public String func_71517_b() {
        return "dbcdimension";
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return "/dbcdimension <player> <dimension>";
    }

    public List<String> func_71516_a(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return CommandBase.func_71530_a((String[])args, (String[])MinecraftServer.func_71276_C().func_71213_z());
        }
        return new ArrayList<String>();
    }

    public int func_82362_a() {
        return 4;
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.func_145747_a((IChatComponent)new ChatComponentText(this.func_71518_a(sender)));
            return;
        }
        EntityPlayerMP player = DimensionCommand.func_82359_c((ICommandSender)sender, (String)args[0]);
        int id = DimensionCommand.func_71526_a((ICommandSender)sender, (String)args[1]);
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (!TeleporterDBUtils.transferPlayer(server, player, id)) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("Dimension " + id + " is not available."));
        }
    }
}
