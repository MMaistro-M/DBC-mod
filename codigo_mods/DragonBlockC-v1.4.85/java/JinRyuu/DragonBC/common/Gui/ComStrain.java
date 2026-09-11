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
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 */
package JinRyuu.DragonBC.common.Gui;

import JinRyuu.JRMCore.JRMCoreH;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ComStrain
extends CommandBase {
    public String func_71517_b() {
        return "dbcstrain";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        if (par2ArrayOfStr.length < 1) {
            throw new WrongUsageException("/dbcstrain remove (playerName)", new Object[0]);
        }
        EntityPlayerMP entityplayermp = par2ArrayOfStr.length > 1 ? ComStrain.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[1]) : ComStrain.func_71521_c((ICommandSender)par1ICommandSender);
        JRMCoreH.setInt(0, (EntityPlayer)entityplayermp, "jrmcStrain");
        String t = JRMCoreH.trlai("dbc", "StrainRemoved");
        ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
        entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(color));
        this.notifyAdmins(par1ICommandSender, "%s's strain has been removed.", new Object[]{entityplayermp.func_70005_c_()});
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComStrain.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/dbcstrain remove (playerName)";
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 2 ? ComStrain.func_71530_a((String[])par2ArrayOfStr, (String[])this.getListOfPlayers()) : null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 0;
    }
}

