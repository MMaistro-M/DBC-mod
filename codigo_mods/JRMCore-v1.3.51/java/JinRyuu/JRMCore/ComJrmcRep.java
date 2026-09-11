/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import java.util.HashMap;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComJrmcRep
extends CommandBase {
    public static HashMap<String, Object[]> SList = new HashMap();

    public String func_71517_b() {
        return "jrmcrepair";
    }

    public int func_82362_a() {
        return 1;
    }

    public String func_71518_a(ICommandSender par1ICommandSender) {
        return "Usage: '/jrmcrepair [playerName]'";
    }

    public boolean func_71519_b(ICommandSender par1ICommandSender) {
        return true;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        boolean n;
        String entitycommansender = "Console";
        try {
            EntityPlayerMP commansender = ComJrmcRep.func_71521_c((ICommandSender)par1ICommandSender);
            entitycommansender = commansender.func_70005_c_();
        }
        catch (Exception commansender) {
            // empty catch block
        }
        EntityPlayerMP entityplayermp = par2ArrayOfStr.length > 0 ? ComJrmcRep.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[2]) : ComJrmcRep.func_71521_c((ICommandSender)par1ICommandSender);
        NBTTagCompound nbt = JRMCoreH.nbt((Entity)entityplayermp, "pres");
        if (entityplayermp != null) {
            int i;
            for (i = 0; i < entityplayermp.field_71071_by.field_70460_b.length; ++i) {
                if (entityplayermp.field_71071_by.field_70460_b[i] == null) continue;
                entityplayermp.field_71071_by.field_70460_b[i].func_77964_b(0);
            }
            for (i = 0; i < 11; ++i) {
                if (ExtendedPlayer.get((EntityPlayer)entityplayermp).inventory.func_70301_a(i) == null) continue;
                ExtendedPlayer.get((EntityPlayer)entityplayermp).inventory.func_70301_a(i).func_77964_b(0);
            }
        }
        boolean bl = entitycommansender.equals("Console") ? JRMCoreConfig.ComHealNAC : (n = entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComHealNAS : JRMCoreConfig.ComHealNAO);
        if (n) {
            this.notifyAdmins(par1ICommandSender, "%s -> all equiped items were fixed!", new Object[]{entityplayermp.func_70005_c_()});
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJrmcRep.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 2 ? ComJrmcRep.func_71530_a((String[])par2ArrayOfStr, (String[])this.getListOfPlayers()) : null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 0;
    }
}

