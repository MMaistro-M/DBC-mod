/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
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
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.HashMap;
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
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ComJrmcKills
extends CommandBase {
    public static HashMap<String, Object[]> SList = new HashMap();

    public String func_71517_b() {
        return "jrmckills";
    }

    public int func_82362_a() {
        return 0;
    }

    public String func_71518_a(ICommandSender par1ICommandSender) {
        return "Usage: '/jrmckills top' to view top kills OR '/jrmckills [playerName]' to view players kill statistics.";
    }

    public boolean func_71519_b(ICommandSender par1ICommandSender) {
        return true;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        boolean alone;
        EntityPlayerMP entitycommansender;
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException(this.func_71518_a(par1ICommandSender), new Object[0]);
        }
        EntityPlayerMP entityplayermp = entitycommansender = ComJrmcKills.func_71521_c((ICommandSender)par1ICommandSender);
        boolean flagTop = false;
        boolean flagPlayer = false;
        if (par2ArrayOfStr.length > 0 && par2ArrayOfStr[0].equalsIgnoreCase("top")) {
            entityplayermp = ComJrmcKills.func_71521_c((ICommandSender)par1ICommandSender);
            flagTop = true;
        } else if (par2ArrayOfStr.length > 0) {
            entityplayermp = ComJrmcKills.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[0]);
            flagPlayer = true;
        }
        NBTTagCompound nbt = JRMCoreH.nbt((Entity)entityplayermp, "pres");
        ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.YELLOW);
        ChatStyle colorG = new ChatStyle().func_150238_a(EnumChatFormatting.GOLD);
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        int cur = server.func_71233_x();
        boolean bl = alone = cur <= 1 && flagTop;
        if (cur <= 1 || flagPlayer) {
            byte Align = JRMCoreH.getByte((EntityPlayer)entityplayermp, "jrmcAlign");
            int Karma = JRMCoreH.getInt((EntityPlayer)entityplayermp, "jrmcKarma");
            int KllCG = JRMCoreH.getInt((EntityPlayer)entityplayermp, "jrmcKillCountGood");
            int KllCN = JRMCoreH.getInt((EntityPlayer)entityplayermp, "jrmcKillCountNeut");
            int KllCE = JRMCoreH.getInt((EntityPlayer)entityplayermp, "jrmcKillCountEvil");
            int total = KllCG + KllCN + KllCE;
            if (alone) {
                entitycommansender.func_145747_a(new ChatComponentText("You are alone on the server!").func_150255_a(color));
            }
            entitycommansender.func_145747_a(new ChatComponentText(entityplayermp.getDisplayName() + "'s Player Kill counts").func_150255_a(colorG));
            entitycommansender.func_145747_a(new ChatComponentText("Total Kills: " + total).func_150255_a(color));
            entitycommansender.func_145747_a(new ChatComponentText("Good Kills: " + KllCG).func_150255_a(color));
            entitycommansender.func_145747_a(new ChatComponentText("Neutral Kills: " + KllCN).func_150255_a(color));
            entitycommansender.func_145747_a(new ChatComponentText("Evil Kills: " + KllCE).func_150255_a(color));
            entitycommansender.func_145747_a(new ChatComponentText("Bad Karma: " + Karma).func_150255_a(color));
            entitycommansender.func_145747_a(new ChatComponentText("Alignment: " + JRMCoreH.AlgnmntNms[JRMCoreH.Algnmnt(Align)]).func_150255_a(color));
            Object[] objectArray = new Object[2];
            objectArray[0] = ComJrmcKills.func_71521_c((ICommandSender)par1ICommandSender).getDisplayName();
            objectArray[1] = entityplayermp.func_70005_c_();
            this.notifyAdmins(par1ICommandSender, "%s checked kill counts of %s  ", objectArray);
        } else if (flagTop) {
            for (int pl = 0; pl < cur; ++pl) {
                EntityPlayerMP player = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                byte Align = JRMCoreH.getByte((EntityPlayer)entityplayermp, "jrmcAlign");
                int Karma = JRMCoreH.getInt((EntityPlayer)entityplayermp, "jrmcKarma");
                int KllCG = JRMCoreH.getInt((EntityPlayer)entityplayermp, "jrmcKillCountGood");
                int KllCN = JRMCoreH.getInt((EntityPlayer)entityplayermp, "jrmcKillCountNeut");
                int KllCE = JRMCoreH.getInt((EntityPlayer)entityplayermp, "jrmcKillCountEvil");
                int total = KllCG + KllCN + KllCE;
                this.setter("topBadKarma", Karma, player.func_70005_c_());
                this.setter("topTotalKills", total, player.func_70005_c_());
                this.setter("topGoodKills", KllCG, player.func_70005_c_());
                this.setter("topNeutralKills", KllCN, player.func_70005_c_());
                this.setter("topEvilKills", KllCE, player.func_70005_c_());
            }
            Object[] topBadKarma = this.getter("topBadKarma");
            Object[] topTotalKills = this.getter("topTotalKills");
            Object[] topGoodKills = this.getter("topGoodKills");
            Object[] topNeutralKills = this.getter("topNeutralKills");
            Object[] topEvilKills = this.getter("topEvilKills");
            int Karma = Integer.parseInt(topBadKarma[0] + "");
            int Total = Integer.parseInt(topTotalKills[0] + "");
            int Good = Integer.parseInt(topGoodKills[0] + "");
            int Neutral = Integer.parseInt(topNeutralKills[0] + "");
            int Evil = Integer.parseInt(topEvilKills[0] + "");
            boolean b = Karma == 0 && Total == 0 && Good == 0 && Neutral == 0 && Evil == 0;
            entitycommansender.func_145747_a(new ChatComponentText("Current Top Players").func_150255_a(colorG));
            if (Karma > 0) {
                entitycommansender.func_145747_a(new ChatComponentText("Most Wanted: " + topBadKarma[1] + " with " + topBadKarma[0] + " bad karma").func_150255_a(color));
            }
            if (Total > 0) {
                entitycommansender.func_145747_a(new ChatComponentText("Top Total Kills: " + topTotalKills[1] + " with " + topTotalKills[0] + " kill count").func_150255_a(color));
            }
            if (Good > 0) {
                entitycommansender.func_145747_a(new ChatComponentText("Top Good Kills: " + topGoodKills[1] + " with " + topGoodKills[0] + " kill count").func_150255_a(color));
            }
            if (Neutral > 0) {
                entitycommansender.func_145747_a(new ChatComponentText("Top Neutral Kills: " + topNeutralKills[1] + " with " + topNeutralKills[0] + " kill count").func_150255_a(color));
            }
            if (Evil > 0) {
                entitycommansender.func_145747_a(new ChatComponentText("Top Evil Kills: " + topEvilKills[1] + " with " + topEvilKills[0] + " kill count").func_150255_a(color));
            }
            if (b) {
                entitycommansender.func_145747_a(new ChatComponentText("There are no Top Players!").func_150255_a(color));
            }
            SList.clear();
        }
    }

    private void setter(String s, int v, String n) {
        Object[] o = SList.get(s);
        if (o != null) {
            int prevV = Integer.parseInt(o[0] + "");
            if (prevV > v) {
                Object[] sTemp = new Object[]{v, n};
                SList.put(s, sTemp);
            }
        } else {
            Object[] sTemp = new Object[]{v, n};
            SList.put(s, sTemp);
        }
    }

    private Object[] getter(String s) {
        Object[] o = SList.get(s);
        if (o.length == 2) {
            return o;
        }
        return null;
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJrmcKills.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 1 ? ComJrmcKills.func_71530_a((String[])par2ArrayOfStr, (String[])this.getListOfPlayers()) : null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 0;
    }
}

