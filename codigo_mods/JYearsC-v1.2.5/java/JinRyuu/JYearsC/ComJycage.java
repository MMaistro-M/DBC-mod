/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.JYearsC;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComJycage
extends CommandBase {
    public String func_71517_b() {
        return "jycage";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/jycage (daysLived) [playerName] OR /jycage (addYears)Y [playerName]";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        NBTTagCompound nbt;
        int i;
        boolean flag1;
        boolean flag;
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException("/jycage (daysLived) [playerName] OR /jycage (addYears)Y [playerName]", new Object[0]);
        }
        String s = par2ArrayOfStr[0];
        boolean bl = flag = s.endsWith("y") || s.endsWith("Y");
        if (flag && s.length() > 1) {
            s = s.substring(0, s.length() - 1);
        }
        boolean bl2 = flag1 = (i = ComJycage.func_71526_a((ICommandSender)par1ICommandSender, (String)s)) < 0;
        if (flag1) {
            i *= -1;
        }
        EntityPlayerMP entityplayermp = par2ArrayOfStr.length > 1 ? ComJycage.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[1]) : ComJycage.func_71521_c((ICommandSender)par1ICommandSender);
        if (!entityplayermp.getEntityData().func_74764_b("PlayerPersisted")) {
            nbt = new NBTTagCompound();
            entityplayermp.getEntityData().func_74782_a("PlayerPersisted", (NBTBase)nbt);
        } else {
            nbt = entityplayermp.getEntityData().func_74775_l("PlayerPersisted");
        }
        if (flag) {
            if (flag1) {
                nbt.func_74776_a("JRYCAge", nbt.func_74760_g("JRYCAge") - (float)(i * 46));
                this.notifyAdmins(par1ICommandSender, "Age (Year) Subtracted success", new Object[]{i, entityplayermp.getDisplayName()});
            } else {
                nbt.func_74776_a("JRYCAge", nbt.func_74760_g("JRYCAge") + (float)(i * 46));
                this.notifyAdmins(par1ICommandSender, "Age (Year) Added success", new Object[]{i, entityplayermp.getDisplayName()});
            }
        } else {
            if (flag1) {
                throw new WrongUsageException("Age Setting failure", new Object[0]);
            }
            nbt.func_74776_a("JRYCAge", (float)i);
            this.notifyAdmins(par1ICommandSender, "Age (Days) Set success", new Object[]{i, entityplayermp.getDisplayName()});
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJycage.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 2 ? ComJycage.func_71530_a((String[])par2ArrayOfStr, (String[])this.getListOfPlayers()) : null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 0;
    }
}

