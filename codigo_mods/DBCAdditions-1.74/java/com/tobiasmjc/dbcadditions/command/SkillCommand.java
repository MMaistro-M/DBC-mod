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

import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SkillCommand
extends CommandBase {
    public String func_71517_b() {
        return "dbcaskill";
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return "/dbcaskill <player> <skill> <level>";
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
        EntityPlayerMP player = SkillCommand.func_82359_c((ICommandSender)sender, (String)args[0]);
        if (player == null) {
            return;
        }
        DBCAPlayer dbcaPlayer = new DBCAPlayer((EntityPlayer)player);
        String name = args[1];
        DBCASkill skill = DBCASkills.getSkill(name);
        if (skill == null) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("Skill " + args[1] + " is not valid."));
            return;
        }
        int level = SkillCommand.func_71526_a((ICommandSender)player, (String)args[2]);
        if (level > 0) {
            if (!dbcaPlayer.hasSkill(skill)) {
                if (skill.race(dbcaPlayer.getRace(), (byte)dbcaPlayer.DBARace)) {
                    dbcaPlayer.learnSkill(skill);
                    String msg = "Learned skill " + skill.getName();
                    msg = level > 1 ? msg + " with level " + level + "!" : msg + "!";
                    sender.func_145747_a((IChatComponent)new ChatComponentText(msg));
                } else {
                    sender.func_145747_a((IChatComponent)new ChatComponentText("Your race can't use this skill!"));
                }
            } else {
                sender.func_145747_a((IChatComponent)new ChatComponentText("Skill " + skill.getName() + " level is now " + level + "!"));
            }
        } else {
            dbcaPlayer.removeSkill(skill);
            sender.func_145747_a((IChatComponent)new ChatComponentText("Removed skill " + skill.getName() + "!"));
        }
        dbcaPlayer.setSkillLevel(skill, level);
    }
}

