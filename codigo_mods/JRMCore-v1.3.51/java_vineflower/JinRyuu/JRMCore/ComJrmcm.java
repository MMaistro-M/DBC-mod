package JinRyuu.JRMCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComJrmcm extends CommandBase {
   public String func_71517_b() {
      return "jrmcm";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      if (par2ArrayOfStr.length <= 0) {
         throw new WrongUsageException("/jrmcm (missionFileName or main) (missionID to jump to) [playerName]", new Object[0]);
      }

      String s = par2ArrayOfStr[0];
      EntityPlayerMP entityplayermp;
      if (par2ArrayOfStr.length > 2) {
         entityplayermp = func_82359_c(par1ICommandSender, par2ArrayOfStr[2]);
      } else {
         entityplayermp = func_71521_c(par1ICommandSender);
      }

      String entitycommansender = "Console";

      try {
         EntityPlayerMP commansender = func_71521_c(par1ICommandSender);
         entitycommansender = commansender.func_70005_c_();
      } catch (Exception var32) {
      }

      int a = 0;

      try {
         a = func_71526_a(par1ICommandSender, par2ArrayOfStr[1]);
      } catch (Exception exception) {
         a = 0;
      }

      boolean matc = false;
      NBTTagCompound nbt = JRMCoreH.nbt(entityplayermp, "pres");
      String msd = nbt.func_74779_i("JRMCmissionSync");
      String msdV = nbt.func_74779_i("JRMCmissionSyncVers");
      String msdO = msd;
      String msdVO = msdV;
      HashMap<String, Integer> h = new HashMap<>();
      if (msd.length() > 3) {
         for (int i = 0; i < JRMCoreM.getSydaAmount(msd); i++) {
            int mid = JRMCoreM.getMda_Mission(msd, i);
            String sid = JRMCoreM.getMda_Series(msd, i);
            h.put(sid, mid);
         }
      }

      int pw = nbt.func_74771_c("jrmcPwrtyp");
      int rc = nbt.func_74771_c("jrmcRace");
      int cl = nbt.func_74771_c("jrmcClass");
      if (s.equalsIgnoreCase("main")) {
         s = pw == 1 ? "mainDBC" : (pw == 2 ? "mainNC" : s);
      }

      label147:
      for (String seriesID : JRMCoreM.missions.keySet()) {
         if (s.equalsIgnoreCase(seriesID)) {
            matc = true;
            JRMCoreMsnBundle M = JRMCoreM.missions.get(seriesID);
            List<JRMCoreMsn> msnl = M.getMissions();
            int msnToSendID = a;
            boolean hasSyda = false;
            if (h.containsKey(seriesID)) {
               hasSyda = true;
            }

            for (int j = 0; j < msnl.size(); j++) {
               JRMCoreMsn msn = msnl.get(j);
               if (!hasSyda) {
                  if (msn.getId() == msnToSendID) {
                     ArrayList<String> o = msn.getObjectives(pw, rc, cl);
                     int size = o.size();
                     if (hasSyda) {
                        if (JRMCoreM.getMda_Con(JRMCoreM.getMda(msd, seriesID), size - 1).equals("")) {
                           String[] ar = new String[]{"0"};
                           String[] var45 = new String[size];

                           for (int i = 0; i < size; i++) {
                              var45[i] = "0";
                           }

                           msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, var45);
                           String[] sydaV = JRMCoreM.getMda(msdV, seriesID);
                           String c = sydaV[3];
                           String cv = sydaV[4];
                           msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), "0", c, cv);
                           if (!msd.equalsIgnoreCase(msdO)) {
                              nbt.func_74778_a("JRMCmissionSync", msd);
                           }

                           if (!msdV.equalsIgnoreCase(msdVO)) {
                              nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                           }
                        }
                     } else {
                        String[] ar = new String[size];

                        for (int i = 0; i < ar.length; i++) {
                           ar[i] = "0";
                        }

                        msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, ar);
                        String[] sydaV = JRMCoreM.getMda(msdV, seriesID);
                        String c = sydaV[3];
                        String cv = sydaV[4];
                        msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), "0", c, cv);
                        if (!msd.equalsIgnoreCase(msdO)) {
                           nbt.func_74778_a("JRMCmissionSync", msd);
                        }

                        if (!msdV.equalsIgnoreCase(msdVO)) {
                           nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                        }
                     }
                     break label147;
                  }
               } else if (msn.getId() == msnToSendID) {
                  ArrayList<String> o = msn.getObjectives(pw, rc, cl);
                  int size = o.size();
                  String[] ar = new String[]{"0"};
                  String[] arx = new String[size];

                  for (int i = 0; i < size; i++) {
                     arx[i] = "0";
                  }

                  msd = JRMCoreM.setSyda(msd, seriesID, msnToSendID, arx);
                  String[] sydaV = JRMCoreM.getMda(msdV, seriesID);
                  String c = sydaV[3];
                  String cv = sydaV[4];
                  msdV = JRMCoreM.setSydaV(msdV, seriesID, M.getVersion(), "0", c, cv);
                  if (!msd.equals(msdO)) {
                     nbt.func_74778_a("JRMCmissionSync", msd);
                  }

                  if (!msdV.equals(msdVO)) {
                     nbt.func_74778_a("JRMCmissionSyncVers", msdV);
                  }
               }
            }
            break;
         }
      }

      boolean n = par1ICommandSender.equals("Console")
         ? JRMCoreConfig.ComANAC
         : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComANAS : JRMCoreConfig.ComANAO);
      if (n) {
         if (!matc) {
            throw new WrongUsageException("Mission change failure", new Object[0]);
         }

         this.notifyAdmins(par1ICommandSender, "Mission has changed for " + s + " to id " + a, new Object[]{entityplayermp.func_70005_c_()});
      }
   }

   private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
      func_152373_a(par1iCommandSender, this, string, objects);
   }

   public String func_71518_a(ICommandSender icommandsender) {
      return "/jrmcm (missionFileName or main) (missionID to jump to) [playerName]";
   }

   public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      return par2ArrayOfStr.length == 2 ? func_71530_a(par2ArrayOfStr, this.getListOfPlayers()) : null;
   }

   protected String[] getListOfPlayers() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean isUsernameIndex(int par1) {
      return par1 == 2;
   }
}
