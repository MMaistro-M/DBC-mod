/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class ComJrmcpvp
extends CommandBase {
    public String func_71517_b() {
        return "jrmcpvp";
    }

    public int func_82362_a() {
        return 2;
    }

    public String func_71518_a(ICommandSender par1ICommandSender) {
        return "Usage: '/jrmcpvp (true or false)' true will enable pvp, false will disable pvp";
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException("Usage: '/jrmcpvp (true or false)' true will enable pvp, false will disable pvp", new Object[0]);
        }
        String s = par2ArrayOfStr[0];
        boolean pvp = Boolean.parseBoolean(s);
        EntityPlayerMP entityplayermp = ComJrmcpvp.func_71521_c((ICommandSender)par1ICommandSender);
        int dim = entityplayermp.field_71093_bK;
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        JRMCoreH.wwip(server, s, dim + "", false);
        this.notifyAdmins(par1ICommandSender, "PVP in dimension %s is %s by %s", new Object[]{dim, pvp ? "enabled" : "disabled", entityplayermp.func_70005_c_()});
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJrmcpvp.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }
}

