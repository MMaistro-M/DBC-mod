package JinRyuu.JRMCore;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComJrmctp extends CommandBase {
   private final String usage = "Usage: '/jrmctp amount [playerName]' OR '/jrmctp amount [playerName] [(nbtdata)]' and the amount can be negative too. Maximum TP is 1 000 000 000";

   public String func_71517_b() {
      return "jrmctp";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender par1ICommandSender) {
      return "Usage: '/jrmctp amount [playerName]' OR '/jrmctp amount [playerName] [(nbtdata)]' and the amount can be negative too. Maximum TP is 1 000 000 000";
   }

   public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      if (par2ArrayOfStr.length <= 0) {
         throw new WrongUsageException(
            "Usage: '/jrmctp amount [playerName]' OR '/jrmctp amount [playerName] [(nbtdata)]' and the amount can be negative too. Maximum TP is 1 000 000 000",
            new Object[0]
         );
      }

      boolean hasTag = false;
      String s = par2ArrayOfStr[0];
      String s2 = "";
      long i = Long.parseLong(s);
      if (i > JRMCoreH.getMaxTP()) {
         i = JRMCoreH.getMaxTP();
      }

      if (i < -JRMCoreH.getMaxTP()) {
         i = -JRMCoreH.getMaxTP();
      }

      boolean flag1 = i < 0L;
      EntityPlayerMP entityplayermp;
      if (par2ArrayOfStr.length > 2 && par2ArrayOfStr[2].contains("[") && par2ArrayOfStr[2].contains("]")) {
         s2 = par2ArrayOfStr[2];
         entityplayermp = func_82359_c(par1ICommandSender, par2ArrayOfStr[1]);
         hasTag = true;
      } else if (par2ArrayOfStr.length > 1) {
         entityplayermp = func_82359_c(par1ICommandSender, par2ArrayOfStr[1]);
      } else {
         entityplayermp = func_71521_c(par1ICommandSender);
      }

      if (flag1 && !hasTag) {
         i *= -1L;
      }

      String entitycommansender = "Console";

      try {
         EntityPlayerMP commansender = func_71521_c(par1ICommandSender);
         entitycommansender = commansender.func_70005_c_();
      } catch (Exception var25) {
      }

      boolean n = entitycommansender.equals("Console")
         ? JRMCoreConfig.ComTPNAC
         : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComTPNAS : JRMCoreConfig.ComTPNAO);
      NBTTagCompound nbt = JRMCoreH.nbt(entityplayermp, "pres");
      if (hasTag) {
         String datas = s2.replace("[", "").replace(")]", "").replace("(", "|");
         String[] nbtArray = datas.split("\\)");

         for (int j = 0; j < nbtArray.length; j++) {
            String[] datas3 = nbtArray[j].split("\\|");
            if (nbt.func_74764_b(datas3[1])) {
               byte methods = (byte)(datas3.length / 2);
               String NBTdataS = "";
               boolean doit = true;
               String NBTdataSat = nbt.func_74779_i(datas3[1]);
               boolean numberFound = false;

               for (int k = 0; k < NBTdataSat.length(); k++) {
                  String value = NBTdataSat.substring(k, k + 1);

                  try {
                     int d = Integer.parseInt(value);
                     doit = true;
                     NBTdataS = NBTdataS + d;
                  } catch (Exception e) {
                     if (k != NBTdataSat.length() - 1) {
                        doit = false;
                     }
                  }
               }

               if (NBTdataS.equals("")) {
                  doit = false;
               }

               if (doit) {
                  double NBTdata = Double.parseDouble(NBTdataS);
                  i = this.returnMathL(
                     i,
                     NBTdata,
                     methods,
                     datas3[0],
                     methods > 1 ? datas3[3] : "",
                     methods > 1 ? Double.parseDouble(datas3[2]) : 0.0,
                     methods > 2 ? datas3[4] : "",
                     methods > 2 ? Double.parseDouble(datas3[5]) : 0.0
                  );
               }

               if (i > JRMCoreH.getMaxTP()) {
                  i = JRMCoreH.getMaxTP();
               }

               if (i < -JRMCoreH.getMaxTP()) {
                  i = -JRMCoreH.getMaxTP();
               }
            }
         }

         if (flag1) {
            i *= -1L;
         }
      }

      if (nbt.func_74771_c("jrmcPwrtyp") == 3) {
         JRMCoreH.sao_expgain((int)i, entityplayermp);
         if (n) {
            this.notifyAdmins(par1ICommandSender, "Exp given %s success %s", new Object[]{(int)i, entityplayermp.func_70005_c_()});
         }
      } else if (flag1) {
         int tp = nbt.func_74762_e("jrmcTpint");
         int added = (int)(tp - i);
         boolean b = false;
         if (added < 0) {
            added = 0;
            b = true;
         }

         nbt.func_74768_a("jrmcTpint", added);
         if (n) {
            this.notifyAdmins(par1ICommandSender, "TP take away %s success %s", new Object[]{(int)(b ? i + (tp - i) : i), entityplayermp.func_70005_c_()});
         }
      } else {
         int tp = nbt.func_74762_e("jrmcTpint");
         long result = tp + i;
         if (result > JRMCoreH.getMaxTP()) {
            result = JRMCoreH.getMaxTP();
         }

         int added = (int)result;
         boolean b = false;
         if (added > JRMCoreH.getMaxTP()) {
            added = JRMCoreH.getMaxTP();
            b = true;
         }

         nbt.func_74768_a("jrmcTpint", added);
         if (n) {
            this.notifyAdmins(
               par1ICommandSender,
               "TP adding %s success for %s",
               new Object[]{(int)(b ? i - (tp + i - JRMCoreH.getMaxTP()) : i), entityplayermp.func_70005_c_()}
            );
         }
      }
   }

   private void notifyAdmins(ICommandSender par1iCommandSender, String string, Object[] objects) {
      func_152373_a(par1iCommandSender, this, string, objects);
   }

   public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      return par2ArrayOfStr.length == 2 ? func_71530_a(par2ArrayOfStr, this.getListOfPlayers()) : null;
   }

   protected String[] getListOfPlayers() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }

   public boolean isUsernameIndex(int par1) {
      return par1 == 0;
   }

   private long returnMathL(long tp, double NBT, byte methods, String methodMain, String method1, double n1, String method2, double n2) {
      switch (methods) {
         case 3:
            NBT = this.methodD(method2, NBT, n2);
         case 2:
            NBT = this.methodD(method1, n1, NBT);
         default:
            if (methodMain.equals("+")) {
               tp = (long)(tp + NBT);
            } else if (methodMain.equals("-")) {
               tp = (long)(tp - NBT);
            } else if (methodMain.equals("*")) {
               tp = (long)(tp * NBT);
            } else if (methodMain.equals("/")) {
               tp = (long)(tp / NBT);
            } else if (methodMain.equals("%")) {
               tp = (long)(tp % NBT);
            }

            return tp;
      }
   }

   private double methodD(String method, double n1, double n2) {
      if (method.equals("+")) {
         n1 += n2;
      } else if (method.equals("-")) {
         n1 -= n2;
      } else if (method.equals("*")) {
         n1 *= n2;
      } else if (method.equals("/")) {
         n1 /= n2;
      } else if (method.equals("%")) {
         n1 %= n2;
      }

      return n1;
   }
}
