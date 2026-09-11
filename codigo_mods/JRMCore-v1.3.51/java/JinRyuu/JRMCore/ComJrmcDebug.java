/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreH;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class ComJrmcDebug
extends CommandBase {
    public String func_71517_b() {
        return "jrmcdebug";
    }

    public int func_82362_a() {
        return 2;
    }

    public String func_71518_a(ICommandSender par1ICommandSender) {
        return "This command is for development and testing purposes only, Don't use it.";
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException(this.func_71518_a(par1ICommandSender), new Object[0]);
        }
        String s = par2ArrayOfStr[0];
        boolean i = Boolean.parseBoolean(s);
        EntityPlayerMP entityplayermp = par2ArrayOfStr.length > 1 ? ComJrmcDebug.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[1]) : ComJrmcDebug.func_71521_c((ICommandSender)par1ICommandSender);
        JRMCoreH.difp = i ? entityplayermp.func_70005_c_() : "";
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJrmcDebug.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 2 ? ComJrmcDebug.func_71530_a((String[])par2ArrayOfStr, (String[])this.getListOfPlayers()) : null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 0;
    }
}

