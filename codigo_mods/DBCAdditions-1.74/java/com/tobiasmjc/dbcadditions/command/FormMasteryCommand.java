/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package com.tobiasmjc.dbcadditions.command;

import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAFormMastery;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class FormMasteryCommand
extends CommandBase {
    public String func_71517_b() {
        return "dbcaformmastery";
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return "/dbcaformmastery <player> <form name> <mastery>";
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
        if (args.length != 3) {
            sender.func_145747_a((IChatComponent)new ChatComponentText(this.func_71518_a(sender)));
            return;
        }
        EntityPlayerMP player = FormMasteryCommand.func_82359_c((ICommandSender)sender, (String)args[0]);
        if (player == null) {
            return;
        }
        DBCAForm form = null;
        for (DBCAForm f : DBCAForms.FORMS) {
            if (!f.getName().equalsIgnoreCase(args[1])) continue;
            form = f;
        }
        if (form == null) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("Form " + args[1] + " is not valid."));
            return;
        }
        DBCAFormMastery mastery = form.getMastery((EntityPlayer)player);
        int masteryLevel = (int)Math.min((double)FormMasteryCommand.func_71526_a((ICommandSender)sender, (String)args[2]), mastery.MasteryData.maxLevel);
        mastery.setFormMastery(masteryLevel);
        sender.func_145747_a((IChatComponent)new ChatComponentText("Form " + args[1] + " Mastery changed to " + masteryLevel + "!"));
    }
}

