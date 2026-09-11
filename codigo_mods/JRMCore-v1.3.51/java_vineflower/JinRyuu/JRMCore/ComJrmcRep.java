package JinRyuu.JRMCore;

import JinRyuu.JRMCore.i.ExtendedPlayer;
import java.util.HashMap;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

public class ComJrmcRep extends CommandBase {
   public static HashMap<String, Object[]> SList = new HashMap<>();

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
      String entitycommansender = "Console";

      try {
         EntityPlayerMP commansender = func_71521_c(par1ICommandSender);
         entitycommansender = commansender.func_70005_c_();
      } catch (Exception var7) {
      }

      EntityPlayerMP entityplayermp;
      if (par2ArrayOfStr.length > 0) {
         entityplayermp = func_82359_c(par1ICommandSender, par2ArrayOfStr[2]);
      } else {
         entityplayermp = func_71521_c(par1ICommandSender);
      }

      NBTTagCompound nbt = JRMCoreH.nbt(entityplayermp, "pres");
      if (entityplayermp != null) {
         for (int i = 0; i < entityplayermp.field_71071_by.field_70460_b.length; i++) {
            if (entityplayermp.field_71071_by.field_70460_b[i] != null) {
               entityplayermp.field_71071_by.field_70460_b[i].func_77964_b(0);
            }
         }

         for (int i = 0; i < 11; i++) {
            if (ExtendedPlayer.get(entityplayermp).inventory.func_70301_a(i) != null) {
               ExtendedPlayer.get(entityplayermp).inventory.func_70301_a(i).func_77964_b(0);
            }
         }
      }

      boolean n = entitycommansender.equals("Console")
         ? JRMCoreConfig.ComHealNAC
         : (entitycommansender.equals(entityplayermp.func_70005_c_()) ? JRMCoreConfig.ComHealNAS : JRMCoreConfig.ComHealNAO);
      if (n) {
         this.notifyAdmins(par1ICommandSender, "%s -> all equiped items were fixed!", new Object[]{entityplayermp.func_70005_c_()});
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
}
