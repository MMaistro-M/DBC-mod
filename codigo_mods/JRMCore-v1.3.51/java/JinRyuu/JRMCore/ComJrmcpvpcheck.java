/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class ComJrmcpvpcheck
extends CommandBase {
    public String func_71517_b() {
        return "jrmcpvpcheck";
    }

    public int func_82362_a() {
        return 0;
    }

    public String func_71518_a(ICommandSender par1ICommandSender) {
        return "Usage: '/jrmcpvpcheck' it will tell if PVP is enabled or disabled in the current dimension";
    }

    public boolean func_71519_b(ICommandSender par1ICommandSender) {
        return true;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        boolean pvp;
        EntityPlayerMP entityplayermp = ComJrmcpvpcheck.func_71521_c((ICommandSender)par1ICommandSender);
        int dim = entityplayermp.field_71093_bK;
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        String s = JRMCoreH.rwip(server, dim + "");
        boolean bl = pvp = !s.equalsIgnoreCase("false");
        if (dim == 0) {
            this.notifyAdmins(par1ICommandSender, "PVP on Planet Earth is %s.", new Object[]{pvp ? "enabled" : "disabled", entityplayermp.func_70005_c_()});
        } else {
            this.notifyAdmins(par1ICommandSender, "PVP in dimension %s is %s.", new Object[]{dim, pvp ? "enabled" : "disabled", entityplayermp.func_70005_c_()});
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJrmcpvpcheck.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }
}

