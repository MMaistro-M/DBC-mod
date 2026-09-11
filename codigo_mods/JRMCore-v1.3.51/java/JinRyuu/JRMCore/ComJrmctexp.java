/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComJrmctexp
extends CommandBase {
    public String func_71517_b() {
        return "jrmctexp";
    }

    public int func_82362_a() {
        return 2;
    }

    public String func_71518_a(ICommandSender par1ICommandSender) {
        return "Usage: '/jrmctexp (slot) (amount) [playerName]'. 'slot' can go from 1-4, 'amount' is max 100 000";
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException(this.func_71518_a(par1ICommandSender), new Object[0]);
        }
        String s0 = par2ArrayOfStr[0];
        String s1 = par2ArrayOfStr[1];
        int sl = Integer.parseInt(s0);
        int sa = Integer.parseInt(s1);
        if (sl > 4) {
            sl = 4;
        }
        if (sl < 1) {
            sl = 1;
        }
        --sl;
        if (sa > 100000) {
            sa = 100000;
        }
        if (sa < 0) {
            sa = 0;
        }
        EntityPlayerMP entityplayermp = par2ArrayOfStr.length > 2 ? ComJrmctexp.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[2]) : ComJrmctexp.func_71521_c((ICommandSender)par1ICommandSender);
        String entitycommansender = "Console";
        try {
            EntityPlayerMP commansender = ComJrmctexp.func_71521_c((ICommandSender)par1ICommandSender);
            entitycommansender = commansender.func_70005_c_();
        }
        catch (Exception commansender) {
            // empty catch block
        }
        boolean n = entitycommansender.equals("Console") ? JRMCoreConfig.ComTPNAC : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComTPNAS : JRMCoreConfig.ComTPNAO);
        NBTTagCompound nbt = JRMCoreH.nbt((Entity)entityplayermp, "pres");
        boolean fl = false;
        if (nbt.func_74771_c("jrmcPwrtyp") == 1) {
            String s2 = JRMCoreH.getString((EntityPlayer)entityplayermp, JRMCoreH.techNbt[sl]);
            if (s2 != null && s2.length() > 3) {
                JRMCoreH.setString(JRMCoreH.tech_expgiv(s2, sa), (EntityPlayer)entityplayermp, JRMCoreH.techNbt[sl]);
                if (n) {
                    this.notifyAdmins(par1ICommandSender, "Tech Exp Giving %s success for %s", new Object[]{sa, entityplayermp.func_70005_c_()});
                }
            } else {
                fl = true;
            }
        } else {
            fl = true;
        }
        if (fl && n) {
            this.notifyAdmins(par1ICommandSender, "Tech Exp Giving failed for %s", new Object[]{entityplayermp.func_70005_c_()});
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJrmctexp.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 2 ? ComJrmctexp.func_71530_a((String[])par2ArrayOfStr, (String[])this.getListOfPlayers()) : null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 0;
    }
}

