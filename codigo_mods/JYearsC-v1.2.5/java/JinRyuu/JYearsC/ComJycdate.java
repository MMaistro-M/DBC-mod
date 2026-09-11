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
 *  net.minecraft.world.WorldServer
 */
package JinRyuu.JYearsC;

import JinRyuu.JRMCore.JYearsCH;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class ComJycdate
extends CommandBase {
    public String func_71517_b() {
        return "jycdate";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/jycdate (amount)(Y or M or D) (amount)(Y or M or D) (amount)(Y or M or D)  Example: /jycdate 2D 3M 5Y";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        int i2;
        boolean flag12;
        boolean flagd2;
        String s2;
        int i1;
        boolean flag11;
        boolean flagd1;
        String s1;
        int i;
        boolean flag1;
        boolean flagd;
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException("/jycdate (amount)(Y or M or D) (amount)(Y or M or D) (amount)(Y or M or D)", new Object[0]);
        }
        if (par2ArrayOfStr.length < 3) {
            throw new WrongUsageException("/jycdate (amount)(Y or M or D) (amount)(Y or M or D) (amount)(Y or M or D)  Example: /jycdate 2D 3M 5Y", new Object[0]);
        }
        String s = par2ArrayOfStr[0];
        boolean flag = s.endsWith("y") || s.endsWith("Y");
        boolean flagm = s.endsWith("m") || s.endsWith("M");
        boolean bl = flagd = s.endsWith("d") || s.endsWith("D");
        if ((flag || flagm || flagd) && s.length() > 1) {
            s = s.substring(0, s.length() - 1);
        }
        boolean bl2 = flag1 = (i = ComJycdate.func_71526_a((ICommandSender)par1ICommandSender, (String)s)) < 0;
        if (flag1) {
            i *= -1;
        }
        boolean flag2 = (s1 = par2ArrayOfStr[1]).endsWith("y") || s1.endsWith("Y");
        boolean flagm1 = s1.endsWith("m") || s1.endsWith("M");
        boolean bl3 = flagd1 = s1.endsWith("d") || s1.endsWith("D");
        if ((flag2 || flagm1 || flagd1) && s1.length() > 1) {
            s1 = s1.substring(0, s1.length() - 1);
        }
        boolean bl4 = flag11 = (i1 = ComJycdate.func_71526_a((ICommandSender)par1ICommandSender, (String)s1)) < 0;
        if (flag11) {
            i1 *= -1;
        }
        boolean flag3 = (s2 = par2ArrayOfStr[2]).endsWith("y") || s2.endsWith("Y");
        boolean flagm2 = s2.endsWith("m") || s2.endsWith("M");
        boolean bl5 = flagd2 = s2.endsWith("d") || s2.endsWith("D");
        if ((flag3 || flagm2 || flagd2) && s2.length() > 1) {
            s2 = s2.substring(0, s2.length() - 1);
        }
        boolean bl6 = flag12 = (i2 = ComJycdate.func_71526_a((ICommandSender)par1ICommandSender, (String)s2)) < 0;
        if (flag12) {
            i2 *= -1;
        }
        EntityPlayerMP entityplayermp = ComJycdate.func_71521_c((ICommandSender)par1ICommandSender);
        WorldServer dim0 = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(0);
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        if (flag1 || flag11 || flag12) {
            throw new WrongUsageException("Nothing can be negative", new Object[0]);
        }
        if (flag || flag2 || flag3) {
            if (i >= 1000000 || i1 >= 1000000 || i2 >= 1000000) {
                throw new WrongUsageException("Year can't be more then a million!", new Object[0]);
            }
            if (flag) {
                JYearsCH.wcd(server, i + "", "y", false);
                i = 1;
            }
            if (flag2) {
                JYearsCH.wcd(server, i1 + "", "y", false);
                i1 = 1;
            }
            if (flag3) {
                JYearsCH.wcd(server, i2 + "", "y", false);
                i2 = 1;
            }
        } else {
            throw new WrongUsageException("The date Year format is not as expected.", new Object[0]);
        }
        this.notifyAdmins(par1ICommandSender, "Year Set success", new Object[]{entityplayermp.getDisplayName()});
        if (i < 1 || i1 < 1 || i2 < 1) {
            throw new WrongUsageException("Dont add 0 (null) or negative numbers!", new Object[0]);
        }
        if (flagd1 || flagd2 || flagd) {
            if (i > 13 || i < 1 || i1 > 13 || i1 < 1 || i2 > 13 || i2 < 1) {
                throw new WrongUsageException("Days can only be from 1 to 13, depending on the month!", new Object[0]);
            }
            if (flagd) {
                JYearsCH.wcd(server, i - 1 + "", "d", false);
                i = 1;
            }
            if (flagd1) {
                JYearsCH.wcd(server, i1 - 1 + "", "d", false);
                i1 = 1;
            }
            if (flagd2) {
                JYearsCH.wcd(server, i2 - 1 + "", "d", false);
                i2 = 1;
            }
        } else {
            throw new WrongUsageException("The date Day format is not as expected.", new Object[0]);
        }
        this.notifyAdmins(par1ICommandSender, "Day Set success", new Object[]{entityplayermp.getDisplayName()});
        if (flagm1 || flagm2 || flagm) {
            if (i > 4 || i < 1 || i1 > 4 || i1 < 1 || i2 > 4 || i2 < 1) {
                throw new WrongUsageException("Month can only be from 1 to 4!", new Object[0]);
            }
            if (flagm) {
                JYearsCH.wcd(server, i - 1 + "", "m", false);
            }
            if (flagm1) {
                JYearsCH.wcd(server, i1 - 1 + "", "m", false);
            }
            if (flagm2) {
                JYearsCH.wcd(server, i2 - 1 + "", "m", false);
            }
        } else {
            throw new WrongUsageException("The date Month format is not as expected.", new Object[0]);
        }
        this.notifyAdmins(par1ICommandSender, "Month Set success", new Object[]{entityplayermp.getDisplayName()});
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJycdate.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }
}

