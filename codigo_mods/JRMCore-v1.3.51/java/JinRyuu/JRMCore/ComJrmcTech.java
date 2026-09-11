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
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 */
package JinRyuu.JRMCore;

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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.math.NumberUtils;

public class ComJrmcTech
extends CommandBase {
    public static final String[] MODES = new String[]{"give", "take"};
    private final String NAME = "jrmctech";

    public String func_71517_b() {
        return "jrmctech";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        int i;
        if (par2ArrayOfStr.length < 2) {
            String listdbc = "";
            for (int i2 = 0; i2 < JRMCoreH.pmdbc.length; ++i2) {
                listdbc = listdbc + ", " + JRMCoreH.pmdbc[i2][0];
            }
            String listnc = "";
            for (int i3 = 0; i3 < JRMCoreH.pmj.length; ++i3) {
                listnc = listnc + ", " + JRMCoreH.pmj[i3][0];
            }
            throw new WrongUsageException("jrmctech (give or take) (techName) [playerName]" + (JRMCoreH.DBC() ? " --> techNames for DBC can be: " + listdbc : "") + (JRMCoreH.NC() ? " --> techNames for NC can be: " + listnc : ""), new Object[0]);
        }
        String s = par2ArrayOfStr[0];
        boolean give = s.toLowerCase().contentEquals("give");
        boolean take = s.toLowerCase().contentEquals("take");
        int id = -1;
        for (int i4 = 0; i4 < JRMCoreH.DBCSkillNames.length; ++i4) {
            if (!par2ArrayOfStr[1].toLowerCase().equals(JRMCoreH.DBCSkillNames[i4].toLowerCase())) continue;
            id = i4;
            break;
        }
        EntityPlayerMP entityplayermp = par2ArrayOfStr.length > 2 ? ComJrmcTech.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[2]) : ComJrmcTech.func_71521_c((ICommandSender)par1ICommandSender);
        NBTTagCompound nbt = JRMCoreH.nbt((Entity)entityplayermp, "pres");
        EntityPlayerMP p = entityplayermp;
        byte pwrtyp = JRMCoreH.getByte((EntityPlayer)p, "jrmcPwrtyp");
        String[][] PMA = null;
        int b = 0;
        PMA = pwrtyp == 2 ? JRMCoreH.pmj : JRMCoreH.pmdbc;
        int id1 = -1;
        for (i = 0; i < PMA.length; ++i) {
            if (!par2ArrayOfStr[1].toLowerCase().equals(PMA[i][0].toLowerCase())) continue;
            id1 = i;
            break;
        }
        if (id1 >= 0) {
            if (take) {
                for (i = 4; i < 8; ++i) {
                    String t = JRMCoreH.getString((EntityPlayer)p, JRMCoreH.techNbt[i]);
                    if (!NumberUtils.isNumber(t) || Integer.parseInt(t) != id1) continue;
                    JRMCoreH.setString(" ", (EntityPlayer)p, JRMCoreH.techNbt[i]);
                    String skl = PMA[id1][0];
                    String t1 = "Skill " + skl + " removed";
                    ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                    entityplayermp.func_145747_a(new ChatComponentText(t1).func_150255_a(color));
                    this.notifyAdmins(par1ICommandSender, "%s's tech %s has been removed!", new Object[]{entityplayermp.func_70005_c_(), skl});
                    break;
                }
            }
            if (give) {
                ChatStyle color;
                int s2 = id1;
                boolean may = false;
                for (int i5 = 0; i5 < 4; ++i5) {
                    String s1 = JRMCoreH.getString((EntityPlayer)p, JRMCoreH.techNbt[4 + i5]);
                    if (!NumberUtils.isNumber(s1) && !s1.contains(",")) {
                        b = (byte)(4 + i5);
                        may = true;
                        break;
                    }
                    may = false;
                }
                if (may) {
                    JRMCoreH.setString("" + s2, (EntityPlayer)p, JRMCoreH.techNbt[b]);
                    String t = JRMCoreH.trlai("jrmc", "techadded");
                    color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                    entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[]{PMA[id1][0]}).func_150255_a(color));
                    this.notifyAdmins(par1ICommandSender, "%s has received tech %s", new Object[]{entityplayermp.func_70005_c_(), PMA[id1][0]});
                } else {
                    String t = JRMCoreH.trlai("jrmc", "noslotleft");
                    color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
                    entityplayermp.func_145747_a(new ChatComponentTranslation(t, new Object[]{PMA[id1][0]}).func_150255_a(color));
                    this.notifyAdmins(par1ICommandSender, "%s has received skill %s", new Object[]{entityplayermp.func_70005_c_(), PMA[id1][0]});
                }
            }
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJrmcTech.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/dbcsaga reset (playerName)";
    }

    public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
        int length = stringArray.length;
        switch (length) {
            case 1: {
                return ComJrmcTech.func_71530_a((String[])stringArray, (String[])MODES);
            }
            case 2: {
                int i;
                String[] listOfTechs = new String[JRMCoreH.pmdbc.length + JRMCoreH.pmj.length];
                int id = 0;
                for (i = 0; i < JRMCoreH.pmdbc.length; ++i) {
                    listOfTechs[id] = JRMCoreH.pmdbc[i][0];
                    ++id;
                }
                for (i = 0; i < JRMCoreH.pmj.length; ++i) {
                    listOfTechs[id] = JRMCoreH.pmj[i][0];
                    ++id;
                }
                return ComJrmcTech.func_71530_a((String[])stringArray, (String[])listOfTechs);
            }
            case 3: {
                return ComJrmcTech.func_71530_a((String[])stringArray, (String[])this.getListOfPlayers());
            }
        }
        return null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 2;
    }
}

