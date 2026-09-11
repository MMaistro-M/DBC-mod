/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreM;
import JinRyuu.JRMCore.JRMCoreMsn;
import JinRyuu.JRMCore.JRMCoreMsnBundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComJrmcm
extends CommandBase {
    public String func_71517_b() {
        return "jrmcm";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        boolean n;
        if (par2ArrayOfStr.length <= 0) {
            throw new WrongUsageException("/jrmcm (missionFileName or main) (missionID to jump to) [playerName]", new Object[0]);
        }
        String s = par2ArrayOfStr[0];
        EntityPlayerMP entityplayermp = par2ArrayOfStr.length > 2 ? ComJrmcm.func_82359_c((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[2]) : ComJrmcm.func_71521_c((ICommandSender)par1ICommandSender);
        String entitycommansender = "Console";
        try {
            EntityPlayerMP commansender = ComJrmcm.func_71521_c((ICommandSender)par1ICommandSender);
            entitycommansender = commansender.func_70005_c_();
        }
        catch (Exception commansender) {
            // empty catch block
        }
        int a = 0;
        try {
            a = ComJrmcm.func_71526_a((ICommandSender)par1ICommandSender, (String)par2ArrayOfStr[1]);
        }
        catch (Exception exception) {
            a = 0;
        }
        boolean matc = false;
        NBTTagCompound nbt = JRMCoreH.nbt((Entity)entityplayermp, "pres");
        String msd = nbt.func_74779_i("JRMCmissionSync");
        String msdV = nbt.func_74779_i("JRMCmissionSyncVers");
        String msdO = msd;
        String msdVO = msdV;
        HashMap<String, Integer> h = new HashMap<String, Integer>();
        if (msd.length() > 3) {
            for (int i = 0; i < JRMCoreM.getSydaAmount(msd); ++i) {
                int mid = JRMCoreM.getMda_Mission(msd, i);
                String sid = JRMCoreM.getMda_Series(msd, i);
                h.put(sid, mid);
            }
        }
        byte pw = nbt.func_74771_c("jrmcPwrtyp");
        byte rc = nbt.func_74771_c("jrmcRace");
        byte cl = nbt.func_74771_c("jrmcClass");
        if (s.equalsIgnoreCase("main")) {
            s = pw == 1 ? "mainDBC" : (pw == 2 ? "mainNC" : s);
        }
        block5: for (String seriesID : JRMCoreM.missions.keySet()) {
            if (!s.equalsIgnoreCase(seriesID)) continue;
            matc = true;
            JRMCoreMsnBundle M = JRMCoreM.missions.get(seriesID);
            ArrayList<JRMCoreMsn> msnl = M.getMissions();
            int msnToSendID = a;
            boolean hasSyda = false;
            if (h.containsKey(seriesID)) {
                hasSyda = true;
            }
            for (int j = 0; j < msnl.size(); ++j) {
                String cv;
                String c;
                String[] ar;
                int size;
                ArrayList<String> o;
                JRMCoreMsn msn = (JRMCoreMsn)msnl.get(j);
                if (hasSyda) {
                    if (msn.getId() != msnToSendID) continue;
                    o = msn.getObjectives(pw, rc, cl);
                    size = o.size();
                    ar = new String[]{"0"};
                    ar = new String[size];
                    for (int i = 0; i < size; ++i) {
                        ar[i] = "0";
                    }
                    msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, ar);
                    String[] sydaV = JRMCoreM.getMda(msdV, seriesID);
                    c = sydaV[3];
                    cv = sydaV[4];
                    msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), "0", c, cv);
                    if (!msd.equals(msdO)) {
                        nbt.func_74778_a("JRMCmissionSync", msd);
                    }
                    if (msdV.equals(msdVO)) continue;
                    nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                    continue;
                }
                if (msn.getId() != msnToSendID) continue;
                o = msn.getObjectives(pw, rc, cl);
                size = o.size();
                if (hasSyda) {
                    if (!JRMCoreM.getMda_Con(JRMCoreM.getMda(msd, seriesID), size - 1).equals("")) break block5;
                    ar = new String[]{"0"};
                    ar = new String[size];
                    for (int i = 0; i < size; ++i) {
                        ar[i] = "0";
                    }
                    msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, ar);
                    String[] sydaV = JRMCoreM.getMda(msdV, seriesID);
                    c = sydaV[3];
                    cv = sydaV[4];
                    msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), "0", c, cv);
                    if (!msd.equalsIgnoreCase(msdO)) {
                        nbt.func_74778_a("JRMCmissionSync", msd);
                    }
                    if (msdV.equalsIgnoreCase(msdVO)) break block5;
                    nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                    break block5;
                }
                ar = new String[size];
                for (int i = 0; i < ar.length; ++i) {
                    ar[i] = "0";
                }
                msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, ar);
                String[] sydaV = JRMCoreM.getMda(msdV, seriesID);
                c = sydaV[3];
                cv = sydaV[4];
                msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), "0", c, cv);
                if (!msd.equalsIgnoreCase(msdO)) {
                    nbt.func_74778_a("JRMCmissionSync", msd);
                }
                if (msdV.equalsIgnoreCase(msdVO)) break block5;
                nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                break block5;
            }
        }
        boolean bl = par1ICommandSender.equals("Console") ? JRMCoreConfig.ComANAC : (n = entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComANAS : JRMCoreConfig.ComANAO);
        if (n) {
            if (matc) {
                this.notifyAdmins(par1ICommandSender, "Mission has changed for " + s + " to id " + a, new Object[]{entityplayermp.func_70005_c_()});
            } else {
                throw new WrongUsageException("Mission change failure", new Object[0]);
            }
        }
    }

    private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
        ComJrmcm.func_152373_a((ICommandSender)par1iCommandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/jrmcm (missionFileName or main) (missionID to jump to) [playerName]";
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 2 ? ComJrmcm.func_71530_a((String[])par2ArrayOfStr, (String[])this.getListOfPlayers()) : null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }

    public boolean isUsernameIndex(int par1) {
        return par1 == 2;
    }
}

