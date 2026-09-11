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
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.FamilyC.FamilyCConfig;
import JinRyuu.JRMCore.FamilyCH;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.Locale;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class FamilyCComJFCsoc
extends CommandBase {
    private static int dnsRaceSlcted;
    private static int dnsGenderSlcted;
    private static int dnsHairSlcted;
    private static int dnsHair2Slcted;
    private static int dnsColorSlcted;
    private static int dnsBreastSizeSlcted;
    private static int dnsBodyTypeSlcted;
    private static int dnsBodyColMainSlcted;
    private static int dnsBodyColSub1Slcted;
    private static int dnsBodyColSub2Slcted;
    private static int dnsBodyColSub3Slcted;
    private static int dnsFaceNoseSlcted;
    private static int dnsFaceMouthSlcted;
    private static int dnsEyesSlcted;
    private static int dnsEyeCol1Slcted;
    private static int dnsEyeCol2Slcted;
    private static String dns;

    public String func_71517_b() {
        return "jfc";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        EntityPlayerMP entityplayermp;
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException("/jfc spawn child [childName]", new Object[0]);
        }
        String s = par2ArrayOfStr[0];
        boolean flag = s.contains("single") || s.contains("Single") || s.contains("SINGLE");
        boolean par = s.contains("noParent") || s.contains("noparent") || s.contains("NOPARENT") || s.contains("Noparent") || s.contains("NoParent");
        boolean chi = s.contains("noChild") || s.contains("nochild") || s.contains("NOCHILD") || s.contains("Nochild") || s.contains("NoChild");
        boolean spwn = s.contains("spawn") || s.contains("SPAWN") || s.contains("Spawn");
        boolean rem = s.toLowerCase().contains("remove");
        if (spwn && par2ArrayOfStr.length > 1) {
            String name;
            boolean child;
            String s1 = par2ArrayOfStr[1];
            boolean bl = child = s1.contains("child") || s.contains("Child") || s.contains("CHILD");
            if (child) {
                String[] p2d;
                entityplayermp = FamilyCComJFCsoc.func_71521_c((ICommandSender)par1ICommandSender);
                name = par2ArrayOfStr.length > 2 ? par2ArrayOfStr[2] : FamilyCH.namGen();
                Random ran = new Random();
                int rid = ran.nextInt(5);
                int r = rid;
                s1 = s1.toLowerCase(Locale.ENGLISH);
                r = JRMCoreH.DBC() ? (s1.contains("human") ? 0 : (s1.contains("saiyan") ? 1 : (s1.contains("halfsaiyan") ? 2 : (s1.contains("namekian") ? 3 : (s1.contains("arcosian") ? 4 : (s1.contains("majin") ? 5 : r)))))) : 0;
                dnsRaceSlcted = r;
                dnsGenderSlcted = ran.nextInt(2);
                dnsHairSlcted = 12;
                dnsHair2Slcted = 0;
                dnsColorSlcted = ran.nextInt(0xFFFF28);
                dnsBreastSizeSlcted = dnsGenderSlcted == 1 ? ran.nextInt(9) : 0;
                dnsBodyTypeSlcted = ran.nextInt(JRMCoreH.customSknLimits[r][0]);
                int rid2 = ran.nextInt(JRMCoreH.customSknLimitsBCP[r]);
                int cls = JRMCoreH.defbodycols[rid2][r].length;
                rid = ran.nextInt(5);
                int n = cls < 1 ? 0 : (dnsBodyColMainSlcted = rid < 4 ? JRMCoreH.defbodycols[ran.nextInt(JRMCoreH.customSknLimitsBCP[r])][r][0] : ran.nextInt(0xFFFF28));
                if (JRMCoreH.isRaceMajin(r)) {
                    dnsColorSlcted = dnsBodyColMainSlcted;
                }
                rid = ran.nextInt(5);
                dnsBodyColSub1Slcted = cls < 2 ? 0 : (rid < 4 ? JRMCoreH.defbodycols[ran.nextInt(JRMCoreH.customSknLimitsBCP[r])][r][1] : ran.nextInt(0xFFFF28));
                rid = ran.nextInt(5);
                dnsBodyColSub2Slcted = cls < 3 ? 0 : (rid < 4 ? JRMCoreH.defbodycols[ran.nextInt(JRMCoreH.customSknLimitsBCP[r])][r][2] : ran.nextInt(0xFFFF28));
                rid = ran.nextInt(5);
                dnsBodyColSub3Slcted = cls < 4 ? 0 : (rid < 4 ? JRMCoreH.defbodycols[ran.nextInt(JRMCoreH.customSknLimitsBCP[r])][r][3] : ran.nextInt(0xFFFF28));
                dnsFaceNoseSlcted = ran.nextInt(JRMCoreH.customSknLimits[r][2]);
                dnsFaceMouthSlcted = ran.nextInt(JRMCoreH.customSknLimits[r][3]);
                dnsEyesSlcted = ran.nextInt(JRMCoreH.customSknLimits[r][4]);
                rid = ran.nextInt(2);
                rid2 = ran.nextInt(5);
                dnsEyeCol1Slcted = rid == 0 ? JRMCoreH.defeyecols[ran.nextInt(JRMCoreH.defeyecols.length)][r] : ran.nextInt(0xFFFF28);
                rid = ran.nextInt(2);
                dnsEyeCol2Slcted = rid2 != 0 ? dnsEyeCol1Slcted : (rid == 0 ? JRMCoreH.defeyecols[ran.nextInt(JRMCoreH.defeyecols.length)][r] : ran.nextInt(0xFFFF28));
                FamilyCComJFCsoc.setdns();
                String dnsHdef = JRMCoreH.defHairPrsts[ran.nextInt(JRMCoreH.defHairPrsts.length)];
                String dnsHc = dnsHairSlcted != 12 ? "0" : dnsHdef;
                MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                boolean bool = true;
                String p2 = FamilyCH.rpfd(server, entityplayermp.func_70005_c_());
                if (p2.contains(";") && ((p2d = p2.split(";")).length >= FamilyCConfig.mc || FamilyCConfig.dcr)) {
                    bool = false;
                    if (FamilyCConfig.dcr) {
                        this.notifyAdmins(par1ICommandSender, "Children are disabled!", new Object[0]);
                    } else {
                        this.notifyAdmins(par1ICommandSender, "You already reached your children limit of %s", new Object[]{FamilyCConfig.mc, entityplayermp.func_70005_c_()});
                    }
                }
                while (bool) {
                    ran = new Random();
                    rid = ran.nextInt(1000000);
                    if (FamilyCH.rcfd(server, rid + "").length() >= 2) continue;
                    FamilyCH.wcfd(server, entityplayermp.func_70005_c_() + ":" + entityplayermp.func_70005_c_() + ":" + name, rid, false);
                    String pm = FamilyCH.rpfd(server, entityplayermp.func_70005_c_());
                    pm = (pm.contains(";") || pm.length() > 2 ? pm + ";" : "") + rid + ":" + entityplayermp.func_70005_c_();
                    FamilyCH.wpfd(server, pm, entityplayermp.func_70005_c_(), false);
                    EntityNPC c = new EntityNPC(entityplayermp.field_70170_p, dns, entityplayermp.func_70005_c_(), entityplayermp.func_70005_c_(), name, rid, dnsHc);
                    c.func_70012_b(entityplayermp.field_70165_t, entityplayermp.field_70163_u, entityplayermp.field_70161_v, 0.0f, 0.0f);
                    c.setCnam((byte)1);
                    c.setNPCAge(0.5f);
                    entityplayermp.field_70170_p.func_72838_d((Entity)c);
                    JRMCoreH.setString("b", (EntityPlayer)entityplayermp, FamilyCH.prID);
                    this.notifyAdmins(par1ICommandSender, "Child Spawned named %s", new Object[]{name, entityplayermp.func_70005_c_()});
                    bool = false;
                }
            } else {
                entityplayermp = FamilyCComJFCsoc.func_82359_c((ICommandSender)par1ICommandSender, (String)s1);
            }
            name = "";
        } else if (rem && par2ArrayOfStr.length > 1) {
            String childName = par2ArrayOfStr[1];
            entityplayermp = par2ArrayOfStr.length > 2 ? FamilyCComJFCsoc.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[2]) : FamilyCComJFCsoc.func_71521_c((ICommandSender)par1ICommandSender);
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            boolean allow = true;
            String p1 = FamilyCH.rpfd(server, entityplayermp.func_70005_c_());
            String[] p1d = p1.split(";");
            String c = "";
            for (int i2 = 0; i2 < p1d.length; ++i2) {
                String[] d = p1d[i2].split(":");
                String[] childFamilyData = FamilyCH.rcfd(server, d[0]).split(":");
                if (childFamilyData[2].equalsIgnoreCase(childName)) {
                    int cid = Integer.parseInt(d[0]);
                    String mom = childFamilyData[0];
                    String dad = childFamilyData[1];
                    String cd = FamilyCH.rcfd(server, cid + "");
                    FamilyCH.wcfd(server, "d", cid, true);
                    String pm = FamilyCH.rpfd(server, mom);
                    String[] pmd = pm.split(";");
                    String pmdn = "d";
                    for (int i = 0; i < pmd.length; ++i) {
                        if (pmd[i].equalsIgnoreCase(cid + ":" + dad)) continue;
                        pmdn = pmdn + ";" + pmd[i];
                    }
                    pmdn = pmdn.length() > 1 ? pmdn.substring(2) : pmdn;
                    FamilyCH.wpfd(server, pmdn, mom, pmdn.length() < 2 && pmdn.startsWith("d"));
                    if (!mom.equalsIgnoreCase(dad)) {
                        String pd = FamilyCH.rpfd(server, dad);
                        String[] pdd = pm.split(";");
                        String pddn = "d";
                        for (int i = 0; i < pdd.length; ++i) {
                            if (pdd[i].equalsIgnoreCase(cid + ":" + dad)) continue;
                            pddn = pddn + ";" + pdd[i];
                        }
                        pddn = pddn.length() > 1 ? pddn.substring(2) : pddn;
                        FamilyCH.wpfd(server, pddn, dad, pddn.length() < 2 && pddn.startsWith("d"));
                    }
                }
                c = c + ";" + FamilyCH.rcfd(server, d[0]) + ":" + FamilyCH.rcpd(server, d[0]);
            }
            c = c.substring(1);
        } else {
            EntityPlayerMP entityplayermp2 = FamilyCComJFCsoc.func_71521_c((ICommandSender)par1ICommandSender);
            throw new WrongUsageException("Child Spawned failed.", new Object[0]);
        }
        if (!entityplayermp.getEntityData().func_74764_b("PlayerPersisted")) {
            NBTTagCompound nbt = new NBTTagCompound();
            entityplayermp.getEntityData().func_74782_a("PlayerPersisted", (NBTBase)nbt);
        } else {
            NBTTagCompound nBTTagCompound = entityplayermp.getEntityData().func_74775_l("PlayerPersisted");
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        FamilyCComJFCsoc.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    private static String ntl(int i) {
        return JRMCoreH.numToLet(i);
    }

    private static String ntl5(int i) {
        return JRMCoreH.numToLet5(i);
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/jfc spawn child [childName]";
    }

    public static void setdns() {
        String R = FamilyCComJFCsoc.ntl(dnsRaceSlcted);
        String G = dnsGenderSlcted + "";
        String H1 = FamilyCComJFCsoc.ntl(dnsHairSlcted);
        String H2 = FamilyCComJFCsoc.ntl(dnsHair2Slcted);
        String HC = FamilyCComJFCsoc.ntl5(dnsColorSlcted);
        String BS = dnsBreastSizeSlcted + "";
        String ST = "1";
        String BT = FamilyCComJFCsoc.ntl(dnsBodyTypeSlcted);
        String BCM = FamilyCComJFCsoc.ntl5(dnsBodyColMainSlcted);
        String BC1 = FamilyCComJFCsoc.ntl5(dnsBodyColSub1Slcted);
        String BC2 = FamilyCComJFCsoc.ntl5(dnsBodyColSub2Slcted);
        String BC3 = FamilyCComJFCsoc.ntl5(dnsBodyColSub3Slcted);
        String FN = FamilyCComJFCsoc.ntl(dnsFaceNoseSlcted);
        String FM = FamilyCComJFCsoc.ntl(dnsFaceMouthSlcted);
        String ET = FamilyCComJFCsoc.ntl(dnsEyesSlcted);
        String EC1 = FamilyCComJFCsoc.ntl5(dnsEyeCol1Slcted);
        String EC2 = FamilyCComJFCsoc.ntl5(dnsEyeCol2Slcted);
        dns = R + G + H1 + H2 + HC + BS + ST + BT + BCM + BC1 + BC2 + BC3 + FN + FM + ET + EC1 + EC2;
    }
}

