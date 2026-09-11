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

public class ComJrmca
extends CommandBase {
    private final String[] MODES = new String[]{"set", "add"};
    private final String[] ATTRIBUTES_LONG = new String[]{"alignment", "strength", "dexterity", "constitution", "willpower", "mind", "spirit", "all"};
    public static final String[] ATTRIBUTES_SHORT = new String[]{"alig", "str", "dex", "con", "wil", "mnd", "spi", "all"};
    private final String[] VALUES = new String[]{"100", "max", "reset", "-100"};

    public String func_71517_b() {
        return "jrmca";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/jrmca (Set or Add) (Attribute) (Amount) [playerName]   ,where Attribute can be Alignment, Strength, Dexterity, Constitution, Willpower, Mind, Spirit or use /dbca Set All Max or Reset .";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender commandSender, String[] stringArray) {
        int i1;
        int cur;
        int i2;
        int s;
        int cur2;
        if (stringArray.length <= 0) {
            throw new WrongUsageException(this.func_71518_a(commandSender), new Object[0]);
        }
        String s0 = stringArray[0].toLowerCase();
        String s1 = stringArray[1];
        String s2 = stringArray[2];
        boolean set = s0.contains("set");
        boolean add = s0.contains("add");
        boolean alignment = s1.toLowerCase().contains("alignment") || s1.equalsIgnoreCase("alig") || s1.equalsIgnoreCase("align");
        boolean stre = s1.toLowerCase().contains("strength") || s1.equalsIgnoreCase("str");
        boolean dext = s1.toLowerCase().contains("dexterity") || s1.equalsIgnoreCase("dex");
        boolean cons = s1.toLowerCase().contains("constitution") || s1.equalsIgnoreCase("con");
        boolean will = s1.toLowerCase().contains("willpower") || s1.equalsIgnoreCase("wil");
        boolean mind = s1.toLowerCase().contains("mind") || s1.equalsIgnoreCase("mnd");
        boolean conc = s1.toLowerCase().contains("spirit") || s1.equalsIgnoreCase("spi");
        boolean all = s1.toLowerCase().contains("all");
        boolean max = s2.toLowerCase().contains("max");
        boolean reset = s2.toLowerCase().contains("reset");
        boolean num = false;
        if (!max && !reset) {
            num = true;
        }
        boolean amount = s2.length() > 0;
        int i = 1;
        try {
            i = ComJrmca.func_71526_a((ICommandSender)commandSender, (String)s2);
        }
        catch (Exception exception) {
            i = 1;
        }
        int am = ComJrmca.NKfw4V();
        if (set && i < 1) {
            i = 1;
        }
        if (i > am) {
            i = am;
        }
        EntityPlayerMP entityplayermp = stringArray.length > 3 ? ComJrmca.func_82359_c((ICommandSender)commandSender, (String)stringArray[3]) : ComJrmca.func_71521_c((ICommandSender)commandSender);
        String entitycommansender = "Console";
        try {
            EntityPlayerMP commansender = ComJrmca.func_71521_c((ICommandSender)commandSender);
            entitycommansender = commansender.func_70005_c_();
        }
        catch (Exception commansender) {
            // empty catch block
        }
        boolean n = entitycommansender.equals("Console") ? JRMCoreConfig.ComANAC : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComANAS : JRMCoreConfig.ComANAO);
        int str = JRMCoreH.getInt((EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[0]);
        int dex = JRMCoreH.getInt((EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[1]);
        int cns = JRMCoreH.getInt((EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[2]);
        int wil = JRMCoreH.getInt((EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[3]);
        int min = JRMCoreH.getInt((EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[4]);
        int cnc = JRMCoreH.getInt((EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[5]);
        String att = "";
        String attnam = "";
        if (stre) {
            att = JRMCoreH.AttrbtNbtI[0];
            cur2 = str;
            attnam = "Strength";
            int n2 = set && i > am ? am : (i = add && cur2 + i > am ? am - cur2 : i);
        }
        if (dext) {
            att = JRMCoreH.AttrbtNbtI[1];
            cur2 = dex;
            attnam = "Dexterity";
            int n3 = set && i > am ? am : (i = add && cur2 + i > am ? am - cur2 : i);
        }
        if (cons) {
            att = JRMCoreH.AttrbtNbtI[2];
            cur2 = cns;
            attnam = "Constitution";
            int n4 = set && i > am ? am : (i = add && cur2 + i > am ? am - cur2 : i);
        }
        if (will) {
            att = JRMCoreH.AttrbtNbtI[3];
            cur2 = wil;
            attnam = "Willpower";
            int n5 = set && i > am ? am : (i = add && cur2 + i > am ? am - cur2 : i);
        }
        if (mind) {
            att = JRMCoreH.AttrbtNbtI[4];
            cur2 = min;
            attnam = "Mind";
            int n6 = set && i > am ? am : (i = add && cur2 + i > am ? am - cur2 : i);
        }
        if (conc) {
            att = JRMCoreH.AttrbtNbtI[5];
            cur2 = cnc;
            attnam = "Spirit";
            i = set && i > am ? am : (add && cur2 + i > am ? am - cur2 : i);
        }
        byte pwr = JRMCoreH.getByte((EntityPlayer)entityplayermp, "jrmcPwrtyp");
        byte rce = JRMCoreH.getByte((EntityPlayer)entityplayermp, "jrmcRace");
        byte cls = JRMCoreH.getByte((EntityPlayer)entityplayermp, "jrmcClass");
        if ((stre || dext || cons || will || mind || conc) && amount) {
            int j = 0;
            if (dext) {
                j = 1;
            }
            if (cons) {
                j = 2;
            }
            if (will) {
                j = 3;
            }
            if (mind) {
                j = 4;
            }
            if (conc) {
                j = 5;
            }
            i2 = i > (s = JRMCoreH.attributeStart(pwr, j, rce, cls)) ? i : s;
            cur = JRMCoreH.getInt((EntityPlayer)entityplayermp, att);
            JRMCoreH.setInt(max ? am : (add ? (cur + i < 1 ? 1 : cur + i) : (set ? (i2 < 1 ? 1 : i2) : 1)), (EntityPlayer)entityplayermp, att);
            if (n) {
                this.notifyAdmins(commandSender, "Attribute " + (add && cur + i >= 1 ? "Adding " + i + " to " + attnam : (set ? attnam + " was set to " + i : attnam + " was set to " + (max ? am : 1))) + " Successfully", new Object[]{i, entityplayermp.func_70005_c_()});
            }
        } else if (alignment && amount) {
            byte cur3 = JRMCoreH.getByte((EntityPlayer)entityplayermp, "jrmcAlign");
            if (alignment) {
                att = "jrmcAlign";
                attnam = "Alignment";
                int n7 = set && i > 100 ? 100 : (i = add && cur3 + i > 100 ? 100 - cur3 : i);
            }
            JRMCoreH.setByte(max ? am : (add ? (cur3 + i < 1 ? 1 : cur3 + i) : (set ? (i < 1 ? 1 : i) : 1)), (EntityPlayer)entityplayermp, att);
            if (n) {
                this.notifyAdmins(commandSender, "Attribute " + (add && cur3 + i >= 1 ? "Adding " + i + " to " + attnam : (set ? attnam + " was set to " + i : attnam + " was set to " + (max ? 100 : 1))) + " Successfully", new Object[]{i, entityplayermp.func_70005_c_()});
            }
        } else if (add && all && num) {
            for (i1 = 0; i1 < JRMCoreH.AttrbtNbtI.length; ++i1) {
                s = JRMCoreH.attributeStart(pwr, i1, rce, cls);
                i2 = i > s ? i : s;
                cur = JRMCoreH.getInt((EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[i1]);
                int newatt = cur + i < 1 ? 1 : cur + i;
                JRMCoreH.setInt(newatt > am ? am : newatt, (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[i1]);
            }
            if (n) {
                this.notifyAdmins(commandSender, "Attribute Everything for " + entityplayermp.func_70005_c_() + " has received " + i + " Successfully", new Object[]{i, entityplayermp.func_70005_c_()});
            }
        } else if (set && all && num) {
            for (i1 = 0; i1 < JRMCoreH.AttrbtNbtI.length; ++i1) {
                s = JRMCoreH.attributeStart(pwr, i1, rce, cls);
                i2 = i > s ? i : s;
                JRMCoreH.setInt(i2, (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[i1]);
            }
            if (n) {
                this.notifyAdmins(commandSender, "Attribute Everything for " + entityplayermp.func_70005_c_() + " was set to " + i + " Successfully", new Object[]{i, entityplayermp.func_70005_c_()});
            }
        } else if (set && all && (max || reset)) {
            if (max) {
                JRMCoreH.setInt(ComJrmca.NKfw4V(), (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[0]);
                JRMCoreH.setInt(ComJrmca.NKfw4V(), (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[1]);
                JRMCoreH.setInt(ComJrmca.NKfw4V(), (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[2]);
                JRMCoreH.setInt(ComJrmca.NKfw4V(), (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[3]);
                JRMCoreH.setInt(ComJrmca.NKfw4V(), (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[4]);
                JRMCoreH.setInt(ComJrmca.NKfw4V(), (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[5]);
            }
            if (reset) {
                int STR = JRMCoreH.attributeStart(pwr, 0, rce, cls);
                int DEX = JRMCoreH.attributeStart(pwr, 1, rce, cls);
                int CON = JRMCoreH.attributeStart(pwr, 2, rce, cls);
                int WIL = JRMCoreH.attributeStart(pwr, 3, rce, cls);
                int MND = JRMCoreH.attributeStart(pwr, 4, rce, cls);
                int SPI = JRMCoreH.attributeStart(pwr, 5, rce, cls);
                JRMCoreH.setInt(STR, (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[0]);
                JRMCoreH.setInt(DEX, (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[1]);
                JRMCoreH.setInt(CON, (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[2]);
                JRMCoreH.setInt(WIL, (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[3]);
                JRMCoreH.setInt(MND, (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[4]);
                JRMCoreH.setInt(SPI, (EntityPlayer)entityplayermp, JRMCoreH.AttrbtNbtI[5]);
            }
            if (n) {
                this.notifyAdmins(commandSender, "Attribute Everything for " + entityplayermp.func_70005_c_() + " has been " + (max ? "Maxed" : (reset ? "Reset" : "failed")), new Object[]{i, entityplayermp.func_70005_c_()});
            }
        } else {
            throw new WrongUsageException("Attribute Change Failure", new Object[0]);
        }
        EntityPlayerMP player = entityplayermp;
        int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts((EntityPlayer)player);
        int maxBody = JRMCoreH.stat((Entity)player, 2, pwr, 2, PlyrAttrbts[2], rce, cls, 0.0f);
        int maxEnergy = JRMCoreH.stat((Entity)player, 5, pwr, 5, PlyrAttrbts[5], rce, cls, JRMCoreH.SklLvl_KiBs((EntityPlayer)player, (int)pwr));
        int maxStam = JRMCoreH.stat((Entity)player, 2, pwr, 3, PlyrAttrbts[2], rce, cls, 0.0f);
        JRMCoreH.setInt(maxBody, (EntityPlayer)player, "jrmcBdy");
        JRMCoreH.setInt(maxEnergy, (EntityPlayer)player, "jrmcEnrgy");
        JRMCoreH.setInt(maxStam, (EntityPlayer)player, "jrmcStamina");
    }

    int calc1(int j, int l) {
        int i = 0;
        for (int k = 0; k < j; ++k) {
            i += i + l;
        }
        return i;
    }

    int calc2(int j, int l) {
        int i = 0;
        for (int k = 0; k < j / l; ++k) {
            int z = k * l / 50 <= 0 ? 1 : k * l / 50;
            i += z;
        }
        return i;
    }

    private NBTTagCompound nbt(EntityPlayer p, String s) {
        return JRMCoreH.nbt((Entity)p, s);
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJrmca.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public static int MCajr(String l) {
        String w = "0123456789ABCDEF";
        l = l.toUpperCase();
        int a = 0;
        for (int i = 0; i < l.length(); ++i) {
            char c = l.charAt(i);
            int d = w.indexOf(c);
            a = 16 * a + d;
        }
        return a;
    }

    public static int NKfw4V() {
        int b = JRMCoreConfig.tmx;
        String r = "64";
        String k = "3B9ACA00";
        return b > ComJrmca.MCajr(k) ? ComJrmca.MCajr(k) : (b < ComJrmca.MCajr(r) ? 0 : b);
    }

    public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
        int length = stringArray.length;
        switch (length) {
            case 1: {
                return ComJrmca.func_71530_a((String[])stringArray, (String[])this.MODES);
            }
            case 2: {
                return ComJrmca.func_71530_a((String[])stringArray, (String[])this.ATTRIBUTES_LONG);
            }
            case 3: {
                return ComJrmca.func_71530_a((String[])stringArray, (String[])this.VALUES);
            }
            case 4: {
                return ComJrmca.func_71530_a((String[])stringArray, (String[])this.getListOfPlayers());
            }
        }
        return null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 3;
    }
}

