/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.FamilyC;

import JinRyuu.JRMCore.JRMCoreH;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class FamilyCComJFCGen
extends CommandBase {
    public String func_71517_b() {
        return "jfcgender";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException("/jfcgender (male or female) [playerName] OR /jfcgender switch [playerName]", new Object[0]);
        }
        String s = par2ArrayOfStr[0];
        boolean flag = s.contentEquals("male") || s.contentEquals("Male") || s.contentEquals("MALE");
        boolean girl = s.contains("female") || s.contains("Female") || s.contains("FEMALE");
        boolean swit = s.contains("switch") || s.contains("Switch") || s.contains("SWITCH");
        EntityPlayerMP entityplayermp = par2ArrayOfStr.length > 1 ? FamilyCComJFCGen.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[1]) : FamilyCComJFCGen.func_71521_c((ICommandSender)par1ICommandSender);
        if (!entityplayermp.getEntityData().func_74764_b("PlayerPersisted")) {
            NBTTagCompound nbt = new NBTTagCompound();
            entityplayermp.getEntityData().func_74782_a("PlayerPersisted", (NBTBase)nbt);
        } else {
            NBTTagCompound nbt = entityplayermp.getEntityData().func_74775_l("PlayerPersisted");
        }
        String dns = JRMCoreH.getString((EntityPlayer)entityplayermp, "jrmcDNS");
        if (flag) {
            JRMCoreH.setString(JRMCoreH.dnsGenderSet(dns, "0"), (EntityPlayer)entityplayermp, "jrmcDNS");
            this.notifyAdmins(par1ICommandSender, "Gender Change to " + (s.contains("male") || s.contains("Male") || s.contains("MALE") ? "Male" : "Man") + " success.", new Object[]{s.contains("male") || s.contains("Male") || s.contains("MALE") ? "Male" : "Man", entityplayermp.func_70005_c_()});
        } else if (girl) {
            JRMCoreH.setString(JRMCoreH.dnsGenderSet(dns, "1"), (EntityPlayer)entityplayermp, "jrmcDNS");
            this.notifyAdmins(par1ICommandSender, "Gender Change to " + (s.contains("female") || s.contains("Female") || s.contains("FEMALE") ? "Female" : "Girl") + " success.", new Object[]{s.contains("female") || s.contains("Female") || s.contains("FEMALE") ? "Female" : "Woman", entityplayermp.func_70005_c_()});
        } else if (swit) {
            String s2 = "";
            if (JRMCoreH.dnsGender(dns) == 0) {
                s2 = "Female";
                JRMCoreH.setString(JRMCoreH.dnsGenderSet(dns, "1"), (EntityPlayer)entityplayermp, "jrmcDNS");
            } else if (JRMCoreH.dnsGender(dns) == 1) {
                s2 = "Male";
                JRMCoreH.setString(JRMCoreH.dnsGenderSet(dns, "0"), (EntityPlayer)entityplayermp, "jrmcDNS");
            }
            this.notifyAdmins(par1ICommandSender, "Gender Change to " + s2 + " was successful.", new Object[]{s2, entityplayermp.func_70005_c_()});
        } else {
            throw new WrongUsageException("Gender Change failed.", new Object[0]);
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        FamilyCComJFCGen.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/jfcgender (male or female) [playerName] OR /jfcgender switch [playerName]";
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 2 ? FamilyCComJFCGen.func_71530_a((String[])par2ArrayOfStr, (String[])this.getListOfPlayers()) : null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 0;
    }
}

